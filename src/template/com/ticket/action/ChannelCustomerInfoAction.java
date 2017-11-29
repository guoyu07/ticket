package com.ticket.action;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.ChannelCustomerInfo;
import com.ticket.pojo.ChannelPosition;
import com.ticket.pojo.ChannelType;
import com.ticket.pojo.CustomerProtectLog;
import com.ticket.pojo.CustomerTransferLog;
import com.ticket.pojo.DataAuthoritys;
import com.ticket.pojo.DepartmentInfo;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.EmployeeOutVisit;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.PhoneVisit;
import com.ticket.pojo.SystemUser;
import com.ticket.service.IChannelCustomerInfoService;
import com.ticket.service.IChannelPositionService;
import com.ticket.service.IChannelTypeService;
import com.ticket.service.ICustomerProtectLogService;
import com.ticket.service.ICustomerTransferLogService;
import com.ticket.service.IDataAuthoritysService;
import com.ticket.service.IDepartmentInfoService;
import com.ticket.service.IEmployeeInfoService;
import com.ticket.service.IEmployeeOutVisitService;
import com.ticket.service.IIndustryService;
import com.ticket.service.IPhoneVisitService;
import com.ticket.service.IPickUpCustomerApplyService;
import com.ticket.util.DateUtil;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 渠道客户信息控制器
 * @ClassName: ChannelCustomerInfoAction   
 * @Description:  提供渠道客户信息的相关操作方法. 
 * @author HiSay  
 * @date 2015-11-04 10:39:40
 */
public class ChannelCustomerInfoAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//渠道客户信息的业务层
	@Resource 
	private IChannelCustomerInfoService channelCustomerInfoService;
	//渠道客户信息的业务层
	@Resource private ICustomerProtectLogService customerProtectLogService = null;
	//捡单客户申请的业务层
	@Resource 
	private  IPickUpCustomerApplyService  pickUpCustomerApplyService= null;
	@Resource 
	private IIndustryService industryService;
	@Resource 
	private ICustomerTransferLogService customerTransferLogService;
	//客户的电话拜访记录业务层
	@Resource 
	private IPhoneVisitService phoneVisitService;
	//客户的外出拜访业务层
	@Resource 
	private IEmployeeOutVisitService employeeOutVisitService;
	//员工业务层
	@Resource 
	private IEmployeeInfoService employeeInfoService;
	@Resource
	private IChannelPositionService channelPositionService;
	//渠道客户信息实体
	private ChannelCustomerInfo channelCustomerInfo;
	//部门业务层
	@Resource private IDepartmentInfoService departmentInfoService = null;
	@Resource private IChannelTypeService channelTypeService = null;//渠道分类业务层
	@Resource private IDataAuthoritysService dataAuthoritysService = null;
	public IDepartmentInfoService getDepartmentInfoService() {
		return departmentInfoService;
	}

	public void setDepartmentInfoService(
			IDepartmentInfoService departmentInfoService) {
		this.departmentInfoService = departmentInfoService;
	}

	//主键
	private String id,name,loginId,password,channelTypeId,contact,contactPhone,payWay,keyword
			,oldPassword,repassword,favouredPolicyId,industry,email,employee_id,industry_id,industryHtml,
			channelPosition_id,idCard,yyzzNumber;
	private Date openAccountDate;
	private Double openAccountMoney,balance;
	private Integer signState= null;
	private List<ChannelPosition> channelPositions ;
	
	/**
	 * 渠道客户状态
	 * 0 意向客户
	 * 1 渠道客户
	 */
	private Integer state;
	
	/**
	 * 客户ID
	 */
	private String customer_id;
	
	/**
	 * 转让前员工ID
	 */
	private String beforeEmployee_id;
	
	/**
	 * 转让后员工ID
	 */
	private String afterEmployee_id;
	
	/**
	 * 员工意向客户列表
	 */
	private List<ChannelCustomerInfo> employeeIntentionCustomerList;
	
	/**
	 * 员工渠道客户列表
	 */
	private List<ChannelCustomerInfo> employeeChannelCustomerList;
	
	/**
	 * 所有客户列表
	 */
	private List<ChannelCustomerInfo> channelCustomerInfos;
	
	/**
	 * 开始日期
	 */
	private String startDate = null;
	
	/**
	 * 结束日期
	 * @return
	 */
	private String endDate = null;
	
	/**
	 * 部门id
	 */
	private String department_id = null;
	
	public String together(){
		
		return "together";
	}
	
	/**
	 * 明细表
	 * @return
	 */
	public String details(){
		
		return "details";
	}
	/**
	 * 改客户名称的客户已存在，但要继续申请合同
	 * @return
	 * @throws ServiceException
	 * @throws UnsupportedEncodingException 
	 */
	public String queryByName() throws ServiceException, UnsupportedEncodingException{
		List<ChannelCustomerInfo> infos = new ArrayList<ChannelCustomerInfo>();
		ChannelCustomerInfo  obj = channelCustomerInfoService.IsExit(name);
		infos.add(obj);
		Pagination pagination = new Pagination();
		pagination.setPageList(infos);
		this.setPageModule(pagination);
		return "manageBaseChannelCustomerInfo";
	}
	
	/**
	 * 新增意向客户的时候，判断这个客户名称是否已被申请合同
	 * @return
	 * @throws ServiceException
	 */
	public String nameIsExsit() throws ServiceException{
		List<ChannelCustomerInfo> infos = channelCustomerInfoService.queryAll(ChannelCustomerInfo.class);
		String msg = "";
		for(ChannelCustomerInfo info:infos){
			if(name.equals(info.getName())){
				if(info.getState() == 0){//意向客户
					CustomerProtectLog log = customerProtectLogService.querybyCustomerId(info.getId());
					if(getSystemEmployeeInfo().getId().equals(info.getEmployeeInfo().getId())){//如果是自己的客户
						msg = "该客户已是您的意向客户，是否直接申请合同？";
					}else{
						if(log != null){//已保护
							Date now = new Date();
							if(now.getTime() > log.getStartTime().getTime() && now.getTime() < log.getEndTime().getTime()){
								//在保护期
								EmployeeInfo employeeInfo = info.getEmployeeInfo();
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								Date startTime = log.getStartTime();
								Date endTime = log.getEndTime();
								String start = sdf.format(startTime);
								String end = sdf.format(endTime);
								msg = "该客户已被"+employeeInfo.getName()+"申请合同，并申请了保护，保护时间为"+start+"至"+end+",是否继续申请合同？";
							}else if(now.getTime() > log.getEndTime().getTime()){//保护过期
								EmployeeInfo employeeInfo = info.getEmployeeInfo();
								msg = "该客户已被"+employeeInfo.getName()+"申请合同，并申请了保护，但保护时间已过期，是否继续申请合同？";
							}
						}else{//未保护
							EmployeeInfo employeeInfo = info.getEmployeeInfo();
							msg = "该客户已被"+employeeInfo.getName()+"申请合同，是否申请合同？";
						}
					}
				}else{//已是渠道客户，提交了合同已无用
					msg = "该客户已是渠道客户，请确认客户名称！";
				}
			}else{//可以申请合同，该客户还未录入系统
				
			}
		}
		data = AjaxData.responseSuccess(msg);
		return JSON;
	}
	/**
	 * @author wangjiafneg
	 *  初始化客户
	 * @method initCustomer
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-2-23 下午03:01:28
	 */
	public String initCustomer() throws Exception{
		channelCustomerInfoService.initCustomer();
		data = "success";
		return TEXT;
	}
	
	/**
	 * @author wangjiafeng
	 * 批量放弃客户
	 * @method batchGiveUpCustomer
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2015-12-31 下午05:43:48
	 */
	public String batchGiveUpCustomer() throws Exception{
		Boolean suc = channelCustomerInfoService.batchGiveUpCustomer(this.getIdsValue());
		if(suc) {
			data = "success";
		} else {
			data = "failed";
		}
		return TEXT;
	}
	
	/**
	 * 添加渠道客户信息(基础信息)
	 * @Title: ChannelCustomerInfoAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String addBase() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			industryHtml  = industryService.createIndustryOptionHtml(null);
			channelPositions = channelPositionService.queryAll(100);
			return "addBaseChannelCustomerInfo";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError(getText("name.required"));
				return JSON;
			}
			
			if(GeneralUtil.isNull(channelTypeId)) {
				data = AjaxData.responseError(getText("channelTypeId.required"));
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
			//保存渠道客户信息实体
			//查询该客户是否被注册
			ChannelCustomerInfo  obj = channelCustomerInfoService.IsExit(name);
			if(obj != null){
				data = AjaxData.responseError("该客户名称已存在,请检查客户信息!");
				return JSON;
			}
			boolean isSuc = channelCustomerInfoService.persist(name, channelTypeId, 
					contact, contactPhone,industry,email,getSystemEmployeeInfo(),industry_id,
					channelPosition_id);
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
	 * 修改渠道客户信息(基础信息)
	 * @Title: ChannelCustomerInfoAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String editBase() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setChannelCustomerInfo(channelCustomerInfoService.queryById(ChannelCustomerInfo.class.getSimpleName(), id));
			if(GeneralUtil.isNotNull(channelCustomerInfo.getIndustry())){
				industryHtml  = industryService.createIndustryOptionHtml(channelCustomerInfo.getIndustry().getId());
			}else{
				industryHtml  = industryService.createIndustryOptionHtml(null);
			}
			channelPositions = channelPositionService.queryAll(10000);
			return "editBaseChannelCustomerInfo";
		} else {
			channelCustomerInfo = channelCustomerInfoService.queryById(ChannelCustomerInfo.class.getSimpleName(), id);
			//非空验证.
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError(getText("name.required"));
				return JSON;
			}
			
			if(GeneralUtil.isNull(channelTypeId)) {
				data = AjaxData.responseError(getText("channelTypeId.required"));
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
			//保存渠道客户信息实体
			//查询该客户是否被注册
			ChannelCustomerInfo  obj = channelCustomerInfoService.IsExit(name);
			if(obj!=null){
				if(!obj.getId().equals(channelCustomerInfo.getId())){
					data = AjaxData.responseError("该客户名称已存在,请检查客户信息!");
					return JSON;
				}
			}
			boolean isSuc = channelCustomerInfoService.merge(id,name, channelTypeId,  contact,
					contactPhone,industry,email, getSystemEmployeeInfo(),industry_id,channelPosition_id);
			//根据保存结果返回页面
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
	 * 查看
	 * @method view
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-1-9 下午02:07:32
	 */
	public String view() throws Exception{
		this.setChannelCustomerInfo(channelCustomerInfoService.queryById(ChannelCustomerInfo.class.getSimpleName(), id));
		return "view";
	}
	
	
	/**
	 * 添加渠道客户信息
	 * @Title: ChannelCustomerInfoAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			industryHtml  = industryService.createIndustryOptionHtml(null);
			channelPositions = channelPositionService.queryAll(10000);
			return "addChannelCustomerInfo";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError(getText("name.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(channelTypeId)) {
				data = AjaxData.responseError(getText("channelTypeId.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(openAccountDate)) {
				data = AjaxData.responseError(getText("openAccountDate.required"));
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
			if(GeneralUtil.isNull(openAccountMoney)) {
				data = AjaxData.responseError(getText("openAccountMoney.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(payWay)) {
				data = AjaxData.responseError(getText("payWay.required"));
				return JSON;
			}
			ChannelCustomerInfo obj = channelCustomerInfoService.queryByLoginIdExist(loginId);
			if(obj != null){
				data = AjaxData.responseError("该用户名已存在!");
				return JSON;
			}
			//查询该客户是否被注册
			obj = channelCustomerInfoService.IsExit(name);
			if(obj != null){
				data = AjaxData.responseError("该客户名称已存在,请检查客户信息!");
				return JSON;
			}
			//保存渠道客户信息实体
			boolean isSuc = channelCustomerInfoService.persist(name, loginId,password, channelTypeId,
					openAccountDate, contact, contactPhone, openAccountMoney, payWay,balance,favouredPolicyId
					,email,getSystemEmployeeInfo(),industry_id,channelPosition_id,1);
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
	 * @author wangjiafeng
	 * 申请捡单
	 * @method applyPickUp
	 * @return
	 * @throws ServiceException
	 * @return String
	 * @date 2015-12-31 下午04:01:33
	 */
	public String applyPickUp() throws ServiceException {
		if(!(getSystemEmployeeInfo() instanceof EmployeeInfo)){
			data = AjaxData.responseError("非销售管理员不能申请捡单!");
			return JSON;
		}
		channelCustomerInfo  = channelCustomerInfoService.queryById(ChannelCustomerInfo.class.getSimpleName(), id);
		if(GeneralUtil.isNull(channelCustomerInfo.getEmployeeInfo())){
			    boolean isSuc  = pickUpCustomerApplyService.persist(getSystemEmployeeInfo().getId(),id,0,employee_id, versionFlag);  
				if(isSuc) {
					data = AjaxData.responseSuccess("捡单成功,该客户成功成为了您的客户!");
				} else {
					data = AjaxData.responseError(getText("applyFailed"));
				}
			
		}else{
			data = AjaxData.responseError("该客户已经有业务员了!请选择其他客户!");
		}
		
		return JSON;
		
	}
	
	public String queryByLoginIdExist() throws ServiceException{
		ChannelCustomerInfo obj = channelCustomerInfoService.queryByLoginIdExist(loginId);
		if(obj != null){
				data = AjaxData.responseError("该用户名已存在！");
		}else{
			data = AjaxData.responseSuccess("用户名可以注册！");
		}
		return JSON;
	}
	/**
	 * 修改渠道客户信息
	 * @Title: ChannelCustomerInfoAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setChannelCustomerInfo(channelCustomerInfoService.queryById(ChannelCustomerInfo.class.getSimpleName(), id));
			if(GeneralUtil.isNotNull(channelCustomerInfo.getIndustry())){
				industryHtml  = industryService.createIndustryOptionHtml(channelCustomerInfo.getIndustry().getId());
			}else{
				industryHtml  = industryService.createIndustryOptionHtml(null);
			}
			channelPositions = channelPositionService.queryAll(10000);
			return "editChannelCustomerInfo";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(channelTypeId)) {
				data = AjaxData.responseError(getText("channelTypeId.required"));
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
			channelCustomerInfo = channelCustomerInfoService.queryById(ChannelCustomerInfo.class.getSimpleName(), id);
			ChannelCustomerInfo obj = channelCustomerInfoService.queryByLoginIdExist(loginId);
			if(obj != null){
				if(!obj.getId().equals(channelCustomerInfo.getId())){
					data = AjaxData.responseError("该用户名已存在!");
					return JSON;
				}
			}
			//查询该客户是否被注册
			/*obj = channelCustomerInfoService.IsExit(name);
			if(obj != null){
				if(!obj.getId().equals(channelCustomerInfo.getId())){
					data = AjaxData.responseError("该客户名称已存在,请检查客户信息!");
					return JSON;
				}
			}*/
			List<String> picList = getMultiPictureUrlFromJQueryUpLoader(fileNames, directory, 1);
			//修改渠道客户信息实体
			boolean isSuc = channelCustomerInfoService.merge(id, name, loginId, password,channelTypeId,
					openAccountDate, contact, contactPhone, openAccountMoney, payWay, balance,
					favouredPolicyId,industry,email,getSystemEmployeeInfo(),industry_id,channelPosition_id
					,idCard,yyzzNumber,picList);
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
	 * 管理渠道客户信息实体
	 * @Title: ChannelCustomerInfoAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		if(GeneralUtil.isNotNull(keyword)){
			keyword = DecoderUtil.UtfDecoder(keyword);
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
							this.setPageModule(channelCustomerInfoService.queryAll3(keyword, info, false,1,null,
									ContextConstants.ADMIN_COMMON_PAGE_SIZE));
						}
						if(authority.getContent().equals("departMent")){
							this.setPageModule(channelCustomerInfoService.queryAllWhereInDepartment(info,1));
						}
						if(authority.getContent().equals("all")){
							this.setPageModule(channelCustomerInfoService.queryAll3(keyword, null, false,1,null,
									ContextConstants.ADMIN_COMMON_PAGE_SIZE));
						}
					}
				}
			}
		}else{
			this.setPageModule(channelCustomerInfoService.queryAll3(keyword, null, false,1,null,
					ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		}
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageChannelCustomerInfo";
	}
	
	/**
	 * 管理公共渠道客户信息实体
	 * @Title: ChannelCustomerInfoAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String publicPool() throws ServiceException {
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
							this.setPageModule(channelCustomerInfoService.queryAll(keyword, info, 
									false,1,"s.employeeInfo_id asc",ContextConstants.ADMIN_COMMON_PAGE_SIZE));
						}
						if(authority.getContent().equals("departMent")){
							this.setPageModule(channelCustomerInfoService.queryAllWhereInDepartment(info,1));
						}
						if(authority.getContent().equals("all")){
							this.setPageModule(channelCustomerInfoService.queryAll(keyword, null, 
									false,1,"s.employeeInfo_id asc",ContextConstants.ADMIN_COMMON_PAGE_SIZE));
						}
					}
				}
			}
		}else{
			this.setPageModule(channelCustomerInfoService.queryAll(keyword, null, 
					false,null,"s.employeeInfo_id asc",ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		}
		
//		getSystemEmployeeInfo();
//		
//		this.setPageModule(channelCustomerInfoService.queryAll(keyword, null, 
//					false,null,"s.employeeInfo_id asc",ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "publicPoolChannelCustomerInfo";
	}
	
	/**
	 * @throws UnsupportedEncodingException 
	 * 管理意向客户信息
	 * @Title: ChannelCustomerInfoAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manageBase() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		if(GeneralUtil.isNotNull(keyword)){
			keyword = DecoderUtil.UtfDecoder(keyword);
		}
		if(GeneralUtil.isNotNull(name)){
			name = DecoderUtil.UtfDecoder(name);
			this.setPageModule(channelCustomerInfoService.paginationQuery("select c from ChannelCustomerInfo c where c.name=?", name));
		}else{
			String method = this.getClass().getSimpleName() + "_" + new Exception().getStackTrace()[0].getMethodName();
			
			SystemUser user = (SystemUser)ActionContext.getContext().getSession().get(ContextConstants.SCOPE_SYSTEM_USER);
			if(user instanceof EmployeeInfo){
				EmployeeInfo info = (EmployeeInfo) user;
				List<DataAuthoritys> authoritys = dataAuthoritysService.queryByEmployeeId(info.getId());
				if(authoritys != null){
					for(DataAuthoritys authority:authoritys){
						if(authority.getInMethod().equals(method)){
							if(authority.getContent().equals("one")){
								this.setPageModule(channelCustomerInfoService.queryAll(keyword, info, false,0,null,
										ContextConstants.ADMIN_COMMON_PAGE_SIZE));
							}
							if(authority.getContent().equals("departMent")){
								this.setPageModule(channelCustomerInfoService.queryAllWhereInDepartment(info,0));
							}
							if(authority.getContent().equals("all")){
								this.setPageModule(channelCustomerInfoService.queryAll(keyword, null, false,0,null,
										ContextConstants.ADMIN_COMMON_PAGE_SIZE));
							}
						}
					}
				}
			}else{
				this.setPageModule(channelCustomerInfoService.queryAll(keyword, null, false,0,null,
						ContextConstants.ADMIN_COMMON_PAGE_SIZE));
			}
		}
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageBaseChannelCustomerInfo";
	}
	
	/**
	 * 查看回收站
	 * @Title: ChannelCustomerInfoAction
	 * @Description: 查看回收站  
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(channelCustomerInfoService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleChannelCustomerInfo";
	}
	
	/**
	 * 逻辑删除渠道客户信息对象
	 * @Title: ChannelCustomerInfoAction
	 * @Description: 把渠道客户信息对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = channelCustomerInfoService.logicDeleteEntity(ChannelCustomerInfo.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除渠道客户信息对象
	 * @Title: ChannelCustomerInfoAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = channelCustomerInfoService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个渠道客户信息对象
	 * @Title: ChannelCustomerInfoAction
	 * @Description: 从回收站还原渠道客户信息对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = channelCustomerInfoService.restoreEntity(ChannelCustomerInfo.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核渠道客户信息对象
	 * @Title: ChannelCustomerInfoAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = channelCustomerInfoService.auditEntity(ChannelCustomerInfo.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: ChannelCustomerInfoAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = channelCustomerInfoService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	/**
	 * 客户转让
	 * @return
	 * @throws ServiceException
	 */
	public String transfer() throws ServiceException{
		if(GeneralUtil.isNull(operationFlag)){
			return "channelCustomerTransfer"; //选择转让客户
		}else{
			//飞空验证
			if(GeneralUtil.isNull(beforeEmployee_id)){
				data = AjaxData.responseError(getText("beforeEmployee.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(afterEmployee_id)){
				data = AjaxData.responseError(getText("afterEmployee.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(customer_id)){
				data = AjaxData.responseError(getText("customer.required"));
				return JSON;
			}
			if(getSystemEmployeeInfo() == null){
				data = AjaxData.responseError(getText("systemuser.cannotoper"));
				return JSON;
			}
			boolean flag = channelCustomerInfoService.transferCustomer(afterEmployee_id, customer_id, versionFlag);
			String[] cus = customer_id.split(",");
			for(String str : cus){
				CustomerTransferLog customerTransferLog = new CustomerTransferLog();
				customerTransferLog.setBeforeEmployee_id(beforeEmployee_id);
				customerTransferLog.setAfterEmployee_id(afterEmployee_id);
				customerTransferLog.setCustomer_id(str);
				customerTransferLog.setOperator_id(getSystemEmployeeInfo().getId());
				customerTransferLogService.persist(customerTransferLog);
			}
			if(flag){
				data = AjaxData.responseSuccess(getText("transferSuccess"));
			}else{
				data = AjaxData.responseError(getText("transferFailed"));
			}
		}
		return JSON;
	}
	
	/**
	 * 根据员工查询客户列表
	 * @return
	 * @throws ServiceException
	 */
	public String queryCustomerByEmployee() throws ServiceException{
		List<ChannelCustomerInfo> intentionCustomerList = channelCustomerInfoService.queryIntentionCustomerListByEmployee(beforeEmployee_id);
		List<ChannelCustomerInfo> channelCustomerList = channelCustomerInfoService.queryChannelCustomerListByEmployee(beforeEmployee_id);
		this.setEmployeeIntentionCustomerList(intentionCustomerList);
		this.setEmployeeChannelCustomerList(channelCustomerList);
		JSONObject json = new JSONObject();
		json.put("intentionList", intentionCustomerList);
		json.put("channelList", channelCustomerList);
		data = json.toString();
		return TEXT;
		//return "intentionCustomerListAjax";
	}
	
	/**
	 * 查看客户的电话拜访记录
	 * @return
	 * @throws ServiceException
	 */
	public String viewCustomerPhoneVisitRecord() throws ServiceException{
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		if(GeneralUtil.isNotNull(customer_id)){
			this.setPageModule(phoneVisitService.queryByCustomerId(customer_id,ContextConstants.ADMIN_COMMON_PAGE_SIZE,versionFlag));
		}else{
			this.setPageModule(phoneVisitService.queryAllByNotAudit(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		}
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "customerPhoneVisitRecord";
	}
	/**
	 * 查看客户的外出拜访记录
	 * @return
	 * @throws ServiceException
	 */
	public String viewCustomerOutVisitRecord() throws ServiceException{
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		if(GeneralUtil.isNotNull(customer_id)){
			this.setPageModule(employeeOutVisitService.queryByCustomerId(customer_id,ContextConstants.ADMIN_COMMON_PAGE_SIZE,versionFlag));
		}
		else{
			this.setPageModule(employeeOutVisitService.queryAllByNotAudit(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		}
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "customerOutVisitRecord";
	}
	
	/**
	 * @author wangjiafeng
	 * 获取渠道客户列表
	 * @method getChannelCustomerInfoList
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-1-2 下午01:37:25
	 */
	public String getChannelCustomerInfoList() throws Exception{
		if(GeneralUtil.isNotNull(keyword)){
			keyword = DecoderUtil.UtfDecoder(keyword);
		}
		channelCustomerInfos = channelCustomerInfoService.getAllCustomer(keyword, versionFlag);
		data = JSONArray.fromObject(channelCustomerInfos).toString();
		return TEXT;
	}
	
	/**
	 * 查看客户流动记录
	 * @return
	 * @throws ServiceException
	 */
	public String manageCustomerFlow() throws ServiceException{
		return "customerFlow";
	}
	
	/**
	 * 查看客户流动
	 * @return
	 * @throws ServiceException
	 */
	public String showCustomerFlow() throws ServiceException{
		List<CustomerTransferLog> list = customerTransferLogService.queryByCustomer(customer_id,versionFlag);
		List<String> strList = new ArrayList<String>();
		if(list != null && !list.isEmpty()){
			//如果仅有一条数据
			if(list.size()==1){
				CustomerTransferLog cus = list.get(0);
				EmployeeInfo be = employeeInfoService.queryById(EmployeeInfo.class.getSimpleName(), cus.getBeforeEmployee_id());
				EmployeeInfo ae = employeeInfoService.queryById(EmployeeInfo.class.getSimpleName(), cus.getAfterEmployee_id());
				strList.add(be.getName());
				strList.add(ae.getName());
			}else{
				for(int i=0;i<list.size();i++){
					CustomerTransferLog cus = list.get(i);
					if(i == list.size()-1){
						EmployeeInfo be = employeeInfoService.queryById(EmployeeInfo.class.getSimpleName(), cus.getBeforeEmployee_id());
						EmployeeInfo ae = employeeInfoService.queryById(EmployeeInfo.class.getSimpleName(), cus.getAfterEmployee_id());
						
						strList.add(be.getName()+" 转让时间："+cus.getStatus().getCreateTime());
						strList.add(ae.getName()+" 转让时间："+cus.getStatus().getCreateTime());
					}else{
						EmployeeInfo be = employeeInfoService.queryById(EmployeeInfo.class.getSimpleName(), cus.getBeforeEmployee_id());
						strList.add(be.getName()+" 转让时间："+cus.getStatus().getCreateTime());
					}
				}
			}
			
			data = AjaxData.responseError(JSONArray.fromObject(strList).toString());
			return JSON;
		}else{
			data = AjaxData.responseError(JSONArray.fromObject(strList).toString());
			return JSON;
		}
	}
	
	/**
	 * 意向客户统计
	 * @return
	 * @throws ServiceException
	 */
	public String statisticIntentionCustomer() throws ServiceException{
		if(getSystemEmployeeInfo() != null){
			this.setDepartment_id(getSystemEmployeeInfo().getDepartment().getId());
		}
		return "intentionCustomerStatistic";
	}
	
	/**
	 * 根据时间段统计意向客户
	 * @return
	 * @throws ServiceException
	 */
	public String statisticByDate() throws ServiceException{
		Date start = DateUtil.parseShortStringToDate(startDate);
		Date end = DateUtil.parseStringToDate(endDate+" 23:59:59");
		
		Long newIntentionCount = 0l;
		Long newCustomerProtectCount = 0l;
		
		JSONArray array = new JSONArray();
		JSONObject json = new JSONObject();
		
		List<DepartmentInfo> deptList = null;
		if(GeneralUtil.isNotNull(department_id)){
			deptList = departmentInfoService.queryListByCurrentId(department_id, versionFlag);
		}else{
			deptList = departmentInfoService.queryByList(versionFlag);
		}
		if(deptList != null && !deptList.isEmpty()){
			for(DepartmentInfo deptObj : deptList){
					
				newIntentionCount = channelCustomerInfoService.countIntentionCustomerByDate(start,end,deptObj.getId());
				newCustomerProtectCount = customerProtectLogService.countCustomerProtectByDate(start,end,deptObj.getId());
				
				List<String> departments = new LinkedList<String>();
				while(deptObj != null){
					
					departments.add(deptObj.getName());
					deptObj = deptObj.getParent();
				}
				Collections.reverse(departments);
				json.put("dept",  GeneralUtil.join(departments, " - "));
				
				
				json.put("customerCount", newIntentionCount);
				json.put("protectCount", newCustomerProtectCount);
				
				array.add(json);
			}
		}
		
		data = array.toString();
		return TEXT;
	}
	
	/**
	 * 意向客户明细统计
	 * @return
	 * @throws ServiceException
	 */
	public String intentionCustomerDetail() throws ServiceException{
		List<ChannelCustomerInfo> intentionCustomerList = null;
		if(getSystemEmployeeInfo() != null){
			this.setDepartment_id(getSystemEmployeeInfo().getDepartment().getId());
			String childIds = departmentInfoService.getChildIdsBycurrentId(department_id);
			//String deptPath = departmentInfoService.parseClassIds(department_id);
			intentionCustomerList = channelCustomerInfoService.queryByTopDepartment(childIds,versionFlag);
		}else{
			intentionCustomerList = channelCustomerInfoService.queryIntentionCustomerList(versionFlag);
		}
		Integer phoneVisitCount;//电话拜访数
		Integer outVisitCount; //外出拜访数
		List<List<String>> outList = new ArrayList<List<String>>();
		if(intentionCustomerList != null && !intentionCustomerList.isEmpty()){
			for(ChannelCustomerInfo intentionCustomer : intentionCustomerList){
				List<String> tempList = new ArrayList<String>();
				tempList.add(intentionCustomer.getLoginId());
				tempList.add(intentionCustomer.getName());
				if(GeneralUtil.isNotNull(intentionCustomer.getDepartment())){
					tempList.add(intentionCustomer.getDepartment().getId());
				}else{
					tempList.add(null);
				}
				if(GeneralUtil.isNotNull(intentionCustomer.getEmployeeInfo())){
					EmployeeInfo emp = intentionCustomer.getEmployeeInfo();
					if(emp != null){
						tempList.add(emp.getName());
					}else{
						tempList.add(null);
					}
				}else{
					tempList.add(null);
				}
				if(GeneralUtil.isNotNull(intentionCustomer.getChannelType())){
					ChannelType channelType = intentionCustomer.getChannelType();
					if(channelType != null){
						tempList.add(channelType.getName());
						
					}else{
						tempList.add(null);
					}
				}else{
					tempList.add(null);
				}
				boolean flag = customerProtectLogService.isInProtected(intentionCustomer.getId());
				if(flag){
					tempList.add("是");
				}else{
					tempList.add("否");
				}
				phoneVisitCount = phoneVisitService.countByCustomerId(intentionCustomer.getId());
				outVisitCount = employeeOutVisitService.countByCustomerId(intentionCustomer.getId());
				tempList.add(phoneVisitCount.toString());
				tempList.add(outVisitCount.toString());
				tempList.add(intentionCustomer.getId());
				
				outList.add(tempList);
			}
		}
		ActionContext.getContext().put("intentionCustomerList", outList);
		return "intentionCustomerDetail";
	}
	
	/**
	 * 客户电话拜访记录详细
	 * @return
	 * @throws ServiceException
	 */
	public String phoneVisitDetail() throws ServiceException{
		List<PhoneVisit> list = phoneVisitService.queryListByCustomerId(id, versionFlag);
		
		ActionContext.getContext().put("phoneVisitList", list);
		
		return "phoneVisitDetail";
	}
	
	/**
	 * 客户外出拜访记录详细
	 * @return
	 * @throws ServiceException
	 */
	public String outVisitDetail() throws ServiceException{
		List<EmployeeOutVisit> list = employeeOutVisitService.queryListByCustomerId(id,versionFlag);
		
		ActionContext.getContext().put("outVisitList", list);
		
		return "outVisitDetail";
	}
	
	public ChannelCustomerInfo getChannelCustomerInfo() {
		return channelCustomerInfo;
	}
	public void setChannelCustomerInfo(ChannelCustomerInfo channelCustomerInfo) {
		this.channelCustomerInfo = channelCustomerInfo;
	}
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getChannelTypeId() {
		return channelTypeId;
	}
	public void setChannelTypeId(String channelTypeId) {
		this.channelTypeId = channelTypeId;
	}
	public Date getOpenAccountDate() {
		return openAccountDate;
	}
	public void setOpenAccountDate(Date openAccountDate) {
		this.openAccountDate = openAccountDate;
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
	public Double getOpenAccountMoney() {
		return openAccountMoney;
	}
	public void setOpenAccountMoney(Double openAccountMoney) {
		this.openAccountMoney = openAccountMoney;
	}
	public String getPayWay() {
		return payWay;
	}
	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}
	
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}
	
	public String getRepassword() {
		return repassword;
	}


	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}


	public void setBalance(Double balance) {
		this.balance = balance;
	}


	public Double getBalance() {
		return balance;
	}


	public void setFavouredPolicyId(String favouredPolicyId) {
		this.favouredPolicyId = favouredPolicyId;
	}


	public String getFavouredPolicyId() {
		return favouredPolicyId;
	}


	public void setSignState(Integer signState) {
		this.signState = signState;
	}


	public Integer getSignState() {
		return signState;
	}


	public void setIndustry(String industry) {
		this.industry = industry;
	}


	public String getIndustry() {
		return industry;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getEmail() {
		return email;
	}


	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}


	public String getEmployee_id() {
		return employee_id;
	}

	public String getIndustry_id() {
		return industry_id;
	}

	public void setIndustry_id(String industryId) {
		industry_id = industryId;
	}

	public String getIndustryHtml() {
		return industryHtml;
	}

	public void setIndustryHtml(String industryHtml) {
		this.industryHtml = industryHtml;
	}

	public String getChannelPosition_id() {
		return channelPosition_id;
	}

	public void setChannelPosition_id(String channelPositionId) {
		channelPosition_id = channelPositionId;
	}

	public List<ChannelPosition> getChannelPositions() {
		return channelPositions;
	}

	public void setChannelPositions(List<ChannelPosition> channelPositions) {
		this.channelPositions = channelPositions;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getYyzzNumber() {
		return yyzzNumber;
	}

	public void setYyzzNumber(String yyzzNumber) {
		this.yyzzNumber = yyzzNumber;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
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

	public List<ChannelCustomerInfo> getEmployeeIntentionCustomerList() {
		return employeeIntentionCustomerList;
	}

	public void setEmployeeIntentionCustomerList(
			List<ChannelCustomerInfo> employeeIntentionCustomerList) {
		this.employeeIntentionCustomerList = employeeIntentionCustomerList;
	}

	public List<ChannelCustomerInfo> getEmployeeChannelCustomerList() {
		return employeeChannelCustomerList;
	}

	public void setEmployeeChannelCustomerList(
			List<ChannelCustomerInfo> employeeChannelCustomerList) {
		this.employeeChannelCustomerList = employeeChannelCustomerList;
	}

	public List<ChannelCustomerInfo> getChannelCustomerInfos() {
		return channelCustomerInfos;
	}

	public void setChannelCustomerInfos(
			List<ChannelCustomerInfo> channelCustomerInfos) {
		this.channelCustomerInfos = channelCustomerInfos;
	}

	public IChannelCustomerInfoService getChannelCustomerInfoService() {
		return channelCustomerInfoService;
	}

	public void setChannelCustomerInfoService(
			IChannelCustomerInfoService channelCustomerInfoService) {
		this.channelCustomerInfoService = channelCustomerInfoService;
	}

	public ICustomerProtectLogService getCustomerProtectLogService() {
		return customerProtectLogService;
	}

	public void setCustomerProtectLogService(
			ICustomerProtectLogService customerProtectLogService) {
		this.customerProtectLogService = customerProtectLogService;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}
	
}
