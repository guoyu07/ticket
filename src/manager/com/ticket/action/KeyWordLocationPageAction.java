package com.ticket.action;

import java.util.List;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.KeyWordLocation;
import com.ticket.pojo.KeyWordLocationPage;
import com.ticket.pojo.Page;
import com.ticket.service.IKeyWordLocationPageService;
import com.ticket.service.IPageService;
import com.ticket.util.GeneralUtil;

/**
 * 关键词定位关联页面控制器
 * @ClassName: KeyWordLocationPageAction   
 * @Description:  提供关键词定位关联页面的相关操作方法. 
 * @author HiSay  
 * @date 2016-09-30 15:38:12
 *
 */
public class KeyWordLocationPageAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//关键词定位关联页面的业务层
	@Resource private IKeyWordLocationPageService keyWordLocationPageService;
	//搜索页面的业务层
	@Resource private IPageService pageService;
	//关键词定位关联页面实体
	private KeyWordLocationPage keyWordLocationPage;
	//主键
	private String id;
    //关键词
	private KeyWordLocation keyword;
    //关联页面
	private String page_id;
	private List<Page> pages;
	
	/**
	 * 添加关键词定位关联页面
	 * @Title: KeyWordLocationPageAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			
			pages = pageService.queryNotIn(id);
			return "addKeyWordLocationPage";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("keyword.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(page_id)) {
				data = AjaxData.responseError(getText("page.required"));
				return JSON;
			}
			//保存关键词定位关联页面实体
			boolean isSuc = keyWordLocationPageService.persist(keyWordLocationPageService.get(KeyWordLocation.class, id), keyWordLocationPageService.get(Page.class, page_id), orderValue);
			//根据保存结果返回页面
			if(isSuc) {
				data = AjaxData.responseSuccess(getText("addSuccess"));
			} else {
				data = AjaxData.responseError(getText("addFailed"));
			}
			return JSON;
		}
	}
	
	/**
	 * 管理关键词定位关联页面实体
	 * @Title: KeyWordLocationPageAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(keyWordLocationPageService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageKeyWordLocationPage";
	}
	
	/**
	 * 查看回收站
	 * @Title: KeyWordLocationPageAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(keyWordLocationPageService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleKeyWordLocationPage";
	}
	
	/**
	 * 逻辑删除关键词定位关联页面对象
	 * @Title: KeyWordLocationPageAction
	 * @Description: 把关键词定位关联页面对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = keyWordLocationPageService.logicDeleteEntity(KeyWordLocationPage.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除关键词定位关联页面对象
	 * @Title: KeyWordLocationPageAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = keyWordLocationPageService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个关键词定位关联页面对象
	 * @Title: KeyWordLocationPageAction
	 * @Description: 从回收站还原关键词定位关联页面对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = keyWordLocationPageService.restoreEntity(KeyWordLocationPage.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核关键词定位关联页面对象
	 * @Title: KeyWordLocationPageAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = keyWordLocationPageService.auditEntity(KeyWordLocationPage.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: KeyWordLocationPageAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = keyWordLocationPageService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public KeyWordLocationPage getKeyWordLocationPage() {
		return keyWordLocationPage;
	}
	public void setKeyWordLocationPage(KeyWordLocationPage keyWordLocationPage) {
		this.keyWordLocationPage = keyWordLocationPage;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public KeyWordLocation getKeyword() {
		return keyword;
	}
	public void setKeyword(KeyWordLocation keyword) {
		this.keyword = keyword;
	}
	public IKeyWordLocationPageService getKeyWordLocationPageService() {
		return keyWordLocationPageService;
	}

	public void setKeyWordLocationPageService(
			IKeyWordLocationPageService keyWordLocationPageService) {
		this.keyWordLocationPageService = keyWordLocationPageService;
	}

	public IPageService getPageService() {
		return pageService;
	}

	public void setPageService(IPageService pageService) {
		this.pageService = pageService;
	}

	public List<Page> getPages() {
		return pages;
	}

	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

	public String getPage_id() {
		return page_id;
	}

	public void setPage_id(String page_id) {
		this.page_id = page_id;
	}
}
