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
 * 服务码延期规则
 * @ClassName: BjdjServiceCodeDelayRule   
 * @Description: 服务码延期规则
 * @author HiSay  
 * @date  2015-12-11 14:46:34
 *
 */
@Entity
@Table(name="ticket_BjdjServiceCodeDelayRule",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class BjdjServiceCodeDelayRule implements Serializable {

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
	 * 延长时间(天)
	 */
	@Column(length=255)
	private Integer delayTime = null;

	/**
	 * 扣除积分
	 */
	private Integer record = null;

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

	public Integer getRecord() {
		return record;
	}

	public void setRecord(Integer record) {
		this.record = record;
	}

	public Integer getDelayTime() {
		return delayTime;
	}

	public void setDelayTime(Integer delayTime) {
		this.delayTime = delayTime;
	}
}
