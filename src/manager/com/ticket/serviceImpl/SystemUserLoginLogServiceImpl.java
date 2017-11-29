package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.SystemUserLoginLog;
import com.ticket.service.ISystemUserLoginLogService;
import com.ticket.util.DecoderUtil;

/**
 * 系统管理员登陆日志业务接口实现类
 * @ClassName: ISystemUserLoginLogService   
 * @Description: 提供系统管理员登陆日志操作的增删改查   
 * @author HiSay  
 * @date 2015-01-03 10:42:16
 *
 */
public class SystemUserLoginLogServiceImpl extends BaseServiceImpl<SystemUserLoginLog, String> implements ISystemUserLoginLogService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(SystemUserLoginLogServiceImpl.class);
	
	@Override
	public boolean merge(String id, String ip,String systemUser_id, String versionFlag) throws ServiceException {
		SystemUserLoginLog systemUserLoginLog = dbDAO.queryById(this.getTableNameFromEntity(SystemUserLoginLog.class), id);
		systemUserLoginLog.setIp(DecoderUtil.UtfDecoder(ip));
		systemUserLoginLog.setSystemUser_id(DecoderUtil.UtfDecoder(systemUser_id));
		CommonEntity status = systemUserLoginLog.getStatus();
		status.setVersionFlag(versionFlag);
		systemUserLoginLog.setStatus(status);
		dbDAO.merge(systemUserLoginLog);
		return true;
	}

	@Override
	public boolean persist(String ip,String systemUser_id, String versionFlag) throws ServiceException {
		SystemUserLoginLog systemUserLoginLog = new SystemUserLoginLog();
		systemUserLoginLog.setIp(DecoderUtil.UtfDecoder(ip));
		systemUserLoginLog.setSystemUser_id(DecoderUtil.UtfDecoder(systemUser_id));
		CommonEntity status = systemUserLoginLog.getStatus();
		status.setVersionFlag(versionFlag);
		systemUserLoginLog.setStatus(status);
		dbDAO.persist(systemUserLoginLog);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		SystemUserLoginLog systemUserLoginLog = dbDAO.queryById(this.getTableNameFromEntity(SystemUserLoginLog.class), id);
		dbDAO.remove(systemUserLoginLog);
		return true;
	}

	@Override
	public SystemUserLoginLog queryBySystemUser(String systemUserId)
			throws ServiceException {
		List<SystemUserLoginLog> loginLogList = dbDAO.queryByList(SystemUserLoginLog.class.getSimpleName(), deleteFlag, versionFlag, 
				"and s.systemUser_id=?3", new Object[]{systemUserId}, orderBy, 2) ;
		if(!loginLogList.isEmpty() && loginLogList.size() > 1) {
			return loginLogList.get(1);
		} 
		return null;
	}

}