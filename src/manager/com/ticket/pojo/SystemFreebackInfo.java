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
 * 系统反馈信息
 * @ClassName: SystemFreebackInfo   
 * @Description: 系统反馈信息表
 * @author HiSay  
 * @date  2016-07-28 13:50:41
 *
 */
@Entity
@Table(name="ticket_SystemFreebackInfo",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class SystemFreebackInfo implements Serializable {

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
	 * 姓名或账号
	 */
	@Column
	private String name;

	/**
	 * 手机号
	 */
	@Column
	private String phone;

	/**
	 * 反馈网址
	 */
	@Column
	private String url;

	/**
	 * 反馈信息
	 */
	@Column
	private String content;


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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
