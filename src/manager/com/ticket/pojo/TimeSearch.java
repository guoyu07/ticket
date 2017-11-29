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
/**
 * 搜索统计
 * @ClassName: TimeSearch   
 * @Description: 搜索统计
 * @author HiSay  
 * @date  2016-08-12 11:26:22
 */
@Entity
@Table(name="ticket_TimeSearch",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class TimeSearch implements Serializable {

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
	 * 落地页链接
	 */
	@Column
	private String goUrl;

	/**
	 * 展现量
	 */
	@Column
	private Integer showRate;

	/**
	 * 点击量
	 */
	@Column
	private Integer clickRate;

	/**
	 * 触发展现的搜索词
	 */
	@Column
	private String showKeyword;

	/**
	 * 触发点击的搜索词
	 */
	@Column
	private String clickKeyword;

	/**
	 * 对应的预定义搜索结果id
	 */
	@ManyToOne
	private PreResultDefinition definition;


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
	
	public String getGoUrl() {
		return goUrl;
	}
	public void setGoUrl(String goUrl) {
		this.goUrl = goUrl;
	}
	public Integer getShowRate() {
		return showRate;
	}
	public void setShowRate(Integer showRate) {
		this.showRate = showRate;
	}
	public Integer getClickRate() {
		return clickRate;
	}
	public void setClickRate(Integer clickRate) {
		this.clickRate = clickRate;
	}
	public String getShowKeyword() {
		return showKeyword;
	}
	public void setShowKeyword(String showKeyword) {
		this.showKeyword = showKeyword;
	}
	public String getClickKeyword() {
		return clickKeyword;
	}
	public void setClickKeyword(String clickKeyword) {
		this.clickKeyword = clickKeyword;
	}
	public PreResultDefinition getDefinition() {
		return definition;
	}
	public void setDefinition(PreResultDefinition definition) {
		this.definition = definition;
	}
}
