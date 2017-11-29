package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.SystemFreebackInfo;


/**
 * 系统反馈信息业务接口
 * @ClassName: ISystemFreebackInfoService   
 * @Description: 提供系统反馈信息操作的增删改查   
 * @author HiSay  
 * @date  2016-07-28 13:50:41
 *
 */
public interface ISystemFreebackInfoService extends IBaseService<SystemFreebackInfo, String> {
	/**
	 * 保存系统反馈信息实体
	 * @Title: ISystemFreebackInfoService
	 * @Description:
	 * @param @param name  姓名或账号
	 * @param @param phone  手机号
	 * @param @param url  反馈网址
	 * @param @param content  反馈信息
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String name,String phone,String url,String content, String versionFlag) throws ServiceException;
	
	/**
	 * 修改系统反馈信息实体
	 * @Title: ISystemFreebackInfoService
	 * @Description:
	 * @param @param name  姓名或账号
	 * @param @param phone  手机号
	 * @param @param url  反馈网址
	 * @param @param content  反馈信息
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String name,String phone,String url,String content, String versionFlag) throws ServiceException;
	
	/**
	 * 移除系统反馈信息实体
	 * @Title: ISystemFreebackInfoService
	 * @Description: 
	 * @param @param id 系统反馈信息ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
}