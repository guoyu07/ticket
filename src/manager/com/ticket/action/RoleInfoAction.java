package com.ticket.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.PrivilegeInfo;
import com.ticket.pojo.RoleInfo;
import com.ticket.pojo.SystemModule;
import com.ticket.service.IPrivilegeInfoService;
import com.ticket.service.IRoleInfoService;
import com.ticket.service.ISystemModuleService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.service.ITreeService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.PrivilegeUtil;
import com.ticket.util.SystemOparetionLogUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 角色信息控制器
 * @ClassName: RoleInfoAction   
 * @Description:  提供角色信息的相关操作方法. 
 * @author HiSay  
 * @date 2015-10-16 11:18:46
 *
 */
public class RoleInfoAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//角色信息的业务层
	@Resource private IRoleInfoService roleInfoService = null;
	@Resource private ISystemModuleService systemModuleService = null;
	@Resource private ISystemOperationLogService logService = null;
	@Resource private ITreeService<SystemModule, String> treeService = null;
	@Resource private IPrivilegeInfoService privilegeInfoService = null;
	//角色信息实体
	private RoleInfo roleInfo = null;
	//主键
	private String id = null;
    //角色名称
	private String name = null;
    //角色描述
	private String descript = null;
	//模块
	private String moduleHtml = null;
	/**
	 * 添加角色信息
	 * @Title: RoleInfoAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addRoleInfo";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError(getText("name.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(descript)) {
				data = AjaxData.responseError(getText("descript.required"));
				return JSON;
			}
			//验证角色名是否已存在
			if(roleInfoService.validateNameExists(name,versionFlag)){
				data = AjaxData.responseError(getText("name.exists"));
				return JSON;
			}
			//保存角色信息实体
			boolean isSuc = roleInfoService.persist(name, descript, versionFlag);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增角色信息";
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
	 * 修改角色信息
	 * @Title: RoleInfoAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setRoleInfo(roleInfoService.queryById(RoleInfo.class.getSimpleName(), id));
			return "editRoleInfo";
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
			//修改角色信息实体
			boolean isSuc = roleInfoService.merge(id, name, descript,  versionFlag);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改角色信息";
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
	 * 管理角色信息实体
	 * @Title: RoleInfoAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(roleInfoService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageRoleInfo";
	}
	
	/**
	 * 查看回收站
	 * @Title: RoleInfoAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(roleInfoService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleRoleInfo";
	}
	
	/**
	 * 逻辑删除角色信息对象
	 * @Title: RoleInfoAction
	 * @Description: 把角色信息对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = roleInfoService.logicDeleteEntity(RoleInfo.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除角色信息";
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
	 * 物理删除角色信息对象
	 * @Title: RoleInfoAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = roleInfoService.remove(id);
		if(isSuc) {
			String logContent = "物理删除角色信息";
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
	 * 还原一个角色信息对象
	 * @Title: RoleInfoAction
	 * @Description: 从回收站还原角色信息对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = roleInfoService.restoreEntity(RoleInfo.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原角色信息";
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
	 * 审核角色信息对象
	 * @Title: RoleInfoAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = roleInfoService.auditEntity(RoleInfo.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核角色信息";
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
	 * @Title: RoleInfoAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = roleInfoService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作角色信息";
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
	 * 批量彻底删除角色信息
	 * @return
	 * @throws ServiceException
	 */
	public String batchRealDelete() throws ServiceException{
		boolean isSuc = roleInfoService.batchRealDelete(idsValue,versionFlag);
		if(isSuc) {
			String logContent = "批量删除角色信息";
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
	 * 给角色赋权
	 * @return
	 * @throws ServiceException
	 */
	public String grantPrivilege() throws ServiceException{
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setModuleHtml(PrivilegeUtil.recursion(systemModuleService.queryFirstModuleList(versionFlag),id));
		return "grantPrivilege";
	}
	
	/**
	 * 加载单层数
	 * @return
	 * @throws ServiceException 
	 */
	public String loadTree() throws ServiceException{
		String parentId = (String)ServletActionContext.getRequest().getParameter("id");
		List<SystemModule> dictionarys = null;
		
		//加载根节点
		if(GeneralUtil.isNull(parentId)){
			
			JSONObject json = new JSONObject();
			Long count = treeService.count(SystemModule.class);
			json.put("total", count);
			
			dictionarys = treeService.queryRootNode(SystemModule.class);
			JSONArray jsonArray = new JSONArray();
			for(Object o : dictionarys){
				
				SystemModule dict = (SystemModule)o;
				Long subCount = treeService.countSubByParent(SystemModule.class, dict.getId());
				List<PrivilegeInfo> modules = privilegeInfoService.queryBuSystemModulId(dict.getId());
				JSONObject jsonSingle = new JSONObject();
				if(subCount > 0){
					
					jsonSingle.put("state", "closed");
				}
				if(GeneralUtil.isNotNull(modules)){
					for(PrivilegeInfo info:modules){
						jsonSingle.put("privilege", info.getName());
					}
				}
				jsonSingle.put("_parentId", "");
				jsonSingle.put("id", dict.getId());
				jsonSingle.put("name", dict.getName());
				jsonSingle.put("orderValue", dict.getStatus().getOrderValue());
				jsonArray.add(jsonSingle);
			}
			json.put("rows", jsonArray);
			data = json.toString();
			
		}else{ //加载非根节点
			
			dictionarys = treeService.querySubByParent(SystemModule.class, parentId);
			JSONArray jsonArray = new JSONArray();
			for(Object o : dictionarys){
				
				SystemModule dict = (SystemModule)o;
				List<PrivilegeInfo> modules = privilegeInfoService.queryBuSystemModulId(dict.getId());
				Long subCount = treeService.countSubByParent(SystemModule.class, dict.getId());
				JSONObject jsonSingle = new JSONObject();
				if(subCount > 0){
					
					jsonSingle.put("state", "closed");
				}
				if(GeneralUtil.isNotNull(modules)){
					for(PrivilegeInfo info:modules){
						jsonSingle.put("privilege", info.getName());
					}
				}
				jsonSingle.put("_parentId", dict.getParent_id());
				jsonSingle.put("id", dict.getId());
				jsonSingle.put("name", dict.getName());
				jsonSingle.put("orderValue", dict.getStatus().getOrderValue());
				jsonArray.add(jsonSingle);
			}
			data = jsonArray.toString();
		}
		return TEXT;
	}
	public RoleInfo getRoleInfo() {
		return roleInfo;
	}
	public void setRoleInfo(RoleInfo roleInfo) {
		this.roleInfo = roleInfo;
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

	public String getModuleHtml() {
		return moduleHtml;
	}

	public void setModuleHtml(String moduleHtml) {
		this.moduleHtml = moduleHtml;
	}
}
