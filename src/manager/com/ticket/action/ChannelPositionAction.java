package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.ChannelPosition;
import com.ticket.service.IChannelPositionService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 渠道联系人岗位控制器
 * @ClassName: ChannelPositionAction   
 * @Description:  提供渠道联系人岗位的相关操作方法. 
 * @author HiSay  
 * @date 2016-01-11 11:35:13
 *
 */
public class ChannelPositionAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//渠道联系人岗位的业务层
	@Resource private IChannelPositionService channelPositionService = null;
	@Resource private ISystemOperationLogService logService = null;
	//渠道联系人岗位实体
	private ChannelPosition channelPosition = null;
	//主键
	private String id = null;
    //岗位名称
	private String name = null;
    //岗位职责
	private String duty = null;
    //岗位描述
	private String remark = null;
	
	/**
	 * 添加渠道联系人岗位
	 * @Title: ChannelPositionAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addChannelPosition";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError("名称不能为空");
				return JSON;
			}
			//保存渠道联系人岗位实体
			boolean isSuc = channelPositionService.persist(name, duty, remark, versionFlag);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增渠道客户信息";
				String logName = SystemOparetionLogUtil.getLogName();
				String logTime = SystemOparetionLogUtil.getLogTime();
				String logIp = SystemOparetionLogUtil.getLogIp();
				
				logService.persist(logName, logContent, logTime, logIp, versionFlag);
				data = AjaxData.responseSuccess("添加成功");
			} else {
				data = AjaxData.responseError("添加失败");
			}
			return JSON;
		}
	}
	
	/**
	 * 修改渠道联系人岗位
	 * @Title: ChannelPositionAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setChannelPosition(channelPositionService.queryById(ChannelPosition.class.getSimpleName(), id));
			return "editChannelPosition";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError("id不能为空");
				return JSON;
			}
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError("名称不能为空");
				return JSON;
			}
			//修改渠道联系人岗位实体
			boolean isSuc = channelPositionService.merge(id, name, duty, remark,  versionFlag);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改渠道客户信息";
				String logName = SystemOparetionLogUtil.getLogName();
				String logTime = SystemOparetionLogUtil.getLogTime();
				String logIp = SystemOparetionLogUtil.getLogIp();
				
				logService.persist(logName, logContent, logTime, logIp, versionFlag);
				data = AjaxData.responseSuccess("添加成功");
			} else {
				data = AjaxData.responseError("添加失败");
			}
			return JSON;
		}
	}
	
	/**
	 * 管理渠道联系人岗位实体
	 * @Title: ChannelPositionAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(channelPositionService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageChannelPosition";
	}
	
	/**
	 * 查看回收站
	 * @Title: ChannelPositionAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(channelPositionService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleChannelPosition";
	}
	
	/**
	 * 逻辑删除渠道联系人岗位对象
	 * @Title: ChannelPositionAction
	 * @Description: 把渠道联系人岗位对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = channelPositionService.logicDeleteEntity(ChannelPosition.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除渠道客户信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess("删除成功");
		} else {
			data = AjaxData.responseError("删除失败");
		}
		return JSON;
	}
	
	/**
	 * 物理删除渠道联系人岗位对象
	 * @Title: ChannelPositionAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = channelPositionService.remove(id);
		if(isSuc) {
			String logContent = "物理删除渠道客户信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess("删除成功");
		} else {
			data = AjaxData.responseError("删除失败");
		}
		return JSON;
	}
	
	/**
	 * 还原一个渠道联系人岗位对象
	 * @Title: ChannelPositionAction
	 * @Description: 从回收站还原渠道联系人岗位对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = channelPositionService.restoreEntity(ChannelPosition.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原渠道客户信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess("还原成功");
		} else {
			data = AjaxData.responseError("还原失败");
		}
		return JSON;
	}
	
	/**
	 * 审核渠道联系人岗位对象
	 * @Title: ChannelPositionAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = channelPositionService.auditEntity(ChannelPosition.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核渠道客户信息";
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
	 * @Title: ChannelPositionAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = channelPositionService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作渠道客户信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess("操作成功");
		} else {
			data = AjaxData.responseError("操作失败");
		}
		return JSON;
	}
	
	public ChannelPosition getChannelPosition() {
		return channelPosition;
	}
	public void setChannelPosition(ChannelPosition channelPosition) {
		this.channelPosition = channelPosition;
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
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
