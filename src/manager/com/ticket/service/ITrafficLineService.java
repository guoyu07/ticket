package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.TrafficLine;


/**
 * 交通路线业务接口
 * @ClassName: ITrafficLineService   
 * @Description: 提供交通路线操作的增删改查   
 * @author HiSay  
 * @date  2015-11-19 09:55:18
 *
 */
public interface ITrafficLineService extends IBaseService<TrafficLine, String> {
	/**
	 * 保存交通路线实体
	 * @Title: ITrafficLineService
	 * @Description:
	 * @param @param trafficType_id  路线类别id
	 * @param @param startStation  起始站
	 * @param @param endStation  终到站
	 * @param @param startTime  发车时间
	 * @param @param endTime  末班车时间
	 * @param @param departureRate  发班频率
	 * @param @param carCount  车辆数
	 * @param @param carModel  车辆型号
	 * @param @param seatCount  座位数
	 * @param @param price  票价
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String trafficType_id,String name,String startStation,String endStation,String startTime,String endTime,String departureRate,Integer carCount,String carModel,Integer seatCount,Double price,String remark,Integer orderValue, String versionFlag) throws ServiceException;
	
	/**
	 * 修改交通路线实体
	 * @Title: ITrafficLineService
	 * @Description:
	 * @param @param trafficType_id  路线类别id
	 * @param @param startStation  起始站
	 * @param @param endStation  终到站
	 * @param @param startTime  发车时间
	 * @param @param endTime  末班车时间
	 * @param @param departureRate  发班频率
	 * @param @param carCount  车辆数
	 * @param @param carModel  车辆型号
	 * @param @param seatCount  座位数
	 * @param @param price  票价
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String trafficType_id,String name,String startStation,String endStation,String startTime,String endTime,String departureRate,Integer carCount,String carModel,Integer seatCount,Double price,String remark,Integer orderValue, String versionFlag) throws ServiceException;
	
	/**
	 * 移除交通路线实体
	 * @Title: ITrafficLineService
	 * @Description: 
	 * @param @param id 交通路线ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 批量彻底删除交通路线实体
	 * @param idsValue
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException;

	/**
	 * 交通线路列表
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<TrafficLine> queryList(String versionFlag) throws ServiceException;

	/**
	 * 根据类别id查询路线实体列表
	 * @param trafficType_id
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<TrafficLine> queryListByTypeId(String trafficType_id, String versionFlag) throws ServiceException;

	/**
	 * 根据路线类别id查询路线列表
	 * @param trafficTypeId
	 * @param pageSize
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryEntityByTypeId(String trafficTypeId, int pageSize, String versionFlag) throws ServiceException;

}