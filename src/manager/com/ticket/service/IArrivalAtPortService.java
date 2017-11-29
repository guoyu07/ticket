package com.ticket.service;

import java.util.Date;
import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.ArrivalAtPort;
import com.ticket.pojo.DepartFromPort;


/**
 * 进港航班业务接口
 * @ClassName: IArrivalAtPortService   
 * @Description: 提供进港航班操作的增删改查   
 * @author HiSay  
 * @date  2015-12-01 10:07:57
 *
 */
public interface IArrivalAtPortService extends IBaseService<ArrivalAtPort, String> {
	/**
	 * 保存进港航班实体
	 * @Title: IArrivalAtPortService
	 * @Description:
	 * @param @param flt  航班号
	 * @param @param acw  航空公司两字码
	 * @param @param frs  航班实时状态
	 * @param @param doi  国内或国际
	 * @param @param pft  经停地
	 * @param @param org  起始站
	 * @param @param std  计划起飞时间
	 * @param @param abt  实际起飞时间
	 * @param @param eta  预计到达时间
	 * @param @param sta  计划到达时间
	 * @param @param tdt  实际到达时间
	 * @param @param btt  行李提取转盘
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String flt,String acw,String frs,String doi,String pft,String org,Date std,Date abt,Date eta,Date sta,Date tdt,String btt,String cedn,boolean isShare, String versionFlag) throws ServiceException;
	
	/**
	 * 修改进港航班实体
	 * @Title: IArrivalAtPortService
	 * @Description:
	 * @param @param flt  航班号
	 * @param @param acw  航空公司两字码
	 * @param @param frs  航班实时状态
	 * @param @param doi  国内或国际
	 * @param @param pft  经停地
	 * @param @param org  起始站
	 * @param @param std  计划起飞时间
	 * @param @param abt  实际起飞时间
	 * @param @param eta  预计到达时间
	 * @param @param sta  计划到达时间
	 * @param @param tdt  实际到达时间
	 * @param @param btt  行李提取转盘
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String flt,String acw,String frs,String doi,String pft,String org,Date std,Date abt,Date eta,Date sta,Date tdt,String btt,String cedn,boolean isShare, String versionFlag) throws ServiceException;
	
	/**
	 * 移除进港航班实体
	 * @Title: IArrivalAtPortService
	 * @Description: 
	 * @param @param id 进港航班ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 导入指定的excel
	 * @param filePath
	 * @return
	 * @throws ServiceException
	 */
	Integer importFromFile(String filePath) throws ServiceException;

	/**
	 * 根据城市和航班日期查询到港航班列表
	 * @param org  出发地
	 * @param des  目的地
	 * @param flightDate  航班日期
	 * @param versionFlag 版本标识
	 * @return
	 * @throws ServiceException
	 */
	List<ArrivalAtPort> queryListByCityAndDate(String org, String des,
			String flightDate, String versionFlag) throws ServiceException;


	/**
	 * 根据航班编号和日期查询到港实体
	 * @param flightNumber  航班编号
	 * @param flightDate    预计出发日期
	 * @param versionFlag   版本标识
	 * @return
	 * @throws ServiceException
	 */
	ArrivalAtPort queryArrivalFlightNoAndDate(String flightNumber,
			String flightDate, String versionFlag) throws ServiceException;
	
	/**
	 * 根据进港、离港解析是否有中转航班
	 * @param arrivalList
	 * @param departList
	 * @return
	 * @throws ServiceException
	 */
	ArrivalAtPort parseToBeTransferFlight(ArrivalAtPort arrivalAtPort, DepartFromPort departFromPort) throws ServiceException;

	/**
	 * 查询会员关注的中转航班
	 * @param flightNumber 航班号
	 * @param flightDate 航班日期
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	ArrivalAtPort queryTrasferByFlightNoAndDate(String flightNumber,
			String flightDate, String versionFlag) throws ServiceException;

	/**
	 * 根据关键词查询进港航班
	 * @param keyword  关键词
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<ArrivalAtPort> queryByKeyword(String keyword, String versionFlag) throws ServiceException;

	/**
	 * 根据航班日期和航班号查询到港航班
	 * @param flightNumber  航班号
	 * @param flightDate	航班日期
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	ArrivalAtPort queryByFlightNoAndDate(String flightNumber, String flightDate,
			String versionFlag) throws ServiceException;

	/**
	 * 根据起降地和航班日期统计经停地数量
	 * @param org 起飞地
	 * @param des 目的地
	 * @param flightDate 航班日期
	 * @param versionFlag
	 * @return
	 */
	Long getCountByStopover(String org, String des, String flightDate,
			String versionFlag);

	/**
	 * 根据条件查询航班
	 * @param keyword 关键词(航班号。航空公司二字码)
	 * @param flightDate 航班日期
	 * @param flightTime 航班时段
	 * @return
	 * @throws ServiceException
	 */
	List<ArrivalAtPort> queryByConditions(String keyword, String flightDate,
			String flightTime) throws ServiceException;
}