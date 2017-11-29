package com.ticket.pojo;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @Description：评价系统的的打分项
 * @author：涂有
 * @date 2015年11月25日 上午9:38:09
 */
@Entity
@Table(name="ticket_EvaluationItem")
public class EvaluationItem {

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
	 * 评价等级
	 */
	private Integer star;
	
	/**
	 * 评论对象
	 */
	@ManyToOne(cascade={CascadeType.ALL})
	private Evaluation comment;
	
	/**
	 * 评价配置对象
	 */
	@ManyToOne
	private EvaluationSetting setting;
	
	/**
	 * 评价内容
	 */
	private String content;
	
	/**
	 * 低分原因
	 */
	private String reason;

	/**
	 * 回复
	 */
	private String feedback;

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

	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public Evaluation getComment() {
		return comment;
	}

	public void setComment(Evaluation comment) {
		this.comment = comment;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public EvaluationSetting getSetting() {
		return setting;
	}

	public void setSetting(EvaluationSetting setting) {
		this.setting = setting;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
