package com.ticket.serviceImpl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.CommonSearch;
import com.ticket.pojo.KeyWordLocation;
import com.ticket.pojo.KeyWordLocationPage;
import com.ticket.pojo.Page;
import com.ticket.service.ICommonSearchService;
import com.ticket.service.IKeyWordLocationPageService;
import com.ticket.service.IKeyWordLocationService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

/**
 * 关键词定位业务接口实现类
 * @ClassName: IKeyWordLocationService   
 * @Description: 提供关键词定位操作的增删改查   
 * @author HiSay  
 * @date 2016-09-28 15:43:22
 */
public class KeyWordLocationServiceImpl extends BaseServiceImpl<KeyWordLocation, String> implements IKeyWordLocationService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(KeyWordLocationServiceImpl.class);
	@Resource
	private IKeyWordLocationPageService keyWordLocationPageService;
	@Resource
	private ICommonSearchService commonSearchService;
	
	/**
	 * 初始化
	 */
	@Override
	public void init(){
		
		List<KeyWordLocation> locations = queryAll(KeyWordLocation.class);
		for(int i = 0; i < locations.size(); i++){
			
			KeyWordLocation location = locations.get(i);
			List<KeyWordLocationPage> pages = keyWordLocationPageService.query(location.getId());
			for(int j = 0; j < pages.size(); j++){
				
				KeyWordLocationPage relation = pages.get(j);
				Page page = pages.get(j).getPage();
				CommonSearch search = CommonSearchServiceImpl.location.get(relation.getId());
				//如果为空，就新建
				if(search == null){
					
					search = new CommonSearch();
				}
				search.setTitle(page.getName());
				search.setSeo(location.getKeyword());
				search.setCreateTime(page.getStatus().getCreateTime());
				search.setUrl(page.getUrl());
				search.setPcUrl(page.getPcUrl());
				search.setOrderValue(relation.getStatus().getOrderValue());
				CommonSearchServiceImpl.location.put(relation.getId(), search);
			}
		}
	}

	@Override
	public boolean persist(String keyword) throws ServiceException {
		KeyWordLocation keyWordLocation = new KeyWordLocation();
		keyWordLocation.setKeyword(DecoderUtil.UtfDecoder(keyword));
		CommonEntity status = keyWordLocation.getStatus();
		status.setVersionFlag(versionFlag);
		keyWordLocation.setStatus(status);
		dbDAO.persist(keyWordLocation);
		return true;
	}
	
	@Override
	public boolean merge(String id,String keyword) throws ServiceException {
		KeyWordLocation keyWordLocation = dbDAO.queryById(this.getTableNameFromEntity(KeyWordLocation.class), id);
		keyWordLocation.setKeyword(DecoderUtil.UtfDecoder(keyword));
		CommonEntity status = keyWordLocation.getStatus();
		status.setVersionFlag(versionFlag);
		keyWordLocation.setStatus(status);
		dbDAO.merge(keyWordLocation);
		commonSearchService.sendFlushNotify();
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		KeyWordLocation keyWordLocation = dbDAO.queryById(this.getTableNameFromEntity(KeyWordLocation.class), id);
		dbDAO.remove(keyWordLocation);
		commonSearchService.sendFlushNotify();
		return true;
	}

	@Override
	public List<KeyWordLocation> query(String keyword) {
		
		if(GeneralUtil.isNull(keyword)){
			
			return new ArrayList<KeyWordLocation>();
		}
		
		keyword = DecoderUtil.UtfDecoder(keyword).trim();
		List<KeyWordLocation> list = dbDAO.executeJPQLForQueryEntity("select s from " + KeyWordLocation.class.getName() + " s where s.keyword=?", keyword);
		return list;
	}

}