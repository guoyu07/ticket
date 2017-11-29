package com.ticket.serviceImpl;


import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.MemberDetailInfo;
import com.ticket.service.IMemberDetailInfoService;
import com.ticket.util.DecoderUtil;

/**
 * 会员详细信息业务接口实现类
 * @ClassName: IMemberDetailInfoService   
 * @Description: 提供会员详细信息操作的增删改查   
 * @author HiSay  
 * @date 2015-11-17 17:16:55
 *
 */
public class MemberDetailInfoServiceImpl extends BaseServiceImpl<MemberDetailInfo, String> implements IMemberDetailInfoService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(MemberDetailInfoServiceImpl.class);
	
	@Override
	public boolean merge(String id, String member_id,Integer sex,String passport,String nationality,String MTP,String enterpriseProperty,String companyName,String politicalOutLook,String honor,String rank,String occupation,String income,String nativePlace,String homeAddress,Integer age,Date birthday,String education,String school,String major,String constellation,String zodiac,Integer marriage,String bloodType,Double height,Double weight,String zipcode,String diseaseHistory,String anaphylactogen,String healthStatus,String hobby,String plateNumber,String weChatId,String weiboNumber,String renrenAccount,String baiduAccount,String sogouAccount,String personalitySignature,String emergencyContactName,String emergencyContactSex,String emergencyContactPhone,Integer emergencyContactAge,String emergencyContactIDCard,String emergencyContacthonor,String emergencyContactEmail,String emergencyContactRelation,String visitorCardData, String versionFlag) throws ServiceException {
		MemberDetailInfo memberDetailInfo = dbDAO.queryById(this.getTableNameFromEntity(MemberDetailInfo.class), id);
		memberDetailInfo.setMember_id(DecoderUtil.UtfDecoder(member_id));
		memberDetailInfo.setSex(sex);
		memberDetailInfo.setPassport(DecoderUtil.UtfDecoder(passport));
		memberDetailInfo.setNationality(DecoderUtil.UtfDecoder(nationality));
		memberDetailInfo.setMTP(DecoderUtil.UtfDecoder(MTP));
		memberDetailInfo.setEnterpriseProperty(DecoderUtil.UtfDecoder(enterpriseProperty));
		memberDetailInfo.setCompanyName(DecoderUtil.UtfDecoder(companyName));
		memberDetailInfo.setPoliticalOutLook(DecoderUtil.UtfDecoder(politicalOutLook));
		memberDetailInfo.setHonor(DecoderUtil.UtfDecoder(honor));
		memberDetailInfo.setRank(DecoderUtil.UtfDecoder(rank));
		memberDetailInfo.setOccupation(DecoderUtil.UtfDecoder(occupation));
		memberDetailInfo.setIncome(income);
		memberDetailInfo.setNativePlace(DecoderUtil.UtfDecoder(nativePlace));
		memberDetailInfo.setHomeAddress(DecoderUtil.UtfDecoder(homeAddress));
		memberDetailInfo.setAge(age);
		memberDetailInfo.setBirthday(birthday);
		memberDetailInfo.setEducation(DecoderUtil.UtfDecoder(education));
		memberDetailInfo.setSchool(DecoderUtil.UtfDecoder(school));
		memberDetailInfo.setMajor(DecoderUtil.UtfDecoder(major));
		memberDetailInfo.setConstellation(DecoderUtil.UtfDecoder(constellation));
		memberDetailInfo.setZodiac(DecoderUtil.UtfDecoder(zodiac));
		memberDetailInfo.setMarriage(marriage);
		memberDetailInfo.setBloodType(DecoderUtil.UtfDecoder(bloodType));
		memberDetailInfo.setHeight(height);
		memberDetailInfo.setWeight(weight);
		memberDetailInfo.setZipcode(DecoderUtil.UtfDecoder(zipcode));
		memberDetailInfo.setDiseaseHistory(DecoderUtil.UtfDecoder(diseaseHistory));
		memberDetailInfo.setAnaphylactogen(DecoderUtil.UtfDecoder(anaphylactogen));
		memberDetailInfo.setHealthStatus(DecoderUtil.UtfDecoder(healthStatus));
		memberDetailInfo.setHobby(DecoderUtil.UtfDecoder(hobby));
		memberDetailInfo.setPlateNumber(DecoderUtil.UtfDecoder(plateNumber));
		memberDetailInfo.setWeChatId(DecoderUtil.UtfDecoder(weChatId));
		memberDetailInfo.setWeiboNumber(DecoderUtil.UtfDecoder(weiboNumber));
		memberDetailInfo.setRenrenAccount(DecoderUtil.UtfDecoder(renrenAccount));
		memberDetailInfo.setBaiduAccount(DecoderUtil.UtfDecoder(baiduAccount));
		memberDetailInfo.setSogouAccount(DecoderUtil.UtfDecoder(sogouAccount));
		memberDetailInfo.setPersonalitySignature(DecoderUtil.UtfDecoder(personalitySignature));
		memberDetailInfo.setEmergencyContactName(DecoderUtil.UtfDecoder(emergencyContactName));
		memberDetailInfo.setEmergencyContactSex(DecoderUtil.UtfDecoder(emergencyContactSex));
		memberDetailInfo.setEmergencyContactPhone(DecoderUtil.UtfDecoder(emergencyContactPhone));
		memberDetailInfo.setEmergencyContactAge(emergencyContactAge);
		memberDetailInfo.setEmergencyContactIDCard(DecoderUtil.UtfDecoder(emergencyContactIDCard));
		memberDetailInfo.setEmergencyContacthonor(DecoderUtil.UtfDecoder(emergencyContacthonor));
		memberDetailInfo.setEmergencyContactEmail(DecoderUtil.UtfDecoder(emergencyContactEmail));
		memberDetailInfo.setEmergencyContactRelation(DecoderUtil.UtfDecoder(emergencyContactRelation));
		memberDetailInfo.setVisitorCardData(DecoderUtil.UtfDecoder(visitorCardData));
		CommonEntity status = memberDetailInfo.getStatus();
		status.setVersionFlag(versionFlag);
		memberDetailInfo.setStatus(status);
		dbDAO.merge(memberDetailInfo);
		return true;
	}

	@Override
	public boolean persist(String member_id,Integer sex,String passport,String nationality,String MTP,String enterpriseProperty,String companyName,String politicalOutLook,String honor,String rank,String occupation,String income,String nativePlace,String homeAddress,Integer age,Date birthday,String education,String school,String major,String constellation,String zodiac,Integer marriage,String bloodType,Double height,Double weight,String zipcode,String diseaseHistory,String anaphylactogen,String healthStatus,String hobby,String plateNumber,String weChatId,String weiboNumber,String renrenAccount,String baiduAccount,String sogouAccount,String personalitySignature,String emergencyContactName,String emergencyContactSex,String emergencyContactPhone,Integer emergencyContactAge,String emergencyContactIDCard,String emergencyContacthonor,String emergencyContactEmail,String emergencyContactRelation,String visitorCardData, String versionFlag) throws ServiceException {
		MemberDetailInfo memberDetailInfo = new MemberDetailInfo();
		memberDetailInfo.setMember_id(DecoderUtil.UtfDecoder(member_id));
		memberDetailInfo.setSex(sex);
		memberDetailInfo.setPassport(DecoderUtil.UtfDecoder(passport));
		memberDetailInfo.setNationality(DecoderUtil.UtfDecoder(nationality));
		memberDetailInfo.setMTP(DecoderUtil.UtfDecoder(MTP));
		memberDetailInfo.setEnterpriseProperty(DecoderUtil.UtfDecoder(enterpriseProperty));
		memberDetailInfo.setCompanyName(DecoderUtil.UtfDecoder(companyName));
		memberDetailInfo.setPoliticalOutLook(DecoderUtil.UtfDecoder(politicalOutLook));
		memberDetailInfo.setHonor(DecoderUtil.UtfDecoder(honor));
		memberDetailInfo.setRank(DecoderUtil.UtfDecoder(rank));
		memberDetailInfo.setOccupation(DecoderUtil.UtfDecoder(occupation));
		memberDetailInfo.setIncome(income);
		memberDetailInfo.setNativePlace(DecoderUtil.UtfDecoder(nativePlace));
		memberDetailInfo.setHomeAddress(DecoderUtil.UtfDecoder(homeAddress));
		memberDetailInfo.setAge(age);
		memberDetailInfo.setBirthday(birthday);
		memberDetailInfo.setEducation(DecoderUtil.UtfDecoder(education));
		memberDetailInfo.setSchool(DecoderUtil.UtfDecoder(school));
		memberDetailInfo.setMajor(DecoderUtil.UtfDecoder(major));
		memberDetailInfo.setConstellation(DecoderUtil.UtfDecoder(constellation));
		memberDetailInfo.setZodiac(DecoderUtil.UtfDecoder(zodiac));
		memberDetailInfo.setMarriage(marriage);
		memberDetailInfo.setBloodType(DecoderUtil.UtfDecoder(bloodType));
		memberDetailInfo.setHeight(height);
		memberDetailInfo.setWeight(weight);
		memberDetailInfo.setZipcode(DecoderUtil.UtfDecoder(zipcode));
		memberDetailInfo.setDiseaseHistory(DecoderUtil.UtfDecoder(diseaseHistory));
		memberDetailInfo.setAnaphylactogen(DecoderUtil.UtfDecoder(anaphylactogen));
		memberDetailInfo.setHealthStatus(DecoderUtil.UtfDecoder(healthStatus));
		memberDetailInfo.setHobby(DecoderUtil.UtfDecoder(hobby));
		memberDetailInfo.setPlateNumber(DecoderUtil.UtfDecoder(plateNumber));
		memberDetailInfo.setWeChatId(DecoderUtil.UtfDecoder(weChatId));
		memberDetailInfo.setWeiboNumber(DecoderUtil.UtfDecoder(weiboNumber));
		memberDetailInfo.setRenrenAccount(DecoderUtil.UtfDecoder(renrenAccount));
		memberDetailInfo.setBaiduAccount(DecoderUtil.UtfDecoder(baiduAccount));
		memberDetailInfo.setSogouAccount(DecoderUtil.UtfDecoder(sogouAccount));
		memberDetailInfo.setPersonalitySignature(DecoderUtil.UtfDecoder(personalitySignature));
		memberDetailInfo.setEmergencyContactName(DecoderUtil.UtfDecoder(emergencyContactName));
		memberDetailInfo.setEmergencyContactSex(DecoderUtil.UtfDecoder(emergencyContactSex));
		memberDetailInfo.setEmergencyContactPhone(DecoderUtil.UtfDecoder(emergencyContactPhone));
		memberDetailInfo.setEmergencyContactAge(emergencyContactAge);
		memberDetailInfo.setEmergencyContactIDCard(DecoderUtil.UtfDecoder(emergencyContactIDCard));
		memberDetailInfo.setEmergencyContacthonor(DecoderUtil.UtfDecoder(emergencyContacthonor));
		memberDetailInfo.setEmergencyContactEmail(DecoderUtil.UtfDecoder(emergencyContactEmail));
		memberDetailInfo.setEmergencyContactRelation(DecoderUtil.UtfDecoder(emergencyContactRelation));
		memberDetailInfo.setVisitorCardData(DecoderUtil.UtfDecoder(visitorCardData));
		CommonEntity status = memberDetailInfo.getStatus();
		status.setVersionFlag(versionFlag);
		memberDetailInfo.setStatus(status);
		dbDAO.persist(memberDetailInfo);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		MemberDetailInfo memberDetailInfo = dbDAO.queryById(this.getTableNameFromEntity(MemberDetailInfo.class), id);
		dbDAO.remove(memberDetailInfo);
		return true;
	}

	@Override
	public MemberDetailInfo queryByMemberId(String member_id,String versionFlag) throws ServiceException {
		return dbDAO.queryByCustom(MemberDetailInfo.class.getSimpleName(), deleteFlag, versionFlag, "and s.member_id =?3", new Object[]{member_id});
	}

}