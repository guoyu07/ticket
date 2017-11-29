package com.ticket.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonAnnex;
import com.ticket.pojo.InfoPosition;
import com.ticket.pojo.Member;
import com.ticket.pojo.MemberFocusFlight;
import com.ticket.pojo.News;
import com.ticket.pojo.NewsClass;
import com.ticket.pojo.QuickMenu;
import com.ticket.pojo.QuickMenuMemberSetting;
import com.ticket.pojo.SystemDictionary;
import com.ticket.pojo.SystemFreebackInfo;
import com.ticket.service.IAppService;
import com.ticket.service.IArrivalAtPortService;
import com.ticket.service.IAutoSendMessageService;
import com.ticket.service.IDepartFromPortService;
import com.ticket.service.IEvaluationService;
import com.ticket.service.IInfoPositionService;
import com.ticket.service.IMemberFavoriteService;
import com.ticket.service.IMemberFocusFlightService;
import com.ticket.service.IMemberSendInfoService;
import com.ticket.service.IMemberService;
import com.ticket.service.INewsClassService;
import com.ticket.service.INewsService;
import com.ticket.service.IQuickMenuMemberSettingService;
import com.ticket.service.IQuickMenuService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.service.ISystemFreebackInfoService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SmsUtil;
public class AppServiceImpl extends BaseServiceImpl<Object, String> implements IAppService {
	//新闻类别的业务层
	@Resource private INewsClassService newsClassService = null;
	//新闻信息的业务层
	@Resource private INewsService newsService = null;
	//字典业务层
	@Resource private ISystemDictionaryService systemDictionaryService = null;
	
	//会员信息的业务层
	@Resource IMemberService memberService = null;
	//会员收藏业务层
	@Resource IMemberFavoriteService memberFavoriteService = null;
	//会员设置快捷菜单业务层
	@Resource IQuickMenuMemberSettingService quickMenuMemberSettingService = null;
	//快捷菜单业务层
	@Resource IQuickMenuService quickMenuService = null;
	//会员关注航班业务层
	@Resource IMemberFocusFlightService memberFocusFlightService = null;
	//设施位置的业务层
	@Resource IInfoPositionService infoPositionService = null;
	//离港航班的业务层
	@Resource IDepartFromPortService departFromPortService = null;
	//进港航班的业务层
	@Resource IArrivalAtPortService arrivalAtPortService = null;
	//评价业务层
	@Resource IEvaluationService evaluationService;
	//消息自动推送业务层
	@Resource IAutoSendMessageService autoSendMessageService;
	//系统反馈信息业务层
	@Resource ISystemFreebackInfoService systemFreebackInfoService;
	@Resource IMemberSendInfoService sendInfoService;
	
	@Override
	public String queryLeftMenu() throws Exception {
		try {
			List<NewsClass> newsClassList = newsClassService.queryParentList(versionFlag);
			JSONObject json = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			if(newsClassList != null && !newsClassList.isEmpty()) {
				for(NewsClass nc : newsClassList) {
					JSONObject jsonChild = new JSONObject();
					jsonChild.put("name", nc.getName());
					jsonChild.put("alias", nc.getAlias());
					jsonChild.put("id", nc.getId());
					
					String url = "";
					String typeId = "";
					List<NewsClass> childsList = newsClassService.queryChildNewsClasssByParent(nc.getId());
					//拼装json格式
					if(childsList != null && !childsList.isEmpty()){
						JSONObject jsonSecondChild = null;
						JSONArray jsonSecondArray = new JSONArray();
						for(NewsClass child:childsList){
							jsonSecondChild = new JSONObject();
							jsonSecondChild.put("name", child.getName());
							jsonSecondChild.put("alias", child.getAlias());
							jsonSecondChild.put("id", child.getId());
							jsonSecondChild.put("url", "/airport/"+nc.getAlias()+".ticket");
							jsonSecondChild.put("typeId", child.getListPageRedirectTemplate_id());
							jsonSecondArray.add(jsonSecondChild);
						}
						jsonChild.put("array", jsonSecondArray);
					}else{
						url= "/airport/"+nc.getAlias()+".ticket";
						typeId = nc.getListPageRedirectTemplate_id();
					}
					jsonChild.put("url", url);
					jsonChild.put("typeId", typeId);
					jsonArray.add(jsonChild);
				}
				json.put("array", jsonArray);
				return json.toString();
			}
			return json.toString();
		} catch(Exception e) {
			return "";
		}
	}
	@Override
	public String queryNewsByNewsClassId(String newsClass_id) throws Exception {
		List<News> newsList = newsService.queryListByFront(newsClass_id, 0, 50);
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		if(newsList != null && !newsList.isEmpty()){
			for(News news:newsList){
				JSONObject jsonChild = new JSONObject();
				jsonChild.put("id", news.getId());
				jsonChild.put("title", news.getTitle());
				if(GeneralUtil.isNull(news.getIntroduce())){
					news.setIntroduce("");
				}
				jsonChild.put("introduce", news.getIntroduce());
				jsonArray.add(jsonChild);
			}
			json.put("array", jsonArray);
		}
		return json.toString();
	}
	@Override
	public String QueryNewsById(String news_id) throws ServiceException {
		News news = newsService.queryById(News.class.getSimpleName(), news_id);
		JSONObject json = new JSONObject();
		if(news != null){
			json.put("news", news);
			return json.toString();
		}
		return json.toString();
	}
	@Override
	public String queryClassByAlias(String classAlias) throws ServiceException {
		NewsClass newsClass = newsClassService.queryByAlias(versionFlag, classAlias);
		JSONObject json = new JSONObject();
		if(newsClass != null){
			json.put("newsClass", newsClass);
		}
		return json.toString();
	}
	
	@Override
	public String queryKindRemindContent() throws ServiceException {
		try {
			return DecoderUtil.UtfEncoder(systemDictionaryService.getDescriptByName("kind_remind_content")) ;
		} catch(Exception e) {
			return "";
		}
	}

	public String loginByPhoneAndPwd(String phone,String password) throws ServiceException{
		Member member =  memberService.queryByMobileAndPwd(phone, password, versionFlag);
		JSONObject json = new JSONObject();
		if(member != null){
			json.put("newsClass", member);
		}
		return json.toString();
	}
	@Override
	public String getRightMenuUrl(String member_id) throws ServiceException {
		
		JSONObject json = new JSONObject();
		json.put("personalData", "/airportm_personalSetting.action");//个人资料
		json.put("myFavorite", "/airportm_myFavorite.action");//我的收藏
		Long count = 0l;
		Member member = memberService.queryById(Member.class.getSimpleName(), member_id);
		if(member == null){
			return null;
		}
		count = memberFavoriteService.queryFavoriteCount(member_id,versionFlag);//统计收藏数量
		if(GeneralUtil.isNotNull(member.getImages())){
			json.put("memberFace", member.getImages());
		}else{
			json.put("memberFace", "/template/wap/images/no_avatar.png");
		}
		if(GeneralUtil.isNotNull(member.getNickName())){
			json.put("nickName", member.getNickName());
		}else{
			json.put("nickName", "未设置");
		}
		
		json.put("noReadMessageCount", sendInfoService.numberOfUnread(member_id));
		json.put("myFavoriteCount", count);
		
		json.put("myEvaluationCount", evaluationService.myEvaluationCount(new Member(member_id)));
		json.put("myEvaluationUrl", "/evaluation_myEvaluatePage.action");
		
		json.put("myFoucus", "/airportm_myFocusOn.action");//我的关注
		json.put("myOrder", "/bjdjOrderTemplate_allOrder.action");//我的订单
		json.put("VIPMember", "/airportm_VIPMember.action");//VIP会员
		json.put("myFlightRecord", "/airportm_myFlightRecord.action");//我的飞行记录
		JSONArray menuArray = new JSONArray();
		List<QuickMenuMemberSetting> frontMenuList = quickMenuMemberSettingService.queryQuickMenuListByFront(member_id,6,versionFlag);
		if(frontMenuList != null && !frontMenuList.isEmpty()){
			for(QuickMenuMemberSetting obj:frontMenuList){
				JSONObject menuJson = new JSONObject();
				List<CommonAnnex> menulist = commonAnnexService.queryListByEntityId(obj.getQuickMenu_id(), 1, 1);
				QuickMenu quickMenu = quickMenuService.queryById(QuickMenu.class.getSimpleName(),obj.getQuickMenu_id());
				CommonAnnex menuAnnex = menulist.get(0);
				menuJson.put("menuUrl", quickMenu.getUrl());
				menuJson.put("menuName", quickMenu.getName());
				menuJson.put("menuPhoto", menuAnnex.getAnnexPath());
				menuJson.put("id", obj.getId());
				menuArray.add(menuJson);
			}
		}
		json.put("quickMenu", menuArray);
		return json.toJSONString();
	}
	@Override
	public boolean deleteQuickMenu(String id) throws ServiceException {
		try {
			quickMenuMemberSettingService.batchRealDelete(QuickMenuMemberSetting.class, id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	@Override
	public List<SystemDictionary> queryCheckOnLineNotice() throws ServiceException {
		List<SystemDictionary> list = systemDictionaryService.querySubByParentValue("checkOnLine");
		return list;
	}
	@Override
	public String focusFlight(String member_id, String flightNumber,
			String flightDate, String flightState, String memberRole,String setoutCity,String reachCity)
			throws ServiceException {
		JSONArray array = new JSONArray();
		JSONObject json = new JSONObject();
		String flag = "";
		String reason = "";
		MemberFocusFlight memberFocusFlight = null;
		memberFocusFlight = memberFocusFlightService.queryAppMemberFocus(member_id, flightNumber, flightDate, flightState, memberRole);
		if(memberFocusFlight != null){
			flag = "false";
			reason = "exists";
		}else{
			try {
				memberFocusFlight = memberFocusFlightService.persist(member_id, flightNumber, flightDate, memberRole, flightState, 
						null, null, null, null, null, null, null, null, null);
				flag = "true";
				reason = "save success";
			} catch (Exception e) {
				flag = "false";
				reason = "save failed";
			}
		}
		SystemDictionary setout = systemDictionaryService.getByName(setoutCity);
		SystemDictionary reach = systemDictionaryService.getByName(reachCity);
		json.put("flag", flag);
		json.put("reason", reason);
		json.put("setoutCity", setout.getDescription());
		json.put("reachCity", reach.getDescription());
		array.add(json);
		return array.toJSONString();
	}
	@Override
	public String savePosition(String name, String positionAlias,
			String longitude, String latitude, String versionFlag)
			throws ServiceException {
		JSONArray array = new JSONArray();
		JSONObject json = new JSONObject();
		String flag = "";
		String reason = "";
		if(infoPositionService.validateAliasExists(positionAlias)){
			flag = "false";
			reason = "positionAlias.exists";
		}else{
			InfoPosition infoPosition = new InfoPosition();
			infoPosition.setName(name);
			infoPosition.setPositionAlias(positionAlias);
			infoPosition.setLongitude(longitude);
			infoPosition.setLatitude(latitude);
			
			try {
				infoPositionService.persist(infoPosition);
				flag = "true";
				reason = "save success";
			} catch (Exception e) {
				e.printStackTrace();
				flag = "false";
				reason = "save failed";
			}
			
		}
		json.put("flag", flag);
		json.put("reason", reason);
		array.add(json);
		return array.toJSONString();
	}
	
	@Override
	public String deletePositionById(String id) throws ServiceException {
		JSONArray array = new JSONArray();
		JSONObject json = new JSONObject();
		InfoPosition infoPosition = infoPositionService.queryById(InfoPosition.class.getSimpleName(), id);
		if(infoPosition == null){
			json.put("flag", "false");
			json.put("reason", "entity.notExsits");
			array.add(json);
		}else{
			try {
				infoPositionService.batchRealDelete(InfoPosition.class, id);
				json.put("flag", "true");
				json.put("reason", "delete Success");
				array.add(json);
			} catch (Exception e) {
				json.put("flag", "false");
				json.put("reason", "delete Failed");
				array.add(json);
			}
		}
		return array.toJSONString();
	}
	
	@Override
	public String focusFlightByCheckin(String memberId, String flightNumber,
			String flightDate, String mobile, String seatNo, String ticketNo,
			String couponId) throws ServiceException {
		JSONArray array = new JSONArray();
		JSONObject json = new JSONObject();
		String flag = "";
		String reason = "";
		MemberFocusFlight memberFocusFlight = null;
		memberFocusFlight = memberFocusFlightService.queryAppMemberFocus(memberId, flightNumber, flightDate, "depart", "seat");
		if(memberFocusFlight != null){
			memberFocusFlight.setMobile(mobile);
			memberFocusFlight.setSeatNo(seatNo);
			memberFocusFlight.setTicketNo(ticketNo);
			memberFocusFlight.setCouponId(couponId);
			
			memberFocusFlightService.merge(memberFocusFlight);
			flag = "true";
			reason = "update success";
			autoSendMessageService.sendMsgAtSucCheck(flightNumber, flightDate, memberId, ticketNo);
		}else{
			try {
				memberFocusFlight = memberFocusFlightService.persist(memberId, flightNumber, flightDate, "seat", "depart", 
						null, null, null, null, mobile, seatNo, ticketNo, couponId, null);
				autoSendMessageService.sendMsgAtSucCheck(flightNumber, flightDate, memberId, ticketNo);
				flag = "true";
				reason = "save success";
			} catch (Exception e) {
				flag = "false";
				reason = "save failed";
			}
		}
		json.put("flag", flag);
		json.put("reason", reason);
		array.add(json);
		return array.toJSONString();
	}
	
	@Override
	public String sendSms(String mobile, String smsContent) throws ServiceException {
		JSONArray array = new JSONArray();
		JSONObject json = new JSONObject();
		String flag = "";
		String reason = "";
		try {
			SmsUtil.sendSms(mobile, smsContent);
			flag = "true";
			reason = "send success";
		} catch (Exception e) {
			flag = "true";
			reason = "send failed";
		}
		json.put("flag", flag);
		json.put("reason", reason);
		array.add(json);
		return array.toJSONString();
	}
	
	@Override
	public String queryAllQuickMenu() throws ServiceException {
		try {
			JSONArray array = new JSONArray();
			JSONObject json =new JSONObject();
			List<SystemDictionary> sysDicList = systemDictionaryService.querySubByParentValue("quick_menu");
			if (sysDicList != null && !sysDicList.isEmpty()) {
				JSONArray subArray = new JSONArray();
				JSONObject subJson = null;
				for(SystemDictionary systemDictionary:sysDicList){
					subJson = new JSONObject();
					subJson.put("typeId", systemDictionary.getId());
					List<QuickMenu> menuList = quickMenuService.queryByMenuId(systemDictionary.getId(), versionFlag);
					if(menuList != null && !menuList.isEmpty()){
						JSONArray menuArray = new JSONArray();
						JSONObject menuJson = null;
						for(QuickMenu quickMenu : menuList){
							List<CommonAnnex> list = commonAnnexService.queryListByEntityId(quickMenu.getId(), 1, 1);
							menuJson = new JSONObject();
							if(list != null && !list.isEmpty()) {
								CommonAnnex annex = list.get(0);
								menuJson.put("imgUrl", annex.getAnnexPath());
							}
							menuJson.put("id", quickMenu.getId());
							menuJson.put("name", quickMenu.getName());
							menuJson.put("url", quickMenu.getUrl());
							menuJson.put("isDefaultShow", quickMenu.getIsDefaultShow());
							menuJson.put("defaultShowPosition", quickMenu.getDefaultShowPosition());
							menuArray.add(menuJson);
						}
						subJson.put("array",menuArray);
					}
					subArray.add(subJson);
				}
				json.put("array",subArray);
			}
			array.add(json);
			return array.toJSONString();
		} catch (Exception e) {
			return null;
		}
	}
	@Override
	public String saveSysFreebackInfo(String name, String mobile, String url, String content) throws ServiceException {
		JSONObject json = new JSONObject();
		try {
			SystemFreebackInfo systemFreebackInfo = new SystemFreebackInfo();
			systemFreebackInfo.setName(name);
			systemFreebackInfo.setPhone(mobile);
			systemFreebackInfo.setUrl(url);
			systemFreebackInfo.setContent(DecoderUtil.UtfDecoder(content));
			systemFreebackInfoService.persist(systemFreebackInfo);
			json.put("status", "1");
			json.put("message", "保存成功！");
		} catch (Exception e) {
			json.put("status", "1");
			json.put("message", "保存失败："+e.getMessage());
		}
		return json.toString();
	}
	
}