package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.RoleInfo;


/**
 * 角色信息业务接口
 * @ClassName: IRoleInfoService   
 * @Description: 提供角色信息操作的增删改查   
 * @author HiSay  
 * @date  2015-10-16 11:18:46
 *
 */
public interface IRoleInfoService extends IBaseService<RoleInfo, String> {
	/**
	 * 保存角色信息实体
	 * @Title: IRoleInfoService
	 * @Description:
	 * @param @param name  角色名称
	 * @param @param descript  角色描述
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String name,String descript, String versionFlag) throws ServiceException;
	
	/**
	 * 修改角色信息实体
	 * @Title: IRoleInfoService
	 * @Description:
	 * @param @param name  角色名称
	 * @param @param descript  角色描述
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String name,String descript, String versionFlag) throws ServiceException;
	
	/**
	 * 移除角色信息实体
	 * @Title: IRoleInfoService
	 * @Description: 
	 * @param @param id 角色信息ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 批量彻底删除角色信息
	 * @param idsValue
	 * @param versionFlag
	 * @return
	 */
	boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException;

	/**
	 * 验证角色名是否已存在
	 * @param name  角色名
	 * @param versionFlag 版本标识
	 * @return
	 */
	boolean validateNameExists(String name, String versionFlag) throws ServiceException;

	/**
	 * 查询角色列表
	 * @param versionFlag
	 * @return
	 */
	List<RoleInfo> queryList(String versionFlag) throws ServiceException;
	/**
	 * 根据角色id查找角色
	 * @param roleId
	 * @return
	 * @throws ServiceException
	 */
	RoleInfo findByRoleId(String roleId) throws ServiceException;
}