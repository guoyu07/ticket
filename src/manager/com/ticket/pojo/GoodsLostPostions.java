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
 * 物品遗失位置
 * @ClassName: GoodsLostPostions   
 * @Description: 物品遗失位置表
 * @author HiSay  
 * @date  2015-11-23 16:44:38
 *
 */
@Entity
@Table(name="ticket_GoodsLostPostions",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class GoodsLostPostions implements Serializable {

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
	 * 位置名称
	 */
	@Column
	private String name = null;

	/**
	 * 位置描述
	 */
	@Column
	private String description = null;

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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
