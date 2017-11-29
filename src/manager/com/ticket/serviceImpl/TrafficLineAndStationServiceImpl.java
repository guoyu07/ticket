package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.TrafficLineAndStation;
import com.ticket.service.ITrafficLineAndStationService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.PaginationContext;

/**
 * 路线与车站关联业务接口实现类
 * @ClassName: ITrafficLineAndStationService   
 * @Description: 提供路线与车站关联操作的增删改查   
 * @author HiSay  
 * @date 2015-12-20 16:30:33
 *
 */
public class TrafficLineAndStationServiceImpl extends BaseServiceImpl<TrafficLineAndStation, String> implements ITrafficLineAndStationService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(TrafficLineAndStationServiceImpl.class);
	
	@Override
	public boolean merge(String id,String trafficType_id, String trafficLine_id,String trafficStation_id,Integer stationType,Integer orderValue, String versionFlag) throws ServiceException {
		TrafficLineAndStation trafficLineAndStation = dbDAO.queryById(this.getTableNameFromEntity(TrafficLineAndStation.class), id);
		trafficLineAndStation.setTrafficType_id(DecoderUtil.UtfDecoder(trafficType_id));
		trafficLineAndStation.setTrafficLine_id(DecoderUtil.UtfDecoder(trafficLine_id));
		trafficLineAndStation.setTrafficStation_id(DecoderUtil.UtfDecoder(trafficStation_id));
		trafficLineAndStation.setStationType(stationType);
		CommonEntity status = trafficLineAndStation.getStatus();
		status.setVersionFlag(versionFlag);
		status.setOrderValue(orderValue);
		trafficLineAndStation.setStatus(status);
		dbDAO.merge(trafficLineAndStation);
		return true;
	}

	@Override
	public boolean persist(String trafficType_id,String trafficLine_id,String trafficStation_id,Integer stationType,Integer orderValue, String versionFlag) throws ServiceException {
		TrafficLineAndStation trafficLineAndStation = new TrafficLineAndStation();
		trafficLineAndStation.setTrafficType_id(DecoderUtil.UtfDecoder(trafficType_id));
		trafficLineAndStation.setTrafficLine_id(DecoderUtil.UtfDecoder(trafficLine_id));
		trafficLineAndStation.setTrafficStation_id(DecoderUtil.UtfDecoder(trafficStation_id));
		trafficLineAndStation.setStationType(stationType);
		CommonEntity status = trafficLineAndStation.getStatus();
		status.setVersionFlag(versionFlag);
		status.setOrderValue(orderValue);
		trafficLineAndStation.setStatus(status);
		dbDAO.persist(trafficLineAndStation);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		TrafficLineAndStation trafficLineAndStation = dbDAO.queryById(this.getTableNameFromEntity(TrafficLineAndStation.class), id);
		dbDAO.remove(trafficLineAndStation);
		return true;
	}

	@Override
	public List<TrafficLineAndStation> queryListByStationId(String stationId,String versionFlag) throws ServiceException {
		return dbDAO.queryByList(TrafficLineAndStation.class.getSimpleName(), deleteFlag, versionFlag, "and s.trafficStation_id =?3", new Object[]{stationId}, orderBy, null);
	}

	@Override
	public List<TrafficLineAndStation> queryListByLineAndGo(
			String trafficLineId, String versionFlag) throws ServiceException {
		List<TrafficLineAndStation> list = dbDAO.queryByList(TrafficLineAndStation.class.getSimpleName(), deleteFlag, versionFlag, "and s.trafficLine_id =?3 and s.stationType in (1,2)", new Object[]{trafficLineId}, orderByOrderValueAsc, null);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}
	
	@Override
	public List<TrafficLineAndStation> queryListByLineAndBack(
			String trafficLineId, String versionFlag) throws ServiceException {
		List<TrafficLineAndStation> list = dbDAO.queryByList(TrafficLineAndStation.class.getSimpleName(), deleteFlag, versionFlag, "and s.trafficLine_id =?3 and s.stationType in (1,3)", new Object[]{trafficLineId}, orderValueOrderBy, null);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public Pagination queryByTrafficLineId(String trafficLineId, int pageSize,
			String versionFlag) throws ServiceException {
		return dbDAO.queryByPageModule(TrafficLineAndStation.class.getSimpleName(), deleteFlag, versionFlag, "and trafficLine_id =?3", new Object[]{trafficLineId}, orderValueOrderBy, PaginationContext.getOffset(), pageSize);
	}

}