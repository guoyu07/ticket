package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.PageRedirectTemplate;
import com.ticket.service.IPageRedirectTemplateService;
import com.ticket.util.DecoderUtil;

/**
 * 页面跳转模板业务接口实现类
 * @ClassName: IPageRedirectTemplateService   
 * @Description: 提供页面跳转模板操作的增删改查   
 * @author HiSay  
 * @date 2015-10-22 14:00:50
 *
 */
public class PageRedirectTemplateServiceImpl extends BaseServiceImpl<PageRedirectTemplate, String> implements IPageRedirectTemplateService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(PageRedirectTemplateServiceImpl.class);
	
	@Override
	public boolean merge(String id, String name,String toPage,Integer type,Integer isSinglePage, String versionFlag, String directory, String toPageAjax) throws ServiceException {
		PageRedirectTemplate pageRedirectTemplate = dbDAO.queryById(this.getTableNameFromEntity(PageRedirectTemplate.class), id);
		pageRedirectTemplate.setName(DecoderUtil.UtfDecoder(name));
		pageRedirectTemplate.setToPage(DecoderUtil.UtfDecoder(toPage));
		pageRedirectTemplate.setType(type);
		pageRedirectTemplate.setIsSinglePage(isSinglePage);
		pageRedirectTemplate.setDirectory(directory);
		pageRedirectTemplate.setToPageAjax(toPageAjax);
		CommonEntity status = pageRedirectTemplate.getStatus();
		status.setVersionFlag(versionFlag);
		pageRedirectTemplate.setStatus(status);
		dbDAO.merge(pageRedirectTemplate);
		return true;
	}

	@Override
	public boolean persist(String name,String toPage,Integer type,Integer isSinglePage, String versionFlag, String directory, String toPageAjax) throws ServiceException {
		PageRedirectTemplate pageRedirectTemplate = new PageRedirectTemplate();
		pageRedirectTemplate.setName(DecoderUtil.UtfDecoder(name));
		pageRedirectTemplate.setToPage(DecoderUtil.UtfDecoder(toPage));
		pageRedirectTemplate.setType(type);
		pageRedirectTemplate.setIsSinglePage(isSinglePage);
		pageRedirectTemplate.setDirectory(directory);
		pageRedirectTemplate.setToPageAjax(toPageAjax);
		CommonEntity status = pageRedirectTemplate.getStatus();
		status.setVersionFlag(versionFlag);
		pageRedirectTemplate.setStatus(status);
		dbDAO.persist(pageRedirectTemplate);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		PageRedirectTemplate pageRedirectTemplate = dbDAO.queryById(this.getTableNameFromEntity(PageRedirectTemplate.class), id);
		dbDAO.remove(pageRedirectTemplate);
		return true;
	}

	@Override
	public List<PageRedirectTemplate> queryList(String versionFlag, Integer type)
			throws ServiceException {
		return dbDAO.queryByList(this.getTableNameFromEntity(PageRedirectTemplate.class), deleteFlag, versionFlag, "and s.type=?3", new Object[]{type}, orderBy, null); 
	}

	@Override
	public String queryPageNameById(String id) throws ServiceException {
		try {
			PageRedirectTemplate template = this.queryById(PageRedirectTemplate.class.getSimpleName(), id) ;
			return template.getToPage();
		} catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	@Override
	public String queryPageDirectoryById(String id) throws ServiceException {
		try {
			PageRedirectTemplate template = this.queryById(PageRedirectTemplate.class.getSimpleName(), id) ;
			return template.getDirectory();
		} catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	@Override
	public PageRedirectTemplate queryByName(String name) throws ServiceException {
		try {
			return dbDAO.queryByCustom(PageRedirectTemplate.class.getSimpleName(), deleteFlag, versionFlag, "and s.name=?3", new Object[]{name}) ;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}