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
 * 数据权限
 * @ClassName: DataAuthoritys   
 * @Description: 数据权限
 * @author HiSay  
 * @date  2016-05-25 15:20:21
 *
 */
@Entity
@Table(name="ticket_DataAuthoritys",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class DataAuthoritys implements Serializable {

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
	 * 权限名称
	 */
	@Column(length=255)
	private String name = null;

	/**
	 * 权限描述
	 */
	@Column(length=255)
	private String descript = null;

	/**
	 * 权限内容
	 */
	@Column(length=255)
	private String content = null;
	
	/**
	 * 系统模块
	 */
	@Column
	private String systemModulId = null;
	
	/**
	 * 访问的方法
	 */
	@Column
	private String inMethod = null;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSystemModulId() {
		return systemModulId;
	}
	public void setSystemModulId(String systemModulId) {
		this.systemModulId = systemModulId;
	}
	public String getInMethod() {
		return inMethod;
	}
	public void setInMethod(String inMethod) {
		this.inMethod = inMethod;
	}
}
