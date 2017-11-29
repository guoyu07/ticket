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
/**
 * 服务码日志表
 * @ClassName: BjdjServiceCodeLog   
 * @Description: 服务码日志表
 * @author HiSay  
 * @date  2015-10-23 15:17:18
 */
@Entity
@Table(name="ticket_BjdjServiceCodeOperation",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class BjdjServiceCodeOperation implements Serializable {

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
	 * 服务码id
	 */
	@ManyToOne
	private BjdjServiceCode serviceCode;
	
	/**
	 * 操作名称（字典）
	 */
	@ManyToOne
	private SystemDictionary operation;
	
	/**
	 * 操作对象
	 */
	@ManyToOne
	private BjdjAppointment appointment;

	/**
	 * 操作用户
	 */
	@ManyToOne
	private Member fromMember;

	/**
	 * 操作指定用户
	 */
	@ManyToOne
	private Member toMember;
	
	/**
	 * 表示系统管理员的操作
	 */
	@ManyToOne
	private SystemUser systemUser;
	
	/**
	 * 关联的渠道客户id
	 */
	@Column
	private String channelCustomerInfo_id;
	/**
	 * 关联的订单id
	 */
	@Column
	private String orderInfo_id;
	/**
	 * 关联的订单详细id
	 */
	@Column
	private String orderInfoDetail_id;
	
	/**
	 * 备注信息
	 */
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
	public void setServiceCode(BjdjServiceCode serviceCode) {
		this.serviceCode = serviceCode;
	}
	public BjdjServiceCode getServiceCode() {
		return serviceCode;
	}
	public Member getFromMember() {
		return fromMember;
	}
	public void setFromMember(Member fromMember) {
		this.fromMember = fromMember;
	}
	public Member getToMember() {
		return toMember;
	}
	public void setToMember(Member toMember) {
		this.toMember = toMember;
	}
	public SystemDictionary getOperation() {
		return operation;
	}
	public void setOperation(SystemDictionary operation) {
		this.operation = operation;
	}
	public void setOrderInfo_id(String orderInfo_id) {
		this.orderInfo_id = orderInfo_id;
	}
	public String getOrderInfo_id() {
		return orderInfo_id;
	}
	public String getOrderInfoDetail_id() {
		return orderInfoDetail_id;
	}
	public void setOrderInfoDetail_id(String orderInfoDetailId) {
		orderInfoDetail_id = orderInfoDetailId;
	}
	public String getChannelCustomerInfo_id() {
		return channelCustomerInfo_id;
	}
	public void setChannelCustomerInfo_id(String channelCustomerInfoId) {
		channelCustomerInfo_id = channelCustomerInfoId;
	}
	public SystemUser getSystemUser() {
		return systemUser;
	}
	public void setSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
	}
	public BjdjAppointment getAppointment() {
		return appointment;
	}
	public void setAppointment(BjdjAppointment appointment) {
		this.appointment = appointment;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
