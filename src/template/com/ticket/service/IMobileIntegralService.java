package com.ticket.service;

import com.ticket.exception.ServiceException;

/**
 * 移动积分服务层
 * @author tuyou
 */
public interface IMobileIntegralService {

	/**
	 * 获取mobile手机对应用户的积分数量
	 * @param mobile 手机号码
	 * @return 积分数量
	 * @throws ServiceException
	 */
	public int getIntegral(String mobile) throws ServiceException;
	
	/**
	 * 扣除mobile手机对应用户的移动积分
	 * @param mobile 手机号码
	 * @throws ServiceException
	 */
	public void deductIntegral(String mobile) throws ServiceException;
	
	/**
	 * 购买一个服务码
	 * @param mobile
	 */
//	public BjdjServiceCode buy(String mobile) throws ServiceException;
}
