package com.ticket.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;
/**
 * 新闻信息
 * @ClassName: News   
 * @Description: 新闻信息表
 * @author HiSay  
 * @date  2015-10-13 17:14:59
 *
 */
@Entity
@Table(name="ticket_News",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class News implements Serializable {

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
	 * 新闻类别id
	 */
	@Column
	private String newsClass_id = null;

	/**
	 * 信息编辑
	 */
	@Column
	private String author = null;

	/**
	 * 信息标题
	 */
	@Column
	private String title = null;

	/**
	 * 信息副标题
	 */
	@Column
	private String subTitle = null;

	/**
	 * 信息简介
	 */
	@Column
	private String introduce = null;

	/**
	 * 新闻内容
	 */
	@Column
	private String content = null;

	/**
	 * pc端显示的内容
	 */
	@Column
	private String pcContent = null;
	
	/**
	 * 管理员id
	 */
	@Column
	private String systemUser_id = null;

	/**
	 * 详细页面跳转模板ID
	 */
	@Column
	private String viewPageRedirectTemplate_id = null;

	/**
	 * 关联新闻栏目别名
	 */
	@Column
	private String useNewsClassName = null;
	/**
	 * 对应的客服电话
	 */
	@Column
	private String mobile = null;
	/**
	 * 最后一次修改时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdateTime = null;
	/**
	 * SEO关键词
	 */
	@Lob
	@Type(type="org.hibernate.type.StringClobType") 
	private String seoKeyword = null;
	/**
	 * SEO关键词描述
	 */
	@Lob
	@Type(type="org.hibernate.type.StringClobType") 
	private String seoDescript = null;
	/**
	 * SEO的链接
	 */
	@Column
	private String seoUrl = null;
	@Column
	private String infoPosition_id = null;
	
	@ManyToOne
	private ScenicSpots scenicSpots = null;

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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSystemUser_id() {
		return systemUser_id;
	}
	public void setSystemUser_id(String systemUser_id) {
		this.systemUser_id = systemUser_id;
	}
	public String getViewPageRedirectTemplate_id() {
		return viewPageRedirectTemplate_id;
	}
	public void setViewPageRedirectTemplate_id(String viewPageRedirectTemplateId) {
		viewPageRedirectTemplate_id = viewPageRedirectTemplateId;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getUseNewsClassName() {
		return useNewsClassName;
	}
	public void setUseNewsClassName(String useNewsClassName) {
		this.useNewsClassName = useNewsClassName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public String getSeoKeyword() {
		return seoKeyword;
	}
	public void setSeoKeyword(String seoKeyword) {
		this.seoKeyword = seoKeyword;
	}
	public String getSeoDescript() {
		return seoDescript;
	}
	public void setSeoDescript(String seoDescript) {
		this.seoDescript = seoDescript;
	}
	public String getSeoUrl() {
		return seoUrl;
	}
	public void setSeoUrl(String seoUrl) {
		this.seoUrl = seoUrl;
	}
	public String getPcContent() {
		return pcContent;
	}
	public void setPcContent(String pcContent) {
		this.pcContent = pcContent;
	}
	public String getInfoPosition_id() {
		return infoPosition_id;
	}
	public void setInfoPosition_id(String infoPosition_id) {
		this.infoPosition_id = infoPosition_id;
	}
	public ScenicSpots getScenicSpots() {
		return scenicSpots;
	}
	public void setScenicSpots(ScenicSpots scenicSpots) {
		this.scenicSpots = scenicSpots;
	}
}
