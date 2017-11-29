package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.SpecialPersonWithQuickMenu;
import com.ticket.service.ISpecialPersonWithQuickMenuService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

/**
 * 测试用户业务接口实现类
 * @ClassName: ISpecialPersonWithQuickMenuService   
 * @Description: 提供测试用户操作的增删改查   
 * @author HiSay  
 * @date 2015-12-05 09:41:10
 *
 */
public class SpecialPersonWithQuickMenuServiceImpl extends BaseServiceImpl<SpecialPersonWithQuickMenu, String> implements ISpecialPersonWithQuickMenuService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(SpecialPersonWithQuickMenuServiceImpl.class);
	
	@Override
	public boolean merge(String id, String personType,String quickMenu_id, String versionFlag) throws ServiceException {
		SpecialPersonWithQuickMenu specialPersonWithQuickMenu = dbDAO.queryById(this.getTableNameFromEntity(SpecialPersonWithQuickMenu.class), id);
		specialPersonWithQuickMenu.setPersonType(DecoderUtil.UtfDecoder(personType));
		specialPersonWithQuickMenu.setQuickMenu_id(DecoderUtil.UtfDecoder(quickMenu_id));
		CommonEntity status = specialPersonWithQuickMenu.getStatus();
		status.setVersionFlag(versionFlag);
		specialPersonWithQuickMenu.setStatus(status);
		dbDAO.merge(specialPersonWithQuickMenu);
		return true;
	}

	@Override
	public boolean persist(String personType,String quickMenu_id, String versionFlag) throws ServiceException {
		SpecialPersonWithQuickMenu specialPersonWithQuickMenu = new SpecialPersonWithQuickMenu();
		specialPersonWithQuickMenu.setPersonType(DecoderUtil.UtfDecoder(personType));
		specialPersonWithQuickMenu.setQuickMenu_id(DecoderUtil.UtfDecoder(quickMenu_id));
		CommonEntity status = specialPersonWithQuickMenu.getStatus();
		status.setVersionFlag(versionFlag);
		specialPersonWithQuickMenu.setStatus(status);
		dbDAO.persist(specialPersonWithQuickMenu);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		SpecialPersonWithQuickMenu specialPersonWithQuickMenu = dbDAO.queryById(this.getTableNameFromEntity(SpecialPersonWithQuickMenu.class), id);
		dbDAO.remove(specialPersonWithQuickMenu);
		return true;
	}

	@Override
	public boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException {
		idsValue = GeneralUtil.convertIdsValueToStringByDelete(idsValue, ",");
		dbDAO.deleteAll(SpecialPersonWithQuickMenu.class.getSimpleName(), idsValue, null);
		return true;
	}

	@Override
	public List<SpecialPersonWithQuickMenu> queryListByPersonType(String personType, String versionFlag) {
		List<SpecialPersonWithQuickMenu> list =dbDAO.queryByList(SpecialPersonWithQuickMenu.class.getSimpleName(), deleteFlag, versionFlag, "and s.personType =?3", new Object[]{personType.trim()}, orderBy, null);
		return list;
	}

}