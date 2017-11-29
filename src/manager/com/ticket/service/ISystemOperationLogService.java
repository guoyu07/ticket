package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.SystemOperationLog;


/**
 * 后台管理员操作日志业务接口
 * @ClassName: ISystemOperationLogService   
 * @Description: 提供后台管理员操作日志操作的增删改查   
 * @author HiSay  
 * @date  2016-03-08 21:01:21
 *
 */
public interface ISystemOperationLogService extends IBaseService<SystemOperationLog, String> {
	
	/**
	 * 保存后台管理员操作日志实体
	 * @Title: ISystemOperationLogService
	 * @Description:
	 * @param logContent  操作内容
	 * @throws ServiceException   
	 */
	boolean persist(String logContent) throws ServiceException;
	
	/**
	 * 保存后台管理员操作日志实体
	 * @Title: ISystemOperationLogService
	 * @Description:
	 * @param @param logName  管理员名称
	 * @param @param logContent  操作内容
	 * @param @param logTime  操作时间
	 * @param @param logIp  操作IP
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String logName,String logContent,String logTime,String logIp, String versionFlag) throws ServiceException;
	
	/**
	 * 修改后台管理员操作日志实体
	 * @Title: ISystemOperationLogService
	 * @Description:
	 * @param @param logName  管理员名称
	 * @param @param logContent  操作内容
	 * @param @param logTime  操作时间
	 * @param @param logIp  操作IP
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String logName,String logContent,String logTime,String logIp, String versionFlag) throws ServiceException;
	
	/**
	 * 移除后台管理员操作日志实体
	 * @Title: ISystemOperationLogService
	 * @Description: 
	 * @param @param id 后台管理员操作日志ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
}