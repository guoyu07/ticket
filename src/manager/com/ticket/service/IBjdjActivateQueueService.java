package com.ticket.service;

import java.util.Date;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjActivateQueue;
import com.ticket.pojo.SystemDictionary;


/**
 * 服务厅表业务接口
 * @ClassName: IBjdjHallService   
 * @Description: 提供服务厅表操作的增删改查   
 * @author HiSay  
 * @date  2015-10-23 15:24:57
 *
 */
public interface IBjdjActivateQueueService extends IBaseService<BjdjActivateQueue, String> {
	
	/**
	 * 增加一个需要接收提示的服务码
	 * @param member
	 * @param flightDate
	 * @return
	 */
	BjdjActivateQueue addWait(String mobile, SystemDictionary channel, Date flightDate);
	
	/**
	 * 通过服务码得到实例对象
	 * @param member
	 * @return
	 */
	BjdjActivateQueue get(String mobile, SystemDictionary channel);
	
	/**
	 * 大厅有空余，则发送消息到等待激活的用户
	 */
	void checkHallAndSendMessage() throws ServiceException;
}