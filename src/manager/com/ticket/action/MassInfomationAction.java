package com.ticket.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.constants.JQueryUploadConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjAppointment;
import com.ticket.pojo.MassInfomation;
import com.ticket.pojo.Member;
import com.ticket.pojo.MemberFocusFlight;
import com.ticket.pojo.MemberSendInfo.PushMethod;
import com.ticket.service.IBjdjAppointmentService;
import com.ticket.service.IMassInfomationService;
import com.ticket.service.IMemberFocusFlightService;
import com.ticket.service.IMemberSendInfoService;
import com.ticket.service.IMemberService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.DateUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SmsUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 群发信息控制器
 * 
 * @ClassName: MassInfomationAction
 * @Description: 提供群发信息的相关操作方法.
 * @author HiSay
 * @date 2016-02-03 20:46:58
 */
public class MassInfomationAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	// 群发信息的业务层
	@Resource
	private IMassInfomationService massInfomationService;
	// 会员信息的业务层
	@Resource
	private IMemberService memberService;
	// 短信发送记录的业务层
	@Resource
	private IMemberSendInfoService memberSendInfoService;
	@Resource
	private IMemberFocusFlightService memberFocusFlightService;
	@Resource
	private IBjdjAppointmentService appointmentService;

	@Resource
	private ISystemOperationLogService logService = null;
	// 群发信息实体
	private MassInfomation massInfomation;

	// 主键
	private String id;
	// 信息标题
	private String title;
	// 短信内容
	private String content;
	// 短信链接
	private String url;
	// 提醒时间
	private Date reminderTime;
	// 群发对象特性
	private String massObjCharacter;
	// 是否发送短信
	private boolean sendSms;
	// 航班号
	private String flightNumber;
	// 航班日期
	private Date flightDate;

	// 头像
	private Integer photo;
	// 昵称
	private Integer nickName;
	// 姓名
	private Integer name;
	// 身份证
	private Integer idCard;
	// 护照信息
	private Integer passport;
	// 家庭住址
	private Integer homeAddress;
	// 常用地址
	private Integer address;
	// 生日
	private Integer birthday;
	// 个性签名
	private Integer underWrite;
	// 车牌号
	private Integer plateNumber;
	// 公司名称
	private Integer companyName;
	// 学校
	private Integer school;
	// 专业
	private Integer major;
	// 邮箱
	private Integer email;
	// 企业名称
	private Integer enterpriseName;

	// 性别
	private Integer sex;
	// 国籍
	private String nation;
	// 籍贯
	private String nativePlace;
	// 血型
	private String bloodType;
	// 星座
	private String constellation;
	// 属相
	private String zodiac;
	// 婚配
	private String marriage;
	// 兴趣点
	private String hobby;
	// 企业性质
	private String enterpriseProperty;
	// 职业
	private String occupation;
	// 月收入
	private String income;
	// 学历
	private String education;
	// 头衔
	private String honor;
	// 职级
	private String rank;
	// 体重
	private String weight;
	// 身高
	private String height;
	// 年龄
	private String age;

	/**
	 * 添加群发信息
	 * 
	 * @Title: MassInfomationAction
	 * @Description:
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String add() throws ServiceException {
		if (GeneralUtil.isNull(operationFlag)) {
			return "addMassInfomation";
		} else {

			// 非空验证.
			if (GeneralUtil.isNull(title)) {
				data = AjaxData.responseError(getText("title.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(content)) {
				data = AjaxData.responseError(getText("content.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(url)) {
				data = AjaxData.responseError(getText("url.required"));
				return JSON;
			}
			massObjCharacter = "";
			if (GeneralUtil.isNotNull(photo)) {
				massObjCharacter = "photo:" + photo;
			}
			if (GeneralUtil.isNotNull(nickName)) {
				massObjCharacter += ",nickName:" + nickName;
			}
			if (GeneralUtil.isNotNull(name)) {
				massObjCharacter += ",realName:" + name;
			}
			if (GeneralUtil.isNotNull(idCard)) {
				massObjCharacter += ",idCard:" + idCard;
			}
			if (GeneralUtil.isNotNull(passport)) {
				massObjCharacter += ",passport:" + passport;
			}
			if (GeneralUtil.isNotNull(homeAddress)) {
				massObjCharacter += ",homeAddress:" + homeAddress;
			}
			if (GeneralUtil.isNotNull(address)) {
				massObjCharacter += ",contactAddress:" + address;
			}
			if (GeneralUtil.isNotNull(birthday)) {
				massObjCharacter += ",birthday:" + birthday;
			}
			if (GeneralUtil.isNotNull(underWrite)) {
				massObjCharacter += ",underWrite:" + underWrite;
			}
			if (GeneralUtil.isNotNull(plateNumber)) {
				massObjCharacter += ",plateNumber:" + plateNumber;
			}
			if (GeneralUtil.isNotNull(companyName)) {
				massObjCharacter += ",companyName:" + companyName;
			}
			if (GeneralUtil.isNotNull(school)) {
				massObjCharacter += ",school:" + school;
			}
			if (GeneralUtil.isNotNull(major)) {
				massObjCharacter += ",major:" + major;
			}
			if (GeneralUtil.isNotNull(email)) {
				massObjCharacter += ",email:" + email;
			}
			if (GeneralUtil.isNotNull(enterpriseName)) {
				massObjCharacter += ",enterpriseName:" + enterpriseName;
			}
			if (GeneralUtil.isNotNull(sex)) {
				massObjCharacter += ",sex:" + sex;
			}
			if (GeneralUtil.isNotNull(nation)) {
				massObjCharacter += ",nation:" + nation;
			}
			if (GeneralUtil.isNotNull(nativePlace)) {
				massObjCharacter += ",nativePlace:" + nativePlace;
			}
			if (GeneralUtil.isNotNull(bloodType)) {
				massObjCharacter += ",bloodType:" + bloodType;
			}
			if (GeneralUtil.isNotNull(constellation)) {
				massObjCharacter += ",constellation:" + constellation;
			}
			if (GeneralUtil.isNotNull(zodiac)) {
				massObjCharacter += ",zodiac:" + zodiac;
			}
			if (GeneralUtil.isNotNull(marriage)) {
				massObjCharacter += ",marriage:" + marriage;
			}
			if (GeneralUtil.isNotNull(hobby)) {
				massObjCharacter += ",hobby:" + hobby;
			}
			if (GeneralUtil.isNotNull(enterpriseProperty)) {
				massObjCharacter += ",enterpriseProperty:" + enterpriseProperty;
			}
			if (GeneralUtil.isNotNull(occupation)) {
				massObjCharacter += ",occupation:" + occupation;
			}
			if (GeneralUtil.isNotNull(income)) {
				massObjCharacter += ",income:" + income;
			}
			if (GeneralUtil.isNotNull(education)) {
				massObjCharacter += ",education:" + education;
			}
			if (GeneralUtil.isNotNull(honor)) {
				massObjCharacter += ",honor:" + honor;
			}
			if (GeneralUtil.isNotNull(rank)) {
				massObjCharacter += ",rank:" + rank;
			}
			if (GeneralUtil.isNotNull(weight)) {
				massObjCharacter += ",weight:" + weight;
			}
			if (GeneralUtil.isNotNull(height)) {
				massObjCharacter += ",height:" + height;
			}
			if (GeneralUtil.isNotNull(age)) {
				massObjCharacter += ",age:" + age;
			}

			// 保存群发信息实体
			String path = getSinglePictureUrlFromJQueryUpLoader(fileNames,
					directory, JQueryUploadConstants.PICTURE_TYPE_DEFAULT);
			boolean isSuc = massInfomationService.persist(title, content, url,
					massObjCharacter, reminderTime, path, sendSms,
					flightNumber, flightDate, 0);
			// 根据保存结果返回页面
			if (isSuc) {
				String logContent = "新增群发信息";
				String logName = SystemOparetionLogUtil.getLogName();
				String logTime = SystemOparetionLogUtil.getLogTime();
				String logIp = SystemOparetionLogUtil.getLogIp();

				logService.persist(logName, logContent, logTime, logIp,
						versionFlag);
				data = AjaxData.responseSuccess(getText("addSuccess"));
			} else {
				data = AjaxData.responseError(getText("addFailed"));
			}
			return JSON;
		}
	}

	public String addForFlightNoIsNI() throws ServiceException {
		if (GeneralUtil.isNull(operationFlag)) {
			return "addForFlightNoIsNI";
		} else {
			// 非空验证.
			if (GeneralUtil.isNull(title)) {
				data = AjaxData.responseError(getText("title.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(content)) {
				data = AjaxData.responseError(getText("content.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(url)) {
				data = AjaxData.responseError(getText("url.required"));
				return JSON;
			}

			// 保存群发信息实体
			String path = getSinglePictureUrlFromJQueryUpLoader(fileNames,
					directory, JQueryUploadConstants.PICTURE_TYPE_DEFAULT);
			boolean isSuc = massInfomationService.persist(title, content, url,
					null, reminderTime, path, sendSms, flightNumber, null, 1);

			if (isSuc) {
				String logContent = "新增群发信息";
				String logName = SystemOparetionLogUtil.getLogName();
				String logTime = SystemOparetionLogUtil.getLogTime();
				String logIp = SystemOparetionLogUtil.getLogIp();

				logService.persist(logName, logContent, logTime, logIp,
						versionFlag);
				data = AjaxData.responseSuccess(getText("addSuccess"));
			} else {
				data = AjaxData.responseError(getText("addFailed"));
			}
		}
		return JSON;
	}

	/**
	 * 发送消息
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public String sendInfo() throws ServiceException {

		MassInfomation massInfomation = massInfomationService.queryById(MassInfomation.class.getSimpleName(), id);
		if (massInfomation.getType() == 1) {//航班延误
			
			flightNumber = massInfomation.getFlightNumber();
			try {
				List<BjdjAppointment> appointments = appointmentService.getByFlightNo(flightNumber);
				for (BjdjAppointment appointment : appointments) {
					
					Member member = appointment.getMember();
					memberSendInfoService.persist(member.getId(), PushMethod.manual, massInfomation.getId(), massInfomation.getTitle(), massInfomation.getContent(), 
							massInfomation.getUrl(), massInfomation.getFlightNumber(), massInfomation.getFlightDate(), versionFlag);
					if (massInfomation.isSendSms()) {

						SmsUtil.sendSms(member.getPhone(), massInfomation.getContent() + massInfomation.getUrl());
					}
					appointment.setIfSendInfo(1);//已发送信息
					appointmentService.merge(appointment);
				}
				String logContent = "发送群发信息";
				String logName = SystemOparetionLogUtil.getLogName();
				String logTime = SystemOparetionLogUtil.getLogTime();
				String logIp = SystemOparetionLogUtil.getLogIp();

				logService.persist(logName, logContent, logTime, logIp, versionFlag);
				data = AjaxData.responseSuccess(getText("sendSMS.success"));
			} catch (Exception e) {
				
				data = AjaxData.responseError(getText("sendSMS.failed"));
			}
		} else {
			try {
				List<Member> memberList = memberService.queryMemberByCharacter(massInfomation.getMassObjCharacter(), versionFlag);
				for (Member member : memberList) {

					if (massInfomation.getFlightDate() != null && massInfomation.getFlightNumber() != null) { // 关注航班的消息，只发给关注了对应航班的会员

						MemberFocusFlight focusFlight = memberFocusFlightService.query(member.getId(), massInfomation.getFlightNumber(), DateUtil.formatDateToShortString(massInfomation.getFlightDate()));
						if (focusFlight != null) {

							memberSendInfoService.persist(member.getId(), PushMethod.manual, massInfomation.getId(), massInfomation.getTitle(), massInfomation.getContent(), 
									massInfomation.getUrl(), massInfomation.getFlightNumber(), massInfomation.getFlightDate(), versionFlag);
							if (massInfomation.isSendSms()) {

								SmsUtil.sendSms(member.getPhone(), massInfomation.getContent() + massInfomation.getUrl());
							}
						}
					} else {

						memberSendInfoService.persist(member.getId(), PushMethod.manual, massInfomation.getId(), massInfomation.getTitle(), massInfomation.getContent(), 
								massInfomation.getUrl(), massInfomation.getFlightNumber(), massInfomation.getFlightDate(), versionFlag);
						if (massInfomation.isSendSms()) {

							SmsUtil.sendSms(member.getPhone(), massInfomation.getContent() + massInfomation.getUrl());
						}
					}
				}
				String logContent = "发送群发信息";
				String logName = SystemOparetionLogUtil.getLogName();
				String logTime = SystemOparetionLogUtil.getLogTime();
				String logIp = SystemOparetionLogUtil.getLogIp();

				logService.persist(logName, logContent, logTime, logIp, versionFlag);
				data = AjaxData.responseSuccess(getText("sendSMS.success"));
			} catch (Exception e) {
				
				e.printStackTrace();
				data = AjaxData.responseError(getText("sendSMS.failed"));
			}
		}
		return JSON;
	}

	/**
	 * 修改群发信息
	 * 
	 * @Title: MassInfomationAction
	 * @Description:
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String edit() throws ServiceException {
		if (GeneralUtil.isNull(operationFlag)) {

			massInfomation = massInfomationService.queryById(
					MassInfomation.class.getSimpleName(), id);
			return "editMassInfomation";
		} else {
			// 非空验证.
			if (GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(title)) {
				data = AjaxData.responseError(getText("title.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(content)) {
				data = AjaxData.responseError(getText("content.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(url)) {
				data = AjaxData.responseError(getText("url.required"));
				return JSON;
			}
			// 修改群发信息实体
			String path = getSinglePictureUrlFromJQueryUpLoader(fileNames,
					directory, JQueryUploadConstants.PICTURE_TYPE_DEFAULT);
			boolean isSuc = massInfomationService.merge(id, title, content,
					url, massObjCharacter, reminderTime, path, sendSms,
					flightNumber, flightDate);
			// 根据修改结果返回页面
			if (isSuc) {
				String logContent = "修改群发信息";
				String logName = SystemOparetionLogUtil.getLogName();
				String logTime = SystemOparetionLogUtil.getLogTime();
				String logIp = SystemOparetionLogUtil.getLogIp();

				logService.persist(logName, logContent, logTime, logIp,
						versionFlag);
				data = AjaxData.responseSuccess(getText("editSuccess"));
			} else {
				data = AjaxData.responseError(getText("editFailed"));
			}
			return JSON;
		}
	}

	/**
	 * 管理群发信息实体
	 * 
	 * @Title: MassInfomationAction
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0]
				.getMethodName());
		this.setPageModule(massInfomationService.queryEntityByAdmin(
				versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageMassInfomation";
	}

	/**
	 * 查看回收站
	 * 
	 * @Title: MassInfomationAction
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(massInfomationService.queryRecycleEntity(
				versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleMassInfomation";
	}

	/**
	 * 逻辑删除群发信息对象
	 * 
	 * @Title: MassInfomationAction
	 * @Description: 把群发信息对象放入回收站.
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = massInfomationService.logicDeleteEntity(
				MassInfomation.class.getSimpleName(), id);
		if (isSuc) {
			String logContent = "逻辑删除群发信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();

			logService
					.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}

	/**
	 * 物理删除群发信息对象
	 * 
	 * @Title: MassInfomationAction
	 * @Description: 把配置对象实际从数据库里面删除.
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = massInfomationService.remove(id);
		if (isSuc) {
			String logContent = "物理删除群发信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();

			logService
					.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}

	/**
	 * 还原一个群发信息对象
	 * 
	 * @Title: MassInfomationAction
	 * @Description: 从回收站还原群发信息对象
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = massInfomationService.restoreEntity(
				MassInfomation.class.getSimpleName(), id);
		if (isSuc) {
			String logContent = "还原群发信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();

			logService
					.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}

	/**
	 * 审核群发信息对象
	 * 
	 * @Title: MassInfomationAction
	 * @Description:
	 * @param @return
	 * @param @throws ServiceException 设定文件
	 * @return 返回类型
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = massInfomationService.auditEntity(
				MassInfomation.class.getSimpleName(), id, statusValue);
		if (isSuc) {
			String logContent = "审核群发信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();

			logService
					.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}

	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * 
	 * @Title: MassInfomationAction
	 * @Description:
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = massInfomationService.batchOperationEntity(versionFlag,
				idsValue, batchOperationType, isChecked);
		if (isSuc) {
			String logContent = "批量操作群发信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();

			logService
					.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}

	public MassInfomation getMassInfomation() {
		return massInfomation;
	}

	public void setMassInfomation(MassInfomation massInfomation) {
		this.massInfomation = massInfomation;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMassObjCharacter() {
		return massObjCharacter;
	}

	public void setMassObjCharacter(String massObjCharacter) {
		this.massObjCharacter = massObjCharacter;
	}

	public IMassInfomationService getMassInfomationService() {
		return massInfomationService;
	}

	public void setMassInfomationService(
			IMassInfomationService massInfomationService) {
		this.massInfomationService = massInfomationService;
	}

	public Integer getPhoto() {
		return photo;
	}

	public void setPhoto(Integer photo) {
		this.photo = photo;
	}

	public Integer getNickName() {
		return nickName;
	}

	public void setNickName(Integer nickName) {
		this.nickName = nickName;
	}

	public Integer getName() {
		return name;
	}

	public void setName(Integer name) {
		this.name = name;
	}

	public Integer getIdCard() {
		return idCard;
	}

	public void setIdCard(Integer idCard) {
		this.idCard = idCard;
	}

	public Integer getPassport() {
		return passport;
	}

	public void setPassport(Integer passport) {
		this.passport = passport;
	}

	public Integer getHomeAdrress() {
		return homeAddress;
	}

	public void setHomeAddress(Integer homeAddress) {
		this.homeAddress = homeAddress;
	}

	public Integer getAddress() {
		return address;
	}

	public void setAddress(Integer address) {
		this.address = address;
	}

	public Integer getBirthday() {
		return birthday;
	}

	public void setBirthday(Integer birthday) {
		this.birthday = birthday;
	}

	public Integer getUnderWrite() {
		return underWrite;
	}

	public void setUnderWrite(Integer underWrite) {
		this.underWrite = underWrite;
	}

	public Integer getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(Integer plateNumber) {
		this.plateNumber = plateNumber;
	}

	public Integer getCompanyName() {
		return companyName;
	}

	public void setCompanyName(Integer companyName) {
		this.companyName = companyName;
	}

	public Integer getSchool() {
		return school;
	}

	public void setSchool(Integer school) {
		this.school = school;
	}

	public Integer getMajor() {
		return major;
	}

	public void setMajor(Integer major) {
		this.major = major;
	}

	public Integer getEmail() {
		return email;
	}

	public void setEmail(Integer email) {
		this.email = email;
	}

	public Integer getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(Integer enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
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

	public String getMarriage() {
		return marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getEnterpriseProperty() {
		return enterpriseProperty;
	}

	public void setEnterpriseProperty(String enterpriseProperty) {
		this.enterpriseProperty = enterpriseProperty;
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

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
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

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public IMemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(IMemberService memberService) {
		this.memberService = memberService;
	}

	public IMemberSendInfoService getMemberSendInfoService() {
		return memberSendInfoService;
	}

	public void setMemberSendInfoService(
			IMemberSendInfoService memberSendInfoService) {
		this.memberSendInfoService = memberSendInfoService;
	}

	public Date getReminderTime() {
		return reminderTime;
	}

	public void setReminderTime(Date reminderTime) {
		this.reminderTime = reminderTime;
	}

	public Integer getHomeAddress() {
		return homeAddress;
	}

	public boolean isSendSms() {
		return sendSms;
	}

	public void setSendSms(boolean sendSms) {
		this.sendSms = sendSms;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public Date getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(Date flightDate) {
		this.flightDate = flightDate;
	}
}
