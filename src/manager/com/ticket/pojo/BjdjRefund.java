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
 * 退款记录
 * @ClassName: BjdjRefund   
 * @Description: 退款记录
 * @author HiSay  
 * @date  2016-11-25 10:59:56
 *
 */
@Entity
@Table(name="ticket_BjdjRefund",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class BjdjRefund implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	@Id
	private String id = UUID.randomUUID().toString().replaceAll("-", "");
	/**
	 * 实体状态
	 */
	@Embedded
	private CommonEntity status = new CommonEntity();
	
	/**
	 * 退款订单
	 */
	@ManyToOne
	private BjdjOrder order;

	/**
	 * 是否允许退款
	 */
	@Column(length=64)
	private Boolean allow;
	
	/**
	 * 是否成功
	 */
	private Boolean success;
	
	/**
	 * 冻结
	 */
	private boolean freeze;

	/**
	 * 管理员备注
	 */
	@Column(length=1024)
	private String remark;

	/**
	 * 退款原因说明
	 */
	@Column(length=1024)
	private String reason;

	/**
	 * 操作时间
	 */
	@Column(length=64)
	private Date time;

	/**
	 * 
	 * @return
	 */
	@Column(length=1024)
	private String responseXML;
	
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
	
	public BjdjOrder getOrder() {
		return order;
	}
	public void setOrder(BjdjOrder order) {
		this.order = order;
	}
	public Boolean getAllow() {
		return allow;
	}
	public void setAllow(Boolean allow) {
		this.allow = allow;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getResponseXML() {
		return responseXML;
	}
	public void setResponseXML(String responseXML) {
		this.responseXML = responseXML;
	}
	public boolean isFreeze() {
		return freeze;
	}
	public void setFreeze(boolean freeze) {
		this.freeze = freeze;
	}
}
