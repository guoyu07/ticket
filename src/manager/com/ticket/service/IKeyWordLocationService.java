package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.KeyWordLocation;


/**
 * 关键词定位业务接口
 * @ClassName: IKeyWordLocationService   
 * @Description: 提供关键词定位操作的增删改查   
 * @author HiSay  
 * @date  2016-09-28 15:43:22
 *
 */
public interface IKeyWordLocationService extends IBaseService<KeyWordLocation, String> {
	
	void init();
	
	/**
	 * 保存关键词定位实体
	 * @Title: IKeyWordLocationService
	 * @Description:
	 * @param @param keyword  关键词
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String keyword) throws ServiceException;
	
	/**
	 * 修改关键词定位实体
	 * @Title: IKeyWordLocationService
	 * @Description:
	 * @param @param keyword  关键词
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String keyword) throws ServiceException;
	
	/**
	 * 移除关键词定位实体
	 * @Title: IKeyWordLocationService
	 * @Description: 
	 * @param @param id 关键词定位ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 根据关键词搜索
	 * @param keyword
	 * @return
	 */
	List<KeyWordLocation> query(String keyword);
}