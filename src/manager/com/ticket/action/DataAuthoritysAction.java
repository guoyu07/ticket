package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.DataAuthoritys;
import com.ticket.pojo.SystemModule;
import com.ticket.service.IDataAuthoritysService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.service.ITreeService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

import net.sf.json.JSONArray;

/**
 * 数据权限控制器
 * @ClassName: DataAuthoritysAction   
 * @Description:  提供数据权限的相关操作方法. 
 * @author HiSay  
 * @date 2016-05-25 15:20:21
 *
 */
public class DataAuthoritysAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//数据权限的业务层
	@Resource private IDataAuthoritysService dataAuthoritysService = null;
	@Resource private ITreeService<SystemModule, String> treeService = null;
	@Resource private ISystemOperationLogService logService = null;
	//数据权限实体
	private DataAuthoritys dataAuthoritys = null;
	//主键
	private String id = null;
    //权限名称
	private String name = null;
    //权限描述
	private String descript = null;
    //权限内容
	private String content = null;
	//系统模块
	private String systemModulId = null;
	//调用的方法
	private String inMethod = null;
	
	/**
	 * 遍历所有菜单
	 * @return
	 */
	public String traverse(){
		try {
			JSONArray json = treeService.traverse(SystemModule.class, "name","id");
			data = json.toString();
			return TEXT;
			
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * 添加数据权限
	 * @Title: DataAuthoritysAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addDataAuthoritys";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError(getText("name.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(descript)) {
				data = AjaxData.responseError(getText("descript.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(content)) {
				data = AjaxData.responseError(getText("content.required"));
				return JSON;
			}
			//保存数据权限实体
			boolean isSuc = dataAuthoritysService.persist(name, descript, content,systemModulId,inMethod, versionFlag);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增数据权限";
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
	 * 修改数据权限
	 * @Title: DataAuthoritysAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setDataAuthoritys(dataAuthoritysService.queryById(DataAuthoritys.class.getSimpleName(), id));
			return "editDataAuthoritys";
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
			if(GeneralUtil.isNull(descript)) {
				data = AjaxData.responseError(getText("descript.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(content)) {
				data = AjaxData.responseError(getText("content.required"));
				return JSON;
			}
			//修改数据权限实体
			boolean isSuc = dataAuthoritysService.merge(id, name, descript, content,systemModulId,inMethod,  versionFlag);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改数据权限";
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
	 * 管理数据权限实体
	 * @Title: DataAuthoritysAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(dataAuthoritysService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageDataAuthoritys";
	}
	
	/**
	 * 查看回收站
	 * @Title: DataAuthoritysAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(dataAuthoritysService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleDataAuthoritys";
	}
	
	/**
	 * 逻辑删除数据权限对象
	 * @Title: DataAuthoritysAction
	 * @Description: 把数据权限对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = dataAuthoritysService.logicDeleteEntity(DataAuthoritys.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除数据权限对象
	 * @Title: DataAuthoritysAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = dataAuthoritysService.remove(id);
		if(isSuc) {
			String logContent = "删除数据权限";
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
	 * 还原一个数据权限对象
	 * @Title: DataAuthoritysAction
	 * @Description: 从回收站还原数据权限对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = dataAuthoritysService.restoreEntity(DataAuthoritys.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核数据权限对象
	 * @Title: DataAuthoritysAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = dataAuthoritysService.auditEntity(DataAuthoritys.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: DataAuthoritysAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = dataAuthoritysService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作数据权限";
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
	
	public DataAuthoritys getDataAuthoritys() {
		return dataAuthoritys;
	}
	public void setDataAuthoritys(DataAuthoritys dataAuthoritys) {
		this.dataAuthoritys = dataAuthoritys;
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
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getSystemModulId() {
		return systemModulId;
	}

	public void setSystemModulId(String systemModulId) {
		this.systemModulId = systemModulId;
	}
	public String getInMethod() {
		return inMethod;
	}
	public void setInMethod(String inMethod) {
		this.inMethod = inMethod;
	}
}
