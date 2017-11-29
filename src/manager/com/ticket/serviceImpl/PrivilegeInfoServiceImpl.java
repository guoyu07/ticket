package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.PrivilegeInfo;
import com.ticket.service.IPrivilegeInfoService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

/**
 * 权限信息业务接口实现类
 * @ClassName: IPrivilegeInfoService   
 * @Description: 提供权限信息操作的增删改查   
 * @author HiSay  
 * @date 2015-10-16 11:37:11
 *
 */
public class PrivilegeInfoServiceImpl extends BaseServiceImpl<PrivilegeInfo, String> implements IPrivilegeInfoService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(PrivilegeInfoServiceImpl.class);
	
	@Override
	public boolean merge(String id, String name,String descript,String includeMethods,String systemModulId, String versionFlag) throws ServiceException {
		PrivilegeInfo privilegeInfo = dbDAO.queryById(this.getTableNameFromEntity(PrivilegeInfo.class), id);
		privilegeInfo.setName(DecoderUtil.UtfDecoder(name));
		privilegeInfo.setDescript(DecoderUtil.UtfDecoder(descript));
		privilegeInfo.setIncludeMethods(DecoderUtil.UtfDecoder(includeMethods));
		privilegeInfo.setSystemModulId(DecoderUtil.UtfDecoder(systemModulId));
		CommonEntity status = privilegeInfo.getStatus();
		status.setVersionFlag(versionFlag);
		privilegeInfo.setStatus(status);
		dbDAO.merge(privilegeInfo);
		return true;
	}

	@Override
	public boolean persist(String name,String descript,String includeMethods,String systemModulId, String versionFlag) throws ServiceException {
		PrivilegeInfo privilegeInfo = new PrivilegeInfo();
		privilegeInfo.setName(DecoderUtil.UtfDecoder(name));
		privilegeInfo.setDescript(DecoderUtil.UtfDecoder(descript));
		privilegeInfo.setIncludeMethods(DecoderUtil.UtfDecoder(includeMethods));
		privilegeInfo.setSystemModulId(DecoderUtil.UtfDecoder(systemModulId));
		CommonEntity status = privilegeInfo.getStatus();
		status.setVersionFlag(versionFlag);
		privilegeInfo.setStatus(status);
		dbDAO.persist(privilegeInfo);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		PrivilegeInfo privilegeInfo = dbDAO.queryById(this.getTableNameFromEntity(PrivilegeInfo.class), id);
		dbDAO.remove(privilegeInfo);
		return true;
	}

	@Override
	public boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException{
		idsValue = GeneralUtil.convertIdsValueToStringByDelete(idsValue, ",");
		dbDAO.deleteAll(PrivilegeInfo.class.getSimpleName(), idsValue, null);
		return true;
	}

	@Override
	public boolean validateNameExists(String name, String versionFlag) throws ServiceException{
		PrivilegeInfo pi = dbDAO.queryByCustom(PrivilegeInfo.class.getSimpleName(), deleteFlag, versionFlag, " and s.name = ?3", new Object[]{name});
		if(pi != null){
			return true;
		}
		return false;
	}

	@Override
	public List<PrivilegeInfo> queryList(String versionFlag)
			throws ServiceException {
		return dbDAO.queryByList(PrivilegeInfo.class.getSimpleName(), deleteFlag, versionFlag, null, null, orderBy, null);
	}

	@Override
	public List<PrivilegeInfo> queryBuSystemModulId(String systemModulId)
			throws ServiceException {
		List<PrivilegeInfo> list = dbDAO.executeJPQLForQueryEntity("select c from " + PrivilegeInfo.class.getName() + " c where c.systemModulId = ?", systemModulId);
		return list;
	}

}