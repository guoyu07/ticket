package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.service.IMemberService;
import com.ticket.service.ISmsService;

/**
 * 短信控制器
 * @ClassName: SmsAction
 * @author HiSay 聚名项目部（云南天蝎网络科技有限公司）
 * @date 2014-11-12 上午12:17:40
 */
public class SmsAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Resource 
	private ISmsService smsService = null;
	@Resource
	protected IMemberService memberService;
	//手机号码
	private String mobile = null;
	//发送类型
	private String sendType = null;
	
	/**
	 * 发送短信
	 */
	@Override
	public String execute() {
		
		boolean sendSuc = false;
		try {
			sendSuc = smsService.sendSmsCommon(mobile, sendType);
		} catch (Exception e) {
			
			data = AjaxData.responseError(e.getMessage()) ;
			return JSON;
		}
		if(sendSuc) {
			data = AjaxData.responseSuccess(getText("handler_success")) ;
		} else {
			data = AjaxData.responseError(getText("handler_failture")) ;
		}
		
		return JSON;
	}
	
//	/**
//	 * 验证短信验证码是否准确
//	 * @Title: validateSms 
//	 * @param @return
//	 * @param @throws Exception    设定文件 
//	 * @return String    返回类型 
//	 * @throws
//	 */
//	public String validateSms() throws Exception {
//		if(smsService.validateSmsCodeIsOK(mobile, smsCode)) {
//			AjaxUtil.writeAppJson(response, request, getText("handler_success"), null, 1, false) ;
//			return JSON;
//		} else {
//			AjaxUtil.writeAppJson(response, request, getText("smsCode.invalid"), null, 1, false) ;
//			return JSON;
//		}
//	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
}
