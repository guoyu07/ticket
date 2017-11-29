package com.ticket.action;

import java.util.List;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.DataAuthoritys;
import com.ticket.pojo.PositionDataAuthority;
import com.ticket.pojo.SystemModule;
import com.ticket.service.IDataAuthoritysService;
import com.ticket.service.IPositionDataAuthorityService;
import com.ticket.service.ISystemModuleService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 岗位权限控制器
 * 
 * @ClassName: PositionDataAuthorityAction
 * @Description: 提供岗位权限的相关操作方法.
 * @author HiSay
 * @date 2016-05-25 15:20:45
 * 
 */
public class PositionDataAuthorityAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	// 岗位权限的业务层
	@Resource
	private IPositionDataAuthorityService positionDataAuthorityService = null;
	@Resource private ISystemOperationLogService logService = null;

	@Resource
	private ISystemModuleService systemModuleService = null;

	@Resource
	private IDataAuthoritysService dataAuthoritysService = null;
	// 岗位权限实体
	private PositionDataAuthority positionDataAuthority = null;
	// 主键
	private String id = null;
	// 岗位id
	private String position_id = null;
	// 数据权限id
	private String dataAuthoritys_id = null;

	private String systemModuleId = null;

	/**
	 * 取消所有授权
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public String selectNone() throws ServiceException {
		positionDataAuthorityService.selectNone(position_id, versionFlag);
		String logContent = "收回全部数据权限";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		return null;
	}

	/**
	 * 授予全部权限
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public String selectAll() throws ServiceException {
		List<SystemModule> moduleList = systemModuleService
				.queryByList(versionFlag);
		List<DataAuthoritys> authoritys = dataAuthoritysService
				.queryAll(DataAuthoritys.class);
		positionDataAuthorityService.selectAll(position_id, moduleList,
				authoritys, versionFlag);
		String logContent = "授予全部数据权限";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		return null;
	}

	/**
	 * 收回数据权限
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public String revoke() throws ServiceException {
		PositionDataAuthority authority = positionDataAuthorityService
				.queryByRIDAndMIDAndPID(position_id,systemModuleId,dataAuthoritys_id,versionFlag);
		positionDataAuthorityService.remove(authority);
		String logContent = "收回数据权限";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		return null;
	}

	/**
	 * 添加岗位权限
	 * 
	 * @Title: PositionDataAuthorityAction
	 * @Description:
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String add() throws ServiceException {
		// 非空验证.
		if (GeneralUtil.isNull(position_id)) {
			data = AjaxData.responseError(getText("position_id.required"));
			return JSON;
		}
		if (GeneralUtil.isNull(dataAuthoritys_id)) {
			data = AjaxData.responseError(getText("dataAuthoritys_id.required"));
			return JSON;
		}
		// 保存岗位权限实体
		boolean isSuc = positionDataAuthorityService.persist(position_id,
				dataAuthoritys_id, systemModuleId, versionFlag);
		positionDataAuthority = positionDataAuthorityService.queryByRIDAndMIDAndPID(position_id, systemModuleId, dataAuthoritys_id, versionFlag);
		positionDataAuthorityService.deleteOthers(positionDataAuthority.getId());
		// 根据保存结果返回页面
		if (isSuc) {
			String logContent = "授予岗位数据权限";
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

	/**
	 * 修改岗位权限
	 * 
	 * @Title: PositionDataAuthorityAction
	 * @Description:
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String edit() throws ServiceException {
		if (GeneralUtil.isNull(operationFlag)) {
			this.setPositionDataAuthority(positionDataAuthorityService
					.queryById(PositionDataAuthority.class.getSimpleName(), id));
			return "editPositionDataAuthority";
		} else {
			// 非空验证.
			if (GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(position_id)) {
				data = AjaxData.responseError(getText("position_id.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(dataAuthoritys_id)) {
				data = AjaxData.responseError(getText("dataAuthoritys_id.required"));
				return JSON;
			}
			// 修改岗位权限实体
			boolean isSuc = positionDataAuthorityService.merge(id, position_id,
					dataAuthoritys_id, systemModuleId, versionFlag);
			// 根据修改结果返回页面
			if (isSuc) {
				data = AjaxData.responseSuccess(getText("editSuccess"));
			} else {
				data = AjaxData.responseError(getText("editFailed"));
			}
			return JSON;
		}
	}

	/**
	 * 管理岗位权限实体
	 * 
	 * @Title: PositionDataAuthorityAction
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0]
				.getMethodName());
		this.setPageModule(positionDataAuthorityService.queryEntityByAdmin(
				versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "managePositionDataAuthority";
	}

	/**
	 * 查看回收站
	 * 
	 * @Title: PositionDataAuthorityAction
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(positionDataAuthorityService.queryRecycleEntity(
				versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recyclePositionDataAuthority";
	}

	/**
	 * 逻辑删除岗位权限对象
	 * 
	 * @Title: PositionDataAuthorityAction
	 * @Description: 把岗位权限对象放入回收站.
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = positionDataAuthorityService.logicDeleteEntity(
				PositionDataAuthority.class.getSimpleName(), id);
		if (isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}

	/**
	 * 物理删除岗位权限对象
	 * 
	 * @Title: PositionDataAuthorityAction
	 * @Description: 把配置对象实际从数据库里面删除.
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = positionDataAuthorityService.remove(id);
		if (isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}

	/**
	 * 还原一个岗位权限对象
	 * 
	 * @Title: PositionDataAuthorityAction
	 * @Description: 从回收站还原岗位权限对象
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = positionDataAuthorityService.restoreEntity(
				PositionDataAuthority.class.getSimpleName(), id);
		if (isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}

	/**
	 * 审核岗位权限对象
	 * 
	 * @Title: PositionDataAuthorityAction
	 * @Description:
	 * @param @return
	 * @param @throws ServiceException 设定文件
	 * @return 返回类型
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = positionDataAuthorityService.auditEntity(
				PositionDataAuthority.class.getSimpleName(), id, statusValue);
		if (isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}

	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * 
	 * @Title: PositionDataAuthorityAction
	 * @Description:
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = positionDataAuthorityService.batchOperationEntity(
				versionFlag, idsValue, batchOperationType, isChecked);
		if (isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}

	public PositionDataAuthority getPositionDataAuthority() {
		return positionDataAuthority;
	}

	public void setPositionDataAuthority(
			PositionDataAuthority positionDataAuthority) {
		this.positionDataAuthority = positionDataAuthority;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPosition_id() {
		return position_id;
	}

	public void setPosition_id(String position_id) {
		this.position_id = position_id;
	}

	public String getDataAuthoritys_id() {
		return dataAuthoritys_id;
	}

	public void setDataAuthoritys_id(String dataAuthoritys_id) {
		this.dataAuthoritys_id = dataAuthoritys_id;
	}

	public String getSystemModuleId() {
		return systemModuleId;
	}

	public void setSystemModuleId(String systemModuleId) {
		this.systemModuleId = systemModuleId;
	}
}
