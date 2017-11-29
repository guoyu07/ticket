package com.ticket.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.UUID;
/**
 * 渠道分类
 * @ClassName: ChannelType   
 * @Description: 渠道分类
 * @author HiSay  
 * @date  2015-11-03 17:39:54
 *
 */
@Entity
@Table(name="ticket_ChannelType",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class ChannelType implements Serializable {

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
	 * 分类名称
	 */
	@Column
	private String name = null;

	/**
	 * 分类描述
	 */
	@Column
	private String descript = null;
	/**
	 * 服务码池子类型ID（字典）
	 */
	@ManyToOne
	private SystemDictionary type = null;


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
	public SystemDictionary getType() {
		return type;
	}
	public void setType(SystemDictionary type) {
		this.type = type;
	}
	
}
