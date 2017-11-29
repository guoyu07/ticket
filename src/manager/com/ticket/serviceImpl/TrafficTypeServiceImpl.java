package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.TrafficType;
import com.ticket.service.ITrafficTypeService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

/**
 * 交通路线类型业务接口实现类
 * @ClassName: ITrafficTypeService   
 * @Description: 提供交通路线类型操作的增删改查   
 * @author HiSay  
 * @date 2015-11-19 09:42:49
 *
 */
public class TrafficTypeServiceImpl extends BaseServiceImpl<TrafficType, String> implements ITrafficTypeService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(TrafficTypeServiceImpl.class);
	
	@Override
	public boolean merge(String id, String name,String introduce,Double longitude,Double latitude,String floorNumber,Integer orderValue,Integer hot, String versionFlag) throws ServiceException {
		TrafficType trafficType = dbDAO.queryById(this.getTableNameFromEntity(TrafficType.class), id);
		trafficType.setName(DecoderUtil.UtfDecoder(name));
		trafficType.setIntroduce(introduce);
		trafficType.setLongitude(longitude);
		trafficType.setLatitude(latitude);
		trafficType.setFloorNumber(floorNumber);
		CommonEntity status = trafficType.getStatus();
		status.setOrderValue(orderValue);
		status.setHot(hot);
		status.setVersionFlag(versionFlag);
		trafficType.setStatus(status);
		dbDAO.merge(trafficType);
		return true;
	}

	@Override
	public boolean persist(String name,String introduce,Double longitude,Double latitude,String floorNumber,Integer orderValue,Integer hot, String versionFlag) throws ServiceException {
		TrafficType trafficType = new TrafficType();
		trafficType.setName(DecoderUtil.UtfDecoder(name));
		trafficType.setIntroduce(introduce);
		trafficType.setLongitude(longitude);
		trafficType.setLatitude(latitude);
		trafficType.setFloorNumber(floorNumber);
		CommonEntity status = trafficType.getStatus();
		status.setOrderValue(orderValue);
		status.setHot(hot);
		status.setVersionFlag(versionFlag);
		trafficType.setStatus(status);
		dbDAO.persist(trafficType);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		TrafficType trafficType = dbDAO.queryById(this.getTableNameFromEntity(TrafficType.class), id);
		dbDAO.remove(trafficType);
		return true;
	}

	@Override
	public boolean batchRealDelete(String idsValue, String versionFlag)
			throws ServiceException {
		idsValue = GeneralUtil.convertIdsValueToStringByDelete(idsValue, ",");
		dbDAO.deleteAll(TrafficType.class.getSimpleName(), idsValue, null);
		return true;
	}

	@Override
	public List<TrafficType> queryList(String versionFlag) throws ServiceException {
		List<TrafficType> list = dbDAO.queryByList(TrafficType.class.getSimpleName(), deleteFlag, versionFlag, null, null, orderValueOrderBy, null);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<TrafficType> queryListByKeyword(String keyword, String versionFlag)
			throws ServiceException {
		List<TrafficType> list = dbDAO.queryByList(TrafficType.class.getSimpleName(), deleteFlag, versionFlag, "and s.name like ?3", new Object[]{"%"+keyword+"%"}, orderBy, null);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}
	
	@Override
	public List<TrafficType> queryListAll(String versionFlag) throws ServiceException {
		List<TrafficType> list = dbDAO.queryByList(TrafficType.class.getSimpleName(), deleteFlag, versionFlag, null, null, orderValueOrderBy, null);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}

}