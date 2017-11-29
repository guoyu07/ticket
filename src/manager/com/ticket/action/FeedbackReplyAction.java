package com.ticket.action;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.Feedback;
import com.ticket.pojo.FeedbackReply;
import com.ticket.pojo.Member;
import com.ticket.pojo.SystemUser;
import com.ticket.service.IFeedbackReplyService;
import com.ticket.service.IFeedbackService;
import com.ticket.util.GeneralUtil;

/**
 * 反馈与回复控制器
 * @ClassName: FeedbackReplyAction   
 * @Description:  提供反馈与回复的相关操作方法. 
 * @author HiSay  
 * @date 2016-08-15 15:11:31
 *
 */
public class FeedbackReplyAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//反馈与回复的业务层
	@Resource private IFeedbackReplyService feedbackReplyService;
	@Resource private IFeedbackService feedbackService;
	//反馈与回复实体
	private FeedbackReply feedbackReply;
	//主键
	private String id;
    //所属反馈问题
	private Feedback feekBack;
    //反馈人员
	private Member member;
    //问题描述
	private String description;
    //客服人员
	private SystemUser systemUser;
    //回复内容
	private String replyContent;
	
	private String feedback_id;
	
	private String images;
	
	/**
	 * 追加反馈
	 * @return
	 * @throws ServiceException
	 */
	public String addFeedback() throws ServiceException{
		
		return "addFeedback";
	}
	
	/**
	 * 添加反馈与回复
	 * @Title: FeedbackReplyAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			Feedback feedback = feedbackService.queryById(Feedback.class.getName(), feedback_id);
			ActionContext.getContext().put("feedback", feedback);
			return "addFeedbackReply";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(feedback_id)) {
				data = AjaxData.responseError(getText("feekBack.required"));
				return JSON;
			}
			Member member = (Member)ActionContext.getContext().getSession().get(ContextConstants.SCOPE_MEMBER);
			SystemUser systemUser = (SystemUser)ActionContext.getContext().getSession().get(ContextConstants.SCOPE_SYSTEM_USER);
			//保存反馈与回复实体
			boolean isSuc = feedbackReplyService.persist(feedback_id, member, description, systemUser, replyContent,images, versionFlag);
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
	 * 修改反馈与回复
	 * @Title: FeedbackReplyAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setFeedbackReply(feedbackReplyService.queryById(FeedbackReply.class.getSimpleName(), id));
			return "editFeedbackReply";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(feekBack)) {
				data = AjaxData.responseError(getText("feekBack.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(member)) {
				data = AjaxData.responseError(getText("member.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(description)) {
				data = AjaxData.responseError(getText("description.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(systemUser)) {
				data = AjaxData.responseError(getText("systemUser.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(replyContent)) {
				data = AjaxData.responseError(getText("replyContent.required"));
				return JSON;
			}
			//修改反馈与回复实体
			boolean isSuc = feedbackReplyService.merge(id, feekBack, member, description, systemUser, replyContent,  versionFlag);
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
	 * 管理反馈与回复实体
	 * @Title: FeedbackReplyAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(feedbackReplyService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageFeedbackReply";
	}
	
	/**
	 * 查看回收站
	 * @Title: FeedbackReplyAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(feedbackReplyService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleFeedbackReply";
	}
	
	/**
	 * 逻辑删除反馈与回复对象
	 * @Title: FeedbackReplyAction
	 * @Description: 把反馈与回复对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = feedbackReplyService.logicDeleteEntity(FeedbackReply.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除反馈与回复对象
	 * @Title: FeedbackReplyAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = feedbackReplyService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个反馈与回复对象
	 * @Title: FeedbackReplyAction
	 * @Description: 从回收站还原反馈与回复对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = feedbackReplyService.restoreEntity(FeedbackReply.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核反馈与回复对象
	 * @Title: FeedbackReplyAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = feedbackReplyService.auditEntity(FeedbackReply.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: FeedbackReplyAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = feedbackReplyService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public FeedbackReply getFeedbackReply() {
		return feedbackReply;
	}
	public void setFeedbackReply(FeedbackReply feedbackReply) {
		this.feedbackReply = feedbackReply;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Feedback getFeekBack() {
		return feekBack;
	}
	public void setFeekBack(Feedback feekBack) {
		this.feekBack = feekBack;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public SystemUser getSystemUser() {
		return systemUser;
	}
	public void setSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getFeedback_id() {
		return feedback_id;
	}

	public void setFeedback_id(String feedback_id) {
		this.feedback_id = feedback_id;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}
}
