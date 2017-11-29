package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.EmployeeInfoZengLogDetail;
import com.ticket.service.IEmployeeInfoZengLogDetailService;
import com.ticket.util.GeneralUtil;

/**
 * 员工转移服务码附表控制器
 * @ClassName: EmployeeInfoZengLogDetailAction   
 * @Description:  提供员工转移服务码附表的相关操作方法. 
 * @author HiSay  
 * @date 2016-01-18 17:27:18
 *
 */
public class EmployeeInfoZengLogDetailAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//员工转移服务码附表的业务层
	@Resource private IEmployeeInfoZengLogDetailService employeeInfoZengLogDetailService = null;
	//员工转移服务码附表实体
	private EmployeeInfoZengLogDetail employeeInfoZengLogDetail = null;
	//主键
	private String id = null;
	
	/**
	 * 添加员工转移服务码附表
	 * @Title: EmployeeInfoZengLogDetailAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addEmployeeInfoZengLogDetail";
		} else {
			//非空验证.
			//保存员工转移服务码附表实体
			boolean isSuc = employeeInfoZengLogDetailService.persist( versionFlag);
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
	 * 修改员工转移服务码附表
	 * @Title: EmployeeInfoZengLogDetailAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setEmployeeInfoZengLogDetail(employeeInfoZengLogDetailService.queryById(EmployeeInfoZengLogDetail.class.getSimpleName(), id));
			return "editEmployeeInfoZengLogDetail";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			//修改员工转移服务码附表实体
			boolean isSuc = employeeInfoZengLogDetailService.merge(id,   versionFlag);
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
	 * 管理员工转移服务码附表实体
	 * @Title: EmployeeInfoZengLogDetailAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(employeeInfoZengLogDetailService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageEmployeeInfoZengLogDetail";
	}
	
	/**
	 * 查看回收站
	 * @Title: EmployeeInfoZengLogDetailAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(employeeInfoZengLogDetailService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleEmployeeInfoZengLogDetail";
	}
	
	/**
	 * 逻辑删除员工转移服务码附表对象
	 * @Title: EmployeeInfoZengLogDetailAction
	 * @Description: 把员工转移服务码附表对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = employeeInfoZengLogDetailService.logicDeleteEntity(EmployeeInfoZengLogDetail.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除员工转移服务码附表对象
	 * @Title: EmployeeInfoZengLogDetailAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = employeeInfoZengLogDetailService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个员工转移服务码附表对象
	 * @Title: EmployeeInfoZengLogDetailAction
	 * @Description: 从回收站还原员工转移服务码附表对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = employeeInfoZengLogDetailService.restoreEntity(EmployeeInfoZengLogDetail.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核员工转移服务码附表对象
	 * @Title: EmployeeInfoZengLogDetailAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = employeeInfoZengLogDetailService.auditEntity(EmployeeInfoZengLogDetail.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: EmployeeInfoZengLogDetailAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = employeeInfoZengLogDetailService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public EmployeeInfoZengLogDetail getEmployeeInfoZengLogDetail() {
		return employeeInfoZengLogDetail;
	}
	public void setEmployeeInfoZengLogDetail(EmployeeInfoZengLogDetail employeeInfoZengLogDetail) {
		this.employeeInfoZengLogDetail = employeeInfoZengLogDetail;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
