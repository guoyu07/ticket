package com.ticket.pojo;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @Description：系统组织表
 * @author：涂有
 * @date 2015年12月31日 上午10:40:47
 */
@Entity
@Table(name="ticket_systemuserorg",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class SystemUserOrg implements Serializable {

	/**   
	 * @Fields serialVersionUID :    
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
	 * 系统用户
	 */
	private SystemUser user;
	
	/**
	 * 系统组织机构
	 */
	private SystemOrg org;

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

	public SystemUser getUser() {
		return user;
	}

	public void setUser(SystemUser user) {
		this.user = user;
	}

	public SystemOrg getOrg() {
		return org;
	}

	public void setOrg(SystemOrg org) {
		this.org = org;
	}
}
