package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.NewsComment;
import com.ticket.service.INewsCommentService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 新闻评论控制器
 * @ClassName: NewsCommentAction   
 * @Description:  提供新闻评论的相关操作方法. 
 * @author HiSay  
 * @date 2015-10-13 17:16:37
 *
 */
public class NewsCommentAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//新闻评论的业务层
	@Resource private INewsCommentService newsCommentService = null;
	
	@Resource private ISystemOperationLogService logService = null;
	//新闻评论实体
	private NewsComment newsComment = null;
	//主键
	private String id = null;
    //新闻id
	private String news_id = null;
    //会员id
	private String member_id = null;
    //会员IP
	private String memberIp = null;
    //评几颗星
	private Integer star = null;
    //评论内容
	private String content = null;
	
	/**
	 * 添加新闻评论
	 * @Title: NewsCommentAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addNewsComment";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(news_id)) {
				data = getText("news_id.required");
				return TEXT;
			}
			/*if(GeneralUtil.isNull(member_id)) {
				data = getText("member_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(memberIp)) {
				data = getText("memberIp.required"));
				return JSON;
			}*/
			if(GeneralUtil.isNull(star)) {
				data = getText("star.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(content)) {
				data = getText("content.required");
				return TEXT;
			}
			//保存新闻评论实体
			boolean isSuc = newsCommentService.persist(news_id, member_id, memberIp, star, content, versionFlag);
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
	 * 修改新闻评论
	 * @Title: NewsCommentAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setNewsComment(newsCommentService.queryById(NewsComment.class.getSimpleName(), id));
			return "editNewsComment";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = getText("id.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(news_id)) {
				data = getText("news_id.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(member_id)) {
				data = getText("member_id.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(memberIp)) {
				data = getText("memberIp.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(star)) {
				data = getText("star.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(content)) {
				data = getText("content.required");
				return TEXT;
			}
			//修改新闻评论实体
			boolean isSuc = newsCommentService.merge(id, news_id, member_id, memberIp, star, content,  versionFlag);
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
	 * 管理新闻评论实体
	 * @Title: NewsCommentAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(newsCommentService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageNewsComment";
	}
	
	/**
	 * 查看回收站
	 * @Title: NewsCommentAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(newsCommentService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleNewsComment";
	}
	
	/**
	 * 逻辑删除新闻评论对象
	 * @Title: NewsCommentAction
	 * @Description: 把新闻评论对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = newsCommentService.logicDeleteEntity(NewsComment.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除新闻评论信息";
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
	 * 物理删除新闻评论对象
	 * @Title: NewsCommentAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = newsCommentService.remove(id);
		if(isSuc) {
			String logContent = "物理删除新闻评论信息";
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
	 * 还原一个新闻评论对象
	 * @Title: NewsCommentAction
	 * @Description: 从回收站还原新闻评论对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = newsCommentService.restoreEntity(NewsComment.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原新闻评论信息";
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
	 * 审核新闻评论对象
	 * @Title: NewsCommentAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = newsCommentService.auditEntity(NewsComment.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核新闻评论信息";
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
	 * @Title: NewsCommentAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = newsCommentService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作新闻评论信息";
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
	 * 批量彻底删除新闻评论信息
	 * @return
	 * @throws ServiceException
	 */
	public String batchRealDelete() throws ServiceException{
		boolean isSuc = newsCommentService.batchRealDelete(idsValue, versionFlag);
		if(isSuc) {
			String logContent = "批量删除新闻评论信息";
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
	
	public NewsComment getNewsComment() {
		return newsComment;
	}
	public void setNewsComment(NewsComment newsComment) {
		this.newsComment = newsComment;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNews_id() {
		return news_id;
	}
	public void setNews_id(String news_id) {
		this.news_id = news_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMemberIp() {
		return memberIp;
	}
	public void setMemberIp(String memberIp) {
		this.memberIp = memberIp;
	}
	public Integer getStar() {
		return star;
	}
	public void setStar(Integer star) {
		this.star = star;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
