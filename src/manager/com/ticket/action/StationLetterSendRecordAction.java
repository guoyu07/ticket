package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.StationLetterSendRecord;
import com.ticket.service.IStationLetterSendRecordService;
import com.ticket.util.GeneralUtil;

/**
 * 站内信发送记录控制器
 * @ClassName: StationLetterSendRecordAction   
 * @Description:  提供站内信发送记录的相关操作方法. 
 * @author HiSay  
 * @date 2016-05-10 14:53:45
 *
 */
public class StationLetterSendRecordAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//站内信发送记录的业务层
	@Resource private IStationLetterSendRecordService stationLetterSendRecordService = null;
	//站内信发送记录实体
	private StationLetterSendRecord stationLetterSendRecord = null;
	//主键
	private String id = null;
    //操作员id
	private String opertator_id = null;
    //站内信id
	private String letter_id = null;
    //对象标识
	private String objectFlag = null;
    //对象id
	private String object_id = null;
    //发送数量
	private Integer sendCount = null;
	
	/**
	 * 添加站内信发送记录
	 * @Title: StationLetterSendRecordAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addStationLetterSendRecord";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(opertator_id)) {
				data = AjaxData.responseError(getText("opertator_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(letter_id)) {
				data = AjaxData.responseError(getText("letter_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(objectFlag)) {
				data = AjaxData.responseError(getText("objectFlag.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(object_id)) {
				data = AjaxData.responseError(getText("object_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(sendCount)) {
				data = AjaxData.responseError(getText("sendCount.required"));
				return JSON;
			}
			//保存站内信发送记录实体
			boolean isSuc = stationLetterSendRecordService.persist(opertator_id, letter_id, objectFlag, object_id, sendCount, versionFlag);
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
	 * 修改站内信发送记录
	 * @Title: StationLetterSendRecordAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setStationLetterSendRecord(stationLetterSendRecordService.queryById(StationLetterSendRecord.class.getSimpleName(), id));
			return "editStationLetterSendRecord";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(opertator_id)) {
				data = AjaxData.responseError(getText("opertator_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(letter_id)) {
				data = AjaxData.responseError(getText("letter_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(objectFlag)) {
				data = AjaxData.responseError(getText("objectFlag.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(object_id)) {
				data = AjaxData.responseError(getText("object_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(sendCount)) {
				data = AjaxData.responseError(getText("sendCount.required"));
				return JSON;
			}
			//修改站内信发送记录实体
			boolean isSuc = stationLetterSendRecordService.merge(id, opertator_id, letter_id, objectFlag, object_id, sendCount,  versionFlag);
			//根据修改结果返回页面
			if(isSuc) {
				data = AjaxData.responseSuccess(getText("editSuccess"));
			} else {
				data = AjaxData.responseError(getText("editFailed"));
			}
			return JSON;
		}
	}
	
	/**
	 * 管理站内信发送记录实体
	 * @Title: StationLetterSendRecordAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(stationLetterSendRecordService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageStationLetterSendRecord";
	}
	
	/**
	 * 查看回收站
	 * @Title: StationLetterSendRecordAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(stationLetterSendRecordService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleStationLetterSendRecord";
	}
	
	/**
	 * 逻辑删除站内信发送记录对象
	 * @Title: StationLetterSendRecordAction
	 * @Description: 把站内信发送记录对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = stationLetterSendRecordService.logicDeleteEntity(StationLetterSendRecord.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除站内信发送记录对象
	 * @Title: StationLetterSendRecordAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = stationLetterSendRecordService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个站内信发送记录对象
	 * @Title: StationLetterSendRecordAction
	 * @Description: 从回收站还原站内信发送记录对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = stationLetterSendRecordService.restoreEntity(StationLetterSendRecord.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核站内信发送记录对象
	 * @Title: StationLetterSendRecordAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = stationLetterSendRecordService.auditEntity(StationLetterSendRecord.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: StationLetterSendRecordAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = stationLetterSendRecordService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public StationLetterSendRecord getStationLetterSendRecord() {
		return stationLetterSendRecord;
	}
	public void setStationLetterSendRecord(StationLetterSendRecord stationLetterSendRecord) {
		this.stationLetterSendRecord = stationLetterSendRecord;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOpertator_id() {
		return opertator_id;
	}
	public void setOpertator_id(String opertator_id) {
		this.opertator_id = opertator_id;
	}
	public String getLetter_id() {
		return letter_id;
	}
	public void setLetter_id(String letter_id) {
		this.letter_id = letter_id;
	}
	public String getObjectFlag() {
		return objectFlag;
	}
	public void setObjectFlag(String objectFlag) {
		this.objectFlag = objectFlag;
	}
	public String getObject_id() {
		return object_id;
	}
	public void setObject_id(String object_id) {
		this.object_id = object_id;
	}
	public Integer getSendCount() {
		return sendCount;
	}
	public void setSendCount(Integer sendCount) {
		this.sendCount = sendCount;
	}
}
