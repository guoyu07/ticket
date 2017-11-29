package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.FCSelfCheckinPosition;


/**
 * 航空公司自助值机位置表业务接口
 * @ClassName: IFCSelfCheckinPositionService   
 * @Description: 提供航空公司自助值机位置表操作的增删改查   
 * @author HiSay  
 * @date  2016-03-30 17:01:09
 *
 */
public interface IFCSelfCheckinPositionService extends IBaseService<FCSelfCheckinPosition, String> {
	/**
	 * 保存航空公司自助值机位置表实体
	 * @Title: IFCSelfCheckinPositionService
	 * @Description:
	 * @param @param name  值机位置名称
	 * @param @param flightCompany_id  航空公司id
	 * @param @param floorNumber  楼层号
	 * @param @param longitude  经度
	 * @param @param latitude  纬度
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String name,String flightCompany_id,String floorNumber,Double longitude,Double latitude, String versionFlag) throws ServiceException;
	
	/**
	 * 修改航空公司自助值机位置表实体
	 * @Title: IFCSelfCheckinPositionService
	 * @Description:
	 * @param @param name  值机位置名称
	 * @param @param flightCompany_id  航空公司id
	 * @param @param floorNumber  楼层号
	 * @param @param longitude  经度
	 * @param @param latitude  纬度
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String name,String flightCompany_id,String floorNumber,Double longitude,Double latitude, String versionFlag) throws ServiceException;
	
	/**
	 * 移除航空公司自助值机位置表实体
	 * @Title: IFCSelfCheckinPositionService
	 * @Description: 
	 * @param @param id 航空公司自助值机位置表ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 根据航空公司id查询自助值机点位
	 * @param id 航空公司id
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<FCSelfCheckinPosition> queryListByFlightCompanyId(String flightCompany_id,
			String versionFlag) throws ServiceException;

	/**
	 * 根据航空公司id删除自助值机点
	 * @param id
	 * @param versionFlag
	 * @throws ServiceException
	 */
	void deleteByCompanyId(String id, String versionFlag) throws ServiceException;
}