package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.AirportEmployee;
import com.ticket.pojo.EvaluationTemplate;
import com.ticket.service.IEvaluationTemplateService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 评论模板控制器
 * @ClassName: EvaluationTemplateAction   
 * @Description:  提供评论模板的相关操作方法. 
 * @author HiSay  
 * @date 2016-02-03 18:17:36
 *
 */
public class EvaluationTemplateAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//评论模板的业务层
	@Resource private IEvaluationTemplateService evaluationTemplateService = null;
	
	@Resource private ISystemOperationLogService logService = null;
	//评论模板实体
	private EvaluationTemplate evaluationTemplate = null;
	//主键
	private String id = null;
    //标题
	private String title = null;
    //内容
	private String content = null;
    //此条模板所属用户
	private AirportEmployee user = null;
	
	/**
	 * 添加评论模板
	 * @Title: EvaluationTemplateAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addEvaluationTemplate";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(content)) {
				data = AjaxData.responseError(getText("content.required"));
				return JSON;
			}
			
			//保存评论模板实体
			try {
				evaluationTemplateService.persist(title, content);
			} catch (Exception e) {
				data = AjaxData.responseError(e.getMessage());
				return JSON;
			}
			String logContent = "新增评论回复模板";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("addFailed"));
			return JSON;
		}
	}
	
	/**
	 * 修改评论模板
	 * @Title: EvaluationTemplateAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setEvaluationTemplate(evaluationTemplateService.queryById(EvaluationTemplate.class.getSimpleName(), id));
			return "editEvaluationTemplate";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(content)) {
				data = AjaxData.responseError(getText("content.required"));
				return JSON;
			}
			//修改评论模板实体
			try {
				evaluationTemplateService.merge(id, title, content);
			} catch (Exception e) {
				data = AjaxData.responseError(e.getMessage());
				return JSON;
			}
			String logContent = "修改评论回复模板";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("editSuccess"));
			return JSON;
		}
	}
	
	/**
	 * 管理评论模板实体
	 * @Title: EvaluationTemplateAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(evaluationTemplateService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageEvaluationTemplate";
	}
	
	/**
	 * 查看回收站
	 * @Title: EvaluationTemplateAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(evaluationTemplateService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleEvaluationTemplate";
	}
	
	/**
	 * 逻辑删除评论模板对象
	 * @Title: EvaluationTemplateAction
	 * @Description: 把评论模板对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = evaluationTemplateService.logicDeleteEntity(EvaluationTemplate.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除评论回复模板";
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
	 * 物理删除评论模板对象
	 * @Title: EvaluationTemplateAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = evaluationTemplateService.remove(id);
		if(isSuc) {
			String logContent = "物理删除评论回复模板";
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
	 * 还原一个评论模板对象
	 * @Title: EvaluationTemplateAction
	 * @Description: 从回收站还原评论模板对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = evaluationTemplateService.restoreEntity(EvaluationTemplate.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原评论回复模板";
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
	 * 审核评论模板对象
	 * @Title: EvaluationTemplateAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = evaluationTemplateService.auditEntity(EvaluationTemplate.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核评论回复模板";
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
	 * @Title: EvaluationTemplateAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = evaluationTemplateService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作评论回复模板";
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
	
	public EvaluationTemplate getEvaluationTemplate() {
		return evaluationTemplate;
	}
	public void setEvaluationTemplate(EvaluationTemplate evaluationTemplate) {
		this.evaluationTemplate = evaluationTemplate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public AirportEmployee getUser() {
		return user;
	}
	public void setUser(AirportEmployee user) {
		this.user = user;
	}
}
