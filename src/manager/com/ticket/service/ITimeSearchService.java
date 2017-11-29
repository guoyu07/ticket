package com.ticket.service;

import java.util.Date;
import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonSearch;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.TimeSearch;


/**
 * 搜索统计业务接口
 * @ClassName: ITimeSearchService   
 * @Description: 提供搜索统计操作的增删改查   
 * @author HiSay  
 * @date  2016-08-12 11:26:22
 *
 */
public interface ITimeSearchService extends IBaseService<TimeSearch, String> {
	/**
	 * 保存搜索统计实体
	 * @Title: ITimeSearchService
	 * @Description:
	 * @param @param goUrl  落地页链接
	 * @param @param showRate  展现量
	 * @param @param clickRate  点击量
	 * @param @param showKeyword  触发展现的搜索词
	 * @param @param clickKeyword  触发点击的搜索词
	 * @param @param preResultDefinitionId  对应的预定义搜索结果id
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String goUrl,Integer showRate,Integer clickRate,String showKeyword,String clickKeyword,String preResultDefinitionId, String versionFlag) throws ServiceException;
	
	/**
	 * 修改搜索统计实体
	 * @Title: ITimeSearchService
	 * @Description:
	 * @param @param goUrl  落地页链接
	 * @param @param showRate  展现量
	 * @param @param clickRate  点击量
	 * @param @param showKeyword  触发展现的搜索词
	 * @param @param clickKeyword  触发点击的搜索词
	 * @param @param preResultDefinitionId  对应的预定义搜索结果id
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String goUrl,Integer showRate,Integer clickRate,String showKeyword,String clickKeyword,String preResultDefinitionId, String versionFlag) throws ServiceException;
	
	/**
	 * 移除搜索统计实体
	 * @Title: ITimeSearchService
	 * @Description: 
	 * @param @param id 搜索统计ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 保存搜索统计实体
	 * @param commonSearchs
	 * @return
	 * @throws ServiceException
	 */
	boolean persist(String showKeyword,List<CommonSearch> commonSearchs) throws ServiceException;
	
	/**
	 * 查看某个页面在今天是否已经被触发过
	 * @param preResultDefinitionId
	 * @return
	 * @throws ServiceException
	 */
	boolean isExsit(String preResultDefinitionId) throws ServiceException;
	
	/**
	 * 根据页面ID查找搜索统计
	 * @param preResultDefinitionId
	 * @return
	 * @throws ServiceException
	 */
	TimeSearch queryByDefinitionId(String preResultDefinitionId) throws ServiceException;
	
	/**
	 * 查看某天的所有的没有结果的搜索词
	 * @param date
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryAllNoDate(Date date, String versionFlag, Integer pageSize) throws ServiceException;
	
	/**
	 * 查看某天的所有的有结果的搜索词
	 * @param date
	 * @param versionFlag
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryEntityByAdmin(Date date, String versionFlag, Integer pageSize) throws ServiceException;
	
	/**
	 * 根据日期查找
	 * @param date
	 * @return
	 * @throws ServiceException
	 */
	List<TimeSearch> queryByDate(Date date) throws ServiceException;
	
	/**
	 * 创建下载xls
	 * @param date
	 * @return
	 * @throws ServiceException
	 */
	String createJxls(Date date) throws ServiceException;
}