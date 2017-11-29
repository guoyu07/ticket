package com.ticket.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.AirportDepartment;
import com.ticket.pojo.AirportEmployee;
import com.ticket.pojo.BjdjHall;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.SqlParam.Condition;
import com.ticket.pojo.SqlParamGroup;
import com.ticket.pojo.SystemUser;
import com.ticket.service.IAirportDepartmentService;
import com.ticket.service.IAirportEmployeeService;
import com.ticket.service.IBjdjHallService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.DateUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 员工信息控制器
 * @ClassName: AirportEmployeeAction   
 * @Description:  提供员工信息的相关操作方法. 
 * @author HiSay  
 * @date 2015-11-16 23:00:35
 *
 */
public class AirportEmployeeAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//员工信息的业务层
	@Resource private IAirportEmployeeService airportEmployeeService;
	//机场部门的业务层
	@Resource private IAirportDepartmentService airportDepartmentService;
	@Resource private ISystemOperationLogService logService;
	@Resource private IBjdjHallService hallService;
	//员工信息实体
	private AirportEmployee airportEmployee;
	//主键
	private String id;
    //部门id
	private String department_id;
    //姓名
	private String name;
    //手机号
	private String phone;
    //工号
	private String employeeId;
	//用户名
	private String loginId;
    //密码
	private String password;
    //身份证号
	private String IDCard;
	//部门树
	private String departTree;
	//服务厅
	private String hall_id;
	//创建时间
	private Date startTime, endTime;
	
	/**
	 * 下载报表
	 * @return
	 * @throws ServiceException
	 */
	public String downReport() {
		
		SqlParamGroup group = new SqlParamGroup("s.status.deleteFlag", Condition.eq, ContextConstants.STATUS_OF_ZERO).and("s.status.versionFlag", Condition.eq, versionFlag);
		group.and("s.status.createTime", Condition.ge, DateUtil.getDayStart(startTime)).and("s.status.createTime", Condition.le, DateUtil.getDayEnd(endTime));
		group.and("s.department_id", Condition.eq, department_id);
		group.and("s.name", Condition.like_left, name);
		group.and("s.phone", Condition.like_left, phone);
		group.and("s.loginId", Condition.like_left, loginId);
		group.and("s.IDCard", Condition.like_left, IDCard);
		List<AirportEmployee> list = airportEmployeeService.getDbDAO().executeJPQLForQuery("select new AirportEmployee(s.status.createTime, "
				+ "(select d.name from "+AirportDepartment.class.getName()+" d where d.id=s.department_id), "
				+ "(select d from "+BjdjHall.class.getName()+" d where d=s.hall), "
				+ "s.name, s.phone, s.loginId, s.IDCard)"
				+ " from " + AirportEmployee.class.getName() + " s " + group.toString(true), group.getParamArray());
		exportExcel("/WEB-INF/excel/jasper/airportEmployee.jasper", list, "机场员工");
		return null;
	}
	
	/**
	 * 添加员工信息
	 * @Title: AirportEmployeeAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
//			this.setDepartTree(airportDepartmentService.queryAirportDepartmentSelectOptionHtml(null, versionFlag));
			List<BjdjHall> halls = hallService.queryAll(BjdjHall.class);
			ActionContext.getContext().put("halls", halls);
			return "addAirportEmployee";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(department_id)) {
				data = AjaxData.responseError(getText("department_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError(getText("name.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(phone)) {
				data = AjaxData.responseError(getText("phone.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(loginId)) {
				data = AjaxData.responseError(getText("loginId.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(password)) {
				data = AjaxData.responseError(getText("password.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(IDCard)) {
				data = AjaxData.responseError(getText("IDCard.required"));
				return JSON;
			}
			//保存员工信息实体
			boolean isSuc = false;
			try {
				isSuc = airportEmployeeService.persist(department_id, name, phone, employeeId, loginId, password, IDCard,hall_id, versionFlag);
			} catch (Exception e) {
				
				data = AjaxData.responseError(e.getMessage());
				return JSON;
			}
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增机场员工";
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
	 * 修改员工信息
	 * @Title: AirportEmployeeAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setAirportEmployee(airportEmployeeService.queryById(AirportEmployee.class.getSimpleName(), id));
			if(this.getDepartment_id() != null) {
//				this.setDepartTree(airportDepartmentService.queryAirportDepartmentSelectOptionHtml(this.getDepartment_id(), versionFlag));
			} else {
//				this.setDepartTree(airportDepartmentService.queryAirportDepartmentSelectOptionHtml(null, versionFlag));
			}
			List<BjdjHall> halls = hallService.queryAll(BjdjHall.class);
			ActionContext.getContext().put("halls", halls);
			
			return "editAirportEmployee";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(department_id)) {
				data = AjaxData.responseError(getText("department_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError(getText("name.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(phone)) {
				data = AjaxData.responseError(getText("phone.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(loginId)) {
				data = AjaxData.responseError(getText("loginId.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(password)) {
				data = AjaxData.responseError(getText("password.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(IDCard)) {
				data = AjaxData.responseError(getText("IDCard.required"));
				return JSON;
			}
			
			//修改员工信息实体
			try {
				airportEmployeeService.merge(id, department_id, name, phone, employeeId, loginId, password, IDCard, hall_id, versionFlag);
			} catch (Exception e) {
				data = AjaxData.responseError(e.getMessage());
				return JSON;
			}
			String logContent = "修改机场员工信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("editSuccess"));
			return JSON;
		}
	}
	
	/**
	 * 管理员工信息实体
	 * @Title: AirportEmployeeAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		//得到当前请求的方法
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		//获得分页数据
		SqlParamGroup group = new SqlParamGroup("s.status.deleteFlag", Condition.eq, ContextConstants.STATUS_OF_ZERO).and("s.status.versionFlag", Condition.eq, versionFlag);
		group.and("s.status.createTime", Condition.ge, DateUtil.getDayStart(startTime)).and("s.status.createTime", Condition.le, DateUtil.getDayEnd(endTime));
		group.and("s.department_id", Condition.eq, department_id);
		group.and("s.name", Condition.like_left, name);
		group.and("s.phone", Condition.like_left, phone);
		group.and("s.loginId", Condition.like_left, loginId);
		group.and("s.IDCard", Condition.like_left, IDCard);
		Pagination page = airportEmployeeService.paginationQuery("select s from " + AirportEmployee.class.getName() + " s " + group.toString(true), group.getParamArray());
		
		//得到后台登录实体
		SystemUser user = getSessionAdminUser();
		if(user != null && user instanceof AirportEmployee){
			//如果是机场员工
			AirportEmployee employee = (AirportEmployee)user;
			List list = new ArrayList();
			//获取在此页面上的数据
			List employees = page.getPageList();
			for(Object e : employees){
				//将页面上的数据转换为机场员工实体
				AirportEmployee employee1 = (AirportEmployee)e;
				//获得员工所在部门
//					AirportDepartment department = employee1.getDepartment();
				while(employee1.getDepartment_id() != null){//部门不为空
					//得到的部门编号与后台登录的实体所在的部门编号相同
					if(employee1.getDepartment_id().equals(employee.getDepartment_id())){
						//则把这个页面上的该员工加到list集合中
						list.add(employee1);
						break;
					}
					//得到该部门的父节点，即上级部门
					AirportDepartment department = airportEmployeeService.get(AirportDepartment.class, employee1.getDepartment_id());
					department = (AirportDepartment)department.getParent();
				}
				//将list放入setPageList()中，得到新的page
				page.setPageList(list);
			}
			this.setPageModule(page);
		}else{
			this.setPageModule(page);
		}
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageAirportEmployee";
	}
	
	/**
	 * 查看回收站
	 * @Title: AirportEmployeeAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(airportEmployeeService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleAirportEmployee";
	}
	
	/**
	 * 逻辑删除员工信息对象
	 * @Title: AirportEmployeeAction
	 * @Description: 把员工信息对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = airportEmployeeService.logicDeleteEntity(AirportEmployee.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除员工信息";
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
	 * 物理删除员工信息对象
	 * @Title: AirportEmployeeAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = airportEmployeeService.remove(id);
		if(isSuc) {
			String logContent = "物理删除员工信息";
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
	 * 还原一个员工信息对象
	 * @Title: AirportEmployeeAction
	 * @Description: 从回收站还原员工信息对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = airportEmployeeService.restoreEntity(AirportEmployee.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原员工信息";
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
	 * 审核员工信息对象
	 * @Title: AirportEmployeeAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = airportEmployeeService.auditEntity(AirportEmployee.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核员工信息";
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
	 * @Title: AirportEmployeeAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = airportEmployeeService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作员工信息";
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
	 * 批量删除员工信息
	 */
	public String batchRealDelete() throws ServiceException {
		boolean isSuc = airportEmployeeService.batchRealDelete(idsValue,versionFlag);
		if(isSuc) {
			String logContent = "批量删除员工信息";
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
	
	public AirportEmployee getAirportEmployee() {
		return airportEmployee;
	}
	public void setAirportEmployee(AirportEmployee airportEmployee) {
		this.airportEmployee = airportEmployee;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIDCard() {
		return IDCard;
	}
	public void setIDCard(String IDCard) {
		this.IDCard = IDCard;
	}

	public String getDepartTree() {
		return departTree;
	}

	public void setDepartTree(String departTree) {
		this.departTree = departTree;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getHall_id() {
		return hall_id;
	}

	public void setHall_id(String hall_id) {
		this.hall_id = hall_id;
	}

	public IAirportEmployeeService getAirportEmployeeService() {
		return airportEmployeeService;
	}

	public void setAirportEmployeeService(
			IAirportEmployeeService airportEmployeeService) {
		this.airportEmployeeService = airportEmployeeService;
	}

	public IAirportDepartmentService getAirportDepartmentService() {
		return airportDepartmentService;
	}

	public void setAirportDepartmentService(
			IAirportDepartmentService airportDepartmentService) {
		this.airportDepartmentService = airportDepartmentService;
	}

	public ISystemOperationLogService getLogService() {
		return logService;
	}

	public void setLogService(ISystemOperationLogService logService) {
		this.logService = logService;
	}

	public IBjdjHallService getHallService() {
		return hallService;
	}

	public void setHallService(IBjdjHallService hallService) {
		this.hallService = hallService;
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
