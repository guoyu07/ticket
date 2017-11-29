package com.ticket.service;

import java.util.Date;
import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.DepartmentInfo;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.SaleTask;


/**
 * 销售任务业务接口
 * @ClassName: ISaleTaskService   
 * @Description: 提供销售任务操作的增删改查   
 * @author HiSay  
 * @date  2016-05-05 11:18:16
 *
 */
public interface ISaleTaskService extends IBaseService<SaleTask, String> {
	/**
	 * 保存销售任务实体
	 * @Title: ISaleTaskService
	 * @Description:
	 * @param @param startTime  起始时间
	 * @param @param endTime  结束时间
	 * @param @param department  任务等级
	 * @param @param employee  创建员工
	 * @param @param signCount  签约数
	 * @param @param recharge  充值金额
	 * @param @param phoneCount  电话拜访量
	 * @param @param visitCount  外出拜访量
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String name, Date startTime,Date endTime,int signCount,double recharge,int phoneCount,int visitCount, String versionFlag) throws ServiceException;
	
	/**
	 * 修改销售任务实体
	 * @Title: ISaleTaskService
	 * @Description:
	 * @param @param startTime  起始时间
	 * @param @param endTime  结束时间
	 * @param @param department  任务等级
	 * @param @param employee  创建员工
	 * @param @param signCount  签约数
	 * @param @param recharge  充值金额
	 * @param @param phoneCount  电话拜访量
	 * @param @param visitCount  外出拜访量
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String name, Date startTime,Date endTime,int signCount,double recharge,int phoneCount,int visitCount, String versionFlag) throws ServiceException;
	
	/**
	 * 移除销售任务实体
	 * @Title: ISaleTaskService
	 * @Description: 
	 * @param @param id 销售任务ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 下发任务给各一线员工
	 * @param taskId
	 * @param emps
	 * @param signCounts
	 * @param recharges
	 * @param phoneCounts
	 * @param visitCounts
	 */
	void publish(String taskId, String[] emps, int[] signCounts, double[] recharges, int[] phoneCounts, int[] visitCounts) throws ServiceException;
	
	/**
	 * 签单的总体完成量
	 * @return
	 */
	int completionDegreeForSignCount(Date startDate, Date endDate);
	
	/**
	 * 充值的总体完成量
	 * @return
	 */
	double completionDegreeForRecharge(Date startDate, Date endDate);
	
	/**
	 * 电话拜访量的总体完成量
	 * @return
	 */
	int completionDegreeForPhoneCount(Date startDate, Date endDate);
	
	/**
	 * 外出拜访量的总体完成量
	 * @return
	 */
	int completionDegreeForVisitCount(Date startDate, Date endDate);
	
	/**
	 * 部门签单完成量
	 * @param department 哪个部门的完成度
	 * @return
	 */
	int departmentCompletionDegreeForSignCount(DepartmentInfo department, Date startDate, Date endDate);
	
	/**
	 * 部门充值完成量
	 * @param department 哪个部门的完成度
	 * @return
	 */
	double departmentCompletionDegreeForRecharge(DepartmentInfo department, Date startDate, Date endDate);
	
	/**
	 * 部门电话拜访完成量
	 * @param department 哪个部门的完成度
	 * @return
	 */
	int departmentCompletionDegreeForPhoneCount(DepartmentInfo department, Date startDate, Date endDate);
	
	/**
	 * 外出电话拜访完成量
	 * @param department 哪个部门的完成度
	 * @return
	 */
	int departmentCompletionDegreeForVisitCount(DepartmentInfo department, Date startDate, Date endDate);
	
	/**
	 * 一个人的签单完成进度
	 * @param employee
	 * @return
	 */
	int onesCompletionDegreeForSignCount(EmployeeInfo employee, Date startDate, Date endDate);
	
	/**
	 * 一个人的充值完成进度
	 * @param employee
	 * @return
	 */
	double onesCompletionDegreeForRecharge(EmployeeInfo employee, Date startDate, Date endDate);
	
	/**
	 * 一个人的电话拜访完成进度
	 * @param employee
	 * @return
	 */
	int onesCompletionDegreeForPhoneCount(EmployeeInfo employee, Date startDate, Date endDate);
	
	/**
	 * 一个人的外出拜访完成进度
	 * @param employee
	 * @return
	 */
	int onesCompletionDegreeForVisitCount(EmployeeInfo employee, Date startDate, Date endDate);
	
	/**
	 * 获取一个任务的所有子任务
	 * @param parent
	 * @return
	 */
	List<SaleTask> subSaleTask(SaleTask parent);
	
	/**
	 * 获取本月所有的任务的充值数
	 * @return
	 * @throws ServiceException
	 */
	double queryAllRechargeCount() throws ServiceException;
	
	/**
	 * 根据所在部门查找所在部门及以下部门的任务数据
	 * @param employeeInfo
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryWhereInDepartment(EmployeeInfo employeeInfo) throws ServiceException;
	
	/**
	 * 得到签单占比
	 * @return
	 */
	int getSignCountPercent();
	
	/**
	 * 设置签单占比
	 * @param percent
	 */
	void setSignCountPercent(int percent);
	
	/**
	 * 得到签单占比
	 * @return
	 */
	int getRechargePercent();
	
	/**
	 * 设置签单占比
	 * @param percent
	 */
	void setRechargePercent(int percent);
	
	/**
	 * 得到签单占比
	 * @return
	 */
	int getPhoneCountPercent();
	
	/**
	 * 设置签单占比
	 * @param percent
	 */
	void setPhoneCountPercent(int percent);
	
	/**
	 * 得到签单占比
	 * @return
	 */
	int getVisitCountPercent();
	
	/**
	 * 设置签单占比
	 * @param percent
	 */
	void setVisitCountPercent(int percent);
}