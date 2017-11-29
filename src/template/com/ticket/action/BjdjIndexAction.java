package com.ticket.action;

import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.Advert;
import com.ticket.pojo.AdvertType;
import com.ticket.pojo.ArrivalAtPort;
import com.ticket.pojo.BjdjPage;
import com.ticket.pojo.BjdjServiceItem;
import com.ticket.pojo.BjdjServicePackage;
import com.ticket.pojo.CommonAnnex;
import com.ticket.pojo.DepartFromPort;
import com.ticket.pojo.InfoPosition;
import com.ticket.pojo.Member;
import com.ticket.pojo.News;
import com.ticket.service.IAdvertService;
import com.ticket.service.IArrivalAtPortService;
import com.ticket.service.IBjdjHallService;
import com.ticket.service.IBjdjServiceItemService;
import com.ticket.service.IBjdjServicePackageItemService;
import com.ticket.service.IBjdjServicePackageService;
import com.ticket.service.ICommonAnnexService;
import com.ticket.service.IDepartFromPortService;
import com.ticket.service.IInfoPositionService;
import com.ticket.service.IMemberFavoriteService;
import com.ticket.service.INewsClassService;
import com.ticket.service.INewsService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.util.GeneralUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @Description：便捷登机首页前台控制器
 * @author：涂有
 * @date 2015年10月27日 下午2:41:08
 */
public class BjdjIndexAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private ISystemDictionaryService dictionaryService;
	@Resource
	private IBjdjHallService hallService;
	@Resource
	private INewsService newsService;
	@Resource
	private INewsClassService newsClassService;
	@Resource
	private IAdvertService advertService;
	@Resource
	private ICommonAnnexService commonAnnexService;
	@Resource
	private IMemberFavoriteService memberFavoriteService;
	@Resource
	private IBjdjServiceItemService serviceItemService;
	@Resource
	private IBjdjServicePackageService servicePackageService;
	@Resource
	private IBjdjServicePackageItemService packageItemService;
	//离港航班Service
	@Resource 
	private IDepartFromPortService departFromPortService;
	//到港航班Service
	@Resource 
	private IArrivalAtPortService arrivalAtPortService;
	private IInfoPositionService infoPositionService;
	
	private String type;

	private String flightNumber;

	private String flightDate;
	
	private String bjdjPage_id;

	/**
	 * 进入便捷登机首页
	 */
	@Override
	public String execute() throws Exception {
		
		if("newAdd".equals(type)){
			
			if(getSession().get(ContextConstants.SCOPE_MEMBER) != null){
				
				return "confirmPage";
			}
		}
		
		//获得便捷登机的价格
		BjdjServicePackage package1 = servicePackageService.getMinPriceByBjdjUrl("#");
		ActionContext.getContext().put("package1", package1);
		
		//获取所有的便捷登机项目
		List<BjdjServiceItem> items = serviceItemService.get(package1);
		ActionContext.getContext().put("bjdj_items", items);
		
		BjdjPage bjdjPage = package1.getBjdjPage();
		
		String service_phone = bjdjPage.getServicePhone();
		ActionContext.getContext().put("service_phone", service_phone);
		
		String infopositionAlias = bjdjPage.getInfoPositionAlias();
		ActionContext.getContext().put("infoPositionAlias", infopositionAlias);
		
		//获取便捷登机首页上的链接
		//介绍
		News serviceProfile = bjdjPage.getServiceProfile();
		Long visitUrl = serviceProfile.getStatus().getVisitUrl();
		ActionContext.getContext().put("serviceProfile", visitUrl);
		
		//流程
		News serviceFlow = bjdjPage.getServiceFlow();
		Long visitUrl1 = serviceFlow.getStatus().getVisitUrl();
		ActionContext.getContext().put("serviceFlow", visitUrl1);
		
		//使用条款
		News useSerms = bjdjPage.getUseSerms();
		Long visitUrl2 = useSerms.getStatus().getVisitUrl();
		ActionContext.getContext().put("useSerms", visitUrl2);
		
		//首页轮播图片
		AdvertType advertType = bjdjPage.getAdvertType();
		String name = advertType.getName();
		ActionContext.getContext().put("advertType", name);
		
		//机场服务热线
//		String service_phone = dictionaryService.getValueByName("service_phone");
//		ActionContext.getContext().put("service_phone", service_phone);
		
		if(GeneralUtil.isNotNull(bjdjPage_id)){
			ActionContext.getContext().put("bjdjPage_id", bjdjPage_id);
		}
		
		return SUCCESS;
	}
	
	/**
	 * 搜索指定搜索词进入便捷登机首页
	 * @return
	 * @throws ServiceException
	 */
	public String indexAjax() throws ServiceException{
		if("newAdd".equals(type)){
			
			if(getSession().get(ContextConstants.SCOPE_MEMBER) != null){
				return "confirmPageAjax";
			}
		}
		
		//获得便捷登机的价格
		BjdjServicePackage package1 = servicePackageService.getMinPriceByBjdjPage(bjdjPage_id);
		ActionContext.getContext().put("package1", package1);
		
		BjdjPage bjdjPage = package1.getBjdjPage();
		
		//获取所有的便捷登机项目
		List<BjdjServiceItem> items = serviceItemService.get(package1);
		ActionContext.getContext().put("bjdj_items", items);
		
		String service_phone = bjdjPage.getServicePhone();
		ActionContext.getContext().put("service_phone", service_phone);
		
		String infopositionAlias = bjdjPage.getInfoPositionAlias();
		ActionContext.getContext().put("infoPositionAlias", infopositionAlias);
		
		//获取便捷登机首页上的链接
		//介绍
		News serviceProfile = bjdjPage.getServiceProfile();
		Long visitUrl = serviceProfile.getStatus().getVisitUrl();
		ActionContext.getContext().put("serviceProfile", visitUrl);
				
		//流程
		News serviceFlow = bjdjPage.getServiceFlow();
		Long visitUrl1 = serviceFlow.getStatus().getVisitUrl();
		ActionContext.getContext().put("serviceFlow", visitUrl1);
				
		//使用条款
		News useSerms = bjdjPage.getUseSerms();
		Long visitUrl2 = useSerms.getStatus().getVisitUrl();
		ActionContext.getContext().put("useSerms", visitUrl2);
		
		//首页轮播图片
		AdvertType advertType = bjdjPage.getAdvertType();
		String name = advertType.getName();
		ActionContext.getContext().put("advertType", name);
		//机场服务热线
//		String service_phone = dictionaryService.getValueByName("service_phone");
//		ActionContext.getContext().put("service_phone", service_phone);
		
		if(GeneralUtil.isNotNull(bjdjPage_id)){
			ActionContext.getContext().put("bjdjPage_id", bjdjPage_id);
		}
		return "indexAjax";
	}
	
	/**
	 * @Description：返回app接口数据
	 * @return
	 * @throws ServiceException 
	 */
	public String appInterface() throws ServiceException{
		
		JSONObject jsonObject = new JSONObject();
		try {
			//查询广告列表
			List<Advert> advertList = advertService.queryList(versionFlag, "便捷登机首页", 10);
	//		jsonObject.put("advertList", advertList);
			//查询广告附件图片
			if(advertList != null){
				
				JSONArray jsonArray = new JSONArray();
				for(Advert advert : advertList){
					
					List<CommonAnnex> annex = commonAnnexService.queryListByEntityId(advert.getId(), 1, size);
					if(annex != null){
						
						jsonArray.add(annex.get(0));
					}
				}
				jsonObject.put("advertList", jsonArray);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			data = AjaxData.responseError("获取信息失败");
		}
		BjdjServicePackage package1 = null;
		if(GeneralUtil.isNotNull(bjdjPage_id)){//如果是隐藏的
			package1 = servicePackageService.getMinPriceByBjdjPage(bjdjPage_id);
		}else{
			package1 = servicePackageService.getMinPriceByBjdjUrl("#");
		}
		
		
		//获得便捷登机的价格
//		BjdjServicePackage package1 = servicePackageService.getMinPrice();
		jsonObject.put("package", package1);
		
		//获取所有的便捷登机项目
		List<BjdjServiceItem> items = serviceItemService.get(package1);
		jsonObject.put("bjdj_items", items);
		
		BjdjPage bjdjPage = package1.getBjdjPage();
		
		//机场服务热线
//		String service_phone = dictionaryService.getValueByName("service_phone");
		String service_phone = bjdjPage.getServicePhone();
		jsonObject.put("service_phone", service_phone);
		
		//获取便捷登机首页上的链接
		//介绍
		News serviceProfile = bjdjPage.getServiceProfile();
		Long visitUrl = serviceProfile.getStatus().getVisitUrl();
						
		//流程
		News serviceFlow = bjdjPage.getServiceFlow();
		Long visitUrl1 = serviceFlow.getStatus().getVisitUrl();
						
		//使用条款
		News useSerms = bjdjPage.getUseSerms();
		Long visitUrl2 = useSerms.getStatus().getVisitUrl();
		
		//介绍页链接
		String[] hrefs = null;
		if(GeneralUtil.isNotNull(bjdjPage_id)){
			hrefs = new String[]{"/airport/"+visitUrl+".ticket?bjdjPage_id=" + bjdjPage_id, "/airport/"+visitUrl1+".ticket?bjdjPage_id=" + bjdjPage_id, "/airport/"+visitUrl2+".ticket"};
		}else{
			hrefs = new String[]{"/airport/"+visitUrl+".ticket", "/airport/"+visitUrl1+".ticket", "/airport/"+visitUrl2+".ticket"};
		}
		jsonObject.put("hrefs", hrefs);
		
		//是否收藏
		Member member = (Member)getSession().get(ContextConstants.SCOPE_MEMBER);
		if(member == null){
			
			jsonObject.put("isFavorite", false);
		}else if(memberFavoriteService.queryByTitleAndUrl("便捷登机", "bjdj.action", versionFlag) != null){
			
			jsonObject.put("isFavorite", true);
		}else if(memberFavoriteService.queryByTitleAndUrl("便捷登机", "bjdj_indexAjax.action?bjdjPage_id=" + bjdjPage_id, versionFlag) != null){
			jsonObject.put("isFavorite", true);
		}else{
			
			jsonObject.put("isFavorite", false);
		}
		
		String alias = bjdjPage.getInfoPositionAlias();
		if(GeneralUtil.isNotNull(alias)){
			//导航
			InfoPosition infoPosition = infoPositionService.queryByAlias(alias);
			String[] latitudes = infoPosition.getLatitude().split(",");
			String[] longitudes = infoPosition.getLongitude().split(",");
			String latitude = latitudes[0];
			String longitude = longitudes[0];
			JSONObject nav = new JSONObject();
			nav.put("latitude", latitude);
			nav.put("longitude", longitude);
			nav.put("name", infoPosition.getName());
			nav.put("floorNumber", infoPosition.getFloorNumber());
			jsonObject.put("nav", nav);
		}else{
			JSONObject nav = new JSONObject();
			nav.put("latitude", "");
			nav.put("longitude", "");
			nav.put("name", "");
			nav.put("floorNumber", "");
			jsonObject.put("nav", nav);
		}

		data = jsonObject.toString();
		return TEXT;
	}
	
	/**
	 * @Description：首页的图片
	 * @date 2015年12月16日 上午1:02:03
	 * @return
	 */
	public String indexImageInterface(){
		
		JSONObject jsonObject = new JSONObject();
		try {
			//查询广告列表
			List<Advert> advertList = advertService.queryList(versionFlag, "首页图片", 10);
			jsonObject.put("advertList", advertList);
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
	 * @Description：首页的图片
	 * @date 2015年12月16日 上午1:02:03
	 * @return
	 */
	public String indexImageInterface2(){
		
		JSONObject jsonObject = new JSONObject();
		try {
			//查询广告列表
			List<Advert> advertList = advertService.queryList(versionFlag, "首页图片", 10);
			jsonObject.put("advertList", advertList);
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
		
		data = AjaxData.responseSuccess(jsonObject);
		return JSON;
	}
	
	/**
	 * 根据航班编号和日期查询航班信息
	 * @return
	 * @throws ServiceException
	 */
	public String queryByFlightNoAndDateInterface() throws ServiceException {
		DepartFromPort departFromPort = departFromPortService.queryByFlightNoAndDate(flightNumber, flightDate,versionFlag);
		ArrivalAtPort arrivalAtPort = arrivalAtPortService.queryByFlightNoAndDate(flightNumber, flightDate,versionFlag);
		
		JSONObject json = new JSONObject();
		json.put("DepartFromPort", departFromPort);
		json.put("ArrivalAtPort", arrivalAtPort);
		data = json.toString();
		return TEXT;
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

	public ISystemDictionaryService getDictionaryService() {
		return dictionaryService;
	}

	public void setDictionaryService(ISystemDictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	public IBjdjHallService getHallService() {
		return hallService;
	}

	public void setHallService(IBjdjHallService hallService) {
		this.hallService = hallService;
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

	public IAdvertService getAdvertService() {
		return advertService;
	}

	public void setAdvertService(IAdvertService advertService) {
		this.advertService = advertService;
	}

	public ICommonAnnexService getCommonAnnexService() {
		return commonAnnexService;
	}

	public void setCommonAnnexService(ICommonAnnexService commonAnnexService) {
		this.commonAnnexService = commonAnnexService;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public IInfoPositionService getInfoPositionService() {
		return infoPositionService;
	}

	public void setInfoPositionService(IInfoPositionService infoPositionService) {
		this.infoPositionService = infoPositionService;
	}

	public String getBjdjPage_id() {
		return bjdjPage_id;
	}

	public void setBjdjPage_id(String bjdjPage_id) {
		this.bjdjPage_id = bjdjPage_id;
	}

	public IMemberFavoriteService getMemberFavoriteService() {
		return memberFavoriteService;
	}

	public void setMemberFavoriteService(
			IMemberFavoriteService memberFavoriteService) {
		this.memberFavoriteService = memberFavoriteService;
	}
}
