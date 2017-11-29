package com.ticket.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 安检数据实体类
 * @author zzf
 */

@Entity
@Table(name="ticket_SecurityData")
public class SecurityData implements Serializable{

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
	 * 电子客票号(也是唯一的)
	 */
	private String ticketNo;
	
	/**
	 * 航班时间
	 */
	private Date airPortTime;
	
	/**
	 * 主航班标识
	 */
	private String mainMarke;
	
	/**
	 * 航班实时状态
	 */
	private String airPortNowStatic;
	
	/**
	 * 计划到达时间
	 */
	private Date arrayTime;
	
	/**
	 * 实际起飞时间
	 */
	private Date realityTime;
	
	/**
	 * 预计起飞时间
	 */
	private Date planTime;
	
	/**
	 * 登机状态
	 */
	private String boardingStatus;
	
	/**
	 * 值机柜台
	 */
	private String checkInCounter;
	
	/**
	 * 登机时间
	 */
	private Date boardingData;
	
	/**
	 * 过检通道号
	 */
	private String channelNumber;
	
	/**
	 * 航班号
	 */
	private String fltNo;
	
	/**
	 * 座位号
	 */
	private String seatNo;
	
	/**
	 * 航空公司二字码
	 */
	private String twoCode;
	
	/**
	 * 舱位等级
	 */
	private String spaceLevel;
	
	/**
	 * 出发地三字码
	 */
	private String deptCode;
	
	/**
	 * 目的地三字码
	 */
	private String arrayCode;
	
	/**
	 * 登机口
	 */
	private String boardingGate;
	
	/**
	 * 登机口状态
	 */
	private String boardingGateStatu;
	
	/**
	 * 计划起飞时间
	 */
	private Date planTateOffTime;
	
	/**
	 * 安检状态
	 */
	private String securityCheckState;
	
	/**
	 * 姓名
	 */
	private String name;
	
	/**
	 * 身份证号
	 */
	private String IDCard;
	
	/**
	 * 安检口编号
	 */
	private String checkGateNo;
	
	/**
	 * 安检口状态
	 */
	private String checkGateStatu;
	
	/**
	 * 安检口性质
	 */
	private String checkGateNature;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public Date getAirPortTime() {
		return airPortTime;
	}

	public void setAirPortTime(Date airPortTime) {
		this.airPortTime = airPortTime;
	}

	public String getMainMarke() {
		return mainMarke;
	}

	public void setMainMarke(String mainMarke) {
		this.mainMarke = mainMarke;
	}

	public String getAirPortNowStatic() {
		return airPortNowStatic;
	}

	public void setAirPortNowStatic(String airPortNowStatic) {
		this.airPortNowStatic = airPortNowStatic;
	}

	public Date getArrayTime() {
		return arrayTime;
	}

	public void setArrayTime(Date arrayTime) {
		this.arrayTime = arrayTime;
	}

	public Date getRealityTime() {
		return realityTime;
	}

	public void setRealityTime(Date realityTime) {
		this.realityTime = realityTime;
	}

	public Date getPlanTime() {
		return planTime;
	}

	public void setPlanTime(Date planTime) {
		this.planTime = planTime;
	}

	public String getBoardingStatus() {
		return boardingStatus;
	}

	public void setBoardingStatus(String boardingStatus) {
		this.boardingStatus = boardingStatus;
	}

	public String getCheckInCounter() {
		return checkInCounter;
	}

	public void setCheckInCounter(String checkInCounter) {
		this.checkInCounter = checkInCounter;
	}

	public Date getBoardingData() {
		return boardingData;
	}

	public void setBoardingData(Date boardingData) {
		this.boardingData = boardingData;
	}

	public String getChannelNumber() {
		return channelNumber;
	}

	public void setChannelNumber(String channelNumber) {
		this.channelNumber = channelNumber;
	}

	public String getFltNo() {
		return fltNo;
	}

	public void setFltNo(String fltNo) {
		this.fltNo = fltNo;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public String getTwoCode() {
		return twoCode;
	}

	public void setTwoCode(String twoCode) {
		this.twoCode = twoCode;
	}

	public String getSpaceLevel() {
		return spaceLevel;
	}

	public void setSpaceLevel(String spaceLevel) {
		this.spaceLevel = spaceLevel;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getArrayCode() {
		return arrayCode;
	}

	public void setArrayCode(String arrayCode) {
		this.arrayCode = arrayCode;
	}

	public String getBoardingGate() {
		return boardingGate;
	}

	public void setBoardingGate(String boardingGate) {
		this.boardingGate = boardingGate;
	}

	public String getBoardingGateStatu() {
		return boardingGateStatu;
	}

	public void setBoardingGateStatu(String boardingGateStatu) {
		this.boardingGateStatu = boardingGateStatu;
	}

	public Date getPlanTateOffTime() {
		return planTateOffTime;
	}

	public void setPlanTateOffTime(Date planTateOffTime) {
		this.planTateOffTime = planTateOffTime;
	}

	public String getSecurityCheckState() {
		return securityCheckState;
	}

	public void setSecurityCheckState(String securityCheckState) {
		this.securityCheckState = securityCheckState;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIDCard() {
		return IDCard;
	}

	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}

	public String getCheckGateNo() {
		return checkGateNo;
	}

	public void setCheckGateNo(String checkGateNo) {
		this.checkGateNo = checkGateNo;
	}

	public String getCheckGateStatu() {
		return checkGateStatu;
	}

	public void setCheckGateStatu(String checkGateStatu) {
		this.checkGateStatu = checkGateStatu;
	}

	public String getCheckGateNature() {
		return checkGateNature;
	}

	public void setCheckGateNature(String checkGateNature) {
		this.checkGateNature = checkGateNature;
	}

	public CommonEntity getStatus() {
		return status;
	}

	public void setStatus(CommonEntity status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SecurityData [id=" + id + ", ticketNo=" + ticketNo
				+ ", airPortTime=" + airPortTime + ", mainMarke=" + mainMarke
				+ ", airPortNowStatic=" + airPortNowStatic + ", arrayTime="
				+ arrayTime + ", realityTime=" + realityTime + ", planTime="
				+ planTime + ", boardingStatus=" + boardingStatus
				+ ", checkInCounter=" + checkInCounter + ", boardingData="
				+ boardingData + ", channelNumber=" + channelNumber
				+ ", fltNo=" + fltNo + ", seatNo=" + seatNo + ", twoCode="
				+ twoCode + ", spaceLevel=" + spaceLevel + ", deptCode="
				+ deptCode + ", arrayCode=" + arrayCode + ", boardingGate="
				+ boardingGate + ", boardingGateStatu=" + boardingGateStatu
				+ ", planTateOffTime=" + planTateOffTime
				+ ", securityCheckState=" + securityCheckState + ", name="
				+ name + ", IDCard=" + IDCard + ", checkGateNo=" + checkGateNo
				+ ", checkGateStatu=" + checkGateStatu + ", checkGateNature="
				+ checkGateNature + "]";
	}
}
