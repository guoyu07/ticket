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
 * 航空公司自助值机位置表
 * @ClassName: FCSelfCheckinPosition   
 * @Description: 航空公司自助值机位置
 * @author HiSay  
 * @date  2016-03-30 17:01:09
 *
 */
@Entity
@Table(name="ticket_FCSelfCheckinPosition",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class FCSelfCheckinPosition implements Serializable {

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
	 * 值机位置名称
	 */
	@Column
	private String name = null;

	/**
	 * 航空公司id
	 */
	@Column
	private String flightCompany_id = null;

	/**
	 * 楼层号
	 */
	@Column
	private String floorNumber = null;

	/**
	 * 经度
	 */
	@Column
	private Double longitude = null;

	/**
	 * 纬度
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
	public String getFlightCompany_id() {
		return flightCompany_id;
	}
	public void setFlightCompany_id(String flightCompany_id) {
		this.flightCompany_id = flightCompany_id;
	}
	public String getFloorNumber() {
		return floorNumber;
	}
	public void setFloorNumber(String floorNumber) {
		this.floorNumber = floorNumber;
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
