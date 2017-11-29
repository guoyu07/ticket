package com.ticket.serviceImpl;


import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.constants.JQueryUploadConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.Advert;
import com.ticket.pojo.AdvertFlight;
import com.ticket.pojo.AdvertType;
import com.ticket.pojo.CommonEntity;
import com.ticket.service.IAdvertFlightService;
import com.ticket.service.IAdvertService;
import com.ticket.service.IAdvertTypeService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

/**
 * 广告信息业务接口实现类
 * @ClassName: IAdvertService   
 * @Description: 提供广告信息操作的增删改查   
 * @author HiSay  
 * @date 2015-10-27 10:31:54
 *
 */
public class AdvertServiceImpl extends BaseServiceImpl<Advert, String> implements IAdvertService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(AdvertServiceImpl.class);
	@Resource private IAdvertTypeService advertTypeService = null;
	@Resource private IAdvertFlightService advertFlightService = null;
	
	@Override
	public boolean merge(String id,String advertType_id, String name,String url, String picture, String content, Integer orderValue,String arriveCitys,String versionFlag) throws ServiceException {
		Advert advert = dbDAO.queryById(this.getTableNameFromEntity(Advert.class), id);
		advert.setAdvertType_id(advertType_id);
		advert.setName(DecoderUtil.UtfDecoder(name));
		advert.setUrl(DecoderUtil.UtfDecoder(url));
		advert.setContent(DecoderUtil.UtfDecoder(content));
		CommonEntity status = advert.getStatus();
		status.setVersionFlag(versionFlag);
		status.setOrderValue(orderValue);
		advert.setStatus(status);
		dbDAO.merge(advert);
		if(GeneralUtil.isNotNull(picture)){
			this.addAnnex(advert, advert.getId(), picture, JQueryUploadConstants.PICTURE_TYPE_DEFAULT, versionFlag);
		}
		List<AdvertFlight> advertFlights = advertFlightService.queryByAdvert(advert);
		if(advertFlights != null){
			for(AdvertFlight advertFlight:advertFlights){
				advertFlightService.remove(advertFlight);
			}
		}
		if(GeneralUtil.isNotNull(arriveCitys)){
			String[] arriveCityArr = arriveCitys.split(",");
			for(String arriveCity:arriveCityArr){
				AdvertFlight advertFlight = advertFlightService.queryByAdvertAndCity(advert, arriveCity);
				if(advertFlight == null){
					advertFlightService.persist(advert, arriveCity, versionFlag);
				}
			}
		}
		return true;
	}

	@Override
	public boolean persist(String advertType_id,String name,String url,String picture, String content,Integer orderValue,String arriveCitys, String versionFlag) throws ServiceException {
		Advert advert = new Advert();
		advert.setAdvertType_id(advertType_id);
		advert.setName(DecoderUtil.UtfDecoder(name));
		advert.setUrl(DecoderUtil.UtfDecoder(url));
		advert.setContent(DecoderUtil.UtfDecoder(content));
		CommonEntity status = advert.getStatus();
		status.setVersionFlag(versionFlag);
		status.setOrderValue(orderValue);
		advert.setStatus(status);
		dbDAO.persist(advert);
		if(GeneralUtil.isNotNull(picture)){
			this.addAnnex(advert, advert.getId(), picture, JQueryUploadConstants.PICTURE_TYPE_DEFAULT, versionFlag);
		}
		if(GeneralUtil.isNotNull(arriveCitys)){
			String[] arriveCityArr = arriveCitys.split(",");
			for(String arriveCity:arriveCityArr){
				AdvertFlight advertFlight = advertFlightService.queryByAdvertAndCity(advert, arriveCity);
				if(advertFlight == null){
					advertFlightService.persist(advert, arriveCity, versionFlag);
				}
			}
		}
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		Advert advert = dbDAO.queryById(this.getTableNameFromEntity(Advert.class), id);
		dbDAO.remove(advert);
		return true;
	}

	@Override
	public boolean batchRealDelete( String idsValue,String versionFlag)
	throws ServiceException {
		idsValue = GeneralUtil.convertIdsValueToStringByDelete(idsValue, ",");
		dbDAO.deleteAll(Advert.class.getSimpleName(), idsValue, null);
		return true;
	}
	
	
	@Override
	public List<Advert> queryList(String versionFlag) throws ServiceException {
		return dbDAO.queryByList(Advert.class.getSimpleName(), deleteFlag, versionFlag, null, null, orderBy, null);
	}

	@Override
	public List<Advert> queryList(String versionFlag, String advertTypeName, Integer size)
			throws ServiceException {
		if(GeneralUtil.isNotNull(advertTypeName)) {
			AdvertType advertType = advertTypeService.queryByName(advertTypeName, versionFlag) ;
			if(advertType != null) {
				return dbDAO.queryByList(Advert.class.getSimpleName(), deleteFlag, versionFlag, "and s.advertType_id=?3", new Object[]{advertType.getId()}, " s.status.orderValue desc", size);
			}
		}
		return null;
	}

	@Override
	public Advert queryByName(String name, String versionFlag)
			throws ServiceException {
		return dbDAO.queryByCustom(Advert.class.getSimpleName() , deleteFlag, versionFlag, "and s.name =?3", new Object[]{name});
	}
	
}