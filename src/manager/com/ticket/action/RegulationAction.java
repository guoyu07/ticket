package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.Regulation;
import com.ticket.service.IRegulationService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 规章制度信息控制器
 * @ClassName: RegulationAction   
 * @Description:  提供规章制度信息的相关操作方法. 
 * @author HiSay  
 * @date 2015-11-20 13:23:55
 *
 */
public class RegulationAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//规章制度信息的业务层
	@Resource private IRegulationService regulationService = null;
	
	@Resource private ISystemOperationLogService logService = null;
	//规章制度信息实体
	private Regulation regulation = null;
	//主键
	private String id = null;
    //制度类别
	private String type_id = null;
    //标题
	private String title = null;
    //制度内容
	private String content = null;
	
	/**
	 * 添加规章制度信息
	 * @Title: RegulationAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addRegulation";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(type_id)) {
				data = AjaxData.responseError(getText("type_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(title)) {
				data = AjaxData.responseError(getText("title.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(content)) {
				data = AjaxData.responseError(getText("content.required"));
				return JSON;
			}
			//保存规章制度信息实体
			boolean isSuc = regulationService.persist(type_id, title, content, versionFlag, orderValue);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增机场规章制度";
				String logName = SystemOparetionLogUtil.getLogName();
				String logTime = SystemOparetionLogUtil.getLogTime();
				String logIp = SystemOparetionLogUtil.getLogIp();
				
				logService.persist(logName, logContent, logTime, logIp, versionFlag);
				data = AjaxData.responseSuccess(getText("addSuccess"));
			} else {
				data = AjaxData.responseError(getText("addFailed"));
			}
			return JSON;
		}
	}
	
	/**
	 * 修改规章制度信息
	 * @Title: RegulationAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setRegulation(regulationService.queryById(Regulation.class.getSimpleName(), id));
			return "editRegulation";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(type_id)) {
				data = AjaxData.responseError(getText("type_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(title)) {
				data = AjaxData.responseError(getText("title.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(content)) {
				data = AjaxData.responseError(getText("content.required"));
				return JSON;
			}
			//修改规章制度信息实体
			boolean isSuc = regulationService.merge(id, type_id, title, content,  versionFlag, orderValue);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改机场规章制度";
				String logName = SystemOparetionLogUtil.getLogName();
				String logTime = SystemOparetionLogUtil.getLogTime();
				String logIp = SystemOparetionLogUtil.getLogIp();
				
				logService.persist(logName, logContent, logTime, logIp, versionFlag);
				data = AjaxData.responseSuccess(getText("editSuccess"));
			} else {
				data = AjaxData.responseError(getText("editFailed"));
			}
			return JSON;
		}
	}
	
	/**
	 * 管理规章制度信息实体
	 * @Title: RegulationAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(regulationService.queryByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageRegulation";
	}
	
	/**
	 * 查看回收站
	 * @Title: RegulationAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(regulationService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleRegulation";
	}
	
	/**
	 * 逻辑删除规章制度信息对象
	 * @Title: RegulationAction
	 * @Description: 把规章制度信息对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = regulationService.logicDeleteEntity(Regulation.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除机场规章制度";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除规章制度信息对象
	 * @Title: RegulationAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = regulationService.remove(id);
		if(isSuc) {
			String logContent = "物理删除机场规章制度";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个规章制度信息对象
	 * @Title: RegulationAction
	 * @Description: 从回收站还原规章制度信息对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = regulationService.restoreEntity(Regulation.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原机场规章制度";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核规章制度信息对象
	 * @Title: RegulationAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = regulationService.auditEntity(Regulation.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核机场规章制度";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: RegulationAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = regulationService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作机场规章制度";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量彻底删除制度实体
	 * @return
	 * @throws ServiceException
	 */
	public String batchRealDelete() throws ServiceException {
		boolean isSuc = regulationService.batchRealDelete(idsValue, versionFlag);
		if(isSuc) {
			String logContent = "批量删除机场规章制度";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public Regulation getRegulation() {
		return regulation;
	}
	public void setRegulation(Regulation regulation) {
		this.regulation = regulation;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType_id() {
		return type_id;
	}
	public void setType_id(String type_id) {
		this.type_id = type_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
