package com.ticket.service;

import java.util.Date;
import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.AirportPlan;

public interface IInDataService extends IBaseService<AirportPlan, String> {

	/**
	 * 导入数据，将计划航班的数据导入到数据库
	 * @return
	 * @throws Exception 
	 */
	public boolean inDataExcelToDataBase(String filePath) throws Exception;

	/**
	 * 根据出发地和目的地四字码查询
	 * @param start 出发地四字码 
	 * @param end   目的地四字码
	 * @param versionFlag
	 * @return
	 */
	AirportPlan getFirsrtData(String start, String end, String versionFlag);

	/**
	 * 根据起飞地和目的地查询计划航班
	 * @param searchDate  查询日期
	 * @param start		  起飞地四字码
	 * @param arrive	目的地四字码
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<AirportPlan> queryFlight(Date searchDate, String start,
			String arrive, String versionFlag) throws ServiceException;

	/**
	 * 根据航班号得出航班数据
	 * @param flightNo 航班号
	 * @param versionFlag
	 * @return
	 */
	public AirportPlan getDataByNo(String flightNo, String versionFlag);

	/**
	 * 根据航班号和航班日期查询计划航班
	 * @param flightNumber  航班编号
	 * @param searchDate    航班日期
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	public List<AirportPlan> queryFlightByNo(String flightNumber,
			Date searchDate, String versionFlag) throws ServiceException;

	/**
	 * 根据起降地四字码和航班日期查询计划航班
	 * @param flightDate  航班日期
	 * @param org		起飞地四字码
	 * @param des		目的地四字码
	 * @param versionFlag  版本标识
	 * @return
	 * @throws ServiceException
	 */
	AirportPlan queryByNoAndDateAndCycle(String flightDate, String setoutCityFourCode,
			String arriveCityFourCode,String cycle, String versionFlag) throws ServiceException;

	/**
	 * 根据航班编号和航班日期查询计划航班
	 * @param flightNumber  航班编号
	 * @param flightDate    航班日期
	 * @param versionFlag   版本标识
	 * @return
	 * @throws ServiceException
	 */
	AirportPlan queryFlightByNoAndDate(String flightNumber, String versionFlag) throws ServiceException;
	
	/**
	 *	根据出发城市和到达城市以计划日期分组查询计划航班
	 * @param setoutCity  出发城市四字码
	 * @param reachCity	  到达城市四字码
	 * @return
	 * @throws ServiceException
	 */
	List<AirportPlan> getListGroupByPlanDate(String setoutCity,String reachCity) throws ServiceException;

	/**
	 * 根据航班号以计划日期分组查询计划航班
	 * @param flightNumber  航班号
	 * @return
	 * @throws ServiceException
	 */
	List<AirportPlan> getListGroupByFlt(String flightNumber) throws ServiceException;

	/**
	 * 根据航班编号，起始日期，终止日期查询航班信息
	 * @param flightNumber 航班编号
	 * @param startDate  起始日期
	 * @param endDate    终止日期
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	public AirportPlan getDataByNoAndSADate(String transferFlightNumber,
			Date startDate, Date endDate, String flag,String versionFlag) throws ServiceException;

	/**
	 * 根据航班号和航班日期查询离港航班
	 * @param flightNumber  航班号
	 * @param flightDate	航班日期
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	public AirportPlan queryDepartByNoAndDate(String flightNumber,
			String flightDate, String versionFlag) throws ServiceException;

	/**
	 * 根据航班号和航班日期查询进港航班
	 * @param flightNumber  航班号
	 * @param flightDate	航班日期
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	public AirportPlan queryArrivalByNoAndDate(String flightNumber,
			String flightDate, String versionFlag) throws ServiceException;
}
