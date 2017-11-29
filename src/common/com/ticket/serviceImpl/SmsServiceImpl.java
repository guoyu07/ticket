package com.ticket.serviceImpl;

import java.util.Random;

import javax.annotation.Resource;

import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.Member;
import com.ticket.service.IMemberService;
import com.ticket.service.ISmsService;
import com.ticket.util.AuthCodeUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.ResourceUtil;
import com.ticket.util.SmsUtil;

/**
 * 短信接口ServiceImpl
 * 
 * @ClassName: ISmsService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author HiSay 聚名项目部（云南天蝎网络科技有限公司）
 * @date 2014-11-4 上午09:17:26
 * 
 */
@SuppressWarnings("unchecked")
public class SmsServiceImpl extends BaseServiceImpl<Object, String> implements ISmsService {

	@Resource
	protected IMemberService memberService;
	
	public boolean sendSmsCode(String mobile) throws ServiceException {
		String smsCode = this.randomRandomCode(4);
		getSession().put(mobile + ContextConstants.SMS_VERIFICATE_CODE, smsCode);
		String content = "您本次操作的验证码为:"+smsCode+"，如非本人操作，还请忽略。" + SmsUtil.SMS_SIGN;
		SmsUtil.sendSms(mobile, content);
		return true;
	}

	public boolean sendSmsCodeByFindPassword(String mobile)
			throws ServiceException {
		String smsCode = AuthCodeUtil.generateAndSave(mobile);
		String content = "尊敬的用户，您正在使用密码找回功能，您本次验证码为："+ smsCode + "。如非本人操作，还请忽略。" + SmsUtil.SMS_SIGN;
		SmsUtil.sendSms(mobile, content);
		return true;
	}

	public boolean sendSmsCodeByLogin(String mobile)
	throws ServiceException {
		String smsCode = AuthCodeUtil.generateAndSave(mobile);
		getSession().put(mobile + ContextConstants.SMS_VERIFICATE_CODE, smsCode);
		String content = "您本次登录的验证码为:"+smsCode+"，如非本人操作，还请忽略。" + SmsUtil.SMS_SIGN;
		SmsUtil.sendSms(mobile, content);
		return true;
	}
	
	public String sendSmsCodeByRegister(String mobile) throws ServiceException {
		String smsCode = AuthCodeUtil.generateAndSave(mobile);
		String content = "亲，您正在注册会员，您本次的验证码为："+ smsCode + "。如非本人操作，还请忽略。" + SmsUtil.SMS_SIGN;
		SmsUtil.sendSms(mobile, content);
		return smsCode;
	}
	
	public String sendSmsCodeByNameCard(String mobile) throws ServiceException {
		String smsCode = AuthCodeUtil.generateAndSave(mobile);
		String content = "亲，您正在进行名片操作，您本次的验证码为："+ smsCode + "。如非本人操作，还请忽略。" + SmsUtil.SMS_SIGN;
		SmsUtil.sendSms(mobile, content);
		return smsCode;
	}
	
	public String sendSmsCodeByApplyStore(String mobile) throws ServiceException {
		String smsCode = AuthCodeUtil.generateAndSave(mobile);
		String content = "亲，您正在进行申请微店铺操作，您本次的验证码为："+ smsCode + "。如非本人操作，还请忽略。" + SmsUtil.SMS_SIGN;
		SmsUtil.sendSms(mobile, content);
		return smsCode;
	}

	public boolean validateSmsCode(String mobile, String smsCode)
			throws ServiceException {
		String smsCodeSession = getSession().get(mobile + ContextConstants.SMS_VERIFICATE_CODE).toString();
		if(smsCode.equals(smsCodeSession)) {
			getSession().remove(mobile + ContextConstants.SMS_VERIFICATE_CODE);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 生成一位指定长度的随机数字
	 * @return
	 */
	private String randomRandomCode(int codeLength) {
		Random rand = new Random();
		String result = "";
		for(int i=0; i<codeLength; i++) {
			result += rand.nextInt(10);
		}
		return result;
	}

	@Override
	public boolean sendSmsCommon(String mobile, String sendType)
			throws ServiceException {
		if(GeneralUtil.isNotNull(mobile) && GeneralUtil.isNotNull(sendType)) {
			if("register".equals(sendType)) {//注册的时候发送验证码
					
				//验证用户是否已经存在
				Member member = memberService.queryByMobile(mobile, versionFlag);
				if(member != null){
					
					throw new ServiceException(ResourceUtil.getText("user.phone.exist"));
				}
				this.sendSmsCodeByRegister(mobile) ;
				return true;
			} else if("findPassword".equals(sendType)) { //找回密码的时候发送验证码
				this.sendSmsCodeByFindPassword(mobile) ;
				return true;
			} else if("login".equals(sendType)) { //登录的时候发送验证码
				this.sendSmsCodeByLogin(mobile) ;
				return true;
			} else if("resetPassword".equals(sendType)) { //重置密码
				
				//验证用户是否已经存在
				Member member = memberService.queryByMobile(mobile, versionFlag);
				if(member == null){
					
					throw new ServiceException(ResourceUtil.getText("loginName.error"));
				}
				this.sendSmsCodeByLogin(mobile) ;
				return true;
			}
		}
		return false;
	}
}
