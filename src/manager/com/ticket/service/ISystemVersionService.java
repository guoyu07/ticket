package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.SystemVersion;


/**
 * 系统版本业务接口
 * @ClassName: ISystemVersionService   
 * @Description: 提供系统版本操作的增删改查   
 * @author HiSay  
 * @date  2014-10-15 14:41:02
 *
 */
public interface ISystemVersionService extends IBaseService<SystemVersion, String> {
	/**
	 * 保存系统版本实体
	 * @Title: ISystemVersionService
	 * @Description:
	 * @param @param version  版本号
	 * @param @param content  版本升级内容
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String version,String content, String versionFlag) throws ServiceException;
	
	/**
	 * 修改系统版本实体
	 * @Title: ISystemVersionService
	 * @Description:
	 * @param @param version  版本号
	 * @param @param content  版本升级内容
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String version,String content, String versionFlag) throws ServiceException;
	
	/**
	 * 移除系统版本实体
	 * @Title: ISystemVersionService
	 * @Description: 
	 * @param @param id 系统版本ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
}