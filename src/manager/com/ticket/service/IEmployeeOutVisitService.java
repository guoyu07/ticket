package com.ticket.service;

import java.util.Date;
import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.EmployeeOutVisit;
import com.ticket.pojo.Pagination;


/**
 * 员工外出拜访记录业务接口
 * @ClassName: IEmployeeOutVisitService   
 * @Description: 提供员工外出拜访记录操作的增删改查   
 * @author HiSay  
 * @date  2015-11-02 22:49:40
 *
 */
public interface IEmployeeOutVisitService extends IBaseService<EmployeeOutVisit, String> {
	/**
	 * 保存员工外出拜访记录实体
	 * @Title: IEmployeeOutVisitService
	 * @Description:
	 * @param @param visitDate  拜访日期
	 * @param @param customer_id  拜访客户
	 * @param @param contact  联系人
	 * @param @param contactPhone  联系电话
	 * @param @param visitPurpose  拜访目的
	 * @param @param employee_id  出访人
	 * @param @param accompanyPerPerson  陪同人
	 * @param @param setoutTime  出发时间
	 * @param @param backTime  返回时间
	 * @param @param visitResult  拜访结果
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(Date visitDate,String customer_id,String contact,String contactPhone,
			String visitPurpose,EmployeeInfo employeeInfo,String accompanyPerPerson,
			String setoutTime,String backTime,String visitResult) throws ServiceException;
	
	/**
	 * 修改员工外出拜访记录实体
	 * @Title: IEmployeeOutVisitService
	 * @Description:
	 * @param @param visitDate  拜访日期
	 * @param @param customer_id  拜访客户
	 * @param @param contact  联系人
	 * @param @param contactPhone  联系电话
	 * @param @param visitPurpose  拜访目的
	 * @param @param employee_id  出访人
	 * @param @param accompanyPerPerson  陪同人
	 * @param @param setoutTime  出发时间
	 * @param @param backTime  返回时间
	 * @param @param visitResult  拜访结果
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, Date visitDate,String customer_id,String contact,
			String contactPhone,String visitPurpose,
			String accompanyPerPerson,String setoutTime,String backTime,
			String visitResult) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 填写回访记录
	 * @method merge
	 * @param id
	 * @param backTime
	 * @param visitResult
	 * @return
	 * @throws ServiceException
	 * @return boolean
	 * @date 2016-1-9 上午11:15:04
	 */
	boolean merge(String id,String backTime,
			String visitResult,Integer state) throws ServiceException;
	
	/**
	 * 移除员工外出拜访记录实体
	 * @Title: IEmployeeOutVisitService
	 * @Description: 
	 * @param @param id 员工外出拜访记录ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 批量彻底删除会员外出拜访记录实体
	 * @param idsValue
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException;

	/**
	 * 根据会员ID获取会员外出拜访记录
	 * @param versionFlag
	 * @param pageSize    页面大小
	 * @param employeeId  员工ID
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryEntityByEmployeeId(String versionFlag, int pageSize, String employeeId,int state) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 获取所有的外出拜访记录
	 * @method queryAll
	 * @param employeeInfo
	 * @param deleteFlag
	 * @param pageSIze
	 * @return
	 * @throws ServiceException
	 * @return Pagination
	 * @date 2016-1-4 下午04:39:32
	 */
	Pagination queryAll(EmployeeInfo employeeInfo,Integer deleteFlag,Integer pageSIze,int state) throws ServiceException;

	/**
	 * 根据客户id查询客户的拜访记录
	 * @param customer_id  客户id
	 * @param pageSize 页面大小
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryByCustomerId(String customer_id, int pageSize, String versionFlag) throws ServiceException;

	/**
	 * 查询所有未回访的拜访记录
	 * @param versionFlag
	 * @param pageSize 页面大小
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryAllByNotAudit(String versionFlag, int pageSize) throws ServiceException;
	/**
	 * 查询所有的外出拜访记录总数
	 * @return
	 * @throws ServiceException
	 */
	long queryAllCount() throws ServiceException;

	/**
	 * 根据客户id统计外出拜访记录
	 * @param customer_id 客户id
	 * @return
	 * @throws ServiceException
	 */
	Integer countByCustomerId(String customer_id) throws ServiceException;

	/**
	 * 根据客户id查看拜访记录
	 * @param customer_id 客户id
	 * @param versionFlag
	 * @return
	 */
	List<EmployeeOutVisit> queryListByCustomerId(String customer_id, String versionFlag);
	/**
	 * 根据客户id统计指定时间内的外出拜访记录
	 * @param customer_id
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Integer countByCustomerIdAndDate(String customer_id,Date startDate,Date endDate);
	
	/**
	 * 查找该登录实体所在的部门的所有拜访记录
	 * @param employeeInfo
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryAllWhereInDepartment(EmployeeInfo employeeInfo,int state) throws ServiceException;
}