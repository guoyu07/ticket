package com.ticket.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ticket.enumer.BjdjOrderType;

/**
 * 便捷登机验证表
 * @ClassName: BjdjValidation   
 * @Description: jdjValidation
 * @author tuyou  
 * @date  2015-11-23 22:52:42
 *
 */
@Entity
@Table(name="ticket_BjdjValidation",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class BjdjValidation implements Serializable {

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
	 * 所属的预约
	 */
	@ManyToOne
	private BjdjAppointment appointment;
	
	/**
	 * 基于此验证的分单
	 */
	@OneToOne(mappedBy="validation", cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	private BjdjDispatch dispatch;
	
	/**
	 * 验证时间
	 */
	@Column(length=255)
	private Date time = null;

	/**
	 * 机场员工ID
	 */
	@ManyToOne
	private AirportEmployee employee = null;

	/**
	 * 验证是否通过
	 */
	@Column(length=255)
	private boolean passed;
	
	/**
	 * 验证类型
	 */
	@Enumerated(EnumType.STRING)
	private BjdjOrderType type;
	
//	/**
//	 * 服务码
//	 */
//	@ManyToOne
//	private BjdjServiceCode serviceCode;

	public BjdjValidation() {
		super();
	}
	public BjdjValidation(String id) {
		super();
		this.id = id;
	}
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
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public BjdjAppointment getAppointment() {
		return appointment;
	}
	public void setAppointment(BjdjAppointment appointment) {
		this.appointment = appointment;
	}
	public AirportEmployee getEmployee() {
		return employee;
	}
	public void setEmployee(AirportEmployee employee) {
		this.employee = employee;
	}
	public boolean isPassed() {
		return passed;
	}
	public void setPassed(boolean passed) {
		this.passed = passed;
	}
	public BjdjDispatch getDispatch() {
		return dispatch;
	}
	public void setDispatch(BjdjDispatch dispatch) {
		this.dispatch = dispatch;
	}
	public BjdjOrderType getType() {
		return type;
	}
	public void setType(BjdjOrderType type) {
		this.type = type;
	}
//	public BjdjServiceCode getServiceCode() {
//		return serviceCode;
//	}
//	public void setServiceCode(BjdjServiceCode serviceCode) {
//		this.serviceCode = serviceCode;
//	}
	
}
