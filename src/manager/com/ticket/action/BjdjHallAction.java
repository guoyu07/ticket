package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjHall;
import com.ticket.service.IBjdjHallService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 服务厅表控制器
 * @ClassName: BjdjHallAction   
 * @Description:  提供服务厅表的相关操作方法. 
 * @author HiSay  
 * @date 2015-10-23 15:24:57
 *
 */
public class BjdjHallAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//服务厅表的业务层
	@Resource private IBjdjHallService bjdjHallService = null;
	@Resource private ISystemOperationLogService logService = null;
	//服务厅表实体
	private BjdjHall bjdjHall = null;
	//主键
	private String id = null;
    //厅号
	private String number = null;
    //容量（最大人数）
	private Integer capacity = null;
    //经度
	private String longitude = null;
    //纬度
	private String latitude = null;
    //描述
	private String description = null;
	
	/**
	 * 添加服务厅表
	 * @Title: BjdjHallAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addBjdjHall";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(number)) {
				data = getText("number.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(capacity)) {
				data = getText("capacity.required");
				return TEXT;
			}
			//保存服务厅表实体
			boolean isSuc = bjdjHallService.persist(number, capacity, longitude, latitude, description, versionFlag);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增服务厅：" + number;
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
	 * 修改服务厅表
	 * @Title: BjdjHallAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setBjdjHall(bjdjHallService.queryById(BjdjHall.class.getSimpleName(), id));
			return "editBjdjHall";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = getText("id.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(number)) {
				data = getText("number.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(capacity)) {
				data = getText("capacity.required");
				return TEXT;
			}
			//修改服务厅表实体
			boolean isSuc = bjdjHallService.merge(id, number, capacity, longitude, latitude, description,  versionFlag);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改服务厅：" + number;
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
	 * 管理服务厅表实体
	 * @Title: BjdjHallAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(bjdjHallService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageBjdjHall";
	}
	
	/**
	 * 查看回收站
	 * @Title: BjdjHallAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(bjdjHallService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleBjdjHall";
	}
	
	/**
	 * 逻辑删除服务厅表对象
	 * @Title: BjdjHallAction
	 * @Description: 把服务厅表对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		BjdjHall hall = bjdjHallService.queryById(BjdjHall.class.getName(), id);
		String number = hall.getNumber();
		boolean isSuc = bjdjHallService.logicDeleteEntity(BjdjHall.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除服务厅：" + number;
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
	 * 物理删除服务厅表对象
	 * @Title: BjdjHallAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		BjdjHall hall = bjdjHallService.queryById(BjdjHall.class.getName(), id);
		String number = hall.getNumber();
		boolean isSuc = bjdjHallService.remove(id);
		if(isSuc) {
			String logContent = "物理删除服务厅：" + number;
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
	 * 还原一个服务厅表对象
	 * @Title: BjdjHallAction
	 * @Description: 从回收站还原服务厅表对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		BjdjHall hall = bjdjHallService.queryById(BjdjHall.class.getName(), id);
		String number = hall.getNumber();
		boolean isSuc = bjdjHallService.restoreEntity(BjdjHall.class.getSimpleName(), id);
		if(isSuc) {
			
			String logContent = "还原服务厅：" + number;
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
	 * 审核服务厅表对象
	 * @Title: BjdjHallAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		BjdjHall hall = bjdjHallService.queryById(BjdjHall.class.getName(), id);
		String number = hall.getNumber();
		boolean isSuc = bjdjHallService.auditEntity(BjdjHall.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核服务厅：" + number;
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
	 * @Title: BjdjHallAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = bjdjHallService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作服务厅";
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
	
	public BjdjHall getBjdjHall() {
		return bjdjHall;
	}
	public void setBjdjHall(BjdjHall bjdjHall) {
		this.bjdjHall = bjdjHall;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
