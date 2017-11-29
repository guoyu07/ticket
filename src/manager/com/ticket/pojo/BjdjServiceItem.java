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
 * 便捷登机服务项
 * @ClassName: BjdjServiceItem   
 * @Description: 便捷登机服务项
 * @author HiSay  
 * @date  2016-06-30 17:48:04
 *
 */
@Entity
@Table(name="ticket_BjdjServiceItem",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class BjdjServiceItem implements Serializable {

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
	 * 服务项名称
	 */
	@Column(length=255)
	private String name;

	/**
	 * 服务项描述
	 */
	@Column(length=255)
	private String description;


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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
