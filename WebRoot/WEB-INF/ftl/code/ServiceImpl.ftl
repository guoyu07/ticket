package com.ticket.serviceImpl;


import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.${entityName};
import com.ticket.service.I${entityName}Service;
import com.ticket.util.DecoderUtil;
<#if importUUID ??>
import java.util.UUID;
</#if>
<#if importDate ??>
import java.util.Date;
</#if>
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ${entityTitle}业务接口实现类
 * @ClassName: I${entityName}Service   
 * @Description: 提供${entityTitle}操作的增删改查   
 * @author HiSay  
 * @date ${dateTime}
 *
 */
public class ${entityName}ServiceImpl extends BaseServiceImpl<${entityName}, ${entityIdType}> implements I${entityName}Service {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(${entityName}ServiceImpl.class);

	@Override
	public boolean persist(${attributesValue} String versionFlag) throws ServiceException {
		${entityName} ${entityNameFirstLowerCase} = new ${entityName}();
		<#if attrList ??>
		<#list attrList as attr>
		<#if attr.attrDataType =='String'>
		${entityNameFirstLowerCase}.set${attr.firstCapAttrName}(DecoderUtil.UtfDecoder(${attr.attrName}));
			<#else>
		${entityNameFirstLowerCase}.set${attr.firstCapAttrName}(${attr.attrName});
		</#if>
		</#list>
		</#if>
		CommonEntity status = ${entityNameFirstLowerCase}.getStatus();
		status.setVersionFlag(versionFlag);
		${entityNameFirstLowerCase}.setStatus(status);
		dbDAO.persist(${entityNameFirstLowerCase});
		return true;
	}
	
	@Override
	public boolean merge(${entityIdType} id, ${attributesValue} String versionFlag) throws ServiceException {
		${entityName} ${entityNameFirstLowerCase} = dbDAO.queryById(this.getTableNameFromEntity(${entityName}.class), id);
		<#if attrList ??>
		<#list attrList as attr>
		<#if attr.attrDataType =='String'>
		${entityNameFirstLowerCase}.set${attr.firstCapAttrName}(DecoderUtil.UtfDecoder(${attr.attrName}));
			<#else>
		${entityNameFirstLowerCase}.set${attr.firstCapAttrName}(${attr.attrName});
		</#if>
		</#list>
		</#if>
		CommonEntity status = ${entityNameFirstLowerCase}.getStatus();
		status.setVersionFlag(versionFlag);
		${entityNameFirstLowerCase}.setStatus(status);
		dbDAO.merge(${entityNameFirstLowerCase});
		return true;
	}

	@Override
	public boolean remove(${entityIdType} id) throws ServiceException {
		${entityName} ${entityNameFirstLowerCase} = dbDAO.queryById(this.getTableNameFromEntity(${entityName}.class), id);
		dbDAO.remove(${entityNameFirstLowerCase});
		return true;
	}

}