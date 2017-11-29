package com.ticket.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.UUID;
/**
 * 电瓶车信息
 * @ClassName: Electrombile   
 * @Description: 电瓶车信息表
 * @author HiSay  
 * @date  2015-10-15 10:55:04
 *
 */
@Entity
@Table(name="ticket_Electrombile",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class Electromobile implements Serializable {

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
	 * 电瓶车编号
	 */
	@Column
	private String electrombileId = null;

	/**
	 * 是否正在使用
	 */
	@Column
	private Integer ifUsing = null;

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
	
	public String getElectrombileId() {
		return electrombileId;
	}
	public void setElectrombileId(String electrombileId) {
		this.electrombileId = electrombileId;
	}
	public Integer getIfUsing() {
		return ifUsing;
	}
	public void setIfUsing(Integer ifUsing) {
		this.ifUsing = ifUsing;
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
