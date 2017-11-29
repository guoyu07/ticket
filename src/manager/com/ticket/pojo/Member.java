package com.ticket.pojo;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
/**
 * 会员信息
 * @ClassName: Member   
 * @Description: 会员信息表
 * @author HiSay  
 * @date  2015-11-13 18:01:16
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Table(name="ticket_Member",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class Member implements Serializable {

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
	 * 会员类型
	 */
	@Column
	private Integer memberType = null;
	/**
	 * 会员等级
	 */
	@Column
	private String memberLevel_id = null;

	/**
	 * 登录名
	 */
	@Column
	private String loginName = null;

	/**
	 * 登录密码
	 */
	@Column
	private String loginPwd = null;

	/**
	 * 昵称
	 */
	@Column
	private String nickName = null;

	/**
	 * 真实姓名
	 */
	@Column
	private String realName = null;
	
	/**
	 * 头像
	 */
	private String images;

	/**
	 * 身份证号
	 */
	@Column
	private String IDCard = null;

	/**
	 * 联系电话
	 */
	@Column
	private String phone = null;

	/**
	 * QQ号码
	 */
	@Column
	private String qq = null;

	/**
	 * 联系邮箱
	 */
	@Column
	private String email = null;

	/**
	 * 联系地址
	 */
	@Column
	private String address = null;

	/**
	 * 积分
	 */
	@Column
	private Integer record = null;

	/**
	 * 经验值
	 */
	@Column
	private String experience = null;

	/**
	 * 旅客类型
	 */
	@Column
	private String visitorType = null;
	
	/**
	 * 是否分享创建
	 */
	@Column
	private Integer share = null;
	
	/**
	 * 会员登录次数
	 */
	@Column
	private Integer loginCount = null;
	
	/**
	 * 渠道客户id
	 */
	@OneToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private ChannelCustomerInfo channelCustomerInfo = null;
	
	private String openid;
	
	public Member() {
		super();
	}
	
	public Member(String id) {
		super();
		this.id = id;
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
	
	public String getMemberLevel_id() {
		return memberLevel_id;
	}
	public void setMemberLevel_id(String memberLevel_id) {
		this.memberLevel_id = memberLevel_id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIDCard() {
		return IDCard;
	}
	public void setIDCard(String IDCard) {
		this.IDCard = IDCard;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getRecord() {
		return record;
	}
	public void setRecord(Integer record) {
		this.record = record;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getVisitorType() {
		return visitorType;
	}
	public void setVisitorType(String visitorType) {
		this.visitorType = visitorType;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Integer getMemberType() {
		return memberType;
	}

	public void setMemberType(Integer memberType) {
		this.memberType = memberType;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public ChannelCustomerInfo getChannelCustomerInfo() {
		return channelCustomerInfo;
	}

	public void setChannelCustomerInfo(ChannelCustomerInfo channelCustomerInfo) {
		this.channelCustomerInfo = channelCustomerInfo;
	}

	public Integer getShare() {
		return share;
	}

	public void setShare(Integer share) {
		this.share = share;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}
	
}
