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

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
/**
 * 撞单日志
 * @ClassName: HitSingleLog   
 * @Description: 撞单日志
 * @author HiSay  
 * @date  2016-01-03 09:38:37
 *
 */
@Entity
@Table(name="ticket_HitSingleLog",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class HitSingleLog implements Serializable {

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
	 * 目标客户
	 */
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private ChannelCustomerInfo channelCustomerInfo;

	/**
	 * 申请员工
	 */
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private EmployeeInfo employeeInfo;
	/**
	 * 关联的合同id
	 */
	@ManyToOne
	private Agreement agreement;
	/**
	 * 审批状态 
	 * 0 未审评
	 * 1 审批通过
	 * 2 审批未通过
	 */
	@Column
	private Integer state = 0;
	/**
	 * 审批描述
	 */
	@Column(length=8000)
	private String remark;

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
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public ChannelCustomerInfo getChannelCustomerInfo() {
		return channelCustomerInfo;
	}
	public void setChannelCustomerInfo(ChannelCustomerInfo channelCustomerInfo) {
		this.channelCustomerInfo = channelCustomerInfo;
	}
	public EmployeeInfo getEmployeeInfo() {
		return employeeInfo;
	}
	public void setEmployeeInfo(EmployeeInfo employeeInfo) {
		this.employeeInfo = employeeInfo;
	}
	public Agreement getAgreement() {
		return agreement;
	}
	public void setAgreement(Agreement agreement) {
		this.agreement = agreement;
	}
	
}
