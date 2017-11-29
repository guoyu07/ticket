package com.ticket.serviceImpl;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.NewsComment;
import com.ticket.service.INewsCommentService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

/**
 * 新闻评论业务接口实现类
 * @ClassName: INewsCommentService   
 * @Description: 提供新闻评论操作的增删改查   
 * @author HiSay  
 * @date 2015-10-13 17:16:37
 *
 */
public class NewsCommentServiceImpl extends BaseServiceImpl<NewsComment, String> implements INewsCommentService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(NewsCommentServiceImpl.class);
	
	@Override
	public boolean merge(String id, String news_id,String member_id,String memberIp,Integer star,String content, String versionFlag) throws ServiceException {
		NewsComment newsComment = dbDAO.queryById(this.getTableNameFromEntity(NewsComment.class), id);
		newsComment.setNews_id(DecoderUtil.UtfDecoder(news_id));
		newsComment.setMember_id(DecoderUtil.UtfDecoder(member_id));
		newsComment.setMemberIp(DecoderUtil.UtfDecoder(memberIp));
		newsComment.setStar(star);
		newsComment.setContent(DecoderUtil.UtfDecoder(content));
		CommonEntity status = newsComment.getStatus();
		status.setVersionFlag(versionFlag);
		newsComment.setStatus(status);
		dbDAO.merge(newsComment);
		return true;
	}

	@Override
	public boolean persist(String news_id,String member_id,String memberIp,Integer star,String content, String versionFlag) throws ServiceException {
		NewsComment newsComment = new NewsComment();
		newsComment.setNews_id(DecoderUtil.UtfDecoder(news_id));
		newsComment.setMember_id(DecoderUtil.UtfDecoder(member_id));
		newsComment.setMemberIp(DecoderUtil.UtfDecoder(memberIp));
		newsComment.setStar(star);
		newsComment.setContent(DecoderUtil.UtfDecoder(content));
		CommonEntity status = newsComment.getStatus();
		status.setVersionFlag(versionFlag);
		newsComment.setStatus(status);
		dbDAO.persist(newsComment);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		NewsComment newsComment = dbDAO.queryById(this.getTableNameFromEntity(NewsComment.class), id);
		dbDAO.remove(newsComment);
		return true;
	}

	@Override
	public boolean batchRealDelete( String idsValue,String versionFlag)
			throws ServiceException {
		idsValue = GeneralUtil.convertIdsValueToStringByDelete(idsValue, ",");
		dbDAO.deleteAll(NewsComment.class.getSimpleName(), idsValue, null);
		return true;
	}
}