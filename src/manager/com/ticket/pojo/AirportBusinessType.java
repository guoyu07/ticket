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
 * 机场商业类别
 * @ClassName: AirportBusinessType   
 * @Description: 机场商业类别表
 * @author HiSay  
 * @date  2015-11-16 13:35:58
 *
 */
@Entity
@Table(name="ticket_AirportBusinessType",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class AirportBusinessType implements Serializable {

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
	 * 类别名称
	 */
	@Column
	private String name = null;

	/**
	 * 上级类别id
	 */
	@Column
	private String parent_id = null;

	/**
	 * 类别介绍
	 */
	@Column
	private String introduce = null;
	
	/**
	 * 是否默认展示
	 */
	@Column
	private Integer isDefaultShow = null;


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
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public Integer getIsDefaultShow() {
		return isDefaultShow;
	}
	public void setIsDefaultShow(Integer isDefaultShow) {
		this.isDefaultShow = isDefaultShow;
	}
}
