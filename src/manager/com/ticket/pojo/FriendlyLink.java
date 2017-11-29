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
 * 友情链接
 * @ClassName: FriendlyLink   
 * @Description: 友情链接表
 * @author HiSay  
 * @date  2015-11-20 10:43:11
 *
 */
@Entity
@Table(name="ticket_FriendlyLink",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class FriendlyLink implements Serializable {

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
	 * 商家id
	 */
	@Column
	private String businessInfo_id = null;

	/**
	 * 链接名称
	 */
	@Column
	private String name = null;

	/**
	 * 链接地址
	 */
	@Column
	private String url = null;


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
	
	public String getBusinessInfo_id() {
		return businessInfo_id;
	}
	public void setBusinessInfo_id(String businessInfo_id) {
		this.businessInfo_id = businessInfo_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
