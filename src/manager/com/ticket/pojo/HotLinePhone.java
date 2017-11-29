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
 * 热线电话
 * @ClassName: HotLinePhone   
 * @Description: 热线电话表
 * @author HiSay  
 * @date  2015-11-17 17:34:17
 *
 */
@Entity
@Table(name="ticket_HotLinePhone",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class HotLinePhone implements Serializable {

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
	 * 热线名称
	 */
	@Column
	private String name = null;

	/**
	 * 热线电话
	 */
	@Column
	private String phone = null;
	
	/**
	 * 热线电话
	 */
	@Column
	private String oneKeyCall = null;


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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getOneKeyCall() {
		return oneKeyCall;
	}
	public void setOneKeyCall(String oneKeyCall) {
		this.oneKeyCall = oneKeyCall;
	}
}
