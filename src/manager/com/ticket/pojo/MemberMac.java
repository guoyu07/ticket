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
 * 用户的mac地址
 * @ClassName: MemberMac   
 * @Description: 用户的mac地址
 * @author HiSay  
 * @date  2016-08-09 10:51:20
 *
 */
@Entity
@Table(name="ticket_MemberMac",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class MemberMac implements Serializable {

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
	@Column(length=64)
	private String phone;

	/**
	 * mac地址
	 */
	@Column(length=64)
	private String mac;


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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
}
