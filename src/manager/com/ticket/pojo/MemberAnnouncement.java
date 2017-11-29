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
 * 通知中心信息阅读
 * @ClassName: MemberAnnouncement   
 * @Description: 通知中心信息阅读
 * @author HiSay  
 * @date  2016-09-22 16:00:51
 *
 */
@Entity
@Table(name="ticket_MemberAnnouncement",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class MemberAnnouncement implements Serializable {

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
	 * 用户游客id
	 */
	@Column
	private String member_id;

	/**
	 * 已阅读新闻信息
	 */
	@ManyToOne
	private News news;


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
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
	}
}
