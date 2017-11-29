package com.ticket.pojo;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 * 便捷登机服务码生成规则
 * @author tuyou  
 */
@Entity
@Table(name="ticket_BjdjServiceCodeRule", uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class BjdjServiceCodeRule implements Serializable {

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
	 * 渠道类型
	 */
	@OneToOne
	private SystemDictionary type;
	
	/**
	 * 规则
	 */
	private String rule;

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

	public SystemDictionary getType() {
		return type;
	}

	public void setType(SystemDictionary type) {
		this.type = type;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

}
