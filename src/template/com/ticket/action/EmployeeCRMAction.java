package com.ticket.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.AirportEmployee;
import com.ticket.pojo.BjdjDispatch;
import com.ticket.pojo.BjdjDispatchList;
import com.ticket.pojo.Member;
import com.ticket.pojo.RoleInfo;
import com.ticket.pojo.SystemUser;
import com.ticket.pojo.UserRole;
import com.ticket.service.IAirportEmployeeService;
import com.ticket.service.IBjdjDispatchListService;
import com.ticket.service.IBjdjDispatchService;
import com.ticket.service.IRoleInfoService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.service.IUserRoleService;
import com.ticket.util.GeneralUtil;

/**
 * @Description：机场员工crm前台控制器
 * @author：涂有
 * @date 2015年11月18日 下午11:08:17
 */
public class EmployeeCRMAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	@Resource
	private ISystemDictionaryService dictionaryService;
	@Resource
	private IBjdjDispatchService dispatchService;
	@Resource
	private IBjdjDispatchListService dispatchListService;
	@Resource
	private IAirportEmployeeService employeeService;
	@Resource
	private IUserRoleService roleService;
	@Resource
	private IRoleInfoService roleInfoService;
	/**
	 * 分单
	 */
	private BjdjDispatch dispatch;
	
	/**
	 * 分单项
	 */
	private BjdjDispatchList dispatchList;
	
	/**
	 * 机场员工
	 */
	private AirportEmployee employee;
	
	private String id;
	private String phone;
	private String loginId;
	private String employeeId;
	private String mix;
	private String password;
	private String dispatch_id;
	
	private String newPwd;
	/**
	 * @Description：默认进入登录页面
	 * @return
	 */
	public String execute(){
		
		//如果不为空就进入主页面
		SystemUser user = (SystemUser)getSession().get(ContextConstants.SCOPE_SYSTEM_USER);
		if(user != null && user instanceof AirportEmployee){
			
			return personInfoPage();
		}
		
		return loginPage();
	}
	/**
	 * 预约页面
	 * @return
	 */
	public String orderSchedule(){
		
		return "appointmentSituation";
	}
	/**
	 * 进入验证页面
	 * @return
	 */
	public String yanZhengCaoZuo(){
		return "yanZhengCaoZuo";
	}
	/**
	 * 进入派单页面
	 * @return
	 */
	public String paiDanCaoZuo(){
		return "paiDanCaoZuo";
	}
	/**
	 * 进入核销页面
	 * @return
	 */
	public String heXiaoCaoZuo(){
		return "heXiaoCaoZuo";
	}
	/**
	 * @Description：进入登录页面
	 * @return
	 */
	public String loginPage(){
		
		return "loginPage";
	}
	/**
	 * 登录成功
	 * @return
	 */
	public String loginSucess(){
		
		return JSON;
	}
	
	/**
	 * @Description：进入个人中心
	 * @return
	 */
	public String personInfoPage(){
		ActionContext.getContext().put("loginUrl", "employeeCRM.action");
		return "personInfoPage";
	}
	/**
	 * 机场员工在个人中心修改密码
	 * @return
	 * @throws ServiceException
	 */
	public String modifyPwd() throws ServiceException{
		if(GeneralUtil.isNull(phone)){
			data = AjaxData.responseError("请先绑定手机号码，修改失败");
			return JSON;
		}
		List<AirportEmployee> employees = employeeService.getByColumn("phone", phone);
		if(employees.size() == 0){
			data = AjaxData.responseError(getText("phone.error"));
			return JSON;
		}
		employee = employees.get(0);
		if(GeneralUtil.isNull(password)){
			data = AjaxData.responseError("请输入原密码");
			return JSON;
		}
		if(!employee.getPassword().equals(password)){
			data = AjaxData.responseError("修改失败");
			return JSON;
		}
		if(GeneralUtil.isNull(newPwd)){
			data = AjaxData.responseError("请输入新密码");
			return JSON;
		}
		if(password.equals(newPwd)){
			data = AjaxData.responseError("新密码不能和原密码相同");
			return JSON;
		}
		//修改成功
		employee.setPassword(newPwd);
		employeeService.merge(employee);
		getSession().remove(ContextConstants.SCOPE_SYSTEM_USER);
		data = AjaxData.responseSuccess("修改成功，请重新登录！");
		return JSON;
	}
	
	/**
	 * @Description：进入工单页
	 * @return
	 */
	public String workOrderPage(){
		
		AirportEmployee employee = (AirportEmployee)getSessionAdminUser();
		Long accept = dispatchListService.acceptAmount(employee);
		Long noAccept = dispatchListService.noAcceptAmount(employee);
		Long endedAccept = dispatchListService.endedAcceptAmount(employee);
		
		ActionContext.getContext().put("accept", accept);
		ActionContext.getContext().put("noAccept", noAccept);
		ActionContext.getContext().put("endedAccept", endedAccept);
		
		return "workOrderPage";
	}
	
	/**
	 * @Description：进入工单页
	 * @return
	 * @throws ServiceException 
	 */
	public String workOrderPageInterface() throws ServiceException{
		
		//检验是否登录
		if(GeneralUtil.isNull(id)){
			
			data = AjaxData.responseError(getText("member.id.required"));
			return JSON;
		}
		employee = employeeService.queryById(Member.class.getName(), id);
		if(employee == null){
			
			data = AjaxData.responseError(getText("member.id.error"));
			return JSON;
		}
		
		Long accept = dispatchListService.acceptAmount(employee);
		Long noAccept = dispatchListService.noAcceptAmount(employee);
		
		JSONObject json = new JSONObject();
		json.put("accept", accept);
		json.put("noAccept", noAccept);
		
		data = AjaxData.responseSuccess(json.toJSONString());
		return JSON;
	}
	
	/**
	 * @Description：进入未接工单页
	 * @return
	 */
	public String workOrderNoReceivePage(){
		
		AirportEmployee employee = (AirportEmployee)getSessionAdminUser();
		List<BjdjDispatchList> list = dispatchListService.noAcceptDispatchList(employee);
		ActionContext.getContext().put("list", list);
		
		return "workOrderNoReceivePage";
	}
	
	/**
	 * @Description：进入未接工单页
	 * @return
	 * @throws ServiceException 
	 */
	public String workOrderNoReceivePageInterface() throws ServiceException{
		
		//检验是否登录
		if(GeneralUtil.isNull(id)){
			
			data = AjaxData.responseError(getText("member.id.required"));
			return JSON;
		}
		employee = employeeService.queryById(Member.class.getName(), id);
		if(employee == null){
			
			data = AjaxData.responseError(getText("member.id.error"));
			return JSON;
		}
		
		List<BjdjDispatchList> list = dispatchListService.noAcceptDispatchList(employee);
		data = AjaxData.responseSuccess(com.alibaba.fastjson.JSON.toJSONString(list));
		
		return JSON;
	}
	
	/**
	 * @Description：进入已接工单页
	 * @return
	 */
	public String workOrderReceivedPage(){
		
		AirportEmployee employee = (AirportEmployee)getSessionAdminUser();
		List<BjdjDispatchList> list = dispatchListService.acceptDispatchList(employee);
		ActionContext.getContext().put("list", list);
		return "workOrderReceivedPage";
	}
	
	/**
	 * @Description：进入已接工单页
	 * @return
	 * @throws ServiceException 
	 */
	public String workOrderReceivedPageInterface() throws ServiceException{
		
		//检验是否登录
		if(GeneralUtil.isNull(id)){
			
			data = AjaxData.responseError(getText("member.id.required"));
			return JSON;
		}
		employee = employeeService.queryById(Member.class.getName(), id);
		if(employee == null){
			
			data = AjaxData.responseError(getText("member.id.error"));
			return JSON;
		}
		
		List<BjdjDispatchList> list = dispatchListService.acceptDispatchList(employee);
		data = AjaxData.responseSuccess(com.alibaba.fastjson.JSON.toJSONString(list));
		
		return JSON;
	}
	
	/**
	 * @Description：进入完成工单页
	 * @return
	 */
	public String workOrderEndedPage(){
		
		AirportEmployee employee = (AirportEmployee)getSessionAdminUser();
		List<BjdjDispatchList> list = dispatchListService.endedDispatchList(employee);
		ActionContext.getContext().put("list", list);
		return "workOrderEndedPage";
	}
	
	/**
	 * @Description：进入未接工单详情页
	 * @return
	 * @throws ServiceException 
	 */
	public String detailsNoReceivePage() throws ServiceException{
		
		BjdjDispatchList dispatchItem = dispatchListService.queryById(BjdjDispatchList.class.getName(), id);
		BjdjDispatch dispatch = dispatchItem.getDispatch();
		List<BjdjDispatchList> items = dispatch.getDispatchList();
		ActionContext.getContext().put("dispatch", dispatch);
		ActionContext.getContext().put("item", dispatchItem);
		ActionContext.getContext().put("items", items);
		return "detailsNoReceivePage";
	}
	
	/**
	 * @Description：进入未接工单详情页
	 * @return
	 * @throws ServiceException 
	 */
	public String detailsPageInterface() throws ServiceException{
		
		//检验是否登录
		if(GeneralUtil.isNull(id)){
			
			data = AjaxData.responseError(getText("member.id.required"));
			return JSON;
		}
		employee = employeeService.queryById(Member.class.getName(), id);
		if(employee == null){
			
			data = AjaxData.responseError(getText("member.id.error"));
			return JSON;
		}
		
		BjdjDispatchList dispatchItem = dispatchListService.queryById(BjdjDispatchList.class.getName(), dispatch_id);
		BjdjDispatch dispatch = dispatchItem.getDispatch();
		
		JSONObject json = new JSONObject();
		json.put("dispatch", dispatch);
		json.put("item", dispatchItem);
		
		data = AjaxData.responseSuccess(json.toJSONString());
		return JSON;
	}
	
	/**
	 * @Description：进入已接工单详情页
	 * @return
	 * @throws ServiceException 
	 */
	public String detailsReceivedPage() throws ServiceException{
		
		BjdjDispatchList dispatchItem = dispatchListService.queryById(BjdjDispatchList.class.getName(), id);
		BjdjDispatch dispatch = dispatchItem.getDispatch();
		ActionContext.getContext().put("dispatch", dispatch);
		ActionContext.getContext().put("item", dispatchItem);
		return "detailsReceivedPage";
	}
	
	/**
	 * @Description：进入完成工单详情页
	 * @return
	 * @throws ServiceException 
	 */
	public String detailsEndedPage() throws ServiceException{
		
		BjdjDispatchList dispatchItem = dispatchListService.queryById(BjdjDispatchList.class.getName(), id);
		BjdjDispatch dispatch = dispatchItem.getDispatch();
		ActionContext.getContext().put("dispatch", dispatch);
		ActionContext.getContext().put("item", dispatchItem);
		return "detailsEndedPage";
	}
	
	/**
	 * @Description：登录控制器
	 * @return
	 */
	public String login() throws ServiceException{
		
		try {
			employee = employeeService.queryByMixAndPwd(mix, password);
			
			
			if(employee != null){
				String employee_id = employee.getId();
				List<UserRole> roles = roleService.findByUserId(employee_id); 
				List<String> names = new ArrayList<String>();
				for(UserRole role: roles){
					String role_id = role.getRoleId();
					RoleInfo info = roleInfoService.findByRoleId(role_id);
					String role_name = info.getName();
					names.add(role_name);
				}
				//如果是服务岗或者机场普通员工登录成功后跳转的页面
				if(names.size() == 1 && (names.get(0).contains(dispatchService.getDispatchRoleName()) || names.get(0).contains("普通员工"))){
					Long accept = dispatchListService.acceptAmount(employee);
					Long noAccept = dispatchListService.noAcceptAmount(employee);
					Long endedAccept = dispatchListService.endedAcceptAmount(employee);
					
					ActionContext.getContext().put("accept", accept);
					ActionContext.getContext().put("noAccept", noAccept);
					ActionContext.getContext().put("endedAccept", endedAccept);
					ActionContext.getContext().getSession().put("isOrdinary", null);
					getSession().put(ContextConstants.SCOPE_SYSTEM_USER, employee);
					return "workOrderPage";
				}else{
					for(String name: names){
						/*if(name.equals("机场普通员工")){
						ActionContext.getContext().getSession().put("ordinary", 1);
						continue;
					}
					if(name.equals("服务岗")){
						ActionContext.getContext().getSession().put("ordinary", 1);
						continue;
					}*/
						if(name.equals("调度岗")){
							ActionContext.getContext().getSession().put("isOrdinary", 1);
							ActionContext.getContext().getSession().put("dispatch", 2);
							ActionContext.getContext().getSession().put("verification", null);
							ActionContext.getContext().getSession().put("administrators", null);
							continue;
						}
						if(name.equals("验证岗")){
							ActionContext.getContext().getSession().put("isOrdinary", 1);
							ActionContext.getContext().getSession().put("verification", 3);
							ActionContext.getContext().getSession().put("dispatch",null);
							ActionContext.getContext().getSession().put("administrators", null);
							continue;
						}
						if(name.equals("管理岗")){
							ActionContext.getContext().getSession().put("isOrdinary", 1);
							ActionContext.getContext().getSession().put("administrators", 4);
							ActionContext.getContext().getSession().put("verification",null);
							ActionContext.getContext().getSession().put("dispatch",null);
							continue;
						}
						if(name.equals("公司销售")){
							ActionContext.getContext().getSession().put("isOrdinary", 1);
							ActionContext.getContext().getSession().put("sale", 5);
							continue;
						}
						if(name.equals("部门经理")){
							ActionContext.getContext().getSession().put("isOrdinary", 1);
							ActionContext.getContext().getSession().put("manager", 6);
							ActionContext.getContext().getSession().put("sale",null);
							ActionContext.getContext().getSession().put("administrators",null);
							ActionContext.getContext().getSession().put("verification",null);
							ActionContext.getContext().getSession().put("dispatch",null);
							continue;
						}
						if(name.equals("超级管理员")){
							ActionContext.getContext().getSession().put("isOrdinary", 1);
							ActionContext.getContext().getSession().put("supperAdministrators", 7);
							ActionContext.getContext().getSession().put("manager",null);
							ActionContext.getContext().getSession().put("sale",null);
							ActionContext.getContext().getSession().put("administrators",null);
							ActionContext.getContext().getSession().put("verification",null);
							ActionContext.getContext().getSession().put("dispatch",null);
							break;
						}
					}
					getSession().put(ContextConstants.SCOPE_SYSTEM_USER, employee);
					return "loginIndex";
				}
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return loginPage();
	}
	
	/**
	 * @Description：登录控制器
	 * @return
	 */
	public String loginInterface(){
		
		try {
			employee = employeeService.queryByMixAndPwd(mix, password);
			
			if(employee != null){
				
				data = AjaxData.responseSuccess(JSONObject.toJSONString(employee));
				return JSON;
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		data = AjaxData.responseError("");
		return JSON;
	}
	
	/**
	 * @Description：登录控制器
	 * @return
	 */
	public String logout(){
		ActionContext.getContext().getSession().put("supperAdministrators", null);
		ActionContext.getContext().getSession().put("manager",null);
		ActionContext.getContext().getSession().put("sale",null);
		ActionContext.getContext().getSession().put("administrators",null);
		ActionContext.getContext().getSession().put("verification",null);
		ActionContext.getContext().getSession().put("dispatch",null);
		ActionContext.getContext().getSession().put("ordinary",null);
		ActionContext.getContext().put(ContextConstants.SCOPE_SYSTEM_USER, null);
		ActionContext.getContext().getSession().put("isOrdinary", null);
		return "logoutSuccess";
	}
	
	public BjdjDispatch getDispatch() {
		return dispatch;
	}

	public void setDispatch(BjdjDispatch dispatch) {
		this.dispatch = dispatch;
	}

	public BjdjDispatchList getDispatchList() {
		return dispatchList;
	}

	public void setDispatchList(BjdjDispatchList dispatchList) {
		this.dispatchList = dispatchList;
	}

	public AirportEmployee getEmployee() {
		return employee;
	}

	public void setEmployee(AirportEmployee employee) {
		this.employee = employee;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getMix() {
		return mix;
	}

	public void setMix(String mix) {
		this.mix = mix;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ISystemDictionaryService getDictionaryService() {
		return dictionaryService;
	}

	public void setDictionaryService(ISystemDictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	public IBjdjDispatchService getDispatchService() {
		return dispatchService;
	}

	public void setDispatchService(IBjdjDispatchService dispatchService) {
		this.dispatchService = dispatchService;
	}

	public IBjdjDispatchListService getDispatchListService() {
		return dispatchListService;
	}

	public void setDispatchListService(IBjdjDispatchListService dispatchListService) {
		this.dispatchListService = dispatchListService;
	}

	public IAirportEmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(IAirportEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public String getDispatch_id() {
		return dispatch_id;
	}

	public void setDispatch_id(String dispatch_id) {
		this.dispatch_id = dispatch_id;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
}