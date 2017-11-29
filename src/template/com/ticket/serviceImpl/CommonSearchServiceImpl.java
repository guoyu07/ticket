package com.ticket.serviceImpl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.action.BaseAction;
import com.ticket.enumer.SearchType;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonSearch;
import com.ticket.pojo.CountSearch;
import com.ticket.pojo.SystemDictionary;
import com.ticket.service.ICommonSearchService;
import com.ticket.service.ICountSearchService;
import com.ticket.service.IKeyWordLocationService;
import com.ticket.service.INewsService;
import com.ticket.service.IPreResultDefinitionService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.service.IWordsPackageService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.HttpClientUtil;
import com.ticket.util.UrlUtil;

/**
 * 预定义搜索结果业务接口实现类
 * @ClassName: IPreResultDefinitionService   
 * @Description: 提供预定义搜索结果操作的增删改查   
 * @author HiSay  
 * @date 2015-12-14 18:57:30
 */
public class CommonSearchServiceImpl extends BaseServiceImpl<CommonSearch, String> implements ICommonSearchService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(CommonSearchServiceImpl.class);
	@Resource private INewsService newsService;
	@Resource private IPreResultDefinitionService preResultDefinitionService;
	@Resource private ICountSearchService countSearchService;
	@Resource private ISystemDictionaryService dictionaryService;
	@Resource private IKeyWordLocationService keyWordLocationService;
	@Resource private IWordsPackageService wordsPackageService;
	
	/**
	 * 站内精确定位搜索关键词
	 */
	public static Map<String, CommonSearch> location = new HashMap<String, CommonSearch>();
	
	/**
	 * 站内搜索关键词
	 */
	public static Map<String, CommonSearch> seo = new HashMap<String, CommonSearch>();
	
	/**
	 * 商业搜索关键词
	 */
	public static Map<String, CommonSearch> business = new HashMap<String, CommonSearch>();
	
	@Override
	public void sendFlushNotify(){
		
//		seo = new HashMap<String, CommonSearch>();
//		preResultDefinitionService.init();
		List<SystemDictionary> urls = dictionaryService.querySubByParentName("tomcat_urls");
		Executor executor = Executors.newFixedThreadPool(5);
		for(final SystemDictionary url : urls){
			
			if(GeneralUtil.isNotNull(url.getValue())){
				
				executor.execute(new Runnable() {
					
					@Override
					public void run() {
						
						HttpClientUtil.get(url.getValue() + "preResultDefinition_flushMemory.action", null);
					}
				});
			}
		}
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<CommonSearch> searchListByKeyword(SearchType type, String keyword, Integer startSize, Integer getCount, String ip) throws ServiceException {
		
		List<CommonSearch> searchList = new ArrayList<CommonSearch>();
		if(keyword == null){
			
			return searchList;
		}
		
		keyword = DecoderUtil.UtfDecoder(keyword).trim();
		ServletActionContext.getRequest().getSession().setAttribute("commonKeyword", keyword);
		
		//查询搜索结果
		StringBuffer touchKeyword = new StringBuffer();
		StringBuffer touchNegative = new StringBuffer();
		
		Collection<CommonSearch> commonSearchs = getSearchListByType(type);
		for(CommonSearch search : commonSearchs){
			
			if(search.getSeo() != null){ //然后再判断url关键词是否为空，以免出现空指针异常
				
				String negative = in(search.getNegative(), keyword, true);
				String _negative = in(search.get_negative(), keyword, false);
				String touch = in(search.getSeo(), keyword, true);
				String _touch = in(search.getSeo(), keyword, false);
				
				if(GeneralUtil.isNotNull(negative)){
					
					touchNegative.append(negative).append(",");
				}
				if(GeneralUtil.isNotNull(_negative)){
					
					touchNegative.append(_negative).append(",");
				}
				if(GeneralUtil.isNotNull(_touch)){
					
					touchKeyword.append(_touch).append(",");
				}else if(GeneralUtil.isNotNull(touch)){
					
					touchKeyword.append(touch).append(",");
				}
				
				if(_negative == null){ //如果不精确否定
					
					if(_touch != null || (touch != null && negative == null)){ //模糊否定为
						
						searchList.add(search);
					}
				}
			}
		}
		
		Collections.sort(searchList, new Comparator<CommonSearch>() {

			@Override
			public int compare(CommonSearch o1, CommonSearch o2) {
				
				if(o1.getOrderValue() > o2.getOrderValue()){
					
					return -1;
				}else if(o1.getOrderValue() == o2.getOrderValue()){
					
					return 0;
				}
				return 1;
			}
		});
		
		//存储搜索统计
		if(touchKeyword != null && touchKeyword.length() > 1){
			
			touchKeyword.deleteCharAt(touchKeyword.length() - 1);
		}
		if(touchNegative != null && touchNegative.length() > 1){
			
			touchNegative.deleteCharAt(touchNegative.length() - 1);
		}
		
		CountSearch search = countSearchService.persist(keyword, touchKeyword.toString(), touchNegative.toString(), 
				UrlUtil.getIpAddr(BaseAction.request), getMember(), type);
		ActionContext.getContext().put("id", search.getId());
		
		return searchList;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<CommonSearch> searchListByKeyword2(SearchType type, String keyword, Integer startSize, Integer getCount, String ip) throws ServiceException {
		
		List<CommonSearch> searchList = new ArrayList<CommonSearch>();
		if(keyword == null){
			
			return searchList;
		}
		
		keyword = DecoderUtil.UtfDecoder(keyword).trim();
		ServletActionContext.getRequest().getSession().setAttribute("commonKeyword", keyword);
		
		//查询搜索结果
		StringBuffer touchKeyword = new StringBuffer();
		
		Collection<CommonSearch> commonSearchs = getSearchListByType(type);
		for(CommonSearch search : commonSearchs){
			
			if(search.getSeo() != null){ //然后再判断url关键词是否为空，以免出现空指针异常
				
				String _touch = in(search.getSeo(), keyword, false);
				if(GeneralUtil.isNotNull(_touch)){
					
					touchKeyword.append(_touch).append(",");
					searchList.add(search);
				}
			}
		}
		
		Collections.sort(searchList, new Comparator<CommonSearch>() {
			
			@Override
			public int compare(CommonSearch o1, CommonSearch o2) {
				
				if(o1.getOrderValue() > o2.getOrderValue()){
					
					return -1;
				}else if(o1.getOrderValue() == o2.getOrderValue()){
					
					return 0;
				}
				return 1;
			}
		});
		
		//存储搜索统计
		if(touchKeyword != null && touchKeyword.length() > 1){
			
			touchKeyword.deleteCharAt(touchKeyword.length() - 1);
		}
		CountSearch search = countSearchService.persist(keyword, touchKeyword.toString(), null, 
				UrlUtil.getIpAddr(BaseAction.request), getMember(), type);
		ActionContext.getContext().put("id", search.getId());
		
		return searchList;
	}
	
	@Override
	public String in(String seo, String keyword, boolean like) {
		
		if(GeneralUtil.isNotNull(seo)){
			
			String[] keys = seo.split(",");
			for(int i = 0; i < keys.length; i++){
				
				String key = keys[i].trim();
				if(GeneralUtil.isNotNull(key)){
					
					if(like && keyword.toLowerCase().contains(key.toLowerCase())){
						
						return key;
					}else if(!like && keyword.toLowerCase().equals(key.toLowerCase())){
						
						return key;
					}
				}
			}
		}
		return null;
	}

	@Override
	public String getCountSearchId() throws ServiceException {
		String id = (String)ActionContext.getContext().get("id");
		return id;
	}

	@Override
	public List<CommonSearch> removeSameUrl(List<CommonSearch> commonSearchs, List<CommonSearch> keyWordLocations) {
		
		List<CommonSearch> newCommonSearchs = new ArrayList<CommonSearch>();
		for(int i = 0; i < commonSearchs.size(); i++){
			
			CommonSearch commonSearch = commonSearchs.get(i);
			boolean same = false; //检测是否相同的标识
			for(int j = 0; j < keyWordLocations.size(); j++){
				
				CommonSearch otherSearch = keyWordLocations.get(j);
				if(GeneralUtil.isNotNull(commonSearch.getUrl()) && GeneralUtil.isNotNull(otherSearch.getUrl())){
					
					String first = commonSearch.getUrl().split("\\?")[0];
					String second = otherSearch.getUrl().split("\\?")[0];
					
					try {
						URI firstURI = new URI(first);
						URI secondURI = new URI(second);
						if(firstURI.getPath().equals(secondURI.getPath())){
							
							same = true;
							break;
						}
					} catch (URISyntaxException e) {
						e.printStackTrace();
					}
				}
			}
			
			if(!same){
				
				newCommonSearchs.add(commonSearch);
			}
		}
		
		return newCommonSearchs;
	}
	
	private Collection<CommonSearch> getSearchListByType(SearchType type){
		
		if(type == SearchType.business){
			
			return business.values();
		}
		if(type == SearchType.location){
			
			return location.values();
		}
		return seo.values();
		
	}
}