package com.ticket.action;

import java.util.List;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.Page;
import com.ticket.service.IPageService;
import com.ticket.util.GeneralUtil;

/**
 * 搜索页面控制器
 * @ClassName: PageAction   
 * @Description:  提供搜索页面的相关操作方法. 
 * @author HiSay  
 * @date 2016-09-30 15:12:25
 *
 */
public class PageAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//搜索页面的业务层
	@Resource private IPageService pageService;
	//搜索页面实体
	private Page page;
	private List<Page> pages;
	//主键
	private String id;
    //页面名称
	private String name;
    //h5链接地址
	private String url;
    //pc端链接地址
	private String pcUrl;
    //描述
	private String remark;
	
	/**
	 * 添加搜索页面
	 * @Title: PageAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			
			return "addPage";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError(getText("name.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(url)) {
				data = AjaxData.responseError(getText("url.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(pcUrl)) {
				data = AjaxData.responseError(getText("pcUrl.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(remark)) {
				data = AjaxData.responseError(getText("remark.required"));
				return JSON;
			}
			//保存搜索页面实体
			boolean isSuc = pageService.persist(name, url, pcUrl, remark, versionFlag);
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
	 * 修改搜索页面
	 * @Title: PageAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setPage(pageService.queryById(Page.class.getSimpleName(), id));
			return "editPage";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError(getText("name.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(url)) {
				data = AjaxData.responseError(getText("url.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(pcUrl)) {
				data = AjaxData.responseError(getText("pcUrl.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(remark)) {
				data = AjaxData.responseError(getText("remark.required"));
				return JSON;
			}
			//修改搜索页面实体
			boolean isSuc = pageService.merge(id, name, url, pcUrl, remark,  versionFlag);
			//根据修改结果返回页面
			if(isSuc) {
				data = AjaxData.responseSuccess(getText("editSuccess"));
			} else {
				data = AjaxData.responseError(getText("editFailed"));
			}
			return JSON;
		}
	}
	
	/**
	 * 管理搜索页面实体
	 * @Title: PageAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(pageService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "managePage";
	}
	
	/**
	 * 查看回收站
	 * @Title: PageAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(pageService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recyclePage";
	}
	
	/**
	 * 逻辑删除搜索页面对象
	 * @Title: PageAction
	 * @Description: 把搜索页面对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = pageService.logicDeleteEntity(Page.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除搜索页面对象
	 * @Title: PageAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = pageService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个搜索页面对象
	 * @Title: PageAction
	 * @Description: 从回收站还原搜索页面对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = pageService.restoreEntity(Page.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核搜索页面对象
	 * @Title: PageAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = pageService.auditEntity(Page.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: PageAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = pageService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPcUrl() {
		return pcUrl;
	}
	public void setPcUrl(String pcUrl) {
		this.pcUrl = pcUrl;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
}
