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
 * 反馈与回复
 * @ClassName: FeedbackReply   
 * @Description: 反馈与回复
 * @author HiSay  
 * @date  2016-08-15 15:11:31
 *
 */
@Entity
@Table(name="ticket_FeedbackReply",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class FeedbackReply implements Serializable {

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
	 * 所属反馈问题
	 */
	@ManyToOne
	private Feedback feekBack;

	/**
	 * 反馈人员
	 */
	@ManyToOne
	private Member member;

	/**
	 * 问题描述
	 */
	@Column
	private String description;

	/**
	 * 客服人员
	 */
	@ManyToOne
	private SystemUser systemUser;

	/**
	 * 回复内容
	 */
	@Column
	private String replyContent;
	
	/**
	 * 追加反馈图片
	 */
	@Column
	private String images;
	
	/**
	 * 回复是否已读
	 */
	@Column
	private boolean rend;


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
	
	public Feedback getFeekBack() {
		return feekBack;
	}
	public void setFeekBack(Feedback feekBack) {
		this.feekBack = feekBack;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public SystemUser getSystemUser() {
		return systemUser;
	}
	public void setSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public boolean isRend() {
		return rend;
	}
	public void setRend(boolean rend) {
		this.rend = rend;
	}
}
