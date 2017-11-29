package com.ticket.serviceImpl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.constants.ContextConstants;
import com.ticket.constants.JQueryUploadConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.CommonSearch;
import com.ticket.pojo.InfoPosition;
import com.ticket.pojo.News;
import com.ticket.pojo.NewsClass;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.ScenicSpots;
import com.ticket.service.ICommonSearchService;
import com.ticket.service.IInfoPositionService;
import com.ticket.service.INewsClassService;
import com.ticket.service.INewsService;
import com.ticket.service.IScenicSpotsService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.PaginationContext;

/**
 * 新闻信息业务接口实现类
 * @ClassName: INewsService   
 * @Description: 提供新闻信息操作的增删改查   
 * @author HiSay  
 * @date 2015-10-13 17:14:59
 *
 */
public class NewsServiceImpl extends BaseServiceImpl<News, String> implements INewsService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(NewsServiceImpl.class);
	@Resource private IInfoPositionService infoPositionService = null;
	@Resource private INewsClassService newsClassService = null;
	@Resource protected ICommonSearchService commonSearchService;
	@Resource private IScenicSpotsService scenicSpotsService;
	
	/**
	 * 更新seo内存
	 * @param news
	 */
	@Override
	public void updateSeo(News news){
		
		if(news != null){
			
			CommonSearch search = CommonSearchServiceImpl.seo.get(news.getId());
			//如果为空，就新建
			if(search == null){
				
				search = new CommonSearch();
			}
			search.setTitle(news.getTitle());
			search.setCreateTime(news.getStatus().getCreateTime());
			search.setSeo(news.getSeoKeyword());
			search.setUrl("/airport/"+news.getStatus().getVisitUrl()+".ticket") ;
			search.setPcUrl("/airport/newsView/" + news.getId() + ".html");
			CommonSearchServiceImpl.seo.put(news.getId(), search);
		}
	}
	
	/**
	 * 初始化
	 */
	@Override
	public void init(){
		
		List<News> list = queryAll(News.class);
		for(int i = 0; i < list.size(); i++){
			
			updateSeo(list.get(i));
		}
	}
	
	@Override
	public boolean merge(String id, String newsClass_id,String author,String title,String subTitle,String introduce,
			String content,String pcContent,String systemUser_id,String thumbnail,List<String> image,String[] locationId,String[] locationName,
			Double[] longitude,Double[] latitude, String versionFlag, String viewPageRedirectTemplate_id, 
			String useNewsClassName, String mobile, Integer orderValue, Date lastUpdateTime, 
			String[] mobiles,String[] url,String[] floorNumber, String video, String seoKeyword,
			String seoDescript, String seoUrl,String pcthumbnail,String infoPositionId,String scenicSpots_id) throws ServiceException {
		News news = dbDAO.queryById(this.getTableNameFromEntity(News.class), id);
		news.setNewsClass_id(DecoderUtil.UtfDecoder(newsClass_id));
		news.setAuthor(DecoderUtil.UtfDecoder(author));
		news.setTitle(DecoderUtil.UtfDecoder(title));
		news.setSubTitle(DecoderUtil.UtfDecoder(subTitle));
		news.setIntroduce(DecoderUtil.UtfDecoder(introduce));
		news.setContent(GeneralUtil.replacePtToPx(content));
		news.setPcContent(GeneralUtil.replacePtToPx(pcContent));
		news.setUseNewsClassName(DecoderUtil.UtfDecoder(useNewsClassName)) ;
		news.setSystemUser_id(DecoderUtil.UtfDecoder(systemUser_id));
		news.setMobile(DecoderUtil.UtfDecoder(mobile));
		CommonEntity status = news.getStatus();
		status.setVersionFlag(versionFlag);
		status.setOrderValue(orderValue) ;
		news.setViewPageRedirectTemplate_id(viewPageRedirectTemplate_id) ;
		news.setStatus(status);
		news.setLastUpdateTime(new Date()) ;
		news.setSeoKeyword(DecoderUtil.UtfDecoder(seoKeyword)) ;
		news.setSeoDescript(DecoderUtil.UtfDecoder(seoDescript)) ;
		news.setSeoUrl(DecoderUtil.UtfDecoder(seoUrl)) ;
		news.setInfoPosition_id(infoPositionId);
		
		if(GeneralUtil.isNotNull(scenicSpots_id)){
			ScenicSpots scenicSpots = scenicSpotsService.queryById(ScenicSpots.class.getName(), scenicSpots_id);
			news.setScenicSpots(scenicSpots);
		}
		dbDAO.merge(news);
		InfoPosition position = null;
		if(GeneralUtil.isNotNull(thumbnail)){
			this.addAnnex(news, news.getId(), thumbnail, JQueryUploadConstants.PICTURE_TYPE_DEFAULT, versionFlag);
		}
		if(GeneralUtil.isNotNull(pcthumbnail)){
			this.addAnnex(news, news.getId(), pcthumbnail, 4, versionFlag);
		}
		if(GeneralUtil.isNotNull(image)){
			this.addManyAnnex(news, news.getId(), image, JQueryUploadConstants.MEMBER_STORE_THUMBNAIL, versionFlag);
		}
		if(GeneralUtil.isNotNull(video)){
			this.addAnnex(news, news.getId(), video, JQueryUploadConstants.NEWS_VIDEO, versionFlag);
		}
		if(GeneralUtil.isNotNull(infoPositionId)){
			position = infoPositionService.queryById(InfoPosition.class.getName(), infoPositionId);
			position.setNews_id(id);
			infoPositionService.persist(position);
		}

		commonSearchService.sendFlushNotify();
//		if(locationName == null){
//			return true;
//		}
		/*for(int i=0;i<locationName.length;i++){
			if(GeneralUtil.isNotNull(locationName[i])){
				try {
					if(locationId != null && GeneralUtil.isNotNull(locationId[i])){
						position = infoPositionService.queryById(InfoPosition.class.getSimpleName(), locationId[i]);
						if(position != null){
							position.setName(locationName[i]);
							position.setLongitude(longitude[i]);
							position.setLatitude(latitude[i]);
							position.setMobile(mobiles[i]);
							position.setUrl(url[i]);
							position.setFloorNumber(floorNumber[i]);
							infoPositionService.merge(position);
						}
					}else{
						position = new InfoPosition();
						position.setNews_id(id);
						position.setName(locationName[i]);
						position.setLongitude(longitude[i]);
						position.setLatitude(latitude[i]);
						position.setMobile(mobiles[i]);
						position.setUrl(url[i]);
						position.setFloorNumber(floorNumber[i]);
						infoPositionService.persist(position);
					}
				} catch(Exception e) {
					position = new InfoPosition();
					position.setNews_id(id);
					position.setName(locationName[i]);
					position.setLongitude(longitude[i]);
					position.setLatitude(latitude[i]);
					position.setMobile(mobiles[i]);
					position.setUrl(url[i]);
					position.setFloorNumber(floorNumber[i]);
					infoPositionService.persist(position);
				}
			}
		}*/
		return true;
	}

	@Override
	public News persist(String newsClass_id,String author,String title,String subTitle,String introduce,String content,String pcContent,
			String systemUser_id,String thumbnail,List<String> image,String[] locationName,Double[] longitude,
			Double[] latitude,String versionFlag, String viewPageRedirectTemplate_id, 
			String useNewsClassName, String mobile, Integer orderValue, Date lastUpdateTime, 
			String[] mobiles,String[] url,String[] floorNumber, String video, 
			String seoKeyword, String seoDescript, String seoUrl,String pcthumbnail,String infoPositionId,String scenicSpots_id) throws ServiceException {
		News news = new News();
		news.setNewsClass_id(DecoderUtil.UtfDecoder(newsClass_id));
		news.setAuthor(DecoderUtil.UtfDecoder(author));
		news.setTitle(DecoderUtil.UtfDecoder(title));
		news.setSubTitle(DecoderUtil.UtfDecoder(subTitle));
		news.setIntroduce(DecoderUtil.UtfDecoder(introduce));
		news.setContent(GeneralUtil.replacePtToPx(content));
		news.setPcContent(GeneralUtil.replacePtToPx(pcContent));
		news.setSystemUser_id(DecoderUtil.UtfDecoder(systemUser_id));
		news.setUseNewsClassName(DecoderUtil.UtfDecoder(useNewsClassName)) ;
		news.setMobile(DecoderUtil.UtfDecoder(mobile));
		CommonEntity status = news.getStatus();
		status.setVersionFlag(versionFlag);
		status.setOrderValue(orderValue) ;
		news.setViewPageRedirectTemplate_id(viewPageRedirectTemplate_id);
		news.setStatus(status);
		news.setLastUpdateTime(news.getStatus().getCreateTime()) ;
		news.setSeoKeyword(DecoderUtil.UtfDecoder(seoKeyword)) ;
		news.setSeoDescript(DecoderUtil.UtfDecoder(seoDescript)) ;
		news.setSeoUrl(DecoderUtil.UtfDecoder(seoUrl)) ;
		news.setInfoPosition_id(infoPositionId);
		
		if(GeneralUtil.isNotNull(scenicSpots_id)){
			ScenicSpots scenicSpots = scenicSpotsService.queryById(ScenicSpots.class.getName(), scenicSpots_id);
			news.setScenicSpots(scenicSpots);
		}
		dbDAO.persist(news);
		if(GeneralUtil.isNotNull(thumbnail)){
			this.addAnnex(news, news.getId(), thumbnail, JQueryUploadConstants.PICTURE_TYPE_DEFAULT, versionFlag);
		}
		if(GeneralUtil.isNotNull(pcthumbnail)){
			this.addAnnex(news, news.getId(), pcthumbnail, 4, versionFlag);
		}
		if(GeneralUtil.isNotNull(image)){
			this.addManyAnnex(news, news.getId(), image, JQueryUploadConstants.MEMBER_STORE_THUMBNAIL, versionFlag);
		}
		if(GeneralUtil.isNotNull(video)){
			this.addAnnex(news, news.getId(), video, JQueryUploadConstants.NEWS_VIDEO, versionFlag);
		}
		if(GeneralUtil.isNotNull(infoPositionId)){
			InfoPosition position = infoPositionService.queryById(InfoPosition.class.getName(), infoPositionId);
			position.setNews_id(news.getId());
			infoPositionService.persist(position);
		}
		
		commonSearchService.sendFlushNotify();
		/**
		 * 保存信息位置
		 */
		/*if(locationName == null){
			return true;
		}
		for(int i=0;i<locationName.length;i++){
			if(locationName != null && GeneralUtil.isNotNull(locationName[i])){
				InfoPosition infoPosition = new InfoPosition();
				infoPosition.setName(locationName[i]);
				infoPosition.setLongitude(longitude[i]);
				infoPosition.setLatitude(latitude[i]);
				infoPosition.setMobile(mobiles[i]);
				infoPosition.setUrl(url[i]);
				infoPosition.setFloorNumber(floorNumber[i]);
				infoPosition.setNews_id(news.getId()) ;
				infoPositionService.persist(infoPosition);
			}
		}
		*/
		return news;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		News news = dbDAO.queryById(this.getTableNameFromEntity(News.class), id);
		dbDAO.remove(news);
		
		commonSearchService.sendFlushNotify();
		return true;
	}

	@Override
	public boolean batchRealDelete( String idsValue,String versionFlag)
	throws ServiceException {
		idsValue = GeneralUtil.convertIdsValueToStringByDelete(idsValue, ",");
		dbDAO.deleteAll(News.class.getSimpleName(), idsValue, null);
		
		if(GeneralUtil.isNotNull(idsValue)){
			
			String[] ids = idsValue.split(",");
			for(int i = 0; i < ids.length; i++){
				
				commonSearchService.sendFlushNotify();
			}
		}
		return true;
	}

	@Override
	public boolean validateTitleExists(String title, String versionFlag)
	throws ServiceException {
		News news = dbDAO.queryByCustom(News.class.getSimpleName(), deleteFlag, versionFlag, "and s.title = ?3", new Object[]{title});
		if(news != null){
			return true;
		}
		return false;
	}

	@Override
	public Pagination queryPageModuleByKeyword(String keyword,
			int pageSize, String versionFlag) throws ServiceException{
		keyword = DecoderUtil.UtfDecoder(keyword);
		return dbDAO.queryByPageModule(News.class.getSimpleName(), deleteFlag, versionFlag, "and s.title like ?3", new Object[]{"%"+keyword+"%"}, orderBy, PaginationContext.getOffset(), pageSize);
	}

	@Override
	public Pagination queryPageModuleByNewsClassId(String newsClass_id, int pageSize, String versionFlag) throws ServiceException {
		String param = null;
		if(GeneralUtil.isNotNull(newsClass_id)){
			String ids = newsClassService.parseClassIds(newsClass_id);
			param = "and s.newsClass_id in ("+ids+") ";
		}
		return dbDAO.queryByPageModuleNew(News.class.getSimpleName(), deleteFlag, versionFlag, param, null, orderBy, PaginationContext.getOffset(), pageSize);
	}

	@Override
	public News queryByUrl(String visitUrl, boolean addHits)
	throws ServiceException {
		News news = dbDAO.queryByCustom(this.getTableNameFromEntity(News.class), deleteFlag, versionFlag, "and s.status.visitUrl=?3", new Object[]{Long.valueOf(visitUrl)}) ;
		if(news != null && addHits) {
			CommonEntity status = news.getStatus();
			status.setHits(status.getHits() + 1);
			news.setStatus(status);
			dbDAO.merge(news);
		}
		return news;
	}

	@Override
	public List<News> queryListByFront(String newsClassId, Integer startSize,
			Integer pageCount) throws ServiceException {
		List<News> newsList = dbDAO.queryByList(this.getTableNameFromEntity(News.class), deleteFlag, versionFlag, "and s.newsClass_id=?3 and s.status.audit=?4", new Object[]{newsClassId, ContextConstants.STATUS_OF_ONE}, orderValueOrderBy, startSize, pageCount);
		return newsList;
	}

	@Override
	public News querySingleByNewsClassId(String newsClass_id, String versionFlag) throws ServiceException {
		List<News> newsList = dbDAO.queryByList(News.class.getSimpleName(), deleteFlag, versionFlag, "and s.newsClass_id =?3", new Object[]{newsClass_id},orderBy, 0, 1);
		if(newsList == null || newsList.isEmpty()){
			return null;
		}
		News news = newsList.get(0);
		return news;
	}

	@Override
	public News queryNextNews(News news) throws ServiceException {
		/*select min(r.id) from Article r where r.status.deleteFlag=?1 and r.status.versionFlag=?2 " +
				"and r.status.isAudit=?3 and r.status.isSecondAudit=?5 and r.newsClass.id in ("
				+ ids + ") and r.id>?4 and r.member.id is null*/
		Long visitUrl = dbDAO
				.queryByCustomLong(
						News.class.getSimpleName(),
						deleteFlag,
						news.getStatus().getVersionFlag(),
						"min(s.status.visitUrl)",
						"and s.newsClass_id=?3 and s.status.visitUrl>?4 and s.status.audit=?5",
						new Object[] { news.getNewsClass_id(),
								news.getStatus().getVisitUrl(),
								ContextConstants.STATUS_OF_ONE });
		return this.queryByUrl(visitUrl+"", false);
	}

	@Override
	public News queryPrevNews(News news) throws ServiceException {
		/*select max(r.id) from Article r where r.status.deleteFlag=?1 and r.status.versionFlag=?2 and r.status.isAudit=?3 and r.status.isSecondAudit=?5 and r.newsClass.id in ("
		+ ids + ") and r.id<?4 and r.member.id is null*/
		Long visitUrl = dbDAO
				.queryByCustomLong(
						News.class.getSimpleName(),
						deleteFlag,
						news.getStatus().getVersionFlag(),
						"max(s.status.visitUrl)",
						"and s.newsClass_id=?3 and s.status.visitUrl<?4 and s.status.audit=?5",
						new Object[] { news.getNewsClass_id(),
								news.getStatus().getVisitUrl(),
								ContextConstants.STATUS_OF_ONE });
		return this.queryByUrl(visitUrl+"", false);
	}

	@Override
	public List<News> queryListByType(String newsClass_id, Integer size, String versionFlag) throws ServiceException {
		List<News> list = dbDAO.queryByList(News.class.getSimpleName(), deleteFlag, versionFlag, " and s.newsClass_id =?3", new Object[]{newsClass_id}, orderBy, size);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public News queryByTitle(String title, String versionFlag)
			throws ServiceException {
		News news = dbDAO.queryByCustom(News.class.getSimpleName(), deleteFlag, versionFlag, "and s.title= ?3", new Object[]{title});
		if(news != null){
			return news;
		}
		return null;
	}

	@Override
	public List<News> queryByNewsClassId(String newsClassId, Integer audit,
			Integer cmd,String order, Integer size) throws ServiceException {
		try {
			StringBuffer sb = new StringBuffer();
			List<Object[]> objects = new ArrayList<Object[]>();
			sb.append("");
			if(GeneralUtil.isNotNull(newsClassId)){
				sb.append("and s.newsClass_id in ");
				sb.append("(");
				sb.append(newsClassService.parseClassIds(newsClassId));
				sb.append(") ");
			}
			if(GeneralUtil.isNotNull(audit)){
				sb.append("and s.status.audit=?3 ");
				objects.add(new Object[]{3,audit});
			}
			if(GeneralUtil.isNotNull(cmd)){
				sb.append("and s.status.commend=?4 ");
				objects.add(new Object[]{4,cmd});
			}
			if(GeneralUtil.isNull(order)){
				order = orderBy;
			}
			return dbDAO.queryByListNew(News.class.getSimpleName(), 
					deleteFlag, versionFlag, sb.toString(), objects, order, size);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Pagination queryByClassAndKeyword(String newsClassId,
			String keyword, Integer audit, String order, Integer pageSize)
			throws ServiceException {
		try {
			List<Object[]> objects = new ArrayList<Object[]>();
			StringBuffer sb = new StringBuffer();
			sb.append("");
			if(GeneralUtil.isNotNull(newsClassId)){
				String ids = newsClassService.parseClassIds(newsClassId);
				sb.append("and s.newsClass_id in (");
				sb.append(ids);
				sb.append(") ");
			}
			if(GeneralUtil.isNotNull(keyword)){
				sb.append("and (s.title like ?3 or s.content like ?4 ) ");
				objects.add(new Object[]{3,"%"+keyword+"%"});
				objects.add(new Object[]{4,"%"+keyword+"%"});
			}
			if(GeneralUtil.isNotNull(audit)){
				sb.append("and s.status.audit=?5 ");
				objects.add(new Object[]{5,audit});
			}
			if(GeneralUtil.isNull(order)){
				order = orderBy;
			}
			return dbDAO.queryByPageModuleNew(News.class.getSimpleName(), deleteFlag, 
					versionFlag, sb.toString(), objects, order, PaginationContext.getOffset(), pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Pagination queryPageModuleByNewsClassPermissionId(
			String permissionId, int pageSize, String versionFlag)
			throws ServiceException {
		NewsClass newsClass = newsClassService.queryByPermissionId(permissionId,versionFlag);
		if(newsClass != null){
			
			if(ContextConstants.ADMIN.equals(getSystemUser().getLoginId())){
				
				return dbDAO.queryByPageModule(News.class.getSimpleName(), deleteFlag, versionFlag, "and s.newsClass_id =?3", new Object[]{newsClass.getId()}, orderBy, PaginationContext.getOffset(), pageSize);
			}else{
				
				return dbDAO.queryByPageModule(News.class.getSimpleName(), deleteFlag, versionFlag, "and s.newsClass_id =?3 and s.systemUser_id =?4", new Object[]{newsClass.getId(), getSystemUser().getId()}, orderBy, PaginationContext.getOffset(), pageSize);
			}
		}
		return null;
	}

	@Override
	public Pagination queryRecycleByNewsClassPermissionId(String permissionId,
			int pageSize, String versionFlag)
			throws ServiceException {
		NewsClass newsClass = newsClassService.queryByPermissionId(permissionId,versionFlag);
		if(newsClass != null){
			return dbDAO.queryByPageModule(News.class.getSimpleName(), ContextConstants.STATUS_OF_ONE, versionFlag, "and s.newsClass_id =?3", new Object[]{newsClass.getId()}, orderBy, PaginationContext.getOffset(), pageSize);
		}
		return null;
	}

	@Override
	public List<News> queryByscenicSpots(String scenicSpots_id)
			throws ServiceException {
		List<News> list = dbDAO.executeJPQLForQuery("select c from " + News.class.getName() + " c where c.status.deleteFlag = 0 and c.scenicSpots.id = ? ", News.class,scenicSpots_id);
		return list;
	}
}