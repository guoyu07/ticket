package com.ticket.serviceImpl;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.QuestionnaireSendRecord;
import com.ticket.service.IQuestionnaireSendRecordService;
import com.ticket.util.DecoderUtil;

/**
 * 问卷发放记录表业务接口实现类
 * @ClassName: IQuestionnaireSendRecordService   
 * @Description: 提供问卷发放记录表操作的增删改查   
 * @author HiSay  
 * @date 2016-05-04 16:18:18
 *
 */
public class QuestionnaireSendRecordServiceImpl extends BaseServiceImpl<QuestionnaireSendRecord, String> implements IQuestionnaireSendRecordService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(QuestionnaireSendRecordServiceImpl.class);
	
	@Override
	public boolean merge(String id, String questionnaireId,String employee_id,String object_id,Integer isWrite, String versionFlag) throws ServiceException {
		QuestionnaireSendRecord questionnaireSendRecord = dbDAO.queryById(this.getTableNameFromEntity(QuestionnaireSendRecord.class), id);
		questionnaireSendRecord.setQuestionnaireId(DecoderUtil.UtfDecoder(questionnaireId));
		questionnaireSendRecord.setEmployee_id(DecoderUtil.UtfDecoder(employee_id));
		questionnaireSendRecord.setObject_id(DecoderUtil.UtfDecoder(object_id));
		questionnaireSendRecord.setIsWrite(isWrite);
		CommonEntity status = questionnaireSendRecord.getStatus();
		status.setVersionFlag(versionFlag);
		questionnaireSendRecord.setStatus(status);
		dbDAO.merge(questionnaireSendRecord);
		return true;
	}

	@Override
	public boolean persist(String questionnaireId,String employee_id,String object_id,Integer isWrite, String versionFlag) throws ServiceException {
		QuestionnaireSendRecord questionnaireSendRecord = new QuestionnaireSendRecord();
		questionnaireSendRecord.setQuestionnaireId(DecoderUtil.UtfDecoder(questionnaireId));
		questionnaireSendRecord.setEmployee_id(DecoderUtil.UtfDecoder(employee_id));
		questionnaireSendRecord.setObject_id(DecoderUtil.UtfDecoder(object_id));
		questionnaireSendRecord.setIsWrite(isWrite);
		CommonEntity status = questionnaireSendRecord.getStatus();
		status.setVersionFlag(versionFlag);
		questionnaireSendRecord.setStatus(status);
		dbDAO.persist(questionnaireSendRecord);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		QuestionnaireSendRecord questionnaireSendRecord = dbDAO.queryById(this.getTableNameFromEntity(QuestionnaireSendRecord.class), id);
		dbDAO.remove(questionnaireSendRecord);
		return true;
	}

	@Override
	public QuestionnaireSendRecord queryByObjectId(String id, String versionFlag)
			throws ServiceException {
		return dbDAO.queryByCustom(QuestionnaireSendRecord.class.getSimpleName(), deleteFlag, versionFlag, " and s.object_id =?3", new Object[]{id});
	}

}