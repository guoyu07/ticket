package com.ticket.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;

import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.AuthCodeHelper;
import com.ticket.pojo.MemberAnnouncement;
import com.ticket.pojo.MemberWeixin;
import com.ticket.service.IAuthCodeHelperService;
import com.ticket.service.IMemberAnnouncementService;
import com.ticket.service.IMemberService;
import com.ticket.service.IMemberWeixinService;
import com.ticket.util.AuthCodeUtil;
import com.ticket.util.CookiesUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.ResourceUtil;
import com.ticket.util.SmsUtil;

import net.sf.json.JSONObject;

public class MemberWeixinAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	@Resource
	private IMemberWeixinService memberWeixinService = null;
	@Resource
	private IAuthCodeHelperService authCodeHelperService = null;
	@Resource
	private IMemberService memberService = null;
	@Resource protected IMemberAnnouncementService memberAnnouncementService;
	public String WEIXIN_URL = "https://open.weixin.qq.com/connect/qrconnect?appid=wx68c48c4b59e2b651&redirect_uri=http%3a%2f%2fm.kunmingia.com%2fairportPc.action&response_type=code&scope=snsapi_login#wechat_redirect";
	public static final String APP_ID = "wx68c48c4b59e2b651";
	public static final String URL = "http://m.kunmingia.com/airport.action";
	
	private String openId = null;
	private String imgUrl = null;
	private String nickName = null;
	private String phone = null;
	private String authCode = null;
	
	public String weixinLogin() throws ServiceException, IOException{
//		String url = CommonUtil.urlEncodeUTF8(URL);
//		WEIXIN_URL.replaceAll("APPID", APP_ID);
//		WEIXIN_URL.replaceAll("URL", url);
		response.sendRedirect(WEIXIN_URL);
		return null;
	}
	
	/**
	 * app 端绑定会员时发送验证码用的接口
	 * @return
	 * @throws ServiceException
	 */
	public String appRelationMemberToSendCode() throws ServiceException{
		JSONObject object = new JSONObject();
		try{
			String authCode = AuthCodeUtil.generateAndSave("relations");
			SmsUtil.sendSms(phone, ResourceUtil.getText("authCode.tip" ,authCode));
			AuthCodeHelper authCodeHelper = new AuthCodeHelper();
			authCodeHelper.setCode(authCode);
			authCodeHelper.setPhone(phone);
			authCodeHelperService.persist(authCodeHelper);
			object.put("status", 1);
			object.put("authCode", authCode);
		}catch (Exception e) {
			object.put("status", 0);
			object.put("msg", "验证码发送失败！");
		}
		data = JSONObject.fromObject(object).toString();
		return TEXT;
	}
	/**
	 * app 微信登录
	 * @return
	 * @throws ServiceException
	 */
	public String appWeixinLogin() throws ServiceException{
		JSONObject object = new JSONObject();
		MemberWeixin weixin = memberWeixinService.queryByOpenId(openId);
		if(weixin != null){
			if(weixin.getMember() != null){
				object.put("member", weixin.getMember());
				getSession().put(ContextConstants.SCOPE_MEMBER, weixin.getMember());
				Cookie tourist = CookiesUtil.getCookies("JSESSIONID");
				if(GeneralUtil.isNotNull(tourist)){
					String tourist_id = tourist.getValue();
					List<MemberAnnouncement> announcements = memberAnnouncementService.queryByMember(tourist_id);
					if(announcements != null){
						for(MemberAnnouncement announcement:announcements){
							announcement.setMember_id(weixin.getMember().getId());
							memberAnnouncementService.merge(announcement);
						}
					}
				}
			}else{
				object.put("member", weixin);
				getSession().put(ContextConstants.SCOPE_MEMBER, weixin);
				Cookie tourist = CookiesUtil.getCookies("JSESSIONID");
				if(GeneralUtil.isNotNull(tourist)){
					String tourist_id = tourist.getValue();
					List<MemberAnnouncement> announcements = memberAnnouncementService.queryByMember(tourist_id);
					if(announcements != null){
						for(MemberAnnouncement announcement:announcements){
							announcement.setMember_id(weixin.getId());
							memberAnnouncementService.merge(announcement);
						}
					}
				}
			}
		}else{
			weixin = memberWeixinService.add(openId, nickName, imgUrl);
			object.put("member", weixin);
			getSession().put(ContextConstants.SCOPE_MEMBER, weixin);
			Cookie tourist = CookiesUtil.getCookies("JSESSIONID");
			if(GeneralUtil.isNotNull(tourist)){
				String tourist_id = tourist.getValue();
				List<MemberAnnouncement> announcements = memberAnnouncementService.queryByMember(tourist_id);
				if(announcements != null){
					for(MemberAnnouncement announcement:announcements){
						announcement.setMember_id(weixin.getId());
						memberAnnouncementService.merge(announcement);
					}
				}
			}
		}
		data = JSONObject.fromObject(object).toString();
		return TEXT;
	}
	
	/**
	 * app绑定会员接口
	 * @return
	 * @throws ServiceException
	 */
	public String appRelationMember() throws ServiceException{
		JSONObject object = new JSONObject();
		AuthCodeHelper authCodeHelper = authCodeHelperService.queryByphoneAndCode(phone, authCode);
		if(authCodeHelper != null){
			boolean a = memberWeixinService.relationMember(phone,id);
//			MemberWeixin memberWeixin = memberWeixinService.queryById(MemberWeixin.class.getName(), id);
			if(a){
				object.put("status", 1);
				object.put("member", memberService.queryByMobile(phone, "site"));
				object.put("msg", getText("relationMemberSuc"));
				data = JSONObject.fromObject(object).toString();
				getSession().remove(ContextConstants.SCOPE_MEMBER);
				getSession().put(ContextConstants.SCOPE_MEMBER, memberService.queryByMobile(phone, "site"));
			}else{
				object.put("status", 0);
				object.put("member", null);
				object.put("msg", "你的会员账号已经绑定过微信账号了~");
				data = JSONObject.fromObject(object).toString();
			}
		}else{
			object.put("status", 0);
			object.put("msg", getText("authCode.error"));
			object.put("member", null);
			data = JSONObject.fromObject(object).toString();
		}
		return TEXT;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
}
