package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.PrivilegeInfo;


/**
 * 权限信息业务接口
 * @ClassName: IPrivilegeInfoService   
 * @Description: 提供权限信息操作的增删改查   
 * @author HiSay  
 * @date  2015-10-16 11:37:11
 *
 */
public interface IPrivilegeInfoService extends IBaseService<PrivilegeInfo, String> {
	/**
	 * 保存权限信息实体
	 * @Title: IPrivilegeInfoService
	 * @Description:
	 * @param @param name  权限名称
	 * @param @param descript  权限描述
	 * @param @param includeMethods  包含的方法
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String name,String descript,String includeMethods,String systemModulId, String versionFlag) throws ServiceException;
	
	/**
	 * 修改权限信息实体
	 * @Title: IPrivilegeInfoService
	 * @Description:
	 * @param @param name  权限名称
	 * @param @param descript  权限描述
	 * @param @param includeMethods  包含的方法
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String name,String descript,String includeMethods,String systemModulId, String versionFlag) throws ServiceException;
	
	/**
	 * 移除权限信息实体
	 * @Title: IPrivilegeInfoService
	 * @Description: 
	 * @param @param id 权限信息ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 批量删除权限信息
	 * @param idsValue
	 * @param versionFlag
	 * @return
	 */
	boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException;

	/**
	 * 验证权限名称是否已经存在
	 * @param name 权限名称
	 * @param versionFlag
	 * @return
	 */
	boolean validateNameExists(String name, String versionFlag) throws ServiceException;

	/**
	 * 查询权限列表
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<PrivilegeInfo> queryList(String versionFlag) throws ServiceException;
	/**
	 * 根据模块id查询这个模块的所有权限设置
	 * @param systemModulId
	 * @return
	 * @throws ServiceException
	 */
	List<PrivilegeInfo> queryBuSystemModulId(String systemModulId) throws ServiceException;
}