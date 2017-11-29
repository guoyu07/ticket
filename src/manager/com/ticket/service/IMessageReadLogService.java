package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.MessageReadLog;


/**
 * 消息阅读日志业务接口
 * @ClassName: IMessageReadLogService   
 * @Description: 提供消息阅读日志操作的增删改查   
 * @author HiSay  
 * @date  2015-11-30 14:14:30
 *
 */
public interface IMessageReadLogService extends IBaseService<MessageReadLog, String> {
	/**
	 * 保存消息阅读日志实体
	 * @Title: IMessageReadLogService
	 * @Description:
	 * @param @param member_id  会员id
	 * @param @param message_id  消息id
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String member_id,String message_id, String versionFlag) throws ServiceException;
	
	/**
	 * 修改消息阅读日志实体
	 * @Title: IMessageReadLogService
	 * @Description:
	 * @param @param member_id  会员id
	 * @param @param message_id  消息id
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String member_id,String message_id, String versionFlag) throws ServiceException;
	
	/**
	 * 移除消息阅读日志实体
	 * @Title: IMessageReadLogService
	 * @Description: 
	 * @param @param id 消息阅读日志ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 统计会员阅读的数量
	 * @param versionFlag
	 * @return
	 */
	Long queryCountByMember(String versionFlag);
}