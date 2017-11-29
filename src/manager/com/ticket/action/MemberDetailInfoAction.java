package com.ticket.action;

import java.util.Date;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.MemberDetailInfo;
import com.ticket.service.IMemberDetailInfoService;
import com.ticket.util.GeneralUtil;

/**
 * 会员详细信息控制器
 * @ClassName: MemberDetailInfoAction   
 * @Description:  提供会员详细信息的相关操作方法. 
 * @author HiSay  
 * @date 2015-11-17 17:16:55
 *
 */
public class MemberDetailInfoAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//会员详细信息的业务层
	@Resource private IMemberDetailInfoService memberDetailInfoService = null;
	//会员详细信息实体
	private MemberDetailInfo memberDetailInfo = null;
	//主键
	private String id = null;
    //会员id
	private String member_id = null;
    //性别
	private Integer sex = null;
    //护照信息
	private String passport = null;
    //国籍
	private String nationality = null;
    //台胞证
	private String MTP = null;
    //企业性质
	private String enterpriseProperty = null;
    //公司名称
	private String companyNAme = null;
    //政治面貌
	private String politicalOutLook = null;
    //头衔
	private String honor = null;
    //职级
	private String rank = null;
    //职位
	private String occupation = null;
    //收入
	private String income = null;
    //籍贯
	private String nativePlace = null;
    //家庭住址
	private String homeAddress = null;
    //年龄
	private Integer age = null;
    //生日
	private Date birthday = null;
    //学历
	private String education = null;
    //学校
	private String school = null;
    //专业
	private String major = null;
    //星座
	private String constellation = null;
    //属相
	private String zodiac = null;
    //婚配
	private Integer marriage = null;
    //血型
	private String bloodType = null;
    //身高
	private Double height = null;
    //体重
	private Double weight = null;
    //邮政编码
	private String zipcode = null;
    //疾病史
	private String diseaseHistory = null;
    //过敏原
	private String anaphylactogen = null;
    //健康状况
	private String healthStatus = null;
    //兴趣点
	private String hobby = null;
    //车牌号
	private String plateNumber = null;
    //微信号
	private String weChatId = null;
    //微博号
	private String weiboNumber = null;
    //人人网账号
	private String renrenAccount = null;
    //百度账号
	private String baiduAccount = null;
    //搜狗账号
	private String sogouAccount = null;
    //个性签名
	private String personalitySignature = null;
    //紧急联系人姓名
	private String emergencyContactName = null;
    //紧急联系人性别
	private String emergencyContactSex = null;
    //紧急联系人电话
	private String emergencyContactPhone = null;
    //紧急联系人年龄
	private Integer emergencyContactAge = null;
    //紧急联系人身份证
	private String emergencyContactIDCard = null;
    //紧急联系人头衔
	private String emergencyContacthonor = null;
    //紧急联系人邮箱
	private String emergencyContactEmail = null;
    //与紧急联系人关系
	private String emergencyContactRelation = null;
    //常旅客卡数据
	private String visitorCardData = null;
	
	/**
	 * 添加会员详细信息
	 * @Title: MemberDetailInfoAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addMemberDetailInfo";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(member_id)) {
				data = AjaxData.responseError(getText("member_id.required"));
				return JSON;
			}
			//保存会员详细信息实体
			boolean isSuc = memberDetailInfoService.persist(member_id, sex, passport, nationality, MTP, enterpriseProperty, companyNAme, politicalOutLook, honor, rank, occupation, income, nativePlace, homeAddress, age, birthday, education, school, major, constellation, zodiac, marriage, bloodType, height, weight, zipcode, diseaseHistory, anaphylactogen, healthStatus, hobby, plateNumber, weChatId, weiboNumber, renrenAccount, baiduAccount, sogouAccount, personalitySignature, emergencyContactName, emergencyContactSex, emergencyContactPhone, emergencyContactAge, emergencyContactIDCard, emergencyContacthonor, emergencyContactEmail, emergencyContactRelation, visitorCardData, versionFlag);
			//根据保存结果返回页面
			if(isSuc) {
				data = AjaxData.responseSuccess(getText("addSuccess"));
			} else {
				data = AjaxData.responseError(getText("addFailed"));
			}
			return JSON;
		}
	}
	
	/**
	 * 修改会员详细信息
	 * @Title: MemberDetailInfoAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setMemberDetailInfo(memberDetailInfoService.queryById(MemberDetailInfo.class.getSimpleName(), id));
			return "editMemberDetailInfo";
		} else {
			//非空验证.
			//修改会员详细信息实体
			boolean isSuc = memberDetailInfoService.merge(id, member_id, sex, passport, nationality, MTP, enterpriseProperty, companyNAme, politicalOutLook, honor, rank, occupation, income, nativePlace, homeAddress, age, birthday, education, school, major, constellation, zodiac, marriage, bloodType, height, weight, zipcode, diseaseHistory, anaphylactogen, healthStatus, hobby, plateNumber, weChatId, weiboNumber, renrenAccount, baiduAccount, sogouAccount, personalitySignature, emergencyContactName, emergencyContactSex, emergencyContactPhone, emergencyContactAge, emergencyContactIDCard, emergencyContacthonor, emergencyContactEmail, emergencyContactRelation, visitorCardData,  versionFlag);
			//根据修改结果返回页面
			if(isSuc) {
				data = AjaxData.responseSuccess(getText("editSuccess"));
			} else {
				data = AjaxData.responseError(getText("editFailed"));
			}
			return JSON;
		}
	}
	
	/**
	 * 管理会员详细信息实体
	 * @Title: MemberDetailInfoAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(memberDetailInfoService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageMemberDetailInfo";
	}
	
	/**
	 * 查看回收站
	 * @Title: MemberDetailInfoAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(memberDetailInfoService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleMemberDetailInfo";
	}
	
	/**
	 * 逻辑删除会员详细信息对象
	 * @Title: MemberDetailInfoAction
	 * @Description: 把会员详细信息对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = memberDetailInfoService.logicDeleteEntity(MemberDetailInfo.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除会员详细信息对象
	 * @Title: MemberDetailInfoAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = memberDetailInfoService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个会员详细信息对象
	 * @Title: MemberDetailInfoAction
	 * @Description: 从回收站还原会员详细信息对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = memberDetailInfoService.restoreEntity(MemberDetailInfo.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核会员详细信息对象
	 * @Title: MemberDetailInfoAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = memberDetailInfoService.auditEntity(MemberDetailInfo.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: MemberDetailInfoAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = memberDetailInfoService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public MemberDetailInfo getMemberDetailInfo() {
		return memberDetailInfo;
	}
	public void setMemberDetailInfo(MemberDetailInfo memberDetailInfo) {
		this.memberDetailInfo = memberDetailInfo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getCompanyNAme() {
		return companyNAme;
	}
	public void setCompanyNAme(String companyNAme) {
		this.companyNAme = companyNAme;
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
