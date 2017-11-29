package com.ticket.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.Feedback;
import com.ticket.pojo.FeedbackReply;
import com.ticket.pojo.Member;
import com.ticket.service.IFeedbackReplyService;
import com.ticket.service.IFeedbackService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 公测反馈控制器
 * @ClassName: FeedbackAction   
 * @Description:  提供公测反馈的相关操作方法. 
 * @author HiSay  
 * @date 2016-08-15 15:10:43
 *
 */
public class FeedbackAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//公测反馈的业务层
	@Resource private IFeedbackService feedbackService;
	@Resource private ISystemOperationLogService logService = null;
	@Resource private IFeedbackReplyService feedbackReplyService = null;
	//公测反馈实体
	private Feedback feedback;
	//主键
	private String id;
    //反馈人员
	private Member member;
    //问题描述
	private String description;
    //手机号码
	private String phone;
	//图片
	private String images;
	
	private InputStream inputStream;
	
	private String fileName;	
	
	private Date startTime;
	
	private Date endTime;
	/**
	 * 详细信息
	 * @return
	 * @throws ServiceException
	 */
	public String detail() throws ServiceException{
		feedback = feedbackService.queryById(Feedback.class.getName(), id);
		List<FeedbackReply> feedbackReplies = feedbackReplyService.queryByFeedbackId(id);
		for(FeedbackReply feedbackReply:feedbackReplies){
			if(GeneralUtil.isNotNull(feedbackReply.getSystemUser())){
				feedbackReply.setRend(true);
				feedbackReplyService.merge(feedbackReply);
			}
		}
		return "detail";
	}
	
	/**
	 * 反馈列表
	 * @return
	 * @throws ServiceException
	 */
	public String list() throws ServiceException{
		pageSize = 20;
		Member member = (Member)ActionContext.getContext().getSession().get(ContextConstants.SCOPE_MEMBER);
		List<Feedback> feedbacks = feedbackService.queryByMember(member);
		ActionContext.getContext().put("feedbacks", feedbacks);
		//直接返回页面
		if("html".equals(operationFlag)){
					
			return "listItem";
		}
				
		//只返回数据
		JSONArray array = new JSONArray();
		
		for(Feedback feedback:feedbacks){
			JSONObject object = new JSONObject();
			
			object.put("content", feedback.getDescription());
			object.put("images", feedback.getImages());
			
			array.add(object);
		}
		data = array.toString();
		return TEXT;
	}
	/**
	 * 我的反馈
	 * @return
	 * @throws ServiceException
	 */
	public String myFeedBack() throws ServiceException{
		Member member = (Member)ActionContext.getContext().getSession().get(ContextConstants.SCOPE_MEMBER);
		long count = feedbackService.count(member);
		List<Feedback> feedbacks = feedbackService.queryByMember(member);
		ActionContext.getContext().put("count", count);
		ActionContext.getContext().put("feedbacks", feedbacks);
		return "myFeedBack";
	}
	/**
	 * 我要反馈
	 * @return
	 * @throws ServiceException
	 */
	public String goToFeedBack() throws ServiceException{
		
		return "feedBackPage";
	}
	
	/**
	 * 反馈
	 * @return
	 * @throws ServiceException
	 */
	public String feedBack() throws ServiceException{
		Member member = (Member)ActionContext.getContext().getSession().get(ContextConstants.SCOPE_MEMBER);
		//保存公测反馈实体
		boolean isSuc = false;
		try {
			isSuc = feedbackService.persist(member, description, phone,images, versionFlag);
		} catch (Exception e) {
			e.printStackTrace();
			data = AjaxData.responseError(getText("addFailed"));
		}
		//根据保存结果返回页面
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("addSuccess"));
		} else {
			data = AjaxData.responseError(getText("addFailed"));
		}
		return JSON;
	}
	/**
	 * 添加公测反馈
	 * @Title: FeedbackAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addFeedback";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(member)) {
				data = AjaxData.responseError(getText("member.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(description)) {
				data = AjaxData.responseError(getText("description.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(phone)) {
				data = AjaxData.responseError(getText("phone.required"));
				return JSON;
			}
			//保存公测反馈实体
			boolean isSuc = feedbackService.persist(member, description, phone,images, versionFlag);
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
	 * 修改公测反馈
	 * @Title: FeedbackAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setFeedback(feedbackService.queryById(Feedback.class.getSimpleName(), id));
			return "editFeedback";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
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
			if(GeneralUtil.isNull(phone)) {
				data = AjaxData.responseError(getText("phone.required"));
				return JSON;
			}
			//修改公测反馈实体
			boolean isSuc = feedbackService.merge(id, member, description, phone, images, versionFlag);
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
	 * 管理公测反馈实体
	 * @Title: FeedbackAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(feedbackService.queryEntityByAdmin(startTime,endTime,versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageFeedback";
	}
	
	/**
	 * 查看回收站
	 * @Title: FeedbackAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(feedbackService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleFeedback";
	}
	
	/**
	 * 逻辑删除公测反馈对象
	 * @Title: FeedbackAction
	 * @Description: 把公测反馈对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = feedbackService.logicDeleteEntity(Feedback.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除公测反馈对象
	 * @Title: FeedbackAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = feedbackService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个公测反馈对象
	 * @Title: FeedbackAction
	 * @Description: 从回收站还原公测反馈对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = feedbackService.restoreEntity(Feedback.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核公测反馈对象
	 * @Title: FeedbackAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = feedbackService.auditEntity(Feedback.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: FeedbackAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = feedbackService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	/**
	 * 下载全部反馈
	 * @return
	 * @throws Exception
	 */
	public String batchDownLoad() throws Exception{
		String logContent = "下载反馈信息";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		String destFilePath = feedbackService.createJxls(startTime, endTime);
		File file = new File(destFilePath);
		fileName = file.getName();
		inputStream = new FileInputStream(file);
		return "download";
	}
	
	public Feedback getFeedback() {
		return feedback;
	}
	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
