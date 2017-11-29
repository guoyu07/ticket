package com.ticket.serviceImpl;


import java.util.Date;
import java.util.List;

import com.ticket.constants.JQueryUploadConstants;
import com.ticket.enumer.FreezeStatus;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.BusinessEvent;
import com.ticket.service.IBusinessEventService;
import com.ticket.util.DateUtil;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 商家活动业务接口实现类
 * @ClassName: IBusinessEventService   
 * @Description: 提供商家活动操作的增删改查   
 * @author HiSay  
 * @date 2016-12-14 10:20:43
 *
 */
public class BusinessEventServiceImpl extends BaseServiceImpl<BusinessEvent, String> implements IBusinessEventService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(BusinessEventServiceImpl.class);

	@Override
	public boolean persist(String name,String content, String pic,Date startTime, Date endTime,String versionFlag) throws ServiceException {
		BusinessEvent businessEvent = new BusinessEvent();
		businessEvent.setName(DecoderUtil.UtfDecoder(name));
		businessEvent.setContent(DecoderUtil.UtfDecoder(content));
		businessEvent.setStartTime(DateUtil.getDayStart(startTime));
		businessEvent.setEndTime(DateUtil.getDayEnd(endTime));
		CommonEntity status = businessEvent.getStatus();
		status.setVersionFlag(versionFlag);
		businessEvent.setStatus(status);
		dbDAO.persist(businessEvent);
		
		if(GeneralUtil.isNotNull(pic)){
			this.addAnnex(businessEvent, businessEvent.getId(), pic, JQueryUploadConstants.PICTURE_TYPE_DEFAULT, versionFlag);
		}
		return true;
	}
	
	@Override
	public boolean merge(String id, String name,String content,String pic,Date startTime, Date endTime, String versionFlag) throws ServiceException {
		BusinessEvent businessEvent = dbDAO.queryById(this.getTableNameFromEntity(BusinessEvent.class), id);
		businessEvent.setName(DecoderUtil.UtfDecoder(name));
		businessEvent.setContent(DecoderUtil.UtfDecoder(content));
		businessEvent.setStartTime(DateUtil.getDayStart(startTime));
		businessEvent.setEndTime(DateUtil.getDayEnd(endTime));
		CommonEntity status = businessEvent.getStatus();
		status.setVersionFlag(versionFlag);
		businessEvent.setStatus(status);
		dbDAO.merge(businessEvent);
		
		if(GeneralUtil.isNotNull(pic)){
			this.addAnnex(businessEvent, businessEvent.getId(), pic, JQueryUploadConstants.PICTURE_TYPE_DEFAULT, versionFlag);
		}
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		BusinessEvent businessEvent = dbDAO.queryById(this.getTableNameFromEntity(BusinessEvent.class), id);
		dbDAO.remove(businessEvent);
		return true;
	}

	@Override
	public boolean freeze(String id) {
		BusinessEvent businessEvent = dbDAO.queryById(this.getTableNameFromEntity(BusinessEvent.class), id);
		businessEvent.setFreezeStatus(FreezeStatus.freeze);
		dbDAO.merge(businessEvent);
		return true;
	}

	@Override
	public boolean actived(String id) {
		BusinessEvent businessEvent = dbDAO.queryById(this.getTableNameFromEntity(BusinessEvent.class), id);
		businessEvent.setFreezeStatus(FreezeStatus.actived);
		dbDAO.merge(businessEvent);
		return true;
	}

	@Override
	public List<BusinessEvent> canBindList() {
		
		return dbDAO.executeJPQLForQuery("select c from " + BusinessEvent.class.getName() + " c where c.freezeStatus != ? and c.endTime > ?" , BusinessEvent.class, FreezeStatus.freeze, new Date());
	}

}