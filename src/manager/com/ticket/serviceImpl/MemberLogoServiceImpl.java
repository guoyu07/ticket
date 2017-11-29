package com.ticket.serviceImpl;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.MemberLogo;
import com.ticket.service.IMemberLogoService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

/**
 * 推荐会员头像业务接口实现类
 * @ClassName: IMemberLogoService   
 * @Description: 提供推荐会员头像操作的增删改查   
 * @author HiSay  
 * @date 2016-03-14 10:49:59
 *
 */
public class MemberLogoServiceImpl extends BaseServiceImpl<MemberLogo, String> implements IMemberLogoService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(MemberLogoServiceImpl.class);
	
	@Override
	public boolean merge(String id, String logoUrl, String versionFlag) throws ServiceException {
		MemberLogo memberLogo = dbDAO.queryById(this.getTableNameFromEntity(MemberLogo.class), id);
		memberLogo.setLogoUrl(DecoderUtil.UtfDecoder(logoUrl));
		CommonEntity status = memberLogo.getStatus();
		status.setVersionFlag(versionFlag);
		memberLogo.setStatus(status);
		dbDAO.merge(memberLogo);
		return true;
	}

	@Override
	public boolean persist(String logoUrl, String versionFlag) throws ServiceException {
		MemberLogo memberLogo = new MemberLogo();
		memberLogo.setLogoUrl(DecoderUtil.UtfDecoder(logoUrl));
		CommonEntity status = memberLogo.getStatus();
		status.setVersionFlag(versionFlag);
		memberLogo.setStatus(status);
		dbDAO.persist(memberLogo);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		MemberLogo memberLogo = dbDAO.queryById(this.getTableNameFromEntity(MemberLogo.class), id);
		dbDAO.remove(memberLogo);
		return true;
	}

	@Override
	public boolean batchRealDelete(String idsValue, String versionFlag)
			throws ServiceException {
		idsValue = GeneralUtil.convertIdsValueToStringByDelete(idsValue, ",");
		dbDAO.deleteAll(MemberLogo.class.getSimpleName(),idsValue,null);
		return true;
	}

}