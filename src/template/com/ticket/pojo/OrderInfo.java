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
 * 订单数据
 * @ClassName: OrderInfo   
 * @Description: 订单数据
 * @author HiSay  
 * @date  2015-11-04 18:04:35
 *
 */
@Entity
@Table(name="ticket_OrderInfo",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class OrderInfo implements Serializable {

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
	 * 订单号
	 */
	private String orderId;


	/**
	 * 商品售价
	 */
	@Column
	private Double goodsPrice;

	/**
	 * 购买数量
	 */
	@Column
	private Integer buyCount;

	/**
	 * 支付方式
	 */
	@Column
	private String payWay;

	/**
	 * 支付时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date payTime;

	/**
	 * 关联的客户id
	 */
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private ChannelCustomerInfo channelCustomerInfo;

	/**
	 * 1 已支付
	 * 0 未支付
	 */
	@Column
	private Integer payStatus = 1;

	/**
	 * 实付金额
	 */
	@Column
	private Double paidAmount;

	/**
	 * 优惠金额
	 */
	@Column
	private Double discountAmount;

	/**
	 * 消耗积分
	 */
	@Column
	private Integer useIntegral;
	/**
	 * 订单类型
	 * 0 购买订单
	 * 1 转赠订单
	 * 2 分销订单
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
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public Double getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public Integer getBuyCount() {
		return buyCount;
	}
	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}
	public String getPayWay() {
		return payWay;
	}
	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public Integer getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	public Double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}
	public Double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}
	public Integer getUseIntegral() {
		return useIntegral;
	}
	public void setUseIntegral(Integer useIntegral) {
		this.useIntegral = useIntegral;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public ChannelCustomerInfo getChannelCustomerInfo() {
		return channelCustomerInfo;
	}
	public void setChannelCustomerInfo(ChannelCustomerInfo channelCustomerInfo) {
		this.channelCustomerInfo = channelCustomerInfo;
	}
	
}
