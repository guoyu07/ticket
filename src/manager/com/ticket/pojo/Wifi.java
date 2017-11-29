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
 * ifi设备
 * @ClassName: Wifi   
 * @Description: ifi设备
 * @author HiSay  
 * @date  2016-08-09 10:49:51
 *
 */
@Entity
@Table(name="ticket_Wifi",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class Wifi implements Serializable {

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
	 * 设备id
	 */
	@Column(length=255)
	private String sid;
	
	/**
	 * 区域名称
	 */
	@ManyToOne
	private WifiArea area;

	/**
	 * 描述
	 */
	@Column(length=2048)
	private String remark;


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
	
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public WifiArea getArea() {
		return area;
	}
	public void setArea(WifiArea area) {
		this.area = area;
	}
}
