package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.PrivilegeInfo;
import com.ticket.pojo.RolePrivilege;
import com.ticket.pojo.SystemModule;


/**
 * 角色权限业务接口
 * @ClassName: IRolePrivilegeService   
 * @Description: 提供角色权限操作的增删改查   
 * @author HiSay  
 * @date  2015-10-17 11:28:35
 *
 */
public interface IRolePrivilegeService extends IBaseService<RolePrivilege, String> {
	/**
	 * 保存角色权限实体
	 * @Title: IRolePrivilegeService
	 * @Description:
	 * @param @param roleId  角色id
	 * @param @param systemModuleId  模块id
	 * @param @param privilegeId  权限id
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String roleId,String systemModuleId,String privilegeId, String versionFlag) throws ServiceException;
	
	/**
	 * 修改角色权限实体
	 * @Title: IRolePrivilegeService
	 * @Description:
	 * @param @param roleId  角色id
	 * @param @param systemModuleId  模块id
	 * @param @param privilegeId  权限id
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String roleId,String systemModuleId,String privilegeId, String versionFlag) throws ServiceException;
	
	/**
	 * 移除角色权限实体
	 * @Title: IRolePrivilegeService
	 * @Description: 
	 * @param @param id 角色权限ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 根据角色id/模块id/权限id查询中间表
	 * @param roleId  角色id
	 * @param moduleId 模块id
	 * @param privilegeId 权限id
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	RolePrivilege queryByRIDAndMIDAndPID(String roleId,String moduleId,String privilegeId,String versionFlag) throws ServiceException;

	/**
	 * 给当前角色赋所有权限
	 * @param roleId
	 * @param privilegeList 
	 * @param moduleList 
	 * @param versionFlag
	 * @throws ServiceException
	 */
	void selectAll(String roleId, List<SystemModule> moduleList, List<PrivilegeInfo> privilegeList, String versionFlag) throws ServiceException;

	/**
	 * 取消对当前角色的所有授权
	 * @param roleId
	 * @param versionFlag
	 * @throws ServiceException
	 */
	void selectNone(String roleId, String versionFlag) throws ServiceException;
	/**
	 * 根据角色id查找所有的角色权限
	 * @param roleId
	 * @return
	 * @throws ServiceException
	 */
	List<RolePrivilege> queryByRoleId(String roleId) throws ServiceException;
}