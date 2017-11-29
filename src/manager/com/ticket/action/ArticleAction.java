package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.Article;
import com.ticket.service.IArticleService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 文章信息控制器
 * @ClassName: ArticleAction   
 * @Description:  提供文章信息的相关操作方法. 
 * @author HiSay  
 * @date 2015-10-13 10:05:09
 *
 */
public class ArticleAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//文章信息的业务层
	@Resource private IArticleService articleService = null;
	@Resource private ISystemOperationLogService logService = null;
	//文章信息实体
	private Article article = null;
	//主键
	private String id = null;
    //所属栏目
	private String newsClass_id = null;
    //文章标题
	private String title = null;
    //文章内容
	private String content = null;
    //文章简介
	private String introduce = null;
    //文章缩略图
	private String thumbnail = null;
    //文章来源
	private String source = null;
    //文章编辑
	private String author = null;
	//所属页面模板
	private String viewPageRedirectTemplate_id = null;
	
	/**
	 * 添加文章信息
	 * @Title: ArticleAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addArticle";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(newsClass_id)) {
				data = getText("newsClass_id.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(title)) {
				data = getText("title.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(content)) {
				data = getText("content.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(introduce)) {
				data = getText("introduce.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(thumbnail)) {
				data = getText("thumbnail.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(source)) {
				data = getText("source.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(author)) {
				data = getText("author.required");
				return TEXT;
			}
			//保存文章信息实体
			boolean isSuc = articleService.persist(newsClass_id, title, content, introduce, thumbnail, source, author, versionFlag, viewPageRedirectTemplate_id);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增文章信息";
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
	 * 修改文章信息
	 * @Title: ArticleAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setArticle(articleService.queryById(Article.class.getSimpleName(), id));
			return "editArticle";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = getText("id.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(newsClass_id)) {
				data = getText("newsClass_id.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(title)) {
				data = getText("title.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(content)) {
				data = getText("content.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(introduce)) {
				data = getText("introduce.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(thumbnail)) {
				data = getText("thumbnail.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(source)) {
				data = getText("source.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(author)) {
				data = getText("author.required");
				return TEXT;
			}
			//修改文章信息实体
			boolean isSuc = articleService.merge(id, newsClass_id, title, content, introduce, thumbnail, source, author,  versionFlag, viewPageRedirectTemplate_id);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改广告信息";
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
	 * 管理文章信息实体
	 * @Title: ArticleAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(articleService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageArticle";
	}
	
	/**
	 * 查看回收站
	 * @Title: ArticleAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(articleService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleArticle";
	}
	
	/**
	 * 逻辑删除文章信息对象
	 * @Title: ArticleAction
	 * @Description: 把文章信息对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = articleService.logicDeleteEntity(Article.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除广告信息";
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
	 * 物理删除文章信息对象
	 * @Title: ArticleAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = articleService.remove(id);
		if(isSuc) {
			String logContent = "物理删除广告信息";
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
	 * 还原一个文章信息对象
	 * @Title: ArticleAction
	 * @Description: 从回收站还原文章信息对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = articleService.restoreEntity(Article.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原广告信息";
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
	 * 审核文章信息对象
	 * @Title: ArticleAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = articleService.auditEntity(Article.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核广告信息";
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
	 * @Title: ArticleAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = articleService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作广告信息";
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
	
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNewsClass_id() {
		return newsClass_id;
	}
	public void setNewsClass_id(String newsClass_id) {
		this.newsClass_id = newsClass_id;
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
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	public String getViewPageRedirectTemplate_id() {
		return viewPageRedirectTemplate_id;
	}

	public void setViewPageRedirectTemplate_id(String viewPageRedirectTemplateId) {
		viewPageRedirectTemplate_id = viewPageRedirectTemplateId;
	}
}
