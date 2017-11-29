package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.TrafficStation;
import com.ticket.service.ITrafficStationService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.PaginationContext;

/**
 * 车站信息业务接口实现类
 * @ClassName: ITrafficStationService   
 * @Description: 提供车站信息操作的增删改查   
 * @author HiSay  
 * @date 2015-11-19 10:07:53
 *
 */
public class TrafficStationServiceImpl extends BaseServiceImpl<TrafficStation, String> implements ITrafficStationService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(TrafficStationServiceImpl.class);
	
	@Override
	public boolean merge(String id, String name,String descript,Double longitude,Double latitude, String versionFlag) throws ServiceException {
		TrafficStation trafficStation = dbDAO.queryById(this.getTableNameFromEntity(TrafficStation.class), id);
		trafficStation.setName(DecoderUtil.UtfDecoder(name));
		trafficStation.setDescript(DecoderUtil.UtfDecoder(descript));
		trafficStation.setLongitude(longitude);
		trafficStation.setLatitude(latitude);
		CommonEntity status = trafficStation.getStatus();
		status.setVersionFlag(versionFlag);
		trafficStation.setStatus(status);
		dbDAO.merge(trafficStation);
		return true;
	}

	@Override
	public boolean persist(String name,String descript,Double longitude,Double latitude, String versionFlag) throws ServiceException {
		TrafficStation trafficStation = new TrafficStation();
		trafficStation.setName(DecoderUtil.UtfDecoder(name));
		trafficStation.setDescript(DecoderUtil.UtfDecoder(descript));
		trafficStation.setLongitude(longitude);
		trafficStation.setLatitude(latitude);
		CommonEntity status = trafficStation.getStatus();
		status.setVersionFlag(versionFlag);
		trafficStation.setStatus(status);
		dbDAO.persist(trafficStation);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		TrafficStation trafficStation = dbDAO.queryById(this.getTableNameFromEntity(TrafficStation.class), id);
		dbDAO.remove(trafficStation);
		return true;
	}

	@Override
	public boolean batchRealDelete(String idsValue, String versionFlag)
			throws ServiceException {
		idsValue = GeneralUtil.convertIdsValueToStringByDelete(idsValue, ",");
		dbDAO.deleteAll(TrafficStation.class.getSimpleName(), idsValue, null);
		return true;
	}

	@Override
	public List<TrafficStation> queryListByLineAndBack(String trafficLine_id, String versionFlag) throws ServiceException {
		List<TrafficStation> list = dbDAO.queryByList(TrafficStation.class.getSimpleName(), deleteFlag, versionFlag, "and s.trafficLine_id =?3 and (s.goOrBack is null or s.goOrBack =?4)", new Object[]{trafficLine_id,0}, orderValueOrderBy, null);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<TrafficStation> queryListByLineAndGo(String trafficLine_id, String versionFlag) throws ServiceException {
		List<TrafficStation> list = dbDAO.queryByList(TrafficStation.class.getSimpleName(), deleteFlag, versionFlag, "and s.trafficLine_id =?3 and (s.goOrBack is null or s.goOrBack =?4)", new Object[]{trafficLine_id,1}, orderByOrderValueAsc, null);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public Pagination queryListByTrafficLineId(String trafficLineId, int pageSize, String versionFlag)
			throws ServiceException {
		return dbDAO.queryByPageModule(TrafficStation.class.getSimpleName(), deleteFlag, versionFlag, "and s.trafficLine_id =?3", new Object[]{trafficLineId}, orderValueOrderBy, PaginationContext.getOffset(), pageSize);
	}

	@Override
	public List<TrafficStation> queryList(String versionFlag) throws ServiceException {
		return dbDAO.queryByList(TrafficStation.class.getSimpleName(), deleteFlag, versionFlag, null, null, orderBy, null);
	}

	@Override
	public List<TrafficStation> queryListByKeyword(String keyword,
			String versionFlag) throws ServiceException {
		keyword = DecoderUtil.UtfDecoder(keyword);
		List<TrafficStation> list = dbDAO.queryByList(TrafficStation.class.getSimpleName(), deleteFlag, versionFlag, " and s.name like ?3", new Object[]{"%"+keyword+"%"}, orderBy, null);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public boolean validateNameExist(String name, String versionFlag)
			throws ServiceException {
		TrafficStation trafficStation = dbDAO.queryByCustom(TrafficStation.class.getSimpleName(), deleteFlag, versionFlag, "and s.name =?3", new Object[]{name});
		if(trafficStation != null){
			return true;
		}
		return false;
	}

	@Override
	public TrafficStation query(String name) throws ServiceException {
		
		TrafficStation trafficStation = dbDAO.queryByCustom(TrafficStation.class.getSimpleName(), deleteFlag, versionFlag, "and s.name =?3", new Object[]{name});
		return trafficStation;
	}

}