package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.TrafficLine;
import com.ticket.service.ITrafficLineService;
import com.ticket.util.DateUtil;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.PaginationContext;

/**
 * 交通路线业务接口实现类
 * @ClassName: ITrafficLineService   
 * @Description: 提供交通路线操作的增删改查   
 * @author HiSay  
 * @date 2015-11-19 09:55:18
 *
 */
public class TrafficLineServiceImpl extends BaseServiceImpl<TrafficLine, String> implements ITrafficLineService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(TrafficLineServiceImpl.class);
	
	@Override
	public boolean merge(String id, String trafficType_id,String name,String startStation,String endStation,String startTime,String endTime,String departureRate,Integer carCount,String carModel,Integer seatCount,Double price,String remark,Integer orderValue, String versionFlag) throws ServiceException {
		TrafficLine trafficLine = dbDAO.queryById(this.getTableNameFromEntity(TrafficLine.class), id);
		trafficLine.setTrafficType_id(DecoderUtil.UtfDecoder(trafficType_id));
		trafficLine.setName(DecoderUtil.UtfDecoder(name));
		trafficLine.setStartStation(DecoderUtil.UtfDecoder(startStation));
		trafficLine.setEndStation(DecoderUtil.UtfDecoder(endStation));
		trafficLine.setStartTime(DateUtil.parseStringToDate(startTime, "HH:mm"));
		trafficLine.setEndTime(DateUtil.parseStringToDate(endTime, "HH:mm"));
		trafficLine.setDepartureRate(DecoderUtil.UtfDecoder(departureRate));
		trafficLine.setCarCount(carCount);
		trafficLine.setCarModel(DecoderUtil.UtfDecoder(carModel));
		trafficLine.setSeatCount(seatCount);
		trafficLine.setPrice(price);
		trafficLine.setRemark(remark);
		CommonEntity status = trafficLine.getStatus();
		status.setOrderValue(orderValue);
		status.setVersionFlag(versionFlag);
		trafficLine.setStatus(status);
		dbDAO.merge(trafficLine);
		return true;
	}

	@Override
	public boolean persist(String trafficType_id,String name,String startStation,String endStation,String startTime,String endTime,String departureRate,Integer carCount,String carModel,Integer seatCount,Double price,String remark,Integer orderValue, String versionFlag) throws ServiceException {
		TrafficLine trafficLine = new TrafficLine();
		trafficLine.setTrafficType_id(DecoderUtil.UtfDecoder(trafficType_id));
		trafficLine.setName(DecoderUtil.UtfDecoder(name));
		trafficLine.setStartStation(DecoderUtil.UtfDecoder(startStation));
		trafficLine.setEndStation(DecoderUtil.UtfDecoder(endStation));
		trafficLine.setStartTime(DateUtil.parseStringToDate(startTime, "HH:mm"));
		trafficLine.setEndTime(DateUtil.parseStringToDate(endTime, "HH:mm"));
		trafficLine.setDepartureRate(DecoderUtil.UtfDecoder(departureRate));
		trafficLine.setCarCount(carCount);
		trafficLine.setCarModel(DecoderUtil.UtfDecoder(carModel));
		trafficLine.setSeatCount(seatCount);
		trafficLine.setPrice(price);
		trafficLine.setRemark(remark);
		CommonEntity status = trafficLine.getStatus();
		status.setOrderValue(orderValue);
		status.setVersionFlag(versionFlag);
		trafficLine.setStatus(status);
		dbDAO.persist(trafficLine);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		TrafficLine trafficLine = dbDAO.queryById(this.getTableNameFromEntity(TrafficLine.class), id);
		dbDAO.remove(trafficLine);
		return true;
	}

	@Override
	public boolean batchRealDelete(String idsValue, String versionFlag)
			throws ServiceException {
		idsValue = GeneralUtil.convertIdsValueToStringByDelete(idsValue, ",");
		dbDAO.deleteAll(TrafficLine.class.getSimpleName(), idsValue, null);
		return true;
	}

	@Override
	public List<TrafficLine> queryList(String versionFlag) throws ServiceException {
		List<TrafficLine> list = dbDAO.queryByList(TrafficLine.class.getSimpleName(), deleteFlag, versionFlag, null, null, orderValueOrderBy, null);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<TrafficLine> queryListByTypeId(String trafficType_id, String versionFlag) throws ServiceException {
		List<TrafficLine> list = dbDAO.queryByList(TrafficLine.class.getSimpleName(), deleteFlag, versionFlag, "and s.trafficType_id =?3", new Object[]{trafficType_id}, orderValueOrderBy, null);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public Pagination queryEntityByTypeId(String trafficTypeId, int pageSize,
			String versionFlag) throws ServiceException {
		return dbDAO.queryByPageModule(TrafficLine.class.getSimpleName(), deleteFlag, versionFlag, "and s.trafficType_id =?3", new Object[]{trafficTypeId}, orderValueOrderBy, PaginationContext.getOffset(), pageSize);
	}

}