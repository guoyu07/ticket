package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.QuestionnaireSendRecord;
import com.ticket.service.IQuestionnaireSendRecordService;
import com.ticket.util.GeneralUtil;

/**
 * 问卷发放记录表控制器
 * @ClassName: QuestionnaireSendRecordAction   
 * @Description:  提供问卷发放记录表的相关操作方法. 
 * @author HiSay  
 * @date 2016-05-04 16:18:18
 *
 */
public class QuestionnaireSendRecordAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//问卷发放记录表的业务层
	@Resource private IQuestionnaireSendRecordService questionnaireSendRecordService = null;
	//问卷发放记录表实体
	private QuestionnaireSendRecord questionnaireSendRecord = null;
	//主键
	private String id = null;
    //问卷id
	private String questionnaireId = null;
    //操作员id
	private String employee_id = null;
    //发送对象id
	private String object_id = null;
    //是否填写
	private Integer isWrite = null;
	
	/**
	 * 添加问卷发放记录表
	 * @Title: QuestionnaireSendRecordAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addQuestionnaireSendRecord";
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
			if(GeneralUtil.isNull(object_id)) {
				data = AjaxData.responseError(getText("object_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(isWrite)) {
				data = AjaxData.responseError(getText("isWrite.required"));
				return JSON;
			}
			//保存问卷发放记录表实体
			boolean isSuc = questionnaireSendRecordService.persist(questionnaireId, employee_id, object_id, isWrite, versionFlag);
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
	 * @Title: QuestionnaireSendRecordAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setQuestionnaireSendRecord(questionnaireSendRecordService.queryById(QuestionnaireSendRecord.class.getSimpleName(), id));
			return "editQuestionnaireSendRecord";
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
			if(GeneralUtil.isNull(object_id)) {
				data = AjaxData.responseError(getText("object_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(isWrite)) {
				data = AjaxData.responseError(getText("isWrite.required"));
				return JSON;
			}
			//修改问卷发放记录表实体
			boolean isSuc = questionnaireSendRecordService.merge(id, questionnaireId, employee_id, object_id, isWrite,  versionFlag);
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
	 * @Title: QuestionnaireSendRecordAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(questionnaireSendRecordService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageQuestionnaireSendRecord";
	}
	
	/**
	 * 查看回收站
	 * @Title: QuestionnaireSendRecordAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(questionnaireSendRecordService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleQuestionnaireSendRecord";
	}
	
	/**
	 * 逻辑删除问卷发放记录表对象
	 * @Title: QuestionnaireSendRecordAction
	 * @Description: 把问卷发放记录表对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = questionnaireSendRecordService.logicDeleteEntity(QuestionnaireSendRecord.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除问卷发放记录表对象
	 * @Title: QuestionnaireSendRecordAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = questionnaireSendRecordService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个问卷发放记录表对象
	 * @Title: QuestionnaireSendRecordAction
	 * @Description: 从回收站还原问卷发放记录表对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = questionnaireSendRecordService.restoreEntity(QuestionnaireSendRecord.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核问卷发放记录表对象
	 * @Title: QuestionnaireSendRecordAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = questionnaireSendRecordService.auditEntity(QuestionnaireSendRecord.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: QuestionnaireSendRecordAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = questionnaireSendRecordService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public QuestionnaireSendRecord getQuestionnaireSendRecord() {
		return questionnaireSendRecord;
	}
	public void setQuestionnaireSendRecord(QuestionnaireSendRecord questionnaireSendRecord) {
		this.questionnaireSendRecord = questionnaireSendRecord;
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
	public String getObject_id() {
		return object_id;
	}
	public void setObject_id(String object_id) {
		this.object_id = object_id;
	}
	public Integer getIsWrite() {
		return isWrite;
	}
	public void setIsWrite(Integer isWrite) {
		this.isWrite = isWrite;
	}
}
