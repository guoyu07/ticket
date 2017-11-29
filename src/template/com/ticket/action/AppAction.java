
package com.ticket.action;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.bo.AppJson;
import com.ticket.enumer.SearchType;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.AirportInfo;
import com.ticket.pojo.ArrivalAtPort;
import com.ticket.pojo.CheckinInfo;
import com.ticket.pojo.CommonSearch;
import com.ticket.pojo.DepartFromPort;
import com.ticket.pojo.FCSelfCheckinPosition;
import com.ticket.pojo.FlightCompany;
import com.ticket.pojo.InfoPosition;
import com.ticket.pojo.Member;
import com.ticket.pojo.MemberFocusFlight;
import com.ticket.pojo.MemberQQ;
import com.ticket.pojo.MemberSendInfo.PushMethod;
import com.ticket.pojo.MemberWeiBo;
import com.ticket.pojo.MemberWeixin;
import com.ticket.pojo.News;
import com.ticket.pojo.NewsClass;
import com.ticket.pojo.SystemConfig;
import com.ticket.pojo.SystemDictionary;
import com.ticket.service.IAirportInfoService;
import com.ticket.service.IAppService;
import com.ticket.service.IArrivalAtPortService;
import com.ticket.service.IAutoSendMessageService;
import com.ticket.service.ICheckinInfoService;
import com.ticket.service.ICommonSearchService;
import com.ticket.service.IDepartFromPortService;
import com.ticket.service.IFCSelfCheckinPositionService;
import com.ticket.service.IFlightCompanyService;
import com.ticket.service.IInfoPositionService;
import com.ticket.service.IMassInfomationService;
import com.ticket.service.IMemberFocusFlightService;
import com.ticket.service.IMemberMacService;
import com.ticket.service.IMemberQqService;
import com.ticket.service.IMemberSendInfoService;
import com.ticket.service.IMemberService;
import com.ticket.service.IMemberWeiBoService;
import com.ticket.service.IMemberWeixinService;
import com.ticket.service.INewsClassService;
import com.ticket.service.INewsService;
import com.ticket.service.ISecurityDataService;
import com.ticket.service.ISystemConfigService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.service.ITimeSearchService;
import com.ticket.util.DateUtil;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SmsUtil;
import com.ticket.util.UrlUtil;
import com.ticket.util.ValidateUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * APP端控制器
 * @author haishui
 */
public class AppAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Resource  private IAppService appService = null;
	@Resource  private INewsClassService newsClassService = null;
	@Resource  private INewsService newsService = null;
	@Resource  private ICommonSearchService commonSearchService = null;
	//系统配置的业务层
	@Resource  private ISystemConfigService systemConfigService = null;
	//机场设施位置的业务层
	@Resource private IInfoPositionService infoPositionService = null;
	//值机信息业务层
	@Resource private ICheckinInfoService checkinInfoService;
	//我的关注
	@Resource protected IMemberFocusFlightService memberFocusFlightService;
	//航空公司业务层
	@Resource protected IFlightCompanyService flightCompanyService;
	//航空公司自助值机点业务层
	@Resource protected IFCSelfCheckinPositionService fCSelfCheckinPositionService;
	//消息自动推送业务层
	@Resource protected IAutoSendMessageService autoSendMessageService;
	//离港航班业务层
	@Resource protected IDepartFromPortService departFromPortService;
	//进港航班业务层
	@Resource protected IArrivalAtPortService arrivalAtPortService;
	//机场信息的业务层
	@Resource protected IAirportInfoService airportInfoService;
	//安检数据的业务层
	@Resource protected ISecurityDataService securityDataService;
	//会员实体的业务层
	@Resource protected IMemberService memberService;
	//群发消息的业务层
	@Resource protected IMassInfomationService massInfomationService;
	//会员发送消息的业务层
	@Resource protected IMemberSendInfoService memberSendInfoService;
	//系统字典的业务层
	@Resource protected ISystemDictionaryService systemDictionaryService;
	//用户mac数据
	@Resource protected IMemberMacService memberMacService;
	@Resource protected ITimeSearchService timeSearchService;
	@Resource protected IMemberQqService memberQqService;
	@Resource protected IMemberWeixinService memberWeixinService;
	@Resource protected IMemberWeiBoService memberWeiBoService;
	
	//实体id
	private String id = null;
	//新闻类别id
	private String newsClass_id = null;
	//新闻id
	private String news_id = null;
	//类别别名
	private String classAlias = null;
	//会员id
	private String member_id = null;
	//关键词
	private String keyword = null;
	//起始位置
	private Integer startSize = 0;
	//每次获取几条
	private Integer pageCount = 6;
	
	//航班号
	private String flightNumber = null;
	//航班日期
	private String flightDate = null;
	//航班状态
	private String flightState = null;
	//会员角色
	private String memberRole = null;
	//短信内容 
	private String smsContent = null;
	
	// 电话号码
	private String mobile = null;
	// 座位号
	private String seatNo = null;
	// 电子客票号
	private String ticketNo = null;
	// 值机标识
	private String couponId = null;
	
	//名称
	private String name = null;
	//设施位置别名
	private String positionAlias = null;
	//经度
	private String longitude = null;
	//纬度
	private String latitude = null;
	
	//出发城市
	private String setoutCity = null;
	//到达城市
	private String reachCity = null;
	//航空公司二字码
	private String twoCode = null;
	private String sendMobile = null;//分享者电话号码
	
	private String url,content;//链接地址，反馈内容
	private String deptAirportCode;//起飞城市三字码
	private String destAirportCode;//到达城市三字码
	
	/**
	 * 接收第三方传输的数据
	 * @return
	 */
	public String receive(){
		
		String receive = UrlUtil.getRequestContent(request);
		try {
			memberMacService.receiveMobileData(receive);
		} catch (ServiceException e) {
			data = AjaxData.responseError(e.getMessage());
			return JSON;
		}
		data = AjaxData.responseSuccess("receive success");
		return JSON;
	}
	
	/**
	 * @Description：获取系统配置接口
	 * @date 2015年12月31日 上午11:50:23
	 * @return
	 * @throws ServiceException 
	 */
	public String settingInterface() throws ServiceException{
		
		SystemConfig systemConfig = systemConfigService.querySystemConfig();
		data = JSONObject.fromObject(systemConfig).toString();
		return TEXT;
	}
	
	/**
	 * 获取左侧的导航菜单
	 * @return
	 * @throws Exception
	 */
	public String queryLeftMenu() throws Exception {
		
		data = appService.queryLeftMenu();
		return TEXT;
	}
	/**
	 * 根据新闻类别获取新闻列表
	 * @return
	 * @throws Exception
	 */
	public String queryNewsByNewsClassId() throws Exception{
		
		data = appService.queryNewsByNewsClassId(newsClass_id);
		return TEXT;
	}
	/**
	 * 根据新闻id查询新闻实体
	 * @return
	 * @throws Exception
	 */
	public String QueryNewsById() throws Exception{
		News news = newsService.queryById(News.class.getSimpleName(), news_id);
		if(news != null){
			data = new AppJson(null, JSONArray.fromObject(news).toString(), 1);
		}else{
			data = new AppJson(null, getText("news.empty"), 1);
		}
		return JSON;
	}

	/**
	 * 根据别名查询新闻类别
	 * @return
	 * @throws Exception
	 */
	public String queryClassByAlias() throws Exception{
		NewsClass newsClass = newsClassService.queryByAlias(versionFlag, classAlias);
		if(newsClass != null){
			data = new AppJson(null, JSONArray.fromObject(newsClass).toString(), 1);
		}else{
			data = new AppJson(null, getText("newsClass.empty"), 0);
		}
		return JSON;

	}

	/**
	 * 右滑菜单接口
	 * @return
	 * @throws Exception
	 */
	public String getRightMenuUrl() throws Exception{
		
		data = appService.getRightMenuUrl(member_id);
		return TEXT;
	}
	
	/**
	 * 根据id删除会员设置的快捷菜单
	 * @return
	 * @throws ServiceException
	 */
	public String deleteQuickMenuById() throws ServiceException{
		if(appService.deleteQuickMenu(id)){
			data = AjaxData.responseSuccess("deleteSuccess");
		}else{
			data = AjaxData.responseError("deleteFailed");
		}
		return JSON;
	}
	/**
	 * 获取温馨提示内容
	 * @return
	 * @throws Exception
	 */
	public String queryKindRemindContent() throws Exception {
		
		data = appService.queryKindRemindContent();
		return TEXT;
	}

	/**
	 * 公共检索
	 * @return
	 * @throws Exception
	 */
	public String commonSearch() throws Exception{
		flightDate = DateUtil.formatDateToShortString(new Date());
		DepartFromPort departFromPort = departFromPortService.queryByFlightNoAndDate(keyword, flightDate,versionFlag);
		ArrivalAtPort arrivalAtPort = arrivalAtPortService.queryByFlightNoAndDate(keyword, flightDate,versionFlag);
		JSONObject json = new JSONObject();
		if(departFromPort == null && arrivalAtPort == null){
			List<CommonSearch> list = commonSearchService.searchListByKeyword(SearchType.normal, keyword, startSize, pageCount, request.getRemoteHost()) ; 
			timeSearchService.persist(DecoderUtil.UtfDecoder(keyword).trim(), list);
			if(list != null && !list.isEmpty()){
				String id = (String)ActionContext.getContext().get("id");
				json.put("error", "");
				json.put("data", list);
				json.put("count", list.size());
				json.put("id", id);
				data = json.toString();
			}else{
				json.put("message", "查无结果");
				json.put("status", 0);
				data = new AppJson(null, JSONArray.fromObject(json).toString(), 0);
			}
		}else{
			json.put("url", "/airport_queryByFlightNoAndDate.action?flightNumber="+keyword+"&flightDate="+flightDate);
			json.put("status", 0);
			data = new AppJson(null, JSONArray.fromObject(json).toString(), 1);
		}
		return JSON;
	}
	
	/**
	 * 网上值机相关须知
	 * @return
	 * @throws ServiceException
	 */
	public String checkOnLineNotice() throws Exception{
		List<SystemDictionary> list = appService.queryCheckOnLineNotice();
		data = new AppJson(null, list, 1);
		return JSON;
	}
	
	/**
	 * 关注航班接口
	 * @return
	 */
	/*public String focusFlight() throws ServiceException{
		String flag = appService.focusFlight(member_id,flightNumber,flightDate,flightState,memberRole,setoutCity,reachCity);
		autoSendMessageService.sendMsgAtSucCheck(flightNumber, flightDate, member_id, ticketNo);
		AjaxUtil.writeAppJson(response, request, null, JSONArray.fromObject(flag).toString(),1,true);
		return JSON;
	}*/
	
	/**
	 * 值机后保存航班信息
	 * @return
	 * @throws ServiceException
	 */
	public String saveFocusFlightByCheckin() throws ServiceException{
		
		DepartFromPort dept = departFromPortService.queryByFlightNoAndDate(flightNumber, flightDate, versionFlag);
		MemberQQ memberQQ = memberQqService.queryById(MemberQQ.class.getSimpleName(), member_id);
		MemberWeiBo memberWeiBo = null;
		MemberWeixin memberWeixin = null;
		Member member = null;
		if(memberQQ == null){
			memberWeiBo = memberWeiBoService.queryById(MemberWeiBo.class.getSimpleName(), member_id);
			if(memberWeiBo == null){
				memberWeixin = memberWeixinService.queryById(MemberWeixin.class.getSimpleName(), member_id);
				if(memberWeixin == null){
					member = memberService.queryById(Member.class.getSimpleName(), member_id);
				}
			}
		}
		if(dept == null){
			
			data = new AppJson("未查到指定的航班信息", null, 0);
			return JSON;
		}else{
			//保存值机信息
			JSONObject json = new JSONObject();
			CheckinInfo chi = new CheckinInfo();
			if(memberQQ != null){
				chi.setMemberQQ(memberQQ);
			}else if(memberWeiBo != null){
				chi.setMemberWeiBo(memberWeiBo);
			}else if(memberWeixin != null){
				chi.setMemberWeixin(memberWeixin);
			}else if(member != null){
				chi.setMember(member);
			}
			chi.setFlightNumber(flightNumber);
			chi.setFlightDate(dept.getFlightDate());
			chi.setCouponId(couponId);
			/*chi.setDeptAirportCode(dept.getOrg());
			chi.setDestAirportCode(dept.getDes());*/
			chi.setDeptAirportCode(deptAirportCode);
			chi.setDestAirportCode(destAirportCode);
			chi.setTicketNo(ticketNo);
			chi.setMobile(mobile);
			chi.setSeatNo(seatNo);
			try {
				checkinInfoService.persist(chi);
				json.put("flag", "save Suc");
			} catch (Exception e) {
				e.printStackTrace();
				json.put("flag", "save failed");
			}
			
			//如果未关注，就要生成一条关注信息
			MemberFocusFlight focusFlight = memberFocusFlightService.query(member_id, flightNumber, flightDate);
			if(focusFlight == null){
				focusFlight = memberFocusFlightService.persist(member_id, flightNumber, dept.getFlightDate(), "send", "depart", 0, null, 1, 0, mobile, seatNo, ticketNo, null, versionFlag);
				memberSendInfoService.persist(member.getId(), PushMethod.app, "值机成功提醒", "您好，航班号为"+flightNumber+"的航班已值机成功！", "airportm_myMessage.action");
			}
			AirportInfo org = airportInfoService.queryByThreeCode(deptAirportCode, versionFlag);
			AirportInfo des = airportInfoService.queryByThreeCode(destAirportCode, versionFlag);
			if(memberQQ != null){
				SmsUtil.sendSms(mobile, "尊敬的旅客您好，您所乘坐的"+dept.getStd()+"从"+org.getName()+"-"+des.getName()+"的"+flightNumber+"航班已由账户["+memberQQ.getNickName()+"]为您值机成功,座位号为"+seatNo+"请登陆长水机场APP的个人中心查看值机信息和电子登机牌，通过电子登机牌直接过安检；如未登陆的旅客需在机场使用身份证打印纸质登机牌过安检。在此非常感谢您的信任和使用，祝您旅途愉快！");
			}else if(memberWeiBo != null){
				SmsUtil.sendSms(mobile, "尊敬的旅客您好，您所乘坐的"+dept.getStd()+"从"+org.getName()+"-"+des.getName()+"的"+flightNumber+"航班已由账户["+memberWeiBo.getNickName()+"]为您值机成功,座位号为"+seatNo+"请登陆长水机场APP的个人中心查看值机信息和电子登机牌，通过电子登机牌直接过安检；如未登陆的旅客需在机场使用身份证打印纸质登机牌过安检。在此非常感谢您的信任和使用，祝您旅途愉快！");
			}else if(memberWeixin != null){
				SmsUtil.sendSms(mobile, "尊敬的旅客您好，您所乘坐的"+dept.getStd()+"从"+org.getName()+"-"+des.getName()+"的"+flightNumber+"航班已由账户["+memberWeixin.getNickName()+"]为您值机成功,座位号为"+seatNo+"请登陆长水机场APP的个人中心查看值机信息和电子登机牌，通过电子登机牌直接过安检；如未登陆的旅客需在机场使用身份证打印纸质登机牌过安检。在此非常感谢您的信任和使用，祝您旅途愉快！");
			}else if(member != null && GeneralUtil.isNotNull(member.getPhone())){
				SmsUtil.sendSms(mobile, "尊敬的旅客您好，您所乘坐的"+dept.getStd()+"从"+org.getName()+"-"+des.getName()+"的"+flightNumber+"航班已由账户["+member.getPhone()+"]为您值机成功,座位号为"+seatNo+"请登陆长水机场APP的个人中心查看值机信息和电子登机牌，通过电子登机牌直接过安检；如未登陆的旅客需在机场使用身份证打印纸质登机牌过安检。在此非常感谢您的信任和使用，祝您旅途愉快！");
			}else if(member != null && GeneralUtil.isNull(member.getPhone()) && GeneralUtil.isNotNull(member.getNickName())){
				SmsUtil.sendSms(mobile, "尊敬的旅客您好，您所乘坐的"+dept.getStd()+"从"+org.getName()+"-"+des.getName()+"的"+flightNumber+"航班已由账户["+member.getNickName()+"]为您值机成功,座位号为"+seatNo+"请登陆长水机场APP的个人中心查看值机信息和电子登机牌，通过电子登机牌直接过安检；如未登陆的旅客需在机场使用身份证打印纸质登机牌过安检。在此非常感谢您的信任和使用，祝您旅途愉快！");
			}
			json.put("realName", "");
			json.put("IDCard", "");
			json.put("setoutName",org.getName());
			json.put("reachName", des.getName());
			json.put("startTime", DateUtil.formatDateToSimpleString(dept.getStd()));
			
			JSONArray array = new JSONArray();
			array.add(json);
			data = new AppJson(null, array, 1);
			return JSON;
		}
	}
	
	/**
	 * 保存设施位置
	 * @return
	 * @throws ServiceException
	 */
	public String savePosition() throws ServiceException{
		String flag = appService.savePosition(name,positionAlias,longitude,latitude,versionFlag);
		data = new AppJson(null, JSONArray.fromObject(flag).toString(), 1);
		return JSON;
	}
	
	/**
	 * 获取所有的设施位置列表
	 * @return
	 * @throws ServiceException
	 */
	public String getPositionList() throws ServiceException{
		List<InfoPosition> list = infoPositionService.queryAll(InfoPosition.class);
		data = new AppJson(null, JSONArray.fromObject(list).toString(), 1);
		return JSON;
	}
	
	/**
	 * 根据id删除设施位置
	 * @return
	 * @throws ServiceException
	 */
	public String deletePositionById() throws ServiceException{
		String flag = appService.deletePositionById(id);
		data = new AppJson(null, JSONArray.fromObject(flag).toString(), 1);
		return JSON;
	}
	
	/**
	 * 根据新闻id查询位置列表
	 * @return
	 * @throws ServiceException
	 */
	public String queryLocationListByNewsId() throws ServiceException{
		List<InfoPosition> list = infoPositionService.queryListByNewsId(news_id, versionFlag);
		data = new AppJson(null, JSONArray.fromObject(list).toString(), 1);
		return JSON;
	}
	
	/**
	 * 发送短信
	 * @return
	 * @throws ServiceException
	 */
	public String sendSms() throws ServiceException{
		String flag = appService.sendSms(mobile,smsContent);
		data = new AppJson(null, JSONArray.fromObject(flag).toString(), 1);
		return JSON;
	}
	/**
	 * 获取所有的快捷菜单
	 * @return
	 * @throws ServiceException
	 */
	public String queryAllQuickMenu() throws ServiceException{
		
		data = appService.queryAllQuickMenu();
		return TEXT;
	}
	
	/**
	 * 根据航空公司二字码查询自助值机点位信息
	 * @return
	 * @throws ServiceException
	 */
	public String getPositionListByFCTwoCode() throws ServiceException{
		FlightCompany fc = flightCompanyService.queryEntityByTwoCode(twoCode, versionFlag);
		if(fc != null){
			List<FCSelfCheckinPosition> list = fCSelfCheckinPositionService.queryListByFlightCompanyId(fc.getId(), versionFlag);
			data = new AppJson(null, JSONArray.fromObject(list).toString(), 1);
			return JSON;
		}else{
			data = AjaxData.responseError("no flightCompany");
			return JSON;
		}
	}
	
	/**
	 * 通过分享创建账户
	 * @return
	 * @throws ServiceException
	 */
	public String createAccountByShare() throws ServiceException{
		
		JSONObject json = new JSONObject();
		boolean flag = false;
		String message = "";
//		String initPassword ="";
		
		if(!ValidateUtil.isMobile(mobile)){
			
			message ="电话号码填写不正确！";
		}else{
			try {
				DepartFromPort departFromPort = departFromPortService.queryByFlightNoAndDate(flightNumber, flightDate, versionFlag);
				Member member = memberService.generateByMobileAndSendSms(mobile, false);
				if(departFromPort != null){
					
					CheckinInfo checkinInfo = checkinInfoService.queryByMember(member,flightNumber,flightDate);
					if(checkinInfo == null){
						
						//将该用户的值机信息添加到用户下 方便用户查看
						checkinInfo = checkinInfoService.persist(member, flightNumber, flightDate, mobile, ticketNo, seatNo, couponId, sendMobile);
						
						//推送站内信
						String messageContent = systemDictionaryService.getDescriptByName("checkinSuccess");
						if(ValidateUtil.isNotNull(messageContent)){
							
							messageContent = MessageFormat.format(messageContent, flightNumber, member.getPhone(), seatNo);
							memberSendInfoService.persist(member.getId(), PushMethod.app, "值机信息分享成功！", message, "airportm_myMessage.action");
						}
					}
					checkinInfo.setByShare("1");
					checkinInfoService.merge(checkinInfo);
				}
				message = "值机信息分享成功！";
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				message = e.getMessage();
			}
		}
		json.put("flag", flag);
		json.put("message", message);
		data = new AppJson(null, JSONArray.fromObject(json), 1);
		return JSON;
	}
	
	/**
	 * 撤销值机信息分享
	 * @return
	 * @throws ServiceException
	 */
	public String cancelShareCheckin() throws ServiceException{
		JSONObject json = new JSONObject();
		Member member = memberService.queryByPhone(mobile, versionFlag);
		if(member != null){
			CheckinInfo checkinInfo = checkinInfoService.queryByMember(member, flightNumber, flightDate);
			if(checkinInfo != null){
				checkinInfoService.remove(checkinInfo);
			}
			MemberFocusFlight memberFocusFlight = memberFocusFlightService.query(member.getId(), flightNumber, flightDate);
			if(memberFocusFlight != null){
				memberFocusFlightService.remove(memberFocusFlight);
			}
			if(GeneralUtil.isNull(member.getLoginCount())){
				memberService.remove(member);
			}
			json.put("flag", true);
			json.put("message", "撤销成功！");
		}else{
			json.put("flag", false);
			json.put("message", "用户不存在！");
		}
//		AjaxUtil.writeAppJson(response, request, null, JSONArray.fromObject(json).toString(), 1, true);
		data = new AppJson(null, JSONArray.fromObject(json).toString(), 1);
		return JSON;
	}
	
	/**
	 * 系统反馈信息接口
	 * @return
	 */
	public String saveSysFreebackInfo() throws ServiceException{
		
		data = appService.saveSysFreebackInfo(name,mobile,url,content);
		return TEXT;
	}
	
	//首页免责声明接口
	public String getDisclaimerContent() throws ServiceException{
		JSONObject json = new JSONObject();
		SystemDictionary sys = systemDictionaryService.getByValue("disclaimer");
		if(sys != null){
			json.put("status", "1");
			json.put("content", sys.getDescription());
		}else{
			json.put("status", "0");
			json.put("content", "获取数据失败！");
		}
		data = json.toString();
		return TEXT;
	}
	
	/**
	 * 发送短信测试接口
	 * @return
	 */
	public String sendSMS(){
		
		if(GeneralUtil.isNull(smsContent)){
			
			smsContent = "默认短信测试内容";
		}
		try {
			SmsUtil.sendSms(mobile, smsContent);
			data = new AppJson(null, "发送成功", 1);
		} catch (ServiceException e) {
			e.printStackTrace();
			data = new AppJson(null, e.getMessage(), 1);
		}
		return JSON;
	}
	
	/**
	 * 获取字典值
	 * @return
	 */
	public String getDictionary(){
		
		com.alibaba.fastjson.JSONObject json = new com.alibaba.fastjson.JSONObject();
		
		SystemDictionary dictionary = systemDictionaryService.getByName(name);
		if(dictionary != null){
			
			json.put("name", dictionary.getName());
			json.put("value", dictionary.getValue());
			json.put("description", dictionary.getDescription());
		}
		data = json.toJSONString();
		return TEXT;
	}
	
	public String getNewsClass_id() {
		return newsClass_id;
	}

	public void setNewsClass_id(String newsClassId) {
		newsClass_id = newsClassId;
	}
	public String getNews_id() {
		return news_id;
	}
	public void setNews_id(String newsId) {
		news_id = newsId;
	}
	public String getClassAlias() {
		return classAlias;
	}
	public void setClassAlias(String classAlias) {
		this.classAlias = classAlias;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String memberId) {
		member_id = memberId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Integer getStartSize() {
		return startSize;
	}
	public void setStartSize(Integer startSize) {
		this.startSize = startSize;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getFlightDate() {
		return flightDate;
	}
	public void setFlightDate(String flightDate) {
		this.flightDate = flightDate;
	}
	public String getFlightState() {
		return flightState;
	}
	public void setFlightState(String flightState) {
		this.flightState = flightState;
	}
	public String getMemberRole() {
		return memberRole;
	}
	public void setMemberRole(String memberRole) {
		this.memberRole = memberRole;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPositionAlias() {
		return positionAlias;
	}

	public void setPositionAlias(String positionAlias) {
		this.positionAlias = positionAlias;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public String getSetoutCity() {
		return setoutCity;
	}

	public void setSetoutCity(String setoutCity) {
		this.setoutCity = setoutCity;
	}

	public String getReachCity() {
		return reachCity;
	}

	public void setReachCity(String reachCity) {
		this.reachCity = reachCity;
	}

	public IAppService getAppService() {
		return appService;
	}

	public void setAppService(IAppService appService) {
		this.appService = appService;
	}

	public INewsClassService getNewsClassService() {
		return newsClassService;
	}

	public void setNewsClassService(INewsClassService newsClassService) {
		this.newsClassService = newsClassService;
	}

	public INewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(INewsService newsService) {
		this.newsService = newsService;
	}

	public ICommonSearchService getCommonSearchService() {
		return commonSearchService;
	}

	public void setCommonSearchService(ICommonSearchService commonSearchService) {
		this.commonSearchService = commonSearchService;
	}

	public ISystemConfigService getSystemConfigService() {
		return systemConfigService;
	}

	public void setSystemConfigService(ISystemConfigService systemConfigService) {
		this.systemConfigService = systemConfigService;
	}

	public IInfoPositionService getInfoPositionService() {
		return infoPositionService;
	}

	public void setInfoPositionService(IInfoPositionService infoPositionService) {
		this.infoPositionService = infoPositionService;
	}

	public ICheckinInfoService getCheckinInfoService() {
		return checkinInfoService;
	}

	public void setCheckinInfoService(ICheckinInfoService checkinInfoService) {
		this.checkinInfoService = checkinInfoService;
	}

	public IMemberFocusFlightService getMemberFocusFlightService() {
		return memberFocusFlightService;
	}

	public void setMemberFocusFlightService(
			IMemberFocusFlightService memberFocusFlightService) {
		this.memberFocusFlightService = memberFocusFlightService;
	}

	public String getTwoCode() {
		return twoCode;
	}

	public void setTwoCode(String twoCode) {
		this.twoCode = twoCode;
	}

	public String getSendMobile() {
		return sendMobile;
	}

	public void setSendMobile(String sendMobile) {
		this.sendMobile = sendMobile;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDeptAirportCode() {
		return deptAirportCode;
	}

	public void setDeptAirportCode(String deptAirportCode) {
		this.deptAirportCode = deptAirportCode;
	}

	public String getDestAirportCode() {
		return destAirportCode;
	}

	public void setDestAirportCode(String destAirportCode) {
		this.destAirportCode = destAirportCode;
	}
	
}
