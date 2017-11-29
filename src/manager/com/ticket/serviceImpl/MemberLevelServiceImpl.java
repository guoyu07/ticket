package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.MemberLevel;
import com.ticket.service.IMemberLevelService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

/**
 * 会员等级业务接口实现类
 * @ClassName: IMemberLevelService   
 * @Description: 提供会员等级操作的增删改查   
 * @author HiSay  
 * @date 2015-10-14 16:38:05
 *
 */
public class MemberLevelServiceImpl extends BaseServiceImpl<MemberLevel, String> implements IMemberLevelService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(MemberLevelServiceImpl.class);
	
	@Override
	public boolean merge(String id, String name,String needScore,String descript, String versionFlag) throws ServiceException {
		MemberLevel memberLevel = dbDAO.queryById(this.getTableNameFromEntity(MemberLevel.class), id);
		memberLevel.setName(DecoderUtil.UtfDecoder(name));
		memberLevel.setNeedScore(DecoderUtil.UtfDecoder(needScore));
		memberLevel.setDescript(DecoderUtil.UtfDecoder(descript));
		CommonEntity status = memberLevel.getStatus();
		status.setVersionFlag(versionFlag);
		memberLevel.setStatus(status);
		dbDAO.merge(memberLevel);
		return true;
	}

	@Override
	public boolean persist(String name,String needScore,String descript, String versionFlag) throws ServiceException {
		MemberLevel memberLevel = new MemberLevel();
		memberLevel.setName(DecoderUtil.UtfDecoder(name));
		memberLevel.setNeedScore(DecoderUtil.UtfDecoder(needScore));
		memberLevel.setDescript(DecoderUtil.UtfDecoder(descript));
		CommonEntity status = memberLevel.getStatus();
		status.setVersionFlag(versionFlag);
		memberLevel.setStatus(status);
		dbDAO.persist(memberLevel);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		MemberLevel memberLevel = dbDAO.queryById(this.getTableNameFromEntity(MemberLevel.class), id);
		dbDAO.remove(memberLevel);
		return true;
	}

	@Override
	public List<MemberLevel> queryList(String versionFlag) throws ServiceException {
		List<MemberLevel> list = dbDAO.queryByList(MemberLevel.class.getSimpleName(), deleteFlag, versionFlag, null, null, orderBy, null);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException {
		idsValue = GeneralUtil.convertIdsValueToStringByDelete(idsValue, ",");
		dbDAO.deleteAll(MemberLevel.class.getSimpleName(), idsValue, null);
		return true;
	}

}