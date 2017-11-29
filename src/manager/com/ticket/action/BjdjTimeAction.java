package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjTime;
import com.ticket.service.IBjdjTimeService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.DateUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 便捷登机时间段分配控制器
 * @ClassName: BjdjTimeAction   
 * @Description:  提供便捷登机时间段分配的相关操作方法. 
 * @author HiSay  
 * @date 2016-03-23 10:11:25
 *
 */
public class BjdjTimeAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//便捷登机时间段分配的业务层
	@Resource private IBjdjTimeService bjdjTimeService = null;
	@Resource private ISystemOperationLogService logService = null;
	//便捷登机时间段分配实体
	private BjdjTime bjdjTime = null;
	//主键
	private String id = null;
	
	/**
	 * 添加便捷登机时间段分配
	 * @Title: BjdjTimeAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addBjdjTime";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(startTime)) {
				data = AjaxData.responseError(getText("startTime.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(endTime)) {
				data = AjaxData.responseError(getText("endTime.required"));
				return JSON;
			}
			//保存便捷登机时间段分配实体
			boolean isSuc = bjdjTimeService.persist(DateUtil.formatDateToSimpleString(startTime), DateUtil.formatDateToSimpleString(endTime),orderValue, versionFlag);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增便捷登机实时时间段";
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
	 * 修改便捷登机时间段分配
	 * @Title: BjdjTimeAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setBjdjTime(bjdjTimeService.queryById(BjdjTime.class.getSimpleName(), id));
			return "editBjdjTime";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(startTime)) {
				data = AjaxData.responseError(getText("startTime.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(endTime)) {
				data = AjaxData.responseError(getText("endTime.required"));
				return JSON;
			}
			//修改便捷登机时间段分配实体
			boolean isSuc = bjdjTimeService.merge(id, DateUtil.formatDateToSimpleString(startTime), DateUtil.formatDateToSimpleString(endTime),orderValue, versionFlag);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改便捷登机实时数据时间段";
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
	 * 管理便捷登机时间段分配实体
	 * @Title: BjdjTimeAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(bjdjTimeService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageBjdjTime";
	}
	
	/**
	 * 查看回收站
	 * @Title: BjdjTimeAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(bjdjTimeService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleBjdjTime";
	}
	
	/**
	 * 逻辑删除便捷登机时间段分配对象
	 * @Title: BjdjTimeAction
	 * @Description: 把便捷登机时间段分配对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = bjdjTimeService.logicDeleteEntity(BjdjTime.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除便捷登机实时数据时间段";
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
	 * 物理删除便捷登机时间段分配对象
	 * @Title: BjdjTimeAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = bjdjTimeService.remove(id);
		if(isSuc) {
			String logContent = "物理删除便捷登机实时数据时间段";
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
	 * 还原一个便捷登机时间段分配对象
	 * @Title: BjdjTimeAction
	 * @Description: 从回收站还原便捷登机时间段分配对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = bjdjTimeService.restoreEntity(BjdjTime.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核便捷登机时间段分配对象
	 * @Title: BjdjTimeAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = bjdjTimeService.auditEntity(BjdjTime.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: BjdjTimeAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = bjdjTimeService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作便捷登机实时数据时间段";
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
	
	public BjdjTime getBjdjTime() {
		return bjdjTime;
	}
	public void setBjdjTime(BjdjTime bjdjTime) {
		this.bjdjTime = bjdjTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
