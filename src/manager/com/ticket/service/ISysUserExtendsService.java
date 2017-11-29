package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.SysUserExtends;


/**
 * 用户扩展表业务接口
 * @ClassName: ISysUserExtendsService   
 * @Description: 提供用户扩展表操作的增删改查   
 * @author HiSay  
 * @date  2015-10-23 15:14:44
 *
 */
public interface ISysUserExtendsService extends IBaseService<SysUserExtends, String> {
	/**
	 * 保存用户扩展表实体
	 * @Title: ISysUserExtendsService
	 * @Description:
	 * @param @param user_id  用户ID
	 * @param @param name  字段名称
	 * @param @param type  字段类型（字典）
	 * @param @param value  字段值
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String user_id,String name,String type,String value, String versionFlag) throws ServiceException;
	
	/**
	 * 修改用户扩展表实体
	 * @Title: ISysUserExtendsService
	 * @Description:
	 * @param @param user_id  用户ID
	 * @param @param name  字段名称
	 * @param @param type  字段类型（字典）
	 * @param @param value  字段值
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String user_id,String name,String type,String value, String versionFlag) throws ServiceException;
	
	/**
	 * 移除用户扩展表实体
	 * @Title: ISysUserExtendsService
	 * @Description: 
	 * @param @param id 用户扩展表ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
}