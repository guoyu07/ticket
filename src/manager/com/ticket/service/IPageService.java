package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.Page;


/**
 * 搜索页面业务接口
 * @ClassName: IPageService   
 * @Description: 提供搜索页面操作的增删改查   
 * @author HiSay  
 * @date  2016-09-30 15:12:25
 *
 */
public interface IPageService extends IBaseService<Page, String> {
	/**
	 * 保存搜索页面实体
	 * @Title: IPageService
	 * @Description:
	 * @param @param name  页面名称
	 * @param @param url  h5链接地址
	 * @param @param pcUrl  pc端链接地址
	 * @param @param remark  描述
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String name,String url,String pcUrl,String remark, String versionFlag) throws ServiceException;
	
	/**
	 * 修改搜索页面实体
	 * @Title: IPageService
	 * @Description:
	 * @param @param name  页面名称
	 * @param @param url  h5链接地址
	 * @param @param pcUrl  pc端链接地址
	 * @param @param remark  描述
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String name,String url,String pcUrl,String remark, String versionFlag) throws ServiceException;
	
	/**
	 * 移除搜索页面实体
	 * @Title: IPageService
	 * @Description: 
	 * @param @param id 搜索页面ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 查询没在某个搜索里面的页面
	 * @param keywordLocation_id
	 * @return
	 */
	List<Page> queryNotIn(String keywordLocation_id);
}