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
 * 系统管理员登陆日志
 * @ClassName: SystemUserLoginLog   
 * @Description: 系统管理员登陆日志
 * @author HiSay  
 * @date  2015-01-03 10:42:16
 *
 */
@Entity
@Table(name="ticket_SystemUserLoginLog",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class SystemUserLoginLog extends BasePojo implements Serializable {

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
	 * 操作IP地址
	 */
	@Column(length=50)
	private String ip = null;

	/**
	 * 所属管理员
	 */
	@Column(length=50)
	private String systemUser_id = null;


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
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getSystemUser_id() {
		return systemUser_id;
	}
	public void setSystemUser_id(String systemUser_id) {
		this.systemUser_id = systemUser_id;
	}
	@Override
	public String getIds() {
		return this.getId();
	}
	@Override
	public void setIds(String ids) {
		super.setIds(ids);
	}
	public String getFlag() {
		return this.getStatus().getVersionFlag();
	}
	public Long getViewUrl() {
		return this.getStatus().getVisitUrl();
	}
}
