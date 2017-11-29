package com.ticket.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * 电话回访记录
 * @ClassName: PhoneVisit   
 * @Description: 电话回访记录表
 * @author HiSay  
 * @date  2015-11-02 23:14:13
 *
 */
@Entity
@Table(name="ticket_PhoneVisit",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class PhoneVisit implements Serializable {

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
	 * 拜访日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date visitDate = null;

	/**
	 * 出访人
	 */
	@Column
	private String employee_id = null;

	/**
	 * 客户id
	 */
	@Column
	private String customer_id = null;

	/**
	 * 联系人
	 */
	@Column
	private String contact = null;

	/**
	 * 联系电话
	 */
	@Column
	private String contactPhone = null;

	/**
	 * 沟通内容
	 */
	@Column
	private String content = null;

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
	
	public Date getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	
}
