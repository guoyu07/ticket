package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.QuestionnaireAnswer;
import com.ticket.service.IQuestionnaireAnswerService;
import com.ticket.util.GeneralUtil;

/**
 * 问卷发放记录表控制器
 * @ClassName: QuestionnaireAnswerAction   
 * @Description:  提供问卷发放记录表的相关操作方法. 
 * @author HiSay  
 * @date 2016-05-04 16:31:27
 *
 */
public class QuestionnaireAnswerAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//问卷发放记录表的业务层
	@Resource private IQuestionnaireAnswerService questionnaireAnswerService = null;
	//问卷发放记录表实体
	private QuestionnaireAnswer questionnaireAnswer = null;
	//主键
	private String id = null;
    //问卷id
	private String questionnaireId = null;
    //答题人id
	private String object_id = null;
    //问题编号
	private Integer questionNo = null;
    //问题答案
	private String qustionAnswer = null;
	
	/**
	 * 添加问卷发放记录表
	 * @Title: QuestionnaireAnswerAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addQuestionnaireAnswer";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(questionnaireId)) {
				data = AjaxData.responseError(getText("questionnaireId.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(object_id)) {
				data = AjaxData.responseError(getText("object_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(questionNo)) {
				data = AjaxData.responseError(getText("questionNo.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(qustionAnswer)) {
				data = AjaxData.responseError(getText("qustionAnswer.required"));
				return JSON;
			}
			//保存问卷发放记录表实体
			boolean isSuc = questionnaireAnswerService.persist(questionnaireId, object_id, questionNo, qustionAnswer, versionFlag);
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
	 * 修改问卷发放记录表
	 * @Title: QuestionnaireAnswerAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setQuestionnaireAnswer(questionnaireAnswerService.queryById(QuestionnaireAnswer.class.getSimpleName(), id));
			return "editQuestionnaireAnswer";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(questionnaireId)) {
				data = AjaxData.responseError(getText("questionnaireId.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(object_id)) {
				data = AjaxData.responseError(getText("object_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(questionNo)) {
				data = AjaxData.responseError(getText("questionNo.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(qustionAnswer)) {
				data = AjaxData.responseError(getText("qustionAnswer.required"));
				return JSON;
			}
			//修改问卷发放记录表实体
			boolean isSuc = questionnaireAnswerService.merge(id, questionnaireId, object_id, questionNo, qustionAnswer,  versionFlag);
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
	 * 管理问卷发放记录表实体
	 * @Title: QuestionnaireAnswerAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(questionnaireAnswerService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageQuestionnaireAnswer";
	}
	
	/**
	 * 查看回收站
	 * @Title: QuestionnaireAnswerAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(questionnaireAnswerService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleQuestionnaireAnswer";
	}
	
	/**
	 * 逻辑删除问卷发放记录表对象
	 * @Title: QuestionnaireAnswerAction
	 * @Description: 把问卷发放记录表对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = questionnaireAnswerService.logicDeleteEntity(QuestionnaireAnswer.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除问卷发放记录表对象
	 * @Title: QuestionnaireAnswerAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = questionnaireAnswerService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个问卷发放记录表对象
	 * @Title: QuestionnaireAnswerAction
	 * @Description: 从回收站还原问卷发放记录表对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = questionnaireAnswerService.restoreEntity(QuestionnaireAnswer.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核问卷发放记录表对象
	 * @Title: QuestionnaireAnswerAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = questionnaireAnswerService.auditEntity(QuestionnaireAnswer.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: QuestionnaireAnswerAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = questionnaireAnswerService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public QuestionnaireAnswer getQuestionnaireAnswer() {
		return questionnaireAnswer;
	}
	public void setQuestionnaireAnswer(QuestionnaireAnswer questionnaireAnswer) {
		this.questionnaireAnswer = questionnaireAnswer;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQuestionnaireId() {
		return questionnaireId;
	}
	public void setQuestionnaireId(String questionnaireId) {
		this.questionnaireId = questionnaireId;
	}
	public String getObject_id() {
		return object_id;
	}
	public void setObject_id(String object_id) {
		this.object_id = object_id;
	}
	public Integer getQuestionNo() {
		return questionNo;
	}
	public void setQuestionNo(Integer questionNo) {
		this.questionNo = questionNo;
	}
	public String getQustionAnswer() {
		return qustionAnswer;
	}
	public void setQustionAnswer(String qustionAnswer) {
		this.qustionAnswer = qustionAnswer;
	}
}
