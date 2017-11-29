package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.SystemUserOperation;


/**
 * 管理员操作日志业务接口
 * @ClassName: ISystemUserOperationService   
 * @Description: 提供管理员操作日志操作的增删改查   
 * @author HiSay  
 * @date  2014-10-15 13:12:06
 *
 */
public interface ISystemUserOperationService extends IBaseService<SystemUserOperation, String> {
	/**
	 * 保存管理员操作日志实体
	 * @Title: ISystemUserOperationService
	 * @Description:
	 * @param @param content  操作内容
	 * @param @param ip  操作IP地址
	 * @param @param systemUser_id  所属管理员
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String content,String ip,String systemUser_id, String versionFlag) throws ServiceException;
	
	/**
	 * 修改管理员操作日志实体
	 * @Title: ISystemUserOperationService
	 * @Description:
	 * @param @param content  操作内容
	 * @param @param ip  操作IP地址
	 * @param @param systemUser_id  所属管理员
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String content,String ip,String systemUser_id, String versionFlag) throws ServiceException;
	
	/**
	 * 移除管理员操作日志实体
	 * @Title: ISystemUserOperationService
	 * @Description: 
	 * @param @param id 管理员操作日志ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
}