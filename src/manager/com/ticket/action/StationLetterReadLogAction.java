package com.ticket.action;

import java.util.Date;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.LetterStation;
import com.ticket.pojo.StationLetterReadLog;
import com.ticket.service.ILetterStationService;
import com.ticket.service.IStationLetterReadLogService;
import com.ticket.util.GeneralUtil;

/**
 * 站内信阅读日志控制器
 * @ClassName: StationLetterReadLogAction   
 * @Description:  提供站内信阅读日志的相关操作方法. 
 * @author HiSay  
 * @date 2016-05-09 14:18:24
 *
 */
public class StationLetterReadLogAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//站内信阅读日志的业务层
	@Resource private IStationLetterReadLogService stationLetterReadLogService = null;
	
	//站内信的业务层
	@Resource private ILetterStationService letterStationService = null;
	//站内信阅读日志实体
	private StationLetterReadLog stationLetterReadLog = null;
	//主键
	private String id = null;
    //接收站内信对象id
	private String object_id = null;
    //是否阅读
	private Integer isRead = null;
    //信息id
	private String letter_id = null;
    //阅读日期
	private Date readDate = null;
	
	//站内信实体
	private LetterStation letterStation = null;
	
	/**
	 * 添加站内信阅读日志
	 * @Title: StationLetterReadLogAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addStationLetterReadLog";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(object_id)) {
				data = AjaxData.responseError(getText("object_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(isRead)) {
				data = AjaxData.responseError(getText("isRead.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(letter_id)) {
				data = AjaxData.responseError(getText("letter_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(readDate)) {
				data = AjaxData.responseError(getText("readDate.required"));
				return JSON;
			}
			//保存站内信阅读日志实体
			boolean isSuc = stationLetterReadLogService.persist(object_id, isRead, letter_id, readDate, versionFlag);
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
	 * 修改站内信阅读日志
	 * @Title: StationLetterReadLogAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setStationLetterReadLog(stationLetterReadLogService.queryById(StationLetterReadLog.class.getSimpleName(), id));
			return "editStationLetterReadLog";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(object_id)) {
				data = AjaxData.responseError(getText("object_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(isRead)) {
				data = AjaxData.responseError(getText("isRead.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(letter_id)) {
				data = AjaxData.responseError(getText("letter_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(readDate)) {
				data = AjaxData.responseError(getText("readDate.required"));
				return JSON;
			}
			//修改站内信阅读日志实体
			boolean isSuc = stationLetterReadLogService.merge(id, object_id, isRead, letter_id, readDate,  versionFlag);
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
	 * 管理站内信阅读日志实体
	 * @Title: StationLetterReadLogAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		if(getSystemEmployeeInfo() != null){
			this.setPageModule(stationLetterReadLogService.queryByEmployeeOrCustomer(getSystemEmployeeInfo().getId(),versionFlag,ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		}
		if(getSystemChannelCustomer() != null){
			this.setPageModule(stationLetterReadLogService.queryByEmployeeOrCustomer(getSystemChannelCustomer().getId(),versionFlag,ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		}
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageStationLetterReadLog";
	}
	
	/**
	 * 查看回收站
	 * @Title: StationLetterReadLogAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(stationLetterReadLogService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleStationLetterReadLog";
	}
	
	/**
	 * 逻辑删除站内信阅读日志对象
	 * @Title: StationLetterReadLogAction
	 * @Description: 把站内信阅读日志对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = stationLetterReadLogService.logicDeleteEntity(StationLetterReadLog.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除站内信阅读日志对象
	 * @Title: StationLetterReadLogAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = stationLetterReadLogService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个站内信阅读日志对象
	 * @Title: StationLetterReadLogAction
	 * @Description: 从回收站还原站内信阅读日志对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = stationLetterReadLogService.restoreEntity(StationLetterReadLog.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核站内信阅读日志对象
	 * @Title: StationLetterReadLogAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = stationLetterReadLogService.auditEntity(StationLetterReadLog.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: StationLetterReadLogAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = stationLetterReadLogService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	/**
	 * 设置站内信为已读
	 * @return
	 * @throws ServiceException
	 */
	public String setLetterRead() throws ServiceException{
		boolean isSuc = stationLetterReadLogService.setLetterRead(id,versionFlag);
		if(isSuc){
			data = AjaxData.responseSuccess("");
		}else{
			data = AjaxData.responseError("");
		}
		return JSON;
	}
	
	/**
	 * 查看站内信详情
	 * @return
	 * @throws ServiceException
	 */
	public String show() throws ServiceException{
		stationLetterReadLogService.setLetterRead(id,versionFlag);
		this.setLetterStation(letterStationService.queryById(LetterStation.class.getSimpleName(), letter_id));
		return "stationLetterDetail";
	}
	
	
	public StationLetterReadLog getStationLetterReadLog() {
		return stationLetterReadLog;
	}
	public void setStationLetterReadLog(StationLetterReadLog stationLetterReadLog) {
		this.stationLetterReadLog = stationLetterReadLog;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getObject_id() {
		return object_id;
	}
	public void setObject_id(String object_id) {
		this.object_id = object_id;
	}
	public Integer getIsRead() {
		return isRead;
	}
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
	public String getLetter_id() {
		return letter_id;
	}
	public void setLetter_id(String letter_id) {
		this.letter_id = letter_id;
	}
	public Date getReadDate() {
		return readDate;
	}
	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}

	public LetterStation getLetterStation() {
		return letterStation;
	}

	public void setLetterStation(LetterStation letterStation) {
		this.letterStation = letterStation;
	}
}
