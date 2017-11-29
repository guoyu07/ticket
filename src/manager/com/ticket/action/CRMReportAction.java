package com.ticket.action;

import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.ChannelCustomerInfo;
import com.ticket.pojo.DataAuthoritys;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.EmployeeOutVisit;
import com.ticket.pojo.OrderInfo;
import com.ticket.pojo.OrderInfoDetail;
import com.ticket.pojo.PhoneVisit;
import com.ticket.pojo.RechargeRecord;
import com.ticket.pojo.SystemUser;
import com.ticket.service.IBjdjServiceCodeOperationService;
import com.ticket.service.IChannelCustomerInfoService;
import com.ticket.service.IChannelTypeService;
import com.ticket.service.IDataAuthoritysService;
import com.ticket.service.IDepartmentInfoService;
import com.ticket.service.IEmployeeInfoService;
import com.ticket.service.IEmployeeOutVisitService;
import com.ticket.service.IOrderInfoDetailService;
import com.ticket.service.IPhoneVisitService;
import com.ticket.service.IRechargeRecordService;

/**
 * 销售CRM数据报表的请求控制器
 * @author tuyou  
 * @date 
 */
public class CRMReportAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private IChannelCustomerInfoService channelCustomerInfoService;
	@Resource
	private IChannelTypeService channelTypeService;
	@Resource
	private IEmployeeInfoService employeeInfoService;
	@Resource
	private IDepartmentInfoService departmentInfoService;
	@Resource
	private IRechargeRecordService rechargeRecordService;
	@Resource
	private IOrderInfoDetailService orderInfoDetailService;
	@Resource
	private IPhoneVisitService phoneVisitService;
	@Resource
	private IEmployeeOutVisitService outVisitService;
	@Resource
	private IBjdjServiceCodeOperationService operationService;
	@Resource
	private IDataAuthoritysService dataAuthoritysService;
	
	//主键
	private String id;
	private ChannelCustomerInfo channelCustomerInfo;
	
	/**
	 * 客户列表
	 * @return
	 * @throws ServiceException    
	 */
	public String customerList() throws ServiceException {
		
		String method = this.getClass().getSimpleName() + "_" + new Exception().getStackTrace()[0].getMethodName();
		
		SystemUser user = (SystemUser)ActionContext.getContext().getSession().get(ContextConstants.SCOPE_SYSTEM_USER);
		if(user instanceof EmployeeInfo){
			EmployeeInfo info = (EmployeeInfo) user;
			
			List<DataAuthoritys> authoritys = dataAuthoritysService.queryByEmployeeId(info.getId());
			if(authoritys != null){
				for(DataAuthoritys authority:authoritys){
					if(authority.getInMethod().equals(method)){
						if(authority.getContent().equals("one")){
							this.setPageModule(channelCustomerInfoService.paginationQuery(
									"select t from " + ChannelCustomerInfo.class.getName() + " t where t.employeeInfo_id = ? and t.id in (select a.channelCustomerInfoId from RechargeRecord a )", ChannelCustomerInfo.class,info.getId()));
						}
						if(authority.getContent().equals("departMent")){
							this.setPageModule(channelCustomerInfoService.queryAllWhereInDepartment(info,null));
						}
						if(authority.getContent().equals("all")){
							this.setPageModule(channelCustomerInfoService.paginationQuery(
									"select t from " + ChannelCustomerInfo.class.getName() + " t where t.id in (select a.channelCustomerInfoId from RechargeRecord a )", ChannelCustomerInfo.class));
						}
					}
				}
			}
		}else{
			this.setPageModule(channelCustomerInfoService.paginationQuery(
					"select t from " + ChannelCustomerInfo.class.getName() + " t where t.id in (select a.channelCustomerInfoId from RechargeRecord a )", ChannelCustomerInfo.class));
		}
		
		
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
//		this.setPageModule(channelCustomerInfoService.paginationQuery(
//				"select t from " + ChannelCustomerInfo.class.getName() + " t where t.employeeInfo_id is not null", ChannelCustomerInfo.class));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "customerList";
	}
	
	/**
	 * 客户详细信息
	 * @return
	 * @throws ServiceException
	 */
	public String customerDetails() throws ServiceException {
		
		channelCustomerInfo = channelCustomerInfoService.get(ChannelCustomerInfo.class, id);
		return "customerDetails";
	}
	
	/**
	 * 充值详细信息
	 * @return
	 * @throws ServiceException    
	 */
	public String rechargeDetails() throws ServiceException {
		
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(channelCustomerInfoService.paginationQuery(
				"select t from " + RechargeRecord.class.getName() + " t where t.channelCustomerInfoId=?", 
				ChannelCustomerInfo.class, id));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "rechargeDetails";
	}
	
	/**
	 * 服务码详细信息
	 * @return
	 * @throws ServiceException    
	 */
	public String serviceCodeDetails() throws ServiceException {
		
		channelCustomerInfo = channelCustomerInfoService.get(ChannelCustomerInfo.class, id);
		
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(channelCustomerInfoService.paginationQuery(
				"select od from " + OrderInfo.class.getName() + " t,"
				+ " " + OrderInfoDetail.class.getName() + " od"
				+ " where t.channelCustomerInfo_id=? and t.id=od.orderInfo_id", 
				ChannelCustomerInfo.class, id));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "serviceCodeDetails";
	}
	
	/**
	 * 电话拜访详细信息
	 * @return
	 * @throws ServiceException    
	 */
	public String phoneVisitDetails() throws ServiceException {
		
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(channelCustomerInfoService.paginationQuery(
				"select t from " + PhoneVisit.class.getName() + " t where t.customer_id=?", 
				ChannelCustomerInfo.class, id));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "phoneVisitDetails";
	}
	
	/**
	 * 外出拜访详细信息
	 * @return
	 * @throws ServiceException    
	 */
	public String outVisitDetails() throws ServiceException {
		
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(channelCustomerInfoService.paginationQuery(
				"select t from " + EmployeeOutVisit.class.getName() + " t where t.customer_id=?", 
				ChannelCustomerInfo.class, id));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "outVisitDetails";
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public IChannelCustomerInfoService getChannelCustomerInfoService() {
		return channelCustomerInfoService;
	}

	public void setChannelCustomerInfoService(
			IChannelCustomerInfoService channelCustomerInfoService) {
		this.channelCustomerInfoService = channelCustomerInfoService;
	}

	public IEmployeeInfoService getEmployeeInfoService() {
		return employeeInfoService;
	}

	public void setEmployeeInfoService(IEmployeeInfoService employeeInfoService) {
		this.employeeInfoService = employeeInfoService;
	}

	public IDepartmentInfoService getDepartmentInfoService() {
		return departmentInfoService;
	}

	public void setDepartmentInfoService(
			IDepartmentInfoService departmentInfoService) {
		this.departmentInfoService = departmentInfoService;
	}

	public IRechargeRecordService getRechargeRecordService() {
		return rechargeRecordService;
	}

	public void setRechargeRecordService(
			IRechargeRecordService rechargeRecordService) {
		this.rechargeRecordService = rechargeRecordService;
	}

	public IChannelTypeService getChannelTypeService() {
		return channelTypeService;
	}

	public void setChannelTypeService(IChannelTypeService channelTypeService) {
		this.channelTypeService = channelTypeService;
	}

	public ChannelCustomerInfo getChannelCustomerInfo() {
		return channelCustomerInfo;
	}

	public void setChannelCustomerInfo(ChannelCustomerInfo channelCustomerInfo) {
		this.channelCustomerInfo = channelCustomerInfo;
	}

	public IOrderInfoDetailService getOrderInfoDetailService() {
		return orderInfoDetailService;
	}

	public void setOrderInfoDetailService(
			IOrderInfoDetailService orderInfoDetailService) {
		this.orderInfoDetailService = orderInfoDetailService;
	}

	public IPhoneVisitService getPhoneVisitService() {
		return phoneVisitService;
	}

	public void setPhoneVisitService(IPhoneVisitService phoneVisitService) {
		this.phoneVisitService = phoneVisitService;
	}

	public IEmployeeOutVisitService getOutVisitService() {
		return outVisitService;
	}

	public void setOutVisitService(IEmployeeOutVisitService outVisitService) {
		this.outVisitService = outVisitService;
	}
}
