package com.ticket.action;

import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.DepartmentInfo;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.service.IDepartmentInfoService;
import com.ticket.service.IEmployeeInfoService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 部门信息控制器
 * @ClassName: DepartmentInfoAction   
 * @Description:  提供部门信息的相关操作方法. 
 * @author HiSay  
 * @date 2015-11-02 22:46:07
 *
 */
public class DepartmentInfoAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//部门信息的业务层
	@Resource 
	private IDepartmentInfoService departmentInfoService;
	@Resource 
	private IEmployeeInfoService employeeInfoService;
	@Resource 
	private ISystemOperationLogService logService;
	//部门信息实体
	private DepartmentInfo departmentInfo;
	private String id,name,introduce,parentId,departmentInfoHtml,departmentInfoTree;

	
	/**
	 * 添加部门信息
	 * @Title: DepartmentInfoAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setDepartmentInfoHtml(departmentInfoService.createDepartmentInfoOptionHtml(id));
			return "addDepartmentInfo";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError("部门名称不能为空");
				return JSON;
			}
			//保存部门信息实体
			boolean isSuc = departmentInfoService.persist(name, introduce, parentId);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增部门信息：" + name;
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
	 * 修改部门信息
	 * @Title: DepartmentInfoAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setDepartmentInfo(departmentInfoService.queryById(DepartmentInfo.class.getSimpleName(), id));
			if(departmentInfo.getParent() != null){
				this.setDepartmentInfoHtml(departmentInfoService.createDepartmentInfoOptionHtml(departmentInfo.getParent().getId()));
			}else{
				this.setDepartmentInfoHtml(departmentInfoService.createDepartmentInfoOptionHtml(null));
			}
			return "editDepartmentInfo";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError("id不能为空");
				return JSON;
			}
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError("部门名称不能为空");
				return JSON;
			}
			//修改部门信息实体
			boolean isSuc = departmentInfoService.merge(id, name, introduce,  parentId);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改部门信息：" + name;
				String logName = SystemOparetionLogUtil.getLogName();
				String logTime = SystemOparetionLogUtil.getLogTime();
				String logIp = SystemOparetionLogUtil.getLogIp();
				
				logService.persist(logName, logContent, logTime, logIp, versionFlag);
				data = AjaxData.responseSuccess("编辑成功");
			} else {
				data = AjaxData.responseError("编辑失败");
			}
			return JSON;
		}
	}
	
	/**
	 * 修改部门信息
	 * @Title: DepartmentInfoAction
	 * @Description:   
	 * @return
	 * @throws ServiceException    
	 */
	public String editInCharge() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			
			List<EmployeeInfo> list = employeeInfoService.queryByDepartmentId(id, versionFlag);
			ActionContext.getContext().put("list", list);
			
			departmentInfo = departmentInfoService.get(DepartmentInfo.class, id);
			return "editPrincipal";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError("部门id不能为空");
				return JSON;
			}
			if(GeneralUtil.isNull(parentId)) {
				data = AjaxData.responseError("所选人不能为空");
				return JSON;
			}
			
			DepartmentInfo department = departmentInfoService.get(DepartmentInfo.class, id);
			EmployeeInfo inCharge = employeeInfoService.get(EmployeeInfo.class, parentId);
			if(department != null && inCharge != null){
				
				department.setInCharge(inCharge);
				departmentInfoService.merge(department);
			}
			//根据修改结果返回页面
			String logContent = "修改部门信息：" + name;
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess("编辑成功");
			return JSON;
		}
	}
	
	/**
	 * 管理部门信息实体
	 * @Title: DepartmentInfoAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		departmentInfoTree = departmentInfoService.createDepartmentInfoTree();
		return "manageDepartmentInfo";
	}
	
	/**
	 * @author wangjiafeng
	 * 修改排序值
	 * @method changeOrderValue
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-1-5 上午11:06:50
	 */
	public String changeOrderValue() throws Exception{
		departmentInfo = departmentInfoService.queryById(DepartmentInfo.class.getSimpleName(), id);
		Boolean suc = false;
		if(departmentInfo != null && GeneralUtil.isNotNull(orderValue)){
			departmentInfo.getStatus().setOrderValue(orderValue);
			departmentInfoService.merge(departmentInfo);
			suc = true;
		}
		if(suc){
			String logContent = "修改部门排序信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = "success";
		}else{
			data = "failed";
		}
		return JSON;
	}
	
	/**
	 * 查看回收站
	 * @Title: DepartmentInfoAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(departmentInfoService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleDepartmentInfo";
	}
	
	/**
	 * 逻辑删除部门信息对象
	 * @Title: DepartmentInfoAction
	 * @Description: 把部门信息对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = departmentInfoService.logicDeleteEntity(DepartmentInfo.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除部门信息";
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
	 * 物理删除部门信息对象
	 * @Title: DepartmentInfoAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = departmentInfoService.remove(id);
		if(isSuc) {
			String logContent = "物理删除部门信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = "success";
		} else {
			data = "failed";
		}
		return TEXT;
	}
	
	/**
	 * 还原一个部门信息对象
	 * @Title: DepartmentInfoAction
	 * @Description: 从回收站还原部门信息对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = departmentInfoService.restoreEntity(DepartmentInfo.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原部门信息";
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
	 * 审核部门信息对象
	 * @Title: DepartmentInfoAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = departmentInfoService.auditEntity(DepartmentInfo.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核部门信息";
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
	 * @Title: DepartmentInfoAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = departmentInfoService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作部门信息";
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
	 * 批量删除部门信息实体
	 * @return
	 * @throws ServiceException
	 */
	public String batchRealDelete() throws ServiceException {
		boolean isSuc = departmentInfoService.batchRealDelete(idsValue,versionFlag);
		if(isSuc) {
			String logContent = "批量删除部门信息";
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
	public DepartmentInfo getDepartmentInfo() {
		return departmentInfo;
	}
	public void setDepartmentInfo(DepartmentInfo departmentInfo) {
		this.departmentInfo = departmentInfo;
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
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getDepartmentInfoHtml() {
		return departmentInfoHtml;
	}

	public void setDepartmentInfoHtml(String departmentInfoHtml) {
		this.departmentInfoHtml = departmentInfoHtml;
	}

	public String getDepartmentInfoTree() {
		return departmentInfoTree;
	}

	public void setDepartmentInfoTree(String departmentInfoTree) {
		this.departmentInfoTree = departmentInfoTree;
	}

	public IDepartmentInfoService getDepartmentInfoService() {
		return departmentInfoService;
	}

	public void setDepartmentInfoService(
			IDepartmentInfoService departmentInfoService) {
		this.departmentInfoService = departmentInfoService;
	}

	public IEmployeeInfoService getEmployeeInfoService() {
		return employeeInfoService;
	}

	public void setEmployeeInfoService(IEmployeeInfoService employeeInfoService) {
		this.employeeInfoService = employeeInfoService;
	}
	
}
