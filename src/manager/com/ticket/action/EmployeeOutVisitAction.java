package com.ticket.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.ChannelCustomerInfo;
import com.ticket.pojo.DataAuthoritys;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.EmployeeOutVisit;
import com.ticket.pojo.RemarkVisitRecord;
import com.ticket.pojo.SystemUser;
import com.ticket.service.IChannelCustomerInfoService;
import com.ticket.service.IDataAuthoritysService;
import com.ticket.service.IEmployeeOutVisitService;
import com.ticket.service.IRemarkVisitRecordService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 员工外出拜访记录控制器
 * @ClassName: EmployeeOutVisitAction   
 * @Description:  提供员工外出拜访记录的相关操作方法. 
 * @author HiSay  
 * @date 2015-11-02 22:49:40
 *
 */
public class EmployeeOutVisitAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//员工外出拜访记录的业务层
	@Resource private IEmployeeOutVisitService employeeOutVisitService = null;
	@Resource private IChannelCustomerInfoService channelCustomerInfoService = null;
	@Resource private ISystemOperationLogService logService = null;
	@Resource private IDataAuthoritysService dataAuthoritysService = null;
	/**
	 * 拜访记录的备注业务层
	 */
	@Resource private IRemarkVisitRecordService remarkVisitRecordService = null;
	//员工外出拜访记录实体
	private EmployeeOutVisit employeeOutVisit = null;
	private String id,contact,contactPhone,visitPurpose,employee_id,accompanyPerPerson,visitResult
			,setoutTime,backTime;
	private Date visitDate = null;
	private Integer state = null;
	private ChannelCustomerInfo channelCustomerInfo = null;
	
	//客户id
	private String customer_id = null;
	
	//备注列表
	private List<RemarkVisitRecord> remarkVisitRecordList = null;
  
	
	/**
	 * 添加员工外出拜访记录
	 * @Title: EmployeeOutVisitAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			channelCustomerInfo = channelCustomerInfoService.queryById(ChannelCustomerInfo.class.getSimpleName(), id);
			return "addEmployeeOutVisit";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(visitDate)) {
				data = AjaxData.responseError(getText("visitDate.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(customer_id)) {
				data = AjaxData.responseError(getText("customer_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(contact)) {
				data = AjaxData.responseError(getText("contact.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(contactPhone)) {
				data = AjaxData.responseError(getText("contactPhone.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(visitPurpose)) {
				data = AjaxData.responseError(getText("visitPurpose.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(setoutTime)) {
				data = AjaxData.responseError(getText("setoutTime.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(backTime)) {
				data = AjaxData.responseError(getText("backTime.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(visitResult)) {
				data = AjaxData.responseError(getText("visitResult.required"));
				return JSON;
			}
			//保存员工外出拜访记录实体
			boolean isSuc = employeeOutVisitService.persist(visitDate, customer_id, contact,
					contactPhone, visitPurpose, getSystemEmployeeInfo(), accompanyPerPerson, 
					setoutTime, backTime, visitResult);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增员工外出拜访记录";
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
	 * 修改员工外出拜访记录
	 * @Title: EmployeeOutVisitAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setEmployeeOutVisit(employeeOutVisitService.queryById(EmployeeOutVisit.class.getSimpleName(), id));
			return "editEmployeeOutVisit";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(visitDate)) {
				data = AjaxData.responseError(getText("visitDate.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(customer_id)) {
				data = AjaxData.responseError(getText("customer_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(contact)) {
				data = AjaxData.responseError(getText("contact.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(contactPhone)) {
				data = AjaxData.responseError(getText("contactPhone.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(visitPurpose)) {
				data = AjaxData.responseError(getText("visitPurpose.required"));
				return JSON;
			}

			if(GeneralUtil.isNull(accompanyPerPerson)) {
				data = AjaxData.responseError(getText("accompanyPerPerson.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(setoutTime)) {
				data = AjaxData.responseError(getText("setoutTime.required"));
				return JSON;
			}
			//修改员工外出拜访记录实体
			boolean isSuc = employeeOutVisitService.merge(id, visitDate, customer_id, contact,
					contactPhone, visitPurpose, accompanyPerPerson, setoutTime,
					backTime, visitResult);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改员工外出拜访记录";
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
	 * 修改员工外出拜访记录
	 * @Title: EmployeeOutVisitAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit2() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setEmployeeOutVisit(employeeOutVisitService.queryById(EmployeeOutVisit.class.getSimpleName(), id));
			return "editEmployeeOutVisit2";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(backTime)) {
				data = AjaxData.responseError(getText("backTime.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(visitResult)) {
				data = AjaxData.responseError(getText("visitResult.required"));
				return JSON;
			}
			//修改员工外出拜访记录实体
			boolean isSuc = employeeOutVisitService.merge(id,backTime, visitResult,state);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改员工外出拜访记录";
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
	 * @author wangjiafeng
	 * 查看详情
	 * @method view
	 * @return
	 * @throws ServiceException
	 * @return String
	 * @date 2016-1-9 下午01:48:55
	 */
	public String view() throws ServiceException {
		this.setEmployeeOutVisit(employeeOutVisitService.queryById(EmployeeOutVisit.class.getSimpleName(), id));
		List<RemarkVisitRecord> list = remarkVisitRecordService.queryListByRecordId(id,versionFlag);
		this.setRemarkVisitRecordList(list);
		
		return "view";
	}
	
	/**
	 * 管理员工外出拜访记录实体
	 * @Title: EmployeeOutVisitAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		if(GeneralUtil.isNull(state)){
			state = 2;
		}
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		String method = this.getClass().getSimpleName() + "_" + new Exception().getStackTrace()[0].getMethodName();;
		SystemUser user = (SystemUser)ActionContext.getContext().getSession().get(ContextConstants.SCOPE_SYSTEM_USER);
		if(user instanceof EmployeeInfo){
			EmployeeInfo info = (EmployeeInfo) user;
			
			List<DataAuthoritys> authoritys = dataAuthoritysService.queryByEmployeeId(info.getId());
			if(authoritys != null){
				for(DataAuthoritys authority:authoritys){
					if(authority.getInMethod().equals(method)){
						if(authority.getContent().equals("one")){
							this.setPageModule(employeeOutVisitService.queryEntityByEmployeeId(versionFlag, 30, info.getId(),state));	
						}
						if(authority.getContent().equals("departMent")){
							this.setPageModule(employeeOutVisitService.queryAllWhereInDepartment(info,state));
						}
						if(authority.getContent().equals("all")){
							this.setPageModule(employeeOutVisitService.queryAll(null, 0, 30,state));
						}
					}
				}
			}
		}else{
			this.setPageModule(employeeOutVisitService.queryAll(null, 0, 30,state));
		}
		
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		/*if(GeneralUtil.isNotNull(customer_id)){
			this.setPageModule(employeeOutVisitService.queryByCustomerId(customer_id,ContextConstants.ADMIN_COMMON_PAGE_SIZE,versionFlag));
			this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		}*/
		//this.setPageModule(employeeOutVisitService.queryAll(getSystemEmployeeInfo(), 0, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		return "manageEmployeeOutVisit";
	}
	
	/**
	 * 查看回收站
	 * @Title: EmployeeOutVisitAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(employeeOutVisitService.queryAll(getSystemEmployeeInfo(), 1, ContextConstants.ADMIN_COMMON_PAGE_SIZE,state));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleEmployeeOutVisit";
	}
	
	/**
	 * 逻辑删除员工外出拜访记录对象
	 * @Title: EmployeeOutVisitAction
	 * @Description: 把员工外出拜访记录对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = employeeOutVisitService.logicDeleteEntity(EmployeeOutVisit.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除员工外出拜访记录";
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
	 * 物理删除员工外出拜访记录对象
	 * @Title: EmployeeOutVisitAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = employeeOutVisitService.remove(id);
		if(isSuc) {
			String logContent = "物理删除员工外出拜访记录";
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
	 * 还原一个员工外出拜访记录对象
	 * @Title: EmployeeOutVisitAction
	 * @Description: 从回收站还原员工外出拜访记录对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = employeeOutVisitService.restoreEntity(EmployeeOutVisit.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原员工外出拜访记录";
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
	 * 审核员工外出拜访记录对象
	 * @Title: EmployeeOutVisitAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = employeeOutVisitService.auditEntity(EmployeeOutVisit.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核员工外出拜访记录";
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
	 * @Title: EmployeeOutVisitAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = employeeOutVisitService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作员工外出拜访记录";
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
	 * 批量彻底删除会员外出拜访记录实体
	 * @return
	 * @throws ServiceException
	 */
	public String batchRealDelete() throws ServiceException {
		boolean isSuc = employeeOutVisitService.batchRealDelete(idsValue, versionFlag);
		if(isSuc) {
			String logContent = "批量删除员工外出拜访记录";
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
	
	public EmployeeOutVisit getEmployeeOutVisit() {
		return employeeOutVisit;
	}
	public void setEmployeeOutVisit(EmployeeOutVisit employeeOutVisit) {
		this.employeeOutVisit = employeeOutVisit;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getVisitPurpose() {
		return visitPurpose;
	}
	public void setVisitPurpose(String visitPurpose) {
		this.visitPurpose = visitPurpose;
	}
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public String getAccompanyPerPerson() {
		return accompanyPerPerson;
	}
	public void setAccompanyPerPerson(String accompanyPerPerson) {
		this.accompanyPerPerson = accompanyPerPerson;
	}
	public String getVisitResult() {
		return visitResult;
	}
	public void setVisitResult(String visitResult) {
		this.visitResult = visitResult;
	}

	public String getSetoutTime() {
		return setoutTime;
	}

	public void setSetoutTime(String setoutTime) {
		this.setoutTime = setoutTime;
	}

	public String getBackTime() {
		return backTime;
	}

	public void setBackTime(String backTime) {
		this.backTime = backTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public ChannelCustomerInfo getChannelCustomerInfo() {
		return channelCustomerInfo;
	}

	public void setChannelCustomerInfo(ChannelCustomerInfo channelCustomerInfo) {
		this.channelCustomerInfo = channelCustomerInfo;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public List<RemarkVisitRecord> getRemarkVisitRecordList() {
		return remarkVisitRecordList;
	}

	public void setRemarkVisitRecordList(
			List<RemarkVisitRecord> remarkVisitRecordList) {
		this.remarkVisitRecordList = remarkVisitRecordList;
	}
	
}
