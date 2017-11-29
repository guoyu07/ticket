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
 * 安全秘钥管理表
 * @ClassName: SecurityKey   
 * @Description: 安全秘钥管理表
 * @author HiSay  
 * @date  2016-01-21 14:43:44
 *
 */
@Entity
@Table(name="ticket_SecurityKey",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class SecurityKey implements Serializable {

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
	 * 公钥
	 */
	@Column(length=255)
	private String publicKey = null;

	/**
	 * 密钥
	 */
	@Column(length=255)
	private String secretKey = null;

	/**
	 * 备注
	 */
	@Column(length=255)
	private String remark;

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
	
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
