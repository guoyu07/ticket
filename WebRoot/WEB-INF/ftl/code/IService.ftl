package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.${entityName};
<#if importUUID ??>
import java.util.UUID;
</#if>
<#if importDate ??>
import java.util.Date;
</#if>


/**
 * ${entityTitle}业务接口
 * @ClassName: I${entityName}Service   
 * @Description: 提供${entityTitle}操作的增删改查   
 * @author HiSay  
 * @date  ${dateTime}
 *
 */
public interface I${entityName}Service extends IBaseService<${entityName}, ${entityIdType}> {
	/**
	 * 保存${entityTitle}实体
	 * @Title: I${entityName}Service
	 * @Description:
	 <#if attrList ??>
	 <#list attrList as attr>
	 * @param @param ${attr.attrName}  ${attr.attrComment}
    	 </#list>
	 </#if>
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(${attributesValue} String versionFlag) throws ServiceException;
	
	/**
	 * 修改${entityTitle}实体
	 * @Title: I${entityName}Service
	 * @Description:
	 <#if attrList ??>
	 <#list attrList as attr>
	 * @param @param ${attr.attrName}  ${attr.attrComment}
    	 </#list>
	 </#if>
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(${entityIdType} id, ${attributesValue} String versionFlag) throws ServiceException;
	
	/**
	 * 移除${entityTitle}实体
	 * @Title: I${entityName}Service
	 * @Description: 
	 * @param @param id ${entityTitle}ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(${entityIdType} id) throws ServiceException;
}