package com.ticket.action;

import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjHall;
import com.ticket.pojo.BjdjHallGate;
import com.ticket.pojo.InfoPosition;
import com.ticket.service.IBjdjHallService;
import com.ticket.service.IInfoPositionService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 服务厅等级关联关系控制器
 * @ClassName: BjdjHallAction   
 * @Description:  提供服务厅等级关联关系的相关操作方法. 
 * @author HiSay  
 * @date 2015-10-23 15:24:57
 */
public class BjdjHallGateAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//服务厅等级关联关系的业务层
	@Resource 
	private IBjdjHallService bjdjHallService;
	@Resource 
	private ISystemOperationLogService logService;
	@Resource
	private IInfoPositionService infoPositionService;
	//服务厅等级关联关系实体
	private BjdjHallGate bjdjHallGate;
	//主键
	private String id;
    //厅号
	private String hall;
    //登机口
	private String gate;
    //距离
	private Integer meter;
    //描述
	private String description;
	
	/**
	 * 添加服务大厅关联的一个登机口
	 * @return
	 * @throws ServiceException
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			List<BjdjHall> halls = bjdjHallService.queryAll(BjdjHall.class);
			List<InfoPosition> gates = infoPositionService.queryByNameAndAlias("-登机口", "");
			ActionContext.getContext().put("halls", halls);
			ActionContext.getContext().put("gates", gates);
			return "addBjdjHallGate";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(hall)) {
				data = getText("hall.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(gate)) {
				data = getText("gate.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(meter)) {
				data = getText("meter.required");
				return TEXT;
			}
			//保存服务厅等级关联关系实体
			try {
				BjdjHall hallObj = bjdjHallService.get(BjdjHall.class, hall);
				InfoPosition infoPositionObj = bjdjHallService.get(InfoPosition.class, gate);
				bjdjHallService.addGate(hallObj, infoPositionObj, meter, description);
				String logContent = "新增服务厅登机口关联：" + hallObj.getNumber() + "--" + infoPositionObj.getName();
				logService.persist(logContent);
				data = AjaxData.responseSuccess(getText("addSuccess"));
			} catch (Exception e) {
				e.printStackTrace();
				data = AjaxData.responseError(e.getMessage());
			}
			return JSON;
		}
	}
	
	/**
	 * 修改服务厅等级关联关系
	 * @Title: BjdjHallAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			List<BjdjHall> halls = bjdjHallService.queryAll(BjdjHall.class);
			List<InfoPosition> gates = infoPositionService.queryByNameAndAlias("-登机口", "");
			ActionContext.getContext().put("halls", halls);
			ActionContext.getContext().put("gates", gates);
			this.setBjdjHallGate(bjdjHallService.get(BjdjHallGate.class, id));
			return "editBjdjHallGate";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = getText("id.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(hall)) {
				data = getText("hall.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(gate)) {
				data = getText("gate.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(meter)) {
				data = getText("meter.required");
				return TEXT;
			}
			//修改服务厅等级关联关系实体
			try {
				BjdjHall hallObj = bjdjHallService.get(BjdjHall.class, hall);
				InfoPosition infoPositionObj = bjdjHallService.get(InfoPosition.class, gate);
				bjdjHallService.editGate(id, hallObj, infoPositionObj, meter, description);
				String logContent = "修改服务厅登机口关联：" + hallObj.getNumber() + "--" + infoPositionObj.getName();
				logService.persist(logContent);
				data = AjaxData.responseSuccess(getText("editSuccess"));
			} catch (Exception e) {
				e.printStackTrace();
				data = AjaxData.responseError(e.getMessage());
			}
			return JSON;
		}
	}
	
	/**
	 * 管理服务厅等级关联关系实体
	 * @Title: BjdjHallAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(bjdjHallService.paginationQuery("select t from " + BjdjHallGate.class.getName() + " t", BjdjHallGate.class));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageBjdjHallGate";
	}
	
	/**
	 * 查看回收站
	 * @Title: BjdjHallAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(bjdjHallService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleBjdjHall";
	}
	
	/**
	 * 逻辑删除服务厅等级关联关系对象
	 * @Title: BjdjHallAction
	 * @Description: 把服务厅等级关联关系对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		BjdjHall hall = bjdjHallService.queryById(BjdjHall.class.getName(), id);
		String number = hall.getNumber();
		boolean isSuc = bjdjHallService.logicDeleteEntity(BjdjHall.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除服务厅：" + number;
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
	 * 物理删除服务厅等级关联关系对象
	 * @Title: BjdjHallAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		BjdjHall hall = bjdjHallService.queryById(BjdjHall.class.getName(), id);
		String number = hall.getNumber();
		boolean isSuc = bjdjHallService.remove(id);
		if(isSuc) {
			String logContent = "物理删除服务厅：" + number;
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
	 * 还原一个服务厅等级关联关系对象
	 * @Title: BjdjHallAction
	 * @Description: 从回收站还原服务厅等级关联关系对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		BjdjHall hall = bjdjHallService.queryById(BjdjHall.class.getName(), id);
		String number = hall.getNumber();
		boolean isSuc = bjdjHallService.restoreEntity(BjdjHall.class.getSimpleName(), id);
		if(isSuc) {
			
			String logContent = "还原服务厅：" + number;
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
	 * 审核服务厅等级关联关系对象
	 * @Title: BjdjHallAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		BjdjHall hall = bjdjHallService.queryById(BjdjHall.class.getName(), id);
		String number = hall.getNumber();
		boolean isSuc = bjdjHallService.auditEntity(BjdjHall.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核服务厅：" + number;
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
	 * @Title: BjdjHallAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = bjdjHallService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作服务厅";
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

	public IBjdjHallService getBjdjHallService() {
		return bjdjHallService;
	}

	public void setBjdjHallService(IBjdjHallService bjdjHallService) {
		this.bjdjHallService = bjdjHallService;
	}

	public ISystemOperationLogService getLogService() {
		return logService;
	}

	public void setLogService(ISystemOperationLogService logService) {
		this.logService = logService;
	}

	public BjdjHallGate getBjdjHallGate() {
		return bjdjHallGate;
	}

	public void setBjdjHallGate(BjdjHallGate bjdjHallGate) {
		this.bjdjHallGate = bjdjHallGate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHall() {
		return hall;
	}

	public void setHall(String hall) {
		this.hall = hall;
	}

	public String getGate() {
		return gate;
	}

	public void setGate(String gate) {
		this.gate = gate;
	}

	public Integer getMeter() {
		return meter;
	}

	public void setMeter(Integer meter) {
		this.meter = meter;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
