package com.ticket.serviceImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.Member;
import com.ticket.service.ITicketOrderService;
import com.ticket.util.GeneralUtil;

/**
 * 机票预订业务接口
 * @author haishui
 *
 */
public class TicketOrderServiceImpl extends BaseServiceImpl<Member, String> implements ITicketOrderService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(TicketOrderServiceImpl.class);
	
	/**
	 * 联盟ID
	 */
	private static final String ALLIANCEID = "303758";
	/**
	 * 代理ID
	 */
	private static final String SID = "776936";

	@Override
	public String generateCtripRedirectUrl(String city1, String city2, String date1, String date2, String ticketType, String buyType, String cityType) throws ServiceException {
		try {
			String returnUrl = "#";//待返回的URL
			
			if("domestic".equals(ticketType)) {//国内
				//往返
				if("goAndBack".equals(buyType)) {
					returnUrl = "http://m.ctrip.com/html5/Flight/"+city1+"-"+city2+"-d.html?Ddate1="+date1+"&Ddate2="+date2+"&allianceid="+ALLIANCEID+"&sid="+SID;
				} 
				//单程
				else if("single".equals(buyType)) {
					returnUrl = "http://m.ctrip.com/html5/Flight/"+city1+"-"+city2+"-day-1.html?Ddate1="+date1+"&allianceid="+ALLIANCEID+"&sid="+SID;
				}
			} else if("international".equals(ticketType)) {//国际
				//往返
				if(GeneralUtil.isNotNull(city1) && GeneralUtil.isNotNull(city2) && GeneralUtil.isNotNull(date1) && GeneralUtil.isNotNull(date2)) {
					String airport1 = city1;
					String airport2 = city2;
					returnUrl = "http://m.ctrip.com/html5/Flight/international/round-"+city1+"-"+city2+"-"+airport1+"-"+airport2+"-all?Relddate="+date1+"&Relrdate="+date2+"&allianceid="+ALLIANCEID+"&sid="+SID;
				} 
				//单程
				else if(GeneralUtil.isNotNull(city1) && GeneralUtil.isNotNull(city2) && GeneralUtil.isNotNull(date1)) {
					String airport1 = city1;
					String airport2 = city2;
					returnUrl = "http://m.ctrip.com/html5/flight/international/single-"+city1+"-"+city2+"-"+airport1+"-"+airport2+"?Relddate="+date1+"&allianceid="+ALLIANCEID+"&sid="+SID;
				}
			}
			return returnUrl;
		} catch(Exception e) {
			e.printStackTrace();
			return "#";//如果错误，则返回“#”
		}
	}

}