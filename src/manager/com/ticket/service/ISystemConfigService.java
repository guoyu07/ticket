package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.SystemConfig;


/**
 * 系统配置业务接口
 * @ClassName: ISystemConfigService   
 * @Description: 提供系统配置操作的增删改查   
 * @author HiSay  
 * @date  2014-10-11 08:26:42
 *
 */
public interface ISystemConfigService extends IBaseService<SystemConfig, String> {
	/**
	 * 设置系统配置实体
	 * @Title: ISystemConfigService
	 * @Description:
	 * @param @param name  系统名称
	 * @param @param keyword  系统关键词
	 * @param @param description  系统关键词描述
	 * @param @param isAllowSignIn  是否允许登陆
	 * @param @param isAllowCreate  是否允许注册
	 * @param @return  设置成功则返回true, 设置失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persistOrMerge(SystemConfig config) throws ServiceException;
	/**
	 * 获取系统配置实体
	* @Title: querySystemConfig 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return
	* @param @throws ServiceException    设定文件 
	* @return SystemConfig    返回类型 
	* @throws
	 */
	SystemConfig querySystemConfig() throws ServiceException;
}