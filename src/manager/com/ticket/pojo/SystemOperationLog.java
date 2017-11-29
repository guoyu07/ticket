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
 * 后台管理员操作日志
 * @ClassName: SystemOperationLog   
 * @Description: 后台管理员操作日志
 * @author HiSay  
 * @date  2016-03-08 21:01:21
 *
 */
@Entity
@Table(name="ticket_SystemOperationLog",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class SystemOperationLog implements Serializable {

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
	 * 管理员名称
	 */
	@Column(length=255)
	private String logName = null;

	/**
	 * 操作内容
	 */
	@Column(length=255)
	private String logContent = null;

	/**
	 * 操作时间
	 */
	@Column(length=255)
	private String logTime = null;

	/**
	 * 操作IP
	 */
	@Column(length=255)
	private String logIp = null;


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
	
	public String getLogName() {
		return logName;
	}
	public void setLogName(String logName) {
		this.logName = logName;
	}
	public String getLogContent() {
		return logContent;
	}
	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}
	public String getLogTime() {
		return logTime;
	}
	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}
	public String getLogIp() {
		return logIp;
	}
	public void setLogIp(String logIp) {
		this.logIp = logIp;
	}
}
