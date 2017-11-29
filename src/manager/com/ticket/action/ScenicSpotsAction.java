package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.constants.JQueryUploadConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.ScenicSpots;
import com.ticket.service.IScenicSpotsService;
import com.ticket.util.GeneralUtil;

/**
 * 旅游景点控制器
 * @ClassName: ScenicSpotsAction   
 * @Description:  提供旅游景点的相关操作方法. 
 * @author HiSay  
 * @date 2016-09-30 09:54:17
 *
 */
public class ScenicSpotsAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//旅游景点的业务层
	@Resource private IScenicSpotsService scenicSpotsService;
	//旅游景点实体
	private ScenicSpots scenicSpots;
	//主键
	private String id;
    //名称
	private String name;
    //景点介绍
	private String introduce;
    //景点详情
	private String detail;
	
	/**
	 * 添加旅游景点
	 * @Title: ScenicSpotsAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addScenicSpots";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError(getText("name.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(introduce)) {
				data = AjaxData.responseError(getText("introduce.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(detail)) {
				data = AjaxData.responseError(getText("detail.required"));
				return JSON;
			}
//			if(GeneralUtil.isNull(news)) {
//				data = AjaxData.responseError(getText("news.required"));
//				return JSON;
//			}
			//保存旅游景点实体
			boolean isSuc = scenicSpotsService.persist(name, introduce, detail, getSinglePictureUrlFromJQueryUpLoader(fileNames, directory, JQueryUploadConstants.PICTURE_TYPE_DEFAULT), versionFlag);
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
	 * 修改旅游景点
	 * @Title: ScenicSpotsAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setScenicSpots(scenicSpotsService.queryById(ScenicSpots.class.getSimpleName(), id));
			return "editScenicSpots";
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
			if(GeneralUtil.isNull(introduce)) {
				data = AjaxData.responseError(getText("introduce.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(detail)) {
				data = AjaxData.responseError(getText("detail.required"));
				return JSON;
			}
//			if(GeneralUtil.isNull(news)) {
//				data = AjaxData.responseError(getText("news.required"));
//				return JSON;
//			}
			//修改旅游景点实体
			boolean isSuc = scenicSpotsService.merge(id, name, introduce, detail, getSinglePictureUrlFromJQueryUpLoader(fileNames, directory, JQueryUploadConstants.PICTURE_TYPE_DEFAULT),  versionFlag);
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
	 * 管理旅游景点实体
	 * @Title: ScenicSpotsAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(scenicSpotsService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageScenicSpots";
	}
	
	/**
	 * 查看回收站
	 * @Title: ScenicSpotsAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(scenicSpotsService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleScenicSpots";
	}
	
	/**
	 * 逻辑删除旅游景点对象
	 * @Title: ScenicSpotsAction
	 * @Description: 把旅游景点对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = scenicSpotsService.logicDeleteEntity(ScenicSpots.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	public String hot() throws ServiceException{
		boolean isSuc = scenicSpotsService.hotEntity(id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("hotSuccess"));
		} else {
			data = AjaxData.responseError(getText("hotFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除旅游景点对象
	 * @Title: ScenicSpotsAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = scenicSpotsService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个旅游景点对象
	 * @Title: ScenicSpotsAction
	 * @Description: 从回收站还原旅游景点对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = scenicSpotsService.restoreEntity(ScenicSpots.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核旅游景点对象
	 * @Title: ScenicSpotsAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = scenicSpotsService.auditEntity(ScenicSpots.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: ScenicSpotsAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = scenicSpotsService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public ScenicSpots getScenicSpots() {
		return scenicSpots;
	}
	public void setScenicSpots(ScenicSpots scenicSpots) {
		this.scenicSpots = scenicSpots;
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
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
}
