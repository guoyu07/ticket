package com.ticket.serviceImpl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.CommonSearch;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.PreResultDefinition;
import com.ticket.service.ICommonSearchService;
import com.ticket.service.IPreResultDefinitionService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.PaginationContext;

/**
 * 预定义搜索结果业务接口实现类
 * @ClassName: IPreResultDefinitionService   
 * @Description: 提供预定义搜索结果操作的增删改查   
 * @author HiSay  
 * @date 2015-12-14 18:57:30
 *
 */
public class PreResultDefinitionServiceImpl extends BaseServiceImpl<PreResultDefinition, String> implements IPreResultDefinitionService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(PreResultDefinitionServiceImpl.class);
	@Resource
	protected ICommonSearchService commonSearchService;
	
	/**
	 * 更新seo内存
	 * @param preResultDefinition
	 */
	@Override
	public void updateSeo(Map<String, CommonSearch> keywords, PreResultDefinition preResultDefinition){
		if(preResultDefinition != null){
			
			CommonSearch search = keywords.get(preResultDefinition.getId());
			//如果为空，就新建
			if(search == null){
				
				search = new CommonSearch();
			}
			search.setTitle(preResultDefinition.getPageName());
			search.setSeo(preResultDefinition.getKeyword());
			search.setCreateTime(preResultDefinition.getStatus().getCreateTime());
			search.setUrl(preResultDefinition.getUrl());
			search.setPcUrl(preResultDefinition.getPcUrl());
			search.setOrderValue(preResultDefinition.getStatus().getOrderValue());
			search.setNegative(preResultDefinition.getNegativeKeyword());
			search.set_negative(preResultDefinition.get_negativeKeyword());
			keywords.put(preResultDefinition.getId(), search);
		}
	}
	
	/**
	 * 初始化
	 */
	@Override
	public void init(){
		
		List<PreResultDefinition> list = queryAll(PreResultDefinition.class);
		for(int i = 0; i < list.size(); i++){
			
			PreResultDefinition item = list.get(i);
			if(item.getType() == 0){
				
				updateSeo(CommonSearchServiceImpl.seo, list.get(i));
			}else if(item.getType() == 1){
				
				updateSeo(CommonSearchServiceImpl.business, list.get(i));
			}
		}
	}
	
	@Override
	public boolean merge(String id,String pageName,String keyword,String negative,String _negative,String description,String url,String pcUrl, String versionFlag, Integer orderValue) throws ServiceException {
		PreResultDefinition preResultDefinition = dbDAO.queryById(this.getTableNameFromEntity(PreResultDefinition.class), id);
		preResultDefinition.setPageName(pageName.trim());
		preResultDefinition.setKeyword(keyword.trim());
		preResultDefinition.setDescription(description);
		preResultDefinition.setUrl(url);
		preResultDefinition.setPcUrl(pcUrl);
		preResultDefinition.setNegativeKeyword(negative);
		preResultDefinition.set_negativeKeyword(_negative);
		CommonEntity status = preResultDefinition.getStatus();
		status.setVersionFlag(versionFlag);
		status.setOrderValue(orderValue) ;
		preResultDefinition.setStatus(status);
		dbDAO.merge(preResultDefinition);
		
		commonSearchService.sendFlushNotify();
		return true;
	}

	@Override
	public boolean persist(Integer type,String pageName,String keyword,String negative,String _negative,String description,String url,String pcUrl, String versionFlag) throws ServiceException {
		PreResultDefinition preResultDefinition = new PreResultDefinition();
		preResultDefinition.setType(type);
		preResultDefinition.setPageName(pageName.trim());
		preResultDefinition.setKeyword(keyword.trim());
		preResultDefinition.setDescription(description);
		preResultDefinition.setUrl(url);
		preResultDefinition.setPcUrl(pcUrl);
		preResultDefinition.setNegativeKeyword(negative);
		preResultDefinition.set_negativeKeyword(_negative);
		CommonEntity status = preResultDefinition.getStatus();
		status.setVersionFlag(versionFlag);
		preResultDefinition.setStatus(status);
		dbDAO.persist(preResultDefinition);
		
		commonSearchService.sendFlushNotify();
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		PreResultDefinition preResultDefinition = dbDAO.queryById(this.getTableNameFromEntity(PreResultDefinition.class), id);
		dbDAO.remove(preResultDefinition);
		
		commonSearchService.sendFlushNotify();
		return true;
	}

	@Override
	public Pagination queryByAdmin(String versionFlag, Integer pageSize)
			throws ServiceException {
		return dbDAO.queryByPageModule(PreResultDefinition.class.getSimpleName(), deleteFlag, versionFlag, null, null, orderValueOrderBy, PaginationContext.getOffset(), pageSize);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Pagination searchByAdmin(String versionFlag, Integer pageSize,
			String keyword) throws ServiceException {
		if(GeneralUtil.isNotNull(keyword)) {
			keyword = DecoderUtil.UtfDecoder(keyword).trim();
			getSession().put("preResultDefinitionKeyword", keyword);
		} else {
			if(getSession().get("preResultDefinitionKeyword") != null) {
				keyword = getSession().get("preResultDefinitionKeyword").toString();
			}
		}
		if(GeneralUtil.isNotNull(keyword)) {
			return dbDAO.queryByPageModule(PreResultDefinition.class.getSimpleName(), deleteFlag, versionFlag, "and s.keyword like ?3", new Object[]{"%"+keyword+"%"}, orderValueOrderBy, PaginationContext.getOffset(), pageSize);
		} else {
			return null;
		}
	}

	@Override
	public PreResultDefinition queryByPagename(String pageName)
			throws ServiceException {
		PreResultDefinition definition = dbDAO.executeJPQLForQuerySingle("select c from " + PreResultDefinition.class.getName() + " c where c.pageName = ? and c.status.deleteFlag=0", PreResultDefinition.class ,pageName);
		return definition;
	}

}