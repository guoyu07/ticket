package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.KeyWordLocation;
import com.ticket.service.IKeyWordLocationPageService;
import com.ticket.service.IKeyWordLocationService;
import com.ticket.util.GeneralUtil;

/**
 * 关键词定位控制器
 * @ClassName: KeyWordLocationAction   
 * @Description:  提供关键词定位的相关操作方法. 
 * @author HiSay  
 * @date 2016-09-28 15:43:22
 *
 */
public class KeyWordLocationAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//关键词定位的业务层
	@Resource private IKeyWordLocationService keyWordLocationService;
	@Resource private IKeyWordLocationPageService keyWordLocationPageService;
	//关键词定位实体
	private KeyWordLocation keyWordLocation;
	//主键
	private String id;
    //关键词
	private String keyword;
	
	/**
	 * 添加关键词定位
	 * @Title: KeyWordLocationAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addKeyWordLocation";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(keyword)) {
				data = AjaxData.responseError(getText("keyword.required"));
				return JSON;
			}
			//保存关键词定位实体
			boolean isSuc = keyWordLocationService.persist(keyword);
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
	 * 修改关键词定位
	 * @Title: KeyWordLocationAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setKeyWordLocation(keyWordLocationService.queryById(KeyWordLocation.class.getSimpleName(), id));
			return "editKeyWordLocation";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(keyword)) {
				data = AjaxData.responseError(getText("keyword.required"));
				return JSON;
			}
			//修改关键词定位实体
			boolean isSuc = keyWordLocationService.merge(id, keyword);
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
	 * 管理关键词定位实体
	 * @Title: KeyWordLocationAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(keyWordLocationService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageKeyWordLocation";
	}
	
	/**
	 * 查看回收站
	 * @Title: KeyWordLocationAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(keyWordLocationService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleKeyWordLocation";
	}
	
	/**
	 * 逻辑删除关键词定位对象
	 * @Title: KeyWordLocationAction
	 * @Description: 把关键词定位对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = keyWordLocationService.logicDeleteEntity(KeyWordLocation.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除关键词定位对象
	 * @Title: KeyWordLocationAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = keyWordLocationService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个关键词定位对象
	 * @Title: KeyWordLocationAction
	 * @Description: 从回收站还原关键词定位对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = keyWordLocationService.restoreEntity(KeyWordLocation.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核关键词定位对象
	 * @Title: KeyWordLocationAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = keyWordLocationService.auditEntity(KeyWordLocation.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: KeyWordLocationAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = keyWordLocationService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public KeyWordLocation getKeyWordLocation() {
		return keyWordLocation;
	}
	public void setKeyWordLocation(KeyWordLocation keyWordLocation) {
		this.keyWordLocation = keyWordLocation;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public IKeyWordLocationService getKeyWordLocationService() {
		return keyWordLocationService;
	}

	public void setKeyWordLocationService(
			IKeyWordLocationService keyWordLocationService) {
		this.keyWordLocationService = keyWordLocationService;
	}

	public IKeyWordLocationPageService getKeyWordLocationPageService() {
		return keyWordLocationPageService;
	}

	public void setKeyWordLocationPageService(
			IKeyWordLocationPageService keyWordLocationPageService) {
		this.keyWordLocationPageService = keyWordLocationPageService;
	}
}
