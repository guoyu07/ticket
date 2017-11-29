package com.ticket.action;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.PreResultDefinition;
import com.ticket.service.ICommonSearchService;
import com.ticket.service.IKeyWordLocationService;
import com.ticket.service.INewsService;
import com.ticket.service.IPreResultDefinitionService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.serviceImpl.CommonSearchServiceImpl;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 预定义搜索结果控制器
 * @ClassName: PreResultDefinitionAction   
 * @Description:  提供预定义搜索结果的相关操作方法. 
 * @author HiSay  
 * @date 2015-12-14 18:57:30
 *
 */
public class PreResultDefinitionAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//预定义搜索结果的业务层
	@Resource private IPreResultDefinitionService preResultDefinitionService = null;
	@Resource private INewsService newsService = null;
	@Resource private ICommonSearchService commonSearchService = null;
	@Resource private ISystemOperationLogService logService = null;
	@Resource private IKeyWordLocationService keyWordLocationService;
	//预定义搜索结果实体
	private PreResultDefinition preResultDefinition = null;
	//主键
	private String id = null;
    //页面名称
	private String pageName = null;
    //系统关键词
	private String keyword = null;
	//系统关键词
	private String negativeKeyword = null;
	private String _negativeKeyword = null;
    //系统关键词描述
	private String description = null;
    //页面链接
	private String url = null;
	//排序值
	private Integer orderValue = 0;
	private Integer type = 0;
	private String pcUrl = null;
	
	
	/**
	 * 刷新搜索内存
	 * @return
	 */
	public String flushMemory(){
		
		CommonSearchServiceImpl.location.clear();
		keyWordLocationService.init();
		
		CommonSearchServiceImpl.seo.clear();
		CommonSearchServiceImpl.business.clear();
		preResultDefinitionService.init();
//		newsService.init();
//		data = AjaxData.responseSuccess(null);
		return JSON;
	}
	
	
	/**
	 * 搜索内存
	 * @return
	 */
	public String memory(){
		
		return "memory";
	}
	
	
	/**
	 * 搜索内存详情
	 * @return
	 */
	public String showDeatils(){
		
		ActionContext.getContext().put("ov", orderValue);
		return "showDeatils";
	}
	
	/**
	 * 添加预定义搜索结果
	 * @Title: PreResultDefinitionAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addPreResultDefinition";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(pageName)) {
				data = AjaxData.responseError(getText("pageName.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(keyword)) {
				data = AjaxData.responseError(getText("keyword.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(url)) {
				data = AjaxData.responseError(getText("url.required"));
				return JSON;
			}
			//保存预定义搜索结果实体
			boolean isSuc = preResultDefinitionService.persist(type, pageName, keyword, negativeKeyword,
					_negativeKeyword, description, url,pcUrl, versionFlag);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增预定义搜索结果";
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
	 * 修改预定义搜索结果
	 * @Title: PreResultDefinitionAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setPreResultDefinition(preResultDefinitionService.queryById(PreResultDefinition.class.getSimpleName(), id));
			return "editPreResultDefinition";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(pageName)) {
				data = AjaxData.responseError(getText("pageName.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(keyword)) {
				data = AjaxData.responseError(getText("keyword.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(url)) {
				data = AjaxData.responseError(getText("url.required"));
				return JSON;
			}
			//修改预定义搜索结果实体
			boolean isSuc = preResultDefinitionService.merge(id, pageName, keyword, negativeKeyword,
					_negativeKeyword, description, url,pcUrl, versionFlag, orderValue);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改预定义搜索结果";
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
	 * 管理预定义搜索结果实体
	 * @Title: PreResultDefinitionAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(preResultDefinitionService.queryEntity(" and s.type=?3", type));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "managePreResultDefinition";
	}
	
	/**
	 * 管理预定义搜索结果实体
	 * @Title: PreResultDefinitionAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String search() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(preResultDefinitionService.searchByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE, keyword));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "managePreResultDefinition";
	}
	
	/**
	 * 查看回收站
	 * @Title: PreResultDefinitionAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(preResultDefinitionService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recyclePreResultDefinition";
	}
	
	/**
	 * 逻辑删除预定义搜索结果对象
	 * @Title: PreResultDefinitionAction
	 * @Description: 把预定义搜索结果对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = preResultDefinitionService.logicDeleteEntity(PreResultDefinition.class.getSimpleName(), id);
		commonSearchService.sendFlushNotify();
		if(isSuc) {
			String logContent = "删除预定义搜索结果";
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
	 * 物理删除预定义搜索结果对象
	 * @Title: PreResultDefinitionAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = preResultDefinitionService.remove(id);
		commonSearchService.sendFlushNotify();
		if(isSuc) {
			String logContent = "删除预定义搜索结果";
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
	 * 还原一个预定义搜索结果对象
	 * @Title: PreResultDefinitionAction
	 * @Description: 从回收站还原预定义搜索结果对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = preResultDefinitionService.restoreEntity(PreResultDefinition.class.getSimpleName(), id);
		PreResultDefinition pre = preResultDefinitionService.get(PreResultDefinition.class, id);
		preResultDefinitionService.updateSeo(CommonSearchServiceImpl.seo, pre);
		
		if(isSuc) {
			String logContent = "还原预定义搜索结果";
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
	 * 审核预定义搜索结果对象
	 * @Title: PreResultDefinitionAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = preResultDefinitionService.auditEntity(PreResultDefinition.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: PreResultDefinitionAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = preResultDefinitionService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作预定义搜索结果";
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
	
	public PreResultDefinition getPreResultDefinition() {
		return preResultDefinition;
	}
	public void setPreResultDefinition(PreResultDefinition preResultDefinition) {
		this.preResultDefinition = preResultDefinition;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(Integer orderValue) {
		this.orderValue = orderValue;
	}

	public String getPcUrl() {
		return pcUrl;
	}

	public void setPcUrl(String pcUrl) {
		this.pcUrl = pcUrl;
	}

	public IPreResultDefinitionService getPreResultDefinitionService() {
		return preResultDefinitionService;
	}

	public void setPreResultDefinitionService(
			IPreResultDefinitionService preResultDefinitionService) {
		this.preResultDefinitionService = preResultDefinitionService;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getNegativeKeyword() {
		return negativeKeyword;
	}


	public void setNegativeKeyword(String negativeKeyword) {
		this.negativeKeyword = negativeKeyword;
	}


	public String get_negativeKeyword() {
		return _negativeKeyword;
	}


	public void set_negativeKeyword(String _negativeKeyword) {
		this._negativeKeyword = _negativeKeyword;
	}


	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}
}
