package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.News;
import com.ticket.pojo.UnshieldNews;
import com.ticket.pojo.WordsPackage;


/**
 * 不用屏蔽的信息
 * @author：涂有
 * @date 2017年1月19日 下午3:44:05
 */
public interface IUnshieldNewsService extends IBaseService<WordsPackage, String> {
	
	/**
	 * 
	 * @author：涂有
	 * @date 2017年1月19日 下午3:48:57
	 * @param news
	 * @return
	 * @throws ServiceException
	 */
	UnshieldNews persist(News news) throws ServiceException;
	
	/**
	 * 
	 * @author：涂有
	 * @date 2017年1月19日 下午3:49:03
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 查询某篇文章的是否已经屏蔽了
	 * @author：涂有
	 * @date 2017年1月19日 下午3:46:27
	 * @param news
	 * @return
	 */
	UnshieldNews query(News news);
}