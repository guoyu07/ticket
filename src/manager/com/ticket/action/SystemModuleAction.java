package com.ticket.action;

import java.util.List;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.NewsClass;
import com.ticket.pojo.SystemModule;
import com.ticket.service.INewsClassService;
import com.ticket.service.ISystemModuleService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.ModuleUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 系统模块控制器
 * @ClassName: SystemModuleAction   
 * @Description:  提供系统模块的相关操作方法. 
 * @author HiSay  
 * @date 2014-10-15 13:49:51
 *
 */
public class SystemModuleAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//系统模块的业务层
	@Resource private ISystemModuleService systemModuleService = null;
	@Resource private ISystemOperationLogService logService = null;
	//新闻类别模块的业务层
	@Resource private INewsClassService newsClassService = null;
	//系统模块实体
	private SystemModule systemModule = null;
	//主键
	private String id = null;
    //模块名称
	private String name = null;
    //模块链接
	private String url = null;
    //所属上级模块
	private String parent_id = null;
    //模块图标
	private String icon = null;
	//模块HTML字符串
	private String moduleHtml = null;
	//新闻类别信息实体
	private NewsClass newsClass = null;
	//新闻类别id
	private String newsClass_id = null;
	/**
	 * 添加系统模块
	 * @Title: SystemModuleAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setModuleHtml(systemModuleService.queryModuleSelectOptionHtml(parent_id, versionFlag));
			return "addSystemModule";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(name)) {
				data = getText("name.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(url)) {
				data = getText("url.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(parent_id)) {
				data = getText("parent_id.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(icon)) {
				data = getText("icon.required");
				return TEXT;
			}
			//保存系统模块实体
			boolean isSuc = systemModuleService.persist(name, url, parent_id, icon, versionFlag, orderValue);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增系统模块";
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
	 * 修改系统模块
	 * @Title: SystemModuleAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setSystemModule(systemModuleService.queryById(SystemModule.class.getSimpleName(), id));
			if(this.getSystemModule().getParent_id() != null) {
				this.setModuleHtml(systemModuleService.queryModuleSelectOptionHtml(this.getSystemModule().getParent_id(), versionFlag));
			} else {
				this.setModuleHtml(systemModuleService.queryModuleSelectOptionHtml(null, versionFlag));
			}
			return "editSystemModule";
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
			if(GeneralUtil.isNull(url)) {
				data = getText("url.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(parent_id)) {
				data = getText("parent_id.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(icon)) {
				data = getText("icon.required");
				return TEXT;
			}
			//修改系统模块实体
			boolean isSuc = systemModuleService.merge(id, name, url, parent_id, icon,  versionFlag, orderValue);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改系统模块";
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
	 * 管理系统模块实体
	 * @Title: SystemModuleAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setModuleHtml(ModuleUtil.recursion(systemModuleService.queryFirstModuleList(versionFlag)));
		return "manageSystemModule";
	}
	
	/**
	 * 查看回收站
	 * @Title: SystemModuleAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(systemModuleService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleSystemModule";
	}
	
	/**
	 * 逻辑删除系统模块对象
	 * @Title: SystemModuleAction
	 * @Description: 把系统模块对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = systemModuleService.logicDeleteEntity(SystemModule.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除系统模块";
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
	 * 物理删除系统模块对象
	 * @Title: SystemModuleAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		systemModuleService.remove(id);
		String logContent = "物理删除系统模块";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		return SUCCESS;
	}
	
	/**
	 * 还原一个系统模块对象
	 * @Title: SystemModuleAction
	 * @Description: 从回收站还原系统模块对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = systemModuleService.restoreEntity(SystemModule.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原系统模块";
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
	 * 审核系统模块对象
	 * @Title: SystemModuleAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = systemModuleService.auditEntity(SystemModule.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核系统模块";
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
	 * @Title: SystemModuleAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = systemModuleService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作系统模块";
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
	 * 设置模块是否默认
	* @Title: defaults 
	* @param @return
	* @param @throws ServiceException    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String defaults() throws ServiceException {
		systemModuleService.setModuleIsDefaultShow(SystemModule.class.getSimpleName(), id);
		return SUCCESS;
	}
	
	/**
	 * 一级导航切换
	 * @Title: moduleNav 
	 * @param @return
	 * @param @throws ServiceException    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	public String moduleNav() throws ServiceException {
		List<SystemModule> leftModuleList = systemModuleService.queryLeftModules(parent_id, versionFlag);
		if(leftModuleList != null && !leftModuleList.isEmpty()) {
			getSession().put(ContextConstants.ADMIN_LEFT_MODULE_LIST, leftModuleList);
		} else {
			getSession().remove(ContextConstants.ADMIN_LEFT_MODULE_LIST);
		}
		return "changeSystemModule";
	}
	/**
	 * 将新闻类别设置为导航模块
	 * @return
	 * @throws ServiceException
	 */
	public String setNewsClassAsSystemModule() throws ServiceException{
		this.setModuleHtml(systemModuleService.queryModuleSelectOptionHtml(parent_id, versionFlag));
		this.setNewsClass(newsClassService.queryById(NewsClass.class.getSimpleName(), newsClass_id));
		String logContent = "将新闻类别设置为导航模块";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		return "setNewsClassAsSystemModule";
	}
	
	/**
	 * 设置类别为模块
	 * @return
	 * @throws ServiceException
	 */
	public String setClassAsModule() throws ServiceException{
		//删除原来的类别
		//newsClassService.remove(newsClass_id);
		//保存系统模块实体
		boolean isSuc = systemModuleService.persistAndGetChild(name, url, parent_id, icon, versionFlag, orderValue,newsClass_id);
		//根据保存结果返回页面
		if(isSuc) {
			String logContent = "设置类别为模块";
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
	
	public SystemModule getSystemModule() {
		return systemModule;
	}
	public void setSystemModule(SystemModule systemModule) {
		this.systemModule = systemModule;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getModuleHtml() {
		return moduleHtml;
	}

	public void setModuleHtml(String moduleHtml) {
		this.moduleHtml = moduleHtml;
	}

	public NewsClass getNewsClass() {
		return newsClass;
	}

	public void setNewsClass(NewsClass newsClass) {
		this.newsClass = newsClass;
	}

	public String getNewsClass_id() {
		return newsClass_id;
	}

	public void setNewsClass_id(String newsClassId) {
		newsClass_id = newsClassId;
	}
}
