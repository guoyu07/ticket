package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.InfoPosition;
import com.ticket.service.IInfoPositionService;
import com.ticket.util.DecoderUtil;

/**
 * 信息定位业务接口实现类
 * @ClassName: IInfoPositionService   
 * @Description: 提供信息定位操作的增删改查   
 * @author HiSay  
 * @date 2015-10-20 18:13:14
 *
 */
public class InfoPositionServiceImpl extends BaseServiceImpl<InfoPosition, String> implements IInfoPositionService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(InfoPositionServiceImpl.class);
	
	@Override
	public boolean merge(String id, String news_id,String name,String positionAlias,String longitude,String latitude,String floorNumber,String mobile,String url,String className, String versionFlag) throws ServiceException {
		InfoPosition infoPosition = dbDAO.queryById(this.getTableNameFromEntity(InfoPosition.class), id);
		infoPosition.setNews_id(DecoderUtil.UtfDecoder(news_id));
		infoPosition.setLongitude(longitude);
		infoPosition.setLatitude(latitude);
		infoPosition.setName(name);
		infoPosition.setPositionAlias(positionAlias);
		infoPosition.setFloorNumber(floorNumber);
		CommonEntity status = infoPosition.getStatus();
		status.setVersionFlag(versionFlag);
		infoPosition.setStatus(status);
		infoPosition.setMobile(mobile);
		infoPosition.setUrl(url);
		infoPosition.setClassName(className);
		dbDAO.merge(infoPosition);
		return true;
	}

	@Override
	public boolean persist(String news_id,String name,String positionAlias, String longitude,String latitude,String floorNumber,String mobile,String url,String className, String versionFlag) throws ServiceException {
		InfoPosition infoPosition = new InfoPosition();
		infoPosition.setNews_id(DecoderUtil.UtfDecoder(news_id));
		infoPosition.setLongitude(longitude);
		infoPosition.setLatitude(latitude);
		infoPosition.setName(name);
		infoPosition.setPositionAlias(positionAlias);
		infoPosition.setFloorNumber(floorNumber);
		CommonEntity status = infoPosition.getStatus();
		status.setVersionFlag(versionFlag);
		infoPosition.setStatus(status);
		infoPosition.setMobile(mobile);
		infoPosition.setUrl(url);
		infoPosition.setClassName(className);
		dbDAO.persist(infoPosition);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		InfoPosition infoPosition = dbDAO.queryById(this.getTableNameFromEntity(InfoPosition.class), id);
		dbDAO.remove(infoPosition);
		return true;
	}

	@Override
	public InfoPosition queryByNewsId(String newsId, String versionFlag)
			throws ServiceException {
		return dbDAO.queryByCustom(InfoPosition.class.getSimpleName(), deleteFlag, versionFlag, "and s.news_id = ?3", new Object[]{newsId});
	}

	@Override
	public List<InfoPosition> queryListByNewsId(String news_id, String versionFlag)
			throws ServiceException {
		return dbDAO.queryByList(InfoPosition.class.getSimpleName(), deleteFlag, versionFlag, "and s.news_id = ?3", new Object[]{news_id}, orderBy, null);
	}

	@Override
	public InfoPosition queryByName(String locationName, String versionFlag)
			throws ServiceException {
		InfoPosition infoPosition =  dbDAO.queryByCustom(InfoPosition.class.getSimpleName(), deleteFlag, versionFlag, "and s.name = ?3", new Object[]{locationName});
		return infoPosition;
	}

	@Override
	public boolean validateAliasExists(String positionAlias)
			throws ServiceException {
		InfoPosition infoPosition = dbDAO.queryByCustom(InfoPosition.class.getSimpleName(), deleteFlag, versionFlag, " and s.positionAlias =?3", new Object[]{positionAlias});
		if(infoPosition != null){
			return true;
		}
		return false;
	}

	@Override
	public InfoPosition queryByAlias(String alias) throws ServiceException {
		InfoPosition infoPosition = dbDAO.queryByCustom(InfoPosition.class.getSimpleName(), deleteFlag, versionFlag, " and s.positionAlias =?3", new Object[]{alias.replace(" ", "")});
		if(infoPosition != null){
			return infoPosition;
		}
		return null;
	}

	@Override
	public List<InfoPosition> queryByNewsAlias(String versionFlag)
			throws ServiceException {
		return dbDAO.queryByList(InfoPosition.class.getSimpleName(), deleteFlag, versionFlag, " and s.positionAlias like 'news%'", null, orderBy, null);
	}

	@Override
	public List<InfoPosition> queryByNameAndAlias(String name,String alias) throws ServiceException {
		List<InfoPosition> list = dbDAO.executeJPQLForQueryEntity("select c from " + InfoPosition.class.getName() + " c where c.name like ? and c.positionAlias like ?", "%" + name + "%","%" + alias + "%");
		return list;
	}

}