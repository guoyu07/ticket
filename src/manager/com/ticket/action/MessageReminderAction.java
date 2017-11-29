package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.constants.JQueryUploadConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.MessageReminder;
import com.ticket.service.IMessageReminderService;
import com.ticket.util.GeneralUtil;

/**
 * 消息提醒控制器
 * @ClassName: MessageReminderAction   
 * @Description:  提供消息提醒的相关操作方法. 
 * @author HiSay  
 * @date 2015-11-17 16:10:53
 *
 */
public class MessageReminderAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//消息提醒的业务层
	@Resource private IMessageReminderService messageReminderService = null;
	//消息提醒实体
	private MessageReminder messageReminder = null;
	//主键
	private String id = null;
    //会员id
	private String member_id = null;
    //标题
	private String title = null;
    //提醒内容
	private String content = null;
    //提醒时间
	private String reminderTime = null;
	
	/**
	 * 添加消息提醒
	 * @Title: MessageReminderAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addMessageReminder";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(title)) {
				data = AjaxData.responseError(getText("title.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(content)) {
				data = AjaxData.responseError(getText("content.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(reminderTime)) {
				data = AjaxData.responseError(getText("reminderTime.required"));
				return JSON;
			}
			//保存消息提醒实体
			boolean isSuc = messageReminderService.persist(member_id, title,
					getSinglePictureUrlFromJQueryUpLoader(fileNames, directory, JQueryUploadConstants.PICTURE_TYPE_DEFAULT),
					content, reminderTime, versionFlag);
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
	 * 修改消息提醒
	 * @Title: MessageReminderAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setMessageReminder(messageReminderService.queryById(MessageReminder.class.getSimpleName(), id));
			return "editMessageReminder";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(title)) {
				data = AjaxData.responseError(getText("title.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(content)) {
				data = AjaxData.responseError(getText("content.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(reminderTime)) {
				data = AjaxData.responseError(getText("reminderTime.required"));
				return JSON;
			}
			//修改消息提醒实体
			boolean isSuc = messageReminderService.merge(id, member_id, title,
					getSinglePictureUrlFromJQueryUpLoader(fileNames, directory, JQueryUploadConstants.PICTURE_TYPE_DEFAULT),
							content, reminderTime,  versionFlag);
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
	 * 管理消息提醒实体
	 * @Title: MessageReminderAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(messageReminderService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageMessageReminder";
	}
	
	/**
	 * 查看回收站
	 * @Title: MessageReminderAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(messageReminderService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleMessageReminder";
	}
	
	/**
	 * 逻辑删除消息提醒对象
	 * @Title: MessageReminderAction
	 * @Description: 把消息提醒对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = messageReminderService.logicDeleteEntity(MessageReminder.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除消息提醒对象
	 * @Title: MessageReminderAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = messageReminderService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个消息提醒对象
	 * @Title: MessageReminderAction
	 * @Description: 从回收站还原消息提醒对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = messageReminderService.restoreEntity(MessageReminder.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核消息提醒对象
	 * @Title: MessageReminderAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = messageReminderService.auditEntity(MessageReminder.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: MessageReminderAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = messageReminderService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	/**
	 * 批量彻底删除消息提醒
	 * @return
	 * @throws ServiceException
	 */
	public String batchRealDelete() throws ServiceException {
		boolean isSuc = messageReminderService.batchRealDelete(idsValue, versionFlag);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public MessageReminder getMessageReminder() {
		return messageReminder;
	}
	public void setMessageReminder(MessageReminder messageReminder) {
		this.messageReminder = messageReminder;
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
	public String getReminderTime() {
		return reminderTime;
	}
	public void setReminderTime(String reminderTime) {
		this.reminderTime = reminderTime;
	}
}
