package com.ticket.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
/**
 * 员工外出拜访记录
 * @ClassName: EmployeeOutVisit   
 * @Description: 员工外出拜访记录表
 * @author HiSay  
 * @date  2015-11-02 22:49:40
 *
 */
@Entity
@Table(name="ticket_EmployeeOutVisit",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class EmployeeOutVisit implements Serializable {

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
	private Date visitDate;

	/**
	 * 客户id
	 */
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private ChannelCustomerInfo customer;

	/**
	 * 联系人
	 */
	@Column
	private String contact;

	/**
	 * 联系电话
	 */
	@Column
	private String contactPhone;

	/**
	 * 拜访目的
	 */
	@Column
	private String visitPurpose;

	/**
	 * 出访人
	 */
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private EmployeeInfo employee;

	/**
	 * 陪同人
	 */
	@Column
	private String accompanyPerPerson;

	/**
	 * 出发时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date setoutTime;

	/**
	 * 返回时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date backTime;

	/**
	 * 拜访结果
	 */
	@Column
	private String visitResult;

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
	public String getVisitPurpose() {
		return visitPurpose;
	}
	public void setVisitPurpose(String visitPurpose) {
		this.visitPurpose = visitPurpose;
	}
	public String getAccompanyPerPerson() {
		return accompanyPerPerson;
	}
	public void setAccompanyPerPerson(String accompanyPerPerson) {
		this.accompanyPerPerson = accompanyPerPerson;
	}
	public Date getSetoutTime() {
		return setoutTime;
	}
	public void setSetoutTime(Date setoutTime) {
		this.setoutTime = setoutTime;
	}
	public Date getBackTime() {
		return backTime;
	}
	public void setBackTime(Date backTime) {
		this.backTime = backTime;
	}
	public String getVisitResult() {
		return visitResult;
	}
	public void setVisitResult(String visitResult) {
		this.visitResult = visitResult;
	}
	public ChannelCustomerInfo getCustomer() {
		return customer;
	}
	public void setCustomer(ChannelCustomerInfo customer) {
		this.customer = customer;
	}
	public EmployeeInfo getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeeInfo employee) {
		this.employee = employee;
	}
	
}
