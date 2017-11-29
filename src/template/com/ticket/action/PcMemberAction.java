package com.ticket.action;

import java.util.List;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.Member;
import com.ticket.pojo.MemberFavorite;
import com.ticket.pojo.MemberFocusFlight;
import com.ticket.pojo.QuickMenu;
import com.ticket.pojo.QuickMenuMemberSetting;
import com.ticket.service.IMemberFavoriteService;
import com.ticket.service.IMemberFocusFlightService;
import com.ticket.service.IMemberService;
import com.ticket.service.IQuickMenuMemberSettingService;
import com.ticket.service.IQuickMenuService;
import com.ticket.util.GeneralUtil;

/**
 * PC端会员中心控制器
 * @author dfq
 * @date 2015-12-31 11:21
 */
public class PcMemberAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	//会员实体的业务层
	@Resource IMemberService memberService = null;
	//快捷菜单的业务层
	@Resource IQuickMenuService quickMenuService = null;
	//会员设置快捷菜单的业务层
	@Resource IQuickMenuMemberSettingService quickMenuMemberSettingService = null;
	//会员关注航班的业务层
	@Resource IMemberFocusFlightService memberFocusFlightService = null;
	//会员收藏夹的业务层
	@Resource IMemberFavoriteService memberFavoriteService = null;
	
	//实体id
	private String id = null;
	//电话
	private String phone = null;
	//密码
	private String password = null;
	//航班状态(进港还离港)
	private String flightState = null;
	//航班号
	private String flightNumber = null;
	//航班日期
	private String flightDate = null;
	//会员角色(接机还是送机)
	private String memberRole = null;
	
	private List<MemberFavorite> memberFavoriteList = null;
	/**
	 * 会员注册
	 * @return
	 * @throws ServiceException
	 */
	public String register() throws ServiceException{
		//验证码是否输入正确
		if(!memberService.validateSmsCodeIsOK(phone, smsCode)){
			data = AjaxData.responseError(getText("smsCode.error"));
			return JSON;
		}
		//电话号码不能为空
		if(GeneralUtil.isNull(phone)) {
			data = AjaxData.responseError(getText("phone.required"));
			return JSON;
		}
		//验证电话号是否已被注册
		if(memberService.validePhoneExist(phone,versionFlag)){
			data = AjaxData.responseError(getText("phone.registed"));
			return JSON;
		}
		Member member = new Member();
		member.setPhone(phone);
		member.setLoginPwd(password);
		member.setLoginName(phone);
		CommonEntity status = member.getStatus();
		status.setVersionFlag(versionFlag);
		member.setStatus(status);
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
			data = AjaxData.responseSuccess(getText("registerSuccess"));
			return JSON;
		} catch (Exception e) {
			e.printStackTrace();
			data = AjaxData.responseSuccess(getText("registerFailed"));
		}
		return JSON;
	}
	
	/**
	 * 会员登录
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public String memberLogin() throws ServiceException{
		if(GeneralUtil.isNull(phone)){
			data = AjaxData.responseError(getText("phone.required"));
		}
		//密码不能为空
		if(GeneralUtil.isNull(password)) {
			data = AjaxData.responseError(getText("password.required"));
			return JSON;
		}
		Member member = memberService.queryByLoginnameAndLoginpwd(phone, password);
		if(member == null){
			data = AjaxData.responseError(getText("member.notExists"));
			return JSON;
		}else{
			data = AjaxData.responseSuccess(getText("loginSuccess"));
			getSession().put(ContextConstants.SCOPE_MEMBER, member);
			return JSON;
		}
		
	}
	/**
	 * 会员注销登录
	 * @return
	 * @throws ServiceException
	 */
	public String memberLogout() throws ServiceException{
		if(GeneralUtil.isNotNull(ContextConstants.SCOPE_MEMBER)){
			getSession().remove(ContextConstants.SCOPE_MEMBER);
		}
		return SUCCESS;
	}
	
	/**
	 * 会员关注航班
	 * @return
	 * @throws ServiceException
	 */
	public String memberFocusFlight() throws ServiceException{
		Member member = (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		MemberFocusFlight memberFocus = memberFocusFlightService.queryByMemberAndFlightNoAndDate(flightNumber,flightDate,flightState,versionFlag);
		if(memberFocus != null){
			data = AjaxData.responseError(getText("flight.focus"));
			return "myFocusOn";
		}
		memberFocus = memberFocusFlightService.persist(member.getId(), flightNumber, flightDate, memberRole, flightState, 
				null, null, null, null, null, null, null, null, null);
		
		try {
			memberFocusFlightService.persist(memberFocus);
			data = AjaxData.responseSuccess(getText("focusSuc"));
			return "myFocusOn";
		} catch (Exception e) {
			data = AjaxData.responseError(getText("focusFailed"));
		}
		return JSON;
	}
	
	/**
	 * 个人收藏夹
	 * @return
	 * @throws ServiceException
	 */
	public String myFavorite() throws ServiceException{
		List<MemberFavorite> list = memberFavoriteService.queryListByMember(versionFlag);
		if(list != null && !list.isEmpty()){
			this.setMemberFavoriteList(list);
		}
		return JSON;
	}
	
	/**
	 * 会员重置密码
	 * @return
	 * @throws ServiceException
	 */
	public String resetPassword() throws ServiceException{
		//非空验证
		if(GeneralUtil.isNull(phone)) {
			data = AjaxData.responseError(getText("mobile.required"));
			return JSON;
		}
		if(GeneralUtil.isNull(password)) {
			data = AjaxData.responseError(getText("password.required"));
			return JSON;
		}
		//验证码是否输入正确
		if(!memberService.validateSmsCodeIsOK(phone, smsCode)){
			data = AjaxData.responseError(getText("smsCode.error"));
			return JSON;
		}
		Member member = memberService.queryByMobile(phone,versionFlag);
		if(member != null){
			member.setLoginPwd(password);
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFlightState() {
		return flightState;
	}

	public void setFlightState(String flightState) {
		this.flightState = flightState;
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

	public String getMemberRole() {
		return memberRole;
	}

	public void setMemberRole(String memberRole) {
		this.memberRole = memberRole;
	}

	public List<MemberFavorite> getMemberFavoriteList() {
		return memberFavoriteList;
	}

	public void setMemberFavoriteList(List<MemberFavorite> memberFavoriteList) {
		this.memberFavoriteList = memberFavoriteList;
	}
	
	
	
}
