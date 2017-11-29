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
 * 角色权限
 * @ClassName: RolePrivilege   
 * @Description: 角色权限关系表
 * @author HiSay  
 * @date  2015-10-17 11:28:35
 *
 */
@Entity
@Table(name="ticket_RolePrivilege",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class RolePrivilege implements Serializable {

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
	 * 角色id
	 */
	@Column
	private String roleId = null;

	/**
	 * 模块id
	 */
	@Column
	private String systemModuleId = null;

	/**
	 * 权限id
	 */
	@Column
	private String privilegeId = null;


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
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getSystemModuleId() {
		return systemModuleId;
	}
	public void setSystemModuleId(String systemModuleId) {
		this.systemModuleId = systemModuleId;
	}
	public String getPrivilegeId() {
		return privilegeId;
	}
	public void setPrivilegeId(String privilegeId) {
		this.privilegeId = privilegeId;
	}
}
