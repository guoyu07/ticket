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
 * 关键词定位关联页面
 * @ClassName: KeyWordLocationPage   
 * @Description: 关键词定位关联页面
 * @author HiSay  
 * @date  2016-09-30 15:38:12
 *
 */
@Entity
@Table(name="ticket_KeyWordLocationPage",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class KeyWordLocationPage implements Serializable {

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
	 * 关键词
	 */
	@ManyToOne
	private KeyWordLocation keyword;

	/**
	 * 关联页面
	 */
	@ManyToOne
	private Page page;

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

	public KeyWordLocation getKeyword() {
		return keyword;
	}

	public void setKeyword(KeyWordLocation keyword) {
		this.keyword = keyword;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

}
