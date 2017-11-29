package com.ticket.serviceImpl;


import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.constants.JQueryUploadConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.MassInfomation;
import com.ticket.service.IMassInfomationService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

/**
 * 群发信息业务接口实现类
 * @ClassName: IMassInfomationService   
 * @Description: 提供群发信息操作的增删改查   
 * @author HiSay  
 * @date 2016-02-03 20:46:58
 *
 */
public class MassInfomationServiceImpl extends BaseServiceImpl<MassInfomation, String> implements IMassInfomationService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(MassInfomationServiceImpl.class);
	
	@Override
	public boolean merge(String id, String title,String content,String url,String massObjCharacter, Date reminderTime, String path
			, boolean sendSms, String flightNumber, Date flightDate) throws ServiceException {
		
		MassInfomation massInfomation = dbDAO.queryById(this.getTableNameFromEntity(MassInfomation.class), id);
		massInfomation.setTitle(DecoderUtil.UtfDecoder(title));
		massInfomation.setContent(DecoderUtil.UtfDecoder(content));
		massInfomation.setUrl(DecoderUtil.UtfDecoder(url));
//		massInfomation.setMassObjCharacter(DecoderUtil.UtfDecoder(massObjCharacter));
		massInfomation.setSendSms(sendSms);
		massInfomation.setReminderTime(reminderTime);
		massInfomation.setFlightNumber(flightNumber);
		massInfomation.setFlightDate(flightDate);
		dbDAO.merge(massInfomation);
		if(GeneralUtil.isNotNull(path)){
			this.addAnnex(massInfomation, massInfomation.getId(), path, JQueryUploadConstants.PICTURE_TYPE_DEFAULT, versionFlag);
		}
		return true;
	}

	@Override
	public boolean persist(String title,String content,String url,String massObjCharacter, Date reminderTime, String path, boolean sendSms,
			String flightNumber, Date flightDate, int type) throws ServiceException {
		
		MassInfomation massInfomation = new MassInfomation();
		massInfomation.setTitle(DecoderUtil.UtfDecoder(title));
		massInfomation.setContent(DecoderUtil.UtfDecoder(content));
		massInfomation.setUrl(DecoderUtil.UtfDecoder(url));
		massInfomation.setMassObjCharacter(massObjCharacter);
		massInfomation.setReminderTime(reminderTime);
		massInfomation.setSendSms(sendSms);
		massInfomation.setFlightNumber(flightNumber);
		massInfomation.setFlightDate(flightDate);
		massInfomation.setType(type);
		dbDAO.persist(massInfomation);
		if(GeneralUtil.isNotNull(path)){
			this.addAnnex(massInfomation, massInfomation.getId(), path, JQueryUploadConstants.PICTURE_TYPE_DEFAULT, versionFlag);
		}
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		MassInfomation massInfomation = dbDAO.queryById(this.getTableNameFromEntity(MassInfomation.class), id);
		dbDAO.remove(massInfomation);
		return true;
	}
}