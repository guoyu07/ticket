package com.ticket.pojo;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @Description：系统组织表
 * @author：涂有
 * @date 2015年12月31日 上午10:40:47
 */
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Entity
@Table(name="ticket_systemorg",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class SystemOrg implements Serializable {

	/**   
	 * @Fields serialVersionUID :    
	 */ 
	protected static final long serialVersionUID = 1L;
	
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
	protected SystemOrg parent;
	
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

	public SystemOrg getParent() {
		return parent;
	}

	public void setParent(SystemOrg parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
