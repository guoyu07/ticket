package com.ticket.pojo;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 * 会员关注航班
 * @ClassName: MemberFocusFlight   
 * @Description: 会员关注航班表
 * @author HiSay  
 * @date  2015-12-04 16:03:42
 */
@Entity
@Table(name="ticket_MemberFocusFlight",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class MemberFocusFlight implements Serializable {

	protected static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	@Id
	protected String id = UUID.randomUUID().toString();
	/**
	 * 实体状态
	 */
	@Embedded
	protected CommonEntity status = new CommonEntity();
	
	/**
	 * 会员id
	 */
	@Column
	protected String member_id;

	/**
	 * 航班号
	 */
	@Column
	protected String flightNumber;

	/**
	 * 航班日期
	 */
	@Column
	protected String flightDate;

	/**
	 * 会员角色
	 */
	@Column
	protected String memberRole;

	/**
	 * 航班状态
	 */
	@Column
	protected String flightState;

	/**
	 * 是否携带行李
	 */
	@Column
	protected Integer ifTakeLuggage;

	/**
	 * 携带特殊人员
	 */
	@Column
	protected String takePerson;

	/**
	 * 乘机人数
	 */
	@Column
	protected Integer personCount;

	/**
	 * 是否已设置过旅程
	 */
	@Column
	protected Integer ifSet;
	
	/**
	 * 电话
	 */
	@Column
	protected String mobile;
	
	/**
	 * 座位号
	 */
	@Column
	protected String seatNo;
	
	/**
	 * 电子客票号
	 */
	@Column 
	protected String ticketNo;
	
	/**
	 * 值机标识
	 */
	@Column 
	protected String couponId;
	
	/**
	 * 身份证号 
	 */
	protected String idcard;
	
	/**
	 * 经停航班标识
	 */
	@Column
	private String stopover = null;

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
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getFlightDate() {
		return flightDate;
	}
	public void setFlightDate(String flightDate) {
		this.flightDate = flightDate;
	}
	public String getMemberRole() {
		return memberRole;
	}
	public void setMemberRole(String memberRole) {
		this.memberRole = memberRole;
	}
	public String getFlightState() {
		return flightState;
	}
	public void setFlightState(String flightState) {
		this.flightState = flightState;
	}
	public Integer getIfTakeLuggage() {
		return ifTakeLuggage;
	}
	public void setIfTakeLuggage(Integer ifTakeLuggage) {
		this.ifTakeLuggage = ifTakeLuggage;
	}
	public String getTakePerson() {
		return takePerson;
	}
	public void setTakePerson(String takePerson) {
		this.takePerson = takePerson;
	}
	public Integer getPersonCount() {
		return personCount;
	}
	public void setPersonCount(Integer personCount) {
		this.personCount = personCount;
	}
	public Integer getIfSet() {
		return ifSet;
	}
	public void setIfSet(Integer ifSet) {
		this.ifSet = ifSet;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}
	public String getTicketNo() {
		return ticketNo;
	}
	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}
	public String getCouponId() {
		return couponId;
	}
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getStopover() {
		return stopover;
	}
	public void setStopover(String stopover) {
		this.stopover = stopover;
	}
}
