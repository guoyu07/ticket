package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.${entityName};
import com.ticket.service.I${entityName}Service;
import com.ticket.bo.AjaxData;
import com.ticket.util.GeneralUtil;
<#if importDate ??>
import java.util.Date;
</#if>
<#if importUUID ??>
import java.util.UUID;
</#if>

/**
 * ${entityTitle}控制器
 * @ClassName: ${entityName}Action   
 * @Description:  提供${entityTitle}的相关操作方法. 
 * @author HiSay  
 * @date ${dateTime}
 *
 */
public class ${entityName}Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//${entityTitle}的业务层
	@Resource private I${entityName}Service ${entityNameFirstLowerCase}Service;
	//${entityTitle}实体
	private ${entityName} ${entityNameFirstLowerCase};
	//主键
	private ${entityIdType} id;
	<#if attrList ??>
	<#list attrList as attr>
    //${attr.attrComment}
	private ${attr.attrDataType} ${attr.attrName};
	</#list>
	</#if>
	
	/**
	 * 添加${entityTitle}
	 * @Title: ${entityName}Action
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "add${entityName}";
		} else {
			//非空验证.
			<#if attrList ??>
			<#list attrList as attr>
			if(GeneralUtil.isNull(${attr.attrName})) {
				data = AjaxData.responseError(getText("${attr.attrName}.required"));
				return JSON;
			}
			</#list>
			</#if>
			//保存${entityTitle}实体
			boolean isSuc = ${entityNameFirstLowerCase}Service.persist(${serviceAttributesValue} versionFlag);
			//根据保存结果返回页面
			if(isSuc) {
				data = AjaxData.responseSuccess(getText("addSuccess"));
			} else {
				data = AjaxData.responseError(getText("addFailed"));
			return JSON;
		}
	}
	
	/**
	 * 修改${entityTitle}
	 * @Title: ${entityName}Action
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.set${entityName}(${entityNameFirstLowerCase}Service.queryById(${entityName}.class.getSimpleName(), id));
			return "edit${entityName}";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			<#if attrList ??>
			<#list attrList as attr>
			if(GeneralUtil.isNull(${attr.attrName})) {
				data = AjaxData.responseError(getText("${attr.attrName}.required"));
				return JSON;
			}
			</#list>
			</#if>
			//修改${entityTitle}实体
			boolean isSuc = ${entityNameFirstLowerCase}Service.merge(id, ${serviceAttributesValue}  versionFlag);
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
	 * 管理${entityTitle}实体
	 * @Title: ${entityName}Action
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(${entityNameFirstLowerCase}Service.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manage${entityName}";
	}
	
	/**
	 * 查看回收站
	 * @Title: ${entityName}Action
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(${entityNameFirstLowerCase}Service.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycle${entityName}";
	}
	
	/**
	 * 逻辑删除${entityTitle}对象
	 * @Title: ${entityName}Action
	 * @Description: 把${entityTitle}对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = ${entityNameFirstLowerCase}Service.logicDeleteEntity(${entityName}.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除${entityTitle}对象
	 * @Title: ${entityName}Action
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = ${entityNameFirstLowerCase}Service.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个${entityTitle}对象
	 * @Title: ${entityName}Action
	 * @Description: 从回收站还原${entityTitle}对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = ${entityNameFirstLowerCase}Service.restoreEntity(${entityName}.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核${entityTitle}对象
	 * @Title: ${entityName}Action
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = ${entityNameFirstLowerCase}Service.auditEntity(${entityName}.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: ${entityName}Action
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = ${entityNameFirstLowerCase}Service.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public ${entityName} get${entityName}() {
		return ${entityNameFirstLowerCase};
	}
	public void set${entityName}(${entityName} ${entityNameFirstLowerCase}) {
		this.${entityNameFirstLowerCase} = ${entityNameFirstLowerCase};
	}
	public ${entityIdType} getId() {
		return id;
	}
	public void setId(${entityIdType} id) {
		this.id = id;
	}
	<#if attrList ??>
	<#list attrList as attr>
	public ${attr.attrDataType} get${attr.firstCapAttrName}() {
		return ${attr.attrName};
	}
	public void set${attr.firstCapAttrName}(${attr.attrDataType} ${attr.attrName}) {
		this.${attr.attrName} = ${attr.attrName};
	}
	</#list>
	</#if>
}
