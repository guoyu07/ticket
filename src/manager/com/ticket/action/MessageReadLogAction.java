package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.MessageReadLog;
import com.ticket.service.IMessageReadLogService;
import com.ticket.util.GeneralUtil;

/**
 * 消息阅读日志控制器
 * @ClassName: MessageReadLogAction   
 * @Description:  提供消息阅读日志的相关操作方法. 
 * @author HiSay  
 * @date 2015-11-30 14:14:30
 *
 */
public class MessageReadLogAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//消息阅读日志的业务层
	@Resource private IMessageReadLogService messageReadLogService = null;
	//消息阅读日志实体
	private MessageReadLog messageReadLog = null;
	//主键
	private String id = null;
    //会员id
	private String member_id = null;
    //消息id
	private String message_id = null;
	
	/**
	 * 添加消息阅读日志
	 * @Title: MessageReadLogAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addMessageReadLog";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(member_id)) {
				data = AjaxData.responseError(getText("member_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(message_id)) {
				data = AjaxData.responseError(getText("message_id.required"));
				return JSON;
			}
			//保存消息阅读日志实体
			boolean isSuc = messageReadLogService.persist(member_id, message_id, versionFlag);
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
	 * 修改消息阅读日志
	 * @Title: MessageReadLogAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setMessageReadLog(messageReadLogService.queryById(MessageReadLog.class.getSimpleName(), id));
			return "editMessageReadLog";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(member_id)) {
				data = AjaxData.responseError(getText("member_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(message_id)) {
				data = AjaxData.responseError(getText("message_id.required"));
				return JSON;
			}
			//修改消息阅读日志实体
			boolean isSuc = messageReadLogService.merge(id, member_id, message_id,  versionFlag);
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
	 * 管理消息阅读日志实体
	 * @Title: MessageReadLogAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(messageReadLogService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageMessageReadLog";
	}
	
	/**
	 * 查看回收站
	 * @Title: MessageReadLogAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(messageReadLogService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleMessageReadLog";
	}
	
	/**
	 * 逻辑删除消息阅读日志对象
	 * @Title: MessageReadLogAction
	 * @Description: 把消息阅读日志对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = messageReadLogService.logicDeleteEntity(MessageReadLog.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除消息阅读日志对象
	 * @Title: MessageReadLogAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = messageReadLogService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个消息阅读日志对象
	 * @Title: MessageReadLogAction
	 * @Description: 从回收站还原消息阅读日志对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = messageReadLogService.restoreEntity(MessageReadLog.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核消息阅读日志对象
	 * @Title: MessageReadLogAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = messageReadLogService.auditEntity(MessageReadLog.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: MessageReadLogAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = messageReadLogService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public MessageReadLog getMessageReadLog() {
		return messageReadLog;
	}
	public void setMessageReadLog(MessageReadLog messageReadLog) {
		this.messageReadLog = messageReadLog;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMessage_id() {
		return message_id;
	}
	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}
}
