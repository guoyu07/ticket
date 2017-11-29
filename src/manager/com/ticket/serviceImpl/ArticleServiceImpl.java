package com.ticket.serviceImpl;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.Article;
import com.ticket.pojo.CommonEntity;
import com.ticket.service.IArticleService;
import com.ticket.util.DecoderUtil;

/**
 * 文章信息业务接口实现类
 * @ClassName: IArticleService   
 * @Description: 提供文章信息操作的增删改查   
 * @author HiSay  
 * @date 2015-10-13 10:05:09
 *
 */
public class ArticleServiceImpl extends BaseServiceImpl<Article, String> implements IArticleService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(ArticleServiceImpl.class);
	
	@Override
	public boolean merge(String id, String newsClass_id,String title,String content,String introduce,
			String thumbnail,String source,String author, String versionFlag, String viewPageRedirectTemplate_id) throws ServiceException {
		Article article = dbDAO.queryById(this.getTableNameFromEntity(Article.class), id);
		article.setNewsClass_id(DecoderUtil.UtfDecoder(newsClass_id));
		article.setTitle(DecoderUtil.UtfDecoder(title));
		article.setContent(DecoderUtil.UtfDecoder(content));
		article.setIntroduce(DecoderUtil.UtfDecoder(introduce));
		article.setThumbnail(DecoderUtil.UtfDecoder(thumbnail));
		article.setSource(DecoderUtil.UtfDecoder(source));
		article.setAuthor(DecoderUtil.UtfDecoder(author));
		article.setViewPageRedirectTemplate_id(viewPageRedirectTemplate_id) ;
		CommonEntity status = article.getStatus();
		status.setVersionFlag(versionFlag);
		article.setStatus(status);
		dbDAO.merge(article);
		return true;
	}

	@Override
	public boolean persist(String newsClass_id,String title,String content,String introduce,String thumbnail,
			String source,String author, String versionFlag, String viewPageRedirectTemplate_id) throws ServiceException {
		Article article = new Article();
		article.setNewsClass_id(DecoderUtil.UtfDecoder(newsClass_id));
		article.setTitle(DecoderUtil.UtfDecoder(title));
		article.setContent(DecoderUtil.UtfDecoder(content));
		article.setIntroduce(DecoderUtil.UtfDecoder(introduce));
		article.setThumbnail(DecoderUtil.UtfDecoder(thumbnail));
		article.setSource(DecoderUtil.UtfDecoder(source));
		article.setAuthor(DecoderUtil.UtfDecoder(author));
		article.setViewPageRedirectTemplate_id(viewPageRedirectTemplate_id) ;
		CommonEntity status = article.getStatus();
		status.setVersionFlag(versionFlag);
		article.setStatus(status);
		dbDAO.persist(article);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		Article article = dbDAO.queryById(this.getTableNameFromEntity(Article.class), id);
		dbDAO.remove(article);
		return true;
	}

	@Override
	public Article queryByUrl(String visitUrl, boolean addHits)
			throws ServiceException {
		Article article = dbDAO.queryByCustom(this.getTableNameFromEntity(Article.class), deleteFlag, versionFlag, "and s.status.visitUrl=?3", new Object[]{visitUrl}) ;
		if(article != null && addHits) {
			CommonEntity status = article.getStatus();
			status.setHits(status.getHits() + 1);
			article.setStatus(status);
			dbDAO.merge(article);
		}
		return article;
	}

}