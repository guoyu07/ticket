package com.ticket.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.Member;
import com.ticket.pojo.MemberAnnouncement;
import com.ticket.pojo.SystemDictionary;
import com.ticket.service.IBaseService;
import com.ticket.service.IMemberAnnouncementService;
import com.ticket.service.IMemberService;
import com.ticket.service.IQuickMenuMemberSettingService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.util.AuthCodeUtil;
import com.ticket.util.CookiesUtil;
import com.ticket.util.EmailUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.ResourceUtil;
import com.ticket.util.SmsUtil;

/**
 * @Description：用户管理（登录、注册、密码找回等）
 * @author：涂有
 * @date 2015年10月30日 上午10:21:40
 */
public class BjdjMemberAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	@Resource IMemberService memberService;
	@Resource IBaseService baseService;
	@Resource ISystemDictionaryService systemDictionaryService;
	@Resource protected IMemberAnnouncementService memberAnnouncementService;
	@Resource
	private IQuickMenuMemberSettingService quickMenuMemberSettingService;
	
	protected Member member;
	
	//id
	protected String id;
	//登录成功之后，要跳转到的action
	protected String destUrl;
	
	//登录用户名和密码
	protected String loginName;
	protected String loginPwd;
	protected String rloginPwd;
	
	//用户邮箱
	protected String email;
	//用户手机号
	protected String phone;
	//验证码
	protected String authCode;
	
	/**
	 * @Description：默认跳到登录页面
	 * @return
	 */
	public String execute(){
		
		if(getSession().get(ContextConstants.SCOPE_MEMBER) != null){
			
			if(GeneralUtil.isNotNull(destUrl)){
				
				return "toPage";
			}
		}
		return SUCCESS;
	}
	
	/**
	 * @Description：执行登录操作，并跳转到指定action
	 * @return
	 * @throws ServiceException 
	 */
	public String login() throws ServiceException{
		
		//检查用户名是否为空
		if(GeneralUtil.isNull(loginName)){
			
			data = AjaxData.responseError(getText("loginName.required"));
			return JSON;
		}
		//检查密码是否为空
		if(GeneralUtil.isNull(loginPwd)){
			
			data = AjaxData.responseError(getText("loginPwd.required"));
			return JSON;
		}
		
		//检验用户名
		List<Member> users = memberService.getByColumn("phone", loginName);
		if(users.size() == 0){
			
			data = AjaxData.responseError(getText("loginName.notExist"));
			return JSON;
		}
		
			//检验密码
		member = memberService.queryByMixAndPwd(loginName, loginPwd);
		if(member == null){
			
			data = AjaxData.responseError(getText("loginPwd.error"));
			return JSON;
		}
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
		data = AjaxData.responseSuccess(ResourceUtil.getText("login.success"));
		return JSON;
	}
	
	/**
	 * 隐藏的便捷登机的登录
	 * @return
	 * @throws ServiceException
	 */
	public String loginAjax() throws ServiceException{
		
		return "loginAjax";
	}
	
	/**
	 * @Description：执行登录操作，并跳转到指定action
	 * @return
	 * @throws ServiceException 
	 */
	public String loginInterface() throws ServiceException{
		
		//检查用户名是否为空
		if(GeneralUtil.isNull(loginName)){
			
			data = AjaxData.responseError(getText("loginName.required"));
			return JSON;
		}
		//检查密码是否为空
		if(GeneralUtil.isNull(loginPwd)){
			
			data = AjaxData.responseError(getText("loginPwd.required"));
			return JSON;
		}
		
		//检验用户名
//		List<Member> users = memberService.getByColumn("phone", loginName);
//		if(users.size() == 0){
//			
//			addFieldError("loginName", getText("loginName.notExist"));
//			return execute();
//		}
		
		//检验密码
		member = memberService.queryByMixAndPwd(loginName, loginPwd);
		if(member == null){
			
			data = AjaxData.responseError(getText("loginName.error"));
			return JSON;
		}
		
		Cookie tourist = CookiesUtil.getCookies("JSESSIONID");
		if(GeneralUtil.isNotNull(tourist)){
			String tourist_id = tourist.getValue();
			List<MemberAnnouncement> announcements = memberAnnouncementService.queryByMember(tourist_id);
			if(announcements != null){
				for(MemberAnnouncement announcement:announcements){
					announcement.setMember_id(member.getId());
					memberAnnouncementService.merge(announcement);
				}
			}
		}
		getSession().put(ContextConstants.SCOPE_MEMBER, member);
		data = AjaxData.responseSuccess(JSONObject.toJSONString(member));
		return JSON;
	}
	
	/**
	 * 检测是否登录
	 */
	public String isLogin(){
		
		Member member = (Member)getSession().get(ContextConstants.SCOPE_MEMBER);
		if(member != null){
			
			data = AjaxData.responseSuccess("");
		}else{
			
			data = AjaxData.responseError("");
		}
		return JSON;
	} 
	
	/**
	 * @Description：进入到注册页面
	 * @return
	 */
	public String registPage(){
		
		return "registPage";
	}
	
	/**
	 * @Description：生成验证码，并发送到手机
	 * @throws ServiceException 
	 */
	public String generateAuthCode() throws ServiceException{
		
		//检查用户级是否为空
		if(GeneralUtil.isNull(phone)){
			
			data = AjaxData.responseError(getText("phone.null"));
			return JSON;
		}
		//检查此手机是否已经注册
//		List<Member> members = memberService.getByColumn("phone", phone);
//		if(members.size() > 0){
//			
//			data = AjaxData.responseError(getText("loginName.repeat"));
//			return JSON;
//		}
		
		String authCode = AuthCodeUtil.generateAndSave(phone);
		SmsUtil.sendSms(phone, getText("authCode.sms", new String[]{authCode}));
		data = AjaxData.responseSuccess(getText("authCode.sended"));
		return JSON;
	}
	
	/**
	 * @Description：处理用户用手机号注册的功能
	 * @return
	 * @throws ServiceException 
	 */
	public String regist() throws ServiceException{
		
		//检查用户级是否为空
		if(GeneralUtil.isNull(phone)){
			
			addFieldError("phone", getText("phone.null"));
			return "registPage";
		}
		//检查此手机是否已经注册
		List<Member> members = memberService.getByColumn("phone", phone);
		if(members.size() > 0){
			
			addFieldError("phone", getText("loginName.repeat"));
			return "registPage";
		}
		
		if(!AuthCodeUtil.check(phone, authCode)){
			
			addFieldError("authCode", getText("authCode.error"));
			return "registPage";
		}
		memberService.generateByMobileAndSendSms(phone);
		return SUCCESS;
	}
	
	/**
	 * @Description：处理用户用手机号注册的功能
	 * @return
	 * @throws ServiceException 
	 */
	public String registInterface() throws ServiceException{
		
		//检查用户级是否为空
		if(GeneralUtil.isNull(phone)){
			
			data = AjaxData.responseError(getText("phone.null"));
			return JSON;
		}
		//检查此手机是否已经注册
		List<Member> members = memberService.getByColumn("phone", phone);
		if(members.size() > 0){
			
			data = AjaxData.responseError(getText("loginName.repeat"));
			return JSON;
		}
		
		if(!AuthCodeUtil.check(phone, authCode)){
			
			data = AjaxData.responseError(getText("authCode.error"));
			return JSON;
		}
		member = memberService.generateByMobileAndSendSms(phone);
		data = AjaxData.responseSuccess("");
		return JSON;
	}
	
	/**
	 * @Description：进入找回密码页面
	 * @return
	 */
	public String retrievePasswordPage(){
		
		SystemDictionary dict = systemDictionaryService.getByName("service_phone");
		ActionContext.getContext().put("service_phone", dict.getValue());
		return "retrievePasswordPage";
	}
	
	/**
	 * @Description：进入手机号码修改页面1
	 * @return
	 */
	public String retrievePasswordForMobile1Page(){
		
		return "retrievePasswordForMobile1Page";
	}
	
	/**
	 * @Description：生成验证码，并发送到手机
	 * @throws ServiceException 
	 */
	public String generateAuthCodeForMobile() throws ServiceException{
		
		//检查用户级是否为空
		if(GeneralUtil.isNull(phone)){
			
			data = AjaxData.responseError(getText("phone.null"));
			return JSON;
		}
		//检查此手机是否已经注册
		List<Member> members = memberService.getByColumn("phone", phone);
		if(members.size() == 0){
			
			data = AjaxData.responseError(getText("user.phone.noExist"));
			return JSON;
		}
		
		String authCode = AuthCodeUtil.generateAndSave(phone);
		SmsUtil.sendSms(phone, getText("authCode.sms", new String[]{authCode}));
		data = AjaxData.responseSuccess(getText("authCode.sended"));
		return JSON;
	}
	
	/**
	 * @Description：验证验证码，并发送到手机
	 * @throws ServiceException 
	 */
	public String checkAuthCodeForMobile() throws ServiceException{
		
		//验证电话号码
		if(GeneralUtil.isNull(phone)){
			
			addFieldError("mobile", getText("mobile.required"));
			return JSON;
		}
		//验证验证码
		if(GeneralUtil.isNull(authCode)){
			
			addFieldError("authCode", getText("authCode.required"));
			return JSON;
		}
		//检查验证码
		if(!AuthCodeUtil.check(phone, authCode)){
			
			addFieldError("authCode", getText("authCode.error"));
			return phone;
		}
		
		data = AjaxData.responseSuccess("");
		return JSON;
	}
	
	/**
	 * @Description：验证手机账号，验证码
	 * @return
	 * @throws ServiceException 
	 */
	public String checkAuthForMobile() throws ServiceException{
		
		//检查用户名是否为空
		if(GeneralUtil.isNull(phone)){
			
			addFieldError("phone", getText("phone.null"));
			return "retrievePasswordForMobile1Page";
		}
		//检查此手机是否已经注册
		List<Member> members = memberService.getByColumn("phone", phone);
		if(members.size() == 0){
			
			addFieldError("phone", getText("loginName.notExist"));
			return "retrievePasswordForMobile1Page";
		}
		//验证不通过
		if(!AuthCodeUtil.check(phone, authCode)){
			
			addFieldError("authCode", getText("authCode.error"));
			return "retrievePasswordForMobile1Page";
		}
		Member member = members.get(0);
		ActionContext.getContext().getSession().put(ContextConstants.SCOPE_MEMBER, member);
		return "retrievePasswordForMobile2Page";
	}
	
	/**
	 * @Description：验证手机账号，验证码
	 * @return
	 * @throws ServiceException 
	 */
	public String checkAuthCodeInterface() throws ServiceException{
		
		//检查用户名是否为空
		if(GeneralUtil.isNull(phone)){
			
			data = AjaxData.responseError(getText("phone.null"));
			return JSON;
		}
		//验证不通过
		if(!AuthCodeUtil.check(phone, authCode)){
			
			data = AjaxData.responseError(getText("authCode.error"));
			return JSON;
		}
		
		data = AjaxData.responseSuccess("");
		return JSON;
	}
	
	/**
	 * @Description：验证手机账号，验证码
	 * @return
	 * @throws ServiceException 
	 */
	public String checkAuthForMobileInterface() throws ServiceException{
		
		//检查用户名是否为空
		if(GeneralUtil.isNull(phone)){
			
			data = AjaxData.responseError(getText("phone.null"));
			return JSON;
		}
		//检查此手机是否已经注册
		List<Member> members = memberService.getByColumn("phone", phone);
		if(members.size() > 0){
			
			data = AjaxData.responseError(getText("loginName.repeat"));
			return JSON;
		}
		//验证不通过
		if(!baseService.validateSmsCodeIsOK(phone, authCode)){
			
			data = AjaxData.responseError(getText("authCode.error"));
			return JSON;
		}
		
		data = AjaxData.responseSuccess("");
		return JSON;
	}
	
	/**
	 * @Description：为邮箱找回密码生成验证码
	 * @return
	 */
	public String generateAuthCodeForEmail(){
		
		//检查用户名是否为空
		if(GeneralUtil.isNull(email)){
			
			data = AjaxData.responseError(getText("email.null"));
			return JSON;
		}
		//检查此手机是否已经注册
		List<Member> members = memberService.getByColumn("email", email);
		if(members.size() == 0){
			
			data = AjaxData.responseError(getText("email.notExist"));
			return JSON;
		}
		
		//生成验证码，发送到邮箱
		String authCode = AuthCodeUtil.generateAndSave(email);
		EmailUtil.send(email, getText("sms.loginPwd.retrieve"), getText("authCode.tip", new String[]{authCode}));
		data = AjaxData.responseSuccess(getText("authCode.success"));
		return JSON;
	}
	
	/**
	 * @Description：为邮箱找回密码生成验证码
	 * @return
	 * @throws ServiceException 
	 */
	public String generateAuthCodeForEmailInterface() throws ServiceException{
		
		//检查用户名是否为空
		if(GeneralUtil.isNull(email)){
			
			data = AjaxData.responseError(getText("email.null"));
			return JSON;
		}
		//检查此手机是否已经注册
		List<Member> members = memberService.getByColumn("email", email);
		if(members.size() == 0){
			
			data = AjaxData.responseError(getText("email.notExist"));
			return JSON;
		}
		
		//生成验证码，发送到邮箱
		String authCode = AuthCodeUtil.generate();
		EmailUtil.send(email, getText("sms.loginPwd.retrieve"), getText("authCode.tip", new String[]{authCode}));
		data = AjaxData.responseSuccess(getText("authCode.success"));
		return JSON;
	}
	
	/**
	 * @Description：验证邮箱，验证码
	 * @return
	 * @throws ServiceException 
	 */
	public String checkAuthForEmail() throws ServiceException{
		
		//检查用户名是否为空
		if(GeneralUtil.isNull(email)){
			
			addFieldError("email", getText("email.null"));
			return "retrievePasswordForEmail1Page";
		}
		//检查此手机是否已经注册
		List<Member> members = memberService.getByColumn("email", email);
		if(members.size() == 0){
			
			addFieldError("email", getText("email.notExist"));
			return "retrievePasswordForEmail1Page";
		}
		//验证不通过
		if(!AuthCodeUtil.check(email, authCode)){
			
			addFieldError("authCode", getText("authCode.error"));
			return "retrievePasswordForEmail1Page";
		}
		Member member = members.get(0);
		ActionContext.getContext().getSession().put(ContextConstants.SCOPE_MEMBER, member);
		return "retrievePasswordForEmail2Page";
	}
	
	/**
	 * @Description：验证邮箱，验证码
	 * @return
	 * @throws ServiceException 
	 */
	public String checkAuthForEmailInterface() throws ServiceException{
		
		//检查用户名是否为空
		if(GeneralUtil.isNull(email)){
			
			data = AjaxData.responseError(getText("email.null"));
			return JSON;
		}
		//检查此手机是否已经注册
		List<Member> members = memberService.getByColumn("email", email);
		if(members.size() == 0){
			
			data = AjaxData.responseError(getText("email.notExist"));
			return JSON;
		}
		//验证不通过
		if(!AuthCodeUtil.check(email, authCode)){
			
			addFieldError("authCode", getText("authCode.error"));
			return "retrievePasswordForEmail1Page";
		}
		
		data = AjaxData.responseSuccess("");
		return JSON;
	}
	
	/**
	 * @Description：修改密码(手机号码)
	 * @return
	 * @throws ServiceException 
	 */
	public String modifyPasswordForMobile() throws ServiceException{
		
		//密码是否为空
		if(GeneralUtil.isNull(loginPwd)){
			
			addFieldError("loginPwd", getText("loginPwd.null"));
			return "retrievePasswordForMobile2Page";
		}
		//二次密码是否为空
		if(GeneralUtil.isNull(rloginPwd)){
			
			addFieldError("rloginPwd", getText("loginPwd.null"));
			return "retrievePasswordForMobile2Page";
		}
		//两次输入的密码不相同
		if(!loginPwd.equals(rloginPwd)){
			
			addFieldError("rloginPwd", getText("loginPwd.differ"));
			return "retrievePasswordForMobile2Page";
		}
		
		//修改密码成功
		Member member = (Member)ActionContext.getContext().getSession().get(ContextConstants.SCOPE_MEMBER);
		member.setLoginPwd(loginPwd);
		memberService.merge(member);
		ActionContext.getContext().put("message", getText("loginPwd.success"));
		return SUCCESS;
	}
	
	/**
	 * @Description：修改密码(手机号码)
	 * @return
	 * @throws ServiceException 
	 */
	public String modifyPasswordForMobileInterface() throws ServiceException{
		
		//检验是否登录
		if(GeneralUtil.isNull(phone)){
			
			data = AjaxData.responseError(getText("phone.required"));
			return JSON;
		}
		List<Member> members = memberService.getByColumn("phone", phone);
		if(members.size() == 0){
			
			data = AjaxData.responseError(getText("phone.error"));
			return JSON;
		}
		member = members.get(0);
		//密码是否为空
		if(GeneralUtil.isNull(loginPwd)){
			
			data = AjaxData.responseError(getText("loginPwd.null"));
			return JSON;
		}
		//二次密码是否为空
		if(GeneralUtil.isNull(rloginPwd)){
			
			data = AjaxData.responseError(getText("loginPwd.null"));
			return JSON;
		}
		//两次输入的密码不相同
		if(!loginPwd.equals(rloginPwd)){
			
			data = AjaxData.responseError(getText("loginPwd.differ"));
			return JSON;
		}
		
		//修改密码成功
		member.setLoginPwd(loginPwd);
		memberService.merge(member);
		
		data = AjaxData.responseSuccess("");
		return JSON;
	}
	
	/**
	 * @Description：修改密码（邮箱）
	 * @return
	 * @throws ServiceException 
	 */
	public String modifyPasswordForEmail() throws ServiceException{
		
		//密码是否为空
		if(GeneralUtil.isNull(loginPwd)){
			
			addFieldError("loginPwd", getText("loginPwd.null"));
			return "retrievePasswordForEmail2Page";
		}
		//二次密码是否为空
		if(GeneralUtil.isNull(rloginPwd)){
			
			addFieldError("rloginPwd", getText("loginPwd.null"));
			return "retrievePasswordForEmail2Page";
		}
		//两次输入的密码不相同
		if(!loginPwd.equals(rloginPwd)){
			
			addFieldError("rloginPwd", getText("loginPwd.differ"));
			return "retrievePasswordForEmail2Page";
		}
		
		//修改密码成功
		Member member = (Member)ActionContext.getContext().getSession().get(ContextConstants.SCOPE_MEMBER);
		member.setLoginPwd(loginPwd);
		memberService.merge(member);
		ActionContext.getContext().put("message", getText("loginPwd.success"));
		return SUCCESS;
	}
	
	/**
	 * @Description：修改密码（邮箱）
	 * @return
	 * @throws ServiceException 
	 */
	public String modifyPasswordInterface() throws ServiceException{
		
		//检验是否登录
		if(GeneralUtil.isNull(phone)){
			
			data = AjaxData.responseError(getText("member.id.required"));
			return JSON;
		}
		List<Member> members = memberService.getByColumn("phone", phone);
		if(members.isEmpty()){
			
			data = AjaxData.responseError(getText("user.phone.noExist"));
			return JSON;
		}
		member = members.get(0);
		//密码是否为空
		if(GeneralUtil.isNull(loginPwd)){
			
			data = AjaxData.responseError(getText("loginPwd.null"));
			return JSON;
		}
		//二次密码是否为空
		if(GeneralUtil.isNull(rloginPwd)){
			
			data = AjaxData.responseError(getText("loginPwd.null"));
			return JSON;
		}
		//两次输入的密码不相同
		if(!loginPwd.equals(rloginPwd)){
			
			data = AjaxData.responseError(getText("loginPwd.differ"));
			return JSON;
		}
		
		//修改密码成功
		member.setLoginPwd(loginPwd);
		memberService.merge(member);
		
		data = AjaxData.responseSuccess(getText("loginPwd.success"));
		return JSON;
	}
	
	/**
	 * @Description：进入邮箱修改页面1
	 * @return
	 */
	public String retrievePasswordForEmail1Page(){
		
		return "retrievePasswordForEmail1Page";
	}
	
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	
	public String getDestUrl() {
		return destUrl;
	}

	public void setDestUrl(String destUrl) {
		this.destUrl = destUrl;
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
	
	public String getRloginPwd() {
		return rloginPwd;
	}

	public void setRloginPwd(String rloginPwd) {
		this.rloginPwd = rloginPwd;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
