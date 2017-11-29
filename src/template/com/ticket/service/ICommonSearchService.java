package com.ticket.service;

import java.util.List;

import com.ticket.enumer.SearchType;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonSearch;

/**
 * 整站搜索业务接口
 * @ClassName: IPreResultDefinitionService   
 * @Description: 提供预定义搜索结果操作的增删改查   
 * @author HiSay  
 * @date  2015-12-14 18:57:30
 *
 */
public interface ICommonSearchService extends IBaseService<CommonSearch, String> {
	
	/**
	 * 发送
	 */
	void sendFlushNotify();
	
	/**
	 * 根据关键词搜索模块
	 * @param keyword
	 * @return
	 * @throws ServiceException
	 */
	List<CommonSearch> searchListByKeyword(SearchType type, String keyword, Integer startSize, Integer getCount, String ip) throws ServiceException;
	
	/**
	 * 根据关键词搜索模块
	 * @param keyword
	 * @return
	 * @throws ServiceException
	 */
	List<CommonSearch> searchListByKeyword2(SearchType type, String keyword, Integer startSize, Integer getCount, String ip) throws ServiceException;
	
	/**
	 * 查找搜索词是否存在
	 * @param seo
	 * @param keyword
	 * @param like
	 * @return
	 */
	String in(String seo, String keyword, boolean like);
	
	/**
	 * 获取id
	 * @return
	 * @throws ServiceException
	 */
	String getCountSearchId() throws ServiceException;
	
	/**
	 * 去除commonSearchs中和keyWordLocations有相同url的对象
	 * @param commonSearchs
	 * @param keyWordLocations
	 */
	List<CommonSearch> removeSameUrl(List<CommonSearch> commonSearchs, List<CommonSearch> keyWordLocations);
}