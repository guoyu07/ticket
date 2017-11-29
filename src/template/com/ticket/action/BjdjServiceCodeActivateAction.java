package com.ticket.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjAppointment;
import com.ticket.pojo.BjdjOrder;
import com.ticket.pojo.BjdjPage;
import com.ticket.pojo.BjdjPageTemplate;
import com.ticket.pojo.BjdjServiceCode;
import com.ticket.pojo.BjdjServicePackage;
import com.ticket.pojo.ButtonHelp;
import com.ticket.pojo.CommonTravellerCard;
import com.ticket.pojo.DepartFromPort;
import com.ticket.pojo.MemberFocusFlight;
import com.ticket.pojo.SecurityData;
import com.ticket.pojo.SystemDictionary;
import com.ticket.service.IBjdjActivateQueueService;
import com.ticket.service.IBjdjAppointmentService;
import com.ticket.service.IBjdjHallService;
import com.ticket.service.IBjdjOrderService;
import com.ticket.service.IBjdjServiceCodeService;
import com.ticket.service.ICommonTravellerCardService;
import com.ticket.service.ICommonTravellerService;
import com.ticket.service.IDepartFromPortService;
import com.ticket.service.IEmployeeInfoZengLogDetailService;
import com.ticket.service.IInfoPositionService;
import com.ticket.service.IMemberFocusFlightService;
import com.ticket.service.ISecurityDataService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.util.DateUtil;
import com.ticket.util.GeneralUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @Description：服务码激活
 * @author：涂有
 * @date 2015年10月30日 下午11:24:16
 */
public class BjdjServiceCodeActivateAction extends BaseAction{
	
	protected static final long serialVersionUID = 1L;
	@Resource
	protected IBjdjServiceCodeService serviceCodeService;
	@Resource
	protected IBjdjOrderService orderService;
	@Resource
	protected IBjdjHallService hallService;
	@Resource
	protected ISystemDictionaryService dictionaryService;
	@Resource
	protected IBjdjAppointmentService appointmentService;
	@Resource
	protected IDepartFromPortService departFromPortService;	
	@Resource 
	protected ICommonTravellerService travellerService;
	@Resource 
	protected ICommonTravellerCardService cardService;
	@Resource
	protected IInfoPositionService infoPositionService;
	@Resource
	protected IBjdjActivateQueueService activateQueueService;
	@Resource
	protected ISecurityDataService securityDataService;
	@Resource
	protected IMemberFocusFlightService focusFlightService;
	@Resource
	protected IEmployeeInfoZengLogDetailService zengLogDetailService;
	
	protected BjdjOrder order;
	protected BjdjAppointment appointment;
	protected List<Map<String, Object>> list;
	
	protected String mid;
	protected String orderId;
	protected String mobile;
	protected String authCode;
	protected BjdjServiceCode serviceCode; 
	protected String codeId;
	protected String[] name;
	protected String[] code;
	protected String[] idCard;
	protected String[] flightNo;
	protected Date[] date;
	protected String agree;
	//证件类型
	protected String[] cardType;
	
	protected String codes;
	
	/************表单ajax验证参数***********/
	protected String param;
	
	//激活项item
	protected String html;
	//是否检测排队
	protected boolean checkQueueWait = true;
	
	/**
	 * @Description：进入激活页面
	 * @return
	 */
	public String page(){
		
		List<SystemDictionary> idcards = dictionaryService.querySubByParentName("certificate_type");
		ActionContext.getContext().put("comboList", idcards);
		
		return "page";
	}
	
	/**
	 * @Description：跳转到激活成功页面
	 * @return
	 * @throws ServiceException 
	 */
	public String successPage() throws ServiceException{
		//修改
		code = codes.split(",");
		if(GeneralUtil.isNotNull(code)){
			String serviceCode = code[0];
			BjdjServiceCode bjdjCode = serviceCodeService.getByCode(serviceCode);
			BjdjOrder bjdjOrder = bjdjCode.getOrder();
			BjdjServicePackage bjdjServicePackage = bjdjOrder.getPackageType();
			BjdjPage bjdjPage = bjdjServicePackage.getBjdjPage();
			BjdjPageTemplate news = bjdjPage.getActiveSuc();
			//设置激活成功提示
			ActionContext.getContext().put("activate_success_tip", news.getContent());
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
		}
		//修改
		
//		SystemDictionary dict = dictionaryService.getByName("activate_success_tip");
//		if(dict != null){
//			
//			ActionContext.getContext().put("activate_success_tip", dict.getDescription());
//		}
		
		return SUCCESS;
	}
	
	/**
	 * @Description：检验服务码
	 * @return
	 */
	public String validateServiceCode(){
		
		try {
			BjdjServiceCode serviceCode = serviceCodeService.getByCode(param.trim());
			if(zengLogDetailService.canActivate(serviceCode)){ //先检查渠道客户分销渠道是否满足
				
				serviceCodeService.canActivate(serviceCode); //会员前端购买的情况
			}
		} catch (ServiceException e) {
			data = AjaxData.responseError(e.getMessage());
			return JSON;
		}
		data = AjaxData.responseSuccess("验证成功");
		return JSON;
	}
	
	/**
	 * @throws ServiceException 
	 * @Description：激活服务码
	 * @return
	 */
	public String activate() {
		
		try {
			
			if(code == null){
				
				data = AjaxData.responseError(getText("serviceCode.required"));
				return JSON;
			}
			
			BjdjServiceCode[] serviceCodes = new BjdjServiceCode[code.length];
			for(int i = 0; i < code.length; i++){
				
				BjdjServiceCode serviceCode = serviceCodeService.getByCode(code[i].trim());
				if(serviceCode == null){
					
					data = AjaxData.responseError(code + "：服务码错误");
					return JSON;
				}
				serviceCodes[i] = serviceCode;
			}
			
			//排队检测
			if(checkQueueWait){
				
				for(Date flightDate : date){
					
					boolean hasSurplus = appointmentService.hasSurplus(flightDate, serviceCodes);
					if(!hasSurplus){
						
						JSONObject json = new JSONObject();
						json.put("saturation", true);
						data = AjaxData.responseError(json);
						return JSON;
					}
				}
			}
			
			appointmentService.onlineAppointment(serviceCodes, date, flightNo, idCard, cardType, name);
			
			request.getSession().removeAttribute(ContextConstants.ACTIVATE_ITEM_HTML);
			data = AjaxData.responseSuccess(getText("activateSuccess"));
			return JSON;
		} catch (ServiceException e) {
			data = AjaxData.responseError(e.getMessage());
			return JSON;
		}
	}
	
	/**
	 * 短信提示，用户提示想要激活的用户大厅有空位  
	 * @return
	 */
	public String smsTip(){
		
		//服务码验证
		if(code != null && date != null){
			
			BjdjServiceCode[] serviceCodes = new BjdjServiceCode[code.length];
			for(int i = 0; i < code.length; i++){
				
				BjdjServiceCode serviceCode = serviceCodeService.getByCode(code[i].trim());
				serviceCodes[i] = serviceCode;
			}
			
			//对所有大厅数量小于零的服务码加入短信提醒队列
			for(int i = 0; i < code.length; i++){
				
				if(appointmentService.surplus(date[i], serviceCodes[i].getType()) <= 0){
					
					activateQueueService.addWait(getSessionMember().getPhone(), serviceCodes[i].getType(), date[i]);
				}
			}
		}
		data = AjaxData.responseSuccess(null);
		return JSON;
	}
	
	/**
	 * 取消激活，可以传多个服务码id
	 * @return
	 */
	public String unActivate(){
		
		try {
			appointmentService.unActivate(idsValue, getSessionMember());
		} catch (ServiceException e) {
			data = AjaxData.responseError(e.getMessage());
			return JSON;
		}
		data = AjaxData.responseSuccess(getText("serviceCode.unactivate.success"));
		return JSON;
	}
	
	/**
	 * 获取所有的值机航班
	 * @return
	 */
	public String checkInFlight(){
		
		List<SecurityData> flights = securityDataService.findByIDCard(idCard[0]);
		if(flightNo != null){
			
			Arrays.sort(flightNo);
		}
		
		JSONArray jsonArray = new JSONArray();
		for(SecurityData flight : flights){
		
			//不在用户添加里面的才显示
			if(flightNo == null){
				
				jsonArray.add(flight.getFltNo());
			}else if(Arrays.binarySearch(flightNo, flight.getFltNo()) < 0){
				
				jsonArray.add(flight.getFltNo());
			}
		}
		data = AjaxData.responseSuccess(jsonArray);
		return JSON;
	}

	/**
	 * 获取所有的关注航班
	 * @return
	 */
	public String focusOnFlight(){
		
		List<MemberFocusFlight> flights;
		try {
			flights = focusFlightService.queryListByMember(versionFlag);
		} catch (ServiceException e) {
			
			data = AjaxData.responseError(null); 
			return JSON;
		}
		
		if(flightNo != null){
			
			Arrays.sort(flightNo);
		}
		JSONArray jsonArray = new JSONArray();
		
		if(flights != null){
			
			for(MemberFocusFlight flight : flights){
				
				DepartFromPort d = departFromPortService.queryByFlightNoAndDate(flight.getFlightNumber(), flight.getFlightDate(), versionFlag);
				if(d == null){ //库中没有指定的航班
					
					continue;
				}
				
				//必须在起飞前一段时间
				Calendar firstDate = Calendar.getInstance();
				firstDate.setTime(d.getStd());
				firstDate.add(Calendar.DAY_OF_MONTH, -2);
				firstDate.add(Calendar.HOUR_OF_DAY, 8);
				if(System.currentTimeMillis() > d.getStd().getTime() - 90*60*1000
						|| System.currentTimeMillis() < firstDate.getTime().getTime()){
					
					continue;
				}
				
				//考虑到服务厅的营业时间，航班早于7:30起飞、晚于23:00的航班，不能激活
				Calendar flightDate = Calendar.getInstance();
				firstDate.setTime(d.getStd());
				if((flightDate.get(Calendar.HOUR_OF_DAY) <= 7 && flightDate.get(Calendar.MINUTE) <= 30)
						|| flightDate.get(Calendar.HOUR_OF_DAY) >= 23){
					
					continue;
				}
				
				JSONArray subJsonArray = new JSONArray();
				//不在用户添加里面的才显示
				if(flightNo == null){
					
					subJsonArray.add(flight.getFlightNumber());
				}else if(Arrays.binarySearch(flightNo, flight.getFlightNumber()) < 0){
					
					subJsonArray.add(flight.getFlightNumber());
				}
				subJsonArray.add(DateUtil.formatDateToShortString(d.getStd()) + "T" + DateUtil.formatDateToTimeString(d.getStd()));
				jsonArray.add(subJsonArray);
			}
		}
		data = AjaxData.responseSuccess(jsonArray);
		return JSON;
	}
	
	/**
	 * @Description：通过身份证等证件添加
	 * @return
	 * @throws ServiceException 
	 */
	public String addByCertificate(){

		if(code != null){
			
			code = code[0].split(",");
		}
		if(code != null && code.length > 5){
			
			data = AjaxData.responseSuccess("1");
			return JSON;
		}
		
		serviceCode = serviceCodeService.randomGetCanActivate(getSessionMember().getId(), orderId, code);
			
		Map<String, Object> obj = new HashMap<String, Object>();
		//自动设置起飞时间
		SecurityData securityData = securityDataService.get(idCard[0], flightNo[0]);
		obj.put("date", DateUtil.formatDateToShortString(securityData.getBoardingData()) + "T" 
				+ DateUtil.formatDateToTimeString(securityData.getBoardingData()));
		//根据身份证号码、护照号码，获取姓名
		CommonTravellerCard card = cardService.findByIdCardValue(getSessionMember(), idCard[0]);
		if(card != null){
			
			obj.put("name", card.getParent().getName());
		}
		if(serviceCode != null){
			
			obj.put("code", serviceCode.getCode());
		}
		obj.put("cardType", cardType[0]);
		//设置身份证号
		obj.put("idCard", idCard[0]);
		//自动设置航班号
		obj.put("flightNo", flightNo[0]);
		
		//设置服务码
		ActionContext.getContext().put("data", obj);
		return "activateItem";
	}
	
	/**
	 * @Description：通过身份证等证件添加
	 * @return
	 * @throws ServiceException 
	 */
	public String addBlank(){
		
		if(code != null){
			
			code = code[0].split(",");
		}
		if(code != null && code.length > 5){
			
			data = AjaxData.responseSuccess("1");
			return JSON;
		}
		
		Map<String, Object> obj = new HashMap<String, Object>();
		//根据身份证号码、护照号码，获取姓名
		CommonTravellerCard card = cardService.findByIdCardValue(getSessionMember(), idCard[0]);
		if(card != null){
			
			obj.put("name", card.getParent().getName());
		}
		serviceCode = serviceCodeService.randomGetCanActivate(getSessionMember().getId(), orderId, code);
		if(serviceCode != null){
			
			obj.put("code", serviceCode.getCode());
		}
		obj.put("cardType", cardType[0]);
		//设置身份证号
		obj.put("idCard", idCard[0]);
		
//		DepartFromPort depart = departFromPortService.randomGetOne();
//		obj.put("flightNo", depart.getFlt());
//		
//		depart.getStd().setDate(new Date().getDate() + 1);
//		String date = DateUtil.formatDateToSimpleString(depart.getStd());
//		obj.put("date", date);
		
		//设置服务码
		ActionContext.getContext().put("data", obj);
		return "activateItem";
	}
	
	/**
	 * @Description：保存激活项
	 * @date 2015年12月21日 下午5:20:02
	 * @return
	 */
	public String saveActivateItem(){

		request.getSession().setAttribute(ContextConstants.ACTIVATE_ITEM_HTML, html);
		data = AjaxData.responseSuccess("");
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
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public BjdjOrder getOrder() {
		return order;
	}

	public void setOrder(BjdjOrder order) {
		this.order = order;
	}

	public BjdjAppointment getAppointment() {
		return appointment;
	}

	public void setAppointment(BjdjAppointment appointment) {
		this.appointment = appointment;
	}

	public String getAgree() {
		return agree;
	}

	public void setAgree(String agree) {
		this.agree = agree;
	}
	
	public String getParam() {
		return param;
	}
	
	public void setParam(String param) {
		this.param = param;
	}

	public BjdjServiceCode getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(BjdjServiceCode serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public IBjdjServiceCodeService getServiceCodeService() {
		return serviceCodeService;
	}

	public void setServiceCodeService(IBjdjServiceCodeService serviceCodeService) {
		this.serviceCodeService = serviceCodeService;
	}

	public IBjdjOrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(IBjdjOrderService orderService) {
		this.orderService = orderService;
	}

	public IBjdjHallService getHallService() {
		return hallService;
	}

	public void setHallService(IBjdjHallService hallService) {
		this.hallService = hallService;
	}

	public ISystemDictionaryService getDictionaryService() {
		return dictionaryService;
	}

	public void setDictionaryService(ISystemDictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	public IBjdjAppointmentService getAppointmentService() {
		return appointmentService;
	}

	public void setAppointmentService(IBjdjAppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}

	public IDepartFromPortService getDepartFromPortService() {
		return departFromPortService;
	}

	public void setDepartFromPortService(
			IDepartFromPortService departFromPortService) {
		this.departFromPortService = departFromPortService;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public ICommonTravellerService getTravellerService() {
		return travellerService;
	}

	public void setTravellerService(ICommonTravellerService travellerService) {
		this.travellerService = travellerService;
	}

	public ICommonTravellerCardService getCardService() {
		return cardService;
	}

	public void setCardService(ICommonTravellerCardService cardService) {
		this.cardService = cardService;
	}

	public List<Map<String, Object>> getList() {
		return list;
	}

	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}

	public String[] getIdCard() {
		return idCard;
	}

	public void setIdCard(String[] idCard) {
		this.idCard = idCard;
	}

	public String[] getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String[] flightNo) {
		this.flightNo = flightNo;
	}

	public String[] getName() {
		return name;
	}

	public void setName(String[] name) {
		this.name = name;
	}

	public String[] getCardType() {
		return cardType;
	}

	public void setCardType(String[] cardType) {
		this.cardType = cardType;
	}

	public Date[] getDate() {
		return date;
	}

	public void setDate(Date[] date) {
		this.date = date;
	}

	public String[] getCode() {
		return code;
	}

	public void setCode(String[] code) {
		this.code = code;
	}

	public IInfoPositionService getInfoPositionService() {
		return infoPositionService;
	}

	public void setInfoPositionService(IInfoPositionService infoPositionService) {
		this.infoPositionService = infoPositionService;
	}

	public IBjdjActivateQueueService getActivateQueueService() {
		return activateQueueService;
	}

	public void setActivateQueueService(
			IBjdjActivateQueueService activateQueueService) {
		this.activateQueueService = activateQueueService;
	}

	public ISecurityDataService getSecurityDataService() {
		return securityDataService;
	}

	public void setSecurityDataService(ISecurityDataService securityDataService) {
		this.securityDataService = securityDataService;
	}

	public IMemberFocusFlightService getFocusFlightService() {
		return focusFlightService;
	}

	public void setFocusFlightService(IMemberFocusFlightService focusFlightService) {
		this.focusFlightService = focusFlightService;
	}

	public boolean isCheckQueueWait() {
		return checkQueueWait;
	}

	public void setCheckQueueWait(boolean checkQueueWait) {
		this.checkQueueWait = checkQueueWait;
	}

	public String getCodes() {
		return codes;
	}

	public void setCodes(String codes) {
		this.codes = codes;
	}
}
