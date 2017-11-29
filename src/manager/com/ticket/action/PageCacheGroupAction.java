package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.PageCacheGroup;
import com.ticket.service.IPageCacheGroupService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 页面缓存group管理控制器
 * @ClassName: PageCacheGroupAction   
 * @Description:  提供页面缓存group管理的相关操作方法. 
 * @author 涂有  
 * @date 2015-12-23 10:34:35
 */
public class PageCacheGroupAction extends BaseAction {

	/**   
	 * @Fields serialVersionUID :    
	 */ 
	private static final long serialVersionUID = 1L;
	
	//页面缓存group管理的业务层
	@Resource 
	private IPageCacheGroupService groupService = null;
	
	@Resource private ISystemOperationLogService logService = null;
	
	//页面缓存group管理实体
	private PageCacheGroup group = null;
	
	/**
	 * 添加页面缓存group管理
	 * @Title: PageCacheGroupAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addPageCacheGroup";
		} else {
			//非空验证.
			if(group == null){
				data = AjaxData.responseError(getText("group.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(group.getGroupName())) {
				data = AjaxData.responseError(getText("group.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(group.getTime())) {
				data = AjaxData.responseError(getText("time.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(group.getScope())) {
				data = AjaxData.responseError(getText("scope.required"));
				return JSON;
			}
			//保存页面缓存group管理实体
			groupService.persist(group);
			
			String logContent = "新增页面缓存组信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("addSuccess"));
			return JSON;
		}
	}
	
	/**
	 * 修改页面缓存group管理
	 * @Title: PageCacheGroupAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setGroup(groupService.queryById(PageCacheGroup.class.getSimpleName(), id));
			return "editPageCacheGroup";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(group.getId())) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(group.getGroupName())) {
				data = AjaxData.responseError(getText("group.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(group.getTime())) {
				data = AjaxData.responseError(getText("time.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(group.getScope())) {
				data = AjaxData.responseError(getText("scope.required"));
				return JSON;
			}
			//修改页面缓存group管理实体
			groupService.merge(group);
			String logContent = "修改页面缓存组信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("editSuccess"));
			return JSON;
		}
	}
	
	/**
	 * @Description：刷新一批group的缓存
	 * @date 2015年12月24日 上午10:00:47
	 * @return
	 * @throws ServiceException 
	 */
	public String refresh() throws ServiceException{
		
		groupService.refresh(idsValue);
		
		String logContent = "刷新一批页面缓存组信息";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		data = AjaxData.responseSuccess("");
		return JSON;
	}
	
	/**
	 * 管理页面缓存group管理实体
	 * @Title: PageCacheGroupAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(groupService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "managePageCacheGroup";
	}
	
	/**
	 * 查看回收站
	 * @Title: PageCacheGroupAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(groupService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recyclePageCacheGroup";
	}
	
	/**
	 * 逻辑删除页面缓存group管理对象
	 * @Title: PageCacheGroupAction
	 * @Description: 把页面缓存group管理对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = groupService.logicDeleteEntity(PageCacheGroup.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除页面缓存组信息";
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
	 * 物理删除页面缓存group管理对象
	 * @Title: PageCacheGroupAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = groupService.remove(id);
		if(isSuc) {
			String logContent = "物理删除页面缓存组信息";
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
	 * 还原一个页面缓存group管理对象
	 * @Title: PageCacheGroupAction
	 * @Description: 从回收站还原页面缓存group管理对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = groupService.restoreEntity(PageCacheGroup.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原页面缓存组信息";
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
	 * 审核页面缓存group管理对象
	 * @Title: PageCacheGroupAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = groupService.auditEntity(PageCacheGroup.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核页面缓存组信息";
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
	 * @Title: PageCacheGroupAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = groupService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作页面缓存组信息";
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public IPageCacheGroupService getPageCacheGroupService() {
		return groupService;
	}
	public void setPageCacheGroupService(
			IPageCacheGroupService pageCacheGroupService) {
		this.groupService = pageCacheGroupService;
	}
	public PageCacheGroup getGroup() {
		return group;
	}
	public void setGroup(PageCacheGroup group) {
		this.group = group;
	}
}
