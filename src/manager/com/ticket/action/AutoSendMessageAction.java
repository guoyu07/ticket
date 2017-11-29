package com.ticket.action;

import java.util.Date;

import javax.annotation.Resource;

import com.ticket.exception.ServiceException;
import com.ticket.service.IAutoSendMessageService;
import com.ticket.service.IBjdjAppointmentService;
import com.ticket.service.IBjdjDispatchListService;

/**
 * 自动推送站内信控制器
 * @ClassName: AutoSendMessageAction   
 * @Description:  提供站内信的相关操作方法. 
 * @author dfq  
 * @date 2016-4-9 10:27:54
 *
 */
public class AutoSendMessageAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//自动推送站内信的业务层
	@Resource 
	private IAutoSendMessageService autoSendMessageService;
	@Resource 
	private IBjdjDispatchListService dispatchListService;
	@Resource 
	private IBjdjAppointmentService appointmentService;
	
	//航班号
	private String flightNumber;
	//航班日期
	private String flightDate;
	//登机口
	private String boardingGate;
	
	/**
	 * 登机口开放时推送站内信
	 * @return
	 * @throws ServiceException
	 */
	public String sendMsgAtOpenBoardingGate() throws ServiceException{
		autoSendMessageService.sendMsgAtOpenBoardingGate(flightNumber, flightDate, boardingGate);
		return null;
	}
	
	/**
	 * 航班到达时推送站内信
	 * @return
	 * @throws ServiceException
	 */
	public String sendMsgAtFlightArrive() throws ServiceException{
		autoSendMessageService.sendMsgAtFlightArrive(flightNumber, flightDate);
		return null;
		
	}
	
	/**
	 * 航班延误时推送站内信
	 * @return
	 * @throws ServiceException
	 */
	public String sendMsgAtFlightDelay() throws ServiceException {
		autoSendMessageService.sendMsgAtFlightDelay(flightNumber, flightDate, new Date());
		appointmentService.flightDelay(flightNumber, flightDate);
		return null;
		
	}
	
	/**
	 * 航班取消时推送站内信
	 * @return
	 * @throws ServiceException
	 */
	public String sendMsgAtCancelFlight() throws ServiceException{
		autoSendMessageService.sendMsgAtCancelFlight(flightNumber, flightDate);
		dispatchListService.systemVerification(flightNumber, flightDate);
		return null;
	}
	
	/**
	 * 航班起飞
	 * @return
	 */
	public String flightTakeOff() throws ServiceException{
		
		dispatchListService.systemVerification(flightNumber, flightDate);
		return null;
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
	public String getBoardingGate() {
		return boardingGate;
	}
	public void setBoardingGate(String boardingGate) {
		this.boardingGate = boardingGate;
	}

}
