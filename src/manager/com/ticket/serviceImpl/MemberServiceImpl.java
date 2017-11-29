package com.ticket.serviceImpl;


import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.ChannelCustomerInfo;
import com.ticket.pojo.CheckinInfo;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.Member;
import com.ticket.pojo.MemberDetailInfo;
import com.ticket.pojo.MemberFocusFlight;
import com.ticket.pojo.Pagination;
import com.ticket.service.ICheckinInfoService;
import com.ticket.service.ICommonAnnexService;
import com.ticket.service.IMemberFocusFlightService;
import com.ticket.service.IMemberService;
import com.ticket.service.IQuickMenuMemberSettingService;
import com.ticket.util.AuthCodeUtil;
import com.ticket.util.DateUtil;
import com.ticket.util.DecoderUtil;
import com.ticket.util.EmailUtil;
import com.ticket.util.FileUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.MD5Util;
import com.ticket.util.PaginationContext;
import com.ticket.util.ResourceUtil;
import com.ticket.util.SmsUtil;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * 会员信息业务接口实现类
 * @ClassName: IMemberService   
 * @Description: 提供会员信息操作的增删改查   
 * @author HiSay  
 * @date 2015-11-13 18:01:16
 *
 */
public class MemberServiceImpl extends BaseServiceImpl<Member, String> implements IMemberService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(MemberServiceImpl.class);
	@Resource
	protected ICommonAnnexService commonAnnexService;
	@Resource IMemberFocusFlightService memberFocusFlightService;
	@Resource ICheckinInfoService checkinInfoService;
	@Resource
	private IQuickMenuMemberSettingService quickMenuMemberSettingService;
	
	@Override
	public boolean merge(String id, String memberLevel_id,String loginName,String loginPwd,String nickName,String realName,String IDCard,String phone,String qq,String email,String address,Integer record,String experience,String visitorType, String versionFlag) throws ServiceException {
		Member member = dbDAO.queryById(this.getTableNameFromEntity(Member.class), id);
		member.setMemberLevel_id(DecoderUtil.UtfDecoder(memberLevel_id));
		member.setLoginName(DecoderUtil.UtfDecoder(loginName));
		
		if(!member.getLoginPwd().equals(loginPwd)){
			
			member.setLoginPwd(MD5Util.Azdg.strMD5(loginPwd));
		}
		member.setNickName(DecoderUtil.UtfDecoder(nickName));
		member.setRealName(DecoderUtil.UtfDecoder(realName));
		member.setIDCard(DecoderUtil.UtfDecoder(IDCard));
		member.setPhone(DecoderUtil.UtfDecoder(phone));
		member.setQq(DecoderUtil.UtfDecoder(qq));
		member.setEmail(DecoderUtil.UtfDecoder(email));
		member.setAddress(DecoderUtil.UtfDecoder(address));
		member.setRecord(record);
		member.setExperience(DecoderUtil.UtfDecoder(experience));
		member.setVisitorType(DecoderUtil.UtfDecoder(visitorType));
		dbDAO.merge(member);
		return true;
	}

	@Override
	public boolean persist(String memberLevel_id,String loginName,String loginPwd,String nickName,String realName,String IDCard,String phone,String qq,String email,String address,Integer record,String experience,String visitorType, String versionFlag) throws ServiceException {
		Member member = new Member();
		member.setMemberLevel_id(DecoderUtil.UtfDecoder(memberLevel_id));
		member.setLoginName(DecoderUtil.UtfDecoder(loginName));
		member.setLoginPwd(MD5Util.Azdg.strMD5(loginPwd));
		member.setNickName(DecoderUtil.UtfDecoder(nickName));
		member.setRealName(DecoderUtil.UtfDecoder(realName));
		member.setIDCard(DecoderUtil.UtfDecoder(IDCard));
		member.setPhone(DecoderUtil.UtfDecoder(phone));
		member.setQq(DecoderUtil.UtfDecoder(qq));
		member.setEmail(DecoderUtil.UtfDecoder(email));
		member.setAddress(DecoderUtil.UtfDecoder(address));
		member.setRecord(record);
		member.setExperience(DecoderUtil.UtfDecoder(experience));
		member.setVisitorType(DecoderUtil.UtfDecoder(visitorType));
		CommonEntity status = member.getStatus();
		status.setVersionFlag(versionFlag);
		member.setStatus(status);
		dbDAO.persist(member);
		quickMenuMemberSettingService.init(member.getId());
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		Member member = dbDAO.queryById(this.getTableNameFromEntity(Member.class), id);
		dbDAO.remove(member);
		return true;
	}
	
	@Override
	public boolean batchRealDelete( String idsValue,String versionFlag) throws ServiceException {
		idsValue = GeneralUtil.convertIdsValueToStringByDelete(idsValue, ",");
		dbDAO.deleteAll(Member.class.getSimpleName(), idsValue, null);
		return true;
	}

	@Override
	public Pagination queryPageModuleByKeyword(String keyword, Integer pageSize, String versionFlag) throws ServiceException {
		keyword = DecoderUtil.UtfDecoder(keyword);
		return dbDAO.queryByPageModule(Member.class.getSimpleName(), deleteFlag, versionFlag, "and (s.phone like ?3 or s.realName like ?4 or s.phone like ?5 or s.IDCard like ?6)", new Object[]{"%"+keyword+"%","%"+keyword+"%","%"+keyword+"%","%"+keyword+"%"}, orderBy, PaginationContext.getOffset(), pageSize);
	}

	@Override
	public List<Member> getByColumn(String columnName, String value) {
		
		String tableName = Member.class.getName();
		List<Member> list = dbDAO.executeJPQLForQueryEntity(
				"select s from " + tableName + " s where " + columnName + "=?", value);
		return list;
	}

	@Override
	public Member queryByLoginnameAndLoginpwd(String loginName, String loginPwd) throws ServiceException{
		
		String tableName = Member.class.getName();
		Member user = (Member)dbDAO.executeJPQLForQuerySingle(
				"select s from " + tableName + " s where loginName=? and loginPwd=?", loginName, MD5Util.Azdg.strMD5(loginPwd));
		return user;
	}
	
	@Override
	public Member queryByEmailAndLoginpwd(String email, String loginPwd) throws ServiceException{
		
		String tableName = Member.class.getName();
		Member user = (Member)dbDAO.executeJPQLForQuerySingle(
				"select s from " + tableName + " s where email=? and loginPwd=?", email, MD5Util.Azdg.strMD5(loginPwd));
		return user;
	}

	@Override
	public boolean validePhoneExist(String phone, String versionFlag)
			throws ServiceException {
		Member member = dbDAO.queryByCustom(Member.class.getSimpleName(), deleteFlag, versionFlag, "and s.phone=?3", new Object[]{phone});
		if(member != null){
			return true;
		}
		return false;
	}
	
	@Override
	public Member queryByMobileAndPwd(String phone,String password,String versionFlag) throws ServiceException{
		return dbDAO.queryByCustom(Member.class.getSimpleName(), deleteFlag, versionFlag, "and s.phone =?3 and s.loginPwd =?4", new Object[]{phone,MD5Util.Azdg.strMD5(password)});
	}

	@Override
	public Member queryByMobile(String mobile, String versionFlag) {
		return dbDAO.queryByCustom(Member.class.getSimpleName(), deleteFlag, versionFlag, "and s.phone =?3", new Object[]{mobile});
	}
	
	@Override
	public Member queryByUrl(String url) throws ServiceException {
		return dbDAO.queryByCustom(Member.class.getSimpleName(), deleteFlag, versionFlag, "and s.status.visitUrl =?3", new Object[]{Long.valueOf(url)});
	}

	@Override
	public Member generateByMobileAndSendSms(String mobile) throws ServiceException {
		
		return generateByMobileAndSendSms(mobile, true);
	}
	
	@Override
	public Member generateByMobileAndSendSms(String mobile, boolean autoLogin) throws ServiceException {
		
		Member member = null; 
		
		List<Member> members = getByColumn("phone", mobile);
		if(members.size() == 0){
			
			member = new Member();
			member.setPhone(mobile);
			
			String password = AuthCodeUtil.generate();
			member.setLoginPwd(MD5Util.Azdg.strMD5(password));
			dbDAO.persist(member);
			quickMenuMemberSettingService.init(member.getId());
			
			SmsUtil.sendSms(mobile, ResourceUtil.getText("sms.regist.success", mobile, password));
		}else{
			
			member = members.get(0);
		}
		
		if(autoLogin){
			
			getSession().put(ContextConstants.SCOPE_MEMBER, member);
		}
		return member;
	}

	@Override
	public Member generateByEmailAndSendSms(String email) {
		
		Member member = null; 
		
		List<Member> members = getByColumn("email", email);
		if(members.size() == 0){
			
			member = new Member();
			member.setEmail(email);
			
			String password = AuthCodeUtil.generate();
			member.setLoginPwd(MD5Util.Azdg.strMD5(password));
			dbDAO.persist(member);
			
			quickMenuMemberSettingService.init(member.getId());
			
			String emailTitle = ResourceUtil.getText("SMS_SIGN");
			String emailContent = ResourceUtil.getText("sms.regist.success", email, password);
			EmailUtil.send(email, emailTitle, emailContent);
		}else{
			
			member = members.get(0);
		}
		
		return member;
	}

	@Override
	public Member getByIdCard(String idCard) {
		
		String tableName = Member.class.getName();
		Member user = (Member)dbDAO.executeJPQLForQuerySingle(
				"select s from " + tableName + " s where idCard=?", idCard);
		return user;
	}

	@Override
	public Member queryByMixAndPwd(String mix, String password)
			throws ServiceException {
		
		Member emp = queryByLoginnameAndLoginpwd(mix, password);
		if(emp == null){
			
			emp = queryByEmailAndLoginpwd(mix, password);
		}
		if(emp == null){
			
			emp = queryByMobileAndPwd(mix, password, versionFlag);
		}
		return emp;
	}

	@Override
	public Boolean add(String memberLevelId, String loginName, String loginPwd,
			String nickName, String realName, String IDCard, String phone,
			String qq, String email, String address, String channelCustomerInfoId)
			throws ServiceException {
		try {
			Member member = new Member();
			member.setMemberLevel_id(memberLevelId);
			member.setLoginName(loginName);
			member.setLoginPwd(MD5Util.Azdg.strMD5(loginPwd));
			member.setNickName(nickName);
			member.setRealName(realName);
			member.setIDCard(IDCard);
			member.setPhone(phone);
			member.setQq(qq);
			member.setEmail(email);
			member.setAddress(address);
			member.setChannelCustomerInfo(get(ChannelCustomerInfo.class, channelCustomerInfoId));
			this.persist(member);
			quickMenuMemberSettingService.init(member.getId());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean update(String id, String memberLevelId, String loginName,
			String loginPwd, String nickName, String realName, String IDCard,
			String phone, String qq, String email, String address) throws ServiceException {
		try {
			Member member = this.queryById(Member.class.getSimpleName(), id);
			member.setMemberLevel_id(memberLevelId);
			member.setLoginName(loginName);
			member.setLoginPwd(MD5Util.Azdg.strMD5(loginPwd));
			member.setNickName(nickName);
			member.setRealName(realName);
			member.setIDCard(IDCard);
			member.setPhone(phone);
			member.setQq(qq);
			member.setEmail(email);
			member.setAddress(address);
			this.merge(member);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean validateEmailExist(String email) throws ServiceException {
		try {
			String sql = "select m from Member m where m.email=?1 ";
			List<Object[]> list = new ArrayList<Object[]>();
			list.add(new Object[]{1,email});
			Member member = dbDAO.executeJPQLForQuerySingle(sql, Member.class, list.toArray());
			if(member != null){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean validateLoginNameExist(String loginName)
			throws ServiceException {
		try {
			String sql = "select m from Member m where m.loginName=?1 ";
			List<Object[]> list = new ArrayList<Object[]>();
			list.add(new Object[]{1,loginName});
			Member member = dbDAO.executeJPQLForQuerySingle(sql, Member.class, list.toArray());
			if(member != null){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Pagination queryByChannelCustomerInfo(String channelCustomerInfoId,
			String keyword, Integer pageSize) throws ServiceException {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("");
			List<Object[]> objects = new ArrayList<Object[]>();
			if(GeneralUtil.isNotNull(channelCustomerInfoId)){
				sb.append("and s.channelCustomerInfo_id=?3 ");
				objects.add(new Object[]{3,channelCustomerInfoId});
			}
			if(GeneralUtil.isNotNull(keyword)){
				sb.append("and (s.phone like ?4 or s.email like ?5 or s.loginName like?6 or s.nickName like ?7 or s.realName like ?8 or s.IDCard like ?9 )");
				objects.add(new Object[]{4,"%"+keyword+"%"});
				objects.add(new Object[]{5,"%"+keyword+"%"});
				objects.add(new Object[]{6,"%"+keyword+"%"});
				objects.add(new Object[]{7,"%"+keyword+"%"});
				objects.add(new Object[]{8,"%"+keyword+"%"});
				objects.add(new Object[]{9,"%"+keyword+"%"});
			}
			return dbDAO.queryByPageModuleNew(Member.class.getSimpleName(), deleteFlag, versionFlag,
					sb.toString(),objects, orderBy, PaginationContext.getOffset(), pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Member> queryByChannelCustomerInfoList(
			String channelCustomerInfoId, String keyword, Integer size)
			throws ServiceException {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("");
			List<Object[]> objects = new ArrayList<Object[]>();
			if(GeneralUtil.isNotNull(channelCustomerInfoId)){
				sb.append("and s.channelCustomerInfo_id=?3 ");
				objects.add(new Object[]{3,channelCustomerInfoId});
			}
			if(GeneralUtil.isNotNull(keyword)){
				sb.append("and (s.phone like ?4 or s.realName like?5 or s.loginName like ?6 or s.IDCard like ?7 )");
				objects.add(new Object[]{4,"%"+keyword+"%"});
				objects.add(new Object[]{5,"%"+keyword+"%"});
				objects.add(new Object[]{6,"%"+keyword+"%"});
				objects.add(new Object[]{7,"%"+keyword+"%"});
			}
			return dbDAO.queryByListNew(Member.class.getSimpleName(), deleteFlag, versionFlag,
					sb.toString(), objects, orderBy, size);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Member> batchLeadingInMember(String channelCustomerInfo_id,HttpServletRequest request,
			String file) throws ServiceException {
		try {
			List<Member> list = new ArrayList<Member>();
			file = request.getSession().getServletContext().getRealPath("") +file;
			file = file.replace("\\", "/");//linux 路径兼容写法
			File f = new File(file);
			FileInputStream fis = new FileInputStream(f);
	        Workbook rwb = Workbook.getWorkbook(fis);      
            Sheet rs = rwb.getSheet(0);  
            Member member = null;
	        for (int j = 1; j < rs.getRows(); j++) {   
               Cell[] cells = rs.getRow(j);   
               cells[0].getContents().trim();
               member = new Member();
               if(GeneralUtil.isNotNull(cells[1].getContents())){
            	   member.setLoginName(cells[1].getContents().trim());
               }
               if(GeneralUtil.isNotNull(cells[2].getContents())){
            	   member.setLoginPwd(MD5Util.Azdg.strMD5(cells[2].getContents().trim()));
               }
               if(GeneralUtil.isNotNull(cells[3].getContents())){
            	   member.setNickName(cells[3].getContents().trim());
               }
               if(GeneralUtil.isNotNull(cells[4].getContents())){
            	   member.setRealName(cells[4].getContents().trim());
               }
               if(GeneralUtil.isNotNull(cells[5].getContents())){
            	   member.setIDCard(cells[5].getContents().trim());
               }
               if(GeneralUtil.isNotNull(cells[6].getContents())){
            	   member.setPhone(cells[6].getContents().trim());
               }
               if(GeneralUtil.isNotNull(cells[7].getContents())){
            	   member.setQq(cells[7].getContents().trim());
               }
               if(GeneralUtil.isNotNull(cells[8].getContents())){
            	   member.setEmail(cells[8].getContents().trim());
               }
               if(GeneralUtil.isNotNull(cells[9].getContents())){
            	   member.setAddress(cells[9].getContents().trim());
               }
               member.setChannelCustomerInfo(get(ChannelCustomerInfo.class, channelCustomerInfo_id));
               if(validateLoginNameExist(member.getLoginName()) || 
            		   validePhoneExist(member.getPhone(),versionFlag) || 
            		   		validateEmailExist(member.getEmail())){
            	   if(GeneralUtil.isNotNull(cells[0].getContents())){
            		   member.setId(cells[0].getContents().trim());
            	   }
            	   if(validateLoginNameExist(member.getLoginName())){
            		   member.setLoginName("用户名重复");
            	   }
            	   if(validePhoneExist(member.getPhone(),versionFlag)){
            		   member.setPhone("手机号码重复");
            	   }
            	   if(validateEmailExist(member.getEmail())){
            		   member.setEmail("电子邮箱重复");
            	   }
            	   list.add(member);
               }else{
            	   this.merge(member);
               }
               member = null;
	        }  
	        fis.close();
	        FileUtil.deleteFile(file);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Member> queryMemberByCharacter(String massObjCharacter, String versionFlag) {
		String sql = "select m from "+Member.class.getName()+" m,"
				+ MemberDetailInfo.class.getName()+" md"
				+ " where m.id=md.member_id ";
		String[] character = massObjCharacter.split(",");
		for(int i=0;i<character.length;i++){
			if(character[i].contains("photo")){
				String[] str = character[i].split(":");
				if("1".equals(str[1])){
					sql += " and m.images is not null ";
				}
			}else if(character[i].contains("nickName")){
				String[] str = character[i].split(":");
				if("1".equals(str[1])){
					sql += " and m.nickName is not null ";
				}
			}else if(character[i].contains("realName")){
				String[] str = character[i].split(":");
				if("1".equals(str[1])){
					sql += " and m.realName is not null ";
				}
			}else if(character[i].contains("idCard")){
				String[] str = character[i].split(":");
				if("1".equals(str[1])){
					sql += " and m.IDCard is not null ";
				}
			}else if(character[i].contains("passport")){
				String[] str = character[i].split(":");
				if("1".equals(str[1])){
					sql += " and md.passport is not null ";
				}
			}else if(character[i].contains("homeAddress")){
				String[] str = character[i].split(":");
				if("1".equals(str[1])){
					sql += " and md.homeAddress is not null ";
				}
			}else if(character[i].contains("contactAddress")){
				String[] str = character[i].split(":");
				if("1".equals(str[1])){
					sql += " and m.address is not null ";
				}
			}else if(character[i].contains("birthday")){
				String[] str = character[i].split(":");
				if("1".equals(str[1])){
					sql += " and md.birthday is not null ";
				}
			}else if(character[i].contains("underWrite")){
				String[] str = character[i].split(":");
				if("1".equals(str[1])){
					sql += " and md.personalitySignature is not null ";
				}
			}else if(character[i].contains("plateNumber")){
				String[] str = character[i].split(":");
				if("1".equals(str[1])){
					sql += " and md.plateNumber is not null ";
				}
			}else if(character[i].contains("companyName")){
				String[] str = character[i].split(":");
				if("1".equals(str[1])){
					sql += " and md.companyName is not null ";
				}
			}else if(character[i].contains("school")){
				String[] str = character[i].split(":");
				if("1".equals(str[1])){
					sql += " and md.school is not null ";
				}
			}else if(character[i].contains("major")){
				String[] str = character[i].split(":");
				if("1".equals(str[1])){
					sql += " and md.major is not null ";
				}
			}else if(character[i].contains("email")){
				String[] str = character[i].split(":");
				if("1".equals(str[1])){
					sql += " and m.email is not null ";
				}
			}else if(character[i].contains("enterpriseName")){
				String[] str = character[i].split(":");
				if("1".equals(str[1])){
					sql += " and md.companyName is not null ";
				}
			}
			/***********************************************/
			else if(character[i].contains("sex")){
				String[] str = character[i].split(":");
				if("1".equals(str[1])){
					sql += " and md.sex=1 ";
				}else if("0".equals(str[1])){
					sql += " and md.sex=0 ";
				}
			}else if(character[i].contains("nation")){
				String[] str = character[i].split(":");
				sql += " and md.nationality = '"+str[1]+"'";
			}else if(character[i].contains("nativePlace")){
				String[] str = character[i].split(":");
				sql += " and md.nativePlace = '"+str[1]+"'";
			}else if(character[i].contains("bloodType")){
				String[] str = character[i].split(":");
				sql += " and md.bloodType = '"+str[1]+"'";
			}else if(character[i].contains("constellation")){
				String[] str = character[i].split(":");
				sql += " and md.constellation = '"+str[1]+"'";
			}else if(character[i].contains("zodiac")){
				String[] str = character[i].split(":");
				sql += " and md.zodiac = '"+str[1]+"'";
			}else if(character[i].contains("marriage")){
				String[] str = character[i].split(":");
				if("1".equals(str[1])){
					sql += " and md.marriage ='1' ";
				}else{
					sql += " and md.marriage ='0' ";
				}
			}else if(character[i].contains("hobby")){
				String[] str = character[i].split(":");
				sql += " and md.hobby = '"+str[1]+"'";
			}else if(character[i].contains("enterpriseProperty")){
				String[] str = character[i].split(":");
				sql += " and md.enterpriseProperty = '"+str[1]+"'";
			}else if(character[i].contains("occupation")){
				String[] str = character[i].split(":");
				sql += " and md.occupation = '"+str[1]+"'";
			}else if(character[i].contains("income")){
				String[] str = character[i].split(":");
				sql += " and md.income = '"+str[1]+"'";
			}else if(character[i].contains("education")){
				String[] str = character[i].split(":");
				sql += " and md.education = '"+str[1]+"'";
			}else if(character[i].contains("honor")){
				String[] str = character[i].split(":");
				sql += " and md.honor = '"+str[1]+"'";
			}else if(character[i].contains("rank")){
				String[] str = character[i].split(":");
				sql += " and md.rank = '"+str[1]+"'";
			}else if(character[i].contains("weight")){
				String[] str = character[i].split(":");
				if("40kg以下".equals(str[1])){
					
					sql += " and md.weight < 40";
				}else if("40kg-49kg".equals(str[1])){
					
					sql += " and md.weight <= 49 and md.weight >= 40";
				}else if("50kg-59kg".equals(str[1])){
					
					sql += " and md.weight <= 59 and md.weight >= 50";
				}else if("60kg-69kg".equals(str[1])){
					
					sql += " and md.weight <= 69 and md.weight >= 60";
				}else if("70kg-79kg".equals(str[1])){
					
					sql += " and md.weight <= 79 and md.weight >= 70";
				}else if("80kg-100kg".equals(str[1])){
					
					sql += " and md.weight <= 100 and md.weight >= 80";
				}else if("100kg以上".equals(str[1])){
					
					sql += " and md.weight > 100";
				}
			}else if(character[i].contains("height")){
				String[] str = character[i].split(":");
				if("140cm以下".equals(str[1])){
					
					sql += " and md.height < 140";
				}else if("140cm-159cm".equals(str[1])){
					
					sql += " and md.height <= 159 and md.height >= 140";
				}else if("160cm-169cm".equals(str[1])){
					
					sql += " and md.height <= 169 and md.height >= 160";
				}else if("170cm-179cm".equals(str[1])){
					
					sql += " and md.height <= 179 and md.height >= 170";
				}else if("180cm-189cm".equals(str[1])){
					
					sql += " and md.height <= 189 and md.height >= 180";
				}else if("190cm以上".equals(str[1])){
					
					sql += " and md.height >= 190";
				}
			}else if(character[i].contains("age")){
				String[] str = character[i].split(":");
				if("19岁以下".equals(str[1])){
					
					sql += " and md.age < 19";
				}else if("20-29岁".equals(str[1])){
					
					sql += " and md.age >= 20 and md.age <= 29";
				}else if("30-39岁".equals(str[1])){
					
					sql += " and md.age >= 30 and md.age <= 39";
				}else if("40-49岁".equals(str[1])){
					
					sql += " and md.age >= 40 and md.age <= 49";
				}else if("50岁以上".equals(str[1])){
					
					sql += " and md.age >= 50";
				}
			}
		}
		List<Member> list = dbDAO.executeJPQLForQuery(sql,Member.class);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public Member queryByPhone(String mobile, String versionFlag)
			throws ServiceException {
		return dbDAO.queryByCustom(Member.class.getSimpleName(), deleteFlag, versionFlag, "and s.phone=?3", new Object[]{mobile});
	}

	@Override
	public void clearShareMember() throws ServiceException {
		List<Member> list = dbDAO.queryByList(Member.class.getSimpleName(), deleteFlag, versionFlag, "and s.share=1 and s.status.createTime<?3", new Object[]{DateUtil.getDateToThisDateStart("1", new Date())}, orderBy, null);
		if(list != null && !list.isEmpty()){
			for(Member member : list){
				if(GeneralUtil.isNull(member.getLoginCount())){
					List<MemberFocusFlight> focusList = memberFocusFlightService.queryListByMemberId(member.getId());
					if(focusList != null && !focusList.isEmpty()){
						for(MemberFocusFlight mf : focusList){
							memberFocusFlightService.remove(mf);
						}
					}
					List<CheckinInfo> checkList = checkinInfoService.queryByMemberId(member);
					if(checkList != null && !checkList.isEmpty()){
						for(CheckinInfo chi :checkList){
							checkinInfoService.remove(chi);
						}
					}
					dbDAO.remove(member);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println(DateUtil.getDateToThisDateStart("3", new Date()));
	}
}