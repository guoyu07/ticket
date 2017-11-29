package com.ticket.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.enumer.ReportRegion;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.AirportEmployee;
import com.ticket.pojo.BjdjAppointment;
import com.ticket.pojo.BjdjHall;
import com.ticket.pojo.BjdjValidation;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.SystemUser;
import com.ticket.service.IBjdjAppointmentService;
import com.ticket.service.IBjdjDispatchService;
import com.ticket.service.IBjdjHallService;
import com.ticket.service.IBjdjOrderService;
import com.ticket.service.IBjdjServiceCodeService;
import com.ticket.service.IBjdjValidationService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;


/**
 * 便捷登机验证表控制器
 * @ClassName: BjdjValidationAction   
 * @Description:  提供便捷登机验证表的相关操作方法. 
 * @author HiSay  
 * @date 2015-11-23 22:52:42
 *
 */
public class BjdjValidationAction extends BaseAction {

	/**   
	 * @Fields serialVersionUID :    
	 */ 
	private static final long serialVersionUID = 1L;
	
	//便捷登机验证表的业务层
	@Resource 
	protected IBjdjValidationService validationService;
	@Resource
	protected IBjdjAppointmentService appointmentService;
	@Resource
	protected ISystemDictionaryService dictionaryService;
	@Resource
	protected IBjdjServiceCodeService serviceCodeService;
	@Resource
	protected IBjdjHallService hallService;
	@Resource
	protected IBjdjOrderService orderService;
	@Resource
	protected IBjdjDispatchService dispatchService;
	@Resource 
	private ISystemOperationLogService logService = null;
	
	//便捷登机验证表实体
	protected BjdjValidation bjdjValidation = null;
	//主键
	protected String id = null;
    //验证时间
	protected Date time = null;
    //机场员工ID
	protected String employee_id = null;
    //是否验证通过
	protected boolean passed;
	//服务码
	protected String serviceCode;
	//大厅id
	protected String hall;
	//大厅list
	protected List<BjdjHall> hallList;
	//航班号
	protected String flightNumber;
	//航班日期
	protected Date flightDate;
	
	protected Date startTime;
	
	protected Date endTime;
	
	protected String keyword;
	
	protected ReportRegion region;

	/**
	 * 汇总统计
	 * @return
	 */
	public String together(){
		
		if(GeneralUtil.isNull(operationFlag) || versionFlag.equals(operationFlag)){
			
			return "togetherReport";
		}else if("html".equals(operationFlag)){
			
			exportHTML("/WEB-INF/excel/jasper/bjdjValidationTogether.jasper", validationService.reportTogether(region), "便捷登机验证统计汇总表");
		}else if("excel".equals(operationFlag)){
			
			exportExcel("/WEB-INF/excel/jasper/bjdjValidationTogether.jasper", validationService.reportTogether(region), "便捷登机验证统计汇总表");
		}
		return null;
	}
	
	/**
	 * 详细报表
	 * @return
	 */
	public String details(){
		
		if(GeneralUtil.isNull(operationFlag) || versionFlag.equals(operationFlag)){
			
			return "detailsReport";
		}else if("html".equals(operationFlag)){
			
			exportHTML("/WEB-INF/excel/jasper/bjdjValidationDetail.jasper", validationService.reportDetails(region), "便捷登机验证统计详细表");
		}else if("excel".equals(operationFlag)){
			
			exportExcel("/WEB-INF/excel/jasper/bjdjValidationDetail.jasper", validationService.reportDetails(region), "便捷登机验证统计详细表");
		}
		return null;
	}
	
	/**
	 * 添加便捷登机验证表
	 * @Title: BjdjValidationAction
	 * @Description:   
	 * @return
	 * @throws ServiceException   
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			
//			hallList = hallService.queryAll(BjdjHall.class);
			return "addBjdjValidation";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(serviceCode)) {
				data = AjaxData.responseError(getText("serviceCode.required"));
				return JSON;
			}
//			if(GeneralUtil.isNull(hall)) {
//				data = AjaxData.responseError(getText("hall.id.requird"));
//				return JSON;
//			}
			
			//检查用户是否为机场员工用户
			if(getSessionAdminUser() instanceof AirportEmployee == false){
				
				data = AjaxData.responseError(getText("airportEmployee.must"));
				return JSON;
			}
			AirportEmployee employee = (AirportEmployee)getSessionAdminUser();
			
			//通过服务码得到预约对象
			BjdjAppointment appointment;
			try {
				appointment = appointmentService.getByServiceCode(serviceCode);
			} catch (ServiceException e) {
				data = AjaxData.responseError(e.getMessage());
				return JSON;
			}
			if(appointment == null){
				
				data = AjaxData.responseError(getText("serviceCode.noAppointment"));
				return JSON;
			}
			
			//保存便捷登机验证表实体
			try {
				validationService.persist(appointment, hall, new Date(), employee.getId(), true);
			} catch (ServiceException e) {
				data = AjaxData.responseError(e.getMessage());
				return JSON;
			}
			String logContent = "新增便捷登机验证";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			//根据保存结果返回页面
			data = AjaxData.responseSuccess(getText("addSuccess"));
			return JSON;
		}
	}
	
	/**
	 * @Description：添加电瓶车验证表
	 * @return
	 * @throws ServiceException 
	 */
//	public String addElectromobile() throws ServiceException {
//		if(GeneralUtil.isNull(operationFlag)) {
//			
//			return "addElectromobileValidation";
//		} else {
//			//非空验证.
//			if(GeneralUtil.isNull(serviceCode)) {
//				data = AjaxData.responseError(getText("serviceCode.required"));
//				return JSON;
//			}
//			//检查用户是否为机场员工用户
//			if(getSessionAdminUser() instanceof AirportEmployee == false){
//				
//				data = AjaxData.responseError(getText("airportEmployee.must"));
//				return JSON;
//			}
//			AirportEmployee employee = (AirportEmployee)getSessionAdminUser();
//			
//			BjdjServiceCode serviceCodeObj = serviceCodeService.getByCode(serviceCode);
//			if(serviceCodeObj == null){
//				
//				data = AjaxData.responseError(getText("serviceCode.notBuy"));
//				return JSON;
//			}
//			
//			if(serviceCodeObj.getOrder().getType() == BjdjOrderType.bjdj){
//				
//				data = AjaxData.responseError(getText("validation.type.error.bjdj"));
//				return JSON;
//			}
//				
//			//保存电瓶车验证表实体
//			try {
//				validationService.persist(serviceCodeObj, null, new Date(), employee, true);
//			} catch (ServiceException e) {
//				e.printStackTrace();
//				data = AjaxData.responseError(e.getMessage());
//				return JSON;
//			}
//			String logContent = "新增电瓶车验证";
//			String logName = SystemOparetionLogUtil.getLogName();
//			String logTime = SystemOparetionLogUtil.getLogTime();
//			String logIp = SystemOparetionLogUtil.getLogIp();
//			
//			logService.persist(logName, logContent, logTime, logIp, versionFlag);
//			//根据保存结果返回页面
//			data = AjaxData.responseSuccess(getText("addSuccess"));
//			return JSON;
//		}
//	}
	
	/**
	 * 修改便捷登机验证表
	 * @Title: BjdjValidationAction
	 * @Description:   
	 * @return
	 * @throws ServiceException    
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setBjdjValidation(validationService.queryById(BjdjValidation.class.getSimpleName(), id));
			return "editBjdjValidation";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(time)) {
				data = AjaxData.responseError(getText("time.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(employee_id)) {
				data = AjaxData.responseError(getText("employee_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(passed)) {
				data = AjaxData.responseError(getText("ended.required"));
				return JSON;
			}
			//修改便捷登机验证表实体
//			boolean isSuc = bjdjValidationService.merge(id, time, serviceCode_id, flightNo, member_id, employee_id, passed,  versionFlag);
//			//根据修改结果返回页面
//			if(isSuc) {
//				data = AjaxData.responseSuccess(getText("editSuccess"));
//			} else {
//				data = AjaxData.responseError(getText("editFailed"));
//			}
			return JSON;
		}
	}
	
	/**
	 * 管理便捷登机验证表实体
	 * @Title: BjdjValidationAction
	 * @Description:    
	 * @return
	 * @throws ServiceException    
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		SystemUser systemUser = (SystemUser) ActionContext.getContext().getSession().get(ContextConstants.SCOPE_SYSTEM_USER);
		if(systemUser instanceof AirportEmployee){//登录的是机场员工
			AirportEmployee airportEmployee = (AirportEmployee)systemUser;
			BjdjHall bjdjHall = airportEmployee.getHall();
			Pagination page = validationService.queryEntityByAdmin(bjdjHall,serviceCode, flightNumber, startTime,endTime, ContextConstants.ADMIN_COMMON_PAGE_SIZE);
			this.setPageModule(page);
		}else{
			Pagination page = validationService.queryEntityByAdmin(serviceCode, flightNumber, startTime,endTime, ContextConstants.ADMIN_COMMON_PAGE_SIZE);
			this.setPageModule(page);
		}
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageBjdjValidation";
	}
	
	/**
	 * @Description：管理电瓶车验证情况
	 * @return
	 * @throws ServiceException
	 */
//	public String manageElectromobile() throws ServiceException {
//		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
//		if(GeneralUtil.isNotNull(keyword)){
//			keyword = DecoderUtil.UtfDecoder(keyword);
//			BjdjServiceCode code = serviceCodeService.getByCode(keyword);
//			List<BjdjValidation> validations = new ArrayList<BjdjValidation>();
//			validations.add(code.getValidation());
//			Pagination pagination = new Pagination();
//			pagination.setPageList(validations);
//			pagination.setTotalCount(validations.size());
//			this.setPageModule(pagination);
//		}else{
//			this.setPageModule(validationService.queryEntity(" and s.type=?3", BjdjOrderType.electromobile));
//		}
//		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
//		return "manageElectromobileValidation";
//	}
	
	/**
	 * 查看回收站
	 * @Title: BjdjValidationAction
	 * @Description:    
	 * @return
	 * @throws ServiceException   
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(validationService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleBjdjValidation";
	}
	
	/**
	 * 逻辑删除便捷登机验证表对象
	 * @Title: BjdjValidationAction
	 * @Description: 把便捷登机验证表对象放入回收站.   
	 * @return
	 * @throws ServiceException    
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = validationService.logicDeleteEntity(BjdjValidation.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除便捷登机验证";
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
	 * 物理删除便捷登机验证表对象
	 * @Title: BjdjValidationAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @throws ServiceException   
	 * @return   
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = validationService.remove(id);
		if(isSuc) {
			String logContent = "物理删除便捷登机验证";
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
	 * 还原一个便捷登机验证表对象
	 * @Title: BjdjValidationAction
	 * @Description: 从回收站还原便捷登机验证表对象   
	 * @throws ServiceException    
	 * @return   
	 */
	public String restore() throws ServiceException {
		boolean isSuc = validationService.restoreEntity(BjdjValidation.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原便捷登机验证";
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
	 * 审核便捷登机验证表对象
	 * @Title: BjdjValidationAction
	 * @Description:  
	 * @throws ServiceException    设定文件   
	 * @return     返回类型   
	 */
	public String audit() throws ServiceException {
		boolean isSuc = validationService.auditEntity(BjdjValidation.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核便捷登机验证";
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
	 * @Title: BjdjValidationAction
	 * @Description:    
	 * @return
	 * @throws ServiceException 
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = validationService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作便捷登机验证";
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
	 * 批量删除便捷登机验证
	 */
	public String batchRealDelete() throws ServiceException {
		boolean isSuc = baseService.batchRealDelete(BjdjValidation.class, idsValue);
		if(isSuc) {
			String logContent = "批量删除便捷登机验证";
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
	public BjdjValidation getBjdjValidation() {
		return bjdjValidation;
	}
	public void setBjdjValidation(BjdjValidation bjdjValidation) {
		this.bjdjValidation = bjdjValidation;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public boolean getEnded() {
		return passed;
	}
	public void setEnded(boolean ended) {
		this.passed = ended;
	}

	public boolean isPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public IBjdjValidationService getValidationService() {
		return validationService;
	}

	public void setValidationService(IBjdjValidationService validationService) {
		this.validationService = validationService;
	}

	public IBjdjAppointmentService getAppointmentService() {
		return appointmentService;
	}

	public void setAppointmentService(IBjdjAppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}

	public ISystemDictionaryService getDictionaryService() {
		return dictionaryService;
	}

	public void setDictionaryService(ISystemDictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	public IBjdjServiceCodeService getServiceCodeService() {
		return serviceCodeService;
	}

	public void setServiceCodeService(IBjdjServiceCodeService serviceCodeService) {
		this.serviceCodeService = serviceCodeService;
	}

	public String getHall() {
		return hall;
	}

	public void setHall(String hall) {
		this.hall = hall;
	}

	public IBjdjHallService getHallService() {
		return hallService;
	}

	public void setHallService(IBjdjHallService hallService) {
		this.hallService = hallService;
	}

	public List<BjdjHall> getHallList() {
		return hallList;
	}

	public void setHallList(List<BjdjHall> hallList) {
		this.hallList = hallList;
	}

	public IBjdjOrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(IBjdjOrderService orderService) {
		this.orderService = orderService;
	}

	public IBjdjDispatchService getDispatchService() {
		return dispatchService;
	}

	public void setDispatchService(IBjdjDispatchService dispatchService) {
		this.dispatchService = dispatchService;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public Date getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(Date flightDate) {
		this.flightDate = flightDate;
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

	public ReportRegion getRegion() {
		return region;
	}

	public void setRegion(ReportRegion region) {
		this.region = region;
	}
}
