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
 * 调查模块
 * @ClassName: SurvryModular   
 * @Description: 调查模块
 * @author HiSay  
 * @date  2015-11-11 17:08:07
 *
 */
@Entity
@Table(name="ticket_SurvryModular",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class SurvryModular implements Serializable {

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
	 * 调查模块名称
	 */
	@Column
	private String name = null;

	/**
	 * 调查模块描述
	 */
	@Column
	private String descript = null;


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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
}
