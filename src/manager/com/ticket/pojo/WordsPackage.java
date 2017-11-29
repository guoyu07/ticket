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
 * 关键词包
 * @ClassName: WordsPackage   
 * @Description: ordsPackage
 * @author HiSay  
 * @date  2016-09-28 15:44:19
 *
 */
@Entity
@Table(name="ticket_WordsPackage",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class WordsPackage implements Serializable {

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
	 * 包名
	 */
	@Column(length=64)
	private String name;

	/**
	 * 关键词
	 */
	@Lob
	@Type(type="org.hibernate.type.StringClobType")
	private String keywords;


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
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
}
