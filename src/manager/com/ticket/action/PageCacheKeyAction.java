package com.ticket.action;

import java.util.List;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.PageCacheGroup;
import com.ticket.pojo.PageCacheKey;
import com.ticket.service.IPageCacheGroupService;
import com.ticket.service.IPageCacheKeyService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 页面缓存key管理控制器
 * @ClassName: PageCacheKeyAction   
 * @Description:  提供页面缓存key管理的相关操作方法. 
 * @author 涂有  
 * @date 2015-12-23 10:39:35
 *
 */
public class PageCacheKeyAction extends BaseAction {

	/**   
	 * @Fields serialVersionUID : 
	 */ 
	private static final long serialVersionUID = 1L;
	
	//页面缓存key管理的业务层
	@Resource private IPageCacheKeyService keyService = null;
	//页面缓存组管理的业务层
	@Resource private IPageCacheGroupService groupService = null;
	
	@Resource private ISystemOperationLogService logService = null;
	private List<PageCacheGroup> groups;
	//页面缓存key管理实体
	private PageCacheKey key = null;
	
	/**
	 * 添加页面缓存key管理
	 * @Title: PageCacheKeyAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			
			groups = groupService.queryAll(PageCacheGroup.class);
			return "addPageCacheKey";
		} else {
			//非空验证.
			if(key == null){
				data = AjaxData.responseError(getText("key.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(key.getKeyName())) {
				data = AjaxData.responseError(getText("key.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(key.getTime())) {
				data = AjaxData.responseError(getText("time.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(key.getScope())) {
				data = AjaxData.responseError(getText("scope.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(key.getGroup())) {
				data = AjaxData.responseError(getText("group.required"));
				return JSON;
			}
			//保存页面缓存key管理实体
			keyService.persist(key);
			
			String logContent = "新增页面缓存";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("addSuccess"));
			return JSON;
		}
	}
	
	/**
	 * 修改页面缓存key管理
	 * @Title: PageCacheKeyAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			
			groups = groupService.queryAll(PageCacheGroup.class);
			this.setPageCacheKey(keyService.queryById(PageCacheKey.class.getSimpleName(), id));
			return "editPageCacheKey";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(key.getId())){
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(key.getKeyName())) {
				data = AjaxData.responseError(getText("key.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(key.getTime())) {
				data = AjaxData.responseError(getText("time.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(key.getScope())) {
				data = AjaxData.responseError(getText("scope.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(key.getGroup())) {
				data = AjaxData.responseError(getText("group.required"));
				return JSON;
			}
			//修改页面缓存key管理实体
			keyService.merge(key);
			
			String logContent = "修改页面缓存";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("editSuccess"));
			return JSON;
		}
	}
	
	/**
	 * @Description：刷新一批key的缓存
	 * @date 2015年12月24日 上午10:00:47
	 * @return
	 * @throws ServiceException 
	 */
	public String refresh() throws ServiceException{
		
		keyService.refresh(idsValue);
		String logContent = "刷新一批页面缓存";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		data = AjaxData.responseSuccess("");
		return JSON;
	}
	
	/**
	 * @Description：刷新所有的缓存
	 * @date 2015年12月24日 上午10:00:47
	 * @return
	 * @throws ServiceException 
	 */
	public String refreshAll() throws ServiceException{
		
		keyService.refreshAll();
		String logContent = "刷新所有页面缓存";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		data = AjaxData.responseSuccess("");
		return JSON;
	}
	
	/**
	 * 管理页面缓存key管理实体
	 * @Title: PageCacheKeyAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(keyService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "managePageCacheKey";
	}
	
	/**
	 * 查看回收站
	 * @Title: PageCacheKeyAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(keyService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recyclePageCacheKey";
	}
	
	/**
	 * 逻辑删除页面缓存key管理对象
	 * @Title: PageCacheKeyAction
	 * @Description: 把页面缓存key管理对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = keyService.logicDeleteEntity(PageCacheKey.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除页面缓存";
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
	 * 物理删除页面缓存key管理对象
	 * @Title: PageCacheKeyAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = keyService.remove(id);
		if(isSuc) {
			String logContent = "物理删除页面缓存";
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
	 * 还原一个页面缓存key管理对象
	 * @Title: PageCacheKeyAction
	 * @Description: 从回收站还原页面缓存key管理对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = keyService.restoreEntity(PageCacheKey.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原页面缓存";
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
	 * 审核页面缓存key管理对象
	 * @Title: PageCacheKeyAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = keyService.auditEntity(PageCacheKey.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核页面缓存";
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
	 * @Title: PageCacheKeyAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = keyService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作页面缓存";
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
	
	public PageCacheKey getPageCacheKey() {
		return key;
	}
	public void setPageCacheKey(PageCacheKey pageCacheKey) {
		this.key = pageCacheKey;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public IPageCacheKeyService getPageCacheKeyService() {
		return keyService;
	}

	public void setPageCacheKeyService(IPageCacheKeyService pageCacheKeyService) {
		this.keyService = pageCacheKeyService;
	}

	public IPageCacheGroupService getPageCacheGroupService() {
		return groupService;
	}

	public void setPageCacheGroupService(
			IPageCacheGroupService pageCacheGroupService) {
		this.groupService = pageCacheGroupService;
	}

	public List<PageCacheGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<PageCacheGroup> groups) {
		this.groups = groups;
	}

	public IPageCacheKeyService getKeyService() {
		return keyService;
	}

	public void setKeyService(IPageCacheKeyService keyService) {
		this.keyService = keyService;
	}

	public IPageCacheGroupService getGroupService() {
		return groupService;
	}

	public void setGroupService(IPageCacheGroupService groupService) {
		this.groupService = groupService;
	}

	public PageCacheKey getKey() {
		return key;
	}

	public void setKey(PageCacheKey key) {
		this.key = key;
	}

}
