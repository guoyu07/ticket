package com.ticket.action;

import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.constants.JQueryUploadConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.FCSelfCheckinPosition;
import com.ticket.pojo.FlightCompany;
import com.ticket.service.IFCSelfCheckinPositionService;
import com.ticket.service.IFlightCompanyService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 航空公司信息控制器
 * @ClassName: FlightCompanyAction   
 * @Description:  提供航空公司信息的相关操作方法. 
 * @author HiSay  
 * @date 2015-11-03 13:39:40
 *
 */
public class FlightCompanyAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//航空公司信息的业务层
	@Resource private IFlightCompanyService flightCompanyService = null;
	@Resource private IFCSelfCheckinPositionService fCheckinPositionService = null;
	@Resource private ISystemOperationLogService logService = null;
	//航空公司信息实体
	private FlightCompany flightCompany = null;
	//主键
	private String id = null;
    //公司名称
	private String name = null;
	//航空公司二字码
	private String twoCode = null;
	//航空公司三字码
	private String threeCode = null;
    //公司电话
	private String phone = null;
    //官网
	private String theOfficialWebsite = null;
    //散客柜台
	private String customerCounter = null;
    //团队柜台
	private String teamCounter = null;
    //头等舱柜台
	private String firstClassCounter = null;
    //应急柜台
	private String emergencyCounter = null;
	//排序值
	private Integer orderValue = 0;
	//经度
	private Double longitude = null;
    //纬度
	private Double latitude = null;
	//自助值机经度
	private String[] selfLongitude = null;
    //自助值机纬度
	private String[] selfLatitude = null;
	//位置名称
	private String[] positionName = null;
	//楼层号
	private String[] floorNumber = null;
	/**
	 * 添加航空公司信息
	 * @Title: FlightCompanyAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addFlightCompany";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError(getText("name.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(twoCode)) {
				data = AjaxData.responseError(getText("twoCode.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(phone)) {
				data = AjaxData.responseError(getText("phone.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(theOfficialWebsite)) {
				data = AjaxData.responseError(getText("theOfficialWebsite.required"));
				return JSON;
			}
			//验证航空公司名是否已存在
			if(flightCompanyService.isExistByName(name)){
				data = AjaxData.responseError(getText("name.exist"));
				return JSON;
			}
			//保存航空公司信息实体
			boolean isSuc = flightCompanyService.persist(name,twoCode,threeCode, phone, theOfficialWebsite, customerCounter, teamCounter, firstClassCounter, emergencyCounter,orderValue,
					getSinglePictureUrlFromJQueryUpLoader(fileNames, directory, JQueryUploadConstants.PICTURE_TYPE_DEFAULT),longitude,latitude,selfLongitude,selfLatitude,positionName,floorNumber, versionFlag);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增航空公司信息";
				String logName = SystemOparetionLogUtil.getLogName();
				String logTime = SystemOparetionLogUtil.getLogTime();
				String logIp = SystemOparetionLogUtil.getLogIp();
				
				logService.persist(logName, logContent, logTime, logIp, versionFlag);
				data = AjaxData.responseSuccess(getText("addSuccess"));
			} else {
				data = AjaxData.responseError(getText("addFailed"));
			}
			return JSON;
		}
	}
	
	/**
	 * 修改航空公司信息
	 * @Title: FlightCompanyAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setFlightCompany(flightCompanyService.queryById(FlightCompany.class.getSimpleName(), id));
			List<FCSelfCheckinPosition> checkPoiList = fCheckinPositionService.queryListByFlightCompanyId(id,versionFlag);
			if(checkPoiList != null && !checkPoiList.isEmpty()){
				ActionContext.getContext().put("poiList", checkPoiList);
			}
			return "editFlightCompany";
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
			if(GeneralUtil.isNull(phone)) {
				data = AjaxData.responseError(getText("phone.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(theOfficialWebsite)) {
				data = AjaxData.responseError(getText("theOfficialWebsite.required"));
				return JSON;
			}
			//修改航空公司信息实体
			boolean isSuc = flightCompanyService.merge(id, name,twoCode,threeCode, phone, theOfficialWebsite, customerCounter, teamCounter, firstClassCounter, emergencyCounter,orderValue,
					getSinglePictureUrlFromJQueryUpLoader(fileNames, directory, JQueryUploadConstants.PICTURE_TYPE_DEFAULT),longitude,latitude,selfLongitude,selfLatitude,positionName,floorNumber,  versionFlag);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改航空公司信息";
				String logName = SystemOparetionLogUtil.getLogName();
				String logTime = SystemOparetionLogUtil.getLogTime();
				String logIp = SystemOparetionLogUtil.getLogIp();
				
				logService.persist(logName, logContent, logTime, logIp, versionFlag);
				data = AjaxData.responseSuccess(getText("editSuccess"));
			} else {
				data = AjaxData.responseError(getText("editFailed"));
			}
			return JSON;
		}
	}
	
	/**
	 * 管理航空公司信息实体
	 * @Title: FlightCompanyAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(flightCompanyService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageFlightCompany";
	}
	
	/**
	 * 查看回收站
	 * @Title: FlightCompanyAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(flightCompanyService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleFlightCompany";
	}
	
	/**
	 * 逻辑删除航空公司信息对象
	 * @Title: FlightCompanyAction
	 * @Description: 把航空公司信息对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = flightCompanyService.logicDeleteEntity(FlightCompany.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除航空公司信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除航空公司信息对象
	 * @Title: FlightCompanyAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = flightCompanyService.remove(id);
		if(isSuc) {
			String logContent = "物理删除航空公司信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个航空公司信息对象
	 * @Title: FlightCompanyAction
	 * @Description: 从回收站还原航空公司信息对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = flightCompanyService.restoreEntity(FlightCompany.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原航空公司信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核航空公司信息对象
	 * @Title: FlightCompanyAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = flightCompanyService.auditEntity(FlightCompany.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核航空公司信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: FlightCompanyAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = flightCompanyService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作航空公司信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	//批量彻底删除航空公司信息
	public String batchRealDelete() throws ServiceException {
		boolean isSuc = flightCompanyService.batchRealDelete(idsValue, versionFlag);
		if(isSuc) {
			String logContent = "批量删除航空公司信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public FlightCompany getFlightCompany() {
		return flightCompany;
	}
	public void setFlightCompany(FlightCompany flightCompany) {
		this.flightCompany = flightCompany;
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
	public String getTwoCode() {
		return twoCode;
	}

	public void setTwoCode(String twoCode) {
		this.twoCode = twoCode;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTheOfficialWebsite() {
		return theOfficialWebsite;
	}
	public void setTheOfficialWebsite(String theOfficialWebsite) {
		this.theOfficialWebsite = theOfficialWebsite;
	}
	public String getCustomerCounter() {
		return customerCounter;
	}
	public void setCustomerCounter(String customerCounter) {
		this.customerCounter = customerCounter;
	}
	public String getTeamCounter() {
		return teamCounter;
	}
	public void setTeamCounter(String teamCounter) {
		this.teamCounter = teamCounter;
	}
	public String getFirstClassCounter() {
		return firstClassCounter;
	}
	public void setFirstClassCounter(String firstClassCounter) {
		this.firstClassCounter = firstClassCounter;
	}
	public String getEmergencyCounter() {
		return emergencyCounter;
	}
	public void setEmergencyCounter(String emergencyCounter) {
		this.emergencyCounter = emergencyCounter;
	}

	public Integer getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(Integer orderValue) {
		this.orderValue = orderValue;
	}


	public String getThreeCode() {
		return threeCode;
	}

	public void setThreeCode(String threeCode) {
		this.threeCode = threeCode;
	}

	public String[] getPositionName() {
		return positionName;
	}

	public void setPositionName(String[] positionName) {
		this.positionName = positionName;
	}

	public String[] getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(String[] floorNumber) {
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

	public String[] getSelfLongitude() {
		return selfLongitude;
	}

	public void setSelfLongitude(String[] selfLongitude) {
		this.selfLongitude = selfLongitude;
	}

	public String[] getSelfLatitude() {
		return selfLatitude;
	}

	public void setSelfLatitude(String[] selfLatitude) {
		this.selfLatitude = selfLatitude;
	}
}
