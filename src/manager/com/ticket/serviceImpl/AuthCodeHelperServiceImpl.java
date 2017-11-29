package com.ticket.serviceImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.AuthCodeHelper;
import com.ticket.pojo.CommonEntity;
import com.ticket.service.IAuthCodeHelperService;

public class AuthCodeHelperServiceImpl extends BaseServiceImpl<AuthCodeHelper, String> 
								implements IAuthCodeHelperService{
	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(AirportBusinessTypeServiceImpl.class);
	@Override
	public boolean persist(String code, String phone) throws ServiceException {
		AuthCodeHelper codeHelper = new AuthCodeHelper();
		codeHelper.setCode(code);
		codeHelper.setPhone(phone);
		CommonEntity status = codeHelper.getStatus();
		status.setVersionFlag(versionFlag);
		codeHelper.setStatus(status);
		dbDAO.persist(codeHelper);
		return true;
	}

	@Override
	public AuthCodeHelper queryByphoneAndCode(String phone, String code)
			throws ServiceException {
		AuthCodeHelper authCodeHelper = dbDAO.executeJPQLForQuerySingle("select c from " + AuthCodeHelper.class.getName() + " c where c.phone = ? and c.code = ?", AuthCodeHelper.class, phone,code);
		return authCodeHelper;
	}

}
