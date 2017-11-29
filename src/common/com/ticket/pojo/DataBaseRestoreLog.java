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
 * 数据库还原日志
 * @ClassName: DataBaseRestoreLog   
 * @Description: 记录数据库的还原日志
 * @author HiSay  
 * @date  2013-09-21 11:55:48
 *
 */
@Entity
@Table(name="ticket_DatabaseRestoreLog",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class DataBaseRestoreLog extends BasePojo implements Serializable {

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
	 * 管理员ID
	 */
	@Column
	private String systemUser_id = null;

	/**
	 * 还原数据库说明
	 */
	@Column
	private String content = null;

	/**
	 * 还原名称
	 */
	@Column
	private String name = null;


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
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSystemUser_id() {
		return systemUser_id;
	}
	public void setSystemUser_id(String systemUserId) {
		systemUser_id = systemUserId;
	}
}
