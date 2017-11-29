package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.SurveyOption;
import com.ticket.service.ISurveyOptionService;
import com.ticket.util.GeneralUtil;

/**
 * 问题选项控制器
 * @ClassName: SurveyOptionAction   
 * @Description:  提供问题选项的相关操作方法. 
 * @author HiSay  
 * @date 2015-11-12 14:58:46
 *
 */
public class SurveyOptionAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//问题选项的业务层
	@Resource private ISurveyOptionService surveyOptionService = null;
	//问题选项实体
	private SurveyOption surveyOption = null;
	//主键
	private String id = null;
    //选项编号
	private String optionNo = null;
    //所属问题
	private String surveyQuestionId = null;
    //选项标题
	private String title = null;
	
	/**
	 * 添加问题选项
	 * @Title: SurveyOptionAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addSurveyOption";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(optionNo)) {
				data = AjaxData.responseError(getText("optionNo.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(surveyQuestionId)) {
				data = AjaxData.responseError(getText("surveyQuestionId.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(title)) {
				data = AjaxData.responseError(getText("title.required"));
				return JSON;
			}
			//验证选项编号是否已存在
			if(surveyOptionService.validateNoExist(surveyQuestionId,optionNo) != null){
				data = AjaxData.responseError(getText("optionNo.exist"));
				return JSON;
			}
			/**
			 * 验证选项是否已存在
			 */
			if(surveyOptionService.validateTitleExist(surveyQuestionId,title) != null){
				data = AjaxData.responseError(getText("title.exist"));
				return JSON;
			}
			/*if(GeneralUtil.isNull(type)) {
				data = AjaxData.responseError(getText("type.required"));
				return JSON;
			}*/
			//保存问题选项实体
			boolean isSuc = surveyOptionService.persist(optionNo, surveyQuestionId, title, versionFlag);
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
	 * 修改问题选项
	 * @Title: SurveyOptionAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setSurveyOption(surveyOptionService.queryById(SurveyOption.class.getSimpleName(), id));
			return "editSurveyOption";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(optionNo)) {
				data = AjaxData.responseError(getText("optionNo.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(surveyQuestionId)) {
				data = AjaxData.responseError(getText("surveyQuestionId.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(title)) {
				data = AjaxData.responseError(getText("title.required"));
				return JSON;
			}
			//验证选项编号是否已存在
			if(surveyOptionService.validateNoExist(surveyQuestionId,optionNo) != null){
				SurveyOption obj = surveyOptionService.validateNoExist(surveyQuestionId,optionNo);
				if(!id.equals(obj.getId())){
					data = AjaxData.responseError(getText("optionNo.exist"));
					return JSON;
				}
				
			}
			/**
			 * 验证选项是否已存在
			 */
			if(surveyOptionService.validateTitleExist(surveyQuestionId,title) != null){
				SurveyOption obj = surveyOptionService.validateTitleExist(surveyQuestionId,title);
				if(!id.equals(obj.getId())){
					data = AjaxData.responseError(getText("title.exist"));
					return JSON;
				}
			}
			/*if(GeneralUtil.isNull(type)) {
				data = AjaxData.responseError(getText("type.required"));
				return JSON;
			}*/
			//修改问题选项实体
			boolean isSuc = surveyOptionService.merge(id, optionNo, surveyQuestionId, title, versionFlag);
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
	 * 管理问题选项实体
	 * @Title: SurveyOptionAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		if(GeneralUtil.isNotNull(surveyQuestionId)){
			this.setPageModule(surveyOptionService.queryList(versionFlag, surveyQuestionId, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
			this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
			return "manageSurveyOption";
		}
		
		this.setPageModule(surveyOptionService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageSurveyOption";
	}
	
	/**
	 * 查看回收站
	 * @Title: SurveyOptionAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(surveyOptionService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleSurveyOption";
	}
	
	/**
	 * 逻辑删除问题选项对象
	 * @Title: SurveyOptionAction
	 * @Description: 把问题选项对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = surveyOptionService.logicDeleteEntity(SurveyOption.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除问题选项对象
	 * @Title: SurveyOptionAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = surveyOptionService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个问题选项对象
	 * @Title: SurveyOptionAction
	 * @Description: 从回收站还原问题选项对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = surveyOptionService.restoreEntity(SurveyOption.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核问题选项对象
	 * @Title: SurveyOptionAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = surveyOptionService.auditEntity(SurveyOption.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: SurveyOptionAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = surveyOptionService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public SurveyOption getSurveyOption() {
		return surveyOption;
	}
	public void setSurveyOption(SurveyOption surveyOption) {
		this.surveyOption = surveyOption;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOptionNo() {
		return optionNo;
	}
	public void setOptionNo(String optionNo) {
		this.optionNo = optionNo;
	}
	public String getSurveyQuestionId() {
		return surveyQuestionId;
	}
	public void setSurveyQuestionId(String surveyQuestionId) {
		this.surveyQuestionId = surveyQuestionId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
