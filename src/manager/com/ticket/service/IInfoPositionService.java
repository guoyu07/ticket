package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.InfoPosition;


/**
 * 信息定位业务接口
 * @ClassName: IInfoPositionService   
 * @Description: 提供信息定位操作的增删改查   
 * @author HiSay  
 * @date  2015-10-20 18:13:14
 *
 */
public interface IInfoPositionService extends IBaseService<InfoPosition, String> {
	/**
	 * 保存信息定位实体
	 * @Title: IInfoPositionService
	 * @Description:
	 * @param @param news_id  新闻id
	 * @param @param longitude  经度
	 * @param @param latitude  纬度
	 * @param @param floorNumber  楼层号
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String news_id,String name,String positionAlias, String longitude,String latitude,String floorNumber,String mobile,String url,String className, String versionFlag) throws ServiceException;
	
	/**
	 * 修改信息定位实体
	 * @Title: IInfoPositionService
	 * @Description:
	 * @param @param news_id  新闻id
	 * @param @param longitude  经度
	 * @param @param latitude  纬度
	 * @param @param floorNumber  楼层号
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String news_id,String name,String positionAlias, String longitude,String latitude,String floorNumber,String mobile,String url,String className, String versionFlag) throws ServiceException;
	
	/**
	 * 移除信息定位实体
	 * @Title: IInfoPositionService
	 * @Description: 
	 * @param @param id 信息定位ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 根据信息Id查询信息位置实体
	 * @param newsId
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	InfoPosition queryByNewsId(String newsId, String versionFlag) throws ServiceException;

	/**
	 * 根据新闻信息查询设施位置
	 * @param news_id
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<InfoPosition> queryListByNewsId(String news_id, String versionFlag) throws ServiceException;

	/**
	 * 根据位置名称查询位置信息
	 * @param locationName
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	InfoPosition queryByName(String locationName, String versionFlag) throws ServiceException;

	/**
	 * 验证位置别名是否已经存在
	 * @param positionAlias  位置别名
	 * @return
	 * @throws ServiceException
	 */
	boolean validateAliasExists(String positionAlias) throws ServiceException;

	/**
	 * 根据别名查询位置信息
	 * @param alias 位置别名
	 * @return
	 * @throws ServiceException
	 */
	InfoPosition queryByAlias(String alias) throws ServiceException;

	/**
	 * 文章位置列表
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<InfoPosition> queryByNewsAlias(String versionFlag) throws ServiceException;
	
	/**
	 * 根据名称查询点位
	 * @param name
	 * @return
	 * @throws ServiceException
	 */
	List<InfoPosition> queryByNameAndAlias(String name,String alias) throws ServiceException;
}