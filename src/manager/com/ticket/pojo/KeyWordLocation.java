package com.ticket.pojo;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;
/**
 * 关键词定位
 * @ClassName: KeyWordLocation   
 * @Description: eyWordLocation
 * @author HiSay  
 * @date  2016-09-28 15:43:22
 *
 */
@Entity
@Table(name="ticket_KeyWordLocation",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class KeyWordLocation implements Serializable {

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
	@Lob
	@Type(type="org.hibernate.type.StringClobType") 
	private String keyword;


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
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
