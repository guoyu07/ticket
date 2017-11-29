package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.FlightCompany;


/**
 * 航空公司信息业务接口
 * @ClassName: IFlightCompanyService   
 * @Description: 提供航空公司信息操作的增删改查   
 * @author HiSay  
 * @date  2015-11-03 13:39:40
 *
 */
public interface IFlightCompanyService extends IBaseService<FlightCompany, String> {
	/**
	 * 保存航空公司信息实体
	 * @Title: IFlightCompanyService
	 * @Description:
	 * @param @param name  公司名称
	 * @param @param cityCode  公司二字码
	 * @param @param phone  公司电话
	 * @param @param theOfficialWebsite  官网
	 * @param @param customerCounter  散客柜台
	 * @param @param teamCounter  团队柜台
	 * @param @param firstClassCounter  头等舱柜台
	 * @param @param emergencyCounter  应急柜台
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String name,String cityCode,String threeCode,String phone,String theOfficialWebsite,String customerCounter,String teamCounter,String firstClassCounter,String emergencyCounter,Integer orderValue,String logo,Double longitude,Double latitude,String[] selfLongitude,String[] selfLatitude,String[] positionName,String[] floorNumber, String versionFlag) throws ServiceException;
	
	/**
	 * 修改航空公司信息实体
	 * @Title: IFlightCompanyService
	 * @Description:
	 * @param @param name  公司名称
	 * @param @param cityCode  公司二字码
	 * @param @param phone  公司电话
	 * @param @param theOfficialWebsite  官网
	 * @param @param customerCounter  散客柜台
	 * @param @param teamCounter  团队柜台
	 * @param @param firstClassCounter  头等舱柜台
	 * @param @param emergencyCounter  应急柜台
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String name,String cityCode,String threeCode,String phone,String theOfficialWebsite,String customerCounter,String teamCounter,String firstClassCounter,String emergencyCounter,Integer orderValue,String logo,Double longitude,Double latitude,String[] selfLongitude,String[] selfLatitude,String[] positionName,String[] floorNumber, String versionFlag) throws ServiceException;
	
	/**
	 * 移除航空公司信息实体
	 * @Title: IFlightCompanyService
	 * @Description: 
	 * @param @param id 航空公司信息ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 批量彻底删除航空公司信息实体
	 * @param idsValue
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException;

	/**
	 * 查询航空公司实体列表
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<FlightCompany> queryList(Integer size,String versionFlag) throws ServiceException;

	/**
	 * 根据航空公司二字码查询航空公司信息
	 * @param twoCode 二字码
	 * @param versionFlag
	 * @return
	 */
	FlightCompany queryEntityByTwoCode(String twoCode, String versionFlag);

	/**
	 * 根据关键词查询航空公司列表
	 * @param keyword 关键词
	 * @param orderFlag 排序标识
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<FlightCompany> queryByConditions(String keyword, String orderFlag, String versionFlag) throws ServiceException;
	/**
	 * 根据关键词查询航空公司列表
	 * @param keyword 关键词
	 * @param orderFlag 排序标识
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<FlightCompany> queryByConditions2(String keyword, String orderFlag, String versionFlag) throws ServiceException;

	/**
	 * 根据三字码查询航空公司信息
	 * @param threeCode 三字码
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	FlightCompany queryByThreeCode(String threeCode, String versionFlag) throws ServiceException;

	/**
	 * 航空公司三字码转二字码
	 * @param threeCode  三字码
	 * @param versionFlag 版本标识
	 * @return
	 * @throws ServiceException
	 */
	String changeCode(String threeCode, String versionFlag) throws ServiceException;
	
	/**
	 * 根据航空公司三字码航班号转二字码航班号
	 * @param flightNumber  航班号
	 * @return
	 * @throws ServiceException
	 */
	String trangeFlightNoByCompanyCode(String flightNumber) throws ServiceException;

	/**
	 * 航空公司二字码转三字码
	 * @param twoCode 二字码
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	String changeCodeTwoToThree(String twoCode, String versionFlag) throws ServiceException;

	/**
	 * 验证航空公司是否已经存在
	 * @param name 航空公司名称
	 * @return
	 * @throws ServiceException
	 */
	boolean isExistByName(String name) throws ServiceException;
	
	/**
	 * 转换航班编号
	 * @param flightNumber 航班编号
	 * @return
	 */
	String transferFlightNumber(String flightNumber);
	
	/**
	 * 根据航班信息查询航空公司信息
	 * @param flightNumber 航班号
	 * @return
	 * @throws ServiceException
	 */
	FlightCompany queryCompanyByFlt(String flightNumber) throws ServiceException;
}