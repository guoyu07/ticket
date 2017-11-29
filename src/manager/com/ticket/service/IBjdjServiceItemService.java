package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjServiceItem;
import com.ticket.pojo.BjdjServicePackage;


/**
 * 便捷登机服务项业务接口
 * @ClassName: IBjdjServiceItemService   
 * @Description: 提供便捷登机服务项操作的增删改查   
 * @author HiSay  
 * @date  2016-06-30 17:48:04
 *
 */
public interface IBjdjServiceItemService extends IBaseService<BjdjServiceItem, String> {
	/**
	 * 保存便捷登机服务项实体
	 * @Title: IBjdjServiceItemService
	 * @Description:
	 * @param @param name  服务项名称
	 * @param @param description  服务项描述
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String name,String description, String versionFlag) throws ServiceException;
	
	/**
	 * 修改便捷登机服务项实体
	 * @Title: IBjdjServiceItemService
	 * @Description:
	 * @param @param name  服务项名称
	 * @param @param description  服务项描述
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String name,String description, String versionFlag) throws ServiceException;
	
	/**
	 * 移除便捷登机服务项实体
	 * @Title: IBjdjServiceItemService
	 * @Description: 
	 * @param @param id 便捷登机服务项ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 通过名称获取套餐
	 * @param name
	 * @return
	 */
	BjdjServiceItem getByName(String name);
	
	/**
	 * 获取一个套餐所有的服务项
	 * @param packageType
	 * @return
	 */
	List<BjdjServiceItem> get(BjdjServicePackage packageType);
}