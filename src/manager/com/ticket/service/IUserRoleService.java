package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.UserRole;


/**
 * 用户角色业务接口
 * @ClassName: IUserRoleService   
 * @Description: 提供用户角色操作的增删改查   
 * @author HiSay  
 * @date  2015-10-17 09:41:22
 *
 */
public interface IUserRoleService extends IBaseService<UserRole, String> {
	/**
	 * 保存用户角色实体
	 * @Title: IUserRoleService
	 * @Description:
	 * @param @param userId  用户id
	 * @param @param roleId  角色id
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String userId,String roleId, String versionFlag) throws ServiceException;
	
	/**
	 * 修改用户角色实体
	 * @Title: IUserRoleService
	 * @Description:
	 * @param @param userId  用户id
	 * @param @param roleId  角色id
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String userId,String roleId, String versionFlag) throws ServiceException;
	
	/**
	 * 移除用户角色实体
	 * @Title: IUserRoleService
	 * @Description: 
	 * @param @param id 用户角色ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 根据用户Id和角色Id查询用户角色关系
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	UserRole queryByUIdAndRID(String userId,String roleId,String versionFlag) throws ServiceException;
	/**
	 * 根据用户id查找用户角色关系
	 * @param employee_id
	 * @return
	 * @throws ServiceException
	 */
	List<UserRole> findByUserId(String employee_id) throws ServiceException;
}