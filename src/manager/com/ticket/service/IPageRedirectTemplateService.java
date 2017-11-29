package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.PageRedirectTemplate;


/**
 * 页面跳转模板业务接口
 * @ClassName: IPageRedirectTemplateService   
 * @Description: 提供页面跳转模板操作的增删改查   
 * @author HiSay  
 * @date  2015-10-22 14:00:50
 *
 */
public interface IPageRedirectTemplateService extends IBaseService<PageRedirectTemplate, String> {
	/**
	 * 保存页面跳转模板实体
	 * @Title: IPageRedirectTemplateService
	 * @Description:
	 * @param @param name  页面名称
	 * @param @param toPage  跳转到哪个JSP
	 * @param @param type  模板类型
	 * @param @param isSinglePage  是否单篇文章
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String name,String toPage,Integer type,Integer isSinglePage, String versionFlag, String directory, String toPageAjax) throws ServiceException;
	
	/**
	 * 修改页面跳转模板实体
	 * @Title: IPageRedirectTemplateService
	 * @Description:
	 * @param @param name  页面名称
	 * @param @param toPage  跳转到哪个JSP
	 * @param @param type  模板类型
	 * @param @param isSinglePage  是否单篇文章
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String name,String toPage,Integer type,Integer isSinglePage, String versionFlag, String directory, String toPageAjax) throws ServiceException;
	
	/**
	 * 移除页面跳转模板实体
	 * @Title: IPageRedirectTemplateService
	 * @Description: 
	 * @param @param id 页面跳转模板ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	/**
	 * 获取页面跳转模板实体列表
	 * @param versionFlag
	 * @param type
	 * @return
	 * @throws ServiceException
	 */
	List<PageRedirectTemplate> queryList(String versionFlag, Integer type) throws ServiceException;
	/**
	 * 根据ID获取页面名称
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	String queryPageNameById(String id) throws ServiceException;
	/**
	 * 根据ID获取页面目录
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	String queryPageDirectoryById(String id) throws ServiceException;
	/**
	 * 根据模板名称获取跳转对象
	 * @param name
	 * @return
	 * @throws ServiceException
	 */
	PageRedirectTemplate queryByName(String name) throws ServiceException;
}