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
 * 我的记事本
 * @ClassName: MyText   
 * @Description: 我的记事本
 * @author HiSay  
 * @date  2016-02-15 11:26:54
 *
 */
@Entity
@Table(name="ticket_MyText",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class MyText implements Serializable {

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
	 * 关联客户id
	 */
	@Column
	private String employeeInfo_id = null;

	/**
	 * 标题
	 */
	@Column
	private String title = null;

	/**
	 * 内容
	 */
	@Column(length=8000)
	private String content = null;

	/**
	 * 是否阅读
	 * 1 已阅读
	 * 0 未阅读
	 */
	@Column
	private Integer isRead = 0;


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
	
	public String getEmployeeInfo_id() {
		return employeeInfo_id;
	}
	public void setEmployeeInfo_id(String employeeInfo_id) {
		this.employeeInfo_id = employeeInfo_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getIsRead() {
		return isRead;
	}
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
}
