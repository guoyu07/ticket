package com.ticket.serviceImpl;


import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.AirportBusinessType;
import com.ticket.pojo.CommonEntity;
import com.ticket.service.IAirportBusinessTypeService;
import com.ticket.util.AirportBusinessTypeUtil;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

/**
 * 机场商业类别业务接口实现类
 * @ClassName: IAirportBusinessTypeService   
 * @Description: 提供机场商业类别操作的增删改查   
 * @author HiSay  
 * @date 2015-11-16 13:35:58
 *
 */
public class AirportBusinessTypeServiceImpl extends BaseServiceImpl<AirportBusinessType, String> implements IAirportBusinessTypeService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(AirportBusinessTypeServiceImpl.class);
	
	@Override
	public boolean merge(String id, String name,String parent_id,String introduce,Integer orderValue, String versionFlag) throws ServiceException {
		AirportBusinessType airportBusinessType = dbDAO.queryById(this.getTableNameFromEntity(AirportBusinessType.class), id);
		airportBusinessType.setName(DecoderUtil.UtfDecoder(name));
		if(GeneralUtil.isNull(parent_id)){
			airportBusinessType.setParent_id(null);
		}else{
			airportBusinessType.setParent_id(DecoderUtil.UtfDecoder(parent_id));
		}
		airportBusinessType.setIntroduce(DecoderUtil.UtfDecoder(introduce));
		CommonEntity status = airportBusinessType.getStatus();
		status.setOrderValue(orderValue);
		status.setVersionFlag(versionFlag);
		airportBusinessType.setStatus(status);
		dbDAO.merge(airportBusinessType);
		return true;
	}

	@Override
	public boolean persist(String name,String parent_id,String introduce,Integer orderValue, String versionFlag) throws ServiceException {
		AirportBusinessType airportBusinessType = new AirportBusinessType();
		airportBusinessType.setName(DecoderUtil.UtfDecoder(name));
		if(GeneralUtil.isNull(parent_id)){
			airportBusinessType.setParent_id(null);
		}else{
			airportBusinessType.setParent_id(DecoderUtil.UtfDecoder(parent_id));
		}
		airportBusinessType.setIntroduce(DecoderUtil.UtfDecoder(introduce));
		CommonEntity status = airportBusinessType.getStatus();
		status.setOrderValue(orderValue);
		status.setVersionFlag(versionFlag);
		airportBusinessType.setStatus(status);
		dbDAO.persist(airportBusinessType);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		AirportBusinessType airportBusinessType = dbDAO.queryById(this.getTableNameFromEntity(AirportBusinessType.class), id);
		dbDAO.remove(airportBusinessType);
		return true;
	}

	@Override
	public String queryAirportBusinessTypeSelectOptionHtml(String parent_id, String versionFlag)
	throws ServiceException {
		List<AirportBusinessType> firstAirportBusinessTypeList = this.queryFirstAirportBusinessTypeList(versionFlag);
		String shopHtml = null;
		if (firstAirportBusinessTypeList != null && !firstAirportBusinessTypeList.isEmpty()) {
			shopHtml = AirportBusinessTypeUtil.getSelectOptionHTML(firstAirportBusinessTypeList, 0, parent_id);
		}
		if (GeneralUtil.isNotNull(shopHtml)) {
			return shopHtml;
		}
		return null;
	}
	
	@Override
	public List<AirportBusinessType> queryFirstAirportBusinessTypeList(String versionFlag) throws ServiceException {
		List<AirportBusinessType> firstAirportBusinessTypeList = dbDAO.queryByList(this.getTableNameFromEntity(AirportBusinessType.class), deleteFlag, versionFlag, "and s.parent_id is null", null, orderValueOrderBy, null);
		if(firstAirportBusinessTypeList != null && !firstAirportBusinessTypeList.isEmpty()) {
			return firstAirportBusinessTypeList;
		} else {
			return null;
		}
	}
	

	@Override
	public List<AirportBusinessType> queryChildAirportBusinessTypesByParent(String parent_id)
	throws ServiceException {
		return dbDAO.queryByList(this.getTableNameFromEntity(AirportBusinessType.class), deleteFlag,versionFlag, "and s.parent_id=?3", new Object[]{parent_id}, orderValueOrderBy, null);
	}

	@Override
	public boolean validateHaveChildAirportBusinessTypes(String parent_id)
	throws ServiceException {
		List<AirportBusinessType> childList = this.queryChildAirportBusinessTypesByParent(parent_id);
		if(childList != null && !childList.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean setAirportBusinessTypeIsDefaultShow(String entityName, String id) throws ServiceException {
		//先取消之前所有的默认
		dbDAO.executeSQL("update " + AirportBusinessType.class.getSimpleName() + " s set s.isDefaultShow=?1", new Object[]{ContextConstants.STATUS_OF_ZERO});

		AirportBusinessType airportBusinessType = this.queryById(entityName, id);
		if(airportBusinessType.getIsDefaultShow().intValue() == ContextConstants.STATUS_OF_ZERO) {
			airportBusinessType.setIsDefaultShow(ContextConstants.STATUS_OF_ONE);
		} else {
			airportBusinessType.setIsDefaultShow(ContextConstants.STATUS_OF_ZERO);
		}
		this.merge(airportBusinessType);
		return true;
	}

	@Override
	public List<AirportBusinessType> queryTopList(String versionFlag) throws ServiceException {
		List<AirportBusinessType> list = dbDAO.queryByList(AirportBusinessType.class.getSimpleName(), deleteFlag, versionFlag, "and s.parent_id is null", null, orderValueOrderBy, null);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<AirportBusinessType> querySecondList(String parent_id, String versionFlag) throws ServiceException {
		List<AirportBusinessType> list = dbDAO.queryByList(AirportBusinessType.class.getSimpleName(), deleteFlag, versionFlag, "and s.parent_id =?3", new Object[]{parent_id}, orderValueOrderBy, null);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public boolean batchRealDelete(String idsValue, String versionFlag)
			throws ServiceException {
		idsValue = GeneralUtil.convertIdsValueToStringByDelete(idsValue, ",");
		dbDAO.deleteAll(AirportBusinessType.class.getSimpleName(), idsValue, null);
		return true;
	}

	@Override
	public String parseClassIds(String classId) throws ServiceException{
		AirportBusinessType newsClass = queryById(AirportBusinessType.class.getSimpleName(), classId);
		if (newsClass != null) {
			StringBuffer result = new StringBuffer();
			List<AirportBusinessType> childs = queryChildAirportBusinessTypesByParent(classId);
			/** 如果是子栏目 */
			if (childs.size() <= 0) {
				result.append("'");
				result.append(newsClass.getId());
				result.append("'");
				return result.toString();
			}
			/** 如果是父栏目 */
			result.append("'");
			result.append(newsClass.getId());
			result.append("'");
			result.append(",");
			getChildsIds(childs, result);
			return result.toString().substring(0,
					result.toString().lastIndexOf(","));
		}
		return null;
	}

	private void getChildsIds(List<AirportBusinessType> sets,
			StringBuffer result) throws ServiceException{
		Iterator<AirportBusinessType> iter = sets.iterator();
		AirportBusinessType n = null;
		while (iter.hasNext()) {
			n = iter.next();
			result.append("'");
			result.append(n.getId());
			result.append("'");
			result.append(",");
			List<AirportBusinessType> childs = queryChildAirportBusinessTypesByParent(n.getId());
			if (childs.size() > 0) {
				getChildsIds(childs, result);
			}
		}
	}

	@Override
	public List<AirportBusinessType> queryChildList(String versionFlag)
			throws ServiceException {
		return dbDAO.queryByList(AirportBusinessType.class.getSimpleName(), deleteFlag, versionFlag, "and s.parent_id is not null", null, orderBy, null);
	}

	@Override
	public AirportBusinessType queryByName(String name) {
		return dbDAO.executeJPQLForQuerySingle("select c from " + AirportBusinessType.class.getName() + " c where c.name = ? and c.status.deleteFlag = 0", AirportBusinessType.class, name);
	}
}