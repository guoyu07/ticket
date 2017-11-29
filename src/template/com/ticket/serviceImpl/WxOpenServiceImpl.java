package com.ticket.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.net.ssl.SSLContext;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
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
import com.ticket.util.OrderNumberUtil;
import com.ticket.util.UrlUtil;
import com.ticket.util.XMLUtil;

/**
 * 微信支付业务接口
 * @author tuyou
 */
public class WxOpenServiceImpl extends BaseServiceImpl<Member, String> implements IPayService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(WxOpenServiceImpl.class);
	
	@Resource
	private IMemberService memberService;
	@Resource
	private IBjdjRefundService bjdjRefundService;
	@Resource
	private IBjdjServiceCodeService bjdjServiceCodeService;
	
	public static final String payUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	public static final String refundUrl = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	public static final String charset = "UTF-8";
	public static String payHandler;
	
	//开放平台相关资料
	public static final String appid_ = "wx0fca5fed1774a138";
	public static final String appsecret_ = "076603ca03f0bb42b5d0b4f62b0dbd7f";
	//开放平台商户号资料
	public static final String partner_ = "1350351501";
	public static final String partnerkey_ = "ec14af12d5882673191f9ced3d2de99d";

	@Override
	public String getPayHandler() {

		if(payHandler == null){
			
			payHandler = UrlUtil.getDomainName() + "pay_wxPayHandler.action";
		}
		return payHandler;
	}
	
	/**
	 * 返回app请求参数
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public String orderPay(final BjdjOrder order, String redirectPath, String showPath) throws ServiceException {
		
		order.setPayMethod(PayMethod.weixin);
		merge(order);
		
		String nonce_str = OrderNumberUtil.generate();
		String trade_type = "APP";
		
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid_);  
		packageParams.put("mch_id", partner_);  
		packageParams.put("nonce_str", nonce_str);  
		packageParams.put("body", order.getName());  
		packageParams.put("attach", order.getMember().getId());  
		packageParams.put("out_trade_no", order.getNumber());
		packageParams.put("total_fee", (int)(order.getCount() * order.getPrice() * 100) + "");  //这里写的金额为1 分到时修改
		packageParams.put("spbill_create_ip", UrlUtil.getIpAddr(BaseAction.request));  
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
		String responseXML = HttpClientUtil.post(payUrl, requestXML);
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
		
		String packages = "Sign=WXPay";
		finalpackage.put("appid", appid_);  
		finalpackage.put("partnerid", partner_);  
		finalpackage.put("prepayid", prepay_id);  
		finalpackage.put("package", packages);  
		finalpackage.put("noncestr", nonce_str);  
		finalpackage.put("timestamp", timestamp);  
		fillSign(finalpackage);
		
		return JSON.toJSONString(finalpackage);
	}
	
	@Override
	public String queryOrderInfo(BjdjOrder order) throws ServiceException {
		
		return null;
	}

	@Override
	public String orderRefund(BjdjRefund refund) throws ServiceException {
		
		try {
			//交易时间超过1年的订单无法提交退款
			//支付部分退款
			BjdjOrder order = refund.getOrder();
			
			String nonce_str = OrderNumberUtil.generate();
			SortedMap<String, String> params = new TreeMap<String, String>();
			params.put("appid", appid_);
			params.put("mch_id", partner_); 
			params.put("nonce_str", nonce_str);  
			params.put("out_trade_no", order.getNumber());
			params.put("out_refund_no", refund.getId());
			params.put("total_fee", (int)(order.getCount() * order.getPrice() * 100) + "");  //这里写的金额为1 分到时修改
			params.put("refund_fee", (int)(order.getCount() * order.getPrice() * 100) + "");  //这里写的金额为1 分到时修改
			params.put("op_user_id", partner_); 
			
			//计算签名
			fillSign(params);
			
			//生成xml
			Element root = DocumentHelper.createElement("xml");
			for(String key : params.keySet()){
				
				Element sub = root.addElement(key);
				sub.addCDATA(params.get(key));
			}
			
			KeyStore keyStore  = KeyStore.getInstance("PKCS12");
			FileInputStream instream = new FileInputStream(new File("/data/apiclient_cert.p12"));
			try {
			    keyStore.load(instream, "1350351501".toCharArray());
			} finally {
			    instream.close();
			}

			// Trust own CA and all self-signed certs
			SSLContext sslcontext = SSLContexts.custom()
			        .loadKeyMaterial(keyStore, "1350351501".toCharArray())
			        .build();
			// Allow TLSv1 protocol only
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
			        sslcontext,
			        new String[] { "TLSv1" },
			        null,
			        SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
			CloseableHttpClient httpclient = HttpClients.custom()
			        .setSSLSocketFactory(sslsf)
			        .build();
			HttpPost post = new HttpPost(refundUrl);
			post.setEntity(new StringEntity(root.asXML()));
			CloseableHttpResponse responseHttp = httpclient.execute(post);
			HttpEntity entity = responseHttp.getEntity();
			String response = IOUtils.toString(entity.getContent());
			
			if(StringUtils.isBlank(response)){
				
				throw new ServiceException("退款请求错误");
			}
			Map<String, String> respParams;
			try {
				respParams = XMLUtil.parseToTreemap(response);
			} catch (DocumentException e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage(), e);
			}
			if("FAIL".equals(respParams.get("return_code"))){
				
				throw new ServiceException("下单失败：" + respParams.get("return_msg"));
			}
			if("FAIL".equals(respParams.get("result_code"))){
				
				throw new ServiceException("下单失败：" + respParams.get("err_code_des"));
			}
			System.out.println();
			if(!verifySign(respParams)){
				
				throw new ServiceException("签名错误");
			}
			
			//退款成功
			if("SUCCESS".equals(respParams.get("result_code"))){
				
				bjdjRefundService.response(refund, true, response);
			}else{
				
				bjdjRefundService.response(refund, false, response);
			}
			
			return response;
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void fillSign(Map<String, String> params) {
		
		String str = UrlUtil.mapToString(params) + "&key=" + partnerkey_;
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
			
			String value = params.get(key);
			if(StringUtils.isNotBlank(value)){
				
				newMap.put(key, value);
			}
		}
		
		newMap.remove("xml");
		String sign = newMap.remove("sign");
		String str = UrlUtil.mapToString(newMap) + "&key=" + partnerkey_;
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