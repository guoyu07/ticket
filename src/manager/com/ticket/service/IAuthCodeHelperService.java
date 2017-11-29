package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.AuthCodeHelper;

public interface IAuthCodeHelperService extends IBaseService<AuthCodeHelper, String>{
	/**
	 * 保存
	 * @param code
	 * @param phone
	 * @return
	 * @throws ServiceException
	 */
	boolean persist(String code,String phone) throws ServiceException;
	
	/**
	 * 根据手机号码和验证码查找
	 * @param phone
	 * @param code
	 * @return
	 * @throws ServiceException
	 */
	AuthCodeHelper queryByphoneAndCode(String phone,String code) throws ServiceException;
}
