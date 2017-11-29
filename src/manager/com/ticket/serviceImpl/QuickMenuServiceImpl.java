package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.constants.JQueryUploadConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.QuickMenu;
import com.ticket.service.IQuickMenuService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

/**
 * 快捷菜单业务接口实现类
 * @ClassName: IQuickMenuService   
 * @Description: 提供快捷菜单操作的增删改查   
 * @author HiSay  
 * @date 2015-10-31 13:01:07
 *
 */
public class QuickMenuServiceImpl extends BaseServiceImpl<QuickMenu, String> implements IQuickMenuService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(QuickMenuServiceImpl.class);
	
	@Override
	public boolean merge(String id, String name,String url,String isDefaultShow,String defaultShowPosition, String versionFlag, String icon, String quickMenuType_id, Integer orderValue) throws ServiceException {
		QuickMenu quickMenu = dbDAO.queryById(this.getTableNameFromEntity(QuickMenu.class), id);
		quickMenu.setName(DecoderUtil.UtfDecoder(name));
		quickMenu.setUrl(DecoderUtil.UtfDecoder(url));
		quickMenu.setIsDefaultShow(isDefaultShow);
		quickMenu.setDefaultShowPosition(defaultShowPosition);
		CommonEntity status = quickMenu.getStatus();
		status.setVersionFlag(versionFlag);
		status.setOrderValue(orderValue) ;
		quickMenu.setStatus(status);
		quickMenu.setQuickMenuType_id(quickMenuType_id) ;
		dbDAO.merge(quickMenu);
		if(GeneralUtil.isNotNull(icon)){
			this.addAnnex(quickMenu, quickMenu.getId(), icon, JQueryUploadConstants.PICTURE_TYPE_DEFAULT, versionFlag);
		}
		return true;
	}

	@Override
	public boolean persist(String name,String url,String isDefaultShow,String defaultShowPosition, String versionFlag, String icon, String quickMenuType_id, Integer orderValue) throws ServiceException {
		QuickMenu quickMenu = new QuickMenu();
		quickMenu.setName(DecoderUtil.UtfDecoder(name));
		quickMenu.setUrl(DecoderUtil.UtfDecoder(url));
		quickMenu.setIsDefaultShow(isDefaultShow);
		quickMenu.setDefaultShowPosition(defaultShowPosition);
		CommonEntity status = quickMenu.getStatus();
		status.setVersionFlag(versionFlag);
		status.setOrderValue(orderValue) ;
		quickMenu.setStatus(status);
		quickMenu.setQuickMenuType_id(quickMenuType_id) ;
		dbDAO.persist(quickMenu);
		if(GeneralUtil.isNotNull(icon)){
			this.addAnnex(quickMenu, quickMenu.getId(), icon, JQueryUploadConstants.PICTURE_TYPE_DEFAULT, versionFlag);
		}
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		QuickMenu quickMenu = dbDAO.queryById(this.getTableNameFromEntity(QuickMenu.class), id);
		this.deleteAllAnnex(quickMenu, quickMenu.getId(), versionFlag) ;
		dbDAO.remove(quickMenu);
		return true;
	}

	@Override
	public List<QuickMenu> queryByMenuId(String menuType_id, String versionFlag)
			throws ServiceException {
		return dbDAO.queryByList(QuickMenu.class.getSimpleName(), deleteFlag, versionFlag, "and s.quickMenuType_id =?3", new Object[]{menuType_id}, orderBy, null);
	}

	@Override
	public List<QuickMenu> queryQuickMenuListByVisitor(String defaultShowPosition,String versionFlag) {
		return dbDAO.queryByList(QuickMenu.class.getSimpleName(), deleteFlag, versionFlag, "and s.isDefaultShow = '1' and s.defaultShowPosition like ?3", new Object[]{"%"+defaultShowPosition+"%"}, orderBy, 7);
	}

	@Override
	public List<QuickMenu> queryListByDefaultShow(String versionFlag) throws ServiceException {
		return dbDAO.queryByList(QuickMenu.class.getSimpleName(), deleteFlag, versionFlag, "and s.isDefaultShow ='1'", null, orderBy, null);
	}

	@Override
	public boolean batchRealDelete(String idsValue, String versionFlag)
			throws ServiceException {
		idsValue = GeneralUtil.convertIdsValueToStringByDelete(idsValue, ",");
		dbDAO.deleteAll(QuickMenu.class.getSimpleName(), idsValue, null);
		return true;
	}

	@Override
	public List<QuickMenu> queryList(String versionFlag) throws ServiceException {
		List<QuickMenu> list = dbDAO.queryByList(QuickMenu.class.getSimpleName(), deleteFlag, versionFlag, null, null, orderBy, null);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<QuickMenu> queryByIdAndPosition(String menuType_id,
			String position, String versionFlag) throws ServiceException {
		List<QuickMenu> list = dbDAO.queryByList(QuickMenu.class.getSimpleName(), deleteFlag, versionFlag, "and s.quickMenuType_id =?3 and (defaultShowPosition not like ?4 or defaultShowPosition is null)", new Object[]{menuType_id,"%"+position+"%"}, orderBy, null);
		if(list != null&& !list.isEmpty()){
			return list;
		}
		return null;
	}

}