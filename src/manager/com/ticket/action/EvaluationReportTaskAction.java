package com.ticket.action;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.enumer.EvaluationReportProperty;
import com.ticket.enumer.EvaluationReportType;
import com.ticket.enumer.EvaluationReportUpdateFrequency;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.EvaluationReportTask;
import com.ticket.service.IEvaluationReportTaskService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 评论报表任务控制器
 * @ClassName: EvaluationReportTaskAction   
 * @Description:  提供评论报表任务的相关操作方法. 
 * @author HiSay  
 * @date 2016-02-04 21:40:28
 */
public class EvaluationReportTaskAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//评论报表任务的业务层
	@Resource 
	private IEvaluationReportTaskService evaluationReportTaskService = null;
	@Resource private ISystemOperationLogService logService = null;
	//评论报表任务实体
	private EvaluationReportTask evaluationReportTask = null;
	//主键
	private String id = null;
    //报表文件名
	private String name = null;
    //报表性质
	private EvaluationReportProperty property = null;
    //报表类型
	private EvaluationReportType type = null;
    //更新频率
	private EvaluationReportUpdateFrequency frequency = null;
    //邮箱
	private String email = null;
	//时间段
	protected Date[] startDate, endDate;
	//是否启动
	protected boolean launch;
	
	/**
	 * 生成报表
	 * @return
	 * @throws IOException 
	 */
	public String generate(){
		
		try {
			response.setCharacterEncoding("utf-8");
			EvaluationReportTask task = evaluationReportTaskService.get(EvaluationReportTask.class, idsValue);
			String fileName = URLEncoder.encode(task.getName() + ".xlsx", "utf-8"); //防止中文乱码
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			evaluationReportTaskService.generate(task, response.getOutputStream());
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 添加评论报表任务
	 * @Title: EvaluationReportTaskAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			
			ActionContext.getContext().put("type", EvaluationReportType.values());
			ActionContext.getContext().put("frequency", EvaluationReportUpdateFrequency.values());
			ActionContext.getContext().put("property", EvaluationReportProperty.values());
			return "addEvaluationReportTask";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError(getText("name.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(type)) {
				data = AjaxData.responseError(getText("type.required"));
				return JSON;
			}
//			if(GeneralUtil.isNull(frequency)) {
//				data = AjaxData.responseError(getText("frequency.required"));
//				return JSON;
//			}
//			if(GeneralUtil.isNull(launch)) {
//				data = AjaxData.responseError(getText("launch.required"));
//				return JSON;
//			}
			//保存评论报表任务实体
			boolean isSuc = evaluationReportTaskService.persist(name, property, type, frequency, email, startDate, endDate, launch);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增评价报表任务";
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
	 * 修改评论报表任务
	 * @Title: EvaluationReportTaskAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setEvaluationReportTask(evaluationReportTaskService.queryById(EvaluationReportTask.class.getSimpleName(), id));
			return "editEvaluationReportTask";
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
			if(GeneralUtil.isNull(type)) {
				data = AjaxData.responseError(getText("type.required"));
				return JSON;
			}
//			if(GeneralUtil.isNull(frequency)) {
//				data = AjaxData.responseError(getText("frequency.required"));
//				return JSON;
//			}
//			if(GeneralUtil.isNull(email)) {
//				data = AjaxData.responseError(getText("email.required"));
//				return JSON;
//			}
//			if(GeneralUtil.isNull(launch)) {
//				data = AjaxData.responseError(getText("launch.required"));
//				return JSON;
//			}
			//修改评论报表任务实体
			boolean isSuc = evaluationReportTaskService.merge(id, name, property, type, frequency, email, startDate, endDate, launch);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改评价报表任务";
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
	 * 管理评论报表任务实体
	 * @Title: EvaluationReportTaskAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(evaluationReportTaskService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageEvaluationReportTask";
	}
	
	/**
	 * 查看回收站
	 * @Title: EvaluationReportTaskAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(evaluationReportTaskService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleEvaluationReportTask";
	}
	
	/**
	 * 逻辑删除评论报表任务对象
	 * @Title: EvaluationReportTaskAction
	 * @Description: 把评论报表任务对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = evaluationReportTaskService.logicDeleteEntity(EvaluationReportTask.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除评价报表任务";
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
	 * 物理删除评论报表任务对象
	 * @Title: EvaluationReportTaskAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = evaluationReportTaskService.remove(id);
		if(isSuc) {
			String logContent = "物理删除评价报表任务";
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
	 * 还原一个评论报表任务对象
	 * @Title: EvaluationReportTaskAction
	 * @Description: 从回收站还原评论报表任务对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = evaluationReportTaskService.restoreEntity(EvaluationReportTask.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原评价报表任务";
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
	 * 审核评论报表任务对象
	 * @Title: EvaluationReportTaskAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = evaluationReportTaskService.auditEntity(EvaluationReportTask.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核评价报表任务";
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
	 * @Title: EvaluationReportTaskAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = evaluationReportTaskService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作评价报表任务";
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
	
	public EvaluationReportTask getEvaluationReportTask() {
		return evaluationReportTask;
	}
	public void setEvaluationReportTask(EvaluationReportTask evaluationReportTask) {
		this.evaluationReportTask = evaluationReportTask;
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
	public EvaluationReportProperty getProperty() {
		return property;
	}
	public void setProperty(EvaluationReportProperty property) {
		this.property = property;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public IEvaluationReportTaskService getEvaluationReportTaskService() {
		return evaluationReportTaskService;
	}
	public void setEvaluationReportTaskService(
			IEvaluationReportTaskService evaluationReportTaskService) {
		this.evaluationReportTaskService = evaluationReportTaskService;
	}
	public boolean isLaunch() {
		return launch;
	}
	public void setLaunch(boolean launch) {
		this.launch = launch;
	}

	public EvaluationReportType getType() {
		return type;
	}

	public void setType(EvaluationReportType type) {
		this.type = type;
	}

	public EvaluationReportUpdateFrequency getFrequency() {
		return frequency;
	}

	public void setFrequency(EvaluationReportUpdateFrequency frequency) {
		this.frequency = frequency;
	}
}
