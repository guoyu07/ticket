package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.DataBaseRestoreLog;


/**
 * 数据库还原日志业务接口
 * @ClassName: IDataBaseRestoreLogService   
 * @Description: 提供数据库还原日志操作的增删改查   
 * @author HiSay  
 * @date  2013-09-21 11:55:48
 *
 */
public interface IDataBaseRestoreLogService extends IBaseService<DataBaseRestoreLog, String> {
	/**
	 * 保存数据库还原日志实体
	 * @Title: IDataBaseRestoreLogService
	 * @Description:
	 * @param @param adminUser_id  管理员ID
	 * @param @param content  还原数据库说明
	 * @param @param name  还原名称
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String systemUser_id,String content,String name, String versionFlag) throws ServiceException;
	
	/**
	 * 修改数据库还原日志实体
	 * @Title: IDataBaseRestoreLogService
	 * @Description:
	 * @param @param adminUser_id  管理员ID
	 * @param @param content  还原数据库说明
	 * @param @param name  还原名称
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String systemUser_id,String content,String name, String versionFlag) throws ServiceException;
	
	/**
	 * 移除数据库还原日志实体
	 * @Title: IDataBaseRestoreLogService
	 * @Description: 
	 * @param @param id 数据库还原日志ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
}