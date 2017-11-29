package com.ticket.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.enumer.BjdjOrderType;
import com.ticket.enumer.PayMethod;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjOrder;
import com.ticket.pojo.BjdjPage;
import com.ticket.pojo.BjdjPageTemplate;
import com.ticket.pojo.BjdjServiceCode;
import com.ticket.pojo.BjdjServiceCodeDelayRule;
import com.ticket.pojo.BjdjServiceCodeOperation;
import com.ticket.pojo.BjdjServicePackage;
import com.ticket.pojo.ButtonHelp;
import com.ticket.pojo.EmployeeInfoZengLogDetail;
import com.ticket.pojo.Member;
import com.ticket.pojo.News;
import com.ticket.pojo.SystemDictionary;
import com.ticket.service.IBjdjOrderService;
import com.ticket.service.IBjdjPageService;
import com.ticket.service.IBjdjRefundService;
import com.ticket.service.IBjdjServiceCodeDelayRuleService;
import com.ticket.service.IBjdjServiceCodeOperationService;
import com.ticket.service.IBjdjServiceCodeService;
import com.ticket.service.IBjdjServiceItemService;
import com.ticket.service.IBjdjServicePackageItemService;
import com.ticket.service.IBjdjServicePackageService;
import com.ticket.service.IEmployeeInfoZengLogDetailService;
import com.ticket.service.IInfoPositionService;
import com.ticket.service.IMemberService;
import com.ticket.service.IPayService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.util.AuthCodeUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SmsUtil;
import com.ticket.util.ValidateUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @Description：订单action
 * @author：涂有
 * @date 2015年10月29日 下午4:25:12
 */
public class BjdjOrderTemplateAction extends BaseAction{

	protected static final long serialVersionUID = 1L;
	
	@Resource
	protected ISystemDictionaryService dictionaryService;
	@Resource
	protected IBjdjOrderService orderService;
	@Resource
	protected IMemberService memberService;
	@Resource
	protected IBjdjServiceCodeService serviceCodeService;
	@Resource
	protected IBjdjServiceCodeOperationService serviceCodeLogService;
//	@Resource(name="baiduService")
//	protected IPayService baiduService;  //百度支付业务层
	@Resource(name="alipayService")
	protected IPayService alipayService; //支付宝支付
	@Resource(name="wxOpenService")
	protected IPayService wxOpenService; //微信支付
	@Resource(name="swiftService")
	protected IPayService swiftService; //威富通支付
	@Resource
	protected IBjdjServiceCodeDelayRuleService delayRuleService;
	@Resource
	protected IBjdjServiceCodeOperationService operationService;
	@Resource
	protected IInfoPositionService infoPositionService;
	@Resource
	protected IEmployeeInfoZengLogDetailService detailService;
	@Resource
	protected IBjdjServiceItemService serviceItemService;
	@Resource
	protected IBjdjServicePackageService servicePackageService;
	@Resource
	protected IBjdjServicePackageItemService packageItemService;
	@Resource
	protected IBjdjPageService bjdjPageService;
	@Resource
	protected IBjdjRefundService bjdjRefundService;
	
	protected BjdjOrder order;
	protected Member member;
	protected BjdjServicePackage packageType;
	protected List<BjdjServiceCode> codes;
	protected List<SystemDictionary> dicts;
	protected List<BjdjServiceCodeDelayRule> delayRules;
	protected BjdjServiceCodeOperation serviceCodeLog;
	
	protected String id;
	protected String mid;
	protected String typeId;
	
	protected String number;
	protected String mobile;
	protected String authCode;
	protected String totals_price;
	protected Integer count;
	protected String orderId;
	protected PayMethod payMethod;
	protected String purchaseType;
	protected String type;
	protected String reason;
	protected String agree;
	protected boolean outer;
	
	/*********直达号回调参数*********/
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
	
	/*********延期参数********/
	protected String ruleId;
    
    protected String orderStatus = null;//订单状态
    protected String code; //服务码

	protected String codeId;
	//便捷登机前端页面id
	protected String bjdjPage_id;
	
	protected String fromApp;
	
	/**
	 * 进入订单确认页面
	 */
	public String bjdjPriceInterface() throws ServiceException{
		
		//获得便捷登机的价格
		List<BjdjServicePackage> packages = null;
		if(GeneralUtil.isNotNull(bjdjPage_id)){
			packages = servicePackageService.queryByBjdjPage(bjdjPage_id);
		}else{
			packages = servicePackageService.queryByBjdjUrl("#");
		}
		JSONArray jsonArray = new JSONArray();
		for(int i = 0; i < packages.size(); i++){
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", packages.get(i).getId());
			jsonObject.put("price", packages.get(i).getPrice());
			jsonObject.put("description", packages.get(i).getDescription());
			jsonObject.put("name", packages.get(i).getName());
			jsonObject.put("orderValue", packages.get(i).getStatus().getOrderValue());
			jsonArray.add(jsonObject);
		}
		data = AjaxData.responseSuccess(jsonArray);
		return JSON;
	}
	
	/**
	 * @Description：根据orderId得到订单号
	 * @return
	 */
	public String getOrderInterface(){
		
		if(GeneralUtil.isNull(orderId)){
			
			data = AjaxData.responseError(getText("order.id.required"));
			return JSON;
		}
		try {
			order = orderService.queryById(BjdjOrder.class.getName(), orderId);
		} catch (ServiceException e) {
			
			data = AjaxData.responseError(getText("order.id.error"));
			return JSON;
		} 
		
		data = AjaxData.responseSuccess(JSONObject.fromObject(order).toString());
		return JSON;
	}
	
	/**
     * @Description：默认的控制器
     * @return
     * @throws IOException 
     */
    public String execute() throws Exception{
    	
    	return "index";
    }
    
	/**
	 * 进入订单确认页面
	 */
	public String confirmPage() throws ServiceException{
		BjdjPage bjdjPage = bjdjPageService.queryByUrl("#");
		
		List<BjdjServicePackage> bjdjServicePackages = servicePackageService.queryByBjdjPage(bjdjPage.getId());
		ActionContext.getContext().put("bjdjServicePackages", bjdjServicePackages);
		return "confirmPage";
	}
	
	/**
	 * 搜索指定关键词出来的便捷登机，进入订单确认页面
	 * @return
	 */
	public String confirmPageAjax() throws ServiceException{
		List<BjdjServicePackage> bjdjServicePackages = servicePackageService.queryByBjdjPage(bjdjPage_id);
		ActionContext.getContext().put("bjdjServicePackages", bjdjServicePackages);
		
		BjdjServicePackage bjdjServicePackage = bjdjServicePackages.get(0);
		BjdjPage bjdjPage = bjdjServicePackage.getBjdjPage();
		
		News useSerms = bjdjPage.getUseSerms();
		Long visitUrl2 = useSerms.getStatus().getVisitUrl();
		ActionContext.getContext().put("useSerms", visitUrl2);
		return "confirmPageAjax";
	}
	
	/**
	 * @Description：订单支付页面
	 * @return
	 * @throws ServiceException 
	 */
	public String payPage() throws ServiceException{
		
		order = (BjdjOrder)getSession().get("order");
		if(GeneralUtil.isNotNull(orderId)){
			
			order = orderService.queryById(BjdjOrder.class.getName(), orderId); 
			ActionContext.getContext().put("order", order);
		}
		return "payPage";
	}
	
	/**
	 * @Description：支付失败页面
	 * @return
	 * @throws ServiceException 
	 */
	public String payFailPage(){
		
		return "payFailPage";
	}
	
	/**
	 * @Description：支付成功页面
	 * @return
	 * @throws ServiceException 
	 */
	public String paySuccessPage(){
		
		if(order == null){
			
			order = orderService.get(BjdjOrder.class, id);
		}
		//做一个延时操作,延时10秒
		if(order != null){
			
			for(int i = 0; i < 10; i++){
				
				if(!"paid".equals(order.getState().getName())){
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				order = orderService.get(BjdjOrder.class, order.getId());
			}
		}
		List<BjdjServiceCode> codes = serviceCodeService.queryByColumn("order", order);
		ActionContext.getContext().put("codes", codes);
		
		if(order != null){
			//修改
			BjdjServicePackage bjdjServicePackage = order.getPackageType();
			BjdjPage bjdjPage = bjdjServicePackage.getBjdjPage();
			BjdjPageTemplate news = bjdjPage.getPaySuccess();
			//设置支付成功提示
			ActionContext.getContext().put("pay_success_tip", news.getContent());
			//按钮
			String buttonName = news.getButtonName();
			String buttonUrl = news.getButtonUrl();
			String buttonType = news.getButtonType();
			String buttonClass = news.getButtonClass();
			List<ButtonHelp> buttonHelps = new ArrayList<ButtonHelp>();
			ButtonHelp buttonHelp = null;
			if(GeneralUtil.isNotNull(buttonName)){
				String[] buttonNameArr = buttonName.split(",");
				String[] buttonUrlArr = buttonUrl.split(",");
				String[] buttonTypeArr = buttonType.split(",");
				String[] buttonClassArr = buttonClass.split(",");
				for(int i=0;i<buttonNameArr.length;i++){
					buttonHelp = new ButtonHelp();
					buttonHelp.setButtonName(buttonNameArr[i]);
					buttonHelp.setButtonUrl(buttonUrlArr[i]);
					buttonHelp.setButtonClass(buttonClassArr[i]);
					buttonHelp.setButtonType(buttonTypeArr[i]);
					buttonHelps.add(buttonHelp);
				}
			}
			ActionContext.getContext().put("buttonHelps", buttonHelps);
			//修改
		}else{
			
			SystemDictionary dict = dictionaryService.getByName("pay_success_tip");
			ActionContext.getContext().put("pay_success_tip", dict.getDescription());
		}
		return "paySuccessPage";
	}
	
	/**
	 * @Description：生成验证码，并发送到手机
	 * @throws ServiceException 
	 */
	public String generateAuthCode() throws ServiceException{
		
		if(!GeneralUtil.isNotNull(mobile)){
			
			data = AjaxData.responseError(getText("mobile.null"));
			return JSON;
		}
		
		String authCode = AuthCodeUtil.generateAndSave(mobile);
		SmsUtil.sendSms(mobile, getText("authCode.sms", new String[]{authCode}));
		data = AjaxData.responseSuccess(getText("authCode.sended"));
		return JSON;
	}
	
	/**
	 * @Description：生成验证码，并发送到手机
	 * @throws ServiceException 
	 */
	public String generateAuthCodeInterface() throws ServiceException{
		
		if(!GeneralUtil.isNotNull(mobile)){
			
			data = AjaxData.responseError(getText("mobile.null"));
			return JSON;
		}
		
		String authCode = AuthCodeUtil.generateAndSave(mobile);
		SmsUtil.sendSms(mobile, getText("authCode.sms", new String[]{authCode}));
		data = AjaxData.responseSuccess(authCode);
		return JSON;
	}
	
	/**
	 * @Description：构造订单
	 * @throws ServiceException 
	 */
	public String generateOrGetUserInterface() throws ServiceException{
		
		//验证电话号码
		if(GeneralUtil.isNull(mobile)){
			
			data = AjaxData.responseError(getText("mobile.required"));
			return JSON;
		}
		//验证验证码
		if(GeneralUtil.isNull(authCode)){
			
			data = AjaxData.responseError(getText("authCode.required"));
			return JSON;
		}
		//检查验证码
		if(!AuthCodeUtil.check(mobile, authCode)){
			
			data = AjaxData.responseError(getText("authCode.error"));
			return JSON;
		}
		
		member = memberService.generateByMobileAndSendSms(mobile);
		data = AjaxData.responseSuccess(JSONObject.fromObject(member).toString());
		return JSON;
	}
	
	/**
	 * @Description：正式生成订单
	 * @throws ServiceException 
	 */
	public String generateOrder() throws ServiceException{
		
		member = getSessionMember();
		if(member == null){ //未登录就要检查验证码
			
			//验证电话号码
			if(GeneralUtil.isNull(mobile)){
				
				data = AjaxData.responseError(getText("mobile.required"));
				return JSON;
			}
			
			//验证验证码
			if(GeneralUtil.isNull(authCode)){
				
				data = AjaxData.responseError(getText("authCode.required"));
				return JSON;
			}
			//检查验证码
			if(!AuthCodeUtil.check(mobile, authCode)){
				
				data = AjaxData.responseError(getText("authCode.error"));
				return JSON;
			}
			member = memberService.generateByMobileAndSendSms(mobile);
			//自动登录
			getSession().put(ContextConstants.SCOPE_MEMBER, member);
		}
		
		packageType = packageItemService.get(BjdjServicePackage.class, id);
		order = orderService.generateOrder(member, packageType, count == 0 ? 1 : count, BjdjOrderType.bjdj);
		getSession().put("order", order);
		data = AjaxData.responseSuccess(getText("order.generate.success"));
		return JSON;
	}
	
	/**
	 * @Description：正式生成订单
	 * @throws ServiceException 
	 */
	public String generateOrderInterface() throws ServiceException{
		
		//检验是否登录
		if(GeneralUtil.isNull(id)){
			
			data = AjaxData.responseError(getText("member.id.required"));
			return JSON;
		}
		member = memberService.queryById(Member.class.getName(), id);
		if(member == null){
			
			data = AjaxData.responseError(getText("member.id.error"));
			return JSON;
		}
		if(GeneralUtil.isNull(count)){
			
			data = AjaxData.responseError(getText("order.count.required"));
			return JSON;
		}
		if(GeneralUtil.isNull(typeId)){
			
			data = AjaxData.responseError(getText("typeId.required"));
			return JSON;
		}
		
		try {
			packageType = packageItemService.get(BjdjServicePackage.class, typeId);
			order = orderService.generateOrder(member, packageType, count, BjdjOrderType.bjdj);
		} catch (Exception e) {
			data = AjaxData.responseError(e.getMessage());
			return JSON;
		}
		
		data = AjaxData.responseSuccess(JSONObject.fromObject(order).toString());
		return JSON;
	}
	
	/**
	 * @throws ServiceException 
	 * @Description：订单支付
	 * @return
	 */
	public String toPay(){
		
		order = orderService.get(BjdjOrder.class, id);
		getSession().put("payMethod", payMethod.getValue());
		
		String orderSourceUrl = "bjdj.action";
		
		try {
			if(payMethod == PayMethod.alipay){
				
				String pageUrl = "bjdjOrderTemplate_alipaySuccessUrl.action";
				alipayService.orderPay(order, pageUrl, orderSourceUrl);
				return "alipay";
			}else if(payMethod == PayMethod.swift){
				
				String pageUrl = "bjdjOrderTemplate_wxSuccessUrl.action";
				swiftService.orderPay(order, pageUrl, orderSourceUrl);
			}
		} catch (ServiceException e) {
			data = e.getMessage();
			return TEXT;
		}
		return JSON;
	}
	
	/**
	 * @throws ServiceException 
	 * @Description：订单支付
	 * @return
	 */
//	public String baiduSuccessPage(){
//		
//		//先检查签名是否正确
//		Map<String, String> map = new TreeMap<String, String>();
//		map.put("order_no", order_no);
//		map.put("order_id", order_id);
//		map.put("sp_no", sp_no);
//		map.put("pay_time", pay_time);
//		map.put("pay_result", pay_result);
//		map.put("paid_amount", paid_amount);
//		map.put("coupons", coupons);
//		map.put("promotion", promotion);
//		//检查签名是否正确
//        if(!BaiduUtil.checkSignature(map, sign)){
//        	
//        	return payFailPage();
//        }
//		
//		try {
//			
//			order = orderService.setPaid(order_no, map);
//		} catch (ServiceException e) {
//			
//			addFieldError("service", e.getMessage());
//			return payFailPage();
//		}
//		
//		if(getSession().get(ContextConstants.ACTIVATE_ITEM_HTML) != null){
//			
//			return "activatePage";
//		}
//		
//		return paySuccessPage();
//	}
	
	/**
	 * 支付宝支付成功的跳回页面
	 * @return
	 */
	public String alipaySuccessUrl(){
		
//		trade_no：2016052321001004300290041221 
//		body：测试勿拍 
//		notify_time：2016-05-23 01:01:03 
//		subject：测试勿拍 
//		sign_type：MD5 
//		notify_type：trade_status_sync 
//		out_trade_no：1463936447916847548 
//		trade_status：TRADE_SUCCESS 
//		sign：4c48eaebda68095d9da5d0560752e297 
//		is_success：T 
//		total_fee：0.01 
//		service：alipay.wap.create.direct.pay.by.user 
//		seller_id：2088221650950002 
//		notify_id：RqPnCoPT3K9%2Fvwbh3InXShfEddyndAfYOjezYo1UktZgKVHh%2FxqbIQmqAryYkDgF6ra4 
//		payment_type：1 
		
//		if(!AlipayNotify.verify(UrlUtil.getMap(request))){
//			
//			return payFailPage();
//		}
		
		order_no = request.getParameter("out_trade_no");
		String order_no_out = request.getParameter("trade_no");
		
		try {
			order = orderService.setPaid(order_no, order_no_out);
		} catch (ServiceException e) {
			
			addFieldError("service", e.getMessage());
			return payFailPage();
		}
		
		if(getSession().get(ContextConstants.ACTIVATE_ITEM_HTML) != null){
			
			return "activatePage";
		}
		
		return paySuccessPage();
	}
	
	/**
	 * 微信支付成功的跳回页面
	 * @return
	 */
	public String wxSuccessUrl(){
		
		return paySuccessPage();
	}
	
	/**
	 * @Description：订单退款页面
	 * @return
	 * @throws ServiceException 
	 */
	public String orderRefundPage(){
		
		try {
			order = orderService.queryById(BjdjOrder.class.getName(), orderId);
			dicts = dictionaryService.querySubByParentName("refund_reason");
		} catch (ServiceException e) {
			
			e.printStackTrace();
		}
		
		return "orderRefundPage";
	}
	
	/**
	 * @Description：订单申请退款
	 * @return
	 * @throws ServiceException 
	 */
	public String orderRefund(){
		
		try {
			bjdjRefundService.apply(orderId, reason);
		} catch (Exception e) {
			
			data = AjaxData.responseError("申请失败：" + e.getMessage());
			return JSON;
		}
		data = AjaxData.responseSuccess("申请成功，退款预计3到5个工作日原渠道退回！");
		return JSON;
	}
	
	/**
	 * @Description：订单转赠页面
	 * @return
	 * @throws ServiceException 
	 */
	public String orderDonationPage() throws ServiceException{
		
		codes = orderService.queryCanDonationServiceCode(orderId);
		return "orderDonationPage";
	}

	/**
	 * @Description：订单转赠
	 * @return
	 * @throws ServiceException 
	 */
	public String orderDonation() throws ServiceException{
		
		//手机号不能为空
		if(GeneralUtil.isNull(mobile)){
			
			data = AjaxData.responseError(getText("mobile.required"));
			return JSON;
		}
		
		//检验转赠的服务码是否为空
		if(GeneralUtil.isNull(code)){
			
			data = AjaxData.responseError(getText("serviceCode.required"));
			return JSON;
		}
		
		String[] codeArr = code.split(",");
		if(codeArr != null){
			
			Member fromMember = getSessionMember();
			Member toMember = memberService.generateByMobileAndSendSms(mobile, false);
			StringBuffer codes = new StringBuffer();
			
			SystemDictionary donation = dictionaryService.getByName("donation");
			for(int i = 0; i < codeArr.length; i++){
				
				String codeStr = codeArr[i].trim();
				
				BjdjServiceCode serviceCode = serviceCodeService.queryById(BjdjServiceCode.class.getName(), codeStr);
				
				//检测是否可以赠送
				if(!serviceCodeService.canDonate(serviceCode)){
					
					data = AjaxData.responseError(getText("serviceCode.cantDonate"));
					return JSON;
				}
				
				//不能赠送给自己
				if(serviceCode.getMember() == null && mobile.equals(serviceCode.getOrder().getMobile())){
					
					data = AjaxData.responseError(getText("order.noDonationMe"));
					return JSON;
				}else if(serviceCode.getMember() != null && mobile.equals(serviceCode.getMember().getPhone())){
					
					data = AjaxData.responseError(getText("order.noDonationMe"));
					return JSON;
				}
				
				//设置服务码拥护者
				serviceCode.setMember(toMember);
				serviceCodeService.merge(serviceCode);
				
				//设置转赠记录
				serviceCodeLogService.persist(serviceCode, donation, fromMember, toMember);
				
				codes.append(serviceCode.getCode());
				codes.append(",");
			}
			SmsUtil.sendSms(toMember.getPhone(), getText("sms.serviceCode.donation", new String[]{fromMember.getPhone(), codes.substring(0, codes.length()-1)}));
		}
		
		data = AjaxData.responseSuccess(getText("donation.success"));
		return JSON;
	}
	
	/**
	 * @Description：服务码延期页面
	 * @return
	 * @throws ServiceException 
	 */
	public String serviceCodeDelayPage() throws ServiceException{
		
		delayRules = delayRuleService.queryAll();
		return "serviceCodeDelayPage";
	}
	
	/**
	 * @Description：服务码延期
	 * @return
	 * @throws ServiceException 
	 */
	public String serviceCodeDelay() throws ServiceException{
		
		//服务码
		if(GeneralUtil.isNull(codeId)){
			
			data = AjaxData.responseError(getText("serviceCode.id.required"));
			return JSON;
		}
		BjdjServiceCode serviceCode = serviceCodeService.queryById(BjdjServiceCode.class.getName(), codeId);
		if(serviceCode == null){
			
			data = AjaxData.responseError(getText("serviceCode.id.error"));
			return JSON;
		}
		
		//规则验证
		if(GeneralUtil.isNull(ruleId)){
			
			data = AjaxData.responseError(getText("delayRule.required"));
			return JSON;
		}
		BjdjServiceCodeDelayRule delayRule = delayRuleService.queryById(BjdjServiceCodeDelayRule.class.getName(), ruleId);
		if(delayRule == null){
			
			data = AjaxData.responseError(getText("delayRule.error"));
			return JSON;
		}
		
		//积分是否够
		member = getSessionMember();
		if(member.getRecord() == null || member.getRecord() < delayRule.getRecord()){
			
			data = AjaxData.responseError(getText("delay.record.noEnough"));
			return JSON;
		}
		
		//扣除积分
		member.setRecord(member.getRecord() - delayRule.getRecord());
		memberService.merge(member);
		//设置过期时间
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(serviceCode.getExpireTime());
		calendar.add(Calendar.DAY_OF_MONTH, delayRule.getDelayTime());
		serviceCode.setExpireTime(calendar.getTime());
		if(calendar.getTime().getTime() > System.currentTimeMillis()){
			
			SystemDictionary noUse = dictionaryService.getByName("unused");
			serviceCode.setState(noUse);
		}
		serviceCodeService.merge(serviceCode);
		
		data = AjaxData.responseSuccess(getText("delay.success"));
		return JSON;
	}
	
	/**
	 * @Description：订单详情页面
	 * @return
	 * @throws ServiceException
	 */
	public String orderDetailsPage() throws ServiceException{
		
		order = orderService.queryById(BjdjOrder.class.getName(), orderId);
		codes = orderService.getByOrderId(order);
		return "orderDetailsPage";
	}
	
	/**
	 * 我的订单
	 * @return
	 * @throws ServiceException
	 */
	public String allOrder() throws ServiceException{
		
		Member member = getSessionMember();
		if(ValidateUtil.isNull(orderStatus) || "all".equals(orderStatus)){
			
			List<BjdjOrder> allOrder = orderService.queryByMemberId(member.getId());
			ActionContext.getContext().put("allOrder", allOrder);
		}else if("needPay".equals(orderStatus)){
			
			List<BjdjOrder> needPay = orderService.queryByMemberIdAndStatus(member.getId(), dictionaryService.getByName("noPay"));
			ActionContext.getContext().put("needPay", needPay);
		}else if("needComment".equals(orderStatus)){
			
			List<BjdjOrder> needComment = orderService.queryByMemberIdAndDontComment(member.getId());
			ActionContext.getContext().put("needComment", needComment);
		}else if("donation".equals(orderStatus)){
			
			//普通会员赠送的
			List<BjdjServiceCode> serviceCodes = serviceCodeService.receivedDonation(member);
			ActionContext.getContext().put("donation", serviceCodes);
			
			//渠道客户分销过来的
			List<EmployeeInfoZengLogDetail> dispatches = detailService.query(member.getPhone());
			ActionContext.getContext().put("dispatches", dispatches);
		}
		
		return "allOrder";
	}
	
	/**
	 * @Description：删除订单
	 * @return
	 * @throws ServiceException
	 */
	public String realDelete() throws ServiceException {

		if(GeneralUtil.isNull(orderId)){
			
			data = AjaxData.responseError(getText("order.id.required"));
			return JSON;
		}
		
		order = orderService.queryById(BjdjOrder.class.getName(), orderId);
		if(order == null){
			
			data = AjaxData.responseError(getText("order.notExist"));
			return JSON;
		}
		
		//检查订单是否可以删除
		boolean canDelete = orderService.canDelete(order);
		if(!canDelete){
			
			data = AjaxData.responseError(getText("order.delete.fail"));
			return JSON;
		}
		
		boolean isSuc = orderService.remove(orderId);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
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
	
	public PayMethod getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(PayMethod payMethod) {
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
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public BjdjOrder getOrder() {
		return order;
	}

	public void setOrder(BjdjOrder order) {
		this.order = order;
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

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public List<BjdjServiceCode> getCodes() {
		return codes;
	}

	public void setCodes(List<BjdjServiceCode> codes) {
		this.codes = codes;
	}
	
	public BjdjServiceCodeOperation getServiceCodeLog() {
		return serviceCodeLog;
	}

	public void setServiceCodeLog(BjdjServiceCodeOperation serviceCodeLog) {
		this.serviceCodeLog = serviceCodeLog;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public IBjdjServiceCodeOperationService getServiceCodeLogService() {
		return serviceCodeLogService;
	}

	public void setServiceCodeLogService(
			IBjdjServiceCodeOperationService serviceCodeLogService) {
		this.serviceCodeLogService = serviceCodeLogService;
	}

	public List<SystemDictionary> getDicts() {
		return dicts;
	}

	public void setDicts(List<SystemDictionary> dicts) {
		this.dicts = dicts;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public IBjdjServiceCodeDelayRuleService getDelayRuleService() {
		return delayRuleService;
	}

	public void setDelayRuleService(
			IBjdjServiceCodeDelayRuleService delayRuleService) {
		this.delayRuleService = delayRuleService;
	}

	public List<BjdjServiceCodeDelayRule> getDelayRules() {
		return delayRules;
	}

	public void setDelayRules(List<BjdjServiceCodeDelayRule> delayRules) {
		this.delayRules = delayRules;
	}

	public String getDelayRule() {
		return ruleId;
	}

	public void setDelayRule(String delayRule) {
		this.ruleId = delayRule;
	}

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public IBjdjServiceCodeOperationService getOperationService() {
		return operationService;
	}

	public void setOperationService(
			IBjdjServiceCodeOperationService operationService) {
		this.operationService = operationService;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public IInfoPositionService getInfoPositionService() {
		return infoPositionService;
	}

	public void setInfoPositionService(IInfoPositionService infoPositionService) {
		this.infoPositionService = infoPositionService;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public boolean isOuter() {
		return outer;
	}

	public void setOuter(boolean outer) {
		this.outer = outer;
	}

	public IPayService getAlipayService() {
		return alipayService;
	}

	public void setAlipayService(IPayService alipayService) {
		this.alipayService = alipayService;
	}

	public IEmployeeInfoZengLogDetailService getDetailService() {
		return detailService;
	}

	public void setDetailService(IEmployeeInfoZengLogDetailService detailService) {
		this.detailService = detailService;
	}

	public IBjdjServiceItemService getServiceItemService() {
		return serviceItemService;
	}

	public void setServiceItemService(IBjdjServiceItemService serviceItemService) {
		this.serviceItemService = serviceItemService;
	}

	public IBjdjServicePackageService getServicePackageService() {
		return servicePackageService;
	}

	public void setServicePackageService(
			IBjdjServicePackageService servicePackageService) {
		this.servicePackageService = servicePackageService;
	}

	public IBjdjServicePackageItemService getPackageItemService() {
		return packageItemService;
	}

	public void setPackageItemService(
			IBjdjServicePackageItemService packageItemService) {
		this.packageItemService = packageItemService;
	}

	public BjdjServicePackage getPackageType() {
		return packageType;
	}

	public void setPackageType(BjdjServicePackage packageType) {
		this.packageType = packageType;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getBjdjPage_id() {
		return bjdjPage_id;
	}

	public void setBjdjPage_id(String bjdjPage_id) {
		this.bjdjPage_id = bjdjPage_id;
	}

	public String getFromApp() {
		return fromApp;
	}

	public void setFromApp(String fromApp) {
		this.fromApp = fromApp;
	}
}
