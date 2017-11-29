package com.ticket.service;

import java.util.Date;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.MemberFocusFlight;

public interface IAutoSendMessageService extends IBaseService<MemberFocusFlight, String>{
	/**
	 * 会员关注航班时推送消息
	 * 1  离港航班
	 * 	 a 乘机人
	 * 		@1 登机时间，机场高峰期、提醒值机、（便捷登机30秒后发送）
	 * 	 b 送机人
	 * 		@1 登机时间，机场高峰期，提醒值机、便捷登机、（便捷登机30秒后发送）
	 * 2 进港航班
	 * 	 a 乘机人
	 * 		@1 天气、旅游活动、提醒值机
	 * 	 b 接机人
	 * 		@1 到达时间、商业活动、停车
	 * 	 c 送机人
	 * 		@1 提醒值机
	 * flightNumber 航班编号
	 * flightDate	航班日期
	 * flightState  航班状态
	 * memberRole 	乘机角色（乘机 接机 送机）
	 * @return
	 * @throws ServiceException
	 */
	void sendMsgAtFocusFlight(String flightNumber,String flightDate,String flightState,String memberRole) throws ServiceException;
	
	/**
	 * 会员值机成功时推送
	 * 1   离港航班 	登机时间、机场高峰期、购物
	 * 2   到港航班 	预计到达时间，天气
	 * flightNumber 航班编号
	 * flightDate	航班日期
	 * member_id 会员id
	 * ticketNo 电子客票号
	 * @return
	 * @throws ServiceException
	 */
	void sendMsgAtSucCheck(String flightNumber,String flightDate,String member_id,String ticketNo) throws ServiceException;
	
	/**
	 * 登机口开放时推送
	 * 1   乘机人  	登机口已开放
	 * 2   送机人  	登机已开始 
	 * flightNumber 航班编号
	 * flightDate	航班日期
	 * boardingGate 登机口
	 * @return
	 * @throws ServiceException
	 */
	void sendMsgAtOpenBoardingGate(String flightNumber,String flightDate,String boardingGate) throws ServiceException;
	
	/**
	 * 航班到达时推送
	 * 1  离港航班
	 * 	 a 乘机人 	祝福
	 *   b 送机人 	提醒到达
	 *   c 接机人 	提醒到达
	 * 2  进港航班
	 * 	 a 乘机人 交通指南、机场购物
	 * 	 b 送机人 提醒到达
	 * 	 c 接机人 提醒到达
	 * flightNumber 航班编号
	 * flightDate	航班日期
	 * @return
	 * @throws ServiceException
	 */
	void sendMsgAtFlightArrive(String flightNumber,String flightDate) throws ServiceException;
	
	/**
	 * 航班延误时发送
	 * 1向所有关注该航班的会员 发送新时间   安抚乘机人
	 * flightNumber 航班编号
	 * flightDate	航班日期
	 * @return
	 * @throws ServiceException
	 */
	void sendMsgAtFlightDelay(String flightNumber,String flightDate,Date newDate) throws ServiceException;
	
	/**
	 * 取消航班时发送
	 * 向所有关注该航班的会员通知航班取消
	 * 1  关注离港航班的乘机人发送航空公司链接
	 * 2  关注进港航班的接机人推送餐饮
	 * 3  关注离港航班的送机人推送酒店
	 * flightNumber 航班编号
	 * flightDate	航班日期
	 * @return
	 * @throws ServiceException
	 */
	void sendMsgAtCancelFlight(String flightNumber,String flightDate) throws ServiceException;
}
