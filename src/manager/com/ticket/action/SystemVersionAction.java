package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.SystemVersion;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.service.ISystemVersionService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 系统版本控制器
 * @ClassName: SystemVersionAction   
 * @Description:  提供系统版本的相关操作方法. 
 * @author HiSay  
 * @date 2014-10-15 14:41:02
 *
 */
public class SystemVersionAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//系统版本的业务层
	@Resource private ISystemVersionService systemVersionService = null;
	
	@Resource private ISystemOperationLogService logService = null;
	//系统版本实体
	private SystemVersion systemVersion = null;
	//主键
	private String id = null;
    //版本号
	private String version = null;
    //版本升级内容
	private String content = null;
	
	/**
	 * 添加系统版本
	 * @Title: SystemVersionAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addSystemVersion";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(version)) {
				data = getText("version.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(content)) {
				data = getText("content.required");
				return TEXT;
			}
			//保存系统版本实体
			boolean isSuc = systemVersionService.persist(version, content, versionFlag);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增系统版本";
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
	 * 修改系统版本
	 * @Title: SystemVersionAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setSystemVersion(systemVersionService.queryById(SystemVersion.class.getSimpleName(), id));
			return "editSystemVersion";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = getText("id.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(version)) {
				data = getText("version.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(content)) {
				data = getText("content.required");
				return TEXT;
			}
			//修改系统版本实体
			boolean isSuc = systemVersionService.merge(id, version, content,  versionFlag);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改系统版本";
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
	 * 管理系统版本实体
	 * @Title: SystemVersionAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(systemVersionService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageSystemVersion";
	}
	
	/**
	 * 查看回收站
	 * @Title: SystemVersionAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(systemVersionService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleSystemVersion";
	}
	
	/**
	 * 逻辑删除系统版本对象
	 * @Title: SystemVersionAction
	 * @Description: 把系统版本对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = systemVersionService.logicDeleteEntity(SystemVersion.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除系统版本";
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
	 * 物理删除系统版本对象
	 * @Title: SystemVersionAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = systemVersionService.remove(id);
		if(isSuc) {
			String logContent = "物理删除系统版本";
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
	 * 还原一个系统版本对象
	 * @Title: SystemVersionAction
	 * @Description: 从回收站还原系统版本对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = systemVersionService.restoreEntity(SystemVersion.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原系统版本";
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
	 * 审核系统版本对象
	 * @Title: SystemVersionAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = systemVersionService.auditEntity(SystemVersion.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核系统版本";
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
	 * @Title: SystemVersionAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = systemVersionService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作系统版本";
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
	
	public SystemVersion getSystemVersion() {
		return systemVersion;
	}
	public void setSystemVersion(SystemVersion systemVersion) {
		this.systemVersion = systemVersion;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
