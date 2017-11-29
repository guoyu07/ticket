package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.MemberFavorite;


/**
 * 会员收藏业务接口
 * @ClassName: IMemberFavoriteService   
 * @Description: 提供会员收藏操作的增删改查   
 * @author HiSay  
 * @date  2015-11-13 09:59:42
 *
 */
public interface IMemberFavoriteService extends IBaseService<MemberFavorite, String> {

	/**
	 * 保存会员收藏实体
	 * @Title: IMemberFavoriteService
	 * @Description:
	 * @param @param member_id  会员id
	 * @param @param objectId  收藏对象id
	 * @param @param objectType  收藏类型
	 * @param @param Title  收藏标题
	 * @param @param url  收藏链接
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String member_id,String objectId,String objectType,String Title,String url, String versionFlag) throws ServiceException;
	
	/**
	 * 修改会员收藏实体
	 * @Title: IMemberFavoriteService
	 * @Description:
	 * @param @param member_id  会员id
	 * @param @param objectId  收藏对象id
	 * @param @param objectType  收藏类型
	 * @param @param Title  收藏标题
	 * @param @param url  收藏链接
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String member_id,String objectId,String objectType,String Title,String url, String versionFlag) throws ServiceException;
	
	/**
	 * 移除会员收藏实体
	 * @Title: IMemberFavoriteService
	 * @Description: 
	 * @param @param id 会员收藏ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 根据收藏对象id查找会员收藏实体
	 * @param objectId 收藏对象id
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	MemberFavorite queryByObjectId(String objectId, String versionFlag) throws ServiceException;

	/**
	 * 会员收藏夹
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<MemberFavorite> queryListByMember(String versionFlag) throws ServiceException;

	/**
	 * 根据收藏标题查询会员收藏实体
	 * @param title
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	MemberFavorite queryByTitleAndUrl(String title,String url, String versionFlag) throws ServiceException;

	/**
	 * 统计会员收藏数量
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	Long queryCountByMember(String versionFlag) throws ServiceException;

	/**
	 * 统计会员收藏数量
	 * @param member_id
	 * @param versionFlag
	 * @return
	 */
	Long queryFavoriteCount(String member_id, String versionFlag);
	
	/**
	 * @Description：根据对象ID和对象类型，得到是否收藏
	 * @date 2015年12月15日 下午7:54:27
	 * @param objectId
	 * @param objectType
	 * @return
	 */
	boolean isFavorite(String member_id, String objectId, String objectType);
}