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
 * 系统代码升级日志
 * @ClassName: SystemUpdateLog   
 * @Description: 记录每次升级的内容和版本
 * @author HiSay  
 * @date  2013-09-21 09:16:57
 *
 */
@Entity
@Table(name="ticket_SystemUpdateLog",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class SystemUpdateLog extends BasePojo implements Serializable {

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
	 * 老版本名称
	 */
	@Column(length=20)
	private String oldVersion = null;

	/**
	 * 新版本名称
	 */
	@Column(length=20)
	private String newVersion = null;

	/**
	 * 升级内容
	 */
	@Column(length=8000)
	private String updateContent = null;


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
	
	public String getOldVersion() {
		return oldVersion;
	}
	public void setOldVersion(String oldVersion) {
		this.oldVersion = oldVersion;
	}
	public String getNewVersion() {
		return newVersion;
	}
	public void setNewVersion(String newVersion) {
		this.newVersion = newVersion;
	}
	public String getUpdateContent() {
		return updateContent;
	}
	public void setUpdateContent(String updateContent) {
		this.updateContent = updateContent;
	}
}
