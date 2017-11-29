package com.ticket.service;

import java.io.OutputStream;
import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjComment;
import com.ticket.pojo.BjdjCommentItem;
import com.ticket.pojo.SystemUser;


/**
 * 便捷登机评论表业务接口
 * @ClassName: IBjdjCommentService   
 * @Description: 提供便捷登机评论表操作的增删改查   
 * @author HiSay  
 * @date  2015-10-23 15:24:09
 *
 */
public interface IBjdjCommentService extends IBaseService<BjdjComment, String> {
	/**
	 * 保存便捷登机评论表实体
	 * @Title: IBjdjCommentService
	 * @Description:
	 * @param content  评论内容
	 * @param member_id  用户ID
	 * @param ip  评论时所在的IP地址
	 * @param time  评论日期
	 * @return  保存成功则返回true, 保存失败则返回false.
	 * @throws ServiceException   
	 */
	BjdjComment persist(String content,String serviceCode_id,String ip, Integer star, String reason, String images) throws ServiceException;
	
	/**
	 * 修改便捷登机评论表实体
	 * @Title: IBjdjCommentService
	 * @Description:
	 * @param content  评论内容
	 * @param member_id  用户ID
	 * @param ip  评论时所在的IP地址
	 * @param time  评论日期
	 * @return  修改成功则返回true, 修改失败则返回false.
	 * @throws ServiceException    
	 */
	boolean feedback(String id, SystemUser user, String feedback) throws ServiceException;
	
	/**
	 * 移除便捷登机评论表实体
	 * @Title: IBjdjCommentService
	 * @Description: 
	 * @param id 便捷登机评论表ID
	 * @return 移除成功返回true, 移除失败返回false.
	 * @throws ServiceException    
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * @Description：统计评价的总条数
	 * @return
	 * @throws Exception
	 */
	long count()throws Exception;
	
	/**
	 * @Description：通过订单id获取所有的评论
	 * @param order_id
	 * @return
	 */
	List<BjdjComment> queryByOrder(String order_id);
	
	/**
	 * @Description：通过评论id获取自定义的评论打分项
	 * @param comment_id
	 * @return
	 */
	List<BjdjCommentItem> queryCommentItems(String comment_id);
	
	/**
	 * @Description：通过评论获取自定义的评论打分项
	 * @param comment
	 * @return
	 */
	List<BjdjCommentItem> queryCommentItems(BjdjComment comment);
	
	/**
	 * 生成报表
	 * @param out 输出流
	 */
	void generateReport(OutputStream out);
	
	
	/**
	 * 增加一条评价
	 * @param content
	 * @param serviceCode_id
	 * @param ip
	 * @param star
	 * @param images
	 * @param showName
	 * @param ruleId
	 * @param rule
	 * @param reason
	 * @return
	 */
	BjdjComment addComment(String content, String serviceCode_id, String ip, Integer star, 
			String images, boolean showName, String[] ruleId, String[] rule, String[] reason) throws ServiceException;
}