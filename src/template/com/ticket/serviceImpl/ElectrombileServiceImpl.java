package com.ticket.serviceImpl;


import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.Electromobile;
import com.ticket.service.IElectrombileService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 电瓶车信息业务接口实现类
 * @ClassName: IElectrombileService   
 * @Description: 提供电瓶车信息操作的增删改查   
 * @author HiSay  
 * @date 2015-10-15 10:55:04
 *
 */
public class ElectrombileServiceImpl extends BaseServiceImpl<Electromobile, String> implements IElectrombileService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(ElectrombileServiceImpl.class);
	
	@Override
	public boolean merge(String id, String electrombileId,Integer ifUsing,Double longitude,Double latitude, String versionFlag) throws ServiceException {
		Electromobile electrombile = dbDAO.queryById(this.getTableNameFromEntity(Electromobile.class), id);
		electrombile.setElectrombileId(DecoderUtil.UtfDecoder(electrombileId));
		electrombile.setIfUsing(ifUsing);
		electrombile.setLongitude(longitude);
		electrombile.setLatitude(latitude);
		CommonEntity status = electrombile.getStatus();
		status.setVersionFlag(versionFlag);
		electrombile.setStatus(status);
		dbDAO.merge(electrombile);
		return true;
	}

	@Override
	public boolean persist(String electrombileId,Integer ifUsing,Double longitude,Double latitude, String versionFlag) throws ServiceException {
		Electromobile electrombile = new Electromobile();
		electrombile.setElectrombileId(DecoderUtil.UtfDecoder(electrombileId));
		electrombile.setIfUsing(ifUsing);
		electrombile.setLongitude(longitude);
		electrombile.setLatitude(latitude);
		CommonEntity status = electrombile.getStatus();
		status.setVersionFlag(versionFlag);
		electrombile.setStatus(status);
		dbDAO.persist(electrombile);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		Electromobile electrombile = dbDAO.queryById(this.getTableNameFromEntity(Electromobile.class), id);
		dbDAO.remove(electrombile);
		return true;
	}

	@Override
	public boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException {
		idsValue = GeneralUtil.convertIdsValueToStringByDelete(idsValue, ",");
		dbDAO.deleteAll(Electromobile.class.getSimpleName(), idsValue, null);
		return true;
	}

}