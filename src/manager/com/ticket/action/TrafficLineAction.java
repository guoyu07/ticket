package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.TrafficLine;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.service.ITrafficLineService;
import com.ticket.util.DateUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 交通路线控制器
 * @ClassName: TrafficLineAction   
 * @Description:  提供交通路线的相关操作方法. 
 * @author HiSay  
 * @date 2015-11-19 09:55:18
 *
 */
public class TrafficLineAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//交通路线的业务层
	@Resource private ITrafficLineService trafficLineService = null;
	
	@Resource private ISystemOperationLogService logService = null;
	//交通路线实体
	private TrafficLine trafficLine = null;
	//主键
	private String id = null;
    //路线类别id
	private String trafficType_id = null;
	//路线名称
	private String name = null;
    //起始站
	private String startStation = null;
    //终到站
	private String endStation = null;
    //发班频率
	private String departureRate = null;
    //车辆数
	private Integer carCount = null;
    //车辆型号
	private String carModel = null;
    //座位数
	private Integer seatCount = null;
    //票价
	private Double price = null;
	//备注
	private String remark = null;
	//排序值
	private Integer orderValue = 0;
	/**
	 * 添加交通路线
	 * @Title: TrafficLineAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addTrafficLine";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(trafficType_id)) {
				data = AjaxData.responseError(getText("trafficType_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError(getText("name.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(startStation)) {
				data = AjaxData.responseError(getText("startStation.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(endStation)) {
				data = AjaxData.responseError(getText("endStation.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(startTime)) {
				data = AjaxData.responseError(getText("startTime.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(endTime)) {
				data = AjaxData.responseError(getText("endTime.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(price)) {
				data = AjaxData.responseError(getText("price.required"));
				return JSON;
			}
			//保存交通路线实体
			boolean isSuc = trafficLineService.persist(trafficType_id, name, startStation, endStation, DateUtil.formatDateToHHmm(startTime), DateUtil.formatDateToHHmm(endTime), departureRate, carCount, carModel, seatCount, price,remark,orderValue, versionFlag);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增交通路线";
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
	 * 修改交通路线
	 * @Title: TrafficLineAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setTrafficLine(trafficLineService.queryById(TrafficLine.class.getSimpleName(), id));
			return "editTrafficLine";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(trafficType_id)) {
				data = AjaxData.responseError(getText("trafficType_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError(getText("name.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(startStation)) {
				data = AjaxData.responseError(getText("startStation.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(endStation)) {
				data = AjaxData.responseError(getText("endStation.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(startTime)) {
				data = AjaxData.responseError(getText("startTime.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(endTime)) {
				data = AjaxData.responseError(getText("endTime.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(price)) {
				data = AjaxData.responseError(getText("price.required"));
				return JSON;
			}
			//修改交通路线实体
			boolean isSuc = trafficLineService.merge(id, trafficType_id,name, startStation, endStation, DateUtil.formatDateToHHmm(startTime), DateUtil.formatDateToHHmm(endTime), departureRate, carCount, carModel, seatCount, price,remark,orderValue, versionFlag);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改交通路线";
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
	 * 管理交通路线实体
	 * @Title: TrafficLineAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		if(GeneralUtil.isNotNull(trafficType_id)){
			this.setPageModule(trafficLineService.queryEntityByTypeId(trafficType_id, ContextConstants.ADMIN_COMMON_PAGE_SIZE,versionFlag));
			this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
			return "manageTrafficLine";
		}
		this.setPageModule(trafficLineService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageTrafficLine";
	}
	
	/**
	 * 查看回收站
	 * @Title: TrafficLineAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(trafficLineService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleTrafficLine";
	}
	
	/**
	 * 逻辑删除交通路线对象
	 * @Title: TrafficLineAction
	 * @Description: 把交通路线对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = trafficLineService.logicDeleteEntity(TrafficLine.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除交通路线";
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
	 * 物理删除交通路线对象
	 * @Title: TrafficLineAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = trafficLineService.remove(id);
		if(isSuc) {
			String logContent = "物理删除交通路线";
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
	 * 还原一个交通路线对象
	 * @Title: TrafficLineAction
	 * @Description: 从回收站还原交通路线对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = trafficLineService.restoreEntity(TrafficLine.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原交通路线";
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
	 * 审核交通路线对象
	 * @Title: TrafficLineAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = trafficLineService.auditEntity(TrafficLine.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核交通路线";
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
	 * @Title: TrafficLineAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = trafficLineService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作交通路线";
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
	/**
	 * 批量彻底删除交通线路实体
	 * @return
	 * @throws ServiceException
	 */
	public String batchRealDelete() throws ServiceException {
		boolean isSuc = trafficLineService.batchRealDelete(idsValue, versionFlag);
		if(isSuc) {
			String logContent = "批量删除交通路线";
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
	
	public TrafficLine getTrafficLine() {
		return trafficLine;
	}
	public void setTrafficLine(TrafficLine trafficLine) {
		this.trafficLine = trafficLine;
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

	public String getTrafficType_id() {
		return trafficType_id;
	}
	public void setTrafficType_id(String trafficType_id) {
		this.trafficType_id = trafficType_id;
	}
	public String getStartStation() {
		return startStation;
	}
	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}
	public String getEndStation() {
		return endStation;
	}
	public void setEndStation(String endStation) {
		this.endStation = endStation;
	}
	public String getDepartureRate() {
		return departureRate;
	}
	public void setDepartureRate(String departureRate) {
		this.departureRate = departureRate;
	}
	public Integer getCarCount() {
		return carCount;
	}
	public void setCarCount(Integer carCount) {
		this.carCount = carCount;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	public Integer getSeatCount() {
		return seatCount;
	}
	public void setSeatCount(Integer seatCount) {
		this.seatCount = seatCount;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(Integer orderValue) {
		this.orderValue = orderValue;
	}
}
