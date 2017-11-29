package com.ticket.serviceImpl;


import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.MessageTemplate;
import com.ticket.pojo.WifiAreaMessage;
import com.ticket.service.IMessageTemplateService;
import com.ticket.service.IWifiAreaMessageService;
import com.ticket.util.DecoderUtil;

/**
 * 消息模板业务接口实现类
 * @ClassName: IMessageTemplateService   
 * @Description: 提供消息模板操作的增删改查   
 * @author HiSay  
 * @date 2016-08-09 10:50:43
 *
 */
public class MessageTemplateServiceImpl extends BaseServiceImpl<MessageTemplate, String> implements IMessageTemplateService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(MessageTemplateServiceImpl.class);
	@Resource
	private IWifiAreaMessageService wifiMessageService;

	@Override
	public boolean persist(String title, String url, String content,String remark, String versionFlag) throws ServiceException {
		MessageTemplate messageTemplate = new MessageTemplate();
		messageTemplate.setTitle(title);
		messageTemplate.setUrl(url);
		messageTemplate.setContent(DecoderUtil.UtfDecoder(content));
		messageTemplate.setRemark(DecoderUtil.UtfDecoder(remark));
		CommonEntity status = messageTemplate.getStatus();
		status.setVersionFlag(versionFlag);
		messageTemplate.setStatus(status);
		dbDAO.persist(messageTemplate);
		return true;
	}
	
	@Override
	public boolean merge(String id, String title, String url, String content,String remark, String versionFlag) throws ServiceException {
		MessageTemplate messageTemplate = dbDAO.queryById(this.getTableNameFromEntity(MessageTemplate.class), id);
		messageTemplate.setTitle(title);
		messageTemplate.setUrl(url);
		messageTemplate.setContent(DecoderUtil.UtfDecoder(content));
		messageTemplate.setRemark(DecoderUtil.UtfDecoder(remark));
		CommonEntity status = messageTemplate.getStatus();
		status.setVersionFlag(versionFlag);
		messageTemplate.setStatus(status);
		dbDAO.merge(messageTemplate);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		MessageTemplate messageTemplate = dbDAO.queryById(this.getTableNameFromEntity(MessageTemplate.class), id);
		dbDAO.remove(messageTemplate);
		return true;
	}

	@Override
	public List<MessageTemplate> query(String sid) {
		
		List<MessageTemplate> list = dbDAO.executeJPQLForQueryEntity(
				"select s.message from " + WifiAreaMessage.class.getName() + " s where s.wifi.sid = ?", sid);
		return list;
	}

}