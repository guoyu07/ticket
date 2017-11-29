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
 * 站内信发送记录
 * @ClassName: StationLetterSendRecord   
 * @Description: 站内信发送的记录
 * @author HiSay  
 * @date  2016-05-10 14:53:45
 *
 */
@Entity
@Table(name="ticket_StationLetterSendRecord",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class StationLetterSendRecord implements Serializable {

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
	 * 操作员id
	 */
	@Column
	private String opertator_id = null;

	/**
	 * 站内信id
	 */
	@Column
	private String letter_id = null;

	/**
	 * 对象标识
	 */
	@Column
	private String objectFlag = null;

	/**
	 * 对象id
	 */
	@Column
	private String object_id = null;

	/**
	 * 发送数量
	 */
	@Column
	private Integer sendCount = null;


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
	
	public String getOpertator_id() {
		return opertator_id;
	}
	public void setOpertator_id(String opertator_id) {
		this.opertator_id = opertator_id;
	}
	public String getLetter_id() {
		return letter_id;
	}
	public void setLetter_id(String letter_id) {
		this.letter_id = letter_id;
	}
	public String getObjectFlag() {
		return objectFlag;
	}
	public void setObjectFlag(String objectFlag) {
		this.objectFlag = objectFlag;
	}
	public String getObject_id() {
		return object_id;
	}
	public void setObject_id(String object_id) {
		this.object_id = object_id;
	}
	public Integer getSendCount() {
		return sendCount;
	}
	public void setSendCount(Integer sendCount) {
		this.sendCount = sendCount;
	}
}
