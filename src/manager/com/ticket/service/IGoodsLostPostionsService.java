package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.GoodsLostPostions;


/**
 * 物品遗失位置业务接口
 * @ClassName: IGoodsLostPostionsService   
 * @Description: 提供物品遗失位置操作的增删改查   
 * @author HiSay  
 * @date  2015-11-23 16:44:38
 *
 */
public interface IGoodsLostPostionsService extends IBaseService<GoodsLostPostions, String> {
	/**
	 * 保存物品遗失位置实体
	 * @Title: IGoodsLostPostionsService
	 * @Description:
	 * @param @param name  位置名称
	 * @param @param description  位置描述
	 * @param @param longitude  经度
	 * @param @param latitude  纬度
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String name,String description,Double longitude,Double latitude, String versionFlag) throws ServiceException;
	
	/**
	 * 修改物品遗失位置实体
	 * @Title: IGoodsLostPostionsService
	 * @Description:
	 * @param @param name  位置名称
	 * @param @param description  位置描述
	 * @param @param longitude  经度
	 * @param @param latitude  纬度
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String name,String description,Double longitude,Double latitude, String versionFlag) throws ServiceException;
	
	/**
	 * 移除物品遗失位置实体
	 * @Title: IGoodsLostPostionsService
	 * @Description: 
	 * @param @param id 物品遗失位置ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 批量彻底删除位置信息实体
	 * @param idsValue
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException;

	/**
	 * 查询物品遗失位置列表
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<GoodsLostPostions> queryByList(String versionFlag) throws ServiceException;
	
	/**
	 * 根据位置名称查询
	 * @param name
	 * @return
	 */
	GoodsLostPostions queryByName(String name);
}