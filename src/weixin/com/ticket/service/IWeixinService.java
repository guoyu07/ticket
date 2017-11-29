package com.ticket.service;

import javax.servlet.http.HttpServletRequest;

import com.ticket.pojo.BasePojo;
import com.ticket.pojo.OutMessage;
import com.ticket.pojo.WeiXinInfo;

/**
 * 微信接口
 * @author haishui
 *
 */
public interface IWeixinService extends IBaseService<BasePojo, String> {
	/**
	 * 把key-value存入application的map里面
	 * @param key   随机值作为KEY值
	 * @param value 当前请求的地址
	 */
	void putStateValue(String key, String value);
	/**
	 * 根据state获取value值
	 * @param key
	 * @return
	 */
	String getStateByKey(String key);
	
	/**
	 * 根据第一步返回的code值获取用户的openid
	 * @param code
	 * @return
	 */
	String getOpenIdByCode(String code, Integer weiXinAccountType);
	
	/**
	 * 根据原始ID获取微信用户基本信息
	 * @param openId
	 * @return
	 */
	WeiXinInfo queryByOpenId(String openId, Integer weiXinAccountType);
	/**
	 * 获取AccessToken
	 * @return
	 */
	String getAccessToken(Integer weiXinAccountType);
	/**
	 * 创建自定义菜单
	 * @Title: IWeiXinService
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	boolean createMenu(Integer weiXinAccountType);
	/**
	 * 获取自定义菜单
	 * @Title: IWeiXinService
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	String getMenuInfo(Integer weiXinAccountType);
	/**
	 * 移除自定义菜单
	 * @Title: IWeiXinService
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	boolean removeMenu(Integer weiXinAccountType);
	/**
	 * 微信关注事件
	 * @Title: IWeiXinService
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param @return    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	OutMessage subscribeEvent(Integer weiXinAccountType);
	/**
	 * 解析request里面的参数
	 * @param request
	 * @return
	 */
	String getParameterValues(HttpServletRequest request);
	/**
	 * 从requestUrl里面获取parentUrl（murl）参数值
	 * @param requestUrl
	 * @return
	 */
	String parseParentUrlFromRequestUrl(String requestUrl);
	/**
	 * 获取随机码
	 * @param codeLength
	 * @return
	 */
	String randomRandomCode(int codeLength);
}
