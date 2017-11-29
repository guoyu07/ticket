package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.NewsComment;


/**
 * 新闻评论业务接口
 * @ClassName: INewsCommentService   
 * @Description: 提供新闻评论操作的增删改查   
 * @author HiSay  
 * @date  2015-10-13 17:16:37
 *
 */
public interface INewsCommentService extends IBaseService<NewsComment, String> {
	/**
	 * 保存新闻评论实体
	 * @Title: INewsCommentService
	 * @Description:
	 * @param @param news_id  新闻id
	 * @param @param member_id  会员id
	 * @param @param memberIp  会员IP
	 * @param @param star  评几颗星
	 * @param @param content  评论内容
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String news_id,String member_id,String memberIp,Integer star,String content, String versionFlag) throws ServiceException;
	
	/**
	 * 修改新闻评论实体
	 * @Title: INewsCommentService
	 * @Description:
	 * @param @param news_id  新闻id
	 * @param @param member_id  会员id
	 * @param @param memberIp  会员IP
	 * @param @param star  评几颗星
	 * @param @param content  评论内容
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String news_id,String member_id,String memberIp,Integer star,String content, String versionFlag) throws ServiceException;
	
	/**
	 * 移除新闻评论实体
	 * @Title: INewsCommentService
	 * @Description: 
	 * @param @param id 新闻评论ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 *批量彻底删除新闻评论信息
	 * @param versionFlag
	 * @param idsValue
	 * @return
	 * @throws ServiceException
	 */
	boolean batchRealDelete(String idsValue,String versionFlag) throws ServiceException;
}