package com.ticket.service;

import java.util.List;

import com.ticket.enumer.SearchType;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.CountSearch;
import com.ticket.pojo.Member;


/**
 * 统计搜索词表业务接口
 * @ClassName: ICountSearchService   
 * @Description: 提供统计搜索词表操作的增删改查   
 * @author HiSay  
 * @date  2016-03-10 15:49:15
 *
 */
public interface ICountSearchService extends IBaseService<CountSearch, String> {
	
	/**
	 * 保存统计搜索词表实体
	 * @Title: ICountSearchService
	 * @Description:
	 * @param @param searchWord  搜索词
	 * @param @param time  搜索时间
	 * @param @param keyword  触发的关键词
	 * @param @param negative  触发的否定词
	 * @param @param goUrl  落地页
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	CountSearch persist(String searchWord,String keyword,String negative, String ip, Member member, SearchType type) throws ServiceException;
	
	/**
	 * 移除统计搜索词表实体
	 * @Title: ICountSearchService
	 * @Description: 
	 * @param @param id 统计搜索词表ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	/**
	 * 修改统计搜索词表实体
	 * @param id
	 * @param goUrl
	 * @param goUrlHref
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	boolean merge(String id,String goUrl,String goUrlHref, String versionFlag) throws ServiceException;
	/**
	 * 批量删除统计搜索词表实体
	 * @param idsValue
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException;
	
	/**
	 * 根据搜索时间排序所有搜索词
	 * @return
	 * @throws ServiceException
	 */
	List<CountSearch> queryAllOrderByCreateTime() throws ServiceException;
}