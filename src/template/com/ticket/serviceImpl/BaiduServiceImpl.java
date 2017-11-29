//package com.ticket.serviceImpl;
//
//import java.io.IOException;
//import java.util.Map;
//import java.util.TreeMap;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.ticket.enumer.PayMethod;
//import com.ticket.exception.ServiceException;
//import com.ticket.pojo.BjdjOrder;
//import com.ticket.pojo.BjdjRefund;
//import com.ticket.pojo.Member;
//import com.ticket.service.IPayService;
//import com.ticket.util.BaiduUtil;
//import com.ticket.util.GeneralUtil;
//import com.ticket.util.ResourceUtil;
//import com.ticket.util.UrlUtil;
//
///**
// * 百度接口业务接口
// * @author tuyou
// *
// */
//public class BaiduServiceImpl extends BaseServiceImpl<Member, String> implements IPayService {
//
//	@SuppressWarnings("unused")
//	private static final Log log = LogFactory.getLog(BaiduServiceImpl.class);
//
//	@Override
//	@Transactional(rollbackFor=Exception.class)
//	public String orderPay(BjdjOrder order, String handlerPath, String redirectPath, String showPath) throws ServiceException {
//		
//		
////		返回（正常）：
////		{
////		  "order_id": 12122832087,
////		  "url": "http://m.baidu.com/lightapp/pay/v2/order?id=121232478"//若接入的直达号拥有vip权限，需要在该支付链接url参数中附加is_vip=1，以避免支付中间页双banner问题
////		}
////		返回（异常）：
////		{
////		  "error_code": 123,
////		  "error_msg":"xxxx"
////		}
//		
//		//对接百度方支付接口
//		order.setPayMethod(PayMethod.baidu);
//		merge(order);
//		
//		Map<String,String> parameters = new TreeMap<String,String>();
//		parameters.put("sp_no", BaiduUtil.SP_NO+"");
//		parameters.put("order_no", order.getNumber());
//		parameters.put("total_amount", (int)(order.getPrice() * order.getCount() * 100) + "");
//		parameters.put("goods_name", order.getName());
//		parameters.put("return_url", UrlUtil.getDomainName() + handlerPath);
//		parameters.put("page_url", UrlUtil.getDomainName() + redirectPath);
//		parameters.put("order_source_url", UrlUtil.getDomainName() + showPath);
//		
////		"[{\"item_id\":\"po8348865999721745\",\"cat_id\":3,\"name\":\"\u65e5\u672c\u5bff\u53f8\","
////		+ "\"desc\":\"\u5f88\u597d\u5403\",\"price\":2000,\"amount\":1}]"
//		JSONArray array = new JSONArray();
//		JSONObject obj = new JSONObject();
//		obj.put("item_id", order.getId());
//		obj.put("cat_id", 3);
//		obj.put("name", order.getName());
//		obj.put("desc", order.getName());
//		obj.put("price", order.getPrice() * 100);
//		obj.put("amount", order.getCount());
//		array.add(obj);
//		
//		parameters.put("detail", array.toString());
//		parameters.put("expire_time", order.getTimeout()+"");
//		parameters.put("customer_name", order.getMobile());
//		parameters.put("customer_mobile", order.getMobile());
//		parameters.put("customer_address", "昆明");
//		String sign = BaiduUtil.getSignature(parameters, BaiduUtil.SECRET_KEY) ;
//		parameters.put("sign", sign);
//		
//		String paramsStr = UrlUtil.mapToString(parameters);
//		String resultStr = BaiduUtil.httpRequest(BaiduUtil.ORDER_ADD_URL, "POST", paramsStr);
//		
//		JSONObject json = JSONObject.parseObject(resultStr);
//		String orderId = json.getString("order_id");
//		String url = json.getString("url");
//		
//		//支付失败的情况
//		//失败result示例：{"error_code":1008748,"error_msg":"invalid sign"}
//		if(GeneralUtil.isNull(orderId) || GeneralUtil.isNull(url)){
//			
//			throw new ServiceException(ResourceUtil.getText("order.generate.fail"));
//		}
//		
//		//支付成功的情况
//		//成功result示例：{"order_id":20668017,"url":"http:\/\/m.baidu.com\/lightapp\/pay\/v2\/order?id=20668017"}
//		order.setNumberOut(orderId);
//		order.setPayUrl(url);
//		merge(order);
//		return url;
//	}
//	
////	请求示例
////	sp_no=1003&order_no=234342&order_id=234323 &sign=DINUYSjlQy7kUHccrDDskzS4z
////	返回（正常）：{"order_id":"220300","order_no":"24323_test","sp_no":"1003","total_amount":"37500","paid_amount":"37500","name":"订单名称","status":"1","pay_time":"1425203166"}
////	返回（异常）：{"error_code": 123, "error_msg": "xxxx"}
//	@Override
//	public String queryOrderInfo(BjdjOrder order) throws ServiceException {
//		
//		Map<String,String> parameters = new TreeMap<String,String>();
//		parameters.put("sp_no", BaiduUtil.SP_NO+"");
//		parameters.put("order_no", order.getNumber());
//		parameters.put("order_id", order.getNumberOut());
//		String sign = BaiduUtil.getSignature(parameters, BaiduUtil.SECRET_KEY) ;
//		parameters.put("sign", sign);
//		String requestData = null;
//		try {
//			requestData = BaiduUtil.buildRequestData(parameters);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		//获取到jsonData后，进行相关的业务处理
//		String result = BaiduUtil.httpRequest(BaiduUtil.ORDER_QUERY_URL, "POST", requestData);
//		return result;
//	}
//
////	退款请求示例
////	sp_no= 1213&order_no=12345&reason=不想要了&sign=ewfweftwef
////	返回（正常）：{"result":1}
////	返回（异常）：{"error_code": 123, "error_msg": "xxxx"}
//	@Override
//	public String orderRefund(BjdjRefund bjdjRefund) throws ServiceException {
//		
//		BjdjOrder order = bjdjRefund.getOrder();
//		
//		Map<String,String> parameters = new TreeMap<String,String>();
//		parameters.put("order_id", order.getNumberOut());
//		parameters.put("order_no", order.getNumber());
//		parameters.put("sp_no", BaiduUtil.SP_NO+"");
//		parameters.put("refund_url", UrlUtil.getDomainName() + "pay_baiduRefundHandler.action");
//		parameters.put("reason", bjdjRefund.getReason()); //退款原因
//		String sign = BaiduUtil.getSignature(parameters, BaiduUtil.SECRET_KEY) ;
//		parameters.put("sign", sign);
//		String requestData = null;
//		try {
//			requestData = BaiduUtil.buildRequestData(parameters);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		//获取到jsonData后，进行相关的业务处理
//		String result = BaiduUtil.httpRequest(BaiduUtil.ORDER_REFUND_URL, "POST", requestData);
//		return result;
//	}
//}