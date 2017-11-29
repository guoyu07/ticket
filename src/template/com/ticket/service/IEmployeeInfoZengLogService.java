package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.EmployeeInfoZengLog;


/**
 * 员工转移服务码主表业务接口
 * @ClassName: IEmployeeInfoZengLogService   
 * @Description: 提供员工转移服务码主表操作的增删改查   
 * @author HiSay  
 * @date  2016-01-18 17:18:08
 *
 */
public interface IEmployeeInfoZengLogService extends IBaseService<EmployeeInfoZengLog, String> {
	/**
	 * 保存员工转移服务码主表实体
	 * @Title: IEmployeeInfoZengLogService
	 * @Description:
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist( String versionFlag) throws ServiceException;
	
	/**
	 * 修改员工转移服务码主表实体
	 * @Title: IEmployeeInfoZengLogService
	 * @Description:
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id,  String versionFlag) throws ServiceException;
	
	/**
	 * 移除员工转移服务码主表实体
	 * @Title: IEmployeeInfoZengLogService
	 * @Description: 
	 * @param @param id 员工转移服务码主表ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * @author wangjiafeng
	 * 添加赠送记录
	 * @method add
	 * @param employeeInfo
	 * @param count
	 * @param channelCustomerInfoLoginId
	 * @return
	 * @throws ServiceException
	 * @return String
	 * @date 2016-1-19 上午09:23:52
	 */
	String add(EmployeeInfo employeeInfo,Integer count,String channelCustomerInfoLoginId,String  remark) throws ServiceException;
	
	/**
	 * 分销时新增一条赠送记录
	 * @param channelCustomerInfo_id
	 * @param name
	 * @param mobile
	 * @param orderDetail 要分销的服务码数组
	 * @return
	 */
	EmployeeInfoZengLog addForDistribution(String channelCustomerInfo_id, String name, String mobile, String[] orderDetail) throws ServiceException;
}