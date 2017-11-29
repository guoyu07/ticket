package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.RoleInfo;
import com.ticket.service.IRoleInfoService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

/**
 * 角色信息业务接口实现类
 * @ClassName: IRoleInfoService   
 * @Description: 提供角色信息操作的增删改查   
 * @author HiSay  
 * @date 2015-10-16 11:18:46
 *
 */
public class RoleInfoServiceImpl extends BaseServiceImpl<RoleInfo, String> implements IRoleInfoService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(RoleInfoServiceImpl.class);
	
	@Override
	public boolean merge(String id, String name,String descript, String versionFlag) throws ServiceException {
		RoleInfo roleInfo = dbDAO.queryById(this.getTableNameFromEntity(RoleInfo.class), id);
		roleInfo.setName(DecoderUtil.UtfDecoder(name));
		roleInfo.setDescript(DecoderUtil.UtfDecoder(descript));
		CommonEntity status = roleInfo.getStatus();
		status.setVersionFlag(versionFlag);
		roleInfo.setStatus(status);
		dbDAO.merge(roleInfo);
		return true;
	}

	@Override
	public boolean persist(String name,String descript, String versionFlag) throws ServiceException {
		RoleInfo roleInfo = new RoleInfo();
		roleInfo.setName(DecoderUtil.UtfDecoder(name));
		roleInfo.setDescript(DecoderUtil.UtfDecoder(descript));
		CommonEntity status = roleInfo.getStatus();
		status.setVersionFlag(versionFlag);
		roleInfo.setStatus(status);
		dbDAO.persist(roleInfo);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		RoleInfo roleInfo = dbDAO.queryById(this.getTableNameFromEntity(RoleInfo.class), id);
		dbDAO.remove(roleInfo);
		return true;
	}

	@Override
	public boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException{
		idsValue = GeneralUtil.convertIdsValueToStringByDelete(idsValue, ",");
		dbDAO.deleteAll(RoleInfo.class.getSimpleName(), idsValue, null);
		return true;
	}

	@Override
	public boolean validateNameExists(String name, String versionFlag) throws ServiceException {
		RoleInfo ri = dbDAO.queryByCustom(RoleInfo.class.getSimpleName(), deleteFlag, versionFlag, " and s.name = ?3", new Object[]{name});
		if(ri != null){
			return true;
		}
		return false;
	}

	@Override
	public List<RoleInfo> queryList(String versionFlag) throws ServiceException {
		return dbDAO.queryByList(RoleInfo.class.getSimpleName(), deleteFlag, versionFlag, null, null, orderBy, null);
	}

	@Override
	public RoleInfo findByRoleId(String roleId) throws ServiceException {
		RoleInfo info = (RoleInfo)dbDAO.executeJPQLForQuerySingle("select c from " + RoleInfo.class.getName() + " c where c.id = ?", roleId);
		
		return info;
	}

}