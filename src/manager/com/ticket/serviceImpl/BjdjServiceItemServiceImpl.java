package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjServiceItem;
import com.ticket.pojo.BjdjServicePackage;
import com.ticket.pojo.BjdjServicePackageItem;
import com.ticket.pojo.CommonEntity;
import com.ticket.service.IBjdjServiceItemService;
import com.ticket.util.DecoderUtil;

/**
 * 便捷登机服务项业务接口实现类
 * @ClassName: IBjdjServiceItemService   
 * @Description: 提供便捷登机服务项操作的增删改查   
 * @author HiSay  
 * @date 2016-06-30 17:48:04
 *
 */
public class BjdjServiceItemServiceImpl extends BaseServiceImpl<BjdjServiceItem, String> implements IBjdjServiceItemService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(BjdjServiceItemServiceImpl.class);
	
	@Override
	public boolean merge(String id, String name,String description, String versionFlag) throws ServiceException {
		BjdjServiceItem bjdjServiceItem = dbDAO.queryById(this.getTableNameFromEntity(BjdjServiceItem.class), id);
		bjdjServiceItem.setName(DecoderUtil.UtfDecoder(name));
		bjdjServiceItem.setDescription(DecoderUtil.UtfDecoder(description));
		CommonEntity status = bjdjServiceItem.getStatus();
		status.setVersionFlag(versionFlag);
		bjdjServiceItem.setStatus(status);
		dbDAO.merge(bjdjServiceItem);
		return true;
	}

	@Override
	public boolean persist(String name,String description, String versionFlag) throws ServiceException {
		BjdjServiceItem bjdjServiceItem = new BjdjServiceItem();
		bjdjServiceItem.setName(DecoderUtil.UtfDecoder(name));
		bjdjServiceItem.setDescription(DecoderUtil.UtfDecoder(description));
		CommonEntity status = bjdjServiceItem.getStatus();
		status.setVersionFlag(versionFlag);
		bjdjServiceItem.setStatus(status);
		dbDAO.persist(bjdjServiceItem);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		BjdjServiceItem bjdjServiceItem = dbDAO.queryById(this.getTableNameFromEntity(BjdjServiceItem.class), id);
		dbDAO.remove(bjdjServiceItem);
		return true;
	}

	@Override
	public BjdjServiceItem getByName(String name) {
		
		BjdjServiceItem obj = dbDAO.executeJPQLForQuerySingle(
				"select t from " + BjdjServiceItem.class.getName() + " t where t.name=?", 
				BjdjServiceItem.class, name);
		return obj;
	}

	@Override
	public List<BjdjServiceItem> get(BjdjServicePackage packageType) {
		
		List<BjdjServiceItem> obj = dbDAO.executeJPQLForQuery(
				"select i from " + BjdjServicePackageItem.class.getName() + " t"
				+ " join t.packageName pt"
				+ " join t.item i where pt=?", 
				BjdjServiceItem.class, packageType);
		return obj;
	}
}