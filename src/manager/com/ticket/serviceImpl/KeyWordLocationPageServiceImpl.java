package com.ticket.serviceImpl;


import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.KeyWordLocation;
import com.ticket.pojo.KeyWordLocationPage;
import com.ticket.pojo.Page;
import com.ticket.service.ICommonSearchService;
import com.ticket.service.IKeyWordLocationPageService;

/**
 * 关键词定位关联页面业务接口实现类
 * @ClassName: IKeyWordLocationPageService   
 * @Description: 提供关键词定位关联页面操作的增删改查   
 * @author HiSay  
 * @date 2016-09-30 15:38:12
 *
 */
public class KeyWordLocationPageServiceImpl extends BaseServiceImpl<KeyWordLocationPage, String> implements IKeyWordLocationPageService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(KeyWordLocationPageServiceImpl.class);
	@Resource
	private ICommonSearchService commonSearchService;

	@Override
	public boolean persist(KeyWordLocation keyword,Page page, int orderValue) throws ServiceException {
		KeyWordLocationPage keyWordLocationPage = new KeyWordLocationPage();
		keyWordLocationPage.setKeyword(keyword);
		keyWordLocationPage.setPage(page);
		CommonEntity status = keyWordLocationPage.getStatus();
		status.setVersionFlag(versionFlag);
		status.setOrderValue(orderValue);
		dbDAO.persist(keyWordLocationPage);
		commonSearchService.sendFlushNotify();
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		KeyWordLocationPage keyWordLocationPage = dbDAO.queryById(this.getTableNameFromEntity(KeyWordLocationPage.class), id);
		dbDAO.remove(keyWordLocationPage);
		commonSearchService.sendFlushNotify();
		return true;
	}

	@Override
	public List<KeyWordLocationPage> query(String keywordLocation_id) {
		
		List<KeyWordLocationPage> list = dbDAO.executeJPQLForQueryEntity("select s from " + KeyWordLocationPage.class.getName() + " s where s.keyword.id=?", keywordLocation_id);
		return list;
	}

}