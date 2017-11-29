package com.ticket.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import java.util.Date;
import java.util.UUID;
/**
 * 服务订单
 * @ClassName: ServiceBook   
 * @Description: 服务订单表
 * @author HiSay  
 * @date  2015-10-15 12:56:11
 *
 */
@Entity
@Table(name="ticket_ServiceBook",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class ServiceBook implements Serializable {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */ 
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
	 * 会员id
	 */
	@Column
	private String member_id = null;

	/**
	 * 身份证号
	 */
	@Column
	private String idCard = null;

	/**
	 * 联系电话
	 */
	@Column
	private String phone = null;

	/**
	 * 航班号
	 */
	@Column
	private String flightNumber = null;

	/**
	 * 预订数量
	 */
	@Column
	private Integer bookAmount = null;

	/**
	 * 使用时间
	 */
	@Column
	private Date useTime = null;

	/**
	 * 服务码
	 */
	@Column
	private String serviceKey = null;

	/**
	 * 付款状态
	 */
	@Column
	private Integer payStatus = null;

	/**
	 * 付款时间
	 */
	@Column
	private Date payTime = null;

	/**
	 * 付款方式
	 */
	@Column
	private Integer payWay = null;

	/**
	 * 微信交易号
	 */
	@Column
	private String weChatTransld = null;

	/**
	 * 支付宝名称
	 */
	@Column
	private String alipayName = null;

	/**
	 * 支付宝交易号
	 */
	@Column
	private String alipayTransld = null;

	/**
	 * 网银名称
	 */
	@Column
	private String bankName = null;


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
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public Integer getBookAmount() {
		return bookAmount;
	}
	public void setBookAmount(Integer bookAmount) {
		this.bookAmount = bookAmount;
	}
	public Date getUseTime() {
		return useTime;
	}
	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}
	public String getServiceKey() {
		return serviceKey;
	}
	public void setServiceKey(String serviceKey) {
		this.serviceKey = serviceKey;
	}
	public Integer getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public Integer getPayWay() {
		return payWay;
	}
	public void setPayWay(Integer payWay) {
		this.payWay = payWay;
	}
	public String getWeChatTransld() {
		return weChatTransld;
	}
	public void setWeChatTransld(String weChatTransld) {
		this.weChatTransld = weChatTransld;
	}
	public String getAlipayName() {
		return alipayName;
	}
	public void setAlipayName(String alipayName) {
		this.alipayName = alipayName;
	}
	public String getAlipayTransld() {
		return alipayTransld;
	}
	public void setAlipayTransld(String alipayTransld) {
		this.alipayTransld = alipayTransld;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
}
