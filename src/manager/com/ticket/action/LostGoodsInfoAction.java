package com.ticket.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.constants.JQueryUploadConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.GoodsLostPostions;
import com.ticket.pojo.LostGoodsAndReceiveRecord;
import com.ticket.pojo.LostGoodsInfo;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.ReceiveRecord;
import com.ticket.pojo.SystemDictionary;
import com.ticket.service.IGoodsLostPostionsService;
import com.ticket.service.ILostGoodsInfoService;
import com.ticket.service.IReceiveRecordService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.DateUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;
import com.ticket.util.ValidateUtil;

import net.sf.jxls.transformer.XLSTransformer;

/**
 * 遗失物品信息控制器
 * @ClassName: LostGoodsInfoAction   
 * @Description:  提供遗失物品信息的相关操作方法. 
 * @author HiSay  
 * @date 2015-11-23 16:17:30
 *
 */
public class LostGoodsInfoAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//遗失物品信息的业务层
	@Resource private ILostGoodsInfoService lostGoodsInfoService = null;
	
	@Resource private IReceiveRecordService recordService = null;
	
	@Resource private ISystemDictionaryService dictionaryService = null;
	
	@Resource private ISystemOperationLogService logService = null;
	
	@Resource private IGoodsLostPostionsService goodsLostPostionsService = null;
	//遗失物品信息实体
	private LostGoodsInfo lostGoodsInfo = null;
	//主键
	private String id = null;
    //物品类型
	private String type_id = null;
    //物品ID
	private String goodsId = null;
    //物品重量
	private Double weight = null;
	//物品名称
	private String name = null;
    //物品颜色
	private String color = null;
    //其他信息一
	private String otherInfoOne = null;
    //其他信息二
	private String otherInfoTwo = null;
    //拾取者姓名
	private String pickerName = null;
    //拾取者电话
	private String pickerPhone = null;
    //拾取时间
	private String pickUpTime = null;
    //拾取位置
	private String pickPosition_id = null;
    //物品描述
	private String goodsDescript = null;
    //备注
	private String remark = null;
    //操作员
	private String operator_id = null;
	//关键词
	private String keyword = null;
	//上交时间
	private String commitTime = null;
	//库存位置
	private String stockPosition = null;
	//查询时的开始时间
	private Date startTime = null;
	//查询时的结束时间
	private Date endTime = null;
	
	private String fileName = null;
	
	private InputStream inputStream = null;
	//状态值
	private Integer stateValue = null;
	
	/**
	 * 物品编号
	 */
	private String goodsNumber = null;
	
	/**
	 * 添加遗失物品信息
	 * @Title: LostGoodsInfoAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addLostGoodsInfo";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(type_id)) {
				data = AjaxData.responseError(getText("type_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError(getText("name.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(color)) {
				data = AjaxData.responseError(getText("color.required"));
				return JSON;
			}
//			if(GeneralUtil.isNull(pickerName)) {
//				data = AjaxData.responseError(getText("pickerName.required"));
//				return JSON;
//			}
//			if(GeneralUtil.isNull(pickerPhone)) {
//				data = AjaxData.responseError(getText("pickerPhone.required"));
//				return JSON;
//			}
			if(GeneralUtil.isNull(pickUpTime)) {
				data = AjaxData.responseError(getText("pickUpTime.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(pickPosition_id)) {
				data = AjaxData.responseError(getText("pickPosition_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(goodsDescript)) {
				data = AjaxData.responseError(getText("goodsDescript.required"));
				return JSON;
			}
			//保存遗失物品信息实体
			boolean isSuc = lostGoodsInfoService.persist(type_id,name, goodsId, weight, color, otherInfoOne, otherInfoTwo, pickerName, pickerPhone, pickUpTime, pickPosition_id, goodsDescript, remark, operator_id,commitTime,stockPosition,goodsNumber, versionFlag);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增遗失物品信息";
				String logName = SystemOparetionLogUtil.getLogName();
				String logTime = SystemOparetionLogUtil.getLogTime();
				String logIp = SystemOparetionLogUtil.getLogIp();
				
				logService.persist(logName, logContent, logTime, logIp, versionFlag);
				data = AjaxData.responseSuccess(getText("addSuccess"));
			} else {
				data = AjaxData.responseError(getText("addFailed"));
			}
			return JSON;
		}
	}
	
	/**
	 * 修改遗失物品信息
	 * @Title: LostGoodsInfoAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setLostGoodsInfo(lostGoodsInfoService.queryById(LostGoodsInfo.class.getSimpleName(), id));
			return "editLostGoodsInfo";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(type_id)) {
				data = AjaxData.responseError(getText("type_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(color)) {
				data = AjaxData.responseError(getText("color.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError(getText("name.required"));
				return JSON;
			}
//			if(GeneralUtil.isNull(pickerName)) {
//				data = AjaxData.responseError(getText("pickerName.required"));
//				return JSON;
//			}
//			if(GeneralUtil.isNull(pickerPhone)) {
//				data = AjaxData.responseError(getText("pickerPhone.required"));
//				return JSON;
//			}
			if(GeneralUtil.isNull(pickUpTime)) {
				data = AjaxData.responseError(getText("pickUpTime.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(pickPosition_id)) {
				data = AjaxData.responseError(getText("pickPosition_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(goodsDescript)) {
				data = AjaxData.responseError(getText("goodsDescript.required"));
				return JSON;
			}
			//修改遗失物品信息实体
			boolean isSuc = lostGoodsInfoService.merge(id, type_id,name, goodsId, weight, color, otherInfoOne, otherInfoTwo, pickerName, pickerPhone, pickUpTime, pickPosition_id, goodsDescript, remark, operator_id,commitTime,stockPosition, goodsNumber, versionFlag);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改遗失物品信息";
				String logName = SystemOparetionLogUtil.getLogName();
				String logTime = SystemOparetionLogUtil.getLogTime();
				String logIp = SystemOparetionLogUtil.getLogIp();
				
				logService.persist(logName, logContent, logTime, logIp, versionFlag);
				data = AjaxData.responseSuccess(getText("editSuccess"));
			} else {
				data = AjaxData.responseError(getText("editFailed"));
			}
			return JSON;
		}
	}
	
	/**
	 * 管理遗失物品信息实体
	 * @Title: LostGoodsInfoAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(lostGoodsInfoService.queryEntityByCommendAndHot(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageLostGoodsInfo";
	}
	
	/**
	 * 管理遗失物品信息实体
	 * @Title: LostGoodsInfoAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String showLostGoodsInfo() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(lostGoodsInfoService.queryEntityByNotHot(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "showLostGoodsInfo";
	}
	/**
	 * 查询
	 * @return
	 * @throws ServiceException
	 */
	public String searchs() throws ServiceException{
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		Pagination page = lostGoodsInfoService.queryEntityByAdmin(goodsNumber,name,color,type_id,pickPosition_id,startTime,endTime,versionFlag,ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		this.setPageModule(page);
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageLostGoodsInfo";
	}
	
	/**
	 * 批量下载登记以及领取信息页面
	 * @return
	 * @throws ServiceException
	 */
	public String download() throws ServiceException{
		
		return "download";
	}
	/**
	 * 批量下载登记以及领取信息
	 * @return
	 * @throws ServiceException
	 */
	public String batchDownLoad() throws Exception{
		
		String logContent = "批量下载遗失物品登记和领取记录信息";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		
		String destFilePath = createXls();
		File file = new File(destFilePath);
		fileName = file.getName();
		inputStream = new FileInputStream(file);
		return "downloads";
	}
	
	public String createXls() throws Exception{
		List<LostGoodsInfo> infos = lostGoodsInfoService.queryByTimes(startTime, DateUtil.getDayEnd(endTime));
		
		List<LostGoodsAndReceiveRecord> list = new ArrayList<LostGoodsAndReceiveRecord>();
		if(GeneralUtil.isNotNull(infos)){
			for(LostGoodsInfo info : infos){
				LostGoodsAndReceiveRecord goodsAndReceiveRecord = new LostGoodsAndReceiveRecord();
				goodsAndReceiveRecord.setName(info.getName());//名称
				SystemDictionary dictionary = dictionaryService.queryById(SystemDictionary.class.getSimpleName(), info.getType_id());
				goodsAndReceiveRecord.setType_id(dictionary.getName());//类型
				goodsAndReceiveRecord.setGoodsId(info.getGoodsId());
				goodsAndReceiveRecord.setWeight(info.getWeight());
				SystemDictionary dictionary2 = dictionaryService.queryById(SystemDictionary.class.getSimpleName(), info.getColor());
				goodsAndReceiveRecord.setColor(dictionary2.getName());//颜色
				goodsAndReceiveRecord.setOtherInfoOne(info.getOtherInfoOne());
				goodsAndReceiveRecord.setOtherInfoTwo(info.getOtherInfoTwo());
				goodsAndReceiveRecord.setPickerName(info.getPickerName());
				goodsAndReceiveRecord.setPickerPhone(info.getPickerPhone());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String pickTime = sdf.format(info.getPickUpTime());
				goodsAndReceiveRecord.setPickUpTime(pickTime);
				GoodsLostPostions dictionary3 = goodsLostPostionsService.queryById(GoodsLostPostions.class.getName(), info.getPickPosition_id());
				goodsAndReceiveRecord.setPickPosition_id(dictionary3.getName());
				goodsAndReceiveRecord.setGoodsDescript(ValidateUtil.htmlFilter(info.getGoodsDescript()));
				goodsAndReceiveRecord.setRemark1(ValidateUtil.htmlFilter(info.getRemark()));
				goodsAndReceiveRecord.setOperator_id(info.getOperator_id());
				goodsAndReceiveRecord.setCommitTime(info.getCommitTime());
				goodsAndReceiveRecord.setStockPosition(info.getStockPosition());
				goodsAndReceiveRecord.setGoodsNumber(info.getGoodsNumber());
				
				String createTime = sdf.format(info.getStatus().getCreateTime());
				goodsAndReceiveRecord.setCreateTime(createTime);
				
				String lostGoods_id = info.getId();
				ReceiveRecord record = recordService.queryByGoodsId(lostGoods_id);
				if(GeneralUtil.isNotNull(record)){
					goodsAndReceiveRecord.setPersonName(record.getPersonName());
					String receiveTime = sdf.format(record.getReceiveTime());
					goodsAndReceiveRecord.setReceiveTime(receiveTime);
					SystemDictionary dictionary4 = dictionaryService.queryById(SystemDictionary.class.getSimpleName(), record.getCertificateType());
					goodsAndReceiveRecord.setCertificateType(dictionary4.getName());
					goodsAndReceiveRecord.setReceiveCertificateNumber(record.getReceiveCertificateNumber());
					goodsAndReceiveRecord.setPhone(record.getPhone());
					SystemDictionary dictionary5 = dictionaryService.queryById(SystemDictionary.class.getSimpleName(), record.getReceiveWay());
					goodsAndReceiveRecord.setReceiveWay(dictionary5.getName());
					goodsAndReceiveRecord.setWriteOffPerson(record.getWriteOffPerson());
					goodsAndReceiveRecord.setWriteOffTime(record.getWriteOffTime());
					goodsAndReceiveRecord.setPutOutPerson(record.getPutOutPerson());
					goodsAndReceiveRecord.setPutOutTime(record.getPutOutTime());
					goodsAndReceiveRecord.setRemark2(ValidateUtil.htmlFilter(record.getRemark()));
				}
				list.add(goodsAndReceiveRecord);
			}
		}
		String rootPath = ServletActionContext.getRequest().getRealPath("/manager");
		String srcFilePath = rootPath + "/memberExcel/info.xls";  
		String destFilePath = rootPath + "/memberExcelTemplate/info.xls";
		Map<String, List<LostGoodsAndReceiveRecord>> beanParams = new HashMap<String, List<LostGoodsAndReceiveRecord>>();
		beanParams.put("infos", list);
		
		
		XLSTransformer former = new XLSTransformer();
		former.transformXLS(srcFilePath, beanParams,destFilePath);
		return destFilePath;
	}
	
	/**
	 * 导入遗失物品信息
	 * @return
	 */
	public String importLostGoods() throws Exception{
		String file = getSinglePictureUrlFromJQueryUpLoader(fileNames, directory, JQueryUploadConstants.PICTURE_TYPE_DEFAULT);
		if(file != null){
			file = file.substring(0,file.indexOf(ContextConstants.UPLOAD_SEPARATOR_VALUE));
			try {
				lostGoodsInfoService.importLostGoods(request, file);
				data = AjaxData.responseSuccess("导入成功！");
				return JSON;
			} catch (Exception e) {
				data = AjaxData.responseSuccess("导入失败！");
				e.printStackTrace();
				return JSON;
			}
		}else{
			data = AjaxData.responseError("请上传文件");
		}
		data = AjaxData.responseSuccess("请求失败！");
		return JSON;
	}
	
	/**
	 * 查看回收站
	 * @Title: LostGoodsInfoAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(lostGoodsInfoService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleLostGoodsInfo";
	}
	
	/**
	 * 逻辑删除遗失物品信息对象
	 * @Title: LostGoodsInfoAction
	 * @Description: 把遗失物品信息对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = lostGoodsInfoService.logicDeleteEntity(LostGoodsInfo.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除遗失物品信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除遗失物品信息对象
	 * @Title: LostGoodsInfoAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = lostGoodsInfoService.remove(id);
		if(isSuc) {
			String logContent = "物理删除遗失物品信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个遗失物品信息对象
	 * @Title: LostGoodsInfoAction
	 * @Description: 从回收站还原遗失物品信息对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = lostGoodsInfoService.restoreEntity(LostGoodsInfo.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原遗失物品信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核遗失物品信息对象
	 * @Title: LostGoodsInfoAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = lostGoodsInfoService.auditEntity(LostGoodsInfo.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核遗失物品信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 上传遗失物品信息对象
	 * @Title: LostGoodsInfoAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String hot() throws ServiceException {
		boolean isSuc = lostGoodsInfoService.hotEntity(id, statusValue);
		if(isSuc) {
			String logContent = "上传遗失物品信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("hotSuccess"));
		} else {
			data = AjaxData.responseError(getText("hotFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: LostGoodsInfoAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = lostGoodsInfoService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作遗失物品信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量物理快删除
	 * @return
	 * @throws ServiceException
	 * @see com.ticket.action.BaseAction#batchRealDelete()
	 */
	public String batchRealDelete() throws ServiceException {
		boolean isSuc = lostGoodsInfoService.batchRealDelete(idsValue, versionFlag);
		if(isSuc) {
			String logContent = "批量删除遗失物品信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物品详情
	 * @return
	 * @throws ServiceException
	 */
	public String detail() throws ServiceException{
		this.setLostGoodsInfo(lostGoodsInfoService.queryById(LostGoodsInfo.class.getSimpleName(), id));
		return "lostGoodsInfoDetail";
	}
	/**
	 * 根据关键词检索信息
	 * @return
	 * @throws ServiceException
	 */
	public String search() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(lostGoodsInfoService.queryByKeyword(keyword,versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageLostGoodsInfo";
	}
	
	public LostGoodsInfo getLostGoodsInfo() {
		return lostGoodsInfo;
	}
	public void setLostGoodsInfo(LostGoodsInfo lostGoodsInfo) {
		this.lostGoodsInfo = lostGoodsInfo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType_id() {
		return type_id;
	}
	public void setType_id(String type_id) {
		this.type_id = type_id;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getOtherInfoOne() {
		return otherInfoOne;
	}
	public void setOtherInfoOne(String otherInfoOne) {
		this.otherInfoOne = otherInfoOne;
	}
	public String getOtherInfoTwo() {
		return otherInfoTwo;
	}
	public void setOtherInfoTwo(String otherInfoTwo) {
		this.otherInfoTwo = otherInfoTwo;
	}
	public String getPickerName() {
		return pickerName;
	}
	public void setPickerName(String pickerName) {
		this.pickerName = pickerName;
	}
	public String getPickerPhone() {
		return pickerPhone;
	}
	public void setPickerPhone(String pickerPhone) {
		this.pickerPhone = pickerPhone;
	}
	public String getPickUpTime() {
		return pickUpTime;
	}
	public void setPickUpTime(String pickUpTime) {
		this.pickUpTime = pickUpTime;
	}
	public String getPickPosition_id() {
		return pickPosition_id;
	}
	public void setPickPosition_id(String pickPosition_id) {
		this.pickPosition_id = pickPosition_id;
	}
	public String getGoodsDescript() {
		return goodsDescript;
	}
	public void setGoodsDescript(String goodsDescript) {
		this.goodsDescript = goodsDescript;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOperator_id() {
		return operator_id;
	}
	public void setOperator_id(String operator_id) {
		this.operator_id = operator_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getCommitTime() {
		return commitTime;
	}

	public void setCommitTime(String commitTime) {
		this.commitTime = commitTime;
	}

	public String getStockPosition() {
		return stockPosition;
	}

	public void setStockPosition(String stockPosition) {
		this.stockPosition = stockPosition;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public Integer getStateValue() {
		return stateValue;
	}

	public void setStateValue(Integer stateValue) {
		this.stateValue = stateValue;
	}

	public String getGoodsNumber() {
		return goodsNumber;
	}

	public void setGoodsNumber(String goodsNumber) {
		this.goodsNumber = goodsNumber;
	}
}
