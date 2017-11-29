package com.ticket.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.Evaluation;
import com.ticket.pojo.EvaluationForbiddenMember;
import com.ticket.pojo.EvaluationProcess;
import com.ticket.pojo.EvaluationTemplate;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.SqlParam.Condition;
import com.ticket.pojo.SqlParamGroup;
import com.ticket.service.IEvaluationTemplateService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.DateUtil;
import com.ticket.util.PaginationContext;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 评价系统的后台请求控制器
 * @author tuyou
 */
public class EvaluationAdminAction extends EvaluationAction {

	private static final long serialVersionUID = 1L;
	public static final String evaluationName = Evaluation.class.getSimpleName();
	public static final String processName = EvaluationProcess.class.getSimpleName();
	
	@Resource
	public IEvaluationTemplateService templateService;
	@Resource private ISystemOperationLogService logService = null;
	protected EvaluationProcess process;
	
	//部门id
	protected String department_id;
	//是否关闭
	protected boolean close;
	//处理意见
	protected String msg;
	//备注
	protected String remark, remark2;
	//涉及制度
	protected String involveSystem;
	//处理结果
	protected String processMsg;
	//运质意见
	protected String shipmentQualityOpinion;
	
	//分页查询的开始、结束时间
	protected Date startTime, endTime;
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: EstimateSetManageAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = evaluationService.batchOperationEntity(versionFlag, idsValue, batchOperationType, true);
		if(isSuc) {
			String logContent = "批量操作评价类别/项目/指标";
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
	 * 管理评价禁止会员
	 * @return
	 * @throws ServiceException 
	 */
	public String forbiddenMember() throws ServiceException{
		
		pageModule = evaluationService.paginationQuery(
				"select s from " + EvaluationForbiddenMember.class.getName() + " s", 
				EvaluationForbiddenMember.class);
		pageSize = PaginationContext.getPagesize();
		return "forbiddenMember";
	}
	
	/**
	 * 解禁会员的评论
	 * @return
	 * @throws ServiceException
	 */
	public String unforbidden() throws ServiceException {
		
		try {
			EvaluationForbiddenMember forbiddenMember = evaluationService.get(EvaluationForbiddenMember.class, id);
			evaluationService.remove(forbiddenMember);
			data = AjaxData.responseSuccess("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			data = AjaxData.responseError("解禁失败");
		}
		return JSON;
	}
	
	/**
	 * 逻辑删除评价对象
	 */
	public String logicDelete() throws ServiceException {
		
		boolean isSuccess = evaluationService.logicDeleteEntity(Evaluation.class.getName(), id);
		if(isSuccess){
			
			data = AjaxData.responseSuccess("删除成功");
		}else{
			
			data = AjaxData.responseError("删除失败");
		}
		return JSON;
	}
	
	/**
	 * 评价管理员列表
	 * @return
	 */
	public String evaluationAdminList() throws ServiceException{
		
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		Pagination page = null;
		
		SqlParamGroup param = new SqlParamGroup("s.status.createTime", Condition.ge, DateUtil.getDayStart(startTime));
		param.and("s.status.createTime", Condition.le, DateUtil.getDayEnd(endTime));
		
		String view = "evaluationAdmin";
		if("all".equals(operationFlag)){
			
			param.and("s.status.deleteFlag", Condition.eq, 0);
			param.and("s.state.name", Condition.eq, "new_state");
			page = evaluationService.paginationQuery("select s from " + evaluationName + " s " + param.toString(true) + " order by s.status.createTime desc", param.getParamArray());
		}else if("feedback".equals(operationFlag)){
			
			param.and("s.status.deleteFlag", Condition.eq, 0);
			param.and("s.state.name", Condition.eq, "pushed_state");
			param.and("s.feedbackTime", Condition.is_not_null);
			page = evaluationService.paginationQuery("select s from " + evaluationName + " s " + param.toString(true) + " order by s.status.createTime desc", param.getParamArray());
			view = "evaluationAdmin-feedback";
		}else if("sended".equals(operationFlag)){
			
			param.and("s.status.deleteFlag", Condition.eq, 0);
			param.and("s.state.name", Condition.eq, "sended_state");
			page = evaluationService.paginationQuery("select s from " + evaluationName + " s " + param.toString(true) + " order by s.status.createTime desc", param.getParamArray());
			view = "evaluationAdmin-sended";
		}else if("hasImages".equals(operationFlag)){
			
			param.and("s.status.deleteFlag", Condition.eq, 0);
			param.and("s.images", Condition.is_not_null);
			param.and(new SqlParamGroup("s.state.name", Condition.eq, "new_state").or("s.state.name", Condition.eq, "sended_state"));
			page = evaluationService.paginationQuery("select s from " + evaluationName + " s " + param.toString(true) + " order by s.status.createTime desc", param.getParamArray());
		}else if("hasContent".equals(operationFlag)){
			
			param.and("s.status.deleteFlag", Condition.eq, 0);
			param.and("s.content", Condition.is_not_null);
			param.and(new SqlParamGroup("s.state.name", Condition.eq, "new_state").or("s.state.name", Condition.eq, "sended_state"));
			page = evaluationService.paginationQuery("select s from " + evaluationName + " s " + param.toString(true) + " order by s.status.createTime desc", param.getParamArray());
		}else if("deleted".equals(operationFlag)){
			
			param.and("s.status.deleteFlag", Condition.eq, 1);
			param.and(new SqlParamGroup("s.state.name", Condition.eq, "new_state").or("s.state.name", Condition.eq, "sended_state"));
			page = evaluationService.paginationQuery("select s from " + evaluationName + " s " + param.toString(true) + " order by s.status.createTime desc", param.getParamArray());
		}
		this.setPageModule(page);
		return view;
	}
	
	/**
	 * 高级管理员列表
	 * @return
	 */
	public String advancedAdminList() throws ServiceException{
		
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		Pagination page = null;
		
		SqlParamGroup param = new SqlParamGroup("t.status.createTime", Condition.ge, DateUtil.getDayStart(startTime));
		param.and("t.status.createTime", Condition.le, DateUtil.getDayEnd(endTime));
		param.and("t.status.deleteFlag", Condition.eq, 0);
		
		String view = "advancedAdmin";
		if("all".equals(operationFlag)){
			
			page = evaluationService.paginationQuery("select t from " + evaluationName + " t " + param.toString(true) + " order by t.status.createTime desc", param.getParamArray());
		}else if("sended".equals(operationFlag)){ //需送审给部门的
			
			param.and("t.state.name", Condition.eq, "sended_state");
			page = evaluationService.paginationQuery("select t from " + evaluationName + " t " + param.toString(true) + " order by t.status.createTime desc", param.getParamArray());
		}else if("untreated".equals(operationFlag)){ //需跟进的
			
			param.and("t.state.name", Condition.eq, "untreated_state");
			page = evaluationService.paginationQuery("select t from " + evaluationName + " t " + param.toString(true) + " order by t.status.createTime desc", param.getParamArray());
			view = "departmentFollowUpList";
		}else if("feedback".equals(operationFlag)){ //部门已回复的
			
			param.and("t.state.name", Condition.eq, "treated_state");
			page = evaluationService.paginationQuery("select t from " + evaluationName + " t " + param.toString(true) + " order by t.status.createTime desc", param.getParamArray());
			view = "departmentFeedbackedList";
		}
		this.setPageModule(page);
		return view;
	}
	
	/**
	 * 发布管理员列表
	 * @return
	 */
	public String publishAdminList() throws ServiceException{
		
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		Pagination page = null;
		
		SqlParamGroup param = new SqlParamGroup("t.status.createTime", Condition.ge, DateUtil.getDayStart(startTime));
		param.and("t.status.createTime", Condition.le, DateUtil.getDayEnd(endTime));
		param.and("t.status.deleteFlag", Condition.eq, 0);
		
		if("unpublished".equals(operationFlag)){
			
			param.and("t.state.name", Condition.eq, "pushed_state");
			page = evaluationService.paginationQuery("select t from " + evaluationName + " t " + param.toString(true) + " order by t.status.createTime desc", param.getParamArray());
		}else if("published".equals(operationFlag)){
			
			param.and("t.state.name", Condition.eq, "published_state");
			page = evaluationService.paginationQuery("select t from " + evaluationName + " t " + param.toString(true) + " order by t.status.createTime desc", param.getParamArray());
		}
		this.setPageModule(page);
		return "publishAdmin";
	}
	
	/**
	 * 部门管理员列表
	 */
	public String departmentAdminList(){
		
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		
		SqlParamGroup param = new SqlParamGroup("t.status.createTime", Condition.ge, DateUtil.getDayStart(startTime));
		param.and("t.status.createTime", Condition.le, DateUtil.getDayEnd(endTime));
		param.and("t.status.deleteFlag", Condition.eq, 0);

		param.and("t.state.name", Condition.eq, "untreated_state");
		Pagination page = evaluationService.paginationQuery("select t from " + evaluationName + " t " + param.toString(true) + " order by t.top, t.status.createTime desc", param.getParamArray());
		this.setPageModule(page);
		return "departmentAdmin";
	}
	
	/**
	 * 送审操作
	 * @return
	 * @throws ServiceException 
	 */
	public String send() throws ServiceException{
		
		try {
			
			evaluationService.send(idsValue);
		} catch (ServiceException e) {
			
			data = AjaxData.responseError(e.getMessage());
			return JSON;
		}
		String logContent = "评价送审";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		data = AjaxData.responseSuccess(getText("addSuccess"));
		return JSON;
	}
	
	/**
	 * 回复页面
	 * @return
	 */
	public String feedbackPage(){
		
		List<EvaluationTemplate> templates = evaluationService.queryAll(EvaluationTemplate.class);
		ActionContext.getContext().put("templates", templates);
		return "feedbackPage";
	}
	
	/**
	 * 回复操作
	 * @return
	 * @throws ServiceException 
	 */
	public String feedback() throws ServiceException{
		
		try {
			
			evaluationService.feedback(idsValue, content);
		} catch (ServiceException e) {
			
			data = AjaxData.responseError(e.getMessage());
			return JSON;
		}
		String logContent = "回复评价";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		data = AjaxData.responseSuccess(getText("addSuccess"));
		return JSON;
	}
	
	/**
	 * 屏蔽操作
	 * @return
	 * @throws ServiceException 
	 */
	public String shield() throws ServiceException{
		
		try {
			
			evaluationService.shield(idsValue);
		} catch (ServiceException e) {
			
			data = AjaxData.responseSuccess(e.getMessage());
			return JSON;
		}
		String logContent = "屏蔽用户评价";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		data = AjaxData.responseSuccess(getText("addSuccess"));
		return JSON;
	}
	
	/**
	 * 发送到指定部门的页面
	 * @return
	 * @throws ServiceException 
	 */
	public String sendToDepartmentPage() throws ServiceException{
		
		evaluation = evaluationService.get(Evaluation.class, idsValue);
		
		return "sendToDepartmentPage";
	}
	
	/**
	 * 发送给部门的操作（高级管理员）
	 * @return
	 * @throws ServiceException 
	 */
	public String sendToDepartment() throws ServiceException{
		
		try {
			evaluationService.sendToDepartment(idsValue, department_id, involveSystem, shipmentQualityOpinion, msg, remark);
		} catch (Exception e) {
			data = AjaxData.responseError(e.getMessage());
			return JSON;
		}
		String logContent = "发送评价给部门（高级管理员）";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		data = AjaxData.responseSuccess(getText("addSuccess"));
		return JSON;
	}
	
	/**
	 * 部门意见处理页面
	 * @return
	 */
	public String departmentProcessPage(){
		
		process = evaluationService.get(EvaluationProcess.class, idsValue);
		return "departmentProcessPage";
	}
	
	/**
	 * 部门意见处理
	 * @return
	 * @throws ServiceException 
	 */
	public String departmentProcess() throws ServiceException{
		
		try {
			
			evaluationService.departmentProcess(idsValue, close, shipmentQualityOpinion, msg, remark);
		} catch (Exception e) {
			
			data = AjaxData.responseError(e.getMessage());
			return JSON;
		}
		String logContent = "部门处理评价意见";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		data = AjaxData.responseSuccess(getText("addSuccess"));
		return JSON;
	}
	
	/**
	 * 发回操作（高级管理员）
	 * @return
	 * @throws ServiceException 
	 */
	public String sendBack() throws ServiceException{
		
		try {
			
			evaluationService.sendBack(idsValue);
		} catch (Exception e) {
			
			data = AjaxData.responseError(e.getMessage());
			return JSON;
		}
		String logContent = "发送评价操作";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		data = AjaxData.responseSuccess(getText("addSuccess"));
		return JSON;
	}
	
	/**
	 * 发布显示操作（发布管理员）
	 * @return
	 * @throws ServiceException 
	 */
	public String publish() throws ServiceException{
		
		try {
			
			evaluationService.publish(idsValue);
		} catch (ServiceException e) {
			
			data = AjaxData.responseError(e.getMessage());
			return JSON;
		}
		String logContent = "发布评价显示操作";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		data = AjaxData.responseSuccess(getText("addSuccess"));
		return JSON;
	}
	
	/**
	 * 取消发布显示操作（发布管理员）
	 * @return
	 * @throws ServiceException 
	 */
	public String unpublish() throws ServiceException{
		
		try {
			
			evaluationService.unpublish(idsValue);
		} catch (ServiceException e) {
			
			data = AjaxData.responseSuccess(e.getMessage());
			return JSON;
		}
		String logContent = "取消发布评价显示操作";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		data = AjaxData.responseSuccess(getText("addSuccess"));
		return JSON;
	}
	
	/**
	 * 跳转到回复部门的页面
	 * @return
	 */
	public String feedbackDepartmentPage(){
		
		return "feedbackDepartmentPage";
	}
	
	/**
	 * 追发部门动作
	 * @return
	 * @throws ServiceException 
	 */
	public String feedbackDepartment() throws ServiceException{
		
		try {
			evaluationService.feedbackDepartment(idsValue);
		} catch (ServiceException e) {
			
			data = AjaxData.responseError(e.getMessage());
			return JSON;
		}
		String logContent = "追发评价部门动作";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		data = AjaxData.responseSuccess(getText("addSuccess"));
		return JSON;
	}
	
	/**
	 * 置顶操作
	 * @return
	 * @throws ServiceException 
	 */
	public String topOrNot() throws ServiceException{
		
		try {
			evaluationService.topOrNot(idsValue);
		} catch (ServiceException e) {
			
			data = AjaxData.responseError(e.getMessage());
			return JSON;
		}
		String logContent = "评价置顶操作";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		data = AjaxData.responseSuccess(getText("addSuccess"));
		return JSON;
	}
	
	public String getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getInvolveSystem() {
		return involveSystem;
	}

	public void setInvolveSystem(String involveSystem) {
		this.involveSystem = involveSystem;
	}

	public EvaluationProcess getProcess() {
		return process;
	}

	public void setProcess(EvaluationProcess process) {
		this.process = process;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getProcessMsg() {
		return processMsg;
	}

	public void setProcessMsg(String processMsg) {
		this.processMsg = processMsg;
	}

	public String getShipmentQualityOpinion() {
		return shipmentQualityOpinion;
	}

	public void setShipmentQualityOpinion(String shipmentQualityOpinion) {
		this.shipmentQualityOpinion = shipmentQualityOpinion;
	}

	public boolean isClose() {
		return close;
	}

	public void setClose(boolean close) {
		this.close = close;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}