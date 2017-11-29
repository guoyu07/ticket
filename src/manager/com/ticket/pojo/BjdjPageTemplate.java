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
 * jdj支付激活页面模板
 * @ClassName: BjdjPageTemplate   
 * @Description: jdj支付激活页面模板
 * @author HiSay  
 * @date  2016-08-18 15:26:40
 *
 */
@Entity
@Table(name="ticket_BjdjPageTemplate",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class BjdjPageTemplate implements Serializable {

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
	 * 模板名称
	 */
	@Column
	private String name;

	/**
	 * 页面内容
	 */
	@Column
	private String content;

	/**
	 * 按钮名称
	 */
	@Column(length=1000)
	private String buttonName;

	/**
	 * 按钮链接
	 */
	@Column(length=1000)
	private String buttonUrl;

	/**
	 * 按钮类型
	 */
	@Column
	private String buttonType;

	/**
	 * 按钮样式
	 */
	@Column
	private String buttonClass;


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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getButtonName() {
		return buttonName;
	}
	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}
	public String getButtonUrl() {
		return buttonUrl;
	}
	public void setButtonUrl(String buttonUrl) {
		this.buttonUrl = buttonUrl;
	}
	public String getButtonType() {
		return buttonType;
	}
	public void setButtonType(String buttonType) {
		this.buttonType = buttonType;
	}
	public String getButtonClass() {
		return buttonClass;
	}
	public void setButtonClass(String buttonClass) {
		this.buttonClass = buttonClass;
	}
}
