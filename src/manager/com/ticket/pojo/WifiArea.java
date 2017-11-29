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
 * ifi区域
 * @ClassName: WifiArea   
 * @Description: ifiArea
 * @author HiSay  
 * @date  2016-09-22 10:17:00
 *
 */
@Entity
@Table(name="ticket_WifiArea",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class WifiArea implements Serializable {

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
	 * 区域名称
	 */
	@Column(length=255)
	private String name;
	
	/**
	 * 时间间隔（分钟）
	 */
	@Column
	private int interv;

	/**
	 * 备注
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
	public int getInterv() {
		return interv;
	}
	public void setInterv(int interv) {
		this.interv = interv;
	}
}
