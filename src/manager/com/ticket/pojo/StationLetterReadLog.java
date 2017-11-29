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
 * 站内信阅读日志
 * @ClassName: StationLetterReadLog   
 * @Description: 站内信阅读记录
 * @author HiSay  
 * @date  2016-05-09 14:18:24
 *
 */
@Entity
@Table(name="ticket_StationLetterReadLog",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class StationLetterReadLog implements Serializable {

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
	 * 接收站内信对象id
	 */
	@Column
	private String object_id = null;

	/**
	 * 是否阅读
	 */
	@Column
	private Integer isRead = null;

	/**
	 * 信息id
	 */
	@Column
	private String letter_id = null;

	/**
	 * 阅读日期
	 */
	@Column
	private Date readDate = null;


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
	
	public String getObject_id() {
		return object_id;
	}
	public void setObject_id(String object_id) {
		this.object_id = object_id;
	}
	public Integer getIsRead() {
		return isRead;
	}
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
	public String getLetter_id() {
		return letter_id;
	}
	public void setLetter_id(String letter_id) {
		this.letter_id = letter_id;
	}
	public Date getReadDate() {
		return readDate;
	}
	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}
}
