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
import javax.persistence.UniqueConstraint;
/**
 * 销售任务
 * @ClassName: SaleTask   
 * @Description: 销售任务
 * @author HiSay  
 * @date  2016-05-05 11:18:16
 *
 */
@Entity
@Table(name="ticket_SaleTask",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class SaleTask implements Serializable {

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
	 * 起始时间
	 */
	@Column(length=255)
	private Date startTime;

	/**
	 * 结束时间
	 */
	@Column(length=255)
	private Date endTime;

	/**
	 * 所属部门
	 */
	@ManyToOne
	private DepartmentInfo department;

	/**
	 * 所属员工
	 */
	@ManyToOne
	private EmployeeInfo employee;

	/**
	 * 签约数
	 */
	@Column(length=255)
	private int signCount;

	/**
	 * 充值金额
	 */
	@Column(length=255)
	private double recharge;

	/**
	 * 电话拜访量
	 */
	@Column(length=255)
	private int phoneCount;

	/**
	 * 外出拜访量
	 */
	@Column(length=255)
	private int visitCount;
	
	/**
	 * 备注
	 */
	@Column
	private String remark;
	
	/**
	 * 上级任务
	 */
	@ManyToOne
	private SaleTask parent;
	
	/**
	 * 是否发布
	 */
	private boolean published;

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
	
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public DepartmentInfo getDepartment() {
		return department;
	}
	public void setDepartment(DepartmentInfo department) {
		this.department = department;
	}
	public EmployeeInfo getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeeInfo employee) {
		this.employee = employee;
	}
	public int getSignCount() {
		return signCount;
	}
	public void setSignCount(int signCount) {
		this.signCount = signCount;
	}
	public double getRecharge() {
		return recharge;
	}
	public void setRecharge(double recharge) {
		this.recharge = recharge;
	}
	public int getPhoneCount() {
		return phoneCount;
	}
	public void setPhoneCount(int phoneCount) {
		this.phoneCount = phoneCount;
	}
	public int getVisitCount() {
		return visitCount;
	}
	public void setVisitCount(int visitCount) {
		this.visitCount = visitCount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public SaleTask getParent() {
		return parent;
	}
	public void setParent(SaleTask parent) {
		this.parent = parent;
	}
	public boolean isPublished() {
		return published;
	}
	public void setPublished(boolean published) {
		this.published = published;
	}
}
