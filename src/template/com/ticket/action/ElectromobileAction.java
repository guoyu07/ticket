package com.ticket.action;

import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjOrder;
import com.ticket.pojo.BjdjServiceCode;
import com.ticket.pojo.SystemDictionary;
import com.ticket.service.IBjdjOrderService;
import com.ticket.service.IBjdjServiceCodeService;
import com.ticket.service.IBjdjServicePackageService;
import com.ticket.service.IInfoPositionService;
import com.ticket.service.IMemberService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.util.GeneralUtil;

import net.sf.json.JSONObject;

/**
 * @Description：电瓶车请求处理类
 * @author：涂有
 * @date 2015年11月16日 下午3:10:54
 */
public class ElectromobileAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	@Resource
	protected ISystemDictionaryService dictionaryService;
	@Resource
	protected IBjdjOrderService orderService;
	@Resource
	protected IMemberService memberService;
	@Resource
	private IBjdjServicePackageService servicePackageService;
	@Resource
	protected IBjdjServiceCodeService serviceCodeService;
//	@Resource
//	protected IPayService baiduService;  //百度支付业务层
	@Resource
	protected IInfoPositionService infoPositionService;
	
	private String id;
	protected BjdjOrder order;
	
	protected String mobile;
	protected String authCode;
	protected String totals_price;
	protected Integer count;
	protected String orderId;
	protected String payMethod;
	protected String purchaseType;
	protected String type;
	protected String reason;
	protected String agree;
	
	/*********直达号参数*********/
	protected String order_no;  //第三方的订单号
	protected String order_id;  //直达号中心订单号
	protected String sp_no; //商户号
	protected String pay_time;  //支付时间unixtimestamp
	protected String pay_result; //1支付成功 2等待支付 3退款成功
	protected String paid_amount;  //成功支付现金金额（单位分）
	protected String coupons;//优惠券使用金额（单位分）
	protected String promotion; //立减金额（单位分）
	protected String sign; //签名值, 以app的sk做秘钥对参数做签名
    
    /*********订单退款参数********/
	protected String refund_code; //1 成功 2 失败
	protected String refunded_amount; //退款金额，单位分

	/**
	 * @Description：跳转到电瓶车首页
	 * @return
	 */
	public String execute(){
		
		//电瓶车价格
//		String electromobile_price = dictionaryService.getValueByName("electromobile_price");
//		ActionContext.getContext().put("electromobile_price", electromobile_price);
		
		//首页温馨提示
		SystemDictionary electromobile_index_tip = dictionaryService.getByName("electromobile_index_tip");
		ActionContext.getContext().put("electromobile_index_tip", electromobile_index_tip);
		
		//机场服务热线
//		String service_phone = dictionaryService.getValueByName("service_phone");
//		ActionContext.getContext().put("service_phone", service_phone);
		
		return "index";
	}
	
	/**
	 * @Description：电瓶车首页的接口
	 * @return
	 */
	public String indexInterface(){
		
		JSONObject json = new JSONObject();
		
		//电瓶车价格
		String electromobile_price = dictionaryService.getValueByName("electromobile_price");
		json.put("electromobile_price", electromobile_price);
		
		//首页温馨提示
		SystemDictionary electromobile_index_tip = dictionaryService.getByName("electromobile_index_tip");
		json.put("electromobile_index_tip", electromobile_index_tip);
		
		data = json.toString();
		return TEXT;
	}
	
	/**
	 * @Description：跳转到订单确认页
	 * @return
	 */
	public String confirmPage(){
		
		String price = dictionaryService.getValueByName("electromobile_price");
		ActionContext.getContext().put("electromobile_price", price);
		
		if(getSession().get(ContextConstants.SCOPE_MEMBER) != null){
			
			return "confirmPage2";
		}
		return "confirmPage";
	}
	
	/**
	 * @Description：跳转到订单确认页
	 * @return
	 */
	public String confirmPage2(){
		
		String price = dictionaryService.getValueByName("electromobile_price");
		ActionContext.getContext().put("electromobile_price", price);
		return "confirmPage2";
	}
	
	/**
	 * @Description：电瓶车价格接口
	 * @return
	 */
	public String priceInterface(){
		
		String price = dictionaryService.getValueByName("electromobile_price");
		data = AjaxData.responseSuccess(price);
		return JSON;
	}
	
	/**
	 * @Description：跳转到支付页
	 * @return
	 * @throws ServiceException 
	 */
	public String payPage() throws ServiceException{
		
		if(GeneralUtil.isNotNull(orderId)){
			
			order = orderService.queryById(BjdjOrder.class.getName(), orderId); 
			ActionContext.getContext().put("order", order);
		}
		return "payPage";
	}
	
	/**
	 * @Description：跳转到支付页
	 * @return
	 * @throws ServiceException 
	 */
	public String orderInterface() throws ServiceException{
		
		if(GeneralUtil.isNotNull(orderId)){
			
			order = orderService.queryById(BjdjOrder.class.getName(), orderId); 
			data = AjaxData.responseSuccess(JSONObject.fromObject(order).toString());
			return JSON;
		}else{
			
			data = AjaxData.responseError(getText("order.id.error"));
			return JSON;
		}
	}
	
	/**
	 * @Description：跳转到订单支付成功页
	 * @return
	 * @throws ServiceException 
	 */
	public String paySuccessPage() throws ServiceException{
		
		if(GeneralUtil.isNotNull(orderId)){
			
			order = orderService.queryById(BjdjOrder.class.getName(), orderId);
			List<BjdjServiceCode> codes = serviceCodeService.queryByColumn("order_id", order.getId());
			ActionContext.getContext().put("codes", codes);
			ActionContext.getContext().put("order", order);
		}
		//设置支付成功提示
		SystemDictionary dict = dictionaryService.getByName("pay_success_tip");
		ActionContext.getContext().put("pay_success_tip", dict.getDescription());
		
		//温馨提示
		SystemDictionary electromobile_order_tip = dictionaryService.getByName("electromobile_order_tip");
		ActionContext.getContext().put("electromobile_order_tip", electromobile_order_tip);
		
		return "paySuccessPage";
	}
	
	/**
	 * @Description：跳转到支付失败页
	 * @return
	 */
	public String payFailPage(){
		
		return "payFailPage";
	}

	/**
	 * @Description：跳转到服务介绍页
	 * @return
	 */
	public String serviceIntroducePage(){
		
		return "serviceIntroducePage";
	}
	
	/**
	 * @Description：跳转到位置导航页
	 * @return
	 */
	public String positionNavigationPage(){
		
		return "positionNavigationPage";
	}
	
	/**
	 * @Description：跳转到详情提示页
	 * @return
	 */
	public String detailPromptPage(){
		
		return "detailPromptPage";
	}
	
	/**
	 * @Description：跳转到详情提示页
	 * @return
	 */
	public String consumeTipPage(){
		
		return "consumeTipPage";
	}
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getTotals_price() {
		return totals_price;
	}

	public void setTotals_price(String totals_price) {
		this.totals_price = totals_price;
	}
	
	public Integer getCount(){
		
		return count;
	}
	
	public void setCount(Integer count){
		
		this.count = count;
	}
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	
	public String getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public BjdjOrder getOrder() {
		return order;
	}

	public void setOrder(BjdjOrder order) {
		this.order = order;
	}
	

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getSp_no() {
		return sp_no;
	}

	public void setSp_no(String sp_no) {
		this.sp_no = sp_no;
	}

	public String getPay_time() {
		return pay_time;
	}

	public void setPay_time(String pay_time) {
		this.pay_time = pay_time;
	}

	public String getPay_result() {
		return pay_result;
	}

	public void setPay_result(String pay_result) {
		this.pay_result = pay_result;
	}

	public String getPaid_amount() {
		return paid_amount;
	}

	public void setPaid_amount(String paid_amount) {
		this.paid_amount = paid_amount;
	}

	public String getCoupons() {
		return coupons;
	}

	public void setCoupons(String coupons) {
		this.coupons = coupons;
	}

	public String getPromotion() {
		return promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getRefund_code() {
		return refund_code;
	}

	public void setRefund_code(String refund_code) {
		this.refund_code = refund_code;
	}

	public String getRefunded_amount() {
		return refunded_amount;
	}

	public void setRefunded_amount(String refunded_amount) {
		this.refunded_amount = refunded_amount;
	}

	public String getAgree() {
		return agree;
	}

	public void setAgree(String agree) {
		this.agree = agree;
	}

	public ISystemDictionaryService getDictionaryService() {
		return dictionaryService;
	}

	public void setDictionaryService(ISystemDictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	public IBjdjOrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(IBjdjOrderService orderService) {
		this.orderService = orderService;
	}

	public IMemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(IMemberService memberService) {
		this.memberService = memberService;
	}

	public IBjdjServiceCodeService getServiceCodeService() {
		return serviceCodeService;
	}

	public void setServiceCodeService(IBjdjServiceCodeService serviceCodeService) {
		this.serviceCodeService = serviceCodeService;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public IInfoPositionService getInfoPositionService() {
		return infoPositionService;
	}

	public void setInfoPositionService(IInfoPositionService infoPositionService) {
		this.infoPositionService = infoPositionService;
	}
}
