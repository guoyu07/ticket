package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.UserRole;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.service.IUserRoleService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 用户角色控制器
 * @ClassName: UserRoleAction   
 * @Description:  提供用户角色的相关操作方法. 
 * @author HiSay  
 * @date 2015-10-17 09:41:22
 *
 */
public class UserRoleAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//用户角色的业务层
	@Resource private IUserRoleService userRoleService = null;
	
	@Resource private ISystemOperationLogService logService = null;
	//用户角色实体
	private UserRole userRole = null;
	//主键
	private String id = null;
    //用户id
	private String userId = null;
    //角色id
	private String roleId = null;
	
	/**
	 * 添加用户角色
	 * @Title: UserRoleAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addUserRole";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(userId)) {
				data = getText("userId.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(roleId)) {
				data = getText("roleId.required");
				return TEXT;
			}
			//保存用户角色实体
			boolean isSuc = userRoleService.persist(userId, roleId, versionFlag);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增用户角色关系";
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
	 * 修改用户角色
	 * @Title: UserRoleAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setUserRole(userRoleService.queryById(UserRole.class.getSimpleName(), id));
			return "editUserRole";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = getText("id.required");
				return JSON;
			}
			if(GeneralUtil.isNull(userId)) {
				data = getText("userId.required");
				return JSON;
			}
			if(GeneralUtil.isNull(roleId)) {
				data = getText("roleId.required");
				return JSON;
			}
			//修改用户角色实体
			boolean isSuc = userRoleService.merge(id, userId, roleId,  versionFlag);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改用户角色关系";
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
	 * 管理用户角色实体
	 * @Title: UserRoleAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(userRoleService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageUserRole";
	}
	
	/**
	 * 查看回收站
	 * @Title: UserRoleAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(userRoleService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleUserRole";
	}
	
	/**
	 * 逻辑删除用户角色对象
	 * @Title: UserRoleAction
	 * @Description: 把用户角色对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = userRoleService.logicDeleteEntity(UserRole.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除用户角色关系";
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
	 * 物理删除用户角色对象
	 * @Title: UserRoleAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = userRoleService.remove(id);
		if(isSuc) {
			String logContent = "物理删除用户角色关系";
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
	 * 还原一个用户角色对象
	 * @Title: UserRoleAction
	 * @Description: 从回收站还原用户角色对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = userRoleService.restoreEntity(UserRole.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原用户角色关系";
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
	 * 审核用户角色对象
	 * @Title: UserRoleAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = userRoleService.auditEntity(UserRole.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核用户角色关系";
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
	 * @Title: UserRoleAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = userRoleService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作用户角色关系";
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
	 * 取消授权
	 * @return
	 * @throws ServiceException
	 */
	public String cancel() throws ServiceException{
		String logContent = "取消用户角色关系";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		UserRole userRole = userRoleService.queryByUIdAndRID(userId,roleId,versionFlag);
		userRoleService.remove(userRole.getId());
		return JSON;
	}
	public UserRole getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
}
