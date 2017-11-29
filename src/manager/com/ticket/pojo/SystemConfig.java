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
 * 系统配置
 * @ClassName: SystemConfig   
 * @Description: 系统基本信息配置
 * @author HiSay  
 * @date  2014-10-11 08:26:42
 *
 */
@Entity
@Table(name="ticket_SystemConfig",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class SystemConfig extends BasePojo implements Serializable {

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
	 * 系统名称
	 */
	@Column(length=50)
	private String name = null;
	
	/**
	 * 前台名称
	 */
	@Column
	private String nameFront = null;

	/**
	 * 系统关键词
	 */
	@Column(length=20)
	private String keyword = null;

	/**
	 * 系统关键词描述
	 */
	@Column(length=500)
	private String description = null;

	/**
	 * 是否允许登陆
	 */
	@Column
	private Integer isAllowSignIn = null;

	/**
	 * 是否允许注册
	 */
	@Column
	private Integer isAllowCreate = null;
	
	/**
	 * android版本号
	 */
	private String androidVersion;
	
	/**
	 * android更新路径
	 */
	private String androidPath;
	
	/**
	 * ios版本号
	 */
	private String iosVersion;

	/**
	 * ios更新路径
	 */
	private String iosPath;
	
	public String getAndroidVersion() {
		return androidVersion;
	}
	public void setAndroidVersion(String androidVersion) {
		this.androidVersion = androidVersion;
	}
	public String getAndroidPath() {
		return androidPath;
	}
	public void setAndroidPath(String androidPath) {
		this.androidPath = androidPath;
	}
	public String getIosVersion() {
		return iosVersion;
	}
	public void setIosVersion(String iosVersion) {
		this.iosVersion = iosVersion;
	}
	public String getIosPath() {
		return iosPath;
	}
	public void setIosPath(String iosPath) {
		this.iosPath = iosPath;
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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getIsAllowSignIn() {
		return isAllowSignIn;
	}
	public void setIsAllowSignIn(Integer isAllowSignIn) {
		this.isAllowSignIn = isAllowSignIn;
	}
	public Integer getIsAllowCreate() {
		return isAllowCreate;
	}
	public void setIsAllowCreate(Integer isAllowCreate) {
		this.isAllowCreate = isAllowCreate;
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
	public String getNameFront() {
		return nameFront;
	}
	public void setNameFront(String nameFront) {
		this.nameFront = nameFront;
	}
}
