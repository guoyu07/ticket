package com.ticket.action;

import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.Member;
import com.ticket.service.IQuickMenuMemberSettingService;
import com.ticket.util.AuthCodeUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SmsUtil;

public class PCBjdjMermberAction extends BjdjMemberAction {

	private static final long serialVersionUID = 1L;
	@Resource
	private IQuickMenuMemberSettingService quickMenuMemberSettingService;

	/**
	 * 进入便捷登机登录页面
	 */
	@Override
	public String execute() {
		String result = super.execute();
		return result;
	}
	
	/**
	 * 搜索出来的便捷登机登录页面
	 * @return
	 * @throws ServiceException
	 */
	public String loginIndexAjax() throws ServiceException{
		
		return "loginIndexAjax";
	}
	/**
	 * 进入登录首页
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public String toLoginPage() throws ServiceException {
		return "loginPage";
	}
	/**
	 * 登录控制器
	 */
	@Override
	public String login() throws ServiceException {
		// 检查用户名是否为空
		if (GeneralUtil.isNull(loginName)) {

			data = AjaxData.responseError(getText("loginName.required"));
			return JSON;
		}
		// 检查密码是否为空
		if (GeneralUtil.isNull(loginPwd)) {

			data = AjaxData.responseError(getText("loginPwd.required"));
			return JSON;
		}

		// 检验用户名
		List<Member> users = memberService.getByColumn("phone", loginName);
		if (users.size() == 0) {

			data = AjaxData.responseError(getText("loginName.notExist"));
			return JSON;
		}

		// 检验密码
		member = memberService.queryByMixAndPwd(loginName, loginPwd);
		if (member == null) {

			data = AjaxData.responseError(getText("loginPwd.error"));
			return JSON;
		}
		ActionContext.getContext().getSession()
				.put(ContextConstants.SCOPE_MEMBER, member);

		data = AjaxData.responseSuccess("登录成功");
		return JSON;
	}

	public String loginPensonCenter() throws ServiceException {

		return "loginIndex";
	}
	/**
	 * 是否登录
	 */
	@Override
	public String isLogin() {
		return super.isLogin();
	}
	/**
	 * 注销
	 * @return
	 * @throws ServiceException
	 */
	public String logouts() throws ServiceException {
		getSession().remove(ContextConstants.SCOPE_MEMBER);
		data = AjaxData.responseSuccess("注销成功");
		return JSON;
	}
	/**
	 * 发送验证码
	 */
	public String generateAuthCode() throws ServiceException {
		// 检查用户级是否为空
		if (GeneralUtil.isNull(phone)) {

			data = AjaxData.responseError(getText("phone.null"));
			return JSON;
		}

		String authCode = AuthCodeUtil.generateAndSave(phone);
		SmsUtil.sendSms(phone,
				getText("authCode.sms", new String[] { authCode }));
		data = AjaxData.responseSuccess("验证码已发送");
		return JSON;
	}
	/**
	 * 验证验证码
	 */
	public String checkAuthForMobile() throws ServiceException {
		// 检查用户名是否为空
		if (GeneralUtil.isNull(phone)) {

			data = AjaxData.responseError("手机号码不能为空");
			return JSON;
		}
		// 检查此手机是否已经注册
		List<Member> members = memberService.getByColumn("phone", phone);
		if (members.size() == 0) {
			String loginPwd = AuthCodeUtil.generate();
			member = new Member();
			member.setPhone(phone);
			member.setLoginName(phone);
			member.setRealName(phone);
			member.setNickName(phone);
			member.setLoginPwd(loginPwd);
			memberService.persist(member);
			
			quickMenuMemberSettingService.init(member.getId());
			SmsUtil.sendSms(
					phone,
					getText("sms.regist.success", new String[] { phone,
							loginPwd }));
		}
		// 验证不通过
		if (!baseService.validateSmsCodeIsOK(phone, authCode)) {

			data = AjaxData.responseError("验证码错误");
			return JSON;
		}
		Member member = members.get(0);
		ActionContext.getContext().getSession()
				.put(ContextConstants.SCOPE_MEMBER, member);
		data = AjaxData.responseSuccess("登录成功");
		return JSON;
	}

}
