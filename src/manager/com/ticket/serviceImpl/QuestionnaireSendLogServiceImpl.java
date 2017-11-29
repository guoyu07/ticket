package com.ticket.serviceImpl;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.QuestionnaireSendLog;
import com.ticket.service.IQuestionnaireSendLogService;
import com.ticket.util.DecoderUtil;

/**
 * 问卷发放日志业务接口实现类
 * @ClassName: IQuestionnaireSendLogService   
 * @Description: 提供问卷发放日志操作的增删改查   
 * @author HiSay  
 * @date 2016-05-05 16:21:51
 *
 */
public class QuestionnaireSendLogServiceImpl extends BaseServiceImpl<QuestionnaireSendLog, String> implements IQuestionnaireSendLogService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(QuestionnaireSendLogServiceImpl.class);
	
	@Override
	public boolean merge(String id, String questionnaireId,String employee_id,Integer sendCount,Integer writeCount, String versionFlag) throws ServiceException {
		QuestionnaireSendLog questionnaireSendLog = dbDAO.queryById(this.getTableNameFromEntity(QuestionnaireSendLog.class), id);
		questionnaireSendLog.setQuestionnaireId(DecoderUtil.UtfDecoder(questionnaireId));
		questionnaireSendLog.setEmployee_id(DecoderUtil.UtfDecoder(employee_id));
		questionnaireSendLog.setSendCount(sendCount);
		questionnaireSendLog.setWriteCount(writeCount);
		CommonEntity status = questionnaireSendLog.getStatus();
		status.setVersionFlag(versionFlag);
		questionnaireSendLog.setStatus(status);
		dbDAO.merge(questionnaireSendLog);
		return true;
	}

	@Override
	public boolean persist(String questionnaireId,String employee_id,Integer sendCount,Integer writeCount, String versionFlag) throws ServiceException {
		QuestionnaireSendLog questionnaireSendLog = new QuestionnaireSendLog();
		questionnaireSendLog.setQuestionnaireId(DecoderUtil.UtfDecoder(questionnaireId));
		questionnaireSendLog.setEmployee_id(DecoderUtil.UtfDecoder(employee_id));
		questionnaireSendLog.setSendCount(sendCount);
		questionnaireSendLog.setWriteCount(writeCount);
		CommonEntity status = questionnaireSendLog.getStatus();
		status.setVersionFlag(versionFlag);
		questionnaireSendLog.setStatus(status);
		dbDAO.persist(questionnaireSendLog);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		QuestionnaireSendLog questionnaireSendLog = dbDAO.queryById(this.getTableNameFromEntity(QuestionnaireSendLog.class), id);
		dbDAO.remove(questionnaireSendLog);
		return true;
	}

	@Override
	public QuestionnaireSendLog queryByQuestionnaireId(String id,
			String versionFlag) throws ServiceException {
		return dbDAO.queryByCustom(QuestionnaireSendLog.class.getSimpleName(), deleteFlag, versionFlag, "and s.questionnaireId =?3", new Object[]{id});
	}

}