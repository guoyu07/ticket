package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.QuestionnaireSendLog;
import com.ticket.service.IQuestionnaireSendLogService;
import com.ticket.util.GeneralUtil;

/**
 * 问卷发放日志控制器
 * @ClassName: QuestionnaireSendLogAction   
 * @Description:  提供问卷发放日志的相关操作方法. 
 * @author HiSay  
 * @date 2016-05-05 16:21:51
 *
 */
public class QuestionnaireSendLogAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//问卷发放日志的业务层
	@Resource private IQuestionnaireSendLogService questionnaireSendLogService = null;
	//问卷发放日志实体
	private QuestionnaireSendLog questionnaireSendLog = null;
	//主键
	private String id = null;
    //问卷id
	private String questionnaireId = null;
    //发放人
	private String employee_id = null;
    //问卷发送数量
	private Integer sendCount = null;
    //问卷完成数量
	private Integer writeCount = null;
	
	/**
	 * 添加问卷发放日志
	 * @Title: QuestionnaireSendLogAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addQuestionnaireSendLog";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(questionnaireId)) {
				data = AjaxData.responseError(getText("questionnaireId.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(employee_id)) {
				data = AjaxData.responseError(getText("employee_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(sendCount)) {
				data = AjaxData.responseError(getText("sendCount.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(writeCount)) {
				data = AjaxData.responseError(getText("writeCount.required"));
				return JSON;
			}
			//保存问卷发放日志实体
			boolean isSuc = questionnaireSendLogService.persist(questionnaireId, employee_id, sendCount, writeCount, versionFlag);
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
	 * 修改问卷发放日志
	 * @Title: QuestionnaireSendLogAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setQuestionnaireSendLog(questionnaireSendLogService.queryById(QuestionnaireSendLog.class.getSimpleName(), id));
			return "editQuestionnaireSendLog";
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
			if(GeneralUtil.isNull(employee_id)) {
				data = AjaxData.responseError(getText("employee_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(sendCount)) {
				data = AjaxData.responseError(getText("sendCount.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(writeCount)) {
				data = AjaxData.responseError(getText("writeCount.required"));
				return JSON;
			}
			//修改问卷发放日志实体
			boolean isSuc = questionnaireSendLogService.merge(id, questionnaireId, employee_id, sendCount, writeCount,  versionFlag);
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
	 * 管理问卷发放日志实体
	 * @Title: QuestionnaireSendLogAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(questionnaireSendLogService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageQuestionnaireSendLog";
	}
	
	/**
	 * 查看回收站
	 * @Title: QuestionnaireSendLogAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(questionnaireSendLogService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleQuestionnaireSendLog";
	}
	
	/**
	 * 逻辑删除问卷发放日志对象
	 * @Title: QuestionnaireSendLogAction
	 * @Description: 把问卷发放日志对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = questionnaireSendLogService.logicDeleteEntity(QuestionnaireSendLog.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除问卷发放日志对象
	 * @Title: QuestionnaireSendLogAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = questionnaireSendLogService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个问卷发放日志对象
	 * @Title: QuestionnaireSendLogAction
	 * @Description: 从回收站还原问卷发放日志对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = questionnaireSendLogService.restoreEntity(QuestionnaireSendLog.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核问卷发放日志对象
	 * @Title: QuestionnaireSendLogAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = questionnaireSendLogService.auditEntity(QuestionnaireSendLog.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: QuestionnaireSendLogAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = questionnaireSendLogService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public QuestionnaireSendLog getQuestionnaireSendLog() {
		return questionnaireSendLog;
	}
	public void setQuestionnaireSendLog(QuestionnaireSendLog questionnaireSendLog) {
		this.questionnaireSendLog = questionnaireSendLog;
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
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public Integer getSendCount() {
		return sendCount;
	}
	public void setSendCount(Integer sendCount) {
		this.sendCount = sendCount;
	}
	public Integer getWriteCount() {
		return writeCount;
	}
	public void setWriteCount(Integer writeCount) {
		this.writeCount = writeCount;
	}
}
