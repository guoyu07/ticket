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
 * 服务厅登机口关联
 * @ClassName: BjdjHall   
 * @Description: 服务厅登机口关联表
 * @author tuyou  
 * @date  2016-06-24 15:24:57
 *
 */
@Entity
@Table(name="ticket_BjdjHallGate",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class BjdjHallGate implements Serializable {

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
	 * 所属大厅
	 */
	@ManyToOne
	private BjdjHall hall;
	
	/**
	 * 哪个登机口
	 */
	@ManyToOne
	private InfoPosition gate;
	
	/**
	 * 登机口距离大厅的位置(单位：米)
	 */
	private int meter;

	/**
	 * 描述
	 */
	@Column(length=255)
	private String description;

	public BjdjHallGate() {
		super();
	}
	
	public BjdjHallGate(String id) {
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public BjdjHall getHall() {
		return hall;
	}

	public void setHall(BjdjHall hall) {
		this.hall = hall;
	}

	public InfoPosition getGate() {
		return gate;
	}

	public void setGate(InfoPosition gate) {
		this.gate = gate;
	}

	public int getMeter() {
		return meter;
	}

	public void setMeter(int meter) {
		this.meter = meter;
	}
}
