package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.SpecialPersonWithQuickMenu;
import com.ticket.service.ISpecialPersonWithQuickMenuService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 服务菜单控制器
 * @ClassName: SpecialPersonWithQuickMenuAction   
 * @Description:  提供服务菜单的相关操作方法. 
 * @author HiSay  
 * @date 2015-12-05 09:41:10
 *
 */
public class SpecialPersonWithQuickMenuAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//服务菜单的业务层
	@Resource private ISpecialPersonWithQuickMenuService specialPersonWithQuickMenuService = null;
	@Resource private ISystemOperationLogService logService = null;
	//服务菜单实体
	private SpecialPersonWithQuickMenu specialPersonWithQuickMenu = null;
	//主键
	private String id = null;
    //服务人员类型
	private String personType = null;
    //快捷菜单id
	private String quickMenu_id = null;
	
	/**
	 * 添加服务菜单
	 * @Title: SpecialPersonWithQuickMenuAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addSpecialPersonWithQuickMenu";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(personType)) {
				data = AjaxData.responseError(getText("personType.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(quickMenu_id)) {
				data = AjaxData.responseError(getText("quickMenu_id.required"));
				return JSON;
			}
			//保存服务菜单实体
			boolean isSuc = specialPersonWithQuickMenuService.persist(personType, quickMenu_id, versionFlag);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增服务菜单";
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
	 * 修改服务菜单
	 * @Title: SpecialPersonWithQuickMenuAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setSpecialPersonWithQuickMenu(specialPersonWithQuickMenuService.queryById(SpecialPersonWithQuickMenu.class.getSimpleName(), id));
			return "editSpecialPersonWithQuickMenu";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(personType)) {
				data = AjaxData.responseError(getText("personType.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(quickMenu_id)) {
				data = AjaxData.responseError(getText("quickMenu_id.required"));
				return JSON;
			}
			//修改服务菜单实体
			boolean isSuc = specialPersonWithQuickMenuService.merge(id, personType, quickMenu_id,  versionFlag);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改服务菜单";
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
	 * 管理服务菜单实体
	 * @Title: SpecialPersonWithQuickMenuAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(specialPersonWithQuickMenuService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageSpecialPersonWithQuickMenu";
	}
	
	/**
	 * 查看回收站
	 * @Title: SpecialPersonWithQuickMenuAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(specialPersonWithQuickMenuService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleSpecialPersonWithQuickMenu";
	}
	
	/**
	 * 逻辑删除服务菜单对象
	 * @Title: SpecialPersonWithQuickMenuAction
	 * @Description: 把服务菜单对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = specialPersonWithQuickMenuService.logicDeleteEntity(SpecialPersonWithQuickMenu.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除服务菜单";
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
	 * 物理删除服务菜单对象
	 * @Title: SpecialPersonWithQuickMenuAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = specialPersonWithQuickMenuService.remove(id);
		if(isSuc) {
			String logContent = "物理删除服务菜单";
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
	 * 还原一个服务菜单对象
	 * @Title: SpecialPersonWithQuickMenuAction
	 * @Description: 从回收站还原服务菜单对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = specialPersonWithQuickMenuService.restoreEntity(SpecialPersonWithQuickMenu.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原服务菜单";
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
	 * 审核服务菜单对象
	 * @Title: SpecialPersonWithQuickMenuAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = specialPersonWithQuickMenuService.auditEntity(SpecialPersonWithQuickMenu.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核服务菜单";
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
	 * @Title: SpecialPersonWithQuickMenuAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = specialPersonWithQuickMenuService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作服务菜单";
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
	 * 批量删除服务菜单
	 * @return
	 * @throws ServiceException
	 */
	public String batchRealDelete() throws ServiceException {
		boolean isSuc = specialPersonWithQuickMenuService.batchRealDelete(idsValue, versionFlag);
		if(isSuc) {
			String logContent = "批量删除服务菜单";
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
	
	public SpecialPersonWithQuickMenu getSpecialPersonWithQuickMenu() {
		return specialPersonWithQuickMenu;
	}
	public void setSpecialPersonWithQuickMenu(SpecialPersonWithQuickMenu specialPersonWithQuickMenu) {
		this.specialPersonWithQuickMenu = specialPersonWithQuickMenu;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPersonType() {
		return personType;
	}
	public void setPersonType(String personType) {
		this.personType = personType;
	}
	public String getQuickMenu_id() {
		return quickMenu_id;
	}
	public void setQuickMenu_id(String quickMenu_id) {
		this.quickMenu_id = quickMenu_id;
	}
}
