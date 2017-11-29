package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.CityHistorySelect;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.Member;
import com.ticket.service.ICityHistorySelectService;
import com.ticket.util.DecoderUtil;

/**
 * 历史选择城市业务接口实现类
 * @ClassName: ICityHistorySelectService   
 * @Description: 提供历史选择城市操作的增删改查   
 * @author HiSay  
 * @date 2015-12-26 17:40:35
 *
 */
public class CityHistorySelectServiceImpl extends BaseServiceImpl<CityHistorySelect, String> implements ICityHistorySelectService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(CityHistorySelectServiceImpl.class);
	
	@Override
	public boolean merge(String id, String member_id,String city_id, String versionFlag) throws ServiceException {
		CityHistorySelect cityHistorySelect = dbDAO.queryById(this.getTableNameFromEntity(CityHistorySelect.class), id);
		cityHistorySelect.setMember_id(DecoderUtil.UtfDecoder(member_id));
		cityHistorySelect.setCity_id(DecoderUtil.UtfDecoder(city_id));
		CommonEntity status = cityHistorySelect.getStatus();
		status.setVersionFlag(versionFlag);
		cityHistorySelect.setStatus(status);
		dbDAO.merge(cityHistorySelect);
		return true;
	}

	@Override
	public boolean persist(String member_id,String city_id, String versionFlag) throws ServiceException {
		CityHistorySelect cityHistorySelect = new CityHistorySelect();
		cityHistorySelect.setMember_id(DecoderUtil.UtfDecoder(member_id));
		cityHistorySelect.setCity_id(DecoderUtil.UtfDecoder(city_id));
		CommonEntity status = cityHistorySelect.getStatus();
		status.setVersionFlag(versionFlag);
		cityHistorySelect.setStatus(status);
		dbDAO.persist(cityHistorySelect);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		CityHistorySelect cityHistorySelect = dbDAO.queryById(this.getTableNameFromEntity(CityHistorySelect.class), id);
		dbDAO.remove(cityHistorySelect);
		return true;
	}

	@Override
	public boolean validateExist(String memberId, String cityId)
			throws ServiceException {
		CityHistorySelect obj = dbDAO.queryByCustom(CityHistorySelect.class.getSimpleName(), deleteFlag, versionFlag, "and s.member_id =?3 and s.city_id =?4", new Object[]{memberId,cityId });
		if(obj != null){
			return true;
		}
		return false;
	}

	@Override
	public List<CityHistorySelect> queryListByMember(String versionFlag)
			throws ServiceException {
		Member member = (Member) getSession().get(ContextConstants.SCOPE_MEMBER);
		List<CityHistorySelect> list = dbDAO.queryByList(CityHistorySelect.class.getSimpleName(), deleteFlag, versionFlag, "and s.member_id =?3", new Object[]{member.getId()}, orderBy, 5);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<CityHistorySelect> queryByTourist(String touristId)
			throws ServiceException {
		List<CityHistorySelect> cityHistorySelects = dbDAO.executeJPQLForQueryEntity("select c from " + CityHistorySelect.class.getName() + " c where c.member_id = ? order by c.status.createTime desc", touristId);
		return cityHistorySelects;
	}

}