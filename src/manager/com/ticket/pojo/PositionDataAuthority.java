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
 * 岗位权限
 * @ClassName: PositionDataAuthority   
 * @Description: 岗位权限
 * @author HiSay  
 * @date  2016-05-25 15:20:45
 *
 */
@Entity
@Table(name="ticket_PositionDataAuthority",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class PositionDataAuthority implements Serializable {

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
	 * 岗位id
	 */
	@Column(length=255)
	private String position_id = null;

	/**
	 * 数据权限id
	 */
	@Column(length=255)
	private String dataAuthoritys_id = null;
	
	/**
	 * 模块id
	 */
	@Column
	private String systemModuleId = null;


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
	
	public String getPosition_id() {
		return position_id;
	}
	public void setPosition_id(String position_id) {
		this.position_id = position_id;
	}
	public String getDataAuthoritys_id() {
		return dataAuthoritys_id;
	}
	public void setDataAuthoritys_id(String dataAuthoritys_id) {
		this.dataAuthoritys_id = dataAuthoritys_id;
	}
	public String getSystemModuleId() {
		return systemModuleId;
	}
	public void setSystemModuleId(String systemModuleId) {
		this.systemModuleId = systemModuleId;
	}
}
