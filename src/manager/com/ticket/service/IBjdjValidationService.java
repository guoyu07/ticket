package com.ticket.service;

import java.util.Date;
import java.util.List;

import com.ticket.enumer.ReportRegion;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.AirportEmployee;
import com.ticket.pojo.BjdjAppointment;
import com.ticket.pojo.BjdjDispatch;
import com.ticket.pojo.BjdjHall;
import com.ticket.pojo.BjdjValidation;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.Report;


/**
 * 便捷登机验证表业务接口
 * @ClassName: IBjdjValidationService   
 * @Description: 提供便捷登机验证表操作的增删改查   
 * @author HiSay  
 * @date  2015-11-23 22:52:42
 *
 */
public interface IBjdjValidationService extends IBaseService<BjdjValidation, String> {
	/**
	 * 保存便捷登机验证表实体
	 * @Title: IBjdjValidationService
	 * @Description:
	 * @param time  验证时间
	 * @param serviceCode_id  服务码ID
	 * @param flightNo  航班号
	 * @param member_id  用户ID
	 * @param employee_id  机场员工ID
	 * @param ended  服务是否结束
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	BjdjValidation persist(BjdjAppointment appointment, String hallId, Date time,String employee_id,boolean passed) throws ServiceException;
	
	/**
	 * 保存便捷登机验证表实体
	 * @Title: IBjdjValidationService
	 * @Description:
	 * @param time  验证时间
	 * @param serviceCode_id  服务码ID
	 * @param flightNo  航班号
	 * @param member_id  用户ID
	 * @param employee_id  机场员工ID
	 * @param ended  服务是否结束
	 * @return  保存成功则返回true, 保存失败则返回false.
	 * @throws ServiceException   
	 */
//	BjdjValidation persist(String serviceCode, String hallId, Date time,String employee_id,boolean passed) throws ServiceException;
	
	/**
	 * 保存便捷登机验证表实体
	 * @Title: IBjdjValidationService
	 * @Description:
	 * @param time  验证时间
	 * @param serviceCode_id  服务码ID
	 * @param flightNo  航班号
	 * @param member_id  用户ID
	 * @param employee_id  机场员工ID
	 * @param ended  服务是否结束
	 * @return  保存成功则返回true, 保存失败则返回false.
	 * @throws ServiceException   
	 */
	BjdjValidation persist(BjdjAppointment appointment, BjdjHall hall, Date time,AirportEmployee employee,boolean passed) throws ServiceException;
	
	/**
	 * 保存便捷登机验证表实体
	 * @Title: IBjdjValidationService
	 * @Description:
	 * @param time  验证时间
	 * @param serviceCode_id  服务码ID
	 * @param flightNo  航班号
	 * @param member_id  用户ID
	 * @param employee_id  机场员工ID
	 * @param ended  服务是否结束
	 * @return  保存成功则返回true, 保存失败则返回false.
	 * @throws ServiceException   
	 */
//	BjdjValidation persist(BjdjServiceCode serviceCodeObj, BjdjHall hall, Date time,AirportEmployee employee,boolean passed) throws ServiceException;
	
	/**
	 * 修改便捷登机验证表实体
	 * @Title: IBjdjValidationService
	 * @Description:
	 * @param time  验证时间
	 * @param serviceCode_id  服务码ID
	 * @param flightNo  航班号
	 * @param member_id  用户ID
	 * @param employee_id  机场员工ID
	 * @param ended  服务是否结束
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	BjdjValidation merge(String id,BjdjAppointment appointment,Date time,String employee_id,boolean passed) throws ServiceException;
	
	/**
	 * 修改便捷登机验证表实体
	 * @Title: IBjdjValidationService
	 * @Description:
	 * @param time  验证时间
	 * @param serviceCode_id  服务码ID
	 * @param flightNo  航班号
	 * @param member_id  用户ID
	 * @param employee_id  机场员工ID
	 * @param ended  服务是否结束
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 */
	BjdjValidation merge(String id,BjdjAppointment appointment,Date time,AirportEmployee employee,boolean passed) throws ServiceException;
	
	/**
	 * 移除便捷登机验证表实体
	 * @Title: IBjdjValidationService
	 * @Description: 
	 * @param id 便捷登机验证表ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * @Description：检查某个预约是否已经验证通过
	 * @param appointment_id
	 * @return
	 */
	boolean isPassedValidatation(String appointment_id);
	
	/**
	 * @Description：检查某个预约是否已经验证通过
	 * @param appointment_id
	 * @return
	 */
	boolean isPassedValidatation(BjdjAppointment appointment);
	
	/**
	 * @Description：得到一个验证的分单
	 * @param bjdjValidation
	 * @return
	 */
	BjdjDispatch getDispatch(BjdjValidation bjdjValidation);
	/**
	 * 根据id集合得到验证的集合
	 * @param ids
	 * @return
	 */
	List<BjdjValidation> findInIds(String ids);
	/**
	 * 根据服务码查找验证表
	 * @param serviceCode
	 * @return
	 */
	BjdjValidation findByServiceCode(String serviceCode);
	/**
	 * 根据服务码查找验证表
	 * @param serviceCode1
	 * @return
	 */
	BjdjValidation findByServiceCode1(String serviceCode);
	
	/**
	 * 根据时间段查找验证表
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ServiceException
	 */
	List<BjdjValidation> getByTimes(Date startTime,Date endTime);
	
	/**
	 * 根据时间段查找验证表(进厅)
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ServiceException
	 */
	List<BjdjValidation> getEnterHallByTimes(Date startTime,Date endTime);
	
	/**
	 * 多条件的分页查询
	 * @param serviceCode 服务器
	 * @param flightNumber 航班号
	 * @param flightDate 航班日期
	 * @param pageSize 分页大小
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryEntityByAdmin(String serviceCode, String flightNumber, Date startTime, Date endTime, Integer pageSize)
			throws ServiceException;
	/**
	 * 多条件的分页查询
	 * @param bjdjHall
	 * @param serviceCode
	 * @param flightNumber
	 * @param startTime
	 * @param endTime
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryEntityByAdmin(BjdjHall bjdjHall,String serviceCode, String flightNumber, Date startTime, Date endTime, Integer pageSize)
			throws ServiceException;
	/**
	 * 查找所有的服务码验证总数
	 * @return
	 * @throws ServiceException
	 */
	long queryAllCount() throws ServiceException;
	
	/**
	 * 报表统计
	 * @param region 时间范围
	 * @return
	 */
	List<Report> reportTogether(ReportRegion region);
	
	/**
	 * 报表详细统计
	 * @param region 时间范围
	 * @return
	 */
	List<Report> reportDetails(ReportRegion region);
}