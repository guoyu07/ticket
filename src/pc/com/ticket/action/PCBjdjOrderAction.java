package com.ticket.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.enumer.PayMethod;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjOrder;
import com.ticket.pojo.BjdjPage;
import com.ticket.pojo.BjdjServiceCode;
import com.ticket.pojo.BjdjServiceItem;
import com.ticket.pojo.BjdjServicePackage;
import com.ticket.pojo.Member;
import com.ticket.pojo.News;
import com.ticket.pojo.SystemDictionary;
import com.ticket.service.IBjdjAppointmentService;
import com.ticket.service.IBjdjCommentService;
import com.ticket.service.IBjdjHallService;
import com.ticket.service.IBjdjRefundService;
import com.ticket.service.INewsService;
import com.ticket.serviceImpl.AlipayServiceImpl;
import com.ticket.serviceImpl.SwiftServiceImpl;

/**
 * PC端便捷登机订单的处理控制器
 * @author tuyou
 */
public class PCBjdjOrderAction extends BjdjOrderTemplateAction {

	private static final long serialVersionUID = 1L;
	
	@Resource
	protected INewsService newsService;
	@Resource
	protected IBjdjAppointmentService appointmentService;
	@Resource
	protected IBjdjHallService hallService;
	@Resource
	protected IBjdjRefundService bjdjRefundService;

	protected Member member;
	protected boolean error;

	// 服务介绍、服务流程、使用条款
	protected News serviceIntroduce, serviceProcess, use;
	protected IBjdjCommentService commentService;

	/**
	 * 进入便捷登机首页
	 */
	@Override
	public String execute() throws Exception {

		if ("newAdd".equals(type)) {

			return generatePage();
		}

		try {
			//获得便捷登机的价格
			BjdjServicePackage package1 = servicePackageService.getMinPriceByBjdjUrl("#");
			ActionContext.getContext().put("package1", package1);
			
			//获取所有的便捷登机项目
			List<BjdjServiceItem> items = serviceItemService.get(package1);
			ActionContext.getContext().put("bjdj_items", items);

			BjdjPage bjdjPage = package1.getBjdjPage();
			
			// 机场服务热线
//			String service_phone = dictionaryService.getValueByName("service_phone");
			String service_phone = bjdjPage.getServicePhone();
			ActionContext.getContext().put("service_phone", service_phone);

			// 排队等待的人数
			SystemDictionary customer = dictionaryService.getByValue("individual");
			int wait = appointmentService.waitNumber(new Date(), customer);
			ActionContext.getContext().put("wait", wait);

			// 还可以进入排队的人数
			int capcity = hallService.totalCapcity(customer);
			ActionContext.getContext().put("surplus", capcity - wait > 0 ? capcity - wait : 0);

			serviceIntroduce = newsService.queryByUrl("1446704881204", true);
			serviceProcess = newsService.queryByUrl("1446705154884", true);
			use = newsService.queryByUrl("1445940941512", true);
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 搜索出来的便捷登机，点击进入便捷登机首页
	 * @return
	 * @throws ServiceException
	 */
	public String indexAjax() throws Exception{
		if ("newAdd".equals(type)) {

			return generatePageAjax();
		}

		try {
			//获得便捷登机的价格
			BjdjServicePackage package1 = servicePackageService.getMinPriceByBjdjPage(bjdjPage_id);
			ActionContext.getContext().put("package1", package1);
			
			//获取所有的便捷登机项目
			List<BjdjServiceItem> items = serviceItemService.get(package1);
			ActionContext.getContext().put("bjdj_items", items);
			
			BjdjPage bjdjPage = package1.getBjdjPage();
			
			//介绍
			News serviceProfile = bjdjPage.getServiceProfile();
			Long visitUrl = serviceProfile.getStatus().getVisitUrl();
			
			//流程
			News serviceFlow = bjdjPage.getServiceFlow();
			Long visitUrl1 = serviceFlow.getStatus().getVisitUrl();
			
			//使用条款
			News useSerms = bjdjPage.getUseSerms();
			Long visitUrl2 = useSerms.getStatus().getVisitUrl();
			
			// 机场服务热线
//			String service_phone = dictionaryService.getValueByName("service_phone");
			String service_phone = bjdjPage.getServicePhone();
			ActionContext.getContext().put("service_phone", service_phone);

			// 排队等待的人数
			SystemDictionary customer = dictionaryService.getByValue("individual");
			int wait = appointmentService.waitNumber(new Date(), customer);
			ActionContext.getContext().put("wait", wait);

			// 还可以进入排队的人数
			int capcity = hallService.totalCapcity(customer);
			ActionContext.getContext().put("surplus", capcity - wait > 0 ? capcity - wait : 0);

			serviceIntroduce = newsService.queryByUrl(visitUrl+"", true);
			serviceProcess = newsService.queryByUrl(visitUrl1+"", true);
			use = newsService.queryByUrl(visitUrl2+"", true);
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "indexAjax";
	}

	/**
	 * 构造订单页
	 * @return
	 */
	public String generatePage() throws Exception {
		BjdjPage bjdjPage = bjdjPageService.queryByUrl("#");
		
		List<BjdjServicePackage> bjdjServicePackages = servicePackageService.queryByBjdjPage(bjdjPage.getId());
		ActionContext.getContext().put("bjdjServicePackages", bjdjServicePackages);
		return "generatePage";
	}
	/**
	 * 搜索指定关键词出来的便捷登机，进入构造订单页
	 * @return
	 * @throws ServiceException
	 */
	public String generatePageAjax() throws ServiceException{
		List<BjdjServicePackage> bjdjServicePackages = servicePackageService.queryByBjdjPage(bjdjPage_id);
		ActionContext.getContext().put("bjdjServicePackages", bjdjServicePackages);
		ActionContext.getContext().put("bjdjPage_id", bjdjPage_id);
		return "generatePageAjax";
	}
	
	/**
	 * 选择支付方式页面
	 * @return
	 */
	public String payMethod(){
		
		order = (BjdjOrder)getSession().get("order");
		if(order == null){
			
			order = orderService.get(BjdjOrder.class, id);
		}
		return "payMethod";
	}

	/**
	 * 跳转到支付页面
	 */
	public String toPay(){
		
		try {
			order = orderService.get(BjdjOrder.class, id);
			getSession().put("payMethod", payMethod);
			String orderSourceUrl = "pcOrder.action";
			
			if(payMethod == PayMethod.alipay){
				
				String pageUrl = "pcOrder_alipaySuccessUrl.action";
				AlipayServiceImpl alipay = (AlipayServiceImpl)alipayService;
				alipay.orderPayPC(order, pageUrl, orderSourceUrl);
				return "alipay";
			}else if(payMethod == PayMethod.swift){
				
				SwiftServiceImpl swift = (SwiftServiceImpl)swiftService;
				String twoCodeUrl = swift.codeUrl(order);
				if(!StringUtils.isBlank(twoCodeUrl)){
					
					ActionContext.getContext().put("url", twoCodeUrl);
					return "wxpay";
				}
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "toPayMethod";
	}

	/**
	 * 内容展示
	 */
	public String contentDisplay() {

		try {
			use = newsService.queryByUrl("1445940941512", true);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "contentDispalayPage";
	}

	/**
	 * 支付成功页面
	 * @return
	 */
	public String paySuccessPage() {

		if (getSession().get(ContextConstants.ACTIVATE_ITEM_HTML) != null) {
			
			return "activatePage";
		}

		if(order == null){
			
			order = orderService.get(BjdjOrder.class, id);
		}
		List<BjdjServiceCode> codes = serviceCodeService.queryByColumn("order", order);
		ActionContext.getContext().put("codes", codes);
		ActionContext.getContext().put("order", order);

		return "paySuccessPage";
	}
	
	/**
	 * 获取订单是否支付的状态
	 * @return
	 */
	public String orderStatus(){
		
		order = orderService.get(BjdjOrder.class, id);
		if("paid".equals(order.getState().getName())){
			
			data = AjaxData.responseSuccess(null);
		}else{
			
			data = AjaxData.responseError(null);
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
//		return super.baiduSuccessPage();
//	}
	
	/**
	 * 支付宝支付成功的跳回页面
	 * @return
	 */
	public String alipaySuccessUrl(){
		
		return super.alipaySuccessUrl();
	}
	
	/**
	 * 微信支付成功的跳回页面
	 * @return
	 */
	public String wxSuccessUrl(){
		
		return super.wxSuccessUrl();
	}

	/**
	 * pc详情页面
	 */
	public String orderDetailsPage() throws ServiceException {
		return super.orderDetailsPage();
	}

	/**
	 * 转增页面
	 */
	public String orderDonationPage() throws ServiceException {
		return super.orderDonationPage();
	}

	/**
	 * 转增
	 */
	public String orderDonation() throws ServiceException {
		return super.orderDonation();
	}

	/**
	 * 退款页面
	 */
	public String orderRefundPage() {
		return super.orderRefundPage();
	}

	/**
	 * 订单退款页面
	 */	
	public String orderRefund() {

		return super.orderRefund();
	}
	
	/**
	 * 服务吗延期页
	 */
	public String serviceCodeDelayPage() throws ServiceException{
		return super.serviceCodeDelayPage();
	}
	
	public String serviceCodeDelay() throws ServiceException{
		return super.serviceCodeDelay();
	}
	
	/**
	 * 我的全部订单
	 */
	public String allOrder() throws ServiceException{
		Member member = (Member)getSession().get(ContextConstants.SCOPE_MEMBER);
		List<BjdjOrder> allOrder = orderService.queryByMemberId(member.getId());
		ActionContext.getContext().put("allOrder", allOrder);
		
		List<BjdjOrder> needPay = orderService.queryByMemberIdAndStatus(member.getId(), dictionaryService.getByName("noPay"));
		ActionContext.getContext().put("needPay", needPay);
		
		List<BjdjOrder> needComment = orderService.queryByMemberIdAndDontComment(member.getId());
		ActionContext.getContext().put("needComment", needComment);
		
		List<BjdjServiceCode> serviceCodes = serviceCodeService.receivedDonation(member);
		ActionContext.getContext().put("donation", serviceCodes);
		
		List<BjdjOrder> used = orderService.queryByMemberIdAndStatus(member.getId(), dictionaryService.getByName("used"));
		ActionContext.getContext().put("used", used);
		return "allOrder";
	}
	
	/**
	 * 删除订单
	 */
	public String realDelete() throws ServiceException {
		return super.realDelete();
	}

	public INewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(INewsService newsService) {
		this.newsService = newsService;
	}

	public News getUse() {
		return use;
	}

	public void setUse(News use) {
		this.use = use;
	}

	public News getServiceIntroduce() {
		return serviceIntroduce;
	}

	public void setServiceIntroduce(News serviceIntroduce) {
		this.serviceIntroduce = serviceIntroduce;
	}

	public News getServiceProcess() {
		return serviceProcess;
	}

	public void setServiceProcess(News serviceProcess) {
		this.serviceProcess = serviceProcess;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public IBjdjCommentService getCommentService() {
		return commentService;
	}

	public void setCommentService(IBjdjCommentService commentService) {
		this.commentService = commentService;
	}

	public IBjdjAppointmentService getAppointmentService() {
		return appointmentService;
	}

	public void setAppointmentService(IBjdjAppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}

	public IBjdjHallService getHallService() {
		return hallService;
	}

	public void setHallService(IBjdjHallService hallService) {
		this.hallService = hallService;
	}

}
