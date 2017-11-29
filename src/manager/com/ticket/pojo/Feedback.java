package com.ticket.pojo;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 * 公测反馈
 * @ClassName: Feedback   
 * @Description: 公测反馈
 * @author HiSay  
 * @date  2016-08-15 15:10:43
 *
 */
@Entity
@Table(name="ticket_Feedback",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class Feedback implements Serializable {

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
	 * 反馈人员
	 */
	@ManyToOne
	private Member member;

	/**
	 * 问题描述
	 */
	@Column
	private String description;

	/**
	 * 手机号码
	 */
	@Column
	private String phone;
	
	/**
	 * 图片
	 */
	@Column
	private String images;


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
	
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
}
