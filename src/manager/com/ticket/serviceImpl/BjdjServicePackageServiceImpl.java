package com.ticket.serviceImpl;


import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjHall;
import com.ticket.pojo.BjdjPage;
import com.ticket.pojo.BjdjServicePackage;
import com.ticket.service.IBjdjHallService;
import com.ticket.service.IBjdjPageService;
import com.ticket.service.IBjdjServicePackageService;
import com.ticket.util.DecoderUtil;

/**
 * 便捷登机服务套餐业务接口实现类
 * @ClassName: IBjdjServicePackageService   
 * @Description: 提供便捷登机服务套餐操作的增删改查   
 * @author HiSay  
 * @date 2016-06-30 17:48:33
 *
 */
public class BjdjServicePackageServiceImpl extends BaseServiceImpl<BjdjServicePackage, String> implements IBjdjServicePackageService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(BjdjServicePackageServiceImpl.class);
	@Resource private IBjdjHallService bjdjHallService;
	@Resource private IBjdjPageService bjdjPageService;
	
	@Override
	public boolean merge(String id, String name,double price,String description,String bjdjHall_id,String page_id, String versionFlag) throws ServiceException {
		
		BjdjServicePackage bjdjServicePackage = dbDAO.queryById(this.getTableNameFromEntity(BjdjServicePackage.class), id);
		bjdjServicePackage.setName(DecoderUtil.UtfDecoder(name));
		bjdjServicePackage.setPrice(price);
		bjdjServicePackage.setDescription(DecoderUtil.UtfDecoder(description));
		
		BjdjHall bjdjHall = bjdjHallService.queryById(BjdjHall.class.getName(), bjdjHall_id);
		bjdjServicePackage.setBjdjHall(bjdjHall);
		
		BjdjPage bjdjPage = bjdjPageService.queryById(BjdjPage.class.getName(), page_id);
		bjdjServicePackage.setBjdjPage(bjdjPage);
		
		bjdjServicePackage.getStatus().setAudit(1);
		dbDAO.merge(bjdjServicePackage);
		return true;
	}

	@Override
	public boolean persist(String name,double price,String description,String bjdjHall_id,String page_id, String versionFlag) throws ServiceException {
		
		BjdjServicePackage bjdjServicePackage = new BjdjServicePackage();
		bjdjServicePackage.setName(DecoderUtil.UtfDecoder(name));
		bjdjServicePackage.setPrice(price);
		bjdjServicePackage.setDescription(DecoderUtil.UtfDecoder(description));
		BjdjHall bjdjHall = bjdjHallService.queryById(BjdjHall.class.getName(), bjdjHall_id);
		bjdjServicePackage.setBjdjHall(bjdjHall);
		BjdjPage bjdjPage = bjdjPageService.queryById(BjdjPage.class.getName(), page_id);
		bjdjServicePackage.setBjdjPage(bjdjPage);
		dbDAO.persist(bjdjServicePackage);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		BjdjServicePackage bjdjServicePackage = dbDAO.queryById(this.getTableNameFromEntity(BjdjServicePackage.class), id);
		dbDAO.remove(bjdjServicePackage);
		return true;
	}

	@Override
	public BjdjServicePackage getByName(String name) {
		
		BjdjServicePackage obj = dbDAO.executeJPQLForQuerySingle(
				"select t from " + BjdjServicePackage.class.getName() + " t where t.name=?", 
				BjdjServicePackage.class, name);
		return obj;
	}
	
	@Override
	public BjdjServicePackage getMinPrice() {
		
		BjdjServicePackage obj = dbDAO.executeJPQLForQuerySingle(
				"select t from " + BjdjServicePackage.class.getName() + " t order by t.price", 
				BjdjServicePackage.class);
		return obj;
	}

	@Override
	public List<BjdjServicePackage> queryByBjdjPage(String bjdjPage_id)
			throws ServiceException {
		List<BjdjServicePackage> bjdjServicePackages = dbDAO.executeJPQLForQuery("select c from " + BjdjServicePackage.class.getName() + " c where c.bjdjPage.id = ? and c.status.deleteFlag = 0 and c.status.audit = 1",BjdjServicePackage.class, bjdjPage_id);
		return bjdjServicePackages;
	}

	@Override
	public BjdjServicePackage getMinPriceByBjdjPage(String bjdjPage_id)
			throws ServiceException {
		BjdjServicePackage bjdjServicePackage = dbDAO.executeJPQLForQuerySingle("select c from " + BjdjServicePackage.class.getName() + " c where c.bjdjPage.id = ? and c.status.deleteFlag = 0 and c.status.audit = 1 order by c.price", BjdjServicePackage.class ,bjdjPage_id);
		return bjdjServicePackage;
	}

	@Override
	public BjdjServicePackage getMinPriceByBjdjUrl(String url)
			throws ServiceException {
		BjdjServicePackage bjdjServicePackage = dbDAO.executeJPQLForQuerySingle("select c from " + BjdjServicePackage.class.getName() + " c where c.bjdjPage.url = ? and c.status.deleteFlag = 0 and c.status.audit = 1 order by c.price", BjdjServicePackage.class , url);
		return bjdjServicePackage;
	}

	@Override
	public List<BjdjServicePackage> queryByBjdjUrl(String url)
			throws ServiceException {
		List<BjdjServicePackage> bjdjServicePackages = dbDAO.executeJPQLForQuery("select c from " + BjdjServicePackage.class.getName() + " c where c.bjdjPage.url = ? and c.status.deleteFlag = 0 and c.status.audit = 1", BjdjServicePackage.class , url);
		return bjdjServicePackages;
	}
}