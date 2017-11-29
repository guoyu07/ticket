package com.ticket.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.ApplayClass;
import com.ticket.pojo.ChannelCustomerInfo;
import com.ticket.pojo.DataAuthoritys;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.Receipt;
import com.ticket.pojo.SystemUser;
import com.ticket.service.IApplayClassService;
import com.ticket.service.IChannelCustomerInfoService;
import com.ticket.service.IDataAuthoritysService;
import com.ticket.service.IReceiptService;
import com.ticket.util.GeneralUtil;

/**
 * 收据发票控制器
 * @ClassName: ReceiptAction   
 * @Description:  提供收据发票的相关操作方法. 
 * @author HiSay  
 * @date 2015-11-17 17:10:15
 *
 */
public class ReceiptAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//收据发票的业务层
	@Resource private IReceiptService receiptService = null;
	@Resource
	private IApplayClassService applayClassService = null;
	@Resource
	private IChannelCustomerInfoService channelCustomerInfoService = null;
	@Resource IDataAuthoritysService dataAuthoritysService = null;
	//收据发票实体
	private Receipt receipt = null;
	private String id,receiptNo,name,channelCustomerInfoId,employeeInfoId,remarks,auditRemarks,applyClass_id;
    //开具时间
	private Date issueTime = null;
    //开具金额
	private Double amountOfMoney = null;
    //申请类型
	private Integer type,state;
	private List<ChannelCustomerInfo> channelCustomerInfos = null;
	private List<ApplayClass> applayClasses = null;
	private  String money = null;
	
	/**
	 * @author wangjiafeng
	 * 查看发票详细
	 * @method view
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-3-3 下午01:53:54
	 */
	public String view() throws Exception{
		receipt = receiptService.queryById(Receipt.class.getSimpleName(), id);
		return "view";
	}

	/**
	 * @author wangjiafeng
	 * 提交发票
	 * @method submitApply
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-1-6 上午10:45:05
	 */
	public String submitApply() throws Exception{
		Boolean suc = receiptService.changeState(id, 1);
		if(suc){
			data = "success";
		}else{
			data = "failed";
		}
		return TEXT;
	}
	/**
	 * @author wangjiafeng
	 * 审核通过
	 * @method auditPass
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-1-6 上午10:47:30
	 */
	public String auditPass() throws Exception{
		Boolean suc = receiptService.changeState(id, 3);
		if(suc){
			receiptService.updateAuditPerson(id, getSessionAdminUser());
			data = "success";
		}else{
			data = "failed";
		}
		return TEXT;
	}
	
	/**
	 * @author wangjiafeng
	 * 审核未通过
	 * @method auditNoPass
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-1-6 上午10:48:28
	 */
	public String auditNoPass() throws Exception{
		Boolean suc = receiptService.changeState(id, 2);
		if(suc){
			data = "success";
		}else{
			data = "failed";
		}
		return TEXT;
	}
	/**
	 * @author wangjiafeng
	 * 已开具发票或收据
	 * @method stateSuccess
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-1-6 上午10:49:56
	 */
	public String stateSuccess() throws Exception{
		Boolean suc = receiptService.changeState(id, 4);
		if(suc){
			data = "success";
		}else{
			data = "failed";
		}
		return TEXT;
	}

	/**
	 * 添加收据发票
	 * @Title: ReceiptAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			if(GeneralUtil.isNull(amountOfMoney) && GeneralUtil.isNull(channelCustomerInfoId)){
				
				applayClasses = applayClassService.queryList(null);
				channelCustomerInfos = channelCustomerInfoService.queryAllList(null, getSystemEmployeeInfo(), null);
				return "addReceipt";
			}else{
				applayClasses = applayClassService.queryList(null);
				return "addReceipt2";
			}
		} else {
			//非空验证.
			if(GeneralUtil.isNull(issueTime)) {
				data = AjaxData.responseError(getText("issueTime.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(channelCustomerInfoId)) {
				data = AjaxData.responseError(getText("channelCustomerInfoId.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(amountOfMoney)) {
				data = AjaxData.responseError(getText("amountOfMoney.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(employeeInfoId)) {
				data = AjaxData.responseError(getText("employeeInfoId.required"));
				return JSON;
			}
			//保存收据发票实体
			boolean isSuc = receiptService.persist(receiptNo, name, issueTime, channelCustomerInfoId, 
					amountOfMoney, employeeInfoId, type, remarks, applyClass_id,money);
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
	 * 修改收据发票
	 * @Title: ReceiptAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			applayClasses = applayClassService.queryList(null);
			this.setReceipt(receiptService.queryById(Receipt.class.getSimpleName(), id));
			channelCustomerInfos = channelCustomerInfoService
							.queryAllList(null, getSystemEmployeeInfo(), null);
			return "editReceipt";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(issueTime)) {
				data = AjaxData.responseError(getText("issueTime.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(channelCustomerInfoId)) {
				data = AjaxData.responseError(getText("channelCustomerInfoId.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(amountOfMoney)) {
				data = AjaxData.responseError(getText("amountOfMoney.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(employeeInfoId)) {
				data = AjaxData.responseError(getText("employeeInfoId.required"));
				return JSON;
			}
			//修改收据发票实体
			boolean isSuc = receiptService.merge(id, receiptNo, name, issueTime, channelCustomerInfoId,
					amountOfMoney, employeeInfoId, type, remarks,  applyClass_id,money);
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
	 * @author wangjiafeng
	 * 编辑发票号
	 * @method detail
	 * @return
	 * @throws ServiceException
	 * @return String
	 * @date 2016-1-9 下午02:33:34
	 */
	public String detail() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setReceipt(receiptService.queryById(Receipt.class.getSimpleName(), id));
			return "detailReceipt";
		} 
		else{
			//修改收据发票实体
			boolean isSuc = receiptService.merge(id,state,auditRemarks,receiptNo);
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
	 * 管理收据发票实体
	 * @Title: ReceiptAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		applayClasses = applayClassService.queryList(null);
		
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
							this.setPageModule(receiptService.queryAll(info,applyClass_id,null,0, 30));
						}
						if(authority.getContent().equals("departMent")){
							this.setPageModule(receiptService.queryAllWhereInDepartment(info));
						}
						if(authority.getContent().equals("all")){
							this.setPageModule(receiptService.queryAll(null,applyClass_id,null,0, 30));
						}
					}
				}
			}
		}else{
			this.setPageModule(receiptService.queryAll(null,applyClass_id,null,0, 30));
		}
		
		
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
//		this.setPageModule(receiptService.queryAll(getSystemEmployeeInfo(),applyClass_id,null,0, 30));
		this.setPageSize(30);
		return "manageReceipt";
	}
	
	/**
	 * 查看回收站
	 * @Title: ReceiptAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(receiptService.queryAll(getSystemEmployeeInfo(),null,null,1, 30));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleReceipt";
	}
	
	/**
	 * 逻辑删除收据发票对象
	 * @Title: ReceiptAction
	 * @Description: 把收据发票对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = receiptService.logicDeleteEntity(Receipt.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除收据发票对象
	 * @Title: ReceiptAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = receiptService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个收据发票对象
	 * @Title: ReceiptAction
	 * @Description: 从回收站还原收据发票对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = receiptService.restoreEntity(Receipt.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核收据发票对象
	 * @Title: ReceiptAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = receiptService.auditEntity(Receipt.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: ReceiptAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = receiptService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public Receipt getReceipt() {
		return receipt;
	}
	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReceiptNo() {
		return receiptNo;
	}
	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getIssueTime() {
		return issueTime;
	}
	public void setIssueTime(Date issueTime) {
		this.issueTime = issueTime;
	}
	public String getChannelCustomerInfoId() {
		return channelCustomerInfoId;
	}
	public void setChannelCustomerInfoId(String channelCustomerInfoId) {
		this.channelCustomerInfoId = channelCustomerInfoId;
	}
	public Double getAmountOfMoney() {
		return amountOfMoney;
	}
	public void setAmountOfMoney(Double amountOfMoney) {
		this.amountOfMoney = amountOfMoney;
	}
	public String getEmployeeInfoId() {
		return employeeInfoId;
	}
	public void setEmployeeInfoId(String employeeInfoId) {
		this.employeeInfoId = employeeInfoId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getState() {
		return state;
	}
	
	public void setAuditRemarks(String auditRemarks) {
		this.auditRemarks = auditRemarks;
	}

	public String getAuditRemarks() {
		return auditRemarks;
	}

	public List<ChannelCustomerInfo> getChannelCustomerInfos() {
		return channelCustomerInfos;
	}

	public void setChannelCustomerInfos(
			List<ChannelCustomerInfo> channelCustomerInfos) {
		this.channelCustomerInfos = channelCustomerInfos;
	}
	public String getApplyClass_id() {
		return applyClass_id;
	}
	public void setApplyClass_id(String applyClassId) {
		applyClass_id = applyClassId;
	}
	public List<ApplayClass> getApplayClasses() {
		return applayClasses;
	}
	public void setApplayClasses(List<ApplayClass> applayClasses) {
		this.applayClasses = applayClasses;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}
	
}
