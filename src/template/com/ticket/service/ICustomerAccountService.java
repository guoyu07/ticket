package com.ticket.service;

import java.rmi.server.ServerCloneException;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CustomerAccount;
import com.ticket.pojo.Pagination;
/**
 * 客户账户业务层接口
 * @package  com.ticket.service
 * @file     ICustomerAccountService.java
 * @author   wangjiafeng
 * @date     2015-12-29 上午09:27:20
 * @version  V 1.0
 */
public interface ICustomerAccountService extends IBaseService<CustomerAccount, String>{
	/**
	 * @author wangjiafeng
	 * 添加账户信息
	 * @method add
	 * @param channelCustomerInfo_id
	 * @param operation
	 * @param money
	 * @param entityName
	 * @param entityId
	 * @param remark
	 * @return
	 * @throws ServerCloneException
	 * @return Boolean
	 * @date 2015-12-29 上午09:36:44
	 */
	Boolean add(String channelCustomerInfo_id,Integer operation,Double money,Double songMoney,String entityName,
			String entityId,String remark) throws ServerCloneException;
	/**
	 * @author wangjiafeng
	 * 获取客户的所有账目
	 * @method queryAll
	 * @param channelCustomerInfo_id
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 * @return Pagination
	 * @date 2015-12-29 上午09:40:23
	 */
	Pagination queryAll(String channelCustomerInfo_id,Integer pageSize) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 获取客户的指定加减账目
	 * @method queryAll
	 * @param channelCustomerInfo_id
	 * @param operation
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 * @return Pagination
	 * @date 2015-12-29 上午09:40:57
	 */
	Pagination queryAll(String channelCustomerInfo_id,Integer operation,Integer pageSize) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 获取操作人员的操作过的账目
	 * @method queryAll
	 * @param entityName
	 * @param entityId
	 * @param operation
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 * @return Pagination
	 * @date 2015-12-29 上午09:41:36
	 */
	Pagination queryAll(String entityName,String entityId,Integer operation,Integer pageSize) throws ServiceException;
	/**
	 * @author wangjaifeng
	 * 统计账户的金额
	 * @method querySumMoney
	 * @param channelCustomerInfo_id
	 * @return
	 * @throws ServiceException
	 * @return Double
	 * @date 2015-12-29 上午09:44:01
	 */
	Double querySumMoney(String channelCustomerInfo_id) throws ServiceException;
	/**
	 * @author wangjaifeng
	 * 统计账户赠送的金额
	 * @method querySumMoney
	 * @param channelCustomerInfo_id
	 * @return
	 * @throws ServiceException
	 * @return Double
	 * @date 2015-12-29 上午09:44:01
	 */
	Double querySumSongMoney(String channelCustomerInfo_id) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 获取账户总金额
	 * @method getSumMoney
	 * @param channelCustomerInfo_id
	 * @return
	 * @throws ServerCloneException
	 * @return Double
	 * @date 2015-12-29 上午10:34:27
	 */
	Double getSumMoney(String channelCustomerInfo_id) throws ServerCloneException;
}
