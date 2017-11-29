package com.ticket.serviceImpl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.ServletException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.action.BaseAction;
import com.ticket.enumer.PayMethod;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjOrder;
import com.ticket.pojo.BjdjRefund;
import com.ticket.pojo.Member;
import com.ticket.service.IBjdjRefundService;
import com.ticket.service.IPayService;
import com.ticket.util.DateUtil;
import com.ticket.util.UrlUtil;
import com.ticket.util.ValidateUtil;

/**
 * 支付宝支付业务接口
 * @author tuyou
 */
public class AlipayServiceImpl extends BaseServiceImpl<Member, String> implements IPayService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(AlipayServiceImpl.class);
	private static String payHandler;
	
	public static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?";
	// 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String partner = "2088221650950002";
	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
	public static String seller_id = partner;
	// MD5密钥，安全检验码，由数字和字母组成的32位字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
    public static String key = "jb023sbwg7nyttq2xucvb7n5uzqelr9a";
	// 签名方式
	public static String sign_type = "MD5";
	// 字符编码格式 目前支持utf-8
	public static String input_charset = "utf-8";
	// 支付类型 ，无需修改
	public static String payment_type = "1";
	// 调用的接口名，h5调用
	public static String service_pay_h5 = "alipay.wap.create.direct.pay.by.user";
	// 调用的接口名，PC调用
	public static String service_pay_pc = "create_direct_pay_by_user";
	// 退款接口
	public static String service_refund = "refund_fastpay_by_platform_pwd";
	
	
	@Resource
	private IBjdjRefundService bjdjRefundService;
	
	@Override
	public String getPayHandler() {

		if(payHandler == null){
			
			payHandler = UrlUtil.getDomainName() + "pay_alipayPayHandler.action";
		}
		return payHandler;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public String orderPay(final BjdjOrder order, String redirectPath, String showPath) {
		
		return packageParams(order, service_pay_h5, redirectPath, showPath);
	}
	
	public String orderPayPC(final BjdjOrder order, String redirectPath, String showPath) {
		
		return packageParams(order, service_pay_pc, redirectPath, showPath);
	}
	
	/**
	 * 打包支付请求参数
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	private String packageParams(final BjdjOrder order, String service, String redirectPath, String showPath){
		
		order.setPayMethod(PayMethod.alipay);
		merge(order);
		
		Map<String, String> params = new TreeMap<String, String>();
		params.put("service", service);
        params.put("partner", partner);
        params.put("seller_id", seller_id);
        params.put("_input_charset", input_charset);
		params.put("payment_type", payment_type);
		params.put("notify_url", getPayHandler());
		params.put("return_url", UrlUtil.getDomainName() + redirectPath);
		params.put("out_trade_no", order.getNumber());
		params.put("subject", order.getName());
		params.put("total_fee", order.getPrice() * order.getCount() + "");
		params.put("show_url", UrlUtil.getDomainName() + showPath);
		params.put("body", order.getName());
		
		fillSign(params);
		
		ActionContext.getContext().put("params", params);
		return UrlUtil.mapToString(params);
	}
	
	@Override
	public String queryOrderInfo(BjdjOrder order) throws ServiceException {
		
		return null;
	}

	@Override
	public String orderRefund(BjdjRefund refund) throws ServiceException {
		
		//支付宝指定3个月内才可退款
		//支付部分退款
		
		BjdjOrder order = refund.getOrder();
		//把请求参数打包成数组
		Map<String, String> params = new TreeMap<String, String>();
		params.put("service", service_refund);
        params.put("partner", partner);
        params.put("_input_charset", input_charset);
		params.put("notify_url", UrlUtil.getDomainName() + "pay_alipayRefundHandler.action");
		params.put("seller_user_id", seller_id);
		params.put("refund_date", DateUtil.formatDateToSimpleString(refund.getStatus().getCreateTime()));
		//退款批次号(时间格式是yyyyMMddHHmmss+数字或者字母)
		params.put("batch_no", DateUtil.formatDateToString(refund.getStatus().getCreateTime()));
		//退款笔数，我们就设为1
		params.put("batch_num", "1");
		
		//退款详细数据(支付宝交易号^退款金额^备注)
		if(StringUtils.isBlank(order.getNumberOut())){
			
			throw new ServiceException("外部订单号为空");
		}
		
		StringBuilder sb = new StringBuilder(order.getNumberOut());
		sb.append("^");
		sb.append(order.getPrice() * order.getCount());
		sb.append("^");
		sb.append(ValidateUtil.htmlFilter(refund.getReason()));
		params.put("detail_data", sb.toString());
		
		//加入签名
		fillSign(params);
		
		ActionContext.getContext().put("params", params);
		try {
			BaseAction.request.getRequestDispatcher("/WEB-INF/jsp/template/wap/orderManager/alipay/alipayApi.jsp")
				.forward(BaseAction.request, BaseAction.response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void fillSign(Map<String, String> params) {
		
		String str = UrlUtil.mapToString(params) + key;
		String sign = null;
		try {
			sign = DigestUtils.md5Hex(str.getBytes(input_charset));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		params.put("sign", sign);
		params.put("sign_type", sign_type);
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
		
		newMap.remove("sign_type");
		String sign = newMap.remove("sign");
		String str = UrlUtil.mapToString(newMap) + key;
		String mySign = null;
		try {
			mySign = DigestUtils.md5Hex(str.getBytes(input_charset));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(StringUtils.isBlank(sign) || !sign.equalsIgnoreCase(mySign)){
			
			return false;
		}
		return true;
	}
}