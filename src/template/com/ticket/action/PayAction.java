package com.ticket.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.dom4j.DocumentException;

import com.ticket.bo.AjaxData;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjOrder;
import com.ticket.pojo.BjdjRefund;
import com.ticket.service.IBjdjOrderService;
import com.ticket.service.IBjdjRefundService;
import com.ticket.service.IBjdjServiceCodeService;
import com.ticket.service.IPayService;
import com.ticket.util.UrlUtil;
import com.ticket.util.XMLUtil;

/**
 * @Description：支付的前台控制器
 * @author：涂有
 * @date 2016年5月25日 下午2:41:08
 */
public class PayAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	@Resource
	protected IBjdjOrderService orderService;
	@Resource
	protected IBjdjServiceCodeService serviceCodeService;
	@Resource
	protected IBjdjRefundService refundService;
//	@Resource(name="baiduService")
//	protected IPayService baiduService;  //百度支付业务层
	@Resource(name="alipayService")
	protected IPayService alipayService; //支付宝支付
	@Resource(name="wxOpenService")
	protected IPayService wxOpenService; //微信支付
	
	protected BjdjOrder order;
	
	/**
	 * 获取百度支付的支付地址
	 * @return
	 * @throws IOException 
	 */
//	public String baiduPayUrl() throws IOException{
//		
//		String actionName = "bjdjOrderTemplate_";
//		String orderSourceUrl = actionName + ".action";
//		try {
//			String returnUrl = actionName + "baiduReturnUrl.action";  //百度服务器调我方服务器
//			String pageUrl = actionName + "baiduSuccessPage.action";  //支付重定向页面
//			
//			order = orderService.get(BjdjOrder.class, id);
//			String url = baiduService.orderPay(order, pageUrl, orderSourceUrl);
//			data = AjaxData.responseSuccess(url);
//		} catch (ServiceException e) {
//			data = AjaxData.responseError(e.getMessage());
//		}
//		return JSON;
//	}
	
//	/**
//	 * @Description：服务器间调用，防止用户掉网和中间手动关闭网页导致的问题
//	 * @return
//	 */
//	public String baiduPayHandler(){
//		
////		通知示例
////		order_no=234342&order_id=35574&sp_no=1022&pay_time=1409714803&pay_result=1&
////		paid_amount=1000&coupons=0&promotion=0&sign=DINUYSjlQy7kUHccrDDskzS4zswnvtrr
////		第三方返回示例
////		正常
////		{"result":0} 
//		
//		//先检查签名是否正确
//		Map<String, String> map = new TreeMap<String, String>();
//		map.put("order_no", request.getParameter("order_no"));
//		map.put("order_id", request.getParameter("order_id"));
//		map.put("sp_no", request.getParameter("sp_no"));
//		map.put("pay_time", request.getParameter("pay_time"));
//		map.put("pay_result", request.getParameter("pay_result"));
//		map.put("paid_amount", request.getParameter("paid_amount"));
//		map.put("coupons", request.getParameter("coupons"));
//		map.put("promotion", request.getParameter("promotion"));
//		//检查签名是否正确
//       if(!BaiduUtil.checkSignature(map, request.getParameter("sign"))){
//       	
//       	return JSON;
//       }
//		
//		try {
//			
//			orderService.setPaid(request.getParameter("order_no"), map);
//		} catch (ServiceException e) {
//			e.printStackTrace();
//		}
//		
//   	AjaxUtil.writeString(response, request, "{\"result\":0}") ; 
//       return JSON;
//	}
	
	/**
	 * @Description：订单退回通知
	 * @return
	 * @throws ServiceException 
	 */
//	public String baiduRefundHandler() throws ServiceException{
//		
////		通知示例
////		order_no=234342&refund_code=1&refunded_amount=3200&sign=DINUYSjlQy7kUHccrDDskzS4z
////		正常
////		{"result":0}
//		
//		Map<String, String> map = new TreeMap<String, String>();
//		map.put("order_no", request.getParameter("order_no"));
//		map.put("refund_code", request.getParameter("refund_code"));
//		map.put("refunded_amount", request.getParameter("refunded_amount"));
//		
//		//签名不对，通知失败
//		if(!BaiduUtil.checkSignature(map, request.getParameter("sign"))){
//			
//			AjaxUtil.writeString(response, request, "{\"result\":1}");
//			return JSON;
//		}
//		
//		BjdjOrder order = orderService.getByNumberOut(request.getParameter("order_no"));
//		BjdjRefund bjdjRefund = refundService.queryWaitDealWith(order);
//		refundService.response(bjdjRefund, true, UrlUtil.mapToString(UrlUtil.getMap(request)));
//		AjaxUtil.writeString(response, request, "{\"result\":0}");
//		return JSON;
//	}

	/**
	 * 获取支付宝的参数字符串
	 * @return
	 * @throws IOException 
	 */
	public String alipayParams() throws IOException{
		
		String orderSourceUrl = "bjdj.action";
		String pageUrl = "pay_wxSuccessUrl.action";
		try {
			order = orderService.get(BjdjOrder.class, id);
			String params = alipayService.orderPay(order, pageUrl, orderSourceUrl);
			data = AjaxData.responseSuccess(params);
		} catch (ServiceException e) {
			data = AjaxData.responseError(e.getMessage());
		}
		return JSON;
	}
	
	/**
	 * 支付退款回调异步通知接口
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws ServiceException 
	 */
	public String alipayRefundHandler() throws UnsupportedEncodingException{
		
		request.setCharacterEncoding("ISO-8859-1");
		//批次号
		String batch_no = new String(request.getParameter("batch_no").getBytes("ISO-8859-1"),"UTF-8");
		//批量退款数据中的详细信息
		String result_details = new String(request.getParameter("result_details").getBytes("ISO-8859-1"),"UTF-8");
		String numberOut = result_details.split("\\^")[0];
		
		if(alipayService.verifySign(UrlUtil.getMap(request))){//验证成功

			BjdjOrder order = orderService.getByNumberOut(numberOut);
			BjdjRefund bjdjRefund = refundService.query(order, batch_no);
			try {
				refundService.response(bjdjRefund, true, UrlUtil.mapToString(UrlUtil.getMap(request)));
				data = "success";
			} catch (ServiceException e) {
				e.printStackTrace();
				data = "fail";
			}
		}else{//验证失败
			
			data = "fail";
		}
		return TEXT;
	}

	/**
	 * 支付宝支付成功的回调通知接口
	 * @return
	 */
	public String alipayPayHandler(){
		
		try {
			request.setCharacterEncoding("ISO-8859-1");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		if(!alipayService.verifySign(UrlUtil.getMap(request))){
			
			data = "fail";
			return TEXT;
		}
		String order_no = request.getParameter("out_trade_no");
		String order_no_out = request.getParameter("trade_no");
		
		try {
			orderService.setPaid(order_no, order_no_out);
			data = "success";
		} catch (ServiceException e) {
			data = "fail";
		}
		return TEXT;
	}

	/**
	 * 微信支付的参数字符串
	 * @return
	 */
	public String wxParams(){
		
		try {
			order = orderService.get(BjdjOrder.class, id);
			String params = wxOpenService.orderPay(order, null, null);
			data = AjaxData.responseSuccess(params);
		} catch (ServiceException e) {
			data = AjaxData.responseError(e.getMessage());
		}
		return JSON;
	}
	
	/**
	 * app微信支付回调接口
	 * @return
	 * @throws Exception
	 */
	public String wxPayHandler() throws Exception{
		
		TreeMap<String, String> map_data = XMLUtil.parseToTreemap(UrlUtil.getRequestContent(request));
		if(!wxOpenService.verifySign(map_data)){
			
			String result = "<xml>" +
			  "<return_code><![CDATA[FAIL]]></return_code>" +
			  "<return_msg><![CDATA[签名失败]]></return_msg>" +
			"</xml>";
			data = result;
			return TEXT;
		}
		
		if("SUCCESS".equals(map_data.get("return_code")) && "SUCCESS".equals(map_data.get("result_code"))){
			
			orderService.setPaid(map_data.get("out_trade_no"), map_data.get("transaction_id"));
			String result = "<xml>" +
					"<return_code><![CDATA[SUCCESS]]></return_code>" +
					"<return_msg><![CDATA[OK]]></return_msg>" +
					"</xml>";
			data = result;
		}
		return TEXT;
	}
	
	/**
	 * 微信支付成功的回调通知接口
	 * @return
	 * @throws DocumentException 
	 * @throws ServiceException 
	 */
	public String swiftPayHandler() throws DocumentException, ServiceException{
		
		String result = UrlUtil.getRequestContent(request);
		Map<String,String> map = XMLUtil.parseTomap(result);
		
		if("0".equals(map.get("status")) && "0".equals(map.get("result_code"))){//成功
			
			orderService.setPaid(map.get("out_trade_no"), map.get("out_transaction_id"));
        	data = "success";
        }else{
        	data = "fail";
        }
		return TEXT;
	}
}
