package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CustomerTransferLog;


/**
 * 客户转让日志业务接口
 * @ClassName: ICustomerTransferLogService   
 * @Description: 提供客户转让日志操作的增删改查   
 * @author HiSay  
 * @date  2016-04-27 15:04:28
 *
 */
public interface ICustomerTransferLogService extends IBaseService<CustomerTransferLog, String> {
	/**
	 * 保存客户转让日志实体
	 * @Title: ICustomerTransferLogService
	 * @Description:
	 * @param @param beforeEmployee_id  转让前员工
	 * @param @param afterEmployee_id  转让后员工
	 * @param @param operator_id  操作员ID
	 * @param @param customer_id  客户ID
	 * @param @param remark  备注
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String beforeEmployee_id,String afterEmployee_id,String operator_id,String customer_id,String remark, String versionFlag) throws ServiceException;
	
	/**
	 * 修改客户转让日志实体
	 * @Title: ICustomerTransferLogService
	 * @Description:
	 * @param @param beforeEmployee_id  转让前员工
	 * @param @param afterEmployee_id  转让后员工
	 * @param @param operator_id  操作员ID
	 * @param @param customer_id  客户ID
	 * @param @param remark  备注
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String beforeEmployee_id,String afterEmployee_id,String operator_id,String customer_id,String remark, String versionFlag) throws ServiceException;
	
	/**
	 * 移除客户转让日志实体
	 * @Title: ICustomerTransferLogService
	 * @Description: 
	 * @param @param id 客户转让日志ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 根据客户id查看流动情况
	 * @param customer_id 客户id
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<CustomerTransferLog> queryByCustomer(String customer_id,
			String versionFlag) throws ServiceException;
}