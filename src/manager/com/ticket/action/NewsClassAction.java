package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.constants.JQueryUploadConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.NewsClass;
import com.ticket.service.INewsClassService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.NewsClassUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 新闻类别控制器
 * @ClassName: NewsClassAction   
 * @Description:  提供新闻类别的相关操作方法. 
 * @author HiSay  
 * @date 2015-10-13 17:40:45
 *
 */
public class NewsClassAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	//新闻类别的业务层
	@Resource private INewsClassService newsClassService = null;
	
	@Resource private ISystemOperationLogService logService = null;
	//新闻类别实体
	private NewsClass newsClass = null;
	//主键
	private String id = null;
	//类别名称
	private String name = null;
	//权限id
	private String permissionId = null;
	//上级类别
	private String parent_id = null;
	//类别描述
	private String descript = null;
	private String newsClassHtml = null;
	// 别名
	private String alias = null;
	//英文名称
	private String englishName = null;
	//列表跳转模板页面ID
	private String listPageRedirectTemplate_id = null;
	//排序值
	private Integer orderValue = 0;
	//类别图片
	private String picture = null;
	private String pcListTemplate_id = null;
	private String pcViewTemplate_id = null;

	/**
	 * 添加新闻类别
	 * @Title: NewsClassAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setNewsClassHtml(newsClassService.queryNewsClassSelectOptionHtml(parent_id, versionFlag));
			return "addNewsClass";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(name)) {
				data = getText("name.required");
				return TEXT;
			}
			if(GeneralUtil.isNotNull(alias) && newsClassService.validateAliasExists(versionFlag, alias, null)) {
				data = AjaxData.responseError(getText("alias.exists"));
				return JSON;
			}
			//验证新闻类别权限是否存在
			if(GeneralUtil.isNotNull(permissionId)){
				if(newsClassService.validatePermissionExists(permissionId,versionFlag)){
					data = AjaxData.responseError(getText("permissionId.exists"));
					return JSON;
				}
			}
			//保存新闻类别实体
			boolean isSuc = newsClassService.persist(name,permissionId, parent_id, descript, versionFlag, alias,
					englishName, listPageRedirectTemplate_id, orderValue,
					getSinglePictureUrlFromJQueryUpLoader(fileNames, directory,
							JQueryUploadConstants.PICTURE_TYPE_DEFAULT),pcListTemplate_id,pcViewTemplate_id);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增新闻类别信息";
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
	 * 修改新闻类别
	 * @Title: NewsClassAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setNewsClass(newsClassService.queryById(NewsClass.class.getSimpleName(), id));
			if(this.getNewsClass().getParent_id() != null) {
				this.setNewsClassHtml(newsClassService.queryNewsClassSelectOptionHtml(this.getNewsClass().getParent_id(), versionFlag));
			} else {
				this.setNewsClassHtml(newsClassService.queryNewsClassSelectOptionHtml(null, versionFlag));
			}
			return "editNewsClass";
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
			if(GeneralUtil.isNotNull(alias) && newsClassService.validateAliasExists(versionFlag, alias, id)) {
				data = getText("alias.exists");
				return TEXT;
			}
			//验证新闻类别权限是否存在
			if(GeneralUtil.isNotNull(permissionId)){
				if(newsClassService.validatePermissionExists(permissionId,versionFlag)){
					data = AjaxData.responseError(getText("permissionId.exists"));
					return JSON;
				}
			}
			//修改新闻类别实体
			boolean isSuc = newsClassService.merge(id, name,permissionId, parent_id, descript, versionFlag, alias, 
					englishName, listPageRedirectTemplate_id, orderValue,
													getSinglePictureUrlFromJQueryUpLoader(fileNames,
															directory, JQueryUploadConstants.PICTURE_TYPE_DEFAULT)
															,pcListTemplate_id,pcViewTemplate_id);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改新闻类别信息";
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
	 * 管理新闻类别实体
	 * @Title: NewsClassAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		if(GeneralUtil.isNotNull(permissionId)){
			this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
			NewsClass nc = newsClassService.queryByPermissionId(permissionId, versionFlag);
			//判断权限id是否为空
			this.setPageModule(newsClassService.queryPageModuleByParentId(nc.getId(), ContextConstants.ADMIN_COMMON_PAGE_SIZE, versionFlag));
			this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
			return "manageNewsClass";
		}
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setNewsClassHtml(NewsClassUtil.recursion(newsClassService.queryFirstNewsClassList(versionFlag)));
		return "manageNewsClass";
	}

	/**
	 * 查看回收站
	 * @Title: NewsClassAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(newsClassService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleNewsClass";
	}

	/**
	 * 逻辑删除新闻类别对象
	 * @Title: NewsClassAction
	 * @Description: 把新闻类别对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = newsClassService.logicDeleteEntity(NewsClass.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除新闻类别信息";
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
	 * 物理删除新闻类别对象
	 * @Title: NewsClassAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		newsClassService.remove(id);
		return SUCCESS;
	}

	/**
	 * 还原一个新闻类别对象
	 * @Title: NewsClassAction
	 * @Description: 从回收站还原新闻类别对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = newsClassService.restoreEntity(NewsClass.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原新闻类别信息";
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
	 * 审核新闻类别对象
	 * @Title: NewsClassAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = newsClassService.auditEntity(NewsClass.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核新闻类别信息";
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
	 * @Title: NewsClassAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = newsClassService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作新闻类别信息";
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
		newsClassService.setNewsClassIsDefaultShow(NewsClass.class.getSimpleName(), id);
		return SUCCESS;
	}
	public NewsClass getNewsClass() {
		return newsClass;
	}
	public void setNewsClass(NewsClass newsClass) {
		this.newsClass = newsClass;
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
	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}

	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public String getNewsClassHtml() {
		return newsClassHtml;
	}
	public void setNewsClassHtml(String newsClassHtml) {
		this.newsClassHtml = newsClassHtml;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getListPageRedirectTemplate_id() {
		return listPageRedirectTemplate_id;
	}

	public void setListPageRedirectTemplate_id(String listPageRedirectTemplateId) {
		listPageRedirectTemplate_id = listPageRedirectTemplateId;
	}

	public Integer getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(Integer orderValue) {
		this.orderValue = orderValue;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getPcListTemplate_id() {
		return pcListTemplate_id;
	}

	public void setPcListTemplate_id(String pcListTemplateId) {
		pcListTemplate_id = pcListTemplateId;
	}

	public String getPcViewTemplate_id() {
		return pcViewTemplate_id;
	}

	public void setPcViewTemplate_id(String pcViewTemplateId) {
		pcViewTemplate_id = pcViewTemplateId;
	}

	public INewsClassService getNewsClassService() {
		return newsClassService;
	}

	public void setNewsClassService(INewsClassService newsClassService) {
		this.newsClassService = newsClassService;
	}
	
}
