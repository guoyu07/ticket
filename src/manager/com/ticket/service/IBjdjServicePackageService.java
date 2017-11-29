package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjServicePackage;


/**
 * 便捷登机服务套餐业务接口
 * @ClassName: IBjdjServicePackageService   
 * @Description: 提供便捷登机服务套餐操作的增删改查   
 * @author HiSay  
 * @date  2016-06-30 17:48:33
 *
 */
public interface IBjdjServicePackageService extends IBaseService<BjdjServicePackage, String> {
	/**
	 * 保存便捷登机服务套餐实体
	 * @Title: IBjdjServicePackageService
	 * @Description:
	 * @param @param name  服务套餐名称
	 * @param @param price  服务套餐价格
	 * @param @param description  服务项描述
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String name,double price,String description,String bjdjHall_id,String page_id, String versionFlag) throws ServiceException;
	
	/**
	 * 修改便捷登机服务套餐实体
	 * @Title: IBjdjServicePackageService
	 * @Description:
	 * @param @param name  服务套餐名称
	 * @param @param price  服务套餐价格
	 * @param @param description  服务项描述
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String name,double price,String description,String bjdjHall_id, String page_id,String versionFlag) throws ServiceException;
	
	/**
	 * 移除便捷登机服务套餐实体
	 * @Title: IBjdjServicePackageService
	 * @Description: 
	 * @param @param id 便捷登机服务套餐ID
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
	BjdjServicePackage getByName(String name);
	
	/**
	 * 得到最小价格的服务套餐
	 * @return
	 */
	BjdjServicePackage getMinPrice();
	
	/**
	 * 根据前端页面id查找对应的套餐
	 * @param bjdjPage_id
	 * @return
	 * @throws ServiceException
	 */
	List<BjdjServicePackage> queryByBjdjPage(String bjdjPage_id) throws ServiceException;
	
	/**
	 * 获取同一个便捷登机页面上的最小价格的套餐
	 * @param bjdjPage_id
	 * @return
	 * @throws ServiceException
	 */
	BjdjServicePackage getMinPriceByBjdjPage(String bjdjPage_id) throws ServiceException;
	
	/**
	 * 获取默认显示的最小价格的套餐
	 * @param url
	 * @return
	 * @throws ServiceException
	 */
	BjdjServicePackage getMinPriceByBjdjUrl(String url) throws ServiceException;
	
	/**
	 * 获取前台默认显示的所有套餐
	 * @param url
	 * @return
	 * @throws ServiceException
	 */
	List<BjdjServicePackage> queryByBjdjUrl(String url) throws ServiceException;
}