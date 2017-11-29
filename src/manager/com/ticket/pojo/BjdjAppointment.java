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

import com.ticket.enumer.BjdjAppointmentType;

/**
 * 便捷登机预约表
 * @ClassName: BjdjAppointment   
 * @Description: jdjAppointment
 * @author HiSay  
 * @date  2015-11-23 22:48:35
 *
 */
@Entity
@Table(name="ticket_BjdjAppointment")
public class BjdjAppointment implements Serializable {

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
	 * 预约方式
	 */
	@Enumerated(EnumType.STRING)
	private BjdjAppointmentType way;

	/**
	 * 预约时间
	 */
	@Column(length=255)
	private Date time;

	/**
	 * 预约使用时间
	 */
	@Column(length=255)
	private Date useTime;

	/**
	 * 服务码ID
	 */
	@ManyToOne
	private BjdjServiceCode serviceCode;

	/**
	 * 航班号
	 */
	@Column(length=255)
	private String flightNo;
	
	/**
	 * 航班是否延迟,1延迟，0未延迟
	 */
	@Column
	private Integer ifNI = 0;
	
	/**
	 * 航班延迟时，是否已发送航班延误信息，1已发，0未发
	 */
	@Column
	private Integer ifSendInfo = 0;
	
	/**
	 * 身份证号
	 */
	private String idCard;
	
	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 备注
	 */
	@Column(length=255)
	private String description;
	
	/**
	 * 电话号码
	 */
	private String mobile;
	
	/**
	 * 电子邮箱
	 */
	private String email;
	
	/**
	 * 行李
	 */
	private String luggage;
	
	/**
	 * 大厅id
	 */
	@ManyToOne
	private BjdjHall hall;
	
	/**
	 * 验证记录
	 */
	@OneToOne(mappedBy="appointment", cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	private BjdjValidation validation;
	
	/**
	 * 线上激活激活的会员
	 */
	@ManyToOne
	private Member member;
	
	/**
	 * 线下预约的管理员
	 */
	@ManyToOne
	private AirportEmployee employee;
	
	public BjdjAppointment() {
		super();
	}
	public BjdjAppointment(String id) {
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
	public Date getUseTime() {
		return useTime;
	}
	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}
	public String getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLuggage() {
		return luggage;
	}
	public void setLuggage(String luggage) {
		this.luggage = luggage;
	}
	public BjdjServiceCode getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(BjdjServiceCode serviceCode) {
		this.serviceCode = serviceCode;
	}
	public BjdjAppointmentType getWay() {
		return way;
	}
	public void setWay(BjdjAppointmentType way) {
		this.way = way;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "BjdjAppointment [description=" + description + ", email="
				+ email + ", flightNo=" + flightNo + ", id=" + id + ", idCard="
				+ idCard + ", luggage=" + luggage + ", mobile=" + mobile
				+ ", name=" + name + ", status=" + status + ", time=" + time
				+ ", useTime=" + useTime + ", way=" + way + "]";
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public AirportEmployee getEmployee() {
		return employee;
	}
	public void setEmployee(AirportEmployee employee) {
		this.employee = employee;
	}
	public Integer getIfNI() {
		return ifNI;
	}
	public void setIfNI(Integer ifNI) {
		this.ifNI = ifNI;
	}
	public Integer getIfSendInfo() {
		return ifSendInfo;
	}
	public void setIfSendInfo(Integer ifSendInfo) {
		this.ifSendInfo = ifSendInfo;
	}
	public BjdjValidation getValidation() {
		return validation;
	}
	public void setValidation(BjdjValidation validation) {
		this.validation = validation;
	}
	public BjdjHall getHall() {
		return hall;
	}
	public void setHall(BjdjHall hall) {
		this.hall = hall;
	}
}
