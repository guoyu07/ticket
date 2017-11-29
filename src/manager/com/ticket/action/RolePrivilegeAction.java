package com.ticket.action;

import java.util.List;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.PrivilegeInfo;
import com.ticket.pojo.RolePrivilege;
import com.ticket.pojo.SystemModule;
import com.ticket.service.IPrivilegeInfoService;
import com.ticket.service.IRolePrivilegeService;
import com.ticket.service.ISystemModuleService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 角色权限控制器
 * @ClassName: RolePrivilegeAction   
 * @Description:  提供角色权限的相关操作方法. 
 * @author HiSay  
 * @date 2015-10-17 11:28:35
 *
 */
public class RolePrivilegeAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//角色权限的业务层
	@Resource private IRolePrivilegeService rolePrivilegeService = null;
	//系统模块的业务层
	@Resource private ISystemModuleService systemModuleService = null;
	//权限信息的业务层
	@Resource private IPrivilegeInfoService privilegeInfoService = null;
	
	@Resource private ISystemOperationLogService logService = null;
	//角色权限实体
	private RolePrivilege rolePrivilege = null;
	//主键
	private String id = null;
    //角色id
	private String roleId = null;
    //模块id
	private String systemModuleId = null;
    //权限id
	private String privilegeId = null;
	
	/**
	 * 添加角色权限
	 * @Title: RolePrivilegeAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(1)) {
			return "addRolePrivilege";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(roleId)) {
				data = getText("roleId.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(systemModuleId)) {
				data = getText("systemModuleId.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(privilegeId)) {
				data = getText("privilegeId.required");
				return TEXT;
			}
			//保存角色权限实体
			boolean isSuc = rolePrivilegeService.persist(roleId, systemModuleId, privilegeId, versionFlag);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增角色权限";
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
	 * 修改角色权限
	 * @Title: RolePrivilegeAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setRolePrivilege(rolePrivilegeService.queryById(RolePrivilege.class.getSimpleName(), id));
			return "editRolePrivilege";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = getText("id.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(roleId)) {
				data = getText("roleId.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(systemModuleId)) {
				data = getText("systemModuleId.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(privilegeId)) {
				data = getText("privilegeId.required");
				return TEXT;
			}
			//修改角色权限实体
			boolean isSuc = rolePrivilegeService.merge(id, roleId, systemModuleId, privilegeId,  versionFlag);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改角色权限";
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
	 * 管理角色权限实体
	 * @Title: RolePrivilegeAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(rolePrivilegeService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageRolePrivilege";
	}
	
	/**
	 * 查看回收站
	 * @Title: RolePrivilegeAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(rolePrivilegeService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleRolePrivilege";
	}
	
	/**
	 * 逻辑删除角色权限对象
	 * @Title: RolePrivilegeAction
	 * @Description: 把角色权限对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = rolePrivilegeService.logicDeleteEntity(RolePrivilege.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除角色权限";
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
	 * 物理删除角色权限对象
	 * @Title: RolePrivilegeAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = rolePrivilegeService.remove(id);
		if(isSuc) {
			String logContent = "物理删除角色权限";
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
	 * 还原一个角色权限对象
	 * @Title: RolePrivilegeAction
	 * @Description: 从回收站还原角色权限对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = rolePrivilegeService.restoreEntity(RolePrivilege.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原角色权限";
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
	 * 审核角色权限对象
	 * @Title: RolePrivilegeAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = rolePrivilegeService.auditEntity(RolePrivilege.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核角色权限";
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
	 * @Title: RolePrivilegeAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = rolePrivilegeService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作角色权限";
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
	 * 收回对角色的授权
	 * @return
	 * @throws ServiceException
	 */
	public String revoke() throws ServiceException{
		RolePrivilege rp = rolePrivilegeService.queryByRIDAndMIDAndPID(roleId, systemModuleId, privilegeId, versionFlag);
		rolePrivilegeService.remove(rp.getId());
		String logContent = "收回角色权限";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		return JSON;
	}
	
	/**
	 * 给当前角色授所有权限
	 * @return
	 * @throws ServiceException
	 */
	public String selectAll() throws ServiceException{
		List<SystemModule> moduleList = systemModuleService.queryByList(versionFlag);
		List<PrivilegeInfo> privilegeList = privilegeInfoService.queryList(versionFlag);
		rolePrivilegeService.selectAll(roleId,moduleList,privilegeList,versionFlag);
		String logContent = "授予角色权限";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		return JSON;
	}
	
	/**
	 * 取消当前角色的所有权限
	 * @return
	 * @throws ServiceException
	 */
	public String selectNone() throws ServiceException{
		rolePrivilegeService.selectNone(roleId,versionFlag);
		String logContent = "取消角色权限";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		return JSON;
	}
	
	public RolePrivilege getRolePrivilege() {
		return rolePrivilege;
	}
	public void setRolePrivilege(RolePrivilege rolePrivilege) {
		this.rolePrivilege = rolePrivilege;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getSystemModuleId() {
		return systemModuleId;
	}
	public void setSystemModuleId(String systemModuleId) {
		this.systemModuleId = systemModuleId;
	}
	public String getPrivilegeId() {
		return privilegeId;
	}
	public void setPrivilegeId(String privilegeId) {
		this.privilegeId = privilegeId;
	}
}
