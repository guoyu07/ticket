package com.ticket.serviceImpl;

import java.rmi.server.ServerCloneException;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.ChannelCustomerInfo;
import com.ticket.pojo.CustomerAccount;
import com.ticket.pojo.Pagination;
import com.ticket.service.ICustomerAccountService;
import com.ticket.util.Arith;
import com.ticket.util.PaginationContext;
/**
 * 客户账号业务层实现类
 * @package  com.ticket.serviceImpl
 * @file     CustomerAccountServiceImpl.java
 * @author   wangjiafeng
 * @date     2015-12-29 上午09:29:11
 * @version  V 1.0
 */
public class CustomerAccountServiceImpl extends BaseServiceImpl<CustomerAccount, String> implements 
		ICustomerAccountService{

	@Override
	public Boolean add(String channelCustomerInfoId, Integer operation,
			Double money,Double songMoney, String entityName, String entityId, String remark)
			throws ServerCloneException {
		try {
			CustomerAccount customerAccount = new CustomerAccount();
			customerAccount.setChannelCustomerInfo(get(ChannelCustomerInfo.class, channelCustomerInfoId));
			customerAccount.setOperation(operation);
			customerAccount.setMoney(money);
			customerAccount.setSongMoney(songMoney);
			customerAccount.setEntityName(entityName);
			customerAccount.setEntityId(entityId);
			customerAccount.setRemark(remark);
			this.persist(customerAccount);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Pagination queryAll(String channelCustomerInfoId, Integer pageSize)
			throws ServiceException {
		try {
			return dbDAO.queryByPageModule(getTableNameFromEntity(CustomerAccount.class), 
					deleteFlag, versionFlag,"and s.channelCustomerInfo.id=?3 ", 
					new Object[]{channelCustomerInfoId}, orderBy, PaginationContext.getOffset(), pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Pagination queryAll(String channelCustomerInfoId, Integer operation,
			Integer pageSize) throws ServiceException {
		try {
			return dbDAO.queryByPageModule(getTableNameFromEntity(CustomerAccount.class), 
					deleteFlag, versionFlag,"and s.channelCustomerInfo.id=?3 and s.operation=?4 ", 
					new Object[]{channelCustomerInfoId,operation}, orderBy, PaginationContext.getOffset(), pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Pagination queryAll(String entityName, String entityId,
			Integer operation, Integer pageSize) throws ServiceException {
		try {
			return dbDAO.queryByPageModule(getTableNameFromEntity(CustomerAccount.class), 
					deleteFlag, versionFlag,"and s.entityName=?3 and s.operation=?4 and s.entityId=?5 ", 
					new Object[]{entityName,operation,entityId}, orderBy, PaginationContext.getOffset(), pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Double querySumMoney(String channelCustomerInfoId)
			throws ServiceException {
		try {
			return dbDAO.executeCountSQL("select sum(s.money) from CustomerAccount s where s.channelCustomerInfo.id=?1 ",
					new Object[]{channelCustomerInfoId});
		} catch (Exception e) {
			e.printStackTrace();
			return 0d;
		}
	}

	@Override
	public Double querySumSongMoney(String channelCustomerInfoId)
			throws ServiceException {
		try {
			return dbDAO.executeCountSQL("select sum(s.songMoney) from CustomerAccount s where s.channelCustomerInfo.id=?1 ",
					new Object[]{channelCustomerInfoId});
		} catch (Exception e) {
			e.printStackTrace();
			return 0d;
		}
	}

	@Override
	public Double getSumMoney(String channelCustomerInfoId)
			throws ServerCloneException {
		try {
			return Arith.add(querySumMoney(channelCustomerInfoId), querySumSongMoney(channelCustomerInfoId));
		} catch (Exception e) {
			e.printStackTrace();
			return 0D;
		}
	}

}
