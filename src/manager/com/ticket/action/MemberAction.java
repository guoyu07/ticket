package com.ticket.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.Member;
import com.ticket.pojo.MemberDetailInfo;
import com.ticket.pojo.MemberInfo;
import com.ticket.pojo.SqlParam.Condition;
import com.ticket.pojo.SqlParamGroup;
import com.ticket.service.IMemberDetailInfoService;
import com.ticket.service.IMemberService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.DateUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

import net.sf.jxls.transformer.XLSTransformer;

/**
 * 会员信息控制器
 * @ClassName: MemberAction   
 * @Description:  提供会员信息的相关操作方法. 
 * @author HiSay  
 * @date 2015-11-13 18:01:16
 *
 */
public class MemberAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//会员信息的业务层
	@Resource private IMemberService memberService = null;
	@Resource private IMemberDetailInfoService detailInfoService = null;
	@Resource private ISystemOperationLogService logService = null;
	//会员信息实体
	private Member member = null;
	//主键
	private String id = null;
    //会员等级
	private String memberLevel_id = null;
    //登录名
	private String loginName = null;
    //登录密码
	private String loginPwd = null;
    //昵称
	private String nickName = null;
    //真实姓名
	private String realName = null;
    //身份证号
	private String IDCard = null;
    //联系电话
	private String phone = null;
    //QQ号码
	private String qq = null;
    //联系邮箱
	private String email = null;
    //联系地址
	private String address = null;
    //积分
	private Integer record = null;
    //经验值
	private String experience = null;
    //旅客类型
	private String visitorType = null;
	//查询关键词
	private String keyword = null;
	
	private InputStream inputStream;
	
	private String fileName;
	//创建时间
	private Date startTime, endTime;
	
	/**
	 * 添加会员信息
	 * @Title: MemberAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addMember";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(memberLevel_id)) {
				data = AjaxData.responseError(getText("memberLevel_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(loginName)) {
				data = AjaxData.responseError(getText("loginName.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(loginPwd)) {
				data = AjaxData.responseError(getText("loginPwd.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(nickName)) {
				data = AjaxData.responseError(getText("nickName.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(realName)) {
				data = AjaxData.responseError(getText("realName.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(IDCard)) {
				data = AjaxData.responseError(getText("IDCard.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(phone)) {
				data = AjaxData.responseError(getText("phone.required"));
				return JSON;
			}
			/*if(GeneralUtil.isNull(qq)) {
				data = AjaxData.responseError(getText("qq.required"));
				return JSON;
			}*/
			if(GeneralUtil.isNull(email)) {
				data = AjaxData.responseError(getText("email.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(address)) {
				data = AjaxData.responseError(getText("address.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(record)) {
				data = AjaxData.responseError(getText("record.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(experience)) {
				data = AjaxData.responseError(getText("experience.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(visitorType)) {
				data = AjaxData.responseError(getText("visitorType.required"));
				return JSON;
			}
			//保存会员信息实体
			boolean isSuc = memberService.persist(memberLevel_id, loginName, loginPwd, nickName, realName, IDCard, phone, qq, email, address, record, experience, visitorType, versionFlag);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增会员信息";
				String logName = SystemOparetionLogUtil.getLogName();
				String logTime = SystemOparetionLogUtil.getLogTime();
				String logIp = SystemOparetionLogUtil.getLogIp();
				
				logService.persist(logName, logContent, logTime, logIp, versionFlag);
				data = AjaxData.responseSuccess(getText("addSuccess"));
			} else {
				data = AjaxData.responseError(getText("addFailed"));
			}
			return JSON;
		}
	}
	
	/**
	 * 修改会员信息
	 * @Title: MemberAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setMember(memberService.queryById(Member.class.getSimpleName(), id));
			return "editMember";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(memberLevel_id)) {
				data = AjaxData.responseError(getText("memberLevel_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(loginName)) {
				data = AjaxData.responseError(getText("loginName.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(loginPwd)) {
				data = AjaxData.responseError(getText("loginPwd.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(nickName)) {
				data = AjaxData.responseError(getText("nickName.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(realName)) {
				data = AjaxData.responseError(getText("realName.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(IDCard)) {
				data = AjaxData.responseError(getText("IDCard.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(phone)) {
				data = AjaxData.responseError(getText("phone.required"));
				return JSON;
			}
			/*if(GeneralUtil.isNull(qq)) {
				data = AjaxData.responseError(getText("qq.required"));
				return JSON;
			}*/
			if(GeneralUtil.isNull(email)) {
				data = AjaxData.responseError(getText("email.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(address)) {
				data = AjaxData.responseError(getText("address.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(record)) {
				data = AjaxData.responseError(getText("record.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(experience)) {
				data = AjaxData.responseError(getText("experience.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(visitorType)) {
				data = AjaxData.responseError(getText("visitorType.required"));
				return JSON;
			}
			//修改会员信息实体
			boolean isSuc = memberService.merge(id, memberLevel_id, loginName, loginPwd, nickName, realName, IDCard, phone, qq, email, address, record, experience, visitorType,  versionFlag);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改会员信息";
				String logName = SystemOparetionLogUtil.getLogName();
				String logTime = SystemOparetionLogUtil.getLogTime();
				String logIp = SystemOparetionLogUtil.getLogIp();
				
				logService.persist(logName, logContent, logTime, logIp, versionFlag);
				data = AjaxData.responseSuccess(getText("editSuccess"));
			} else {
				data = AjaxData.responseError(getText("editFailed"));
			}
			return JSON;
		}
	}
	
	/**
	 * 管理会员信息实体
	 * @Title: MemberAction
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		SqlParamGroup group = new SqlParamGroup("s.status.createTime", Condition.ge, DateUtil.getDayStart(startTime));
		group = group.and("s.status.createTime", Condition.le, DateUtil.getDayEnd(endTime));
		group = group.and("s.status.deleteFlag", Condition.eq, ContextConstants.STATUS_OF_ZERO);
		group = group.and("s.status.versionFlag", Condition.eq, versionFlag);
		this.setPageModule(memberService.paginationQuery(
				"select s from " + Member.class.getName() + " s " + group.toString(true) + " order by s.status.createTime desc"
				, group.getParamArray()));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageMember";
	}
	
	/**
	 * 查看回收站
	 * @Title: MemberAction
	 * @param @return
	 * @param @throws ServiceException   
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(memberService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleMember";
	}
	
	/**
	 * 逻辑删除会员信息对象
	 * @Title: MemberAction
	 * @Description: 把会员信息对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = memberService.logicDeleteEntity(Member.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除会员信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除会员信息对象
	 * @Title: MemberAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = memberService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个会员信息对象
	 * @Title: MemberAction
	 * @Description: 从回收站还原会员信息对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = memberService.restoreEntity(Member.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原会员信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核会员信息对象
	 * @Title: MemberAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = memberService.auditEntity(Member.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核会员信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: MemberAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = memberService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作会员信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	/**
	 * 下载会员信息
	 * @method batchDownLoad
	 * @throws Exception
	 * @return String
	 * @date 2016-3-15 上午10:33:36
	 */
//	public String batchDownLoad() throws Exception{
//		
//		SqlParamGroup group = new SqlParamGroup("s.status.createTime", Condition.ge, DateUtil.getDayStart(startTime)).and("s.status.createTime", Condition.le, DateUtil.getDayEnd(endTime));
//		group = group.and("s.status.deleteFlag", Condition.eq, ContextConstants.STATUS_OF_ZERO);
//		group = group.and("s.status.versionFlag", Condition.eq, versionFlag);
//		List<Member> list = memberService.getDbDAO().executeJPQLForQuery(
//				"select s from " + Member.class.getName() + " s " + group.toString(true) + " order by s.status.createTime desc", 
//				group.getParamArray());
//		exportExcel("/WEB-INF/excel/jasper/member.jasper", list, "会员用户");
//		return JSON;
//	}
	
	/**
	 * 下载会员信息
	 * @method batchDownLoad
	 * @throws Exception
	 * @return String
	 * @date 2016-3-15 上午10:33:36
	 */
	public String batchDownLoad() throws Exception{
		
		String logContent = "下载会员信息";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		String destFilePath = createJxls();
		File file = new File(destFilePath);
		fileName = file.getName();
		inputStream = new FileInputStream(file);
		return "download";
	}
	
	public String createJxls() throws Exception{
//		if(GeneralUtil.isNull(idsValue)){
//			return JSON;
//		}
//		String[] ids = idsValue.split(",");
		
		List<Member> members = memberService.queryAll(Member.class);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<MemberInfo> memberInfo = new ArrayList<MemberInfo>();
		for(Member member: members){
//			Member member = memberService.queryById(Member.class.getName(), id);
			
			MemberDetailInfo detailInfo = detailInfoService.queryByMemberId(member.getId(), versionFlag);
			
			MemberInfo info = new MemberInfo();
			
			String memberLoginName = member.getLoginName();
			String memberLoginPwd = member.getLoginPwd();
			String memberRealName = member.getRealName();
			String memberIDCard = member.getIDCard();
			String memberNickName = member.getNickName();
			String memberQq = member.getQq();
			String memberPhone = member.getPhone();
			String memberAddress = member.getAddress();//常用住址
			String memberEmail = member.getEmail();
			Date createTime = member.getStatus().getCreateTime();
			String memberCreateTime = sdf.format(createTime);
			
			info.setMemberCreateTime(memberCreateTime);
			info.setMemberAddress(memberAddress);
			info.setMemberIDCard(memberIDCard);
			info.setMemberLoginName(memberLoginName);
			info.setMemberLoginPwd(memberLoginPwd);
			info.setMemberEmail(memberEmail);
			info.setMemberPhone(memberPhone);
			info.setMemberQq(memberQq);
			info.setMemberNickName(memberNickName);
			info.setMemberRealName(memberRealName);
			
			if(detailInfo != null){
				String memberPassPort = detailInfo.getPassport();
				String memberNationality = detailInfo.getNationality();//国籍
				String memberNativePlace = detailInfo.getNativePlace();//籍贯
				String memberHomeAddress = detailInfo.getHomeAddress();//家庭住址
				Integer age = detailInfo.getAge();
				String memberAge = "";
				if(GeneralUtil.isNotNull(age)){
					memberAge = age.toString();
				}
				Date birthday = detailInfo.getBirthday();
				//转换生日
				String memberBirthday = "";
				if(GeneralUtil.isNotNull(birthday)){
					memberBirthday = sdf.format(birthday);
				}
				String memberBloodType = detailInfo.getBloodType();
				String memberConstellation = detailInfo.getConstellation();//星座
				String memberZodiac = detailInfo.getZodiac();//属相
				Integer marriage = detailInfo.getMarriage();
				String memberMarriage = "";
				if(GeneralUtil.isNotNull(marriage)){
					if(marriage == 1){
						memberMarriage = "已婚";
					}else{
						memberMarriage = "未婚";
					}
				}
				Double height = detailInfo.getHeight();
				String memberHeight = "";
				if(GeneralUtil.isNotNull(height)){
					memberHeight = height.toString();
				}
				Double weight = detailInfo.getWeight();
				String memberWeight = "";
				if(GeneralUtil.isNotNull(weight)){
					memberWeight = weight.toString();
				}
				String memberHobby = detailInfo.getHobby();//兴趣
				String memberPlateNumber = detailInfo.getPlateNumber();//车牌号码
				String memberPersonalitySignature = detailInfo.getPersonalitySignature();//个性签名
				String memberCompanyName = detailInfo.getCompanyName();
				String memberEnterpriseProperty = detailInfo.getEnterpriseProperty();//企业性质
				String memberHonor = detailInfo.getHonor();//头衔
				String memberRank = detailInfo.getRank();//职级
				String memberOccupation = detailInfo.getOccupation();//职位
				String memberIncome = detailInfo.getIncome();//月收入
				String memberEducation = detailInfo.getEducation();//学历
				String memberSchool = detailInfo.getSchool();
				String memberMajor = detailInfo.getMajor();//专业
				String memberWeChatId = detailInfo.getWeChatId();//微信
				String memberWeiboNumber = detailInfo.getWeiboNumber();//微博
				String memberBaiduAccount = detailInfo.getBaiduAccount();
				Integer sex = detailInfo.getSex();
				String memberSex = "";
				if(GeneralUtil.isNotNull(sex)){
					if(sex == 1){
						memberSex = "男";
					}else{
						memberSex = "女";
					}
				}
				info.setMemberAge(memberAge);
				info.setMemberBaiduAccount(memberBaiduAccount);
				info.setMemberBirthday(memberBirthday);
				info.setMemberBloodType(memberBloodType);
				info.setMemberCompanyName(memberCompanyName);
				info.setMemberConstellation(memberConstellation);
				info.setMemberEducation(memberEducation);
				info.setMemberEnterpriseProperty(memberEnterpriseProperty);
				info.setMemberHeight(memberHeight);
				info.setMemberHobby(memberHobby);
				info.setMemberHomeAddress(memberHomeAddress);
				info.setMemberHonor(memberHonor);
				info.setMemberIncome(memberIncome);
				info.setMemberMajor(memberMajor);
				info.setMemberMarriage(memberMarriage);
				info.setMemberNationality(memberNationality);
				info.setMemberNativePlace(memberNativePlace);
				info.setMemberOccupation(memberOccupation);
				info.setMemberPassPort(memberPassPort);
				info.setMemberPersonalitySignature(memberPersonalitySignature);
				info.setMemberPlateNumber(memberPlateNumber);
				info.setMemberRank(memberRank);
				info.setMemberSchool(memberSchool);
				info.setMemberSex(memberSex);
				info.setMemberWeChatId(memberWeChatId);
				info.setMemberWeiboNumber(memberWeiboNumber);
				info.setMemberWeight(memberWeight);
				info.setMemberZodiac(memberZodiac);
			}
			
			memberInfo.add(info);
		}
		
		String rootPath = ServletActionContext.getRequest().getRealPath("/manager");
		String srcFilePath = rootPath + "/memberExcel/member.xls";  
		String destFilePath = rootPath + "/memberExcelTemplate/member.xls";
		Map<String, List<MemberInfo>> beanParams = new HashMap<String, List<MemberInfo>>();
		beanParams.put("members", memberInfo);
		
		
		XLSTransformer former = new XLSTransformer();
		former.transformXLS(srcFilePath, beanParams,destFilePath);
		return destFilePath;
	}
	/**
	 * 批量物理删除会员信息
	 * @return
	 * @throws ServiceException
	 */
	public String batchRealDelete()throws ServiceException{
		boolean isSuc = memberService.batchRealDelete(idsValue, versionFlag);
		if(isSuc) {
			String logContent = "批量删除会员信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	/**
	 * 根据手机号、姓名、身份证、编号检索会员信息
	 * @return
	 * @throws ServiceException
	 */
	public String search() throws ServiceException{
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(memberService.queryPageModuleByKeyword(keyword, ContextConstants.ADMIN_COMMON_PAGE_SIZE, versionFlag));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageMember";
	}
	
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public IMemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(IMemberService memberService) {
		this.memberService = memberService;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
