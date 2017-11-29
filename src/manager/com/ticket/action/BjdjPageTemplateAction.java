package com.ticket.action;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjPageTemplate;
import com.ticket.service.IBjdjPageTemplateService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * jdj支付激活页面模板控制器
 * @ClassName: BjdjPageTemplateAction   
 * @Description:  提供jdj支付激活页面模板的相关操作方法. 
 * @author HiSay  
 * @date 2016-08-18 15:26:40
 *
 */
public class BjdjPageTemplateAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//jdj支付激活页面模板的业务层
	@Resource private IBjdjPageTemplateService bjdjPageTemplateService;
	//jdj支付激活页面模板实体
	private BjdjPageTemplate bjdjPageTemplate;
	@Resource private ISystemOperationLogService logService = null;
	//主键
	private String id;
    //模板名称
	private String name;
    //页面内容
	private String content;
    //按钮名称
	private String buttonName;
    //按钮链接
	private String buttonUrl;
    //按钮类型
	private String buttonType;
    //按钮样式
	private String buttonClass;
	
	private String[] buttonNames;
	
	private String[] buttonUrls;
	
	private String[] buttonTypes;
	
	private String[] buttonClasses;
	
	/**
	 * 详细信息
	 * @return
	 * @throws ServiceException
	 */
	public String detail() throws ServiceException{
		this.setBjdjPageTemplate(bjdjPageTemplateService.queryById(BjdjPageTemplate.class.getName(), id));
		return "detail";
	}
	/**
	 * 添加jdj支付激活页面模板
	 * @Title: BjdjPageTemplateAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addBjdjPageTemplate";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError(getText("name.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(content)) {
				data = AjaxData.responseError(getText("content.required"));
				return JSON;
			}
			/*if(GeneralUtil.isNull(buttonNames)) {
				data = AjaxData.responseError(getText("buttonName.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(buttonUrls)) {
				data = AjaxData.responseError(getText("buttonUrl.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(buttonTypes)) {
				data = AjaxData.responseError(getText("buttonType.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(buttonClasses)) {
				data = AjaxData.responseError(getText("buttonClass.required"));
				return JSON;
			}*/
			if(buttonNames.length == 1){
				buttonName = buttonNames[0].trim();
			}else{
				buttonName = buttonNames[0];
				for (int i = 1; i < buttonNames.length; i++) {
					buttonName += "," + buttonNames[i].trim() + ",";
				}
			}
			if(buttonUrls.length == 1){
				buttonUrl = buttonUrls[0].trim();
			}else{
				buttonUrl = buttonUrls[0];
				for (int i = 1; i < buttonUrls.length; i++) {
					buttonUrl += "," + buttonUrls[i].trim() + ",";
				}
			}
			if(buttonTypes.length == 1){
				buttonType = buttonTypes[0].trim();
			}else{
				buttonType = buttonTypes[0];
				for (int i = 1; i < buttonTypes.length; i++) {
					buttonType += "," + buttonTypes[i].trim() + ",";
				}
			}
			if(buttonClasses.length == 1){
				buttonClass = buttonClasses[0].trim();
			}else{
				buttonClass = buttonClasses[0];
				for (int i = 1; i < buttonClasses.length; i++) {
					buttonClass += "," + buttonClasses[i].trim() + ",";
				}
			}
			//保存jdj支付激活页面模板实体
			boolean isSuc = bjdjPageTemplateService.persist(name, content, buttonName, buttonUrl, buttonType, buttonClass, versionFlag);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增支付激活页面模板";
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
	 * 修改jdj支付激活页面模板
	 * @Title: BjdjPageTemplateAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			BjdjPageTemplate bjdjPageTemplate = bjdjPageTemplateService.queryById(BjdjPageTemplate.class.getName(), id);
			String buttonName = bjdjPageTemplate.getButtonName();
			String buttonUrl = bjdjPageTemplate.getButtonUrl();
			String buttonType = bjdjPageTemplate.getButtonType();
			String buttonClass = bjdjPageTemplate.getButtonClass();
			String name = bjdjPageTemplate.getName();
			String content = bjdjPageTemplate.getContent();
			if(buttonName.indexOf(",") != -1){
				buttonNames = buttonName.split(",");
				if(GeneralUtil.isNotNull(buttonUrl)){
					buttonUrls = buttonUrl.split(",");
				}
				buttonTypes = buttonType.split(",");
				if(GeneralUtil.isNotNull(buttonClass)){
					buttonClasses = buttonClass.split(",");
				}
				ActionContext.getContext().put("name", name);
				ActionContext.getContext().put("content", content);
				ActionContext.getContext().put("buttonNames", buttonNames);
				ActionContext.getContext().put("buttonUrls", buttonUrls);
				ActionContext.getContext().put("buttonTypes", buttonTypes);
				ActionContext.getContext().put("buttonClasses", buttonClasses);
			}else{
				this.setBjdjPageTemplate(bjdjPageTemplateService.queryById(BjdjPageTemplate.class.getSimpleName(), id));
			}
			
			return "editBjdjPageTemplate";
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
			if(GeneralUtil.isNull(content)) {
				data = AjaxData.responseError(getText("content.required"));
				return JSON;
			}
			/*if(GeneralUtil.isNull(buttonName)) {
				data = AjaxData.responseError(getText("buttonName.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(buttonUrl)) {
				data = AjaxData.responseError(getText("buttonUrl.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(buttonType)) {
				data = AjaxData.responseError(getText("buttonType.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(buttonClass)) {
				data = AjaxData.responseError(getText("buttonClass.required"));
				return JSON;
			}*/
			if(buttonNames.length == 1){
				buttonName = buttonNames[0].trim();
			}else{
				buttonName = buttonNames[0];
				for (int i = 1; i < buttonNames.length; i++) {
					buttonName += "," + buttonNames[i].trim() + ",";
				}
			}
			if(buttonUrls.length == 1){
				buttonUrl = buttonUrls[0].trim();
			}else{
				buttonUrl = buttonUrls[0];
				for (int i = 1; i < buttonUrls.length; i++) {
					buttonUrl += "," + buttonUrls[i].trim() + ",";
				}
			}
			if(buttonTypes.length == 1){
				buttonType = buttonTypes[0].trim();
			}else{
				buttonType = buttonTypes[0];
				for (int i = 1; i < buttonTypes.length; i++) {
					buttonType += "," + buttonTypes[i].trim() + ",";
				}
			}
			if(buttonClasses.length == 1){
				buttonClass = buttonClasses[0].trim();
			}else{
				buttonClass = buttonClasses[0];
				for (int i = 1; i < buttonClasses.length; i++) {
					buttonClass += "," + buttonClasses[i].trim() + ",";
				}
			}
			//修改jdj支付激活页面模板实体
			boolean isSuc = bjdjPageTemplateService.merge(id, name, content, buttonName, buttonUrl, buttonType, buttonClass,  versionFlag);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改支付激活页面模板";
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
	 * 管理jdj支付激活页面模板实体
	 * @Title: BjdjPageTemplateAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(bjdjPageTemplateService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageBjdjPageTemplate";
	}
	
	/**
	 * 查看回收站
	 * @Title: BjdjPageTemplateAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(bjdjPageTemplateService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleBjdjPageTemplate";
	}
	
	/**
	 * 逻辑删除jdj支付激活页面模板对象
	 * @Title: BjdjPageTemplateAction
	 * @Description: 把jdj支付激活页面模板对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = bjdjPageTemplateService.logicDeleteEntity(BjdjPageTemplate.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "删除支付激活页面模板";
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
	 * 物理删除jdj支付激活页面模板对象
	 * @Title: BjdjPageTemplateAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = bjdjPageTemplateService.remove(id);
		if(isSuc) {
			String logContent = "删除支付激活页面模板";
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
	 * 还原一个jdj支付激活页面模板对象
	 * @Title: BjdjPageTemplateAction
	 * @Description: 从回收站还原jdj支付激活页面模板对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = bjdjPageTemplateService.restoreEntity(BjdjPageTemplate.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核jdj支付激活页面模板对象
	 * @Title: BjdjPageTemplateAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = bjdjPageTemplateService.auditEntity(BjdjPageTemplate.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: BjdjPageTemplateAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = bjdjPageTemplateService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作支付激活页面模板";
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
	
	public BjdjPageTemplate getBjdjPageTemplate() {
		return bjdjPageTemplate;
	}
	public void setBjdjPageTemplate(BjdjPageTemplate bjdjPageTemplate) {
		this.bjdjPageTemplate = bjdjPageTemplate;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getButtonName() {
		return buttonName;
	}
	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}
	public String getButtonUrl() {
		return buttonUrl;
	}
	public void setButtonUrl(String buttonUrl) {
		this.buttonUrl = buttonUrl;
	}
	public String getButtonType() {
		return buttonType;
	}
	public void setButtonType(String buttonType) {
		this.buttonType = buttonType;
	}
	public String getButtonClass() {
		return buttonClass;
	}
	public void setButtonClass(String buttonClass) {
		this.buttonClass = buttonClass;
	}

	public String[] getButtonNames() {
		return buttonNames;
	}

	public void setButtonNames(String[] buttonNames) {
		this.buttonNames = buttonNames;
	}

	public String[] getButtonUrls() {
		return buttonUrls;
	}

	public void setButtonUrls(String[] buttonUrls) {
		this.buttonUrls = buttonUrls;
	}

	public String[] getButtonTypes() {
		return buttonTypes;
	}

	public void setButtonTypes(String[] buttonTypes) {
		this.buttonTypes = buttonTypes;
	}

	public String[] getButtonClasses() {
		return buttonClasses;
	}

	public void setButtonClasses(String[] buttonClasses) {
		this.buttonClasses = buttonClasses;
	}
}
