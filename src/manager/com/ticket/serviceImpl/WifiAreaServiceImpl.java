package com.ticket.serviceImpl;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.WifiArea;
import com.ticket.service.IWifiAreaService;
import com.ticket.util.DecoderUtil;

/**
 * ifi区域业务接口实现类
 * @ClassName: IWifiAreaService   
 * @Description: 提供ifi区域操作的增删改查   
 * @author HiSay  
 * @date 2016-09-22 10:17:00
 *
 */
public class WifiAreaServiceImpl extends BaseServiceImpl<WifiArea, String> implements IWifiAreaService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(WifiAreaServiceImpl.class);

	@Override
	public boolean persist(String name,int interval,String remark, String versionFlag) throws ServiceException {
		WifiArea wifiArea = new WifiArea();
		wifiArea.setName(DecoderUtil.UtfDecoder(name));
		wifiArea.setInterv(interval);
		wifiArea.setRemark(DecoderUtil.UtfDecoder(remark));
		CommonEntity status = wifiArea.getStatus();
		status.setVersionFlag(versionFlag);
		wifiArea.setStatus(status);
		dbDAO.persist(wifiArea);
		return true;
	}
	
	@Override
	public boolean merge(String id, String name,int interval,String remark, String versionFlag) throws ServiceException {
		WifiArea wifiArea = dbDAO.queryById(this.getTableNameFromEntity(WifiArea.class), id);
		wifiArea.setName(DecoderUtil.UtfDecoder(name));
		wifiArea.setInterv(interval);
		wifiArea.setRemark(DecoderUtil.UtfDecoder(remark));
		CommonEntity status = wifiArea.getStatus();
		status.setVersionFlag(versionFlag);
		wifiArea.setStatus(status);
		dbDAO.merge(wifiArea);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		WifiArea wifiArea = dbDAO.queryById(this.getTableNameFromEntity(WifiArea.class), id);
		dbDAO.remove(wifiArea);
		return true;
	}

}