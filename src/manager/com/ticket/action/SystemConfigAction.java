package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.SystemConfig;
import com.ticket.service.ISystemConfigService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 系统配置控制器
 * @ClassName: SystemConfigAction   
 * @Description:  提供系统配置的相关操作方法. 
 * @author HiSay  
 * @date 2014-10-11 08:26:42
 *
 */
public class SystemConfigAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//系统配置的业务层
	@Resource 
	private ISystemConfigService systemConfigService = null;
	@Resource private ISystemOperationLogService logService = null;
	//系统配置实体
	private SystemConfig systemConfig = null;
	
	/**
	 * 设置系统配置
	 * @Title: SystemConfigAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String setting() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setSystemConfig(systemConfigService.querySystemConfig()) ;
			return "setSystemConfig";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(systemConfig.getName())) {
				data = getText("name.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(systemConfig.getKeyword())) {
				data = getText("keyword.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(systemConfig.getDescription())) {
				data = getText("description.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(systemConfig.getIsAllowSignIn())) {
				data = getText("isAllowSignIn.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(systemConfig.getIsAllowCreate())) {
				data = getText("isAllowCreate.required");
				return TEXT;
			}
			//保存系统配置实体
			boolean isSuc = systemConfigService.persistOrMerge(systemConfig);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增系统配置";
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
	
	public SystemConfig getSystemConfig() {
		return systemConfig;
	}
	public void setSystemConfig(SystemConfig systemConfig) {
		this.systemConfig = systemConfig;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ISystemConfigService getSystemConfigService() {
		return systemConfigService;
	}
	public void setSystemConfigService(ISystemConfigService systemConfigService) {
		this.systemConfigService = systemConfigService;
	}
}
