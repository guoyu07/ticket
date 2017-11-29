package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.constants.JQueryUploadConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.Advert;
import com.ticket.service.IAdvertService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 广告信息控制器
 * @ClassName: AdvertAction   
 * @Description:  提供广告信息的相关操作方法. 
 * @author HiSay  
 * @date 2015-10-27 10:31:54
 */
public class AdvertAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//广告信息的业务层
	@Resource private IAdvertService advertService = null;
	@Resource private ISystemOperationLogService logService = null;
	//广告信息实体
	private Advert advert = null;
	//主键
	private String id = null;
	//广告类型
	private String advertType_id = null;
    //广告名称
	private String name = null;
    //广告链接
	private String url = null;
	//广告图片
	private String picture = null;
    //广告内容
	private String content = null;
	
	private String arriveCitys = null;
	
	/**
	 * 添加广告信息
	 * @Title: AdvertAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addAdvert";
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
			if(GeneralUtil.isNull(content)) {
				data = getText("content.required");
				return TEXT;
			}
			//保存广告信息实体
			boolean isSuc = advertService.persist(advertType_id,name, url,getSinglePictureUrlFromJQueryUpLoader(fileNames, directory, JQueryUploadConstants.PICTURE_TYPE_DEFAULT), content,orderValue,arriveCitys, versionFlag);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增广告信息";
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
	 * 修改广告信息
	 * @Title: AdvertAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setAdvert(advertService.queryById(Advert.class.getSimpleName(), id));
			return "editAdvert";
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
			if(GeneralUtil.isNull(content)) {
				data = getText("content.required");
				return TEXT;
			}
			//修改广告信息实体
			boolean isSuc = advertService.merge(id,advertType_id, name, url,getSinglePictureUrlFromJQueryUpLoader(fileNames, directory, JQueryUploadConstants.PICTURE_TYPE_DEFAULT), content, orderValue,arriveCitys, versionFlag);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改广告信息";
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
	 * 管理广告信息实体
	 * @Title: AdvertAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(advertService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageAdvert";
	}
	
	/**
	 * 查看回收站
	 * @Title: AdvertAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(advertService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleAdvert";
	}
	
	/**
	 * 逻辑删除广告信息对象
	 * @Title: AdvertAction
	 * @Description: 把广告信息对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = advertService.logicDeleteEntity(Advert.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除广告信息";
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
	 * 物理删除广告信息对象
	 * @Title: AdvertAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = advertService.remove(id);
		if(isSuc) {
			String logContent = "物理删除广告信息";
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
	 * 还原一个广告信息对象
	 * @Title: AdvertAction
	 * @Description: 从回收站还原广告信息对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = advertService.restoreEntity(Advert.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原广告信息";
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
	 * 审核广告信息对象
	 * @Title: AdvertAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = advertService.auditEntity(Advert.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核广告信息";
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
	 * @Title: AdvertAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = advertService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作广告信息";
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
	 * 批量彻底删除广告信息
	 * @return
	 * @throws ServiceException
	 */
	public String batchRealDelete() throws ServiceException{
		boolean isSuc = advertService.batchRealDelete(idsValue, versionFlag);
		if(isSuc) {
			String logContent = "批量删除广告信息";
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
	
	public Advert getAdvert() {
		return advert;
	}
	public void setAdvert(Advert advert) {
		this.advert = advert;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAdvertType_id() {
		return advertType_id;
	}

	public void setAdvertType_id(String advertTypeId) {
		advertType_id = advertTypeId;
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
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public IAdvertService getAdvertService() {
		return advertService;
	}

	public void setAdvertService(IAdvertService advertService) {
		this.advertService = advertService;
	}

	public ISystemOperationLogService getLogService() {
		return logService;
	}

	public void setLogService(ISystemOperationLogService logService) {
		this.logService = logService;
	}

	public String getArriveCitys() {
		return arriveCitys;
	}

	public void setArriveCitys(String arriveCitys) {
		this.arriveCitys = arriveCitys;
	}
}
