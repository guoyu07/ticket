package com.ticket.service;

import java.util.Date;
import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.Feedback;
import com.ticket.pojo.Member;
import com.ticket.pojo.Pagination;


/**
 * 公测反馈业务接口
 * @ClassName: IFeedbackService   
 * @Description: 提供公测反馈操作的增删改查   
 * @author HiSay  
 * @date  2016-08-15 15:10:43
 *
 */
public interface IFeedbackService extends IBaseService<Feedback, String> {
	/**
	 * 保存公测反馈实体
	 * @Title: IFeedbackService
	 * @Description:
	 * @param @param member  反馈人员
	 * @param @param description  问题描述
	 * @param @param phone  手机号码
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(Member member,String description,String phone,String images, String versionFlag) throws ServiceException;
	
	/**
	 * 修改公测反馈实体
	 * @Title: IFeedbackService
	 * @Description:
	 * @param @param member  反馈人员
	 * @param @param description  问题描述
	 * @param @param phone  手机号码
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, Member member,String description,String phone,String images, String versionFlag) throws ServiceException;
	
	/**
	 * 移除公测反馈实体
	 * @Title: IFeedbackService
	 * @Description: 
	 * @param @param id 公测反馈ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 根据会员查找反馈
	 * @param member
	 * @return
	 * @throws ServiceException
	 */
	List<Feedback> queryByMember(Member member) throws ServiceException;
	
	/**
	 * 会员反馈的条数
	 * @param member
	 * @return
	 * @throws ServiceException
	 */
	long count(Member member) throws ServiceException;
	
	/**
	 * 根据时间段查询反馈
	 * @param startTime
	 * @param endTime
	 * @param versionFlag
	 * @param page
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryEntityByAdmin(Date startTime ,Date endTime,String versionFlag,Integer page) throws ServiceException;
	/**
	 * 根据时间端查询反馈
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ServiceException
	 */
	List<Feedback> queryAllByTime(Date startTime ,Date endTime) throws ServiceException;
	
	/**
	 * 创建xls
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ServiceException
	 */
	String createJxls(Date startTime ,Date endTime) throws ServiceException;
	
	/**
	 * 查询是否反馈是否有新回复
	 * @return
	 */
	Integer queryByMember() throws ServiceException;
}