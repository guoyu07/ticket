package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.MessageTemplate;
import com.ticket.service.IMessageTemplateService;
import com.ticket.util.GeneralUtil;

/**
 * 消息模板控制器
 * @ClassName: MessageTemplateAction   
 * @Description:  提供消息模板的相关操作方法. 
 * @author HiSay  
 * @date 2016-08-09 10:50:43
 *
 */
public class MessageTemplateAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//消息模板的业务层
	@Resource private IMessageTemplateService messageTemplateService;
	//消息模板实体
	private MessageTemplate messageTemplate;
	//主键
	private String id;
	//消息内容
	private String title;
	//消息内容
	private String url;
    //消息内容
	private String content;
    //描述
	private String remark;
	
	/**
	 * 添加消息模板
	 * @Title: MessageTemplateAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addMessageTemplate";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(content)) {
				data = AjaxData.responseError(getText("content.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(remark)) {
				data = AjaxData.responseError(getText("remark.required"));
				return JSON;
			}
			//保存消息模板实体
			boolean isSuc = messageTemplateService.persist(title, url, content, remark, versionFlag);
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
	 * 修改消息模板
	 * @Title: MessageTemplateAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setMessageTemplate(messageTemplateService.queryById(MessageTemplate.class.getSimpleName(), id));
			return "editMessageTemplate";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(content)) {
				data = AjaxData.responseError(getText("content.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(remark)) {
				data = AjaxData.responseError(getText("remark.required"));
				return JSON;
			}
			//修改消息模板实体
			boolean isSuc = messageTemplateService.merge(id, title, url, content, remark,  versionFlag);
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
	 * 管理消息模板实体
	 * @Title: MessageTemplateAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(messageTemplateService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageMessageTemplate";
	}
	
	/**
	 * 查看回收站
	 * @Title: MessageTemplateAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(messageTemplateService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleMessageTemplate";
	}
	
	/**
	 * 逻辑删除消息模板对象
	 * @Title: MessageTemplateAction
	 * @Description: 把消息模板对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = messageTemplateService.logicDeleteEntity(MessageTemplate.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除消息模板对象
	 * @Title: MessageTemplateAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = messageTemplateService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个消息模板对象
	 * @Title: MessageTemplateAction
	 * @Description: 从回收站还原消息模板对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = messageTemplateService.restoreEntity(MessageTemplate.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核消息模板对象
	 * @Title: MessageTemplateAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = messageTemplateService.auditEntity(MessageTemplate.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: MessageTemplateAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = messageTemplateService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public MessageTemplate getMessageTemplate() {
		return messageTemplate;
	}
	public void setMessageTemplate(MessageTemplate messageTemplate) {
		this.messageTemplate = messageTemplate;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
