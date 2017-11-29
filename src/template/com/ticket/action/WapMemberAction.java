package com.ticket.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;

import com.hp.hpl.sparta.ParseException;
import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.AirportInfo;
import com.ticket.pojo.AirportPlan;
import com.ticket.pojo.ArrivalAtPort;
import com.ticket.pojo.CheckinInfo;
import com.ticket.pojo.DepartFromPort;
import com.ticket.pojo.Member;
import com.ticket.pojo.MemberAnnouncement;
import com.ticket.pojo.MemberDetailInfo;
import com.ticket.pojo.MemberFavorite;
import com.ticket.pojo.MemberFocusFlight;
import com.ticket.pojo.MemberLogo;
import com.ticket.pojo.MemberSendInfo;
import com.ticket.pojo.QuickMenu;
import com.ticket.pojo.QuickMenuMemberSetting;
import com.ticket.pojo.SecurityData;
import com.ticket.pojo.SystemDictionary;
import com.ticket.pojo.SystemUser;
import com.ticket.service.IAirportInfoService;
import com.ticket.service.IArrivalAtPortService;
import com.ticket.service.IAutoSendMessageService;
import com.ticket.service.IBjdjAppointmentService;
import com.ticket.service.IBjdjOrderService;
import com.ticket.service.ICheckinInfoService;
import com.ticket.service.IDepartFromPortService;
import com.ticket.service.IFCSelfCheckinPositionService;
import com.ticket.service.IFlightCompanyService;
import com.ticket.service.IInDataService;
import com.ticket.service.IMemberAnnouncementService;
import com.ticket.service.IMemberDetailInfoService;
import com.ticket.service.IMemberFavoriteService;
import com.ticket.service.IMemberFocusFlightService;
import com.ticket.service.IMemberLogoService;
import com.ticket.service.IMemberSendInfoService;
import com.ticket.service.IMemberService;
import com.ticket.service.IMessageReadLogService;
import com.ticket.service.IPageCacheGroupService;
import com.ticket.service.IQuickMenuMemberSettingService;
import com.ticket.service.IQuickMenuService;
import com.ticket.service.ISecurityDataService;
import com.ticket.service.ISpecialPersonWithQuickMenuService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.util.AirportPlaneUtil;
import com.ticket.util.AuthCodeUtil;
import com.ticket.util.ConvertUtil;
import com.ticket.util.CookiesUtil;
import com.ticket.util.DateUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.MD5Util;

/**
 * 会员中心控制器
 * @author dfq
 * @datetime 2015-11-07 13:48:00
 */
public class WapMemberAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Resource protected IMemberService memberService = null;
	@Resource protected IQuickMenuMemberSettingService quickMenuMemberSettingService = null;
	@Resource protected IQuickMenuService quickMenuService = null;
	@Resource protected IMemberFavoriteService memberFavoriteService = null;
	@Resource protected IMemberDetailInfoService memberDetailInfoService = null;
	@Resource protected IMemberFocusFlightService memberFocusFlightService = null;
	@Resource protected IDepartFromPortService departFromPortService = null;
	@Resource protected IArrivalAtPortService arrivalAtPortService = null;
	@Resource protected IBjdjOrderService orderService; //订单业务层
	@Resource protected ISystemDictionaryService systemDictionaryService; //系统字典业务层
	@Resource protected ISpecialPersonWithQuickMenuService specialPersonWithQuickMenuService; //服务菜单业务层
	@Resource protected IMessageReadLogService messageReadLogService; //系统消息阅读日志业务层
	@Resource protected IPageCacheGroupService groupService; //系统消息阅读日志业务层
	@Resource protected ICheckinInfoService checkinInfoService;
	@Resource protected IMemberSendInfoService memberSendInfoService;
	@Resource protected IMemberLogoService logoService;
	@Resource protected IInDataService airportPlanService;
	@Resource protected IAirportInfoService airportInfoService;
	@Resource protected ISecurityDataService securityDataService;
	@Resource protected IFlightCompanyService flightCompanyService;
	@Resource protected IFCSelfCheckinPositionService fCSelfCheckinPositionService;//航空公司自助值机点位
	@Resource protected IAutoSendMessageService autoSendMessageService;//消息自动推送
	@Resource protected IBjdjAppointmentService bjdjAppointmentService;//便捷登机业务层
	@Resource protected IInDataService inDataService;//计划航班业务层
	@Resource protected IMemberAnnouncementService memberAnnouncementService;
	
	
	protected static final ConvertUtil<String> convertUtil = new ConvertUtil<String>();
	
	protected String mobile = null; //手机号码
	protected String password = null; //密码
	protected String recheckPwd = null; //密码
	protected String defaultPosition = null;//设置快捷菜单到哪个位置
	protected String orderStatus = null;//订单状态
	protected String objectId = null;//会员收藏ID
	protected String objectType = null; //会员收藏类型
	protected String title = null; //收藏标题
	protected String url = null;  //收藏链接
	protected String id = null;//实体id
	//会员详细信息
	protected MemberDetailInfo memberDetailInfo = null;
	
	//头像信息
	protected String images;
	//会员id
	protected String member_id = null;
    //性别
	protected String sex = null;
    //护照信息
	protected String passport = null;
    //国籍
	protected String nationality = null;
    //台胞证
	protected String MTP = null;
    //企业性质
	protected String enterpriseProperty = null;
    //公司名称
	protected String companyName = null;
    //政治面貌
	protected String politicalOutLook = null;
    //头衔
	protected String honor = null;
    //职级
	protected String rank = null;
    //职位
	protected String occupation = null;
    //收入
	protected String income = null;
    //籍贯
	protected String nativePlace = null;
    //家庭住址
	protected String homeAddress = null;
    //年龄
	protected String age = null;
    //生日
	protected Date birthday = null;
    //学历
	protected String education = null;
    //学校
	protected String school = null;
    //专业
	protected String major = null;
    //星座
	protected String constellation = null;
    //属相
	protected String zodiac = null;
    //婚配
	protected String marriage = null;
    //血型
	protected String bloodType = null;
    //身高
	protected String height = null;
    //体重
	protected String weight = null;
    //邮政编码
	protected String zipcode = null;
    //疾病史
	protected String diseaseHistory = null;
    //过敏原
	protected String anaphylactogen = null;
    //健康状况
	protected String healthStatus = null;
    //兴趣点
	protected String hobby = null;
    //车牌号
	protected String plateNumber = null;
    //微信号
	protected String weChatId = null;
    //微博号
	protected String weiboNumber = null;
    //人人网账号
	protected String renrenAccount = null;
    //百度账号
	protected String baiduAccount = null;
    //搜狗账号
	protected String sogouAccount = null;
    //个性签名
	protected String personalitySignature = null;
    //紧急联系人姓名
	protected String emergencyContactName = null;
    //紧急联系人性别
	protected String emergencyContactSex = null;
    //紧急联系人电话
	protected String emergencyContactPhone = null;
    //紧急联系人年龄
	protected Integer emergencyContactAge = null;
    //紧急联系人身份证
	protected String emergencyContactIDCard = null;
    //紧急联系人头衔
	protected String emergencyContacthonor = null;
    //紧急联系人邮箱
	protected String emergencyContactEmail = null;
    //与紧急联系人关系
	protected String emergencyContactRelation = null;
    //常旅客卡数据
	protected String visitorCardData = null;
	
	//昵称
	protected String nickName = null;
    //真实姓名
	protected String realName = null;
    //身份证号
	protected String IDCard = null;
    //联系电话
	protected String phone = null;
    //QQ号码
	protected String qq = null;
    //联系邮箱
	protected String email = null;
    //联系地址
	protected String address = null;
	
	//航班编号
	protected String flightNumber = null;
	//航班日期
	protected String flightDate = null; 
	//离港实体
	protected DepartFromPort departFromPort = null;
	//会员角色（乘机，送机，接机）
	protected String memberRole = null;
	//航班状态（离港还是进港）
	protected String flightState = null;
	//行李状态(是否有行李)
	protected Integer takeLuggage = 1;
	//起始站(航班起始站)
	protected String org = null;
	//终点站(航班目的地)
	protected String des = null;
	//乘机人数
	protected Integer personCount = 0;
	//携带特殊旅客
	protected String specialPerson = null;
	//会员是否设置过旅程指南
	protected Integer ifSet = 0;
	//会员关注航班实体
	protected MemberFocusFlight memberFocusFlight = null;
	//服务菜单
	protected String flightQuickMenu = null;
	//消息id
	protected String message_id = null;
	//位置
	protected String position = null;
	//会员自定义服务菜单id
	protected String memberSelfMenuId = null;
	//已设置快捷菜单数量
	protected Integer menuCount = 0;
	protected String noLuggage;
	
	//标识
	protected String flag = null;
	//特殊旅客
	protected String takeSpecialPerson;
	
	/**
	 * 快捷菜单标识
	 */
	protected String fromQuickMenu = null;
	
	//设置快捷菜单之前的url
	protected String preUrl = null;
	//快捷菜单位置
	protected String quickMenuPosition = null;
	protected String focusId;
	
	protected String cid = null;
	protected String acw = null;
	protected String gat = null;
	
	protected AirportPlan airportPlan = null;
	
	//电子客票号
	private String ticketNo = null;
	//航班计划日期
	private Date std = null;
	
	//值机身份证列表
	protected String[] IDCardArray = null;
	//值机电话列表
	protected String[] phoneArray = null;
	
	private String stopover = null;//经停地标识
	
	/**
	 * 进入会员中心首页
	 */
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	/**
	 * 跳转到会员注册页面
	 * @return
	 * @throws Exception
	 */
	public String register() throws Exception {
		return "register";
	}
	
	/**
	 * 会员注册
	 * @return String
	 * @throws ServiceException
	 */
	public String regist() throws ServiceException{
		//验证码是否输入正确
		if(!memberService.validateSmsCodeIsOK(mobile, smsCode)){
			data = AjaxData.responseError(getText("smsCode.error"));
			return JSON;
		}
		//电话号码不能为空
		if(GeneralUtil.isNull(mobile)) {
			data = AjaxData.responseError(getText("mobile.required"));
			return JSON;
		}
		//验证电话号是否已被注册
		if(memberService.validePhoneExist(mobile,versionFlag)){
			data = AjaxData.responseError(getText("mobile.registed"));
			return JSON;
		}
		
		Member member = new Member();
		member.setPhone(mobile);
		member.setLoginName(mobile);
		member.setLoginPwd(MD5Util.Azdg.strMD5(password));
		try {
			memberService.persist(member);
			quickMenuMemberSettingService.init(member.getId());
			List<QuickMenu> quickMenuList = quickMenuService.queryListByDefaultShow(versionFlag);
			if(quickMenuList != null && !quickMenuList.isEmpty()){
				for(QuickMenu quickMenu : quickMenuList){
					String[] positionStr = quickMenu.getDefaultShowPosition().split(",");
					for(String str : positionStr){
						QuickMenuMemberSetting quickMenuMemberSetting = new QuickMenuMemberSetting();
						quickMenuMemberSetting.setMember_id(member.getId());
						quickMenuMemberSetting.setQuickMenu_id(quickMenu.getId());
						quickMenuMemberSetting.setDefaultShowPosition(str);
						quickMenuMemberSettingService.persist(quickMenuMemberSetting);
					}
				}
			}
			getSession().put(ContextConstants.SCOPE_MEMBER, member);
			data = AjaxData.responseSuccess(getText("registerSuccess"));
			return JSON;
		} catch (Exception e) {
			e.printStackTrace();
			data = AjaxData.responseSuccess(getText("registerFailed"));
		}
		return JSON;
	}
	
	/**
	 * @Description：判断是否登录
	 * @date 2016年1月3日 下午4:07:56
	 * @return
	 */
	public String isLogin(){
		
		SystemUser user = (SystemUser)getSession().get(ContextConstants.SCOPE_SYSTEM_USER);
		if(user != null){
			
			data = AjaxData.responseSuccess("");
		}else{
			data = AjaxData.responseError("");
		}
		return JSON;
	}
	
	/**
	 * 会员登录
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception {
		if(GeneralUtil.isNull(operationFlag)){
			return "login";
		}else{
			//电话号码不能为空
			if(GeneralUtil.isNull(mobile)) {
				data = AjaxData.responseError(getText("mobile.required"));
				return JSON;
			}
			//密码不能为空
			if(GeneralUtil.isNull(password)) {
				data = AjaxData.responseError(getText("password.required"));
				return JSON;
			}
			Member member = memberService.queryByMixAndPwd(mobile, password); 
			if(member == null){
				data = AjaxData.responseError(getText("member.notExists"));
				return JSON;
			}else{
				getSession().put(ContextConstants.SCOPE_MEMBER, member);
				
				Cookie tourist = CookiesUtil.getCookies("JSESSIONID");
				String tourist_id = tourist.getValue();
				List<MemberAnnouncement> announcements = memberAnnouncementService.queryByMember(tourist_id);
				if(announcements != null){
					for(MemberAnnouncement announcement:announcements){
						announcement.setMember_id(member.getId());
						memberAnnouncementService.merge(announcement);
					}
				}
				
				if(GeneralUtil.isNull(member.getLoginCount())){
					member.setLoginCount(1);
					memberService.merge(member);
				}
				data = AjaxData.responseSuccess(getText("loginSuccess"));
				return JSON;
			}
		}
	}
	
	/**
	 * 用户注销登录
	 * @return
	 * @throws ServiceException
	 */
	public String logout() throws ServiceException{
		request.getSession().invalidate();
		return "personalCenterNoLogin";
	}
	/**
	 * 会员收藏
	 * @return
	 * @throws ServiceException
	 */
	public String favorite() throws ServiceException{
		MemberFavorite memberFavorite = new MemberFavorite();
		Member member = (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		if("NewsClass".equals(objectType) || "News".equals(objectType)){
			memberFavorite = memberFavoriteService.queryByObjectId(objectId,versionFlag);
			if(memberFavorite != null){
				memberFavoriteService.remove(memberFavorite);
				data = AjaxData.responseSuccess(getText("nofavoriteSuccess"));
				return JSON;
			}else{
				memberFavorite = new MemberFavorite();
				memberFavorite.setMember_id(member.getId());
				memberFavorite.setObjectType(objectType);
				memberFavorite.setObjectId(objectId);
				memberFavoriteService.persist(memberFavorite);
				data = AjaxData.responseSuccess(getText("favoriteSuccess"));
				return JSON;
			}
		}else if(GeneralUtil.isNotNull(title)){
			try {
				title = URLDecoder.decode(title, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			memberFavorite = memberFavoriteService.queryByTitleAndUrl(title,url,versionFlag);
			if(memberFavorite != null){
				memberFavoriteService.remove(memberFavorite);
				data = AjaxData.responseSuccess(getText("nofavoriteSuccess"));
				return JSON;
			}else{
				memberFavorite = new MemberFavorite();
				memberFavorite.setMember_id(member.getId());
				memberFavorite.setTitle(title);
				memberFavorite.setUrl(url);
				memberFavoriteService.persist(memberFavorite);
				data = AjaxData.responseSuccess(getText("favoriteSuccess"));
				return JSON;
			}
		}else{
			data = AjaxData.responseError(getText("favoriteFailed"));
		}
		return JSON;
	}
	/**
	 * 个人中心
	 * @return
	 * @throws ServiceException
	 */
	public String personalCenter() throws ServiceException{
		return "personalCenter";
	}
	/**
	 * 个人中心未登录
	 * @return
	 * @throws ServiceException
	 */
	public String personalCenterNoLogin() throws ServiceException{
		return "personalCenterNoLogin";
	}
	/**
	 * 机场服务
	 * @return
	 * @throws ServiceException
	 */
	public String airportService() throws ServiceException{
		return "airportService";
	}
	/**
	 * 发现
	 * @return
	 * @throws ServiceException
	 */
	public String found() throws ServiceException{
		return "found";
	}
	/**
	 * 消息提醒
	 * @return
	 * @throws ServiceException
	 */
	public String myMessage() throws ServiceException{
		return "myMessage";
	}
	
	/**
	 * 删除会员信息
	 * @return
	 * @throws ServiceException
	 */
	public String deleteMessage()throws ServiceException
	{
	    this.memberSendInfoService.remove(this.id);
	    data = AjaxData.responseSuccess(null);
	    return JSON;
	}
	
	/**
	 * 消息提醒详情
	 * @return
	 * @throws ServiceException
	 */
	public String messageDetail() throws ServiceException{
		
		MemberSendInfo sendinfo = memberSendInfoService.get(MemberSendInfo.class, id);
		sendinfo.setH5(true);
		memberSendInfoService.merge(sendinfo);
		if(sendinfo.getFlightDate() != null){
			
			flightDate = DateUtil.formatDateToShortString(sendinfo.getFlightDate()); 
		}
		
		return "messageDetail";
	}
	
	/**
	 * 收藏夹
	 * @return
	 * @throws ServiceException
	 */
	public String myFavorite() throws ServiceException{
		return "myFavorite";
	}
	
	/**
	 * @Description：取消收藏
	 * @date 2015年12月23日 下午2:19:07
	 * @return
	 */
	public String unFavorite(){
		
		try {
			memberFavoriteService.remove(id);
		} catch (ServiceException e) {
			e.printStackTrace();
			data = AjaxData.responseError(getText("nofavoriteFail"));
			return JSON;
		}
		data = AjaxData.responseSuccess(getText("nofavoriteSuccess"));
		return JSON;
	}
	
	/**
	 * 我的飞行记录
	 * @return
	 * @throws ServiceException
	 */
	public String myFlightRecord() throws ServiceException{
		return "myFlightRecord";
	}
	/**
	 * 我的关注
	 * @return
	 * @throws ServiceException
	 */
	public String myFocusOn() throws ServiceException{
		
		return "myFocusOn";
	}
	/**
	 * 个人设置
	 * @return
	 * @throws ServiceException
	 */
	public String personalSetting() throws ServiceException{
		Member member = (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		String ifChangePersonalData = (String) getSession().get(ContextConstants.ISCHANGEPERSONALDATA);
		if("1".equals(ifChangePersonalData)){
			getSession().remove(ContextConstants.ISCHANGEPERSONALDATA);
			member = memberService.queryById(Member.class.getSimpleName(), member.getId());
			getSession().put(ContextConstants.SCOPE_MEMBER, member);
		}
		
		MemberDetailInfo detailInfo = memberDetailInfoService.queryByMemberId(member.getId(), versionFlag);
		ActionContext.getContext().put("detailInfo", detailInfo);
		return "personalSetting";
	}
	/**
	 * 重置密码
	 * @return
	 * @throws ServiceException
	 */
	public String toResetPassword() throws ServiceException{
		return "toResetPassword";
	}
	/**
	 * 重置密码
	 * @return
	 * @throws ServiceException
	 */
	public String resetPassword() throws ServiceException{
		//非空验证
		if(GeneralUtil.isNull(mobile)) {
			data = AjaxData.responseError(getText("mobile.required"));
			return JSON;
		}
		if(GeneralUtil.isNull(password)) {
			data = AjaxData.responseError(getText("password.required"));
			return JSON;
		}
		if(!password.equals(recheckPwd)){
			
			data = AjaxData.responseError(getText("loginPwd.differ"));
			return JSON;
		}
		//验证码是否输入正确
		if(!AuthCodeUtil.check(mobile, smsCode)){
			data = AjaxData.responseError(getText("authCode.error"));
			return JSON;
		}
		Member member = memberService.queryByMobile(mobile,versionFlag);
		if(member != null){
			member.setLoginPwd(MD5Util.Azdg.strMD5(password));
			try {
				memberService.merge(member);
				if(getSession().get(ContextConstants.SCOPE_MEMBER) != null){
					getSession().remove(ContextConstants.SCOPE_MEMBER);
				}
				data = AjaxData.responseSuccess(getText("resetPwd.success"));
				return JSON;
			} catch (Exception e) {
				data = AjaxData.responseError(getText("resetPwd.failed"));
				return JSON;
			}
		}else{
			data = AjaxData.responseError(getText("phone.notRegister"));
			return JSON;
		}
		
	}
	/**
	 * 验证加V
	 * @return
	 * @throws ServiceException
	 */
	public String validateAddV() throws ServiceException{
		return "validateAddV";
	}
	/**
	 * VIP会员
	 * @return
	 * @throws ServiceException
	 */
	public String VIPMember() throws ServiceException{
		return "VIPMember";
	}
	/**
	 * 会员设置快捷菜单
	 * @return
	 * @throws ServiceException
	 */
	public String setQuickMenu() throws ServiceException{
		return "setQuickMenu";
	}
	
	/**
	 * 会员设置服务快捷菜单
	 * @return
	 * @throws ServiceException
	 */
	public String setServiceQuickMenu() throws ServiceException{
		return "setServiceQuickMenu";
	}
	
	/**
	 * 会员删除快捷菜单
	 * @return
	 * @throws ServiceException
	 */
	public String deleteQuickMenu() throws ServiceException {
		boolean isSuc = quickMenuMemberSettingService.batchRealDelete(QuickMenuMemberSetting.class, id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return "deleteQuickMenuAjax";
	}
	/**
	 * 修改个人头像
	 * @return
	 * @throws ServiceException
	 */
	public String updatePersonalInfo() throws ServiceException{
		Member member = (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		this.setMemberDetailInfo(memberDetailInfoService.queryByMemberId(member.getId(), versionFlag));
		
		List<MemberLogo> logos = logoService.queryAll(MemberLogo.class);
		ActionContext.getContext().put("logos", logos);
		return "updatePersonalInfo";
	}
	
	/**
	 * 修改头像
	 */
	public String updateImage(){
		
		if(GeneralUtil.isNotNull(images)){
			
			Member member = getSessionMember();
			member.setImages(images);
			try {
				memberService.merge(member);
			} catch (ServiceException e) {
				e.printStackTrace();
				data = AjaxData.responseError(getText("editFailed"));
				return JSON;
			}
		}
		
		data = AjaxData.responseSuccess(getText("editSuccess"));
		return JSON;
	}
	
	/**
	 * 会员基本信息
	 * @return
	 * @throws ServiceException
	 */
	public String baseInfo() throws ServiceException{
		Member member = (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		this.setMemberDetailInfo(memberDetailInfoService.queryByMemberId(member.getId(), versionFlag));
		return "baseInfo";
	}
	
	/**
	 * 会员详细信息
	 * @return
	 * @throws ServiceException
	 */
	public String detailInfo() throws ServiceException{
		Member member = (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		this.setMemberDetailInfo(memberDetailInfoService.queryByMemberId(member.getId(), versionFlag));
		return "detailInfo";
	}
	/**
	 * 会员公司信息
	 * @return
	 * @throws ServiceException
	 */
	public String companyInfo() throws ServiceException{
		Member member = (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		this.setMemberDetailInfo(memberDetailInfoService.queryByMemberId(member.getId(), versionFlag));
		return "companyInfo";
	}
	/**
	 * 会员教育信息
	 * @return
	 * @throws ServiceException
	 */
	public String educationInfo() throws ServiceException{
		Member member = (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		this.setMemberDetailInfo(memberDetailInfoService.queryByMemberId(member.getId(), versionFlag));
		return "educationInfo";
	}
	
	/**
	 * 会员其他信息
	 * @return
	 * @throws ServiceException
	 */
	public String otherInfo() throws ServiceException{
		Member member = (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		this.setMemberDetailInfo(memberDetailInfoService.queryByMemberId(member.getId(), versionFlag));
		return "otherInfo";
	}
	/**
	 * 根据日期计算年龄
	 * @param brithday
	 * @return
	 * @throws ParseException
	 * @throws Exception
	 */
	public static Integer getCurrentAgeByBirthdate(Date brithday) throws ParseException, Exception {
		  try {
		   Calendar calendar = Calendar.getInstance();
		   SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd");
		   String currentTime = formatDate.format(calendar.getTime());
		   Date today = formatDate.parse(currentTime);
		 
		   return today.getYear() - brithday.getYear();
		  } catch (Exception e) {
		   return 0;
		  }
	 }
	
	/**
	 * 编辑会员基本信息
	 * @return
	 * @throws Exception 
	 * @throws ParseException 
	 */
	@SuppressWarnings("static-access")
	public String editMemberBaseInfo() throws ParseException, Exception{
		
		Member member = (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		MemberDetailInfo mDetail = memberDetailInfoService.queryByMemberId(member.getId(),versionFlag);
		if(mDetail == null){
			mDetail = new MemberDetailInfo();
			mDetail.setMember_id(member.getId());
		}
		//基本信息
		member.setRealName(realName);
		if(GeneralUtil.isNotNull(IDCard)){
			
			IDCard = IDCard.toUpperCase();
			boolean eighteen = IDCard.matches("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$");
			if(eighteen){
				member.setIDCard(IDCard);
				Date birthday = DateUtil.parseStringToDate(IDCard.substring(6,14), "yyyyMMdd");
				mDetail.setBirthday(birthday);
				Integer tempAge = this.getCurrentAgeByBirthdate(birthday);
				if(GeneralUtil.isNotNull(birthday)){
					if(GeneralUtil.isNull(mDetail.getAge())){
						mDetail.setAge(tempAge);
					}
					if(GeneralUtil.isNull(mDetail.getZodiac())){
						String tempZodica = GeneralUtil.getZodica(birthday);
						mDetail.setZodiac(tempZodica);
					}
					if(GeneralUtil.isNull(mDetail.getConstellation())){
						String tempConstellation = GeneralUtil.getConstellation(birthday);
						mDetail.setConstellation(tempConstellation);
					}
				}
				
			}else{
				data = AjaxData.responseError(getText("IDcard.error"));
				return JSON;
			}
		}
		
		member.setNickName(nickName);
		if(GeneralUtil.isNotNull(sex)){
			if("男".equals(sex)){
				mDetail.setSex(1);
			}else{
				mDetail.setSex(0);
			}
		}
		
		mDetail.setPassport(passport);
		mDetail.setNationality(nationality);
		member.setPhone(phone);
		mDetail.setNativePlace(nativePlace);
		mDetail.setHomeAddress(homeAddress);
		member.setAddress(address);
		if(GeneralUtil.isNotNull(age)){
			if(!GeneralUtil.isInteger(age)){
				data = AjaxData.responseError(getText("age.needInteger"));
				return JSON;
			}else{
				mDetail.setAge(Integer.parseInt(age));
			}
		}
		
		mDetail.setBirthday(birthday);
		
		memberDetailInfoService.merge(mDetail);
		memberService.merge(member);
		//刷新缓存
		groupService.refresh("667bb53f-9792-4c06-88f1-1790cbe8db44");
		
		getSession().put(ContextConstants.ISCHANGEPERSONALDATA, "1");
		data = AjaxData.responseSuccess(getText("updateSuccess"));
		return JSON;
	}
	
	/**
	 * 编辑会员详细信息
	 * @return
	 * @throws ServiceException
	 */
	public String editMemberDetailInfo() throws ServiceException{
		Member member = (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		MemberDetailInfo mDetail = memberDetailInfoService.queryByMemberId(member.getId(),versionFlag);
		if(mDetail == null){
			mDetail = new MemberDetailInfo();
			mDetail.setMember_id(member.getId());
		}
		
		//详细信息
		mDetail.setBloodType(bloodType);
		mDetail.setConstellation(constellation);
		mDetail.setZodiac(zodiac);
		if(GeneralUtil.isNotNull(marriage)){
			if("未婚".equals(marriage)){
				mDetail.setMarriage(0);
			}else if("已婚".equals(marriage)){
				mDetail.setMarriage(1);
			}else{
				mDetail.setMarriage(2);
			}
		}
		if(GeneralUtil.isNotNull(height)){
			if(!GeneralUtil.isDouble(height)){
				data = AjaxData.responseError(getText("height.needDouble"));
				return JSON;
			}else{
				mDetail.setHeight(Double.parseDouble(height));
			}
		}
		if(GeneralUtil.isNotNull(weight)){
			if(!GeneralUtil.isDouble(weight)){
				data = AjaxData.responseError(getText("weight.needDouble"));
				return JSON;
			}else {
				mDetail.setWeight(Double.parseDouble(weight));
			}
		}
		
		member.setEmail(email);
		mDetail.setHobby(hobby);
		mDetail.setPlateNumber(plateNumber);
		mDetail.setPersonalitySignature(personalitySignature);
		
		memberDetailInfoService.merge(mDetail);
		memberService.merge(member);
		//刷新缓存
		groupService.refresh("667bb53f-9792-4c06-88f1-1790cbe8db44");
		
		getSession().put(ContextConstants.ISCHANGEPERSONALDATA, "1");
		data = AjaxData.responseSuccess(getText("updateSuccess"));
		return JSON;
	}
	
	/**
	 * 编辑会员公司信息
	 * @return
	 * @throws ServiceException
	 */
	public String editMemberCompanyInfo() throws ServiceException{
		Member member = (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		MemberDetailInfo mDetail = memberDetailInfoService.queryByMemberId(member.getId(),versionFlag);
		if(mDetail == null){
			mDetail = new MemberDetailInfo();
			mDetail.setMember_id(member.getId());
		}
		//公司信息
		mDetail.setCompanyName(companyName);
		mDetail.setEnterpriseProperty(enterpriseProperty);
		mDetail.setHonor(honor);
		mDetail.setRank(rank);
		mDetail.setOccupation(occupation);
		if(GeneralUtil.isNotNull(income)){
			mDetail.setIncome(income);
		}
		memberDetailInfoService.merge(mDetail);
		//刷新缓存
		groupService.refresh("667bb53f-9792-4c06-88f1-1790cbe8db44");
		
		getSession().put(ContextConstants.ISCHANGEPERSONALDATA, "1");
		data = AjaxData.responseSuccess(getText("updateSuccess"));
		return JSON;
	}
	
	
	/**
	 * 编辑会员教育信息
	 * @return
	 * @throws ServiceException
	 */
	public String editMemberEducationInfo() throws ServiceException{
		Member member = (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		MemberDetailInfo mDetail = memberDetailInfoService.queryByMemberId(member.getId(),versionFlag);
		if(mDetail == null){
			mDetail = new MemberDetailInfo();
			mDetail.setMember_id(member.getId());
		}
		//教育信息
		mDetail.setEducation(education);
		mDetail.setSchool(school);
		mDetail.setMajor(major);
		
		memberDetailInfoService.merge(mDetail);
		//刷新缓存
		groupService.refresh("667bb53f-9792-4c06-88f1-1790cbe8db44");
		
		getSession().put(ContextConstants.ISCHANGEPERSONALDATA, "1");
		data = AjaxData.responseSuccess(getText("updateSuccess"));
		return JSON;
	}
	
	/**
	 * 查看航班是不是可以激活服务码
	 */
	public String checkFlightDateByActive() throws ServiceException{
		DepartFromPort dept = departFromPortService.queryByFlightNoAndDate(flightNumber, flightDate, versionFlag);
		boolean activeFlag = bjdjAppointmentService.canActivate(dept.getStd());
		if(activeFlag){
			data = AjaxData.responseSuccess("");
		}else{
			data = AjaxData.responseError("");
		}
		return JSON;
	}
	
	/**
	 * 关注航班
	 * @return
	 * @throws ServiceException
	 */
	public String focusFlight() throws ServiceException{
		Cookie cookie = CookiesUtil.getCookies("flag");
		MemberFocusFlight memberFocus = null;
		Member member = (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		if(GeneralUtil.isNotNull(stopover)){
			memberFocus = memberFocusFlightService.queryByMemberAndStopover(flightNumber,flightDate,flightState,versionFlag);
			if(memberFocus != null){
				if(cookie != null){
					return "activePage";
				}else{
					return "myFocusOn";
				}
			}
		}else{
			stopover = null;
			memberFocus = memberFocusFlightService.queryByMemberAndFlightNoAndDate(flightNumber,flightDate,flightState,versionFlag);
			if(memberFocus != null){
				if(cookie != null){
					return "activePage";
				}else{
					return "myFocusOn";
				}
			}
		}
		try {
			memberFocus = memberFocusFlightService.persist(member.getId(), flightNumber, flightDate, memberRole, 
					flightState, null, null, null, null, null, null, null, null, stopover);
			autoSendMessageService.sendMsgAtFocusFlight(flightNumber, flightDate, flightState, memberRole);
			if(cookie != null){
				return "activePage";
			}else{
				return "myFocusOn";
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(cookie != null){
				return "activePage";
			}else{
				return "myFocusOn";
			}
		}
	}
	/**
	 * 会员关注航班
	 * @return
	 * @throws ServiceException
	 */
	public String memberFocusFlight() throws ServiceException{
		Cookie cookie = CookiesUtil.getCookies("flag");
		MemberFocusFlight memberFocus = null;
		Member member = (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		if("1".equals(stopover)){
			memberFocus = memberFocusFlightService.queryByMemberAndStopover(flightNumber,flightDate,flightState,versionFlag);
			if(memberFocus != null){
				if(cookie != null){
					return "activePage";
				}else{
					data = AjaxData.responseError(getText("flightHasFocus"));
					return JSON;
				}
			}
		}else{
			stopover = null;
			memberFocus = memberFocusFlightService.queryByMemberAndFlightNoAndDate(flightNumber,flightDate,flightState,versionFlag);
			if(memberFocus != null){
				if(cookie != null){
					return "activePage";
				}else{
					data = AjaxData.responseError(getText("flightHasFocus"));
					return JSON;
				}
			}
		}
		try {
			boolean isSuc = memberFocusFlightService.saveFocus(member.getId(),flightNumber,flightDate,flightState,stopover);
			if(isSuc){
				if(cookie != null){
					return "activePage";
				}else{
					data = AjaxData.responseSuccess(getText("focusFlightSuc"));
					return JSON;
				}
			}else{
				if(cookie != null){
					return "activePage";
				}else{
					data = AjaxData.responseError(getText("focusFlightFailed"));
					return JSON;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			data = AjaxData.responseError(getText("focusFlightFailed"));
			return JSON;
		}
	}
	
//	/**
//	 * 航班旅程指南
//	 * @return
//	 * @throws ServiceException
//	 */
//	public String journeyGuide() throws ServiceException{
//		MemberFocusFlight memberFocusFlight = memberFocusFlightService.queryById(MemberFocusFlight.class.getSimpleName(), id);
//		boolean flag = AirportPlaneUtil.compareDate(DateUtil.parseShortStringToDate(memberFocusFlight.getFlightDate()));
//		if(flag){
//			String threeCode= flightCompanyService.changeCodeTwoToThree(memberFocusFlight.getFlightNumber().substring(0,2), versionFlag) ;
//			this.setStd(DateUtil.parseSimpleStringToDate(memberFocusFlight.getFlightDate()));
//			AirportPlan ap = airportPlanService.getDataByNo(threeCode+memberFocusFlight.getFlightNumber().substring(2), versionFlag);
//			if(ap != null){
//				this.setAirportPlan(ap);
//			}
//		}else{
//			departFromPort = departFromPortService.queryByFlightNoAndDate(memberFocusFlight.getFlightNumber(), memberFocusFlight.getFlightDate(), versionFlag);
//			this.setDepartFromPort(departFromPort);
//			List<CheckinInfo> checkinInfos = checkinInfoService.query(memberFocusFlight.getFlightNumber(), memberFocusFlight.getFlightDate());
//			if(checkinInfos != null && !checkinInfos.isEmpty()){
//				List<String> idCardList = new ArrayList<String>();
//				for(CheckinInfo chi :checkinInfos){
//					SecurityData sd = securityDataService.getSecurityByTicketNo(chi.getTicketNo());
//					if(sd != null){
//						idCardList.add(sd.getIDCard());
//					}
//				}
//				if(idCardList != null && !idCardList.isEmpty()){
//					ActionContext.getContext().put("IDCardList", idCardList);
//				}
//			}
//		}
//		return "journeyGuide";
//	}
	
	/**
	 * 定制服务
	 * @return
	 * @throws ServiceException
	 */
	public String customizedService() throws ServiceException{
		MemberFocusFlight memberFocusFlight = memberFocusFlightService.queryById(MemberFocusFlight.class.getSimpleName(), id);
		if(memberFocusFlight != null){
			boolean flag = AirportPlaneUtil.compareDate(DateUtil.parseShortStringToDate(memberFocusFlight.getFlightDate()));
			if(flag){
				String tempFlt = flightCompanyService.changeCodeTwoToThree(memberFocusFlight.getFlightNumber().substring(0, 2), versionFlag)+memberFocusFlight.getFlightNumber().substring(2);
				AirportPlan ap = airportPlanService.getDataByNo(tempFlt, versionFlag);
				if(ap != null){
					this.setAirportPlan(ap);
				}
			}else{
				departFromPort = departFromPortService.queryByFlightNoAndDate(memberFocusFlight.getFlightNumber(), memberFocusFlight.getFlightDate(), versionFlag);
				this.setDepartFromPort(departFromPort);
				List<CheckinInfo> checkinInfos = checkinInfoService.query(flightNumber, memberFocusFlight.getFlightDate());
				if(checkinInfos != null && !checkinInfos.isEmpty()){
					List<String> idCardList = new ArrayList<String>();
					for(CheckinInfo chi :checkinInfos){
						SecurityData sd = securityDataService.getSecurityByTicketNo(chi.getTicketNo());
						if(sd != null){
							idCardList.add(sd.getIDCard());
						}
					}
					if(idCardList != null && !idCardList.isEmpty()){
						ActionContext.getContext().put("IDCardList", idCardList);
					}
				}
			}
		}
		return "journeyGuide";
	}
	
	/**
	 * 网上值机
	 * @return
	 * @throws ServiceException
	 */
	public String checkOnline() throws ServiceException{
		return "checkOnline";
	}
	
	/**
	 * 值机选座
	 * @return
	 * @throws ServiceException
	 */
	public String checkOnlineSubmit() throws ServiceException{
		return "";
	}
	/**
	 * 编辑关注信息
	 * @return
	 * @throws ServiceException
	 */
	public String editFocus() throws ServiceException{
		MemberFocusFlight memberFocusFlight = memberFocusFlightService.queryById(MemberFocusFlight.class.getSimpleName(), id);
		if(AirportPlaneUtil.compareDate(DateUtil.parseShortStringToDate(memberFocusFlight.getFlightDate()))){
			AirportPlan aPlan = airportPlanService.queryDepartByNoAndDate(memberFocusFlight.getFlightNumber(),memberFocusFlight.getFlightDate(), versionFlag);
			this.setAirportPlan(aPlan);
		}else{
			DepartFromPort departFromPort = departFromPortService.queryByFlightNoAndDate(memberFocusFlight.getFlightNumber(), memberFocusFlight.getFlightDate(),versionFlag);
			this.setDepartFromPort(departFromPort);
		}
		this.setMemberFocusFlight(memberFocusFlight);
		this.setMemberSelfMenuId(memberFocusFlight.getFlightNumber()+memberFocusFlight.getFlightDate().replace("-", ""));
		return "editFocus";
	}

	/**
	 * 保存会员关注信息
	 * @return
	 * @throws ServiceException
	 */
	public String saveMemberFocus() throws ServiceException{
		
		MemberFocusFlight memberFocusFlight = memberFocusFlightService.queryById(MemberFocusFlight.class.getSimpleName(), id);
		String districtFlag = "";
		if(AirportPlaneUtil.compareDate(DateUtil.parseShortStringToDate(memberFocusFlight.getFlightDate()))){
			AirportPlan aPlan = airportPlanService.queryDepartByNoAndDate(memberFocusFlight.getFlightNumber(),memberFocusFlight.getFlightDate(), versionFlag);
			if(aPlan != null){
				AirportInfo ap = airportInfoService.queryByFourCode(aPlan.getArrive(), versionFlag);
				districtFlag = ap.getDistrictFlag();
			}
		}else{
			DepartFromPort dept = departFromPortService.queryByFlightNoAndDate(memberFocusFlight.getFlightNumber(),memberFocusFlight.getFlightDate(), versionFlag);
			if(dept != null){
				districtFlag = dept.getDoi();
			}
		}
		flightDate = GeneralUtil.subString(memberFocusFlight.getFlightDate(), 10);
		flightDate = flightDate.replace("-", "");
		this.setMemberSelfMenuId(memberFocusFlight.getFlightNumber()+flightDate);
		String position = "";
		if("D".equals(districtFlag)){
			position = "h";
		}else{
			position = "i";
		}
		
		//设置特殊人群的快捷菜单
		quickMenuMemberSettingService.setBySpecialPerson(takeSpecialPerson, personCount, memberSelfMenuId, position);
		
		if(memberFocusFlight != null){
			
			memberFocusFlightService.set(memberFocusFlight.getId(), takeLuggage, personCount, takeSpecialPerson, phone, IDCard);
		}
		data = AjaxData.responseSuccess(getText("editSuccess"));
		return JSON;
	}
	
	/**
	 * 旅程助手
	 * @return
	 * @throws ServiceException
	 */
	public String journeyHelper() throws ServiceException{
		this.setMemberSelfMenuId(flightNumber+flightDate.replace("-", ""));
		if(GeneralUtil.isNotNull(flightState)){
			MemberFocusFlight memberFocusFlight = null;
			if(GeneralUtil.isNotNull(stopover)&&!"undefined".equals(stopover)){
				memberFocusFlight = memberFocusFlightService.queryByMemberAndStopover(flightNumber, flightDate, flightState, versionFlag);
			}else{
				memberFocusFlight = memberFocusFlightService.queryByMemberAndFlightNoAndDate(flightNumber, flightDate, flightState, versionFlag);
			}
			if(memberFocusFlight == null){
				memberFocusFlight = memberFocusFlightService.persist(getSessionMember().getId(), flightNumber, flightDate, memberRole, flightState, 
						0, null, personCount, ifSet, mobile, null, ticketNo, null, stopover);
			}
			this.setId(memberFocusFlight.getId());
			String doi = "";
			if(!AirportPlaneUtil.compareDate(DateUtil.parseShortStringToDate(flightDate))){
				if("depart".equals(flightState)){
					departFromPort = departFromPortService.queryByFlightNoAndDate(flightNumber, flightDate, versionFlag);
					doi = departFromPort.getDoi();
				}else if("arrival".equals(flightState)){
					ArrivalAtPort arrivalAtPort = arrivalAtPortService.queryArrivalFlightNoAndDate(flightNumber, flightDate, versionFlag);
					doi = arrivalAtPort.getDoi();
				}
			}else{
				AirportPlan airportPlan = null;
				if("depart".equals(flightState)){
					airportPlan = inDataService.queryDepartByNoAndDate(flightNumber, flightDate, versionFlag);
					AirportInfo apInfo = airportInfoService.queryByFourCode(airportPlan.getArrive(), versionFlag);
					if("D".equals(apInfo.getDistrictFlag())){
						doi = "D";
					}else{
						doi ="I";
					}
				}else if("arrival".equals(flightState)){
					airportPlan = inDataService.queryArrivalByNoAndDate(flightNumber, flightDate, versionFlag);
					AirportInfo apInfo = airportInfoService.queryByFourCode(airportPlan.getDept(), versionFlag);
					if("D".equals(apInfo.getDistrictFlag())){
						doi = "D";
					}else{
						doi ="I";
					}
				}
			}
			if("depart".equals(flightState)){//离港航班
				if(departFromPort != null){
					this.setGat(departFromPort.getGat());
					this.setAcw(departFromPort.getAcw());
				}
				List<CheckinInfo> checkinInfo = checkinInfoService.query(flightNumber, flightDate);
				if("D".equals(doi)){//国内航班
					if("1".equals(memberFocusFlight.getIfTakeLuggage())){
						if(checkinInfo.size() > 0){//已值机
							return "SDServiceCheckinTakeLuggage";
						}else{ //未值机
							return "SDServiceNoCheckin";
						}
					}else{
						if(checkinInfo.size() > 0){//已值机
							return "SDServiceCheckinNoLuggage";
						}else{ //未值机
							return "SDServiceNoCheckin";
						}
					}
				}else{//国际航班
					if(GeneralUtil.isNull(memberFocusFlight.getIfTakeLuggage())||memberFocusFlight.getIfTakeLuggage()==1){
						if(checkinInfo.size() > 0){//已值机
							return "SIServiceCheckinTakeLuggage";
						}else{ //未值机
							return "SIServiceNoCheckin";
						}
					}else{
						if(checkinInfo.size() > 0){//已值机
							return "SIServiceCheckinNoLuggage";
						}else{ //未值机
							return "SIServiceNoCheckin";
						}
					}
				}
			}else if("arrival".equals(flightState)){//进港航班
				if("D".equals(doi)){//国内航班
					return "RDServiceTakeLuggage";
				}else{//国际航班
					return "RIServiceTakeLuggage";
				}
			}else{//中转航班
				ArrivalAtPort arrivalAtPort = arrivalAtPortService.queryArrivalFlightNoAndDate(flightNumber, flightDate, versionFlag);
				if("D".equals(arrivalAtPort.getDoi())){
					if("D".equals(arrivalAtPort.getDoi())){
						return "DTDServiceTakeLuggage";
					}else{
						return "DTIServiceTakeLuggage";
					}
				}else{
					return "ITDServiceTakeLuggage";
				}
			}
		}
		return JSON;
	}
	
//	/**
//	 * 乘机导航
//	 * @return
//	 * @throws ServiceException
//	 */
//	public String flightNavigation() throws ServiceException{
//		MemberFocusFlight memberFocusFlight = memberFocusFlightService.queryById(MemberFocusFlight.class.getSimpleName(), id);
//		String districtFlag = "";
//		if(AirportPlaneUtil.compareDate(DateUtil.parseShortStringToDate(memberFocusFlight.getFlightDate()))){
//			AirportPlan aPlan = airportPlanService.queryDepartByNoAndDate(memberFocusFlight.getFlightNumber(), memberFocusFlight.getFlightDate(), versionFlag);
//			if(aPlan != null){
//				AirportInfo ap = airportInfoService.queryByFourCode(aPlan.getArrive(), versionFlag);
//				districtFlag = ap.getDistrictFlag();
//			}
//		}else{
//			DepartFromPort dept = departFromPortService.queryByFlightNoAndDate(memberFocusFlight.getFlightNumber(), memberFocusFlight.getFlightDate(), versionFlag);
//			if(dept != null){
//				districtFlag = dept.getDoi();
//			}
//		}
//		String position = "";
//		if("D".equals(districtFlag)){//国内出发
//			position = "h";
//		}else{
//			position = "i";
//		}
//		if(GeneralUtil.isNull(memberFocusFlight.getIfSet()) || memberFocusFlight.getIfSet() != 1 ){//未设置过旅程条件
//			
//			memberFocusFlightService.set(memberFocusFlight.getId(), takeLuggage, personCount, specialPerson, phone, IDCard);
//		}
//		
//		this.setDepartFromPort(departFromPortService.queryByFlightNoAndDate(memberFocusFlight.getFlightNumber(), memberFocusFlight.getFlightDate(), versionFlag));
//		
//		String fd = GeneralUtil.subString(memberFocusFlight.getFlightDate(), 10);
//		fd = memberFocusFlight.getFlightDate().replace("-", "");
//		this.setMemberSelfMenuId(memberFocusFlight.getFlightNumber()+fd);
//		this.setFlightNumber(memberFocusFlight.getFlightNumber());
//		
//		quickMenuMemberSettingService.setBySpecialPerson(takeSpecialPerson, personCount, memberSelfMenuId, position);
//		
////		List<CheckinInfo> checkinInfo = checkinInfoService.query(memberFocusFlight.getFlightNumber(), memberFocusFlight.getFlightDate());
//		String action = "airportm_navigation.action";
////		if("D".equals(districtFlag)){//国内出发
////			if(checkinInfo.size() > 0){//已值机
////				if(takeLuggage==1){//有行李
////					action = "airportm" + "_SDServiceCheckinTakeLuggage.action";
////				}
////				else{ //无行李
////					action = "airportm" + "_SDServiceCheckinNoLuggage.action";
////				}
////			}else{ //未值机
////				action = "airportm" + "_SDServiceNoCheckin.action";
////			}
////		}else{//国际出发
////			if(checkinInfo.size() > 0){//已值机
////				if(takeLuggage==1){//有行李
////					action = "airportm" + "_SIServiceCheckinTakeLuggage.action";
////				}
////				else{ //无行李
////					action = "airportm" + "_SIServiceCheckinNoLuggage.action";
////				}
////			}else{ //未值机
////				action = "airportm" + "_SIServiceNoCheckin.action";
////			}
////		}
//		action += "?id="+memberFocusFlight.getId();
//		JSONObject href = new JSONObject();
//		href.put("href", action);
//		AjaxUtil.writeSuccessJson(getResponse(), request, href);
//		String twoCode = memberFocusFlight.getFlightNumber().substring(0, 2);
//		FlightCompany fc = flightCompanyService.queryEntityByTwoCode(twoCode, versionFlag);
//		if(fc != null){
//			List<FCSelfCheckinPosition> list = fCSelfCheckinPositionService.queryListByFlightCompanyId(fc.getId(), versionFlag);
//			if(list != null && !list.isEmpty()){
//				ActionContext.getContext().put("positionList",list);
//			}
//		}
//		return JSON;
//	}
	
	/**
	 * 导航
	 * @return
	 * @throws ServiceException
	 */
	public String navigation() throws ServiceException{
		String doi ="";
		MemberFocusFlight memberFocusFlight = memberFocusFlightService.queryById(MemberFocusFlight.class.getSimpleName(), id);
		this.setId(memberFocusFlight.getId());
		this.setMemberSelfMenuId(memberFocusFlight.getFlightNumber()+memberFocusFlight.getFlightDate().replace("-", ""));
		boolean flag = AirportPlaneUtil.compareDate(DateUtil.parseShortStringToDate(memberFocusFlight.getFlightDate()));
		if(flag){
			if("depart".equals(memberFocusFlight.getFlightState())){
				
				AirportPlan aPlan = airportPlanService.queryDepartByNoAndDate(memberFocusFlight.getFlightNumber(), memberFocusFlight.getFlightDate(), versionFlag);
				if(aPlan != null){
					AirportInfo ap = airportInfoService.queryByFourCode(aPlan.getArrive(), versionFlag);
					if("D".equals(ap.getDistrictFlag())){
						return "SDServiceNoCheckin";
						
					}else{
						return "SIServiceNoCheckin";
					}
				}
			}else if("arrival".equals(memberFocusFlight.getFlightState())){
				AirportPlan aPlan = airportPlanService.queryArrivalByNoAndDate(memberFocusFlight.getFlightNumber(), memberFocusFlight.getFlightDate(), versionFlag);
				if(aPlan != null){
					AirportInfo ap = airportInfoService.queryByFourCode(aPlan.getDept(), versionFlag);
					if("D".equals(ap.getDistrictFlag())){
						return "RDServiceTakeLuggage";
						
					}else{
						return "RIServiceTakeLuggage";
					}
				}
				
			}
		}else{
			
			/*departFromPort = departFromPortService.queryByFlightNoAndDate(memberFocusFlight.getFlightNumber(), memberFocusFlight.getFlightDate(), versionFlag);
			doi = departFromPort.getDoi();*/
			if("depart".equals(memberFocusFlight.getFlightState())){//离港航班
				departFromPort = departFromPortService.queryByFlightNoAndDate(memberFocusFlight.getFlightNumber(), memberFocusFlight.getFlightDate(), versionFlag);
				doi = departFromPort.getDoi();
				if(departFromPort != null){
					
					this.setGat(departFromPort.getGat());
					this.setAcw(departFromPort.getAcw());
				}
				List<CheckinInfo> checkinInfo = checkinInfoService.query(memberFocusFlight.getFlightNumber(), memberFocusFlight.getFlightDate());
				if("D".equals(doi)){//国内航班
					if("1".equals(memberFocusFlight.getIfTakeLuggage())){
						if(checkinInfo.size() > 0){//已值机
							return "SDServiceCheckinTakeLuggage";
						}else{ //未值机
							return "SDServiceNoCheckin";
						}
					}else{
						if(checkinInfo.size() > 0){//已值机
							return "SDServiceCheckinNoLuggage";
						}else{ //未值机
							return "SDServiceNoCheckin";
						}
					}
				}else{//国际航班
					if(memberFocusFlight.getIfTakeLuggage()==1){
						if(checkinInfo.size() > 0){//已值机
							return "SIServiceCheckinTakeLuggage";
						}else{ //未值机
							return "SIServiceNoCheckin";
						}
					}else{
						if(checkinInfo.size() > 0){//已值机
							return "SIServiceCheckinNoLuggage";
						}else{ //未值机
							return "SIServiceNoCheckin";
						}
					}
				}
			}else if("arrival".equals(memberFocusFlight.getFlightState())){//进港航班
				ArrivalAtPort arrivalAtPort = arrivalAtPortService.queryArrivalFlightNoAndDate(memberFocusFlight.getFlightNumber(), memberFocusFlight.getFlightDate(), versionFlag);
				if("D".equals(arrivalAtPort.getDoi())){//国内航班
					return "RDServiceTakeLuggage";
				}else{//国际航班
					return "RIServiceTakeLuggage";
				}
			}else{//中转航班
				departFromPort = departFromPortService.queryByFlightNoAndDate(memberFocusFlight.getFlightNumber(), memberFocusFlight.getFlightDate(), versionFlag);
				ArrivalAtPort arrivalAtPort = arrivalAtPortService.queryArrivalFlightNoAndDate(memberFocusFlight.getFlightNumber(), memberFocusFlight.getFlightDate(), versionFlag);
				if("D".equals(departFromPort.getDoi())){
					if("D".equals(arrivalAtPort.getDoi())){
						return "DTDServiceTakeLuggage";
					}else{
						return "DTIServiceTakeLuggage";
					}
				}else{
					return "ITDServiceTakeLuggage";
				}
			}
		}
		return JSON;
	}
	
	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public String revokeShare() throws ServiceException{
		if(GeneralUtil.isNotNull(ticketNo)){
			MemberFocusFlight mf = memberFocusFlightService.queryByShare(ticketNo);
			if(mf != null){
				memberFocusFlightService.remove(mf);
			}
			CheckinInfo ch = checkinInfoService.queryByTicketNotShare(ticketNo);
			if(ch != null){
				checkinInfoService.remove(ch);
			}
			CheckinInfo ch1 = checkinInfoService.queryById(CheckinInfo.class.getSimpleName(), id);
			ch1.setByShare(null);
			checkinInfoService.merge(ch1);
			data = AjaxData.responseSuccess("");
		}else{
			data = AjaxData.responseError("");
		}
		return JSON;
	}
	public String SDServiceCheckinTakeLuggage(){
		
		return "SDServiceCheckinTakeLuggage";
	}
	
	public String SDServiceCheckinNoLuggage(){
		
		return "SDServiceCheckinNoLuggage";
	}
	
	public String SDServiceNoCheckin(){
		
		return "SDServiceNoCheckin";
	}
	
	public String SIServiceCheckinTakeLuggage(){
		
		return "SIServiceCheckinTakeLuggage";
	}
	
	public String SIServiceCheckinNoLuggage(){
		
		return "SIServiceCheckinNoLuggage";
	}
	
	public String SIServiceNoCheckin(){
		
		return "SIServiceNoCheckin";
	}
	
	/**
	 * 到达
	 * @return
	 * @throws ServiceException
	 */
	public String reach() throws ServiceException{
		SystemDictionary sys = systemDictionaryService.getByName(org);//sys.getDescription()
		flightDate = GeneralUtil.subString(flightDate, 10);
		flightDate = flightDate.replace("-", "");
		this.setMemberSelfMenuId(flightNumber+flightDate);
		SystemDictionary cityType = systemDictionaryService.queryById(SystemDictionary.class.getSimpleName(), sys.getParent().getId());
		if("domesticCityThreeCode".equals(cityType.getValue())){//国内城市
			return "RDServiceTakeLuggage";
		}else{
			return "RIServiceTakeLuggage";
		}
	}
	
	/**
	 * 中转航班导航
	 * @return
	 * @throws ServiceException
	 */
	public String transferNav() throws ServiceException{
		SystemDictionary orgCity = systemDictionaryService.getByName(org);//sys.getDescription()
		SystemDictionary desCity = systemDictionaryService.getByName(des);//sys.getDescription()
		flightDate = GeneralUtil.subString(flightDate, 10);
		flightDate = flightDate.replace("-", "");
		this.setMemberSelfMenuId(flightNumber+flightDate);
		SystemDictionary orgCityType = systemDictionaryService.queryById(SystemDictionary.class.getSimpleName(), orgCity.getParent().getId());
		SystemDictionary desCityType = systemDictionaryService.queryById(SystemDictionary.class.getSimpleName(), desCity.getParent().getId());
		if("domesticCityThreeCode".equals(orgCityType.getValue()) && "domesticCityThreeCode".equals(desCityType.getValue())){//国内到国内
			return "DTDServiceTakeLuggage";
		} else if("domesticCityThreeCode".equals(orgCityType.getValue()) && "InternationalCityThreeCode".equals(desCityType.getValue())){//国内到国际
			return "DTIServiceTakeLuggage";
		} else if("InternationalCityThreeCode".equals(orgCityType.getValue()) && "domesticCityThreeCode".equals(desCityType.getValue())){//国际到国内
			return "ITDServiceTakeLuggage";
		} else{//国际到国际
			return "ITIService";
		}  
	}
	

	/**
	 * 会员取消关注
	 * @return
	 * @throws ServiceException
	 */
	public String deleteFocus() throws ServiceException{
		try {
			memberFocusFlightService.batchRealDelete(MemberFocusFlight.class, id);
			data = AjaxData.responseSuccess("delete Suc");
		} catch (Exception e) {
			e.printStackTrace();
			data = AjaxData.responseError("delete Fail");
		}
		return JSON;
	}
	
	/**
	 * 国内出发Ajax
	 * @return
	 * @throws ServiceException
	 */
	public String SDAjax() throws ServiceException{
		return "SDAjax";
	}
	
	/**
	 * 国际出发Ajax
	 * @return
	 * @throws ServiceException
	 */
	public String SIAjax() throws ServiceException{
		return "SIAjax";
	}
	/**
	 * 国内转国内Ajax
	 * @return
	 * @throws ServiceException
	 */
	public String DTDAjax() throws ServiceException{
		return "DTDAjax";
	}
	/**
	 * 国内转国际Ajax
	 * @return
	 * @throws ServiceException
	 */
	public String DTIAjax() throws ServiceException{
		return "DTIAjax";
	}
	/**
	 * 国际转国内Ajax
	 * @return
	 * @throws ServiceException
	 */
	public String ITDAjax() throws ServiceException{
		return "ITDAjax";
	}
	
	/**
	 * 国内到达Ajax
	 * @return
	 * @throws ServiceException
	 */
	public String RDAjax() throws ServiceException{
		return "RDAjax";
	}
	/**
	 * 国际到达Ajax
	 * @return
	 * @throws ServiceException
	 */
	public String RIAjax() throws ServiceException{
		return "RIAjax";
	}
	
	public String reloadQuickMenu() throws ServiceException{
		this.setMemberSelfMenuId(memberSelfMenuId);
		if(GeneralUtil.isNotNull(quickMenuPosition)){
			if("h".equals(quickMenuPosition)){
				return "SDServiceQuickMenuAjax";
			} else if("i".equals(quickMenuPosition)){
				return "SIServiceQuickMenuAjax";
			} else if("j".equals(quickMenuPosition)){
				return "TTDServiceQuickMenuAjax";
			} else if("k".equals(quickMenuPosition)){
				return "TTIServiceQuickMenuAjax";
			} else if("l".equals(quickMenuPosition)){
				return "RDServiceQuickMenuAjax";
			} else if("m".equals(quickMenuPosition)){
				return "RIServiceQuickMenuAjax";
			} 
		}
		return "";
	}
	
	/**
	 * 值机信息
	 * @return
	 * @throws ServiceException
	 */
	public String checkinInfo() throws ServiceException{
		List<CheckinInfo> ciList = checkinInfoService.queryByMemberId(getSessionMember());
		if(ciList.size() == 0){
			
			return "checkinInfoNoData";
		}
		
		ActionContext.getContext().put("checkinList", ciList);
//		List<CheckinInfo> ciList = checkinInfoService.query(flightNumber,flightDate);
//		if(ciList != null && !ciList.isEmpty()){
//			ActionContext.getContext().put("checkinList", ciList);
//		}
//		/*List<SecurityData> list = new ArrayList<SecurityData>();
//		if(ciList != null && !ciList.isEmpty()){
//			for(CheckinInfo ci : ciList){
//				SecurityData sd = securityDataService.getSecurityByTicketNo(ci.getTicketNo());
//				if(sd != null){
//					list.add(sd);
//				}
//			}
//		}
//		//List<CheckinInfo> flights = checkinInfoService.query(flightNumber, DateUtil.parseSimpleStringToDate(flightDate));
//		if(list != null && !list.isEmpty()){
//			ActionContext.getContext().put("checkinList", list);
//		}*/
//		DepartFromPort dp = departFromPortService.queryByFlightNoAndDate(flightNumber, flightDate,versionFlag);
//		this.setDepartFromPort(dp);
		return "checkinInfo";
	}
	
	/**
	 * 取消值机
	 * @return
	 */
	public String removeCheckinInfo(){
		
		return "tipDownload";
	}
	
	/**
	 * 取消值机回调信息
	 * @author：涂有
	 * @date 2017年1月12日 下午6:24:26
	 * @return
	 */
	public String removeCheckinHandler(){
		
		try {
			checkinInfoService.remove(id);
			data = AjaxData.responseSuccess("删除成功");
		} catch (ServiceException e) {
			e.printStackTrace();
			data = AjaxData.responseError("删除失败");
		}
		return JSON;
	}
	
	/**
	 * 添加值机信息
	 * @return
	 */
	public String addCheckinInfo(){
		
		return "tipDownload";
	}
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getObjectType() {
		return objectType;
	}
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDefaultPosition() {
		return defaultPosition;
	}
	public void setDefaultPosition(String defaultPosition) {
		this.defaultPosition = defaultPosition;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public IMemberService getMemberService() {
		return memberService;
	}
	public void setMemberService(IMemberService memberService) {
		this.memberService = memberService;
	}
	public IQuickMenuService getQuickMenuService() {
		return quickMenuService;
	}
	public void setQuickMenuService(IQuickMenuService quickMenuService) {
		this.quickMenuService = quickMenuService;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String memberId) {
		member_id = memberId;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPassport() {
		return passport;
	}
	public void setPassport(String passport) {
		this.passport = passport;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getMTP() {
		return MTP;
	}
	public void setMTP(String mTP) {
		MTP = mTP;
	}
	public String getEnterpriseProperty() {
		return enterpriseProperty;
	}
	public void setEnterpriseProperty(String enterpriseProperty) {
		this.enterpriseProperty = enterpriseProperty;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getPoliticalOutLook() {
		return politicalOutLook;
	}
	public void setPoliticalOutLook(String politicalOutLook) {
		this.politicalOutLook = politicalOutLook;
	}
	public String getHonor() {
		return honor;
	}
	public void setHonor(String honor) {
		this.honor = honor;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	public String getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getConstellation() {
		return constellation;
	}
	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}
	public String getZodiac() {
		return zodiac;
	}
	public void setZodiac(String zodiac) {
		this.zodiac = zodiac;
	}
	public String getMarriage() {
		return marriage;
	}
	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}
	public String getBloodType() {
		return bloodType;
	}
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getDiseaseHistory() {
		return diseaseHistory;
	}
	public void setDiseaseHistory(String diseaseHistory) {
		this.diseaseHistory = diseaseHistory;
	}
	public String getAnaphylactogen() {
		return anaphylactogen;
	}
	public void setAnaphylactogen(String anaphylactogen) {
		this.anaphylactogen = anaphylactogen;
	}
	public String getHealthStatus() {
		return healthStatus;
	}
	public void setHealthStatus(String healthStatus) {
		this.healthStatus = healthStatus;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public String getWeChatId() {
		return weChatId;
	}
	public void setWeChatId(String weChatId) {
		this.weChatId = weChatId;
	}
	public String getWeiboNumber() {
		return weiboNumber;
	}
	public void setWeiboNumber(String weiboNumber) {
		this.weiboNumber = weiboNumber;
	}
	public String getRenrenAccount() {
		return renrenAccount;
	}
	public void setRenrenAccount(String renrenAccount) {
		this.renrenAccount = renrenAccount;
	}
	public String getBaiduAccount() {
		return baiduAccount;
	}
	public void setBaiduAccount(String baiduAccount) {
		this.baiduAccount = baiduAccount;
	}
	public String getSogouAccount() {
		return sogouAccount;
	}
	public void setSogouAccount(String sogouAccount) {
		this.sogouAccount = sogouAccount;
	}
	public String getPersonalitySignature() {
		return personalitySignature;
	}
	public void setPersonalitySignature(String personalitySignature) {
		this.personalitySignature = personalitySignature;
	}
	public String getEmergencyContactName() {
		return emergencyContactName;
	}
	public void setEmergencyContactName(String emergencyContactName) {
		this.emergencyContactName = emergencyContactName;
	}
	public String getEmergencyContactSex() {
		return emergencyContactSex;
	}
	public void setEmergencyContactSex(String emergencyContactSex) {
		this.emergencyContactSex = emergencyContactSex;
	}
	public String getEmergencyContactPhone() {
		return emergencyContactPhone;
	}
	public void setEmergencyContactPhone(String emergencyContactPhone) {
		this.emergencyContactPhone = emergencyContactPhone;
	}
	public Integer getEmergencyContactAge() {
		return emergencyContactAge;
	}
	public void setEmergencyContactAge(Integer emergencyContactAge) {
		this.emergencyContactAge = emergencyContactAge;
	}
	public String getEmergencyContactIDCard() {
		return emergencyContactIDCard;
	}
	public void setEmergencyContactIDCard(String emergencyContactIDCard) {
		this.emergencyContactIDCard = emergencyContactIDCard;
	}
	public String getEmergencyContacthonor() {
		return emergencyContacthonor;
	}
	public void setEmergencyContacthonor(String emergencyContacthonor) {
		this.emergencyContacthonor = emergencyContacthonor;
	}
	public String getEmergencyContactEmail() {
		return emergencyContactEmail;
	}
	public void setEmergencyContactEmail(String emergencyContactEmail) {
		this.emergencyContactEmail = emergencyContactEmail;
	}
	public String getEmergencyContactRelation() {
		return emergencyContactRelation;
	}
	public void setEmergencyContactRelation(String emergencyContactRelation) {
		this.emergencyContactRelation = emergencyContactRelation;
	}
	public String getVisitorCardData() {
		return visitorCardData;
	}
	public void setVisitorCardData(String visitorCardData) {
		this.visitorCardData = visitorCardData;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIDCard() {
		return IDCard;
	}
	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public MemberDetailInfo getMemberDetailInfo() {
		return memberDetailInfo;
	}
	public void setMemberDetailInfo(MemberDetailInfo memberDetailInfo) {
		this.memberDetailInfo = memberDetailInfo;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getFlightDate() {
		return flightDate;
	}
	public void setFlightDate(String flightDate) {
		this.flightDate = flightDate;
	}
	public DepartFromPort getDepartFromPort() {
		return departFromPort;
	}
	public void setDepartFromPort(DepartFromPort departFromPort) {
		this.departFromPort = departFromPort;
	}
	public String getMemberRole() {
		return memberRole;
	}
	public void setMemberRole(String memberRole) {
		this.memberRole = memberRole;
	}
	public String getFlightState() {
		return flightState;
	}
	public void setFlightState(String flightState) {
		this.flightState = flightState;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public Integer getPersonCount() {
		return personCount;
	}
	public void setPersonCount(Integer personCount) {
		this.personCount = personCount;
	}
	public String getSpecialPerson() {
		return specialPerson;
	}
	public void setSpecialPerson(String specialPerson) {
		this.specialPerson = specialPerson;
	}
	public Integer getIfSet() {
		return ifSet;
	}
	public void setIfSet(Integer ifSet) {
		this.ifSet = ifSet;
	}
	public String getFlightQuickMenu() {
		return flightQuickMenu;
	}
	public void setFlightQuickMenu(String flightQuickMenu) {
		this.flightQuickMenu = flightQuickMenu;
	}
	public MemberFocusFlight getMemberFocusFlight() {
		return memberFocusFlight;
	}
	public void setMemberFocusFlight(MemberFocusFlight memberFocusFlight) {
		this.memberFocusFlight = memberFocusFlight;
	}
	public String getMessage_id() {
		return message_id;
	}
	public void setMessage_id(String messageId) {
		message_id = messageId;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getMemberSelfMenuId() {
		return memberSelfMenuId;
	}
	public void setMemberSelfMenuId(String memberSelfMenuId) {
		this.memberSelfMenuId = memberSelfMenuId;
	}
	public Integer getMenuCount() {
		return menuCount;
	}
	public void setMenuCount(Integer menuCount) {
		this.menuCount = menuCount;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getPreUrl() {
		return preUrl;
	}
	public void setPreUrl(String preUrl) {
		this.preUrl = preUrl;
	}
	public String getQuickMenuPosition() {
		return quickMenuPosition;
	}
	public void setQuickMenuPosition(String quickMenuPosition) {
		this.quickMenuPosition = quickMenuPosition;
	}
	public String getFromQuickMenu() {
		return fromQuickMenu;
	}
	public void setFromQuickMenu(String fromQuickMenu) {
		this.fromQuickMenu = fromQuickMenu;
	}
	public IQuickMenuMemberSettingService getQuickMenuMemberSettingService() {
		return quickMenuMemberSettingService;
	}
	public void setQuickMenuMemberSettingService(
			IQuickMenuMemberSettingService quickMenuMemberSettingService) {
		this.quickMenuMemberSettingService = quickMenuMemberSettingService;
	}
	public IMemberFavoriteService getMemberFavoriteService() {
		return memberFavoriteService;
	}
	public void setMemberFavoriteService(
			IMemberFavoriteService memberFavoriteService) {
		this.memberFavoriteService = memberFavoriteService;
	}
	public IMemberDetailInfoService getMemberDetailInfoService() {
		return memberDetailInfoService;
	}
	public void setMemberDetailInfoService(
			IMemberDetailInfoService memberDetailInfoService) {
		this.memberDetailInfoService = memberDetailInfoService;
	}
	public IMemberFocusFlightService getMemberFocusFlightService() {
		return memberFocusFlightService;
	}
	public void setMemberFocusFlightService(
			IMemberFocusFlightService memberFocusFlightService) {
		this.memberFocusFlightService = memberFocusFlightService;
	}
	public IDepartFromPortService getDepartFromPortService() {
		return departFromPortService;
	}
	public void setDepartFromPortService(
			IDepartFromPortService departFromPortService) {
		this.departFromPortService = departFromPortService;
	}
	public IArrivalAtPortService getArrivalAtPortService() {
		return arrivalAtPortService;
	}
	public void setArrivalAtPortService(IArrivalAtPortService arrivalAtPortService) {
		this.arrivalAtPortService = arrivalAtPortService;
	}
	public IBjdjOrderService getOrderService() {
		return orderService;
	}
	public void setOrderService(IBjdjOrderService orderService) {
		this.orderService = orderService;
	}
	public ISystemDictionaryService getSystemDictionaryService() {
		return systemDictionaryService;
	}
	public void setSystemDictionaryService(
			ISystemDictionaryService systemDictionaryService) {
		this.systemDictionaryService = systemDictionaryService;
	}
	public ISpecialPersonWithQuickMenuService getSpecialPersonWithQuickMenuService() {
		return specialPersonWithQuickMenuService;
	}
	public void setSpecialPersonWithQuickMenuService(
			ISpecialPersonWithQuickMenuService specialPersonWithQuickMenuService) {
		this.specialPersonWithQuickMenuService = specialPersonWithQuickMenuService;
	}
	public IMessageReadLogService getMessageReadLogService() {
		return messageReadLogService;
	}
	public void setMessageReadLogService(
			IMessageReadLogService messageReadLogService) {
		this.messageReadLogService = messageReadLogService;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getRecheckPwd() {
		return recheckPwd;
	}
	public void setRecheckPwd(String recheckPwd) {
		this.recheckPwd = recheckPwd;
	}
	public IPageCacheGroupService getGroupService() {
		return groupService;
	}
	public void setGroupService(IPageCacheGroupService groupService) {
		this.groupService = groupService;
	}
	public ICheckinInfoService getCheckinInfoService() {
		return checkinInfoService;
	}
	public void setCheckinInfoService(ICheckinInfoService checkinInfoService) {
		this.checkinInfoService = checkinInfoService;
	}
	public String getNoLuggage() {
		return noLuggage;
	}
	public void setNoLuggage(String noLuggage) {
		this.noLuggage = noLuggage;
	}
	public Integer getTakeLuggage() {
		return takeLuggage;
	}
	public void setTakeLuggage(Integer takeLuggage) {
		this.takeLuggage = takeLuggage;
	}
	public IMemberSendInfoService getMemberSendInfoService() {
		return memberSendInfoService;
	}
	public void setMemberSendInfoService(
			IMemberSendInfoService memberSendInfoService) {
		this.memberSendInfoService = memberSendInfoService;
	}
	public String getFocusId() {
		return focusId;
	}
	public void setFocusId(String focusId) {
		this.focusId = focusId;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getAcw() {
		return acw;
	}
	public void setAcw(String acw) {
		this.acw = acw;
	}
	public String getGat() {
		return gat;
	}
	public void setGat(String gat) {
		this.gat = gat;
	}
	public String getTicketNo() {
		return ticketNo;
	}
	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}
	public AirportPlan getAirportPlan() {
		return airportPlan;
	}
	public void setAirportPlan(AirportPlan airportPlan) {
		this.airportPlan = airportPlan;
	}
	public Date getStd() {
		return std;
	}
	public void setStd(Date std) {
		this.std = std;
	}
	public IFlightCompanyService getFlightCompanyService() {
		return flightCompanyService;
	}
	public void setFlightCompanyService(IFlightCompanyService flightCompanyService) {
		this.flightCompanyService = flightCompanyService;
	}
	public String[] getIDCardArray() {
		return IDCardArray;
	}
	public void setIDCardArray(String[] iDCardArray) {
		IDCardArray = iDCardArray;
	}
	public String[] getPhoneArray() {
		return phoneArray;
	}
	public void setPhoneArray(String[] phoneArray) {
		this.phoneArray = phoneArray;
	}
	public String getTakeSpecialPerson() {
		return takeSpecialPerson;
	}
	public void setTakeSpecialPerson(String takeSpecialPerson) {
		this.takeSpecialPerson = takeSpecialPerson;
	}
	public String getStopover() {
		return stopover;
	}
	public void setStopover(String stopover) {
		this.stopover = stopover;
	}
}
