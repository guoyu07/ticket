package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.TrafficType;


/**
 * 交通路线类型业务接口
 * @ClassName: ITrafficTypeService   
 * @Description: 提供交通路线类型操作的增删改查   
 * @author HiSay  
 * @date  2015-11-19 09:42:49
 *
 */
public interface ITrafficTypeService extends IBaseService<TrafficType, String> {
	/**
	 * 保存交通路线类型实体
	 * @Title: ITrafficTypeService
	 * @Description:
	 * @param @param name  路线类别名称
	 * @param @param introduce  路线类别介绍
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String name,String introduce,Double longitude,Double latitude,String floorNumber,Integer orderValue,Integer hot, String versionFlag) throws ServiceException;
	
	/**
	 * 修改交通路线类型实体
	 * @Title: ITrafficTypeService
	 * @Description:
	 * @param @param name  路线类别名称
	 * @param @param introduce  路线类别介绍
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String name,String introduce,Double longitude,Double latitude,String floorNumber,Integer orderValue,Integer hot, String versionFlag) throws ServiceException;
	
	/**
	 * 移除交通路线类型实体
	 * @Title: ITrafficTypeService
	 * @Description: 
	 * @param @param id 交通路线类型ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 批量彻底删除交通线路类别
	 * @param idsValue
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException;

	/**
	 * 交通类别列表
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<TrafficType> queryList(String versionFlag) throws ServiceException;
	
	/**
	 * 根据关键词查询交通类别列表
	 * @param keyword 关键词
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<TrafficType> queryListByKeyword(String keyword, String versionFlag) throws ServiceException;

	/**
	 * 查询所有的路线类别
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<TrafficType> queryListAll(String versionFlag) throws ServiceException;
}