package com.ticket.action;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjComment;
import com.ticket.pojo.Pagination;
import com.ticket.service.IBjdjCommentService;
import com.ticket.service.IBjdjOrderService;
import com.ticket.service.IMemberService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 便捷登机评论表控制器
 * @ClassName: BjdjCommentAction   
 * @Description:  提供便捷登机评论表的相关操作方法. 
 * @author HiSay  
 * @date 2015-10-23 15:24:09
 */
public class BjdjCommentAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//便捷登机评论表的业务层
	@Resource 
	private IBjdjCommentService commentService;
	@Resource
	private IMemberService memberService;
	@Resource
	private IBjdjOrderService orderService;
	@Resource private ISystemOperationLogService logService;
	
	//便捷登机评论表实体
	private BjdjComment bjdjComment;
	//主键
	private String id;
    //评论内容
	private String content;
    //用户ID
	private String member_id;
    //评论时所在的IP地址
	private String ip;
    //评论日期
	private Date time;
	//是否显示
	private boolean showName;
	//回复
	private String feedback;
	
	/**
	 * 生成报表
	 * @return
	 */
	public String generate(){
		
		try {
			response.setCharacterEncoding("utf-8");
			String fileName = URLEncoder.encode("评论报表.xlsx", "utf-8"); //防止中文乱码
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			commentService.generateReport(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 添加便捷登机评论表
	 * @Title: BjdjCommentAction
	 * @Description:   
	 * @return
	 * @throws ServiceException   
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addBjdjComment";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(content)) {
				data = getText("content.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(member_id)) {
				data = getText("member_id.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(ip)) {
				data = getText("ip.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(time)) {
				data = getText("time.required");
				return TEXT;
			}
			//保存便捷登机评论表实体
			boolean isSuc = false;//bjdjCommentService.persist(content, member_id, ip, time, versionFlag);
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
	 * 修改便捷登机评论表
	 * @Title: BjdjCommentAction
	 * @Description:   
	 * @return
	 * @throws ServiceException    
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "editBjdjComment";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = getText("id.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(feedback)) {
				data = getText("feedback.required");
				return TEXT;
			}
			//修改便捷登机评论表实体
			boolean isSuc = commentService.feedback(id, getSessionAdminUser(), feedback);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "回复便捷登机评价";
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
	 * 管理便捷登机评论表实体
	 * @Title: BjdjCommentAction
	 * @Description:    
	 * @return
	 * @throws ServiceException    
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		Pagination page = commentService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		
		this.setPageModule(page);
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageBjdjComment";
	}
	
	/**
	 * 查看回收站
	 * @Title: BjdjCommentAction
	 * @Description:    
	 * @return
	 * @throws ServiceException   
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(commentService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleBjdjComment";
	}
	
	/**
	 * 逻辑删除便捷登机评论表对象
	 * @Title: BjdjCommentAction
	 * @Description: 把便捷登机评论表对象放入回收站.   
	 * @return
	 * @throws ServiceException    
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = commentService.logicDeleteEntity(BjdjComment.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除便捷登机评价";
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
	 * 物理删除便捷登机评论表对象
	 * @Title: BjdjCommentAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @return
	 * @throws ServiceException   
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = commentService.remove(id);
		if(isSuc) {
			String logContent = "物理删除便捷登机评价";
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
	 * 还原一个便捷登机评论表对象
	 * @Title: BjdjCommentAction
	 * @Description: 从回收站还原便捷登机评论表对象   
	 * @return
	 * @throws ServiceException    
	 */
	public String restore() throws ServiceException {
		boolean isSuc = commentService.restoreEntity(BjdjComment.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原便捷登机评价";
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
	 * 审核便捷登机评论表对象
	 * @Title: BjdjCommentAction
	 * @Description:  
	 * @throws ServiceException    设定文件   
	 * @return     返回类型   
	 */
	public String audit() throws ServiceException {
		boolean isSuc = commentService.auditEntity(BjdjComment.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核便捷登机评价";
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
	 * @Title: BjdjCommentAction
	 * @Description:    
	 * @return
	 * @throws ServiceException 
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = commentService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作便捷登机评价";
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
	
	/**
	 * 物理删除评论表对象
	 * @Title: BjdjQueueAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @return
	 * @throws ServiceException   
	 */
	public String batchRealDelete() throws ServiceException {
		
		boolean isSuc = commentService.batchRealDelete(BjdjComment.class, idsValue);
		if(isSuc) {
			String logContent = "批量删除便捷登机评价";
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
	
	public BjdjComment getBjdjComment() {
		return bjdjComment;
	}
	public void setBjdjComment(BjdjComment bjdjComment) {
		this.bjdjComment = bjdjComment;
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
	public String getOrder_id() {
		return member_id;
	}
	public void setOrder_id(String member_id) {
		this.member_id = member_id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	public boolean isShowName() {
		return showName;
	}

	public void setShowName(boolean showName) {
		this.showName = showName;
	}

	public IBjdjCommentService getBjdjCommentService() {
		return commentService;
	}

	public void setBjdjCommentService(IBjdjCommentService bjdjCommentService) {
		this.commentService = bjdjCommentService;
	}

	public IMemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(IMemberService memberService) {
		this.memberService = memberService;
	}

	public IBjdjOrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(IBjdjOrderService orderService) {
		this.orderService = orderService;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
}
