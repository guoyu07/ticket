package com.ticket.serviceImpl;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjServiceItem;
import com.ticket.pojo.BjdjServicePackage;
import com.ticket.pojo.BjdjServicePackageItem;
import com.ticket.pojo.CommonEntity;
import com.ticket.service.IBjdjServicePackageItemService;

/**
 * 便捷登机服务套餐关联项业务接口实现类
 * @ClassName: IBjdjServicePackageItemService   
 * @Description: 提供便捷登机服务套餐关联项操作的增删改查
 * @author HiSay  
 * @date 2016-06-30 17:49:00
 *
 */
public class BjdjServicePackageItemServiceImpl extends BaseServiceImpl<BjdjServicePackageItem, String> implements IBjdjServicePackageItemService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(BjdjServicePackageItemServiceImpl.class);
	
	@Override
	public boolean merge(String id, BjdjServicePackage packageName,BjdjServiceItem item, Integer orderValue, String versionFlag) throws ServiceException {
		BjdjServicePackageItem bjdjServicePackageItem = dbDAO.queryById(this.getTableNameFromEntity(BjdjServicePackageItem.class), id);
		bjdjServicePackageItem.setPackageName(packageName);
		bjdjServicePackageItem.setItem(item);
		CommonEntity status = bjdjServicePackageItem.getStatus();
		status.setVersionFlag(versionFlag);
		status.setOrderValue(orderValue);
		bjdjServicePackageItem.setStatus(status);
		dbDAO.merge(bjdjServicePackageItem);
		return true;
	}

	@Override
	public boolean persist(BjdjServicePackage packageName,BjdjServiceItem item, Integer orderValue, String versionFlag) throws ServiceException {
		BjdjServicePackageItem bjdjServicePackageItem = query(packageName, item);
		if(bjdjServicePackageItem != null){
			
			throw new ServiceException("已存在对应的关联关系，请勿重复添加");
		}
		bjdjServicePackageItem = new BjdjServicePackageItem();
		bjdjServicePackageItem.setPackageName(packageName);
		bjdjServicePackageItem.setItem(item);
		CommonEntity status = bjdjServicePackageItem.getStatus();
		status.setVersionFlag(versionFlag);
		status.setOrderValue(orderValue);
		bjdjServicePackageItem.setStatus(status);
		dbDAO.persist(bjdjServicePackageItem);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		BjdjServicePackageItem bjdjServicePackageItem = dbDAO.queryById(this.getTableNameFromEntity(BjdjServicePackageItem.class), id);
		dbDAO.remove(bjdjServicePackageItem);
		return true;
	}

	@Override
	public BjdjServicePackageItem query(BjdjServicePackage package1, BjdjServiceItem item) {
		
		BjdjServicePackageItem bjdjServicePackageItem = dbDAO.executeJPQLForQuerySingle(
				"select pi from " + BjdjServicePackageItem.class.getName() + " pi where pi.packageName=? and pi.item=?", 
				BjdjServicePackageItem.class, package1, item);
		return bjdjServicePackageItem;
	}

	@Override
	public BjdjServicePackageItem getByName(String name) {
		
		BjdjServicePackageItem obj = dbDAO.executeJPQLForQuerySingle(
				"select t from " + BjdjServicePackageItem.class.getName() + " t where t.name=?", 
				BjdjServicePackageItem.class, name);
		return obj;
	}
}