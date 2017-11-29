package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjServiceItem;
import com.ticket.pojo.BjdjServicePackage;
import com.ticket.pojo.BjdjServicePackageItem;


/**
 * 便捷登机服务套餐关联项业务接口
 * @ClassName: IBjdjServicePackageItemService   
 * @Description: 提供便捷登机服务套餐关联项操作的增删改查   
 * @author HiSay  
 * @date  2016-06-30 17:49:00
 *
 */
public interface IBjdjServicePackageItemService extends IBaseService<BjdjServicePackageItem, String> {
	/**
	 * 保存便捷登机服务套餐关联项实体
	 * @Title: IBjdjServicePackageItemService
	 * @Description:
	 * @param @param package  服务套餐名称
	 * @param @param item  服务项
	 * @param @param orderValue  服务顺序
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(BjdjServicePackage packageName,BjdjServiceItem item, Integer orderValue, String versionFlag) throws ServiceException;
	
	/**
	 * 修改便捷登机服务套餐关联项实体
	 * @Title: IBjdjServicePackageItemService
	 * @Description:
	 * @param @param package  服务套餐名称
	 * @param @param item  服务项
	 * @param @param orderValue  服务顺序
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, BjdjServicePackage packageName,BjdjServiceItem item, Integer orderValue, String versionFlag) throws ServiceException;
	
	/**
	 * 移除便捷登机服务套餐关联项实体
	 * @Title: IBjdjServicePackageItemService
	 * @Description: 
	 * @param @param id 便捷登机服务套餐关联项ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 通过套餐和服务项，查看是否存在对应的关联
	 * @param package1
	 * @param item
	 * @return
	 */
	BjdjServicePackageItem query(BjdjServicePackage package1, BjdjServiceItem item);
	
	/**
	 * 通过名称获取套餐
	 * @param name
	 * @return
	 */
	BjdjServicePackageItem getByName(String name);
}