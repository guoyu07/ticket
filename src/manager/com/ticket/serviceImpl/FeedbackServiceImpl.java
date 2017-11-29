package com.ticket.serviceImpl;


import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.Feedback;
import com.ticket.pojo.FeedbackReply;
import com.ticket.pojo.Member;
import com.ticket.pojo.Pagination;
import com.ticket.service.IFeedbackReplyService;
import com.ticket.service.IFeedbackService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.PaginationContext;

/**
 * 公测反馈业务接口实现类
 * @ClassName: IFeedbackService   
 * @Description: 提供公测反馈操作的增删改查   
 * @author HiSay  
 * @date 2016-08-15 15:10:43
 *
 */
public class FeedbackServiceImpl extends BaseServiceImpl<Feedback, String> implements IFeedbackService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(FeedbackServiceImpl.class);
	@Resource
	IFeedbackReplyService feedbackReplyService;

	@Override
	public boolean persist(Member member,String description,String phone,String images, String versionFlag) throws ServiceException {
		Feedback feedback = new Feedback();
		feedback.setMember(member);
		feedback.setDescription(DecoderUtil.UtfDecoder(description));
		feedback.setPhone(DecoderUtil.UtfDecoder(phone));
		feedback.setImages(images);
		CommonEntity status = feedback.getStatus();
		status.setVersionFlag(versionFlag);
		feedback.setStatus(status);
		dbDAO.persist(feedback);
		return true;
	}
	
	@Override
	public boolean merge(String id, Member member,String description,String phone,String images, String versionFlag) throws ServiceException {
		Feedback feedback = dbDAO.queryById(this.getTableNameFromEntity(Feedback.class), id);
		feedback.setMember(member);
		feedback.setDescription(DecoderUtil.UtfDecoder(description));
		feedback.setPhone(DecoderUtil.UtfDecoder(phone));
		feedback.setImages(images);
		CommonEntity status = feedback.getStatus();
		status.setVersionFlag(versionFlag);
		feedback.setStatus(status);
		dbDAO.merge(feedback);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		Feedback feedback = dbDAO.queryById(this.getTableNameFromEntity(Feedback.class), id);
		dbDAO.remove(feedback);
		return true;
	}

	@Override
	public List<Feedback> queryByMember(Member member) throws ServiceException {
		List<Feedback> feedbacks = dbDAO.executeJPQLForQuery("select c from " + Feedback.class.getName() + " c where c.member.id = ? order by c.status.createTime desc", Feedback.class ,member.getId());
		return feedbacks;
	}

	@Override
	public long count(Member member) throws ServiceException {
		long count = dbDAO.executeJPQLForQuerySingle("select count(*) from " + Feedback.class.getName() + " c where c.member.id = ?",Long.class ,member.getId());
		return count;
	}

	@Override
	public Pagination queryEntityByAdmin(Date startTime, Date endTime,
			String versionFlag, Integer page) throws ServiceException {
		try {
			//先获取当前的实体名称
			String entityName = this.getTableNameFromEntity(this.getClass());
			
			StringBuffer whereCondition = new StringBuffer();
			int index = 3;
			List<Object> params = new ArrayList<Object>();
			if(GeneralUtil.isNotNull(startTime)){
				
				whereCondition.append(" and s.status.createTime >= ?");
				whereCondition.append(index++);
				params.add(startTime);
				
				/*whereCondition.append(") and MONTH(s.status.createTime) = MONTH(?");
				whereCondition.append(index++);
				params.add(startTime);
				
				whereCondition.append(") and DAY(s.status.createTime) = DAY(?");
				whereCondition.append(index++);
				whereCondition.append(")");
				params.add(startTime);*/
			}
			if(GeneralUtil.isNotNull(endTime)){
				whereCondition.append(" and s.status.createTime <= ?");
				whereCondition.append(index++);
				params.add(startTime);
				
				/*whereCondition.append(" and YEAR(s.status.createTime) = YEAR(?");
				whereCondition.append(index++);
				params.add(endTime);
				
				whereCondition.append(") and MONTH(s.status.createTime) = MONTH(?");
				whereCondition.append(index++);
				params.add(endTime);
				
				whereCondition.append(") and DAY(s.status.createTime) = DAY(?");
				whereCondition.append(index++);
				whereCondition.append(")");
				params.add(endTime);*/
			}
			return dbDAO.queryByPageModule(entityName, deleteFlag, versionFlag, whereCondition.toString(), params.toArray(), orderBy, PaginationContext.getOffset(), page);
		} catch (Exception e) {
			log.info("查询实体列表出错: " + e.fillInStackTrace());
			return null;
		}
	}

	@Override
	public List<Feedback> queryAllByTime(Date startTime, Date endTime)
			throws ServiceException {
		List<Feedback> feedbacks = null;
		if(GeneralUtil.isNotNull(startTime) && GeneralUtil.isNull(endTime)){
			feedbacks = dbDAO.executeJPQLForQuery("select c from " + Feedback.class.getName() + " c where c.status.createTime >= ?", Feedback.class,startTime);
		}
		if(GeneralUtil.isNotNull(startTime) && GeneralUtil.isNotNull(endTime)){
			feedbacks = dbDAO.executeJPQLForQuery("select c from " + Feedback.class.getName() + " c where c.status.createTime >= ? and c.status.createTime <= ?", Feedback.class,startTime,endTime);
		}
		if(GeneralUtil.isNull(startTime) && GeneralUtil.isNotNull(endTime)){
			feedbacks = dbDAO.executeJPQLForQuery("select c from " + Feedback.class.getName() + " c where  c.status.createTime <= ?", Feedback.class,endTime);
		}
		if(GeneralUtil.isNull(startTime) && GeneralUtil.isNull(endTime)){
			feedbacks = this.queryAll(Feedback.class);
		}
		return feedbacks;
	}

	@Override
	public String createJxls(Date startTime, Date endTime)
			throws ServiceException {
		List<Feedback> list = queryAllByTime(startTime,endTime);
		if(GeneralUtil.isNotNull(list)){
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("反馈信息");
			
			HSSFRow row = sheet.createRow(0); 
		    HSSFCellStyle style = wb.createCellStyle();   
		    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    
		    HSSFCell cell = row.createCell(0);     //第一个单元格 
		    cell.setCellValue("反馈时间");         //设定值 
		    cell.setCellStyle(style);          //内容居中 
		      
		    cell = row.createCell(1);          //第二个单元格   
		    cell.setCellValue("联系方式"); 
		    cell.setCellStyle(style); 
		      
		    cell = row.createCell(2);          //第三个单元格  
		    cell.setCellValue("反馈内容"); 
		    cell.setCellStyle(style); 
		      
//		    cell = row.createCell(3);          //第四个单元格  
//		    cell.setCellValue("图片"); 
//		    cell.setCellStyle(style); 
		    
		    for (int i = 0; i < list.size(); i++) { 
		    	Feedback feedback = list.get(i);
		    	row = sheet.createRow(i+1); 
		    	
		    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    	String date = sdf.format(feedback.getStatus().getCreateTime());
		    	row.createCell(0).setCellValue(date); 
		        row.createCell(1).setCellValue(feedback.getPhone()); 
		        row.createCell(2).setCellValue(feedback.getDescription()); 
//		        row.createCell(3).setCellValue(feedback.getImages()); 
		    }
		    String rootPath = ServletActionContext.getRequest().getRealPath("/manager");
		    String srcFilePath = rootPath + "/memberExcel/feedback.xls";
		    try { 
		        FileOutputStream fout = new FileOutputStream(srcFilePath); 
		        wb.write(fout); 
		        fout.close(); 
		      } catch (IOException e) { 
		        e.printStackTrace(); 
		      } 
			
			/*String rootPath = ServletActionContext.getRequest().getRealPath("/manager");
			String srcFilePath = rootPath + "/memberExcel/feedback.xls";  
			String destFilePath = rootPath + "/memberExcelTemplate/feedback.xls";
			Map<String, List<Feedback>> map = new HashMap<String, List<Feedback>>();
			map.put("feedbacks", feedbacks);
			XLSTransformer former = new XLSTransformer();
			try {
				former.transformXLS(srcFilePath, map,destFilePath);
			} catch (ParsePropertyException e) {
				e.printStackTrace();
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return destFilePath;*/
		    return srcFilePath;
		}else{
			return null;
		}
	}

	@Override
	public Integer queryByMember() throws ServiceException {
		List<Feedback> feedbacks = dbDAO.executeJPQLForQuery("select c from " + Feedback.class.getName() + " c where c.member = ? and c.status.deleteFlag=0", Feedback.class, getMember());
		int count = 0;
		if(GeneralUtil.isNotNull(feedbacks)){
			for(Feedback feedback:feedbacks){
				List<FeedbackReply> feedbackReplies = feedbackReplyService.queryByFeedbackId(feedback.getId());
				FeedbackReply feedbackReply = feedbackReplies.get(feedbackReplies.size() - 1);
				if(GeneralUtil.isNotNull(feedbackReply.getSystemUser()) && !feedbackReply.isRend()){
					count++;
				}
			}
		}
		return count;
	}

}