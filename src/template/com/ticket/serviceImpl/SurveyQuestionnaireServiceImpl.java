package com.ticket.serviceImpl;


import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.SurveyQuestionnaire;
import com.ticket.service.ISurveyQuestionnaireService;
import com.ticket.util.DecoderUtil;

/**
 * 调查问卷业务接口实现类
 * @ClassName: ISurveyQuestionnaireService   
 * @Description: 提供调查问卷操作的增删改查   
 * @author HiSay  
 * @date 2015-11-11 17:10:59
 *
 */
public class SurveyQuestionnaireServiceImpl extends BaseServiceImpl<SurveyQuestionnaire, String> implements ISurveyQuestionnaireService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(SurveyQuestionnaireServiceImpl.class);
	
	@Override
	public boolean merge(String id, String survryNo,String title,String descript,Integer type,String survryModularId,String entityName,String entityId,Integer currentOpenUp,Date deadLine, String versionFlag) throws ServiceException {
		SurveyQuestionnaire surveyQuestionnaire = dbDAO.queryById(this.getTableNameFromEntity(SurveyQuestionnaire.class), id);
		surveyQuestionnaire.setSurvryNo(survryNo);
		surveyQuestionnaire.setTitle(DecoderUtil.UtfDecoder(title));
		surveyQuestionnaire.setDescript(DecoderUtil.UtfDecoder(descript));
		surveyQuestionnaire.setType(type);
		surveyQuestionnaire.setSurvryModularId(DecoderUtil.UtfDecoder(survryModularId));
		surveyQuestionnaire.setEntityName(DecoderUtil.UtfDecoder(entityName));
		surveyQuestionnaire.setEntityId(DecoderUtil.UtfDecoder(entityId));
		surveyQuestionnaire.setCurrentOpenUp(currentOpenUp);
		surveyQuestionnaire.setDeadLine(deadLine);
		CommonEntity status = surveyQuestionnaire.getStatus();
		status.setVersionFlag(versionFlag);
		surveyQuestionnaire.setStatus(status);
		dbDAO.merge(surveyQuestionnaire);
		return true;
	}

	@Override
	public boolean persist(String survryNo,String title,String descript,Integer type,String survryModularId,String entityName,String entityId,Integer currentOpenUp,Date deadLine, String versionFlag) throws ServiceException {
		SurveyQuestionnaire surveyQuestionnaire = new SurveyQuestionnaire();
		surveyQuestionnaire.setSurvryNo(survryNo);
		surveyQuestionnaire.setTitle(DecoderUtil.UtfDecoder(title));
		surveyQuestionnaire.setDescript(DecoderUtil.UtfDecoder(descript));
		surveyQuestionnaire.setType(type);
		surveyQuestionnaire.setSurvryModularId(DecoderUtil.UtfDecoder(survryModularId));
		surveyQuestionnaire.setEntityName(DecoderUtil.UtfDecoder(entityName));
		surveyQuestionnaire.setEntityId(DecoderUtil.UtfDecoder(entityId));
		surveyQuestionnaire.setCurrentOpenUp(currentOpenUp);
		surveyQuestionnaire.setDeadLine(deadLine);
		CommonEntity status = surveyQuestionnaire.getStatus();
		status.setVersionFlag(versionFlag);
		surveyQuestionnaire.setStatus(status);
		dbDAO.persist(surveyQuestionnaire);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		SurveyQuestionnaire surveyQuestionnaire = dbDAO.queryById(this.getTableNameFromEntity(SurveyQuestionnaire.class), id);
		dbDAO.remove(surveyQuestionnaire);
		return true;
	}
	
	@Override
	public List<SurveyQuestionnaire> queryList(String versionFlag)
			throws ServiceException {
		return dbDAO.queryByList(SurveyQuestionnaire.class.getSimpleName(), deleteFlag, versionFlag, null, null, orderBy, null);
	}

	@Override
	public SurveyQuestionnaire validateTitleExist(String title,
			String versionFlag) throws ServiceException {
		return dbDAO.queryByCustom(SurveyQuestionnaire.class.getSimpleName(), deleteFlag, versionFlag, "and s.title =?3", new Object[]{title});
	}

	@Override
	public boolean validateDeadlineExpire(String id, String versionFlag)
			throws ServiceException {
		SurveyQuestionnaire obj = dbDAO.queryById(SurveyQuestionnaire.class.getSimpleName(), id);
		if(obj != null){
			Date now = new Date();
			if(obj.getDeadLine().getTime() < now.getTime()){
				return true;
			}
		}
		return false;
	}

}