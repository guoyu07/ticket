package com.ticket.pojo;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
/**
 * 会员收藏
 * @ClassName: MemberFavorite   
 * @Description: 会员收藏表
 * @author HiSay  
 * @date  2015-11-13 09:59:42
 *
 */
@Entity
@Table(name="ticket_MemberFavorite",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class MemberFavorite implements Serializable {

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
	 * 会员id
	 */
	@Column
	private String member_id = null;

	/**
	 * 收藏对象id
	 */
	@Column
	private String objectId = null;

	/**
	 * 收藏类型
	 */
	@Column
	private String objectType = null;

	/**
	 * 收藏标题
	 */
	@Column
	private String title = null;

	/**
	 * 收藏链接
	 */
	@Column
	private String url = null;

	@Version
	private int version;

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
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getObjectType() {
		return objectType;
	}
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String Title) {
		this.title = Title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
}
