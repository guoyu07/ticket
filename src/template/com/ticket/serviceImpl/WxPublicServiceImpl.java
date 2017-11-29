package com.ticket.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionContext;
import com.ticket.action.BaseAction;
import com.ticket.enumer.PayMethod;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjOrder;
import com.ticket.pojo.BjdjRefund;
import com.ticket.pojo.Member;
import com.ticket.service.IBjdjRefundService;
import com.ticket.service.IMemberService;
import com.ticket.service.IPayService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.HttpClientUtil;
import com.ticket.util.OrderNumberUtil;
import com.ticket.util.TwoDimensionCode;
import com.ticket.util.UrlUtil;
import com.ticket.util.XMLUtil;

/**
 * 微信支付业务接口
 * @author tuyou
 */
public class WxPublicServiceImpl extends BaseServiceImpl<Member, String> implements IPayService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(WxPublicServiceImpl.class);
	
	@Resource
	private IMemberService memberService;
	@Resource
	private IBjdjRefundService bjdjRefundService;
	
	public static final String authorize_page = "https://open.weixin.qq.com/connect/oauth2/authorize?";
	public static final String gateway = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	public static final String access_token_page = "https://api.weixin.qq.com/sns/oauth2/access_token?";
	public static final String charset = "UTF-8";
	public static String payHandler;
	
	//公众平台相关资料
	public static final String appid = "wx26765484f66a690a";
	public static final String appsecret = "ec14af12d5882673191f9ced3d2de99d";
	//公众平台商户号资料
	public static final String partner = "1341403701";
	public static final String partnerkey = "ec14af12d5882673191f9ced3d2de99d";

	@Override
	public String getPayHandler() {

		if(payHandler == null){
			
			payHandler = UrlUtil.getDomainName() + "pay_wxPayHandler.action";
		}
		return payHandler;
	}
	
	/**
	 * 微信内置浏览器，返回h5端支付参数
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public String orderPay(final BjdjOrder order, String redirectPath, String showPath) throws ServiceException {
		
		order.setPayMethod(PayMethod.weixin);
		merge(order);
		
		String nonce_str = OrderNumberUtil.generate();
		String openid = getOpenid(order);
		
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);  
		packageParams.put("mch_id", partner);  
		packageParams.put("nonce_str", nonce_str);  
		packageParams.put("body", order.getName());  
		packageParams.put("attach", order.getMember().getId());  
		packageParams.put("out_trade_no", order.getNumber());
		packageParams.put("total_fee", (int)(order.getCount() * order.getPrice() * 100) + "");  //这里写的金额为1 分到时修改
		packageParams.put("spbill_create_ip", UrlUtil.getIpAddr(BaseAction.request));  
		packageParams.put("notify_url", getPayHandler());  
		packageParams.put("trade_type", "JSAPI");
		packageParams.put("openid", openid);  
		
		fillSign(packageParams);
		Element element = DocumentHelper.createElement("xml");
		for(String key : packageParams.keySet()){
			
			Element sub = element.addElement(key);
			sub.addCDATA(packageParams.get(key));
		}
		String requestXML = element.asXML();
		
		//预支付
		String responseXML = HttpClientUtil.post(gateway, requestXML);
		if(StringUtils.isBlank(responseXML)){
			
			throw new ServiceException("下单失败");
		}
		Map<String, String> responseMap;
		try {
			responseMap = XMLUtil.parseTomap(responseXML);
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		if("FAIL".equals(responseMap.get("return_code"))){
			
			throw new ServiceException("下单失败：" + responseMap.get("return_msg"));
		}
		if("FAIL".equals(responseMap.get("result_code"))){
			
			throw new ServiceException("下单失败：" + responseMap.get("err_code_des"));
		}
		
		SortedMap<String, String> finalpackage = new TreeMap<String, String>();
		String timestamp = System.currentTimeMillis() / 1000 + "";
		String prepay_id = responseMap.get("prepay_id");
		
		String packages = "prepay_id=" + prepay_id;
		finalpackage.put("appId", appid);  
		finalpackage.put("timeStamp", timestamp);  
		finalpackage.put("nonceStr", nonce_str);  
		finalpackage.put("package", packages);  
		finalpackage.put("signType", "MD5");
		fillSign(finalpackage);
		
		ActionContext.getContext().put("appid", appid);
		ActionContext.getContext().put("timeStamp", timestamp);
		ActionContext.getContext().put("nonceStr", nonce_str);
		ActionContext.getContext().put("packages", packages);
		ActionContext.getContext().put("sign", finalpackage.get("sign"));
		ActionContext.getContext().put("prepayid", prepay_id);
		
		JSONObject json = new JSONObject();
		json.put("openid", openid);
		json.put("partnerid", partner);
		json.put("prepayid", prepay_id);
		json.put("package", packages);
		json.put("nonceStr", nonce_str);
		json.put("timeStamp", timestamp);
		json.put("sign", finalpackage.get("sign"));
		return json.toString();
	}
	
	/**
	 * 用于在PC端支付的时候，获取二维码地址
	 * @param order
	 * @return 支付地址
	 * @throws ServiceException
	 * @throws IOException 
	 */
	public String codeUrl(final BjdjOrder order) throws ServiceException, IOException {
		
		String nonce_str = OrderNumberUtil.generate();
		String trade_type = "NATIVE";
		
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);  
		packageParams.put("mch_id", partner);  
		packageParams.put("nonce_str", nonce_str);  
		packageParams.put("body", order.getName());  
		packageParams.put("attach", order.getMember().getId());  
		packageParams.put("out_trade_no", order.getNumber());
		packageParams.put("total_fee", (int)(order.getCount() * order.getPrice() * 100) + "");  //这里写的金额为1 分到时修改
		packageParams.put("spbill_create_ip", UrlUtil.getIpAddr(ServletActionContext.getRequest()));  
		packageParams.put("notify_url", getPayHandler());  
		packageParams.put("trade_type", trade_type);
		
		fillSign(packageParams);
		Element element = DocumentHelper.createElement("xml");
		for(String key : packageParams.keySet()){
			
			Element sub = element.addElement(key);
			sub.addCDATA(packageParams.get(key));
		}
		String requestXML = element.asXML();
		
		//预支付
		String responseXML = HttpClientUtil.post(gateway, requestXML);
		if(StringUtils.isBlank(responseXML)){
			
			throw new ServiceException("下单失败");
		}
		Map<String, String> responseMap;
		try {
			responseMap = XMLUtil.parseTomap(responseXML);
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		if("FAIL".equals(responseMap.get("return_code"))){
			
			throw new ServiceException("下单失败：" + responseMap.get("return_msg"));
		}
		if("FAIL".equals(responseMap.get("result_code"))){
			
			throw new ServiceException("下单失败：" + responseMap.get("err_code_des"));
		}
		
		String codeUrl = responseMap.get("code_url");
		if(GeneralUtil.isNotNull(codeUrl)){
			
			TwoDimensionCode codeUtil = new TwoDimensionCode();
			String suffix = "png";
			String rootPath = ServletActionContext.getServletContext().getRealPath(File.separator);
			String httpPath = "/upload/" + UUID.randomUUID() + "." + suffix;
			String realPath = rootPath + httpPath;
			codeUtil.encoderQRCode(codeUrl, realPath, suffix, 20);
			return httpPath;
		}
        return null;
	}
	
	@Override
	public String queryOrderInfo(BjdjOrder order) throws ServiceException {
		
		return null;
	}

	@Override
	public String orderRefund(BjdjRefund refund) throws ServiceException {
		
		//交易时间超过1年的订单无法提交退款
		//支付部分退款
		BjdjOrder order = refund.getOrder();
		
		String nonce_str = OrderNumberUtil.generate();
		SortedMap<String, String> params = new TreeMap<String, String>();
		params.put("appid", appid);
		params.put("mch_id", partner); 
		params.put("nonce_str", nonce_str);  
		params.put("out_trade_no", order.getNumber());
		params.put("out_refund_no", refund.getId());
		params.put("total_fee", (int)(order.getCount() * order.getPrice() * 100) + "");  //这里写的金额为1 分到时修改
		params.put("refund_fee", (int)(order.getCount() * order.getPrice() * 100) + "");  //这里写的金额为1 分到时修改
		params.put("op_user_id", partner); 
		
		//计算签名
		String sign = DigestUtils.md5Hex(UrlUtil.mapToString(params) + "&key=" + appsecret);
		params.put("sign", sign);
		
		//生成xml
		Element root = DocumentHelper.createElement("xml");
		for(String key : params.keySet()){
			
			Element sub = root.addElement(key);
			sub.setText(params.get(key));
		}
		
		String response = HttpClientUtil.post(gateway, root.asXML());
		if(StringUtils.isBlank(response)){
			
			throw new ServiceException("退款请求错误");
		}
		Map<String, String> respParams;
		try {
			respParams = XMLUtil.parseTomap(response);
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
		if(verifySign(respParams)){
			
			throw new ServiceException("签名错误");
		}
		
		//退款成功
		if("SUCCESS".equals(respParams.get("result_code"))){
			
			bjdjRefundService.response(refund, true, response);
		}else{
			
			bjdjRefundService.response(refund, true, response);
		}
		
		return response;
	}
	
	/**
	 * 得到微信的Openid
	 * @return
	 */
	public String getOpenid(BjdjOrder order){
		
		String openid;
		if(order != null && order.getMember() != null && order.getMember().getOpenid() != null){
			
			return order.getMember().getOpenid();
		}
		
		//如果不是重定向过来的，则跳转到微信服务器授权
		String type = ServletActionContext.getRequest().getParameter("type");
		if(GeneralUtil.isNull(type) || !"redirect".equals(type)){
			
			authorize(order);
		}else{
			
			String code = ServletActionContext.getRequest().getParameter("code");
			//文件中未保存有，则向微信服务器请求，获取openid
			String URL = access_token_page + "appid="+appid+"&secret="+appsecret+"&code="+code+"&grant_type=authorization_code";
			String jsonStr = HttpClientUtil.get(URL, null);
			if (!StringUtils.isBlank(jsonStr)) {
				
				JSONObject json = JSONObject.parseObject(jsonStr);
				openid = json.getString("openid");
				order.getMember().setOpenid(openid);
				merge(order.getMember());
				return openid;
			}
		}
		return null;
	}
	
	/**
	 * 微信网页授权
	 */
	public void authorize(BjdjOrder order){
		
		try {
			//URLEncoder.encode后可以在backUri的url里面获取传递的所有参数
			String backUri = UrlUtil.getDomainName() + "bjdjOrderTemplate_" + "toPay.action?id="+order.getId()+"&type=redirect&payMethod=" + PayMethod.weixin.getValue();
			backUri = URLEncoder.encode(backUri, charset);
			//scope参数视各自需求而定，这里用scope=snsapi_base不弹出授权页面直接授权目的只获取统一支付接口的openid
			String url = authorize_page + "appid=" + appid + "&redirect_uri=" + backUri + "&response_type=code&scope=snsapi_base#wechat_redirect";
			ServletActionContext.getResponse().sendRedirect(url);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void fillSign(Map<String, String> params) {
		
		String str = UrlUtil.mapToString(params) + "&key=" + appsecret;
		String sign = null;
		try {
			sign = DigestUtils.md5Hex(str.getBytes(charset));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		params.put("sign", sign);
	}

	@Override
	public boolean verifySign(Map<String, String> params) {
		
		Map<String, String> newMap = new TreeMap<String, String>();
		for(String key : params.keySet()){
			
			newMap.put(key, params.get(key));
		}
		
		newMap.remove("xml");
		String sign = newMap.remove("sign");
		String str = UrlUtil.mapToString(newMap) + "&key=" + appsecret;
		String mySign = null;
		try {
			mySign = DigestUtils.md5Hex(str.getBytes(charset));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(StringUtils.isBlank(sign) || !sign.equalsIgnoreCase(mySign)){
			
			return false;
		}
		return true;
	}
}