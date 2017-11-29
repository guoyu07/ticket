package com.ticket.serviceImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.FlightSite;
import com.ticket.service.IMqService;

/**
 * MQ业务层接口
 * @author haishui
 */
public class MqServiceImpl extends BaseServiceImpl<Object, String> implements IMqService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(MqServiceImpl.class);
	
	@Override
	public boolean isInternational(String site) {
		
		String international = dbDAO.executeJPQLForQuerySingle(
				"select t.site from " + FlightSite.class.getName() + " t where t.org=?", 
				String.class, site);
		if("CN".equals(international)){
			
			return false;
		}
		return true;
	}

	@Override
	public FlightSite queryEntityByThreeCOde(String threeCOde,
			String versionFlag) throws ServiceException {
		return (FlightSite) dbDAO.queryByCustom(FlightSite.class.getSimpleName(), deleteFlag, versionFlag, "and s.des =?3", new Object[]{threeCOde});
	}
}