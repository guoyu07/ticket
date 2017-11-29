package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.FCSelfCheckinPosition;
import com.ticket.service.IFCSelfCheckinPositionService;
import com.ticket.util.DecoderUtil;

/**
 * 航空公司自助值机位置表业务接口实现类
 * @ClassName: IFCSelfCheckinPositionService   
 * @Description: 提供航空公司自助值机位置表操作的增删改查   
 * @author HiSay  
 * @date 2016-03-30 17:01:09
 *
 */
public class FCSelfCheckinPositionServiceImpl extends BaseServiceImpl<FCSelfCheckinPosition, String> implements IFCSelfCheckinPositionService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(FCSelfCheckinPositionServiceImpl.class);
	
	@Override
	public boolean merge(String id, String name,String flightCompany_id,String floorNumber,Double longitude,Double latitude, String versionFlag) throws ServiceException {
		FCSelfCheckinPosition fCSelfCheckinPosition = dbDAO.queryById(this.getTableNameFromEntity(FCSelfCheckinPosition.class), id);
		fCSelfCheckinPosition.setName(DecoderUtil.UtfDecoder(name));
		fCSelfCheckinPosition.setFlightCompany_id(DecoderUtil.UtfDecoder(flightCompany_id));
		fCSelfCheckinPosition.setFloorNumber(DecoderUtil.UtfDecoder(floorNumber));
		fCSelfCheckinPosition.setLongitude(longitude);
		fCSelfCheckinPosition.setLatitude(latitude);
		CommonEntity status = fCSelfCheckinPosition.getStatus();
		status.setVersionFlag(versionFlag);
		fCSelfCheckinPosition.setStatus(status);
		dbDAO.merge(fCSelfCheckinPosition);
		return true;
	}

	@Override
	public boolean persist(String name,String flightCompany_id,String floorNumber,Double longitude,Double latitude, String versionFlag) throws ServiceException {
		FCSelfCheckinPosition fCSelfCheckinPosition = new FCSelfCheckinPosition();
		fCSelfCheckinPosition.setName(DecoderUtil.UtfDecoder(name));
		fCSelfCheckinPosition.setFlightCompany_id(DecoderUtil.UtfDecoder(flightCompany_id));
		fCSelfCheckinPosition.setFloorNumber(DecoderUtil.UtfDecoder(floorNumber));
		fCSelfCheckinPosition.setLongitude(longitude);
		fCSelfCheckinPosition.setLatitude(latitude);
		CommonEntity status = fCSelfCheckinPosition.getStatus();
		status.setVersionFlag(versionFlag);
		fCSelfCheckinPosition.setStatus(status);
		dbDAO.persist(fCSelfCheckinPosition);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		FCSelfCheckinPosition fCSelfCheckinPosition = dbDAO.queryById(this.getTableNameFromEntity(FCSelfCheckinPosition.class), id);
		dbDAO.remove(fCSelfCheckinPosition);
		return true;
	}

	@Override
	public List<FCSelfCheckinPosition> queryListByFlightCompanyId(
			String flightCompanyId, String versionFlag) throws ServiceException {
		return dbDAO.queryByList(FCSelfCheckinPosition.class.getSimpleName(), deleteFlag, versionFlag, "and s.flightCompany_id =?3", new Object[]{flightCompanyId}, orderBy, null);
	}

	@Override
	public void deleteByCompanyId(String id, String versionFlag)
			throws ServiceException {
		dbDAO.deleteByWhere(FCSelfCheckinPosition.class.getSimpleName(), deleteFlag, versionFlag, "and s.flightCompany_id =?3", new Object[]{id});
	}
	
}