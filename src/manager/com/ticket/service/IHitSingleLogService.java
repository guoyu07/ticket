package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.HitSingleLog;
import com.ticket.pojo.Pagination;


/**
 * 撞单日志业务接口
 * @ClassName: IHitSingleLogService   
 * @Description: 提供撞单日志操作的增删改查   
 * @author HiSay  
 * @date  2016-01-03 09:38:37
 *
 */
public interface IHitSingleLogService extends IBaseService<HitSingleLog, String> {
	/**
	 * 保存撞单日志实体
	 * @Title: IHitSingleLogService
	 * @Description:
	 * @param @param channelCustomerInfo_id  目标客户
	 * @param @param employeeInfo_id  申请员工
	 * @param @return  保存成功则返回true, 保存失败则返回错误信息.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	String persist(String channelCustomerInfo_id,EmployeeInfo employeeInfo,String agreement_id) throws ServiceException;
	
	/**
	 * 移除撞单日志实体
	 * @Title: IHitSingleLogService
	 * @Description: 
	 * @param @param id 撞单日志ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	/**
	 * @author wangjiafeng
	 *  获取所有的撞单申请
	 * @method queryAll
	 * @param employeeInfo
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 * @return Pagination
	 * @date 2016-1-3 上午10:35:53
	 */
	Pagination queryAll(EmployeeInfo employeeInfo,Integer pageSize) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 判定撞单申请
	 * @method changeState
	 * @param id
	 * @param state
	 * @param remark
	 * @return
	 * @throws ServiceException
	 * @return String
	 * @date 2016-1-15 上午11:30:33
	 */
	Boolean changeState(String id,Integer state,String remark) throws ServiceException;
}