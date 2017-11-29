package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.DataBaseBackUpsLog;


/**
 * 数据库备份日志业务接口
 * @ClassName: IDataBaseBackUpsLogService   
 * @Description: 提供数据库备份日志操作的增删改查   
 * @author HiSay  
 * @date  2013-09-21 11:47:54
 *
 */
public interface IDataBaseBackUpsLogService extends IBaseService<DataBaseBackUpsLog, String> {
	/**
	 * 保存数据库备份日志实体
	 * @Title: IDataBaseBackUpsLogService
	 * @Description:
	 * @param @param systemUser_id  管理员ID
	 * @param @param sqlPath  备份路径
	 * @param @param name  备份文件名称
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String systemUser_id,String sqlPath,String name, String versionFlag) throws ServiceException;
	
	/**
	 * 修改数据库备份日志实体
	 * @Title: IDataBaseBackUpsLogService
	 * @Description:
	 * @param @param systemUser_id  管理员ID
	 * @param @param sqlPath  备份路径
	 * @param @param name  备份文件名称
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String systemUser_id,String sqlPath,String name, String versionFlag) throws ServiceException;
	
	/**
	 * 移除数据库备份日志实体
	 * @Title: IDataBaseBackUpsLogService
	 * @Description: 
	 * @param @param id 数据库备份日志ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
}