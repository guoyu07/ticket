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

import com.ticket.enumer.SearchType;
/**
 * 统计搜索词表
 * @ClassName: CountSearch   
 * @Description: 统计搜索词表
 * @author HiSay  
 * @date  2016-03-10 15:49:15
 *
 */
@Entity
@Table(name="ticket_CountSearch",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class CountSearch implements Serializable {

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
	 * 搜索词
	 */
	@Column(length=255)
	private String searchWord;

	/**
	 * 触发的关键词
	 */
	@Column(length=1000)
	private String keyword;
	
	/**
	 * 触发的否定词
	 */
	@Column(length=1000)
	private String negative;

	/**
	 * 落地页
	 */
	@Column(length=255)
	private String goUrl;
	
	/**
	 * 落地页链接
	 */
	@Column(length=255)
	private String goUrlHref;
	
	/**
	 * 搜索时的IP地址
	 */
	@Column(length=20)
	private String ip;

	/**
	 * 会员ID
	 */
	@ManyToOne
	private Member member;
	
	/**
	 * 搜索类型
	 */
	private SearchType type;

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
	
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getGoUrl() {
		return goUrl;
	}
	public void setGoUrl(String goUrl) {
		this.goUrl = goUrl;
	}
	public String getGoUrlHref() {
		return goUrlHref;
	}
	public void setGoUrlHref(String goUrlHref) {
		this.goUrlHref = goUrlHref;
	}
	public String getNegative() {
		return negative;
	}
	public void setNegative(String negative) {
		this.negative = negative;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public SearchType getType() {
		return type;
	}
	public void setType(SearchType type) {
		this.type = type;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
}
