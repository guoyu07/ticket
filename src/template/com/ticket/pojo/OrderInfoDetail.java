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
 * 订单详细
 * @package  com.ticket.pojo
 * @file     OrderInfoDetail.java
 * @author   wangjiafeng
 * @date     2015-12-28 上午10:04:42
 * @version  V 1.0
 */
@Entity
@Table(name="ticket_OrderInfoDetail",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class OrderInfoDetail implements Serializable{

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
	 * 关联的订单id
	 */
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private OrderInfo orderInfo;
	/**
	 * 关联的服务码id
	 */
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private BjdjServiceCode bjdjServiceCode;
	/**
	 * 订单状态
	 * 0 正常
	 * 1 已转赠
	 */
	@Column
	private Integer state = 0;
	/**
	 * 订单类型
	 * 0 购买订单
	 * 1 转赠订单
	 */
	@Column
	private Integer orderType = 0;

	
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
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public OrderInfo getOrderInfo() {
		return orderInfo;
	}
	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}
	public BjdjServiceCode getBjdjServiceCode() {
		return bjdjServiceCode;
	}
	public void setBjdjServiceCode(BjdjServiceCode bjdjServiceCode) {
		this.bjdjServiceCode = bjdjServiceCode;
	}
	
}
