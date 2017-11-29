package com.ticket.pojo;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 系统管理员
 * @ClassName: SystemUser   
 * @Description: 系统管理员
 * @author HiSay  
 * @date  2014-10-14 09:35:50
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Table(name="ticket_SystemUser",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class SystemUser extends BasePojo implements Serializable {

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
	 * 管理员呢称
	 */
	@Column(length=50)
	protected String name = null;

	/**
	 * 管理员性别
	 */
	@Column
	protected Integer sex = null;

	/**
	 * 登陆名称
	 */
	@Column(length=50)
	protected String loginId = null;

	/**
	 * 登陆密码
	 */
	@Column(length=50)
	protected String password = null;

	/**
	 * 联系电话
	 */
	@Column(length=50)
	protected String phone = null;
	
	
	//protected String employee_id;

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
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String getIds() {
		return this.getId();
	}
	@Override
	public void setIds(String ids) {
		super.setIds(ids);
	}
	public String getFlag() {
		return this.getStatus().getVersionFlag();
	}
	public Long getViewUrl() {
		return this.getStatus().getVisitUrl();
	}
}
