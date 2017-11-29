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
 * 消息模板
 * @ClassName: MessageTemplate   
 * @Description: 消息模板
 * @author HiSay  
 * @date  2016-08-09 10:50:43
 *
 */
@Entity
@Table(name="ticket_MessageTemplate",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class MessageTemplate implements Serializable {

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
	 * 标题
	 */
	private String title;
	
	/**
	 * 消息内容
	 */
	@Column(length=1024)
	private String content;
	
	/**
	 * 消息内容
	 */
	@Column(length=1024)
	private String url;

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
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
