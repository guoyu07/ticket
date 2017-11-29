package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.PickUpCustomerApply;
import com.ticket.service.IPickUpCustomerApplyService;
import com.ticket.util.GeneralUtil;

/**
 * 捡单客户申请控制器
 * @ClassName: PickUpCustomerApplyAction   
 * @Description:  提供捡单客户申请的相关操作方法. 
 * @author HiSay  
 * @date 2015-11-24 15:45:34
 *
 */
public class PickUpCustomerApplyAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//捡单客户申请的业务层
	@Resource private IPickUpCustomerApplyService pickUpCustomerApplyService = null;
	//捡单客户申请实体
	private PickUpCustomerApply pickUpCustomerApply = null;
	//主键
	private String id = null;
    //申请人
	private String employee_id = null;
    //客户
	private String customer_id = null;
	
	private String oldemployee_id = null;
  
	//申请状态
	private Integer applyState = null;
	
	/**
	 * 添加捡单客户申请
	 * @Title: PickUpCustomerApplyAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addPickUpCustomerApply";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(employee_id)) {
				data = AjaxData.responseError(getText("employee_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(customer_id)) {
				data = AjaxData.responseError(getText("customer_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(applyState)) {
				data = AjaxData.responseError(getText("applyState.required"));
				return JSON;
			}
			//保存捡单客户申请实体
			boolean isSuc = pickUpCustomerApplyService.persist(employee_id, customer_id, applyState,oldemployee_id, versionFlag);
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
	 * 修改捡单客户申请
	 * @Title: PickUpCustomerApplyAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setPickUpCustomerApply(pickUpCustomerApplyService.queryById(PickUpCustomerApply.class.getSimpleName(), id));
			return "editPickUpCustomerApply";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(employee_id)) {
				data = AjaxData.responseError(getText("employee_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(customer_id)) {
				data = AjaxData.responseError(getText("customer_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(applyState)) {
				data = AjaxData.responseError(getText("applyState.required"));
				return JSON;
			}
			//修改捡单客户申请实体
			boolean isSuc = pickUpCustomerApplyService.merge(id, employee_id, customer_id, applyState,oldemployee_id, versionFlag);
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
	 * 管理捡单客户申请实体
	 * @Title: PickUpCustomerApplyAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(pickUpCustomerApplyService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "managePickUpCustomerApply";
	}
	
	/**
	 * 查看回收站
	 * @Title: PickUpCustomerApplyAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(pickUpCustomerApplyService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recyclePickUpCustomerApply";
	}
	
	/**
	 * 逻辑删除捡单客户申请对象
	 * @Title: PickUpCustomerApplyAction
	 * @Description: 把捡单客户申请对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = pickUpCustomerApplyService.logicDeleteEntity(PickUpCustomerApply.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除捡单客户申请对象
	 * @Title: PickUpCustomerApplyAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = pickUpCustomerApplyService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个捡单客户申请对象
	 * @Title: PickUpCustomerApplyAction
	 * @Description: 从回收站还原捡单客户申请对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = pickUpCustomerApplyService.restoreEntity(PickUpCustomerApply.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	
	/**
	 * 审核通过捡单客户,并将该客户数据转移至新员工
	 * @Title: PickUpCustomerApplyAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String auditPickUp() throws ServiceException {
		
		boolean isSuc  = pickUpCustomerApplyService.TransferData(id,employee_id, customer_id, oldemployee_id);
		//boolean isSuc = pickUpCustomerApplyService.auditEntity(PickUpCustomerApply.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("transferSuccess"));
		} else {
			data = AjaxData.responseError(getText("transferFailed"));
		}
		return JSON;
	}
	
	
	/**
	 * 审核捡单客户申请对象
	 * @Title: PickUpCustomerApplyAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = pickUpCustomerApplyService.auditEntity(PickUpCustomerApply.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: PickUpCustomerApplyAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = pickUpCustomerApplyService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public PickUpCustomerApply getPickUpCustomerApply() {
		return pickUpCustomerApply;
	}
	public void setPickUpCustomerApply(PickUpCustomerApply pickUpCustomerApply) {
		this.pickUpCustomerApply = pickUpCustomerApply;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public Integer getApplyState() {
		return applyState;
	}
	public void setApplyState(Integer applyState) {
		this.applyState = applyState;
	}
    public String getOldemployee_id() {
		return oldemployee_id;
	}

	public void setOldemployee_id(String oldemployeeId) {
		oldemployee_id = oldemployeeId;
	}
}
