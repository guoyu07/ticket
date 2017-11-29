package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.PrivilegeInfo;
import com.ticket.pojo.SystemModule;
import com.ticket.service.IPrivilegeInfoService;
import com.ticket.service.ISystemModuleService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.service.ITreeService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

import net.sf.json.JSONArray;

/**
 * 权限信息控制器
 * @ClassName: PrivilegeInfoAction   
 * @Description:  提供权限信息的相关操作方法. 
 * @author HiSay  
 * @date 2015-10-16 11:37:11
 *
 */
public class PrivilegeInfoAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//权限信息的业务层
	@Resource private IPrivilegeInfoService privilegeInfoService = null;
	
	@Resource private ISystemOperationLogService logService = null;
	
	@Resource private ITreeService<SystemModule, String> treeService = null;
	
	@Resource private ISystemModuleService systemModuleService = null;
	
	//权限信息实体
	private PrivilegeInfo privilegeInfo = null;
	//主键
	private String id = null;
    //权限名称
	private String name = null;
    //权限描述
	private String descript = null;
    //包含的方法
	private String includeMethods = null;
	//模块
	private String systemModulId = null;
	/**
	 * 添加权限信息
	 * @Title: PrivilegeInfoAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addPrivilegeInfo";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(name)) {
				data = getText("name.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(descript)) {
				data = getText("descript.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(includeMethods)) {
				data = getText("includeMethods.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(systemModulId)) {
				data = "菜单不能为空~";
				return TEXT;
			}
			//验证权限名是否已存在
//			if(privilegeInfoService.validateNameExists(name,versionFlag)){
//				data = AjaxData.responseError(getText("name.exists"));
//				return JSON;
//			}
			//保存权限信息实体
			boolean isSuc = privilegeInfoService.persist(name, descript, includeMethods,systemModulId, versionFlag);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增权限信息";
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
	 * 遍历所有菜单
	 * @return
	 */
	public String traverse(){
		try {
			JSONArray json = treeService.traverse(SystemModule.class, "name","id");
			data = json.toString();
			return TEXT;
			
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	/**
	 * 修改权限信息
	 * @Title: PrivilegeInfoAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setPrivilegeInfo(privilegeInfoService.queryById(PrivilegeInfo.class.getSimpleName(), id));
			return "editPrivilegeInfo";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = getText("id.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(name)) {
				data = getText("name.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(descript)) {
				data = getText("descript.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(includeMethods)) {
				data = getText("includeMethods.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(systemModulId)) {
				data = "菜单不能为空~";
				return TEXT;
			}
			//修改权限信息实体
			boolean isSuc = privilegeInfoService.merge(id, name, descript, includeMethods,systemModulId,  versionFlag);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改权限信息";
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
	 * 管理权限信息实体
	 * @Title: PrivilegeInfoAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(privilegeInfoService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "managePrivilegeInfo";
	}
	
	/**
	 * 查看回收站
	 * @Title: PrivilegeInfoAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(privilegeInfoService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recyclePrivilegeInfo";
	}
	
	/**
	 * 逻辑删除权限信息对象
	 * @Title: PrivilegeInfoAction
	 * @Description: 把权限信息对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = privilegeInfoService.logicDeleteEntity(PrivilegeInfo.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除权限信息";
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
	 * 物理删除权限信息对象
	 * @Title: PrivilegeInfoAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = privilegeInfoService.remove(id);
		if(isSuc) {
			String logContent = "物理删除权限信息";
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
	 * 还原一个权限信息对象
	 * @Title: PrivilegeInfoAction
	 * @Description: 从回收站还原权限信息对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = privilegeInfoService.restoreEntity(PrivilegeInfo.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原权限信息";
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
	 * 审核权限信息对象
	 * @Title: PrivilegeInfoAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = privilegeInfoService.auditEntity(PrivilegeInfo.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核权限信息";
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
	 * @Title: PrivilegeInfoAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = privilegeInfoService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作权限信息";
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
	 * 批量物理删除
	 * @return
	 * @throws ServiceException
	 * @see com.ticket.action.BaseAction#batchRealDelete()
	 */
	public String batchRealDelete() throws ServiceException {
		boolean isSuc = privilegeInfoService.batchRealDelete(idsValue,versionFlag);
		if(isSuc) {
			String logContent = "批量删除权限信息";
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
	
	public PrivilegeInfo getPrivilegeInfo() {
		return privilegeInfo;
	}
	public void setPrivilegeInfo(PrivilegeInfo privilegeInfo) {
		this.privilegeInfo = privilegeInfo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public String getIncludeMethods() {
		return includeMethods;
	}
	public void setIncludeMethods(String includeMethods) {
		this.includeMethods = includeMethods;
	}

	public String getSystemModulId() {
		return systemModulId;
	}

	public void setSystemModulId(String systemModulId) {
		this.systemModulId = systemModulId;
	}
}
