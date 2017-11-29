package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.AuditVisitRecord;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.EmployeeOutVisit;
import com.ticket.pojo.PhoneVisit;
import com.ticket.pojo.SystemUser;
import com.ticket.service.IAuditVisitRecordService;
import com.ticket.service.IEmployeeOutVisitService;
import com.ticket.service.IPhoneVisitService;
import com.ticket.util.GeneralUtil;

/**
 * 审核拜访记录控制器
 * @ClassName: AuditVisitRecordAction   
 * @Description:  提供审核拜访记录的相关操作方法. 
 * @author HiSay  
 * @date 2016-04-29 10:39:00
 *
 */
public class AuditVisitRecordAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//审核拜访记录的业务层
	@Resource private IAuditVisitRecordService auditVisitRecordService = null;
	
	@Resource private IPhoneVisitService phoneVisitService = null;//电话拜访记录的业务层
	@Resource private IEmployeeOutVisitService outVisitService = null; //外出拜访记录的业务层
	//审核拜访记录实体
	private AuditVisitRecord auditVisitRecord = null;
	//主键
	private String id = null;
    //记录id
	private String record_id = null;
    //审核员工id
	private String employee_id = null;
    //备注内容
	private String remark = null;
	//电话或者外出拜访
	private String phoneOrOut = null;
	//审核是否通过 1 通过 0 未通过
	private Integer auditState;
	//电话拜访记录实体
	private PhoneVisit phoneVisit = null;
	//外出拜访记录实体
	private EmployeeOutVisit employeeOutVisit = null;
	
	/**
	 * @author wangjiafeng
	 * 获取后台登录的员工
	 * @method getSystemEmployeeInfo
	 * @return
	 * @return EmployeeInfo
	 * @date 2015-12-31 上午10:51:58
	 */
	public EmployeeInfo getSystemEmployeeInfo() {
		if(getSession().get(ContextConstants.SCOPE_SYSTEM_EMPLOYEEINFO) != null){
			return (EmployeeInfo)getSession().get(ContextConstants.SCOPE_SYSTEM_EMPLOYEEINFO);
		}
		if(getSession().get(ContextConstants.SCOPE_SYSTEM_USER) != null) {
			SystemUser systemUser = (SystemUser) getSession().get(ContextConstants.SCOPE_SYSTEM_USER);
			if(systemUser instanceof EmployeeInfo){
				EmployeeInfo employeeInfo = (EmployeeInfo)systemUser;
				getSession().put(ContextConstants.SCOPE_SYSTEM_EMPLOYEEINFO,employeeInfo);
				return employeeInfo;
			}
			return null;
		} else {
			return null;
		}
	}
	/**
	 * 添加审核拜访记录
	 * @Title: AuditVisitRecordAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addAuditVisitRecord";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(record_id)) {
				data = AjaxData.responseError(getText("record_id.required"));
				return JSON;
			}
			if(getSystemEmployeeInfo()==null) {
				data = AjaxData.responseError(getText("employee_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(remark)) {
				data = AjaxData.responseError(getText("remark.required"));
				return JSON;
			}
			//保存审核拜访记录实体
			boolean isSuc = auditVisitRecordService.persist(record_id, getSystemEmployeeInfo().getId(),phoneOrOut, remark,auditState, versionFlag);
			//根据保存结果返回页面
			if(isSuc) {
				if("phone".equals(phoneOrOut)){
					PhoneVisit phoneVisit = phoneVisitService.queryById(PhoneVisit.class.getSimpleName(), record_id);
					phoneVisit.getStatus().setAudit(1);
					phoneVisitService.merge(phoneVisit);
				}
				else if("out".equals(phoneOrOut)){
					EmployeeOutVisit outVisit = outVisitService.queryById(EmployeeOutVisit.class.getSimpleName(), record_id);
					outVisit.getStatus().setAudit(1);
					outVisitService.merge(outVisit);
				}
				data = AjaxData.responseSuccess(getText("addSuccess"));
			} else {
				data = AjaxData.responseError(getText("addFailed"));
			}
			return JSON;
		}
	}
	
	/**
	 * 修改审核拜访记录
	 * @Title: AuditVisitRecordAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setAuditVisitRecord(auditVisitRecordService.queryById(AuditVisitRecord.class.getSimpleName(), id));
			return "editAuditVisitRecord";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(record_id)) {
				data = AjaxData.responseError(getText("record_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(employee_id)) {
				data = AjaxData.responseError(getText("employee_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(remark)) {
				data = AjaxData.responseError(getText("remark.required"));
				return JSON;
			}
			//修改审核拜访记录实体
			boolean isSuc = auditVisitRecordService.merge(id, record_id, employee_id, remark,  versionFlag);
			//根据修改结果返回页面
			if(isSuc) {
				data = AjaxData.responseSuccess(getText("editSuccess"));
			} else {
				data = AjaxData.responseError(getText("editFailed"));
			}
			return JSON;
		}
	}
	
	/**
	 * 管理审核拜访记录实体
	 * @Title: AuditVisitRecordAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		if(GeneralUtil.isNotNull(phoneOrOut)){
			if("phone".equals(phoneOrOut)){
				this.setPageModule(auditVisitRecordService.queryByPhone(auditState,ContextConstants.ADMIN_COMMON_PAGE_SIZE,versionFlag));
				
			}else if("out".equals(phoneOrOut)){
				this.setPageModule(auditVisitRecordService.queryByOut(auditState,ContextConstants.ADMIN_COMMON_PAGE_SIZE,versionFlag));
				
			}
		}
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageAuditVisitRecord";
	}
	
	/**
	 * 查看回收站
	 * @Title: AuditVisitRecordAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(auditVisitRecordService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleAuditVisitRecord";
	}
	
	/**
	 * 逻辑删除审核拜访记录对象
	 * @Title: AuditVisitRecordAction
	 * @Description: 把审核拜访记录对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = auditVisitRecordService.logicDeleteEntity(AuditVisitRecord.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除审核拜访记录对象
	 * @Title: AuditVisitRecordAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = auditVisitRecordService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个审核拜访记录对象
	 * @Title: AuditVisitRecordAction
	 * @Description: 从回收站还原审核拜访记录对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = auditVisitRecordService.restoreEntity(AuditVisitRecord.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核审核拜访记录对象
	 * @Title: AuditVisitRecordAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = auditVisitRecordService.auditEntity(AuditVisitRecord.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: AuditVisitRecordAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = auditVisitRecordService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	/**
	 * 查看回访详情
	 * @return
	 * @throws ServiceException
	 */
	public String showDetail() throws ServiceException{
		if("phone".equals(phoneOrOut)){
			this.setPhoneVisit(phoneVisitService.queryById(PhoneVisit.class.getSimpleName(), record_id));
		}else{
			this.setEmployeeOutVisit(outVisitService.queryById(EmployeeOutVisit.class.getSimpleName(), record_id));
		}
		this.setAuditVisitRecord(auditVisitRecordService.queryById(AuditVisitRecord.class.getSimpleName(), id));
		return "showDetail";
	}
	
	public AuditVisitRecord getAuditVisitRecord() {
		return auditVisitRecord;
	}
	public void setAuditVisitRecord(AuditVisitRecord auditVisitRecord) {
		this.auditVisitRecord = auditVisitRecord;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRecord_id() {
		return record_id;
	}
	public void setRecord_id(String record_id) {
		this.record_id = record_id;
	}
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPhoneOrOut() {
		return phoneOrOut;
	}
	public void setPhoneOrOut(String phoneOrOut) {
		this.phoneOrOut = phoneOrOut;
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
	public Integer getAuditState() {
		return auditState;
	}
	public void setAuditState(Integer auditState) {
		this.auditState = auditState;
	}
	public PhoneVisit getPhoneVisit() {
		return phoneVisit;
	}
	public void setPhoneVisit(PhoneVisit phoneVisit) {
		this.phoneVisit = phoneVisit;
	}
	public EmployeeOutVisit getEmployeeOutVisit() {
		return employeeOutVisit;
	}
	public void setEmployeeOutVisit(EmployeeOutVisit employeeOutVisit) {
		this.employeeOutVisit = employeeOutVisit;
	}
}
