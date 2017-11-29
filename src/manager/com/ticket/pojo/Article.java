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
 * 文章信息
 * @ClassName: Article   
 * @Description: 文章信息表
 * @author HiSay  
 * @date  2015-10-13 10:05:09
 *
 */
@Entity
@Table(name="ticket_Article",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class Article implements Serializable {

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
	 * 所属栏目
	 */
	@Column(length=50)
	private String newsClass_id = null;

	/**
	 * 文章标题
	 */
	@Column
	private String title = null;

	/**
	 * 文章内容
	 */
	@Column
	private String content = null;

	/**
	 * 文章简介
	 */
	@Column
	private String introduce = null;

	/**
	 * 文章缩略图
	 */
	@Column
	private String thumbnail = null;

	/**
	 * 文章来源
	 */
	@Column
	private String source = null;

	/**
	 * 文章编辑
	 */
	@Column
	private String author = null;

	/**
	 * 详细页面跳转模板ID
	 */
	@Column
	private String viewPageRedirectTemplate_id = null;

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
	
	public String getNewsClass_id() {
		return newsClass_id;
	}
	public void setNewsClass_id(String newsClass_id) {
		this.newsClass_id = newsClass_id;
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
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getViewPageRedirectTemplate_id() {
		return viewPageRedirectTemplate_id;
	}
	public void setViewPageRedirectTemplate_id(String viewPageRedirectTemplateId) {
		viewPageRedirectTemplate_id = viewPageRedirectTemplateId;
	}
}
