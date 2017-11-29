package com.ticket.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.UUID;
/**
 * 调查问题
 * @ClassName: SurveyQuestion   
 * @Description: 调查问题
 * @author HiSay  
 * @date  2015-11-12 14:53:43
 *
 */
@Entity
@Table(name="ticket_SurveyQuestion",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class SurveyQuestion implements Serializable {

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
	 * 问题编号
	 */
	@Column
	private Integer questionNo = null;

	/**
	 * 问题标题
	 */
	@Column
	private String title = null;
	
	/**
	 * 所属问卷
	 */
	private String surveyQuestionnaireId =null;
	/**
	 * 问题类型
	 */
	@Column
	private Integer type = null;
	
	/**
	 * 问题排序
	 */
	@Column
	private Integer iseq = null;

	/**
	 * 问题调查形式
	 */
	@Column
	private Integer questionType = null;


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
	
	public Integer getQuestionNo() {
		return questionNo;
	}
	public void setQuestionNo(Integer questionNo) {
		this.questionNo = questionNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getIseq() {
		return iseq;
	}
	public void setIseq(Integer iseq) {
		this.iseq = iseq;
	}
	public Integer getQuestionType() {
		return questionType;
	}
	public void setQuestionType(Integer questionType) {
		this.questionType = questionType;
	}
	public void setSurveyQuestionnaireId(String surveyQuestionnaireId) {
		this.surveyQuestionnaireId = surveyQuestionnaireId;
	}
	public String getSurveyQuestionnaireId() {
		return surveyQuestionnaireId;
	}
}
