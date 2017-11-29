package com.ticket.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.enumer.SearchType;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.Advert;
import com.ticket.pojo.AirportEmployee;
import com.ticket.pojo.AirportInfo;
import com.ticket.pojo.AirportPlan;
import com.ticket.pojo.ArrivalAtPort;
import com.ticket.pojo.BusinessInfo;
import com.ticket.pojo.CityHistorySelect;
import com.ticket.pojo.CommonAnnex;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.CommonSearch;
import com.ticket.pojo.DepartFromPort;
import com.ticket.pojo.InfoPosition;
import com.ticket.pojo.KeyWordLocation;
import com.ticket.pojo.LostGoodsInfo;
import com.ticket.pojo.Member;
import com.ticket.pojo.MemberAnnouncement;
import com.ticket.pojo.MemberFocusFlight;
import com.ticket.pojo.News;
import com.ticket.pojo.NewsClass;
import com.ticket.pojo.PageRedirectTemplate;
import com.ticket.pojo.Regulation;
import com.ticket.pojo.ScenicSpots;
import com.ticket.pojo.SystemDictionary;
import com.ticket.pojo.SystemFreebackInfo;
import com.ticket.pojo.TrafficLine;
import com.ticket.pojo.TrafficLineAndStation;
import com.ticket.pojo.TrafficStation;
import com.ticket.pojo.TrafficType;
import com.ticket.service.IAdvertService;
import com.ticket.service.IAirportEmployeeService;
import com.ticket.service.IAirportInfoService;
import com.ticket.service.IArrivalAtPortService;
import com.ticket.service.IBusinessInfoService;
import com.ticket.service.ICheckinInfoService;
import com.ticket.service.ICityHistorySelectService;
import com.ticket.service.ICommonAnnexService;
import com.ticket.service.ICommonSearchService;
import com.ticket.service.IDepartFromPortService;
import com.ticket.service.IFlightCompanyService;
import com.ticket.service.IInDataService;
import com.ticket.service.IInfoPositionService;
import com.ticket.service.IKeyWordLocationService;
import com.ticket.service.ILostGoodsInfoService;
import com.ticket.service.IMemberAnnouncementService;
import com.ticket.service.IMemberFocusFlightService;
import com.ticket.service.INewsClassService;
import com.ticket.service.INewsService;
import com.ticket.service.IPageRedirectTemplateService;
import com.ticket.service.IRegulationService;
import com.ticket.service.IScenicSpotsService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.service.ISystemFreebackInfoService;
import com.ticket.service.ITimeSearchService;
import com.ticket.service.ITrafficLineAndStationService;
import com.ticket.service.ITrafficLineService;
import com.ticket.service.ITrafficStationService;
import com.ticket.service.ITrafficTypeService;
import com.ticket.util.AirportPlaneUtil;
import com.ticket.util.CookiesUtil;
import com.ticket.util.DateUtil;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

/**
 * WAP端前台控制器
 * @author haishui
 * @date 2015-10-19 18:13
 * @descript 处理wap前端的程序逻辑
 * 
 */
public class WapAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//新闻栏目Service
	@Resource private INewsClassService newsClassService = null;
	//新闻Service
	@Resource private INewsService newsService = null;
	//页面跳转设置Service
	@Resource private IPageRedirectTemplateService pageRedirectTemplateService = null;
	//商家信息Service
	@Resource private IBusinessInfoService businessInfoService = null;
	//机场员工Service
	@Resource private IAirportEmployeeService airportEmployeeService= null;
	//遗失物品Service
	@Resource private ILostGoodsInfoService lostGoodsInfoService= null;
	//离港航班Service
	@Resource private IDepartFromPortService departFromPortService= null;
	//到港航班Service
	@Resource private IArrivalAtPortService arrivalAtPortService= null;
	//交通类型Service
	@Resource private ITrafficTypeService trafficTypeService= null;
	//交通路线Service
	@Resource private ITrafficLineService trafficLineService = null;
	//路线站点关联Service
	@Resource private ITrafficLineAndStationService trafficLineAndStationService = null;
	//交通站点Service
	@Resource private ITrafficStationService trafficStationService= null;
	//机场制度service
	@Resource private IRegulationService regulationService = null;
	//系统字典service
	@Resource private ISystemDictionaryService systemDictionaryService = null;
	//会员历史城市选择
	@Resource private ICityHistorySelectService cityHistorySelectService = null;
	//设施位置的service
	@Resource protected IInfoPositionService infoPositionService;
	//机场航班计划的Service
	@Resource protected IInDataService inDataService;
	//机场信息的Service
	@Resource protected IAirportInfoService airportInfoService = null;
	//航空公司的Service
	@Resource protected IFlightCompanyService flightCompanyService = null;
	//值机信息的Service
	@Resource protected ICheckinInfoService checkinInfoService;
	//会员关注航班信息的Service
	@Resource protected IMemberFocusFlightService memberFocusFlightService;
	//系统反馈信息的Service
	@Resource protected ISystemFreebackInfoService systemFreebackInfoService;
	@Resource protected ITimeSearchService timeSearchService;
	@Resource protected IMemberAnnouncementService memberAnnouncementService;
	@Resource
	private IAdvertService advertService;
	@Resource
	private ICommonAnnexService commonAnnexService;
	@Resource
	private IScenicSpotsService scenicSpotsService;
	//新闻栏目
	private NewsClass newsClass = null;
	//信息实体
	private News news = null;
	//信息的URL
	private String visitUrl = null;
	//别名
	private String alias = null;
	//页面所在目录
	private String pageDirectory = null;
	//页面名称
	private String pageName = null;
	//起始位置
	private Integer startSize = 0;
	//每次获取几条
	private Integer pageCount = 6;
	//文章列表
	private List<News> newsList = null;
	//文章类别列表
	private List<NewsClass> newsClassList = null;
	//实体ID
	private String id = null;
	//Ajax加载类型   news:加载文章列表 newsClass是加载分类列表
	private String ajaxType = "newsClass";
	//标识 用于异步加载
	private String flag = null;
	//中转 左侧
	private String transferLeft = null;
	//中转 右侧
	private String transferRight = null;
	//商家信息实体
	private BusinessInfo businessInfo = null;
	//电话号码
	private String phone = null;
	//密码
	private String password = null;
	//员工姓名
	private String name = null;
	//员工工号
	private String employeeId = null;
	//机场员工实体
	private AirportEmployee airportEmployee = null;
	//关键词
	private String keyword = null;
	//全局搜索关键词
	private String commonKeyword = null;
	//物品类型
	private String type_id = null;
	//物品颜色
	private String color = null;
	//拾取时间
	private String pickUpTime = null;
    //拾取位置
	private String pickPosition_id = null;
    //物品描述
	private String goodsDescript = null;
	
	//遗失物品查询结果数量
	private Integer goodsCount = 0;
	//离港时间
	private String flightDate = null;
	//航班号
	private String flightNumber = null;
	//航班状态
	private String flightState = null;
	

	//航班列表
	private List<DepartFromPort> dfpList = null;
	//离港实体
	private DepartFromPort departFromPort = null;
	//离港实体
	private ArrivalAtPort arrivalAtPort = null;
	
	//出发城市
	private String setoutCity = null;
	//到达城市
	private String arrivalCity = null;
	//航班数量
	private Integer flightCount = 0;
	//查询离港结果列表
	private List<DepartFromPort> departFromPortList = null;
	//查询到港结果列表
	private List<ArrivalAtPort> arrivalAtPortList = null;
	
	//出发地
	private String orgCode = null;
	//目的地
	private String desCode = null;
	//机场制度
	private Regulation regulation = null;
	//查询类型
	private String searchType = "chufa";
	//中转航班
	private ArrivalAtPort transferPort = null;
	
	//左边显示
	private String left;
	private String right;
	
	private List<CommonSearch> locationList = null;
	private List<CommonSearch> searchList = null;
	private List<CommonSearch> businessList = null;
	private List<KeyWordLocation> keyWordLocations;
	@Resource private ICommonSearchService commonSearchService;
	@Resource private IKeyWordLocationService keyWordLocationService;
	//行李状态
	private String luggageState = null;
	private List<SystemDictionary> dictList = null;
	
	//快捷菜单标识
	private String fromQuickMenu = null;
	/**
	 * 车站列表
	 */
	private List<TrafficStation> trafficStationList = null;
	//路线列表
	private List<TrafficLine> trafficLineList = null;
	//路线类别列表
	private List<TrafficType> trafficTypeList = null;
	
	//快捷菜单位置
	private String quickMenuPosition = null;
	
	//离港计划航班
	private List<AirportPlan> departPlanFlightList = null;
	//进港计划航班
	private List<AirportPlan> arrivePlanFlightList = null;
	
	//出发城市四字码
	private String setoutCityFourCode = null;
	//到达城市四字码
	private String arriveCityFourCode = null;
	
	//计划航班周期
	private String cycle = null;
	
	//计划航班实体
	private AirportPlan airportPlan = null;
	//关注航班标识
	private String focusFlightFlag = null;
	//机场信息列表
	private List<AirportInfo> airportInfoList = null;
	//mid（app访问时用户的会员id）
	private String mid = null;
	
	private String stopover = null;//航班经停标识
	private String forFocus = null;//关注标识
	
	private String url,content;//反馈链接 反馈内容
	
	private ScenicSpots scenicSpots;
	
	private String var;
	private String wz;
	private String order;
	
	public String getForFocus() {
		return forFocus;
	}

	public void setForFocus(String forFocus) {
		this.forFocus = forFocus;
	}
	
	/**
	 * 景点详情页面
	 * @return
	 * @throws ServiceException
	 */
	public String scenicSpotsDetail() throws ServiceException{
		scenicSpots = scenicSpotsService.queryById(ScenicSpots.class.getName(), id);
		return "scenicSpotsDetail";
	}
	
	/**
	 * 景点列表页面
	 * @return
	 * @throws ServiceException
	 */
	public String scenicSpotsList() throws ServiceException{
		
		return "scenicSpotsList";
	}
	
	/**
	 * app开启时的广告
	 * @return
	 * @throws ServiceException
	 */
	public String appStartNewsInterface() throws ServiceException{
		JSONObject jsonObject = new JSONObject();
		try {
			//查询广告列表
			List<Advert> advertList = advertService.queryList(versionFlag, "APP开启广告", 10);
			jsonObject.put("advertList", advertList);
			SystemDictionary dictionary = systemDictionaryService.getByName("app_close_startNews_time");
			if(GeneralUtil.isNotNull(dictionary)){
				jsonObject.put("closeTime", dictionary.getValue());
			}
			//查询广告附件图片
			if(advertList != null){
				
				JSONArray jsonArray = new JSONArray();
				for(Advert advert : advertList){
					
					List<CommonAnnex> annex = commonAnnexService.queryListByEntityId(advert.getId(), 1, size);
					if(annex != null){
						
						jsonArray.add(annex.get(0));
					}
				}
				jsonObject.put("annex", jsonArray);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			data = AjaxData.responseError("获取信息失败");
			return JSON;
		}
		
		data = AjaxData.responseSuccess(jsonObject.toString());
		return JSON;
	}
	
	/**
	 * 记录游客会员阅读通知中心信息
	 * @return
	 * @throws ServiceException
	 */
	public String addMemberAnnouncement() throws ServiceException{
		Member member = getSessionMember();
		if(member == null){//未登录
			Cookie tourist = CookiesUtil.getCookies("JSESSIONID");
			String tourist_id = tourist.getValue();
			MemberAnnouncement announcement = memberAnnouncementService.queryByNewsAndMember(id, tourist_id);
			if(announcement == null){
				memberAnnouncementService.persist(tourist_id, newsService.queryById(News.class.getName(),id), versionFlag);
			}
		}else{
			MemberAnnouncement announcement = memberAnnouncementService.queryByNewsAndMember(id, member.getId());
			if(announcement == null){
				memberAnnouncementService.persist(member.getId(), newsService.queryById(News.class.getName(),id), versionFlag);
			}
		}
		data = AjaxData.responseSuccess(null);
		return JSON;
	}
	
	/**
	 * 公告
	 * @return
	 * @throws ServiceException
	 */
	public String announcement() throws ServiceException{
		return "announcement";
	}
	
	/**
	 * 根据航班号查询页面
	 * @return
	 * @throws ServiceException
	 */
	public String flightQueryByFlightNum() throws ServiceException{
		
		return "flightQueryByFlightNum";
	}
	/**
	 * 进入WAP首页
	 */
	@Override
	public String execute() throws Exception {
		this.setTipMessage("indexPage");
		return SUCCESS;
	}
	
	/**
	 * 注册
	 * @return
	 * @throws ServiceException
	 */
	public String register() throws ServiceException{
		return "register";
	}

	/**
	 * 登机牌
	 * @return
	 */
	public String dengjipai(){
		
		return "tipDownload";
	}

	
	/**
	 * 进入分类页面
	 * @return
	 * @throws Exception
	 */
	public String infoType() throws Exception {
		this.setNewsClass(newsClassService.queryByAlias(versionFlag, alias)); 
		//栏目不存在
		if(this.getNewsClass() == null) {
			return ERROR;
		}
		if(GeneralUtil.isNull(newsClass.getListPageRedirectTemplate_id())) {
			PageRedirectTemplate pt = pageRedirectTemplateService.queryByName("默认模板");
			if(pt != null) {
				newsClass.setListPageRedirectTemplate_id(pt.getId());
			}
		}
		//获取栏目对应的信息列表
		if(GeneralUtil.isNotNull(newsClass.getListPageRedirectTemplate_id())) {
			PageRedirectTemplate pageTemplate = pageRedirectTemplateService.queryById(PageRedirectTemplate.class.getSimpleName(), newsClass.getListPageRedirectTemplate_id());
			//如果类别是单篇文章
			if(pageTemplate.getIsSinglePage() == ContextConstants.STATUS_OF_ONE) {
				this.setPageDirectory(pageTemplate.getDirectory());
				this.setNews(newsService.querySingleByNewsClassId(newsClass.getId(), versionFlag));
				return pageTemplate.getToPage();
			} else {
				this.setPageDirectory(pageTemplate.getDirectory());
				this.setNewsList(newsService.queryListByFront(newsClass.getId(), startSize, pageCount));
				return pageTemplate.getToPage();
			}
		} else {
			return ERROR;
		}
	}
	
	/**
	 * 关注页面复制的页面（栾浩源要求）
	 * @return
	 */
	public String payAttentionToUsTemplate2(){
		
		return "payAttentionToUsTemplate2";
	}
	
	/**
	 * 进入分类页面Ajax
	 * @return
	 * @throws Exception
	 */
	public String infoTypeAjax() throws Exception {
		if(GeneralUtil.isNotNull(id)) {
			if("newsAjax".equals(ajaxType)) {
				newsClass = newsClassService.queryById(NewsClass.class.getSimpleName(), id);
				PageRedirectTemplate pageTemplate = pageRedirectTemplateService.queryById(PageRedirectTemplate.class.getSimpleName(), newsClass.getListPageRedirectTemplate_id());
				this.setNewsList(newsService.queryListByFront(newsClass.getId(), startSize, pageCount));
				return pageTemplate.getToPageAjax();
			} else {
				newsClass = newsClassService.queryById(NewsClass.class.getSimpleName(), id);
				PageRedirectTemplate pageTemplate = pageRedirectTemplateService.queryById(PageRedirectTemplate.class.getSimpleName(), newsClass.getListPageRedirectTemplate_id());
				this.setNewsClassList(newsClassService.queryListByFront(newsClass.getId(), startSize, pageCount));
				return pageTemplate.getToPageAjax();
			}
		} else {
			return null;
		}
	}
	
	/**
	 * 进入详细页面
	 * @return
	 * @throws Exception
	 */
	public String infoView() throws Exception {
		this.setNews(newsService.queryByUrl(visitUrl, true)) ;
		//文章不存在
		if(this.getNews() == null) {
			return ERROR;
		}
		//文章未审核
		if(this.getNews().getStatus().getAudit() == ContextConstants.STATUS_OF_ZERO) {
			return ERROR;
		}
		this.setNewsClass(newsClassService.queryById(NewsClass.class.getSimpleName(), news.getNewsClass_id())) ;
		
		if(GeneralUtil.isNull(news.getViewPageRedirectTemplate_id())) {
			PageRedirectTemplate pt = pageRedirectTemplateService.queryByName("默认模板");
			if(pt != null) {
				news.setViewPageRedirectTemplate_id(pt.getId());
			}
		}
		this.setPageDirectory(pageRedirectTemplateService.queryPageDirectoryById(news.getViewPageRedirectTemplate_id()));
		return pageRedirectTemplateService.queryPageNameById(news.getViewPageRedirectTemplate_id()) ;
	}
	
	//国内或者国外出发Ajax
	public String setoutAjax() throws ServiceException{
		return "setoutTemplateAjax";
	}
	//国际出发
	public String setoutFromInternational() throws ServiceException{
		return "setoutFromInternational";
	}
	//国内或者国外到达Ajax
	public String reachAjax() throws ServiceException{
		this.setFlag(flag);
		if("no".equals(luggageState)){
			return "reachTemplateNoLuggageAjax";
		}else{
			return "reachTemplateAjax";
		}
	}
	/**
	 * 国际到达
	 * @return
	 * @throws ServiceException
	 */
	public String reachInternational() throws ServiceException{
		return "reachInternational";
	}
	
	/**
	 * 国际到达无行李
	 * @return
	 * @throws ServiceException
	 */
	public String reachInternationalNoLuggage() throws ServiceException{
		return "reachInternationalNoLuggage";
	}
	/**
	 * 国内到达无行李
	 * @return
	 * @throws ServiceException
	 */
	public String reachDomesticNoLuggage() throws ServiceException{
		return "reachTemplateNoLuggage";
	}
	
	/**
	 * 国内到达有行李
	 * @return
	 * @throws ServiceException
	 */
	public String reachDomesticLuggage() throws ServiceException{
		return "reachDomesticLuggage";
	}
	//行李查询Ajax
	public String luggageQueryAjax() throws ServiceException{
		return "luggageQueryAjax";
	}
	
	//行李托运 Ajax
	public String luggageConsignAjax() throws ServiceException{
		return "luggageConsignAjax";
	}
	
	//航空公司信息
	public String flightCompany() throws ServiceException{
		return "flightCompany";
	}
	//航空公司Ajax
	public String flightAjax() throws ServiceException{
		return "flightAjax";
	}
	//航空公司Ajax
	public String selectFlightCompanyAjax() throws ServiceException{
		return "contentAndSelectFlightCompanyAjax";
	}
	
	//国内或者国外中转Ajax
	public String transferAjax() throws ServiceException{
		if(GeneralUtil.isNotNull(transferLeft)) {
			this.setTransferLeft(DecoderUtil.UtfDecoder(transferLeft));
		}
		if(GeneralUtil.isNotNull(transferRight)) {
			this.setTransferRight(DecoderUtil.UtfDecoder(transferRight));
		}
		if("no".equals(luggageState)){
			return "transferTemplateNoLuggageAjax";
		}else{
			return "transferTemplateAjax";
		}
	}
	//中转无行李
	public String transferNoLuggage() throws ServiceException{
		
		return "transferTemplateNoLuggage";
	}
	//中转有行李
	public String transferLuggage() throws ServiceException{
		
		return "transferTemplateLuggage";
	}
	//中转无行李Ajax
	public String transferNoLuggageAjax() throws ServiceException{
		if(GeneralUtil.isNotNull(transferLeft)) {
			this.setTransferLeft(DecoderUtil.UtfDecoder(transferLeft));
		}
		if(GeneralUtil.isNotNull(transferRight)) {
			this.setTransferRight(DecoderUtil.UtfDecoder(transferRight));
		}
		return "transferTemplateNoLuggageAjax";
	}
	//爱心服务模板Ajax
	public String caringAjax() throws ServiceException{
		return "caringTemplateAjax";
	}
	//切换模板Ajax
	public String changeFacilitiesAjax() throws ServiceException{
		return "changeContentAndNavigationAndPhoneAjax";
	}
	
	/********************第三模块********************/
	//1 餐饮
	public String restaurant() throws ServiceException{
		return "restaurant";
	}
	//2 零售
	public String shopping() throws ServiceException{
		return "shopping";
	}
	//3 住宿
	public String hotel() throws ServiceException{
		return "hotel";
	}
	public String goYouZanUrl() throws ServiceException{
		ActionContext.getContext().put("url", visitUrl);
		name = DecoderUtil.UtfDecoder(name);
		ActionContext.getContext().put("name", name);
		return "goYouZanUrl";
	}
	//内部员工登录
	public String employeeLogin() throws ServiceException{
		/*if(getSession().get(ContextConstants.SCOPE_EMPLOYEEINFO)!=null){
			return "internalEmployeeLogin";
		}*/
		if(GeneralUtil.isNull(operationFlag)){
			return "internalEmployeeLogin";
		}else{
			//非空验证
			if(GeneralUtil.isNull(phone)){
				data = AjaxData.responseError(getText("phone.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(password)){
				data = AjaxData.responseError(getText("password.required"));
				return JSON;
			}
			AirportEmployee emp = airportEmployeeService.queryByPhoneAndPwd(phone,password);
			if(emp != null){
				getSession().put(ContextConstants.SCOPE_EMPLOYEEINFO, emp);
				data = AjaxData.responseSuccess(getText("employeeLoginSuccess"));
			}else{
				data = AjaxData.responseError(getText("employeeLoginFailed"));
			}
			return JSON;
		}
	}
	
	public String loginSuccessIndex() throws ServiceException{
		return "internalEmployeeIndex";
	}
	
	//员工通道
	public String employeeHome() throws ServiceException{
		return "internalEmployeeChannel";
	}
	public String regulationDetail() throws ServiceException{
		regulation = regulationService.queryById(Regulation.class.getSimpleName(), id);
		this.setRegulation(regulation);
		return "regulationDetail";
	}
	//员工重置密码
	public String employeeResetPwd() throws ServiceException{
		if(GeneralUtil.isNull(operationFlag)){
			return "internalEmployeePwdReset";
		}else{
			//非空验证
			if(GeneralUtil.isNull(phone)){
				data = AjaxData.responseError(getText("phone.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(name)){
				data = AjaxData.responseError(getText("name.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(employeeId)){
				data = AjaxData.responseError(getText("employeeId.required"));
				return JSON;
			}
			AirportEmployee emp = airportEmployeeService.queryEmpByConditions(phone,name,employeeId,versionFlag);
			if(emp != null){
				if(getSession().get(ContextConstants.SCOPE_EMPLOYEEINFO)!= null){
					getSession().put(ContextConstants.SCOPE_EMPLOYEEINFO, emp);
				}
				data = AjaxData.responseSuccess("hold on");
				return JSON;
			}else{
				data = AjaxData.responseError(getText("noEmployee"));
				return JSON;
			}
		}
	}
	//员工重置密码页面
	public String EmployeePwdResetNewPwd() throws ServiceException{
		if(GeneralUtil.isNull(operationFlag)){
			return "internalEmployeePwdResetNewPwd";
		}else{
			//非空验证
			if(GeneralUtil.isNull(password)){
				data = AjaxData.responseError(getText("password.required"));
				return JSON;
			}
			AirportEmployee emp = (AirportEmployee) getSession().get(ContextConstants.SCOPE_EMPLOYEEINFO);
			if(emp != null){
				emp.setPassword(password);
				airportEmployeeService.merge(emp);
				if(getSession().get(ContextConstants.SCOPE_EMPLOYEEINFO)!= null){
					getSession().remove(ContextConstants.SCOPE_EMPLOYEEINFO);
				}
				data = AjaxData.responseSuccess(getText("resetPwd.success"));
			}else{
				data = AjaxData.responseError(getText("resetPwd.failed"));
			}
			return JSON;
		}
		
	}
	
	//商业类别二级菜单Ajax
	public String businessSecondTypeAjax() throws ServiceException{
		return "businessSecondTypeAjax";
	}
	//根据商家类别查询商家列表
	public String businessListAjax() throws ServiceException{
		ActionContext.getContext().put("cTypeId", var);
		ActionContext.getContext().put("order", order);
		ActionContext.getContext().put("wz", wz);
		return "businessListAjax";
	}
	//根据商家位置查询商家列表
	public String businessListAjaxByWz() throws ServiceException{
		return "businessListAjaxByWz";
	}
	//根据活动查询商家列表
	public String businessListAjaxByEvent() throws ServiceException{
		return "businessListAjaxByEvent";
	}
	//根据有赞销量排序
	public String businessListAjaxByOrder() throws ServiceException{
		return "businessListAjaxByOrder";
	}
	//商家详细列表
	public String businessDetail()throws ServiceException{
		this.setBusinessInfo(businessInfoService.queryById(BusinessInfo.class.getSimpleName(), id));
		return "businessDetail";
	}
	
	//交通指南
	public String trafficGuide() throws ServiceException{
		return "trafficGuide";
	}
	
	//交通指南详情
	public String trafficGuideDetail() throws ServiceException{
		
		//获取配置停车楼的在位置中心配置的点位
		InfoPosition position = infoPositionService.queryByAlias("airportStop");
		if(position != null && position.getLongitude() != null){
			
			List<String[]> list = new ArrayList<String[]>(3);
			String[] lngs = position.getLongitude().split(",");
			String[] lats = position.getLatitude().split(",");
			String[] names = position.getClassName().split(",");
			String[] floors = position.getFloorNumber().split(",");
			for(int i = 0; i < lats.length; i++){
				
				String[] item = new String[4];
				item[0] = lngs[i];
				item[1] = lats[i];
				item[2] = names[i];
				item[3] = floors[i];
				list.add(item);
			}
			ActionContext.getContext().put("positions", list);
		}
		return "trafficGuideDetail";
	}
	
	//交通指南路线
	public String trafficGuideLine() throws ServiceException{
		return "trafficGuideLine";
	}
	
	//交通指南路线Ajax
	public String trafficLineAjax() throws ServiceException{
		TrafficType trafficType = trafficTypeService.queryById(TrafficType.class.getSimpleName(), id);
		//获取配置停车楼的在位置中心配置的点位
		InfoPosition position = infoPositionService.queryByAlias("airportStop");
		if(position != null && position.getLongitude() != null){
			
			List<String[]> list = new ArrayList<String[]>(3);
			String[] lngs = position.getLongitude().split(",");
			String[] lats = position.getLatitude().split(",");
			String[] names = position.getClassName().split(",");
			String[] floors = position.getFloorNumber().split(",");
			for(int i = 0; i < lats.length; i++){
				
				String[] item = new String[4];
				item[0] = lngs[i];
				item[1] = lats[i];
				item[2] = names[i];
				item[3] = floors[i];
				list.add(item);
			}
			ActionContext.getContext().put("positions", list);
		}
		
		if(trafficType != null){
			CommonEntity status = trafficType.getStatus();
			Integer hot = status.getHot();
			if(hot == 0){
				
				return "trafficLineDetailAjax";
			}else{
				return "trafficLineAjax";
			}
		}
		return null;
	}
	
	//交通指南类别Ajax
	public String trafficTypeAjax() throws ServiceException{
		return "trafficTypeAjax";
	}
	
	/**
	 * 根据关键词查询车站
	 * @return
	 * @throws ServiceException
	 */
	public String selectStationAjax() throws ServiceException{
		List<TrafficStation> list = trafficStationService.queryListByKeyword(keyword,versionFlag);
		if(list != null && !list.isEmpty()){
			this.setTrafficStationList(list);
		}
		return "selectStationAjax";
	}
	
	/**
	 * 根据交通车站搜索路线
	 * @return
	 * @throws ServiceException
	 */
	public String queryTrafficLineByStationId() throws ServiceException{
		List<TrafficLineAndStation> list = trafficLineAndStationService.queryListByStationId(id,versionFlag);
		List<TrafficLine> lineList = new ArrayList<TrafficLine>();
		List<TrafficType> typeList = new ArrayList<TrafficType>();
		
		Set<String> set = new HashSet<String>();
		for(TrafficLineAndStation ls: list){
			set.add(ls.getTrafficType_id());
		}
		
		for(String str: set){
			TrafficType trafficType = trafficTypeService.queryById(TrafficType.class.getSimpleName(), str);
			typeList.add(trafficType);
		}
		
		
		if(list != null && !list.isEmpty()){
			for(TrafficLineAndStation trafficLineAndStation:list){
				TrafficLine trafficLine = trafficLineService.queryById(TrafficLine.class.getSimpleName(), trafficLineAndStation.getTrafficLine_id());
				lineList.add(trafficLine);
			}
		}
		if(typeList != null && !typeList.isEmpty()){
			getSession().put("trafficTypeList", typeList);
			this.setTrafficTypeList(typeList);
		}else{
			getSession().remove("trafficTypeList");
		}
		if(lineList != null && !lineList.isEmpty()){
			getSession().put("trafficLineList", lineList);
			this.setTrafficLineList(lineList);
		}else{
			getSession().remove("trafficLineList");
		}
		data = "success";
		return TEXT;
	}
	
	/**
	 * 交通路线搜索结果
	 * @return
	 * @throws ServiceException
	 */
	public String resultBySearchTraffic() throws ServiceException{
		return "resultBySearchTraffic";
	}
	//机场热线
	public String hotLinePhone() throws ServiceException{
		return "hotLinePhone";
	}
	//航空公司热线
	public String flightCompanyPhone() throws ServiceException{
		return "flightCompanyPhone";
	}
	
	/**
	 * 法律法规
	 * @return
	 * @throws ServiceException
	 */
	public String flightDelay() throws ServiceException{
		return "flightDelay";
	}
	/**
	 * 延误公告
	 * @return
	 * @throws ServiceException
	 */
	public String delayNotice() throws ServiceException{
		return "delayNotice";
	}
	/**
	 * 法律法规
	 * @return
	 * @throws ServiceException
	 */
	public String flightDelayLaws() throws ServiceException{
		this.setNews(newsService.queryById(News.class.getSimpleName(), id));
		return "lawsAndRegulations";
	}
	/**
	 * 机票改签
	 * @return
	 * @throws ServiceException
	 */
	public String mealTicket() throws ServiceException{
		return "mealTicket";
	}
	/**
	 * 查询遗失物品
	 * @return
	 * @throws ServiceException
	 */
	public String lostGoodsQuery() throws ServiceException{
		List<LostGoodsInfo> list = lostGoodsInfoService.queryByFront(pickUpTime,pickPosition_id,type_id,color,goodsDescript,versionFlag);
		if(list != null && !list.isEmpty()){
			this.setGoodsCount(list.size());
			data = AjaxData.responseSuccess(list.size());
		}else{
			data = AjaxData.responseSuccess(0);
		}
		return JSON;
//		return "goodsQueryResult";
	}
	
	public String goodsQueryResult(){
		
		return "goodsQueryResult";
	}
	
	/**
	 * 航班查询方式Ajax
	 * @return
	 * @throws ServiceException
	 */
	public String flightQueryTemplateAjax() throws ServiceException{
		return "flightQueryTemplateAjax";
	}
	
	/**
	 * 根据航班编号和日期查询航班信息
	 * @return
	 * @throws ServiceException
	 */
	public String queryByFlightNoAndDate() throws ServiceException {
		Date searchDate = DateUtil.parseShortStringToDate(flightDate);
		if(!AirportPlaneUtil.compareDate(searchDate)){
			DepartFromPort departFromPort = departFromPortService.queryByFlightNoAndDate(flightNumber, flightDate,versionFlag);
			ArrivalAtPort arrivalAtPort = arrivalAtPortService.queryByFlightNoAndDate(flightNumber, flightDate,versionFlag);
			this.setDepartFromPort(departFromPort);
			this.setArrivalAtPort(arrivalAtPort);
			this.setTransferPort(arrivalAtPortService.parseToBeTransferFlight(arrivalAtPort, departFromPort)) ;
			return "queryResultByNoAndDate";
		}else{
			//根据不同的计划表时段取出计划表数据
			List<AirportPlan> apList = inDataService.getListGroupByFlt(flightNumber);
			if(apList != null && !apList.isEmpty()){
				for(AirportPlan ap : apList){
					//查询航班计划表，取出计划时间段
					Date startDate = ap.getStart_date();
					Date endDate = ap.getEnd_date();
					//3 判断是否在航班计划时段之内
					boolean duringDate = AirportPlaneUtil.airportPlanisInCycle(startDate, endDate, searchDate);
					if(duringDate){
						boolean isInPlan = AirportPlaneUtil.weekIsAirportPlan(ap.getCycle(), searchDate);
						if(isInPlan){
							//4 通过查询日期、航班号查询航班列表
							List<AirportPlan> list = inDataService.queryFlightByNo(flightNumber,searchDate,versionFlag);
							if(list != null && !list.isEmpty()){
								List<AirportPlan> tempList = new ArrayList<AirportPlan>();
								for(AirportPlan aPlan : list){
									boolean flightIsInPlan = AirportPlaneUtil.weekIsAirportPlan(aPlan.getCycle(), searchDate);
									if(flightIsInPlan){
										tempList.add(aPlan);
									}
								}
								if(tempList != null && !tempList.isEmpty()){
									List<AirportPlan> departPlanList = new ArrayList<AirportPlan>();
									List<AirportPlan> arrivePlanList = new ArrayList<AirportPlan>();
									for(AirportPlan aPlan : tempList){
										if("ZPPP".equals(aPlan.getDept())){
											departPlanList.add(aPlan);
										}else if("ZPPP".equals(aPlan.getArrive())){
											arrivePlanList.add(aPlan);
										}
									}
									this.setDepartPlanFlightList(departPlanList);
									this.setArrivePlanFlightList(arrivePlanList);
									this.setFlightCount(tempList.size());
									return "queryPlanFlightByNoAndDate";
								}
							}
						}
						return "queryPlanFlightByNoAndDate";
					}
				}
			}
			return "queryPlanFlightByNoAndDate";
		}
	}
	/**
	 * 根据航班号和出发日期查询离港航班
	 * @return
	 * @throws ServiceException
	 */
	public String queryDepartFlightNoAndDate() throws ServiceException {
		departFromPort = departFromPortService.queryByFlightNoAndDate(flightNumber, flightDate,versionFlag);
		MemberFocusFlight mf = null;
		if(GeneralUtil.isNotNull(stopover) && !"undefined".equals(stopover)){
			mf = memberFocusFlightService.queryByMemberAndStopover(flightNumber, flightDate, "depart", versionFlag);
		}else{
			mf = memberFocusFlightService.queryByMemberAndFlightNoAndDate(flightNumber, flightDate, "depart", versionFlag);
		}
		if(mf != null){
			this.setFocusFlightFlag("hadFocus");
		}
		return "querydepartFlightByNoAndDate";
	}
	
	/**
	 * 返回到航班查询界面
	 * @return
	 * @throws ServiceException
	 */
	public String backToQuery() throws ServiceException{
		return "flightQueryTemplate";
	}
	
	/**
	 * 进港航班详情
	 * @return
	 * @throws ServiceException
	 */
	public String departFlightDetail() throws ServiceException {
		DepartFromPort departFromPort = departFromPortService.queryByFlightNoAndDate(flightNumber, flightDate,versionFlag);
		this.setDepartFromPort(departFromPort);
		return "departFlightDetail";
	}
	/**
	 * 根据航班号和航班日期查询进港航班信息
	 * @return
	 * @throws ServiceException
	 */
	public String queryArrivalFlightNoAndDate() throws ServiceException {
		if("transfer".equals(flightState)){
			arrivalAtPort = arrivalAtPortService.queryTrasferByFlightNoAndDate(flightNumber, flightDate, versionFlag);
			
		}else{
			arrivalAtPort = arrivalAtPortService.queryArrivalFlightNoAndDate(flightNumber, flightDate, versionFlag);
		}
		MemberFocusFlight mf  = null;
		if("transfer".equals(flightState)){
			mf = memberFocusFlightService.queryByMemberAndFlightNoAndDate(flightNumber, flightDate, "transfer", versionFlag);
		}else{
			if("1".equals(stopover)){
				mf = memberFocusFlightService.queryByMemberAndStopover(flightNumber, flightDate, "arrival", versionFlag);
				
			}else{
				mf = memberFocusFlightService.queryByMemberAndFlightNoAndDate(flightNumber, flightDate, "arrival", versionFlag);
			}
		}
		
		if(mf != null){
			this.setFocusFlightFlag("hadFocus");
		}
		return "queryArrivalFlightByNoAndDate";
	}
	
	/**
	 * 到港航班详情
	 * @return
	 * @throws ServiceException
	 */
	public String arrivalFlightDetail() throws ServiceException {
		ArrivalAtPort arrivalAtPort = arrivalAtPortService.queryByFlightNoAndDate(flightNumber, flightDate,versionFlag);
		this.setArrivalAtPort(arrivalAtPort);
		return "arrivalFlightDetail";
	}
	
	/**
	 * 根据起飞地和目的地查询航班信息
	 * @return
	 * @throws ServiceException
	 */
	public String queryFlightByCity() throws ServiceException{
		Date searchDate = DateUtil.parseShortStringToDate(flightDate);
		AirportInfo orgCity = airportInfoService.queryByThreeCode(orgCode, versionFlag);
		AirportInfo desCity = airportInfoService.queryByThreeCode(desCode, versionFlag);
		if(orgCity == null || desCity == null){
			return "queryResultByCity";
		}
		this.setSetoutCity(orgCity.getName());
		this.setArrivalCity(desCity.getName());
		
		//搜索日期是否在三天之内，查询实时航班
		if(!AirportPlaneUtil.compareDate(searchDate)){
			if(!"KMG".equals(orgCode) && !"KMG".equals(desCode)){
				this.setDepartFromPortList(null);
			}
			if("KMG".equals(orgCode)){
				List<DepartFromPort> departList = departFromPortService.queryListByCityAndDate(orgCode,desCode,flightDate,versionFlag);
				this.setFlightCount(departList.size());
				this.setDepartFromPortList(departList);
			}else{
				List<ArrivalAtPort> arrivalList = arrivalAtPortService.queryListByCityAndDate(orgCode,desCode,flightDate,versionFlag);
				this.setFlightCount(arrivalList.size());
				this.setArrivalAtPortList(arrivalList);
			}
			return "queryResultByCity";
		}else{//搜索日期在三天之外，查询计划航班
			//如果出发地是昆明
			if("KMG".equals(orgCode)){//
				//1 转换三四字码
				String start = "ZPPP"; //昆明的四字码
				List<AirportPlan> apList = inDataService.getListGroupByPlanDate(start, airportInfoService.getFourCodeByThreeCode(desCode, versionFlag));
				if(apList != null && !apList.isEmpty()){
					for(AirportPlan ap : apList){
						//查询航班计划表，取出计划时间段
						Date startDate = ap.getStart_date();
						Date endDate = ap.getEnd_date();
						//3 判断是否在航班计划时段之内
						boolean duringDate = AirportPlaneUtil.airportPlanisInCycle(startDate, endDate, searchDate);
						if(duringDate){
							//4 通过查询日期、起降地查询航班列表
							List<AirportPlan> list = inDataService.queryFlight(searchDate,start,ap.getArrive(),versionFlag);
							List<AirportPlan> tempList = new ArrayList<AirportPlan>();
							for(AirportPlan aPlan : list){
								//判断航班是否在指定日期之内
								boolean flightIsInPlan = AirportPlaneUtil.weekIsAirportPlan(aPlan.getCycle(), searchDate);
								if(flightIsInPlan){//将指定日期之内的航班信息放入列表内
									tempList.add(aPlan);
								}
							}
							if(tempList != null && !tempList.isEmpty()){
								this.setFlightCount(tempList.size());
								this.setDepartPlanFlightList(tempList);
								return "queryPlanFlightByCity";
							}
						}
					}
				}
					
			}else if("KMG".equals(desCode)){//JHG
				//1 转换三四字码
				String end = "ZPPP"; //昆明的四字码
				List<AirportPlan> apList = inDataService.getListGroupByPlanDate(airportInfoService.getFourCodeByThreeCode(orgCode,versionFlag),end);
				if(apList != null && !apList.isEmpty()){
					for(AirportPlan ap : apList){
						//查询航班计划表，取出计划时间段
						Date startDate = ap.getStart_date();
						Date endDate = ap.getEnd_date();
						//3 判断是否在航班计划时段之内
						boolean duringDate = AirportPlaneUtil.airportPlanisInCycle(startDate, endDate, searchDate);
						if(duringDate){
							boolean isInPlan = AirportPlaneUtil.weekIsAirportPlan(ap.getCycle(), searchDate);
							if(isInPlan){
								//4 通过查询日期、起降地查询航班列表
								List<AirportPlan> list = inDataService.queryFlight(searchDate,ap.getDept(),end,versionFlag);
								List<AirportPlan> tempList = new ArrayList<AirportPlan>();
								for(AirportPlan aPlan : list){
									boolean flightIsInPlan = AirportPlaneUtil.weekIsAirportPlan(aPlan.getCycle(), searchDate);
									if(flightIsInPlan){
										tempList.add(aPlan);
									}
								}
								if(tempList != null && !tempList.isEmpty()){
									this.setFlightCount(tempList.size());
									this.setArrivePlanFlightList(tempList);
									return "queryPlanFlightByCity";
								}
							}
						}
					}
				}
			}
			return "queryPlanFlightByCity";
		}
	}
	
	/**
	 * 刷新出发快捷菜单
	 * @return
	 * @throws ServiceException
	 */
	public String reloadSetoutQuickMenuAjax() throws ServiceException{
		if(getSession().get(ContextConstants.ISSETQUICKMENU) != null){
			getSession().remove(ContextConstants.ISSETQUICKMENU);
			return "setoutQuickMenuAjax";
		}else{
			return null;
		}
	}
	
	/**
	 * 刷新中转快捷菜单
	 * @return
	 * @throws ServiceException
	 */
	public String reloadTransferQuickMenuAjax() throws ServiceException{
		if(getSession().get(ContextConstants.ISSETQUICKMENU) != null){
			getSession().remove(ContextConstants.ISSETQUICKMENU);
			return "transferQuickMenuAjax";
		}else{
			return null;
		}
	}
	
	/**
	 * 刷新快捷菜单
	 * @return
	 * @throws ServiceException
	 */
	public String reloadQuickMenu() throws ServiceException{
		if(GeneralUtil.isNotNull(quickMenuPosition)){
			if("a".equals(quickMenuPosition)){
				return "SDQuickMenuAjax";
			} else if("b".equals(quickMenuPosition)){
				return "SIQuickMenuAjax";
			} else if("e".equals(quickMenuPosition)){
				return "RDQuickMenuAjax";
			} else if("f".equals(quickMenuPosition)){
				return "RIQuickMenuAjax";
			} 
		}
		return "";
	}
	
	/**
	 * 国内转国内无行李
	 * @return
	 * @throws ServiceException
	 */
	public String domesticToDomesticNoLuggage() throws ServiceException{
		return "domesticToDomesticNoLuggage";
	}
	/**
	 * 国内转国内有行李
	 * @return
	 * @throws ServiceException
	 */
	public String domesticToDomesticTakeLuggage() throws ServiceException{
		return "domesticToDomesticTakeLuggage";
	}
	
	/**
	 * 国际转国内无行李
	 * @return
	 * @throws ServiceException
	 */
	public String internationalToDomesticNoLuggage() throws ServiceException{
		return "internationalToDomesticNoLuggage";
	}
	
	/**
	 * 国际装转国内有行李
	 * @return
	 * @throws ServiceException
	 */
	public String internationalToDomesticTakeLuggage() throws ServiceException{
		return "internationalToDomesticTakeLuggage";
	}
	
	/**
	 * 刷新到达快捷菜单
	 * @return
	 * @throws ServiceException
	 */
	public String reloadReachQuickMenuAjax() throws ServiceException{
		if(getSession().get(ContextConstants.ISSETQUICKMENU) != null){
			getSession().remove(ContextConstants.ISSETQUICKMENU);
			return "reachQuickMenuAjax";
		}else{
			return null;
		}
	}
	
	
	/**
	 * 刷新个人中心快捷菜单
	 * @return
	 * @throws ServiceException
	 */
	public String personalCenterQuickMenuAjax() throws ServiceException{
		if(getSession().get(ContextConstants.ISSETQUICKMENU) != null){
			getSession().remove(ContextConstants.ISSETQUICKMENU);
			return "deleteQuickMenuAjax";
		}else{
			return null;
		}
	}
	
	/**
	 * 选择出发城市和目的地城市
	 * @return
	 * @throws ServiceException
	 */
	public String selectCity() throws ServiceException{
		return "selectCity";
	}
	
	/**
	 * 国际国内城市切换查找城市列表
	 * @return
	 * @throws ServiceException
	 */
	public String changeCityPosition() throws ServiceException{
		this.setFlag(flag);
		return "selectCityListAjax";
	}
	
	/**
	 * 国际国内城市切换查找城市列表
	 * @return
	 * @throws ServiceException
	 */
	public String changeCityPositionOrder() throws ServiceException{
		this.setFlag(flag);
		return "selectCityListAjaxOrder";
	}
	
	/**
	 * 选择出发城市和目的地城市
	 * @return
	 * @throws ServiceException
	 */
	public String selectCityAjax() throws ServiceException {
		if(GeneralUtil.isNotNull(keyword)) {
			keyword = DecoderUtil.UtfDecoder(keyword);
			if("guonei".equals(flag)) {//查询国内的三字码
				List<AirportInfo> domList = airportInfoService.queryListByKeyword(keyword,versionFlag);
				this.setAirportInfoList(domList);
			} else if("guowai".equals(flag)) {//查询国外的三字码
				List<AirportInfo> intList = airportInfoService.queryListByKeyword(keyword,versionFlag);
				this.setAirportInfoList(intList);
			} else {
			}
		}
		return "selectCityAjax";
	}
	
	/**
	 * 查询城市列表
	 * @return
	 * @throws ServiceException
	 */
	public String findCityList() throws ServiceException{
		List<SystemDictionary> list = systemDictionaryService.querySubByParentValue("");
		JSONArray array = new JSONArray();
		if(list != null && !list.isEmpty()){
			for(SystemDictionary sysDic:list){
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("name", sysDic.getName());
				jsonObj.put("value", sysDic.getValue());
				array.add(jsonObj);
			}
			data = array.toJSONString();
		}
		return TEXT;
	}
	
	/**
	 * 根据城市拼音首字母检索城市列表
	 * @return
	 * @throws ServiceException
	 */
	public String queryCityByFirstLetter() throws ServiceException{
		List<SystemDictionary> list = systemDictionaryService.querySubByParentNameAndValueAndPinYing(flag, name);
		this.setDictList(list);
		return "selectCityAjax";
	}
	
	/**
	 * 公共查询
	 * @return
	 * @throws ServiceException
	 */
	public String commonSearch() throws ServiceException {
		flightDate = DateUtil.formatDateToShortString(new Date());
		DepartFromPort departFromPort = departFromPortService.queryByFlightNoAndDate(commonKeyword, flightDate,versionFlag);
		ArrivalAtPort arrivalAtPort = arrivalAtPortService.queryByFlightNoAndDate(commonKeyword, flightDate,versionFlag);
		this.setFlightNumber(commonKeyword);
		this.setFlightDate(flightDate);
		if(departFromPort == null){
			if(arrivalAtPort == null){
				locationList = commonSearchService.searchListByKeyword2(SearchType.location, commonKeyword, startSize, pageCount, request.getRemoteHost());
				
				searchList = commonSearchService.searchListByKeyword(SearchType.normal, commonKeyword, startSize, pageCount, request.getRemoteHost());
				searchList = commonSearchService.removeSameUrl(searchList, locationList);
				
				businessList = commonSearchService.searchListByKeyword(SearchType.business, commonKeyword, startSize, pageCount, request.getRemoteHost());
				businessList = commonSearchService.removeSameUrl(businessList, locationList);
				
				timeSearchService.persist(DecoderUtil.UtfDecoder(commonKeyword).trim(), searchList);
				return "searchResult";
			}else{
				this.setArrivalAtPort(arrivalAtPort);
				return "queryResultByNoAndDate";
			}
		}else{
			if(arrivalAtPort != null){
				this.setDepartFromPort(departFromPort);
				this.setArrivalAtPort(arrivalAtPort);
				this.setTransferPort(arrivalAtPortService.parseToBeTransferFlight(arrivalAtPort, departFromPort)) ;
				return "queryResultByNoAndDate";
			}
			this.setDepartFromPort(departFromPort);
			return "queryResultByNoAndDate";
		}
	}
	
	/**
	 * 查询城市信息
	 * @return
	 * @throws ServiceException
	 */
	public String queryCityInfo() throws ServiceException {
		AirportInfo ap = airportInfoService.queryByThreeCode(keyword, versionFlag);
		//SystemDictionary systemDictionary = systemDictionaryService.queryByName(keyword,versionFlag);
		CityHistorySelect cityHistorySelect = new CityHistorySelect();
		Member member = (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		if("chufa".equals(searchType)) {
			getSession().put(ContextConstants.ORGCITY, ap.getName());
			getSession().put(ContextConstants.ORGCITYCODE, ap.getThreeCode());
			if(member != null){
				if(!cityHistorySelectService.validateExist(member.getId(),ap.getId())){
					cityHistorySelect.setMember_id(member.getId());
					cityHistorySelect.setCity_id(ap.getId());
					cityHistorySelectService.persist(cityHistorySelect);
				}
			}else{
				 Cookie[] cookies = request.getCookies();
				 boolean a = true;
				 if (cookies != null) {
					 for (int i = 0; i < cookies.length; i++) {  
						 Cookie c = cookies[i]; 
						 if (c.getName().equals("tourist")) {
							 List<CityHistorySelect> list = cityHistorySelectService.queryByTourist(c.getValue());
							 boolean b = true;
							 for(CityHistorySelect historySelect:list){//如果已经保存过同一个城市
								 if(historySelect.getCity_id().equals(cityHistorySelect.getCity_id())){
									 CommonEntity status = historySelect.getStatus();
									 status.setCreateTime(new Date());
									 historySelect.setStatus(status);
									 cityHistorySelectService.merge(historySelect);
									 b = false;
									 a = false;
								 }
							 }
							 if(b){
							 	cityHistorySelect.setMember_id(c.getValue());
								cityHistorySelect.setCity_id(ap.getId());
								cityHistorySelectService.persist(cityHistorySelect);
								a = false;
							 }
						 }
					 }
					 if(a){
						 Cookie cookie = new Cookie("tourist", new Date().getTime()+"");
						 cityHistorySelect.setMember_id(cookie.getValue());
						 cityHistorySelect.setCity_id(ap.getId());
						 cityHistorySelectService.persist(cityHistorySelect);
						 response.addCookie(cookie);
					 }
				 }else{
					 Cookie cookie = new Cookie("tourist", new Date().getTime()+"");
					 cityHistorySelect.setMember_id(cookie.getValue());
					 cityHistorySelect.setCity_id(ap.getId());
					 cityHistorySelectService.persist(cityHistorySelect);
					 response.addCookie(cookie);
				 }
			}
		} else {
			getSession().put(ContextConstants.DESCITY, ap.getName());
			getSession().put(ContextConstants.DESCITYCODE, ap.getThreeCode());
			if(member != null){
				if(!cityHistorySelectService.validateExist(member.getId(),ap.getId())){
					cityHistorySelect.setMember_id(member.getId());
					cityHistorySelect.setCity_id(ap.getId());
					cityHistorySelectService.persist(cityHistorySelect);
				}
			}else{
				Cookie[] cookies = request.getCookies();
				boolean a = true;
				 if (cookies != null) {
					 for (int i = 0; i < cookies.length; i++) {  
						 Cookie c = cookies[i]; 
						 if (c.getName().equals("tourist")) {  
							 List<CityHistorySelect> list = cityHistorySelectService.queryByTourist(c.getValue());
							 boolean b = true;
							 for(CityHistorySelect historySelect:list){//如果已经保存过同一个城市
								 if(historySelect.getCity_id().equals(cityHistorySelect.getCity_id())){
									 CommonEntity status = historySelect.getStatus();
									 status.setCreateTime(new Date());
									 historySelect.setStatus(status);
									 cityHistorySelectService.merge(historySelect);
									 b = false;
									 a = false;
								 }
							 }
							 if(b){
							 	cityHistorySelect.setMember_id(c.getValue());
								cityHistorySelect.setCity_id(ap.getId());
								cityHistorySelectService.persist(cityHistorySelect);
								a = false;
							 }
						 }
					 }
					 if(a){
						 Cookie cookie = new Cookie("tourist", new Date().getTime()+"");
						 cityHistorySelect.setMember_id(cookie.getValue());
						 cityHistorySelect.setCity_id(ap.getId());
						 cityHistorySelectService.persist(cityHistorySelect);
						 response.addCookie(cookie);
					 }
				 }else{
					 Cookie cookie = new Cookie("tourist", new Date().getTime()+"");
					 cityHistorySelect.setMember_id(cookie.getValue());
					 cityHistorySelect.setCity_id(ap.getId());
					 cityHistorySelectService.persist(cityHistorySelect);
					 response.addCookie(cookie);
				 }
			}
		}
		
		data = "success";
		return TEXT;
	}
	
	/**
	 * 获取所有的城市
	 * @return
	 * @throws ServiceException
	 */
	public String getAllCity() throws ServiceException{
		/*List<SystemDictionary> list = systemDictionaryService.getAllCityByCityPoi(flag);
		if(list != null && !list.isEmpty()){
			AjaxUtil.writeString(response, request, list.toString());
			this.setDictList(list);
		}*/
		return "selectCityAjax";
	}
	
	/**
	 * 值机协议服务须知
	 * @return
	 * @throws ServiceException
	 */
	public String checkinServiceNotice() throws ServiceException{
		return "checkinServiceNotice";
	}
	
	/**
	 * 根据IP定位城市
	 * @return
	 * @throws ServiceException
	 */
	public String queryCityByIp() throws ServiceException{
		String city = keyword.substring(0, keyword.length()-1);
		List<AirportInfo> list = airportInfoService.queryByCity(city,versionFlag);
		if(list != null && !list.isEmpty()){
			AirportInfo ap = list.get(0);
			JSONObject json = new JSONObject();
			json.put("name", ap.getThreeCode());
			json.put("value", ap.getFourCode());
			json.put("description", ap.getName());
			data = json.toJSONString();
		} else{
			data = AjaxData.responseError("failed");
		}
		
		return TEXT;
	}
	
	/**
	 * 国内旅游
	 * @return
	 * @throws ServiceException
	 */
	public String domesticTravel() throws ServiceException{
		return "domesticTravel";
	}
	
	/**
	 * 国外旅游
	 * @return
	 * @throws ServiceException
	 */
	public String foreignTravel() throws ServiceException{
		return "foreignTravel";
	}
	
	/**
	 * 自由行
	 * @return
	 * @throws ServiceException
	 */
	public String freeWalker() throws ServiceException{
		return "freeWalker";
	}
	
	/**
	 * 自由行套餐
	 * @return
	 * @throws ServiceException
	 */
	public String freeWalkerCombo() throws ServiceException{
		return "freeWalkerCombo";
	}
	
	/**
	 * 录像详情
	 * @return
	 * @throws ServiceException
	 */
	public String lineDetail() throws ServiceException{
		return "lineDetail";
	}
	
	/**
	 * 景点详情
	 * @return
	 * @throws ServiceException
	 */
	public String scenicSpotDetail() throws ServiceException{
		return "scenicSpotDetail";
	}
	
	/**
	 * 服务预订
	 * @return
	 * @throws ServiceException
	 */
	public String serviceBook() throws ServiceException{
		return "serviceBook";
	}
	
	/**
	 * 旅游资讯
	 * @return
	 * @throws ServiceException
	 */
	public String travelInformation() throws ServiceException{
		return "travelInformation";
	}
	
	/**
	 * 旅游资讯2
	 * @return
	 * @throws ServiceException
	 */
	public String travelInformationTwo() throws ServiceException{
		return "";
	}
	
	/*public String appPersonnalCenter() throws ServiceException{
		return "personalCenterNoLogin";
	}*/
	
	/**
	 * 购票须知
	 */
	public String buyTicketNotice() throws ServiceException{
		return "buyTicketNotice";
	}
	
	/**
	 * 购票须知Ajax
	 */
	public String buyTicketNoticeAjax() throws ServiceException{
		return "buyTicketNoticeAjax";
	}
	
	/**
	 * 使用导航，需要下载app
	 */
	public String daohang(){
		
		return "tipDownload";
	}
	
	/**
	 * 爱心服务列表
	 * @return
	 * @throws ServiceException
	 */
	public String careServiceList() throws ServiceException{
		return "careServiceList";
	}
	
	/**
	 * 根据条件查询计划航班
	 * @return
	 * @throws ServiceException
	 */
	public String queryPlanFlightByConditions() throws ServiceException{
		AirportPlan ap = inDataService.queryByNoAndDateAndCycle(flightDate,setoutCityFourCode,arriveCityFourCode,cycle,versionFlag);
		if(ap != null){
			this.setAirportPlan(ap);
			MemberFocusFlight mf = null;
			if("ZPPP".equals(setoutCityFourCode)){
				mf = memberFocusFlightService.queryByMemberAndFlightNoAndDate(flightCompanyService.transferFlightNumber(ap.getFlt()), flightDate, "depart", versionFlag);
			}
			else if("ZPPP".equals(arriveCityFourCode)){
				mf = memberFocusFlightService.queryByMemberAndFlightNoAndDate(flightCompanyService.transferFlightNumber(ap.getFlt()), flightDate, "arrival", versionFlag);
			}
			if(mf != null){
				this.setFocusFlightFlag("hadFocus");
			}
		}
		return "planFlightDetail";
	}
	
	/**
	 * 根据航班编号和航班日期以及航班周期查询计划航班信息
	 * @return
	 * @throws ServiceException
	 */
	public String queryPlanFlightByNoAndDateAndCycle() throws ServiceException{
		Date searchDate = DateUtil.parseShortStringToDate(flightDate);
		String regStr = flightNumber.substring(0, 3);
		String str = "^[A-Za-z]{3}$";
		Pattern pattern = Pattern.compile(str);  
		Matcher matcher=pattern.matcher(regStr);  
		boolean matchFlag = matcher.matches();
		String tempFlt = "";
		if(!matchFlag){
			String threeCode = flightCompanyService.changeCodeTwoToThree(flightNumber.substring(0, 2), versionFlag);
			tempFlt = threeCode+flightNumber.substring(2);
		}
		List<AirportPlan> apList = inDataService.getListGroupByFlt(tempFlt);
		if(apList != null && !apList.isEmpty()){
			for(AirportPlan ap : apList){
				//查询航班计划表，取出计划时间段
				Date startDate = ap.getStart_date();
				Date endDate = ap.getEnd_date();
				//3 判断是否在航班计划时段之内
				boolean duringDate = AirportPlaneUtil.airportPlanisInCycle(startDate, endDate, searchDate);
				if(duringDate){
					AirportPlan aPlan = inDataService.getDataByNoAndSADate(tempFlt,startDate,endDate,flightState,versionFlag);
					if(aPlan != null){
						this.setAirportPlan(aPlan);
						MemberFocusFlight mf = null;
						if("ZPPP".equals(aPlan.getDept())){
							mf = memberFocusFlightService.queryByMemberAndFlightNoAndDate(flightCompanyService.transferFlightNumber(ap.getFlt()), flightDate, "depart", versionFlag);
							this.setFlag("depart");
						}
						else if("ZPPP".equals(aPlan.getArrive())){
							mf = memberFocusFlightService.queryByMemberAndFlightNoAndDate(flightCompanyService.transferFlightNumber(ap.getFlt()), flightDate, "arrival", versionFlag);
							this.setFlag("arrival");
						}
						if(mf != null){
							this.setFocusFlightFlag("hadFocus");
						}
						return "planFlightDetail";
					}
				}
			}
		}
		return "planFlightDetail";
	}
	
	/**
	 * 保存用户反馈信息
	 * @return
	 * @throws ServiceException
	 */
	public String saveSysFreebackInfo() throws ServiceException{
		try {
			SystemFreebackInfo systemFreebackInfo = new SystemFreebackInfo();
			systemFreebackInfo.setName(name);
			systemFreebackInfo.setPhone(phone);
			systemFreebackInfo.setUrl(url);
			systemFreebackInfo.setContent(DecoderUtil.UtfDecoder(content));
			systemFreebackInfoService.persist(systemFreebackInfo);
			data = AjaxData.responseSuccess("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			data = AjaxData.responseError("保存失败"+e.getMessage());
		}
		return JSON;
	}
	
	
	public NewsClass getNewsClass() {
		return newsClass;
	}

	public void setNewsClass(NewsClass newsClass) {
		this.newsClass = newsClass;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public String getVisitUrl() {
		return visitUrl;
	}

	public void setVisitUrl(String visitUrl) {
		this.visitUrl = visitUrl;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getPageDirectory() {
		return pageDirectory;
	}

	public void setPageDirectory(String pageDirectory) {
		this.pageDirectory = pageDirectory;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
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

	public List<News> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAjaxType() {
		return ajaxType;
	}

	public void setAjaxType(String ajaxType) {
		this.ajaxType = ajaxType;
	}

	public List<NewsClass> getNewsClassList() {
		return newsClassList;
	}

	public void setNewsClassList(List<NewsClass> newsClassList) {
		this.newsClassList = newsClassList;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getTransferLeft() {
		return transferLeft;
	}

	public void setTransferLeft(String transferLeft) {
		this.transferLeft = transferLeft;
	}

	public String getTransferRight() {
		return transferRight;
	}

	public void setTransferRight(String transferRight) {
		this.transferRight = transferRight;
	}
	public BusinessInfo getBusinessInfo() {
		return businessInfo;
	}
	public void setBusinessInfo(BusinessInfo businessInfo) {
		this.businessInfo = businessInfo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public AirportEmployee getAirportEmployee() {
		return airportEmployee;
	}

	public void setAirportEmployee(AirportEmployee airportEmployee) {
		this.airportEmployee = airportEmployee;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getType_id() {
		return type_id;
	}

	public void setType_id(String typeId) {
		type_id = typeId;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPickUpTime() {
		return pickUpTime;
	}

	public void setPickUpTime(String pickUpTime) {
		this.pickUpTime = pickUpTime;
	}

	public String getPickPosition_id() {
		return pickPosition_id;
	}

	public void setPickPosition_id(String pickPositionId) {
		pickPosition_id = pickPositionId;
	}

	public String getGoodsDescript() {
		return goodsDescript;
	}

	public void setGoodsDescript(String goodsDescript) {
		this.goodsDescript = goodsDescript;
	}

	public Integer getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public List<DepartFromPort> getDfpList() {
		return dfpList;
	}

	public void setDfpList(List<DepartFromPort> dfpList) {
		this.dfpList = dfpList;
	}

	public String getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(String flightDate) {
		this.flightDate = flightDate;
	}

	public String getSetoutCity() {
		return setoutCity;
	}

	public void setSetoutCity(String setoutCity) {
		this.setoutCity = setoutCity;
	}

	public String getArrivalCity() {
		return arrivalCity;
	}

	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}

	public Integer getFlightCount() {
		return flightCount;
	}

	public void setFlightCount(Integer flightCount) {
		this.flightCount = flightCount;
	}

	public DepartFromPort getDepartFromPort() {
		return departFromPort;
	}

	public void setDepartFromPort(DepartFromPort departFromPort) {
		this.departFromPort = departFromPort;
	}

	public List<DepartFromPort> getDepartFromPortList() {
		return departFromPortList;
	}

	public void setDepartFromPortList(List<DepartFromPort> departFromPortList) {
		this.departFromPortList = departFromPortList;
	}

	public List<ArrivalAtPort> getArrivalAtPortList() {
		return arrivalAtPortList;
	}

	public void setArrivalAtPortList(List<ArrivalAtPort> arrivalAtPortList) {
		this.arrivalAtPortList = arrivalAtPortList;
	}

	

	public ArrivalAtPort getArrivalAtPort() {
		return arrivalAtPort;
	}

	public void setArrivalAtPort(ArrivalAtPort arrivalAtPort) {
		this.arrivalAtPort = arrivalAtPort;
	}

	public Regulation getRegulation() {
		return regulation;
	}

	public void setRegulation(Regulation regulation) {
		this.regulation = regulation;
	}

	public List<CommonSearch> getSearchList() {
		return searchList;
	}

	public void setSearchList(List<CommonSearch> searchList) {
		this.searchList = searchList;
	}

	public String getCommonKeyword() {
		return commonKeyword;
	}

	public void setCommonKeyword(String commonKeyword) {
		this.commonKeyword = commonKeyword;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getLeft() {
		return left;
	}

	public void setLeft(String left) {
		this.left = left;
	}

	public String getRight() {
		return right;
	}

	public void setRight(String right) {
		this.right = right;
	}

	public String getLuggageState() {
		return luggageState;
	}

	public void setLuggageState(String luggageState) {
		this.luggageState = luggageState;
	}

	public List<SystemDictionary> getDictList() {
		return dictList;
	}

	public void setDictList(List<SystemDictionary> dictList) {
		this.dictList = dictList;
	}

	public ArrivalAtPort getTransferPort() {
		return transferPort;
	}

	public void setTransferPort(ArrivalAtPort transferPort) {
		this.transferPort = transferPort;
	}

	public List<TrafficStation> getTrafficStationList() {
		return trafficStationList;
	}

	public void setTrafficStationList(List<TrafficStation> trafficStationList) {
		this.trafficStationList = trafficStationList;
	}

	public List<TrafficLine> getTrafficLineList() {
		return trafficLineList;
	}

	public void setTrafficLineList(List<TrafficLine> trafficLineList) {
		this.trafficLineList = trafficLineList;
	}

	public List<TrafficType> getTrafficTypeList() {
		return trafficTypeList;
	}

	public void setTrafficTypeList(List<TrafficType> trafficTypeList) {
		this.trafficTypeList = trafficTypeList;
	}

	public String getQuickMenuPosition() {
		return quickMenuPosition;
	}

	public void setQuickMenuPosition(String quickMenuPosition) {
		this.quickMenuPosition = quickMenuPosition;
	}

	public String getFromQuickMenu() {
		return fromQuickMenu;
	}

	public void setFromQuickMenu(String fromQuickMenu) {
		this.fromQuickMenu = fromQuickMenu;
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

	public IPageRedirectTemplateService getPageRedirectTemplateService() {
		return pageRedirectTemplateService;
	}

	public void setPageRedirectTemplateService(
			IPageRedirectTemplateService pageRedirectTemplateService) {
		this.pageRedirectTemplateService = pageRedirectTemplateService;
	}

	public IBusinessInfoService getBusinessInfoService() {
		return businessInfoService;
	}

	public void setBusinessInfoService(IBusinessInfoService businessInfoService) {
		this.businessInfoService = businessInfoService;
	}

	public IAirportEmployeeService getAirportEmployeeService() {
		return airportEmployeeService;
	}

	public void setAirportEmployeeService(
			IAirportEmployeeService airportEmployeeService) {
		this.airportEmployeeService = airportEmployeeService;
	}

	public ILostGoodsInfoService getLostGoodsInfoService() {
		return lostGoodsInfoService;
	}

	public void setLostGoodsInfoService(ILostGoodsInfoService lostGoodsInfoService) {
		this.lostGoodsInfoService = lostGoodsInfoService;
	}

	public IDepartFromPortService getDepartFromPortService() {
		return departFromPortService;
	}

	public void setDepartFromPortService(
			IDepartFromPortService departFromPortService) {
		this.departFromPortService = departFromPortService;
	}

	public IArrivalAtPortService getArrivalAtPortService() {
		return arrivalAtPortService;
	}

	public void setArrivalAtPortService(IArrivalAtPortService arrivalAtPortService) {
		this.arrivalAtPortService = arrivalAtPortService;
	}

	public ITrafficTypeService getTrafficTypeService() {
		return trafficTypeService;
	}

	public void setTrafficTypeService(ITrafficTypeService trafficTypeService) {
		this.trafficTypeService = trafficTypeService;
	}

	public ITrafficLineService getTrafficLineService() {
		return trafficLineService;
	}

	public void setTrafficLineService(ITrafficLineService trafficLineService) {
		this.trafficLineService = trafficLineService;
	}

	public ITrafficLineAndStationService getTrafficLineAndStationService() {
		return trafficLineAndStationService;
	}

	public void setTrafficLineAndStationService(
			ITrafficLineAndStationService trafficLineAndStationService) {
		this.trafficLineAndStationService = trafficLineAndStationService;
	}

	public ITrafficStationService getTrafficStationService() {
		return trafficStationService;
	}

	public void setTrafficStationService(
			ITrafficStationService trafficStationService) {
		this.trafficStationService = trafficStationService;
	}

	public IRegulationService getRegulationService() {
		return regulationService;
	}

	public void setRegulationService(IRegulationService regulationService) {
		this.regulationService = regulationService;
	}

	public ISystemDictionaryService getSystemDictionaryService() {
		return systemDictionaryService;
	}

	public void setSystemDictionaryService(
			ISystemDictionaryService systemDictionaryService) {
		this.systemDictionaryService = systemDictionaryService;
	}

	public ICityHistorySelectService getCityHistorySelectService() {
		return cityHistorySelectService;
	}

	public void setCityHistorySelectService(
			ICityHistorySelectService cityHistorySelectService) {
		this.cityHistorySelectService = cityHistorySelectService;
	}

	public IInfoPositionService getInfoPositionService() {
		return infoPositionService;
	}

	public void setInfoPositionService(IInfoPositionService infoPositionService) {
		this.infoPositionService = infoPositionService;
	}

	public ICommonSearchService getCommonSearchService() {
		return commonSearchService;
	}

	public void setCommonSearchService(ICommonSearchService commonSearchService) {
		this.commonSearchService = commonSearchService;
	}

	public List<AirportPlan> getDepartPlanFlightList() {
		return departPlanFlightList;
	}

	public void setDepartPlanFlightList(List<AirportPlan> departPlanFlightList) {
		this.departPlanFlightList = departPlanFlightList;
	}

	public List<AirportPlan> getArrivePlanFlightList() {
		return arrivePlanFlightList;
	}

	public void setArrivePlanFlightList(List<AirportPlan> arrivePlanFlightList) {
		this.arrivePlanFlightList = arrivePlanFlightList;
	}

	public String getSetoutCityFourCode() {
		return setoutCityFourCode;
	}

	public void setSetoutCityFourCode(String setoutCityFourCode) {
		this.setoutCityFourCode = setoutCityFourCode;
	}

	public String getArriveCityFourCode() {
		return arriveCityFourCode;
	}

	public void setArriveCityFourCode(String arriveCityFourCode) {
		this.arriveCityFourCode = arriveCityFourCode;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public AirportPlan getAirportPlan() {
		return airportPlan;
	}

	public void setAirportPlan(AirportPlan airportPlan) {
		this.airportPlan = airportPlan;
	}

	public IFlightCompanyService getFlightCompanyService() {
		return flightCompanyService;
	}

	public void setFlightCompanyService(IFlightCompanyService flightCompanyService) {
		this.flightCompanyService = flightCompanyService;
	}

	public ICheckinInfoService getCheckinInfoService() {
		return checkinInfoService;
	}

	public void setCheckinInfoService(ICheckinInfoService checkinInfoService) {
		this.checkinInfoService = checkinInfoService;
	}

	public String getFocusFlightFlag() {
		return focusFlightFlag;
	}

	public void setFocusFlightFlag(String focusFlightFlag) {
		this.focusFlightFlag = focusFlightFlag;
	}

	public List<AirportInfo> getAirportInfoList() {
		return airportInfoList;
	}

	public void setAirportInfoList(List<AirportInfo> airportInfoList) {
		this.airportInfoList = airportInfoList;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public IAirportInfoService getAirportInfoService() {
		return airportInfoService;
	}

	public void setAirportInfoService(IAirportInfoService airportInfoService) {
		this.airportInfoService = airportInfoService;
	}

	public String getStopover() {
		return stopover;
	}

	public void setStopover(String stopover) {
		this.stopover = stopover;
	}

	public String getDesCode() {
		return desCode;
	}

	public void setDesCode(String desCode) {
		this.desCode = desCode;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getFlightState() {
		return flightState;
	}

	public void setFlightState(String flightState) {
		this.flightState = flightState;
	}

	public IMemberFocusFlightService getMemberFocusFlightService() {
		return memberFocusFlightService;
	}

	public void setMemberFocusFlightService(
			IMemberFocusFlightService memberFocusFlightService) {
		this.memberFocusFlightService = memberFocusFlightService;
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

	public List<KeyWordLocation> getKeyWordLocations() {
		return keyWordLocations;
	}

	public void setKeyWordLocations(List<KeyWordLocation> keyWordLocations) {
		this.keyWordLocations = keyWordLocations;
	}

	public List<CommonSearch> getBusinessList() {
		return businessList;
	}

	public void setBusinessList(List<CommonSearch> businessList) {
		this.businessList = businessList;
	}

	public List<CommonSearch> getLocationList() {
		return locationList;
	}

	public void setLocationList(List<CommonSearch> locationList) {
		this.locationList = locationList;
	}

	public ScenicSpots getScenicSpots() {
		return scenicSpots;
	}

	public void setScenicSpots(ScenicSpots scenicSpots) {
		this.scenicSpots = scenicSpots;
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

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}
}
