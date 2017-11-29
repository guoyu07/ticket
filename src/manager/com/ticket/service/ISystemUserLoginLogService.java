package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.SystemUserLoginLog;


/**
 * 系统管理员登陆日志业务接口
 * @ClassName: ISystemUserLoginLogService   
 * @Description: 提供系统管理员登陆日志操作的增删改查   
 * @author HiSay  
 * @date  2015-01-03 10:42:16
 *
 */
public interface ISystemUserLoginLogService extends IBaseService<SystemUserLoginLog, String> {
	/**
	 * 保存系统管理员登陆日志实体
	 * @Title: ISystemUserLoginLogService
	 * @Description:
	 * @param @param ip  操作IP地址
	 * @param @param systemUser_id  所属管理员
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String ip,String systemUser_id, String versionFlag) throws ServiceException;
	
	/**
	 * 修改系统管理员登陆日志实体
	 * @Title: ISystemUserLoginLogService
	 * @Description:
	 * @param @param ip  操作IP地址
	 * @param @param systemUser_id  所属管理员
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String ip, String systemUser_id, String versionFlag) throws ServiceException;
	
	/**
	 * 移除系统管理员登陆日志实体
	 * @Title: ISystemUserLoginLogService
	 * @Description: 
	 * @param @param id 系统管理员登陆日志ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 根据系统用户获取最近一次登陆日志
	 * @Title: queryBySystemUser 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param systemUser_id
	 * @param @return
	 * @param @throws ServiceException    设定文件 
	 * @return SystemUserLoginLog    返回类型 
	 * @throws
	 */
	SystemUserLoginLog queryBySystemUser(String systemUser_id) throws ServiceException;
}