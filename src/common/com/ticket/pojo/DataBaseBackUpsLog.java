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
 * 数据库备份日志
 * @ClassName: DataBaseBackUpsLog   
 * @Description: 记录数据库的备份日志
 * @author HiSay  
 * @date  2013-09-21 11:47:54
 *
 */
@Entity
@Table(name="ticket_DatabaseBackupsLog",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class DataBaseBackUpsLog extends BasePojo implements Serializable {

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
	 * 备份路径
	 */
	@Column
	private String sqlPath = null;

	/**
	 * 备份文件名称
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
	public String getSqlPath() {
		return sqlPath;
	}
	public void setSqlPath(String sqlPath) {
		this.sqlPath = sqlPath;
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
