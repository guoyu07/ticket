package com.ticket.pojo;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ticket.constants.ContextConstants;
/**
 * 新闻类别
 * @ClassName: NewsClass   
 * @Description: 新闻类别表
 * @author HiSay  
 * @date  2015-10-13 17:40:45
 *
 */
@Entity
@Table(name="ticket_NewsClass",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class NewsClass implements Serializable {

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
	 * 类别名称
	 */
	@Column
	private String name = null;
	
	/**
	 * 权限id
	 */
	@Column
	private String permissionId = null;

	/**
	 * 上级类别
	 */
	@Column
	private String parent_id = null;

	/**
	 * 类别描述
	 */
	@Column
	private String descript = null;
	
	/**
	 * 是否默认显示该模块
	 * 当后台管理员登陆进后台的时候, 默认加载的模块.
	 */
	@Column
	private Integer isDefaultShow = ContextConstants.STATUS_OF_ZERO;
	/**
	 * 别名
	 */
	@Column
	private String alias = null;
	/**
	 * 英文名称
	 */
	@Column
	private String englishName = null;
	/**
	 * 列表页面跳转模板ID
	 */
	@Column
	private String listPageRedirectTemplate_id = null;
	/**
	 * pc列表模板
	 */
	@Column
	private String pcListTemplate_id = null;
	/**
	 * pc详细模板
	 */
	@Column
	private String pcViewTemplate_id = null;
	
	private String imageUrl = null;
	
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
	public String getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public Integer getIsDefaultShow() {
		return isDefaultShow;
	}
	public void setIsDefaultShow(Integer isDefaultShow) {
		this.isDefaultShow = isDefaultShow;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getEnglishName() {
		return englishName;
	}
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	public String getListPageRedirectTemplate_id() {
		return listPageRedirectTemplate_id;
	}
	public void setListPageRedirectTemplate_id(String listPageRedirectTemplateId) {
		listPageRedirectTemplate_id = listPageRedirectTemplateId;
	}
	public String getPcListTemplate_id() {
		return pcListTemplate_id;
	}
	public void setPcListTemplate_id(String pcListTemplateId) {
		pcListTemplate_id = pcListTemplateId;
	}
	public String getPcViewTemplate_id() {
		return pcViewTemplate_id;
	}
	public void setPcViewTemplate_id(String pcViewTemplateId) {
		pcViewTemplate_id = pcViewTemplateId;
	}
	
}
