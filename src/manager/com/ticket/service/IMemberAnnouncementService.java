package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.MemberAnnouncement;
import com.ticket.pojo.News;


/**
 * 通知中心信息阅读业务接口
 * @ClassName: IMemberAnnouncementService   
 * @Description: 提供通知中心信息阅读操作的增删改查   
 * @author HiSay  
 * @date  2016-09-22 16:00:51
 *
 */
public interface IMemberAnnouncementService extends IBaseService<MemberAnnouncement, String> {
	/**
	 * 保存通知中心信息阅读实体
	 * @Title: IMemberAnnouncementService
	 * @Description:
	 * @param @param member_id  用户游客id
	 * @param @param news  已阅读新闻信息
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String member_id,News news, String versionFlag) throws ServiceException;
	
	/**
	 * 修改通知中心信息阅读实体
	 * @Title: IMemberAnnouncementService
	 * @Description:
	 * @param @param member_id  用户游客id
	 * @param @param news  已阅读新闻信息
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String member_id,News news, String versionFlag) throws ServiceException;
	
	/**
	 * 移除通知中心信息阅读实体
	 * @Title: IMemberAnnouncementService
	 * @Description: 
	 * @param @param id 通知中心信息阅读ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 查找已阅读的新闻
	 * @return
	 * @throws ServiceException
	 */
	List<MemberAnnouncement> queryByMember(String member_id) throws ServiceException;
	
	/**
	 * 根据游客和信息查询
	 * @param news_id
	 * @param member_id
	 * @return
	 * @throws ServiceException
	 */
	MemberAnnouncement queryByNewsAndMember(String news_id,String member_id) throws ServiceException;
}