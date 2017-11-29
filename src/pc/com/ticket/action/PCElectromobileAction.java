package com.ticket.action;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.News;
import com.ticket.service.INewsService;

public class PCElectromobileAction extends ElectromobileAction{
	
	private static final long serialVersionUID = 1L;
	@Resource
	protected INewsService newsService;
	
	//消费提示,服务介绍
	protected News consumerTIPS,serviceIntroduce;
	
	private String num;
	/**
	 * 电瓶车首页
	 */
	@Override
	public String execute(){
		try {
			consumerTIPS = newsService.queryByUrl("1447834954394", true);
			serviceIntroduce = newsService.queryByUrl("1447834645050", true);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		ActionContext.getContext().put("serviceIntroduce", serviceIntroduce);
		ActionContext.getContext().put("consumerTIPS", consumerTIPS);
		return super.execute();
	}

//	/**
//	 * 进入支付页面
//	 */
//	public String confirmPage(){
//		super.confirmPage();
//		return interceptCount();
//	}
//	/**
//	 * 拦截用户在地址栏输入不规范的数据
//	 * @return
//	 */
//	public String interceptCount(){
//		String reg = "^\\+?[1-9][0-9]*$";
//		Pattern p = Pattern.compile(reg);
//		Matcher m = p.matcher(num);
//		if(!m.matches()){
//			return execute();
//		}
//		super.count = Integer.parseInt(num);
//		return "toPay";
//	}
//	/**
//	 * 生成验证码，并发送到手机
//	 */
//	public String generateAuthCode() throws ServiceException{
//		return super.generateAuthCode();
//	}
//	/**
//	 * 构造订单
//	 */
//	@SuppressWarnings("unchecked")
//	public String constructOrder() throws ServiceException{
//		// 验证电话号码
//		if (GeneralUtil.isNull(mobile)) {
//
//			data = AjaxData.responseError(getText("mobile.required"));
//			return JSON;
//		}
//		// 验证验证码
//		if (GeneralUtil.isNull(authCode)) {
//
//			data = AjaxData.responseError(getText("authCode.required"));
//			return JSON;
//		}
//		// 检查验证码
//		if (!orderService.validateSmsCodeIsOK(mobile, authCode)) {
//
//			data = AjaxData.responseError(getText("authCode.error"));
//			return JSON;
//		}
//
//		Member member = (Member) ActionContext.getContext().getSession()
//				.get(ContextConstants.SCOPE_MEMBER);
//		List<Member> list = memberService.getByColumn("phone", mobile);
//		if (list.size() == 0) { // 没有此账号就自动生成账号
//
//			String loginPwd = AuthCodeUtil.generate();
//			member = new Member();
//			member.setPhone(mobile);
//			member.setLoginName(mobile);
//			member.setRealName(mobile);
//			member.setNickName(mobile);
//			member.setLoginPwd(loginPwd);
//			memberService.persist(member);
//			SmsUtil.sendSms(
//					mobile,
//					getText("sms.regist.success", new String[] { mobile,
//							loginPwd }));
//
//		} else if (member == null
//				|| (member != null && !mobile.equals(member.getLoginName()))) { // 自动登录
//
//			List<Member> members = memberService.getByColumn("phone", mobile);
//			if (members.size() > 0) {
//				member = members.get(0);
//			}
//		}
//
//		// 单价
//		Double price = Double.parseDouble(dictionaryService.getValueByName("bjdj_price"));
//		getSession().put(ContextConstants.SCOPE_MEMBER, member);
//		ActionContext.getContext().put("message", getText("user.generated"));
//		ActionContext.getContext().put("total_price", price * count);
//		String isOk = generateOrder();
//		String url = order.getPayUrl();
//		if("ok".equals(isOk)){
//			data = AjaxData.responseSuccess(url);
//			return JSON;
//		}
//		data = AjaxData.responseError(getText("order.generate.fail"));
//		return JSON;
//	}
//	/**
//	 * 构造订单
//	 * @return
//	 * @throws ServiceException
//	 */
//	public String constructOrder2() throws ServiceException{
//		Member member = (Member) ActionContext.getContext().getSession()
//				.get(ContextConstants.SCOPE_MEMBER);
//		if(GeneralUtil.isNull(member)){
//			data = AjaxData.responseError(getText("order.generate.fail"));
//			return JSON;
//		}
//		String isOk = generateOrder();
//		String url = order.getPayUrl();
//		if("ok".equals(isOk)){
//			data = AjaxData.responseSuccess(url);
//			return JSON;
//		}
//		data = AjaxData.responseError(getText("order.generate.fail"));
//		return JSON;
//	}
//	/**
//	 * 生成订单,跳到百度支付页面
//	 */
//	public String generateOrder() throws ServiceException{
//		Member member = (Member)getSession().get(ContextConstants.SCOPE_MEMBER);
//		//检验是否登录
//		if(member == null){
//
//			addFieldError("notLogin", getText("notLogin"));
//			return confirmPage();
//		}
//		//检验服务码是否有库存
//		List<BjdjServiceCode> codes = serviceCodeService.getNotUsedByType("散客", count);
//		if(codes.size() != count){
//			
//			addFieldError("serviceCode", getText("serviceCode.notEnough"));
//			return confirmPage();
//		}
//		
//		//生成我方订单
//		order = orderService.persist(member.getId(), count, BjdjOrderType.electromobile, member.getPhone());
//		order.setName(dictionaryService.getValueByName("electromobile"));
//		//对接百度方支付接口
//		String actionName = "pcElectromobile_";
//		String orderActionName = "bjdjOrderTemplate_";
//		String returnUrl = UrlUtil.getDomainName() + orderActionName + "returnUrl.action";  //百度服务器调我方服务器
//		String pageUrl = UrlUtil.getDomainName() + actionName + "pay.action";  //支付重定向页面
//		String orderSourceUrl = UrlUtil.getDomainName() + actionName + ".action"; //订单主页面
//		
//		order.setReturnUrl(returnUrl);
//		order.setPageUrl(pageUrl);
//		order.setOrderSourceUrl(orderSourceUrl);
//		String resultStr = baiduService.orderPay(order);
//		JSONObject json = JSONObject.fromObject(resultStr);
//		String orderIdB = json.getString("order_id");  //直达号订单id
//		String url = json.getString("url"); //支付页面
//		
//		//支付失败的情况
//		//失败result示例：{"error_code":1008748,"error_msg":"invalid sign"}
//		if(GeneralUtil.isNull(orderIdB) || GeneralUtil.isNull(url)){
//			
////			String error_code = json.getString("error_code");
////			String msg = json.getString("error_code");
//			addFieldError("serviceCode", getText("order.generate.fail"));
//			return confirmPage();
//		}
//		
//		//支付成功的情况
//		//成功result示例：{"order_id":20668017,"url":"http://m.baidu.com/lightapp/pay/v2/order?id=20668017"}
//		order.setNumberOut(orderIdB);
//		order.setPayUrl(url);
//		orderService.merge(order);
//		
//		ActionContext.getContext().put("order", order);
//		return "ok";
//	}
//	/**
//	 * 订单支付
//	 */
//	public String pay() throws ServiceException{
//		String pay = super.pay();
//		return pay;
//	}
	/**
	 * 支付成功页面
	 */
	public String paySuccessPage() throws ServiceException{
		return super.paySuccessPage();
	}
	
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	

}
