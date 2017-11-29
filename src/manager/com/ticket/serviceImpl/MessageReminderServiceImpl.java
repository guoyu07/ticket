package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.constants.ContextConstants;
import com.ticket.constants.JQueryUploadConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.Member;
import com.ticket.pojo.MessageReadLog;
import com.ticket.pojo.MessageReminder;
import com.ticket.service.IMessageReminderService;
import com.ticket.util.DateUtil;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

/**
 * 消息提醒业务接口实现类
 * @ClassName: IMessageReminderService   
 * @Description: 提供消息提醒操作的增删改查   
 * @author HiSay  
 * @date 2015-11-17 16:10:53
 *
 */
public class MessageReminderServiceImpl extends BaseServiceImpl<MessageReminder, String> implements IMessageReminderService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(MessageReminderServiceImpl.class);
	
	@Override
	public boolean merge(String id, String member_id,String title,String picture,String content,String reminderTime, String versionFlag) throws ServiceException {
		MessageReminder messageReminder = dbDAO.queryById(this.getTableNameFromEntity(MessageReminder.class), id);
		messageReminder.setMember_id(DecoderUtil.UtfDecoder(member_id));
		messageReminder.setTitle(DecoderUtil.UtfDecoder(title));
		messageReminder.setContent(DecoderUtil.UtfDecoder(content));
		messageReminder.setReminderTime(DateUtil.parseStringToDate(reminderTime, "yyyy-MM-dd HH:mm"));
		CommonEntity status = messageReminder.getStatus();
		status.setVersionFlag(versionFlag);
		messageReminder.setStatus(status);
		dbDAO.merge(messageReminder);
		if(GeneralUtil.isNotNull(picture)){
			this.addAnnex(messageReminder, messageReminder.getId(), picture, JQueryUploadConstants.PICTURE_TYPE_DEFAULT, versionFlag);
		}
		return true;
	}

	@Override
	public boolean persist(String member_id,String title,String picture,String content,String reminderTime, String versionFlag) throws ServiceException {
		MessageReminder messageReminder = new MessageReminder();
		messageReminder.setMember_id(DecoderUtil.UtfDecoder(member_id));
		messageReminder.setTitle(DecoderUtil.UtfDecoder(title));
		messageReminder.setContent(DecoderUtil.UtfDecoder(content));
		messageReminder.setReminderTime(DateUtil.parseStringToDate(reminderTime, "yyyy-MM-dd HH:mm"));
		dbDAO.persist(messageReminder);
		if(GeneralUtil.isNotNull(picture)){
			addAnnex(messageReminder, messageReminder.getId(), picture, JQueryUploadConstants.PICTURE_TYPE_DEFAULT, versionFlag);
		}
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		MessageReminder messageReminder = dbDAO.queryById(this.getTableNameFromEntity(MessageReminder.class), id);
		dbDAO.remove(messageReminder);
		return true;
	}

	@Override
	public boolean batchRealDelete(String idsValue, String versionFlag)
			throws ServiceException {
		idsValue = GeneralUtil.convertIdsValueToStringByDelete(idsValue, ",");
		dbDAO.deleteAll(MessageReminder.class.getSimpleName(), idsValue, null);
		return true;
	}

	@Override
	public List<MessageReminder> queryList(String versionFlag) throws ServiceException {
		String table1 = this.getTableNameFromEntity(MessageReminder.class);
		String table2 = this.getTableNameFromEntity(MessageReadLog.class);
		Member member = (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		String sql = "select s from "+table1+" s where id not in (select r.id from "+table2+" r where r.member.id =?)";
		List<MessageReminder> list = dbDAO.executeJPQLForQueryEntity(sql, member.getId());
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public Long queryCountByNotAudit(String versionFlag) throws ServiceException {
		Member member = (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		Long l = 0l;
		String t1 = this.getTableNameFromEntity(MessageReminder.class);
		String t2 = this.getTableNameFromEntity(MessageReadLog.class);
		String sql = "select count(*) "+"form " +t1+","+t2+" where t1.id = t2.message_id and t2.member_id = "+member.getId()+" and t2.status.audit = 1";
		l = dbDAO.getTotalCount(MessageReminder.class.getSimpleName(), deleteFlag, versionFlag, "and s.audit =0", null);
		return l;
	}

	@Override
	public Long queryCount(String versionFlag) throws ServiceException {
		Long count = dbDAO.getTotalCount(MessageReminder.class.getSimpleName(), deleteFlag, versionFlag, null, null);
		
		return count;
	}

}