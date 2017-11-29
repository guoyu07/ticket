package com.ticket.pojo;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 * 评论模板
 * @ClassName: EvaluationTemplate   
 * @Description: 评论模板
 * @author HiSay  
 * @date  2016-02-03 18:17:36
 *
 */
@Entity
@Table(name="ticket_EvaluationTemplate",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class EvaluationTemplate implements Serializable {

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
	 * 标题
	 */
	@Column(length=255)
	private String title = null;

	/**
	 * 内容
	 */
	@Column(length=255)
	private String content = null;

	/**
	 * 此条模板所属用户
	 */
	@ManyToOne
	private AirportEmployee user = null;


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
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public AirportEmployee getUser() {
		return user;
	}
	public void setUser(AirportEmployee user) {
		this.user = user;
	}
}
