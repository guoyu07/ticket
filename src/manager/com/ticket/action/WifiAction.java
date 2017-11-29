package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.Wifi;
import com.ticket.service.IWifiAreaService;
import com.ticket.service.IWifiService;
import com.ticket.util.GeneralUtil;

/**
 * wifi设备控制器
 * @ClassName: WifiAction   
 * @Description:  提供wifi设备的相关操作方法. 
 * @author HiSay  
 * @date 2016-08-09 10:49:51
 */
public class WifiAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//ifi设备的业务层
	@Resource private IWifiService wifiService;
	@Resource private IWifiAreaService wifiAreaService;
	//ifi设备实体
	private Wifi wifi;
	//主键
	private String id;
    //设备id
	private String sid;
	//区域名称
	private String area_id;
    //描述
	private String remark;
	
	/**
	 * 添加ifi设备
	 * @Title: WifiAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addWifi";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(sid)) {
				data = AjaxData.responseError(getText("sid.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(area_id)) {
				data = AjaxData.responseError("请选择区域");
				return JSON;
			}
			if(GeneralUtil.isNull(remark)) {
				data = AjaxData.responseError(getText("remark.required"));
				return JSON;
			}
			//保存ifi设备实体
			boolean isSuc = wifiService.persist(sid, area_id, remark, versionFlag);
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
	 * 修改ifi设备
	 * @Title: WifiAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setWifi(wifiService.queryById(Wifi.class.getSimpleName(), id));
			return "editWifi";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(sid)) {
				data = AjaxData.responseError(getText("sid.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(area_id)) {
				data = AjaxData.responseError("请选择区域");
				return JSON;
			}
			if(GeneralUtil.isNull(remark)) {
				data = AjaxData.responseError(getText("remark.required"));
				return JSON;
			}
			//修改wifi设备实体
			boolean isSuc = wifiService.merge(id, sid, area_id, remark,  versionFlag);
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
	 * 管理ifi设备实体
	 * @Title: WifiAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(wifiService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageWifi";
	}
	
	/**
	 * 查看回收站
	 * @Title: WifiAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(wifiService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleWifi";
	}
	
	/**
	 * 逻辑删除ifi设备对象
	 * @Title: WifiAction
	 * @Description: 把ifi设备对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = wifiService.logicDeleteEntity(Wifi.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除ifi设备对象
	 * @Title: WifiAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = wifiService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个ifi设备对象
	 * @Title: WifiAction
	 * @Description: 从回收站还原ifi设备对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = wifiService.restoreEntity(Wifi.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核ifi设备对象
	 * @Title: WifiAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = wifiService.auditEntity(Wifi.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: WifiAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = wifiService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public Wifi getWifi() {
		return wifi;
	}
	public void setWifi(Wifi wifi) {
		this.wifi = wifi;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public IWifiService getWifiService() {
		return wifiService;
	}

	public void setWifiService(IWifiService wifiService) {
		this.wifiService = wifiService;
	}

	public String getArea_id() {
		return area_id;
	}

	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}

	public IWifiAreaService getWifiAreaService() {
		return wifiAreaService;
	}

	public void setWifiAreaService(IWifiAreaService wifiAreaService) {
		this.wifiAreaService = wifiAreaService;
	}
}
