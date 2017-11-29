package com.ticket.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ticket.enumer.FreezeStatus;

import java.util.Date;
import java.util.UUID;
/**
 * 商家活动
 * @ClassName: BusinessEvent   
 * @Description: 商家活动
 * @author HiSay  
 * @date  2016-12-14 10:20:43
 *
 */
@Entity
@Table(name="ticket_BusinessEvent",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class BusinessEvent implements Serializable {

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
	 * 商家活动名称
	 */
	@Column
	private String name;

	/**
	 * 活动内容
	 */
	@Column
	private String content;
	
	/**
	 * 活动开始时间
	 */
	@Column
	private Date startTime;
	
	/**
	 * 活动结束时间
	 */
	@Column
	private Date endTime;
	
	/**
	 * 冻结状态
	 */
	@Column
	private FreezeStatus freezeStatus = FreezeStatus.init;


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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public FreezeStatus getFreezeStatus() {
		return freezeStatus;
	}
	public void setFreezeStatus(FreezeStatus freezeStatus) {
		this.freezeStatus = freezeStatus;
	}
}
