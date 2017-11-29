package com.ticket.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ticket.menu.HttpClientConnectionManager;
import com.ticket.pojo.Articles;
import com.ticket.pojo.BasePojo;
import com.ticket.pojo.OutMessage;
import com.ticket.pojo.WeiXinAccessToken;
import com.ticket.pojo.WeiXinInfo;
import com.ticket.service.IArticleService;
import com.ticket.service.INewsClassService;
import com.ticket.service.IWeixinService;
import com.ticket.service.MessageProcessingHandler;
import com.ticket.util.GeneralUtil;
import com.ticket.util.WeixinConstants;
import com.ticket.util.WxMenuUtils;
import com.qq.connect.utils.json.JSONObject;

public class WeixinServiceImpl extends BaseServiceImpl<BasePojo, String> implements IWeixinService {

	@Resource private INewsClassService newsClassService = null;
	@Resource private IArticleService articleService = null;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String getAccessToken(Integer weiXinAccountType) {
		WeiXinAccessToken token = null;
		Long expireTime = 7000 * 1000L;//accessToken的过期时间为7200秒
		
		if(getApplication().get(WeixinConstants.WEIXIN_ACCESS_TOKEN) != null) {
			token = (WeiXinAccessToken) getApplication().get(WeixinConstants.WEIXIN_ACCESS_TOKEN);
			if(System.currentTimeMillis() - token.getGetTime() < expireTime) {
				return token.getAccessToken();
			} else {
				//如果帐号类型为服务号，则获取服务号的AppId和AppSecret
				String tokenValue = null;
				if(weiXinAccountType == WeixinConstants.WEIXIN_ACCOUNT_SERVICE) {
					//tokenValue = WxMenuUtils.getAccessToken(getSetting().getServiceAppId(), getSetting().getServiceAppSecret());
				} else {
					//tokenValue = WxMenuUtils.getAccessToken(getSetting().getServiceAppId(), getSetting().getServiceAppSecret());
				}
				token = new WeiXinAccessToken();
				token.setAccessToken(tokenValue);
				token.setGetTime(System.currentTimeMillis());
				getApplication().put(WeixinConstants.WEIXIN_ACCESS_TOKEN, token);
			}
		} else {
			//如果帐号类型为服务号，则获取服务号的AppId和AppSecret
			String tokenValue = null;
			if(weiXinAccountType == WeixinConstants.WEIXIN_ACCOUNT_SERVICE) {
				//tokenValue = WxMenuUtils.getAccessToken(getSetting().getServiceAppId(), getSetting().getServiceAppSecret());
			} else {
				//tokenValue = WxMenuUtils.getAccessToken(getSetting().getSubscribeAppId(), getSetting().getSubscribeAppSecret());
			}
			token = new WeiXinAccessToken();
			token.setAccessToken(tokenValue);
			token.setGetTime(System.currentTimeMillis());
			getApplication().put(WeixinConstants.WEIXIN_ACCESS_TOKEN, token);
		}
		return token.getAccessToken();
	}
	
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	//错误信息：{"errcode":40013,"errmsg":"invalid appid"}
	/*//正确信息：{
		    "subscribe": 1, 
		    "openid": "o6_bmjrPTlm6_2sgVt7hMZOPfL2M", 
		    "nickname": "Band", 
		    "sex": 1, 
		    "language": "zh_CN", 
		    "city": "广州", 
		    "province": "广东", 
		    "country": "中国", 
		    "headimgurl":    "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0", 
		    "subscribe_time": 1382694957
	}*/
	public WeiXinInfo queryByOpenId(String openId, Integer weiXinAccountType) {
		try {
			if(weiXinAccountType == null) {
				weiXinAccountType = WeixinConstants.WEIXIN_ACCOUNT_SERVICE;
			}
			
			String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+this.getAccessToken(weiXinAccountType)+"&openid="+openId+"&lang=zh_CN";
			HttpGet get = HttpClientConnectionManager.getGetMethod(requestUrl);
			HttpResponse response = WxMenuUtils.httpclient.execute(get);
			String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
			
			JSONObject jsonObj = new JSONObject(jsonStr);  
		    String isSuc = jsonObj.getString("errcode");
		    //如果isSuc为空，在说明获取信息成功。
		    if(GeneralUtil.isNull(isSuc)) {
		    	WeiXinInfo info = new WeiXinInfo();
		    	info.setSubscribe(jsonObj.getInt("subscribe"));
		    	info.setOpenid(jsonObj.getString("openid"));
		    	info.setNickname(jsonObj.getString("nickname"));
		    	info.setSex(jsonObj.getInt("sex"));
		    	info.setLanguage(jsonObj.getString("language"));
		    	info.setCity(jsonObj.getString("city"));
		    	info.setProvince(jsonObj.getString("province"));
		    	info.setCountry(jsonObj.getString("country"));
		    	info.setHeadimgurl(jsonObj.getString("headimgurl"));
		    	info.setSubscribe_time(jsonObj.getLong("subscribe_time"));
		    	return info;
		    }
			return null;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean createMenu(Integer weiXinAccountType) {
		try {
			String menuJson = null;//newsClassService.generalWeiXinMenu(ContextConstants.MOBILE_VERSION);
			if(GeneralUtil.isNotNull(menuJson)) {
				String result = WxMenuUtils.createMenu(menuJson, this.getAccessToken(weiXinAccountType));
				if(WxMenuUtils.SUCCESS.equals(result)) {
					return true;
				}
			}
			return false;
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public String getMenuInfo(Integer weiXinAccountType) {
		try {
			return WxMenuUtils.getMenuInfo(this.getAccessToken(weiXinAccountType));
		} catch(Exception e) {
			return "";
		}
	}

	@Override
	public boolean removeMenu(Integer weiXinAccountType) {
		try {
			String result = WxMenuUtils.removeMenu(this.getAccessToken(weiXinAccountType));
			if(WxMenuUtils.SUCCESS.equals(result)) {
				return true;
			}
			return false;
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public OutMessage subscribeEvent(Integer weiXinAccountType) {
		try {
			OutMessage oms = new OutMessage();
			oms.setMsgType(MessageProcessingHandler.MSG_TYPE_NEWS);
			List<Articles> articles = new ArrayList<Articles>();
			
			/*NewsClass nc = newsClassService.queryByAlias(ContextConstants.MOBILE_VERSION, "weixin_subscribe", false);
			List<Article> list = articleService.queryArticleListFront(nc.getId(), null, ContextConstants.MOBILE_VERSION, null, true);
			if(list != null && !list.isEmpty()) {
				for(Article art : list) {
					Articles article = new Articles();
					article.setTitle(art.getTitle());
					article.setDescription(art.getContent());
					article.setPicUrl(art.getPicture());
					article.setUrl(art.getAssignUrl());
					articles.add(article);
				}
			}*/
			oms.setArticles(articles);
			oms.setArticleCount(articles.size());
			oms.setFuncFlag(0);
			return oms;
		} catch(Exception e) {
			return null;
		}
	}


	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String getOpenIdByCode(String code, Integer weiXinAccountType) {
		try {
			if(weiXinAccountType == null) {
				weiXinAccountType = WeixinConstants.WEIXIN_ACCOUNT_SERVICE;
			}
			String appId = null;
			String secret = null;
			/*if(weiXinAccountType == WeixinConstants.WEIXIN_ACCOUNT_SERVICE) {
				appId = getSetting().getServiceAppId();
				secret = getSetting().getServiceAppSecret();
			} else {
				appId = getSetting().getSubscribeAppId();
				secret = getSetting().getSubscribeAppSecret();
			}*/
			String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appId+"&secret="+secret+"&code="+code+"&grant_type=authorization_code";
			HttpGet get = HttpClientConnectionManager.getGetMethod(requestUrl);
			HttpResponse response = WxMenuUtils.httpclient.execute(get);
			String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
			JSONObject jsonObj = new JSONObject(jsonStr);  
		    return jsonObj.getString("openid");
		} catch(Exception e) {
			return "error";
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String getStateByKey(String key) {
		try {
			if(getApplication().get("stateMap") != null) {
				Map<String, String> stateMap = (Map<String, String>)getApplication().get("stateMap");
				for(Map.Entry<String, String> entry : stateMap.entrySet()){
					if(entry.getKey().equals(key)) {
						return entry.getValue();
					}
				} 
			}
			return "/WEB-INF/jsp/front/mobile/index.jsp";
		} catch(Exception e) {
			return "/WEB-INF/jsp/front/mobile/index.jsp";
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public void putStateValue(String key, String value) {
		try {
			if(getApplication().get("stateMap") != null) {
				Map<String, String> stateMap = (Map<String, String>)getApplication().get("stateMap");
				stateMap.remove(key);
				stateMap.put(key, value);
				getApplication().put("stateMap", stateMap);
			} else {
				Map<String, String> stateMap = new HashMap<String, String>();
				stateMap.put(key, value);
				getApplication().put("stateMap", stateMap);
			}
		} catch(Exception e) {
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String getParameterValues(HttpServletRequest request) {
		try {
			StringBuffer result = new StringBuffer();
			Map<String, String[]> maps = request.getParameterMap();
			if(maps != null && !maps.isEmpty()) {
				result.append("?");
				for(String key : maps.keySet()) {
					String[] temp = (String[]) maps.get(key);
					result.append(key).append("=").append(temp[0]).append("&");
				}
				String tempValue = result.toString();
				tempValue = tempValue.substring(0, tempValue.length() - 1);
				return tempValue;
			}
			return "";
		} catch(Exception e) {
			return "";
		}
	}


	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String parseParentUrlFromRequestUrl(String requestUrl) {
		try {
			Integer startIndex = requestUrl.indexOf("&murl=");
			if(startIndex != -1) {
				String str1 = requestUrl.substring(startIndex + 6);
				String str2 = str1.substring(0, str1.indexOf("&"));
				return str2;
			} else {
				return "";
			}
		} catch(Exception e) {
			return "";
		}
	}


	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String randomRandomCode(int codeLength) {
		Random rand = new Random();
		String result = "";
		for(int i=0; i<codeLength; i++) {
			result += rand.nextInt(10);
		}
		return result;
	}

}