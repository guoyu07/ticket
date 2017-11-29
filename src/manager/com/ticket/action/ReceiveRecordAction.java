package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.LostGoodsInfo;
import com.ticket.pojo.ReceiveRecord;
import com.ticket.service.ILostGoodsInfoService;
import com.ticket.service.IReceiveRecordService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 领取记录控制器
 * @ClassName: ReceiveRecordAction   
 * @Description:  提供领取记录的相关操作方法. 
 * @author HiSay  
 * @date 2015-11-23 16:26:07
 *
 */
public class ReceiveRecordAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//领取记录的业务层
	@Resource private IReceiveRecordService receiveRecordService = null;
	//遗失物品信息的业务层
	@Resource private ILostGoodsInfoService lostGoodsInfoService = null;
	
	@Resource private ISystemOperationLogService logService = null;
	//领取记录实体
	private ReceiveRecord receiveRecord = null;
	//主键
	private String id = null;
    //物品id
	private String lostGoods_id = null;
    //领取人姓名
	private String personName = null;
    //领取时间
	private String receiveTime = null;
    //证件类型
	private String certificateType = null;
    //证件号码
	private String receiveCertificateNumber = null;
    //领取人电话
	private String phone = null;
    //领取方式
	private String receiveWay = null;
    //出库人
	private String writeOffPerson = null;
    //发放人
	private String putOutPerson = null;
    //备注
	private String remark = null;
	//出库时间
	private String writeOffTime = null;
	//发放时间
	private String putOutTime = null;
	
	/**
	 * 添加领取记录
	 * @Title: ReceiveRecordAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addReceiveRecord";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(lostGoods_id)) {
				data = AjaxData.responseError(getText("lostGoods_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(personName)) {
				data = AjaxData.responseError(getText("personName.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(receiveTime)) {
				data = AjaxData.responseError(getText("receiveTime.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(certificateType)) {
				data = AjaxData.responseError(getText("certificateType.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(receiveCertificateNumber)) {
				data = AjaxData.responseError(getText("receiveCertificateNumber.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(phone)) {
				data = AjaxData.responseError(getText("phone.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(receiveWay)) {
				data = AjaxData.responseError(getText("receiveWay.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(writeOffPerson)) {
				data = AjaxData.responseError(getText("writeOffPerson.required"));
				return JSON;
			}
			//保存领取记录实体
			boolean isSuc = receiveRecordService.persist(lostGoods_id, personName, receiveTime, certificateType, receiveCertificateNumber, phone, receiveWay, writeOffPerson, putOutPerson, remark,writeOffTime,putOutTime, versionFlag);
			LostGoodsInfo goodsInfo = lostGoodsInfoService.queryById(LostGoodsInfo.class.getSimpleName(), lostGoods_id);
			CommonEntity status = goodsInfo.getStatus();
			status.setCommend(1); //设置该物品已领
			lostGoodsInfoService.merge(goodsInfo);
			//根据保存结果返回页面
			if(isSuc) {
				data = AjaxData.responseSuccess(getText("addSuccess"));
			} else {
				data = AjaxData.responseError(getText("addFailed"));
			}
			return JSON;
		}
	}
	
	/**
	 * 修改领取记录
	 * @Title: ReceiveRecordAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setReceiveRecord(receiveRecordService.queryById(ReceiveRecord.class.getSimpleName(), id));
			return "editReceiveRecord";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(lostGoods_id)) {
				data = AjaxData.responseError(getText("lostGoods_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(personName)) {
				data = AjaxData.responseError(getText("personName.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(receiveTime)) {
				data = AjaxData.responseError(getText("receiveTime.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(certificateType)) {
				data = AjaxData.responseError(getText("certificateType.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(receiveCertificateNumber)) {
				data = AjaxData.responseError(getText("receiveCertificateNumber.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(phone)) {
				data = AjaxData.responseError(getText("phone.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(receiveWay)) {
				data = AjaxData.responseError(getText("receiveWay.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(writeOffPerson)) {
				data = AjaxData.responseError(getText("writeOffPerson.required"));
				return JSON;
			}
			//修改领取记录实体
			boolean isSuc = receiveRecordService.merge(id, lostGoods_id, personName, receiveTime, certificateType, receiveCertificateNumber, phone, receiveWay, writeOffPerson, putOutPerson, remark,writeOffTime,putOutTime,  versionFlag);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改遗失物品领取记录";
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
	 * 管理领取记录实体
	 * @Title: ReceiveRecordAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(receiveRecordService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageReceiveRecord";
	}
	
	/**
	 * 查看回收站
	 * @Title: ReceiveRecordAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(receiveRecordService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleReceiveRecord";
	}
	
	/**
	 * 逻辑删除领取记录对象
	 * @Title: ReceiveRecordAction
	 * @Description: 把领取记录对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = receiveRecordService.logicDeleteEntity(ReceiveRecord.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除遗失物品领取记录";
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
	 * 物理删除领取记录对象
	 * @Title: ReceiveRecordAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = receiveRecordService.remove(id);
		if(isSuc) {
			String logContent = "物理删除遗失物品领取记录";
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
	 * 还原一个领取记录对象
	 * @Title: ReceiveRecordAction
	 * @Description: 从回收站还原领取记录对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = receiveRecordService.restoreEntity(ReceiveRecord.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原遗失物品领取记录";
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
	 * 审核领取记录对象
	 * @Title: ReceiveRecordAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = receiveRecordService.auditEntity(ReceiveRecord.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核遗失物品领取记录";
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
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: ReceiveRecordAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = receiveRecordService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作遗失物品领取记录";
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
	 * 批量彻底删除领取记录
	 * @return
	 * @throws ServiceException
	 */
	public String batchRealDelete() throws ServiceException {
		boolean isSuc = receiveRecordService.batchRealDelete(idsValue,versionFlag);
		if(isSuc) {
			String logContent = "批量删除遗失物品领取记录";
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
	public ReceiveRecord getReceiveRecord() {
		return receiveRecord;
	}
	public void setReceiveRecord(ReceiveRecord receiveRecord) {
		this.receiveRecord = receiveRecord;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLostGoods_id() {
		return lostGoods_id;
	}
	public void setLostGoods_id(String lostGoods_id) {
		this.lostGoods_id = lostGoods_id;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}
	public String getCertificateType() {
		return certificateType;
	}
	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}
	public String getReceiveCertificateNumber() {
		return receiveCertificateNumber;
	}
	public void setReceiveCertificateNumber(String receiveCertificateNumber) {
		this.receiveCertificateNumber = receiveCertificateNumber;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getReceiveWay() {
		return receiveWay;
	}
	public void setReceiveWay(String receiveWay) {
		this.receiveWay = receiveWay;
	}
	public String getWriteOffPerson() {
		return writeOffPerson;
	}
	public void setWriteOffPerson(String writeOffPerson) {
		this.writeOffPerson = writeOffPerson;
	}
	public String getPutOutPerson() {
		return putOutPerson;
	}
	public void setPutOutPerson(String putOutPerson) {
		this.putOutPerson = putOutPerson;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getWriteOffTime() {
		return writeOffTime;
	}

	public void setWriteOffTime(String writeOffTime) {
		this.writeOffTime = writeOffTime;
	}

	public String getPutOutTime() {
		return putOutTime;
	}

	public void setPutOutTime(String putOutTime) {
		this.putOutTime = putOutTime;
	}

	
}
