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
 * 问卷发放日志
 * @ClassName: QuestionnaireSendLog   
 * @Description: 问卷发放的记录
 * @author HiSay  
 * @date  2016-05-05 16:21:51
 *
 */
@Entity
@Table(name="ticket_QuestionnaireSendLog",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class QuestionnaireSendLog implements Serializable {

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
	 * 发放人
	 */
	@Column
	private String employee_id = null;

	/**
	 * 问卷发送数量
	 */
	@Column
	private Integer sendCount = null;

	/**
	 * 问卷完成数量
	 */
	@Column
	private Integer writeCount = null;


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
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public Integer getSendCount() {
		return sendCount;
	}
	public void setSendCount(Integer sendCount) {
		this.sendCount = sendCount;
	}
	public Integer getWriteCount() {
		return writeCount;
	}
	public void setWriteCount(Integer writeCount) {
		this.writeCount = writeCount;
	}
}
