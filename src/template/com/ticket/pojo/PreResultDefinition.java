package com.ticket.pojo;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;
/**
 * 预定义搜索结果
 * @ClassName: PreResultDefinition   
 * @Description: 预定义搜索结果
 * @author HiSay  
 * @date  2015-12-14 18:57:30
 *
 */
@Entity
@Table(name="ticket_PreResultDefinition",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class PreResultDefinition implements Serializable {

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
	@Column(length=50)
	private String pageName;

	/**
	 * 系统关键词
	 */
	@Lob
	@Type(type="org.hibernate.type.StringClobType") 
	private String keyword;
	
	/**
	 * 系统否定词
	 */
	@Lob
	@Type(type="org.hibernate.type.StringClobType") 
	private String negativeKeyword;
	
	/**
	 * 系统精确否定词
	 */
	@Lob
	@Type(type="org.hibernate.type.StringClobType") 
	private String _negativeKeyword;

	/**
	 * 系统关键词描述
	 */
	@Lob
	@Type(type="org.hibernate.type.StringClobType") 
	private String description;

	/**
	 * 页面链接
	 */
	@Column
	private String url;
	
	/**
	 * pc搜索链接
	 */
	@Column
	private String pcUrl;
	
	/**
	 * 0、站内搜索
	 * 1、商业搜索
	 */
	private Integer type = 0;


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
	
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPcUrl() {
		return pcUrl;
	}
	public void setPcUrl(String pcUrl) {
		this.pcUrl = pcUrl;
	}
	public String getNegativeKeyword() {
		return negativeKeyword;
	}
	public void setNegativeKeyword(String negativeKeyword) {
		this.negativeKeyword = negativeKeyword;
	}
	public String get_negativeKeyword() {
		return _negativeKeyword;
	}
	public void set_negativeKeyword(String _negativeKeyword) {
		this._negativeKeyword = _negativeKeyword;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
}
