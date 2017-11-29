package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.GoodsLostPostions;
import com.ticket.service.IGoodsLostPostionsService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 物品遗失位置控制器
 * @ClassName: GoodsLostPostionsAction   
 * @Description:  提供物品遗失位置的相关操作方法. 
 * @author HiSay  
 * @date 2015-11-23 16:44:38
 *
 */
public class GoodsLostPostionsAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//物品遗失位置的业务层
	@Resource private IGoodsLostPostionsService goodsLostPostionsService = null;
	@Resource private ISystemOperationLogService logService = null;
	//物品遗失位置实体
	private GoodsLostPostions goodsLostPostions = null;
	//主键
	private String id = null;
    //位置名称
	private String name = null;
    //位置描述
	private String description = null;
    //经度
	private Double longitude = null;
    //纬度
	private Double latitude = null;
	
	/**
	 * 添加物品遗失位置
	 * @Title: GoodsLostPostionsAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addGoodsLostPostions";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError(getText("name.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(description)) {
				data = AjaxData.responseError(getText("description.required"));
				return JSON;
			}
			//保存物品遗失位置实体
			boolean isSuc = goodsLostPostionsService.persist(name, description, longitude, latitude, versionFlag);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增遗失物品位置信息";
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
	 * 修改物品遗失位置
	 * @Title: GoodsLostPostionsAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setGoodsLostPostions(goodsLostPostionsService.queryById(GoodsLostPostions.class.getSimpleName(), id));
			return "editGoodsLostPostions";
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
			if(GeneralUtil.isNull(description)) {
				data = AjaxData.responseError(getText("description.required"));
				return JSON;
			}
			//修改物品遗失位置实体
			boolean isSuc = goodsLostPostionsService.merge(id, name, description, longitude, latitude,  versionFlag);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改遗失物品位置信息";
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
	 * 管理物品遗失位置实体
	 * @Title: GoodsLostPostionsAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(goodsLostPostionsService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageGoodsLostPostions";
	}
	
	/**
	 * 查看回收站
	 * @Title: GoodsLostPostionsAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(goodsLostPostionsService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleGoodsLostPostions";
	}
	
	/**
	 * 逻辑删除物品遗失位置对象
	 * @Title: GoodsLostPostionsAction
	 * @Description: 把物品遗失位置对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = goodsLostPostionsService.logicDeleteEntity(GoodsLostPostions.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除遗失物品位置信息";
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
	 * 物理删除物品遗失位置对象
	 * @Title: GoodsLostPostionsAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = goodsLostPostionsService.remove(id);
		if(isSuc) {
			String logContent = "物理删除遗失物品位置信息";
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
	 * 还原一个物品遗失位置对象
	 * @Title: GoodsLostPostionsAction
	 * @Description: 从回收站还原物品遗失位置对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = goodsLostPostionsService.restoreEntity(GoodsLostPostions.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原遗失物品位置信息";
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
	 * 审核物品遗失位置对象
	 * @Title: GoodsLostPostionsAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = goodsLostPostionsService.auditEntity(GoodsLostPostions.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核遗失物品位置信息";
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
	 * @Title: GoodsLostPostionsAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = goodsLostPostionsService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作遗失物品位置信息";
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
	 * 批量彻底删除位置信息
	 * @return
	 * @throws ServiceException
	 */
	public String batchRealDelete() throws ServiceException {
		boolean isSuc = goodsLostPostionsService.batchRealDelete(idsValue,versionFlag);
		if(isSuc) {
			String logContent = "批量删除遗失物品位置信息";
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
	
	public GoodsLostPostions getGoodsLostPostions() {
		return goodsLostPostions;
	}
	public void setGoodsLostPostions(GoodsLostPostions goodsLostPostions) {
		this.goodsLostPostions = goodsLostPostions;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
