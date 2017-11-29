package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.PageRedirectTemplate;
import com.ticket.service.IPageRedirectTemplateService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 页面跳转模板控制器
 * @ClassName: PageRedirectTemplateAction   
 * @Description:  提供页面跳转模板的相关操作方法. 
 * @author HiSay  
 * @date 2015-10-22 14:00:50
 *
 */
public class PageRedirectTemplateAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//页面跳转模板的业务层
	@Resource private IPageRedirectTemplateService pageRedirectTemplateService = null;
	@Resource private ISystemOperationLogService logService = null;
	//页面跳转模板实体
	private PageRedirectTemplate pageRedirectTemplate = null;
	//主键
	private String id = null;
    //页面名称
	private String name = null;
    //跳转到哪个JSP
	private String toPage = null;
    //模板类型
	private Integer type = null;
    //是否单篇文章
	private Integer isSinglePage = null;
	//所在目录
	private String directory = null;
	//跳转到哪个JSP(AJax)
	private String toPageAjax = null;
	
	
	/**
	 * 添加页面跳转模板
	 * @Title: PageRedirectTemplateAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addPageRedirectTemplate";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(name)) {
				data = getText("name.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(toPage)) {
				data = getText("toPage.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(type)) {
				data = getText("type.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(isSinglePage)) {
				data = getText("isSinglePage.required");
				return TEXT;
			}
			//保存页面跳转模板实体
			boolean isSuc = pageRedirectTemplateService.persist(name, toPage, type, isSinglePage, versionFlag, directory,toPageAjax);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增页面跳转模板";
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
	 * 修改页面跳转模板
	 * @Title: PageRedirectTemplateAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setPageRedirectTemplate(pageRedirectTemplateService.queryById(PageRedirectTemplate.class.getSimpleName(), id));
			return "editPageRedirectTemplate";
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
			if(GeneralUtil.isNull(toPage)) {
				data = getText("toPage.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(type)) {
				data = getText("type.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(isSinglePage)) {
				data = getText("isSinglePage.required");
				return TEXT;
			}
			//修改页面跳转模板实体
			boolean isSuc = pageRedirectTemplateService.merge(id, name, toPage, type, isSinglePage,  versionFlag, directory,toPageAjax);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改页面跳转模板";
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
	 * 管理页面跳转模板实体
	 * @Title: PageRedirectTemplateAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(pageRedirectTemplateService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "managePageRedirectTemplate";
	}
	
	/**
	 * 查看回收站
	 * @Title: PageRedirectTemplateAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(pageRedirectTemplateService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recyclePageRedirectTemplate";
	}
	
	/**
	 * 逻辑删除页面跳转模板对象
	 * @Title: PageRedirectTemplateAction
	 * @Description: 把页面跳转模板对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = pageRedirectTemplateService.logicDeleteEntity(PageRedirectTemplate.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除页面跳转模板";
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
	 * 物理删除页面跳转模板对象
	 * @Title: PageRedirectTemplateAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = pageRedirectTemplateService.remove(id);
		if(isSuc) {
			String logContent = "物理删除页面跳转模板";
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
	 * 还原一个页面跳转模板对象
	 * @Title: PageRedirectTemplateAction
	 * @Description: 从回收站还原页面跳转模板对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = pageRedirectTemplateService.restoreEntity(PageRedirectTemplate.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原页面跳转模板";
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
	 * 审核页面跳转模板对象
	 * @Title: PageRedirectTemplateAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = pageRedirectTemplateService.auditEntity(PageRedirectTemplate.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核页面跳转模板";
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
	 * @Title: PageRedirectTemplateAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = pageRedirectTemplateService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作页面跳转模板";
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
	
	public PageRedirectTemplate getPageRedirectTemplate() {
		return pageRedirectTemplate;
	}
	public void setPageRedirectTemplate(PageRedirectTemplate pageRedirectTemplate) {
		this.pageRedirectTemplate = pageRedirectTemplate;
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
	public String getToPage() {
		return toPage;
	}
	public void setToPage(String toPage) {
		this.toPage = toPage;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getIsSinglePage() {
		return isSinglePage;
	}
	public void setIsSinglePage(Integer isSinglePage) {
		this.isSinglePage = isSinglePage;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public String getToPageAjax() {
		return toPageAjax;
	}

	public void setToPageAjax(String toPageAjax) {
		this.toPageAjax = toPageAjax;
	}
}
