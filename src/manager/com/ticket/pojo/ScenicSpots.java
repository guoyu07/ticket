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
 * 旅游景点
 * @ClassName: ScenicSpots   
 * @Description: 旅游景点
 * @author HiSay  
 * @date  2016-09-30 09:54:17
 *
 */
@Entity
@Table(name="ticket_ScenicSpots",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class ScenicSpots implements Serializable {

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
	 * 名称
	 */
	@Column
	private String name;

	/**
	 * 景点介绍
	 */
	@Column
	private String introduce;

	/**
	 * 景点详情
	 */
	@Column(length=1000)
	private String detail;


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
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
}
