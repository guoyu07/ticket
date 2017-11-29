package com.ticket.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.DataAuthoritys;
import com.ticket.pojo.DepartmentInfo;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.SaleTask;
import com.ticket.pojo.SystemUser;
import com.ticket.service.IDataAuthoritysService;
import com.ticket.service.IEmployeeInfoService;
import com.ticket.service.ISaleTaskService;
import com.ticket.util.GeneralUtil;

/**
 * 销售任务控制器
 * @ClassName: SaleTaskAction   
 * @Description:  提供销售任务的相关操作方法. 
 * @author HiSay  
 * @date 2016-05-05 11:18:16
 *
 */
public class SaleTaskAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//销售任务的业务层
	@Resource 
	private ISaleTaskService saleTaskService;
	@Resource 
	private IEmployeeInfoService employeeInfoService;
	@Resource IDataAuthoritysService dataAuthoritysService = null;
	//销售任务实体
	private SaleTask saleTask;
	//主键
	private String id;
    //起始时间
	private Date startTime;
    //结束时间
	private Date endTime;
    //任务等级
	private DepartmentInfo department;
    //创建员工
	private EmployeeInfo employee;
    //签约数
	private int signCount;
    //充值金额
	private double recharge;
    //电话拜访量
	private int phoneCount;
    //外出拜访量
	private int visitCount;
	//备注
	private String remark;
	
	//创建员工
	private String[] employees;
	//签约数
	private int[] signCounts;
	//充值金额
	private double[] recharges;
	//电话拜访量
	private int[] phoneCounts;
	//外出拜访量
	private int[] visitCounts;
	
	/**
	 * 添加销售任务
	 * @Title: SaleTaskAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addSaleTask";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(startTime)) {
				data = AjaxData.responseError(getText("startTime.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(endTime)) {
				data = AjaxData.responseError(getText("endTime.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(signCount)) {
				data = AjaxData.responseError(getText("signCount.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(recharge)) {
				data = AjaxData.responseError(getText("recharge.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(phoneCount)) {
				data = AjaxData.responseError(getText("phoneCount.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(visitCount)) {
				data = AjaxData.responseError(getText("visitCount.required"));
				return JSON;
			}
			//保存销售任务实体
			try {
				saleTaskService.persist(remark, startTime, endTime, signCount, recharge, phoneCount, visitCount, versionFlag);
			} catch (ServiceException e) {
				data = AjaxData.responseError(e.getMessage());
				return JSON;
			}
			//根据保存结果返回页面
			data = AjaxData.responseSuccess(getText("addSuccess"));
			return JSON;
		}
	}
	
	/**
	 * 修改销售任务
	 * @Title: SaleTaskAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setSaleTask(saleTaskService.queryById(SaleTask.class.getSimpleName(), id));
			return "editSaleTask";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(startTime)) {
				data = AjaxData.responseError(getText("startTime.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(endTime)) {
				data = AjaxData.responseError(getText("endTime.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(department)) {
				data = AjaxData.responseError(getText("department.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(employee)) {
				data = AjaxData.responseError(getText("employee.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(signCount)) {
				data = AjaxData.responseError(getText("signCount.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(recharge)) {
				data = AjaxData.responseError(getText("recharge.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(phoneCount)) {
				data = AjaxData.responseError(getText("phoneCount.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(visitCount)) {
				data = AjaxData.responseError(getText("visitCount.required"));
				return JSON;
			}
			//修改销售任务实体
			try {
				saleTaskService.merge(id, remark, startTime, endTime, signCount, recharge, phoneCount, visitCount,  versionFlag);
			} catch (ServiceException e) {
				data = AjaxData.responseError(e.getMessage());
				return JSON;
			}
			data = AjaxData.responseSuccess(getText("editSuccess"));
			return JSON;
		}
	}
	
	/**
	 * 管理销售任务实体
	 * @Title: SaleTaskAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		String method = this.getClass().getSimpleName() + "_" + new Exception().getStackTrace()[0].getMethodName();
		
		SystemUser user = (SystemUser)ActionContext.getContext().getSession().get(ContextConstants.SCOPE_SYSTEM_USER);
		if(user instanceof EmployeeInfo){
			EmployeeInfo info = (EmployeeInfo) user;
			
			List<DataAuthoritys> authoritys = dataAuthoritysService.queryByEmployeeId(info.getId());
			if(authoritys != null){
				for(DataAuthoritys authority:authoritys){
					if(authority.getInMethod().equals(method)){	
						if(authority.getContent().equals("one")){
							this.setPageModule(saleTaskService.paginationQuery("select t from " + SaleTask.class.getName() + " t where t.employee=? order by t.status.createTime desc", getSessionAdminUser()));
						}
						if(authority.getContent().equals("all")){
							this.setPageModule(saleTaskService.paginationQuery("select t from " + SaleTask.class.getName() + " t where t.published = true order by t.status.createTime desc"));
						}
						if(authority.getContent().equals("departMent")){
							this.setPageModule(saleTaskService.paginationQuery("select t from " + SaleTask.class.getName() + " t where t.department.id=? and t.published = true order by t.status.createTime desc", info.getDepartment().getId()));
						}
					}
				}
			}
		}else{
			this.setPageModule(saleTaskService.paginationQuery("select t from " + SaleTask.class.getName() + " t order by t.status.createTime desc"));
		}
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageSaleTask";
	}
	
	/**
	 * 设置销售任务的比重
	 * @return
	 */
	public String configTaskPercent(){
		
		if(GeneralUtil.isNull(operationFlag)){
			
			return "configTaskPercent";
		}
		saleTaskService.setSignCountPercent(signCount);
		saleTaskService.setRechargePercent((int)recharge);
		saleTaskService.setPhoneCountPercent(phoneCount);
		saleTaskService.setVisitCountPercent(visitCount);
		data = AjaxData.responseSuccess(getText("editSuccess"));
		return JSON;
	}
	
	/**
	 * 下发任务
	 * @return
	 */
	public String publish() throws ServiceException {
		
		if(GeneralUtil.isNull(operationFlag)){

			saleTask = saleTaskService.get(SaleTask.class, id);
			List<DepartmentInfo> list = saleTask.getDepartment().getChilds();
			if(list == null || list.isEmpty()){
				
				List<EmployeeInfo> emps = employeeInfoService.queryByDepartmentId(saleTask.getDepartment().getId(), versionFlag);
				ActionContext.getContext().put("list", emps);
			}
			return "publish";
		}

		saleTaskService.publish(id, employees, signCounts, recharges, phoneCounts, visitCounts);
		data = AjaxData.responseSuccess("下发成功");
		return JSON;
	}
	
	/**
	 * 查看任务完成度
	 * @return
	 * @throws ServiceException
	 */
	public String degreeOfCompletion() throws ServiceException{
		
		saleTask = saleTaskService.get(SaleTask.class, id);
		if(saleTask.getParent() == null){ //总任务
			
			int signCount = saleTaskService.completionDegreeForSignCount(saleTask.getStartTime(), saleTask.getEndTime());
			double rechargeCount = saleTaskService.completionDegreeForRecharge(saleTask.getStartTime(), saleTask.getEndTime());
			int phoneCount = saleTaskService.completionDegreeForPhoneCount(saleTask.getStartTime(), saleTask.getEndTime());
			int visitCount = saleTaskService.completionDegreeForVisitCount(saleTask.getStartTime(), saleTask.getEndTime());
			
			ActionContext.getContext().put("signCount2", signCount);
			ActionContext.getContext().put("rechargeCount2", rechargeCount);
			ActionContext.getContext().put("phoneCount2", phoneCount);
			ActionContext.getContext().put("visitCount2", visitCount);
			return "overallDegree";
		}else if(saleTask.getDepartment() != null && saleTask.getDepartment().getChilds().size() == 0){ //部门所有人任务
			
			List<Object[]> list = new ArrayList<Object[]>();
			List<SaleTask> sub = saleTaskService.subSaleTask(saleTask);
			for(int i = 0; i < sub.size(); i++){
				
				int signCount = saleTaskService.onesCompletionDegreeForSignCount(sub.get(i).getEmployee(), saleTask.getStartTime(), saleTask.getEndTime());
				Object[] item1 = new Object[7];
				item1[0] = sub.get(i).getEmployee().getName();
				item1[1] = "业绩考核";
				item1[2] = "签单数";
				item1[3] = sub.get(i).getSignCount();
				item1[4] = signCount;
				item1[5] = sub.get(i).getSignCount() == 0 ? "--" : signCount * 100 / sub.get(i).getSignCount() + "%";
				item1[6] = saleTaskService.getSignCountPercent() + "%";
				list.add(item1);
				
				double rechargeCount = saleTaskService.onesCompletionDegreeForRecharge(sub.get(i).getEmployee(), saleTask.getStartTime(), saleTask.getEndTime());
				Object[] item2 = new Object[7];
				item2[0] = sub.get(i).getEmployee().getName();
				item2[1] = "业绩考核";
				item2[2] = "充值金额";
				item2[3] = sub.get(i).getRecharge();
				item2[4] = rechargeCount;
				item2[5] = sub.get(i).getRecharge() == 0 ? "--" : (int)rechargeCount * 100 / (int)sub.get(i).getRecharge() + "%";
				item2[6] = saleTaskService.getRechargePercent() + "%";
				list.add(item2);
				
				int phoneCount = saleTaskService.onesCompletionDegreeForPhoneCount(sub.get(i).getEmployee(), saleTask.getStartTime(), saleTask.getEndTime());
				Object[] item3 = new Object[7];
				item3[0] = sub.get(i).getEmployee().getName();
				item3[1] = "过程考核";
				item3[2] = "电话拜访";
				item3[3] = sub.get(i).getPhoneCount();
				item3[4] = phoneCount;
				item3[5] = sub.get(i).getPhoneCount() == 0 ? "--" : phoneCount * 100 / sub.get(i).getPhoneCount() + "%";
				item3[6] = saleTaskService.getPhoneCountPercent() + "%";
				list.add(item3);
				
				int visitCount = saleTaskService.onesCompletionDegreeForVisitCount(sub.get(i).getEmployee(), saleTask.getStartTime(), saleTask.getEndTime());
				Object[] item4 = new Object[7];
				item4[0] = sub.get(i).getEmployee().getName();
				item4[1] = "过程考核";
				item4[2] = "外出拜访";
				item4[3] = sub.get(i).getVisitCount();
				item4[4] = visitCount;
				item4[5] = sub.get(i).getVisitCount() == 0 ? "--" : visitCount * 100 / sub.get(i).getVisitCount() + "%";
				item4[6] = saleTaskService.getVisitCountPercent() + "%";
				list.add(item4);
			}
			ActionContext.getContext().put("list", list);
			return "departmentDegree";
		}else if(saleTask.getDepartment() != null){ //部门任务
			
			List<Object[]> list = new ArrayList<Object[]>();
			List<SaleTask> sub = saleTaskService.subSaleTask(saleTask);
			for(int i = 0; i < sub.size(); i++){
				
				int signCount = saleTaskService.departmentCompletionDegreeForSignCount(sub.get(i).getDepartment(), saleTask.getStartTime(), saleTask.getEndTime());
				Object[] item1 = new Object[7];
				item1[0] = sub.get(i).getDepartment().getName();
				item1[1] = "业绩考核";
				item1[2] = "签单数";
				item1[3] = sub.get(i).getSignCount();
				item1[4] = signCount;
				item1[5] = sub.get(i).getSignCount() == 0 ? "--" : signCount * 100 / sub.get(i).getSignCount() + "%";
				item1[6] = saleTaskService.getSignCountPercent() + "%";
				list.add(item1);
				
				double rechargeCount = saleTaskService.departmentCompletionDegreeForRecharge(sub.get(i).getDepartment(), saleTask.getStartTime(), saleTask.getEndTime());
				Object[] item2 = new Object[7];
				item2[0] = sub.get(i).getDepartment().getName();
				item2[1] = "业绩考核";
				item2[2] = "充值金额";
				item2[3] = sub.get(i).getRecharge();
				item2[4] = rechargeCount;
				item2[5] = sub.get(i).getRecharge() == 0 ? "--" : (int)rechargeCount * 100 / (int)sub.get(i).getRecharge() + "%";
				item2[6] = saleTaskService.getRechargePercent() + "%";
				list.add(item2);
				
				int phoneCount = saleTaskService.departmentCompletionDegreeForPhoneCount(sub.get(i).getDepartment(), saleTask.getStartTime(), saleTask.getEndTime());
				Object[] item3 = new Object[7];
				item3[0] = sub.get(i).getDepartment().getName();
				item3[1] = "过程考核";
				item3[2] = "电话拜访";
				item3[3] = sub.get(i).getPhoneCount();
				item3[4] = phoneCount;
				item3[5] = sub.get(i).getRecharge() == 0 ? "--" : phoneCount * 100 / sub.get(i).getPhoneCount() + "%";
				item3[6] = saleTaskService.getPhoneCountPercent() + "%";
				list.add(item3);
				
				int visitCount = saleTaskService.departmentCompletionDegreeForVisitCount(sub.get(i).getDepartment(), saleTask.getStartTime(), saleTask.getEndTime());
				Object[] item4 = new Object[7];
				item4[0] = sub.get(i).getDepartment().getName();
				item4[1] = "过程考核";
				item4[2] = "外出拜访";
				item4[3] = sub.get(i).getVisitCount();
				item4[4] = visitCount;
				item4[5] = sub.get(i).getVisitCount() == 0 ? "--" : visitCount * 100 / sub.get(i).getVisitCount() + "%";
				item4[6] = saleTaskService.getVisitCountPercent() + "%";
				list.add(item4);
			}
			ActionContext.getContext().put("list", list);
			return "departmentDegree";
		}else{ //针对个人的任务
			
			int signCount = saleTaskService.onesCompletionDegreeForSignCount(saleTask.getEmployee(), saleTask.getStartTime(), saleTask.getEndTime());
			double rechargeCount = saleTaskService.onesCompletionDegreeForRecharge(saleTask.getEmployee(), saleTask.getStartTime(), saleTask.getEndTime());
			int phoneCount = saleTaskService.onesCompletionDegreeForPhoneCount(saleTask.getEmployee(), saleTask.getStartTime(), saleTask.getEndTime());
			int visitCount = saleTaskService.onesCompletionDegreeForVisitCount(saleTask.getEmployee(), saleTask.getStartTime(), saleTask.getEndTime());
			
			ActionContext.getContext().put("signCount2", signCount);
			ActionContext.getContext().put("rechargeCount2", rechargeCount);
			ActionContext.getContext().put("phoneCount2", phoneCount);
			ActionContext.getContext().put("visitCount2", visitCount);
			return "personalDegree";
		}
	}
	
	/**
	 * 查看回收站
	 * @Title: SaleTaskAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(saleTaskService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleSaleTask";
	}
	
	/**
	 * 逻辑删除销售任务对象
	 * @Title: SaleTaskAction
	 * @Description: 把销售任务对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = saleTaskService.logicDeleteEntity(SaleTask.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除销售任务对象
	 * @Title: SaleTaskAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = saleTaskService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个销售任务对象
	 * @Title: SaleTaskAction
	 * @Description: 从回收站还原销售任务对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = saleTaskService.restoreEntity(SaleTask.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核销售任务对象
	 * @Title: SaleTaskAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = saleTaskService.auditEntity(SaleTask.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: SaleTaskAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = saleTaskService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public SaleTask getSaleTask() {
		return saleTask;
	}
	public void setSaleTask(SaleTask saleTask) {
		this.saleTask = saleTask;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public DepartmentInfo getDepartment() {
		return department;
	}
	public void setDepartment(DepartmentInfo department) {
		this.department = department;
	}
	public EmployeeInfo getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeeInfo employee) {
		this.employee = employee;
	}
	public int getSignCount() {
		return signCount;
	}
	public void setSignCount(int signCount) {
		this.signCount = signCount;
	}
	public double getRecharge() {
		return recharge;
	}
	public void setRecharge(double recharge) {
		this.recharge = recharge;
	}
	public int getPhoneCount() {
		return phoneCount;
	}
	public void setPhoneCount(int phoneCount) {
		this.phoneCount = phoneCount;
	}
	public int getVisitCount() {
		return visitCount;
	}
	public void setVisitCount(int visitCount) {
		this.visitCount = visitCount;
	}

	public ISaleTaskService getSaleTaskService() {
		return saleTaskService;
	}

	public void setSaleTaskService(ISaleTaskService saleTaskService) {
		this.saleTaskService = saleTaskService;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public IEmployeeInfoService getEmployeeInfoService() {
		return employeeInfoService;
	}

	public void setEmployeeInfoService(IEmployeeInfoService employeeInfoService) {
		this.employeeInfoService = employeeInfoService;
	}

	public String[] getEmployees() {
		return employees;
	}

	public void setEmployees(String[] employees) {
		this.employees = employees;
	}

	public int[] getSignCounts() {
		return signCounts;
	}

	public void setSignCounts(int[] signCounts) {
		this.signCounts = signCounts;
	}

	public double[] getRecharges() {
		return recharges;
	}

	public void setRecharges(double[] recharges) {
		this.recharges = recharges;
	}

	public int[] getPhoneCounts() {
		return phoneCounts;
	}

	public void setPhoneCounts(int[] phoneCounts) {
		this.phoneCounts = phoneCounts;
	}

	public int[] getVisitCounts() {
		return visitCounts;
	}

	public void setVisitCounts(int[] visitCounts) {
		this.visitCounts = visitCounts;
	}
}
