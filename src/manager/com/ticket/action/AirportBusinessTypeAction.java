package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.AirportBusinessType;
import com.ticket.service.IAirportBusinessTypeService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 机场商业类别控制器
 * @ClassName: AirportBusinessTypeAction   
 * @Description:  提供机场商业类别的相关操作方法. 
 * @author HiSay  
 * @date 2015-11-16 13:35:58
 *
 */
public class AirportBusinessTypeAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//机场商业类别的业务层
	@Resource private IAirportBusinessTypeService airportBusinessTypeService = null;
	//机场商业类别实体
	private AirportBusinessType airportBusinessType = null;
	@Resource private ISystemOperationLogService logService = null;
	//主键
	private String id = null;
    //类别名称
	private String name = null;
    //上级类别id
	private String parent_id = null;
    //类别介绍
	private String introduce = null;
	//类别树
	private String classTree = null;
	//排序值
	private Integer orderValue = 0;
	/**
	 * 添加机场商业类别
	 * @Title: AirportBusinessTypeAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setClassTree(airportBusinessTypeService.queryAirportBusinessTypeSelectOptionHtml(parent_id, versionFlag));
			return "addAirportBusinessType";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError(getText("name.required"));
				return JSON;
			}
			//保存机场商业类别实体
			boolean isSuc = airportBusinessTypeService.persist(name, parent_id, introduce,orderValue, versionFlag);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增机场商业类别";
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
	 * 修改机场商业类别
	 * @Title: AirportBusinessTypeAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setAirportBusinessType(airportBusinessTypeService.queryById(AirportBusinessType.class.getSimpleName(), id));
			if(this.getAirportBusinessType().getParent_id() != null) {
				this.setClassTree(airportBusinessTypeService.queryAirportBusinessTypeSelectOptionHtml(this.getAirportBusinessType().getParent_id(), versionFlag));
			} else {
				this.setClassTree(airportBusinessTypeService.queryAirportBusinessTypeSelectOptionHtml(null, versionFlag));
			}
			return "editAirportBusinessType";
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
			//修改机场商业类别实体
			boolean isSuc = airportBusinessTypeService.merge(id, name, parent_id, introduce,orderValue, versionFlag);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改机场商业类别";
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
	 * 管理机场商业类别实体
	 * @Title: AirportBusinessTypeAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(airportBusinessTypeService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageAirportBusinessType";
	}
	
	/**
	 * 查看回收站
	 * @Title: AirportBusinessTypeAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(airportBusinessTypeService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleAirportBusinessType";
	}
	
	/**
	 * 逻辑删除机场商业类别对象
	 * @Title: AirportBusinessTypeAction
	 * @Description: 把机场商业类别对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = airportBusinessTypeService.logicDeleteEntity(AirportBusinessType.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除机场商业类别";
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
	 * 物理删除机场商业类别对象
	 * @Title: AirportBusinessTypeAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = airportBusinessTypeService.remove(id);
		if(isSuc) {
			String logContent = "物理删除机场商业类别";
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
	 * 还原一个机场商业类别对象
	 * @Title: AirportBusinessTypeAction
	 * @Description: 从回收站还原机场商业类别对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = airportBusinessTypeService.restoreEntity(AirportBusinessType.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "物理删除机场商业类别";
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
	 * 审核机场商业类别对象
	 * @Title: AirportBusinessTypeAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = airportBusinessTypeService.auditEntity(AirportBusinessType.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核机场商业类别";
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
	 * @Title: AirportBusinessTypeAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = airportBusinessTypeService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作机场商业类别";
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
	 * 批量彻底删除商家类别信息
	 * @return
	 * @throws ServiceException
	 */
	public String batchRealDelete() throws ServiceException {
		boolean isSuc = airportBusinessTypeService.batchRealDelete( idsValue,versionFlag);
		if(isSuc) {
			String logContent = "批量彻底删除机场商业类别";
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
	
	public AirportBusinessType getAirportBusinessType() {
		return airportBusinessType;
	}
	public void setAirportBusinessType(AirportBusinessType airportBusinessType) {
		this.airportBusinessType = airportBusinessType;
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
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getClassTree() {
		return classTree;
	}

	public void setClassTree(String classTree) {
		this.classTree = classTree;
	}

	public Integer getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(Integer orderValue) {
		this.orderValue = orderValue;
	}
}
