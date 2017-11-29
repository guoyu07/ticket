package com.ticket.serviceImpl;


import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.DataBaseBackUpsLog;
import com.ticket.service.IDataBaseBackUpsLogService;
import com.ticket.util.DecoderUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 数据库备份日志业务接口实现类
 * @ClassName: IDataBaseBackUpsLogService   
 * @Description: 提供数据库备份日志操作的增删改查   
 * @author HiSay  
 * @date 2013-09-21 11:47:54
 *
 */
public class DataBaseBackUpsLogServiceImpl extends BaseServiceImpl<DataBaseBackUpsLog, String> implements IDataBaseBackUpsLogService {

	private static final Log log = LogFactory.getLog(DataBaseBackUpsLogServiceImpl.class);
	
	@Override
	public boolean merge(String id, String systemUser_id,String sqlPath,String name, String versionFlag) throws ServiceException {
		try {
			DataBaseBackUpsLog dataBaseBackUpsLog = dbDAO.queryById(this.getTableNameFromEntity(DataBaseBackUpsLog.class), id);
			dataBaseBackUpsLog.setSystemUser_id(systemUser_id) ;
			dataBaseBackUpsLog.setSqlPath(DecoderUtil.UtfDecoder(sqlPath));
			dataBaseBackUpsLog.setName(DecoderUtil.UtfDecoder(name));
			CommonEntity status = dataBaseBackUpsLog.getStatus();
			status.setVersionFlag(versionFlag);
			dataBaseBackUpsLog.setStatus(status);
			dbDAO.merge(dataBaseBackUpsLog);
			return true;
		} catch(Exception e) {
			log.info("修改数据库备份日志出错 :" + e.fillInStackTrace());
			return false;
		}
	}

	@Override
	public boolean persist(String systemUser_id,String sqlPath,String name, String versionFlag) throws ServiceException {
		try {
			DataBaseBackUpsLog dataBaseBackUpsLog = new DataBaseBackUpsLog();
			dataBaseBackUpsLog.setSystemUser_id(systemUser_id) ;
			dataBaseBackUpsLog.setSqlPath(DecoderUtil.UtfDecoder(sqlPath));
			dataBaseBackUpsLog.setName(DecoderUtil.UtfDecoder(name));
			CommonEntity status = dataBaseBackUpsLog.getStatus();
			status.setVersionFlag(versionFlag);
			dataBaseBackUpsLog.setStatus(status);
			dbDAO.persist(dataBaseBackUpsLog);
			return true;
		} catch(Exception e) {
			log.info("保存数据库备份日志出错 :" + e.fillInStackTrace());
			return false;
		}
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		try {
			DataBaseBackUpsLog dataBaseBackUpsLog = dbDAO.queryById(this.getTableNameFromEntity(DataBaseBackUpsLog.class), id);
			dbDAO.remove(dataBaseBackUpsLog);
			return true;
		} catch(Exception e) {
			log.info("删除数据库备份日志出错 :" + e.fillInStackTrace());
			return false;
		}
	}

}