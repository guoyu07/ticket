package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.RemarkVisitRecord;
import com.ticket.pojo.SystemUser;
import com.ticket.service.IRemarkVisitRecordService;
import com.ticket.util.GeneralUtil;

/**
 * 备注拜访记录控制器
 * @ClassName: RemarkVisitRecordAction   
 * @Description:  提供备注拜访记录的相关操作方法. 
 * @author HiSay  
 * @date 2016-04-28 15:38:02
 *
 */
public class RemarkVisitRecordAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//备注拜访记录的业务层
	@Resource private IRemarkVisitRecordService remarkVisitRecordService = null;
	//备注拜访记录实体
	private RemarkVisitRecord remarkVisitRecord = null;
	//主键
	private String id = null;
    //记录id
	private String record_id = null;
    //员工id
	private String employee_id = null;
    //备注内容
	private String remark = null;
	//客户id
	private String customer_id = null;
	//备注电话拜访或者是外出拜访
	private String phoneOrOut = null;
	
	
	/**
	 * 添加备注拜访记录
	 * @Title: RemarkVisitRecordAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addRemarkVisitRecord";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(record_id)) {
				data = AjaxData.responseError(getText("record_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(remark)) {
				data = AjaxData.responseError(getText("remark.required"));
				return JSON;
			}
			if(getSystemEmployeeInfo()==null) {
				data = AjaxData.responseError(getText("employee_id.required"));
				return JSON;
			}else{
				employee_id = getSystemEmployeeInfo().getId();
			}
			//保存备注拜访记录实体
			boolean isSuc = remarkVisitRecordService.persist(record_id, employee_id, remark, versionFlag);
			//根据保存结果返回页面
			if(isSuc) {
				data = AjaxData.responseSuccess(getText("addSuccess"));
			} else {
				data = AjaxData.responseError(getText("addFailed"));
			}
			return JSON;
		}
	}
	
	/**
	 * 修改备注拜访记录
	 * @Title: RemarkVisitRecordAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setRemarkVisitRecord(remarkVisitRecordService.queryById(RemarkVisitRecord.class.getSimpleName(), id));
			return "editRemarkVisitRecord";
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
			//修改备注拜访记录实体
			boolean isSuc = remarkVisitRecordService.merge(id, record_id, employee_id, remark,  versionFlag);
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
	 * 管理备注拜访记录实体
	 * @Title: RemarkVisitRecordAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		if(GeneralUtil.isNotNull(record_id)){
			this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
			this.setPageModule(remarkVisitRecordService.queryEntityByRecordId(record_id,versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
			this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		}
		return "manageRemarkVisitRecord";
	}
	
	/**
	 * 查看回收站
	 * @Title: RemarkVisitRecordAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(remarkVisitRecordService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleRemarkVisitRecord";
	}
	
	/**
	 * 逻辑删除备注拜访记录对象
	 * @Title: RemarkVisitRecordAction
	 * @Description: 把备注拜访记录对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = remarkVisitRecordService.logicDeleteEntity(RemarkVisitRecord.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除备注拜访记录对象
	 * @Title: RemarkVisitRecordAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = remarkVisitRecordService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个备注拜访记录对象
	 * @Title: RemarkVisitRecordAction
	 * @Description: 从回收站还原备注拜访记录对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = remarkVisitRecordService.restoreEntity(RemarkVisitRecord.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核备注拜访记录对象
	 * @Title: RemarkVisitRecordAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = remarkVisitRecordService.auditEntity(RemarkVisitRecord.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: RemarkVisitRecordAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = remarkVisitRecordService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
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
	 * 查看备注详情
	 * @return
	 * @throws ServiceException
	 */
	public String showDetail() throws ServiceException{
		this.setRemarkVisitRecord(remarkVisitRecordService.queryById(RemarkVisitRecord.class.getSimpleName(), id));
		return "remarkVisitRecordDetail";
	}
	public RemarkVisitRecord getRemarkVisitRecord() {
		return remarkVisitRecord;
	}
	public void setRemarkVisitRecord(RemarkVisitRecord remarkVisitRecord) {
		this.remarkVisitRecord = remarkVisitRecord;
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

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getPhoneOrOut() {
		return phoneOrOut;
	}

	public void setPhoneOrOut(String phoneOrOut) {
		this.phoneOrOut = phoneOrOut;
	}
}
