package com.ticket.serviceImpl;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.SystemPlugin;
import com.ticket.service.ISystemPluginService;
import com.ticket.util.DecoderUtil;

/**
 * 系统插件业务接口实现类
 * @ClassName: ISystemPluginService   
 * @Description: 提供系统插件操作的增删改查   
 * @author HiSay  
 * @date 2014-10-15 14:44:02
 *
 */
public class SystemPluginServiceImpl extends BaseServiceImpl<SystemPlugin, String> implements ISystemPluginService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(SystemPluginServiceImpl.class);
	
	@Override
	public boolean merge(String id, String name,String thumbnail,String content, String versionFlag) throws ServiceException {
		SystemPlugin systemPlugin = dbDAO.queryById(this.getTableNameFromEntity(SystemPlugin.class), id);
		systemPlugin.setName(DecoderUtil.UtfDecoder(name));
		systemPlugin.setThumbnail(DecoderUtil.UtfDecoder(thumbnail));
		systemPlugin.setContent(DecoderUtil.UtfDecoder(content));
		CommonEntity status = systemPlugin.getStatus();
		status.setVersionFlag(versionFlag);
		systemPlugin.setStatus(status);
		dbDAO.merge(systemPlugin);
		return true;
	}

	@Override
	public boolean persist(String name,String thumbnail,String content, String versionFlag) throws ServiceException {
		SystemPlugin systemPlugin = new SystemPlugin();
		systemPlugin.setName(DecoderUtil.UtfDecoder(name));
		systemPlugin.setThumbnail(DecoderUtil.UtfDecoder(thumbnail));
		systemPlugin.setContent(DecoderUtil.UtfDecoder(content));
		CommonEntity status = systemPlugin.getStatus();
		status.setVersionFlag(versionFlag);
		systemPlugin.setStatus(status);
		dbDAO.persist(systemPlugin);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		SystemPlugin systemPlugin = dbDAO.queryById(this.getTableNameFromEntity(SystemPlugin.class), id);
		dbDAO.remove(systemPlugin);
		return true;
	}

}