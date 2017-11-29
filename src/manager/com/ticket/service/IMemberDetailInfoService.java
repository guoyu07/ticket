package com.ticket.service;

import java.util.Date;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.MemberDetailInfo;


/**
 * 会员详细信息业务接口
 * @ClassName: IMemberDetailInfoService   
 * @Description: 提供会员详细信息操作的增删改查   
 * @author HiSay  
 * @date  2015-11-17 17:16:55
 *
 */
public interface IMemberDetailInfoService extends IBaseService<MemberDetailInfo, String> {
	/**
	 * 保存会员详细信息实体
	 * @Title: IMemberDetailInfoService
	 * @Description:
	 * @param @param member_id  会员id
	 * @param @param sex  性别
	 * @param @param passport  护照信息
	 * @param @param nationality  国籍
	 * @param @param MTP  台胞证
	 * @param @param enterpriseProperty  企业性质
	 * @param @param companyNAme  公司名称
	 * @param @param politicalOutLook  政治面貌
	 * @param @param honor  头衔
	 * @param @param rank  职级
	 * @param @param occupation  职位
	 * @param @param income  收入
	 * @param @param nativePlace  籍贯
	 * @param @param homeAddress  家庭住址
	 * @param @param age  年龄
	 * @param @param birthday  生日
	 * @param @param education  学历
	 * @param @param school  学校
	 * @param @param major  专业
	 * @param @param constellation  星座
	 * @param @param zodiac  属相
	 * @param @param marriage  婚配
	 * @param @param bloodType  血型
	 * @param @param height  身高
	 * @param @param weight  体重
	 * @param @param zipcode  邮政编码
	 * @param @param diseaseHistory  疾病史
	 * @param @param anaphylactogen  过敏原
	 * @param @param healthStatus  健康状况
	 * @param @param hobby  兴趣点
	 * @param @param plateNumber  车牌号
	 * @param @param weChatId  微信号
	 * @param @param weiboNumber  微博号
	 * @param @param renrenAccount  人人网账号
	 * @param @param baiduAccount  百度账号
	 * @param @param sogouAccount  搜狗账号
	 * @param @param personalitySignature  个性签名
	 * @param @param emergencyContactName  紧急联系人姓名
	 * @param @param emergencyContactSex  紧急联系人性别
	 * @param @param emergencyContactPhone  紧急联系人电话
	 * @param @param emergencyContactAge  紧急联系人年龄
	 * @param @param emergencyContactIDCard  紧急联系人身份证
	 * @param @param emergencyContacthonor  紧急联系人头衔
	 * @param @param emergencyContactEmail  紧急联系人邮箱
	 * @param @param emergencyContactRelation  与紧急联系人关系
	 * @param @param visitorCardData  常旅客卡数据
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String member_id,Integer sex,String passport,String nationality,String MTP,String enterpriseProperty,String companyNAme,String politicalOutLook,String honor,String rank,String occupation,String income,String nativePlace,String homeAddress,Integer age,Date birthday,String education,String school,String major,String constellation,String zodiac,Integer marriage,String bloodType,Double height,Double weight,String zipcode,String diseaseHistory,String anaphylactogen,String healthStatus,String hobby,String plateNumber,String weChatId,String weiboNumber,String renrenAccount,String baiduAccount,String sogouAccount,String personalitySignature,String emergencyContactName,String emergencyContactSex,String emergencyContactPhone,Integer emergencyContactAge,String emergencyContactIDCard,String emergencyContacthonor,String emergencyContactEmail,String emergencyContactRelation,String visitorCardData, String versionFlag) throws ServiceException;
	
	/**
	 * 修改会员详细信息实体
	 * @Title: IMemberDetailInfoService
	 * @Description:
	 * @param @param member_id  会员id
	 * @param @param sex  性别
	 * @param @param passport  护照信息
	 * @param @param nationality  国籍
	 * @param @param MTP  台胞证
	 * @param @param enterpriseProperty  企业性质
	 * @param @param companyNAme  公司名称
	 * @param @param politicalOutLook  政治面貌
	 * @param @param honor  头衔
	 * @param @param rank  职级
	 * @param @param occupation  职位
	 * @param @param income  收入
	 * @param @param nativePlace  籍贯
	 * @param @param homeAddress  家庭住址
	 * @param @param age  年龄
	 * @param @param birthday  生日
	 * @param @param education  学历
	 * @param @param school  学校
	 * @param @param major  专业
	 * @param @param constellation  星座
	 * @param @param zodiac  属相
	 * @param @param marriage  婚配
	 * @param @param bloodType  血型
	 * @param @param height  身高
	 * @param @param weight  体重
	 * @param @param zipcode  邮政编码
	 * @param @param diseaseHistory  疾病史
	 * @param @param anaphylactogen  过敏原
	 * @param @param healthStatus  健康状况
	 * @param @param hobby  兴趣点
	 * @param @param plateNumber  车牌号
	 * @param @param weChatId  微信号
	 * @param @param weiboNumber  微博号
	 * @param @param renrenAccount  人人网账号
	 * @param @param baiduAccount  百度账号
	 * @param @param sogouAccount  搜狗账号
	 * @param @param personalitySignature  个性签名
	 * @param @param emergencyContactName  紧急联系人姓名
	 * @param @param emergencyContactSex  紧急联系人性别
	 * @param @param emergencyContactPhone  紧急联系人电话
	 * @param @param emergencyContactAge  紧急联系人年龄
	 * @param @param emergencyContactIDCard  紧急联系人身份证
	 * @param @param emergencyContacthonor  紧急联系人头衔
	 * @param @param emergencyContactEmail  紧急联系人邮箱
	 * @param @param emergencyContactRelation  与紧急联系人关系
	 * @param @param visitorCardData  常旅客卡数据
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String member_id,Integer sex,String passport,String nationality,String MTP,String enterpriseProperty,String companyNAme,String politicalOutLook,String honor,String rank,String occupation,String income,String nativePlace,String homeAddress,Integer age,Date birthday,String education,String school,String major,String constellation,String zodiac,Integer marriage,String bloodType,Double height,Double weight,String zipcode,String diseaseHistory,String anaphylactogen,String healthStatus,String hobby,String plateNumber,String weChatId,String weiboNumber,String renrenAccount,String baiduAccount,String sogouAccount,String personalitySignature,String emergencyContactName,String emergencyContactSex,String emergencyContactPhone,Integer emergencyContactAge,String emergencyContactIDCard,String emergencyContacthonor,String emergencyContactEmail,String emergencyContactRelation,String visitorCardData, String versionFlag) throws ServiceException;
	
	/**
	 * 移除会员详细信息实体
	 * @Title: IMemberDetailInfoService
	 * @Description: 
	 * @param @param id 会员详细信息ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 根据会员id查询会员详细信息
	 * @param member_id 会员id
	 * @param versionFlag 
	 * @return
	 * @throws ServiceException
	 */
	MemberDetailInfo queryByMemberId(String member_id, String versionFlag) throws ServiceException;
}