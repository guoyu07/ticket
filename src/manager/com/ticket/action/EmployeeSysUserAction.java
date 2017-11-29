package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.EmployeeSysUser;
import com.ticket.service.IEmployeeSysUserService;
import com.ticket.util.GeneralUtil;

/**
 * 员工系统用户关系控制器
 * @ClassName: EmployeeSysUserAction   
 * @Description:  提供员工系统用户关系的相关操作方法. 
 * @author HiSay  
 * @date 2015-11-18 16:09:00
 *
 */
public class EmployeeSysUserAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//员工系统用户关系的业务层
	@Resource private IEmployeeSysUserService employeeSysUserService = null;
	//员工系统用户关系实体
	private EmployeeSysUser employeeSysUser = null;
	//主键
	private String id = null;
    //员工id
	private String employee_id = null;
    //用户id
	private String sysUser_id = null;
	
	/**
	 * 添加员工系统用户关系
	 * @Title: EmployeeSysUserAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addEmployeeSysUser";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(employee_id)) {
				data = AjaxData.responseError(getText("employee_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(sysUser_id)) {
				data = AjaxData.responseError(getText("sysUser_id.required"));
				return JSON;
			}
			//保存员工系统用户关系实体
			boolean isSuc = employeeSysUserService.persist(employee_id, sysUser_id, versionFlag);
			//根据保存结果返回页面
			if(isSuc) {
				data = AjaxData.responseSuccess(getText("addSuccess"));
			} else {
				data = AjaxData.responseError(getText("addFailed"));
			}
			return JSON;
		}
	}
	
	/**
	 * 修改员工系统用户关系
	 * @Title: EmployeeSysUserAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setEmployeeSysUser(employeeSysUserService.queryById(EmployeeSysUser.class.getSimpleName(), id));
			return "editEmployeeSysUser";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(employee_id)) {
				data = AjaxData.responseError(getText("employee_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(sysUser_id)) {
				data = AjaxData.responseError(getText("sysUser_id.required"));
				return JSON;
			}
			//修改员工系统用户关系实体
			boolean isSuc = employeeSysUserService.merge(id, employee_id, sysUser_id,  versionFlag);
			//根据修改结果返回页面
			if(isSuc) {
				data = AjaxData.responseSuccess(getText("editSuccess"));
			} else {
				data = AjaxData.responseError(getText("editFailed"));
			}
			return JSON;
		}
	}
	
	/**
	 * 管理员工系统用户关系实体
	 * @Title: EmployeeSysUserAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(employeeSysUserService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageEmployeeSysUser";
	}
	
	/**
	 * 查看回收站
	 * @Title: EmployeeSysUserAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(employeeSysUserService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleEmployeeSysUser";
	}
	
	/**
	 * 逻辑删除员工系统用户关系对象
	 * @Title: EmployeeSysUserAction
	 * @Description: 把员工系统用户关系对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = employeeSysUserService.logicDeleteEntity(EmployeeSysUser.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除员工系统用户关系对象
	 * @Title: EmployeeSysUserAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = employeeSysUserService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个员工系统用户关系对象
	 * @Title: EmployeeSysUserAction
	 * @Description: 从回收站还原员工系统用户关系对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = employeeSysUserService.restoreEntity(EmployeeSysUser.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核员工系统用户关系对象
	 * @Title: EmployeeSysUserAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = employeeSysUserService.auditEntity(EmployeeSysUser.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: EmployeeSysUserAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = employeeSysUserService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public EmployeeSysUser getEmployeeSysUser() {
		return employeeSysUser;
	}
	public void setEmployeeSysUser(EmployeeSysUser employeeSysUser) {
		this.employeeSysUser = employeeSysUser;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public String getSysUser_id() {
		return sysUser_id;
	}
	public void setSysUser_id(String sysUser_id) {
		this.sysUser_id = sysUser_id;
	}
}
