package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.AdvertType;


/**
 * 广告类型业务接口
 * @ClassName: IAdvertTypeService   
 * @Description: 提供广告类型操作的增删改查   
 * @author HiSay  
 * @date  2015-10-27 10:29:57
 *
 */
public interface IAdvertTypeService extends IBaseService<AdvertType, String> {
	/**
	 * 保存广告类型实体
	 * @Title: IAdvertTypeService
	 * @Description:
	 * @param @param name  类型名称
	 * @param @param descript  类型描述
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String name,String descript, String versionFlag) throws ServiceException;
	
	/**
	 * 修改广告类型实体
	 * @Title: IAdvertTypeService
	 * @Description:
	 * @param @param name  类型名称
	 * @param @param descript  类型描述
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String name,String descript, String versionFlag) throws ServiceException;
	
	/**
	 * 移除广告类型实体
	 * @Title: IAdvertTypeService
	 * @Description: 
	 * @param @param id 广告类型ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 批量彻底删除广告类型
	 * @param idsValue  类型id数组
	 * @param versionFlag  版本标识
	 * @return
	 * @throws ServiceException
	 */
	boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException ;

	/**
	 * 查询广告类型列表
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<AdvertType> queryByList(String versionFlag) throws ServiceException ;
	/**
	 * 根据广告类型名称获取实体
	 * @param name
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	AdvertType queryByName(String name, String versionFlag) throws ServiceException;
}