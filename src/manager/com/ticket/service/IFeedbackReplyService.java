package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.Feedback;
import com.ticket.pojo.FeedbackReply;
import com.ticket.pojo.Member;
import com.ticket.pojo.SystemUser;


/**
 * 反馈与回复业务接口
 * @ClassName: IFeedbackReplyService   
 * @Description: 提供反馈与回复操作的增删改查   
 * @author HiSay  
 * @date  2016-08-15 15:11:31
 *
 */
public interface IFeedbackReplyService extends IBaseService<FeedbackReply, String> {
	/**
	 * 保存反馈与回复实体
	 * @Title: IFeedbackReplyService
	 * @Description:
	 * @param @param feekBack  所属反馈问题
	 * @param @param member  反馈人员
	 * @param @param description  问题描述
	 * @param @param systemUser  客服人员
	 * @param @param replyContent  回复内容
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String feedBack_id,Member member,String description,SystemUser systemUser,String replyContent,String images, String versionFlag) throws ServiceException;
	
	/**
	 * 修改反馈与回复实体
	 * @Title: IFeedbackReplyService
	 * @Description:
	 * @param @param feekBack  所属反馈问题
	 * @param @param member  反馈人员
	 * @param @param description  问题描述
	 * @param @param systemUser  客服人员
	 * @param @param replyContent  回复内容
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, Feedback feekBack,Member member,String description,SystemUser systemUser,String replyContent, String versionFlag) throws ServiceException;
	
	/**
	 * 移除反馈与回复实体
	 * @Title: IFeedbackReplyService
	 * @Description: 
	 * @param @param id 反馈与回复ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 根据反馈实体查询是否有回复或者追加反馈
	 * @param feedbackId
	 * @return
	 * @throws ServiceException
	 */
	List<FeedbackReply> queryByFeedbackId(String feedbackId) throws ServiceException;
	
	/**
	 * 查找是否已经回复
	 * @param feedback_id
	 * @return
	 * @throws ServiceException
	 */
	FeedbackReply isBacks(String feedback_id) throws ServiceException;
	
	/**
	 * 查看是否有追加反馈并且是否回复了追加反馈
	 * @param feedback_id
	 * @return
	 * @throws ServiceException
	 */
	Integer isFeedBackOrBack(String feedback_id) throws ServiceException;
}