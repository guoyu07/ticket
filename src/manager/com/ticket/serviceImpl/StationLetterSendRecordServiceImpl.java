package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.StationLetterSendRecord;
import com.ticket.service.IStationLetterSendRecordService;
import com.ticket.util.DecoderUtil;

/**
 * 站内信发送记录业务接口实现类
 * @ClassName: IStationLetterSendRecordService   
 * @Description: 提供站内信发送记录操作的增删改查   
 * @author HiSay  
 * @date 2016-05-10 14:53:45
 *
 */
public class StationLetterSendRecordServiceImpl extends BaseServiceImpl<StationLetterSendRecord, String> implements IStationLetterSendRecordService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(StationLetterSendRecordServiceImpl.class);
	
	@Override
	public boolean merge(String id, String opertator_id,String letter_id,String objectFlag,String object_id,Integer sendCount, String versionFlag) throws ServiceException {
		StationLetterSendRecord stationLetterSendRecord = dbDAO.queryById(this.getTableNameFromEntity(StationLetterSendRecord.class), id);
		stationLetterSendRecord.setOpertator_id(DecoderUtil.UtfDecoder(opertator_id));
		stationLetterSendRecord.setLetter_id(DecoderUtil.UtfDecoder(letter_id));
		stationLetterSendRecord.setObjectFlag(DecoderUtil.UtfDecoder(objectFlag));
		stationLetterSendRecord.setObject_id(DecoderUtil.UtfDecoder(object_id));
		stationLetterSendRecord.setSendCount(sendCount);
		CommonEntity status = stationLetterSendRecord.getStatus();
		status.setVersionFlag(versionFlag);
		stationLetterSendRecord.setStatus(status);
		dbDAO.merge(stationLetterSendRecord);
		return true;
	}

	@Override
	public boolean persist(String opertator_id,String letter_id,String objectFlag,String object_id,Integer sendCount, String versionFlag) throws ServiceException {
		StationLetterSendRecord stationLetterSendRecord = new StationLetterSendRecord();
		stationLetterSendRecord.setOpertator_id(DecoderUtil.UtfDecoder(opertator_id));
		stationLetterSendRecord.setLetter_id(DecoderUtil.UtfDecoder(letter_id));
		stationLetterSendRecord.setObjectFlag(DecoderUtil.UtfDecoder(objectFlag));
		stationLetterSendRecord.setObject_id(DecoderUtil.UtfDecoder(object_id));
		stationLetterSendRecord.setSendCount(sendCount);
		CommonEntity status = stationLetterSendRecord.getStatus();
		status.setVersionFlag(versionFlag);
		stationLetterSendRecord.setStatus(status);
		dbDAO.persist(stationLetterSendRecord);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		StationLetterSendRecord stationLetterSendRecord = dbDAO.queryById(this.getTableNameFromEntity(StationLetterSendRecord.class), id);
		dbDAO.remove(stationLetterSendRecord);
		return true;
	}

	@Override
	public List<StationLetterSendRecord> queryByDept(String id,
			String versionFlag) throws ServiceException {
		return dbDAO.queryByList(StationLetterSendRecord.class.getSimpleName(), deleteFlag, versionFlag, "and s.objectFlag='employee' and s.letter_id = ?3", new Object[]{id}, orderBy, null);
	}

	@Override
	public List<StationLetterSendRecord> queryByChannelType(String id,
			String versionFlag) throws ServiceException {
		return dbDAO.queryByList(StationLetterSendRecord.class.getSimpleName(), deleteFlag, versionFlag, "and s.objectFlag='customer' and s.letter_id =?3", new Object[]{id}, orderBy, null);
	}

}