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
 * 新闻评论
 * @ClassName: NewsComment   
 * @Description: 新闻评论表
 * @author HiSay  
 * @date  2015-10-13 17:16:37
 *
 */
@Entity
@Table(name="ticket_NewsComment",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class NewsComment implements Serializable {

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
	 * 新闻id
	 */
	@Column
	private String news_id = null;

	/**
	 * 会员id
	 */
	@Column
	private String member_id = null;

	/**
	 * 会员IP
	 */
	@Column
	private String memberIp = null;

	/**
	 * 评几颗星
	 */
	@Column
	private Integer star = null;

	/**
	 * 评论内容
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
	
	public String getNews_id() {
		return news_id;
	}
	public void setNews_id(String news_id) {
		this.news_id = news_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMemberIp() {
		return memberIp;
	}
	public void setMemberIp(String memberIp) {
		this.memberIp = memberIp;
	}
	public Integer getStar() {
		return star;
	}
	public void setStar(Integer star) {
		this.star = star;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
