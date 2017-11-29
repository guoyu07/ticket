package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.FriendlyLink;
import com.ticket.pojo.Pagination;
import com.ticket.service.IFriendlyLinkService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.PaginationContext;

/**
 * 友情链接业务接口实现类
 * @ClassName: IFriendlyLinkService   
 * @Description: 提供友情链接操作的增删改查   
 * @author HiSay  
 * @date 2015-11-20 10:43:11
 *
 */
public class FriendlyLinkServiceImpl extends BaseServiceImpl<FriendlyLink, String> implements IFriendlyLinkService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(FriendlyLinkServiceImpl.class);
	
	@Override
	public boolean merge(String id, String businessInfo_id,String name,String url, String versionFlag) throws ServiceException {
		FriendlyLink friendlyLink = dbDAO.queryById(this.getTableNameFromEntity(FriendlyLink.class), id);
		friendlyLink.setBusinessInfo_id(DecoderUtil.UtfDecoder(businessInfo_id));
		friendlyLink.setName(DecoderUtil.UtfDecoder(name));
		friendlyLink.setUrl(DecoderUtil.UtfDecoder(url));
		CommonEntity status = friendlyLink.getStatus();
		status.setVersionFlag(versionFlag);
		friendlyLink.setStatus(status);
		dbDAO.merge(friendlyLink);
		return true;
	}

	@Override
	public boolean persist(String businessInfo_id,String name,String url, String versionFlag) throws ServiceException {
		FriendlyLink friendlyLink = new FriendlyLink();
		friendlyLink.setBusinessInfo_id(DecoderUtil.UtfDecoder(businessInfo_id));
		friendlyLink.setName(DecoderUtil.UtfDecoder(name));
		friendlyLink.setUrl(DecoderUtil.UtfDecoder(url));
		CommonEntity status = friendlyLink.getStatus();
		status.setVersionFlag(versionFlag);
		friendlyLink.setStatus(status);
		dbDAO.persist(friendlyLink);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		FriendlyLink friendlyLink = dbDAO.queryById(this.getTableNameFromEntity(FriendlyLink.class), id);
		dbDAO.remove(friendlyLink);
		return true;
	}

	@Override
	public Pagination queryEntityByBusinessInfoId(String businessInfoId, int pageSize, String versionFlag)
			throws ServiceException {
		return dbDAO.queryByPageModule(this.getTableNameFromEntity(FriendlyLink.class), deleteFlag, versionFlag, "and s.businessInfo_id =?3", new Object[]{businessInfoId},orderBy, PaginationContext.getOffset(), pageSize);
	}

	@Override
	public List<FriendlyLink> queryList(String buinessInfoId, String versionFlag) throws ServiceException {
		List<FriendlyLink> list = dbDAO.queryByList(FriendlyLink.class.getSimpleName(), deleteFlag, versionFlag, "and s.businessInfo_id =?3", new Object[]{buinessInfoId}, orderBy, null);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}

	
}