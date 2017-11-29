package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.EmployeeSysUser;


/**
 * 员工系统用户关系业务接口
 * @ClassName: IEmployeeSysUserService   
 * @Description: 提供员工系统用户关系操作的增删改查   
 * @author HiSay  
 * @date  2015-11-18 16:09:00
 *
 */
public interface IEmployeeSysUserService extends IBaseService<EmployeeSysUser, String> {
	/**
	 * 保存员工系统用户关系实体
	 * @Title: IEmployeeSysUserService
	 * @Description:
	 * @param @param employee_id  员工id
	 * @param @param sysUser_id  用户id
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String employee_id,String sysUser_id, String versionFlag) throws ServiceException;
	
	/**
	 * 修改员工系统用户关系实体
	 * @Title: IEmployeeSysUserService
	 * @Description:
	 * @param @param employee_id  员工id
	 * @param @param sysUser_id  用户id
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String employee_id,String sysUser_id, String versionFlag) throws ServiceException;
	
	/**
	 * 移除员工系统用户关系实体
	 * @Title: IEmployeeSysUserService
	 * @Description: 
	 * @param @param id 员工系统用户关系ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
}