package com.ticket.action;

import java.util.Date;

import com.hp.hpl.sparta.ParseException;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.Member;
import com.ticket.pojo.MemberDetailInfo;
import com.ticket.util.DateUtil;
import com.ticket.util.GeneralUtil;
/**
 * 
 * @author xw
 *
 */
public class PCMembersAction extends WapMemberAction{

	private static final long serialVersionUID = 1L;
	/**
	 * 跳转到会员注册页面
	 */
	public String register() throws Exception {
		return super.register();
	}
	/**
	 * 会员注册
	 */
	public String regist() throws ServiceException{
		return super.regist();
	}
	/**
	 * 调到登录页面
	 * @return
	 * @throws ServiceException
	 */
	public String toLoginPage() throws ServiceException {
		return "loginPage";
	}
	/**
	 * 登录控制器
	 */
	public String login() throws Exception {
		return super.login();
	}
	/**
	 * 个人账户信息设置
	 */
	public String personalSetting() throws ServiceException{
		return super.personalSetting();
	}
	/**
	 * 我的支付页面
	 * @return
	 * @throws ServiceException
	 */
	public String myPayInfo() throws ServiceException{
		return "myPayInfo";
	}
	/**
	 * 到达
	 * @return
	 * @throws ServiceException
	 */
	public String domesticArriveHaveNo() throws ServiceException{
		return "domesticArriveHaveNo";
	}
	/**
	 * 中转
	 * @return
	 * @throws ServiceException
	 */
	public String domesticToDomesticHaveNo() throws ServiceException{
		return "domesticToDomesticHaveNo";
	}
	/**
	 * 到达
	 * @return
	 * @throws ServiceException
	 */
	public String domesticGoNoFlightInfo() throws ServiceException{
		return "domesticGoNoFlightInfo";
	}
	/**
	 * 重置密码页面
	 * @return
	 * @throws ServiceException
	 */
	public String resetPwd() throws ServiceException{
		return "resetPwd";
	}
	/**
	 * 重置密码控制器
	 */
	public String resetPassword() throws ServiceException{
		return super.resetPassword();
	}
	/**
	 * 修改个人信息
	 * @return
	 * @throws ParseException
	 * @throws Exception
	 */
	public String updateInfo() throws ParseException, Exception{
		
		Member member = (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		MemberDetailInfo mDetail = memberDetailInfoService.queryByMemberId(member.getId(),versionFlag);
		if(mDetail == null){
			mDetail = new MemberDetailInfo();
			mDetail.setMember_id(member.getId());
		}
		//基本信息
		member.setRealName(realName);
		member.setEmail(email);
		member.setPhone(phone);
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
		
		
		mDetail.setBirthday(birthday);
		
		memberDetailInfoService.merge(mDetail);
		memberService.merge(member);
		//刷新缓存
		groupService.refresh("667bb53f-9792-4c06-88f1-1790cbe8db44");
		
		getSession().put(ContextConstants.ISCHANGEPERSONALDATA, "1");
		data = AjaxData.responseSuccess(getText("updateSuccess"));
		return JSON;
	}
}
