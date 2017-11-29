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
 * 会员等级
 * @ClassName: MemberLevel   
 * @Description: 会员等级表
 * @author HiSay  
 * @date  2015-10-14 16:38:05
 *
 */
@Entity
@Table(name="ticket_MemberLevel",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class MemberLevel implements Serializable {

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
	 * 等级名称
	 */
	@Column
	private String name = null;

	/**
	 * 所需积分
	 */
	@Column
	private String needScore = null;

	/**
	 * 等级描述
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
	public String getNeedScore() {
		return needScore;
	}
	public void setNeedScore(String needScore) {
		this.needScore = needScore;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
}
