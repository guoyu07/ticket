package com.ticket.serviceImpl;


import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.SystemUpdateLog;
import com.ticket.service.ISystemUpdateLogService;
import com.ticket.util.DecoderUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 系统代码升级日志业务接口实现类
 * @ClassName: ISystemUpdateLogService   
 * @Description: 提供系统代码升级日志操作的增删改查   
 * @author HiSay  
 * @date 2013-09-21 09:16:57
 *
 */
public class SystemUpdateLogServiceImpl extends BaseServiceImpl<SystemUpdateLog, Long> implements ISystemUpdateLogService {

	private static final Log log = LogFactory.getLog(SystemUpdateLogServiceImpl.class);
	
	@Override
	public boolean merge(Long id, String oldVersion,String newVersion,String updateContent, String versionFlag) throws ServiceException {
		try {
			SystemUpdateLog systemUpdateLog = dbDAO.queryById(this.getTableNameFromEntity(SystemUpdateLog.class), id);
			systemUpdateLog.setOldVersion(DecoderUtil.UtfDecoder(oldVersion));
			systemUpdateLog.setNewVersion(DecoderUtil.UtfDecoder(newVersion));
			systemUpdateLog.setUpdateContent(DecoderUtil.UtfDecoder(updateContent));
			CommonEntity status = systemUpdateLog.getStatus();
			status.setVersionFlag(versionFlag);
			systemUpdateLog.setStatus(status);
			dbDAO.merge(systemUpdateLog);
			return true;
		} catch(Exception e) {
			log.info("修改系统代码升级日志出错 :" + e.fillInStackTrace());
			return false;
		}
	}

	@Override
	public boolean persist(String oldVersion,String newVersion,String updateContent, String versionFlag) throws ServiceException {
		try {
			SystemUpdateLog systemUpdateLog = new SystemUpdateLog();
			systemUpdateLog.setOldVersion(DecoderUtil.UtfDecoder(oldVersion));
			systemUpdateLog.setNewVersion(DecoderUtil.UtfDecoder(newVersion));
			systemUpdateLog.setUpdateContent(DecoderUtil.UtfDecoder(updateContent));
			CommonEntity status = systemUpdateLog.getStatus();
			status.setVersionFlag(versionFlag);
			systemUpdateLog.setStatus(status);
			dbDAO.persist(systemUpdateLog);
			return true;
		} catch(Exception e) {
			log.info("保存系统代码升级日志出错 :" + e.fillInStackTrace());
			return false;
		}
	}

	@Override
	public boolean remove(Long id) throws ServiceException {
		try {
			SystemUpdateLog systemUpdateLog = dbDAO.queryById(this.getTableNameFromEntity(SystemUpdateLog.class), id);
			dbDAO.remove(systemUpdateLog);
			return true;
		} catch(Exception e) {
			log.info("删除系统代码升级日志出错 :" + e.fillInStackTrace());
			return false;
		}
	}

}