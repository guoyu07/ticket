package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.Member;
import com.ticket.pojo.MemberFavorite;
import com.ticket.service.IMemberFavoriteService;
import com.ticket.util.DecoderUtil;

/**
 * 会员收藏业务接口实现类
 * @ClassName: IMemberFavoriteService   
 * @Description: 提供会员收藏操作的增删改查   
 * @author HiSay  
 * @date 2015-11-13 09:59:42
 *
 */
public class MemberFavoriteServiceImpl extends BaseServiceImpl<MemberFavorite, String> implements IMemberFavoriteService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(MemberFavoriteServiceImpl.class);
	
	@Override
	public boolean merge(String id, String member_id,String objectId,String objectType,String Title,String url, String versionFlag) throws ServiceException {
		MemberFavorite memberFavorite = dbDAO.queryById(this.getTableNameFromEntity(MemberFavorite.class), id);
		memberFavorite.setMember_id(DecoderUtil.UtfDecoder(member_id));
		memberFavorite.setObjectId(DecoderUtil.UtfDecoder(objectId));
		memberFavorite.setObjectType(DecoderUtil.UtfDecoder(objectType));
		memberFavorite.setTitle(DecoderUtil.UtfDecoder(Title));
		memberFavorite.setUrl(DecoderUtil.UtfDecoder(url));
		CommonEntity status = memberFavorite.getStatus();
		status.setVersionFlag(versionFlag);
		memberFavorite.setStatus(status);
		dbDAO.merge(memberFavorite);
		return true;
	}

	@Override
	public boolean persist(String member_id,String objectId,String objectType,String Title,String url, String versionFlag) throws ServiceException {
		MemberFavorite memberFavorite = new MemberFavorite();
		memberFavorite.setMember_id(DecoderUtil.UtfDecoder(member_id));
		memberFavorite.setObjectId(DecoderUtil.UtfDecoder(objectId));
		memberFavorite.setObjectType(DecoderUtil.UtfDecoder(objectType));
		memberFavorite.setTitle(DecoderUtil.UtfDecoder(Title));
		memberFavorite.setUrl(DecoderUtil.UtfDecoder(url));
		CommonEntity status = memberFavorite.getStatus();
		status.setVersionFlag(versionFlag);
		memberFavorite.setStatus(status);
		dbDAO.persist(memberFavorite);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		MemberFavorite memberFavorite = dbDAO.queryById(this.getTableNameFromEntity(MemberFavorite.class), id);
		dbDAO.remove(memberFavorite);
		return true;
	}

	@Override
	public MemberFavorite queryByObjectId(String objectId, String versionFlag) throws ServiceException {
		Member member = (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		return dbDAO.queryByCustom(MemberFavorite.class.getSimpleName(), deleteFlag, versionFlag, "and s.objectId =?3 and s.member_id =?4", new Object[]{objectId,member.getId()});
	}

	@Override
	public List<MemberFavorite> queryListByMember(String versionFlag) throws ServiceException {
		Member member = (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		
		List<MemberFavorite> list =dbDAO.queryByList(MemberFavorite.class.getSimpleName(), deleteFlag, versionFlag, "and s.member_id =?3", new Object[]{member.getId()}, orderBy, null);
		return list;
	}

	@Override
	public MemberFavorite queryByTitleAndUrl(String title,String url, String versionFlag)
			throws ServiceException {
		Member member = (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		return dbDAO.queryByCustom(MemberFavorite.class.getSimpleName(), deleteFlag, versionFlag, "and s.member_id =?3 and s.title =?4 and s.url =?5", new Object[]{member.getId(),title,url});
	}

	@Override
	public Long queryCountByMember(String versionFlag) throws ServiceException {
		Member member = (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		Long count = 0l;
		count = dbDAO.getTotalCount(MemberFavorite.class.getSimpleName(), deleteFlag, versionFlag, "and s.member_id =?3", new Object[]{member.getId()});
		return count;
	}

	@Override
	public Long queryFavoriteCount(String member_id, String versionFlag) {
		Long count = dbDAO.getTotalCount(MemberFavorite.class.getSimpleName(), deleteFlag, versionFlag, "and s.member_id =?3", new Object[]{member_id});
		return count;
	}

	@Override
	public boolean isFavorite(String member_id, String objectId, String objectType) {
		
		Long count = dbDAO.getTotalCount(MemberFavorite.class.getSimpleName(), deleteFlag, versionFlag, "and s.objectId =?3 and objectType=?4 and member_id=?5", new Object[]{objectId, objectType, member_id});
		return count == 0 ? false : true;
	}
}