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
 * 搜索页面
 * @ClassName: Page   
 * @Description: 搜索页面
 * @author HiSay  
 * @date  2016-09-30 15:12:25
 *
 */
@Entity
@Table(name="ticket_Page",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class Page implements Serializable {

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
	 * 页面名称
	 */
	@Column(length=64)
	private String name;

	/**
	 * h5链接地址
	 */
	@Column(length=64)
	private String url;

	/**
	 * pc端链接地址
	 */
	@Column(length=64)
	private String pcUrl;

	/**
	 * 描述
	 */
	@Column(length=2048)
	private String remark;


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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPcUrl() {
		return pcUrl;
	}
	public void setPcUrl(String pcUrl) {
		this.pcUrl = pcUrl;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
