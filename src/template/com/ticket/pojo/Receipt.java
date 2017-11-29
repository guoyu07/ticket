package com.ticket.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.Date;
import java.util.UUID;
/**
 * 收据发票
 * @ClassName: Receipt   
 * @Description: 收据发票
 * @author HiSay  
 * @date  2015-11-17 17:10:15
 *
 */
@Entity
@Table(name="ticket_Receipt",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class Receipt implements Serializable {

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
	 * 收据编号
	 */
	@Column
	private String receiptNo;

	/**
	 * 收据名称
	 */
	@Column
	private String name;

	/**
	 * 开具时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date issueTime;

	/**
	 * 付款客户
	 */
	@Column
	private String channelCustomerInfoId;

	/**
	 * 开具金额
	 */
	@Column
	private Double amountOfMoney;
	/**
	 * 大写金额
	 */
	@Column
	private String money;
	/**
	 * 申请人
	 */
	@JoinColumn(name="employeeInfoId")
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	private EmployeeInfo employeeInfo;

	/**
	 * 申请类型
	 * 0 预开申请
	 * 1 到账申请
	 */
	@Column
	private Integer type = 0;

	/**
	 * 备注
	 */
	@Column
	private String remarks;
	/**
	 * 审核备注
	 */
	@Column
	private String auditRemarks;
	/**
	 * 收据状态
	 * 0 未提交
	 * 1 已提交,未审核
	 * 2 已提交审核未通过
	 * 3 已审核通过
	 * 4 开据发票或收据
	 */
	@Column
	private Integer state = 0;
	/**
	 * 审核人员
	 */
	@ManyToOne
	private SystemUser systemUser;
	/**
	 * 审核时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date auditTime;
	/**
	 * 关联的预开申请
	 */
	@ManyToOne
	private ApplayClass applyClass;
	
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
	
	public String getReceiptNo() {
		return receiptNo;
	}
	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getIssueTime() {
		return issueTime;
	}
	public void setIssueTime(Date issueTime) {
		this.issueTime = issueTime;
	}
	public String getChannelCustomerInfoId() {
		return channelCustomerInfoId;
	}
	public void setChannelCustomerInfoId(String channelCustomerInfoId) {
		this.channelCustomerInfoId = channelCustomerInfoId;
	}
	public Double getAmountOfMoney() {
		return amountOfMoney;
	}
	public void setAmountOfMoney(Double amountOfMoney) {
		this.amountOfMoney = amountOfMoney;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public void setAuditRemarks(String auditRemarks) {
		this.auditRemarks = auditRemarks;
	}
	public String getAuditRemarks() {
		return auditRemarks;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public EmployeeInfo getEmployeeInfo() {
		return employeeInfo;
	}
	public void setEmployeeInfo(EmployeeInfo employeeInfo) {
		this.employeeInfo = employeeInfo;
	}
	public SystemUser getSystemUser() {
		return systemUser;
	}
	public void setSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
	}
	public ApplayClass getApplyClass() {
		return applyClass;
	}
	public void setApplyClass(ApplayClass applyClass) {
		this.applyClass = applyClass;
	}
	
}
