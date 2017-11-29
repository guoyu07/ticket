package com.ticket.action;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.ChannelCustomerInfo;
import com.ticket.pojo.ChannelType;
import com.ticket.pojo.DataAuthoritys;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.FavouredPolicy;
import com.ticket.pojo.RechargeRecord;
import com.ticket.pojo.SystemDictionary;
import com.ticket.pojo.SystemUser;
import com.ticket.service.IChannelCustomerInfoService;
import com.ticket.service.IChannelTypeService;
import com.ticket.service.IDataAuthoritysService;
import com.ticket.service.IDepartmentInfoService;
import com.ticket.service.IEmployeeInfoService;
import com.ticket.service.IFavouredPolicyService;
import com.ticket.service.IRechargeRecordService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

import net.sf.json.JSONArray;

/**
 * 充值记录控制器
 * @ClassName: RechargeRecordAction   
 * @Description:  提供充值记录的相关操作方法. 
 * @author HiSay  
 * @date 2015-11-13 16:45:25
 *
 */
public class RechargeRecordAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//充值记录的业务层
	@Resource 
	private IRechargeRecordService rechargeRecordService;
	//渠道用户的业务层
	@Resource 
	private IChannelCustomerInfoService channelCustomerInfoService;
	@Resource 
	private IFavouredPolicyService favouredPolicyService;
	@Resource
	private IChannelTypeService channelTypeService;
	@Resource
	private IEmployeeInfoService employeeInfoService;
	@Resource
	private IDepartmentInfoService departmentInfoService;
	@Resource
	private IDataAuthoritysService dataAuthoritysService;
	@Resource
	private ISystemDictionaryService systemDictionaryService = null;
	
	
	//充值记录实体
	private RechargeRecord rechargeRecord;
	//主键
	private String id,recordNo,channelCustomerInfoId,payWay,receiptNo,keyword;
    //充值金额
	private Double amountOfMoney;
    //支付时间
	private Date payTime;
	private List<ChannelCustomerInfo> channelCustomerInfos;
	private ChannelCustomerInfo channelCustomerInfo;
	private FavouredPolicy favouredPolicy;
	private ChannelType channelType;
	
	private List<?> list;
	private Date startDate, endDate;
	
	/**
	 * 生成汇总报表
	 * @return
	 */
	public String togetherReport(){
		String method = this.getClass().getSimpleName() + "_" + new Exception().getStackTrace()[0].getMethodName();
		
		Object[] list = new Object[4];
		
		SystemUser user = (SystemUser)ActionContext.getContext().getSession().get(ContextConstants.SCOPE_SYSTEM_USER);
		if(user instanceof EmployeeInfo){
			EmployeeInfo info = (EmployeeInfo) user;
			
			List<DataAuthoritys> authoritys = dataAuthoritysService.queryByEmployeeId(info.getId());
			if(authoritys != null){
				for(DataAuthoritys authority:authoritys){
					if(authority.getInMethod().equals(method)){
						if(authority.getContent().equals("one")){
							long count = rechargeRecordService.customCount(startDate, endDate,info,0);
							list[0] = count;
							double count2 = rechargeRecordService.moneyAmount(startDate, endDate,info,0);
							list[1] = count2;
							count = rechargeRecordService.ticketCount(startDate, endDate,info,0);
							list[2] = count;
							count2 = rechargeRecordService.moneyAmountForTicket(startDate, endDate,info,0);
							list[3] = count2;
							ActionContext.getContext().put("count", list);
							return "togetherReport";
						}
						if(authority.getContent().equals("departMent")){
							long count = rechargeRecordService.customCount(startDate, endDate,null,1);
							list[0] = count;
							double count2 = rechargeRecordService.moneyAmount(startDate, endDate,null,1);
							list[1] = count2;
							count = rechargeRecordService.ticketCount(startDate, endDate,null,1);
							list[2] = count;
							count2 = rechargeRecordService.moneyAmountForTicket(startDate, endDate,null,1);
							list[3] = count2;
							ActionContext.getContext().put("count", list);
							return "togetherReport";
						}
						if(authority.getContent().equals("all")){
							long count = rechargeRecordService.customCount(startDate, endDate,null,2);
							list[0] = count;
							double count2 = rechargeRecordService.moneyAmount(startDate, endDate,null,2);
							list[1] = count2;
							count = rechargeRecordService.ticketCount(startDate, endDate,null,2);
							list[2] = count;
							count2 = rechargeRecordService.moneyAmountForTicket(startDate, endDate,null,2);
							list[3] = count2;
							ActionContext.getContext().put("count", list);
							return "togetherReport";
						}
					}
				}
			}
		}else{
			long count = rechargeRecordService.customCount(startDate, endDate,null,2);
			list[0] = count;
			double count2 = rechargeRecordService.moneyAmount(startDate, endDate,null,2);
			list[1] = count2;
			count = rechargeRecordService.ticketCount(startDate, endDate,null,2);
			list[2] = count;
			count2 = rechargeRecordService.moneyAmountForTicket(startDate, endDate,null,2);
			list[3] = count2;
		}
		ActionContext.getContext().put("count", list);
		return "togetherReport";
	}
	
	/**
	 * 生成细节报表
	 * @return
	 */
	public String detailsReport(){
		
		if("all".equals(keyword)){
			
			list = rechargeRecordService.allList(startDate, endDate);
		}else if("ticket".equals(keyword)){
			
			list = rechargeRecordService.ticketList(startDate, endDate);
		}
		return "detailsReport";
	}
	
	/**
	 * @author wangjiafeng
	 * 同意开票
	 * @method tykp
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-3-3 下午02:52:54
	 */
	public String tykp() throws Exception{
		rechargeRecord = rechargeRecordService.queryById(RechargeRecord.class.getSimpleName(), id);
		if(GeneralUtil.isNotNull(rechargeRecord.getState()) && rechargeRecord.getState() == 2){
			rechargeRecord.setState(1);
			rechargeRecordService.merge(rechargeRecord);
		}
		data = "success";
		return TEXT;
	}
	
	/**
	 * @author wangjiafeng
	 * 优惠政策详细
	 * @method detail
	 * @return
	 * @throws ServiceException
	 * @return String
	 * @date 2016-1-9 下午04:49:56
	 */
	public String detail() throws ServiceException{
		channelCustomerInfo  = channelCustomerInfoService
				.queryById(ChannelCustomerInfo.class.getSimpleName(), id);
		if(channelCustomerInfo != null){
			if(GeneralUtil.isNotNull(channelCustomerInfo.getFavouredPolicy())){
				favouredPolicy = channelCustomerInfo.getFavouredPolicy();
			}
			channelType = channelCustomerInfo.getChannelType();
		}
		return "detail";
	}
	
	/**
	 * @author wangjiafeng
	 * 获取渠道客户列表
	 * @method getChannelCustomerInfoList
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-1-3 下午05:22:28
	 */
	public String getChannelCustomerInfoList() throws Exception{
		if(getSystemEmployeeInfo() == null){
			data = "";
			return TEXT;
		}
		if(GeneralUtil.isNotNull(keyword)){
			keyword = DecoderUtil.UtfDecoder(keyword);
		}
		channelCustomerInfos = channelCustomerInfoService.queryAllList(keyword,null);
		data = JSONArray.fromObject(channelCustomerInfos).toString();
		return TEXT;
	}
	
	/**
	 * @author wangjiafeng
	 * 充值
	 * @method add
	 * @return
	 * @throws ServiceException
	 * @return String
	 * @date 2016-1-6 下午02:44:51
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			List<SystemDictionary> dicts = systemDictionaryService.querySubByParentName("支付方式");
			channelCustomerInfos = channelCustomerInfoService.queryAllList(null, 20);
			ActionContext.getContext().put("payWay", dicts);
			return "addRechargeRecord";
		} else {
			//非空验证.
			
			if(GeneralUtil.isNull(amountOfMoney)) {
				data = AjaxData.responseError(getText("amountOfMoney.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(payWay)) {
				data = AjaxData.responseError(getText("payWay.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(channelCustomerInfoId)) {
				data = AjaxData.responseError("请选择充值客户");
				return JSON;
			}
			Date date=new Date();
			SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
			StringBuffer str = new StringBuffer(format.format(date).toString());
			recordNo ="000"+ str.toString();
			receiptNo ="000";
			//保存充值记录实体
			boolean isSuc = rechargeRecordService.persist(recordNo, amountOfMoney, channelCustomerInfoId,
					payTime, payWay, receiptNo, getSessionAdminUser());
			
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
	 * 修改充值记录
	 * @Title: RechargeRecordAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setRechargeRecord(rechargeRecordService.queryById(RechargeRecord.class.getSimpleName(), id));
			return "editRechargeRecord";
		} else {
			//非空验证.
			
			if(GeneralUtil.isNull(amountOfMoney)) {
				data = AjaxData.responseError(getText("amountOfMoney.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(payWay)) {
				data = AjaxData.responseError(getText("payWay.required"));
				return JSON;
			}
			//修改充值记录实体
			boolean isSuc = rechargeRecordService.merge(id, recordNo, amountOfMoney, channelCustomerInfoId, payTime, payWay, receiptNo,  versionFlag);
			//根据修改结果返回页面
			if(isSuc) {
				ChannelCustomerInfo cc =  channelCustomerInfoService.queryById(ChannelCustomerInfo.class.getName(), channelCustomerInfoId);
				cc.setBalance(cc.getBalance()+amountOfMoney);
				channelCustomerInfoService.merge(cc);
				data = AjaxData.responseSuccess(getText("editSuccess"));
			} else {
				data = AjaxData.responseError(getText("editFailed"));
			}
			return JSON;
		}
	}
	
	/**
	 * @throws UnsupportedEncodingException 
	 * 管理充值记录实体
	 * @Title: RechargeRecordAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException, UnsupportedEncodingException {
		if(GeneralUtil.isNull(keyword)){
			keyword = "";
		}
		String method = this.getClass().getSimpleName() + "_" + new Exception().getStackTrace()[0].getMethodName();
		
		SystemUser user = (SystemUser)ActionContext.getContext().getSession().get(ContextConstants.SCOPE_SYSTEM_USER);
		if(user instanceof EmployeeInfo){
			EmployeeInfo info = (EmployeeInfo) user;
			List<DataAuthoritys> authoritys = dataAuthoritysService.queryByEmployeeId(info.getId());
			if(authoritys != null){
				for(DataAuthoritys authority:authoritys){
					if(authority.getInMethod().equals(method)){
						if(authority.getContent().equals("one")){
							this.setPageModule(rechargeRecordService.queryPageModuleByCustomerId(ContextConstants.ADMIN_COMMON_PAGE_SIZE, null, info.getId(), versionFlag,keyword));
						}
						if(authority.getContent().equals("departMent")){
							this.setPageModule(rechargeRecordService.queryAllWhereIndepartment(info,keyword));
						}
						if(authority.getContent().equals("all")){
							this.setPageModule(rechargeRecordService.queryPageModuleByCustomerId(ContextConstants.ADMIN_COMMON_PAGE_SIZE, null, null, versionFlag,keyword));
						}
					}
				}
			}
		}else if(user instanceof ChannelCustomerInfo){
			this.setPageModule(rechargeRecordService.queryPageModuleByCustomerId(ContextConstants.ADMIN_COMMON_PAGE_SIZE, user.getId(),null, versionFlag,keyword));
		}else{
			this.setPageModule(rechargeRecordService.queryPageModuleByCustomerId(ContextConstants.ADMIN_COMMON_PAGE_SIZE, null,null, versionFlag,keyword));
		}
		
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageRechargeRecord";
	}
	
	
	/**
	 * 查看回收站
	 * @Title: RechargeRecordAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(rechargeRecordService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleRechargeRecord";
	}
	
	/**
	 * 逻辑删除充值记录对象
	 * @Title: RechargeRecordAction
	 * @Description: 把充值记录对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = rechargeRecordService.logicDeleteEntity(RechargeRecord.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除充值记录对象
	 * @Title: RechargeRecordAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = rechargeRecordService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个充值记录对象
	 * @Title: RechargeRecordAction
	 * @Description: 从回收站还原充值记录对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = rechargeRecordService.restoreEntity(RechargeRecord.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核充值记录对象
	 * @Title: RechargeRecordAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = rechargeRecordService.auditEntity(RechargeRecord.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: RechargeRecordAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = rechargeRecordService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public RechargeRecord getRechargeRecord() {
		return rechargeRecord;
	}
	public void setRechargeRecord(RechargeRecord rechargeRecord) {
		this.rechargeRecord = rechargeRecord;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRecordNo() {
		return recordNo;
	}
	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
	}
	public Double getAmountOfMoney() {
		return amountOfMoney;
	}
	public void setAmountOfMoney(Double amountOfMoney) {
		this.amountOfMoney = amountOfMoney;
	}
	public String getChannelCustomerInfoId() {
		return channelCustomerInfoId;
	}
	public void setChannelCustomerInfoId(String channelCustomerInfoId) {
		this.channelCustomerInfoId = channelCustomerInfoId;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public String getPayWay() {
		return payWay;
	}
	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
	public String getReceiptNo() {
		return receiptNo;
	}
	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public List<ChannelCustomerInfo> getChannelCustomerInfos() {
		return channelCustomerInfos;
	}

	public void setChannelCustomerInfos(
			List<ChannelCustomerInfo> channelCustomerInfos) {
		this.channelCustomerInfos = channelCustomerInfos;
	}

	public ChannelCustomerInfo getChannelCustomerInfo() {
		return channelCustomerInfo;
	}

	public void setChannelCustomerInfo(ChannelCustomerInfo channelCustomerInfo) {
		this.channelCustomerInfo = channelCustomerInfo;
	}

	public FavouredPolicy getFavouredPolicy() {
		return favouredPolicy;
	}

	public void setFavouredPolicy(FavouredPolicy favouredPolicy) {
		this.favouredPolicy = favouredPolicy;
	}

	public ChannelType getChannelType() {
		return channelType;
	}

	public void setChannelType(ChannelType channelType) {
		this.channelType = channelType;
	}

	public IRechargeRecordService getRechargeRecordService() {
		return rechargeRecordService;
	}

	public void setRechargeRecordService(
			IRechargeRecordService rechargeRecordService) {
		this.rechargeRecordService = rechargeRecordService;
	}

	public IChannelCustomerInfoService getChannelCustomerInfoService() {
		return channelCustomerInfoService;
	}

	public void setChannelCustomerInfoService(
			IChannelCustomerInfoService channelCustomerInfoService) {
		this.channelCustomerInfoService = channelCustomerInfoService;
	}

	public IFavouredPolicyService getFavouredPolicyService() {
		return favouredPolicyService;
	}

	public void setFavouredPolicyService(
			IFavouredPolicyService favouredPolicyService) {
		this.favouredPolicyService = favouredPolicyService;
	}

	public IChannelTypeService getChannelTypeService() {
		return channelTypeService;
	}

	public void setChannelTypeService(IChannelTypeService channelTypeService) {
		this.channelTypeService = channelTypeService;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	
}
