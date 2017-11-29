package com.ticket.serviceImpl;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.Wifi;
import com.ticket.pojo.WifiArea;
import com.ticket.service.IWifiService;
import com.ticket.util.DecoderUtil;

/**
 * ifi设备业务接口实现类
 * @ClassName: IWifiService   
 * @Description: 提供ifi设备操作的增删改查   
 * @author HiSay  
 * @date 2016-08-09 10:49:51
 *
 */
public class WifiServiceImpl extends BaseServiceImpl<Wifi, String> implements IWifiService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(WifiServiceImpl.class);

	@Override
	public boolean persist(String sid, String area_id,String remark, String versionFlag) throws ServiceException {
		Wifi wifi = new Wifi();
		wifi.setSid(DecoderUtil.UtfDecoder(sid));
		wifi.setRemark(DecoderUtil.UtfDecoder(remark));
		wifi.setArea(get(WifiArea.class, area_id));
		CommonEntity status = wifi.getStatus();
		status.setVersionFlag(versionFlag);
		wifi.setStatus(status);
		dbDAO.persist(wifi);
		return true;
	}
	
	@Override
	public boolean merge(String id, String sid, String area_id,String remark, String versionFlag) throws ServiceException {
		Wifi wifi = dbDAO.queryById(this.getTableNameFromEntity(Wifi.class), id);
		wifi.setSid(DecoderUtil.UtfDecoder(sid));
		wifi.setRemark(DecoderUtil.UtfDecoder(remark));
		wifi.setArea(get(WifiArea.class, area_id));
		CommonEntity status = wifi.getStatus();
		status.setVersionFlag(versionFlag);
		wifi.setStatus(status);
		dbDAO.merge(wifi);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		Wifi wifi = dbDAO.queryById(this.getTableNameFromEntity(Wifi.class), id);
		dbDAO.remove(wifi);
		return true;
	}

}