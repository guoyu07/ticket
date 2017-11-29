package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.AdvertType;
import com.ticket.pojo.CommonEntity;
import com.ticket.service.IAdvertTypeService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

/**
 * 广告类型业务接口实现类
 * @ClassName: IAdvertTypeService   
 * @Description: 提供广告类型操作的增删改查   
 * @author HiSay  
 * @date 2015-10-27 10:29:57
 *
 */
public class AdvertTypeServiceImpl extends BaseServiceImpl<AdvertType, String> implements IAdvertTypeService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(AdvertTypeServiceImpl.class);
	
	@Override
	public boolean merge(String id, String name,String descript, String versionFlag) throws ServiceException {
		AdvertType advertType = dbDAO.queryById(this.getTableNameFromEntity(AdvertType.class), id);
		advertType.setName(DecoderUtil.UtfDecoder(name));
		advertType.setDescript(DecoderUtil.UtfDecoder(descript));
		CommonEntity status = advertType.getStatus();
		status.setVersionFlag(versionFlag);
		advertType.setStatus(status);
		dbDAO.merge(advertType);
		return true;
	}

	@Override
	public boolean persist(String name,String descript, String versionFlag) throws ServiceException {
		AdvertType advertType = new AdvertType();
		advertType.setName(DecoderUtil.UtfDecoder(name));
		advertType.setDescript(DecoderUtil.UtfDecoder(descript));
		CommonEntity status = advertType.getStatus();
		status.setVersionFlag(versionFlag);
		advertType.setStatus(status);
		dbDAO.persist(advertType);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		AdvertType advertType = dbDAO.queryById(this.getTableNameFromEntity(AdvertType.class), id);
		dbDAO.remove(advertType);
		return true;
	}
	
	@Override
	public boolean batchRealDelete( String idsValue,String versionFlag)
	throws ServiceException {
		idsValue = GeneralUtil.convertIdsValueToStringByDelete(idsValue, ",");
		dbDAO.deleteAll(AdvertType.class.getSimpleName(), idsValue, null);
		return true;
	}

	@Override
	public List<AdvertType> queryByList(String versionFlag) throws ServiceException {
		return dbDAO.queryByList(AdvertType.class.getSimpleName(), deleteFlag, versionFlag, null, null, orderBy, null);
	}

	@Override
	public AdvertType queryByName(String name, String versionFlag)
			throws ServiceException {
		return dbDAO.queryByCustom(AdvertType.class.getSimpleName(), deleteFlag, versionFlag, "and s.name=?3", new Object[]{name}) ;
	}

}