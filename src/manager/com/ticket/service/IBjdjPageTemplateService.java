package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjPageTemplate;


/**
 * jdj支付激活页面模板业务接口
 * @ClassName: IBjdjPageTemplateService   
 * @Description: 提供jdj支付激活页面模板操作的增删改查   
 * @author HiSay  
 * @date  2016-08-18 15:26:40
 *
 */
public interface IBjdjPageTemplateService extends IBaseService<BjdjPageTemplate, String> {
	/**
	 * 保存jdj支付激活页面模板实体
	 * @Title: IBjdjPageTemplateService
	 * @Description:
	 * @param @param name  模板名称
	 * @param @param content  页面内容
	 * @param @param buttonName  按钮名称
	 * @param @param buttonUrl  按钮链接
	 * @param @param buttonType  按钮类型
	 * @param @param buttonClass  按钮样式
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String name,String content,String buttonName,String buttonUrl,String buttonType,String buttonClass, String versionFlag) throws ServiceException;
	
	/**
	 * 修改jdj支付激活页面模板实体
	 * @Title: IBjdjPageTemplateService
	 * @Description:
	 * @param @param name  模板名称
	 * @param @param content  页面内容
	 * @param @param buttonName  按钮名称
	 * @param @param buttonUrl  按钮链接
	 * @param @param buttonType  按钮类型
	 * @param @param buttonClass  按钮样式
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String name,String content,String buttonName,String buttonUrl,String buttonType,String buttonClass, String versionFlag) throws ServiceException;
	
	/**
	 * 移除jdj支付激活页面模板实体
	 * @Title: IBjdjPageTemplateService
	 * @Description: 
	 * @param @param id jdj支付激活页面模板ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
}