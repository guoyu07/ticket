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
 * 值机信息表
 * @ClassName: CheckinInfo   
 * @Description: 值机信息表
 * @author HiSay  
 * @date  2016-02-24 16:09:34
 *
 */
@Entity
@Table(name="ticket_CheckinInfo",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class CheckinInfo implements Serializable {

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
	@ManyToOne
	private Member member = null;
	
	@ManyToOne
	private MemberQQ memberQQ = null;
	
	@ManyToOne
	private MemberWeiBo memberWeiBo = null;
	
	@ManyToOne
	private MemberWeixin memberWeixin = null;

	/**
	 * 航班编号
	 */
	@Column
	private String flightNumber = null;

	/**
	 * 航班日期
	 */
	@Column
	private String flightDate = null;
	
	/**
	 * 起飞城市三字码
	 */
	@Column
	private String deptAirportCode;
	
	/**
	 * 到达城市三字码
	 */
	@Column
	private String destAirportCode;

	/**
	 * 联系电话
	 */
	@Column
	private String mobile = null;

	/**
	 * 电子客票号
	 */
	@Column
	private String ticketNo = null;

	/**
	 * 座位号
	 */
	@Column
	private String seatNo = null;

	/**
	 * 值机标识
	 */
	@Column
	private String couponId = null;
	
	/**
	 * 是否通过分享
	 */
	@Column
	private String byShare;
	
	/**
	 * 分享者电话
	 */
	@Column
	private String sendMobile;
	
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
	
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTicketNo() {
		return ticketNo;
	}
	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}
	public String getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}
	public String getCouponId() {
		return couponId;
	}
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	public String getDeptAirportCode() {
		return deptAirportCode;
	}
	public void setDeptAirportCode(String deptAirportCode) {
		this.deptAirportCode = deptAirportCode;
	}
	public String getDestAirportCode() {
		return destAirportCode;
	}
	public void setDestAirportCode(String destAirportCode) {
		this.destAirportCode = destAirportCode;
	}
	public String getByShare() {
		return byShare;
	}
	public void setByShare(String byShare) {
		this.byShare = byShare;
	}
	public String getSendMobile() {
		return sendMobile;
	}
	public void setSendMobile(String sendMobile) {
		this.sendMobile = sendMobile;
	}
	public MemberQQ getMemberQQ() {
		return memberQQ;
	}
	public void setMemberQQ(MemberQQ memberQQ) {
		this.memberQQ = memberQQ;
	}
	public MemberWeiBo getMemberWeiBo() {
		return memberWeiBo;
	}
	public void setMemberWeiBo(MemberWeiBo memberWeiBo) {
		this.memberWeiBo = memberWeiBo;
	}
	public MemberWeixin getMemberWeixin() {
		return memberWeixin;
	}
	public void setMemberWeixin(MemberWeixin memberWeixin) {
		this.memberWeixin = memberWeixin;
	}
}
