package com.ticket.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.UUID;
/**
 * 捡单客户申请
 * @ClassName: PickUpCustomerApply   
 * @Description: 捡单客户申请
 * @author HiSay  
 * @date  2015-11-24 15:45:34
 *
 */
@Entity
@Table(name="ticket_PickUpCustomerApply",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class PickUpCustomerApply implements Serializable {

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
	 * 申请人
	 */
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private EmployeeInfo employee;
	
	/**
	 * 原员工id
	 */
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private EmployeeInfo oldemployee;

	/**
	 * 客户
	 */
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private ChannelCustomerInfo customer;
	
	/**
	 * 申请状态
	 */
	@Column
	private Integer applyState;


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
	public Integer getApplyState() {
		return applyState;
	}
	public void setApplyState(Integer applyState) {
		this.applyState = applyState;
	}
	public EmployeeInfo getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeeInfo employee) {
		this.employee = employee;
	}
	public EmployeeInfo getOldemployee() {
		return oldemployee;
	}
	public void setOldemployee(EmployeeInfo oldemployee) {
		this.oldemployee = oldemployee;
	}
	public ChannelCustomerInfo getCustomer() {
		return customer;
	}
	public void setCustomer(ChannelCustomerInfo customer) {
		this.customer = customer;
	}
}
