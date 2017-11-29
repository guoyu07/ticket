package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.SystemUserOperation;
import com.ticket.service.ISystemUserOperationService;
import com.ticket.util.GeneralUtil;

/**
 * 管理员操作日志控制器
 * @ClassName: SystemUserOperationAction   
 * @Description:  提供管理员操作日志的相关操作方法. 
 * @author HiSay  
 * @date 2014-10-15 13:12:06
 *
 */
public class SystemUserOperationAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//管理员操作日志的业务层
	@Resource private ISystemUserOperationService systemUserOperationService = null;
	//管理员操作日志实体
	private SystemUserOperation systemUserOperation = null;
	//主键
	private String id = null;
    //操作内容
	private String content = null;
    //操作IP地址
	private String ip = null;
    //所属管理员
	private String systemUser_id = null;
	
	/**
	 * 添加管理员操作日志
	 * @Title: SystemUserOperationAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addSystemUserOperation";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(content)) {
				data = getText("content.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(ip)) {
				data = getText("ip.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(systemUser_id)) {
				data = getText("systemUser_id.required");
				return TEXT;
			}
			//保存管理员操作日志实体
			boolean isSuc = systemUserOperationService.persist(content, ip, systemUser_id, versionFlag);
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
	 * 修改管理员操作日志
	 * @Title: SystemUserOperationAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setSystemUserOperation(systemUserOperationService.queryById(SystemUserOperation.class.getSimpleName(), id));
			return "editSystemUserOperation";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = getText("id.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(content)) {
				data = getText("content.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(ip)) {
				data = getText("ip.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(systemUser_id)) {
				data = getText("systemUser_id.required");
				return TEXT;
			}
			//修改管理员操作日志实体
			boolean isSuc = systemUserOperationService.merge(id, content, ip, systemUser_id,  versionFlag);
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
	 * 管理管理员操作日志实体
	 * @Title: SystemUserOperationAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(systemUserOperationService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageSystemUserOperation";
	}
	
	/**
	 * 查看回收站
	 * @Title: SystemUserOperationAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(systemUserOperationService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleSystemUserOperation";
	}
	
	/**
	 * 逻辑删除管理员操作日志对象
	 * @Title: SystemUserOperationAction
	 * @Description: 把管理员操作日志对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = systemUserOperationService.logicDeleteEntity(SystemUserOperation.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除管理员操作日志对象
	 * @Title: SystemUserOperationAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = systemUserOperationService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个管理员操作日志对象
	 * @Title: SystemUserOperationAction
	 * @Description: 从回收站还原管理员操作日志对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = systemUserOperationService.restoreEntity(SystemUserOperation.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核管理员操作日志对象
	 * @Title: SystemUserOperationAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = systemUserOperationService.auditEntity(SystemUserOperation.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: SystemUserOperationAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = systemUserOperationService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public SystemUserOperation getSystemUserOperation() {
		return systemUserOperation;
	}
	public void setSystemUserOperation(SystemUserOperation systemUserOperation) {
		this.systemUserOperation = systemUserOperation;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getSystemUser_id() {
		return systemUser_id;
	}
	public void setSystemUser_id(String systemUser_id) {
		this.systemUser_id = systemUser_id;
	}
}
