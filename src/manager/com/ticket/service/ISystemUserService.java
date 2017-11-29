package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.SystemUser;


/**
 * 系统管理员业务接口
 * @ClassName: ISystemUserService   
 * @Description: 提供系统管理员操作的增删改查   
 * @author HiSay  
 * @date  2014-10-14 09:35:50
 *
 */
public interface ISystemUserService extends IBaseService<SystemUser, String> {
	/**
	 * 保存系统管理员实体
	 * @Title: ISystemUserService
	 * @Description:
	 * @param @param name  管理员呢称
	 * @param @param sex  管理员性别
	 * @param @param loginId  登陆名称
	 * @param @param password  登陆密码
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String name,Integer sex,String loginId,String password, String phone, String versionFlag) throws ServiceException;
	
	/**
	 * 新增系统管理员实体
	 * @Title: ISystemUserService
	 * @Description:
	 * @param @param name  管理员呢称
	 * @param @param sex  管理员性别
	 * @param @param loginId  登陆名称
	 * @param @param password  登陆密码
	 * @param @return  保存成功则返回id 失败返回null
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
//	String add(String name,Integer sex,String loginId,String password, String phone,String employee_id, String versionFlag) throws ServiceException;
	
	/**
	 * 修改系统管理员实体
	 * @Title: ISystemUserService
	 * @Description:
	 * @param @param name  管理员呢称
	 * @param @param sex  管理员性别
	 * @param @param loginId  登陆名称
	 * @param @param password  登陆密码
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String name,Integer sex,String loginId,String password, String phone, String versionFlag) throws ServiceException;
	
	/**
	 * 移除系统管理员实体
	 * @Title: ISystemUserService
	 * @Description: 
	 * @param @param id 系统管理员ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	/**
	 * 验证登录名是否已存在
	* @Title: validateLoginIsExists 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param loginId
	* @param @return
	* @param @throws ServiceException    设定文件 
	* @return boolean    返回类型 
	* @throws
	 */
	boolean validateLoginIsExists(String loginId, String id) throws ServiceException;
	/**
	 * 修改系统管理员密码
	 * @Title: updatePassword 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param password
	 * @param @return
	 * @param @throws ServiceException    设定文件 
	 * @return boolean    返回类型 
	 * @throws
	 */
	boolean updatePassword(String systemUser_id, String password) throws ServiceException;
	
	/**
	 * 根据登录名和密码获取管理员实体
	* @Title: queryByLoginIdAndPassword 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param loginId
	* @param @param password
	* @param @return
	* @param @throws ServiceException    设定文件 
	* @return SystemUser    返回类型 
	* @throws
	 */
	SystemUser queryByLoginIdAndPassword(String loginId, String password) throws ServiceException;

	/**
	 * 批量彻底删除用户信息
	 * @param idsValue
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException;
	
	/**
	 * 重启项目
	 */
	String restartProject() throws ServiceException;
}