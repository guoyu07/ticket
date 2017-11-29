package com.ticket.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.pojo.BjdjOrder;
import com.ticket.pojo.BjdjServiceCode;
import com.ticket.pojo.SecurityKey;
import com.ticket.service.IBjdjOrderService;
import com.ticket.service.IBjdjServiceCodeService;
import com.ticket.service.IMobileIntegralService;
import com.ticket.service.ISecurityKeyService;
import com.ticket.util.BaiduUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.UrlUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 移动方请求我方服务器的控制器
 * @author tuyou
 */
public class MobileRequestAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Resource
	protected IMobileIntegralService mobileIntegralService;
	@Resource
	protected ISecurityKeyService securityKeyService;
	@Resource
	protected IBjdjOrderService orderService;
	@Resource
	protected IBjdjServiceCodeService codeService;
	
	protected BjdjOrder order;
	protected BjdjServiceCode serviceCode;
	
	//请求数据
	protected String requestData;
	//手机号码
	protected String mobile;
	//电话号码
	protected String idcard;
	//公钥
	protected String appkey;
	//签名
	protected String sign;
	//请求类型
	protected String requestType;
	
	/**
	 * 购买一个服务码
	 * @return
	 */
	public String buy(){
		
		if(GeneralUtil.isNull(requestData)){
			
			data = AjaxData.responseError(getText("params.error"));
			return JSON;
		}
		
		JSONObject json = JSONObject.fromObject(requestData);
		mobile = json.getString("mobile");
		idcard = json.getString("idcard");
		appkey = json.getString("appkey");
		sign = json.getString("sign");
		requestType = json.getString("requestType");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("mobile", mobile);
		map.put("idcard", idcard);
		map.put("appkey", appkey);
		map.put("requestType", requestType);
		
		SecurityKey publicKey = securityKeyService.getByPublicKey(appkey);
		if(!BaiduUtil.checkSignature(map, publicKey.getSecretKey(), sign)){
			
			data = AjaxData.responseError(getText("sign.distort"));
			return JSON;
		}
//		try {
//			serviceCode = mobileIntegralService.buy(mobile);
//		} catch (ServiceException e) {
//			
//			data = AjaxData.responseError(e.getMessage());
//			return JSON;
//		}
		
		JSONObject codeJson = new JSONObject();
		codeJson.put("code", serviceCode.getCode());
		codeJson.put("creatTime", serviceCode.getStatus().getCreateTime().getTime());
		codeJson.put("expireTime", serviceCode.getExpireTime().getTime());
		codeJson.put("useStatus", serviceCode.getState().getName());
		codeJson.put("useStatusName", serviceCode.getState().getValue());
		codeJson.put("usedTime", null);
		codeJson.put("twoDimensionCode", UrlUtil.getDomainName() + serviceCode.getTwoDimensionCodePath().substring(1));
		
		data = AjaxData.responseSuccess(codeJson);
		return JSON;
	}
	
	/**
	 * 根据手机号码返回服务码列表
	 * @return
	 */
	public String codeList(){
		
		if(GeneralUtil.isNull(requestData)){
			
			data = AjaxData.responseError(getText("params.error"));
			return JSON;
		}
		
		JSONObject json = JSONObject.fromObject(requestData);
		mobile = json.getString("mobile");
		appkey = json.getString("appkey");
		sign = json.getString("sign");
		requestType = json.getString("requestType");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("mobile", mobile);
		map.put("appkey", appkey);
		map.put("requestType", requestType);
		
		SecurityKey publicKey = securityKeyService.getByPublicKey(appkey);
		if(!BaiduUtil.checkSignature(map, publicKey.getSecretKey(), sign)){
			
			data = AjaxData.responseError(getText("sign.distort"));
			return JSON;
		}
		List<BjdjServiceCode> codes = codeService.queryByMobileUser(mobile);
		
		JSONObject result = new JSONObject();
		result.put("mobile", mobile);
		
		JSONArray array = new JSONArray();
		for(BjdjServiceCode code : codes){
			
			JSONObject codeJson = new JSONObject();
			codeJson.put("code", code.getCode());
			codeJson.put("creatTime", code.getStatus().getCreateTime().getTime());
			codeJson.put("expireTime", code.getExpireTime().getTime());
			codeJson.put("useStatus", code.getState().getName());
			codeJson.put("useStatusName", code.getState().getValue());
			codeJson.put("usedTime", null);
			codeJson.put("twoDimensionCode", UrlUtil.getDomainName() + code.getTwoDimensionCodePath().substring(1));
			array.add(codeJson);
		}
		result.put("serviceCodeData", array);
		
		data = AjaxData.responseSuccess(result);
		return JSON;
	}

	public IMobileIntegralService getMobileIntegralService() {
		return mobileIntegralService;
	}

	public void setMobileIntegralService(
			IMobileIntegralService mobileIntegralService) {
		this.mobileIntegralService = mobileIntegralService;
	}

	public ISecurityKeyService getSecurityKeyService() {
		return securityKeyService;
	}

	public void setSecurityKeyService(ISecurityKeyService securityKeyService) {
		this.securityKeyService = securityKeyService;
	}

	public IBjdjOrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(IBjdjOrderService orderService) {
		this.orderService = orderService;
	}

	public BjdjOrder getOrder() {
		return order;
	}

	public void setOrder(BjdjOrder order) {
		this.order = order;
	}

	public String getRequestData() {
		return requestData;
	}

	public void setRequestData(String requestData) {
		this.requestData = requestData;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public BjdjServiceCode getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(BjdjServiceCode serviceCode) {
		this.serviceCode = serviceCode;
	}

	public IBjdjServiceCodeService getCodeService() {
		return codeService;
	}

	public void setCodeService(IBjdjServiceCodeService codeService) {
		this.codeService = codeService;
	}
}
