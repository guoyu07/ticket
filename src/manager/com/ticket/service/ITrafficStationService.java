package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.TrafficStation;


/**
 * 车站信息业务接口
 * @ClassName: ITrafficStationService   
 * @Description: 提供车站信息操作的增删改查   
 * @author HiSay  
 * @date  2015-11-19 10:07:53
 *
 */
public interface ITrafficStationService extends IBaseService<TrafficStation, String> {
	/**
	 * 保存车站信息实体
	 * @Title: ITrafficStationService
	 * @Description:
	 * @param @param trafficLine_id  路线id
	 * @param @param name  车站名称
	 * @param @param descript  车站描述
	 * @param @param goOrBack  去程还是回程
	 * @param @param longitude  经度
	 * @param @param latitude  纬度
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String name,String descript,Double longitude,Double latitude, String versionFlag) throws ServiceException;
	
	/**
	 * 修改车站信息实体
	 * @Title: ITrafficStationService
	 * @Description:
	 * @param @param trafficLine_id  路线id
	 * @param @param name  车站名称
	 * @param @param descript  车站描述
	 * @param @param goOrBack  去程还是回程
	 * @param @param longitude  经度
	 * @param @param latitude  纬度
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String name,String descript,Double longitude,Double latitude, String versionFlag) throws ServiceException;
	
	/**
	 * 移除车站信息实体
	 * @Title: ITrafficStationService
	 * @Description: 
	 * @param @param id 车站信息ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 批量彻底删除车站实体
	 * @param idsValue
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException;

	/**
	 * 去程站点
	 * @param trafficLine_id 路线id
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<TrafficStation> queryListByLineAndGo(String trafficLine_id, String versionFlag) throws ServiceException;
	
	/**
	 * 回程站点
	 * @param trafficLine_id 路线id
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<TrafficStation> queryListByLineAndBack(String trafficLine_id, String versionFlag) throws ServiceException;

	/**
	 * 根据路线id查询站点列表
	 * @param trafficLineId
	 * @param adminCommonPageSize
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryListByTrafficLineId(String trafficLineId, int pageSize, String versionFlag) throws ServiceException;

	/**
	 * 查询车站列表
	 * @param versionFlag 版本标识
	 * @return
	 * @throws ServiceException
	 */
	List<TrafficStation> queryList(String versionFlag) throws ServiceException;

	/**
	 * 根据关键词检索车站信息
	 * @param keyword
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<TrafficStation> queryListByKeyword(String keyword, String versionFlag) throws ServiceException;

	/**
	 * 验证车站名称是否已存在
	 * @param name
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	boolean validateNameExist(String name, String versionFlag) throws ServiceException;
	
	/**
	 * 通过名称查询车站
	 * @param name
	 * @return
	 * @throws ServiceException
	 */
	TrafficStation query(String name) throws ServiceException;
}