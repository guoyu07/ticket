package com.ticket.service;

import java.util.Date;
import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.PhoneVisit;


/**
 * 电话回访记录业务接口
 * @ClassName: IPhoneVisitService   
 * @Description: 提供电话回访记录操作的增删改查   
 * @author HiSay  
 * @date  2015-11-02 23:14:13
 *
 */
public interface IPhoneVisitService extends IBaseService<PhoneVisit, String> {
	/**
	 * 保存电话回访记录实体
	 * @Title: IPhoneVisitService
	 * @Description:
	 * @param @param visitDate  回访日期
	 * @param @param employee_id  回访人
	 * @param @param customer_id  客户id
	 * @param @param contact  联系人
	 * @param @param contactPhone  联系电话
	 * @param @param content  沟通内容
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String visitDate,EmployeeInfo employeeInfo,String customer_id,String contact,
			String contactPhone,String content) throws ServiceException;
	
	/**
	 * 修改电话回访记录实体
	 * @Title: IPhoneVisitService
	 * @Description:
	 * @param @param visitDate  回访日期
	 * @param @param department_id  部门
	 * @param @param employee_id  回访人
	 * @param @param customer_id  客户id
	 * @param @param contact  联系人
	 * @param @param contactPhone  联系电话
	 * @param @param content  沟通内容
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String visitDate,String customer_id,String contact,String contactPhone,
			String content) throws ServiceException;
	
	/**
	 * 移除电话回访记录实体
	 * @Title: IPhoneVisitService
	 * @Description: 
	 * @param @param id 电话回访记录ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 批量彻底删除员工电话拜访记录实体
	 * @param idsValue
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException;

	/**
	 * 根据员工ID查询会员电话拜访记录
	 * @param versionFlag
	 * @param pageSize
	 * @param employeeId
	 * @return
	 */
	Pagination queryEntityByEmployeeId(String versionFlag, int pageSize, String employeeId,int state) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 获取所有的电话回访记录
	 * @method queryAll
	 * @param employeeInfo
	 * @param deleteFlag
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 * @return Pagination
	 * @date 2016-1-5 上午09:33:45
	 */
	Pagination queryAll(EmployeeInfo employeeInfo,Integer deleteFlag,Integer pageSize,int state) throws ServiceException;

	/**
	 * 根据客户id查询
	 * @param customer_id 客户id
	 * @param pageSize    页面大小
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryByCustomerId(String customer_id, int pageSize,
			String versionFlag) throws ServiceException;

	/**
	 * 查询所有未被回访的电话拜访记录
	 * @param versionFlag
	 * @param pageSize 页面大小
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryAllByNotAudit(String versionFlag, int pageSize) throws ServiceException;
	
	/**
	 * 查询所有电话拜访的总数
	 * @return
	 * @throws ServiceException
	 */
	long queryAllCount() throws ServiceException;

	/**
	 * 根据客户id统计电话拜访记录
	 * @param customer_id 客户id
	 * @return
	 * @throws ServiceException
	 */
	Integer countByCustomerId(String customer_id) throws ServiceException;

	/**
	 * 根据客户id查询电话拜访记录
	 * @param customer_id 客户id
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<PhoneVisit> queryListByCustomerId(String customer_id, String versionFlag) throws ServiceException;
	/**
	 * 根据客户id统计指定时间内的电话拜访记录
	 * @param customer_id
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Integer countByCustomerIdAndDate(String customer_id,Date startDate,Date endDate);
	
	/**
	 * 查询该登录实体所在部门的所有员工的电话拜访记录
	 * @param employeeInfo
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryAllWhereInDepartment(EmployeeInfo employeeInfo,int state) throws ServiceException;
}