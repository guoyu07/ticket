package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.PageCacheGroup;


/**
 * 页面缓存group管理业务接口
 * @ClassName: IPageCacheGroupService   
 * @Description: 提供页面缓存group管理操作的增删改查   
 * @author 涂有  
 * @date  2015-12-23 10:34:35
 *
 */
public interface IPageCacheGroupService extends IBaseService<PageCacheGroup, String> {
	
	/**
	 * 移除页面缓存group管理实体
	 * @Title: IPageCacheGroupService
	 * @Description: 
	 * @param id 页面缓存group管理ID
	 * @return 移除成功返回true, 移除失败返回false.
	 * @throws ServiceException    
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * @Description：根据组名得到group对象
	 * @date 2015年12月23日 下午10:35:55
	 * @param groupName
	 * @return
	 */
	PageCacheGroup get(String groupName);
	
	/**
	 * @Description：根据id得到group对象
	 * @date 2015年12月24日 上午9:08:23
	 * @param id
	 * @return
	 */
	PageCacheGroup getById(String id);
	
	/**
	 * @Description：刷新一批group的缓存（多个以英文逗号隔开）
	 * @date 2015年12月23日 下午10:41:13
	 * @param ids
	 */
	void refresh(String ids);
}