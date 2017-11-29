package com.ticket.serviceImpl;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.SystemVersion;
import com.ticket.service.ISystemVersionService;
import com.ticket.util.DecoderUtil;

/**
 * 系统版本业务接口实现类
 * @ClassName: ISystemVersionService   
 * @Description: 提供系统版本操作的增删改查   
 * @author HiSay  
 * @date 2014-10-15 14:41:02
 *
 */
public class SystemVersionServiceImpl extends BaseServiceImpl<SystemVersion, String> implements ISystemVersionService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(SystemVersionServiceImpl.class);
	
	@Override
	public boolean merge(String id, String version,String content, String versionFlag) throws ServiceException {
		SystemVersion systemVersion = dbDAO.queryById(this.getTableNameFromEntity(SystemVersion.class), id);
		systemVersion.setVersion(DecoderUtil.UtfDecoder(version));
		systemVersion.setContent(DecoderUtil.UtfDecoder(content));
		CommonEntity status = systemVersion.getStatus();
		status.setVersionFlag(versionFlag);
		systemVersion.setStatus(status);
		dbDAO.merge(systemVersion);
		return true;
	}

	@Override
	public boolean persist(String version,String content, String versionFlag) throws ServiceException {
		SystemVersion systemVersion = new SystemVersion();
		systemVersion.setVersion(DecoderUtil.UtfDecoder(version));
		systemVersion.setContent(DecoderUtil.UtfDecoder(content));
		CommonEntity status = systemVersion.getStatus();
		status.setVersionFlag(versionFlag);
		systemVersion.setStatus(status);
		dbDAO.persist(systemVersion);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		SystemVersion systemVersion = dbDAO.queryById(this.getTableNameFromEntity(SystemVersion.class), id);
		dbDAO.remove(systemVersion);
		return true;
	}

}