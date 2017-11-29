package com.ticket.serviceImpl;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.SystemConfig;
import com.ticket.service.ISystemConfigService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

/**
 * 系统配置业务接口实现类
 * @ClassName: ISystemConfigService   
 * @Description: 提供系统配置操作的增删改查   
 * @author HiSay  
 * @date 2014-10-11 08:26:42
 *
 */
public class SystemConfigServiceImpl extends BaseServiceImpl<SystemConfig, String> implements ISystemConfigService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(SystemConfigServiceImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean persistOrMerge(SystemConfig config) throws ServiceException {
		SystemConfig systemConfig = null;
		//如果id不为空，则获取已有的实体
		if(GeneralUtil.isNotNull(config.getId())) {
			systemConfig = dbDAO.queryById(SystemConfig.class.getSimpleName(), config.getId());
		}
		//如果实体为null，则直接new一个出来
		if(systemConfig == null) {
			systemConfig = new SystemConfig();
		}
		systemConfig.setName(DecoderUtil.UtfDecoder(config.getName()));
		systemConfig.setKeyword(DecoderUtil.UtfDecoder(config.getKeyword()));
		systemConfig.setDescription(DecoderUtil.UtfDecoder(config.getDescription()));
		systemConfig.setIsAllowSignIn(config.getIsAllowSignIn());
		systemConfig.setIsAllowCreate(config.getIsAllowCreate());
		/*CommonEntity status = systemConfig.getStatus();
		status.setVersionFlag(versionFlag);
		systemConfig.setStatus(status);*/
		systemConfig.setNameFront(DecoderUtil.UtfDecoder(config.getNameFront())) ;
		systemConfig.setAndroidVersion(config.getAndroidVersion());
		systemConfig.setAndroidPath(config.getAndroidPath());
		systemConfig.setIosVersion(config.getIosVersion());
		systemConfig.setIosPath(config.getIosPath());
		dbDAO.merge(systemConfig);
		//更新到application里面
		getApplication().put(ContextConstants.SCOPE_SYSTEM_SETTING, systemConfig);
		return true;
	}

	@Override
	public SystemConfig querySystemConfig() throws ServiceException {
		return dbDAO.queryByCustom(SystemConfig.class.getSimpleName(), deleteFlag, versionFlag, null, null) ;
	}
}