package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.Member;

/**
 * 机票预订业务接口
 * @author haishui
 */
public interface ITicketOrderService extends IBaseService<Member, String> {
	/**
	 * 根据选择的城市、日期来构造携程机票预订跳转的URL
	 * @param city1                出发城市
	 * @param city2                到达城市
	 * @param date1                出发日期
	 * @param date2                到达日期
	 * @param ticketType 机票类型：domestic(国内) international(国外)
	 * @return
	 * @throws ServiceException
	 */
	String generateCtripRedirectUrl(String city1, String city2, String date1, String date2, String ticketType, String buyType, String cityType) throws ServiceException;
}