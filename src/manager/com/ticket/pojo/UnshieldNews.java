package com.ticket.pojo;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 * 关键词包
 * @ClassName: WordsPackage   
 * @Description: ordsPackage
 * @author HiSay  
 * @date  2016-09-28 15:44:19
 *
 */
@Entity
@Table(name="ticket_UnshieldNews",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class UnshieldNews implements Serializable {

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
	
	@ManyToOne
	private News news;

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
	public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
	}
}
