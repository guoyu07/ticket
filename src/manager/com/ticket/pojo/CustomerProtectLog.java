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
 * 客保日志
 * @ClassName: CustomerProtectLog   
 * @Description: 客保日志
 * @author HiSay  
 * @date  2016-01-02 10:21:06
 *
 */
@Entity
@Table(name="ticket_CustomerProtectLog",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class CustomerProtectLog implements Serializable {

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
	 * 保护客户
	 */
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private ChannelCustomerInfo channelCustomerInfo;

	/**
	 * 销售人员
	 */
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private EmployeeInfo employeeInfo;
	
	/**
	 * 部门id
	 */
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private DepartmentInfo department;

	/**
	 * 保护开始时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;
	
	/**
	 * 3天的期限
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date threeTime= null;

	/**
	 * 保护结束时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;
	/**
	 * 是否过期
	 * 0 未过期
	 * 1 已过期
	 */
	@Column
	private Integer isExpire = 0;


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
	public Integer getIsExpire() {
		return isExpire;
	}
	public void setIsExpire(Integer isExpire) {
		this.isExpire = isExpire;
	}
	public Date getThreeTime() {
		return threeTime;
	}
	public void setThreeTime(Date threeTime) {
		this.threeTime = threeTime;
	}
	public ChannelCustomerInfo getChannelCustomerInfo() {
		return channelCustomerInfo;
	}
	public void setChannelCustomerInfo(ChannelCustomerInfo channelCustomerInfo) {
		this.channelCustomerInfo = channelCustomerInfo;
	}
	public DepartmentInfo getDepartment() {
		return department;
	}
	public void setDepartment(DepartmentInfo department) {
		this.department = department;
	}
	public EmployeeInfo getEmployeeInfo() {
		return employeeInfo;
	}
	public void setEmployeeInfo(EmployeeInfo employeeInfo) {
		this.employeeInfo = employeeInfo;
	}
	
}
