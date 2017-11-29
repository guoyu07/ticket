package com.ticket.pojo;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 * 前端菜单管理表
 * @ClassName: FrontMenu   
 * @Description: 前端菜单管理表
 * @author HiSay  
 * @date  2016-02-19 14:55:25
 *
 */
@Entity
@Table(name="ticket_FrontMenu",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class FrontMenu implements Serializable {

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
	 * 菜单名称
	 */
	@Column(length=255)
	private String name = null;

	/**
	 * 菜单值
	 */
	@Column(length=255)
	private String value = null;

	/**
	 * 父菜单
	 */
	@ManyToOne
	private FrontMenu parent = null;

	/**
	 * 是否加载子节点
	 */
	@Column(length=255)
	private boolean loadSub;

	/**
	 * 备注
	 */
	@Column(length=1024)
	private String description = null;


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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public FrontMenu getParent() {
		return parent;
	}
	public void setParent(FrontMenu parent) {
		this.parent = parent;
	}
	public boolean getLoadSub() {
		return loadSub;
	}
	public void setLoadSub(boolean loadSub) {
		this.loadSub = loadSub;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
