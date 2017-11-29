package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjServiceCodeDelayRule;
import com.ticket.service.IBjdjServiceCodeDelayRuleService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 服务码延期规则控制器
 * @ClassName: BjdjServiceCodeDelayRuleAction   
 * @Description:  提供服务码延期规则的相关操作方法. 
 * @author HiSay  
 * @date 2015-12-11 14:46:34
 *
 */
public class BjdjServiceCodeDelayRuleAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//服务码延期规则的业务层
	@Resource private IBjdjServiceCodeDelayRuleService bjdjServiceCodeDelayRuleService = null;
	//服务码延期规则实体
	private BjdjServiceCodeDelayRule bjdjServiceCodeDelayRule = null;
	@Resource private ISystemOperationLogService logService = null;
	//主键
	private String id = null;
    //延长时间
	private Integer delayTime = null;
    //扣除积分
	private Integer record = null;
	//排序值
	private Integer orderValue = null;
	
	
	/**
	 * 添加服务码延期规则
	 * @Title: BjdjServiceCodeDelayRuleAction
	 * @Description:   
	 * @return
	 * @throws ServiceException   
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addBjdjServiceCodeDelayRule";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(delayTime)) {
				data = AjaxData.responseError(getText("deadline.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(record)) {
				data = AjaxData.responseError(getText("record.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(orderValue)) {
				data = AjaxData.responseError(getText("orderValue.required"));
				return JSON;
			}
			//保存服务码延期规则实体
			boolean isSuc = bjdjServiceCodeDelayRuleService.persist(delayTime, record, orderValue);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "添加服务码延期规则";
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
	 * 修改服务码延期规则
	 * @Title: BjdjServiceCodeDelayRuleAction
	 * @Description:   
	 * @return
	 * @throws ServiceException    
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setBjdjServiceCodeDelayRule(bjdjServiceCodeDelayRuleService.queryById(BjdjServiceCodeDelayRule.class.getSimpleName(), id));
			return "editBjdjServiceCodeDelayRule";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(delayTime)) {
				data = AjaxData.responseError(getText("deadline.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(record)) {
				data = AjaxData.responseError(getText("record.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(orderValue)) {
				data = AjaxData.responseError(getText("orderValue.required"));
				return JSON;
			}
			//修改服务码延期规则实体
			boolean isSuc = bjdjServiceCodeDelayRuleService.merge(id, delayTime, record,  orderValue);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改服务码延期规则";
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
	 * 管理服务码延期规则实体
	 * @Title: BjdjServiceCodeDelayRuleAction
	 * @return
	 * @throws ServiceException    
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(bjdjServiceCodeDelayRuleService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageBjdjServiceCodeDelayRule";
	}
	
	/**
	 * 查看回收站
	 * @Title: BjdjServiceCodeDelayRuleAction
	 * @return
	 * @throws ServiceException   
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(bjdjServiceCodeDelayRuleService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleBjdjServiceCodeDelayRule";
	}
	
	/**
	 * 逻辑删除服务码延期规则对象
	 * @Title: BjdjServiceCodeDelayRuleAction
	 * @Description: 把服务码延期规则对象放入回收站.   
	 * @return
	 * @throws ServiceException    
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = bjdjServiceCodeDelayRuleService.logicDeleteEntity(BjdjServiceCodeDelayRule.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除服务码延期规则";
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
	 * 物理删除服务码延期规则对象
	 * @Title: BjdjServiceCodeDelayRuleAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @return
	 * @throws ServiceException   
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = bjdjServiceCodeDelayRuleService.remove(id);
		if(isSuc) {
			String logContent = "物理删除服务码延期规则";
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
	 * 还原一个服务码延期规则对象
	 * @Title: BjdjServiceCodeDelayRuleAction
	 * @Description: 从回收站还原服务码延期规则对象   
	 * @return
	 * @throws ServiceException    
	 */
	public String restore() throws ServiceException {
		boolean isSuc = bjdjServiceCodeDelayRuleService.restoreEntity(BjdjServiceCodeDelayRule.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原服务码延期规则";
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
	 * 审核服务码延期规则对象
	 * @Title: BjdjServiceCodeDelayRuleAction
	 * @Description:  
	 * @throws ServiceException    设定文件   
	 * @return     返回类型   
	 */
	public String audit() throws ServiceException {
		boolean isSuc = bjdjServiceCodeDelayRuleService.auditEntity(BjdjServiceCodeDelayRule.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核服务码延期规则";
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
	 * @Title: BjdjServiceCodeDelayRuleAction
	 * @Description:    
	 * @return
	 * @throws ServiceException 
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = bjdjServiceCodeDelayRuleService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作服务码延期规则";
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
	
	public BjdjServiceCodeDelayRule getBjdjServiceCodeDelayRule() {
		return bjdjServiceCodeDelayRule;
	}
	public void setBjdjServiceCodeDelayRule(BjdjServiceCodeDelayRule bjdjServiceCodeDelayRule) {
		this.bjdjServiceCodeDelayRule = bjdjServiceCodeDelayRule;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getRecord() {
		return record;
	}
	public void setRecord(Integer record) {
		this.record = record;
	}
	public IBjdjServiceCodeDelayRuleService getBjdjServiceCodeDelayRuleService() {
		return bjdjServiceCodeDelayRuleService;
	}
	public void setBjdjServiceCodeDelayRuleService(
			IBjdjServiceCodeDelayRuleService bjdjServiceCodeDelayRuleService) {
		this.bjdjServiceCodeDelayRuleService = bjdjServiceCodeDelayRuleService;
	}
	public Integer getOrderValue() {
		return orderValue;
	}
	public void setOrderValue(Integer orderValue) {
		this.orderValue = orderValue;
	}

	public Integer getDelayTime() {
		return delayTime;
	}

	public void setDelayTime(Integer delayTime) {
		this.delayTime = delayTime;
	}
}
