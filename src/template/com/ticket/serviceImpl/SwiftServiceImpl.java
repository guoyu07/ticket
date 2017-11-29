package com.ticket.serviceImpl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.transaction.annotation.Transactional;

import com.ticket.action.BaseAction;
import com.ticket.enumer.PayMethod;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjOrder;
import com.ticket.pojo.BjdjRefund;
import com.ticket.pojo.Member;
import com.ticket.service.IBjdjRefundService;
import com.ticket.service.IBjdjServiceCodeService;
import com.ticket.service.IMemberService;
import com.ticket.service.IPayService;
import com.ticket.util.HttpClientUtil;
import com.ticket.util.UrlUtil;
import com.ticket.util.XMLUtil;

/**
 * 微信支付业务接口
 * @author tuyou
 */
public class SwiftServiceImpl extends BaseServiceImpl<Member, String> implements IPayService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(SwiftServiceImpl.class);
	@Resource
	private IMemberService memberService;
	@Resource
	private IBjdjServiceCodeService bjdjServiceCodeService;
	@Resource
	private IBjdjRefundService bjdjRefundService;
	@Resource(name="wxPublicService")
	private IPayService wxPublicService;
	
	//威富通接口数据
	public static Map<String,String> orderResult;
	public static String key = "cd856f9eebc94e4ae8fd974a287e58e5";
	public static String mch_id = "100560000317";
	public static final String url = "https://pay.swiftpass.cn/pay/gateway";
	public static String sign_type = "MD5";
	public static String charset = "UTF-8";
	public static String payHandler;
	
	
	@Override
	public String getPayHandler() {
		
		if(payHandler == null){
			
			payHandler = UrlUtil.getDomainName() + "pay_swiftPayHandler.action";
		}
		return payHandler;
	}

	/**
	 * 微信内置浏览器，返回h5端支付参数
	 * @throws IOException 
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public String orderPay(final BjdjOrder order, String redirectPath, String showPath) throws ServiceException {
		
		order.setPayMethod(PayMethod.swift);
		merge(order);
		
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("service", "pay.weixin.jspay");
		packageParams.put("mch_id", mch_id);
		packageParams.put("out_trade_no", order.getNumber());
		packageParams.put("body", order.getPackageType().getName());
		packageParams.put("total_fee", (int)(order.getCount() * order.getPrice() * 100) + "");
		packageParams.put("mch_create_ip", UrlUtil.getIpAddr(BaseAction.request).split(",")[0]);
		packageParams.put("notify_url", getPayHandler());
		packageParams.put("nonce_str", order.getNumber());
		packageParams.put("charset", charset);
		packageParams.put("callback_url", UrlUtil.getDomainName() + redirectPath);
		
		fillSign(packageParams);
		Element root = DocumentHelper.createElement("xml");
		for(String key : packageParams.keySet()){
			
			Element element = root.addElement(key);
			element.addCDATA(packageParams.get(key));
		}
		String requestXML = root.asXML();
        String responseXML = HttpClientUtil.post(url, requestXML);
        if(StringUtils.isBlank(responseXML)){
        	
        	throw new ServiceException("支付请求失败");
        }
        Map<String, String> responseMap;
		try {
			responseMap = XMLUtil.parseTomap(responseXML);
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
		if(!"0".equals(responseMap.get("status"))){
			
			throw new ServiceException(responseMap.get("message"));
		}
		if(!"0".equals(responseMap.get("result_code"))){
			
			throw new ServiceException(responseMap.get("err_msg"));
		}
        if(!verifySign(responseMap)){
        	
        	throw new ServiceException("支付请求结果签名错误");
        }
        
    	try {
			BaseAction.response.sendRedirect("https://pay.swiftpass.cn/pay/jspay?token_id=" + 
					responseMap.get("token_id") + "&showwxtitle=1");
		} catch (IOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
    	return null;
	}
	
	/**
	 * 用于在PC端支付的时候，获取二维码地址
	 * @param order
	 * @return 支付地址
	 * @throws ServiceException
	 * @throws IOException 
	 */
	@Transactional(rollbackFor=Exception.class)
	public String codeUrl(final BjdjOrder order) throws ServiceException {
		
		order.setPayMethod(PayMethod.swift);
		merge(order);
		
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("attach", "附加信息");
		packageParams.put("body", order.getPackageType().getName());
		packageParams.put("charset", charset);
		packageParams.put("mch_create_ip", UrlUtil.getIpAddr(BaseAction.request).split(",")[0]);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", order.getNumber());
		packageParams.put("notify_url", getPayHandler());
		packageParams.put("out_trade_no", order.getNumber());
		packageParams.put("service", "pay.weixin.native");
		packageParams.put("total_fee", (int)(order.getCount() * order.getPrice() * 100) + "");
		packageParams.put("version", "1.0");
		
		fillSign(packageParams);
		Element root = DocumentHelper.createElement("xml");
		for(String key : packageParams.keySet()){
			
			Element element = root.addElement(key);
			element.addCDATA(packageParams.get(key));
		}
		String requestXML = root.asXML();
        String responseXML = HttpClientUtil.post(url, requestXML, "utf-8");
        if(StringUtils.isBlank(responseXML)){
        	
        	throw new ServiceException("支付请求失败");
        }
        Map<String, String> responseMap;
		try {
			responseMap = XMLUtil.parseTomap(responseXML);
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
		if(!"0".equals(responseMap.get("status"))){
			
			throw new ServiceException(responseMap.get("message"));
		}
		if(!"0".equals(responseMap.get("result_code"))){
			
			throw new ServiceException(responseMap.get("err_msg"));
		}
        if(!verifySign(responseMap)){
        	
        	throw new ServiceException("支付请求结果签名错误");
        }
        return responseMap.get("code_img_url");
	}
	
	@Override
	public String queryOrderInfo(BjdjOrder order) throws ServiceException {
		
		return null;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public String orderRefund(BjdjRefund refund) throws ServiceException {
		
		BjdjOrder order = refund.getOrder();
		
		Map<String, String> params = new TreeMap<String, String>();
		params.put("service", "unified.trade.refund");
		params.put("mch_id", mch_id);
		params.put("out_trade_no", order.getNumber());
		params.put("out_refund_no", refund.getId());
		params.put("total_fee", ((int)(order.getPrice() * order.getCount() * 100)) + "");
		params.put("refund_fee", ((int)(order.getPrice() * order.getCount() * 100)) + "");
		params.put("op_user_id", mch_id);
		params.put("nonce_str", refund.getId());
		
		fillSign(params);
		Element root = DocumentHelper.createElement("xml");
		for(String key : params.keySet()){
			
			Element element = root.addElement(key);
			element.addCDATA(params.get(key));
		}
		String requestXML = root.asXML();
		String xml = HttpClientUtil.post(url, requestXML, "utf-8");
		try {
			Map<String, String> response = XMLUtil.parseTomap(xml);

			if(!"0".equals(response.get("status"))){
				
				throw new ServiceException(response.get("message"));
			}
			if(!"0".equals(response.get("result_code"))){
				
				throw new ServiceException(response.get("err_msg"));
			}
			//检查签名，及是否退款成功
			if(!verifySign(response)){
				
				throw new ServiceException("退款结果签名错误");
			}
			
			//检查支付返回状态标识
			if("0".equals(response.get("status")) && "0".equals(response.get("result_code"))){
				
				bjdjRefundService.response(refund, true, xml);
			}else{
				
				bjdjRefundService.response(refund, false, xml);
			}
			
		} catch (DocumentException e) {

			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
		
		return xml;
	}
	
	@Override
	public void fillSign(Map<String, String> params) {
		
		String str= UrlUtil.mapToString(params) + "&key=" + key;
		String sign = null;
		try {
			sign = DigestUtils.md5Hex(str.getBytes(charset)).toUpperCase();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		params.put("sign", sign);
//		params.put("sign_type", sign_type);
	}

	@Override
	public boolean verifySign(Map<String, String> params) {
		
		Map<String, String> newMap = new TreeMap<String, String>();
		for(String key : params.keySet()){
			
			String value = params.get(key);
			if(StringUtils.isNotBlank(value)){
				
				newMap.put(key, value);
			}
		}
		
		newMap.remove("xml");
		String sign = newMap.remove("sign");
		
		String str = UrlUtil.mapToString(newMap) + "&key=" + key;
		String mySign = null;
		try {
			mySign = DigestUtils.md5Hex(str.getBytes(charset)).toUpperCase();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(StringUtils.isBlank(sign) || !sign.equalsIgnoreCase(mySign)){
			
			return false;
		}
		return true;
	}
}