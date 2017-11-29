package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.Article;


/**
 * 文章信息业务接口
 * @ClassName: IArticleService   
 * @Description: 提供文章信息操作的增删改查   
 * @author HiSay  
 * @date  2015-10-13 10:05:09
 *
 */
public interface IArticleService extends IBaseService<Article, String> {
	/**
	 * 保存文章信息实体
	 * @Title: IArticleService
	 * @Description:
	 * @param @param newsClass_id  所属栏目
	 * @param @param title  文章标题
	 * @param @param content  文章内容
	 * @param @param introduce  文章简介
	 * @param @param thumbnail  文章缩略图
	 * @param @param source  文章来源
	 * @param @param author  文章编辑
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String newsClass_id,String title,String content,String introduce,String thumbnail,
			String source,String author, String versionFlag, String viewPageRedirectTemplate_id) throws ServiceException;
	
	/**
	 * 修改文章信息实体
	 * @Title: IArticleService
	 * @Description:
	 * @param @param newsClass_id  所属栏目
	 * @param @param title  文章标题
	 * @param @param content  文章内容
	 * @param @param introduce  文章简介
	 * @param @param thumbnail  文章缩略图
	 * @param @param source  文章来源
	 * @param @param author  文章编辑
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String newsClass_id,String title,String content,String introduce,String thumbnail,
			String source,String author, String versionFlag, String viewPageRedirectTemplate_id) throws ServiceException;
	
	/**
	 * 移除文章信息实体
	 * @Title: IArticleService
	 * @Description: 
	 * @param @param id 文章信息ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	/**
	 * 根据URL获取信息
	 * @param visitUrl
	 * @return
	 * @throws ServiceException
	 */
	Article queryByUrl(String visitUrl, boolean addHits) throws ServiceException;
}