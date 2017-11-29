package com.ticket.pojo;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 * 机场部门
 * @ClassName: AirportDepartment   
 * @Description: 机场部门表
 * @author HiSay  
 * @date  2015-11-16 22:56:41
 *
 */
@Entity
@Table(name="ticket_AirportDepartment",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class AirportDepartment {

	/**
	 * 主键
	 */
	@Id
	protected String id = UUID.randomUUID().toString();
	
	/**
	 * 实体状态
	 */
	@Embedded
	protected CommonEntity status = new CommonEntity();
	
	/**
	 * 父组织机构
	 */
	@ManyToOne
	protected AirportDepartment parent;
	
	/**
	 * 组织机构名称
	 */
	@Column(length=255)
	protected String name;
	
	/**
	 * 是否可以编辑
	 */
	protected boolean editable = true;
	
	/**
	 * 是否可以删除
	 */
	protected boolean deletable = true;
	
	/**
	 * 备注
	 */
	protected String description;
	
	/**
	 * 部门编号
	 */
	@Column
	private String departmentId;

	/**
	 * 是否默认显示
	 */
	@Column
	private Integer isDefaultShow;

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

	public AirportDepartment getParent() {
		return parent;
	}

	public void setParent(AirportDepartment parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public boolean isDeletable() {
		return deletable;
	}

	public void setDeletable(boolean deletable) {
		this.deletable = deletable;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getIsDefaultShow() {
		return isDefaultShow;
	}

	public void setIsDefaultShow(Integer isDefaultShow) {
		this.isDefaultShow = isDefaultShow;
	}
}
