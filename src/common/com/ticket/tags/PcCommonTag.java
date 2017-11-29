package com.ticket.tags;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.ticket.constants.ContextConstants;
import com.ticket.pojo.BusinessInfo;
import com.ticket.pojo.DepartFromPort;
import com.ticket.pojo.FlightCompany;
import com.ticket.pojo.HotLinePhone;
import com.ticket.pojo.News;
import com.ticket.pojo.NewsClass;
import com.ticket.pojo.TrafficLine;
import com.ticket.pojo.TrafficLineAndStation;
import com.ticket.pojo.TrafficType;
import com.ticket.service.IBusinessInfoService;
import com.ticket.service.IDepartFromPortService;
import com.ticket.service.IFlightCompanyService;
import com.ticket.service.IHotLinePhoneService;
import com.ticket.service.INewsClassService;
import com.ticket.service.INewsService;
import com.ticket.service.ITrafficLineAndStationService;
import com.ticket.service.ITrafficLineService;
import com.ticket.service.ITrafficTypeService;
import com.ticket.util.SpringUtil;

public class PcCommonTag extends TagSupport{

	private static final long serialVersionUID = 1L;
	private String versionFlag = ContextConstants.VERSION_FLAG_OF_CHINESE; // 语言版本标识,默认为中文版.
	
	// 机场商家的业务层
	private static final IBusinessInfoService businessInfoService = (IBusinessInfoService) SpringUtil.getObjectFromSpring("businessInfoService");
	// 航空公司的业务层
	private static final IFlightCompanyService flightCompanyService = (IFlightCompanyService) SpringUtil.getObjectFromSpring("flightCompanyService");
	// 离港航班的业务层
	private static final IDepartFromPortService departFromPortService = (IDepartFromPortService) SpringUtil.getObjectFromSpring("departFromPortService");
	// 新闻类别的业务层
	private static final INewsClassService newsClassService = (INewsClassService) SpringUtil.getObjectFromSpring("newsClassService");
	// 新闻信息的业务层
	private static final INewsService newsService = (INewsService) SpringUtil.getObjectFromSpring("newsService");
	//交通类别的业务层
	private static final ITrafficTypeService trafficTypeService = (ITrafficTypeService) SpringUtil.getObjectFromSpring("trafficTypeService");
	//交通路线的业务层
	private static final ITrafficLineService trafficLineService = (ITrafficLineService) SpringUtil.getObjectFromSpring("trafficLineService");
	//交通路线类别关联的业务层
	private static final ITrafficLineAndStationService trafficLineAndStationService = (ITrafficLineAndStationService) SpringUtil.getObjectFromSpring("trafficLineAndStationService");
	//热线电话的业务层
	private static final IHotLinePhoneService hotLinePhoneService = (IHotLinePhoneService) SpringUtil.getObjectFromSpring("hotLinePhoneService");
	
	private String type = null;
	private String value = null;
	private Integer size = null;

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
			
			if("showCommendBusiness".equals(type)){//推荐商家
				List<BusinessInfo> list = businessInfoService.queryListByCommend(size,versionFlag);
				if(list != null && !list.isEmpty()){
					request.setAttribute(type, list);
				}else{
					request.removeAttribute(type);
				}
			} else if("flightCompanyList".equals(type)){//航空公司列表
				List<FlightCompany> list = flightCompanyService.queryList(size,versionFlag);
				if(list != null && !list.isEmpty()){
					request.setAttribute(type, list);
				}else{
					request.removeAttribute(type);
				}
			} else if("flightInfoList".equals(type)){//航班信息列表
				List<DepartFromPort> list = departFromPortService.queryList(size,versionFlag);
				if(list != null && !list.isEmpty()){
					request.setAttribute(type, list);
				}else{
					request.removeAttribute(type);
				}
			} else if("newsClassByParentAlias".equals(type)){//根据父类别查询新闻类别列表
				NewsClass newsClass = newsClassService.queryByAlias(versionFlag, value);
				List<NewsClass> list = newsClassService.queryChildNewsClasssByParent(newsClass.getId());
				if(list != null && !list.isEmpty()){
					request.setAttribute(type, list);
				}else{
					request.removeAttribute(type);
				}
			} else if("newsListByTypeAlias".equals(type)){ //根据类别id查询新闻信息列表
				NewsClass newsClass = newsClassService.queryByAlias(versionFlag, value);
				List<News> list = newsService.queryListByType(newsClass.getId(),size,versionFlag);
				if(list != null && !list.isEmpty()){
					request.setAttribute(type, list);
				}else{
					request.removeAttribute(type);
				}
			} else if("newsObj".equals(type)){   //根据id查询新闻信息
				News news = newsService.queryById(News.class.getSimpleName(), value);
				if(news != null){
					request.setAttribute(type, news);
				}else{
					request.removeAttribute(type);
				}
			} else if("queryNewsByTitle".equals(type)){//根据标题查询新闻实体
				News news = newsService.queryByTitle(value,versionFlag);
				if(news != null){
					request.setAttribute(type, news);
				}else{
					request.removeAttribute(type);
				}
			}  else if ("trafficTypeList".equals(type)) { //查询路线类别列表
				List<TrafficType> list = trafficTypeService .queryList(versionFlag);
				if (list != null && !list.isEmpty()) {
					request.setAttribute(type, list);
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
			} else if ("hotLineList".equals(type)) { //热线电话列表
				List<HotLinePhone> list = hotLinePhoneService.queryList(versionFlag);
				if (list != null && !list.isEmpty()) {
					request.setAttribute(type, list);
				} else {
					request.removeAttribute(type);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
	
	
}
