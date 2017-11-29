package com.ticket.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 * 会员详细信息
 * @ClassName: MemberDetailInfo   
 * @Description: 会员详细信息表
 * @author HiSay  
 * @date  2015-11-17 17:16:55
 *
 */
@Entity
@Table(name="ticket_MemberDetailInfo",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class MemberDetailInfo implements Serializable {

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
	 * 性别
	 */
	@Column
	private Integer sex = null;

	/**
	 * 护照信息
	 */
	@Column
	private String passport = null;

	/**
	 * 国籍
	 */
	@Column
	private String nationality = null;

	/**
	 * 台胞证
	 */
	@Column
	private String MTP = null;

	/**
	 * 企业性质
	 */
	@Column
	private String enterpriseProperty = null;

	/**
	 * 公司名称
	 */
	@Column
	private String companyName = null;

	/**
	 * 政治面貌
	 */
	@Column
	private String politicalOutLook = null;

	/**
	 * 头衔
	 */
	@Column
	private String honor = null;

	/**
	 * 职级
	 */
	@Column
	private String rank = null;

	/**
	 * 职位
	 */
	@Column
	private String occupation = null;

	/**
	 * 收入
	 */
	@Column
	private String income = null;

	/**
	 * 籍贯
	 */
	@Column
	private String nativePlace = null;

	/**
	 * 家庭住址
	 */
	@Column
	private String homeAddress = null;

	/**
	 * 年龄
	 */
	@Column
	private Integer age = null;

	/**
	 * 生日
	 */
	@Column
	private Date birthday = null;

	/**
	 * 学历
	 */
	@Column
	private String education = null;

	/**
	 * 学校
	 */
	@Column
	private String school = null;

	/**
	 * 专业
	 */
	@Column
	private String major = null;

	/**
	 * 星座
	 */
	@Column
	private String constellation = null;

	/**
	 * 属相
	 */
	@Column
	private String zodiac = null;

	/**
	 * 婚配
	 */
	@Column
	private Integer marriage = null;

	/**
	 * 血型
	 */
	@Column
	private String bloodType = null;

	/**
	 * 身高
	 */
	@Column
	private Double height = null;

	/**
	 * 体重
	 */
	@Column
	private Double weight = null;

	/**
	 * 邮政编码
	 */
	@Column
	private String zipcode = null;

	/**
	 * 疾病史
	 */
	@Column
	private String diseaseHistory = null;

	/**
	 * 过敏原
	 */
	@Column
	private String anaphylactogen = null;

	/**
	 * 健康状况
	 */
	@Column
	private String healthStatus = null;

	/**
	 * 兴趣点
	 */
	@Column
	private String hobby = null;

	/**
	 * 车牌号
	 */
	@Column
	private String plateNumber = null;

	/**
	 * 微信号
	 */
	@Column
	private String weChatId = null;

	/**
	 * 微博号
	 */
	@Column
	private String weiboNumber = null;

	/**
	 * 人人网账号
	 */
	@Column
	private String renrenAccount = null;

	/**
	 * 百度账号
	 */
	@Column
	private String baiduAccount = null;

	/**
	 * 搜狗账号
	 */
	@Column
	private String sogouAccount = null;

	/**
	 * 个性签名
	 */
	@Column
	private String personalitySignature = null;

	/**
	 * 紧急联系人姓名
	 */
	@Column
	private String emergencyContactName = null;

	/**
	 * 紧急联系人性别
	 */
	@Column
	private String emergencyContactSex = null;

	/**
	 * 紧急联系人电话
	 */
	@Column
	private String emergencyContactPhone = null;

	/**
	 * 紧急联系人年龄
	 */
	@Column
	private Integer emergencyContactAge = null;

	/**
	 * 紧急联系人身份证
	 */
	@Column
	private String emergencyContactIDCard = null;

	/**
	 * 紧急联系人头衔
	 */
	@Column
	private String emergencyContacthonor = null;

	/**
	 * 紧急联系人邮箱
	 */
	@Column
	private String emergencyContactEmail = null;

	/**
	 * 与紧急联系人关系
	 */
	@Column
	private String emergencyContactRelation = null;

	/**
	 * 常旅客卡数据
	 */
	@Column
	private String visitorCardData = null;


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
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getPassport() {
		return passport;
	}
	public void setPassport(String passport) {
		this.passport = passport;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getMTP() {
		return MTP;
	}
	public void setMTP(String MTP) {
		this.MTP = MTP;
	}
	public String getEnterpriseProperty() {
		return enterpriseProperty;
	}
	public void setEnterpriseProperty(String enterpriseProperty) {
		this.enterpriseProperty = enterpriseProperty;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getPoliticalOutLook() {
		return politicalOutLook;
	}
	public void setPoliticalOutLook(String politicalOutLook) {
		this.politicalOutLook = politicalOutLook;
	}
	public String getHonor() {
		return honor;
	}
	public void setHonor(String honor) {
		this.honor = honor;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	public String getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getConstellation() {
		return constellation;
	}
	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}
	public String getZodiac() {
		return zodiac;
	}
	public void setZodiac(String zodiac) {
		this.zodiac = zodiac;
	}
	public Integer getMarriage() {
		return marriage;
	}
	public void setMarriage(Integer marriage) {
		this.marriage = marriage;
	}
	public String getBloodType() {
		return bloodType;
	}
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getDiseaseHistory() {
		return diseaseHistory;
	}
	public void setDiseaseHistory(String diseaseHistory) {
		this.diseaseHistory = diseaseHistory;
	}
	public String getAnaphylactogen() {
		return anaphylactogen;
	}
	public void setAnaphylactogen(String anaphylactogen) {
		this.anaphylactogen = anaphylactogen;
	}
	public String getHealthStatus() {
		return healthStatus;
	}
	public void setHealthStatus(String healthStatus) {
		this.healthStatus = healthStatus;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public String getWeChatId() {
		return weChatId;
	}
	public void setWeChatId(String weChatId) {
		this.weChatId = weChatId;
	}
	public String getWeiboNumber() {
		return weiboNumber;
	}
	public void setWeiboNumber(String weiboNumber) {
		this.weiboNumber = weiboNumber;
	}
	public String getRenrenAccount() {
		return renrenAccount;
	}
	public void setRenrenAccount(String renrenAccount) {
		this.renrenAccount = renrenAccount;
	}
	public String getBaiduAccount() {
		return baiduAccount;
	}
	public void setBaiduAccount(String baiduAccount) {
		this.baiduAccount = baiduAccount;
	}
	public String getSogouAccount() {
		return sogouAccount;
	}
	public void setSogouAccount(String sogouAccount) {
		this.sogouAccount = sogouAccount;
	}
	public String getPersonalitySignature() {
		return personalitySignature;
	}
	public void setPersonalitySignature(String personalitySignature) {
		this.personalitySignature = personalitySignature;
	}
	public String getEmergencyContactName() {
		return emergencyContactName;
	}
	public void setEmergencyContactName(String emergencyContactName) {
		this.emergencyContactName = emergencyContactName;
	}
	public String getEmergencyContactSex() {
		return emergencyContactSex;
	}
	public void setEmergencyContactSex(String emergencyContactSex) {
		this.emergencyContactSex = emergencyContactSex;
	}
	public String getEmergencyContactPhone() {
		return emergencyContactPhone;
	}
	public void setEmergencyContactPhone(String emergencyContactPhone) {
		this.emergencyContactPhone = emergencyContactPhone;
	}
	public Integer getEmergencyContactAge() {
		return emergencyContactAge;
	}
	public void setEmergencyContactAge(Integer emergencyContactAge) {
		this.emergencyContactAge = emergencyContactAge;
	}
	public String getEmergencyContactIDCard() {
		return emergencyContactIDCard;
	}
	public void setEmergencyContactIDCard(String emergencyContactIDCard) {
		this.emergencyContactIDCard = emergencyContactIDCard;
	}
	public String getEmergencyContacthonor() {
		return emergencyContacthonor;
	}
	public void setEmergencyContacthonor(String emergencyContacthonor) {
		this.emergencyContacthonor = emergencyContacthonor;
	}
	public String getEmergencyContactEmail() {
		return emergencyContactEmail;
	}
	public void setEmergencyContactEmail(String emergencyContactEmail) {
		this.emergencyContactEmail = emergencyContactEmail;
	}
	public String getEmergencyContactRelation() {
		return emergencyContactRelation;
	}
	public void setEmergencyContactRelation(String emergencyContactRelation) {
		this.emergencyContactRelation = emergencyContactRelation;
	}
	public String getVisitorCardData() {
		return visitorCardData;
	}
	public void setVisitorCardData(String visitorCardData) {
		this.visitorCardData = visitorCardData;
	}
}
