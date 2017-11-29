package com.ticket.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
<#if importLob ??>
import javax.persistence.Lob;
</#if>
<#if importDate ??>
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
</#if>
import java.util.UUID;
/**
 * ${entityTitle}
 * @ClassName: ${entityName}   
 * @Description: ${entityDescript}
 * @author HiSay  
 * @date  ${dateTime}
 *
 */
@Entity
@Table(name="ticket_${entityName}",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class ${entityName} implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	@Id
	private ${entityIdType} id = UUID.randomUUID().toString();
	/**
	 * 实体状态
	 */
	@Embedded
	private CommonEntity status = new CommonEntity();
	
	<#if attrList ??>
	<#list attrList as attr>
	/**
	 * ${attr.attrComment}
	 */
	${attr.attrAnnotation}
	private ${attr.attrDataType} ${attr.attrName};

	</#list>
	</#if>

	public ${entityIdType} getId() {
		return id;
	}
	public void setId(${entityIdType} id) {
		this.id = id;
	}
	public CommonEntity getStatus() {
		return status;
	}
	public void setStatus(CommonEntity status) {
		this.status = status;
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
