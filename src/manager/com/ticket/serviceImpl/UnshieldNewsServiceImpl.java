package com.ticket.serviceImpl;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.News;
import com.ticket.pojo.UnshieldNews;
import com.ticket.pojo.WordsPackage;
import com.ticket.service.IUnshieldNewsService;

/**
 * 不用屏蔽的信息
 * @author：涂有
 * @date 2017年1月19日 下午3:43:40
 */
public class UnshieldNewsServiceImpl extends BaseServiceImpl<WordsPackage, String> implements IUnshieldNewsService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(UnshieldNewsServiceImpl.class);

	@Override
	public UnshieldNews persist(News news) throws ServiceException {
		
		UnshieldNews unshield = query(news); 
		if(unshield != null){
			
			return unshield;
		}
		
		unshield = new UnshieldNews();
		unshield.setNews(news);
		dbDAO.persist(news);
		return unshield;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		WordsPackage wordsPackage = dbDAO.queryById(this.getTableNameFromEntity(WordsPackage.class), id);
		dbDAO.remove(wordsPackage);
		return true;
	}

	public UnshieldNews query(News news){
		
		UnshieldNews unshield = dbDAO.executeJPQLForQuerySingle(
				"select s from " + UnshieldNews.class.getName() + " s where s.news=?", UnshieldNews.class, news);
		return unshield;
	}
}