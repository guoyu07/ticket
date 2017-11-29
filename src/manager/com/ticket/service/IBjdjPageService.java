package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjPage;


/**
 * 便捷登机跳转页面业务接口
 * @ClassName: IBjdjPageService   
 * @Description: 提供便捷登机跳转页面操作的增删改查   
 * @author HiSay  
 * @date  2016-08-08 16:09:07
 *
 */
public interface IBjdjPageService extends IBaseService<BjdjPage, String> {
	/**
	 * 保存便捷登机跳转页面实体
	 * @Title: IBjdjPageService
	 * @Description:
	 * @param @param name  页面名称
	 * @param @param url  页面跳转链接
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String name,String url,String servicePhone,String infoPositionAlias,String serviceFlow_id,String serviceProfile_id,String useSerms_id,String paymentSuccess_id,String advertType_id,String smsTemplate_id,String activeSuccess_id, String versionFlag) throws ServiceException;
	
	/**
	 * 修改便捷登机跳转页面实体
	 * @Title: IBjdjPageService
	 * @Description:
	 * @param @param name  页面名称
	 * @param @param url  页面跳转链接
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String name,String url,String servicePhone,String infoPositionAlias,String serviceFlow_id,String serviceProfile_id,String useSerms_id,String paymentSuccess_id, String advertType_id,String smsTemplate_id,String activeSuccess_id,String versionFlag) throws ServiceException;
	
	/**
	 * 移除便捷登机跳转页面实体
	 * @Title: IBjdjPageService
	 * @Description: 
	 * @param @param id 便捷登机跳转页面ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 根据跳转链接查找便捷登机跳转页面
	 * @param url
	 * @return
	 * @throws ServiceException
	 */
	BjdjPage queryByUrl(String url) throws ServiceException;
}