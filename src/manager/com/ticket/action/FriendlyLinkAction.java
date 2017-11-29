package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.FriendlyLink;
import com.ticket.service.IFriendlyLinkService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 友情链接控制器
 * @ClassName: FriendlyLinkAction   
 * @Description:  提供友情链接的相关操作方法. 
 * @author HiSay  
 * @date 2015-11-20 10:43:11
 *
 */
public class FriendlyLinkAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//友情链接的业务层
	@Resource private IFriendlyLinkService friendlyLinkService = null;
	
	@Resource private ISystemOperationLogService logService = null;
	//友情链接实体
	private FriendlyLink friendlyLink = null;
	//主键
	private String id = null;
    //商家id
	private String businessInfo_id = null;
    //链接名称
	private String name = null;
    //链接地址
	private String url = null;
	
	/**
	 * 添加友情链接
	 * @Title: FriendlyLinkAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addFriendlyLink";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(businessInfo_id)) {
				data = AjaxData.responseError(getText("businessInfo_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError(getText("name.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(url)) {
				data = AjaxData.responseError(getText("url.required"));
				return JSON;
			}
			//保存友情链接实体
			boolean isSuc = friendlyLinkService.persist(businessInfo_id, name, url, versionFlag);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增友情链接";
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
	 * 修改友情链接
	 * @Title: FriendlyLinkAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setFriendlyLink(friendlyLinkService.queryById(FriendlyLink.class.getSimpleName(), id));
			return "editFriendlyLink";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(businessInfo_id)) {
				data = AjaxData.responseError(getText("businessInfo_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError(getText("name.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(url)) {
				data = AjaxData.responseError(getText("url.required"));
				return JSON;
			}
			//修改友情链接实体
			boolean isSuc = friendlyLinkService.merge(id, businessInfo_id, name, url,  versionFlag);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改友情链接";
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
	 * 管理友情链接实体
	 * @Title: FriendlyLinkAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		if(GeneralUtil.isNotNull(businessInfo_id)){
			this.setPageModule(friendlyLinkService.queryEntityByBusinessInfoId(businessInfo_id,ContextConstants.ADMIN_COMMON_PAGE_SIZE,versionFlag));
			this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
			return "manageFriendlyLink";
		}
		this.setPageModule(friendlyLinkService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageFriendlyLink";
	}
	
	/**
	 * 查看回收站
	 * @Title: FriendlyLinkAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(friendlyLinkService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleFriendlyLink";
	}
	
	/**
	 * 逻辑删除友情链接对象
	 * @Title: FriendlyLinkAction
	 * @Description: 把友情链接对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = friendlyLinkService.logicDeleteEntity(FriendlyLink.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除友情链接";
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
	 * 物理删除友情链接对象
	 * @Title: FriendlyLinkAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = friendlyLinkService.remove(id);
		if(isSuc) {
			String logContent = "物理删除友情链接";
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
	 * 还原一个友情链接对象
	 * @Title: FriendlyLinkAction
	 * @Description: 从回收站还原友情链接对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = friendlyLinkService.restoreEntity(FriendlyLink.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原友情链接";
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
	 * 审核友情链接对象
	 * @Title: FriendlyLinkAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = friendlyLinkService.auditEntity(FriendlyLink.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核友情链接";
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
	 * @Title: FriendlyLinkAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = friendlyLinkService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作友情链接";
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
	
	public FriendlyLink getFriendlyLink() {
		return friendlyLink;
	}
	public void setFriendlyLink(FriendlyLink friendlyLink) {
		this.friendlyLink = friendlyLink;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBusinessInfo_id() {
		return businessInfo_id;
	}
	public void setBusinessInfo_id(String businessInfo_id) {
		this.businessInfo_id = businessInfo_id;
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
}
