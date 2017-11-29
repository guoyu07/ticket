package com.ticket.serviceImpl;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjActivateQueue;
import com.ticket.pojo.SystemDictionary;
import com.ticket.service.IBjdjActivateQueueService;
import com.ticket.service.IBjdjAppointmentService;
import com.ticket.service.IChannelHallRelationService;
import com.ticket.util.ResourceUtil;
import com.ticket.util.SmsUtil;

/**
 * @Description: 激活排队
 * @author HiSay  
 * @date 2015-10-23 15:24:57
 */
public class BjdjActivateQueueServiceImpl extends BaseServiceImpl<BjdjActivateQueue, String> 
		implements IBjdjActivateQueueService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(BjdjActivateQueueServiceImpl.class);
	@Resource
	private IChannelHallRelationService channelHallRelationService;
	@Resource
	private IBjdjAppointmentService appointmentService;
	
	@Override
	public BjdjActivateQueue addWait(String mobile, SystemDictionary channel, Date flightDate) {
		
		BjdjActivateQueue queue = get(mobile, channel);
		if(queue != null){
			
			return queue; 
		}
		
		queue = new BjdjActivateQueue();
		queue.setMobile(mobile);
		queue.setChannel(channel);
		queue.setFlightDate(flightDate);
		persist(queue);
		return queue;
	}

	@Override
	public BjdjActivateQueue get(String mobile, SystemDictionary channel) {
		
		return dbDAO.executeJPQLForQuerySingle("select t from " + BjdjActivateQueue.class.getName() + " t where t.mobile=? and t.channel=?", 
				BjdjActivateQueue.class, mobile, channel);
	}
	
	@Override
	public void checkHallAndSendMessage() throws ServiceException{

		List<BjdjActivateQueue> queues = queryAll(BjdjActivateQueue.class);
		for(BjdjActivateQueue q : queues){
			
			int surplus = appointmentService.surplus(q.getFlightDate(), q.getChannel());
			if(surplus > 0){
				
				SmsUtil.sendSms(q.getMobile(), ResourceUtil.getText("serviceCode.activate.wait.sms"));
				remove(q);
			}
		}
	}
}