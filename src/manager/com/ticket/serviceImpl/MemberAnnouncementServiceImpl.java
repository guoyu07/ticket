package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.MemberAnnouncement;
import com.ticket.pojo.News;
import com.ticket.service.IMemberAnnouncementService;
import com.ticket.util.DecoderUtil;

/**
 * 通知中心信息阅读业务接口实现类
 * @ClassName: IMemberAnnouncementService   
 * @Description: 提供通知中心信息阅读操作的增删改查   
 * @author HiSay  
 * @date 2016-09-22 16:00:51
 *
 */
public class MemberAnnouncementServiceImpl extends BaseServiceImpl<MemberAnnouncement, String> implements IMemberAnnouncementService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(MemberAnnouncementServiceImpl.class);

	@Override
	public boolean persist(String member_id,News news, String versionFlag) throws ServiceException {
		MemberAnnouncement memberAnnouncement = new MemberAnnouncement();
		memberAnnouncement.setMember_id(DecoderUtil.UtfDecoder(member_id));
		memberAnnouncement.setNews(news);
		CommonEntity status = memberAnnouncement.getStatus();
		status.setVersionFlag(versionFlag);
		memberAnnouncement.setStatus(status);
		dbDAO.persist(memberAnnouncement);
		return true;
	}
	
	@Override
	public boolean merge(String id, String member_id,News news, String versionFlag) throws ServiceException {
		MemberAnnouncement memberAnnouncement = dbDAO.queryById(this.getTableNameFromEntity(MemberAnnouncement.class), id);
		memberAnnouncement.setMember_id(DecoderUtil.UtfDecoder(member_id));
		memberAnnouncement.setNews(news);
		CommonEntity status = memberAnnouncement.getStatus();
		status.setVersionFlag(versionFlag);
		memberAnnouncement.setStatus(status);
		dbDAO.merge(memberAnnouncement);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		MemberAnnouncement memberAnnouncement = dbDAO.queryById(this.getTableNameFromEntity(MemberAnnouncement.class), id);
		dbDAO.remove(memberAnnouncement);
		return true;
	}

	@Override
	public List<MemberAnnouncement> queryByMember(String member_id) throws ServiceException {
		List<MemberAnnouncement> announcements = dbDAO.executeJPQLForQuery("select c from " + MemberAnnouncement.class.getName() + " c where c.member_id = ?",MemberAnnouncement.class ,member_id);
		return announcements;
	}

	@Override
	public MemberAnnouncement queryByNewsAndMember(String news_id,
			String member_id) throws ServiceException {
		MemberAnnouncement announcement = dbDAO.executeJPQLForQuerySingle("select c from " + MemberAnnouncement.class.getName() + " c where c.member_id = ? and c.news.id = ?", MemberAnnouncement.class , member_id,news_id);
		return announcement;
	}

}