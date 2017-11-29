package com.ticket.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import java.util.UUID;
/**
 * 商家活动绑定
 * @ClassName: BusinessEventBind   
 * @Description: 商家活动绑定
 * @author HiSay  
 * @date  2016-12-15 10:18:13
 *
 */
@Entity
@Table(name="ticket_BusinessEventBind",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class BusinessEventBind implements Serializable {

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
	 * 商家
	 */
	@ManyToOne
	private BusinessInfo businessInfo;

	/**
	 * 活动
	 */
	@ManyToOne
	private BusinessEvent businessEvent;


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
	
	public BusinessInfo getBusinessInfo() {
		return businessInfo;
	}
	public void setBusinessInfo(BusinessInfo businessInfo) {
		this.businessInfo = businessInfo;
	}
	public BusinessEvent getBusinessEvent() {
		return businessEvent;
	}
	public void setBusinessEvent(BusinessEvent businessEvent) {
		this.businessEvent = businessEvent;
	}
}
