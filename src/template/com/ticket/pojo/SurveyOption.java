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
 * 问题选项
 * @ClassName: SurveyOption   
 * @Description: 问题选项
 * @author HiSay  
 * @date  2015-11-12 14:58:46
 *
 */
@Entity
@Table(name="ticket_SurveyOption",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class SurveyOption implements Serializable {

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
	 * 选项编号
	 */
	@Column
	private String optionNo = null;

	/**
	 * 所属问题
	 */
	@Column
	private String surveyQuestionId = null;

	/**
	 * 选项标题
	 */
	@Column
	private String title = null;


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
	
	public String getOptionNo() {
		return optionNo;
	}
	public void setOptionNo(String optionNo) {
		this.optionNo = optionNo;
	}
	public String getSurveyQuestionId() {
		return surveyQuestionId;
	}
	public void setSurveyQuestionId(String surveyQuestionId) {
		this.surveyQuestionId = surveyQuestionId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
