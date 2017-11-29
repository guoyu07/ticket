package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.WifiArea;
import com.ticket.service.IWifiAreaService;
import com.ticket.util.GeneralUtil;

/**
 * ifi区域控制器
 * @ClassName: WifiAreaAction   
 * @Description:  提供ifi区域的相关操作方法. 
 * @author HiSay  
 * @date 2016-09-22 10:17:00
 *
 */
public class WifiAreaAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//ifi区域的业务层
	@Resource private IWifiAreaService wifiAreaService;
	//ifi区域实体
	private WifiArea wifiArea;
	//主键
	private String id;
    //区域名称
	private String name;
    //时间间隔（分钟）
	private int interval;
    //备注
	private String remark;
	
	/**
	 * 添加ifi区域
	 * @Title: WifiAreaAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addWifiArea";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError(getText("name.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(interval)) {
				data = AjaxData.responseError(getText("interval.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(remark)) {
				data = AjaxData.responseError(getText("remark.required"));
				return JSON;
			}
			//保存ifi区域实体
			boolean isSuc = wifiAreaService.persist(name, interval, remark, versionFlag);
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
	 * 修改ifi区域
	 * @Title: WifiAreaAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setWifiArea(wifiAreaService.queryById(WifiArea.class.getSimpleName(), id));
			return "editWifiArea";
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
			if(GeneralUtil.isNull(interval)) {
				data = AjaxData.responseError(getText("interval.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(remark)) {
				data = AjaxData.responseError(getText("remark.required"));
				return JSON;
			}
			//修改ifi区域实体
			boolean isSuc = wifiAreaService.merge(id, name, interval, remark,  versionFlag);
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
	 * 管理ifi区域实体
	 * @Title: WifiAreaAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(wifiAreaService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageWifiArea";
	}
	
	/**
	 * 查看回收站
	 * @Title: WifiAreaAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(wifiAreaService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleWifiArea";
	}
	
	/**
	 * 逻辑删除ifi区域对象
	 * @Title: WifiAreaAction
	 * @Description: 把ifi区域对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = wifiAreaService.logicDeleteEntity(WifiArea.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除ifi区域对象
	 * @Title: WifiAreaAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = wifiAreaService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个ifi区域对象
	 * @Title: WifiAreaAction
	 * @Description: 从回收站还原ifi区域对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = wifiAreaService.restoreEntity(WifiArea.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核ifi区域对象
	 * @Title: WifiAreaAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = wifiAreaService.auditEntity(WifiArea.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: WifiAreaAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = wifiAreaService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public WifiArea getWifiArea() {
		return wifiArea;
	}
	public void setWifiArea(WifiArea wifiArea) {
		this.wifiArea = wifiArea;
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
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
