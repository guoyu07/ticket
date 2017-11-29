package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.CustomerTransferLog;
import com.ticket.service.ICustomerTransferLogService;
import com.ticket.util.GeneralUtil;

/**
 * 客户转让日志控制器
 * @ClassName: CustomerTransferLogAction   
 * @Description:  提供客户转让日志的相关操作方法. 
 * @author HiSay  
 * @date 2016-04-27 15:04:28
 *
 */
public class CustomerTransferLogAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//客户转让日志的业务层
	@Resource private ICustomerTransferLogService customerTransferLogService = null;
	//客户转让日志实体
	private CustomerTransferLog customerTransferLog = null;
	//主键
	private String id = null;
    //转让前员工
	private String beforeEmployee_id = null;
    //转让后员工
	private String afterEmployee_id = null;
    //操作员ID
	private String operator_id = null;
    //客户ID
	private String customer_id = null;
    //备注
	private String remark = null;
	
	/**
	 * 添加客户转让日志
	 * @Title: CustomerTransferLogAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addCustomerTransferLog";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(beforeEmployee_id)) {
				data = AjaxData.responseError(getText("beforeEmployee_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(afterEmployee_id)) {
				data = AjaxData.responseError(getText("afterEmployee_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(operator_id)) {
				data = AjaxData.responseError(getText("operator_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(customer_id)) {
				data = AjaxData.responseError(getText("customer_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(remark)) {
				data = AjaxData.responseError(getText("remark.required"));
				return JSON;
			}
			//保存客户转让日志实体
			boolean isSuc = customerTransferLogService.persist(beforeEmployee_id, afterEmployee_id, operator_id, customer_id, remark, versionFlag);
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
	 * 修改客户转让日志
	 * @Title: CustomerTransferLogAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setCustomerTransferLog(customerTransferLogService.queryById(CustomerTransferLog.class.getSimpleName(), id));
			return "editCustomerTransferLog";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(beforeEmployee_id)) {
				data = AjaxData.responseError(getText("beforeEmployee_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(afterEmployee_id)) {
				data = AjaxData.responseError(getText("afterEmployee_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(operator_id)) {
				data = AjaxData.responseError(getText("operator_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(customer_id)) {
				data = AjaxData.responseError(getText("customer_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(remark)) {
				data = AjaxData.responseError(getText("remark.required"));
				return JSON;
			}
			//修改客户转让日志实体
			boolean isSuc = customerTransferLogService.merge(id, beforeEmployee_id, afterEmployee_id, operator_id, customer_id, remark,  versionFlag);
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
	 * 管理客户转让日志实体
	 * @Title: CustomerTransferLogAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(customerTransferLogService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageCustomerTransferLog";
	}
	
	/**
	 * 查看回收站
	 * @Title: CustomerTransferLogAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(customerTransferLogService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleCustomerTransferLog";
	}
	
	/**
	 * 逻辑删除客户转让日志对象
	 * @Title: CustomerTransferLogAction
	 * @Description: 把客户转让日志对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = customerTransferLogService.logicDeleteEntity(CustomerTransferLog.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除客户转让日志对象
	 * @Title: CustomerTransferLogAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = customerTransferLogService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个客户转让日志对象
	 * @Title: CustomerTransferLogAction
	 * @Description: 从回收站还原客户转让日志对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = customerTransferLogService.restoreEntity(CustomerTransferLog.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核客户转让日志对象
	 * @Title: CustomerTransferLogAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = customerTransferLogService.auditEntity(CustomerTransferLog.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: CustomerTransferLogAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = customerTransferLogService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public CustomerTransferLog getCustomerTransferLog() {
		return customerTransferLog;
	}
	public void setCustomerTransferLog(CustomerTransferLog customerTransferLog) {
		this.customerTransferLog = customerTransferLog;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBeforeEmployee_id() {
		return beforeEmployee_id;
	}
	public void setBeforeEmployee_id(String beforeEmployee_id) {
		this.beforeEmployee_id = beforeEmployee_id;
	}
	public String getAfterEmployee_id() {
		return afterEmployee_id;
	}
	public void setAfterEmployee_id(String afterEmployee_id) {
		this.afterEmployee_id = afterEmployee_id;
	}
	public String getOperator_id() {
		return operator_id;
	}
	public void setOperator_id(String operator_id) {
		this.operator_id = operator_id;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
