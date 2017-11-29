package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.FlightSite;

/**
 * MQ业务层接口
 * @author haishui
 */
public interface IMqService extends IBaseService<Object, String> {
	
	/**
	 * 通过起始地和到达地判断是国内还是国际航班
	 * @param org
	 * @return 返回是否是国际航班，不是国际就是国内航班
	 */
	boolean isInternational(String org);
	
	/**
	 * 根据三字码查询机场三四字码表
	 * @param threeCOde 三字码
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	FlightSite queryEntityByThreeCOde(String threeCOde,String versionFlag) throws ServiceException;
}