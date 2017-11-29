package com.ticket.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 * 服务码激活排队表
 * @author tuyou  
 * @date  2015-10-23 15:16:42
 */
@Entity
@Table(name="ticket_BjdjActivateQueue",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class BjdjActivateQueue implements Serializable {

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
	 * 用户
	 */
	private String mobile;
	
	/**
	 * 渠道类型
	 */
	@ManyToOne(fetch=FetchType.EAGER)
	private SystemDictionary channel;
	
	/**
	 * 航班日期
	 */
	private Date flightDate;

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public SystemDictionary getChannel() {
		return channel;
	}

	public void setChannel(SystemDictionary channel) {
		this.channel = channel;
	}

	public Date getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(Date flightDate) {
		this.flightDate = flightDate;
	}
	

}
