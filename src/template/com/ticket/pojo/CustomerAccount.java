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
 * 渠道客户账目
 * @package  com.ticket.pojo
 * @file     CustomerAccount.java
 * @author   wangjiafeng
 * @date     2015-12-29 上午09:14:37
 * @version  V 1.0
 */
@Entity
@Table(name="ticket_CustomerAccount",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class CustomerAccount implements Serializable{

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
	 * 关联的客户
	 */
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private ChannelCustomerInfo channelCustomerInfo;
	/**
	 * 金额加减
	 * 0 减
	 * 1 加
	 */
	@Column
	private Integer operation = 1;
	/**
	 * 金额
	 */
	@Column
	private Double money = 0D;
	/**
	 * 赠送金额
	 */
	@Column
	private Double songMoney = 0D;
	/**
	 * 账目描述
	 */
	@Column(length=2000)
	private String remark = null;
	/**
	 * 操作人实体名称
	 */
	@Column
	private String entityName = null;
	/**
	 * 操作人id
	 */
	@Column
	private String entityId = null;
	
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
	public Integer getOperation() {
		return operation;
	}
	public void setOperation(Integer operation) {
		this.operation = operation;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getEntityId() {
		return entityId;
	}
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
	public Double getSongMoney() {
		return songMoney;
	}
	public void setSongMoney(Double songMoney) {
		this.songMoney = songMoney;
	}
	public ChannelCustomerInfo getChannelCustomerInfo() {
		return channelCustomerInfo;
	}
	public void setChannelCustomerInfo(ChannelCustomerInfo channelCustomerInfo) {
		this.channelCustomerInfo = channelCustomerInfo;
	}
	
}
