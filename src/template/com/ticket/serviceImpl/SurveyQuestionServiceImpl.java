package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.SurveyQuestion;
import com.ticket.service.ISurveyQuestionService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.PaginationContext;

/**
 * 调查问题业务接口实现类
 * @ClassName: ISurveyQuestionService   
 * @Description: 提供调查问题操作的增删改查   
 * @author HiSay  
 * @date 2015-11-12 14:53:43
 *
 */
public class SurveyQuestionServiceImpl extends BaseServiceImpl<SurveyQuestion, String> implements ISurveyQuestionService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(SurveyQuestionServiceImpl.class);
	
	@Override
	public boolean merge(String id, Integer questionNo,String title,String surveyQuestionnaireId,Integer type,Integer iseq,Integer questionType, String versionFlag) throws ServiceException {
		SurveyQuestion surveyQuestion = dbDAO.queryById(this.getTableNameFromEntity(SurveyQuestion.class), id);
		surveyQuestion.setQuestionNo(questionNo);
		surveyQuestion.setTitle(DecoderUtil.UtfDecoder(title));
		surveyQuestion.setSurveyQuestionnaireId(surveyQuestionnaireId);
		surveyQuestion.setType(type);
		surveyQuestion.setIseq(iseq);
		surveyQuestion.setQuestionType(questionType);
		CommonEntity status = surveyQuestion.getStatus();
		status.setVersionFlag(versionFlag);
		surveyQuestion.setStatus(status);
		dbDAO.merge(surveyQuestion);
		return true;
	}

	@Override
	public boolean persist(Integer questionNo,String title,String surveyQuestionnaireId,Integer type,Integer iseq,Integer questionType, String versionFlag) throws ServiceException {
		SurveyQuestion surveyQuestion = new SurveyQuestion();
		surveyQuestion.setQuestionNo(questionNo);
		surveyQuestion.setTitle(DecoderUtil.UtfDecoder(title));
		surveyQuestion.setSurveyQuestionnaireId(surveyQuestionnaireId);
		surveyQuestion.setType(type);
		surveyQuestion.setIseq(iseq);
		surveyQuestion.setQuestionType(questionType);
		CommonEntity status = surveyQuestion.getStatus();
		status.setVersionFlag(versionFlag);
		surveyQuestion.setStatus(status);
		dbDAO.persist(surveyQuestion);
		return true;
	}
	
	@Override
	public boolean remove(String id) throws ServiceException {
		SurveyQuestion surveyQuestion = dbDAO.queryById(this.getTableNameFromEntity(SurveyQuestion.class), id);
		dbDAO.remove(surveyQuestion);
		return true;
	}
	
	@Override
	public List<SurveyQuestion> queryList(String versionFlag)
			throws ServiceException {
		return dbDAO.queryByList(SurveyQuestion.class.getSimpleName(), deleteFlag, versionFlag, null, null, orderBy, null);
	}
	
	
	@Override
	public Pagination queryList(String versionFlag,
			String surveyQuestionnaireId,int pageSize) throws ServiceException {
		return  dbDAO.queryByPageModule(SurveyQuestion.class.getSimpleName(), deleteFlag, versionFlag, "and s.surveyQuestionnaireId =?3", new Object[]{surveyQuestionnaireId}, orderBy, PaginationContext.getOffset(), pageSize);
	}

	@Override
	public List<SurveyQuestion> queryByQuertionnaireId(String id,
			String versionFlag) throws ServiceException {
		return dbDAO.queryByList(SurveyQuestion.class.getSimpleName(), deleteFlag, versionFlag, "and s.surveyQuestionnaireId =?3", new Object[]{id}, "s.questionNo asc", null);
	}

	@Override
	public SurveyQuestion validateNoIsExist(String surveyQuestionnaireId,
			Integer questionNo) throws ServiceException {
		return dbDAO.queryByCustom(SurveyQuestion.class.getSimpleName(), deleteFlag, versionFlag, "and s.surveyQuestionnaireId =?3 and s.questionNo =?4", new Object[]{surveyQuestionnaireId,questionNo});
	}

	@Override
	public SurveyQuestion validateTitleIsExist(String surveyQuestionnaireId,
			String title) throws ServiceException {
		return dbDAO.queryByCustom(SurveyQuestion.class.getSimpleName(), deleteFlag, versionFlag, "and s.surveyQuestionnaireId =?3 and s.title =?4", new Object[]{surveyQuestionnaireId,title});
	}

}