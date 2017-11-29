package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.Advert;
import com.ticket.pojo.AdvertFlight;
import com.ticket.service.IAdvertFlightService;
import com.ticket.util.GeneralUtil;

/**
 * 航班详情对应广告控制器
 * @ClassName: AdvertFlightAction   
 * @Description:  提供航班详情对应广告的相关操作方法. 
 * @author HiSay  
 * @date 2016-09-28 11:33:27
 *
 */
public class AdvertFlightAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//航班详情对应广告的业务层
	@Resource private IAdvertFlightService advertFlightService;
	//航班详情对应广告实体
	private AdvertFlight advertFlight;
	//主键
	private String id;
    //广告
	private Advert advert;
    //到达城市
	private String arriveCity;
	
	/**
	 * 添加航班详情对应广告
	 * @Title: AdvertFlightAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addAdvertFlight";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(advert)) {
				data = AjaxData.responseError(getText("advert.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(arriveCity)) {
				data = AjaxData.responseError(getText("arriveCity.required"));
				return JSON;
			}
			//保存航班详情对应广告实体
			boolean isSuc = advertFlightService.persist(advert, arriveCity, versionFlag);
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
	 * 修改航班详情对应广告
	 * @Title: AdvertFlightAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setAdvertFlight(advertFlightService.queryById(AdvertFlight.class.getSimpleName(), id));
			return "editAdvertFlight";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(advert)) {
				data = AjaxData.responseError(getText("advert.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(arriveCity)) {
				data = AjaxData.responseError(getText("arriveCity.required"));
				return JSON;
			}
			//修改航班详情对应广告实体
			boolean isSuc = advertFlightService.merge(id, advert, arriveCity,  versionFlag);
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
	 * 管理航班详情对应广告实体
	 * @Title: AdvertFlightAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(advertFlightService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageAdvertFlight";
	}
	
	/**
	 * 查看回收站
	 * @Title: AdvertFlightAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(advertFlightService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleAdvertFlight";
	}
	
	/**
	 * 逻辑删除航班详情对应广告对象
	 * @Title: AdvertFlightAction
	 * @Description: 把航班详情对应广告对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = advertFlightService.logicDeleteEntity(AdvertFlight.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除航班详情对应广告对象
	 * @Title: AdvertFlightAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = advertFlightService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个航班详情对应广告对象
	 * @Title: AdvertFlightAction
	 * @Description: 从回收站还原航班详情对应广告对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = advertFlightService.restoreEntity(AdvertFlight.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核航班详情对应广告对象
	 * @Title: AdvertFlightAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = advertFlightService.auditEntity(AdvertFlight.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: AdvertFlightAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = advertFlightService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public AdvertFlight getAdvertFlight() {
		return advertFlight;
	}
	public void setAdvertFlight(AdvertFlight advertFlight) {
		this.advertFlight = advertFlight;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Advert getAdvert() {
		return advert;
	}
	public void setAdvert(Advert advert) {
		this.advert = advert;
	}
	public String getArriveCity() {
		return arriveCity;
	}
	public void setArriveCity(String arriveCity) {
		this.arriveCity = arriveCity;
	}
}
