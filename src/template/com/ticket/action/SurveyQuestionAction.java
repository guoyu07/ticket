package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.SurveyQuestion;
import com.ticket.service.ISurveyQuestionService;
import com.ticket.util.GeneralUtil;

/**
 * 调查问题控制器
 * @ClassName: SurveyQuestionAction   
 * @Description:  提供调查问题的相关操作方法. 
 * @author HiSay  
 * @date 2015-11-12 14:53:43
 *
 */
public class SurveyQuestionAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//调查问题的业务层
	@Resource private ISurveyQuestionService surveyQuestionService = null;
	//调查问题实体
	private SurveyQuestion surveyQuestion = null;
	//主键
	private String id = null;
    //问题编号
	private Integer questionNo = null;
	// 所属问卷
	private String surveyQuestionnaireId =null;
    //问题标题
	private String title = null;
    //问题类型
	private Integer type = null;
    //问题排序
	private Integer iseq = null;
    //问题调查形式
	private Integer questionType = null;
	
	/**
	 * 添加调查问题
	 * @Title: SurveyQuestionAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addSurveyQuestion";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(questionNo)) {
				data = AjaxData.responseError(getText("questionNo.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(title)) {
				data = AjaxData.responseError(getText("title.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(surveyQuestionnaireId)) {
				data = AjaxData.responseError(getText("surveyQuestionnaireId.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(type)) {
				data = AjaxData.responseError(getText("type.required"));
				return JSON;
			}

			//验证编号是否已存在
			if(surveyQuestionService.validateNoIsExist(surveyQuestionnaireId,questionNo) != null){
				data = AjaxData.responseError(getText("questionNo.exist"));
				return JSON;
			}	
			//验证问题是否已存在
			if(surveyQuestionService.validateTitleIsExist(surveyQuestionnaireId,title) != null){
				data = AjaxData.responseError(getText("title.exist"));
				return JSON;
			}
			/*if(GeneralUtil.isNull(iseq)) {
				data = AjaxData.responseError(getText("iseq.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(questionType)) {
				data = AjaxData.responseError(getText("questionType.required"));
				return JSON;
			}*/
			//保存调查问题实体
			boolean isSuc = surveyQuestionService.persist(questionNo, title, surveyQuestionnaireId,type, iseq, questionType, versionFlag);
			//根据保存结果返回页面
			if(isSuc) {
				data = AjaxData.responseSuccess(getText("addSuccess"));
			} else {
				data = AjaxData.responseError(getText("addFailed"));
			}
			return JSON;
		}
	}
	
	/**
	 * 修改调查问题
	 * @Title: SurveyQuestionAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setSurveyQuestion(surveyQuestionService.queryById(SurveyQuestion.class.getSimpleName(), id));
			return "editSurveyQuestion";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(questionNo)) {
				data = AjaxData.responseError(getText("questionNo.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(title)) {
				data = AjaxData.responseError(getText("title.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(surveyQuestionnaireId)) {
				data = AjaxData.responseError(getText("surveyQuestionnaireId.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(type)) {
				data = AjaxData.responseError(getText("type.required"));
				return JSON;
			}
			
			//验证编号是否已存在
			if(surveyQuestionService.validateNoIsExist(surveyQuestionnaireId,questionNo) != null){
				SurveyQuestion sq = surveyQuestionService.validateNoIsExist(surveyQuestionnaireId,questionNo);
				if(!id.equals(sq.getId())){
					data = AjaxData.responseError(getText("questionNo.exist"));
					return JSON;
				}
			}
			//验证问题是否已存在
			if(surveyQuestionService.validateTitleIsExist(surveyQuestionnaireId,title) != null){
				SurveyQuestion sq = surveyQuestionService.validateTitleIsExist(surveyQuestionnaireId,title);
				if(!id.equals(sq.getId())){
					data = AjaxData.responseError(getText("title.exist"));
					return JSON;
				}
			}
			/*if(GeneralUtil.isNull(iseq)) {
				data = AjaxData.responseError(getText("iseq.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(questionType)) {
				data = AjaxData.responseError(getText("questionType.required"));
				return JSON;
			}*/
			//修改调查问题实体
			boolean isSuc = surveyQuestionService.merge(id, questionNo, title,surveyQuestionnaireId, type, iseq, questionType,  versionFlag);
			//根据修改结果返回页面
			if(isSuc) {
				data = AjaxData.responseSuccess(getText("editSuccess"));
			} else {
				data = AjaxData.responseError(getText("editFailed"));
			}
			return JSON;
		}
	}
	
	/**
	 * 管理调查问题实体
	 * @Title: SurveyQuestionAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		if(GeneralUtil.isNotNull(surveyQuestionnaireId)){
			this.setPageModule(surveyQuestionService.queryList(versionFlag, surveyQuestionnaireId, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
			this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
			return "manageSurveyQuestion";
		}
		
		this.setPageModule(surveyQuestionService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageSurveyQuestion";
	}
	
	/**
	 * 查看回收站
	 * @Title: SurveyQuestionAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(surveyQuestionService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleSurveyQuestion";
	}
	
	/**
	 * 逻辑删除调查问题对象
	 * @Title: SurveyQuestionAction
	 * @Description: 把调查问题对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = surveyQuestionService.logicDeleteEntity(SurveyQuestion.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除调查问题对象
	 * @Title: SurveyQuestionAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = surveyQuestionService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个调查问题对象
	 * @Title: SurveyQuestionAction
	 * @Description: 从回收站还原调查问题对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = surveyQuestionService.restoreEntity(SurveyQuestion.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核调查问题对象
	 * @Title: SurveyQuestionAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = surveyQuestionService.auditEntity(SurveyQuestion.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: SurveyQuestionAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = surveyQuestionService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public SurveyQuestion getSurveyQuestion() {
		return surveyQuestion;
	}
	public void setSurveyQuestion(SurveyQuestion surveyQuestion) {
		this.surveyQuestion = surveyQuestion;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getIseq() {
		return iseq;
	}
	public void setIseq(Integer iseq) {
		this.iseq = iseq;
	}
	public Integer getQuestionType() {
		return questionType;
	}
	public void setQuestionType(Integer questionType) {
		this.questionType = questionType;
	}

	public void setSurveyQuestionnaireId(String surveyQuestionnaireId) {
		this.surveyQuestionnaireId = surveyQuestionnaireId;
	}

	public String getSurveyQuestionnaireId() {
		return surveyQuestionnaireId;
	}

	public Integer getQuestionNo() {
		return questionNo;
	}

	public void setQuestionNo(Integer questionNo) {
		this.questionNo = questionNo;
	}
}
