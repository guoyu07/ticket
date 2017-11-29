package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.FlightSite;


/**
 * MQ业务层接口
 * @author haishui
 */
public interface IFlightSiteService extends IBaseService<FlightSite, String> {
	
	/**
	 * 根据三字码查询机场三四字码表
	 * @param threeCode 三字码
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	FlightSite queryEntityByThreeCode(String threeCode,String versionFlag) throws ServiceException;
}