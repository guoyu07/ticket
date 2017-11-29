package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.SystemPlugin;


/**
 * 系统插件业务接口
 * @ClassName: ISystemPluginService   
 * @Description: 提供系统插件操作的增删改查   
 * @author HiSay  
 * @date  2014-10-15 14:44:02
 *
 */
public interface ISystemPluginService extends IBaseService<SystemPlugin, String> {
	/**
	 * 保存系统插件实体
	 * @Title: ISystemPluginService
	 * @Description:
	 * @param @param name  插件名称
	 * @param @param thumbnail  插件图标
	 * @param @param content  插件描述内容
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String name,String thumbnail,String content, String versionFlag) throws ServiceException;
	
	/**
	 * 修改系统插件实体
	 * @Title: ISystemPluginService
	 * @Description:
	 * @param @param name  插件名称
	 * @param @param thumbnail  插件图标
	 * @param @param content  插件描述内容
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String name,String thumbnail,String content, String versionFlag) throws ServiceException;
	
	/**
	 * 移除系统插件实体
	 * @Title: ISystemPluginService
	 * @Description: 
	 * @param @param id 系统插件ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
}