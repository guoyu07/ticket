package com.ticket.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;

import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.AirportInfo;
import com.ticket.pojo.CityHistorySelect;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.Member;
import com.ticket.service.IAirportInfoService;
import com.ticket.service.ICityHistorySelectService;
import com.ticket.service.ITicketOrderService;
import com.ticket.util.DecoderUtil;

/**
 * 机票预订的请求控制器
 * @author tuyou
 *
 */
public class TicketOrderAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	@Resource private ITicketOrderService ticketOrderService = null;
	@Resource private IAirportInfoService airportInfoService = null;
	@Resource private ICityHistorySelectService cityHistorySelectService = null;
	//出发时间
	private String date1 = null;
	//返程时间
	private String date2 = null;
	//出发城市
	private String city1 = null;
	//到达城市
	private String city2 = null;
	
	
	//机票类型（国内还是国际）
	private String ticketType = null;
	//往返类型（单程或者往返）
	private String buyType = null;
	//城市三字码
	private String cityCode = null;
	//城市中文名称
	private String cityName = null;
	//城市类型（出发或者到达）
	private String cityType = null;
	
	/**
	 * 航班信息页面(接口原因，暂时不做)
	 * @return
	 */
	public String flightInformationPage(){
		return "flightInformationPage";
	}
	
	/**
	 * 机票信息页面(接口原因，暂时不做)
	 * @return
	 */
	public String ticketInformationPage(){
		return "ticketInformationPage";
	}
	
	/**
	 * 机票预订页面-国际国内多程（接口原因，暂时不做）
	 * @return
	 */
	public String ticketOrderPage(){
		return "ticketOrderPage";
	}
	
	/**
	 * 选择目的地页面
	 * @return
	 */
	public String selectCity(){
		return "selectCity";
	}
	
	/**
	 * 机票预订-往返
	 * @return
	 */
	public String goAndBack(){
		return "ticketGoAndBack";
	}
	
	/**
	 * 机票预订-首页（默认单程）
	 * @return
	 */
	public String execute(){
		return "ticketSingle";
	}
	
	/**
	 * 机票预订-国际单程
	 * @return
	 * @throws Exception
	 */
	public String international() throws Exception {
		return "ticketInternational";
	}
	
	/**
	 * 机票预订-国际返程
	 * @return
	 * @throws Exception
	 */
	public String internationalGoAndBack() throws Exception {
		return "ticketInternationalGoAndBack";
	}

	/***
	 * 设置城市
	 * @return
	 * @throws ServiceException 
	 */
	@SuppressWarnings("unchecked")
	public String setCitySelect() throws ServiceException {
		if("domestic".equals(ticketType)) {//国内
			if("single".equals(buyType)) {//单程
				if("departure".equals(cityType)) {//出发
//					getSession().put("domesticDepartureName", DecoderUtil.UtfDecoder(cityName));
//					getSession().put("domesticDepartureCode", DecoderUtil.UtfDecoder(cityCode));
					getSession().put(ContextConstants.ORGCITY, DecoderUtil.UtfDecoder(cityName));
					getSession().put(ContextConstants.ORGCITYCODE, DecoderUtil.UtfDecoder(cityCode));
				} else if("arrival".equals(cityType)) {//到达
//					getSession().put("domesticArrivalName", DecoderUtil.UtfDecoder(cityName));
//					getSession().put("domesticArrivalCode", DecoderUtil.UtfDecoder(cityCode));
					getSession().put(ContextConstants.DESCITY, DecoderUtil.UtfDecoder(cityName));
					getSession().put(ContextConstants.DESCITYCODE, DecoderUtil.UtfDecoder(cityCode));
				}
			} else if("goAndBack".equals(buyType)) {//往返
				if("departure".equals(cityType)) {//出发
					getSession().put("domesticDepartureName", DecoderUtil.UtfDecoder(cityName));
					getSession().put("domesticDepartureCode", DecoderUtil.UtfDecoder(cityCode));
				} else if("arrival".equals(cityType)) {//到达
					getSession().put("domesticArrivalName", DecoderUtil.UtfDecoder(cityName));
					getSession().put("domesticArrivalCode", DecoderUtil.UtfDecoder(cityCode));
				}
			}
		} else if("international".equals(ticketType)) {//国际
			if("single".equals(buyType)) {//单程
				if("departure".equals(cityType)) {//出发
					getSession().put("internationalDepartureName", DecoderUtil.UtfDecoder(cityName));
					getSession().put("internationalDepartureCode", DecoderUtil.UtfDecoder(cityCode));
				} else if("arrival".equals(cityType)) {//到达
					getSession().put("internationalArrivalName", DecoderUtil.UtfDecoder(cityName));
					getSession().put("internationalArrivalCode", DecoderUtil.UtfDecoder(cityCode));
				}
			} else if("goAndBack".equals(buyType)) {//往返
				if("departure".equals(cityType)) {//出发
					getSession().put("internationalDepartureName", DecoderUtil.UtfDecoder(cityName));
					getSession().put("internationalDepartureCode", DecoderUtil.UtfDecoder(cityCode));
				} else if("arrival".equals(cityType)) {//到达
					getSession().put("internationalArrivalName", DecoderUtil.UtfDecoder(cityName));
					getSession().put("internationalArrivalCode", DecoderUtil.UtfDecoder(cityCode));
				}
			}
		}
		AirportInfo ap = airportInfoService.queryByThreeCode(cityCode, versionFlag);
		//SystemDictionary systemDictionary = systemDictionaryService.queryByName(keyword,versionFlag);
		CityHistorySelect cityHistorySelect = new CityHistorySelect();
		Member member = (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
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
							 if(historySelect.getCity_id().equals(ap.getId())){
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
		return null;
	}
	
	/**
	 * 根据城市信息获取携程的URL
	 * @return
	 * @throws Exception
	 */
	public String parseInfoToGetUrl() throws Exception {
		data = ticketOrderService.generateCtripRedirectUrl(city1, city2, date1, date2, ticketType, buyType, cityType);
		return TEXT;
	}
	
	public String getDate1() {
		return date1;
	}

	public void setDate1(String date1) {
		this.date1 = date1;
	}

	public String getDate2() {
		return date2;
	}

	public void setDate2(String date2) {
		this.date2 = date2;
	}

	public String getCity1() {
		return city1;
	}

	public void setCity1(String city1) {
		this.city1 = city1;
	}

	public String getCity2() {
		return city2;
	}

	public void setCity2(String city2) {
		this.city2 = city2;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getBuyType() {
		return buyType;
	}

	public void setBuyType(String buyType) {
		this.buyType = buyType;
	}

	public String getCityType() {
		return cityType;
	}

	public void setCityType(String cityType) {
		this.cityType = cityType;
	}
}
