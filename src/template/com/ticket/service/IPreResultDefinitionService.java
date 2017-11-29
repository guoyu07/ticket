package com.ticket.service;

import java.util.Map;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonSearch;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.PreResultDefinition;


/**
 * 预定义搜索结果业务接口
 * @ClassName: IPreResultDefinitionService   
 * @Description: 提供预定义搜索结果操作的增删改查   
 * @author HiSay  
 * @date  2015-12-14 18:57:30
 *
 */
public interface IPreResultDefinitionService extends IBaseService<PreResultDefinition, String> {
	
	/**
	 * 初始化
	 */
	void init();
	
	/**
	 * 更新seo内存
	 * @param preResultDefinition
	 */
	void updateSeo(Map<String, CommonSearch> keywords, PreResultDefinition preResultDefinition);
	
	/**
	 * 保存预定义搜索结果实体
	 * @Title: IPreResultDefinitionService
	 * @Description:
	 * @param @param pageName  页面名称
	 * @param @param keyword  系统关键词
	 * @param @param negative  否定词
	 * @param @param _negative  精确否定词
	 * @param @param description  系统关键词描述
	 * @param @param url  页面链接
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(Integer type,String pageName,String keyword,String negative,String _negative,String description,String url,String pcUrl,String versionFlag) throws ServiceException;
	
	/**
	 * 修改预定义搜索结果实体
	 * @Title: IPreResultDefinitionService
	 * @Description:
	 * @param @param pageName  页面名称
	 * @param @param keyword  系统关键词
	 * @param @param negative  否定词
	 * @param @param _negative  精确否定词
	 * @param @param description  系统关键词描述
	 * @param @param url  页面链接
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String pageName,String keyword,String negative,String _negative,String description,String url,String pcUrl,String versionFlag, Integer orderValue) throws ServiceException;
	
	/**
	 * 移除预定义搜索结果实体
	 * @Title: IPreResultDefinitionService
	 * @Description: 
	 * @param @param id 预定义搜索结果ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	/**
	 * 后台查看预定义搜索结果
	 * @param versionFlag
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryByAdmin(String versionFlag, Integer pageSize) throws ServiceException;
	/**
	 * 后台搜索预定义搜索结果
	 * @param versionFlag
	 * @param pageSize
	 * @param keyword
	 * @return
	 * @throws ServiceException
	 */
	Pagination searchByAdmin(String versionFlag, Integer pageSize, String keyword) throws ServiceException;
	
	/**
	 *  根据页面名称查找预定义搜索
	 * @param pageName
	 * @return
	 * @throws ServiceException
	 */
	PreResultDefinition queryByPagename(String pageName) throws ServiceException;
}