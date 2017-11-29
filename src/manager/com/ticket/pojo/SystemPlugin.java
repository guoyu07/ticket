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
 * 系统插件
 * @ClassName: SystemPlugin   
 * @Description: 系统插件信息
 * @author HiSay  
 * @date  2014-10-15 14:44:02
 *
 */
@Entity
@Table(name="ticket_SystemPlugin",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class SystemPlugin extends BasePojo implements Serializable {

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
	 * 插件名称
	 */
	@Column
	private String name = null;

	/**
	 * 插件图标
	 */
	@Column
	private String thumbnail = null;

	/**
	 * 插件描述内容
	 */
	@Column
	private String content = null;


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
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String getIds() {
		return this.getId();
	}
	@Override
	public void setIds(String ids) {
		super.setIds(ids);
	}
	public String getFlag() {
		return this.getStatus().getVersionFlag();
	}
	public Long getViewUrl() {
		return this.getStatus().getVisitUrl();
	}
}
