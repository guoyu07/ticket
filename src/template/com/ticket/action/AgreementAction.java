package com.ticket.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.constants.JQueryUploadConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.Agreement;
import com.ticket.pojo.ChannelCustomerInfo;
import com.ticket.pojo.DataAuthoritys;
import com.ticket.pojo.DepartmentInfo;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.SystemDictionary;
import com.ticket.pojo.SystemUser;
import com.ticket.service.IAgreementService;
import com.ticket.service.IApplayClassService;
import com.ticket.service.IBjdjAppointmentService;
import com.ticket.service.IBjdjServicePackageService;
import com.ticket.service.IBjdjValidationService;
import com.ticket.service.IChannelCustomerInfoService;
import com.ticket.service.IChannelTypeService;
import com.ticket.service.ICustomerProtectLogService;
import com.ticket.service.IDataAuthoritysService;
import com.ticket.service.IDepartmentInfoService;
import com.ticket.service.IEmployeeInfoService;
import com.ticket.service.IEmployeeOutVisitService;
import com.ticket.service.IOrderInfoService;
import com.ticket.service.IPhoneVisitService;
import com.ticket.service.IRechargeRecordService;
import com.ticket.service.ISaleTaskService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

import net.sf.json.JSONArray;

/**
 * 客户合同控制器
 * @ClassName: AgreementAction   
 * @Description:  提供客户合同的相关操作方法. 
 * @author HiSay  
 * @date 2015-11-04 14:49:37
 *
 */
public class AgreementAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	  
	//客户合同的业务层
	@Resource 
	private IAgreementService agreementService;
	@Resource
	private IBjdjServicePackageService servicePackageService;
	@Resource 
	private IChannelCustomerInfoService channelCustomerInfoService;
	//客保业务层
	@Resource 
	private ICustomerProtectLogService customerProtectLogService;
	@Resource
	private IEmployeeInfoService employeeInfoService;
	@Resource
	private IDepartmentInfoService departmentInfoService;
	@Resource
	private IApplayClassService applayClassService;
	@Resource
	private IChannelTypeService channelTypeService;
	@Resource
	private IOrderInfoService orderInfoService;
	@Resource
	private IRechargeRecordService rechargeRecordService;
	@Resource
	private IBjdjAppointmentService bjdjAppointmentService;
	@Resource
	private IBjdjValidationService bjdjValidationService;
	@Resource
	private IEmployeeOutVisitService employeeOutVisitService;
	@Resource
	private IPhoneVisitService phoneVisitService;
	@Resource
	private ISaleTaskService saleTaskService;
	@Resource
	private ISystemDictionaryService systemDictionaryService;
	@Resource
	private IDataAuthoritysService dataAuthoritysService;
	
	//客户合同实体
	private Agreement agreement;
	private String id,name,applayClassId,employee_id,chargeStatus,content,contacts,phone,email,payWay,
		keyword,applyDate,auditDate,secondAuditRemark,qhRemark,employeeInfo_id;
	private Date receiveDate,signingDate,receivablesDate,returnDate,
		openAccountDate,onlineDate,firstRenewDate,lastCheckDate;
	private Integer canApplayCount,gotCount,agreementState,secondAuditState;
	private Double firstRenewMoney,amountOfMoney;
	private String customerId;
	private List<ChannelCustomerInfo> channelCustomerInfos;
	private List<?> list;
	private ChannelCustomerInfo channelCustomerInfo;
	
	private Date startDate, endDate;
	/**
	 * 生成已签约一线报表
	 * @return
	 * @throws ServiceException 
	 */
	public String frontlineReport() throws ServiceException{
		if(GeneralUtil.isNull(startDate)){
			return "frontlineReport"; 
		}
		if(GeneralUtil.isNull(endDate)){
			return "frontlineReport";
		}
		List<EmployeeInfo> all = new ArrayList<EmployeeInfo>();
		
		String method = this.getClass().getSimpleName() + "_" + new Exception().getStackTrace()[0].getMethodName();
		
		SystemUser user = (SystemUser)ActionContext.getContext().getSession().get(ContextConstants.SCOPE_SYSTEM_USER);
		if(user instanceof EmployeeInfo){
			EmployeeInfo info = (EmployeeInfo) user;
			
			List<DataAuthoritys> authoritys = dataAuthoritysService.queryByEmployeeId(info.getId());
			if(authoritys != null){
				for(DataAuthoritys authority:authoritys){
					if(authority.getInMethod().equals(method)){
						if(authority.getContent().equals("one")){//个人
							all.add(info);
						}
						if(authority.getContent().equals("departMent")){//部门
							DepartmentInfo departmentInfo = departmentInfoService.getByEmployee(info.getId());
							List<EmployeeInfo> employeeInfos = employeeInfoService.queryByDepartmentId(departmentInfo.getId(), versionFlag);
							for(EmployeeInfo employeeInfo: employeeInfos){
								all.add(employeeInfo);
							}
							List<DepartmentInfo> childDepartments = departmentInfoService.getChildList(departmentInfo);
							if(childDepartments != null){
								for(DepartmentInfo departmentInfo2:childDepartments){
									List<EmployeeInfo> employeeInfos2 = employeeInfoService.queryByDepartmentId(departmentInfo2.getId(), versionFlag);
									for(EmployeeInfo employeeInfo:employeeInfos2){
										all.add(employeeInfo);
									}
								}
							}
						}
						if(authority.getContent().equals("all")){//全部
							//获取销售部的所有部门
							DepartmentInfo departmentInfo = departmentInfoService.getByName("销售部");
							List<EmployeeInfo> list1 = employeeInfoService.queryByDepartmentId(departmentInfo.getId(), versionFlag);
							if(GeneralUtil.isNotNull(list1)){
								for(EmployeeInfo employeeInfo:list1){
									all.add(employeeInfo);
								}
							}
							List<DepartmentInfo> departmentInfos = departmentInfoService.getChildList(departmentInfo);
							for(DepartmentInfo departmentInfo2:departmentInfos){
								List<EmployeeInfo> list = employeeInfoService.queryByDepartmentId(departmentInfo2.getId(), versionFlag);
								if(GeneralUtil.isNotNull(list)){
									for(EmployeeInfo employeeInfo:list){
										all.add(employeeInfo);
									}
								}
								
								List<DepartmentInfo> departmentInfos1 = departmentInfoService.getChildList(departmentInfo2);
								for(DepartmentInfo departmentInfo3:departmentInfos1){
									List<EmployeeInfo> list2 = employeeInfoService.queryByDepartmentId(departmentInfo3.getId(), versionFlag);
									if(GeneralUtil.isNotNull(list2)){
										for(EmployeeInfo employeeInfo:list2){
											all.add(employeeInfo);
										}
									}
								}
							}
						}
					}
				}
			}
		}else{
			//获取销售部的所有部门
			DepartmentInfo departmentInfo = departmentInfoService.getByName("销售部");
			List<EmployeeInfo> list1 = employeeInfoService.queryByDepartmentId(departmentInfo.getId(), versionFlag);
			if(GeneralUtil.isNotNull(list1)){
				for(EmployeeInfo employeeInfo:list1){
					all.add(employeeInfo);
				}
			}
			List<DepartmentInfo> departmentInfos = departmentInfoService.getChildList(departmentInfo);
			for(DepartmentInfo departmentInfo2:departmentInfos){
				List<EmployeeInfo> list = employeeInfoService.queryByDepartmentId(departmentInfo2.getId(), versionFlag);
				if(GeneralUtil.isNotNull(list)){
					for(EmployeeInfo employeeInfo:list){
						all.add(employeeInfo);
					}
				}
				
				List<DepartmentInfo> departmentInfos1 = departmentInfoService.getChildList(departmentInfo2);
				for(DepartmentInfo departmentInfo3:departmentInfos1){
					List<EmployeeInfo> list2 = employeeInfoService.queryByDepartmentId(departmentInfo3.getId(), versionFlag);
					if(GeneralUtil.isNotNull(list2)){
						for(EmployeeInfo employeeInfo:list2){
							all.add(employeeInfo);
						}
					}
				}
			}
		}
		ActionContext.getContext().put("startDate", startDate);
		ActionContext.getContext().put("endDate", endDate);
		ActionContext.getContext().put("employeeInfos", all);
		return "frontlineReport";
	}
	/**
	 * 生成已签约部门报表
	 * @return
	 * @throws ServiceException 
	 */
	public String departmentReport() throws ServiceException{
		if(GeneralUtil.isNull(startDate)){
			return "departmentReport"; 
		}
		if(GeneralUtil.isNull(endDate)){
			return "departmentReport";
		}
		
		List<EmployeeInfo> all = new ArrayList<EmployeeInfo>();
		
		String method = this.getClass().getSimpleName() + "_" + new Exception().getStackTrace()[0].getMethodName();
		
		SystemUser user = (SystemUser)ActionContext.getContext().getSession().get(ContextConstants.SCOPE_SYSTEM_USER);
		if(user instanceof EmployeeInfo){
			EmployeeInfo info = (EmployeeInfo) user;
			
			List<DataAuthoritys> authoritys = dataAuthoritysService.queryByEmployeeId(info.getId());
			if(authoritys != null){
				for(DataAuthoritys authority:authoritys){
					if(authority.getInMethod().equals(method)){
						if(authority.getContent().equals("one")){//个人
							all.add(info);
						}
						if(authority.getContent().equals("departMent")){//部门
							DepartmentInfo departmentInfo = departmentInfoService.getByEmployee(info.getId());
							List<EmployeeInfo> employeeInfos = employeeInfoService.queryByDepartmentId(departmentInfo.getId(), versionFlag);
							for(EmployeeInfo employeeInfo: employeeInfos){
								all.add(employeeInfo);
							}
							List<DepartmentInfo> childDepartments = departmentInfoService.getChildList(departmentInfo);
							if(childDepartments != null){
								for(DepartmentInfo departmentInfo2:childDepartments){
									List<EmployeeInfo> employeeInfos2 = employeeInfoService.queryByDepartmentId(departmentInfo2.getId(), versionFlag);
									for(EmployeeInfo employeeInfo:employeeInfos2){
										all.add(employeeInfo);
									}
								}
							}
						}
						if(authority.getContent().equals("all")){//全部
							//获取销售部的所有部门
							DepartmentInfo departmentInfo = departmentInfoService.getByName("销售部");
							List<EmployeeInfo> list1 = employeeInfoService.queryByDepartmentId(departmentInfo.getId(), versionFlag);
							if(GeneralUtil.isNotNull(list1)){
								for(EmployeeInfo employeeInfo:list1){
									all.add(employeeInfo);
								}
							}
							List<DepartmentInfo> departmentInfos = departmentInfoService.getChildList(departmentInfo);
							for(DepartmentInfo departmentInfo2:departmentInfos){
								List<EmployeeInfo> list = employeeInfoService.queryByDepartmentId(departmentInfo2.getId(), versionFlag);
								if(GeneralUtil.isNotNull(list)){
									for(EmployeeInfo employeeInfo:list){
										all.add(employeeInfo);
									}
								}
								
								List<DepartmentInfo> departmentInfos1 = departmentInfoService.getChildList(departmentInfo2);
								for(DepartmentInfo departmentInfo3:departmentInfos1){
									List<EmployeeInfo> list2 = employeeInfoService.queryByDepartmentId(departmentInfo3.getId(), versionFlag);
									if(GeneralUtil.isNotNull(list2)){
										for(EmployeeInfo employeeInfo:list2){
											all.add(employeeInfo);
										}
									}
								}
							}
						}
					}
				}
			}
		}else{
			//获取销售部的所有部门
			DepartmentInfo departmentInfo = departmentInfoService.getByName("销售部");
			List<EmployeeInfo> list1 = employeeInfoService.queryByDepartmentId(departmentInfo.getId(), versionFlag);
			if(GeneralUtil.isNotNull(list1)){
				for(EmployeeInfo employeeInfo:list1){
					all.add(employeeInfo);
				}
			}
			List<DepartmentInfo> departmentInfos = departmentInfoService.getChildList(departmentInfo);
			for(DepartmentInfo departmentInfo2:departmentInfos){
				List<EmployeeInfo> list = employeeInfoService.queryByDepartmentId(departmentInfo2.getId(), versionFlag);
				if(GeneralUtil.isNotNull(list)){
					for(EmployeeInfo employeeInfo:list){
						all.add(employeeInfo);
					}
				}
				
				List<DepartmentInfo> departmentInfos1 = departmentInfoService.getChildList(departmentInfo2);
				for(DepartmentInfo departmentInfo3:departmentInfos1){
					List<EmployeeInfo> list2 = employeeInfoService.queryByDepartmentId(departmentInfo3.getId(), versionFlag);
					if(GeneralUtil.isNotNull(list2)){
						for(EmployeeInfo employeeInfo:list2){
							all.add(employeeInfo);
						}
					}
				}
			}
		}
		
		ActionContext.getContext().put("employeeInfos", all);
		ActionContext.getContext().put("startDate", startDate);
		ActionContext.getContext().put("endDate", endDate);
		return "departmentReport";
	}
	/**
	 * 生成已签约累计报表
	 * @return
	 * @throws ServiceException 
	 */
	public String cumulativeReport() throws ServiceException{
		
		//客户总数
		long kehuCount = channelCustomerInfoService.queryAllCount(null);
		//新增客户总数
		long kehuAddCount = channelCustomerInfoService.queryThisMonthAddCount(null);
		//充值金额
		double czMoney = rechargeRecordService.queryAllMoney(null);
		//服务码购买数
		long buyCount = orderInfoService.queryAllBuyCount(null);
		//购买服务码实付金额
		double paidAmount = buyCount * servicePackageService.getByName("全程服务套餐").getPrice();
		//服务码激活数
		long activeCount = bjdjAppointmentService.queryAllCount(null);
		//服务码验证数
		long validationCount = bjdjValidationService.queryAllCount();
		//外出拜访数
		long visitCount = employeeOutVisitService.queryAllCount();
		//电话拜访数
		long phoneCount = phoneVisitService.queryAllCount();
		//本月业绩任务
		double taskMoney = saleTaskService.queryAllRechargeCount();
		//本月累计完成数
		double completeMoney = rechargeRecordService.queryThisMonthMoney();
		//完成进度
		double progress = 0;
		if(taskMoney == 0){
			progress = 0;
		}else{
			progress = (completeMoney / taskMoney) * 100;
		}
		
		ActionContext.getContext().put("kehuCount", kehuCount);
		ActionContext.getContext().put("kehuAddCount", kehuAddCount);
		ActionContext.getContext().put("czMoney", czMoney);
		ActionContext.getContext().put("buyCount", buyCount);
		ActionContext.getContext().put("paidAmount", paidAmount);
		ActionContext.getContext().put("activeCount", activeCount);
		ActionContext.getContext().put("validationCount", validationCount);
		ActionContext.getContext().put("visitCount", visitCount);
		ActionContext.getContext().put("phoneCount", phoneCount);
		ActionContext.getContext().put("taskMoney", taskMoney);
		ActionContext.getContext().put("completeMoney", completeMoney);
		ActionContext.getContext().put("progress", progress);
		return "cumulativeReport";
	}
	/**
	 * 生成汇总报表
	 * @return
	 */
	public String togetherReport(){
		long[] list = new long[5];
		String method = this.getClass().getSimpleName() + "_" + new Exception().getStackTrace()[0].getMethodName();
		
		SystemUser user = (SystemUser)ActionContext.getContext().getSession().get(ContextConstants.SCOPE_SYSTEM_USER);
		if(user instanceof EmployeeInfo){
			EmployeeInfo info = (EmployeeInfo) user;
			
			List<DataAuthoritys> authoritys = dataAuthoritysService.queryByEmployeeId(info.getId());
			if(authoritys != null){
				for(DataAuthoritys authority:authoritys){
					if(authority.getInMethod().equals(method)){
						if(authority.getContent().equals("one")){
							long count = agreementService.allCount(startDate, endDate,info,1);
							list[0] = count;
							count = agreementService.approvalCount(startDate, endDate,info,1);
							list[1] = count;
							count = agreementService.issueCount(startDate, endDate,info,1);
							list[2] = count;
							count = agreementService.signBackCount(startDate, endDate,info,1);
							list[3] = count;
							count = agreementService.archiveCount(startDate, endDate,info,1);
							list[4] = count;
						}
						if(authority.getContent().equals("departMent")){
							long count = agreementService.allCount(startDate, endDate,info,2);
							list[0] = count;
							count = agreementService.approvalCount(startDate, endDate,info,2);
							list[1] = count;
							count = agreementService.issueCount(startDate, endDate,info,2);
							list[2] = count;
							count = agreementService.signBackCount(startDate, endDate,info,2);
							list[3] = count;
							count = agreementService.archiveCount(startDate, endDate,info,2);
							list[4] = count;
						}
						if(authority.getContent().equals("all")){
							long count = agreementService.allCount(startDate, endDate,null,0);
							list[0] = count;
							count = agreementService.approvalCount(startDate, endDate,null,0);
							list[1] = count;
							count = agreementService.issueCount(startDate, endDate,null,0);
							list[2] = count;
							count = agreementService.signBackCount(startDate, endDate,null,0);
							list[3] = count;
							count = agreementService.archiveCount(startDate, endDate,null,0);
							list[4] = count;
						}
					}
				}
			}
		}else{
			long count = agreementService.allCount(startDate, endDate,null,0);
			list[0] = count;
			count = agreementService.approvalCount(startDate, endDate,null,0);
			list[1] = count;
			count = agreementService.issueCount(startDate, endDate,null,0);
			list[2] = count;
			count = agreementService.signBackCount(startDate, endDate,null,0);
			list[3] = count;
			count = agreementService.archiveCount(startDate, endDate,null,0);
			list[4] = count;
		}
		
		ActionContext.getContext().put("count", list);
		return "togetherReport";
	}
	
	/**
	 * 生成细节报表
	 * @return
	 */
	public String detailsReport(){
		SystemUser user = (SystemUser)ActionContext.getContext().getSession().get(ContextConstants.SCOPE_SYSTEM_USER);
		if(user instanceof EmployeeInfo){
			EmployeeInfo info = (EmployeeInfo) user;
			
			List<DataAuthoritys> authoritys = dataAuthoritysService.queryByEmployeeId(info.getId());
			if(authoritys != null){
				for(DataAuthoritys authority:authoritys){
					if(authority.getInMethod().equals("AgreementAction_togetherReport")){
						if(authority.getContent().equals("one")){
							if("all".equals(keyword)){
								
								list = agreementService.allList(startDate, endDate,info,1);
							}else if("approval".equals(keyword)){
								
								list = agreementService.approvalList(startDate, endDate,info,1);
							}else if("issue".equals(keyword)){
								
								list = agreementService.issueList(startDate, endDate,info,1);
							}else if("signback".equals(keyword)){
								
								list = agreementService.signBackList(startDate, endDate,info,1);
							}else if("archive".equals(keyword)){
								
								list = agreementService.archiveList(startDate, endDate,info,1);
							}
						}
						if(authority.getContent().equals("departMent")){
							if("all".equals(keyword)){
								
								list = agreementService.allList(startDate, endDate,info,2);
							}else if("approval".equals(keyword)){
								
								list = agreementService.approvalList(startDate, endDate,info,2);
							}else if("issue".equals(keyword)){
								
								list = agreementService.issueList(startDate, endDate,info,2);
							}else if("signback".equals(keyword)){
								
								list = agreementService.signBackList(startDate, endDate,info,2);
							}else if("archive".equals(keyword)){
								
								list = agreementService.archiveList(startDate, endDate,info,2);
							}
						}
						if(authority.getContent().equals("all")){
							if("all".equals(keyword)){
								
								list = agreementService.allList(startDate, endDate,null,0);
							}else if("approval".equals(keyword)){
								
								list = agreementService.approvalList(startDate, endDate,null,0);
							}else if("issue".equals(keyword)){
								
								list = agreementService.issueList(startDate, endDate,null,0);
							}else if("signback".equals(keyword)){
								
								list = agreementService.signBackList(startDate, endDate,null,0);
							}else if("archive".equals(keyword)){
								
								list = agreementService.archiveList(startDate, endDate,null,0);
							}
						}
					}
				}
			}
		}else{
			if("all".equals(keyword)){
				
				list = agreementService.allList(startDate, endDate,null,0);
			}else if("approval".equals(keyword)){
				
				list = agreementService.approvalList(startDate, endDate,null,0);
			}else if("issue".equals(keyword)){
				
				list = agreementService.issueList(startDate, endDate,null,0);
			}else if("signback".equals(keyword)){
				
				list = agreementService.signBackList(startDate, endDate,null,0);
			}else if("archive".equals(keyword)){
				
				list = agreementService.archiveList(startDate, endDate,null,0);
			}
		}
		return "detailsReport";
	}
	
	/**
	 * @author wangjiafeng
	 * 合同作废
	 * @method changeEffective
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-1-15 上午10:09:28
	 */
	public String changeEffective() throws Exception{
		Boolean suc = agreementService.changeEffective(id);
		if(suc){
			data = "success";
		}else{
			data = "failed";
		}
		return TEXT;
	}
	
	/**
	 * @author wangjiafeng
	 * 二次审核合同
	 * @method fafang
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-1-6 上午09:54:48
	 */
	public String fafang() throws Exception{
		if(GeneralUtil.isNull(operationFlag)){
			agreement = agreementService.queryById(Agreement.class.getSimpleName(), id);
			return "fafang";
		}
		
		Boolean suc = agreementService.fafang(id,secondAuditState,secondAuditRemark, 4,employeeInfo_id);
		
		if(suc){
			
			data = AjaxData.responseSuccess("操作成功");
		} else {
			data = AjaxData.responseError("操作失败");
		}
		return JSON;
	}
	/**
	 * @author wangjiafeng
	 * 合同归档
	 * @method guidang
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-1-6 上午09:59:50
	 */
	public String guidang() throws Exception{
		Boolean suc = agreementService.changeAgreementState(id, 5);
		if(suc){
			data = "success";
		}else{
			data = "failed";
		}
		return TEXT;
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
		channelCustomerInfos = channelCustomerInfoService.queryAllList(keyword,getSystemEmployeeInfo(), null);
		data = JSONArray.fromObject(channelCustomerInfos).toString();
		return TEXT;
	}
	
	/**
	 * @author wangjiafeng
	 *  验证是否添加
	 * @method validateIsAdd
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-1-12 上午11:27:17
	 */
	public String validateIsAdd() throws Exception{
		String result = agreementService.validateIsAdd(id, getSystemEmployeeInfo());
		if(Boolean.valueOf(result)){
			data = "";
		}
		return TEXT;
	}
	
	/**
	 * 添加客户合同
	 * @Title: AgreementAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			if(getSystemEmployeeInfo() != null){
				channelCustomerInfos = channelCustomerInfoService.queryAllList(null,null, 2000);
			}
			return "addAgreement";
		} else {
			if(getSystemEmployeeInfo() == null){
				data = AjaxData.responseError("系统管理员不能申请合同");
				return JSON;
			}
			if(agreementService.validateIsExist(customerId,5,1)){
				data = AjaxData.responseError("该用户已经录入过合同,请再确认信息");
				return JSON;
			}
			if(GeneralUtil.isNull(applayClassId)) {
				data = AjaxData.responseError(getText("applayClassId.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(applyDate)) {
				data = AjaxData.responseError(getText("applyDate.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(customerId)) {
				data = AjaxData.responseError(getText("customerId.required"));
				return JSON;
			}
			agreementState=0;
			//保存客户合同实体
			String result = agreementService.persist(name,applayClassId, getSystemEmployeeInfo(),
					applyDate, canApplayCount, gotCount, chargeStatus, receiveDate, signingDate,
					receivablesDate, returnDate, auditDate, openAccountDate, onlineDate, firstRenewMoney, 
					firstRenewDate, lastCheckDate, customerId, amountOfMoney,content,agreementState);
			//根据保存结果返回页面
			if(Boolean.valueOf(result)) {
				data = AjaxData.responseSuccess("合同申请成功");
			} else {
				data = AjaxData.responseError(result);
			}
			return JSON;
		}
	}
	
	/**
	 * 添加客户合同
	 * @Title: AgreementAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add2() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			channelCustomerInfo = channelCustomerInfoService
					.queryById(ChannelCustomerInfo.class.getSimpleName(), customerId);
			return "addAgreement2";
		} else {
			if(getSystemEmployeeInfo() == null){
				data = AjaxData.responseError("系统管理员不能申请合同");
				return JSON;
			}
			if(agreementService.validateIsExist(customerId,5,1)){
				data = AjaxData.responseError("该用户已经录入过合同,请再确认信息");
				return JSON;
			}
			if(GeneralUtil.isNull(applayClassId)) {
				data = AjaxData.responseError(getText("applayClassId.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(applyDate)) {
				data = AjaxData.responseError(getText("applyDate.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(customerId)) {
				data = AjaxData.responseError(getText("customerId.required"));
				return JSON;
			}
			agreementState=0;
			//保存客户合同实体
			String result = agreementService.persist(name,applayClassId, getSystemEmployeeInfo(),
					applyDate, canApplayCount, gotCount, chargeStatus, receiveDate, signingDate,
					receivablesDate, returnDate, auditDate, openAccountDate, onlineDate, firstRenewMoney, 
					firstRenewDate, lastCheckDate, customerId, amountOfMoney,content,agreementState);
			//根据保存结果返回页面
			if(Boolean.valueOf(result)) {
				data = AjaxData.responseSuccess("合同申请成功");
			} else {
				data = AjaxData.responseError(result);
			}
			return JSON;
		}
	}
	
	/**
	 * 修改客户合同
	 * @Title: AgreementAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			channelCustomerInfos = channelCustomerInfoService.queryAllList(null, getSystemEmployeeInfo(), 20);
			this.setAgreement(agreementService.queryById(Agreement.class.getSimpleName(), id));
			return "editAgreement";
		} else {
			
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			agreement = agreementService.queryById(Agreement.class.getSimpleName(), id);
			if(getSystemEmployeeInfo() == null){
				data = AjaxData.responseError("系统管理员不能修改员工的合同");
				return JSON;
			}else{
				if(!agreement.getEmployeeInfo2().getId().equals(getSystemEmployeeInfo().getId())){
					data = AjaxData.responseError("该合同不属于您,您不能修改合同!");
					return JSON;
				}
			}
			if(GeneralUtil.isNull(applayClassId)) {
				data = AjaxData.responseError(getText("applayClassId.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(applyDate)) {
				data = AjaxData.responseError(getText("applyDate.required"));
				return JSON;
			}
			//修改客户合同实体
			boolean isSuc = agreementService.merge(id, name, applayClassId, applyDate, content);
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
	 * 审批通过客户合同
	 * @Title: AgreementAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String sp() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setAgreement(agreementService.queryById(Agreement.class.getSimpleName(), id));
			return "spAgreement";
		} else {
			String picture = null;
			if(agreementState == 1){
				picture = getSinglePictureUrlFromJQueryUpLoader(fileNames, directory, JQueryUploadConstants.PICTURE_TYPE_DEFAULT);
				if(GeneralUtil.isNull(picture)){
					data = AjaxData.responseError("请上传合同副本");
					return JSON;
				}
			}
			//修改客户合同实体
			boolean isSuc = agreementService.merge(id, name, auditDate, (EmployeeInfo)getSessionAdminUser(), chargeStatus, lastCheckDate, 
					agreementState,picture);

			//根据修改结果返回页面
			if(isSuc) {
				data = AjaxData.responseSuccess("审核成功");
			} else {
				data = AjaxData.responseError("审核失败");
			}
			return JSON;
		}
	}
	
	/**
	 * 重新申请
	 * @return
	 */
	public String reapply(){
		
		try {
			Agreement agreement = agreementService.get(Agreement.class, id);
			agreement.setAgreementState(0);
			agreement.setSecondAuditState(null);
			agreement.setSecondAuditRemark(null);
			agreement.setChargeStatus(null);
			agreement.setEffective(1);
			agreementService.merge(agreement);
			data = AjaxData.responseSuccess("success");
		} catch (Exception e) {
			data = AjaxData.responseError("failed");
			e.printStackTrace();
		}
		return JSON;
	}
	
	
	/**
	 * 签回客户合同
	 * @Title: AgreementAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String qh() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setAgreement(agreementService.queryById(Agreement.class.getSimpleName(), id));
			List<SystemDictionary> dicts = systemDictionaryService.querySubByParentName("支付方式");
			ActionContext.getContext().put("payWay", dicts);
			return "qhAgreement";
		} else {
			String picture  = getSinglePictureUrlFromJQueryUpLoader(fileNames, directory,2);
			if(GeneralUtil.isNull(picture)){
				data = AjaxData.responseError("请上传合同副本");
				return JSON;
			}
			if(GeneralUtil.isNull(payWay)) {
				data = AjaxData.responseError(getText("payway.required"));

				return JSON;
			}
			if(GeneralUtil.isNull(signingDate)) {
				data = AjaxData.responseError(getText("signingDate.required"));

				return JSON;
			}
			if(GeneralUtil.isNull(firstRenewMoney)) {
				data = AjaxData.responseError(getText("firstRenewMoney.required"));

				return JSON;
			}
			agreementState=3;
			//修改客户合同实体
			boolean isSuc = agreementService.merge(id, contacts, phone, email, payWay, 
					signingDate, firstRenewMoney,agreementState,picture,qhRemark);
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
	 * 管理客户合同实体
	 * @Title: AgreementAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		String method = this.getClass().getSimpleName() + "_" + new Exception().getStackTrace()[0].getMethodName();
		
		SystemUser user = (SystemUser)ActionContext.getContext().getSession().get(ContextConstants.SCOPE_SYSTEM_USER);
		if(user instanceof EmployeeInfo){
			EmployeeInfo info = (EmployeeInfo) user;
			
			List<DataAuthoritys> authoritys = dataAuthoritysService.queryByEmployeeId(info.getId());
			if(authoritys != null){
				for(DataAuthoritys authority:authoritys){
					if(authority.getInMethod().equals(method)){
						if(authority.getContent().equals("one")){
							this.setPageModule(agreementService.queryAll(info, "0", ContextConstants.ADMIN_COMMON_PAGE_SIZE));
						}
						if(authority.getContent().equals("departMent")){
							this.setPageModule(agreementService.queryAllWhereIsDepartMent(info, "0",  ContextConstants.ADMIN_COMMON_PAGE_SIZE));
						}
						if(authority.getContent().equals("all")){
							this.setPageModule(agreementService.queryAll(null, "0", ContextConstants.ADMIN_COMMON_PAGE_SIZE));
						}
					}
				}
			}
		}else{
			this.setPageModule(agreementService.queryAll(null, "0", ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		}
		
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageAgreement";
	}
	
	/**
	 * 管理员工申请合同
	 * @Title: AgreementAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manageEmployee() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(agreementService.queryAll(null, "0", ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageEmployeeAgreement";
	
	}
	
	
	/**
	 * 管理审批客户合同实体
	 * @Title: AgreementAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manageSp() throws ServiceException {
		String method = this.getClass().getSimpleName() + "_" + new Exception().getStackTrace()[0].getMethodName();
		
		SystemUser user = (SystemUser)ActionContext.getContext().getSession().get(ContextConstants.SCOPE_SYSTEM_USER);
		if(user instanceof EmployeeInfo){
			EmployeeInfo info = (EmployeeInfo) user;
			
			List<DataAuthoritys> authoritys = dataAuthoritysService.queryByEmployeeId(info.getId());
			if(authoritys != null){
				for(DataAuthoritys authority:authoritys){
					if(authority.getInMethod().equals(method)){
						if(authority.getContent().equals("one")){
							this.setPageModule(agreementService.queryAll(info, "0,1,2", ContextConstants.ADMIN_COMMON_PAGE_SIZE));
						}
						if(authority.getContent().equals("departMent")){
							this.setPageModule(agreementService.queryAllWhereIsDepartMent(info, "0,1,2", ContextConstants.ADMIN_COMMON_PAGE_SIZE));
						}
						if(authority.getContent().equals("all")){
							this.setPageModule(agreementService.queryAll(null, "0,1,2", ContextConstants.ADMIN_COMMON_PAGE_SIZE));
						}
					}
				}
			}
		}else{
			this.setPageModule(agreementService.queryAll(null, "0,1,2", ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		}
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageSpAgreement";
	}
	
	
	/**
	 * 管理审批客户合同实体
	 * @Title: AgreementAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manageQh() throws ServiceException {
		
		String method = this.getClass().getSimpleName() + "_" + new Exception().getStackTrace()[0].getMethodName();
		
		
		SystemUser user = (SystemUser)ActionContext.getContext().getSession().get(ContextConstants.SCOPE_SYSTEM_USER);
		if(user instanceof EmployeeInfo){
			EmployeeInfo info = (EmployeeInfo) user;
			
			List<DataAuthoritys> authoritys = dataAuthoritysService.queryByEmployeeId(info.getId());
			if(authoritys != null){
				for(DataAuthoritys authority:authoritys){
					if(authority.getInMethod().equals(method)){
						if(authority.getContent().equals("one")){
							this.setPageModule(agreementService.queryAll(info, "3,4,5", ContextConstants.ADMIN_COMMON_PAGE_SIZE));
						}
						if(authority.getContent().equals("departMent")){
							this.setPageModule(agreementService.queryAllWhereIsDepartMent(info, "3,4,5", ContextConstants.ADMIN_COMMON_PAGE_SIZE));
						}
						if(authority.getContent().equals("all")){
							this.setPageModule(agreementService.queryAll(null, "3,4,5", ContextConstants.ADMIN_COMMON_PAGE_SIZE));
						}
					}
				}
			}
		}else{
			this.setPageModule(agreementService.queryAll(null, "3,4,5", ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		}
		
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageQhAgreement";
	}
	
	
	/**
	 * 查看回收站
	 * @Title: AgreementAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(agreementService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleAgreement";
	}
	
	/**
	 * 逻辑删除客户合同对象
	 * @Title: AgreementAction
	 * @Description: 把客户合同对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = agreementService.logicDeleteEntity(Agreement.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除客户合同对象
	 * @Title: AgreementAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = agreementService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个客户合同对象
	 * @Title: AgreementAction
	 * @Description: 从回收站还原客户合同对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = agreementService.restoreEntity(Agreement.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核客户合同对象
	 * @Title: AgreementAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = agreementService.auditEntity(Agreement.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: AgreementAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = agreementService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public Agreement getAgreement() {
		return agreement;
	}
	public void setAgreement(Agreement agreement) {
		this.agreement = agreement;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getApplayClassId() {
		return applayClassId;
	}
	public void setApplayClassId(String applayClassId) {
		this.applayClassId = applayClassId;
	}
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public Integer getCanApplayCount() {
		return canApplayCount;
	}
	public void setCanApplayCount(Integer canApplayCount) {
		this.canApplayCount = canApplayCount;
	}
	public Integer getGotCount() {
		return gotCount;
	}
	public void setGotCount(Integer gotCount) {
		this.gotCount = gotCount;
	}
	public String getChargeStatus() {
		return chargeStatus;
	}
	public void setChargeStatus(String chargeStatus) {
		this.chargeStatus = chargeStatus;
	}
	public Date getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}
	public Date getSigningDate() {
		return signingDate;
	}
	public void setSigningDate(Date signingDate) {
		this.signingDate = signingDate;
	}
	public Date getReceivablesDate() {
		return receivablesDate;
	}
	public void setReceivablesDate(Date receivablesDate) {
		this.receivablesDate = receivablesDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	
	public Date getOpenAccountDate() {
		return openAccountDate;
	}
	public void setOpenAccountDate(Date openAccountDate) {
		this.openAccountDate = openAccountDate;
	}
	public Date getOnlineDate() {
		return onlineDate;
	}
	public void setOnlineDate(Date onlineDate) {
		this.onlineDate = onlineDate;
	}
	public Double getFirstRenewMoney() {
		return firstRenewMoney;
	}
	public void setFirstRenewMoney(Double firstRenewMoney) {
		this.firstRenewMoney = firstRenewMoney;
	}
	public Date getFirstRenewDate() {
		return firstRenewDate;
	}
	public void setFirstRenewDate(Date firstRenewDate) {
		this.firstRenewDate = firstRenewDate;
	}
	public Date getLastCheckDate() {
		return lastCheckDate;
	}
	public void setLastCheckDate(Date lastCheckDate) {
		this.lastCheckDate = lastCheckDate;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public Double getAmountOfMoney() {
		return amountOfMoney;
	}
	public void setAmountOfMoney(Double amountOfMoney) {
		this.amountOfMoney = amountOfMoney;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setAgreementState(Integer agreementState) {
		this.agreementState = agreementState;
	}

	public Integer getAgreementState() {
		return agreementState;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getContacts() {
		return contacts;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	public String getPayWay() {
		return payWay;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public String getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}

	public ChannelCustomerInfo getChannelCustomerInfo() {
		return channelCustomerInfo;
	}

	public void setChannelCustomerInfo(ChannelCustomerInfo channelCustomerInfo) {
		this.channelCustomerInfo = channelCustomerInfo;
	}

	public String getSecondAuditRemark() {
		return secondAuditRemark;
	}

	public void setSecondAuditRemark(String secondAuditRemark) {
		this.secondAuditRemark = secondAuditRemark;
	}

	public Integer getSecondAuditState() {
		return secondAuditState;
	}

	public void setSecondAuditState(Integer secondAuditState) {
		this.secondAuditState = secondAuditState;
	}

	public String getQhRemark() {
		return qhRemark;
	}

	public void setQhRemark(String qhRemark) {
		this.qhRemark = qhRemark;
	}

	public IAgreementService getAgreementService() {
		return agreementService;
	}

	public void setAgreementService(IAgreementService agreementService) {
		this.agreementService = agreementService;
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

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
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

	public IApplayClassService getApplayClassService() {
		return applayClassService;
	}

	public void setApplayClassService(IApplayClassService applayClassService) {
		this.applayClassService = applayClassService;
	}

	public IChannelTypeService getChannelTypeService() {
		return channelTypeService;
	}

	public void setChannelTypeService(IChannelTypeService channelTypeService) {
		this.channelTypeService = channelTypeService;
	}
	public String getEmployeeInfo_id() {
		return employeeInfo_id;
	}
	public void setEmployeeInfo_id(String employeeInfo_id) {
		this.employeeInfo_id = employeeInfo_id;
	}
	
}
