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
 * 系统模块
 * @ClassName: SystemModule   
 * @Description: 系统模块信息
 * @author HiSay  
 * @date  2014-10-15 13:49:51
 *
 */
@Entity
@Table(name="ticket_SystemModule",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class SystemModule extends BasePojo implements Serializable {

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
	 * 模块名称
	 */
	@Column
	private String name = null;

	/**
	 * 模块链接
	 */
	@Column(name="moduleUrl")
	private String url = null;

	/**
	 * 所属上级模块
	 */
	@Column(length=50)
	private String parent_id = null;

	/**
	 * 模块图标
	 */
	@Column
	private String icon = null;

	/**
	 * 是否默认显示该模块
	 * 当后台管理员登陆进后台的时候, 默认加载的模块.
	 */
	@Column
	private Integer isDefaultShow = ContextConstants.STATUS_OF_ZERO;

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
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getIsDefaultShow() {
		return isDefaultShow;
	}
	public void setIsDefaultShow(Integer isDefaultShow) {
		this.isDefaultShow = isDefaultShow;
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
