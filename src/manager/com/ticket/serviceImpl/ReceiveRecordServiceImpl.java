package com.ticket.serviceImpl;


import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.ReceiveRecord;
import com.ticket.service.IReceiveRecordService;
import com.ticket.util.DateUtil;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

/**
 * 领取记录业务接口实现类
 * @ClassName: IReceiveRecordService   
 * @Description: 提供领取记录操作的增删改查   
 * @author HiSay  
 * @date 2015-11-23 16:26:07
 *
 */
public class ReceiveRecordServiceImpl extends BaseServiceImpl<ReceiveRecord, String> implements IReceiveRecordService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(ReceiveRecordServiceImpl.class);
	
	@Override
	public boolean merge(String id, String lostGoods_id,String personName,String receiveTime,String certificateType,String receiveCertificateNumber,String phone,String receiveWay,String writeOffPerson,String putOutPerson,String remark,String writeOffTime,String putOutTime, String versionFlag) throws ServiceException {
		ReceiveRecord receiveRecord = dbDAO.queryById(this.getTableNameFromEntity(ReceiveRecord.class), id);
		receiveRecord.setLostGoods_id(DecoderUtil.UtfDecoder(lostGoods_id));
		receiveRecord.setPersonName(DecoderUtil.UtfDecoder(personName));
		receiveRecord.setReceiveTime(DateUtil.parseStringToDate(receiveTime, "yyyy-MM-dd HH:mm"));
		receiveRecord.setCertificateType(DecoderUtil.UtfDecoder(certificateType));
		receiveRecord.setReceiveCertificateNumber(DecoderUtil.UtfDecoder(receiveCertificateNumber));
		receiveRecord.setPhone(DecoderUtil.UtfDecoder(phone));
		receiveRecord.setReceiveWay(DecoderUtil.UtfDecoder(receiveWay));
		receiveRecord.setWriteOffPerson(DecoderUtil.UtfDecoder(writeOffPerson));
		receiveRecord.setPutOutPerson(DecoderUtil.UtfDecoder(putOutPerson));
		receiveRecord.setRemark(DecoderUtil.UtfDecoder(remark));
		receiveRecord.setPutOutTime(DecoderUtil.UtfDecoder(putOutTime));
		receiveRecord.setWriteOffTime(DecoderUtil.UtfDecoder(writeOffTime));
		CommonEntity status = receiveRecord.getStatus();
		status.setVersionFlag(versionFlag);
		receiveRecord.setStatus(status);
		dbDAO.merge(receiveRecord);
		return true;
	}

	@Override
	public boolean persist(String lostGoods_id,String personName,String receiveTime,String certificateType,String receiveCertificateNumber,String phone,String receiveWay,String writeOffPerson,String putOutPerson,String remark,String writeOffTime,String putOutTime, String versionFlag) throws ServiceException {
		ReceiveRecord receiveRecord = new ReceiveRecord();
		receiveRecord.setLostGoods_id(DecoderUtil.UtfDecoder(lostGoods_id));
		receiveRecord.setPersonName(DecoderUtil.UtfDecoder(personName));
		receiveRecord.setReceiveTime(DateUtil.parseStringToDate(receiveTime, "yyyy-MM-dd HH:mm"));
		receiveRecord.setCertificateType(DecoderUtil.UtfDecoder(certificateType));
		receiveRecord.setReceiveCertificateNumber(DecoderUtil.UtfDecoder(receiveCertificateNumber));
		receiveRecord.setPhone(DecoderUtil.UtfDecoder(phone));
		receiveRecord.setReceiveWay(DecoderUtil.UtfDecoder(receiveWay));
		receiveRecord.setWriteOffPerson(DecoderUtil.UtfDecoder(writeOffPerson));
		receiveRecord.setPutOutPerson(DecoderUtil.UtfDecoder(putOutPerson));
		receiveRecord.setRemark(DecoderUtil.UtfDecoder(remark));
		receiveRecord.setPutOutTime(DecoderUtil.UtfDecoder(putOutTime));
		receiveRecord.setWriteOffTime(DecoderUtil.UtfDecoder(writeOffTime));
		CommonEntity status = receiveRecord.getStatus();
		status.setVersionFlag(versionFlag);
		receiveRecord.setStatus(status);
		dbDAO.persist(receiveRecord);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		ReceiveRecord receiveRecord = dbDAO.queryById(this.getTableNameFromEntity(ReceiveRecord.class), id);
		dbDAO.remove(receiveRecord);
		return true;
	}

	@Override
	public boolean batchRealDelete(String idsValue, String versionFlag)
			throws ServiceException {
		idsValue = GeneralUtil.convertIdsValueToStringByDelete(idsValue, ",");
		dbDAO.deleteAll(ReceiveRecord.class.getSimpleName(), idsValue, null);
		return true;
	}

	@Override
	public List<ReceiveRecord> querByTimes(Date startTime, Date endTime)
			throws ServiceException {
		List<ReceiveRecord> list = dbDAO.executeJPQLForQueryEntity("select c from " + ReceiveRecord.class.getName() + " c where c.status.createTime > ? and c.status.createTime < ?",startTime, endTime);
		return list;
	}

	@Override
	public ReceiveRecord queryByGoodsId(String lostGoods_id)
			throws ServiceException {
		ReceiveRecord receiveRecord = (ReceiveRecord)dbDAO.executeJPQLForQuerySingle("select c from " + ReceiveRecord.class.getName() + " c where c.lostGoods_id = ?", lostGoods_id);
		return receiveRecord;
	}

}