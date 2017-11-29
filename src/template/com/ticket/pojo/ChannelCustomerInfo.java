package com.ticket.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * 渠道客户信息
 * @ClassName: ChannelCustomerInfo   
 * @Description: 渠道客户信息
 * @author HiSay  
 * @date  2015-11-04 10:39:40
 *
 */
@Entity
@Table(name="ticket_ChannelCustomerInfo",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class ChannelCustomerInfo extends SystemUser implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 渠道分类
	 */
	@JoinColumn(name="channelTypeId")
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	private ChannelType channelType;

	/**
	 * 开户日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date openAccountDate;

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
	 * 开户金额
	 */
	@Column
	private Double openAccountMoney;
	/**
	 * 账户余额
	 */
	@Column
	private Double balance;
	/**
	 * 关联的优惠政策id
	 */
	@JoinColumn(name="favouredPolicyId")
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	private FavouredPolicy favouredPolicy;
	/**
	 * 邮箱
	 */
	@Column
	private String email;
	/**
	 * 意向客户关联的员工id
	 */
	@ManyToOne
	private EmployeeInfo employeeInfo;
	
	/**
	 * 渠道客户关联的员工id
	 */
	@JoinColumn(name="employeeInfo_id2")
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	private EmployeeInfo employeeInfo2;
	
	/**
	 * 员工所属部门
	 */
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private DepartmentInfo department;
	/**
	 * 支付方式
	 */
	@Column
	private String payWay;
	
	/**
	 * 关联的行业id
	 */
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private Industry industry;
	/**
	 * 联系人岗位
	 */
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private ChannelPosition channelPosition;
	/**
	 * 渠道客户状态
	 * 0 意向客户
	 * 1 渠道客户
	 */
	@Column
	private Integer state = 0;
	/**
	 * 关联的合同信息
	 */
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private Agreement agreement;
	/**
	 * 身份证号
	 */
	@Column
	private String idCard;
	/**
	 * 营业执照号码
	 */
	@Column
	private String yyzzNumber;
	
	/**
	 * 客户拥有者
	 */
	@JoinColumn(name="customerOwner")
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	private ChannelCustomerInfo customerOwner;
	
	public Date getOpenAccountDate() {
		return openAccountDate;
	}
	public void setOpenAccountDate(Date openAccountDate) {
		this.openAccountDate = openAccountDate;
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
	public Double getOpenAccountMoney() {
		return openAccountMoney;
	}
	public void setOpenAccountMoney(Double openAccountMoney) {
		this.openAccountMoney = openAccountMoney;
	}
	public String getPayWay() {
		return payWay;
	}
	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Double getBalance() {
		return balance;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getYyzzNumber() {
		return yyzzNumber;
	}
	public void setYyzzNumber(String yyzzNumber) {
		this.yyzzNumber = yyzzNumber;
	}
	public String getName() {
		return super.getName();
	}
	public void setDepartment(DepartmentInfo department) {
		this.department = department;
	}
	public Industry getIndustry() {
		return industry;
	}
	public void setIndustry(Industry industry) {
		this.industry = industry;
	}
	public ChannelPosition getChannelPosition() {
		return channelPosition;
	}
	public void setChannelPosition(ChannelPosition channelPosition) {
		this.channelPosition = channelPosition;
	}
	public Agreement getAgreement() {
		return agreement;
	}
	public void setAgreement(Agreement agreement) {
		this.agreement = agreement;
	}
	public ChannelCustomerInfo getCustomerOwner() {
		return customerOwner;
	}
	public void setCustomerOwner(ChannelCustomerInfo customerOwner) {
		this.customerOwner = customerOwner;
	}
	public FavouredPolicy getFavouredPolicy() {
		return favouredPolicy;
	}
	public void setFavouredPolicy(FavouredPolicy favouredPolicy) {
		this.favouredPolicy = favouredPolicy;
	}
	public EmployeeInfo getEmployeeInfo() {
		return employeeInfo;
	}
	public void setEmployeeInfo(EmployeeInfo employeeInfo) {
		this.employeeInfo = employeeInfo;
	}
	public EmployeeInfo getEmployeeInfo2() {
		return employeeInfo2;
	}
	public void setEmployeeInfo2(EmployeeInfo employeeInfo2) {
		this.employeeInfo2 = employeeInfo2;
	}
	public DepartmentInfo getDepartment() {
		return department;
	}
	public ChannelType getChannelType() {
		return channelType;
	}
	public void setChannelType(ChannelType channelType) {
		this.channelType = channelType;
	}
}
