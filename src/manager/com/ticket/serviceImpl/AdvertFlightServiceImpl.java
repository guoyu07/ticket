package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.Advert;
import com.ticket.pojo.AdvertFlight;
import com.ticket.pojo.CommonEntity;
import com.ticket.service.IAdvertFlightService;
import com.ticket.util.DecoderUtil;

/**
 * 航班详情对应广告业务接口实现类
 * @ClassName: IAdvertFlightService   
 * @Description: 提供航班详情对应广告操作的增删改查   
 * @author HiSay  
 * @date 2016-09-28 11:33:27
 *
 */
public class AdvertFlightServiceImpl extends BaseServiceImpl<AdvertFlight, String> implements IAdvertFlightService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(AdvertFlightServiceImpl.class);

	@Override
	public boolean persist(Advert advert,String arriveCity, String versionFlag) throws ServiceException {
		AdvertFlight advertFlight = new AdvertFlight();
		advertFlight.setAdvert(advert);
		advertFlight.setArriveCity(DecoderUtil.UtfDecoder(arriveCity));
		CommonEntity status = advertFlight.getStatus();
		status.setVersionFlag(versionFlag);
		advertFlight.setStatus(status);
		dbDAO.persist(advertFlight);
		return true;
	}
	
	@Override
	public boolean merge(String id, Advert advert,String arriveCity, String versionFlag) throws ServiceException {
		AdvertFlight advertFlight = dbDAO.queryById(this.getTableNameFromEntity(AdvertFlight.class), id);
		advertFlight.setAdvert(advert);
		advertFlight.setArriveCity(DecoderUtil.UtfDecoder(arriveCity));
		CommonEntity status = advertFlight.getStatus();
		status.setVersionFlag(versionFlag);
		advertFlight.setStatus(status);
		dbDAO.merge(advertFlight);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		AdvertFlight advertFlight = dbDAO.queryById(this.getTableNameFromEntity(AdvertFlight.class), id);
		dbDAO.remove(advertFlight);
		return true;
	}

	@Override
	public AdvertFlight queryByAdvertAndCity(Advert advert, String city)
			throws ServiceException {
		AdvertFlight advertFlight = dbDAO.executeJPQLForQuerySingle("select c from " + AdvertFlight.class.getName() + " c where c.advert = ? and c.arriveCity = ?", AdvertFlight.class , advert,city);
		return advertFlight;
	}

	@Override
	public List<AdvertFlight> queryByAdvert(Advert advert)
			throws ServiceException {
		List<AdvertFlight> advertFlights = dbDAO.executeJPQLForQuery("select c from " + AdvertFlight.class.getName() + " c where c.advert = ?", AdvertFlight.class , advert);
		return advertFlights;
	}

	@Override
	public List<AdvertFlight> queryByCity(String city) throws ServiceException {
		List<AdvertFlight> advertFlights = dbDAO.executeJPQLForQuery("select c from " + AdvertFlight.class.getName() + " c where c.arriveCity = ?", AdvertFlight.class , city);
		return advertFlights;
	}

}