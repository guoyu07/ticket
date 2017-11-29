package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.TrafficLineAndStation;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.service.ITrafficLineAndStationService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 路线与车站关联控制器
 * @ClassName: TrafficLineAndStationAction   
 * @Description:  提供路线与车站关联的相关操作方法. 
 * @author HiSay  
 * @date 2015-12-20 16:30:33
 *
 */
public class TrafficLineAndStationAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//路线与车站关联的业务层
	@Resource private ITrafficLineAndStationService trafficLineAndStationService = null;
	@Resource private ISystemOperationLogService logService = null;
	//路线与车站关联实体
	private TrafficLineAndStation trafficLineAndStation = null;
	//主键
	private String id = null;
	//路线类别id
	private String trafficType_id = null;
    //路线id
	private String trafficLine_id = null;
    //车站id
	private String trafficStation_id = null;
    //车站类型
	private Integer stationType = null;
	//排序值
	private Integer orderValue = 0;
	
	/**
	 * 添加路线与车站关联
	 * @Title: TrafficLineAndStationAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addTrafficLineAndStation";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(trafficLine_id)) {
				data = AjaxData.responseError(getText("trafficLine_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(trafficStation_id)) {
				data = AjaxData.responseError(getText("trafficStation_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(stationType)) {
				data = AjaxData.responseError(getText("stationType.required"));
				return JSON;
			}
			String[] str = trafficLine_id.split("#");
			trafficLine_id = str[0];
			trafficType_id = str[1];
			//保存路线与车站关联实体
			boolean isSuc = trafficLineAndStationService.persist(trafficType_id,trafficLine_id, trafficStation_id, stationType,orderValue, versionFlag);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增路线与车站关联信息";
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
	 * 修改路线与车站关联
	 * @Title: TrafficLineAndStationAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setTrafficLineAndStation(trafficLineAndStationService.queryById(TrafficLineAndStation.class.getSimpleName(), id));
			return "editTrafficLineAndStation";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(trafficLine_id)) {
				data = AjaxData.responseError(getText("trafficLine_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(trafficStation_id)) {
				data = AjaxData.responseError(getText("trafficStation_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(stationType)) {
				data = AjaxData.responseError(getText("stationType.required"));
				return JSON;
			}
			String[] str = trafficLine_id.split("#");
			trafficLine_id = str[0];
			trafficType_id = str[1];
			//修改路线与车站关联实体
			boolean isSuc = trafficLineAndStationService.merge(id,trafficType_id, trafficLine_id, trafficStation_id, stationType,orderValue,  versionFlag);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改路线与车站关联信息";
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
	 * 管理路线与车站关联实体
	 * @Title: TrafficLineAndStationAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		if(GeneralUtil.isNotNull(trafficLine_id)){
			this.setPageModule(trafficLineAndStationService.queryByTrafficLineId(trafficLine_id,ContextConstants.ADMIN_COMMON_PAGE_SIZE,versionFlag));
		}else{
			this.setPageModule(trafficLineAndStationService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		}
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageTrafficLineAndStation";
	}
	
	/**
	 * 查看回收站
	 * @Title: TrafficLineAndStationAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(trafficLineAndStationService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleTrafficLineAndStation";
	}
	
	/**
	 * 逻辑删除路线与车站关联对象
	 * @Title: TrafficLineAndStationAction
	 * @Description: 把路线与车站关联对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = trafficLineAndStationService.logicDeleteEntity(TrafficLineAndStation.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除路线与车站关联信息";
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
	 * 物理删除路线与车站关联对象
	 * @Title: TrafficLineAndStationAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = trafficLineAndStationService.remove(id);
		if(isSuc) {
			String logContent = "物理删除路线与车站关联信息";
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
	 * 还原一个路线与车站关联对象
	 * @Title: TrafficLineAndStationAction
	 * @Description: 从回收站还原路线与车站关联对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = trafficLineAndStationService.restoreEntity(TrafficLineAndStation.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原路线与车站关联信息";
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
	 * 审核路线与车站关联对象
	 * @Title: TrafficLineAndStationAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = trafficLineAndStationService.auditEntity(TrafficLineAndStation.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核路线与车站关联信息";
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
	 * @Title: TrafficLineAndStationAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = trafficLineAndStationService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作路线与车站关联信息";
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
	 * 批量删除路线与车站关联信息
	 */
	public String batchRealDelete() throws ServiceException {
		boolean isSuc = trafficLineAndStationService.batchRealDelete(TrafficLineAndStation.class, idsValue);
		if(isSuc) {
			String logContent = "批量删除路线与车站关联信息";
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
	
	public TrafficLineAndStation getTrafficLineAndStation() {
		return trafficLineAndStation;
	}
	public void setTrafficLineAndStation(TrafficLineAndStation trafficLineAndStation) {
		this.trafficLineAndStation = trafficLineAndStation;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTrafficLine_id() {
		return trafficLine_id;
	}
	public void setTrafficLine_id(String trafficLine_id) {
		this.trafficLine_id = trafficLine_id;
	}
	public String getTrafficStation_id() {
		return trafficStation_id;
	}
	public void setTrafficStation_id(String trafficStation_id) {
		this.trafficStation_id = trafficStation_id;
	}
	public Integer getStationType() {
		return stationType;
	}
	public void setStationType(Integer stationType) {
		this.stationType = stationType;
	}

	public String getTrafficType_id() {
		return trafficType_id;
	}

	public void setTrafficType_id(String trafficTypeId) {
		trafficType_id = trafficTypeId;
	}

	public Integer getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(Integer orderValue) {
		this.orderValue = orderValue;
	}
}
