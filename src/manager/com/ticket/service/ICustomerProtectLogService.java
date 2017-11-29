package com.ticket.service;

import java.util.Date;
import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CustomerProtectLog;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.Pagination;


/**
 * 客保日志业务接口
 * @ClassName: ICustomerProtectLogService   
 * @Description: 提供客保日志操作的增删改查   
 * @author HiSay  
 * @date  2016-01-02 10:21:06
 *
 */
public interface ICustomerProtectLogService extends IBaseService<CustomerProtectLog, String> {
	/**
	 * 保存客保日志实体
	 * @Title: ICustomerProtectLogService
	 * @Description:
	 * @param @param channelCustomerInfo_id  保护客户
	 * @param @param employeeInfo_id  销售人员
	 * @param @param startTime  保护开始时间
	 * @param @param endTime  保护结束时间
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	String persist(String channelCustomerInfo_id,EmployeeInfo employeeInfo) throws ServiceException;
	
	/**
	 * 移除客保日志实体
	 * @Title: ICustomerProtectLogService
	 * @Description: 
	 * @param @param id 客保日志ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 检测该客户是否能保护
	 * @method validateIsProtected
	 * @param channelCustomerInfo_id
	 * @param employeeInfo_id
	 * @return
	 * @throws ServiceException
	 * @return String
	 * @date 2016-1-2 上午11:03:58
	 */
	String validateIsProtected(String channelCustomerInfo_id,String employeeInfo_id) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 获取保护日志列表
	 * @method queryAllList
	 * @param channelCustomerInfo_id
	 * @param employeeInfo_id
	 * @param startTime
	 * @param size
	 * @return
	 * @throws ServiceException
	 * @return List<CustomerProtectLog>
	 * @date 2016-1-2 上午11:07:08
	 */
	List<CustomerProtectLog> queryAllList(String channelCustomerInfo_id,String employeeInfo_id,Date startTime,Integer expire,Integer size) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 获取所有的客保日志列表
	 * @method queryAll
	 * @param employeeInfo
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 * @return Pagination
	 * @date 2016-1-2 下午02:30:05
	 */
	Pagination queryAll(EmployeeInfo employeeInfo,Integer pageSize) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 检测客保系统保护时间是否到期,并把到期的客户放入公共池
	 * @method validateCustomerProtectIsToPublicPool
	 * @throws ServiceException
	 * @return void
	 * @date 2016-1-2 下午03:22:44
	 */
	void validateCustomerProtectIsToPublicPool() throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 查询已过期的客保日志
	 * @method queryExpireLog
	 * @param date
	 * @param size
	 * @return
	 * @throws ServiceException
	 * @return List<CustomerProtectLog>
	 * @date 2016-1-2 下午03:44:05
	 */
	List<CustomerProtectLog> queryExpireLog(Date date,Integer expire,Integer size) throws ServiceException;
	/**
	 * @author 改变过期状态
	 * 改变过期状态
	 * @method changeExpire
	 * @param id
	 * @return
	 * @throws ServiceException
	 * @return Boolean
	 * @date 2016-1-2 下午04:08:05
	 */
	Boolean changeExpire(String id) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 延长保护期
	 * @method changeEndTime
	 * @param id
	 * @param endTime
	 * @return
	 * @throws ServiceException
	 * @return Boolean
	 * @date 2016-1-2 下午04:41:58
	 */
	String changeEndTime(String id,Integer endTime) throws ServiceException;
	/**
	 * @author wangjiafeng 
	 * 获取保护期3天内的保护日志
	 * @method queryNewLog
	 * @param channelCustomerInfo_id
	 * @param threeDate
	 * @param isExpire
	 * @return
	 * @throws ServiceException
	 * @return CustomerProtectLog
	 * @date 2016-1-3 上午10:22:13
	 */
	CustomerProtectLog queryNewLogLess(String channelCustomerInfo_id,Date threeDate,Integer isExpire) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 获取4到15天内的保护日志
	 * @method queryNewLogThan
	 * @param channelCustomerInfo_id
	 * @param threeDate
	 * @param isExpire
	 * @return
	 * @throws ServiceException
	 * @return CustomerProtectLog
	 * @date 2016-1-3 上午10:24:17
	 */
	CustomerProtectLog queryNewLogThan(String channelCustomerInfo_id,Date threeDate,Integer isExpire) throws ServiceException;
	/**
	 * @author wangjiafeng
	 *  获取3天内即将到期的客保日志列表
	 * @method queryNewLogListLess
	 * @param threeDate
	 * @param isExpire
	 * @return
	 * @throws ServiceException
	 * @return List<CustomerProtectLog>
	 * @date 2016-1-7 上午09:52:40
	 */
	List<CustomerProtectLog> queryNewLogListLess(Date threeDate,Integer isExpire) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 获取4到15天内的即将到期的客保日志列表
	 * @method queryNewLogListThan
	 * @param threeDate
	 * @param isExpire
	 * @return
	 * @throws ServiceException
	 * @return List<CustomerProtectLog>
	 * @date 2016-1-7 上午09:53:27
	 */
	List<CustomerProtectLog> queryNewLogListThan(Date threeDate,Integer isExpire) throws ServiceException;
	/**
	 * @author wangjiafeng
	 *  验证是否能保护
	 * @method validateIsChangeEndTime
	 * @param endTime
	 * @return
	 * @throws ServiceException
	 * @return Boolean
	 * @date 2016-1-11 下午02:05:19
	 */
	Boolean validateIsChangeEndTime(String id) throws ServiceException;

	/**
	 * 根据客户id查询客保信息
	 * @param channelCustomerInfo_id 客户id
	 * @return
	 * @throws ServiceException
	 */
	CustomerProtectLog querybyCustomerId(String channelCustomerInfo_id) throws ServiceException;
	
	/**
	 * 根据部门id统计客保
	 * @param dept_id 部门id
	 * @return
	 * @throws ServiceException
	 */
	Long countIntentionByDept(String dept_id) throws ServiceException;
	
	/**
	 * 统计当天的客保数量
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	Long countToday(String versionFlag) throws ServiceException;
	
	/**
	 * 根据时间段统计客保数量
	 * @param start 起始日期
	 * @param end 终止日期
	 * @param dept_id 部门id
	 * @return
	 */
	Long countCustomerProtectByDate(Date startDate, Date endDate, String dept_id) throws ServiceException;

	/**
	 * 验证客户是否证受保护
	 * @param customer_id 客户id
	 * @return
	 * @throws ServiceException
	 */
	boolean isInProtected(String customer_id) throws ServiceException;
	
	/**
	 * 根据所在部门查看所在部门及以下部门的数据
	 * @param employeeInfo
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryWhereInDepartment(EmployeeInfo employeeInfo) throws ServiceException;
}