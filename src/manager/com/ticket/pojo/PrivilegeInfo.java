package com.ticket.pojo;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 * 权限信息
 * @ClassName: PrivilegeInfo   
 * @Description: 权限信息表
 * @author HiSay  
 * @date  2015-10-16 11:37:11
 *
 */
@Entity
@Table(name="ticket_PrivilegeInfo",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class PrivilegeInfo implements Serializable {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */ 
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	@Id
	private String id = UUID.randomUUID().toString();
	/**
	 * 实体状态
	 */
	@Embedded
	private CommonEntity status = new CommonEntity();
	/**
	 * 页面Id
	 */
	@Column
	private String systemModulId = null;
	
	/**
	 * 权限名称
	 */
	@Column
	private String name = null;

	/**
	 * 权限描述
	 */
	@Column
	private String descript = null;

	/**
	 * 包含的方法
	 */
	@Column
	private String includeMethods = null;


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public CommonEntity getStatus() {
		return status;
	}
	public void setStatus(CommonEntity status) {
		this.status = status;
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
	public String getIncludeMethods() {
		return includeMethods;
	}
	public void setIncludeMethods(String includeMethods) {
		this.includeMethods = includeMethods;
	}
	public String getSystemModulId() {
		return systemModulId;
	}
	public void setSystemModulId(String systemModulId) {
		this.systemModulId = systemModulId;
	}
}
