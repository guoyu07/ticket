package com.ticket.serviceImpl;


import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.oscache.base.Cache;
import com.opensymphony.oscache.web.ServletCacheAdministrator;
import com.ticket.enumer.PageCacheScope;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.PageCacheGroup;
import com.ticket.service.IPageCacheGroupService;

/**
 * 页面缓存group管理业务接口实现类
 * @ClassName: IPageCacheGroupService   
 * @Description: 提供页面缓存group管理操作的增删改查   
 * @author 涂有  
 * @date 2015-12-23 10:34:35
 */
public class PageCacheGroupServiceImpl extends BaseServiceImpl<PageCacheGroup, String> implements IPageCacheGroupService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(PageCacheGroupServiceImpl.class);
	
	/**
	 * 保存数据库中的所有缓存group对象在内存，提高查询速度
	 */
	List<PageCacheGroup> groups;
	
	/**
	 * @Description：初始化即把所有的缓存配置加载到内存
	 * @date 2015年12月24日 上午9:34:13
	 */
	public void init(){
		
		groups = queryAll(PageCacheGroup.class);
	}
	
	@Override
	public void persist(Object group) {
		
		dbDAO.persist(group);
		groups.add((PageCacheGroup)group);
	}
	
	@Override
	public void merge(Object group) {
		
		PageCacheGroup cacheGroup = (PageCacheGroup)group;
		PageCacheGroup pageCacheGroup = dbDAO.queryById(this.getTableNameFromEntity(PageCacheGroup.class), cacheGroup.getId());
		
		pageCacheGroup.setGroupName(cacheGroup.getGroupName());
		pageCacheGroup.setTime(cacheGroup.getTime());
		pageCacheGroup.setScope(cacheGroup.getScope());
		pageCacheGroup.setRemarks(cacheGroup.getRemarks());
		pageCacheGroup.setCron(cacheGroup.getCron());
		
		dbDAO.merge(pageCacheGroup);
		groups.remove(group);
		groups.add(pageCacheGroup);
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		PageCacheGroup pageCacheGroup = dbDAO.queryById(this.getTableNameFromEntity(PageCacheGroup.class), id);
		dbDAO.remove(pageCacheGroup);
		groups.remove(pageCacheGroup);
		return true;
	}

	@Override
	public PageCacheGroup get(String groupName) {
		
		for(PageCacheGroup group : groups){
			
			if(group.getGroupName().endsWith(groupName)){
				
				return group;
			}
		}
		return null;
	}
	
	@Override
	public PageCacheGroup getById(String id) {
		
		for(PageCacheGroup group : groups){
			
			if(group.getId().equals(id)){
				
				return group;
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
			
			PageCacheGroup group = getById(id);
			Cache cache = null;
			if(group.getScope() == PageCacheScope.application){
				
				cache = cacheAdmin.getAppScopeCache(sc);
			}else{
				
				cache = cacheAdmin.getSessionScopeCache(ServletActionContext.getRequest().getSession());
			}
			cache.flushGroup(group.getGroupName());
		}
	}
}