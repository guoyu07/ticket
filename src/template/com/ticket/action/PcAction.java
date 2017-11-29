package com.ticket.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.ticket.enumer.SearchType;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.AirportBusinessType;
import com.ticket.pojo.ArrivalAtPort;
import com.ticket.pojo.BusinessInfo;
import com.ticket.pojo.CommonSearch;
import com.ticket.pojo.DepartFromPort;
import com.ticket.pojo.FlightCompany;
import com.ticket.pojo.News;
import com.ticket.pojo.NewsClass;
import com.ticket.pojo.PageRedirectTemplate;
import com.ticket.service.IAirportBusinessTypeService;
import com.ticket.service.IAirportInfoService;
import com.ticket.service.IArrivalAtPortService;
import com.ticket.service.IBusinessInfoService;
import com.ticket.service.ICommonSearchService;
import com.ticket.service.IDepartFromPortService;
import com.ticket.service.IFlightCompanyService;
import com.ticket.service.INewsClassService;
import com.ticket.service.INewsService;
import com.ticket.service.IPageRedirectTemplateService;
import com.ticket.service.ITimeSearchService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

/**
 * pc前端控制器
 * @author dfq
 * @date 2015-12-31 9:34
 * @descript 处理pc端的程序逻辑
 * 
 */
public class PcAction extends BaseAction {
    
	private static final long serialVersionUID = 1L;
	
	//机场商户业务层
	@Resource IBusinessInfoService businessInfoService = null;
	//航空公司的业务层
	@Resource IFlightCompanyService flightCompanyService = null;
	//离港航班的业务层
	@Resource IDepartFromPortService departFromPortService = null;
	//进港航班的业务层
	@Resource IArrivalAtPortService arrivalAtPortService = null;
	//新闻业务层
	@Resource private INewsService newsService = null;
	@Resource private INewsClassService newsClassService = null;

	@Resource private IAirportInfoService airportInfoService = null;
	
	@Resource protected ITimeSearchService timeSearchService;
	//关键词
	private String keyword = null;
	//商户类别
	private String businessType = null;
	//地址
	private String address = null;
	//排序标识
	private String orderFlag = null;
	//航班标识
	private String flightFlag = null;
	//航班编号
	private String flightNumber = null;
	//航班日期
	private String flightDate = null;
	//航班时段
	private String flightTime = null;
	//出发城市
	private String setoutCity = null;
	//到达城市
	private String reachCity = null;
	//航班数量
	private Integer flightCount = null;
	//标识
	private String flag = null;
	//别名
	private String alias = null;
	private String alias2 = null;
	
	//商户列表
	private List<BusinessInfo> businessList = null;
	//航空公司列表
	private List<FlightCompany> flightCompanyList = null;
	//进港航班列表
	private List<DepartFromPort> departList = null;
	//离港航班列表
	private List<ArrivalAtPort> arrivalList = null;
	private NewsClass newsClass,parentNewsClass,newsClass2;
	private String visitUrl = null;
	private News news = null;
	private List<CommonSearch> searchList = null;
	@Resource private ICommonSearchService commonSearchService = null;
	//起始位置
	private Integer startSize = 0;
	//每次获取几条
	private Integer pageCount = 30;
	private String businessInfoType_id,lc,wz,fl;
	private List<BusinessInfo> businessInfos = null;
	private BusinessInfo businessInfo = null;
	private String businessInfoType_id2 = null;
	/**
	 * 1 出发
	 * 0 到达
	 */
	private Integer isChufa = null;
	private List<AirportBusinessType> businessTypes = null;
	@Resource
	private IAirportBusinessTypeService airportBusinessTypeService = null;
	@Resource
	private IPageRedirectTemplateService pageRedirectTemplateService = null;
	
	/**
	 * @author wangjiafeng
	 * 商家详细
	 * @method businessInfoView
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-2-20 下午02:50:43
	 */
	public String businessInfoView() throws Exception{
		businessInfo = businessInfoService.queryById(BusinessInfo.class.getSimpleName(),id);
		return "businessInfoView";
	}
	
	/**
	 * 公共查询
	 * @return
	 * @throws ServiceException
	 */
	public String commonSearch() throws ServiceException {
		this.setSearchList(commonSearchService.searchListByKeyword(SearchType.normal, keyword, startSize, pageCount, request.getRemoteHost())) ;
		timeSearchService.persist(DecoderUtil.UtfDecoder(keyword).trim(), getSearchList());
		return "searchResult";
	}
	
	/**
	 * @author wangjiafeng
	 * 新闻详细
	 * @method newsView
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-2-18 上午10:09:37
	 */
	public String newsView() throws Exception{
		if(GeneralUtil.isNotNull(id)){
			news = newsService.queryById(News.class.getSimpleName(), id);
		}
		if(GeneralUtil.isNotNull(visitUrl)){
			news = newsService.queryByUrl(visitUrl, true);
		}
		if(GeneralUtil.isNotNull(news.getNewsClass_id())){
			newsClass = newsClassService.queryById(NewsClass.class.getSimpleName(), news.getNewsClass_id());
			parentNewsClass = newsClassService.getTopNewsClass(newsClass);
		}
		if(news != null && GeneralUtil.isNotNull(newsClass.getPcViewTemplate_id())){
			PageRedirectTemplate template = pageRedirectTemplateService
					.queryById(PageRedirectTemplate.class.getSimpleName(), newsClass.getPcViewTemplate_id());
			return template.getToPage();
		}else{
			return "newsView";
		}
	}
	
	/**
	 * @author wangjiafeng
	 * 获取新闻列表
	 * @method newsList
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-2-17 下午05:43:47
	 */
	public String newsList() throws Exception{
		newsClass = newsClassService.queryById(NewsClass.class.getSimpleName(), id);
		parentNewsClass = newsClassService.getTopNewsClass(newsClass);
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(newsService.queryPageModuleByNewsClassId(id, 15, versionFlag));
		this.setPageSize(15);
		if(GeneralUtil.isNotNull(newsClass.getPcListTemplate_id())){
			PageRedirectTemplate template = pageRedirectTemplateService.queryById(PageRedirectTemplate.class.getSimpleName(), newsClass.getPcListTemplate_id());
			return template.getToPage();
		}else{
			return "newsAllList";
		}
	}
	
	/**
	 * PC首页
	 * @return
	 * @throws Exception
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	/**
	 * 便捷登机首页
	 * @return
	 * @throws ServiceException
	 */
	public String convenientBoardingIndex() throws ServiceException{
		return "convenientBoardingIndex";
	}
	
	/**
	 * 支付信息
	 * @return
	 * @throws ServiceException
	 */
	public String payInfo() throws ServiceException{
		return "payInfo";
	}
	
	/**
	 * 我的账户
	 * @return
	 * @throws ServiceException
	 */
	public String myAccount() throws ServiceException{
		return "myAccount";
	}
	
	/**
	 * 我的订单
	 * @return
	 * @throws ServiceException
	 */
	public String myOrder() throws ServiceException{
		return "myOrder";
	}
	
	/**
	 * 发现
	 * @return
	 * @throws ServiceException
	 */
	public String find() throws ServiceException{
		return "find";
	}
	
	
	
	/**
	 * 航班动态
	 * @return
	 * @throws ServiceException
	 */
	public String flightDynamic() throws ServiceException{
		return "flightDynamic";
	}
	
	/**
	 * 网上值机
	 * @return
	 * @throws ServiceException
	 */
	public String checkOnLine() throws ServiceException{
		return "checkOnLine";
	}
	
	/**
	 * 我的行程
	 * @return
	 * @throws ServiceException
	 */
	public String myWay() throws ServiceException{
		return "myWay";
	}
	
	/**
	 * 乘机须知
	 * @return
	 * @throws ServiceException
	 */
	public String flightNotice() throws ServiceException{
		return "flightNotice";
	}
	/**
	 * 帮助中心/机场百问
	 * @return
	 * @throws ServiceException
	 */
	public String airportFAQ() throws ServiceException{
		newsClass = newsClassService.queryByAlias(versionFlag, "changshuibaiwen");
		parentNewsClass = newsClassService.getTopNewsClass(newsClass);
		if(GeneralUtil.isNotNull(keyword)){
			keyword = DecoderUtil.UtfDecoder(keyword);
			
		}
		this.setPageModule(newsService.queryByClassAndKeyword(newsClass.getId(), keyword, 1, null, 20));
		this.setPageSize(20);
		return "airportFAQ";
	}
	/**
	 * 帮助中心
	 * @return
	 * @throws ServiceException
	 */
	public String helpCenter() throws ServiceException{
		newsClass = newsClassService.queryByAlias(versionFlag, "bangzhuzhongxin");
		parentNewsClass = newsClassService.getTopNewsClass(newsClass);
		if(GeneralUtil.isNotNull(keyword)){
			keyword = DecoderUtil.UtfDecoder(keyword);
			
		}
		this.setPageModule(newsService.queryByClassAndKeyword(newsClass.getId(), keyword, 1, null, 20));
		this.setPageSize(20);
		return "helpCenter";
	}
	
	/**
	 * @author wangjiafeng
	 * 旅客服务
	 * @method trafficGuideMenu
	 * @return
	 * @throws ServiceException
	 * @return String
	 * @date 2016-3-15 上午10:52:22
	 */
	public String trafficGuideMenu() throws ServiceException{
		
		return "trafficGuideMenu";
	}
	
	/**
	 * 消息盒子
	 * @return
	 * @throws ServiceException
	 */
	public String myMessage() throws ServiceException{
		return "myMessage";
	}
	
	/**
	 * 我的收藏
	 * @return
	 * @throws ServiceException
	 */
	public String myFavorite() throws ServiceException{
		return "myFavorite";
	}
	
	/**
	 * 个人中心
	 * @return
	 * @throws ServiceException
	 */
	public String personalCenter() throws ServiceException{
		return "personalCenter";
	}
	
	/**
	 * 免责申明
	 * @return
	 * @throws ServiceException
	 */
	public String disclaimer() throws ServiceException{
		return "disclaimer";
	}
	
	/**
	 * 航班延误公告
	 * @return
	 * @throws ServiceException
	 */
	public String flightDelayNotice() throws ServiceException{
		return "flightDelayNotice";
	}
	
	/**
	 * 关注我们
	 * @return
	 * @throws ServiceException
	 */
	public String payAttentionToUs() throws ServiceException{
		return "payAttentionToUs";
	}
	
	/**
	 * 服务热线
	 * @return
	 * @throws ServiceException
	 */
	public String serviceHotLine() throws ServiceException{
		return "serviceHotLine";
	}
	
	/**
	 * 商家展示
	 * @return
	 * @throws ServiceException
	 */
	public String businessDisplay() throws ServiceException{
		if(GeneralUtil.isNotNull(businessInfoType_id2)){
			businessInfos = businessInfoService.queryByPc(businessInfoType_id2, keyword, lc, wz, fl);
		}else{
			businessInfos = businessInfoService.queryByPc(businessInfoType_id, keyword, lc, wz, fl);
		}
		if(GeneralUtil.isNotNull(businessInfoType_id)){
			businessTypes = airportBusinessTypeService.queryChildAirportBusinessTypesByParent(businessInfoType_id);
		}
		
		return "businessDisplay";
	}
	
	/**
	 * 交通指南
	 * @return
	 * @throws ServiceException
	 */
	public String trafficGuide() throws ServiceException{
		return "trafficGuide";
	}
	
	/**
	 * 旅客服务
	 * @return
	 * @throws ServiceException
	 */
	public String passengerService() throws ServiceException{
		this.setNewsClass(newsClassService.queryByAlias(versionFlag, alias));
		this.setNewsClass2(newsClassService.queryByAlias(versionFlag, alias2));
		return "passengerService";
	}
	/**
	 * 长水动态
	 * @return
	 * @throws ServiceException
	 */
	public String airportDynamic() throws ServiceException{
		return "newsDynamic";
	}
	
	/**
	 * 航空公司信息
	 * @return
	 * @throws ServiceException
	 */
	public String flightCompanyInfo() throws ServiceException{
		return "flightCompanyInfo";
	}
	
	/**
	 * 注册用户
	 * @return
	 * @throws ServiceException
	 */
	public String register() throws ServiceException{
		return "register";
	}
	
	/**
	 * 切换机场商业
	 * @return
	 * @throws ServiceException
	 */
	public String changeAirportBusiness() throws ServiceException{
		return "businessAjax";
	}
	
	/**
	 * 根据地址和商户类别查询商户信息
	 * @return
	 * @throws ServiceException
	 */
	public String queryBusiness() throws ServiceException{
		List<BusinessInfo> list = new ArrayList<BusinessInfo>();
		if(GeneralUtil.isNotNull(keyword)){
			list = businessInfoService.queryByTypeAndKeyword(businessType,keyword,versionFlag);
		}else{
			list = businessInfoService.queryByConditions(businessType,address,versionFlag);
		}
		this.setBusinessList(list);
		return null;
	}
	
	/**
	 * 根据类别查询所有商户信息
	 * @return
	 * @throws ServiceException
	 */
	public String queryAllBusiness() throws ServiceException{
		List<BusinessInfo> list = businessInfoService.queryListByType(businessType,versionFlag);
		this.setBusinessList(list);
		return null;
	}
	
	/**
	 * 查询推荐商家列表
	 * @return
	 * @throws ServiceException
	 */
	public String queryCommendBusiness() throws ServiceException{
		List<BusinessInfo> list = businessInfoService.queryListByCommend(null,versionFlag);
		this.setBusinessList(list);
		return null;
	}
	
	/**
	 * 查询航空公司列表
	 * @return
	 * @throws ServiceException
	 */
	public String queryFlightCompany() throws ServiceException{
		List<FlightCompany> list = flightCompanyService.queryByConditions2(keyword,orderFlag,versionFlag);
		this.setFlightCompanyList(list);
		return "flightCompanyList";
	}
	
	/**
	 * 根据关键词查询航班
	 * @return
	 * @throws ServiceException
	 */
	public String queryFlightByKeyword() throws ServiceException{
		if(GeneralUtil.isNotNull(flightFlag)){
			if("arrival".equals(flightFlag)){
				List<ArrivalAtPort> list = arrivalAtPortService.queryByKeyword(keyword,versionFlag);
				if(list != null && !list.isEmpty()){
					this.setArrivalList(list);
				}
			}else{
				List<DepartFromPort> list = departFromPortService.queryBykeyword(keyword,versionFlag);
				if(list != null && !list.isEmpty()){
					this.setDepartList(list);
				}
			}
		}else{
			List<DepartFromPort> list = departFromPortService.queryBykeyword(keyword,versionFlag);
			if(list != null && !list.isEmpty()){
				this.setDepartList(list);
			}
		}
		return null;
	}
	
	/**
	 * 航班查询
	 * @return
	 * @throws ServiceException
	 */
	public String flightQuery() throws ServiceException{
		/*if(GeneralUtil.isNotNull(keyword)){
			flightDate = DateUtil.parseDateToString(new Date(), "yyyy-MM-dd");
			DepartFromPort dept = null;
			ArrivalAtPort arrival = null;
			List<SystemDictionary> list = systemDictionaryService.queryByDescription(keyword, versionFlag);
			List<String> threeList = new ArrayList<String>();
			if(list != null && list.size() > 0){
				for(SystemDictionary s:list){
					if(s.getParent() != null){
						if(s.getParent().getName().equals("国内城市三字码") || s.getParent().getName().equals("国际城市三字码")){
							threeList.add(s.getName());
						}
					}
				}
			}

			dept = departFromPortService.queryByFlightNoAndDate(keyword, flightDate,versionFlag);
			arrival = arrivalAtPortService.queryByFlightNoAndDate(keyword, flightDate,versionFlag);
			this.setDepartList(dlist);
			this.setArrivalList(alist);
			if(threeList != null && threeList.size() > 0){
				if(isChufa == 1){
					for(String s:threeList){
						List<DepartFromPort> dlList2 = departFromPortService.queryListByCityAndDate("KMG",s,flightDate,versionFlag);
						if(dlist != null && dlist.size() > 0){
							if(dlList2 != null && dlList2.size() > 0){
								dlist.addAll(dlList2);
							}
							this.setDepartList(dlist);
						}else{
							if(dlList2 != null && dlList2.size() > 0){
								this.setDepartList(dlList2);
							}
						}
					}
				}else{
					for(String s:threeList){
						List<ArrivalAtPort> alist2 = arrivalAtPortService.queryListByCityAndDate(s,"KMG",flightDate,versionFlag);
						if(alist != null && alist.size() > 0){
							if(alist2 != null && alist2.size() > 0){
								alist.addAll(alist2);
							}
							this.setArrivalList(alist);
						}else{
							if(alist2 != null && alist2.size() > 0){
								this.setArrivalList(alist2);
							}
						}
					}
				}
			}
		}*/
		return "flightQuery";
	}
	
	/**
	 * 根据航班号和航班日期查询航班
	 * @return
	 * @throws ServiceException
	 */
	public String queryFlightByNoAndDate() throws ServiceException{
		DepartFromPort departFromPort = departFromPortService.queryByFlightNoAndDate(flightNumber, flightDate,versionFlag);
		departList = new ArrayList<DepartFromPort>();
		departList.add(departFromPort);
		ArrivalAtPort arrivalAtPort = arrivalAtPortService.queryByFlightNoAndDate(flightNumber, flightDate,versionFlag);
		arrivalList = new ArrayList<ArrivalAtPort>();
		arrivalList.add(arrivalAtPort);
		this.setDepartList(departList);
		this.setArrivalList(arrivalList);
		return "flightQueryResultList";
	}

	/**
	 * 根据城市查询航班信息
	 * @return
	 * @throws ServiceException
	 */
	public String queryFlightByCity() throws ServiceException{
		if("KMG".equals(setoutCity)){
			List<DepartFromPort> departList = departFromPortService.queryListByCityAndDate(setoutCity,reachCity,flightDate,versionFlag);
			if(departList != null && !departList.isEmpty()){
				this.setFlightCount(departList.size());
				this.setDepartList(departList);
			}
			
		}else{
			List<ArrivalAtPort> arrivalList = arrivalAtPortService.queryListByCityAndDate(setoutCity,reachCity,flightDate,versionFlag);
			if(arrivalList != null && !arrivalList.isEmpty()){
				this.setFlightCount(arrivalList.size());
				this.setArrivalList(arrivalList);
			}
			
		}
		return "flightQueryResultList";
	}
	
	/**
	 * 根据条件查询航班信息
	 * @return
	 * @throws ServiceException
	 */
	public String queryFlightByConditions() throws ServiceException{
		if("depart".equals(flightFlag)){
			this.setDepartList(departFromPortService.queryByConditions(keyword,flightDate,flightTime));
		}else{
			this.setArrivalList(arrivalAtPortService.queryByConditions(keyword,flightDate,flightTime));
		}
		return "flightQueryResult";
	}
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<BusinessInfo> getBusinessList() {
		return businessList;
	}

	public void setBusinessList(List<BusinessInfo> businessList) {
		this.businessList = businessList;
	}

	public List<FlightCompany> getFlightCompanyList() {
		return flightCompanyList;
	}

	public void setFlightCompanyList(List<FlightCompany> flightCompanyList) {
		this.flightCompanyList = flightCompanyList;
	}

	public String getOrderFlag() {
		return orderFlag;
	}

	public void setOrderFlag(String orderFlag) {
		this.orderFlag = orderFlag;
	}

	public String getFlightFlag() {
		return flightFlag;
	}

	public void setFlightFlag(String flightFlag) {
		this.flightFlag = flightFlag;
	}

	public List<DepartFromPort> getDepartList() {
		return departList;
	}

	public void setDepartList(List<DepartFromPort> departList) {
		this.departList = departList;
	}

	public List<ArrivalAtPort> getArrivalList() {
		return arrivalList;
	}

	public void setArrivalList(List<ArrivalAtPort> arrivalList) {
		this.arrivalList = arrivalList;
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

	public Integer getFlightCount() {
		return flightCount;
	}

	public void setFlightCount(Integer flightCount) {
		this.flightCount = flightCount;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

	public NewsClass getParentNewsClass() {
		return parentNewsClass;
	}

	public void setParentNewsClass(NewsClass parentNewsClass) {
		this.parentNewsClass = parentNewsClass;
	}

	public String getVisitUrl() {
		return visitUrl;
	}

	public void setVisitUrl(String visitUrl) {
		this.visitUrl = visitUrl;
	}

	public NewsClass getNewsClass2() {
		return newsClass2;
	}

	public void setNewsClass2(NewsClass newsClass2) {
		this.newsClass2 = newsClass2;
	}

	public String getAlias2() {
		return alias2;
	}

	public void setAlias2(String alias2) {
		this.alias2 = alias2;
	}

	
	public IBusinessInfoService getBusinessInfoService() {
		return businessInfoService;
	}

	public void setBusinessInfoService(IBusinessInfoService businessInfoService) {
		this.businessInfoService = businessInfoService;
	}

	public IFlightCompanyService getFlightCompanyService() {
		return flightCompanyService;
	}

	public void setFlightCompanyService(IFlightCompanyService flightCompanyService) {
		this.flightCompanyService = flightCompanyService;
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

	public INewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(INewsService newsService) {
		this.newsService = newsService;
	}

	public INewsClassService getNewsClassService() {
		return newsClassService;
	}

	public void setNewsClassService(INewsClassService newsClassService) {
		this.newsClassService = newsClassService;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public List<CommonSearch> getSearchList() {
		return searchList;
	}

	public void setSearchList(List<CommonSearch> searchList) {
		this.searchList = searchList;
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

	public String getBusinessInfoType_id() {
		return businessInfoType_id;
	}

	public void setBusinessInfoType_id(String businessInfoTypeId) {
		businessInfoType_id = businessInfoTypeId;
	}

	public String getLc() {
		return lc;
	}

	public void setLc(String lc) {
		this.lc = lc;
	}

	public String getWz() {
		return wz;
	}

	public void setWz(String wz) {
		this.wz = wz;
	}

	public String getFl() {
		return fl;
	}

	public void setFl(String fl) {
		this.fl = fl;
	}

	public List<BusinessInfo> getBusinessInfos() {
		return businessInfos;
	}

	public void setBusinessInfos(List<BusinessInfo> businessInfos) {
		this.businessInfos = businessInfos;
	}

	public BusinessInfo getBusinessInfo() {
		return businessInfo;
	}

	public void setBusinessInfo(BusinessInfo businessInfo) {
		this.businessInfo = businessInfo;
	}

	public Integer getIsChufa() {
		return isChufa;
	}

	public void setIsChufa(Integer isChufa) {
		this.isChufa = isChufa;
	}

	public List<AirportBusinessType> getBusinessTypes() {
		return businessTypes;
	}

	public void setBusinessTypes(List<AirportBusinessType> businessTypes) {
		this.businessTypes = businessTypes;
	}

	public String getBusinessInfoType_id2() {
		return businessInfoType_id2;
	}

	public void setBusinessInfoType_id2(String businessInfoTypeId2) {
		businessInfoType_id2 = businessInfoTypeId2;
	}

	public String getFlightTime() {
		return flightTime;
	}

	public void setFlightTime(String flightTime) {
		this.flightTime = flightTime;
	}
	
}
