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
 * 航空公司信息
 * @ClassName: FlightCompany   
 * @Description: 航空公司信息表
 * @author HiSay  
 * @date  2015-11-03 13:39:40
 *
 */
@Entity
@Table(name="ticket_FlightCompany",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class FlightCompany implements Serializable {

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
	 * 公司名称
	 */
	@Column
	private String name = null;
	
	/**
	 * 公司二字码
	 */
	@Column
	private String twoCode = null;
	
	/**
	 * 公司三字码
	 */
	@Column
	private String threeCode = null;

	/**
	 * 公司电话
	 */
	@Column
	private String phone = null;

	/**
	 * 官网
	 */
	@Column
	private String theOfficialWebsite = null;

	/**
	 * 散客柜台
	 */
	@Column
	private String customerCounter = null;

	/**
	 * 团队柜台
	 */
	@Column
	private String teamCounter = null;

	/**
	 * 头等舱柜台
	 */
	@Column
	private String firstClassCounter = null;

	/**
	 * 应急柜台
	 */
	@Column
	private String emergencyCounter = null;
	/**
	 * 人工值机经度
	 */
	@Column
	private Double longitude = null;
	/**
	 * 人工值机纬度
	 */
	@Column
	private Double latitude = null;

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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTwoCode() {
		return twoCode;
	}
	public void setTwoCode(String twoCode) {
		this.twoCode = twoCode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTheOfficialWebsite() {
		return theOfficialWebsite;
	}
	public void setTheOfficialWebsite(String theOfficialWebsite) {
		this.theOfficialWebsite = theOfficialWebsite;
	}
	public String getCustomerCounter() {
		return customerCounter;
	}
	public void setCustomerCounter(String customerCounter) {
		this.customerCounter = customerCounter;
	}
	public String getTeamCounter() {
		return teamCounter;
	}
	public void setTeamCounter(String teamCounter) {
		this.teamCounter = teamCounter;
	}
	public String getFirstClassCounter() {
		return firstClassCounter;
	}
	public void setFirstClassCounter(String firstClassCounter) {
		this.firstClassCounter = firstClassCounter;
	}
	public String getEmergencyCounter() {
		return emergencyCounter;
	}
	public void setEmergencyCounter(String emergencyCounter) {
		this.emergencyCounter = emergencyCounter;
	}
	public String getThreeCode() {
		return threeCode;
	}
	public void setThreeCode(String threeCode) {
		this.threeCode = threeCode;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
}
