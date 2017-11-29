package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.FCSelfCheckinPosition;
import com.ticket.service.IFCSelfCheckinPositionService;
import com.ticket.util.GeneralUtil;

/**
 * 航空公司自助值机位置表控制器
 * @ClassName: FCSelfCheckinPositionAction   
 * @Description:  提供航空公司自助值机位置表的相关操作方法. 
 * @author HiSay  
 * @date 2016-03-30 17:01:09
 *
 */
public class FCSelfCheckinPositionAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//航空公司自助值机位置表的业务层
	@Resource private IFCSelfCheckinPositionService fCSelfCheckinPositionService = null;
	//航空公司自助值机位置表实体
	private FCSelfCheckinPosition fCSelfCheckinPosition = null;
	//主键
	private String id = null;
    //值机位置名称
	private String name = null;
    //航空公司id
	private String flightCompany_id = null;
    //楼层号
	private String floorNumber = null;
    //经度
	private Double longitude = null;
    //纬度
	private Double latitude = null;
	
	/**
	 * 添加航空公司自助值机位置表
	 * @Title: FCSelfCheckinPositionAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addFCSelfCheckinPosition";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError(getText("name.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(flightCompany_id)) {
				data = AjaxData.responseError(getText("flightCompany_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(floorNumber)) {
				data = AjaxData.responseError(getText("floorNumber.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(longitude)) {
				data = AjaxData.responseError(getText("longitude.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(latitude)) {
				data = AjaxData.responseError(getText("latitude.required"));
				return JSON;
			}
			//保存航空公司自助值机位置表实体
			boolean isSuc = fCSelfCheckinPositionService.persist(name, flightCompany_id, floorNumber, longitude, latitude, versionFlag);
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
	 * 修改航空公司自助值机位置表
	 * @Title: FCSelfCheckinPositionAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setFCSelfCheckinPosition(fCSelfCheckinPositionService.queryById(FCSelfCheckinPosition.class.getSimpleName(), id));
			return "editFCSelfCheckinPosition";
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
			if(GeneralUtil.isNull(flightCompany_id)) {
				data = AjaxData.responseError(getText("flightCompany_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(floorNumber)) {
				data = AjaxData.responseError(getText("floorNumber.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(longitude)) {
				data = AjaxData.responseError(getText("longitude.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(latitude)) {
				data = AjaxData.responseError(getText("latitude.required"));
				return JSON;
			}
			//修改航空公司自助值机位置表实体
			boolean isSuc = fCSelfCheckinPositionService.merge(id, name, flightCompany_id, floorNumber, longitude, latitude,  versionFlag);
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
	 * 管理航空公司自助值机位置表实体
	 * @Title: FCSelfCheckinPositionAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(fCSelfCheckinPositionService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageFCSelfCheckinPosition";
	}
	
	/**
	 * 查看回收站
	 * @Title: FCSelfCheckinPositionAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(fCSelfCheckinPositionService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleFCSelfCheckinPosition";
	}
	
	/**
	 * 逻辑删除航空公司自助值机位置表对象
	 * @Title: FCSelfCheckinPositionAction
	 * @Description: 把航空公司自助值机位置表对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = fCSelfCheckinPositionService.logicDeleteEntity(FCSelfCheckinPosition.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除航空公司自助值机位置表对象
	 * @Title: FCSelfCheckinPositionAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = fCSelfCheckinPositionService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个航空公司自助值机位置表对象
	 * @Title: FCSelfCheckinPositionAction
	 * @Description: 从回收站还原航空公司自助值机位置表对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = fCSelfCheckinPositionService.restoreEntity(FCSelfCheckinPosition.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核航空公司自助值机位置表对象
	 * @Title: FCSelfCheckinPositionAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = fCSelfCheckinPositionService.auditEntity(FCSelfCheckinPosition.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: FCSelfCheckinPositionAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = fCSelfCheckinPositionService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public FCSelfCheckinPosition getFCSelfCheckinPosition() {
		return fCSelfCheckinPosition;
	}
	public void setFCSelfCheckinPosition(FCSelfCheckinPosition fCSelfCheckinPosition) {
		this.fCSelfCheckinPosition = fCSelfCheckinPosition;
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
	public String getFlightCompany_id() {
		return flightCompany_id;
	}
	public void setFlightCompany_id(String flightCompany_id) {
		this.flightCompany_id = flightCompany_id;
	}
	public String getFloorNumber() {
		return floorNumber;
	}
	public void setFloorNumber(String floorNumber) {
		this.floorNumber = floorNumber;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
}
