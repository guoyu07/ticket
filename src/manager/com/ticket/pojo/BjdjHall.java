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
 * 服务厅表
 * @ClassName: BjdjHall   
 * @Description: 服务厅表
 * @author tuyou  
 * @date  2015-10-23 15:24:57
 *
 */
@Entity
@Table(name="ticket_BjdjHall",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class BjdjHall implements Serializable {

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
	 * 厅号
	 */
	@Column(length=255)
	private String number = null;

	/**
	 * 容量（最大人数）
	 */
	@Column(length=255)
	private Integer capacity = null;
	
	/**
	 * 经度
	 */
	@Column(length=255)
	private String longitude = null;

	/**
	 * 纬度
	 */
	@Column(length=255)
	private String latitude = null;

	/**
	 * 描述
	 */
	@Column(length=255)
	private String description = null;

	public BjdjHall() {
		super();
	}
	
	public BjdjHall(String id) {
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
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
