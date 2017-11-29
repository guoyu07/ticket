package com.ticket.serviceImpl;


import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.GoodsLostPostions;
import com.ticket.pojo.LostGoodsInfo;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.ReceiveRecord;
import com.ticket.pojo.SystemDictionary;
import com.ticket.service.IGoodsLostPostionsService;
import com.ticket.service.ILostGoodsInfoService;
import com.ticket.service.IReceiveRecordService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.util.DateUtil;
import com.ticket.util.DecoderUtil;
import com.ticket.util.FileUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.PaginationContext;

/**
 * 遗失物品信息业务接口实现类
 * @ClassName: ILostGoodsInfoService   
 * @Description: 提供遗失物品信息操作的增删改查   
 * @author HiSay  
 * @date 2015-11-23 16:17:30
 *
 */
public class LostGoodsInfoServiceImpl extends BaseServiceImpl<LostGoodsInfo, String> implements ILostGoodsInfoService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(LostGoodsInfoServiceImpl.class);
	@Resource
	private ISystemDictionaryService dictionaryService;
	@Resource
	private IReceiveRecordService recordService;
	@Resource
	private IGoodsLostPostionsService goodsLostPostionsService;
	
	@Override
	public boolean merge(String id, String type_id,String name,String goodsId,Double weight,String color,String otherInfoOne,String otherInfoTwo,String pickerName,String pickerPhone,String pickUpTime,String pickPosition_id,String goodsDescript,String remark,String operator_id,String commitTime,String stockPosition, String goodsNumber,String versionFlag) throws ServiceException {
		LostGoodsInfo lostGoodsInfo = dbDAO.queryById(this.getTableNameFromEntity(LostGoodsInfo.class), id);
		lostGoodsInfo.setType_id(DecoderUtil.UtfDecoder(type_id));
		if(GeneralUtil.isNull(lostGoodsInfo.getGoodsId())){
			lostGoodsInfo.setGoodsId(GeneralUtil.getRandomId());
		}
		lostGoodsInfo.setName(name);
		lostGoodsInfo.setWeight(weight);
		lostGoodsInfo.setColor(DecoderUtil.UtfDecoder(color));
		lostGoodsInfo.setOtherInfoOne(DecoderUtil.UtfDecoder(otherInfoOne));
		lostGoodsInfo.setOtherInfoTwo(DecoderUtil.UtfDecoder(otherInfoTwo));
		lostGoodsInfo.setPickerName(DecoderUtil.UtfDecoder(pickerName));
		lostGoodsInfo.setPickerPhone(DecoderUtil.UtfDecoder(pickerPhone));
		lostGoodsInfo.setPickUpTime(DateUtil.parseStringToDate(pickUpTime, "yyyy-MM-dd HH:mm"));
		lostGoodsInfo.setPickPosition_id(DecoderUtil.UtfDecoder(pickPosition_id));
		lostGoodsInfo.setGoodsDescript(DecoderUtil.UtfDecoder(goodsDescript));
		lostGoodsInfo.setRemark(DecoderUtil.UtfDecoder(remark));
		lostGoodsInfo.setOperator_id(DecoderUtil.UtfDecoder(operator_id));
		lostGoodsInfo.setCommitTime(DecoderUtil.UtfDecoder(commitTime));
		lostGoodsInfo.setStockPosition(DecoderUtil.UtfDecoder(stockPosition));
		lostGoodsInfo.setGoodsNumber(DecoderUtil.UtfDecoder(goodsNumber));
		CommonEntity status = lostGoodsInfo.getStatus();
		status.setVersionFlag(versionFlag);
		lostGoodsInfo.setStatus(status);
		dbDAO.merge(lostGoodsInfo);
		return true;
	}

	@Override
	public boolean persist(String type_id,String name,String goodsId,Double weight,String color,String otherInfoOne,String otherInfoTwo,String pickerName,String pickerPhone,String pickUpTime,String pickPosition_id,String goodsDescript,String remark,String operator_id,String commitTime,String stockPosition,String goodsNumber, String versionFlag) throws ServiceException {
		LostGoodsInfo lostGoodsInfo = new LostGoodsInfo();
		lostGoodsInfo.setType_id(DecoderUtil.UtfDecoder(type_id));
		lostGoodsInfo.setGoodsId(GeneralUtil.getRandomId());
		lostGoodsInfo.setName(name);
		lostGoodsInfo.setWeight(weight);
		lostGoodsInfo.setColor(DecoderUtil.UtfDecoder(color));
		lostGoodsInfo.setOtherInfoOne(DecoderUtil.UtfDecoder(otherInfoOne));
		lostGoodsInfo.setOtherInfoTwo(DecoderUtil.UtfDecoder(otherInfoTwo));
		lostGoodsInfo.setPickerName(DecoderUtil.UtfDecoder(pickerName));
		lostGoodsInfo.setPickerPhone(DecoderUtil.UtfDecoder(pickerPhone));
		lostGoodsInfo.setPickUpTime(DateUtil.parseStringToDate(pickUpTime, "yyyy-MM-dd HH:mm"));
		lostGoodsInfo.setPickPosition_id(DecoderUtil.UtfDecoder(pickPosition_id));
		lostGoodsInfo.setGoodsDescript(DecoderUtil.UtfDecoder(goodsDescript));
		lostGoodsInfo.setRemark(DecoderUtil.UtfDecoder(remark));
		lostGoodsInfo.setOperator_id(DecoderUtil.UtfDecoder(operator_id));
		lostGoodsInfo.setCommitTime(DecoderUtil.UtfDecoder(commitTime));
		lostGoodsInfo.setStockPosition(DecoderUtil.UtfDecoder(stockPosition));
		lostGoodsInfo.setGoodsNumber(DecoderUtil.UtfDecoder(goodsNumber));
		CommonEntity status = lostGoodsInfo.getStatus();
		status.setVersionFlag(versionFlag);
		lostGoodsInfo.setStatus(status);
		dbDAO.persist(lostGoodsInfo);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		LostGoodsInfo lostGoodsInfo = dbDAO.queryById(this.getTableNameFromEntity(LostGoodsInfo.class), id);
		dbDAO.remove(lostGoodsInfo);
		return true;
	}

	@Override
	public Pagination queryByKeyword(String keyword, String versionFlag,
			int pageSize) throws ServiceException {
		return dbDAO.queryByPageModule(LostGoodsInfo.class.getSimpleName(), deleteFlag, versionFlag, "and s.goodsDescript like ?3", new Object[]{keyword}, orderBy, PaginationContext.getOffset(), pageSize);
	}

	@Override
	public Pagination queryEntityByCommendAndHot(String versionFlag, int pageSize) throws ServiceException {
		return dbDAO.queryByPageModule(LostGoodsInfo.class.getSimpleName(), deleteFlag, versionFlag, "and s.status.commend =0 and s.status.hot=1", null, orderBy, PaginationContext.getOffset(), pageSize);
	}

	@Override
	public boolean batchRealDelete(String idsValue, String versionFlag)
			throws ServiceException {
		idsValue = GeneralUtil.convertIdsValueToStringByDelete(idsValue, ",");
		dbDAO.deleteAll(LostGoodsInfo.class.getSimpleName(), idsValue, null);
		return true;
	}

	@Override
	public List<LostGoodsInfo> queryByFront(String pickUpTime, String pickPosition_id, String type_id, String color,
			String goodsDescript, String versionFlag) throws ServiceException {
		String tableName = this.getTableNameFromEntity(LostGoodsInfo.class);
		String sql = "select t from "+tableName+" t where t.type_id ="+"'"+type_id+"'"+" and t.status.commend=0 and t.status.hot=1";
		if(GeneralUtil.isNotNull(pickPosition_id)){
			sql += " and t.pickPosition_id ="+"'"+pickPosition_id+"'";
		}
		if(GeneralUtil.isNotNull(pickUpTime)){
			 sql += " and t.pickUpTime between '"+pickUpTime+" 00:00:00' and '"+pickUpTime+" 23:59:59'";
		}
		if(GeneralUtil.isNotNull(color)){
			sql += " and t.color ="+"'"+color+"'";
		}
		if(GeneralUtil.isNotNull(goodsDescript)){
			String tempSql = sql+" and t.goodsDescript like "+"'%"+DecoderUtil.UtfDecoder(goodsDescript)+"%'";
			List<LostGoodsInfo> templist = dbDAO.executeJPQLForQueryEntity(tempSql);
			if(templist != null && !templist.isEmpty()){
				return templist;
			}
		}
		List<LostGoodsInfo> list = dbDAO.executeJPQLForQueryEntity(sql);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public Pagination queryEntityByAdmin(String goodsNumber,String name, String color,
			String type_id, String pickPosition_id, Date startTime,
			Date endTime, String versionFlag, Integer pageSize)
			throws ServiceException {
		try{
			String entityName = this.getTableNameFromEntity(this.getClass());
			
			StringBuffer whereCondition = new StringBuffer();
			int index = 3;
			List<Object> params = new ArrayList<Object>();
			if(GeneralUtil.isNotNull(goodsNumber)){
				whereCondition.append(" and s.goodsNumber = ?");
				whereCondition.append(index++);
				params.add(goodsNumber);
			}
			if(GeneralUtil.isNotNull(name)){
				whereCondition.append(" and s.name like ?");
				whereCondition.append(index++);
				params.add("%"+name+"%");
			}
			if(GeneralUtil.isNotNull(color)){
				whereCondition.append(" and s.color = ?");
				whereCondition.append(index++);
				params.add(color);
			}
			if(GeneralUtil.isNotNull(type_id)){
				whereCondition.append(" and s.type_id = ?");
				whereCondition.append(index++);
				params.add(type_id);
			}
			if(GeneralUtil.isNotNull(pickPosition_id)){
				whereCondition.append(" and s.pickPosition_id = ?");
				whereCondition.append(index++);
				params.add(pickPosition_id);
			}
			if(GeneralUtil.isNotNull(startTime)){
				whereCondition.append(" and s.status.createTime >= ?");
				whereCondition.append(index++);
				params.add(startTime);
			}
			if(GeneralUtil.isNotNull(endTime)){
				whereCondition.append(" and s.status.createTime <= ?");
				whereCondition.append(index++);
				params.add(DateUtil.getDayEnd(endTime));
			}
			whereCondition.append(" and s.status.commend = ?");
			whereCondition.append(index++);
			whereCondition.append(" and s.status.hot =1");
			
			params.add(0);
			return dbDAO.queryByPageModule(entityName, deleteFlag, versionFlag, whereCondition.toString(), params.toArray(), orderBy, PaginationContext.getOffset(), pageSize);
		}catch(Exception e){
			log.info("查询实体列表出错: " + e.fillInStackTrace());
			return null;
		}
	}

	@Override
	public List<LostGoodsInfo> queryByTimes(Date startTime, Date endTime)
			throws ServiceException {
		List<LostGoodsInfo> list = new ArrayList<LostGoodsInfo>();
		if(!GeneralUtil.isNull(startTime) && GeneralUtil.isNotNull(endTime)){
			 list = dbDAO.executeJPQLForQueryEntity("select c from " + LostGoodsInfo.class.getName() + " c where c.status.createTime >= ? and c.status.createTime <= ? order by c.status.createTime desc",startTime,endTime);
		}else if(GeneralUtil.isNotNull(startTime) && GeneralUtil.isNull(endTime)){
			 list = dbDAO.executeJPQLForQueryEntity("select c from " + LostGoodsInfo.class.getName() + " c where c.status.createTime >= ? order by c.status.createTime desc",startTime);
		}else if(GeneralUtil.isNull(startTime) && GeneralUtil.isNotNull(endTime)){
			 list = dbDAO.executeJPQLForQueryEntity("select c from " + LostGoodsInfo.class.getName() + " c where c.status.createTime <= ? order by c.status.createTime desc",endTime);
		}
		return list;
	}

	@Override
	public boolean hotEntity(String id, Integer statusValue) throws ServiceException {
		try {
			LostGoodsInfo lostGoodsInfo = dbDAO.queryById(LostGoodsInfo.class.getSimpleName(), id);
			lostGoodsInfo.getStatus().setHot(statusValue);
			dbDAO.merge(lostGoodsInfo);
			return true;
		} catch(Exception e) {
			log.info("上传单个实体出错 :" + e.fillInStackTrace());;
			return false;
		}
	}

	@Override
	public Pagination queryEntityByNotHot(String versionFlag, int pageSize) {
		return dbDAO.queryByPageModule(LostGoodsInfo.class.getSimpleName(), deleteFlag, versionFlag, "and s.status.hot= ?3 and s.status.commend= ?4", new Object[]{ContextConstants.STATUS_OF_ZERO,ContextConstants.STATUS_OF_ZERO}, orderBy, PaginationContext.getOffset(), pageSize);
	}
	
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean importLostGoods(HttpServletRequest request, String file) throws Exception{
			file = request.getSession().getServletContext().getRealPath("") +file;
			file = file.replace("\\", "/");//linux 路径兼容写法
			File f = new File(file);
			FileInputStream fis = new FileInputStream(f);
			Workbook rwb = Workbook.getWorkbook(fis);      
			Sheet rs = rwb.getSheet(0);  
			LostGoodsInfo goodsInfo = null;
			ReceiveRecord receiveRecord = null;
			for (int j = 1; j < rs.getRows(); j++) {   
			    Cell[] cells = rs.getRow(j);   
//			    cells[0].getContents().trim();
			    //遗失物品
			    goodsInfo = new LostGoodsInfo();
			    //物品类型
			    if(GeneralUtil.isNotNull(cells[1].getContents())){
			    	String type = cells[1].getContents().trim();
			    	SystemDictionary tpye1 = dictionaryService.getByName(type);
			    	goodsInfo.setType_id(tpye1.getId());
			    }
			    //物品名称
			    if(GeneralUtil.isNotNull(cells[2].getContents())){
			    	goodsInfo.setName(cells[2].getContents().trim());
			    }
			    //物品编号
			    if(GeneralUtil.isNotNull(cells[3].getContents())){
			    	goodsInfo.setGoodsNumber(cells[3].getContents().trim());
			    }
			    //物品颜色
			    if(GeneralUtil.isNotNull(cells[4].getContents())){
			    	goodsInfo.setColor(cells[4].getContents().trim());
			    }
			    //拾取者姓名
			    if(GeneralUtil.isNotNull(cells[5].getContents())){
			    	goodsInfo.setPickerName(cells[4].getContents().trim());
			    }
			    //拾取者电话
			    if(GeneralUtil.isNotNull(cells[6].getContents())){
			    	goodsInfo.setPickerPhone(cells[4].getContents().trim());
			    }
			    //拾取时间
			    if(GeneralUtil.isNotNull(cells[7].getContents())){
			    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			    	String date = cells[7].getContents().trim();
			    	Date time = sdf.parse(date);
			    	goodsInfo.setPickUpTime(time);
			    }
			    //拾取位置
			    if(GeneralUtil.isNotNull(cells[8].getContents())){
			    	String type = cells[8].getContents().trim();
			    	GoodsLostPostions goodsLostPostions = goodsLostPostionsService.queryByName(type);
			    	if(goodsLostPostions != null){
			    		goodsInfo.setPickPosition_id(goodsLostPostions.getId());
			    	}
			    }
			    //上交时间
			    if(GeneralUtil.isNotNull(cells[9].getContents())){
			    	goodsInfo.setCommitTime(cells[9].getContents().trim());
			    }
			    //库存位置
			    if(GeneralUtil.isNotNull(cells[10].getContents())){
			    	goodsInfo.setStockPosition(cells[10].getContents().trim());
			    }
			    //物品描述
			    if(GeneralUtil.isNotNull(cells[11].getContents())){
			    	goodsInfo.setGoodsDescript(cells[11].getContents().trim());
			    }
			    //登机备注
			    if(GeneralUtil.isNotNull(cells[12].getContents())){
			    	goodsInfo.setRemark(cells[12].getContents().trim());
			    }
			    if(cells.length >= 13){
			    	//接受人
				    if(GeneralUtil.isNotNull(cells[13].getContents())){
				    	goodsInfo.setOperator_id(cells[13].getContents().trim());
				    }
			    }
			    if(cells.length >= 14){
			    	//其他信息一
			    	if(GeneralUtil.isNotNull(cells[14].getContents())){
			    		goodsInfo.setOtherInfoOne(cells[14].getContents().trim());
			    	}
			    }
			    if(cells.length >= 15){
			    	//其他信息二
			    	if(GeneralUtil.isNotNull(cells[15].getContents())){
			    		goodsInfo.setOtherInfoTwo(cells[15].getContents().trim());
			    	}
			    }
			    CommonEntity status = new CommonEntity();
			    status.setHot(1);//上传
			    goodsInfo.setStatus(status);
			    dbDAO.persist(goodsInfo);
			    
			    /*________________________________________________________________*/
			    //领取记录
			    if(GeneralUtil.isNotNull(cells[16].getContents()) || GeneralUtil.isNotNull(cells[17].getContents()) || 
			    		GeneralUtil.isNotNull(cells[18].getContents()) || GeneralUtil.isNotNull(cells[19].getContents()) ||
			    				GeneralUtil.isNotNull(cells[20].getContents()) || GeneralUtil.isNotNull(cells[21].getContents()) ||
			    						GeneralUtil.isNotNull(cells[22].getContents()) || GeneralUtil.isNotNull(cells[23].getContents()) ||
			    								GeneralUtil.isNotNull(cells[24].getContents()) || GeneralUtil.isNotNull(cells[25].getContents()) ||
			    										GeneralUtil.isNotNull(cells[26].getContents())){
			    	receiveRecord = new ReceiveRecord();
			    	//领取人姓名
			    	if(GeneralUtil.isNotNull(cells[16].getContents())){
			    		receiveRecord.setPersonName(cells[16].getContents().trim());
			    	}
			    	//领取时间
			    	if(GeneralUtil.isNotNull(cells[17].getContents())){
			    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			    		String date = cells[17].getContents().trim();
			    		Date time = sdf.parse(date);
			    		receiveRecord.setReceiveTime(time);
			    	}
			    	//证件类型
			    	if(GeneralUtil.isNotNull(cells[18].getContents())){
			    		String type = cells[18].getContents().trim();
			    		SystemDictionary tpye1 = dictionaryService.getByName(type);
			    		receiveRecord.setCertificateType(tpye1.getId());
			    	}
			    	//证件号码
			    	if(GeneralUtil.isNotNull(cells[19].getContents())){
			    		receiveRecord.setReceiveCertificateNumber(cells[19].getContents().trim());
			    	}
			    	//领取人电话
			    	if(GeneralUtil.isNotNull(cells[20].getContents())){
			    		receiveRecord.setPhone(cells[20].getContents().trim());
			    	}
			    	//领取方式
			    	if(GeneralUtil.isNotNull(cells[21].getContents())){
			    		String type = cells[21].getContents().trim();
			    		SystemDictionary tpye1 = dictionaryService.getByName(type);
			    		receiveRecord.setReceiveWay(tpye1.getId());
			    	}
			    	//出库人
			    	if(GeneralUtil.isNotNull(cells[22].getContents())){
			    		receiveRecord.setWriteOffPerson(cells[22].getContents());
			    	}
			    	//出库时间
			    	if(GeneralUtil.isNotNull(cells[23].getContents())){
			    		receiveRecord.setWriteOffTime(cells[23].getContents().trim());
			    	}
			    	//发放人
			    	if(GeneralUtil.isNotNull(cells[24].getContents())){
			    		receiveRecord.setPutOutPerson(cells[23].getContents());
			    	}
			    	//发放时间
			    	if(GeneralUtil.isNotNull(cells[25].getContents())){
			    		receiveRecord.setPutOutTime(cells[25].getContents());
			    	}
			    	//发放备注
			    	if(GeneralUtil.isNotNull(cells[26].getContents())){
			    		receiveRecord.setRemark(cells[26].getContents());
			    	}
			    	receiveRecord.setLostGoods_id(goodsInfo.getId());
			    	recordService.persist(receiveRecord);
			    	cells[27].getContents().trim();
			    	
			    	//遗失物品已发放
			    	CommonEntity status1 = goodsInfo.getStatus();
			    	status1.setCommend(1);
			    	goodsInfo.setStatus(status1);
			    	dbDAO.merge(goodsInfo);
			    }
			}
			fis.close();
			FileUtil.deleteFile(file);
			return true;
	}
}

















