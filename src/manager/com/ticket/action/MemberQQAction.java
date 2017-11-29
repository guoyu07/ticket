package com.ticket.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;

import com.opensymphony.xwork2.ActionContext;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.Member;
import com.ticket.pojo.MemberAnnouncement;
import com.ticket.pojo.MemberQQ;
import com.ticket.pojo.MemberWeiBo;
import com.ticket.service.IMemberAnnouncementService;
import com.ticket.service.IMemberQqService;
import com.ticket.service.IMemberService;
import com.ticket.service.IMemberWeiBoService;
import com.ticket.util.AuthCodeUtil;
import com.ticket.util.CookiesUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.ResourceUtil;
import com.ticket.util.SmsUtil;

import net.sf.json.JSONObject;
/**
 * QQ登录控制器
 * @author xw
 */
public class MemberQQAction extends BaseAction{
	
	@Resource
	private IMemberQqService memberQqService = null;
	@Resource
	private IMemberWeiBoService memberWeiBoService = null;
	@Resource
	private IMemberService memberService = null;
	@Resource protected IMemberAnnouncementService memberAnnouncementService;
	
	private static final long serialVersionUID = 1L;
	/**
	 * h5端appid
	 */
	public static final String APPID = "101303597";
	public static final String APPSESECRET = "eeb7ca7d0cec27af12400e02754b3658";
	public static final String BACK_URL = "http://m.kunmingia.com/memberQq_qqLogin.action";
	
	/**
	 * ios端appid
	 */
	public static final String APPID1 = "1105259212";
	/**
	 * 安卓端appid
	 */
	public static final String APPID2 = "1105376297";
	
	private String authCode = null;
	private String phone = null;
	private String openId = null;
	private String access_token = null;
	private String app_id = null;
	private String imgUrl = null;
	private String nickName = null;
	private String fromApp = null;
	private String destUrl = null;
	private String lastUrl = null;
	
	
	public String relationInteface() throws ServiceException{
		data = "http://m.kmcsia.com/airport.action";
		return TEXT;
	}
	/**
	 * 
	 * @return
	 */
	public String relation() throws ServiceException{
		if(GeneralUtil.isNotNull(fromApp)){
			data = AjaxData.responseError("");
		}else{
			data = AjaxData.responseSuccess("");
		}
		return JSON;
	}
	/**
	 * QQ登录
	 * @return
	 * @throws ServiceException
	 * @throws IOException 
	 */
	public String qqLogin() throws ServiceException, IOException{
		try {
            AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);

            String accessToken   = null,
                   openID        = null;
            if (accessTokenObj.getAccessToken().equals("")) {
                System.out.print("没有获取到响应参数");
            } else {
                accessToken = accessTokenObj.getAccessToken();
                OpenID openIDObj =  new OpenID(accessToken);
                openID = openIDObj.getUserOpenID();
                MemberQQ qq = memberQqService.findByOpenId(openID);
                if(qq != null){
                	if(qq.getMember() != null){
                		ActionContext.getContext().getSession().put(ContextConstants.SCOPE_MEMBER, qq.getMember());
                	}else{
                		ActionContext.getContext().getSession().put(ContextConstants.SCOPE_MEMBER, qq);
                	}
                }else{
                	
                	URL urlObj = new URL("https://graph.qq.com/user/get_user_info?access_token="+ accessToken +"&oauth_consumer_key="+ APPID +"&openid=" + openID);
            		//连接
            		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
            		
            		con.setRequestMethod("GET"); 
            		con.setDoInput(true);
            		con.setDoOutput(true);
            		con.setUseCaches(false);
            		
            		InputStream inputStream = con.getInputStream();
        			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        			String str = null;
        			StringBuffer buffer = new StringBuffer();
        			while ((str = bufferedReader.readLine()) != null) {
        				buffer.append(str);
        			}
        			
        			bufferedReader.close();
        			inputStreamReader.close();
        			inputStream.close();
        			inputStream = null;
        			con.disconnect();
        			JSONObject jsonObject = JSONObject.fromObject(buffer.toString());
        			
        			String imgUrl = jsonObject.getString("figureurl_qq_2");
        			if(imgUrl == null || imgUrl.equals("")){
        				imgUrl = jsonObject.getString("figureurl_qq_1");
        			}
            		
                	
                	UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
                	UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
                	if (userInfoBean.getRet() == 0) {
                		MemberQQ memberQQ = null;
                		try {
                			memberQQ = memberQqService.add(openID, userInfoBean.getNickname(), userInfoBean.getGender(), imgUrl);
                		} catch (ServiceException e) {
                			e.printStackTrace();
                		}
                		
                		ActionContext.getContext().getSession().put(ContextConstants.SCOPE_MEMBER, memberQQ);
                	}
                }

            }
        } catch (QQConnectException e) {
        	e.printStackTrace();
        }
		String pc = (String)ActionContext.getContext().getSession().get("pc");
		String wap = (String)ActionContext.getContext().getSession().get("wap");
		String bjdj = (String)ActionContext.getContext().getSession().get("bjdj");
		String lastUrl = (String)ActionContext.getContext().getSession().get("lastUrl");
		String bjdjYinCanUrl = (String)ActionContext.getContext().getSession().get("bjdjYinCanUrl");
		if(GeneralUtil.isNotNull(pc)){
			if(GeneralUtil.isNotNull(bjdjYinCanUrl)){
				ActionContext.getContext().getSession().remove("pc");
				ActionContext.getContext().getSession().remove("bjdjYinCanUrl");
				response.sendRedirect(bjdjYinCanUrl);
			}else
			if(GeneralUtil.isNotNull(bjdj)){
					ActionContext.getContext().getSession().remove("pc");
					ActionContext.getContext().getSession().remove("bjdj");
					response.sendRedirect("pcOrder.action");
			}else{
				if(GeneralUtil.isNotNull(lastUrl)){
					ActionContext.getContext().getSession().remove("pc");
					ActionContext.getContext().getSession().remove("lastUrl");
					response.sendRedirect(lastUrl);
				}else{
					ActionContext.getContext().getSession().remove("pc");
					response.sendRedirect("airportPc.action");
				}
			}
		}
		if(GeneralUtil.isNotNull(wap)){
			if(GeneralUtil.isNotNull(bjdjYinCanUrl)){
				ActionContext.getContext().getSession().remove("wap");
				ActionContext.getContext().getSession().remove("bjdjYinCanUrl");
				response.sendRedirect(bjdjYinCanUrl);
			}else if(GeneralUtil.isNotNull(bjdj)){
					ActionContext.getContext().getSession().remove("wap");
					ActionContext.getContext().getSession().remove("bjdj");
					response.sendRedirect("bjdj.action");
			}else{
				ActionContext.getContext().getSession().remove("wap");
				response.sendRedirect("airport.action");
			}
		}
		return JSON;
	}
	/**
	 * 绑定时发送验证码
	 * @return
	 * @throws Exception
	 */
	public String relationMemberToSendCode() throws Exception{
		SmsUtil.sendSms(phone, ResourceUtil.getText("authCode.tip" ,AuthCodeUtil.generateAndSave("relation")));
		data = getText("authCode.sended");
		return TEXT;
	}
	/**
	 * app QQ登录接口
	 * @return
	 * @throws ServiceException
	 * @throws QQConnectException 
	 * @throws IOException 
	 */
	public String appMemberQQLogin() throws ServiceException, QQConnectException, IOException{
		JSONObject object = new JSONObject();
		MemberQQ memberQQ = memberQqService.findByOpenId(openId);
		if(memberQQ != null){
			if(memberQQ.getMember() != null){
				object.put("member", memberQQ.getMember());
				getSession().put(ContextConstants.SCOPE_MEMBER, memberQQ.getMember());
				Cookie tourist = CookiesUtil.getCookies("JSESSIONID");
				if(GeneralUtil.isNotNull(tourist)){
					String tourist_id = tourist.getValue();
					List<MemberAnnouncement> announcements = memberAnnouncementService.queryByMember(tourist_id);
					if(announcements != null){
						for(MemberAnnouncement announcement:announcements){
							announcement.setMember_id(memberQQ.getMember().getId());
							memberAnnouncementService.merge(announcement);
						}
					}
				}
			}else{
				object.put("member", memberQQ);
				getSession().put(ContextConstants.SCOPE_MEMBER, memberQQ);
				Cookie tourist = CookiesUtil.getCookies("JSESSIONID");
				if(GeneralUtil.isNotNull(tourist)){
					String tourist_id = tourist.getValue();
					List<MemberAnnouncement> announcements = memberAnnouncementService.queryByMember(tourist_id);
					if(announcements != null){
						for(MemberAnnouncement announcement:announcements){
							announcement.setMember_id(memberQQ.getId());
							memberAnnouncementService.merge(announcement);
						}
					}
				}
			}
		}else{
    		try {
    			memberQQ = new MemberQQ();
    			memberQQ.setNickName(nickName);
    			memberQQ.setOpenid(openId);
    			memberQQ.setImages(imgUrl);
    			memberQQ.setMemberType(2);
    			memberQqService.persist(memberQQ);
    			object.put("member", memberQQ);
    			getSession().put(ContextConstants.SCOPE_MEMBER, memberQQ);
    			Cookie tourist = CookiesUtil.getCookies("JSESSIONID");
    			if(GeneralUtil.isNotNull(tourist)){
    				String tourist_id = tourist.getValue();
    				List<MemberAnnouncement> announcements = memberAnnouncementService.queryByMember(tourist_id);
    				if(announcements != null){
    					for(MemberAnnouncement announcement:announcements){
    						announcement.setMember_id(memberQQ.getId());
    						memberAnnouncementService.merge(announcement);
    					}
    				}
    			}
    		} catch (Exception e) {
    			object.put("status", "n");
    			object.put("msg", "请求失败！");
    			e.printStackTrace();
    		}
		}
		data = JSONObject.fromObject(object).toString();
		return TEXT;
	}
	
	/**
	 * app 端绑定会员时发送验证码用的接口
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
	 * app 端绑定会员账号
	 * @return
	 * @throws ServiceException
	 */
	public String appRelationMember() throws ServiceException{
		JSONObject object = new JSONObject();
		boolean isSuc = AuthCodeUtil.check("relation", authCode);
		if(isSuc){
			boolean a = false;
//			MemberQQ memberQQ = memberQqService.queryById(MemberQQ.class.getName(), id);
			a = memberQqService.relationMember(phone,id);
			if (a) {
				object.put("status", 1);
				object.put("member", memberService.queryByMobile(phone, "site"));
				object.put("msg", getText("relationMemberSuc"));
				data = JSONObject.fromObject(object).toString();
				getSession().remove(ContextConstants.SCOPE_MEMBER);
				getSession().put(ContextConstants.SCOPE_MEMBER, memberService.queryByMobile(phone, "site"));
			} else {
				object.put("status", 0);
				object.put("msg", getText("yourMeberIsRelationedQQ"));
				object.put("member", null);
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
	 * 绑定会员账号
	 * @return
	 * @throws Exception
	 */
	public String relationMember() throws Exception{
		boolean isSuc = AuthCodeUtil.check("relation", authCode);
		if(isSuc){
			boolean a = false;
			Member member = (Member)getSession().get(ContextConstants.SCOPE_MEMBER);
			if(member instanceof MemberQQ){
				a = memberQqService.relationMember(phone);
				if(a){
					ActionContext.getContext().getSession().remove(ContextConstants.SCOPE_MEMBER);
					ActionContext.getContext().getSession().put(ContextConstants.SCOPE_MEMBER, ((MemberQQ) member).getMember());
					data = AjaxData.responseSuccess(getText("relationMemberSuc"));
				}else{
					data = AjaxData.responseError(getText("yourMeberIsRelationedQQ"));
				}
			}else if(member instanceof MemberWeiBo){
				a = memberWeiBoService.relationMember(phone);
				if(a){
					ActionContext.getContext().getSession().remove(ContextConstants.SCOPE_MEMBER);
					ActionContext.getContext().getSession().put(ContextConstants.SCOPE_MEMBER, ((MemberWeiBo) member).getMember());
					data = AjaxData.responseSuccess(getText("relationMemberSuc"));
				}else{
					data = AjaxData.responseError(getText("yourMemberIsRelationedWeibo"));
				}
			}
		}else{
			data = AjaxData.responseError(getText("authCode.error"));
		}
		return JSON;
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
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
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
	public String getFromApp() {
		return fromApp;
	}
	public void setFromApp(String fromApp) {
		this.fromApp = fromApp;
	}
	public String getDestUrl() {
		return destUrl;
	}
	public void setDestUrl(String destUrl) {
		this.destUrl = destUrl;
	}
	public String getLastUrl() {
		return lastUrl;
	}
	public void setLastUrl(String lastUrl) {
		this.lastUrl = lastUrl;
	}
}
