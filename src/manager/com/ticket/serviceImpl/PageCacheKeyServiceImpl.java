package com.ticket.serviceImpl;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.jsp.PageContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.oscache.base.Cache;
import com.opensymphony.oscache.web.ServletCacheAdministrator;
import com.ticket.enumer.PageCacheScope;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.PageCacheKey;
import com.ticket.service.IPageCacheKeyService;
import com.ticket.util.GeneralUtil;

/**
 * 页面缓存key管理业务接口实现类
 * @ClassName: IPageCacheKeyService   
 * @Description: 提供页面缓存key管理操作的增删改查   
 * @author 涂有  
 * @date 2015-12-23 10:39:35
 */
public class PageCacheKeyServiceImpl extends BaseServiceImpl<PageCacheKey, String> implements IPageCacheKeyService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(PageCacheKeyServiceImpl.class);
	
	/**
	 * 保存数据库中的所有缓存key对象在内存，提高查询速度
	 */
	List<PageCacheKey> keys;
	
	/**
	 * @Description：初始化即把所有的缓存配置加载到内存
	 * @date 2015年12月24日 上午9:34:13
	 */
	public void init(){
		
		keys = queryAll(PageCacheKey.class);
	}
	
	@Override
	public void persist(Object key) {
		
		dbDAO.persist(key);
		keys.add((PageCacheKey)key);
	}
	
	@Override
	public void merge(Object key) {
		
		PageCacheKey cacheKey = (PageCacheKey)key;
		PageCacheKey pageCacheKey = dbDAO.queryById(this.getTableNameFromEntity(PageCacheKey.class), cacheKey.getId());
		
		pageCacheKey.setKeyName(cacheKey.getKeyName());
		pageCacheKey.setTime(cacheKey.getTime());
		pageCacheKey.setScope(cacheKey.getScope());
		pageCacheKey.setGroup(cacheKey.getGroup());
		pageCacheKey.setRemarks(cacheKey.getRemarks());
		pageCacheKey.setCron(cacheKey.getCron());
		
		dbDAO.merge(pageCacheKey);
		
		keys.remove(key);
		keys.add(pageCacheKey);
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		PageCacheKey pageCacheKey = dbDAO.queryById(this.getTableNameFromEntity(PageCacheKey.class), id);
		dbDAO.remove(pageCacheKey);
		
		keys.remove(pageCacheKey);
		return true;
	}

	@Override
	public PageCacheKey get(String keyName) {
		
		for(PageCacheKey key : keys){
			
			if(key.getKeyName().equals(keyName)){
				
				return key;
			}
		}
		return null;
	}
	
	@Override
	public PageCacheKey getById(String id) {
		
		for(PageCacheKey key : keys){
			
			if(key.getId().equals(id)){
				
				return key;
			}
		}
		return null;
	}

	@Override
	public void refresh(String ids) {

		ServletContext sc = ServletActionContext.getServletContext();
		ServletCacheAdministrator cacheAdmin = ServletCacheAdministrator.getInstance(sc);
		String[] idArray = ids.split(",");
		for(String id : idArray){
			
			PageCacheKey key = getById(id);
			Cache cache = null;
			String actualKey = null;
			if(key.getScope() == PageCacheScope.application){
				
				cache = cacheAdmin.getAppScopeCache(sc);
				actualKey = cacheAdmin.generateEntryKey(key.getKeyName(), ServletActionContext.getRequest(), PageContext.APPLICATION_SCOPE);
			}else{
				
				cache = cacheAdmin.getSessionScopeCache(ServletActionContext.getRequest().getSession());
				actualKey = cacheAdmin.generateEntryKey(key.getKeyName(), ServletActionContext.getRequest(), PageContext.SESSION_SCOPE);
			}
			
			if(GeneralUtil.isNotNull(actualKey)){
				
				cache.flushEntry(actualKey);
			}
		}
	}

	@Override
	public void refreshAll() {
		
		ServletContext sc = ServletActionContext.getServletContext();
		ServletCacheAdministrator cacheAdmin = ServletCacheAdministrator.getInstance(sc);
		cacheAdmin.flushAll();
	}

}