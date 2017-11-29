package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.SystemUpdateLog;


/**
 * 系统代码升级日志业务接口
 * @ClassName: ISystemUpdateLogService   
 * @Description: 提供系统代码升级日志操作的增删改查   
 * @author HiSay  
 * @date  2013-09-21 09:16:57
 *
 */
public interface ISystemUpdateLogService extends IBaseService<SystemUpdateLog, Long> {
	/**
	 * 保存系统代码升级日志实体
	 * @Title: ISystemUpdateLogService
	 * @Description:
	 * @param @param oldVersion  老版本名称
	 * @param @param newVersion  新版本名称
	 * @param @param updateContent  升级内容
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String oldVersion,String newVersion,String updateContent, String versionFlag) throws ServiceException;
	
	/**
	 * 修改系统代码升级日志实体
	 * @Title: ISystemUpdateLogService
	 * @Description:
	 * @param @param oldVersion  老版本名称
	 * @param @param newVersion  新版本名称
	 * @param @param updateContent  升级内容
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(Long id, String oldVersion,String newVersion,String updateContent, String versionFlag) throws ServiceException;
	
	/**
	 * 移除系统代码升级日志实体
	 * @Title: ISystemUpdateLogService
	 * @Description: 
	 * @param @param id 系统代码升级日志ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(Long id) throws ServiceException;
}