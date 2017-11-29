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
 * 页面跳转模板
 * @ClassName: PageRedirectTemplate   
 * @Description: 页面跳转模板
 * @author HiSay  
 * @date  2015-10-22 14:00:50
 *
 */
@Entity
@Table(name="ticket_PageRedirectTemplate",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class PageRedirectTemplate implements Serializable {

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
	 * 页面名称
	 */
	@Column
	private String name = null;

	/**
	 * 跳转到哪个JSP
	 */
	@Column
	private String toPage = null;

	/**
	 * 跳转到哪个JSP(AJax)
	 */
	@Column
	private String toPageAjax = null;
	
	/**
	 * 模板类型
	 */
	@Column
	private Integer type = null;

	/**
	 * 是否单篇文章
	 */
	@Column
	private Integer isSinglePage = null;
	/**
	 * 页面所在的目录
	 */
	@Column
	private String directory = null;

	public String getDirectory() {
		return directory;
	}
	public void setDirectory(String directory) {
		this.directory = directory;
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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getToPage() {
		return toPage;
	}
	public void setToPage(String toPage) {
		this.toPage = toPage;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getIsSinglePage() {
		return isSinglePage;
	}
	public void setIsSinglePage(Integer isSinglePage) {
		this.isSinglePage = isSinglePage;
	}
	public String getToPageAjax() {
		return toPageAjax;
	}
	public void setToPageAjax(String toPageAjax) {
		this.toPageAjax = toPageAjax;
	}
}
