package com.ticket.serviceImpl;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.SystemUserOperation;
import com.ticket.service.ISystemUserOperationService;
import com.ticket.util.DecoderUtil;

/**
 * 管理员操作日志业务接口实现类
 * @ClassName: ISystemUserOperationService   
 * @Description: 提供管理员操作日志操作的增删改查   
 * @author HiSay  
 * @date 2014-10-15 13:12:06
 *
 */
public class SystemUserOperationServiceImpl extends BaseServiceImpl<SystemUserOperation, String> implements ISystemUserOperationService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(SystemUserOperationServiceImpl.class);
	
	@Override
	public boolean merge(String id, String content,String ip,String systemUser_id, String versionFlag) throws ServiceException {
		SystemUserOperation systemUserOperation = dbDAO.queryById(this.getTableNameFromEntity(SystemUserOperation.class), id);
		systemUserOperation.setContent(DecoderUtil.UtfDecoder(content));
		systemUserOperation.setIp(DecoderUtil.UtfDecoder(ip));
		systemUserOperation.setSystemUser_id(DecoderUtil.UtfDecoder(systemUser_id));
		CommonEntity status = systemUserOperation.getStatus();
		status.setVersionFlag(versionFlag);
		systemUserOperation.setStatus(status);
		dbDAO.merge(systemUserOperation);
		return true;
	}

	@Override
	public boolean persist(String content,String ip,String systemUser_id, String versionFlag) throws ServiceException {
		SystemUserOperation systemUserOperation = new SystemUserOperation();
		systemUserOperation.setContent(DecoderUtil.UtfDecoder(content));
		systemUserOperation.setIp(DecoderUtil.UtfDecoder(ip));
		systemUserOperation.setSystemUser_id(DecoderUtil.UtfDecoder(systemUser_id));
		CommonEntity status = systemUserOperation.getStatus();
		status.setVersionFlag(versionFlag);
		systemUserOperation.setStatus(status);
		dbDAO.persist(systemUserOperation);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		SystemUserOperation systemUserOperation = dbDAO.queryById(this.getTableNameFromEntity(SystemUserOperation.class), id);
		dbDAO.remove(systemUserOperation);
		return true;
	}

}