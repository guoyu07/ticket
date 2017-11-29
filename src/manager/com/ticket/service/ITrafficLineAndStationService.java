package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.TrafficLineAndStation;


/**
 * 路线与车站关联业务接口
 * @ClassName: ITrafficLineAndStationService   
 * @Description: 提供路线与车站关联操作的增删改查   
 * @author HiSay  
 * @date  2015-12-20 16:30:33
 *
 */
public interface ITrafficLineAndStationService extends IBaseService<TrafficLineAndStation, String> {
	/**
	 * 保存路线与车站关联实体
	 * @Title: ITrafficLineAndStationService
	 * @Description:
	 * @param @param trafficLine_id  路线id
	 * @param @param trafficStation_id  车站id
	 * @param @param stationType  车站类型
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String trafficType_id,String trafficLine_id,String trafficStation_id,Integer stationType,Integer orderValue, String versionFlag) throws ServiceException;
	
	/**
	 * 修改路线与车站关联实体
	 * @Title: ITrafficLineAndStationService
	 * @Description:
	 * @param @param trafficLine_id  路线id
	 * @param @param trafficStation_id  车站id
	 * @param @param stationType  车站类型
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id,String trafficType_id, String trafficLine_id,String trafficStation_id,Integer stationType,Integer orderValue, String versionFlag) throws ServiceException;
	
	/**
	 * 移除路线与车站关联实体
	 * @Title: ITrafficLineAndStationService
	 * @Description: 
	 * @param @param id 路线与车站关联ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 根据车站id查询关联路线
	 * @param station_id 车站id
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<TrafficLineAndStation> queryListByStationId(String station_id, String versionFlag) throws ServiceException;
	
	/**
	 * 根据路线id查询去程路线站点
	 * @param trafficLine_id 路线id
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<TrafficLineAndStation> queryListByLineAndGo(String trafficLine_id, String versionFlag) throws ServiceException;

	/**
	 * 根据路线id查询回程路线站点
	 * @param trafficLine_id 路线id
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<TrafficLineAndStation> queryListByLineAndBack(String trafficLine_id,String versionFlag) throws ServiceException;

	/**
	 * 根据路线查看与站点的关联
	 * @param trafficLineId
	 * @param pageSize
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryByTrafficLineId(String trafficLineId,
			int pageSize, String versionFlag) throws ServiceException;

}