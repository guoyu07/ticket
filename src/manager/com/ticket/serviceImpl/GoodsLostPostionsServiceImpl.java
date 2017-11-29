package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.GoodsLostPostions;
import com.ticket.service.IGoodsLostPostionsService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

/**
 * 物品遗失位置业务接口实现类
 * @ClassName: IGoodsLostPostionsService   
 * @Description: 提供物品遗失位置操作的增删改查   
 * @author HiSay  
 * @date 2015-11-23 16:44:38
 *
 */
public class GoodsLostPostionsServiceImpl extends BaseServiceImpl<GoodsLostPostions, String> implements IGoodsLostPostionsService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(GoodsLostPostionsServiceImpl.class);
	
	@Override
	public boolean merge(String id, String name,String description,Double longitude,Double latitude, String versionFlag) throws ServiceException {
		GoodsLostPostions goodsLostPostions = dbDAO.queryById(this.getTableNameFromEntity(GoodsLostPostions.class), id);
		goodsLostPostions.setName(DecoderUtil.UtfDecoder(name));
		goodsLostPostions.setDescription(DecoderUtil.UtfDecoder(description));
		goodsLostPostions.setLongitude(longitude);
		goodsLostPostions.setLatitude(latitude);
		CommonEntity status = goodsLostPostions.getStatus();
		status.setVersionFlag(versionFlag);
		goodsLostPostions.setStatus(status);
		dbDAO.merge(goodsLostPostions);
		return true;
	}

	@Override
	public boolean persist(String name,String description,Double longitude,Double latitude, String versionFlag) throws ServiceException {
		GoodsLostPostions goodsLostPostions = new GoodsLostPostions();
		goodsLostPostions.setName(DecoderUtil.UtfDecoder(name));
		goodsLostPostions.setDescription(DecoderUtil.UtfDecoder(description));
		goodsLostPostions.setLongitude(longitude);
		goodsLostPostions.setLatitude(latitude);
		CommonEntity status = goodsLostPostions.getStatus();
		status.setVersionFlag(versionFlag);
		goodsLostPostions.setStatus(status);
		dbDAO.persist(goodsLostPostions);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		GoodsLostPostions goodsLostPostions = dbDAO.queryById(this.getTableNameFromEntity(GoodsLostPostions.class), id);
		dbDAO.remove(goodsLostPostions);
		return true;
	}

	@Override
	public boolean batchRealDelete(String idsValue, String versionFlag)
			throws ServiceException {
		idsValue = GeneralUtil.convertIdsValueToStringByDelete(idsValue, ",");
		dbDAO.deleteAll(GoodsLostPostions.class.getSimpleName(), idsValue, null);
		return true;
	}

	@Override
	public List<GoodsLostPostions> queryByList(String versionFlag) throws ServiceException {
		return dbDAO.queryByList(GoodsLostPostions.class.getSimpleName(), deleteFlag, versionFlag, null, null, orderBy, null);
	}

	@Override
	public GoodsLostPostions queryByName(String name) {
		
		return dbDAO.executeJPQLForQuerySingle("select c from " + GoodsLostPostions.class.getName() + " c where c.status.deleteFlag = 0 and c.name = ?", GoodsLostPostions.class, name);
	}

}