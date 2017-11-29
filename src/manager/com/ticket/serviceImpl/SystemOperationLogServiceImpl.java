package com.ticket.serviceImpl;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.SystemOperationLog;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 后台管理员操作日志业务接口实现类
 * @ClassName: ISystemOperationLogService   
 * @Description: 提供后台管理员操作日志操作的增删改查   
 * @author HiSay  
 * @date 2016-03-08 21:01:21
 *
 */
public class SystemOperationLogServiceImpl extends BaseServiceImpl<SystemOperationLog, String> implements ISystemOperationLogService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(SystemOperationLogServiceImpl.class);
	
	@Override
	public boolean persist(String logContent) throws ServiceException {
		
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		persist(logName, logContent, logTime, logIp, versionFlag);
		return true;
	}
	
	@Override
	public boolean merge(String id, String logName,String logContent,String logTime,String logIp, String versionFlag) throws ServiceException {
		SystemOperationLog systemOperationLog = dbDAO.queryById(this.getTableNameFromEntity(SystemOperationLog.class), id);
		systemOperationLog.setLogName(DecoderUtil.UtfDecoder(logName));
		systemOperationLog.setLogContent(DecoderUtil.UtfDecoder(logContent));
		systemOperationLog.setLogTime(logTime);
		systemOperationLog.setLogIp(DecoderUtil.UtfDecoder(logIp));
		CommonEntity status = systemOperationLog.getStatus();
		status.setVersionFlag(versionFlag);
		systemOperationLog.setStatus(status);
		dbDAO.merge(systemOperationLog);
		return true;
	}

	@Override
	public boolean persist(String logName,String logContent,String logTime,String logIp, String versionFlag) throws ServiceException {
		SystemOperationLog systemOperationLog = new SystemOperationLog();
		systemOperationLog.setLogName(DecoderUtil.UtfDecoder(logName));
		systemOperationLog.setLogContent(DecoderUtil.UtfDecoder(logContent));
		systemOperationLog.setLogTime(logTime);
		systemOperationLog.setLogIp(DecoderUtil.UtfDecoder(logIp));
		CommonEntity status = systemOperationLog.getStatus();
		status.setVersionFlag(versionFlag);
		systemOperationLog.setStatus(status);
		dbDAO.persist(systemOperationLog);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		SystemOperationLog systemOperationLog = dbDAO.queryById(this.getTableNameFromEntity(SystemOperationLog.class), id);
		dbDAO.remove(systemOperationLog);
		return true;
	}

}