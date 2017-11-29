package com.ticket.pojo;
import java.util.Date;
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

import java.util.UUID;
/**
 * 客户合同
 * @ClassName: Agreement   
 * @Description: 客户合同
 * @author HiSay  
 * @date  2015-11-04 14:49:37
 *
 */
@Entity
@Table(name="ticket_Agreement",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class Agreement implements Serializable {

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
	 * 合同名称
	 */
	@Column
	private String name;
	
	/**
	 * 申请类别
	 */
	@JoinColumn(name="applayClassId")
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	private ApplayClass applayClass;

	/**
	 * 最终所属员工
	 */
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private EmployeeInfo employeeInfo;
	/**
	 * 合同申请员工
	 */
	@JoinColumn(name="employeeInfo_id2")
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	private EmployeeInfo employeeInfo2;
	/**
	 * 申请时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date applyDate;

	/**
	 * 可申请数
	 */
	@Column
	private Integer canApplayCount;

	/**
	 * 当前已领取数
	 */
	@Column
	private Integer gotCount;

	/**
	 * 未批复原因
	 */
	@Column
	private String chargeStatus;

	/**
	 * 领用日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date receiveDate;

	/**
	 * 签订日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date signingDate;

	/**
	 * 收款日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date receivablesDate;

	/**
	 * 交回日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date returnDate;

	/**
	 * 审核日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date auditDate;
	
	/**
	 * 审核账号
	 */
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private EmployeeInfo auditEmployee;

	/**
	 * 开户日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date openAccountDate;

	/**
	 * 上线日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date onlineDate;

	/**
	 * 签订金额
	 */
	@Column
	private Double firstRenewMoney;

	/**
	 * 首次续费
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date firstRenewDate;

	/**
	 * 上次清查日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastCheckDate;

	/**
	 * 关联的合同
	 */
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private ChannelCustomerInfo channelCustomerInfo;
	/**
	 * 金额
	 */
	@Column
	private Double amountOfMoney;
	/**
	 * 合同内容
	 */
	@Column
	private String content;
	
	/**
	 * 合同状态
	 * 0 未审批
	 * 1 审批通过
	 * 2 审批未通过
	 * 3 签回合同
	 * 4 合同已发放
	 * 5 已归档
	 */
	@Column
	private Integer agreementState = 0;
	@Column
	private String  contacts ;
	@Column
	private String  phone;
	@Column
	private String email ;
	@Column
	private String payWay;
	/**
	 * 是否有效
	 * 0 无效,过期
	 * 1 有效
	 */
	@Column
	private Integer effective = 1;
	/**
	 * 申请理由
	 */
	@Column(length=8000)
	private String remark;
	/**
	 * 二次审核状态
	 * 1 通过
	 * 0 未通过
	 */
	@Column
	private Integer secondAuditState = 0;
	/**
	 * 二次审核理由
	 */
	@Column(length=8000)
	private String secondAuditRemark;
	/**
	 * 签回备注
	 */
	@Column(length=8000)
	private String qhRemark;
	
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
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public Integer getCanApplayCount() {
		return canApplayCount;
	}
	public void setCanApplayCount(Integer canApplayCount) {
		this.canApplayCount = canApplayCount;
	}
	public Integer getGotCount() {
		return gotCount;
	}
	public void setGotCount(Integer gotCount) {
		this.gotCount = gotCount;
	}
	public String getChargeStatus() {
		return chargeStatus;
	}
	public void setChargeStatus(String chargeStatus) {
		this.chargeStatus = chargeStatus;
	}
	public Date getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}
	public Date getSigningDate() {
		return signingDate;
	}
	public void setSigningDate(Date signingDate) {
		this.signingDate = signingDate;
	}
	public Date getReceivablesDate() {
		return receivablesDate;
	}
	public void setReceivablesDate(Date receivablesDate) {
		this.receivablesDate = receivablesDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public Date getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	public Date getOpenAccountDate() {
		return openAccountDate;
	}
	public void setOpenAccountDate(Date openAccountDate) {
		this.openAccountDate = openAccountDate;
	}
	public Date getOnlineDate() {
		return onlineDate;
	}
	public void setOnlineDate(Date onlineDate) {
		this.onlineDate = onlineDate;
	}
	public Date getFirstRenewDate() {
		return firstRenewDate;
	}
	public void setFirstRenewDate(Date firstRenewDate) {
		this.firstRenewDate = firstRenewDate;
	}
	public Date getLastCheckDate() {
		return lastCheckDate;
	}
	public void setLastCheckDate(Date lastCheckDate) {
		this.lastCheckDate = lastCheckDate;
	}
	public Double getAmountOfMoney() {
		return amountOfMoney;
	}
	public void setAmountOfMoney(Double amountOfMoney) {
		this.amountOfMoney = amountOfMoney;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContent() {
		return content;
	}
	public void setAgreementState(Integer agreementState) {
		this.agreementState = agreementState;
	}
	public Integer getAgreementState() {
		return agreementState;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getContacts() {
		return contacts;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPhone() {
		return phone;
	}
	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
	public String getPayWay() {
		return payWay;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getEffective() {
		return effective;
	}
	public void setEffective(Integer effective) {
		this.effective = effective;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getSecondAuditState() {
		return secondAuditState;
	}
	public void setSecondAuditState(Integer secondAuditState) {
		this.secondAuditState = secondAuditState;
	}
	public String getSecondAuditRemark() {
		return secondAuditRemark;
	}
	public void setSecondAuditRemark(String secondAuditRemark) {
		this.secondAuditRemark = secondAuditRemark;
	}
	public String getQhRemark() {
		return qhRemark;
	}
	public void setQhRemark(String qhRemark) {
		this.qhRemark = qhRemark;
	}
	public EmployeeInfo getAuditEmployee() {
		return auditEmployee;
	}
	public void setAuditEmployee(EmployeeInfo auditEmployee) {
		this.auditEmployee = auditEmployee;
	}
	public Double getFirstRenewMoney() {
		return firstRenewMoney;
	}
	public void setFirstRenewMoney(Double firstRenewMoney) {
		this.firstRenewMoney = firstRenewMoney;
	}
	public EmployeeInfo getEmployeeInfo() {
		return employeeInfo;
	}
	public void setEmployeeInfo(EmployeeInfo employeeInfo) {
		this.employeeInfo = employeeInfo;
	}
	public ChannelCustomerInfo getChannelCustomerInfo() {
		return channelCustomerInfo;
	}
	public void setChannelCustomerInfo(ChannelCustomerInfo channelCustomerInfo) {
		this.channelCustomerInfo = channelCustomerInfo;
	}
	public EmployeeInfo getEmployeeInfo2() {
		return employeeInfo2;
	}
	public void setEmployeeInfo2(EmployeeInfo employeeInfo2) {
		this.employeeInfo2 = employeeInfo2;
	}
	public ApplayClass getApplayClass() {
		return applayClass;
	}
	public void setApplayClass(ApplayClass applayClass) {
		this.applayClass = applayClass;
	}
}
