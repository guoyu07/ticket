package com.ticket.serviceImpl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.constants.ContextConstants;
import com.ticket.constants.JQueryUploadConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.NewsClass;
import com.ticket.pojo.Pagination;
import com.ticket.service.INewsClassService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.NewsClassUtil;
import com.ticket.util.PaginationContext;

/**
 * 新闻类别业务接口实现类
 * @ClassName: INewsClassService   
 * @Description: 提供新闻类别操作的增删改查   
 * @author HiSay  
 * @date 2015-10-13 17:40:45
 *
 */
public class NewsClassServiceImpl extends BaseServiceImpl<NewsClass, String> implements INewsClassService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(NewsClassServiceImpl.class);

	@Override
	public boolean merge(String id, String name,String permissionId,String parent_id,String descript, String versionFlag, 
			String alias, String englishName, String listPageRedirectTemplate_id, 
			Integer orderValue,String picture,String pcListTemplate_id,String pcViewTemplate_id) throws ServiceException {
		NewsClass newsClass = dbDAO.queryById(this.getTableNameFromEntity(NewsClass.class), id);
		newsClass.setName(DecoderUtil.UtfDecoder(name));
		newsClass.setPermissionId(permissionId);
		if(GeneralUtil.isNotNull(parent_id)) {
			newsClass.setParent_id(DecoderUtil.UtfDecoder(parent_id));
		} else {
			newsClass.setParent_id(null);
		}
		newsClass.setDescript(DecoderUtil.UtfDecoder(descript));
		newsClass.setAlias(DecoderUtil.UtfDecoder(alias)) ;
		newsClass.setEnglishName(DecoderUtil.UtfDecoder(englishName)) ;
		newsClass.setPcListTemplate_id(pcListTemplate_id);
		CommonEntity status = newsClass.getStatus();
		status.setVersionFlag(versionFlag);
		status.setOrderValue(orderValue) ;
		newsClass.setStatus(status);
		newsClass.setListPageRedirectTemplate_id(listPageRedirectTemplate_id) ;
		newsClass.setPcViewTemplate_id(pcViewTemplate_id);
		dbDAO.merge(newsClass);
		if(GeneralUtil.isNotNull(picture)){
			this.addAnnex(newsClass, newsClass.getId(), picture, JQueryUploadConstants.PICTURE_TYPE_DEFAULT, versionFlag);
		}
		return true;
	}

	@Override
	public boolean persist(String name,String permissionId,String parent_id,String descript, String versionFlag, 
			String alias, String englishName, String listPageRedirectTemplate_id, 
			Integer orderValue,String picture,String pcListTemplate_id,String pcViewTemplate_id) throws ServiceException {
		NewsClass newsClass = new NewsClass();
		newsClass.setName(DecoderUtil.UtfDecoder(name));
		newsClass.setPermissionId(permissionId);
		if(GeneralUtil.isNotNull(parent_id)) {
			newsClass.setParent_id(DecoderUtil.UtfDecoder(parent_id));
		} else {
			newsClass.setParent_id(null);
		}
		newsClass.setDescript(DecoderUtil.UtfDecoder(descript));
		newsClass.setAlias(DecoderUtil.UtfDecoder(alias)) ;
		newsClass.setEnglishName(DecoderUtil.UtfDecoder(englishName)) ;
		newsClass.setPcListTemplate_id(pcListTemplate_id);
		CommonEntity status = newsClass.getStatus();
		status.setVersionFlag(versionFlag);
		status.setOrderValue(orderValue) ;
		newsClass.setStatus(status);
		newsClass.setListPageRedirectTemplate_id(listPageRedirectTemplate_id) ;
		newsClass.setPcViewTemplate_id(pcViewTemplate_id);
		dbDAO.persist(newsClass);
		if(GeneralUtil.isNotNull(picture)){
			this.addAnnex(newsClass, newsClass.getId(), picture, JQueryUploadConstants.PICTURE_TYPE_DEFAULT, versionFlag);
		}
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		NewsClass newsClass = dbDAO.queryById(this.getTableNameFromEntity(NewsClass.class), id);
		dbDAO.remove(newsClass);
		return true;
	}

	@Override
	public List<NewsClass> queryFirstNewsClassList(String versionFlag) throws ServiceException {
		List<NewsClass> firstNewsClassList = dbDAO.queryByList(this.getTableNameFromEntity(NewsClass.class), deleteFlag, versionFlag, "and s.parent_id is null", null, orderValueOrderBy, null);
		if(firstNewsClassList != null && !firstNewsClassList.isEmpty()) {
			return firstNewsClassList;
		} else {
			return null;
		}
	}

	@Override
	public String queryNewsClassSelectOptionHtml(String parent_id, String versionFlag)
	throws ServiceException {
		List<NewsClass> firstNewsClassList = this.queryFirstNewsClassList(versionFlag);
		String shopHtml = null;
		if (firstNewsClassList != null && !firstNewsClassList.isEmpty()) {
			shopHtml = NewsClassUtil.getSelectOptionHTML(firstNewsClassList, 0, parent_id);
		}
		if (GeneralUtil.isNotNull(shopHtml)) {
			return shopHtml;
		}
		return null;
	}

	@Override
	public List<NewsClass> queryChildNewsClasssByParent(String parent_id)
	throws ServiceException {
		return dbDAO.queryByList(this.getTableNameFromEntity(NewsClass.class), deleteFlag,versionFlag, "and s.parent_id=?3", new Object[]{parent_id}, orderValueOrderBy, null);
	}

	@Override
	public boolean validateHaveChildNewsClasss(String parent_id)
	throws ServiceException {
		List<NewsClass> childList = this.queryChildNewsClasssByParent(parent_id);
		if(childList != null && !childList.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean setNewsClassIsDefaultShow(String entityName, String id) throws ServiceException {
		//先取消之前所有的默认
		dbDAO.executeSQL("update " + NewsClass.class.getSimpleName() + " s set s.isDefaultShow=?1", new Object[]{ContextConstants.STATUS_OF_ZERO});

		NewsClass newsClass = this.queryById(entityName, id);
		if(newsClass.getIsDefaultShow().intValue() == ContextConstants.STATUS_OF_ZERO) {
			newsClass.setIsDefaultShow(ContextConstants.STATUS_OF_ONE);
		} else {
			newsClass.setIsDefaultShow(ContextConstants.STATUS_OF_ZERO);
		}
		this.merge(newsClass);
		return true;
	}

	@Override
	public boolean validateAliasExists(String versionFlag, String alias, String id) throws ServiceException {
		if(GeneralUtil.isNotNull(id)) {
			NewsClass nc = dbDAO.queryByCustom(NewsClass.class.getSimpleName(), deleteFlag, versionFlag, "and s.alias=?3", new Object[]{alias}) ;
			if(nc != null && !nc.getId().equals(id)) {
				return true;
			}
			return false;
		} else {
			NewsClass nc = dbDAO.queryByCustom(NewsClass.class.getSimpleName(), deleteFlag, versionFlag, "and s.alias=?3", new Object[]{alias}) ;
			if(nc != null) {
				return true;
			}
			return false;
		}
	}

	@Override
	public NewsClass queryByAlias(String versionFlag, String alias)
			throws ServiceException {
		return dbDAO.queryByCustom(NewsClass.class.getSimpleName(), deleteFlag, versionFlag, "and s.alias=?3", new Object[]{alias}) ;
	}

	@Override
	public List<NewsClass> queryListByFront(String id, Integer startSize, Integer pageCount) {
		return dbDAO.queryByList(NewsClass.class.getSimpleName(), deleteFlag, versionFlag, "and s.parent_id =?3", new Object[]{id}, orderValueOrderBy, startSize, pageCount);
	}

	@Override
	public List<NewsClass> queryParentList(String versionFlag) throws ServiceException {
		return dbDAO.queryByList(NewsClass.class.getSimpleName(), deleteFlag, versionFlag, "and s.parent_id is null", null, orderValueOrderBy, null);
	}

	@Override
	public List<NewsClass> queryListByParentId(String newsClassId, String versionFlag) throws ServiceException {
		List<NewsClass> newsClassList = dbDAO.queryByList(NewsClass.class.getSimpleName(), deleteFlag, versionFlag, "and s.parent_id =?3",new Object[]{newsClassId}, orderBy, null);
		if(newsClassList != null && !newsClassList.isEmpty()){
			return newsClassList;
		}
		return null;
	}

	@Override
	public String parseClassIds(String classId) throws ServiceException{
		NewsClass newsClass = queryById(NewsClass.class.getSimpleName(), classId);
		if (newsClass != null) {
			StringBuffer result = new StringBuffer();
			List<NewsClass> childs = queryChildNewsClasssByParent(classId);
			/** 如果是子栏目 */
			if (childs.size() <= 0) {
				result.append("'");
				result.append(newsClass.getId());
				result.append("'");
				return result.toString();
			}
			/** 如果是父栏目 */
			result.append("'");
			result.append(newsClass.getId());
			result.append("'");
			result.append(",");
			getChildsIds(childs, result);
			return result.toString().substring(0,
					result.toString().lastIndexOf(","));
		}
		return null;
	}

	private void getChildsIds(List<NewsClass> sets,
			StringBuffer result) throws ServiceException{
		Iterator<NewsClass> iter = sets.iterator();
		NewsClass n = null;
		while (iter.hasNext()) {
			n = iter.next();
			result.append("'");
			result.append(n.getId());
			result.append("'");
			result.append(",");
			List<NewsClass> childs = queryChildNewsClasssByParent(n.getId());
			if (childs.size() > 0) {
				getChildsIds(childs, result);
			}
		}
	}

	@Override
	public NewsClass getTopNewsClass(NewsClass newsClass)
			throws ServiceException {
		try {
			if(GeneralUtil.isNotNull(newsClass.getParent_id())){
				newsClass = queryById(NewsClass.class.getSimpleName(), newsClass.getParent_id());
				newsClass = getTopNewsClass(newsClass);
			}
			return newsClass;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public NewsClass queryByPermissionId(String permissionId, String versionFlag)
			throws ServiceException {
		return dbDAO.queryByCustom(NewsClass.class.getSimpleName(), deleteFlag, versionFlag, "and s.permissionId =?3", new Object[]{permissionId});
	}

	@Override
	public boolean validatePermissionExists(String permissionId,
			String versionFlag) throws ServiceException {
		NewsClass newsClass = dbDAO.queryByCustom(NewsClass.class.getSimpleName(), deleteFlag, versionFlag, "and s.permissionId =?3", new Object[]{permissionId});
		if(newsClass != null){
			return true;
		}
		return false;
	}

	@Override
	public Pagination queryPageModuleByParentId(String parent_id, int pageSize,
			String versionFlag) throws ServiceException {
		return dbDAO.queryByPageModule(NewsClass.class.getSimpleName(), deleteFlag, versionFlag, " and s.parent_id =?3", new Object[]{parent_id}, orderBy, PaginationContext.getOffset(), pageSize);
	}
	
	
}