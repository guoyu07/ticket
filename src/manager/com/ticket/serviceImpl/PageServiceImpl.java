package com.ticket.serviceImpl;


import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.KeyWordLocationPage;
import com.ticket.pojo.Page;
import com.ticket.service.ICommonSearchService;
import com.ticket.service.IPageService;
import com.ticket.util.DecoderUtil;

/**
 * 搜索页面业务接口实现类
 * @ClassName: IPageService   
 * @Description: 提供搜索页面操作的增删改查   
 * @author HiSay  
 * @date 2016-09-30 15:12:25
 *
 */
public class PageServiceImpl extends BaseServiceImpl<Page, String> implements IPageService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(PageServiceImpl.class);
	@Resource
	private ICommonSearchService commonSearchService;

	@Override
	public boolean persist(String name,String url,String pcUrl,String remark, String versionFlag) throws ServiceException {
		Page page = new Page();
		page.setName(DecoderUtil.UtfDecoder(name));
		page.setUrl(DecoderUtil.UtfDecoder(url));
		page.setPcUrl(DecoderUtil.UtfDecoder(pcUrl));
		page.setRemark(DecoderUtil.UtfDecoder(remark));
		dbDAO.persist(page);
		
		commonSearchService.sendFlushNotify();
		return true;
	}
	
	@Override
	public boolean merge(String id, String name,String url,String pcUrl,String remark, String versionFlag) throws ServiceException {
		Page page = dbDAO.queryById(this.getTableNameFromEntity(Page.class), id);
		page.setName(DecoderUtil.UtfDecoder(name));
		page.setUrl(DecoderUtil.UtfDecoder(url));
		page.setPcUrl(DecoderUtil.UtfDecoder(pcUrl));
		page.setRemark(DecoderUtil.UtfDecoder(remark));
		dbDAO.merge(page);
		
		commonSearchService.sendFlushNotify();
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		Page page = dbDAO.queryById(this.getTableNameFromEntity(Page.class), id);
		dbDAO.remove(page);
		
		commonSearchService.sendFlushNotify();
		return true;
	}

	@Override
	public List<Page> queryNotIn(String keywordLocation_id) {

		List<Page> list = dbDAO.executeJPQLForQueryEntity(
				"select s from " + Page.class.getName() + " s where"
				+ " not exists(select wp from " + KeyWordLocationPage.class.getName() + " wp where wp.keyword.id=? and wp.page=s)", 
				keywordLocation_id);
		return list;
	}

}