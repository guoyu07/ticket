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
 * 渠道联系人岗位
 * @ClassName: ChannelPosition   
 * @Description: 渠道联系人岗位
 * @author HiSay  
 * @date  2016-01-11 11:35:13
 *
 */
@Entity
@Table(name="ticket_ChannelPosition",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class ChannelPosition implements Serializable {

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
	 * 岗位名称
	 */
	@Column
	private String name = null;

	/**
	 * 岗位职责
	 */
	@Column
	private String duty = null;

	/**
	 * 岗位描述
	 */
	@Column
	private String remark = null;


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
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
