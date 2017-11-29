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
 * 充值记录
 * @ClassName: RechargeRecord   
 * @Description: 充值记录
 * @author HiSay  
 * @date  2015-11-13 16:45:25
 *
 */
@Entity
@Table(name="ticket_RechargeRecord",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class RechargeRecord implements Serializable {

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
	 * 记录编号
	 */
	@Column
	private String recordNo;

	/**
	 * 充值金额
	 */
	@Column
	private Double amountOfMoney;
	/**
	 * 赠送金额
	 */
	@Column
	private Double songMoney;
	/**
	 * 充值人员
	 */
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private SystemUser systemUser;
	/**
	 * 充值客户
	 */
	@JoinColumn(name="channelCustomerInfoId")
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	private ChannelCustomerInfo channelCustomerInfo;

	/**
	 * 支付时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date payTime;

	/**
	 * 支付方式
	 */
	@Column
	private String payWay;

	/**
	 * 回单号
	 */
	@Column
	private String receiptNo;
	/**
	 * 开发票状态
	 * 0 不开发票
	 * 1 已处理
	 * 2 申请开发票
	 */
	@Column
	private Integer state = 0;


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
	
	public String getRecordNo() {
		return recordNo;
	}
	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
	}
	public Double getAmountOfMoney() {
		return amountOfMoney;
	}
	public void setAmountOfMoney(Double amountOfMoney) {
		this.amountOfMoney = amountOfMoney;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public String getPayWay() {
		return payWay;
	}
	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
	public String getReceiptNo() {
		return receiptNo;
	}
	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}
	public Double getSongMoney() {
		return songMoney;
	}
	public void setSongMoney(Double songMoney) {
		this.songMoney = songMoney;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public SystemUser getSystemUser() {
		return systemUser;
	}
	public void setSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
	}
	public ChannelCustomerInfo getChannelCustomerInfo() {
		return channelCustomerInfo;
	}
	public void setChannelCustomerInfo(ChannelCustomerInfo channelCustomerInfo) {
		this.channelCustomerInfo = channelCustomerInfo;
	}
	
}
