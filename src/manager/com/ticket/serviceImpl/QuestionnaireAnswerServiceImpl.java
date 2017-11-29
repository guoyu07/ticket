package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.QuestionnaireAnswer;
import com.ticket.service.IQuestionnaireAnswerService;
import com.ticket.util.DecoderUtil;

/**
 * 问卷发放记录表业务接口实现类
 * @ClassName: IQuestionnaireAnswerService   
 * @Description: 提供问卷发放记录表操作的增删改查   
 * @author HiSay  
 * @date 2016-05-04 16:31:27
 *
 */
public class QuestionnaireAnswerServiceImpl extends BaseServiceImpl<QuestionnaireAnswer, String> implements IQuestionnaireAnswerService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(QuestionnaireAnswerServiceImpl.class);
	
	@Override
	public boolean merge(String id, String questionnaireId,String object_id,Integer questionNo,String qustionAnswer, String versionFlag) throws ServiceException {
		QuestionnaireAnswer questionnaireAnswer = dbDAO.queryById(this.getTableNameFromEntity(QuestionnaireAnswer.class), id);
		questionnaireAnswer.setQuestionnaireId(DecoderUtil.UtfDecoder(questionnaireId));
		questionnaireAnswer.setObject_id(DecoderUtil.UtfDecoder(object_id));
		questionnaireAnswer.setQuestionNo(questionNo);
		questionnaireAnswer.setQustionAnswer(DecoderUtil.UtfDecoder(qustionAnswer));
		CommonEntity status = questionnaireAnswer.getStatus();
		status.setVersionFlag(versionFlag);
		questionnaireAnswer.setStatus(status);
		dbDAO.merge(questionnaireAnswer);
		return true;
	}

	@Override
	public boolean persist(String questionnaireId,String object_id,Integer questionNo,String qustionAnswer, String versionFlag) throws ServiceException {
		QuestionnaireAnswer questionnaireAnswer = new QuestionnaireAnswer();
		questionnaireAnswer.setQuestionnaireId(DecoderUtil.UtfDecoder(questionnaireId));
		questionnaireAnswer.setObject_id(DecoderUtil.UtfDecoder(object_id));
		questionnaireAnswer.setQuestionNo(questionNo);
		questionnaireAnswer.setQustionAnswer(DecoderUtil.UtfDecoder(qustionAnswer));
		CommonEntity status = questionnaireAnswer.getStatus();
		status.setVersionFlag(versionFlag);
		questionnaireAnswer.setStatus(status);
		dbDAO.persist(questionnaireAnswer);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		QuestionnaireAnswer questionnaireAnswer = dbDAO.queryById(this.getTableNameFromEntity(QuestionnaireAnswer.class), id);
		dbDAO.remove(questionnaireAnswer);
		return true;
	}

	@Override
	public Long countByQuestionNo(String questionnaireId, String questionNo)
			throws ServiceException {
		return dbDAO.getTotalCount(QuestionnaireAnswer.class.getSimpleName(), deleteFlag, versionFlag, " and s.questionnaireId =?3 and s.questionNo =?4", new Object[]{questionnaireId,Integer.parseInt(questionNo)});
	}

	@Override
	public Long countRadioAnswerByQustion(String questionnaireId,
			String questionNo, String optionNo) {
		return dbDAO.getTotalCount(QuestionnaireAnswer.class.getSimpleName(), deleteFlag, versionFlag, " and s.questionnaireId =?3 and s.questionNo =?4 and s.qustionAnswer =?5", new Object[]{questionnaireId,Integer.parseInt(questionNo),optionNo});
	}

	@Override
	public Long countCheckboxAnswerByQustion(String questionnaireId,
			String questionNo, String optionNo) {
		return dbDAO.getTotalCount(QuestionnaireAnswer.class.getSimpleName(), deleteFlag, versionFlag, " and s.questionnaireId =?3 and s.questionNo =?4 and s.qustionAnswer like ?5", new Object[]{questionnaireId,Integer.parseInt(questionNo),"%"+optionNo+"%"});
	}

	@Override
	public List<QuestionnaireAnswer> queryByQuestionNo(String questionnaireId,
			String questionNo) throws ServiceException {
		return dbDAO.queryByList(QuestionnaireAnswer.class.getSimpleName(), deleteFlag, versionFlag, "and s.questionnaireId =?3 and s.questionNo =?4", new Object[]{questionnaireId,Integer.parseInt(questionNo)}, orderBy, null);
	}

	

}