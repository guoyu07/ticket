package com.ticket.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import java.util.UUID;
/**
 * 常用旅客证件
 * @ClassName: CommonTravellerCard   
 * @Description: 常用旅客证件
 * @author HiSay  
 * @date  2016-01-07 15:20:41
 */
@Entity
@Table(name="ticket_CommonTravellerCard",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class CommonTravellerCard implements Serializable {

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
	 * 证件类型
	 */
	@Column(length=30)
	private String cardType;

	/**
	 * 证件号码
	 */
	@Column(length=40)
	private String cardValue;

	/**
	 * 旅客id
	 */
	@ManyToOne
	@JoinColumn(name="parentId")
	private CommonTraveller parent;

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
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardValue() {
		return cardValue;
	}
	public void setCardValue(String cardValue) {
		this.cardValue = cardValue;
	}
	public CommonTraveller getParent() {
		return parent;
	}
	public void setParent(CommonTraveller parent) {
		this.parent = parent;
	}
}
