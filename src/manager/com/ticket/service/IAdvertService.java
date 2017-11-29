package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.Advert;


/**
 * 广告信息业务接口
 * @ClassName: IAdvertService   
 * @Description: 提供广告信息操作的增删改查   
 * @author HiSay  
 * @date  2015-10-27 10:31:54
 *
 */
public interface IAdvertService extends IBaseService<Advert, String> {
	/**
	 * 保存广告信息实体
	 * @Title: IAdvertService
	 * @Description:
	 * @param @param name  广告名称
	 * @param @param url  广告链接
	 * @param @param content  广告内容
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String advertType_id,String name,String url,String picture,String content,Integer orderValue,String arriveCitys, String versionFlag) throws ServiceException;
	
	/**
	 * 修改广告信息实体
	 * @Title: IAdvertService
	 * @Description:
	 * @param @param name  广告名称
	 * @param @param url  广告链接
	 * @param @param content  广告内容
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id,String advertType_id, String name,String url,String picture,String content,Integer orderValue,String arriveCitys, String versionFlag) throws ServiceException;
	
	/**
	 * 移除广告信息实体
	 * @Title: IAdvertService
	 * @Description: 
	 * @param @param id 广告信息ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 批量彻底删除广告信息
	 * @param idsValue  广告id数组
	 * @param versionFlag  版本标识
	 * @return
	 * @throws ServiceException
	 */
	boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException;

	/**
	 * 查询广告列表
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<Advert> queryList(String versionFlag) throws ServiceException;
	/**
	 * 查询公告类型查看广告列表
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<Advert> queryList(String versionFlag, String advertTypeName, Integer size) throws ServiceException;

	/**
	 * 根据名称查询广告实体
	 * @param value
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	Advert queryByName(String name, String versionFlag) throws ServiceException;
}