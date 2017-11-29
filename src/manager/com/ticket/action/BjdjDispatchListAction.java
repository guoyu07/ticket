package com.ticket.action;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.enumer.BjdjAppointmentType;
import com.ticket.enumer.BjdjCheckWay;
import com.ticket.enumer.BjdjDispatchListState;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.AirportEmployee;
import com.ticket.pojo.BjdjDispatch;
import com.ticket.pojo.BjdjDispatchList;
import com.ticket.pojo.BjdjHall;
import com.ticket.pojo.SqlParam.Condition;
import com.ticket.pojo.SqlParamGroup;
import com.ticket.pojo.SystemUser;
import com.ticket.service.IBjdjDispatchListService;
import com.ticket.service.IBjdjDispatchService;
import com.ticket.service.IBjdjServiceCodeService;
import com.ticket.service.IBjdjServiceItemService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 分单流程表控制器
 * @ClassName: BjdjDispatchListAction   
 * @Description:  提供分单流程表的相关操作方法. 
 * @author HiSay  
 * @date 2015-11-23 22:55:31
 */
public class BjdjDispatchListAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//分单流程表的业务层
	@Resource 
	private IBjdjDispatchService dispatchService;
	@Resource 
	private IBjdjDispatchListService dispatchListService;
	@Resource
	private IBjdjServiceCodeService codeService;
	@Resource
	private IBjdjServiceItemService serviceItemService;
	@Resource 
	private ISystemOperationLogService logService;
	
	//分单流程表实体
	private BjdjDispatchList dispatchList;
	
    //分单ID
	private String dispatch_id;
	//分单项ID
	private String dispatchList_id;
	//服务员工ID
	private String employee_id;
    //顺序
	private Integer sequence;
    //问题反馈
	private String feedback;
	
	private String keyword;
	//查询条件的开始、结束日期
	private Date startTime, endTime;
	private Date startTime2, endTime2;
	private BjdjAppointmentType type;
	private BjdjDispatchListState state;
	private String packageType;
	private String flightNo;
	private String code;
	private String name;
	private String IDCard;
	private String employeeName;
	private String dispatchListName;
	private String mobile;
	
	/**
	 * 下载报表
	 * @return
	 * @throws ServiceException
	 */
	public String downReport() {
		
		SystemUser systemUser = (SystemUser) getSessionAdminUser();
		SqlParamGroup group = new SqlParamGroup("s.status.deleteFlag", Condition.eq, ContextConstants.STATUS_OF_ZERO).and("s.status.versionFlag", Condition.eq, versionFlag);
		if(systemUser instanceof AirportEmployee){//登录的是机场员工
			
			AirportEmployee airportEmployee = (AirportEmployee)systemUser;
			BjdjHall bjdjHall = airportEmployee.getHall();
			group.and("s.validation.appointment.hall.id", Condition.ge, bjdjHall.getId());
		}
		group.and("s.validation.appointment.flightNo", Condition.like_left, flightNo);
		group.and("s.validation.appointment.useTime", Condition.ge, startTime2);
		group.and("s.validation.appointment.useTime", Condition.le, endTime2);
		group.and("s.validation.time", Condition.ge, startTime);
		group.and("s.validation.time", Condition.le, endTime);
		group.and("s.validation.appointment.serviceCode.code", Condition.like_left, code);
		group.and("s.validation.appointment.idCard", Condition.like_left, IDCard);
		group.and("s.validation.appointment.name", Condition.like_left, name);
		group.and("s.validation.appointment.serviceCode.packageType.name", Condition.eq, packageType);
		group.and("s.validation.appointment.way", Condition.eq, type);
		List<BjdjDispatch> list = dispatchListService.getDbDAO().executeJPQLForQuery("select s from " + BjdjDispatch.class.getName() + " s " + group.toString(true) + " order by s.status.createTime desc", group.getParamArray());
		
		List<BjdjDispatch> newList = new LinkedList<BjdjDispatch>();
		for(int i = 0 ; i < list.size(); i++){
			
			BjdjDispatch dispatch = list.get(i);
			group = new SqlParamGroup("l.dispatch", Condition.eq, dispatch).and("l.state", Condition.eq, state).and("l.employee.name", Condition.like_left, employeeName).and("l.info.name", Condition.like_left, dispatchListName);
			dispatch.setDispatchList(dispatchListService.getDbDAO().executeJPQLForQuery("select l from " + BjdjDispatchList.class.getName() + " l " + group.toString(true) + " order by l.sequence", group.getParamArray()));
			if(dispatch.getDispatchList().size() > 0){
				
				newList.add(dispatch);
			}
		}
		exportExcel("/WEB-INF/excel/jasper/bjdjDispatchList.jasper", newList, "便捷登机核销情况", "/WEB-INF/excel/jasper/");
		return null;
	}
	
	/**
	 * @Description：生成一个分单的控制器
	 * @return
	 * @throws ServiceException
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addBjdjDispatchList";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(dispatch_id)) {
				data = AjaxData.responseError(getText("dispatch_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(employee_id)) {
				data = AjaxData.responseError(getText("employee_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(sequence)) {
				data = AjaxData.responseError(getText("order.required"));
				return JSON;
			}
			//保存分单流程表实体
			BjdjDispatchList item = dispatchListService.persist(dispatch_id, employee_id, null, sequence);
			//根据保存结果返回页面
			if(item != null) {
				String logContent = "新增便捷登机分单流程";
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
	 * @Description：接单的请求控制器
	 * @return
	 * @throws ServiceException
	 */
	public String ing() throws ServiceException {
		
		//非空验证.
		if(GeneralUtil.isNull(idsValue)) {
			data = AjaxData.responseError(getText("id.required"));
			return JSON;
		}
		String[] ids = idsValue.split(",");
		for(String id : ids){
			//判断如果有空值，则跳过
			if(id.equals("")){
				continue;
			}
			//修改分单流程表实体
			try {
				dispatchListService.acceptDispatchList(id.trim());
			} catch (ServiceException e) {
				e.printStackTrace();
				data = AjaxData.responseError(e.getMessage());
				return JSON;
			}
		}
		String logContent = "修改便捷登机分单流程";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		data = AjaxData.responseSuccess(getText("dispatch.receive.success"));
		return JSON;
	}
	
	/**
	 * @Description：核销的请求控制器
	 * @return
	 * @throws ServiceException
	 */
	public String end() throws ServiceException {
		
		//非空验证.
		if(GeneralUtil.isNull(idsValue)) {
			data = AjaxData.responseError(getText("id.required"));
			return JSON;
		}
//		if(GeneralUtil.isNull(feedback)) {
//			data = AjaxData.responseError(getText("dispatch.verificate.feedback.required"));
//			return JSON;
//		}
		
		String[] ids = idsValue.split(",");
		for(String id : ids){
			//判断如果有空值，则跳过
			if(id.equals("")){
				continue;
			}
			//修改分单流程表实体
			try {
				dispatchListService.verification(id.trim(), feedback, BjdjCheckWay.artificial);
			} catch (Exception e) {
//				e.printStackTrace();
				data = AjaxData.responseError(e.getMessage());
				return JSON;
			}
		}
		String logContent = "核销便捷登机分单流程";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		data = AjaxData.responseSuccess(getText("dispatch.verificate.success"));
		return JSON;
	}
	
	/**
	 * 管理分单流程表实体
	 * @Title: BjdjDispatchListAction
	 * @return
	 * @throws ServiceException    
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		SystemUser systemUser = (SystemUser) getSessionAdminUser();
		SqlParamGroup group = new SqlParamGroup("s.status.deleteFlag", Condition.eq, ContextConstants.STATUS_OF_ZERO).and("s.status.versionFlag", Condition.eq, versionFlag);
		if(systemUser instanceof AirportEmployee){//登录的是机场员工
			
			AirportEmployee airportEmployee = (AirportEmployee)systemUser;
			BjdjHall bjdjHall = airportEmployee.getHall();
			group.and("s.validation.appointment.hall.id", Condition.ge, bjdjHall.getId());
		}
		group.and("s.validation.appointment.flightNo", Condition.like_left, flightNo);
		group.and("s.validation.appointment.useTime", Condition.ge, startTime2);
		group.and("s.validation.appointment.useTime", Condition.le, endTime2);
		group.and("s.validation.time", Condition.ge, startTime);
		group.and("s.validation.time", Condition.le, endTime);
		group.and("s.validation.appointment.serviceCode.code", Condition.like_left, code);
		group.and("s.validation.appointment.idCard", Condition.like_left, IDCard);
		group.and("s.validation.appointment.name", Condition.like_left, name);
		group.and("s.validation.appointment.serviceCode.packageType.name", Condition.eq, packageType);
		group.and("s.validation.appointment.way", Condition.eq, type);
		this.setPageModule(dispatchListService.paginationQuery("select s from " + BjdjDispatch.class.getName() + " s " + group.toString(true) + " order by s.status.createTime desc", group.getParamArray()));
		
		for(int i = 0 ; i < pageModule.getPageList().size(); i++){
			
			BjdjDispatch dispatch = (BjdjDispatch)pageModule.getPageList().get(i);
			group = new SqlParamGroup("l.dispatch", Condition.eq, dispatch).and("l.state", Condition.eq, state).and("l.employee.name", Condition.like_left, employeeName).and("l.info.name", Condition.like_left, dispatchListName);
			dispatch.setDispatchList(dispatchListService.getDbDAO().executeJPQLForQuery("select l from " + BjdjDispatchList.class.getName() + " l " + group.toString(true) + " order by l.sequence", group.getParamArray()));
		}
		
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageBjdjDispatchList";
	}
	
	/**
	 * 管理分单流程表实体
	 * @Title: BjdjDispatchListAction
	 * @return
	 * @throws ServiceException    
	 */
//	public String manageElectromobile() throws ServiceException {
//		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
//		if(GeneralUtil.isNotNull(keyword)){
//			BjdjServiceCode code = codeService.getByCode(keyword);
//			BjdjDispatch dispatch = code.getValidation().getDispatch();
//			List<BjdjDispatchList> dispatchLists = dispatch.getDispatchList();
//			Pagination pagination = new Pagination();
//			pagination.setPageList(dispatchLists);
//			pagination.setTotalCount(dispatchLists.size());
//			this.setPageModule(pagination);
//		}else{
//			this.setPageModule(dispatchListService.queryEntity(
//					" and s.dispatch.validation.type=?3", " dispatch, sequence", BjdjOrderType.electromobile));
//		}
//		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
//		return "manageElectromobileDispatchList";
//	}

	/**
	 * 服务人员工作汇总统计
	 * @return
	 */
	public String serviceGather(){
		
		List<Object[]> list = dispatchListService.serviceGather(startTime, endTime);
		ActionContext.getContext().put("list", list);
		return "serviceGather";
	}
	
	/**
	 * 服务人员工作明细
	 * @return
	 */
	public String serviceDetail(){
		
		List<Object[]> list = dispatchListService.serviceDetail(startTime, endTime);
		ActionContext.getContext().put("list", list);
		return "serviceDetail";
	}
	
	/**
	 * 查看回收站
	 * @Title: BjdjDispatchListAction
	 * @return
	 * @throws ServiceException   
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(dispatchListService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleBjdjDispatchList";
	}
	
	/**
	 * 逻辑删除分单流程表对象
	 * @Title: BjdjDispatchListAction
	 * @Description: 把分单流程表对象放入回收站.   
	 * @return
	 * @throws ServiceException    
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = dispatchListService.logicDeleteEntity(BjdjDispatchList.class.getSimpleName(), dispatchList_id);
		if(isSuc) {
			String logContent = "逻辑删除便捷登机分单流程";
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
	 * 物理删除分单流程表对象
	 * @Title: BjdjDispatchListAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @return
	 * @throws ServiceException   
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = dispatchListService.remove(dispatchList_id);
		if(isSuc) {
			String logContent = "物理删除便捷登机分单流程";
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
	 * 还原一个分单流程表对象
	 * @Title: BjdjDispatchListAction
	 * @Description: 从回收站还原分单流程表对象   
	 * @return
	 * @throws ServiceException    
	 */
	public String restore() throws ServiceException {
		boolean isSuc = dispatchListService.restoreEntity(BjdjDispatchList.class.getSimpleName(), dispatchList_id);
		if(isSuc) {
			String logContent = "还原便捷登机分单流程";
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
	 * 审核分单流程表对象
	 * @Title: BjdjDispatchListAction
	 * @Description:  
	 * @throws ServiceException    设定文件   
	 * @return     返回类型   
	 */
	public String audit() throws ServiceException {
		boolean isSuc = dispatchListService.auditEntity(BjdjDispatchList.class.getSimpleName(), dispatchList_id, statusValue);
		if(isSuc) {
			String logContent = "审核便捷登机分单流程";
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
	 * @Title: BjdjDispatchListAction
	 * @Description:    
	 * @return
	 * @throws ServiceException 
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = dispatchListService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作便捷登机分单流程";
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
	 * 批量删除便捷登机分单流程
	 */
	public String batchRealDelete() throws ServiceException {
		boolean isSuc = baseService.batchRealDelete(BjdjDispatchList.class, idsValue);
		if(isSuc) {
			String logContent = "批量删除便捷登机分单流程";
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
	
	public BjdjDispatchList getBjdjDispatchList() {
		return dispatchList;
	}
	public void setBjdjDispatchList(BjdjDispatchList bjdjDispatchList) {
		this.dispatchList = bjdjDispatchList;
	}
	public String getDispatch_id() {
		return dispatch_id;
	}
	public void setDispatch_id(String dispatch_id) {
		this.dispatch_id = dispatch_id;
	}
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public Integer getOrder() {
		return sequence;
	}
	public void setOrder(Integer order) {
		this.sequence = order;
	}
    public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public String getDispatchList_id() {
		return dispatchList_id;
	}
	public void setDispatchList_id(String dispatchList_id) {
		this.dispatchList_id = dispatchList_id;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public IBjdjDispatchListService getDispatchListService() {
		return dispatchListService;
	}

	public void setDispatchListService(IBjdjDispatchListService dispatchListService) {
		this.dispatchListService = dispatchListService;
	}

	public BjdjDispatchList getDispatchList() {
		return dispatchList;
	}

	public void setDispatchList(BjdjDispatchList dispatchList) {
		this.dispatchList = dispatchList;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public IBjdjServiceCodeService getCodeService() {
		return codeService;
	}

	public void setCodeService(IBjdjServiceCodeService codeService) {
		this.codeService = codeService;
	}

	public IBjdjDispatchService getDispatchService() {
		return dispatchService;
	}

	public void setDispatchService(IBjdjDispatchService dispatchService) {
		this.dispatchService = dispatchService;
	}

	public Date getStartDate() {
		return startTime;
	}

	public void setStartDate(Date startDate) {
		this.startTime = startDate;
	}

	public Date getEndDate() {
		return endTime;
	}

	public void setEndDate(Date endDate) {
		this.endTime = endDate;
	}

	public ISystemOperationLogService getLogService() {
		return logService;
	}

	public void setLogService(ISystemOperationLogService logService) {
		this.logService = logService;
	}

	public BjdjAppointmentType getType() {
		return type;
	}

	public void setType(BjdjAppointmentType type) {
		this.type = type;
	}

	public BjdjDispatchListState getState() {
		return state;
	}

	public void setState(BjdjDispatchListState state) {
		this.state = state;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIDCard() {
		return IDCard;
	}

	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDispatchListName() {
		return dispatchListName;
	}

	public void setDispatchListName(String dispatchListName) {
		this.dispatchListName = dispatchListName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public Date getStartTime2() {
		return startTime2;
	}

	public void setStartTime2(Date startTime2) {
		this.startTime2 = startTime2;
	}

	public Date getEndTime2() {
		return endTime2;
	}

	public void setEndTime2(Date endTime2) {
		this.endTime2 = endTime2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IBjdjServiceItemService getServiceItemService() {
		return serviceItemService;
	}

	public void setServiceItemService(IBjdjServiceItemService serviceItemService) {
		this.serviceItemService = serviceItemService;
	}
}
