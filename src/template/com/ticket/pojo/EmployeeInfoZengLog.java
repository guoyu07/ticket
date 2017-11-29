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
 * 员工转移服务码主表
 * @ClassName: EmployeeInfoZengLog   
 * @Description: 员工转移服务码主表
 * @author HiSay  
 * @date  2016-01-18 17:18:08
 *
 */
@Entity
@Table(name="ticket_EmployeeInfoZengLog",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class EmployeeInfoZengLog implements Serializable {

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
	 * 关联的员工
	 */
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private EmployeeInfo employeeInfo;
	/**
	 * 转移目标渠道客户
	 */
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private ChannelCustomerInfo channelCustomerInfo;
	/**
	 * 转移源渠道客户
	 */
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private ChannelCustomerInfo sourceChannelCustomerInfo;
	/**
	 * 转移数量
	 */
	@Column
	private Integer count = 0;
	/**
	 * 赠送理由
	 */
	@Column(length=8000)
	private String remark;
	
	/**
	 * 分销至指定的人
	 */
	private String name;
	
	/**
	 * 分销至的手机号码
	 */
	private String mobile;

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
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	public ChannelCustomerInfo getSourceChannelCustomerInfo() {
		return sourceChannelCustomerInfo;
	}
	public void setSourceChannelCustomerInfo(
			ChannelCustomerInfo sourceChannelCustomerInfo) {
		this.sourceChannelCustomerInfo = sourceChannelCustomerInfo;
	}
	
}
