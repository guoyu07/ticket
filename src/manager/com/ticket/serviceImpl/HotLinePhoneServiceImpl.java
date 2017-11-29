package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.HotLinePhone;
import com.ticket.pojo.Pagination;
import com.ticket.service.IHotLinePhoneService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.PaginationContext;

/**
 * 热线电话业务接口实现类
 * @ClassName: IHotLinePhoneService   
 * @Description: 提供热线电话操作的增删改查   
 * @author HiSay  
 * @date 2015-11-17 17:34:17
 *
 */
public class HotLinePhoneServiceImpl extends BaseServiceImpl<HotLinePhone, String> implements IHotLinePhoneService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(HotLinePhoneServiceImpl.class);
	
	@Override
	public boolean merge(String id, String name,String phone,String oneKeyCall,Integer orderValue, String versionFlag) throws ServiceException {
		HotLinePhone hotLinePhone = dbDAO.queryById(this.getTableNameFromEntity(HotLinePhone.class), id);
		hotLinePhone.setName(DecoderUtil.UtfDecoder(name));
		hotLinePhone.setPhone(DecoderUtil.UtfDecoder(phone));
		hotLinePhone.setOneKeyCall(DecoderUtil.UtfDecoder(oneKeyCall));
		CommonEntity status = hotLinePhone.getStatus();
		status.setOrderValue(orderValue);
		status.setVersionFlag(versionFlag);
		hotLinePhone.setStatus(status);
		dbDAO.merge(hotLinePhone);
		return true;
	}

	@Override
	public boolean persist(String name,String phone,String oneKeyCall,Integer orderValue, String versionFlag) throws ServiceException {
		HotLinePhone hotLinePhone = new HotLinePhone();
		hotLinePhone.setName(DecoderUtil.UtfDecoder(name));
		hotLinePhone.setPhone(DecoderUtil.UtfDecoder(phone));
		hotLinePhone.setOneKeyCall(DecoderUtil.UtfDecoder(oneKeyCall));
		CommonEntity status = hotLinePhone.getStatus();
		status.setOrderValue(orderValue);
		status.setVersionFlag(versionFlag);
		hotLinePhone.setStatus(status);
		dbDAO.persist(hotLinePhone);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		HotLinePhone hotLinePhone = dbDAO.queryById(this.getTableNameFromEntity(HotLinePhone.class), id);
		dbDAO.remove(hotLinePhone);
		return true;
	}

	@Override
	public boolean batchRealDelete(String idsValue, String versionFlag)
			throws ServiceException {
		idsValue = GeneralUtil.convertIdsValueToStringByDelete(idsValue, ",");
		dbDAO.deleteAll(HotLinePhone.class.getSimpleName(), idsValue, null);
		return true;
	}

	@Override
	public List<HotLinePhone> queryList(String versionFlag)
			throws ServiceException {
		List<HotLinePhone> list = dbDAO.queryByList(HotLinePhone.class.getSimpleName(), deleteFlag, versionFlag, null, null, orderValueOrderBy, null);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public Pagination queryEntityByAdminAndOrder(String versionFlag,
			int pageSize) throws ServiceException {
		return dbDAO.queryByPageModule(HotLinePhone.class.getSimpleName(), deleteFlag, versionFlag, null, null, orderValueOrderBy, PaginationContext.getOffset(), pageSize);
	}

}