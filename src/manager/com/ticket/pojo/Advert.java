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
 * 广告信息
 * @ClassName: Advert   
 * @Description: 广告信息表
 * @author HiSay  
 * @date  2015-10-27 10:31:54
 *
 */
@Entity
@Table(name="ticket_Advert",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class Advert implements Serializable {

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
	 * 广告类别
	 */
	@Column
	private String advertType_id = null;
	
	
	/**
	 * 广告名称
	 */
	@Column
	private String name = null;

	/**
	 * 广告链接
	 */
	@Column
	private String url = null;

	/**
	 * 广告内容
	 */
	@Column(length=10000)
	private String content = null;


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
	
	public String getAdvertType_id() {
		return advertType_id;
	}
	public void setAdvertType_id(String advertTypeId) {
		advertType_id = advertTypeId;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
