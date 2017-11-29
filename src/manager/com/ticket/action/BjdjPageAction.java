package com.ticket.action;

import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.AdvertType;
import com.ticket.pojo.BjdjPage;
import com.ticket.pojo.BjdjPageTemplate;
import com.ticket.pojo.BjdjServicePackage;
import com.ticket.pojo.News;
import com.ticket.pojo.NewsClass;
import com.ticket.pojo.SystemDictionary;
import com.ticket.service.IAdvertTypeService;
import com.ticket.service.IBjdjPageService;
import com.ticket.service.IBjdjPageTemplateService;
import com.ticket.service.IBjdjServicePackageService;
import com.ticket.service.INewsClassService;
import com.ticket.service.INewsService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 便捷登机跳转页面控制器
 * @ClassName: BjdjPageAction   
 * @Description:  提供便捷登机跳转页面的相关操作方法. 
 * @author HiSay  
 * @date 2016-08-08 16:09:07
 *
 */
public class BjdjPageAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//便捷登机跳转页面的业务层
	@Resource private IBjdjPageService bjdjPageService;
	@Resource 
	private ISystemOperationLogService logService;
	@Resource
	private IBjdjServicePackageService bjdjServicePackageService;
	@Resource
	private INewsService newsService;
	@Resource
	private INewsClassService newsClassService;
	@Resource
	private IAdvertTypeService advertTypeService;
	@Resource
	private ISystemDictionaryService systemDictionaryService;
	@Resource
	private IBjdjPageTemplateService bjdjPageTemplateService;
	
	//便捷登机跳转页面实体
	private BjdjPage bjdjPage;
	//主键
	private String id;
    //页面名称
	private String name;
    //页面跳转链接
	private String url;
	//该页面上的服务电话号码
	private String servicePhone;
	//该页面上导航的点位的别名
	private String infoPositionAlias;
	//服务流程
	private String serviceFlow_id;
	//服务简介
	private String serviceProfile_id;
	//使用条款
	private String useSerms_id;
	//支付成功
	private String paymentSuccess_id;
	//首页图片
	private String advertType_id;
	//短信模板
	private String smsTemplate_id;
	//该便捷登机的服务码激活成功后的页面
	private String activeSuccess_id;
	
	/**
	 * 添加便捷登机跳转页面
	 * @Title: BjdjPageAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			NewsClass newsClass = newsClassService.queryByAlias(versionFlag, "bianjiedengji");
			List<News> list = newsService.queryListByType(newsClass.getId(), 0, versionFlag);
			ActionContext.getContext().put("newsList", list);
			
			List<AdvertType> advertTypes = advertTypeService.queryAll(AdvertType.class);
			ActionContext.getContext().put("advertTypes", advertTypes);
			
			SystemDictionary dictionary = systemDictionaryService.getByName("bjdj");
			List<SystemDictionary> dictionaries = systemDictionaryService.querySubByParent(dictionary.getId());
			ActionContext.getContext().put("dictionaries", dictionaries);
			
			List<BjdjPageTemplate> bjdjPageTemplates = bjdjPageTemplateService.queryAll(BjdjPageTemplate.class);
			ActionContext.getContext().put("bjdjPageTemplates", bjdjPageTemplates);
			return "addBjdjPage";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError(getText("name.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(url)) {
				data = AjaxData.responseError(getText("url.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(servicePhone)) {
				data = AjaxData.responseError("服务电话号码不能为空！");
				return JSON;
			}
			if(GeneralUtil.isNull(infoPositionAlias)) {
				data = AjaxData.responseError("导航的点位别名不能为空！");
				return JSON;
			}
			//保存便捷登机跳转页面实体
			boolean isSuc = bjdjPageService.persist(name, url, servicePhone, infoPositionAlias,serviceFlow_id,serviceProfile_id,useSerms_id,paymentSuccess_id,advertType_id,smsTemplate_id,activeSuccess_id, versionFlag);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增便捷登机跳转页面：" + name;
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
	 * 修改便捷登机跳转页面
	 * @Title: BjdjPageAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			NewsClass newsClass = newsClassService.queryByAlias(versionFlag, "bianjiedengji");
			List<News> list = newsService.queryListByType(newsClass.getId(), 0, versionFlag);
			ActionContext.getContext().put("newsList", list);
			
			List<AdvertType> advertTypes = advertTypeService.queryAll(AdvertType.class);
			ActionContext.getContext().put("advertTypes", advertTypes);
			
			SystemDictionary dictionary = systemDictionaryService.getByName("bjdj");
			List<SystemDictionary> dictionaries = systemDictionaryService.querySubByParent(dictionary.getId());
			ActionContext.getContext().put("dictionaries", dictionaries);
			
			List<BjdjPageTemplate> bjdjPageTemplates = bjdjPageTemplateService.queryAll(BjdjPageTemplate.class);
			ActionContext.getContext().put("bjdjPageTemplates", bjdjPageTemplates);
			this.setBjdjPage(bjdjPageService.queryById(BjdjPage.class.getSimpleName(), id));
			return "editBjdjPage";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
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
			if(GeneralUtil.isNull(servicePhone)) {
				data = AjaxData.responseError("服务电话号码不能为空！");
				return JSON;
			}
			if(GeneralUtil.isNull(infoPositionAlias)) {
				data = AjaxData.responseError("导航的点位别名不能为空！");
				return JSON;
			}
			//修改便捷登机跳转页面实体
			boolean isSuc = bjdjPageService.merge(id, name, url, servicePhone, infoPositionAlias,serviceFlow_id,serviceProfile_id,useSerms_id,paymentSuccess_id,advertType_id, smsTemplate_id, activeSuccess_id,versionFlag);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改便捷登机跳转页面：" + name;
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
	 * 管理便捷登机跳转页面实体
	 * @Title: BjdjPageAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(bjdjPageService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageBjdjPage";
	}
	
	/**
	 * 查看回收站
	 * @Title: BjdjPageAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(bjdjPageService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleBjdjPage";
	}
	
	/**
	 * 逻辑删除便捷登机跳转页面对象
	 * @Title: BjdjPageAction
	 * @Description: 把便捷登机跳转页面对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = bjdjPageService.logicDeleteEntity(BjdjPage.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "删除便捷登机跳转页面";
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
	 * 物理删除便捷登机跳转页面对象
	 * @Title: BjdjPageAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		List<BjdjServicePackage> bjdjServicePackages = bjdjServicePackageService.queryByBjdjPage(id);
		if(bjdjServicePackages.size() > 0){
			data = AjaxData.responseError("这个页面还关联着便捷登机套餐，请先删除对应的套餐!");
		}else{
			boolean isSuc = bjdjPageService.remove(id);
			if(isSuc) {
				String logContent = "删除便捷登机跳转页面";
				String logName = SystemOparetionLogUtil.getLogName();
				String logTime = SystemOparetionLogUtil.getLogTime();
				String logIp = SystemOparetionLogUtil.getLogIp();
				
				logService.persist(logName, logContent, logTime, logIp, versionFlag);
				data = AjaxData.responseSuccess(getText("deleteSuccess"));
			} else {
				data = AjaxData.responseError(getText("deleteFailed"));
			}
		}
		return JSON;
	}
	
	/**
	 * 还原一个便捷登机跳转页面对象
	 * @Title: BjdjPageAction
	 * @Description: 从回收站还原便捷登机跳转页面对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = bjdjPageService.restoreEntity(BjdjPage.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核便捷登机跳转页面对象
	 * @Title: BjdjPageAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = bjdjPageService.auditEntity(BjdjPage.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: BjdjPageAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = bjdjPageService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作便捷登机跳转页面";
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
	
	public BjdjPage getBjdjPage() {
		return bjdjPage;
	}
	public void setBjdjPage(BjdjPage bjdjPage) {
		this.bjdjPage = bjdjPage;
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

	public String getServicePhone() {
		return servicePhone;
	}

	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}

	public String getInfoPositionAlias() {
		return infoPositionAlias;
	}

	public void setInfoPositionAlias(String infoPositionAlias) {
		this.infoPositionAlias = infoPositionAlias;
	}

	public String getServiceFlow_id() {
		return serviceFlow_id;
	}

	public void setServiceFlow_id(String serviceFlow_id) {
		this.serviceFlow_id = serviceFlow_id;
	}

	public String getServiceProfile_id() {
		return serviceProfile_id;
	}

	public void setServiceProfile_id(String serviceProfile_id) {
		this.serviceProfile_id = serviceProfile_id;
	}

	public String getUseSerms_id() {
		return useSerms_id;
	}

	public void setUseSerms_id(String useSerms_id) {
		this.useSerms_id = useSerms_id;
	}

	public String getPaymentSuccess_id() {
		return paymentSuccess_id;
	}

	public void setPaymentSuccess_id(String paymentSuccess_id) {
		this.paymentSuccess_id = paymentSuccess_id;
	}

	public String getAdvertType_id() {
		return advertType_id;
	}

	public void setAdvertType_id(String advertType_id) {
		this.advertType_id = advertType_id;
	}

	public String getSmsTemplate_id() {
		return smsTemplate_id;
	}

	public void setSmsTemplate_id(String smsTemplate_id) {
		this.smsTemplate_id = smsTemplate_id;
	}

	public String getActiveSuccess_id() {
		return activeSuccess_id;
	}

	public void setActiveSuccess_id(String activeSuccess_id) {
		this.activeSuccess_id = activeSuccess_id;
	}
}
