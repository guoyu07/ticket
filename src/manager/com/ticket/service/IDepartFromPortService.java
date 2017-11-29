package com.ticket.service;

import java.util.Date;
import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.DepartFromPort;


/**
 * 离港航班业务接口
 * @ClassName: IDepartFromPortService   
 * @Description: 提供离港航班操作的增删改查   
 * @author HiSay  
 * @date  2015-12-01 10:07:22
 *
 */
public interface IDepartFromPortService extends IBaseService<DepartFromPort, String> {
	/**
	 * 保存离港航班实体
	 * @Title: IDepartFromPortService
	 * @Description:
	 * @param @param flt  航班号
	 * @param @param acw  航空公司两字码
	 * @param @param frs  航班实时状态
	 * @param @param doi  国内或国际
	 * @param @param std  计划出港时间
	 * @param @param des  目的地三字码
	 * @param @param sta  计划到达时间
	 * @param @param tdt  实际到达时间
	 * @param @param cid  值机柜台
	 * @param @param gat  登机口
	 * @param @param got  预计登机时间
	 * @param @param etd  预计出港时间
	 * @param @param abt  实际出港时间
	 * @param @param rfc  航段
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String flt,String acw,String frs,String doi,Date std, String des,Date sta,Date tdt,String cid,String gat,Date got,Date etd,Date abt,boolean isShare, String versionFlag) throws ServiceException;
	
	/**
	 * 修改离港航班实体
	 * @Title: IDepartFromPortService
	 * @Description:
	 * @param @param flt  航班号
	 * @param @param acw  航空公司两字码
	 * @param @param frs  航班实时状态
	 * @param @param doi  国内或国际
	 * @param @param std  计划出港时间
	 * @param @param des  目的地三字码
	 * @param @param sta  计划到达时间
	 * @param @param tdt  实际到达时间
	 * @param @param cid  值机柜台
	 * @param @param gat  登机口
	 * @param @param got  预计登机时间
	 * @param @param etd  预计出港时间
	 * @param @param abt  实际出港时间
	 * @param @param rfc  航段
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String flt,String acw,String frs,String doi,Date std,String des,Date sta,Date tdt,String cid,String gat,Date got,Date etd,Date abt,boolean isShare, String versionFlag) throws ServiceException;
	
	/**
	 * 移除离港航班实体
	 * @Title: IDepartFromPortService
	 * @Description: 
	 * @param @param id 离港航班ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 导入excel表数据
	 * @param filePath
	 * @return
	 * @throws ServiceException
	 */
	Integer importFromFile(String filePath) throws ServiceException;

	/**
	 * 根据航班号和日期查询航班信息
	 * @param flightNumber  航班号
	 * @param departPortTime  日期
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	DepartFromPort queryByFlightNoAndDate(String flightNumber, String flightDate,String versionFlag);

	/**
	 * 根据城市和时间查询离港航班列表
	 * @param org  出发地
	 * @param des  目的地
	 * @param flightDate  航班时间
	 * @param versionFlag 版本标识
	 * @return
	 * @throws ServiceException
	 */
	List<DepartFromPort> queryListByCityAndDate(String org, String des,
			String flightDate, String versionFlag) throws ServiceException; 
	
	/**
	 * @Description：随机得到一个离港
	 * @return
	 */
	DepartFromPort randomGetOne();

	/**
	 * 查询离港航班列表
	 * @param size 数据条数
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<DepartFromPort> queryList(Integer size, String versionFlag) throws ServiceException;

	/**
	 * 根据关键词查询进港航班
	 * @param keyword 关键词
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<DepartFromPort> queryBykeyword(String keyword, String versionFlag) throws ServiceException;
	/**
	 * 根据航班实时状态查找航班
	 * @param frs
	 * @return
	 * @throws ServiceException
	 */
	List<DepartFromPort> getByFrs(String frs) throws ServiceException;

	/**
	 * 根据起飞地和目的地以及航班日期查询统计有经停地的航班数量
	 * @param org 起飞地
	 * @param des 目的地
	 * @param flightDate 航班日期
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	Long getCountByStopover(String org, String des, String flightDate,
			String versionFlag) throws ServiceException;

	/**
	 * 根据条件查询航班信息
	 * @param keyword 关键词（航班号 目的地三字码）
	 * @param flightDate 航班日期
	 * @param flightTime 航班时段
	 * @return
	 * @throws ServiceException
	 */
	List<DepartFromPort> queryByConditions(String keyword, String flightDate,
			String flightTime) throws ServiceException;
}