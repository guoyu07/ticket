package com.ticket.action;

import java.util.Date;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjServiceCodeOperation;
import com.ticket.pojo.Pagination;
import com.ticket.service.IBjdjServiceCodeOperationService;
import com.ticket.util.GeneralUtil;

/**
 * 服务码日志表控制器
 * @ClassName: BjdjServiceCodeLogAction   
 * @Description:  提供服务码日志表的相关操作方法. 
 * @author HiSay  
 * @date 2015-10-23 15:17:18
 *
 */
public class BjdjServiceCodeLogAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//服务码日志表的业务层
	@Resource 
	private IBjdjServiceCodeOperationService bjdjServiceCodeLogService = null;
	
	
	//服务码日志表实体
	private BjdjServiceCodeOperation bjdjServiceCodeLog = null;
	//主键
	private String id = null;
    //操作名称（字典）
	private String operation = null;
    //日期
	private Date time = null;
    //用户ID
	private String member_id = null;
    //操作用户ID
	private String systemUser_id = null;
	
	
	
	/**
	 * 添加服务码日志表
	 * @Title: BjdjServiceCodeLogAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addBjdjServiceCodeLog";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(operation)) {
				data = getText("operation.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(time)) {
				data = getText("time.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(member_id)) {
				data = getText("member_id.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(systemUser_id)) {
				data = getText("systemUser_id.required");
				return TEXT;
			}
			//保存服务码日志表实体
//			boolean isSuc = bjdjServiceCodeLogService.persist(operation, time, member_id, systemUser_id,"", versionFlag);
//			//根据保存结果返回页面
//			if(isSuc) {
//				data = AjaxData.responseSuccess(getText("addSuccess"));
//			} else {
//				data = AjaxData.responseError(getText("addFailed"));
//			}
			return JSON;
		}
	}
	
	/**
	 * 修改服务码日志表
	 * @Title: BjdjServiceCodeLogAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setBjdjServiceCodeLog(bjdjServiceCodeLogService.queryById(BjdjServiceCodeOperation.class.getSimpleName(), id));
			return "editBjdjServiceCodeLog";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = getText("id.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(operation)) {
				data = getText("operation.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(time)) {
				data = getText("time.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(member_id)) {
				data = getText("member_id.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(systemUser_id)) {
				data = getText("systemUser_id.required");
				return TEXT;
			}
			//修改服务码日志表实体
//			boolean isSuc = bjdjServiceCodeLogService.merge(id, operation, time, member_id, systemUser_id,  versionFlag);
//			//根据修改结果返回页面
//			if(isSuc) {
//				data = AjaxData.responseSuccess(getText("editSuccess"));
//			} else {
//				data = AjaxData.responseError(getText("editFailed"));
//			}
			return JSON;
		}
	}
	
	/**
	 * 管理服务码日志表实体
	 * @Title: BjdjServiceCodeLogAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		
		Pagination pg = bjdjServiceCodeLogService.queryEntityByUserId(null, ContextConstants.ADMIN_COMMON_PAGE_SIZE, super.getSessionAdminUser().getId());
		this.setPageModule(pg);
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageBjdjServiceCodeLog";
	}
	
	/**
	 * 查看回收站
	 * @Title: BjdjServiceCodeLogAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(bjdjServiceCodeLogService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleBjdjServiceCodeLog";
	}
	
	/**
	 * 逻辑删除服务码日志表对象
	 * @Title: BjdjServiceCodeLogAction
	 * @Description: 把服务码日志表对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = bjdjServiceCodeLogService.logicDeleteEntity(BjdjServiceCodeOperation.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除服务码日志表对象
	 * @Title: BjdjServiceCodeLogAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = bjdjServiceCodeLogService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个服务码日志表对象
	 * @Title: BjdjServiceCodeLogAction
	 * @Description: 从回收站还原服务码日志表对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = bjdjServiceCodeLogService.restoreEntity(BjdjServiceCodeOperation.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核服务码日志表对象
	 * @Title: BjdjServiceCodeLogAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = bjdjServiceCodeLogService.auditEntity(BjdjServiceCodeOperation.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: BjdjServiceCodeLogAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = bjdjServiceCodeLogService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除对象
	 * @Title: BjdjQueueAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String batchRealDelete() throws ServiceException {
		
		boolean isSuc = bjdjServiceCodeLogService.batchRealDelete(BjdjServiceCodeOperation.class, idsValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	public BjdjServiceCodeOperation getBjdjServiceCodeLog() {
		return bjdjServiceCodeLog;
	}
	public void setBjdjServiceCodeLog(BjdjServiceCodeOperation bjdjServiceCodeLog) {
		this.bjdjServiceCodeLog = bjdjServiceCodeLog;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getsystemUser_id() {
		return systemUser_id;
	}
	public void setsystemUser_id(String systemUser_id) {
		this.systemUser_id = systemUser_id;
	}
}
