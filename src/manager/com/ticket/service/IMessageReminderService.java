package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.MessageReminder;


/**
 * 消息提醒业务接口
 * @ClassName: IMessageReminderService   
 * @Description: 提供消息提醒操作的增删改查   
 * @author HiSay  
 * @date  2015-11-17 16:10:53
 *
 */
public interface IMessageReminderService extends IBaseService<MessageReminder, String> {
	/**
	 * 保存消息提醒实体
	 * @Title: IMessageReminderService
	 * @Description:
	 * @param @param member_id  会员id
	 * @param @param title  标题
	 * @param @param content  提醒内容
	 * @param @param reminderTime  提醒时间
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String member_id,String title,String picture,String content,String reminderTime, String versionFlag) throws ServiceException;
	
	/**
	 * 修改消息提醒实体
	 * @Title: IMessageReminderService
	 * @Description:
	 * @param @param member_id  会员id
	 * @param @param title  标题
	 * @param @param content  提醒内容
	 * @param @param reminderTime  提醒时间
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String member_id,String title,String picture,String content,String reminderTime, String versionFlag) throws ServiceException;
	
	/**
	 * 移除消息提醒实体
	 * @Title: IMessageReminderService
	 * @Description: 
	 * @param @param id 消息提醒ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 批量彻底删除消息提醒
	 * @param idsValue
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException;

	/**
	 * 查询消息提醒列表
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<MessageReminder> queryList(String versionFlag) throws ServiceException;

	/**
	 * 统计未读消息的数量
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	Long queryCountByNotAudit(String versionFlag) throws ServiceException;

	/**
	 * 统计所有的未读消息
	 * @return
	 * @throws ServiceException
	 */
	Long queryCount(String versionFlag) throws ServiceException;
}