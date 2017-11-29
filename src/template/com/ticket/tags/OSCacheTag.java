package com.ticket.tags;

import javax.servlet.jsp.JspTagException;

import com.opensymphony.oscache.web.tag.CacheTag;
import com.ticket.enumer.PageCacheScope;
import com.ticket.pojo.PageCacheGroup;
import com.ticket.pojo.PageCacheKey;
import com.ticket.service.IPageCacheGroupService;
import com.ticket.service.IPageCacheKeyService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SpringUtil;

/**
 * @Description：OScache缓存标签
 * @author：涂有
 * @date 2015年12月26日 下午3:27:18
 */
public class OSCacheTag extends CacheTag {
	
	private static final long serialVersionUID = 1L;
	
	private IPageCacheGroupService groupService = (IPageCacheGroupService)SpringUtil.getObjectFromSpring("pageCacheGroupService");
	private IPageCacheKeyService keyService = (IPageCacheKeyService)SpringUtil.getObjectFromSpring("pageCacheKeyService");
	
	private PageCacheKey cacheKey;
	private PageCacheGroup cacheGroup;
	
	/**
	 * 所属组
	 */
	private String group;
	
	/**
	 * 设置缓存key值，根据key获取数据库的缓存配置
	 */
	public void setKey(String key){
		
		super.setKey(key);
		
		cacheKey = keyService.get(key);
		cacheGroup = cacheKey.getGroup();
		
		//设置时间
		Integer time = cacheKey.getTime();
		if(time == null){

			time = cacheGroup.getTime();
		}
		setTime(time);
		
		//设置cron表达式
		String cron = cacheKey.getCron();
		if(GeneralUtil.isNull(cron)){
			
			cron = cacheGroup.getCron();
		}
		setCron(cron);
		
		//设置scope
		String scope;
		if(cacheKey.getScope() == PageCacheScope.session || cacheGroup.getScope() == PageCacheScope.session){
			
			scope = PageCacheScope.session.toString();
		}else{
			
			scope = PageCacheScope.application.toString();
		}
		setScope(scope);
	}
	
	/**
	 * 设置缓存group值，根据group获取数据库的缓存配置
	 */
	public void setGroup(String group){
		
		super.setGroups(group);
		
		cacheGroup = groupService.get(group);
		setTime(cacheGroup.getTime());
		setCron(cacheGroup.getCron());
		String scope;
		if(cacheGroup.getScope() == PageCacheScope.session){
			
			scope = PageCacheScope.session.toString();
		}else{
			
			scope = PageCacheScope.application.toString();
		}
		setScope(scope);
	}
	
	public IPageCacheGroupService getGroupService() {
		return groupService;
	}
	public void setGroupService(IPageCacheGroupService groupService) {
		this.groupService = groupService;
	}
	public IPageCacheKeyService getKeyService() {
		return keyService;
	}
	public void setKeyService(IPageCacheKeyService keyService) {
		this.keyService = keyService;
	}
	public PageCacheKey getCacheKey() {
		return cacheKey;
	}
	public void setCacheKey(PageCacheKey cacheKey) {
		this.cacheKey = cacheKey;
	}
	public PageCacheGroup getCacheGroup() {
		return cacheGroup;
	}
	public void setCacheGroup(PageCacheGroup cacheGroup) {
		this.cacheGroup = cacheGroup;
	}

	@Override
	public int doAfterBody() throws JspTagException {
		return super.doAfterBody();
	}

	@Override
	public void doCatch(Throwable throwable) throws Throwable {
		super.doCatch(throwable);
	}

	@Override
	public int doEndTag() throws JspTagException {
		return super.doEndTag();
	}

	@Override
	public void doFinally() {
		super.doFinally();
	}

	@Override
	public int doStartTag() throws JspTagException {
		return super.doStartTag();
	}

	public String getGroup() {
		return group;
	}
}
