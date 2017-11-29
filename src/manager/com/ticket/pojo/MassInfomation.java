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
 * 群发信息
 * @ClassName: MassInfomation   
 * @Description: 群发信息表
 * @author tuyou  
 * @date  2016-02-03 20:46:58
 *
 */
@Entity
@Table(name="ticket_MassInfomation",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class MassInfomation implements Serializable {

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
	 * 信息标题
	 */
	@Column
	private String title = null;

	/**
	 * 内容
	 */
	@Column
	private String content = null;

	/**
	 * 短信链接
	 */
	@Column
	private String url = null;
	
	/**
	 * 是否发送短信
	 */
	private boolean sendSms;
	
	/**
	 * 提醒时间
	 */
	@Column
	private Date reminderTime = null;

	/**
	 * 群发对象特性
	 */
	@Column
	private String massObjCharacter = null;
	
	/**
	 * 航班号
	 */
	private String flightNumber;
	
	/**
	 * 航班日期
	 */
	private Date flightDate;
	
	/**
	 * 类型
	 * 1 航班延误
	 */
	private int type;

	public MassInfomation(){}
	
	public MassInfomation(String id){
		
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
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMassObjCharacter() {
		return massObjCharacter;
	}
	public void setMassObjCharacter(String massObjCharacter) {
		this.massObjCharacter = massObjCharacter;
	}

	public boolean isSendSms() {
		return sendSms;
	}

	public void setSendSms(boolean sendSms) {
		this.sendSms = sendSms;
	}

	public Date getReminderTime() {
		return reminderTime;
	}

	public void setReminderTime(Date reminderTime) {
		this.reminderTime = reminderTime;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public Date getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(Date flightDate) {
		this.flightDate = flightDate;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
