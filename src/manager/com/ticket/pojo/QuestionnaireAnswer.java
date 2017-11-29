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
 * 问卷发放记录表
 * @ClassName: QuestionnaireAnswer   
 * @Description: 问卷发放记录
 * @author HiSay  
 * @date  2016-05-04 16:31:27
 *
 */
@Entity
@Table(name="ticket_QuestionnaireAnswer",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class QuestionnaireAnswer implements Serializable {

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
	 * 问卷id
	 */
	@Column
	private String questionnaireId = null;

	/**
	 * 答题人id
	 */
	@Column
	private String object_id = null;

	/**
	 * 问题编号
	 */
	@Column
	private Integer questionNo = null;

	/**
	 * 问题答案
	 */
	@Column
	private String qustionAnswer = null;


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
	
	public String getQuestionnaireId() {
		return questionnaireId;
	}
	public void setQuestionnaireId(String questionnaireId) {
		this.questionnaireId = questionnaireId;
	}
	public String getObject_id() {
		return object_id;
	}
	public void setObject_id(String object_id) {
		this.object_id = object_id;
	}
	public Integer getQuestionNo() {
		return questionNo;
	}
	public void setQuestionNo(Integer questionNo) {
		this.questionNo = questionNo;
	}
	public String getQustionAnswer() {
		return qustionAnswer;
	}
	public void setQustionAnswer(String qustionAnswer) {
		this.qustionAnswer = qustionAnswer;
	}
}
