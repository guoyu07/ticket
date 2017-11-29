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
 * 测试用户
 * @ClassName: SpecialPersonWithQuickMenu   
 * @Description: 服务菜单对应表
 * @author HiSay  
 * @date  2015-12-05 09:41:10
 *
 */
@Entity
@Table(name="ticket_SpecialPersonWithQuickMenu",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class SpecialPersonWithQuickMenu implements Serializable {

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
	 * 服务人员类型
	 */
	@Column
	private String personType = null;

	/**
	 * 快捷菜单id
	 */
	@Column
	private String quickMenu_id = null;


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
	
	public String getPersonType() {
		return personType;
	}
	public void setPersonType(String personType) {
		this.personType = personType;
	}
	public String getQuickMenu_id() {
		return quickMenu_id;
	}
	public void setQuickMenu_id(String quickMenu_id) {
		this.quickMenu_id = quickMenu_id;
	}
}
