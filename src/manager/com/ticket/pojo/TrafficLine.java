package com.ticket.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 * 交通路线
 * @ClassName: TrafficLine   
 * @Description: 交通路线表
 * @author HiSay  
 * @date  2015-11-19 09:55:18
 *
 */
@Entity
@Table(name="ticket_TrafficLine",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class TrafficLine implements Serializable {

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
	 * 路线类别id
	 */
	@Column
	private String trafficType_id = null;

	/**
	 * 路线名称
	 */
	@Column
	private String name = null;
	
	/**
	 * 起始站
	 */
	@Column
	private String startStation = null;

	/**
	 * 终到站
	 */
	@Column
	private String endStation = null;

	/**
	 * 发车时间
	 */
	@Column
	private Date startTime = null;

	/**
	 * 末班车时间
	 */
	@Column
	private Date endTime = null;

	/**
	 * 发班频率
	 */
	@Column
	private String departureRate = null;

	/**
	 * 车辆数
	 */
	@Column
	private Integer carCount = null;

	/**
	 * 车辆型号
	 */
	@Column
	private String carModel = null;

	/**
	 * 座位数
	 */
	@Column
	private Integer seatCount = null;

	/**
	 * 票价
	 */
	@Column
	private Double price = null;
	
	/**
	 * 备注
	 */
	@Column
	private String remark = null;


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
	
	public String getTrafficType_id() {
		return trafficType_id;
	}
	public void setTrafficType_id(String trafficType_id) {
		this.trafficType_id = trafficType_id;
	}
	public String getStartStation() {
		return startStation;
	}
	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}
	public String getEndStation() {
		return endStation;
	}
	public void setEndStation(String endStation) {
		this.endStation = endStation;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getDepartureRate() {
		return departureRate;
	}
	public void setDepartureRate(String departureRate) {
		this.departureRate = departureRate;
	}
	public Integer getCarCount() {
		return carCount;
	}
	public void setCarCount(Integer carCount) {
		this.carCount = carCount;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	public Integer getSeatCount() {
		return seatCount;
	}
	public void setSeatCount(Integer seatCount) {
		this.seatCount = seatCount;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
