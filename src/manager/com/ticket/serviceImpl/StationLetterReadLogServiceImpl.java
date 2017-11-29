package com.ticket.serviceImpl;


import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.StationLetterReadLog;
import com.ticket.service.IStationLetterReadLogService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.PaginationContext;

/**
 * 站内信阅读日志业务接口实现类
 * @ClassName: IStationLetterReadLogService   
 * @Description: 提供站内信阅读日志操作的增删改查   
 * @author HiSay  
 * @date 2016-05-09 14:18:24
 *
 */
public class StationLetterReadLogServiceImpl extends BaseServiceImpl<StationLetterReadLog, String> implements IStationLetterReadLogService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(StationLetterReadLogServiceImpl.class);
	
	@Override
	public boolean merge(String id, String object_id,Integer isRead,String letter_id,Date readDate, String versionFlag) throws ServiceException {
		StationLetterReadLog stationLetterReadLog = dbDAO.queryById(this.getTableNameFromEntity(StationLetterReadLog.class), id);
		stationLetterReadLog.setObject_id(DecoderUtil.UtfDecoder(object_id));
		stationLetterReadLog.setIsRead(isRead);
		stationLetterReadLog.setLetter_id(DecoderUtil.UtfDecoder(letter_id));
		stationLetterReadLog.setReadDate(readDate);
		CommonEntity status = stationLetterReadLog.getStatus();
		status.setVersionFlag(versionFlag);
		stationLetterReadLog.setStatus(status);
		dbDAO.merge(stationLetterReadLog);
		return true;
	}

	@Override
	public boolean persist(String object_id,Integer isRead,String letter_id,Date readDate, String versionFlag) throws ServiceException {
		StationLetterReadLog stationLetterReadLog = new StationLetterReadLog();
		stationLetterReadLog.setObject_id(DecoderUtil.UtfDecoder(object_id));
		stationLetterReadLog.setIsRead(isRead);
		stationLetterReadLog.setLetter_id(DecoderUtil.UtfDecoder(letter_id));
		stationLetterReadLog.setReadDate(readDate);
		CommonEntity status = stationLetterReadLog.getStatus();
		status.setVersionFlag(versionFlag);
		stationLetterReadLog.setStatus(status);
		dbDAO.persist(stationLetterReadLog);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		StationLetterReadLog stationLetterReadLog = dbDAO.queryById(this.getTableNameFromEntity(StationLetterReadLog.class), id);
		dbDAO.remove(stationLetterReadLog);
		return true;
	}

	@Override
	public Pagination queryByEmployeeOrCustomer(String object_id,
			String versionFlag, int pageSize) throws ServiceException {
		return dbDAO.queryByPageModule(StationLetterReadLog.class.getSimpleName(), deleteFlag, versionFlag, "and s.object_id =?3", new Object[]{object_id}, orderBy, PaginationContext.getOffset(), pageSize);
	}

	@Override
	public boolean setLetterRead(String id, String versionFlag)
			throws ServiceException {
		try {
			StationLetterReadLog stationLetterReadLog = dbDAO.queryById(StationLetterReadLog.class.getSimpleName(), id);
			stationLetterReadLog.setIsRead(1);
			stationLetterReadLog.setReadDate(new Date());
			dbDAO.merge(stationLetterReadLog);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Long countReadCountByLetterId(String letter_id)
			throws ServiceException {
		return dbDAO.getTotalCount(StationLetterReadLog.class.getSimpleName(), deleteFlag, versionFlag,"and s.letter_id = ?3 and s.isRead =1", new Object[]{letter_id});
	}

	@Override
	public Long countAllCountByLetterId(String letter_id)
			throws ServiceException {
		return dbDAO.getTotalCount(StationLetterReadLog.class.getSimpleName(), deleteFlag, versionFlag,"and s.letter_id = ?3", new Object[]{letter_id});
	}

}