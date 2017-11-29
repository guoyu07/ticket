package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.MessageTemplate;
import com.ticket.pojo.Wifi;
import com.ticket.pojo.WifiArea;
import com.ticket.pojo.WifiAreaMessage;
import com.ticket.service.IWifiAreaMessageService;

/**
 * ifi消息关联业务接口实现类
 * @ClassName: IWifiMessageService   
 * @Description: 提供ifi消息关联操作的增删改查   
 * @author HiSay  
 * @date 2016-08-09 10:51:02
 *
 */
public class WifiAreaMessageServiceImpl extends BaseServiceImpl<WifiAreaMessage, String> implements IWifiAreaMessageService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(WifiAreaMessageServiceImpl.class);

	@Override
	public boolean persist(WifiArea wifiArea,MessageTemplate message, String versionFlag) throws ServiceException {
		WifiAreaMessage wifiMessage = new WifiAreaMessage();
		wifiMessage.setWifiArea(wifiArea);
		wifiMessage.setMessage(message);
		CommonEntity status = wifiMessage.getStatus();
		status.setVersionFlag(versionFlag);
		wifiMessage.setStatus(status);
		dbDAO.persist(wifiMessage);
		return true;
	}
	
	@Override
	public boolean merge(String id, WifiArea wifiArea,MessageTemplate message, String versionFlag) throws ServiceException {
		WifiAreaMessage wifiMessage = dbDAO.queryById(this.getTableNameFromEntity(WifiAreaMessage.class), id);
		wifiMessage.setWifiArea(wifiArea);
		wifiMessage.setMessage(message);
		CommonEntity status = wifiMessage.getStatus();
		status.setVersionFlag(versionFlag);
		wifiMessage.setStatus(status);
		dbDAO.merge(wifiMessage);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		WifiAreaMessage wifiMessage = dbDAO.queryById(this.getTableNameFromEntity(WifiAreaMessage.class), id);
		dbDAO.remove(wifiMessage);
		return true;
	}

	@Override
	public List<WifiAreaMessage> query(String sid) {
		
		List<WifiAreaMessage> list = dbDAO.executeJPQLForQueryEntity(
				"select s from " + WifiAreaMessage.class.getName() + " s"
						+ " where exists (select w from "+Wifi.class.getName()+" w where w.sid = ? and w.area=s.wifiArea)", sid);
		return list;
	}
}