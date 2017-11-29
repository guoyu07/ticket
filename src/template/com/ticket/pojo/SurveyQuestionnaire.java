package com.ticket.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 * 调查问卷
 * @ClassName: SurveyQuestionnaire   
 * @Description: 调查问卷
 * @author HiSay  
 * @date  2015-11-11 17:10:59
 *
 */
@Entity
@Table(name="ticket_SurveyQuestionnaire",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class SurveyQuestionnaire implements Serializable {

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
	 * 问卷编号
	 */
	@Column
	private String survryNo = null;

	/**
	 * 问卷标题
	 */
	@Column
	private String title = null;

	/**
	 * 问卷描述
	 */
	@Column
	private String descript = null;

	/**
	 * 问卷模块类型
	 */
	@Column
	private Integer type = null;

	/**
	 * 调查模块ID
	 */
	@Column
	private String survryModularId = null;

	/**
	 * 实体表名
	 */
	@Column
	private String entityName = null;

	/**
	 * 实体ID
	 */
	@Column
	private String entityId = null;

	/**
	 * 当前开放问卷
	 */
	@Column
	private Integer currentOpenUp = 0;

	/**
	 * 截止日期
	 */
	@Column
	private Date deadLine = null;

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
	
	public String getSurvryNo() {
		return survryNo;
	}
	public void setSurvryNo(String survryNo) {
		this.survryNo = survryNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getSurvryModularId() {
		return survryModularId;
	}
	public void setSurvryModularId(String survryModularId) {
		this.survryModularId = survryModularId;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getEntityId() {
		return entityId;
	}
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
	public Integer getCurrentOpenUp() {
		return currentOpenUp;
	}
	public void setCurrentOpenUp(Integer currentOpenUp) {
		this.currentOpenUp = currentOpenUp;
	}
	public Date getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}
}
