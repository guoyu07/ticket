package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.constants.JQueryUploadConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.QuickMenu;
import com.ticket.service.IQuickMenuService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 快捷菜单控制器
 * @ClassName: QuickMenuAction   
 * @Description:  提供快捷菜单的相关操作方法. 
 * @author HiSay  
 * @date 2015-10-31 13:01:07
 *
 */
public class QuickMenuAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//快捷菜单的业务层
	@Resource private IQuickMenuService quickMenuService = null;
	
	@Resource private ISystemOperationLogService logService = null;
	//快捷菜单实体
	private QuickMenu quickMenu = null;
	//主键
	private String id = null;
    //菜单名称
	private String name = null;
    //菜单链接
	private String url = null;
	//菜单分类
	private String quickMenuType_id = null;
	//排序值
	private Integer orderValue = 0;
	//是否默认显示
	private String isDefultShow = "0";
	//默认现实的位置（出发 到达 中转）
	private String[] defaultShowPosition = null;
	//临时存放快捷菜单的默认位置
	private String temp = null;
	/**
	 * 添加快捷菜单
	 * @Title: QuickMenuAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addQuickMenu";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError(getText("name.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(url)) {
				data = AjaxData.responseError(getText("url.required"));
				return JSON;
			}
			if(GeneralUtil.isNotNull(defaultShowPosition)){
				isDefultShow = "1";
				StringBuffer sb = new StringBuffer();
				for(String str : defaultShowPosition){
					sb.append(str);
					sb.append(",");
				}
				temp = sb.toString();
			}
			//保存快捷菜单实体
			boolean isSuc = quickMenuService.persist(name, url,isDefultShow,temp, versionFlag, 
					getSinglePictureUrlFromJQueryUpLoader(fileNames, directory, JQueryUploadConstants.PICTURE_TYPE_DEFAULT), quickMenuType_id, orderValue);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增快捷菜单";
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
	 * 修改快捷菜单
	 * @Title: QuickMenuAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setQuickMenu(quickMenuService.queryById(QuickMenu.class.getSimpleName(), id));
			return "editQuickMenu";
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
			if(GeneralUtil.isNull(url)) {
				data = AjaxData.responseError(getText("url.required"));
				return JSON;
			}
			if(GeneralUtil.isNotNull(defaultShowPosition)){
				isDefultShow = "1";
				StringBuffer sb = new StringBuffer();
				for(String str : defaultShowPosition){
					sb.append(str);
					sb.append(",");
				}
				temp = sb.toString();
			}else{
				isDefultShow="0";
			}
			//修改快捷菜单实体
			boolean isSuc = quickMenuService.merge(id, name, url,isDefultShow,temp,versionFlag, 
					getSinglePictureUrlFromJQueryUpLoader(fileNames, directory, JQueryUploadConstants.PICTURE_TYPE_DEFAULT), quickMenuType_id, orderValue);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改快捷菜单";
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
	 * 管理快捷菜单实体
	 * @Title: QuickMenuAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(quickMenuService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageQuickMenu";
	}
	
	/**
	 * 查看回收站
	 * @Title: QuickMenuAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(quickMenuService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleQuickMenu";
	}
	
	/**
	 * 逻辑删除快捷菜单对象
	 * @Title: QuickMenuAction
	 * @Description: 把快捷菜单对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = quickMenuService.logicDeleteEntity(QuickMenu.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除快捷菜单";
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
	 * 物理删除快捷菜单对象
	 * @Title: QuickMenuAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = quickMenuService.remove(id);
		if(isSuc) {
			String logContent = "物理删除快捷菜单";
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
	 * 还原一个快捷菜单对象
	 * @Title: QuickMenuAction
	 * @Description: 从回收站还原快捷菜单对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = quickMenuService.restoreEntity(QuickMenu.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原快捷菜单";
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
	 * 审核快捷菜单对象
	 * @Title: QuickMenuAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = quickMenuService.auditEntity(QuickMenu.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核快捷菜单";
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
	 * @Title: QuickMenuAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = quickMenuService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作快捷菜单";
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
	 * 批量彻底删除快捷菜单实体
	 * @return
	 * @throws ServiceException
	 */
	public String batchRealDelete() throws ServiceException {
		boolean isSuc = quickMenuService.batchRealDelete(idsValue,versionFlag);
		if(isSuc) {
			String logContent = "批量删除快捷菜单";
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
	
	public QuickMenu getQuickMenu() {
		return quickMenu;
	}
	public void setQuickMenu(QuickMenu quickMenu) {
		this.quickMenu = quickMenu;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getQuickMenuType_id() {
		return quickMenuType_id;
	}

	public void setQuickMenuType_id(String quickMenuTypeId) {
		quickMenuType_id = quickMenuTypeId;
	}

	public Integer getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(Integer orderValue) {
		this.orderValue = orderValue;
	}

	public String getIsDefultShow() {
		return isDefultShow;
	}

	public void setIsDefultShow(String isDefultShow) {
		this.isDefultShow = isDefultShow;
	}

	public String[] getDefaultShowPosition() {
		return defaultShowPosition;
	}

	public void setDefaultShowPosition(String[] defaultShowPosition) {
		this.defaultShowPosition = defaultShowPosition;
	}

}
