package com.ticket.serviceImpl;


import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.DataBaseRestoreLog;
import com.ticket.service.IDataBaseRestoreLogService;
import com.ticket.util.DecoderUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 数据库还原日志业务接口实现类
 * @ClassName: IDataBaseRestoreLogService   
 * @Description: 提供数据库还原日志操作的增删改查   
 * @author HiSay  
 * @date 2013-09-21 11:55:48
 *
 */
public class DataBaseRestoreLogServiceImpl extends BaseServiceImpl<DataBaseRestoreLog, String> implements IDataBaseRestoreLogService {

	private static final Log log = LogFactory.getLog(DataBaseRestoreLogServiceImpl.class);
	
	@Override
	public boolean merge(String id, String systemUser_id,String content,String name, String versionFlag) throws ServiceException {
		try {
			DataBaseRestoreLog dataBaseRestoreLog = dbDAO.queryById(this.getTableNameFromEntity(DataBaseRestoreLog.class), id);
			dataBaseRestoreLog.setSystemUser_id(systemUser_id) ;
			dataBaseRestoreLog.setContent(DecoderUtil.UtfDecoder(content));
			dataBaseRestoreLog.setName(DecoderUtil.UtfDecoder(name));
			CommonEntity status = dataBaseRestoreLog.getStatus();
			status.setVersionFlag(versionFlag);
			dataBaseRestoreLog.setStatus(status);
			dbDAO.merge(dataBaseRestoreLog);
			return true;
		} catch(Exception e) {
			log.info("修改数据库还原日志出错 :" + e.fillInStackTrace());
			return false;
		}
	}

	@Override
	public boolean persist(String systemUser_id,String content,String name, String versionFlag) throws ServiceException {
		try {
			DataBaseRestoreLog dataBaseRestoreLog = new DataBaseRestoreLog();
			dataBaseRestoreLog.setSystemUser_id(systemUser_id) ;
			dataBaseRestoreLog.setContent(DecoderUtil.UtfDecoder(content));
			dataBaseRestoreLog.setName(DecoderUtil.UtfDecoder(name));
			CommonEntity status = dataBaseRestoreLog.getStatus();
			status.setVersionFlag(versionFlag);
			dataBaseRestoreLog.setStatus(status);
			dbDAO.persist(dataBaseRestoreLog);
			return true;
		} catch(Exception e) {
			log.info("保存数据库还原日志出错 :" + e.fillInStackTrace());
			return false;
		}
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		try {
			DataBaseRestoreLog dataBaseRestoreLog = dbDAO.queryById(this.getTableNameFromEntity(DataBaseRestoreLog.class), id);
			dbDAO.remove(dataBaseRestoreLog);
			return true;
		} catch(Exception e) {
			log.info("删除数据库还原日志出错 :" + e.fillInStackTrace());
			return false;
		}
	}

}