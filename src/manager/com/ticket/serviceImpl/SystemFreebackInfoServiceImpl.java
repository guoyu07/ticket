package com.ticket.serviceImpl;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.SystemFreebackInfo;
import com.ticket.service.ISystemFreebackInfoService;
import com.ticket.util.DecoderUtil;

/**
 * 系统反馈信息业务接口实现类
 * @ClassName: ISystemFreebackInfoService   
 * @Description: 提供系统反馈信息操作的增删改查   
 * @author HiSay  
 * @date 2016-07-28 13:50:41
 *
 */
public class SystemFreebackInfoServiceImpl extends BaseServiceImpl<SystemFreebackInfo, String> implements ISystemFreebackInfoService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(SystemFreebackInfoServiceImpl.class);

	@Override
	public boolean persist(String name,String phone,String url,String content, String versionFlag) throws ServiceException {
		SystemFreebackInfo systemFreebackInfo = new SystemFreebackInfo();
		systemFreebackInfo.setName(DecoderUtil.UtfDecoder(name));
		systemFreebackInfo.setPhone(DecoderUtil.UtfDecoder(phone));
		systemFreebackInfo.setUrl(DecoderUtil.UtfDecoder(url));
		systemFreebackInfo.setContent(DecoderUtil.UtfDecoder(content));
		CommonEntity status = systemFreebackInfo.getStatus();
		status.setVersionFlag(versionFlag);
		systemFreebackInfo.setStatus(status);
		dbDAO.persist(systemFreebackInfo);
		return true;
	}
	
	@Override
	public boolean merge(String id, String name,String phone,String url,String content, String versionFlag) throws ServiceException {
		SystemFreebackInfo systemFreebackInfo = dbDAO.queryById(this.getTableNameFromEntity(SystemFreebackInfo.class), id);
		systemFreebackInfo.setName(DecoderUtil.UtfDecoder(name));
		systemFreebackInfo.setPhone(DecoderUtil.UtfDecoder(phone));
		systemFreebackInfo.setUrl(DecoderUtil.UtfDecoder(url));
		systemFreebackInfo.setContent(DecoderUtil.UtfDecoder(content));
		CommonEntity status = systemFreebackInfo.getStatus();
		status.setVersionFlag(versionFlag);
		systemFreebackInfo.setStatus(status);
		dbDAO.merge(systemFreebackInfo);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		SystemFreebackInfo systemFreebackInfo = dbDAO.queryById(this.getTableNameFromEntity(SystemFreebackInfo.class), id);
		dbDAO.remove(systemFreebackInfo);
		return true;
	}

}