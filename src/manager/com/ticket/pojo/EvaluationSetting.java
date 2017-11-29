package com.ticket.pojo;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
/**
 * 评价设置
 * @ClassName: EvaluationSetting   
 * @Description: 评价设置
 * @author HiSay  
 * @date  2016-01-26 10:56:19
 *
 */
@Entity
@Table(name="ticket_evaluationSetting",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class EvaluationSetting implements Serializable {

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
	 * 是否启用(具体到指标)
	 */
	@Column(length=20)
	private Integer enabled;

	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 父ID
	 */
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private EvaluationSetting parent;
	
	public EvaluationSetting (){
		super();
	}
	public EvaluationSetting (String id){
		super();
		this.id = id;
	}
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
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public EvaluationSetting getParent() {
		return parent;
	}
	public void setParent(EvaluationSetting parent) {
		this.parent = parent;
	}
}
