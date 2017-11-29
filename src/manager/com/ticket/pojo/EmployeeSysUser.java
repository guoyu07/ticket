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
 * 员工系统用户关系
 * @ClassName: EmployeeSysUser   
 * @Description: 员工系统用户关系
 * @author HiSay  
 * @date  2015-11-18 16:09:00
 *
 */
@Entity
@Table(name="ticket_EmployeeSysUser",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class EmployeeSysUser implements Serializable {

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
	 * 员工id
	 */
	@Column
	private String employee_id = null;

	/**
	 * 用户id
	 */
	@Column
	private String sysUser_id = null;


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
