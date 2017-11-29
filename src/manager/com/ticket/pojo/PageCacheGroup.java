package com.ticket.pojo;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ticket.enumer.PageCacheScope;
/**
 * 页面缓存group管理
 * @ClassName: PageCacheGroup   
 * @Description: 页面缓存group管理
 * @author HiSay  
 * @date  2015-12-23 10:34:35
 */
@Entity
@Table(name="ticket_PageCacheGroup",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class PageCacheGroup implements Serializable {

	/**   
	 * @Fields serialVersionUID :    
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
	 * 组名
	 */
	@Column(length=255, unique=true, nullable=false)
	private String groupName = null;

	/**
	 * 缓存时间（秒）
	 */
	@Column(nullable=false)
	private Integer time = null;
	
	/**
	 * 更新时间表达式
	 */
	private String cron;

	/**
	 * 缓存生命范围
	 */
	@Enumerated(EnumType.STRING)
	private PageCacheScope scope = null;
	
	/**
	 * 备注
	 */
	@Column(length=1024)
	private String remarks;
	
	public PageCacheGroup() {
		super();
	}
	public PageCacheGroup(String id) {
		super();
		this.id = id;
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
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	public PageCacheScope getScope() {
		return scope;
	}
	public void setScope(PageCacheScope scope) {
		this.scope = scope;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getCron() {
		return cron;
	}
	public void setCron(String cron) {
		this.cron = cron;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PageCacheGroup other = (PageCacheGroup) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
