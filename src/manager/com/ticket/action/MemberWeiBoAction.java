package com.ticket.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.MemberAnnouncement;
import com.ticket.pojo.MemberWeiBo;
import com.ticket.service.IMemberAnnouncementService;
import com.ticket.service.IMemberWeiBoService;
import com.ticket.util.AuthCodeUtil;
import com.ticket.util.CookiesUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.ResourceUtil;
import com.ticket.util.SmsUtil;

import net.sf.json.JSONObject;
import weibo4j.Oauth;
import weibo4j.Users;
import weibo4j.http.AccessToken;
import weibo4j.model.User;
import weibo4j.model.WeiboException;
/**
 * 微博登录控制器
 * @author xw
 *
 */
public class MemberWeiBoAction extends BaseAction{
	
	@Resource
	private IMemberWeiBoService weiBoService = null;
	@Resource protected IMemberAnnouncementService memberAnnouncementService;
	
	private MemberWeiBo memberWeiBo = null;
	private String code = null;
	private Oauth oauth = null;
	private String phone = null;
	private String authCode = null;
	private String pc = null;
	private String wap = null;
	private String bjdj = null;
	private String openId = null;
	private String imgUrl = null;
	private String nickName = null;
	private String destUrl = null;
	private String lastUrl = null;
	private String bjdjYinCanUrl = null;
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * app 绑定会员时发送验证码
	 * @return
	 * @throws ServiceException
	 */
	public String appRelationMemberToSendCode() throws ServiceException{
		JSONObject object = new JSONObject();
		try{
			String authCode = AuthCodeUtil.generateAndSave("relation");
			SmsUtil.sendSms(phone, ResourceUtil.getText("authCode.tip" ,authCode));
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
	 * app 端绑定会员
	 * @return
	 * @throws ServiceException
	 */
	public String appRelationMember() throws ServiceException{
		JSONObject object = new JSONObject();
		boolean isSuc = AuthCodeUtil.check("relation", authCode);
		if(isSuc){
			boolean a = weiBoService.relationMember(phone,id);
			MemberWeiBo memberWeiBo = weiBoService.queryById(MemberWeiBo.class.getName(), id);
			if(a){
				object.put("status", 1);
				object.put("member", memberWeiBo.getMember());
				object.put("msg", getText("relationMemberSuc"));
				data = JSONObject.fromObject(object).toString();
				getSession().remove(ContextConstants.SCOPE_MEMBER);
				getSession().put(ContextConstants.SCOPE_MEMBER, memberWeiBo.getMember());
			}else{
				object.put("status", 0);
				object.put("member", memberWeiBo.getMember());
				object.put("msg", getText("relationMemberFailed"));
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
	/**
	 * app 端微博登录
	 * @return
	 * @throws ServiceException
	 * @throws WeiboException
	 */
	public String appWeiBoLogin() throws ServiceException, WeiboException{
		JSONObject object = new JSONObject();
		if(weiBoService.findByOpenId(openId) != null){
			if((weiBoService.findByOpenId(openId).getMember() != null)){
				object.put("member", weiBoService.findByOpenId(openId).getMember());
				getSession().put(ContextConstants.SCOPE_MEMBER, weiBoService.findByOpenId(openId).getMember());
				Cookie tourist = CookiesUtil.getCookies("JSESSIONID");
				if(GeneralUtil.isNotNull(tourist)){
					String tourist_id = tourist.getValue();
					List<MemberAnnouncement> announcements = memberAnnouncementService.queryByMember(tourist_id);
					if(announcements != null){
						for(MemberAnnouncement announcement:announcements){
							announcement.setMember_id(weiBoService.findByOpenId(openId).getMember().getId());
							memberAnnouncementService.merge(announcement);
						}
					}
				}
			}else{
				object.put("member", weiBoService.findByOpenId(openId));
				getSession().put(ContextConstants.SCOPE_MEMBER, weiBoService.findByOpenId(openId));
				Cookie tourist = CookiesUtil.getCookies("JSESSIONID");
				if(GeneralUtil.isNotNull(tourist)){
					String tourist_id = tourist.getValue();
					List<MemberAnnouncement> announcements = memberAnnouncementService.queryByMember(tourist_id);
					if(announcements != null){
						for(MemberAnnouncement announcement:announcements){
							announcement.setMember_id(weiBoService.findByOpenId(openId).getId());
							memberAnnouncementService.merge(announcement);
						}
					}
				}
			}
		}else{
			memberWeiBo =  weiBoService.add(openId, nickName, imgUrl,null);
			object.put("member", memberWeiBo);
			getSession().put(ContextConstants.SCOPE_MEMBER, memberWeiBo);
			Cookie tourist = CookiesUtil.getCookies("JSESSIONID");
			if(GeneralUtil.isNotNull(tourist)){
				String tourist_id = tourist.getValue();
				List<MemberAnnouncement> announcements = memberAnnouncementService.queryByMember(tourist_id);
				if(announcements != null){
					for(MemberAnnouncement announcement:announcements){
						announcement.setMember_id(memberWeiBo.getId());
						memberAnnouncementService.merge(announcement);
					}
				}
			}
		}
		data = JSONObject.fromObject(object).toString();
		return TEXT;
	}
	/**
	 * 微博登录
	 * @return
	 * @throws ServiceException
	 * @throws WeiboException
	 * @throws IOException
	 */
	public String weiBoLogin() throws ServiceException, WeiboException, IOException{
		oauth = new Oauth();
		if(GeneralUtil.isNotNull(pc)){
			ActionContext.getContext().getSession().put("pc", pc);
		}
		if(GeneralUtil.isNotNull(wap)){
			ActionContext.getContext().getSession().put("wap", wap);
		}
		if(GeneralUtil.isNotNull(bjdj)){
			ActionContext.getContext().getSession().put("bjdj", bjdj);
		}
		if(GeneralUtil.isNotNull(lastUrl)){
			ActionContext.getContext().getSession().put("lastUrl", lastUrl);  
		}
		if(GeneralUtil.isNotNull(bjdjYinCanUrl)){
			ActionContext.getContext().getSession().put("bjdjYinCanUrl", bjdjYinCanUrl);  
		}
		if(GeneralUtil.isNull(code)){
			response.sendRedirect(oauth.authorize("code"));
		}else{
			AccessToken at = oauth.getAccessTokenByCode(code);
			
			String access_token =at.getAccessToken();
			String uid = at.getUid();
			Users um = new Users(access_token);
			User user = um.showUserById(uid);
			if(weiBoService.findByOpenId(user.getId()) != null){
				if((weiBoService.findByOpenId(user.getId()).getMember() != null)){
					ActionContext.getContext().getSession().remove(ContextConstants.SCOPE_MEMBER);
					ActionContext.getContext().getSession().put(ContextConstants.SCOPE_MEMBER, weiBoService.findByOpenId(user.getId()).getMember());
				}else{
					ActionContext.getContext().getSession().put(ContextConstants.SCOPE_MEMBER, weiBoService.findByOpenId(user.getId()));
				}
			}else{
				memberWeiBo =  weiBoService.add(user.getId(), user.getScreenName(), user.getProfileImageUrl(),user.getGender());
				ActionContext.getContext().getSession().put(ContextConstants.SCOPE_MEMBER, memberWeiBo);
			}
		}
		String pc = (String)ActionContext.getContext().getSession().get("pc");
		String wap = (String)ActionContext.getContext().getSession().get("wap");
		String bjdj = (String)ActionContext.getContext().getSession().get("bjdj");
		String lastUrl = (String)ActionContext.getContext().getSession().get("lastUrl");
		String bjdjYinCanUrl = (String)ActionContext.getContext().getSession().get("bjdjYinCanUrl");
		if(GeneralUtil.isNotNull(pc)){
			if(GeneralUtil.isNotNull(bjdjYinCanUrl)){
				response.sendRedirect(bjdjYinCanUrl);
			}else if(GeneralUtil.isNotNull(bjdj)){
				response.sendRedirect("pcOrder.action");
			}else{
				if(GeneralUtil.isNotNull(lastUrl)){
					response.sendRedirect(lastUrl);
				}else{
					response.sendRedirect("airportPc.action");
				}
			}
		}
		if(GeneralUtil.isNotNull(wap)){
			if(GeneralUtil.isNotNull(bjdjYinCanUrl)){
				response.sendRedirect(bjdjYinCanUrl);
			}else if(GeneralUtil.isNotNull(bjdj)){
				response.sendRedirect("bjdj.action");
			}else{
				response.sendRedirect("airport.action");
			}
		}
		return null;
	}
	
	/**
	 * 绑定会员时发送验证码
	 * @return
	 * @throws Exception
	 */
	public String relationMemberToSendCode() throws Exception{
		SmsUtil.sendSms(phone, ResourceUtil.getText("sms.regist.success", phone,AuthCodeUtil.generateAndSave("relation")));
		data = AjaxData.responseSuccess(getText("authCode.sended"));
		return JSON;
	}
	/**
	 * 绑定会员
	 * @return
	 * @throws Exception
	 */
	public String relationMember() throws Exception{
		boolean isSuc = AuthCodeUtil.check("relation", authCode);
		if(isSuc){
			boolean a = weiBoService.relationMember(phone);
			if(a){
				data = AjaxData.responseSuccess(getText("relationMemberSuc"));
			}else{
				data = AjaxData.responseError(getText("relationMemberFailed"));
			}
		}else{
			data = AjaxData.responseError(getText("authCode.error"));
		}
		return JSON;
	}
	public MemberWeiBo getMemberWeiBo() {
		return memberWeiBo;
	}

	public void setMemberWeiBo(MemberWeiBo memberWeiBo) {
		this.memberWeiBo = memberWeiBo;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Oauth getOauth() {
		return oauth;
	}
	public void setOauth(Oauth oauth) {
		this.oauth = oauth;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCode() {
		return code;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	public String getPc() {
		return pc;
	}
	public void setPc(String pc) {
		this.pc = pc;
	}
	public String getWap() {
		return wap;
	}
	public void setWap(String wap) {
		this.wap = wap;
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
	public String getDestUrl() {
		return destUrl;
	}
	public void setDestUrl(String destUrl) {
		this.destUrl = destUrl;
	}
	public String getBjdj() {
		return bjdj;
	}
	public void setBjdj(String bjdj) {
		this.bjdj = bjdj;
	}
	public String getLastUrl() {
		return lastUrl;
	}
	public void setLastUrl(String lastUrl) {
		this.lastUrl = lastUrl;
	}
	public String getBjdjYinCanUrl() {
		return bjdjYinCanUrl;
	}
	public void setBjdjYinCanUrl(String bjdjYinCanUrl) {
		this.bjdjYinCanUrl = bjdjYinCanUrl;
	}
}
