package com.ticket.serviceImpl;


import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjComment;
import com.ticket.pojo.BjdjCommentItem;
import com.ticket.pojo.BjdjOrder;
import com.ticket.pojo.BjdjServiceCode;
import com.ticket.pojo.SystemDictionary;
import com.ticket.pojo.SystemUser;
import com.ticket.service.IBjdjCommentKeywordStatisticsService;
import com.ticket.service.IBjdjCommentService;
import com.ticket.service.IBjdjServiceCodeService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.ExcelUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.ResourceUtil;

/**
 * 便捷登机评论表业务接口实现类
 * @ClassName: IBjdjCommentService   
 * @Description: 提供便捷登机评论表操作的增删改查   
 * @author HiSay  
 * @date 2015-10-23 15:24:09
 *
 */
public class BjdjCommentServiceImpl extends BaseServiceImpl<BjdjComment, String> implements IBjdjCommentService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(BjdjCommentServiceImpl.class);
	@Resource
	private IBjdjCommentKeywordStatisticsService keywordStatisticsService;
	@Resource
	private IBjdjServiceCodeService serviceCodeService;
	@Resource
	private ISystemDictionaryService dictionaryService;
	
	@Override
	public BjdjComment persist(String content,String serviceCode_id,String ip,Integer star,String reason,String images) throws ServiceException {
		
		BjdjComment bjdjComment = new BjdjComment();
		bjdjComment.setContent(DecoderUtil.UtfDecoder(content));
		bjdjComment.setServiceCode(new BjdjServiceCode(serviceCode_id));
		bjdjComment.setIp(ip);
		bjdjComment.setStar(star);
		bjdjComment.setReason(reason);
		bjdjComment.setImages(images);
		bjdjComment.setMember(getMember());
		
		keywordStatisticsService.addContent(content);
		dbDAO.persist(bjdjComment);
		return bjdjComment;
	}
	
	@Override
	public boolean feedback(String id,SystemUser user,String feedback) throws ServiceException {
		
		BjdjComment comment = dbDAO.queryById(this.getTableNameFromEntity(BjdjComment.class), id);
		comment.setFeedback_user(user);
		comment.setFeedback(feedback);
		comment.setFeedbackTime(new Date());
		dbDAO.merge(comment);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		
		BjdjComment commtent = get(BjdjComment.class, id);
		if(commtent.getContent() != null){
			
			keywordStatisticsService.removeContent(commtent.getContent());
		}
		batchRealDelete(BjdjComment.class, id);
		return true;
	}

	@Override
	public long count() throws Exception {
		
		long count = (Long)dbDAO.executeJPQLForQuerySingle("select count(*) from " + BjdjComment.class.getName());
		return count;
	}
	
	@Override
	public List<BjdjComment> queryByOrder(String order_id) {
		
		List<BjdjComment> comments = dbDAO.executeJPQLForQueryEntity(
				"select c from " + BjdjComment.class.getName() + " c"
				+ " where order=?", new BjdjOrder(order_id));
		return comments;
	}

	@Override
	public List<BjdjCommentItem> queryCommentItems(String comment_id) {
		
		List<Object> list = dbDAO.executeJPQLForQuery(
				"select c from " + BjdjCommentItem.class.getName() + " c"
				+ " where comment=?", new BjdjComment(comment_id));
		
		List<BjdjCommentItem> items = new ArrayList<BjdjCommentItem>();
		for(int i = 0; i < list.size(); i++){
			
			items.add((BjdjCommentItem)list.get(i));
		}
		return items;
	}
	
	@Override
	public List<BjdjCommentItem> queryCommentItems(BjdjComment comment) {
		
		List<Object> list = dbDAO.executeJPQLForQuery(
				"select c from " + BjdjCommentItem.class.getName() + " c"
						+ " where comment=?", comment);
		
		List<BjdjCommentItem> items = new ArrayList<BjdjCommentItem>();
		for(int i = 0; i < list.size(); i++){
			
			items.add((BjdjCommentItem)list.get(i));
		}
		return items;
	}

	@Override
	public void generateReport(OutputStream out) {

		//***********************以下为获取数据************************
		List<SystemDictionary> items = dictionaryService.querySubByParentName("evaluate_rule");
		List<SystemDictionary> params = new LinkedList<SystemDictionary>();
		
		StringBuffer sql = new StringBuffer("select sc.code,");
		sql.append(" sca.useTime,");
		sql.append(" m.phone,");
		sql.append(" t.star,");
		sql.append(" t.content,");
		for(SystemDictionary dict : items){
			
			sql.append(" (select i.star from ").append(BjdjCommentItem.class.getName()).append(" i where i.comment=t and i.setting=?),");
			sql.append(" (select i.reason from ").append(BjdjCommentItem.class.getName()).append(" i where i.comment=t and i.setting=?),");
			params.add(dict);
			params.add(dict);
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(" from " + BjdjComment.class.getName() + " t");
		sql.append(" left join t.serviceCode sc");
		sql.append(" left join sc.appointment sca");
		sql.append(" left join t.member m");
		List<Object> data = dbDAO.executeJPQLForQuery(sql.toString(), params.toArray());
		
		
		//***********************以下为生成excel************************
		@SuppressWarnings("deprecation")
		String templatePath = ServletActionContext.getRequest().getRealPath("/WEB-INF/excel/template/bjdjEvaluation.xlsx");
		ExcelUtil util = new ExcelUtil();
		try {
			util.readExcel(templatePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Sheet sheet = util.readSheet(0);
		for(int i = 0; i < items.size(); i++){
			
			util.readRow(0);
			int colIndex = 5 + i * 2;
			CellRangeAddress rangeAddress = new CellRangeAddress(0, 0, colIndex, colIndex + 1);
			sheet.addMergedRegion(rangeAddress);
			util.write(colIndex, items.get(i).getValue());
			
			util.readRow(1);
			util.write(colIndex, "星级");
			util.write(colIndex + 1, "低于4星原因");
		}
		for(int i = 0; i < data.size(); i++){
			
			Object[] rowData = (Object[])data.get(i);
			util.readRow(i + 2);
			for(int j = 0; j < rowData.length; j++){
				
				util.write(j, rowData[j]);
			}
		}
		util.close();
		util.save(out);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public BjdjComment addComment(String content, String serviceCode_id,
			String ip, Integer star, String images, boolean showName,
			String[] ruleId, String[] rule, String[] reason) throws ServiceException {
		
		//非空验证.
		if(GeneralUtil.isNull(content)) {
			
			throw new ServiceException(ResourceUtil.getText("comment.content.required"));
		}
		if(GeneralUtil.isNull(star)){

			throw new ServiceException(ResourceUtil.getText("star.required"));
		}
		if(GeneralUtil.isNull(showName)){

			throw new ServiceException(ResourceUtil.getText("showName.required"));
		}
		if(GeneralUtil.isNull(serviceCode_id)){
			
			throw new ServiceException(ResourceUtil.getText("serviceCode.id.required"));
		}
		
		BjdjServiceCode serviceCode = serviceCodeService.queryById(BjdjServiceCode.class.getName(), serviceCode_id);
		//服务码是否为空
		if(serviceCode == null){
			
			throw new ServiceException(ResourceUtil.getText("serviceCode.notExist", ""));
			
		}else if(!serviceCodeService.isMine(serviceCode)){
		
			//检测是否是本人的订单，否则没有权限评论
			throw new ServiceException(ResourceUtil.getText("comment.authNotEnough"));
		}
		//查看订单是否评论
		if("commented".equals(serviceCode.getState().getName())){
			
			throw new ServiceException(ResourceUtil.getText("commented"));
		}
		
		//保存便捷登机评论表实体
		BjdjComment bjdjComment = persist(content, serviceCode_id, ip, star, null, images);
		//根据保存结果返回页面
		if(bjdjComment == null) {
			
			throw new ServiceException(ResourceUtil.getText("addFailed"));
		}
		bjdjComment.setShowName(showName);
		merge(bjdjComment);
		
		//保存自定义评论项
		for(int i = 0; ruleId != null && i < ruleId.length; i++){
			
			BjdjCommentItem item = new BjdjCommentItem();
			item.setStar(Integer.parseInt(rule[i].trim()));
			item.setComment(bjdjComment);
			item.setReason(reason[i].trim());
			SystemDictionary dict = dictionaryService.get(SystemDictionary.class, ruleId[i].trim());
			item.setContent(dict.getValue());
			item.setSetting(dict);
			persist(item);
		}
		
		SystemDictionary commented = dictionaryService.getByName("commented");
		serviceCode.setState(commented);
		serviceCodeService.merge(serviceCode);
		
		return bjdjComment;
	}
}