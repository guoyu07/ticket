package com.ticket.serviceImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.FlightSite;
import com.ticket.service.IFlightSiteService;

/**
 * MQ业务层接口
 * @author haishui
 */
public class FlightSiteServiceImpl extends BaseServiceImpl<FlightSite, String> implements IFlightSiteService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(FlightSiteServiceImpl.class);

	@Override
	public FlightSite queryEntityByThreeCode(String threeCode,
			String versionFlag) throws ServiceException {
		String sql = "select t from "+FlightSite.class.getName()+" t where t.des =?";
		
		return dbDAO.executeJPQLForQuerySingle(sql, FlightSite.class, threeCode);
	}
}