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
 * 用户扩展表
 * @ClassName: SysUserExtends   
 * @Description: 用户扩展表
 * @author HiSay  
 * @date  2015-10-23 15:14:44
 *
 */
@Entity
@Table(name="ticket_SystemUserExtends",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class SysUserExtends implements Serializable {

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
	 * 用户ID
	 */
	@Column(length=255)
	private String user_id = null;

	/**
	 * 字段名称
	 */
	@Column(length=255)
	private String name = null;

	/**
	 * 字段类型（字典）
	 */
	@Column(length=255)
	private String type = null;

	/**
	 * 字段值
	 */
	@Column(length=255)
	private String value = null;


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
