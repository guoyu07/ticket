package com.ticket.serviceImpl;


import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.BusinessEvent;
import com.ticket.pojo.BusinessInfo;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.BusinessEventBind;
import com.ticket.service.IBusinessEventBindService;
import com.ticket.util.DecoderUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 商家活动绑定业务接口实现类
 * @ClassName: IBusinessEventBindService   
 * @Description: 提供商家活动绑定操作的增删改查   
 * @author HiSay  
 * @date 2016-12-15 10:18:13
 *
 */
public class BusinessEventBindServiceImpl extends BaseServiceImpl<BusinessEventBind, String> implements IBusinessEventBindService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(BusinessEventBindServiceImpl.class);

	@Override
	public boolean persist(BusinessInfo businessInfo,BusinessEvent businessEvent, String versionFlag) throws ServiceException {
		BusinessEventBind businessEventBind = new BusinessEventBind();
		businessEventBind.setBusinessInfo(businessInfo);
		businessEventBind.setBusinessEvent(businessEvent);
		CommonEntity status = businessEventBind.getStatus();
		status.setVersionFlag(versionFlag);
		businessEventBind.setStatus(status);
		dbDAO.persist(businessEventBind);
		return true;
	}
	
	@Override
	public boolean merge(String id, BusinessInfo businessInfo,BusinessEvent businessEvent, String versionFlag) throws ServiceException {
		BusinessEventBind businessEventBind = dbDAO.queryById(this.getTableNameFromEntity(BusinessEventBind.class), id);
		businessEventBind.setBusinessInfo(businessInfo);
		businessEventBind.setBusinessEvent(businessEvent);
		CommonEntity status = businessEventBind.getStatus();
		status.setVersionFlag(versionFlag);
		businessEventBind.setStatus(status);
		dbDAO.merge(businessEventBind);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		BusinessEventBind businessEventBind = dbDAO.queryById(this.getTableNameFromEntity(BusinessEventBind.class), id);
		dbDAO.remove(businessEventBind);
		return true;
	}

	@Override
	public List<BusinessEventBind> queryByEvent(String event_id) {
		
		return dbDAO.executeJPQLForQuery("select c from " + BusinessEventBind.class.getName() + " c where c.businessEvent.id = ?", BusinessEventBind.class, event_id);
	}

	@Override
	public BusinessEventBind queryByBusinessIdAndEventId(String business_id,
			String event_id) {
		return dbDAO.executeJPQLForQuerySingle("select c from " + BusinessEventBind.class.getName() + " c where c.businessEvent.id = ? and c.businessInfo.id = ?", BusinessEventBind.class, event_id, business_id);
	}

}