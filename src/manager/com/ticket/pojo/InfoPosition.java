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
 * 信息定位
 * @ClassName: InfoPosition   
 * @Description: 信息定位表
 * @author HiSay  
 * @date  2015-10-20 18:13:14
 *
 */
@Entity
@Table(name="ticket_InfoPosition",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class InfoPosition implements Serializable {

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
	 * 新闻id
	 */
	@Column
	private String news_id = null;
	
	/**
	 * 位置名称
	 */
	@Column
	private String name = null;
	
	/**
	 * 位置别名
	 */
	@Column
	private String positionAlias = null;

	/**
	 * 经度
	 */
	@Column
	private String longitude = null;

	/**
	 * 纬度
	 */
	@Column
	private String latitude = null;

	/**
	 * 服务电话
	 */
	@Column
	private String mobile = null;
	
	/**
	 * 链接
	 */
	@Column
	private String url = null;
	
	/**
	 * 楼层号
	 */
	@Column
	private String floorNumber = null;
	/**
	 * 多点位的不同点位的名称
	 */
	@Column
	private String className;
	

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
	
	public String getNews_id() {
		return news_id;
	}
	public void setNews_id(String news_id) {
		this.news_id = news_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPositionAlias() {
		return positionAlias;
	}
	public void setPositionAlias(String positionAlias) {
		this.positionAlias = positionAlias;
	}
	public String getFloorNumber() {
		return floorNumber;
	}
	public void setFloorNumber(String floorNumber) {
		this.floorNumber = floorNumber;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
