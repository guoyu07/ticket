package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.ScenicSpots;


/**
 * 旅游景点业务接口
 * @ClassName: IScenicSpotsService   
 * @Description: 提供旅游景点操作的增删改查   
 * @author HiSay  
 * @date  2016-09-30 09:54:17
 *
 */
public interface IScenicSpotsService extends IBaseService<ScenicSpots, String> {
	/**
	 * 保存旅游景点实体
	 * @Title: IScenicSpotsService
	 * @Description:
	 * @param @param name  名称
	 * @param @param introduce  景点介绍
	 * @param @param detail  景点详情
	 * @param @param news  关联文章
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String name,String introduce,String detail,String picture, String versionFlag) throws ServiceException;
	
	/**
	 * 修改旅游景点实体
	 * @Title: IScenicSpotsService
	 * @Description:
	 * @param @param name  名称
	 * @param @param introduce  景点介绍
	 * @param @param detail  景点详情
	 * @param @param news  关联文章
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String name,String introduce,String detail,String picture, String versionFlag) throws ServiceException;
	
	/**
	 * 移除旅游景点实体
	 * @Title: IScenicSpotsService
	 * @Description: 
	 * @param @param id 旅游景点ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 设置/取消热门
	 * @param id
	 * @param hot
	 * @return
	 * @throws ServiceException
	 */
	boolean hotEntity(String id,Integer hot) throws ServiceException;
	
	/**
	 * 热门景点
	 * @return
	 * @throws ServiceException
	 */
	List<ScenicSpots> queryHot() throws ServiceException;
}