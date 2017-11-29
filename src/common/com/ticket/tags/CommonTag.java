package com.ticket.tags;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.constants.ContextConstants;
import com.ticket.pojo.Advert;
import com.ticket.pojo.AdvertFlight;
import com.ticket.pojo.AirportBusinessType;
import com.ticket.pojo.AirportInfo;
import com.ticket.pojo.AirportPlan;
import com.ticket.pojo.ArrivalAtPort;
import com.ticket.pojo.BindYouZan;
import com.ticket.pojo.BjdjHall;
import com.ticket.pojo.BjdjOrder;
import com.ticket.pojo.BjdjPage;
import com.ticket.pojo.BjdjServiceCode;
import com.ticket.pojo.BjdjServicePackage;
import com.ticket.pojo.BusinessEvent;
import com.ticket.pojo.BusinessEventBind;
import com.ticket.pojo.BusinessInfo;
import com.ticket.pojo.ChannelCustomerInfo;
import com.ticket.pojo.ChannelType;
import com.ticket.pojo.CityHistorySelect;
import com.ticket.pojo.CommonTraveller;
import com.ticket.pojo.CommonTravellerCard;
import com.ticket.pojo.DepartFromPort;
import com.ticket.pojo.DepartmentInfo;
import com.ticket.pojo.FeedbackReply;
import com.ticket.pojo.FlightCompany;
import com.ticket.pojo.FriendlyLink;
import com.ticket.pojo.HotLinePhone;
import com.ticket.pojo.InfoPosition;
import com.ticket.pojo.Member;
import com.ticket.pojo.MemberAnnouncement;
import com.ticket.pojo.MemberFavorite;
import com.ticket.pojo.MemberFocusFlight;
import com.ticket.pojo.MemberSendInfo;
import com.ticket.pojo.News;
import com.ticket.pojo.NewsClass;
import com.ticket.pojo.QuickMenu;
import com.ticket.pojo.QuickMenuMemberSetting;
import com.ticket.pojo.RechargeRecord;
import com.ticket.pojo.Regulation;
import com.ticket.pojo.RegulationType;
import com.ticket.pojo.ScenicSpots;
import com.ticket.pojo.SystemDictionary;
import com.ticket.pojo.SystemModule;
import com.ticket.pojo.TrafficLine;
import com.ticket.pojo.TrafficLineAndStation;
import com.ticket.pojo.TrafficStation;
import com.ticket.pojo.TrafficType;
import com.ticket.pojo.VpnBroken;
import com.ticket.pojo.YouZanGoodsInfo;
import com.ticket.service.IAdvertFlightService;
import com.ticket.service.IAdvertService;
import com.ticket.service.IAirportBusinessTypeService;
import com.ticket.service.IAirportInfoService;
import com.ticket.service.IArrivalAtPortService;
import com.ticket.service.IBindYouZanService;
import com.ticket.service.IBjdjHallService;
import com.ticket.service.IBjdjOrderService;
import com.ticket.service.IBjdjPageService;
import com.ticket.service.IBjdjServiceCodeService;
import com.ticket.service.IBjdjServiceItemService;
import com.ticket.service.IBjdjServicePackageItemService;
import com.ticket.service.IBjdjServicePackageService;
import com.ticket.service.IBusinessEventBindService;
import com.ticket.service.IBusinessEventService;
import com.ticket.service.IBusinessInfoService;
import com.ticket.service.IChannelCustomerInfoService;
import com.ticket.service.IChannelTypeService;
import com.ticket.service.ICheckinInfoService;
import com.ticket.service.ICityHistorySelectService;
import com.ticket.service.ICommonTravellerCardService;
import com.ticket.service.ICommonTravellerService;
import com.ticket.service.IDepartFromPortService;
import com.ticket.service.IDepartmentInfoService;
import com.ticket.service.IEmployeeOutVisitService;
import com.ticket.service.IEvaluationService;
import com.ticket.service.IFeedbackReplyService;
import com.ticket.service.IFeedbackService;
import com.ticket.service.IFlightCompanyService;
import com.ticket.service.IFriendlyLinkService;
import com.ticket.service.IHotLinePhoneService;
import com.ticket.service.IInDataService;
import com.ticket.service.IInfoPositionService;
import com.ticket.service.IMemberAnnouncementService;
import com.ticket.service.IMemberFavoriteService;
import com.ticket.service.IMemberFocusFlightService;
import com.ticket.service.IMemberSendInfoService;
import com.ticket.service.IMemberService;
import com.ticket.service.IMessageReminderService;
import com.ticket.service.INewsClassService;
import com.ticket.service.INewsService;
import com.ticket.service.IOrderInfoService;
import com.ticket.service.IPhoneVisitService;
import com.ticket.service.IQuickMenuMemberSettingService;
import com.ticket.service.IQuickMenuService;
import com.ticket.service.IRechargeRecordService;
import com.ticket.service.IRegulationService;
import com.ticket.service.IRegulationTypeService;
import com.ticket.service.IScenicSpotsService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.service.ISystemModuleService;
import com.ticket.service.ITrafficLineAndStationService;
import com.ticket.service.ITrafficLineService;
import com.ticket.service.ITrafficStationService;
import com.ticket.service.ITrafficTypeService;
import com.ticket.service.IVpnBrokenService;
import com.ticket.service.IYouZanGoodsInfoService;
import com.ticket.util.AirportPlaneUtil;
import com.ticket.util.CookiesUtil;
import com.ticket.util.DateUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.MD5Util;
import com.ticket.util.PinyinUtil;
import com.ticket.util.SpringUtil;
import com.ticket.util.WebUtil;

/**
 * 共用标签
 * 
 * @ClassName: CommonTag
 * @Description: 对于前后台都会用到的一些功能.
 * @author HiSay
 * @date Oct 10, 2013 22:24:17 PM
 * 
 */
public class CommonTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	private String versionFlag = ContextConstants.VERSION_FLAG_OF_CHINESE; // 语言版本标识,默认为中文版.
	private String type = null; // 自定义标签业务分类,
	// 用type属性来区分自定义标签处理的不同的业务方法.默认为获取constants.properties里面的value值
	private Integer size = 1; // 当获取列表的时候. 这个sidomHotAirportListze表示获取的数量.默认取1条.
	private String value = null; // 辅助传值进来.
	private Date flightDate = null;

	private String defaultImage = null; // 默认图片
	private Integer width = null; // 图片的宽度

	private News news = null;
	//提醒消息实体
	private MemberSendInfo msg = null;
	//变量名
	private String var;
	private Date startDate;
	private Date endDate;
	private String cTypeId;
	private String member_id;//会员id
	private String flightState;//航班状态
	private String stopover;//是否中转航班
	private String wz;
	private String order;

	// 新闻类别业务层
	private static final INewsClassService newsClassService = (INewsClassService) SpringUtil.getObjectFromSpring("newsClassService");
	// 新闻业务层
	private static final INewsService newsService = (INewsService) SpringUtil.getObjectFromSpring("newsService");
	// 航空公司业务层
	private static final IFlightCompanyService flightCompanyService = (IFlightCompanyService) SpringUtil.getObjectFromSpring("flightCompanyService");
	// 设施位置业务层
	private static final IInfoPositionService infoPositionService = (IInfoPositionService) SpringUtil.getObjectFromSpring("infoPositionService");
	// 广告信息业务层
	private static final IAdvertService advertService = (IAdvertService) SpringUtil.getObjectFromSpring("advertService");
	// 系统字典
	private static final ISystemDictionaryService systemDictionaryService = (ISystemDictionaryService) SpringUtil.getObjectFromSpring("systemDictionaryService");
	// 快捷菜单
	private static final IQuickMenuService quickMenuService = (IQuickMenuService) SpringUtil.getObjectFromSpring("quickMenuService");
	// 会员设置快捷菜单
	private static final IQuickMenuMemberSettingService quickMenuMemberSettingService = (IQuickMenuMemberSettingService) SpringUtil.getObjectFromSpring("quickMenuMemberSettingService");
	// 会员收藏的业务层
	private static final IMemberFavoriteService memberFavoriteService = (IMemberFavoriteService) SpringUtil.getObjectFromSpring("memberFavoriteService");
	// 机场商业类别的业务层
	private static final IAirportBusinessTypeService airportBusinessTypeService = (IAirportBusinessTypeService) SpringUtil.getObjectFromSpring("airportBusinessTypeService");
	// 机场商家的业务层
	private static final IBusinessInfoService businessInfoService = (IBusinessInfoService) SpringUtil.getObjectFromSpring("businessInfoService");
	// 机场商家友情链接的业务层
	private static final IFriendlyLinkService friendlyLinkService = (IFriendlyLinkService) SpringUtil.getObjectFromSpring("friendlyLinkService");
	// 消息提醒的业务层
	private static final IMessageReminderService messageReminderService = (IMessageReminderService) SpringUtil.getObjectFromSpring("messageReminderService");
	// 交通路线类别业务层
	private static final ITrafficTypeService trafficTypeService = (ITrafficTypeService) SpringUtil.getObjectFromSpring("trafficTypeService");
	// 交通路线业务层
	private static final ITrafficLineService trafficLineService = (ITrafficLineService) SpringUtil.getObjectFromSpring("trafficLineService");
	// 交通站点业务层
	private static final ITrafficStationService trafficStationService = (ITrafficStationService) SpringUtil.getObjectFromSpring("trafficStationService");
	// 交通路线关联站点业务层
	private static final ITrafficLineAndStationService trafficLineAndStationService = (ITrafficLineAndStationService) SpringUtil.getObjectFromSpring("trafficLineAndStationService");
	// 机场热线业务层
	private static final IHotLinePhoneService hotLinePhoneService = (IHotLinePhoneService) SpringUtil.getObjectFromSpring("hotLinePhoneService");
	// 会员关注航班业务层
	private static final IMemberFocusFlightService memberFocusFlightService = (IMemberFocusFlightService) SpringUtil.getObjectFromSpring("memberFocusFlightService");
	// 离港航班业务层
	private static final IDepartFromPortService departFromPortService = (IDepartFromPortService) SpringUtil.getObjectFromSpring("departFromPortService");
	// 进港航班业务层
	private static final IArrivalAtPortService arrivalAtPortService = (IArrivalAtPortService) SpringUtil.getObjectFromSpring("arrivalAtPortService");
	//机场制度类别业务层
	private static final IRegulationTypeService regulationTypeService = (IRegulationTypeService) SpringUtil.getObjectFromSpring("regulationTypeService");
	//机场制度业务层
	private static final IRegulationService regulationService = (IRegulationService) SpringUtil.getObjectFromSpring("regulationService");
	//历史选择城市业务层
	private static final ICityHistorySelectService cityHistorySelectService = (ICityHistorySelectService) SpringUtil.getObjectFromSpring("cityHistorySelectService");
	//会员业务层
	private static final IMemberService memberService = (IMemberService) SpringUtil.getObjectFromSpring("memberService");
	//评价业务层
	private static final IEvaluationService evaluationService = (IEvaluationService) SpringUtil.getObjectFromSpring("evaluationService");
	//消息业务层
	private static final IMemberSendInfoService sendInfoService = (IMemberSendInfoService) SpringUtil.getObjectFromSpring("memberSendInfoService");
	//服务大厅
	private static final IBjdjHallService hallService = (IBjdjHallService) SpringUtil.getObjectFromSpring("bjdjHallService");
	//机场信息业务层
	private static final IAirportInfoService airportInfoService =  (IAirportInfoService) SpringUtil.getObjectFromSpring("airportInfoService");
	//计划航班的业务层
	private static final IInDataService airportPlanService =  (IInDataService) SpringUtil.getObjectFromSpring("inDataService");
	//系统模块的业务层
	private static final ISystemModuleService systemModuleService = (ISystemModuleService) SpringUtil.getObjectFromSpring("systemModuleService");
	//部门信息的业务层
	private static final IDepartmentInfoService departmentInfoService = (IDepartmentInfoService) SpringUtil.getObjectFromSpring("departmentInfoService");
	//
	private static final IChannelTypeService channelTypeService = (IChannelTypeService) SpringUtil.getObjectFromSpring("channelTypeService");
	private static final IChannelCustomerInfoService channelCustomerInfoService = (IChannelCustomerInfoService) SpringUtil.getObjectFromSpring("channelCustomerInfoService");
	private static final IRechargeRecordService rechargeRecordService = (IRechargeRecordService) SpringUtil.getObjectFromSpring("rechargeRecordService");
	private static final IOrderInfoService orderInfoService = (IOrderInfoService) SpringUtil.getObjectFromSpring("orderInfoService");
	private static final IEmployeeOutVisitService employeeOutVisitService = (IEmployeeOutVisitService) SpringUtil.getObjectFromSpring("employeeOutVisitService");
	private static final IPhoneVisitService phoneVisitService = (IPhoneVisitService) SpringUtil.getObjectFromSpring("phoneVisitService");
	private static final ICheckinInfoService checkinInfoService = (ICheckinInfoService) SpringUtil.getObjectFromSpring("checkinInfoService");
	private static final IVpnBrokenService vpnBrokenService = (IVpnBrokenService) SpringUtil.getObjectFromSpring("vpnBrokenService");
	
	private IBjdjServiceItemService serviceItemService = (IBjdjServiceItemService) SpringUtil.getObjectFromSpring("bjdjServiceItemService");
	private IBjdjServicePackageService servicePackageService = (IBjdjServicePackageService) SpringUtil.getObjectFromSpring("bjdjServicePackageService");
	private IBjdjServicePackageItemService packageItemService = (IBjdjServicePackageItemService) SpringUtil.getObjectFromSpring("bjdjServicePackageItemService");
	private IBjdjPageService bjdjPageService = (IBjdjPageService)SpringUtil.getObjectFromSpring("bjdjPageService");
	private IBjdjOrderService bjdjOrderService = (IBjdjOrderService)SpringUtil.getObjectFromSpring("bjdjOrderService");
	private IFeedbackReplyService feedbackReplyService = (IFeedbackReplyService)SpringUtil.getObjectFromSpring("feedbackReplyService");
	private IBjdjServiceCodeService bjdjServiceCodeService = (IBjdjServiceCodeService)SpringUtil.getObjectFromSpring("bjdjServiceCodeService");
	private IMemberAnnouncementService memberAnnouncementService = (IMemberAnnouncementService) SpringUtil.getObjectFromSpring("memberAnnouncementService");
	private IAdvertFlightService advertFlightService = (IAdvertFlightService)SpringUtil.getObjectFromSpring("advertFlightService");
	private IScenicSpotsService scenicSpotsService = (IScenicSpotsService) SpringUtil.getObjectFromSpring("scenicSpotsService");
	private IFeedbackService feedbackService = (IFeedbackService)SpringUtil.getObjectFromSpring("feedbackService");
	private IBusinessEventService businessEventService = (IBusinessEventService)SpringUtil.getObjectFromSpring("businessEventService");
	private IBusinessEventBindService businessEventBindService = (IBusinessEventBindService)SpringUtil.getObjectFromSpring("businessEventBindService");
	private ICommonTravellerService commonTravellerService = (ICommonTravellerService)SpringUtil.getObjectFromSpring("commonTravellerService");
	private ICommonTravellerCardService commonTravellerCardService = (ICommonTravellerCardService)SpringUtil.getObjectFromSpring("commonTravellerCardService");
	private IYouZanGoodsInfoService youZanGoodsInfoService = (IYouZanGoodsInfoService)SpringUtil.getObjectFromSpring("youZanGoodsInfoService");
	private IBindYouZanService bindYouZanService = (IBindYouZanService)SpringUtil.getObjectFromSpring("bindYouZanService");
	@Override
	public int doAfterBody() throws JspException {
		return super.doAfterBody();
	}

	@Override
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			if ("imageNothing".equals(type)) { // 处理消失的图片
				if (value == null || "".equals(value)) {
					if (width != null) {
						out.write("<img width=\"" + width + "\" src=\""
								+ defaultImage + "\" />");
					} else {
						out.write("<img src=\"" + defaultImage + "\" />");
					}
				} else {
					if (width != null) {
						out.write("<img width=\"" + width + "\" src=\"" + value
								+ "\" />");
					} else {
						out.write("<img src=\"" + value + "\" />");
					}
				}
			} else if ("memberHasSetList".equals(type)) { // 会员快捷菜单列表
				List<QuickMenuMemberSetting> memberDefaultMenuList = quickMenuMemberSettingService
				.queryListByMemberHasSet(value, versionFlag);
				if (memberDefaultMenuList != null && !memberDefaultMenuList.isEmpty()) {
					request.setAttribute(type, memberDefaultMenuList);
				} else {
					request.removeAttribute(type);
				}
			} else if ("memberHasSetServiceList".equals(type)) { // 会员快捷菜单列表
				String[] valueSplit = value.split("&");
				value= valueSplit[0];
				String flightQuickMenu = valueSplit[1];
				List<QuickMenuMemberSetting> memberDefaultMenuList = quickMenuMemberSettingService
				.queryServiceListByMemberHasSet(value,flightQuickMenu, versionFlag);
				if (memberDefaultMenuList != null && !memberDefaultMenuList.isEmpty()) {
					request.setAttribute(type, memberDefaultMenuList);
				} else {
					request.removeAttribute(type);
				}
			} else if ("memberHasSetListByService".equals(type)) { // 会员快捷菜单列表
				List<QuickMenuMemberSetting> memberDefaultMenuList = quickMenuMemberSettingService.queryListByServiceMenu(value, versionFlag);
				if (memberDefaultMenuList != null && !memberDefaultMenuList.isEmpty()) {
					request.setAttribute(type, memberDefaultMenuList);
				} else {
					request.removeAttribute(type);
				}
			} 	
			else if ("decoder".equals(type)) {// 把加密的密码解密
				if (value != null) {
					out.write(MD5Util.decryptString(value));
				}
			} else if ("newsClassObj".equals(type)) {// 获取新闻类别实体
				NewsClass nc = newsClassService.queryById(NewsClass.class
						.getSimpleName(), value);
				if (nc != null) {
					request.setAttribute(type, nc);
				} else {
					request.removeAttribute(type);
				}
			} else if ("newsClassChilds".equals(type)) {// 获取新闻类别实体列表
				NewsClass nc = newsClassService
				.queryByAlias(versionFlag, value);
				if (nc != null) {
					request.setAttribute("newsClassObj", nc);
					List<NewsClass> childsList = newsClassService
					.queryChildNewsClasssByParent(nc.getId());
					if (childsList != null && !childsList.isEmpty()) {
						request.setAttribute(type, childsList);
					} else {
						request.removeAttribute(type);
					}
				} else {
					request.removeAttribute(type);
					request.removeAttribute("newsClassObj");
				}
			} else if ("newsObj".equals(type)) { // 根据新闻类别查找新闻（一篇）
				News news = newsService.querySingleByNewsClassId(value,
						versionFlag);
				if (news != null) {
					request.setAttribute(type, news);
				} else {
					request.removeAttribute(type);
				}
			} else if ("locationList".equals(type)) { // 根据信息id查找位置列表
				List<InfoPosition> positionList = infoPositionService
				.queryListByNewsId(value, versionFlag);
				if (positionList != null && !positionList.isEmpty()) {
					request.setAttribute(type, positionList);
				} else {
					request.removeAttribute(type);
				}
			} else if("locationListById".equals(type)){
				InfoPosition ip = infoPositionService.queryById(InfoPosition.class.getSimpleName(), value);
				if(ip != null){
					InfoPosition position = infoPositionService.queryByAlias(ip.getPositionAlias());
					String longitudes = position.getLongitude();
					String latitudes = position.getLatitude();
					String floorNumbers = position.getFloorNumber();
					String names = position.getClassName();
					String[] longitudesArr = longitudes.split(",");
					String[] latitudesArr = latitudes.split(",");
					String[] floorNumbersArr = floorNumbers.split(",");
					String[] nameArr = names.split(",");
					List<InfoPosition> list = new ArrayList<InfoPosition>();
					if(longitudesArr.length>0){
						for(int i = 0; i < longitudesArr.length; i++){
							InfoPosition infoPosition = new InfoPosition();
							infoPosition.setLongitude(longitudesArr[i]);
							infoPosition.setLatitude(latitudesArr[i]);
							infoPosition.setName(nameArr[i]);
							infoPosition.setFloorNumber(floorNumbersArr[i]);
							list.add(infoPosition);
						}
					}
					if(list != null && !list.isEmpty()){
						request.setAttribute(type, list);
					}else{
						request.removeAttribute(type);
					}
				}
				
			}else if ("newsList".equals(type)) { // 根据新闻类别获取信息列表
				List<News> newsList = newsService.queryListByFront(value, 0,
						size);
				if (newsList != null && !newsList.isEmpty()) {
					request.setAttribute(type, newsList);
				} else {
					request.removeAttribute(type);
				}
			} else if ("newsListByNewsClassAlias".equals(type)) { // 根据新闻类别别名查询新闻列表
				NewsClass nc = newsClassService.queryByAlias(versionFlag, value);
				if (nc != null) {
					request.setAttribute("newsClassObj", nc);
					List<News> newsList = newsService.queryListByFront(nc
							.getId(), 0, size);
					if (newsList != null && !newsList.isEmpty()) {
						request.setAttribute(type, newsList);
					} else {
						request.removeAttribute(type);
					}
				} else {
					request.removeAttribute(type);
					request.removeAttribute("newsClassObj");
				}
			} else if ("advertList".equals(type)) { // 查询广告列表
				List<Advert> advertList = advertService.queryList(versionFlag);
				if (advertList != null && !advertList.isEmpty()) {
					request.setAttribute(type, advertList);
				} else {
					request.removeAttribute(type);
				}
			} else if ("advertListNew".equals(type)) { // 根据广告类型查询广告列表
				List<Advert> advertList = advertService.queryList(versionFlag,value, size);
				if (advertList != null && !advertList.isEmpty()) {
					request.setAttribute(type, advertList);
				} else {
					request.removeAttribute(type);
				}
			} else if ("prevNews".equals(type)) { // 获取上一篇信息
				News prevNews = newsService.queryPrevNews(news);
				if (prevNews != null) {
					request.setAttribute(type, prevNews);
				} else {
					request.removeAttribute(type);
				}
			} else if ("nextNews".equals(type)) { // 获取下一篇信息
				News nextNews = newsService.queryNextNews(news);
				if (nextNews != null) {
					request.setAttribute(type, nextNews);
				} else {
					request.removeAttribute(type);
				}
			} else if ("servicePhone".equals(type)) {  //服务电话
				String phone = systemDictionaryService
				.getValueByName("service_phone");
				if (GeneralUtil.isNotNull(phone)) {
					request.setAttribute(type, phone);
				} else {
					request.removeAttribute(type);
				}
			} else if ("sysDicObj".equals(type)) {
				SystemDictionary sd = systemDictionaryService.queryById(
						SystemDictionary.class.getSimpleName(), value);
				if (sd != null) {
					request.setAttribute(type, sd);
				} else {
					request.removeAttribute(type);
				}
			} else if ("newsCommonObj".equals(type)) { // 根据id查询新闻实体
				News news = newsService.queryById(News.class.getSimpleName(),
						value);
				if (news != null) {
					request.setAttribute(type, news);
				} else {
					request.removeAttribute(type);
				}
			} else if ("flightCompanyList".equals(type)) { // 查询航空公司列表
				List<FlightCompany> flightCompanyList = flightCompanyService.queryList(null,versionFlag);
				if (flightCompanyList != null && !flightCompanyList.isEmpty()) {
					request.setAttribute(type, flightCompanyList);
				} else {
					request.removeAttribute(type);
				}
			} else if ("flightCompanyObj".equals(type)) { // 查询航空公司信息
				FlightCompany flightCompany = flightCompanyService.queryById(
						FlightCompany.class.getSimpleName(), value);
				if (flightCompany != null) {
					request.setAttribute(type, flightCompany);
				} else {
					request.removeAttribute(type);
				}
			} else if ("quickMenuList".equals(type)) { // 查询快捷菜单分类
				List<SystemDictionary> sysDicList = systemDictionaryService
				.querySubByParentValue("quick_menu");
				if (sysDicList != null && !sysDicList.isEmpty()) {
					request.setAttribute(type, sysDicList);
				} else {
					request.removeAttribute(type);
				}
			} else if ("menuList".equals(type)) { // 根据菜单类别查询菜单列表
				List<QuickMenu> quickMenuList = quickMenuService.queryByMenuId(value, versionFlag);
				if (quickMenuList != null && !quickMenuList.isEmpty()) {
					request.setAttribute(type, quickMenuList);
				} else {
					request.removeAttribute(type);
				}
			} else if ("serviceMenuList".equals(type)) { // 根据菜单类别和位置查询菜单列表
				String[] split = value.split("&");
				value = split[0];
				String position = split[1];
//				List<QuickMenu> quickMenuList = quickMenuService.queryByIdAndPosition(value,position,versionFlag);
				List<QuickMenu> quickMenuList = quickMenuService.queryByMenuId(value, versionFlag);
				if (quickMenuList != null && !quickMenuList.isEmpty()) {
					request.setAttribute(type, quickMenuList);
				} else {
					request.removeAttribute(type);
				}
			} else if ("visiorMenuList".equals(type)) { // 访客快捷菜单列表
				List<QuickMenu> visitorMenuList = quickMenuService.queryQuickMenuListByVisitor(value, versionFlag);
				int maxCount = 8;
				int unUsedList = 8;
				if (visitorMenuList != null && !visitorMenuList.isEmpty()) {
					request.setAttribute(type, visitorMenuList);
					unUsedList = maxCount - visitorMenuList.size();
				} else {
					request.removeAttribute(type);
				}
				List<String> defaultList = new ArrayList<String>();
				for (int i = 0; i < unUsedList; i++) {
					defaultList.add(i + "");
				}
				if (!defaultList.isEmpty()) {
					request.setAttribute("defaultList", defaultList);
				} else {
					request.removeAttribute("defaultList");
				}
			}  else if ("memberMenuList".equals(type)) { // 会员快捷菜单列表
				int maxCount = 8;
				int unUsedList = 8;
				List<QuickMenuMemberSetting> memberDefaultMenuList = quickMenuMemberSettingService.queryListByMemberIdAndSet(value, versionFlag);
				if (memberDefaultMenuList != null && !memberDefaultMenuList.isEmpty()) {
					request.setAttribute(type, memberDefaultMenuList);
					unUsedList = maxCount - memberDefaultMenuList.size();
				} else {
					request.removeAttribute(type);
				}
				List<String> defaultList = new ArrayList<String>();
				for (int i = 0; i < unUsedList; i++) {
					defaultList.add(i + "");
				}
				if (!defaultList.isEmpty()) {
					request.setAttribute("defaultList", defaultList);
				} else {
					request.removeAttribute("defaultList");
				}
			} else if ("quickMenuObj".equals(type)) { // 会员快捷菜单实体
				QuickMenu quickMenu = quickMenuService.queryById(
						QuickMenu.class.getSimpleName(), value);
				if (quickMenu != null) {
					request.setAttribute(type, quickMenu);
				} else {
					request.removeAttribute(type);
				}
			} else if ("memberQuickMenuObj".equals(type)) { // 会员快捷菜单实体
				QuickMenu quickMenu = quickMenuService.queryById(
						QuickMenu.class.getSimpleName(), value);
				if (quickMenu != null) {
					request.setAttribute(type, quickMenu);
				} else {
					request.removeAttribute(type);
				}
			} else if ("memberNoSetQuickMenuList".equals(type)) { // 会员未设置的快捷菜单空位
				List<QuickMenuMemberSetting> menuSize1 = quickMenuMemberSettingService.queryListByMemberIdAndSet(value, versionFlag);
				int unUsedList = 8;
				if (menuSize1 != null && !menuSize1.isEmpty()) {
					unUsedList = unUsedList - menuSize1.size();
				}
				List<String> defaultList = new ArrayList<String>();
				for (int i = 0; i < unUsedList; i++) {
					defaultList.add(i + "");
				}
				if (!defaultList.isEmpty()) {
					request.setAttribute(type, defaultList);
				} else {
					request.removeAttribute(type);
				}
			} else if ("memberFavoriteList".equals(type)) { // 查询会员收藏列表
				List<MemberFavorite> memberFavoriteList = memberFavoriteService.queryListByMember(versionFlag);
				if (memberFavoriteList != null && !memberFavoriteList.isEmpty()) {
					request.setAttribute(type, memberFavoriteList);
				} else {
					request.removeAttribute(type);
				}
			} else if ("memberSetQuickMenuList".equals(type)) { // 会员首页快捷菜单
				List<QuickMenuMemberSetting> frontMenuList = quickMenuMemberSettingService.queryQuickMenuListByFront(size,versionFlag);
				int unUsedList = 6;
				if (frontMenuList != null && !frontMenuList.isEmpty()) {
					request.setAttribute(type, frontMenuList);
					unUsedList = unUsedList - frontMenuList.size();
				} else {
					request.removeAttribute(type);
				}
				List<String> defaultList = new ArrayList<String>();
				for (int i = 0; i < unUsedList; i++) {
					defaultList.add(i + "");
				}
				if (!defaultList.isEmpty()) {
					request.setAttribute("defaultList", defaultList);
				} else {
					request.removeAttribute("defaultList");
				}
			} else if ("getChildNewsClassListByAlias".equals(type)) { // 根据新闻类别别名查询新闻列表
				NewsClass nc = newsClassService
				.queryByAlias(versionFlag, value);
				if (nc != null) {
					List<NewsClass> newsClassList = newsClassService
					.queryListByParentId(nc.getId(), versionFlag);
					if (newsClassList != null && !newsClassList.isEmpty()) {
						request.setAttribute(type, newsClassList);
					} else {
						request.removeAttribute(type);
					}
				} else {
					request.removeAttribute(type);
				}
			} else if ("kindlyReminder".equals(type)) { // 查询会员收藏列表
				Advert advert = advertService.queryByName(value, versionFlag);
				if (advert != null) {
					request.setAttribute(type, advert);
				} else {
					request.removeAttribute(type);
				}
			} else if ("businessTopTypeList".equals(type)) { // 查询一级商户类别
				List<AirportBusinessType> businessTopTypeList = airportBusinessTypeService
				.queryTopList(versionFlag);
				if (businessTopTypeList != null
						&& !businessTopTypeList.isEmpty()) {
					request.setAttribute(type, businessTopTypeList);
				} else {
					request.removeAttribute(type);
				}
			} else if ("businessSecondTypeList".equals(type)) { // 查询二级商户类别
				List<AirportBusinessType> businessSecondTypeList = airportBusinessTypeService
				.querySecondList(value, versionFlag);
				if (businessSecondTypeList != null
						&& !businessSecondTypeList.isEmpty()) {
					request.setAttribute(type, businessSecondTypeList);
				} else {
					request.removeAttribute(type);
				}
			} else if ("businessInfoListByType".equals(type)) { // 查询顶级类别下的商户列表
				List<AirportBusinessType> businessSecondTypeList = airportBusinessTypeService
				.querySecondList(value, versionFlag);
				List<BusinessInfo> businessInfoList = new ArrayList<BusinessInfo>();
				if (businessSecondTypeList != null
						&& !businessSecondTypeList.isEmpty()) {
					List<BusinessInfo> businessList = null;
					for (AirportBusinessType abt : businessSecondTypeList) {
						businessList = businessInfoService.queryListByTypeId(
								abt.getId(), versionFlag);
						if (businessList != null && !businessList.isEmpty()) {
							businessInfoList.addAll(businessList);
						}
					}
				}
				if (businessInfoList != null && !businessInfoList.isEmpty()) {
					request.setAttribute(type, businessInfoList);
				} else {
					request.removeAttribute(type);
				}
			} else if ("businessListByType".equals(type)) { // 根据类别查询商家列表
//				List<BusinessInfo> businessInfoList = businessInfoService
//				.queryListByTypeId(value, versionFlag);
				List<BusinessInfo> businessInfoList = businessInfoService.queryByTypeAndWzAndOrder(cTypeId, wz, order);
				if (businessInfoList != null && !businessInfoList.isEmpty()) {
					request.setAttribute(type, businessInfoList);
				} else {
					request.removeAttribute(type);
				}
			} else if("businessListByWz".equals(type)){//根据商家位置查询
				List<BusinessInfo> businessInfoList = businessInfoService.queryByWz(value);
				if(businessInfoList != null){
					request.setAttribute(type, businessInfoList);
				}else{
					request.removeAttribute(type);
				}
			} else if("businessListByOrder".equals(type)){//根据有赞销量排序
				List<BusinessInfo> businessInfoList = businessInfoService.queryByOrder(value);
				if(businessInfoList != null){
					request.setAttribute(type, businessInfoList);
				}else{
					request.removeAttribute(type);
				}
			} else if ("businessTypeObj".equals(type)) {//商家类别实体
				AirportBusinessType businessType = airportBusinessTypeService.queryById(AirportBusinessType.class.getSimpleName(), value);
				if (businessType != null) {
					request.setAttribute(type, businessType);
				} else {
					request.removeAttribute(type);
				}
			} else if ("myMessageList".equals(type)) { // 查询消息提醒列表
				List<MemberSendInfo> list = sendInfoService.queryMy();
				if (GeneralUtil.isNotNull(var)) {
					request.setAttribute(var, list);
				} else {
					request.setAttribute(type, list);
				}
			} else if ("messageDetail".equals(type)) { //消息提醒实体
				MemberSendInfo message = sendInfoService.get(MemberSendInfo.class, value);
				if (GeneralUtil.isNotNull(var)) {
					request.setAttribute(var, message);
				} else {
					request.setAttribute(type, message);
				}
			} else if ("prevMessage".equals(type)) { // 获取上一则提醒消息
				MemberSendInfo reminderMessage = sendInfoService.queryPrevMessage(msg);
				if (reminderMessage != null) {
					request.setAttribute(type, reminderMessage);
				} else {
					request.removeAttribute(type);
				}
			} else if ("nextMessage".equals(type)) { // 获取下一则提醒消息
				MemberSendInfo reminderMessage = sendInfoService.queryNextMessage(msg);
				if (reminderMessage != null) {
					request.setAttribute(type, reminderMessage);
				} else {
					request.removeAttribute(type);
				}
			} else if ("trafficTypeList".equals(type)) { //查询路线类别列表
				List<TrafficType> list = trafficTypeService .queryList(versionFlag);
				if (list != null && !list.isEmpty()) {
					request.setAttribute(type, list);
				} else {
					request.removeAttribute(type);
				}
			}else if ("trafficTypeListAll".equals(type)) {  //查询所有的路线类别
				List<TrafficType> list = trafficTypeService .queryListAll(versionFlag);
				if (list != null && !list.isEmpty()) {
					request.setAttribute(type, list);
				} else {
					request.removeAttribute(type);
				}
			}else if ("trafficTypeObj".equals(type)) {  //查询路线类别
				TrafficType trafficTypeObj = trafficTypeService.queryById(
						TrafficType.class.getSimpleName(), value);
				if (trafficTypeObj != null) {
					request.setAttribute(type, trafficTypeObj);
				} else {
					request.removeAttribute(type);
				}
			} else if ("trafficLineList".equals(type)) {  //查询路线列表
				List<TrafficLine> trafficLineList = trafficLineService
				.queryList(versionFlag);
				if (trafficLineList != null && !trafficLineList.isEmpty()) {
					request.setAttribute(type, trafficLineList);
				} else {
					request.removeAttribute(type);
				}
			} else if ("trafficLineObj".equals(type)) {  //查询路线实体
				TrafficLine trafficLineObj = trafficLineService.queryById(
						TrafficLine.class.getSimpleName(), value);
				if (trafficLineObj != null) {
					request.setAttribute(type, trafficLineObj);
				} else {
					request.removeAttribute(type);
				}
			} else if ("trafficLineListByTypeId".equals(type)) {  //根据路线类别查询路线列表
				List<TrafficLine> list = trafficLineService.queryListByTypeId(value, versionFlag);
				if (list != null && !list.isEmpty()) {
					request.setAttribute(type, list);
				} else {
					request.removeAttribute(type);
				}
			} else if ("trafficStationListByLineAndGo".equals(type)) {//查询去程路线的站点
				List<TrafficLineAndStation> list = trafficLineAndStationService.queryListByLineAndGo(value, versionFlag);
				if (list != null && !list.isEmpty()) {
					request.setAttribute(type, list);
				} else {
					request.removeAttribute(type);
				}
			} else if ("trafficStationListByLineAndBack".equals(type)) {  //查询回程路线的站点
				List<TrafficLineAndStation> list = trafficLineAndStationService.queryListByLineAndBack(value, versionFlag);
				if (list != null && !list.isEmpty()) {
					request.setAttribute(type, list);
				} else {
					request.removeAttribute(type);
				}
			} else if ("trafficStationObj".equals(type)) {  //查询站台信息
				TrafficStation trafficStation = trafficStationService.queryById(TrafficStation.class.getSimpleName(), value);
				if (trafficStation != null) {
					request.setAttribute(type, trafficStation);
				} else {
					request.removeAttribute(type);
				}
			} else if ("trafficTypeListByKeyword".equals(type)) {  //根据关键词查询路线类别
				List<TrafficType> list = trafficTypeService.queryListByKeyword(value, versionFlag);
				if (list != null && !list.isEmpty()) {
					request.setAttribute(type, list);
				} else {
					request.removeAttribute(type);
				}
			} else if ("businessLinkList".equals(type)) { //友情链接列表
				List<FriendlyLink> list = friendlyLinkService.queryList(value,
						versionFlag);
				if (list != null && !list.isEmpty()) {
					request.setAttribute(type, list);
				} else {
					request.removeAttribute(type);
				}
			} else if ("hotLineList".equals(type)) { //热线列表
				List<HotLinePhone> list = hotLinePhoneService.queryList(versionFlag);
				if (list != null && !list.isEmpty()) {
					request.setAttribute(type, list);
				} else {
					request.removeAttribute(type);
				}
			} else if("flightDelayNoticeObj".equals(type)){ //航班延误公告
				SystemDictionary obj = systemDictionaryService.getByValue("flightDelayNotice");
				  if(obj != null){
						request.setAttribute(type, obj);
				  }else{
						request.removeAttribute(type);
				  }
			} else if ("lostGoodsQueryPhoneList".equals(type)) { // 遗失物品查询电话列表
				List<SystemDictionary> sysDicList = systemDictionaryService.querySubByParentValue("lostGoodsQueryPhone");
				if (sysDicList != null && !sysDicList.isEmpty()) {
					request.setAttribute(type, sysDicList);
				} else {
					request.removeAttribute(type);
				}
			} else if("flightDelayLawsObj".equals(type)){ //航班延误法律法规
				SystemDictionary obj = systemDictionaryService.getByValue("flightDelayLaws");
				  if(obj != null){
						request.setAttribute(type, obj);
				  }else{
						request.removeAttribute(type);
				  }
			} else if("receiveNoticeObj".equals(type)){ //遗失物品领取须知
				SystemDictionary obj = systemDictionaryService.getByValue("receiveNotice");
				  if(obj != null){
						request.setAttribute(type, obj);
				  }else{
						request.removeAttribute(type);
				  }
			}  else if("flightDelayLawsAbstractObj".equals(type)){ //航班延误法律法规摘要
				SystemDictionary obj = systemDictionaryService.getByValue("flightDelayAbstractLaws");
				  if(obj != null){
						request.setAttribute(type, obj);
				  }else{
						request.removeAttribute(type);
				  }
			} else if("myFavoriteCount".equals(type)){  //个人收藏数量
				Long count = 0l;
				count = memberFavoriteService.queryCountByMember(versionFlag);
				if(count>=0){
					request.setAttribute(type, count);
				}else{
					request.removeAttribute(type);
				}
			} else if("myEvaluateCount".equals(type)){  //个人收藏数量
				Long count = 0l;
				count = evaluationService.myEvaluationCount();
				if(count>=0){
					request.setAttribute(type, count);
				}else{
					request.removeAttribute(type);
				}
			} else if("noReadMessageCount".equals(type)){ //未读消息数量
				Long count = sendInfoService.myNumberOfUnread();
				request.setAttribute(type, count);
			} else if("advertListByTypeName".equals(type)){  //未登录的个人中心广告图信息
				List<Advert> list = advertService.queryList(versionFlag, value, 5);
				if(list != null && !list.isEmpty()){
					request.setAttribute(type, list.get(0));
				}else{
					request.removeAttribute(type);
				}
			} else if("notReadMessageCount".equals(type)){  //统计未读消息数量
				Long count = 0l;
				count = messageReminderService.queryCountByNotAudit(versionFlag);
				if(count>=1){
					request.setAttribute(type, count);
				}else{
					request.removeAttribute(type);
				}
			} else if("cityTreeCodeList".equals(type)){ //查询城市三字码列表
				List<SystemDictionary> list = systemDictionaryService.querySubByParentValue("cityThreeCode");
				if(list != null && !list.isEmpty()){
					request.setAttribute(type, list);
				}else{
					request.removeAttribute(type);
				}
			} else if("memberFocusListByTransfer".equals(type)){ //查询会员关注航班列表
				List<MemberFocusFlight> list = memberFocusFlightService.queryTransferListByMember("transfer",versionFlag);
				if(list != null && !list.isEmpty()){
					request.setAttribute(type, list);
				}else{
					request.removeAttribute(type);
				}
			} else if("memberFocusFlightObj".equals(type)){ //查询会员关注航班列表
				MemberFocusFlight memberFocusFlight = memberFocusFlightService.queryByJudge(value, var, flightState,stopover, versionFlag);
				if(memberFocusFlight != null){
					request.setAttribute(type, memberFocusFlight);
				}else{
					request.removeAttribute(type);
				}
			}  else if("arrivalFlightInfo".equals(type)){  //根据航班编号和航班时间查询航班信息
				ArrivalAtPort arrivalAtPort = arrivalAtPortService.queryByFlightNoAndDate(value, var, versionFlag);
				if(arrivalAtPort != null){
					request.setAttribute(type, arrivalAtPort);
				}else{
					request.removeAttribute(type);
				}
			} else if("transferflightInfo".equals(type)){  //根据航班编号和航班时间查询航班信息
				ArrivalAtPort arrivalAtPort = arrivalAtPortService.queryTrasferByFlightNoAndDate(value, var, versionFlag);
				if(arrivalAtPort != null){
					request.setAttribute(type, arrivalAtPort);
				}else{
					request.removeAttribute("flightInfo");
				}
			}else if("systemDicObjByValue".equals(type)){ //根据值查询字典实体
				SystemDictionary obj = systemDictionaryService.getByValue(value);
				  if(obj != null){
						request.setAttribute(type, obj);
				  }else{
						request.removeAttribute(type);
				  }
			} else if("systemDicObjByName".equals(type)){ //根据名称查询字典实体
				SystemDictionary obj = systemDictionaryService.getByName(value);
				  if(obj != null){
						request.setAttribute(type, obj);
				  }else{
						request.removeAttribute(type);
				  }
			} else if("regulationTypeList".equals(type)){//查询机场制度类别列表
				List<RegulationType> list = regulationTypeService.queryList(versionFlag);
				if(list != null && !list.isEmpty()){
					request.setAttribute(type, list);
				}else{
					request.removeAttribute(type);
				}
				
			} else if("regulationListByType".equals(type)){ //根据制度类别查询机场制度列表
				List<Regulation> list = regulationService.queryListByType(value,versionFlag);
				if(list != null && !list.isEmpty()){
					request.setAttribute(type, list);
				}else{
					request.removeAttribute(type);
				}
			} else if("memberFocusFlightList".equals(type)){ //
				List<MemberFocusFlight> list = memberFocusFlightService.queryListByMember(versionFlag);
				if(list != null && !list.isEmpty()){
					request.setAttribute(type, list);
				}else{
					request.removeAttribute(type);
				}
			} else if("flightCompanyByTwoCode".equals(type)){ //根据二字码查询航空公司信息
				FlightCompany flightCompany = flightCompanyService.queryEntityByTwoCode(value,versionFlag);
				if(flightCompany != null){
					request.setAttribute(type, flightCompany);
				}else{
					request.removeAttribute(type);
				}
			} else if("systemDictionaryListByParentValue".equals(type)){//根据父节点值查询字典列表
				List<SystemDictionary> list = systemDictionaryService.querySubByParentName(value);
				if(list != null && !list.isEmpty()){
					request.setAttribute(type, list);
				}else{
					request.removeAttribute(type);
				}
			} else if("systemDictionaryListByParentValueOrder".equals(type)){//根据父节点值查询字典列表
				List<SystemDictionary> list = systemDictionaryService.querySubByParentValueOrderByLetter(value, "name");
				if(list != null && !list.isEmpty()){
					request.setAttribute(type, list);
				}else{
					request.removeAttribute(type);
				}
			} else if ("flightQuickMenuList".equals(type)) { //会员自定义航班菜单
				String[] valueSplit = value.split("&");
				value = valueSplit[0];
				String position = valueSplit[1];
				List<QuickMenuMemberSetting> list = quickMenuMemberSettingService.queryListByFlightMenu(value,size,position,versionFlag);
				if (list != null && !list.isEmpty()) {
					request.setAttribute(type, list);
				} else {
					request.removeAttribute(type);
				}
			} else if("historyCityList".equals(type)){//历史选择城市列表
				List<CityHistorySelect> list = cityHistorySelectService.queryListByMember(versionFlag);
				if(list != null && !list.isEmpty()){
					request.setAttribute(type, list);
				}else{
					request.removeAttribute(type);
				}
			} else if("airportInfoById".equals(type)){
				AirportInfo ap = airportInfoService.queryById(AirportInfo.class.getSimpleName(), value);
				if(ap != null){
					request.setAttribute(type, ap);
				}else{
					request.removeAttribute(type);
				}
			} else if("domHotAirportList".equals(type)){//国内热门城市列表
				List<AirportInfo> list = airportInfoService.queryDomHotAirport(versionFlag);
				if (list != null && !list.isEmpty()) {
					request.setAttribute(type, list);
				}else{
					request.removeAttribute(type);
				}
			}  else if("intHotAirportList".equals(type)){//国际热门城市列表
				List<AirportInfo> list = airportInfoService.queryIntHotAirport(versionFlag);
				if (list != null && !list.isEmpty()) {
					request.setAttribute(type, list);
				}else{
					request.removeAttribute(type);
				}
			} else if("cityCookie".equals(type)){ //未关注人员历史选择城市
				List<CityHistorySelect> list = new ArrayList<CityHistorySelect>();
        		Cookie[] cookies = request.getCookies();
        		if(cookies != null){
        			for(int i=0;i<cookies.length;i++){
        				Cookie cookie = cookies[i];
        				if(cookie.getName().equals("tourist")){
        					list = cityHistorySelectService.queryByTourist(cookie.getValue());
        				}
        			}
        		}
        		if(list != null && !list.isEmpty()){
        			request.setAttribute(type, list);
        		}else{
        			request.removeAttribute(type);
        		}
				
			} else if("leftCookie".equals(type)){ //左侧
				Cookie[] cookies = request.getCookies();
				if(cookies != null){
					for(int i=0;i<cookies.length;i++){
        				Cookie cookie = cookies[i];
        				if(cookie.getName().equalsIgnoreCase("leftPreUrl")){
        					request.setAttribute(type, cookie.getValue());
        				}else{
        					
        				}
        			}
				}else{
					request.removeAttribute(type);
				}
			} else if("positionUrl".equals(type)) { //位置导航链接
				InfoPosition infoPosition = infoPositionService.queryByAlias(value);
				if(infoPosition != null){
					if(WebUtil.validateSessionAttribute(request, "fromApp")) {//说明是从APP过来的请求
						if(infoPosition.getLatitude().indexOf(",") != -1){//如果是多点位
							pageContext.getOut().write("/airport_daohang.action?MapMorePoint=true&positionAlias="+value);
						}else{
							pageContext.getOut().write("/airport_daohang.action?longitude="+infoPosition.getLongitude()+"&latitude="+infoPosition.getLatitude()+"&name="+infoPosition.getName()+"&floorNumber="+infoPosition.getFloorNumber());
						}
					} else {
						if(infoPosition.getLatitude().indexOf(",") != -1){
							String[] latitudes = infoPosition.getLatitude().split(",");
							String[] longitudes = infoPosition.getLongitude().split(",");
							String latitude = latitudes[0];
							String longitude = longitudes[0];
							pageContext.getOut().write("javascript:window.location.href='http://api.map.baidu.com/marker?location="+latitude+","+longitude+"&title="+infoPosition.getName()+"&content="+infoPosition.getName()+"&output=html'");
						}
						else{
							pageContext.getOut().write("javascript:window.location.href='http://api.map.baidu.com/marker?location="+infoPosition.getLatitude()+","+infoPosition.getLongitude()+"&title="+infoPosition.getName()+"&content="+infoPosition.getName()+"&output=html'");
						}
					}
				}else{
					pageContext.getOut().write("#");
				}
			} else if("infoPositionListByCounter".equals(type)){//通过柜台查找点位
				Integer index = value.indexOf("(");
				if(index != -1){
					value = value.substring(0, index);
				}
				String[] aliass = value.trim().split("/");
				if(GeneralUtil.isNotNull(aliass)){
					if(WebUtil.validateSessionAttribute(request, "fromApp")) {//说明是从APP过来的请求
						pageContext.getOut().write("/airport_daohang.action?morePoint=true&positionAlias="+value);
					}else{
						for(String alias: aliass){
							String[] a = alias.split("-");
							InfoPosition infoPosition = infoPositionService.queryByAlias(a[0]);
							pageContext.getOut().write("javascript:window.location.href='http://api.map.baidu.com/marker?location="+infoPosition.getLatitude()+","+infoPosition.getLongitude()+"&title="+infoPosition.getName()+"&content="+infoPosition.getName()+"&output=html'");
							break;
						}
					}
				}
			} else if("infoPositionListByCounter2".equals(type)){
				String[] aliass = value.trim().split("-");
				if(GeneralUtil.isNotNull(aliass)){
					if(WebUtil.validateSessionAttribute(request, "fromApp")) {//说明是从APP过来的请求
						pageContext.getOut().write("/airport_daohang.action?morePoints=true&positionAlias="+value);
					}else{
						for(String alias: aliass){
							//String[] a = alias.split("-");
							String first = alias.substring(0, 1);
							InfoPosition infoPosition = infoPositionService.queryByAlias("newszhijiqu-" + first.toLowerCase());
							pageContext.getOut().write("javascript:window.location.href='http://api.map.baidu.com/marker?location="+infoPosition.getLatitude()+","+infoPosition.getLongitude()+"&title="+infoPosition.getName()+"&content="+infoPosition.getName()+"&output=html'");
							break;
						}
					}
				}
			}else if("positionUrl2".equals(type)) { //位置导航链接
				if(WebUtil.validateSessionAttribute(request, "fromApp")) {//说明是从APP过来的请求
					pageContext.getOut().write("/airport_daohang.action?name="+value);
				} else {
					pageContext.getOut().write("javascript:window.location.href='http://api.map.baidu.com/marker?location=25.102384,102.934956&title="+value+"&content=下载App能享受室内导航服务&output=html'");
				}
			} else if("currentDate".equals(type)){ //当前日期
				String currentDate = DateUtil.getSystemDateTime("yyyy-MM-dd");
				pageContext.getOut().write(currentDate);
			} else if("checkinList".equals(type)){ //值机列表
				List<MemberFocusFlight> list = memberFocusFlightService.queryListByCoupon(value,versionFlag);
				if(list != null && !list.isEmpty()){
					request.setAttribute(type,list);
				}else{
					request.removeAttribute(type);
				}
			} else if("memberObj".equals(type)){ //会员信息
				Member member = memberService.queryById(Member.class.getSimpleName(), value);
				if(member != null){
					request.setAttribute(type, member);
				}else{
					request.removeAttribute(type);
				}
			} else if("queryArrivalFlightByMemberAndNo".equals(type)){
				MemberFocusFlight memberFocusFlight = memberFocusFlightService.queryArrivalByMemberAndNo(value, flightDate, versionFlag);
				if(memberFocusFlight != null){
					request.setAttribute(type, memberFocusFlight);
				}else {
					request.removeAttribute(type);
				}
			} else if("flightPositionUrl".equals(type)) { //航班位置导航链接
				String djk = "djk";
				String ddk = "ddk";
				String xlzp = "xlzp";
				String zjgt = "zjgt";
				if(value.indexOf(djk) != -1){
					value=value.replace("djk", "-djk");
				}
				else if(value.indexOf(ddk) != -1){
					value=value.replace("ddk", "-ddk");
				}
				else if(value.indexOf(xlzp) != -1){
					value=value.replace("xlzp", "-xlzp");
				}
				else if(value.indexOf(zjgt) != -1){
					value=value.replace("zjgt", "");
				}
				InfoPosition infoPosition = infoPositionService.queryByAlias(value);
				if(infoPosition != null){
					if(WebUtil.validateSessionAttribute(request, "fromApp")) {//说明是从APP过来的请求
						pageContext.getOut().write("/airport_daohang.action?longitude="+infoPosition.getLongitude()+"&latitude="+infoPosition.getLatitude()+"&name="+infoPosition.getName()+"&floorNumber="+infoPosition.getFloorNumber());
					} else {
//						pageContext.getOut().write("javascript:tipDownloadApp();");
						pageContext.getOut().write("javascript:window.location.href='http://api.map.baidu.com/marker?location="+infoPosition.getLatitude()+","+infoPosition.getLongitude()+"&title="+infoPosition.getName()+"&content="+infoPosition.getName()+"&output=html'");
					}
				}else{
					pageContext.getOut().write("#");
				}
			} else if("filghtType".equals(type)) {
				SystemDictionary sd = systemDictionaryService.getByName(value) ;
				if(sd.getParent().getName().indexOf("国内") != -1) {
					pageContext.getRequest().setAttribute(type, "domestic") ;
				} else {
					pageContext.getRequest().setAttribute(type, "international") ;
				}
			} else if("channelTypes".equals(type)){

				List<SystemDictionary> channelTypes = systemDictionaryService.querySubByParentValue("service_code_type");
				pageContext.getRequest().setAttribute(var, channelTypes);
			} else if("halls".equals(type)){

				List<BjdjHall> halls = hallService.queryAll(BjdjHall.class);
				pageContext.getRequest().setAttribute(var, halls);
			} else if("preDayDate".equals(type)){
				Date date = DateUtil.parseShortStringToDate(value);
				String preDate = DateUtil.formatDateToShortString(new Date(date.getTime() - 24 * 60 * 60 * 1000));
				pageContext.getOut().write(preDate);
			} else if("nextDayDate".equals(type)){
				Date date = DateUtil.parseShortStringToDate(value);
				String preDate = DateUtil.formatDateToShortString(new Date(date.getTime() + 24 * 60 * 60 * 1000));
				pageContext.getOut().write(preDate);
			} else if("queryCompanyByFlightNo".equals(type)){
				String threeCode = value.substring(0, 3);
				FlightCompany fc = flightCompanyService.queryByThreeCode(threeCode,versionFlag);
				if(fc != null){
					request.setAttribute(type, fc);
				}else{
					request.removeAttribute(type);
				}
				
			} else if("queryCompanyByFlightNoTwo".equals(type)){
				String twoCode = value.substring(0, 2);
				FlightCompany fc = flightCompanyService.queryEntityByTwoCode(twoCode,versionFlag);
				if(fc != null){
					request.setAttribute(type, fc);
				}else{
					request.removeAttribute(type);
				}
				
			} else if("airportInfoByFourCode".equals(type)){
				AirportInfo api = airportInfoService.queryByFourCode(value,versionFlag);
				if(api != null){
					request.setAttribute(type, api);
				}else{
					request.removeAttribute(type);
				}
			} else if("airportInfoByThreeCode".equals(type)){
				AirportInfo api = airportInfoService.queryByThreeCode(value,versionFlag);
				if(api != null){
					request.setAttribute(type, api);
				}else{
					request.removeAttribute(type);
				}
			} else if("trangeFlightNoByCompanyCode".equals(type)){
				String threeCode = value.substring(0, 3);
				String number = value.substring(3);
				String twoCode = flightCompanyService.changeCode(threeCode,versionFlag);
				if(!"".equals(twoCode)){
					request.setAttribute(type, twoCode+number);
				}else{
					request.removeAttribute(type);
				}
			} else if("trangeFlightNoFromTwoToThree".equals(type)){
				String twoCode = value.substring(0, 2);
				String number = value.substring(2);
				String threeCode = flightCompanyService.changeCodeTwoToThree(twoCode,versionFlag);
				if(!"".equals(threeCode)){
					request.setAttribute(type, threeCode+number);
				}else{
					request.removeAttribute(type);
				}
			}else if("IsDateInPlan".equals(type)){//判断航班日期
				boolean flag = AirportPlaneUtil.compareDate(DateUtil.parseShortStringToDate(value));
				request.setAttribute(type, flag);
			} else if("planFlightInfo".equals(type)){
				AirportPlan airportPlan = airportPlanService.getDataByNo(value, versionFlag);
				if(airportPlan != null){
					request.setAttribute(type, airportPlan);
				}else{
					request.removeAttribute(type);
				}
			} else if("getStringByDateFormat".equals(type)){
				SimpleDateFormat sdf = new SimpleDateFormat(value);
				String dateString = sdf.format(flightDate);
				if(GeneralUtil.isNotNull(dateString)){
					request.setAttribute(type, dateString);
				}else{
					request.removeAttribute(type);
				}
			} else if("departFlightInfo".equals(type)){
				DepartFromPort dept = departFromPortService.queryByFlightNoAndDate(value, var, versionFlag);
				if(dept != null){
					request.setAttribute(type, dept);
				}else{
					request.removeAttribute(type);
				}
			}else if("systemModule".equals(type)){
				SystemModule module = systemModuleService.queryById(SystemModule.class.getName(), value);
				if(module != null){
					request.setAttribute(type, module);
				}else{
					request.removeAttribute(type);
				}
			}else if("department".equals(type)){
				DepartmentInfo info = departmentInfoService.getByEmployee(value);
				if(info != null){
					request.setAttribute(type, info);
				}else{
					request.removeAttribute(type);
				}
			}else if("customerType".equals(type)){
				ChannelType customerType = null;
				List<ChannelCustomerInfo> channelCustomerInfos = channelCustomerInfoService.queryChannelCustomerListByEmployee(value);
				for(ChannelCustomerInfo channelCustomerInfo:channelCustomerInfos){
					
					customerType = channelTypeService.getByChannelCustomer(channelCustomerInfo.getId());
				}
				if(customerType != null){
					request.setAttribute(type, customerType);
				}else{
					request.removeAttribute(type);
				}
			}else if("newCustomerCount".equals(type)){
				long newCustomerCount = channelCustomerInfoService.countNewCustomer(value, startDate, endDate,cTypeId);
				request.setAttribute(type, newCustomerCount);
			}else if("oldCustomerCount".equals(type)){
				long oldCustomerCount = channelCustomerInfoService.countOldCustomer(value, startDate, endDate,cTypeId);
				request.setAttribute(type, oldCustomerCount);
			}else if("czMoney".equals(type)){
				double czMoney = 0;
				List<ChannelCustomerInfo> channelCustomerInfos = channelCustomerInfoService.queryChannelCustomerListByEmployeeAndChannelType(value,cTypeId);
				for(ChannelCustomerInfo channelCustomerInfo:channelCustomerInfos){
					List<RechargeRecord> rechargeRecords = rechargeRecordService.queryByChannelCustomerInfoId(channelCustomerInfo.getId(),startDate,endDate);
					for(RechargeRecord rechargeRecord:rechargeRecords){
						czMoney += rechargeRecord.getAmountOfMoney(); 
					}
				}
				request.setAttribute(type, czMoney);
			}else if("buyCount".equals(type)){
				double buyCount = 0;
				double paidAmount = 0;
				List<ChannelCustomerInfo> channelCustomerInfos = channelCustomerInfoService.queryChannelCustomerListByEmployeeAndChannelType(value,cTypeId);
				for(ChannelCustomerInfo channelCustomerInfo:channelCustomerInfos){
					buyCount += orderInfoService.queryByChannelCustomerInfo_id(channelCustomerInfo.getId(),startDate,endDate);
				}
				paidAmount = buyCount * servicePackageService.getByName("全程服务套餐").getPrice();
				request.setAttribute(type, buyCount);
				request.setAttribute("paidAmount", paidAmount);
			}else if("activeCount".equals(type)){
				long activeCount = 0;
				List<ChannelCustomerInfo> channelCustomerInfos = channelCustomerInfoService.queryChannelCustomerListByEmployeeAndChannelType(value,cTypeId);
				for(ChannelCustomerInfo channelCustomerInfo:channelCustomerInfos){
					activeCount += orderInfoService.queryAllBjdjServiceCodeAllReadyActive(channelCustomerInfo.getId(),startDate,endDate);
				}
				request.setAttribute(type, activeCount);
			}else if("validationCount".equals(type)){
				long validationCount = 0;
				List<ChannelCustomerInfo> channelCustomerInfos = channelCustomerInfoService.queryChannelCustomerListByEmployeeAndChannelType(value,cTypeId);
				for(ChannelCustomerInfo channelCustomerInfo:channelCustomerInfos){
					validationCount += orderInfoService.queryAllBjdjServiceCodeAllReadyValidation(channelCustomerInfo.getId(),startDate,endDate);
				}
				request.setAttribute(type, validationCount);
			}else if("visitCount".equals(type)){
				int visitCount = 0;
				List<ChannelCustomerInfo> channelCustomerInfos = channelCustomerInfoService.queryChannelCustomerListByEmployeeAndChannelType(value,cTypeId);
				for(ChannelCustomerInfo channelCustomerInfo:channelCustomerInfos){
					visitCount += employeeOutVisitService.countByCustomerIdAndDate(channelCustomerInfo.getId(),startDate,endDate);
				}
				request.setAttribute(type, visitCount);
			}else if("phoneCount".equals(type)){
				int phoneCount = 0;
				List<ChannelCustomerInfo> channelCustomerInfos = channelCustomerInfoService.queryChannelCustomerListByEmployeeAndChannelType(value,cTypeId);
				for(ChannelCustomerInfo channelCustomerInfo:channelCustomerInfos){
					phoneCount += phoneVisitService.countByCustomerIdAndDate(channelCustomerInfo.getId(),startDate,endDate);
				}
				request.setAttribute(type, phoneCount);
			}else if("customerTypeCount".equals(type)){
				List<String> customerTypeCounts = new ArrayList<String>();
				Set<String> customerTypeCount = new HashSet<String>();
				ChannelType customerType = null;
				List<ChannelCustomerInfo> channelCustomerInfos = channelCustomerInfoService.queryChannelCustomerListByEmployee(value);
				if(GeneralUtil.isNotNull(channelCustomerInfos)){
					for(ChannelCustomerInfo channelCustomerInfo:channelCustomerInfos){
						customerType = channelTypeService.getByChannelCustomer(channelCustomerInfo.getId());
						if(GeneralUtil.isNotNull(customerType)){
							customerTypeCount.add(customerType.getName());
						}
					}
				}
				for(String s:customerTypeCount){
					customerTypeCounts.add(s);
				}
				request.setAttribute(type, customerTypeCounts);
			}else if("customerTypeId".equals(type)){
				ChannelType channelType = channelTypeService.getByName(value);
				request.setAttribute(type, channelType);
			}else if("customerTypeList".equals(type)){
				List<ChannelCustomerInfo> channelCustomerInfos = channelCustomerInfoService.queryChannelCustomerListByEmployeeAndChannelType(value,cTypeId);
				request.setAttribute(type, channelCustomerInfos);
			}else if("oneCzMoney".equals(type)){
				double czMoney = 0;
				List<RechargeRecord> rechargeRecords = rechargeRecordService.queryByChannelCustomerInfoId(cTypeId,startDate,endDate);
				for(RechargeRecord rechargeRecord:rechargeRecords){
					czMoney += rechargeRecord.getAmountOfMoney(); 
				}
				request.setAttribute(type, czMoney);
			}else if("oneBuyCount".equals(type)){
				long buyCount = 0;
				double pay = 0;
				buyCount = orderInfoService.queryByChannelCustomerInfo_id(cTypeId,startDate,endDate);
				pay = buyCount * Double.valueOf(systemDictionaryService.getValueByName("bjdj_price"));
				request.setAttribute(type, buyCount);
				request.setAttribute("payMoney", pay);
			}else if("oneActiveCount".equals(type)){
				long activeCount = 0;
				activeCount = orderInfoService.queryAllBjdjServiceCodeAllReadyActive(cTypeId,startDate,endDate);
				request.setAttribute(type, activeCount);
			}else if("oneValidationCount".equals(type)){
				long validationCount = 0;
				validationCount = orderInfoService.queryAllBjdjServiceCodeAllReadyValidation(cTypeId,startDate,endDate);
				request.setAttribute(type, validationCount);
			}else if("oneVisitCount".equals(type)){
				int visitCount = 0;
				visitCount = employeeOutVisitService.countByCustomerIdAndDate(cTypeId,startDate,endDate);
				request.setAttribute(type, visitCount);
			}else if("onePhoneCount".equals(type)){
				int phoneCount = 0;
				phoneCount = phoneVisitService.countByCustomerIdAndDate(cTypeId,startDate,endDate);
				request.setAttribute(type, phoneCount);
			}else if("advertObjByName".equals(type)){
				Advert advert = advertService.queryByName(value,versionFlag);
				if(advert != null){
					request.setAttribute(type, advert);
				}else{
					request.removeAttribute(type);
				}
			}else if("vpnBroken".equals(type)){
				VpnBroken vpnBroken = vpnBrokenService.get();
				if(vpnBroken != null && vpnBroken.isPageNotify()){
					
					request.setAttribute(type, true);
					request.setAttribute(var, systemDictionaryService.getByName("vpn_page_tip"));
				}else{
					request.setAttribute(type, false);
				}
			}else if("bjdjPageObj".equals(type)){
				BjdjOrder bjdjOrder = bjdjOrderService.queryById(BjdjOrder.class.getName(), value);
				if(bjdjOrder != null){
					BjdjServicePackage bjdjServicePackage = bjdjOrder.getPackageType();
					if(bjdjServicePackage != null){
						BjdjPage bjdjPage = bjdjServicePackage.getBjdjPage();
						if(bjdjPage != null){
							request.setAttribute(type, bjdjPage);
						}else{
							request.removeAttribute(type);
						}
					}
				}
			}else if("feedbackReplyObjList".equals(type)){
				List<FeedbackReply> feedbackReplies = feedbackReplyService.queryByFeedbackId(value);
				if(GeneralUtil.isNotNull(feedbackReplies)){
					request.setAttribute(type, feedbackReplies);
				}else{
					request.removeAttribute(type);
				}
			}else if("feedbackReplyObj".equals(type)){//反馈是否有回复
				FeedbackReply feedbackReply = feedbackReplyService.isBacks(value);
				if(feedbackReply != null){
					request.setAttribute(type, feedbackReply);
				}else{
					request.removeAttribute(type);
				}
			}else if("bjdjPageByServiceCode".equals(type)){//通过便捷登机服务码判断他所属套餐所在的便捷登机页面
				BjdjServiceCode code = bjdjServiceCodeService.getByCode(value);
				BjdjOrder order = code.getOrder();
				if(GeneralUtil.isNotNull(order)){
					BjdjServicePackage bjdjServicePackage = order.getPackageType();
					if(GeneralUtil.isNotNull(bjdjServicePackage)){
						BjdjPage bjdjPage = bjdjServicePackage.getBjdjPage();
						if(GeneralUtil.isNotNull(bjdjPage)){
							request.setAttribute(type, bjdjPage);
						}else{
							request.removeAttribute(type);
						}
					}
				}
			}else if("feedBackStatus".equals(type)){//判断反馈中是否有追加反馈，并且是否已回复
				Integer sta = feedbackReplyService.isFeedBackOrBack(value);
				request.setAttribute(type, sta);
			}else if("newsByTitle".equals(type)){//根据名称查询新闻（法律法规）
				News news = newsService.queryByTitle(value, versionFlag);
				if(news != null){
					request.setAttribute(type, news);
				}else{
					request.removeAttribute(type);
				}
			}else if("myAnnounecementCount".equals(type)){//通知中心阅读数量
				Member member = (Member)ActionContext.getContext().getSession().get(ContextConstants.SCOPE_MEMBER);
				List<MemberAnnouncement> list = null;
				NewsClass nc = newsClassService.queryByAlias(versionFlag, "tzzx");
				List<News> news = newsService.queryListByType(nc.getId(), null, versionFlag);
				if(member != null){
					list = memberAnnouncementService.queryByMember(member.getId());
					if(news.size() > list.size()){
						request.setAttribute(type, 1);
					}else{
						request.setAttribute(type, 0);
					}
				}else{
					Cookie tourist = CookiesUtil.getCookies("JSESSIONID");
					String tourist_id = tourist.getValue();
					list = memberAnnouncementService.queryByMember(tourist_id);
					if(news.size() > list.size()){
						request.setAttribute(type, 1);
					}else{
						request.setAttribute(type, 0);
					}
				}
			}else if("domesticCityThreeCodeChilds".equals(type)){
				List<SystemDictionary> list = systemDictionaryService.querySubByParentValue("domesticCityThreeCode");
				if(list != null && !list.isEmpty()){
					request.setAttribute(type, list);
				}else{
					request.removeAttribute(type);
				}
			}else if("advertFlightListByAdvert".equals(type)){
				List<AdvertFlight> advertFlights = advertFlightService.queryByAdvert(advertService.get(Advert.class, value));
				if(advertFlights != null){
					request.setAttribute(type, advertFlights);
				}else{
					request.removeAttribute(type);
				}
			}else if("advertFlightListByCity".equals(type)){
				List<AdvertFlight> advertFlights = advertFlightService.queryByCity(value);
				if(advertFlights != null){
					request.setAttribute(type, advertFlights);
				}else{
					request.removeAttribute(type);
				}
			}else if("internationalCityThreeCodeChilds".equals(type)){
				List<SystemDictionary> list = systemDictionaryService.querySubByParentValue("InternationalCityThreeCode");
				if(list != null && !list.isEmpty()){
					request.setAttribute(type, list);
				}else{
					request.removeAttribute(type);
				}
			}else if("scenicSpotsList".equals(type)){//景点列表
				List<ScenicSpots> list = scenicSpotsService.queryAll(ScenicSpots.class);
				if(list != null){
					request.setAttribute(type, list);
				}else{
					request.removeAttribute(type);
				}
			}else if("scenicSpotsHotList".equals(type)){
				List<ScenicSpots> list = scenicSpotsService.queryHot();
				if(list != null){
					request.setAttribute(type, list);
				}else{
					request.removeAttribute(type);
				}
			}else if("scenicSpotsNewsList".equals(type)){
				List<News> list = newsService.queryByscenicSpots(value);
				if(list != null){
					request.setAttribute(type, list);
				}else{
					request.removeAttribute(type);
				}
			}else if("airportInfoList".equals(type)){
				List<AirportInfo> airportInfos = airportInfoService.queryAll(AirportInfo.class);
				if(airportInfos != null){
					request.setAttribute(type, airportInfos);
				}else{
					request.removeAttribute(type);
				}
			}else if("infopositionByFlight".equals(type)){//根据航班号查询出它的值机柜台，再查出点位,乘机导航人工值机
				MemberFocusFlight flight = memberFocusFlightService.get(MemberFocusFlight.class, value);
				DepartFromPort dept = departFromPortService.queryByFlightNoAndDate(flight.getFlightNumber(), flight.getFlightDate(), versionFlag);
				String[] aliass = dept.getCid().trim().split("-");
				if(GeneralUtil.isNotNull(aliass)){
					if(WebUtil.validateSessionAttribute(request, "fromApp")) {//说明是从APP过来的请求
						pageContext.getOut().write("/airport_daohang.action?morePoints=true&positionAlias="+dept.getCid());
					}else{
						for(String alias: aliass){
							//String[] a = alias.split("-");
							String first = alias.substring(0, 1);
							InfoPosition infoPosition = infoPositionService.queryByAlias("newszhijiqu-" + first.toLowerCase());
							pageContext.getOut().write("javascript:window.location.href='http://api.map.baidu.com/marker?location="+infoPosition.getLatitude()+","+infoPosition.getLongitude()+"&title="+infoPosition.getName()+"&content="+infoPosition.getName()+"&output=html'");
							break;
						}
					}
				}
			}else if("departFromPort1".equals(type)){//查询出港航班
				MemberFocusFlight flight = memberFocusFlightService.get(MemberFocusFlight.class, value);
				DepartFromPort dept = departFromPortService.queryByFlightNoAndDate(flight.getFlightNumber(), flight.getFlightDate(), versionFlag);
				if(dept != null){
					request.setAttribute(type, dept);
				}else {
					request.removeAttribute(type);
				}
			}else if("infoposionByZhiji".equals(type)){//乘机导航自助值机是共享航班
				DepartFromPort dept = departFromPortService.get(DepartFromPort.class, value);
				FlightCompany company = flightCompanyService.queryEntityByTwoCode(dept.getAcw(), versionFlag);
				if(GeneralUtil.isNotNull(company)){
					String pinyin = PinyinUtil.getPinYin(company.getName());
					if(pinyin.indexOf(" ") != -1){
						int a = pinyin.indexOf(" ");
						pinyin = pinyin.substring(0,a);
					}
					if(WebUtil.validateSessionAttribute(request, "fromApp")) {//说明是从APP过来的请求
						pageContext.getOut().write("/airport_daohang.action?MapMorePoint=true&positionAlias="+"zizhuzhiji_"+pinyin+"_guonei");
					}else{
						InfoPosition infoPosition = infoPositionService.queryByAlias("zizhuzhiji_"+pinyin+"_guonei");
						String longitudes = infoPosition.getLongitude();
						String latitudes = infoPosition.getLatitude();
						/*String floorNumbers = infoPosition.getFloorNumber();*/
						String names = infoPosition.getClassName();
						String[] longitudesArr = longitudes.split(",");
						String[] latitudesArr = latitudes.split(",");
						/*String[] floorNumbersArr = floorNumbers.split(",");*/
						String[] nameArr = new String[]{};
						if(GeneralUtil.isNotNull(names)){
							nameArr = names.split(",");
						}
						pageContext.getOut().write("javascript:window.location.href='http://api.map.baidu.com/marker?location="+latitudesArr[0]+","+longitudesArr[0]+"&title="+nameArr[0]+"&content="+nameArr[0]+"&output=html'");
					}
				}
			}else if("infoposionByZhiji1".equals(type)){//乘机导航自助值机不是共享航班
				DepartFromPort dept = departFromPortService.get(DepartFromPort.class, value);
				FlightCompany company = flightCompanyService.queryEntityByTwoCode(dept.getAcw(), versionFlag);
				if(GeneralUtil.isNotNull(company)){
					String pinyin = PinyinUtil.getPinYin(company.getName());
					if(pinyin.indexOf(" ") != -1){
						int a = pinyin.indexOf(" ");
						pinyin = pinyin.substring(0,a);
					}
					if(WebUtil.validateSessionAttribute(request, "fromApp")) {//说明是从APP过来的请求
						pageContext.getOut().write("/airport_daohang.action?MapMorePoint=true&positionAlias="+"zizhuzhiji_"+pinyin+"_guonei");
					}else{
						InfoPosition infoPosition = infoPositionService.queryByAlias("zizhuzhiji_"+pinyin+"_guonei");
						String longitudes = infoPosition.getLongitude();
						String latitudes = infoPosition.getLatitude();
						/*String floorNumbers = infoPosition.getFloorNumber();*/
						String names = infoPosition.getClassName();
						String[] longitudesArr = longitudes.split(",");
						String[] latitudesArr = latitudes.split(",");
						/*String[] floorNumbersArr = floorNumbers.split(",");*/
						String[] nameArr = new String[]{};
						if(GeneralUtil.isNotNull(names)){
							nameArr = names.split(",");
						}
						pageContext.getOut().write("javascript:window.location.href='http://api.map.baidu.com/marker?location="+latitudesArr[0]+","+longitudesArr[0]+"&title="+nameArr[0]+"&content="+nameArr[0]+"&output=html'");
					}
				}
			}else if("myMessageCount".equals(type)){//我的站内信未阅读数量
				List<MemberSendInfo> list = sendInfoService.queryMy();
				int count = 0;
				for(MemberSendInfo info:list){
					if(!info.isH5()){
						count++;
					}
				}
				request.setAttribute(type, count);
			}else if("myEvaluationCount".equals(type)){//我的评论是否有新回复
				int count = evaluationService.queryByMember();
				request.setAttribute(type, count);
			}else if("myFeedBackCount".equals(type)){//我的反馈是否有新回复
				int count = feedbackService.queryByMember();
				request.setAttribute(type, count);
			}else if("newMessages".equals(type)){//是否有新信息（包括以上4个）
				Long count = sendInfoService.myNumberOfUnread();
				List<MemberSendInfo> list = sendInfoService.queryMy();
				int count1 = 0;
				if(GeneralUtil.isNotNull(list)){
					for(MemberSendInfo info:list){
						if(!info.isH5()){
							count1++;
						}
					}
				}
				int count2 = evaluationService.queryByMember();
				int count3 = feedbackService.queryByMember();
				request.setAttribute(type, count+count1+count2+count3);
			}
			else if("businessEventExpiry".equals(type)){//商家活动有效期
				BusinessEvent businessEvent = businessEventService.queryById(BusinessEvent.class.getName(), value);
				Date now = new Date();
				Date startTime = businessEvent.getStartTime();
				Date endTime = businessEvent.getEndTime();
				if(startTime.getTime() < now.getTime() && endTime.getTime() > now.getTime()){//有效期
					request.setAttribute(type, 1);
				}
				if(startTime.getTime() > now.getTime()){//未开始
					request.setAttribute(type, 2);
				}
				if(endTime.getTime() < now.getTime()){//过期
					request.setAttribute(type, 3);
				}
			}else if("canBindBusinessEventList".equals(type)){//可用活动
				List<BusinessEvent> businessEvents = businessEventService.canBindList();
				if(businessEvents != null){
					request.setAttribute(type, businessEvents);
				}else{
					request.removeAttribute(type);
				}
			}else if("businessInfoListByBusinessEvent".equals(type)){//根据商家活动查询该活动的商品
				List<BusinessEventBind> businessEventBinds = businessEventBindService.queryByEvent(value);
				if(businessEventBinds != null){
					request.setAttribute(type, businessEventBinds);
				}else{
					request.removeAttribute(type);
				}
			}
			else if("travellerList".equals(type)){//常用旅客列表
				Member member = (Member)ActionContext.getContext().getSession().get(ContextConstants.SCOPE_MEMBER);
				if(member == null){
					request.removeAttribute(type);
				}else{
					String memberId = member.getId();
					List<CommonTraveller> list = commonTravellerService
							.getByMemberId(memberId);
					if(list != null){
						request.setAttribute(type, list);
					}else{
						request.removeAttribute(type);
					}
				}
			}else if("travellerCardList".equals(type)){//常用旅客证件列表
				List<CommonTravellerCard> cards = commonTravellerCardService
						.findByParentId(value);
				if(cards != null){
					request.setAttribute(type, cards);
				}else{
					request.removeAttribute(type);
				}
			}
			else if("businessInfoLists".equals(type)){//所有商家
				List<BusinessInfo> businessInfos = businessInfoService.queryAll(BusinessInfo.class);
				if(businessInfos != null){
					request.setAttribute(type, businessInfos);
				}else{
					request.removeAttribute(type);
				}
			}else if("youZangGoodsList".equals(type)){//有赞商品列表
				List<YouZanGoodsInfo> goodsInfos = youZanGoodsInfoService.queryAll(YouZanGoodsInfo.class);
				if(goodsInfos != null){
					request.setAttribute(type, goodsInfos);
				}else{
					request.removeAttribute(type);
				}
			}else if("bindYouZanList".equals(type)){//是否绑定有赞商品
				List<BindYouZan> bindYouZans = bindYouZanService.queryByBusiness(value);
				if(bindYouZans != null && bindYouZans.size() > 0){
					request.setAttribute(type, bindYouZans);
				}else{
					request.removeAttribute(type);
				}
			}else if("binYouZanObj".equals(type)){//绑定的有赞商品
				BindYouZan bindYouZan = bindYouZanService.queryByBusinessAndGoods(value, versionFlag);
				if(bindYouZan != null){
					request.setAttribute(type, bindYouZan);
				}else{
					request.removeAttribute(type);
				}
			}else if("airportBusinessTypeObj".equals(type)){
				AirportBusinessType airportBusinessType = airportBusinessTypeService.queryByName(value);
				if(airportBusinessType != null){
					request.setAttribute(type, airportBusinessType);
				}else{
					request.removeAttribute(type);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

	public String getVersionFlag() {
		return versionFlag;
	}

	public void setVersionFlag(String versionFlag) {
		this.versionFlag = versionFlag;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDefaultImage() {
		return defaultImage;
	}

	public void setDefaultImage(String defaultImage) {
		this.defaultImage = defaultImage;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public Date getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(Date flightDate) {
		this.flightDate = flightDate;
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public MemberSendInfo getMsg() {
		return msg;
	}

	public void setMsg(MemberSendInfo msg) {
		this.msg = msg;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getcTypeId() {
		return cTypeId;
	}

	public void setcTypeId(String cTypeId) {
		this.cTypeId = cTypeId;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getFlightState() {
		return flightState;
	}

	public void setFlightState(String flightState) {
		this.flightState = flightState;
	}

	public String getStopover() {
		return stopover;
	}

	public void setStopover(String stopover) {
		this.stopover = stopover;
	}

	public String getWz() {
		return wz;
	}

	public void setWz(String wz) {
		this.wz = wz;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
