package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.SecurityKey;
import com.ticket.service.ISecurityKeyService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 安全秘钥管理表控制器
 * @ClassName: SecurityKeyAction   
 * @Description:  提供安全秘钥管理表的相关操作方法. 
 * @author HiSay  
 * @date 2016-01-21 14:43:44
 *
 */
public class SecurityKeyAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//安全秘钥管理表的业务层
	@Resource private ISecurityKeyService securityKeyService = null;
	
	@Resource private ISystemOperationLogService logService = null;
	//安全秘钥管理表实体
	private SecurityKey securityKey = null;
	//主键
	private String id = null;
    //公钥
	private String publicKey = null;
    //密钥
	private String secretKey = null;
	//备注
	private String remark;
	
	/**
	 * 添加安全秘钥管理表
	 * @Title: SecurityKeyAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addSecurityKey";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(publicKey)) {
				data = AjaxData.responseError(getText("publicKey.required"));
				return JSON;
			}
			securityKey = securityKeyService.getByPublicKey(publicKey);
			if(securityKey != null){
				
				data = AjaxData.responseError(getText("publicKey.reqeat"));
				return JSON;
			}
			
			//保存安全秘钥管理表实体
			boolean isSuc = securityKeyService.persist(publicKey, remark, versionFlag);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增安全密钥";
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
	 * 修改安全秘钥管理表
	 * @Title: SecurityKeyAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setSecurityKey(securityKeyService.queryById(SecurityKey.class.getSimpleName(), id));
			return "editSecurityKey";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(publicKey)) {
				data = AjaxData.responseError(getText("publicKey.required"));
				return JSON;
			}
			//修改安全秘钥管理表实体
			boolean isSuc = securityKeyService.merge(id, publicKey, remark, versionFlag);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改安全密钥";
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
	 * 管理安全秘钥管理表实体
	 * @Title: SecurityKeyAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(securityKeyService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageSecurityKey";
	}
	
	/**
	 * 查看回收站
	 * @Title: SecurityKeyAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(securityKeyService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleSecurityKey";
	}
	
	/**
	 * 逻辑删除安全秘钥管理表对象
	 * @Title: SecurityKeyAction
	 * @Description: 把安全秘钥管理表对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = securityKeyService.logicDeleteEntity(SecurityKey.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除安全密钥";
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
	 * 物理删除安全秘钥管理表对象
	 * @Title: SecurityKeyAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = securityKeyService.remove(id);
		if(isSuc) {
			String logContent = "物理删除安全密钥";
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
	 * 还原一个安全秘钥管理表对象
	 * @Title: SecurityKeyAction
	 * @Description: 从回收站还原安全秘钥管理表对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = securityKeyService.restoreEntity(SecurityKey.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原安全密钥";
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
	 * 审核安全秘钥管理表对象
	 * @Title: SecurityKeyAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = securityKeyService.auditEntity(SecurityKey.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核安全密钥";
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
	 * @Title: SecurityKeyAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = securityKeyService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作安全密钥";
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
	
	public SecurityKey getSecurityKey() {
		return securityKey;
	}
	public void setSecurityKey(SecurityKey securityKey) {
		this.securityKey = securityKey;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public ISecurityKeyService getSecurityKeyService() {
		return securityKeyService;
	}

	public void setSecurityKeyService(ISecurityKeyService securityKeyService) {
		this.securityKeyService = securityKeyService;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
