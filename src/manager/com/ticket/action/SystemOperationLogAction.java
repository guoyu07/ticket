package com.ticket.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.SystemOperationLog;
import com.ticket.pojo.SystemUser;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;

/**
 * 后台管理员操作日志控制器
 * 
 * @ClassName: SystemOperationLogAction
 * @Description: 提供后台管理员操作日志的相关操作方法.
 * @author HiSay
 * @date 2016-03-08 21:01:21
 * 
 */
public class SystemOperationLogAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	// 后台管理员操作日志的业务层
	@Resource
	private ISystemOperationLogService systemOperationLogService = null;
	// 后台管理员操作日志实体
	private SystemOperationLog systemOperationLog = null;
	// 主键
	private String id = null;
	// 管理员名称
	private String logName = null;
	// 操作内容
	private String logContent = null;
	// 操作时间
	private String logTime = null;
	// 操作IP
	private String logIp = null;

	/**
	 * 添加后台管理员操作日志
	 * 
	 * @Title: SystemOperationLogAction
	 * @Description:
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public void add(String logContent) throws ServiceException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Map session = ActionContext.getContext().getSession();
		SystemUser user = (SystemUser)session.get(ContextConstants.SCOPE_SYSTEM_USER);
		logName = user.getName();
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		logTime = sdf.format(date);
		
		logIp = request.getRemoteAddr();
		// 保存后台管理员操作日志实体
		systemOperationLogService.persist(logName, logContent,
				logTime, logIp, versionFlag);
	}

	/**
	 * 修改后台管理员操作日志
	 * 
	 * @Title: SystemOperationLogAction
	 * @Description:
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String edit() throws ServiceException {
		if (GeneralUtil.isNull(operationFlag)) {
			this.setSystemOperationLog(systemOperationLogService.queryById(
					SystemOperationLog.class.getSimpleName(), id));
			return "editSystemOperationLog";
		} else {
			// 非空验证.
			if (GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(logName)) {
				data = AjaxData.responseError(getText("logName.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(logContent)) {
				data = AjaxData.responseError(getText("logContent.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(logTime)) {
				data = AjaxData.responseError(getText("logTime.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(logIp)) {
				data = AjaxData.responseError(getText("logIp.required"));
				return JSON;
			}
			// 修改后台管理员操作日志实体
			boolean isSuc = systemOperationLogService.merge(id, logName,
					logContent, logTime, logIp, versionFlag);
			// 根据修改结果返回页面
			if (isSuc) {
				data = AjaxData.responseSuccess(getText("editSuccess"));
			} else {
				data = AjaxData.responseError(getText("editFailed"));
			}
			return JSON;
		}
	}

	/**
	 * 管理后台管理员操作日志实体
	 * 
	 * @Title: SystemOperationLogAction
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0]
				.getMethodName());
		this.setPageModule(systemOperationLogService.queryEntityByAdmin(
				versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageSystemOperationLog";
	}

	/**
	 * 查看回收站
	 * 
	 * @Title: SystemOperationLogAction
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(systemOperationLogService.queryRecycleEntity(
				versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleSystemOperationLog";
	}

	/**
	 * 逻辑删除后台管理员操作日志对象
	 * 
	 * @Title: SystemOperationLogAction
	 * @Description: 把后台管理员操作日志对象放入回收站.
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = systemOperationLogService.logicDeleteEntity(
				SystemOperationLog.class.getSimpleName(), id);
		if (isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}

	/**
	 * 物理删除后台管理员操作日志对象
	 * 
	 * @Title: SystemOperationLogAction
	 * @Description: 把配置对象实际从数据库里面删除.
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = systemOperationLogService.remove(id);
		if (isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}

	/**
	 * 还原一个后台管理员操作日志对象
	 * 
	 * @Title: SystemOperationLogAction
	 * @Description: 从回收站还原后台管理员操作日志对象
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = systemOperationLogService.restoreEntity(
				SystemOperationLog.class.getSimpleName(), id);
		if (isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}

	/**
	 * 审核后台管理员操作日志对象
	 * 
	 * @Title: SystemOperationLogAction
	 * @Description:
	 * @param @return
	 * @param @throws ServiceException 设定文件
	 * @return 返回类型
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = systemOperationLogService.auditEntity(
				SystemOperationLog.class.getSimpleName(), id, statusValue);
		if (isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}

	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * 
	 * @Title: SystemOperationLogAction
	 * @Description:
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = systemOperationLogService.batchOperationEntity(
				versionFlag, idsValue, batchOperationType, isChecked);
		if (isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}

	public SystemOperationLog getSystemOperationLog() {
		return systemOperationLog;
	}

	public void setSystemOperationLog(SystemOperationLog systemOperationLog) {
		this.systemOperationLog = systemOperationLog;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogName() {
		return logName;
	}

	public void setLogName(String logName) {
		this.logName = logName;
	}

	public String getLogContent() {
		return logContent;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}

	public String getLogTime() {
		return logTime;
	}

	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}

	public String getLogIp() {
		return logIp;
	}

	public void setLogIp(String logIp) {
		this.logIp = logIp;
	}
}
