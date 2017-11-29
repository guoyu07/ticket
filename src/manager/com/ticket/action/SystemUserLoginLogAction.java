package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.SystemUserLoginLog;
import com.ticket.service.ISystemUserLoginLogService;
import com.ticket.util.GeneralUtil;

/**
 * 系统管理员登陆日志控制器
 * @ClassName: SystemUserLoginLogAction   
 * @Description:  提供系统管理员登陆日志的相关操作方法. 
 * @author HiSay  
 * @date 2015-01-03 10:42:16
 *
 */
public class SystemUserLoginLogAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//系统管理员登陆日志的业务层
	@Resource private ISystemUserLoginLogService systemUserLoginLogService = null;
	//系统管理员登陆日志实体
	private SystemUserLoginLog systemUserLoginLog = null;
	//主键
	private String id = null;
    //操作IP地址
	private String ip = null;
    //所属管理员
	private String systemUser_id = null;
	
	/**
	 * 添加系统管理员登陆日志
	 * @Title: SystemUserLoginLogAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addSystemUserLoginLog";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(ip)) {
				data = getText("ip.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(systemUser_id)) {
				data = getText("systemUser_id.required");
				return TEXT;
			}
			//保存系统管理员登陆日志实体
			boolean isSuc = systemUserLoginLogService.persist(ip, systemUser_id, versionFlag);
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
	 * 修改系统管理员登陆日志
	 * @Title: SystemUserLoginLogAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setSystemUserLoginLog(systemUserLoginLogService.queryById(SystemUserLoginLog.class.getSimpleName(), id));
			return "editSystemUserLoginLog";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = getText("id.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(ip)) {
				data = getText("ip.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(systemUser_id)) {
				data = getText("systemUser_id.required");
				return TEXT;
			}
			//修改系统管理员登陆日志实体
			boolean isSuc = systemUserLoginLogService.merge(id, ip, systemUser_id,  versionFlag);
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
	 * 管理系统管理员登陆日志实体
	 * @Title: SystemUserLoginLogAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(systemUserLoginLogService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageSystemUserLoginLog";
	}
	
	/**
	 * 查看回收站
	 * @Title: SystemUserLoginLogAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(systemUserLoginLogService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleSystemUserLoginLog";
	}
	
	/**
	 * 逻辑删除系统管理员登陆日志对象
	 * @Title: SystemUserLoginLogAction
	 * @Description: 把系统管理员登陆日志对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = systemUserLoginLogService.logicDeleteEntity(SystemUserLoginLog.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除系统管理员登陆日志对象
	 * @Title: SystemUserLoginLogAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = systemUserLoginLogService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个系统管理员登陆日志对象
	 * @Title: SystemUserLoginLogAction
	 * @Description: 从回收站还原系统管理员登陆日志对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = systemUserLoginLogService.restoreEntity(SystemUserLoginLog.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核系统管理员登陆日志对象
	 * @Title: SystemUserLoginLogAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = systemUserLoginLogService.auditEntity(SystemUserLoginLog.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: SystemUserLoginLogAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = systemUserLoginLogService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public SystemUserLoginLog getSystemUserLoginLog() {
		return systemUserLoginLog;
	}
	public void setSystemUserLoginLog(SystemUserLoginLog systemUserLoginLog) {
		this.systemUserLoginLog = systemUserLoginLog;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getSystemUser_id() {
		return systemUser_id;
	}
	public void setSystemUser_id(String systemUser_id) {
		this.systemUser_id = systemUser_id;
	}
}
