package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjTime;


/**
 * 便捷登机时间段分配业务接口
 * @ClassName: IBjdjTimeService   
 * @Description: 提供便捷登机时间段分配操作的增删改查   
 * @author HiSay  
 * @date  2016-03-23 10:11:25
 *
 */
public interface IBjdjTimeService extends IBaseService<BjdjTime, String> {
	/**
	 * 保存便捷登机时间段分配实体
	 * @Title: IBjdjTimeService
	 * @Description:
	 * @param @param startTime  起始时间
	 * @param @param endTime  结束时间
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String startTime,String endTime,Integer orderValue, String versionFlag) throws ServiceException;
	
	/**
	 * 修改便捷登机时间段分配实体
	 * @Title: IBjdjTimeService
	 * @Description:
	 * @param @param startTime  起始时间
	 * @param @param endTime  结束时间
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String startTime,String endTime,Integer orderValue, String versionFlag) throws ServiceException;
	
	/**
	 * 移除便捷登机时间段分配实体
	 * @Title: IBjdjTimeService
	 * @Description: 
	 * @param @param id 便捷登机时间段分配ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	/**
	 * 查询所有实时数据时间段
	 * @return
	 * @throws ServiceException
	 */
	List<BjdjTime> getAll() throws ServiceException;
}