package com.ticket.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ticket.util.GeneralUtil;

import java.util.List;
import java.util.UUID;
/**
 * 常用旅客
 * @ClassName: CommonTraveller   
 * @Description: 常用旅客
 * @author HiSay  
 * @date  2016-01-05 16:30:37
 *
 */
@Entity
@Table(name="ticket_CommonTraveller",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class CommonTraveller implements Serializable {

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
	 * 中文名
	 */
	@Column(length=30)
	private String chaName = null;

	/**
	 * 英文名
	 */
	@Column(length=30)
	private String engName = null;

	/**
	 * 手机号
	 */
	@Column(length=20)
	private String phone = null;

	/**
	 * 性别
	 */
	@Column(length=5)
	private String gender = null;

	/**
	 * 生日
	 */
	@Column(length=30)
	private String birth = null;

	/**
	 * 会员ID
	 */
	@Column(length=50)
	private String memberId = null;
	
	@OneToMany(mappedBy="parent",fetch=FetchType.EAGER)
	private List<CommonTravellerCard> cards;


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
	
	public String getChaName() {
		return chaName;
	}
	public void setChaName(String chaName) {
		this.chaName = chaName;
	}
	public String getEngName() {
		return engName;
	}
	public void setEngName(String engName) {
		this.engName = engName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getName(){
		
		if(GeneralUtil.isNotNull(chaName)){
			
			return chaName;
		}
		return engName;
	}
	public void setName(String name){
		
		String regEngName = "[A-Za-z ]+";
		if(name.matches(regEngName)){
			
			engName = name;
		}else{
			
			chaName = name;
		}
	}
	public List<CommonTravellerCard> getCards() {
		return cards;
	}
	public void setCards(List<CommonTravellerCard> cards) {
		this.cards = cards;
	}
}
