package com.ticket.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.constants.JQueryUploadConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.InfoPosition;
import com.ticket.pojo.News;
import com.ticket.pojo.NewsClass;
import com.ticket.pojo.SystemUser;
import com.ticket.service.ICommonSearchService;
import com.ticket.service.IInfoPositionService;
import com.ticket.service.INewsClassService;
import com.ticket.service.INewsService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.JPushClientUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 新闻信息控制器
 * @ClassName: NewsAction   
 * @Description:  提供新闻信息的相关操作方法. 
 * @author HiSay  
 * @date 2015-10-13 17:14:59
 *
 */
public class NewsAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//新闻信息的业务层
	@Resource private INewsService newsService = null;
	//信息位置的业务层
	@Resource private IInfoPositionService infoPositionService = null;
	//新闻类别的业务层
	@Resource private INewsClassService newsClassService = null;
	
	@Resource private ISystemOperationLogService logService = null;
	@Resource private ICommonSearchService commonSearchService = null;
	
	
	//新闻信息实体
	private News news = null;
	//信息位置实体
	private InfoPosition infoPosition = null;
	//主键
	private String id = null;
    //新闻类别id
	private String newsClass_id = null;
    //信息编辑
	private String author = null;
    //信息标题
	private String title = null;
    //信息副标题
	private String subTitle = null;
	//新闻简介
	private String introduce = null;
    //新闻内容
	private String content = null;
	//pc端内容
	private String pcContent = null;
    //管理员id
	private String systemUser_id = null;
	//缩略图
	private String thumbnail = null;
	//详细图
	private String image = null;
	//新闻类别
	private String newsClassHtml = null;
	//经度
	private Double[] longitude = null;
	//纬度
	private Double[] latitude = null;
	//位置名称
	private String[] locationName = null;
	//位置id用于更新
	private String[] locationId = null;
	//位置电话
	private String[] mobiles = null;
	//位置链接
	private String[] url = null;
	//楼层号
	private String[] floorNumber = null;
	
	//搜索关键词
	private String keyword =null;
	//页面模板ID
	private String viewPageRedirectTemplate_id = null;
	//关联新闻栏目别名
	private String useNewsClassName = null;
	//服务电话
	private String mobile = null;
	//排序值
	private Integer orderValue = 0;
	//最后修改时间
	private Date lastUpdateTime = null;
	//SEO关键词
	private String seoKeyword = null;
	//SEO关键词描述
	private String seoDescript = null;
	//SEO的链接
	private String seoUrl = null;
	
	private String infoPosition_id = null;
	//权限id
	private String permissionId = null;
	
	private List<News> newsList = null;
	
	private String scenicSpots_id = null;
	
	/**
	 * 旅游资讯文章分页模板
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public String scenicSpotsNewsList() throws ServiceException{
		NewsClass nc = newsClassService.queryByAlias(versionFlag, "jingdianwenzhan");
		pageSize = 20;
		String tableName = News.class.getName();
		newsList = newsService.getDbDAO().executeJPQLForQuery("select s from " + tableName + " s where s.status.deleteFlag=0 and s.status.audit=1 and s.newsClass_id = ? order by s.status.orderValue desc,s.status.createTime desc"
				, pn, pageSize,nc.getId());
		return "scenicSpotsNewsItem";
	}
	
	/**
	 * 通知中心分页模板
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public String list() throws ServiceException{
		NewsClass nc = newsClassService.queryByAlias(versionFlag, "tzzx");
		pageSize = 20;
		String tableName = News.class.getName();
		newsList = newsService.getDbDAO().executeJPQLForQuery("select s from " + tableName + " s where s.status.deleteFlag=0 and s.newsClass_id = ? and s.status.audit=1 order by s.status.orderValue desc"
				, pn, pageSize,nc.getId());
		return "item";
	}
	
	/**
	 * 通知中心
	 * @return
	 * @throws ServiceException
	 */
	public String announcement() throws ServiceException{
		NewsClass nc = newsClassService.queryByAlias(versionFlag, "tzzx");
		newsList = newsService.queryListByFront(nc
				.getId(), 0, 10);
		ActionContext.getContext().put("pageSize", ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "announcement";
	}
	/**
	 * 通知中心
	 * @return
	 * @throws ServiceException
	 */
	public String announcement1() throws ServiceException{
		NewsClass nc = newsClassService.queryByAlias(versionFlag, "tzzx");
		newsList = newsService.queryListByFront(nc
				.getId(), 0, 10);
		ActionContext.getContext().put("pageSize", ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "announcement1";
	}
	/**
	 * 添加新闻信息
	 * @Title: NewsAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setNewsClassHtml(newsClassService.queryNewsClassSelectOptionHtml(null, versionFlag));
			List<InfoPosition> list = infoPositionService.queryAll(InfoPosition.class);
			ActionContext.getContext().put("infoPositionList", list);
			return "addNews";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(newsClass_id)) {
				data = AjaxData.responseError(getText("newsClass_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(title)) {
				data = AjaxData.responseError(getText("title.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(content)) {
				data = AjaxData.responseError(getText("content.required"));
				return JSON;
			}
			/*if(newsService.validateTitleExists(title,versionFlag)) {
				data = AjaxData.responseError(getText("title.exists"));
				return JSON;
			}*/
			SystemUser systemUser = (SystemUser) getSession().get("systemUser");
			//保存新闻信息实体
			News isSuc = newsService.persist(newsClass_id, author, title, subTitle,introduce, content,pcContent, systemUser.getId(),
					getSinglePictureUrlFromJQueryUpLoader(fileNames, directory, JQueryUploadConstants.PICTURE_TYPE_DEFAULT),
					getMultiPictureUrlFromJQueryUpLoader(fileNames, directory, JQueryUploadConstants.MEMBER_STORE_THUMBNAIL),
					locationName,longitude,latitude,versionFlag, viewPageRedirectTemplate_id, 
					useNewsClassName, mobile, orderValue, lastUpdateTime, mobiles,url, floorNumber,
					getSinglePictureUrlFromJQueryUpLoader(fileNames, directory, JQueryUploadConstants.NEWS_VIDEO), 
					seoKeyword, seoDescript, seoUrl,getSinglePictureUrlFromJQueryUpLoader(fileNames, directory,4),infoPosition_id,scenicSpots_id);
//			NewsClass nc = newsClassService.queryById(NewsClass.class.getName(), newsClass_id);
//			if(nc.getAlias().equals("tzzx")){
//				JPushClientUtil.sendAll(title, content, "/airport/"+isSuc.getStatus().getVisitUrl()+".ticket");
//			}
			//根据保存结果返回页面
			if(isSuc != null) {
				String logContent = "新增新闻信息";
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
	 * 修改新闻信息
	 * @Title: NewsAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setNews(newsService.queryById(News.class.getSimpleName(), id));
			this.setInfoPosition(infoPositionService.queryByNewsId(id, versionFlag));
			if(this.getNewsClass_id() != null) {
				this.setNewsClassHtml(newsClassService.queryNewsClassSelectOptionHtml(this.getNewsClass_id(), versionFlag));
			} else {
				this.setNewsClassHtml(newsClassService.queryNewsClassSelectOptionHtml(null, versionFlag));
			}
			News news = this.getNews();
			if(GeneralUtil.isNotNull(news.getInfoPosition_id())){
				InfoPosition position = infoPositionService.queryById(InfoPosition.class.getName(), news.getInfoPosition_id());
				ActionContext.getContext().put("position", position);
			}
			List<InfoPosition> list = infoPositionService.queryByNewsAlias(versionFlag);
			ActionContext.getContext().put("infoPositionList", list);
			return "editNews";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(newsClass_id)) {
				data = AjaxData.responseError(getText("newsClass_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(title)) {
				data = AjaxData.responseError(getText("title.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(content)) {
				data = AjaxData.responseError(getText("content.required"));
				return JSON;
			}
			SystemUser systemUser = (SystemUser) getSession().get("systemUser");
			//修改新闻信息实体
			boolean isSuc = newsService.merge(id, newsClass_id, author, title, subTitle, introduce,content,pcContent, systemUser.getId(),
					getSinglePictureUrlFromJQueryUpLoader(fileNames, directory, JQueryUploadConstants.PICTURE_TYPE_DEFAULT),
					getMultiPictureUrlFromJQueryUpLoader(fileNames, directory, JQueryUploadConstants.MEMBER_STORE_THUMBNAIL),
					locationId,locationName,longitude,latitude,versionFlag, viewPageRedirectTemplate_id, 
					useNewsClassName, mobile, orderValue, lastUpdateTime, mobiles,url, floorNumber,
					getSinglePictureUrlFromJQueryUpLoader(fileNames, directory, JQueryUploadConstants.NEWS_VIDEO),
					seoKeyword, seoDescript, seoUrl,getSinglePictureUrlFromJQueryUpLoader(fileNames, directory,4),infoPosition_id,scenicSpots_id);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改新闻信息";
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
	 * 管理新闻信息实体
	 * @Title: NewsAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		//判断权限id是否为空
		if(GeneralUtil.isNotNull(permissionId)){
			this.setPageModule(newsService.queryPageModuleByNewsClassPermissionId(permissionId, ContextConstants.ADMIN_COMMON_PAGE_SIZE, versionFlag));
			this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
			return "manageNewsByPermission";
		}else{
			this.setNewsClassHtml(newsClassService.queryNewsClassSelectOptionHtml(newsClass_id, versionFlag));
			if(GeneralUtil.isNotNull(newsClass_id)){
				this.setPageModule(newsService.queryPageModuleByNewsClassId(newsClass_id, ContextConstants.ADMIN_COMMON_PAGE_SIZE, versionFlag));
				this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
				return "manageNews";
			}
			this.setPageModule(newsService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
			this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
			return "manageNews";
		}
	}
	
	/**
	 * 查看回收站
	 * @Title: NewsAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		if(GeneralUtil.isNotNull(permissionId)){
			this.setPageModule(newsService.queryRecycleByNewsClassPermissionId(permissionId, ContextConstants.ADMIN_COMMON_PAGE_SIZE, versionFlag));
			this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
			return "recycleNews";
		}else{
			this.setPageModule(newsService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
			this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		}
		return "recycleNews";
	}
	
	/**
	 * 逻辑删除新闻信息对象
	 * @Title: NewsAction
	 * @Description: 把新闻信息对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = newsService.logicDeleteEntity(News.class.getSimpleName(), id);
		commonSearchService.sendFlushNotify();
		if(isSuc) {
			String logContent = "逻辑删除新闻信息";
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
	 * 物理删除新闻信息对象
	 * @Title: NewsAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = newsService.remove(id);
		commonSearchService.sendFlushNotify();
		if(isSuc) {
			String logContent = "物理删除新闻信息";
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
	 * 还原一个新闻信息对象
	 * @Title: NewsAction
	 * @Description: 从回收站还原新闻信息对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = newsService.restoreEntity(News.class.getSimpleName(), id);
		News news = newsService.get(News.class, id);
		newsService.updateSeo(news);
		if(isSuc) {
			String logContent = "还原新闻信息";
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
	 * 审核新闻信息对象
	 * @Title: NewsAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = newsService.auditEntity(News.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核新闻信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			//如果是通知中心的新闻，则进行推送
			News news = newsService.queryById(News.class.getName(), id);
			NewsClass class1 = newsClassService.get(NewsClass.class, news.getNewsClass_id());
			if(class1.getAlias().equals("tzzx") && statusValue == 1){
				JPushClientUtil.sendAll(news.getTitle(), news.getIntroduce(), "/airport/" + news.getStatus().getVisitUrl() + ".ticket");
			}
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: NewsAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = newsService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作新闻信息";
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
	 * 批量彻底删除新闻信息
	 * @return
	 * @throws ServiceException
	 */
	public String batchRealDelete() throws ServiceException{
		boolean isSuc = newsService.batchRealDelete(idsValue, versionFlag);
		if(isSuc) {
			String logContent = "批量删除新闻信息";
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
	 * 新闻详细信息
	 * @return
	 * @throws ServiceException
	 */
	public String detail() throws ServiceException{
		this.setNews(newsService.queryById(News.class.getSimpleName(), id));
		return "detailNews";
	}
	
	/**
	 * 根据关键词搜索新闻信息
	 * @return
	 * @throws ServiceException
	 */
	public String search() throws ServiceException{
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(newsService.queryPageModuleByKeyword(keyword, ContextConstants.ADMIN_COMMON_PAGE_SIZE, versionFlag));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageNews";
	}
	public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
	}
	public InfoPosition getInfoPosition() {
		return infoPosition;
	}

	public void setInfoPosition(InfoPosition infoPosition) {
		this.infoPosition = infoPosition;
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSystemUser_id() {
		return systemUser_id;
	}
	public void setSystemUser_id(String systemUser_id) {
		this.systemUser_id = systemUser_id;
	}
	public String getNewsClassHtml() {
		return newsClassHtml;
	}
	public void setNewsClassHtml(String newsClassHtml) {
		this.newsClassHtml = newsClassHtml;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getKeyword() {
		return keyword;
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getViewPageRedirectTemplate_id() {
		return viewPageRedirectTemplate_id;
	}

	public void setViewPageRedirectTemplate_id(String viewPageRedirectTemplateId) {
		viewPageRedirectTemplate_id = viewPageRedirectTemplateId;
	}

	public String[] getLocationName() {
		return locationName;
	}

	public void setLocationName(String[] locationName) {
		this.locationName = locationName;
	}

	public Double[] getLongitude() {
		return longitude;
	}

	public void setLongitude(Double[] longitude) {
		this.longitude = longitude;
	}

	public Double[] getLatitude() {
		return latitude;
	}

	public void setLatitude(Double[] latitude) {
		this.latitude = latitude;
	}

	public String[] getLocationId() {
		return locationId;
	}

	public void setLocationId(String[] locationId) {
		this.locationId = locationId;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getUseNewsClassName() {
		return useNewsClassName;
	}

	public void setUseNewsClassName(String useNewsClassName) {
		this.useNewsClassName = useNewsClassName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(Integer orderValue) {
		this.orderValue = orderValue;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String[] getMobiles() {
		return mobiles;
	}

	public void setMobiles(String[] mobiles) {
		this.mobiles = mobiles;
	}

	public String[] getUrl() {
		return url;
	}

	public void setUrl(String[] url) {
		this.url = url;
	}

	public String getSeoKeyword() {
		return seoKeyword;
	}

	public void setSeoKeyword(String seoKeyword) {
		this.seoKeyword = seoKeyword;
	}

	public String getSeoDescript() {
		return seoDescript;
	}

	public void setSeoDescript(String seoDescript) {
		this.seoDescript = seoDescript;
	}

	public String getSeoUrl() {
		return seoUrl;
	}

	public void setSeoUrl(String seoUrl) {
		this.seoUrl = seoUrl;
	}

	public String[] getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(String[] floorNumber) {
		this.floorNumber = floorNumber;
	}

	public String getInfoPosition_id() {
		return infoPosition_id;
	}

	public void setInfoPosition_id(String infoPosition_id) {
		this.infoPosition_id = infoPosition_id;
	}

	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}
	
	public INewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(INewsService newsService) {
		this.newsService = newsService;
	}

	public IInfoPositionService getInfoPositionService() {
		return infoPositionService;
	}

	public void setInfoPositionService(IInfoPositionService infoPositionService) {
		this.infoPositionService = infoPositionService;
	}

	public INewsClassService getNewsClassService() {
		return newsClassService;
	}

	public void setNewsClassService(INewsClassService newsClassService) {
		this.newsClassService = newsClassService;
	}

	public String getPcContent() {
		return pcContent;
	}

	public void setPcContent(String pcContent) {
		this.pcContent = pcContent;
	}

	public List<News> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}

	public String getScenicSpots_id() {
		return scenicSpots_id;
	}

	public void setScenicSpots_id(String scenicSpots_id) {
		this.scenicSpots_id = scenicSpots_id;
	}
}
