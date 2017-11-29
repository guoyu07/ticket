package com.ticket.tags;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.constants.ContextConstants;
import com.ticket.pojo.AdvertType;
import com.ticket.pojo.Agreement;
import com.ticket.pojo.AirportBusinessType;
import com.ticket.pojo.AirportDepartment;
import com.ticket.pojo.ApplayClass;
import com.ticket.pojo.BjdjServiceCode;
import com.ticket.pojo.BusinessInfo;
import com.ticket.pojo.ChannelCustomerInfo;
import com.ticket.pojo.ChannelType;
import com.ticket.pojo.DepartmentInfo;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.FavouredPolicy;
import com.ticket.pojo.GoodsLostPostions;
import com.ticket.pojo.LetterStation;
import com.ticket.pojo.LostGoodsInfo;
import com.ticket.pojo.Member;
import com.ticket.pojo.MemberLevel;
import com.ticket.pojo.News;
import com.ticket.pojo.NewsClass;
import com.ticket.pojo.PageRedirectTemplate;
import com.ticket.pojo.Position;
import com.ticket.pojo.PrivilegeInfo;
import com.ticket.pojo.QuickMenu;
import com.ticket.pojo.RegulationType;
import com.ticket.pojo.RoleInfo;
import com.ticket.pojo.SurveyOption;
import com.ticket.pojo.SurveyQuestion;
import com.ticket.pojo.SurveyQuestionnaire;
import com.ticket.pojo.SurvryModular;
import com.ticket.pojo.SystemDictionary;
import com.ticket.pojo.SystemModule;
import com.ticket.pojo.SystemUser;
import com.ticket.pojo.SystemUserLoginLog;
import com.ticket.pojo.TrafficLine;
import com.ticket.pojo.TrafficStation;
import com.ticket.pojo.TrafficType;
import com.ticket.pojo.UserRole;
import com.ticket.service.IAdvertService;
import com.ticket.service.IAdvertTypeService;
import com.ticket.service.IAgreementService;
import com.ticket.service.IAirportBusinessTypeService;
import com.ticket.service.IAirportDepartmentService;
import com.ticket.service.IApplayClassService;
import com.ticket.service.IBjdjServiceCodeService;
import com.ticket.service.IBusinessInfoService;
import com.ticket.service.IChannelCustomerInfoService;
import com.ticket.service.IChannelTypeService;
import com.ticket.service.ICustomerAccountService;
import com.ticket.service.IDepartmentInfoService;
import com.ticket.service.IEmployeeInfoService;
import com.ticket.service.IFavouredPolicyService;
import com.ticket.service.IGoodsLostPostionsService;
import com.ticket.service.ILetterStationService;
import com.ticket.service.ILostGoodsInfoService;
import com.ticket.service.IMemberLevelService;
import com.ticket.service.IMemberService;
import com.ticket.service.INewsClassService;
import com.ticket.service.INewsService;
import com.ticket.service.IOrderInfoService;
import com.ticket.service.IPageRedirectTemplateService;
import com.ticket.service.IPositionService;
import com.ticket.service.IPrivilegeInfoService;
import com.ticket.service.IQuickMenuService;
import com.ticket.service.IRegulationTypeService;
import com.ticket.service.IRoleInfoService;
import com.ticket.service.ISurveyOptionService;
import com.ticket.service.ISurveyQuestionService;
import com.ticket.service.ISurveyQuestionnaireService;
import com.ticket.service.ISurvryModularService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.service.ISystemModuleService;
import com.ticket.service.ISystemUserLoginLogService;
import com.ticket.service.ISystemUserService;
import com.ticket.service.ITrafficLineService;
import com.ticket.service.ITrafficStationService;
import com.ticket.service.ITrafficTypeService;
import com.ticket.service.IUserRoleService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SpringUtil;

/**
 * 后台系统共用标签
 * @ClassName: CommonTag   
 * @Description: 对于台都会用到的一些功能.
 * @author HiSay  
 * @date Oct 10, 2013 22:24:17 PM   
 *
 */
public class SystemCommonTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	
	private String versionFlag = ContextConstants.VERSION_FLAG_OF_CHINESE; //语言版本标识,默认为中文版.
	private String type = null;        //自定义标签业务分类, 用type属性来区分自定义标签处理的不同的业务方法.默认为获取constants.properties里面的value值
	private Integer size = 1;          //当获取列表的时候. 这个size表示获取的数量.默认取1条.
	private String value = null;       //辅助传值进来. 
	private String userId = null;     //用户id
	private String roleId = null;		//角色id
	
	private static final ISystemModuleService systemModuleService = (ISystemModuleService)SpringUtil.getObjectFromSpring("systemModuleService");
	private static final ISystemUserLoginLogService systemUserLoginLogService = (ISystemUserLoginLogService)SpringUtil.getObjectFromSpring("systemUserLoginLogService");
	//角色信息的业务层
	private static final IRoleInfoService roleInfoService = (IRoleInfoService)SpringUtil.getObjectFromSpring("roleInfoService");
	//权限信息的业务层
	private static final IPrivilegeInfoService privilegeInfoService = (IPrivilegeInfoService) SpringUtil.getObjectFromSpring("privilegeInfoService");
	//用户角色关系的业务层
	private static final IUserRoleService userRoleService = (IUserRoleService) SpringUtil.getObjectFromSpring("userRoleService");
	//会员等级的业务层
	private static final IMemberLevelService memberLevelService = (IMemberLevelService)SpringUtil.getObjectFromSpring("memberLevelService");
	//会员信息的业务层
	private static final IMemberService memberService = (IMemberService) SpringUtil.getObjectFromSpring("memberService");
	//新闻类别的业务层
	private static final INewsClassService newsClassService = (INewsClassService)SpringUtil.getObjectFromSpring("newsClassService");
	//新闻信息的业务层
	private static final INewsService newsService = (INewsService) SpringUtil.getObjectFromSpring("newsService");
	//页面跳转模板的业务层
	private static final IPageRedirectTemplateService pageRedirectTemplateService = (IPageRedirectTemplateService) SpringUtil.getObjectFromSpring("pageRedirectTemplateService");
	//广告类型的业务层
	private static final IAdvertTypeService advertTypeService = (IAdvertTypeService) SpringUtil.getObjectFromSpring("advertTypeService");
	//广告信息的业务层
	private static final IAdvertService advertService = (IAdvertService) SpringUtil.getObjectFromSpring("advertService");

	//渠道分类的业务层
	private static final IChannelTypeService channelTypeService = (IChannelTypeService) SpringUtil.getObjectFromSpring("channelTypeService");
	//申请类别业务层
	private static final IApplayClassService applayClassService = (IApplayClassService) SpringUtil.getObjectFromSpring("applayClassService");
	
	private static final IOrderInfoService orderInfoService = (IOrderInfoService) SpringUtil.getObjectFromSpring("orderInfoService");

	//渠道客户的业务层
	private static final IChannelCustomerInfoService channelCustomerInfoService = (IChannelCustomerInfoService) SpringUtil.getObjectFromSpring("channelCustomerInfoService");
	//优惠政策的业务层
	private static final IFavouredPolicyService favouredPolicyService = (IFavouredPolicyService) SpringUtil.getObjectFromSpring("favouredPolicyService");
	
	//部门信息的业务层
	private static final IDepartmentInfoService departmentInfoService = (IDepartmentInfoService) SpringUtil.getObjectFromSpring("departmentInfoService");
	//员工信息的业务层
	private static final IEmployeeInfoService employeeInfoService = (IEmployeeInfoService) SpringUtil.getObjectFromSpring("employeeInfoService");

	//调查模块的业务层
	private static final ISurvryModularService survryModularService = (ISurvryModularService) SpringUtil.getObjectFromSpring("survryModularService");
	
	//调查问卷的业务层
	private static final ISurveyQuestionnaireService surveyQuestionnaireService = (ISurveyQuestionnaireService) SpringUtil.getObjectFromSpring("surveyQuestionnaireService");
	
	//调查模块的业务层
	private static final ISurveyQuestionService surveyQuestionService = (ISurveyQuestionService) SpringUtil.getObjectFromSpring("surveyQuestionService");
	
	//调查模块的业务层
	private static final ISurveyOptionService surveyOptionService = (ISurveyOptionService) SpringUtil.getObjectFromSpring("surveyOptionService");
	//机场商业类别的业务层
	private static final IAirportBusinessTypeService airportBusinessTypeService = (IAirportBusinessTypeService) SpringUtil.getObjectFromSpring("airportBusinessTypeService");
	//机场商家信息的业务层
	private static final IBusinessInfoService businessInfoService = (IBusinessInfoService) SpringUtil.getObjectFromSpring("businessInfoService");
	//机场部门的业务层
	private static final IAirportDepartmentService airportDepartmentService = (IAirportDepartmentService) SpringUtil.getObjectFromSpring("airportDepartmentService");
	//服务码业务层
	private static final IBjdjServiceCodeService bjdjServiceCodeService = (IBjdjServiceCodeService) SpringUtil.getObjectFromSpring("bjdjServiceCodeService");
	
	//字典业务层
	private static final ISystemDictionaryService systemDictionaryService = (ISystemDictionaryService) SpringUtil.getObjectFromSpring("systemDictionaryService");
	//交通路线类别业务层
	private static final ITrafficTypeService trafficTypeService = (ITrafficTypeService) SpringUtil.getObjectFromSpring("trafficTypeService");
	//交通路线业务层
	private static final ITrafficLineService trafficLineService = (ITrafficLineService) SpringUtil.getObjectFromSpring("trafficLineService");
	//交通路线车站业务层
	private static final ITrafficStationService trafficStationService = (ITrafficStationService) SpringUtil.getObjectFromSpring("trafficStationService");
	//机场制度类别业务层
	private static final IRegulationTypeService regulationTypeService = (IRegulationTypeService) SpringUtil.getObjectFromSpring("regulationTypeService");
	//遗失物品位置的业务层
	private static final IGoodsLostPostionsService goodsLostPostionsService = (IGoodsLostPostionsService) SpringUtil.getObjectFromSpring("goodsLostPostionsService");
	//遗失物品的业务层
	private static final ILostGoodsInfoService lostGoodsInfoService = (ILostGoodsInfoService) SpringUtil.getObjectFromSpring("lostGoodsInfoService");
	//快捷菜单的业务层
	private static final IQuickMenuService quickMenuService = (IQuickMenuService) SpringUtil.getObjectFromSpring("quickMenuService");
	//客户账号的业务层
	private static final ICustomerAccountService customerAccountService = (ICustomerAccountService) SpringUtil.getObjectFromSpring("customerAccountService");
	private static final IPositionService positionService = (IPositionService) SpringUtil.getObjectFromSpring("positionService");
	private static final ISystemUserService systemUserService = (ISystemUserService) SpringUtil.getObjectFromSpring("systemUserService");
	private static final IAgreementService agreementService = (IAgreementService) SpringUtil.getObjectFromSpring("agreementService");
	//站内信业务层
	private static final ILetterStationService letterStationService = (ILetterStationService) SpringUtil.getObjectFromSpring("letterStationService");
	
	@Override
	public int doAfterBody() throws JspException {
		return super.doAfterBody();
	}

	@Override
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}

	@Override
	@SuppressWarnings("unused")
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			if("systemModuleObj".equals(type)) {//根据模块ID获取实体对象
				SystemModule module = systemModuleService.queryById(SystemModule.class.getSimpleName(), value) ;
				if(module != null) {
					request.setAttribute(type, module) ;
				} else {
					request.removeAttribute(type) ;
				}
			} else if("childSystemModuleList".equals(type)) { //验证模块ID是否有子模块列表
				
				List<SystemModule> childList = null;
				SystemUser systemUser = (SystemUser)ActionContext.getContext().getSession().get(ContextConstants.SCOPE_SYSTEM_USER);
				if(ContextConstants.ADMIN.equals(systemUser.getLoginId())){
					
					childList = systemModuleService.queryChildModulesByParent(value) ;
				}else{
					
					childList = systemModuleService.queryChildModulesByParent(systemUser.getId(), value) ;
				}
				if(childList != null && !childList.isEmpty()) {
					request.setAttribute(type, childList) ;
				} else {
					request.removeAttribute(type) ;
				}
			} else if("systemUserLoginLog".equals(type)) { //获取管理员上一次登陆日志时间
				SystemUserLoginLog log = systemUserLoginLogService.queryBySystemUser(value) ;
				if(log != null) {
					request.setAttribute(type, log) ;
				} else {
					request.removeAttribute(type) ;
				}
			} else if("roleList".equals(type)){
				List<RoleInfo> roleList = roleInfoService.queryList(versionFlag);
				if(roleList != null && !roleList.isEmpty()) {
					request.setAttribute(type, roleList) ;
				} else {
					request.removeAttribute(type) ;
				}
			} else if("privilegeList".equals(type)){
				List<PrivilegeInfo> privilegeList = privilegeInfoService.queryList(versionFlag);
				if(privilegeList != null && !privilegeList.isEmpty()) {
					request.setAttribute(type, privilegeList) ;
				} else {
					request.removeAttribute(type) ;
				}
			}else if("userRoleObj".equals(type)){
				UserRole userRole = userRoleService.queryByUIdAndRID(userId, roleId, versionFlag);
				if(userRole != null){
					request.setAttribute(type, userRole);
				}else{
					request.removeAttribute(type);
				}
			}else if("roleObj".equals(type)){
				RoleInfo roleInfo = roleInfoService.queryById(RoleInfo.class.getSimpleName(), roleId);
				if(roleInfo != null){
					request.setAttribute(type, roleInfo);
				}else{
					request.removeAttribute(type);
				}
			}else if("memberLevelList".equals(type)){ //会员等级列表
				List<MemberLevel> memberLevelList = memberLevelService.queryList(versionFlag);
				if(memberLevelList!= null && !memberLevelList.isEmpty()){
					request.setAttribute(type, memberLevelList);
				}else{
					request.removeAttribute(type);
				}
			} else if("memberLevelObj".equals(type)) { //会员等级实体
				MemberLevel memberLevel = memberLevelService.queryById(MemberLevel.class.getSimpleName(), value);
				if(memberLevel != null) {
					request.setAttribute(type, memberLevel) ;
				} else {
					request.removeAttribute(type) ;
				}
			} else if("memberObj".equals(type)) {//会员实体
				Member member = memberService.queryById(Member.class.getSimpleName(), value) ;
				if(member != null) {
					request.setAttribute(type, member) ;
				} else {
					request.removeAttribute(type) ;
				}
			} else if("newsClassObj".equals(type)) {//根据模块ID获取实体对象
				NewsClass newsClass = newsClassService.queryById(NewsClass.class.getSimpleName(), value) ;
				if(newsClass != null) {
					request.setAttribute(type, newsClass) ;
				} else {
					request.removeAttribute(type) ;
				}
			} else if("newsObj".equals(type)) { //验证模块ID是否有子模块列表
				News news = newsService.queryById(News.class.getSimpleName(), value);
				if(news != null) {
					request.setAttribute(type, news) ;
				} else {
					request.removeAttribute(type) ;
				}
			} else if("pageRedirectTemplateList".equals(type)) { //页面跳转模板实体列表
				List<PageRedirectTemplate> list = pageRedirectTemplateService.queryList(versionFlag, Integer.valueOf(value)) ;
				if(list != null && !list.isEmpty()) {
					request.setAttribute(type, list) ;
				} else {
					request.removeAttribute(type) ;
				}
			}else if("advertTypeList".equals(type)){
				List<AdvertType> advertTypeList = advertTypeService.queryByList(versionFlag);
				if(advertTypeList != null && !advertTypeList.isEmpty()){
					request.setAttribute(type, advertTypeList);
				}else{
					request.removeAttribute(type);
				}
			}else if("advertTypeObj".equals(type)){
				AdvertType advertType = advertTypeService.queryById(AdvertType.class.getSimpleName(), value);
				if(advertType != null){
					request.setAttribute(type, advertType);
				}else{
					request.removeAttribute(type);
				}

			}else if("channelTypeList".equals(type)){
				List<ChannelType> channelTypeList = channelTypeService.queryList(versionFlag);
				if(channelTypeList != null && !channelTypeList.isEmpty()){
					request.setAttribute(type, channelTypeList);
				}else{
					request.removeAttribute(type);
				}
			}else if("channelEmpCustomerTypeList".equals(type)){
				List<ChannelType> list = channelTypeService.queryEmpCustomerTypeList(versionFlag);
				if(list != null && !list.isEmpty()){
					request.setAttribute(type, list);
				}else{
					request.removeAttribute(type);
				}
			}else if("channelTypeObj".equals(type)){
				ChannelType channelType = channelTypeService.get(ChannelType.class, value);
				if(channelType != null){
					request.setAttribute(type, channelType);
				}else{
					request.removeAttribute(type);
				}

			}else if("channelCustomerInfoObj".equals(type)){
				Object channelCustomerInfo = channelCustomerInfoService.queryById(ChannelCustomerInfo.class.getSimpleName(), value);
				if(channelCustomerInfo != null){
					request.setAttribute(type, channelCustomerInfo);
				}else{
					request.removeAttribute(type);
				}

			}else if("favouredPolicyList".equals(type)){
				List<FavouredPolicy> favouredPolicyList = favouredPolicyService.queryList(versionFlag);
				if(favouredPolicyList != null && !favouredPolicyList.isEmpty()){
					request.setAttribute(type, favouredPolicyList);
				}else{
					request.removeAttribute(type);
				}
			}else if("favouredPolicyObj".equals(type)){
				Object favouredPolicy = favouredPolicyService.queryById(FavouredPolicy.class.getSimpleName(), value);
				if(favouredPolicy != null){
					request.setAttribute(type, favouredPolicy);
				}else{
					request.removeAttribute(type);
				}

			}else if("applayClassList".equals(type)){
				List<ApplayClass> applayClassList = applayClassService.queryList(versionFlag);
				if(applayClassList != null && !applayClassList.isEmpty()){
					request.setAttribute(type, applayClassList);
				}else{
					request.removeAttribute(type);
				}
			}else if("applayClassObj".equals(type)){
				ApplayClass applayClass = applayClassService.queryById(ApplayClass.class.getSimpleName(), value);
				if(applayClass != null){
					request.setAttribute(type, applayClass);
				}else{
					request.removeAttribute(type);
				}

			}else if("departmentInfoList".equals(type)){
				List<DepartmentInfo> departmentInfoList = departmentInfoService.queryByList(versionFlag);
				if(departmentInfoList != null && !departmentInfoList.isEmpty()){
					request.setAttribute(type, departmentInfoList);
				}else{
					request.removeAttribute(type);
				}
			} else if("departmentInfoListByCurrentId".equals(type)){
				List<DepartmentInfo> departmentInfoList = departmentInfoService.queryListByCurrentId(value,versionFlag);
				if(departmentInfoList != null && !departmentInfoList.isEmpty()){
					request.setAttribute(type, departmentInfoList);
				}else{
					request.removeAttribute(type);
				}
			} else if("departmentInfoObj".equals(type)){
				DepartmentInfo departmentInfo = departmentInfoService.queryById(DepartmentInfo.class.getSimpleName(), value);
				List<String> departments = new LinkedList<String>();
				while(departmentInfo != null){
					
					departments.add(departmentInfo.getName());
					departmentInfo = departmentInfo.getParent();
				}
				Collections.reverse(departments);
				request.setAttribute(type, GeneralUtil.join(departments, " - "));
			}else if("employeeInfoList".equals(type)){
				List<EmployeeInfo> employeeInfoList = employeeInfoService.queryByList(versionFlag);
				if(employeeInfoList != null && !employeeInfoList.isEmpty()){
					request.setAttribute(type, employeeInfoList);
				}else{
					request.removeAttribute(type);
				}
			}else if("employeeInfoObj".equals(type)){
				EmployeeInfo employeeInfo = employeeInfoService.queryById(EmployeeInfo.class.getSimpleName(), value);
				if(employeeInfo != null){
					request.setAttribute(type, employeeInfo);
				}else{
					request.removeAttribute(type);
				}
			}else if("employeeListByDepartmentId".equals(type)){
				List<EmployeeInfo> employeeInfoList = employeeInfoService.queryByDepartmentId(value,versionFlag);
				if(employeeInfoList != null && !employeeInfoList.isEmpty()){
					request.setAttribute(type, employeeInfoList);
				}else{
					request.removeAttribute(type);
				}

			}else if("saleCount".equals(type)){
				String result =  orderInfoService.getEmployeeSaleCount(value);
				int a=0;
				
				request.setAttribute(type, result);
				
			}else if("survryModularList".equals(type)){
				List<SurvryModular> survryModularList = survryModularService.queryList(versionFlag);
				if(survryModularList != null && !survryModularList.isEmpty()){
					request.setAttribute(type, survryModularList);
				}else{
					request.removeAttribute(type);
				}
			}else if("survryModularObj".equals(type)){
				SurvryModular survryModular = survryModularService.queryById(SurvryModular.class.getSimpleName(), value);
				if(survryModular != null){
					request.setAttribute(type, survryModular);
				}else{
					request.removeAttribute(type);
				}
			
			}else if("surveyQuestionnaireList".equals(type)){
				List<SurveyQuestionnaire> surveyQuestionnaireList = surveyQuestionnaireService.queryList(versionFlag);
				if(surveyQuestionnaireList != null && !surveyQuestionnaireList.isEmpty()){
					request.setAttribute(type, surveyQuestionnaireList);
				}else{
					request.removeAttribute(type);
				}
			}else if("surveyQuestionnaireObj".equals(type)){
				SurveyQuestionnaire surveyQuestionnaire = surveyQuestionnaireService.queryById(SurveyQuestionnaire.class.getSimpleName(), value);
				if(surveyQuestionnaire != null){
					request.setAttribute(type, surveyQuestionnaire);
				}else{
					request.removeAttribute(type);
				}

			}else if("surveyQuestionList".equals(type)){
				List<SurveyQuestion> surveyQuestionList = surveyQuestionService.queryList(versionFlag);
				if(surveyQuestionList != null && !surveyQuestionList.isEmpty()){
					request.setAttribute(type, surveyQuestionList);
				}else{
					request.removeAttribute(type);
				}
			}else if("surveyQuestionObj".equals(type)){
				SurveyQuestion surveyQuestion = surveyQuestionService.queryById(SurveyQuestion.class.getSimpleName(), value);
				if(surveyQuestion != null){
					request.setAttribute(type, surveyQuestion);
				}else{
					request.removeAttribute(type);
				}
			}else if("airportBusinessTypeObj".equals(type)){
				AirportBusinessType airportBusinessType = airportBusinessTypeService.queryById(AirportBusinessType.class.getSimpleName(), value);
				if(airportBusinessType != null){
					request.setAttribute(type, airportBusinessType);
				}else{
					request.removeAttribute(type);
				}
			}else if("businessInfoObj".equals(type)){
				BusinessInfo businessInfo = businessInfoService.queryById(BusinessInfo.class.getSimpleName(), value);
				if(businessInfo != null){
					request.setAttribute(type, businessInfo);
				}else{
					request.removeAttribute(type);
				}
			}else if("airportDepartObj".equals(type)){
				AirportDepartment depart = (AirportDepartment)airportDepartmentService.queryById(AirportDepartment.class.getSimpleName(), value);
				if(depart != null){
					request.setAttribute(type, depart);
				}else{
					request.removeAttribute(type);
				}
			}else if("bjdjServiceCodeLogList".equals(type)){

			}else if("bjdjServiceCodeObj".equals(type)){
				BjdjServiceCode bjdjServiceCode = bjdjServiceCodeService.queryById(BjdjServiceCode.class.getSimpleName(), value);
			    if(bjdjServiceCode != null){
			    	request.setAttribute(type, bjdjServiceCode);
			    }else{
			    	request.removeAttribute(type);
				}
			}else if("systemDictionaryObj".equals(type)){
				SystemDictionary systemDictionary = systemDictionaryService.queryById(SystemDictionary.class.getSimpleName(), value);
				  if(systemDictionary != null){
						request.setAttribute(type, systemDictionary);
				  }else{
						request.removeAttribute(type);
				  }
			}else if("typeObj".equals(type)){
				String typeString = "";
				int index= Integer.parseInt(value);
				
				switch(index) {
				case 0:
					typeString="单选";
					break;
				case 1:
					typeString="多选";           
					break;
				case 2:
					typeString="自定义文本";
					break;
				default:
					typeString="单选";
					break;
				}
				request.setAttribute(type, typeString);

			}else if("questionTypeObj".equals(type)){
				String questionTypeString = "";
				int index= Integer.parseInt(value);
				
				switch(index) {
				case 0:
					questionTypeString="选项";
					break;
				case 1:
					questionTypeString="打分";           
					break;
				default:
					questionTypeString="选项";
					break;
				}
				request.setAttribute(type, questionTypeString);

			}else if("trafficTypeList".equals(type)){
				List<TrafficType> trafficTypeList = trafficTypeService.queryList(versionFlag);
				if(trafficTypeList != null && !trafficTypeList.isEmpty()){
					request.setAttribute(type, trafficTypeList);
				}else{
					request.removeAttribute(type);
				}
			}else if("trafficTypeObj".equals(type)){
				TrafficType trafficTypeObj = trafficTypeService.queryById(TrafficType.class.getSimpleName(), value);
				  if(trafficTypeObj != null){
						request.setAttribute(type, trafficTypeObj);
				  }else{
						request.removeAttribute(type);
				  }
			}else if("trafficLineList".equals(type)){
				List<TrafficLine> trafficLineList = trafficLineService.queryList(versionFlag);
				if(trafficLineList != null && !trafficLineList.isEmpty()){
					request.setAttribute(type, trafficLineList);
				}else{
					request.removeAttribute(type);
				}
			} else if("trafficStationList".equals(type)){
				List<TrafficStation> trafficStationList = trafficStationService.queryList(versionFlag);
				if(trafficStationList != null && !trafficStationList.isEmpty()){
					request.setAttribute(type, trafficStationList);
				}else{
					request.removeAttribute(type);
				}
			} else if("trafficLineObj".equals(type)){
				TrafficLine trafficLineObj = trafficLineService.queryById(TrafficLine.class.getSimpleName(), value);
				  if(trafficLineObj != null){
						request.setAttribute(type, trafficLineObj);
				  }else{
						request.removeAttribute(type);
				  }
			} else if("trafficStationObj".equals(type)){
				TrafficStation trafficStationObj = trafficStationService.queryById(TrafficStation.class.getSimpleName(), value);
				  if(trafficStationObj != null){
						request.setAttribute(type, trafficStationObj);
				  }else{
						request.removeAttribute(type);
				  }
			} else if("regulationTypeList".equals(type)){
				List<RegulationType> list = regulationTypeService.queryList(versionFlag);
				if(list != null && !list.isEmpty()){
					request.setAttribute(type, list);
				}else{
					request.removeAttribute(type);
				}
			}else if("regulationTypeObj".equals(type)){
				RegulationType regulationType = regulationTypeService.queryById(RegulationType.class.getSimpleName(), value);
				  if(regulationType != null){
						request.setAttribute(type, regulationType);
				  }else{
						request.removeAttribute(type);
				  }
			} else if ("goodsTypeList".equals(type)) {//遗失物品类别列表
				List<SystemDictionary> list = systemDictionaryService.querySubByParentValue("lostGoodsType");
				if (list != null && !list.isEmpty()) {
					request.setAttribute(type, list);
				} else {
					request.removeAttribute(type);
				}
			} else if ("goodsColorList".equals(type)) {//遗失物品类别列表
				List<SystemDictionary> list = systemDictionaryService.querySubByParentValue("lostGoodsColor");
				if (list != null && !list.isEmpty()) {
					request.setAttribute(type, list);
				} else {
					request.removeAttribute(type);
				}
			} else if("goodsColor".equals(type)){//遗失物品的颜色
				SystemDictionary dictionary = systemDictionaryService.queryById(SystemDictionary.class.getSimpleName(), value);
				if(GeneralUtil.isNotNull(dictionary)){
					request.setAttribute(type, dictionary);
				}else{
					request.removeAttribute(type);
				}
			} else if("lostPosition".equals(type)){//遗失物品位置/拾取位置
				GoodsLostPostions dictionary = goodsLostPostionsService.queryById(GoodsLostPostions.class.getSimpleName(), value);
				if(GeneralUtil.isNotNull(dictionary)){
					request.setAttribute(type, dictionary);
				}else{
					request.removeAttribute(type);
				}
			}else if ("systemDictionayList".equals(type)) {//遗失物品类别列表
				List<SystemDictionary> list = systemDictionaryService.querySubByParentValue(value);
				if (list != null && !list.isEmpty()) {
					request.setAttribute(type, list);
				} else {
					request.removeAttribute(type);
				}
			}
			
			else if ("lostPositionList".equals(type)) {//物品遗失位置列表
				List<GoodsLostPostions> list = goodsLostPostionsService.queryByList(versionFlag);
				if (list != null && !list.isEmpty()) {
					request.setAttribute(type, list);
				} else {
					request.removeAttribute(type);
				}
			} else if ("receiveWayObj".equals(type)) {//物品遗失位置列表
				Object obj = systemDictionaryService.queryById(SystemDictionary.class.getSimpleName(), value);
				if (obj != null) {
					request.setAttribute(type, obj);
				} else {
					request.removeAttribute(type);
				}
			} else if ("receiveWayList".equals(type)) {//领取方式
				List<SystemDictionary> list = systemDictionaryService.querySubByParentValue("receiveWay");
				if (list != null && !list.isEmpty()) {
					request.setAttribute(type, list);
				} else {
					request.removeAttribute(type);
				}
			} else if ("certificateTypeObj".equals(type)) {//物品遗失位置列表
				Object obj = systemDictionaryService.queryById(SystemDictionary.class.getSimpleName(), value);
				if (obj != null) {
					request.setAttribute(type, obj);
				} else {
					request.removeAttribute(type);
				}
			} else if ("certificateTypeList".equals(type)) {//证件类型
				List<SystemDictionary> list = systemDictionaryService.querySubByParentValue("certificateType");
				if (list != null && !list.isEmpty()) {
					request.setAttribute(type, list);
				} else {
					request.removeAttribute(type);
				}
			} else if("lostGoodsInfoObj".equals(type)){ //遗失物品实体
				LostGoodsInfo lostGoodsInfo = lostGoodsInfoService.queryById(LostGoodsInfo.class.getSimpleName(), value);
				  if(lostGoodsInfo != null){
						request.setAttribute(type, lostGoodsInfo);
				  }else{
						request.removeAttribute(type);
				  }
			}else if("goodsLostPositionObj".equals(type)){//物品遗失位置
				GoodsLostPostions glp = goodsLostPostionsService.queryById(GoodsLostPostions.class.getSimpleName(), value);
				  if(glp != null){
						request.setAttribute(type, glp);
				  }else{
						request.removeAttribute(type);
				  }
			} else if("quickMenuList".equals(type)){
				List<QuickMenu> list = quickMenuService.queryList(versionFlag);
				if(list != null && !list.isEmpty()){
					request.setAttribute(type, list);
				}else{
					request.removeAttribute(type);
				}
			} else if("quickMenuObj".equals(type)){
				QuickMenu quickMenu = quickMenuService.queryById(QuickMenu.class.getSimpleName(), value);
				if(quickMenu != null){
					request.setAttribute(type, quickMenu);
				}else{
					request.removeAttribute(type);
				}
			} else if("getCustomerAccountSumMoney".equals(type)){//获取账户总金额
				out.print(customerAccountService.getSumMoney(value));
			}else if("getSystemDictionatyObjByName".equals(type)){//根据数据字典name获取数据字典对象
				SystemDictionary systemDictionary = systemDictionaryService.getByName(value);
				if(systemDictionary != null){
					request.setAttribute(type, systemDictionary);
				}else{
					request.removeAttribute(type);
				}
			}else if("getPositionObj".equals(type)){//获取岗位对象
				Position position = positionService.queryById(Position.class.getSimpleName(), value);
				if(position != null){
					request.setAttribute(type, position);
				}else{
					request.removeAttribute(type);
				}
			}else if("getSystemUserObj".equals(type)){//获取用户对象
				SystemUser systemUser = systemUserService.queryById(SystemUser.class.getSimpleName(), value);
				if(systemUser != null){
					request.setAttribute(type, systemUser);
				}else{
					request.removeAttribute(type);
				}
			}else if("getChannelCustomerInfoState".equals(type)){//获取客户状态
				out.print(channelCustomerInfoService.getChannelCustomerInfoState(value));
			}else if("getNewsListByNewsClassId".equals(type)){//根据类别id获取新闻列表
				List<News> list = newsService.queryByNewsClassId(value, null, null, null, size);
				if(list != null && list.size() > 0){
					request.setAttribute(type, list);
				}else{
					request.removeAttribute(type);
				}
			}else if("getNewsClassObj".equals(type)){//根据栏目类别别名获取栏目对象
				NewsClass newsClass = newsClassService.queryByAlias(versionFlag, value);
				if(newsClass != null){
					request.setAttribute(type, newsClass);
				}else{
					request.removeAttribute(type);
				}
			}else if("getAgreementObj".equals(type)){//根据合同id获取合同对象
				Agreement agreement = agreementService.queryById(Agreement.class.getSimpleName(), value);
				if(agreement != null){
					request.setAttribute(type, agreement);
				}else{
					request.removeAttribute(type);
				}
			} else if("businessTypeList".equals(type)){
				List<AirportBusinessType> list = airportBusinessTypeService.queryChildList(versionFlag);
				if(list != null && !list.isEmpty()){
					request.setAttribute(type, list);
				}else{
					request.removeAttribute(type);
				}
			} else if("employeeList".equals(type)){
				List<EmployeeInfo> list = employeeInfoService.queryByList(versionFlag);
				if(list != null && !list.isEmpty()){
					request.setAttribute(type, list);
				}else{
					request.removeAttribute(type);
				}
			}else if("questionOptionByQuestionId".equals(type)){
				List<SurveyOption> list = surveyOptionService.queryByQuestionId(value,versionFlag);
				if(list != null && !list.isEmpty()){
					request.setAttribute(type, list);
				}else{
					request.removeAttribute(type);
				}
			} else if("letterStationObj".equals(type)){
				LetterStation letterStation = letterStationService.queryById(LetterStation.class.getSimpleName(), value);
				if(letterStation != null){
					request.setAttribute(type, letterStation);
				}else{
					request.removeAttribute(type);
				}
			} else if("positionObj".equals(type)){
				Position position = positionService.queryById(Position.class.getName(), value);
				if(position != null){
					request.setAttribute(type, position);
				}else{
					request.removeAttribute(type);
				}
			}else if("employeeByCustomerId".equals(type)){
				ChannelCustomerInfo channelCustomerInfo = channelCustomerInfoService.queryById(ChannelCustomerInfo.class.getName(), value);
				EmployeeInfo employeeInfo = null;
				if(channelCustomerInfo != null){
					employeeInfo = channelCustomerInfo.getEmployeeInfo();
				}
				if(employeeInfo != null){
					request.setAttribute(type, employeeInfo);
				}else{
					request.removeAttribute(type);
				}
			}else if("AgreeMentObj".equals(type)){
				Agreement agreement = agreementService.querybyCustomerId(value);
				if(agreement != null){
					request.setAttribute(type, agreement);
				}else{
					request.removeAttribute(type);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

	public String getVersionFlag() {
		return versionFlag;
	}

	public void setVersionFlag(String versionFlag) {
		this.versionFlag = versionFlag;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}