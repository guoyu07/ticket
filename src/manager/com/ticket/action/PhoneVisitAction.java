package com.ticket.action;

import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.ChannelCustomerInfo;
import com.ticket.pojo.DataAuthoritys;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.PhoneVisit;
import com.ticket.pojo.RemarkVisitRecord;
import com.ticket.pojo.SystemUser;
import com.ticket.service.IChannelCustomerInfoService;
import com.ticket.service.IDataAuthoritysService;
import com.ticket.service.IPhoneVisitService;
import com.ticket.service.IRemarkVisitRecordService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 电话回访记录控制器
 * @ClassName: PhoneVisitAction   
 * @Description:  提供电话回访记录的相关操作方法. 
 * @author HiSay  
 * @date 2015-11-02 23:14:13
 *
 */
public class PhoneVisitAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//电话回访记录的业务层
	@Resource private IPhoneVisitService phoneVisitService = null;
	@Resource
	 private IChannelCustomerInfoService channelCustomerInfoService = null;
	@Resource private ISystemOperationLogService logService = null;
	@Resource private IDataAuthoritysService dataAuthoritysService = null;
	/**
	 * 拜访记录的备注业务层
	 */
	@Resource private IRemarkVisitRecordService remarkVisitRecordService = null;
	//电话回访记录实体
	private PhoneVisit phoneVisit = null;
	//主键
	private String id,visitDate,contact,contactPhone,content;
	private ChannelCustomerInfo channelCustomerInfo = null;
	
	//客户id
	private String customer_id = null;
	//备注列表
	private List<RemarkVisitRecord> remarkVisitRecordList = null;
	private Integer state = null;
	/**
	 * @author wangjiafeng
	 * 获取详细
	 * @method view
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-3-3 下午04:15:51
	 */
	public String view() throws Exception{
		phoneVisit = phoneVisitService.queryById(PhoneVisit.class.getSimpleName(), id);
		List<RemarkVisitRecord> list = remarkVisitRecordService.queryListByRecordId(id,versionFlag);
		this.setRemarkVisitRecordList(list);
		return "view";
	}
	
	/**
	 * 添加电话回访记录
	 * @Title: PhoneVisitAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			channelCustomerInfo = channelCustomerInfoService
				.queryById(ChannelCustomerInfo.class.getSimpleName(), id);
			return "addPhoneVisit";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(visitDate)) {
				data = AjaxData.responseError(getText("visitDate.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(customer_id)) {
				data = AjaxData.responseError(getText("companyName.required"));
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
			if(GeneralUtil.isNull(content)) {
				data = AjaxData.responseError(getText("content.required"));
				return JSON;
			}
			//保存电话回访记录实体
			boolean isSuc = phoneVisitService.persist(visitDate, getSystemEmployeeInfo(), customer_id, 
					contact, contactPhone, content);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增电话拜访记录";
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
	 * 修改电话回访记录
	 * @Title: PhoneVisitAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setPhoneVisit(phoneVisitService.queryById(PhoneVisit.class.getSimpleName(), id));
			return "editPhoneVisit";
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
				data = AjaxData.responseError(getText("companyName.required"));
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
			if(GeneralUtil.isNull(content)) {
				data = AjaxData.responseError(getText("content.required"));
				return JSON;
			}
			//修改电话回访记录实体
			boolean isSuc = phoneVisitService.merge(id, visitDate,customer_id, contact, contactPhone, content);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改电话拜访记录";
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
	 * 管理电话回访记录实体
	 * @Title: PhoneVisitAction
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
							this.setPageModule(phoneVisitService.queryEntityByEmployeeId(versionFlag, 30, info.getId(),state));
						}
						if(authority.getContent().equals("departMent")){
							this.setPageModule(phoneVisitService.queryAllWhereInDepartment(info,state));
						}
						if(authority.getContent().equals("all")){
							this.setPageModule(phoneVisitService.queryAll(null, 0, 30,state));
						}
					}
				}
			}
		}else{
			this.setPageModule(phoneVisitService.queryAll(null, 0, 30,state));
		}
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		/*if(GeneralUtil.isNotNull(customer_id)){
			this.setPageModule(phoneVisitService.queryByCustomerId(customer_id,ContextConstants.ADMIN_COMMON_PAGE_SIZE,versionFlag));
			this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		}*/
		return "managePhoneVisit";
	}
	
	/**
	 * 查看回收站
	 * @Title: PhoneVisitAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(phoneVisitService.queryAll(getSystemEmployeeInfo(), 1, ContextConstants.ADMIN_COMMON_PAGE_SIZE,2));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recyclePhoneVisit";
	}
	
	/**
	 * 逻辑删除电话回访记录对象
	 * @Title: PhoneVisitAction
	 * @Description: 把电话回访记录对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = phoneVisitService.logicDeleteEntity(PhoneVisit.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除电话拜访记录";
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
	 * 物理删除电话回访记录对象
	 * @Title: PhoneVisitAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = phoneVisitService.remove(id);
		if(isSuc) {
			String logContent = "物理删除电话拜访记录";
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
	 * 还原一个电话回访记录对象
	 * @Title: PhoneVisitAction
	 * @Description: 从回收站还原电话回访记录对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = phoneVisitService.restoreEntity(PhoneVisit.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原电话拜访记录";
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
	 * 审核电话回访记录对象
	 * @Title: PhoneVisitAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = phoneVisitService.auditEntity(PhoneVisit.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核电话拜访记录";
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
	 * @Title: PhoneVisitAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = phoneVisitService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作电话拜访记录";
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
	 * 批量彻底删除员工电话拜访记录实体
	 * @return
	 * @throws ServiceException
	 */
	public String batchORealDelete() throws ServiceException {
		boolean isSuc = phoneVisitService.batchRealDelete(idsValue,versionFlag);
		if(isSuc) {
			String logContent = "批量删除电话拜访记录";
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
	
	public PhoneVisit getPhoneVisit() {
		return phoneVisit;
	}
	public void setPhoneVisit(PhoneVisit phoneVisit) {
		this.phoneVisit = phoneVisit;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
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
