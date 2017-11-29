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
 * 快捷菜单
 * @ClassName: QuickMenu   
 * @Description: 快捷菜单表
 * @author HiSay  
 * @date  2015-10-31 13:01:07
 *
 */
@Entity
@Table(name="ticket_QuickMenu",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class QuickMenu implements Serializable {

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
	 * 菜单名称
	 */
	@Column
	private String name = null;

	/**
	 * 菜单链接
	 */
	@Column
	private String url = null;
	/**
	 * 菜单所属分类（字典）
	 */
	@Column
	private String quickMenuType_id = null;
	/**
	 * 是否默认显示
	 */
	@Column 
	private String isDefaultShow = null;
	/**
	 * 默认显示的位置
	 */
	@Column
	private String defaultShowPosition = null;

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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getQuickMenuType_id() {
		return quickMenuType_id;
	}
	public void setQuickMenuType_id(String quickMenuTypeId) {
		quickMenuType_id = quickMenuTypeId;
	}
	public String getIsDefaultShow() {
		return isDefaultShow;
	}
	public void setIsDefaultShow(String isDefaultShow) {
		this.isDefaultShow = isDefaultShow;
	}
	public String getDefaultShowPosition() {
		return defaultShowPosition;
	}
	public void setDefaultShowPosition(String defaultShowPosition) {
		this.defaultShowPosition = defaultShowPosition;
	}
}
