package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.SysUserExtends;
import com.ticket.service.ISysUserExtendsService;
import com.ticket.util.GeneralUtil;

/**
 * 用户扩展表控制器
 * @ClassName: SysUserExtendsAction   
 * @Description:  提供用户扩展表的相关操作方法. 
 * @author HiSay  
 * @date 2015-10-23 15:14:44
 *
 */
public class SysUserExtendsAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//用户扩展表的业务层
	@Resource private ISysUserExtendsService sysUserExtendsService = null;
	//用户扩展表实体
	private SysUserExtends sysUserExtends = null;
	//主键
	private String id = null;
    //用户ID
	private String user_id = null;
    //字段名称
	private String name = null;
    //字段类型（字典）
	private String type = null;
    //字段值
	private String value = null;
	
	/**
	 * 添加用户扩展表
	 * @Title: SysUserExtendsAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addSysUserExtends";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(user_id)) {
				data = getText("user_id.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(name)) {
				data = getText("name.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(type)) {
				data = getText("type.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(value)) {
				data = getText("value.required");
				return TEXT;
			}
			//保存用户扩展表实体
			boolean isSuc = sysUserExtendsService.persist(user_id, name, type, value, versionFlag);
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
	 * 修改用户扩展表
	 * @Title: SysUserExtendsAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setSysUserExtends(sysUserExtendsService.queryById(SysUserExtends.class.getSimpleName(), id));
			return "editSysUserExtends";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = getText("id.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(user_id)) {
				data = getText("user_id.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(name)) {
				data = getText("name.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(type)) {
				data = getText("type.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(value)) {
				data = getText("value.required");
				return TEXT;
			}
			//修改用户扩展表实体
			boolean isSuc = sysUserExtendsService.merge(id, user_id, name, type, value,  versionFlag);
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
	 * 管理用户扩展表实体
	 * @Title: SysUserExtendsAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(sysUserExtendsService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageSysUserExtends";
	}
	
	/**
	 * 查看回收站
	 * @Title: SysUserExtendsAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(sysUserExtendsService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleSysUserExtends";
	}
	
	/**
	 * 逻辑删除用户扩展表对象
	 * @Title: SysUserExtendsAction
	 * @Description: 把用户扩展表对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = sysUserExtendsService.logicDeleteEntity(SysUserExtends.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除用户扩展表对象
	 * @Title: SysUserExtendsAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = sysUserExtendsService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个用户扩展表对象
	 * @Title: SysUserExtendsAction
	 * @Description: 从回收站还原用户扩展表对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = sysUserExtendsService.restoreEntity(SysUserExtends.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核用户扩展表对象
	 * @Title: SysUserExtendsAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = sysUserExtendsService.auditEntity(SysUserExtends.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: SysUserExtendsAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = sysUserExtendsService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public SysUserExtends getSysUserExtends() {
		return sysUserExtends;
	}
	public void setSysUserExtends(SysUserExtends sysUserExtends) {
		this.sysUserExtends = sysUserExtends;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
