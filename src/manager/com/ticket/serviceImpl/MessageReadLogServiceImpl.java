package com.ticket.serviceImpl;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.Member;
import com.ticket.pojo.MessageReadLog;
import com.ticket.service.IMessageReadLogService;
import com.ticket.util.DecoderUtil;

/**
 * 消息阅读日志业务接口实现类
 * @ClassName: IMessageReadLogService   
 * @Description: 提供消息阅读日志操作的增删改查   
 * @author HiSay  
 * @date 2015-11-30 14:14:30
 *
 */
public class MessageReadLogServiceImpl extends BaseServiceImpl<MessageReadLog, String> implements IMessageReadLogService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(MessageReadLogServiceImpl.class);
	
	@Override
	public boolean merge(String id, String member_id,String message_id, String versionFlag) throws ServiceException {
		MessageReadLog messageReadLog = dbDAO.queryById(this.getTableNameFromEntity(MessageReadLog.class), id);
		messageReadLog.setMember_id(DecoderUtil.UtfDecoder(member_id));
		messageReadLog.setMessage_id(DecoderUtil.UtfDecoder(message_id));
		CommonEntity status = messageReadLog.getStatus();
		status.setVersionFlag(versionFlag);
		messageReadLog.setStatus(status);
		dbDAO.merge(messageReadLog);
		return true;
	}

	@Override
	public boolean persist(String member_id,String message_id, String versionFlag) throws ServiceException {
		MessageReadLog messageReadLog = new MessageReadLog();
		messageReadLog.setMember_id(DecoderUtil.UtfDecoder(member_id));
		messageReadLog.setMessage_id(DecoderUtil.UtfDecoder(message_id));
		CommonEntity status = messageReadLog.getStatus();
		status.setVersionFlag(versionFlag);
		messageReadLog.setStatus(status);
		dbDAO.persist(messageReadLog);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		MessageReadLog messageReadLog = dbDAO.queryById(this.getTableNameFromEntity(MessageReadLog.class), id);
		dbDAO.remove(messageReadLog);
		return true;
	}

	@Override
	public Long queryCountByMember(String versionFlag) {
		Member member = (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		Long count = dbDAO.getTotalCount(MessageReadLog.class.getSimpleName(), deleteFlag, versionFlag, "and s.member_id =?3", new Object[]{member.getId()});
		return count;
	}

}