package com.ticket.pojo;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 * 便捷登机评论表
 * @ClassName: BjdjComment   
 * @Description: 便捷登机评论表
 * @author HiSay  
 * @date  2015-10-23 15:24:09
 *
 */
@Entity
@Table(name="ticket_BjdjComment",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class BjdjComment {

	protected static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	@Id
	protected String id = UUID.randomUUID().toString();
	
	/**
	 * 实体状态
	 */
	@Embedded
	protected CommonEntity status = new CommonEntity();
	
	/**
	 * 评论用户
	 */
	@ManyToOne
	protected Member member;
	
	/**
	 * 评价自定义项
	 */
	@OneToMany(mappedBy="comment", cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	protected List<BjdjCommentItem> items;
	
	/**
	 * 评论内容
	 */
	@Column(length=1024)
	protected String content;
	
	/**
	 * 是否显示备注名
	 */
	protected boolean showName;
	
	/**
	 * 图片数组
	 */
	@Column(length=1024)
	protected String images;

	/**
	 * 评论时所在的IP地址
	 */
	@Column(length=255)
	protected String ip;

	/**
	 * 回复内容
	 */
	protected String feedback;
	
	/**
	 * 回复时间
	 */
	protected Date feedbackTime;
	
	/**
	 * 回复用户
	 */
	@ManyToOne
	protected SystemUser feedback_user;
	
	/**
	 * 备注
	 */
	protected String remark;
	
	/**
	 * 服务码ID
	 */
	@ManyToOne
	protected BjdjServiceCode serviceCode;
	
	/**
	 * 评价等级
	 */
	private Integer star;
	
	/**
	 * 原因
	 */
	private String reason;
	
	public BjdjComment() {
		super();
	}
	public BjdjComment(String id) {
		super();
		this.id = id;
	}
	public Integer getStar() {
		return star;
	}
	public void setStar(Integer star) {
		this.star = star;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public BjdjServiceCode getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(BjdjServiceCode serviceCode) {
		this.serviceCode = serviceCode;
	}
	public List<BjdjCommentItem> getItems() {
		return items;
	}
	public void setItems(List<BjdjCommentItem> items) {
		this.items = items;
	}
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
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isShowName() {
		return showName;
	}
	public void setShowName(boolean showName) {
		this.showName = showName;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public Date getFeedbackTime() {
		return feedbackTime;
	}
	public void setFeedbackTime(Date feedbackTime) {
		this.feedbackTime = feedbackTime;
	}
	public SystemUser getFeedback_user() {
		return feedback_user;
	}
	public void setFeedback_user(SystemUser feedback_user) {
		this.feedback_user = feedback_user;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
