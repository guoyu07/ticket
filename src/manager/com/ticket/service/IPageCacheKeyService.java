package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.PageCacheKey;


/**
 * 页面缓存key管理业务接口
 * @ClassName: IPageCacheKeyService   
 * @Description: 提供页面缓存key管理操作的增删改查   
 * @author 涂有  
 * @date  2015-12-23 10:39:35
 *
 */
public interface IPageCacheKeyService extends IBaseService<PageCacheKey, String> {
	
	/**
	 * 移除页面缓存key管理实体
	 * @Title: IPageCacheKeyService
	 * @Description: 
	 * @param @param id 页面缓存key管理ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * @Description：根据key名得到对象
	 * @date 2015年12月23日 下午10:37:48
	 * @param keyName
	 * @return
	 */
	PageCacheKey get(String keyName);
	
	/**
	 * @Description：根据id得到对象
	 * @date 2015年12月24日 上午9:08:38
	 * @param id
	 * @return
	 */
	PageCacheKey getById(String id);
	
	/**
	 * @Description：刷新一批key的缓存（多个以英文逗号隔开）
	 * @date 2015年12月23日 下午10:41:13
	 * @param ids
	 */
	void refresh(String ids);
	
	/**
	 * @Description：刷新全部缓存
	 * @date 2015年12月24日 上午9:16:44
	 */
	void refreshAll();
}