package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.SurveyOption;
import com.ticket.service.ISurveyOptionService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.PaginationContext;

/**
 * 问题选项业务接口实现类
 * @ClassName: ISurveyOptionService   
 * @Description: 提供问题选项操作的增删改查   
 * @author HiSay  
 * @date 2015-11-12 14:58:46
 *
 */
public class SurveyOptionServiceImpl extends BaseServiceImpl<SurveyOption, String> implements ISurveyOptionService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(SurveyOptionServiceImpl.class);
	
	@Override
	public boolean merge(String id, String optionNo,String surveyQuestionId,String title,String versionFlag) throws ServiceException {
		SurveyOption surveyOption = dbDAO.queryById(this.getTableNameFromEntity(SurveyOption.class), id);
		surveyOption.setOptionNo(DecoderUtil.UtfDecoder(optionNo));
		surveyOption.setSurveyQuestionId(DecoderUtil.UtfDecoder(surveyQuestionId));
		surveyOption.setTitle(DecoderUtil.UtfDecoder(title));
		CommonEntity status = surveyOption.getStatus();
		status.setVersionFlag(versionFlag);
		surveyOption.setStatus(status);
		dbDAO.merge(surveyOption);
		return true;
	}

	@Override
	public boolean persist(String optionNo,String surveyQuestionId,String title, String versionFlag) throws ServiceException {
		SurveyOption surveyOption = new SurveyOption();
		surveyOption.setOptionNo(DecoderUtil.UtfDecoder(optionNo));
		surveyOption.setSurveyQuestionId(DecoderUtil.UtfDecoder(surveyQuestionId));
		surveyOption.setTitle(DecoderUtil.UtfDecoder(title));
		CommonEntity status = surveyOption.getStatus();
		status.setVersionFlag(versionFlag);
		surveyOption.setStatus(status);
		dbDAO.persist(surveyOption);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		SurveyOption surveyOption = dbDAO.queryById(this.getTableNameFromEntity(SurveyOption.class), id);
		dbDAO.remove(surveyOption);
		return true;
	}

	@Override
	public Pagination queryList(String versionFlag,
			String surveyQuestionId, int pageSize) throws ServiceException {
		return  dbDAO.queryByPageModule(SurveyOption.class.getSimpleName(), deleteFlag, versionFlag, "and s.surveyQuestionId =?3", new Object[]{surveyQuestionId}, orderBy, PaginationContext.getOffset(), pageSize);
	}

	@Override
	public List<SurveyOption> queryByQuestionId(String question_id,
			String versionFlag) throws ServiceException {
		return dbDAO.queryByList(SurveyOption.class.getSimpleName(), deleteFlag, versionFlag, "and s.surveyQuestionId = ?3", new Object[]{question_id}, "s.optionNo asc", null);
	}

	@Override
	public SurveyOption validateNoExist(String surveyQuestionId, String optionNo)
			throws ServiceException {
		return dbDAO.queryByCustom(SurveyOption.class.getSimpleName(), deleteFlag, versionFlag, "and s.surveyQuestionId =?3 and s.optionNo =?4", new Object[]{surveyQuestionId,optionNo});
	}

	@Override
	public SurveyOption validateTitleExist(String surveyQuestionId, String title)
			throws ServiceException {
		return dbDAO.queryByCustom(SurveyOption.class.getSimpleName(), deleteFlag, versionFlag, "and s.surveyQuestionId =?3 and s.title =?4", new Object[]{surveyQuestionId,title});
	}
	
	
	
}