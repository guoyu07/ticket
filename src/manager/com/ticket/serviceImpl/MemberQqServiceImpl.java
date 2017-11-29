package com.ticket.serviceImpl;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.Member;
import com.ticket.pojo.MemberDetailInfo;
import com.ticket.pojo.MemberQQ;
import com.ticket.service.IMemberDetailInfoService;
import com.ticket.service.IMemberQqService;
import com.ticket.service.IMemberService;
import com.ticket.service.IQuickMenuMemberSettingService;
import com.ticket.util.AuthCodeUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.MD5Util;
import com.ticket.util.ResourceUtil;
import com.ticket.util.SmsUtil;
 
public class MemberQqServiceImpl extends BaseServiceImpl<MemberQQ, String> implements IMemberQqService{
	@Resource
	private IMemberService memberService = null;
	@Resource
	private IMemberDetailInfoService memberDetailInfoService = null;
	@Resource
	private IQuickMenuMemberSettingService quickMenuMemberSettingService;
	
	@Override
	public MemberQQ add(String openId, String nickName, String gender,String images)
			throws ServiceException {
		MemberQQ memberQQ = new MemberQQ();
//		memberQQ.setLoginPwd(MD5Util.Azdg.strMD5(""));
		memberQQ.setLoginName(nickName);
		memberQQ.setOpenid(openId);
		memberQQ.setNickName(nickName);
		memberQQ.setImages(images);
		memberQQ.setMemberType(2);
		dbDAO.persist(memberQQ);
		
		MemberDetailInfo detailInfo = null;
		detailInfo = memberDetailInfoService.queryByMemberId(memberQQ.getId(), "site");
		if(detailInfo == null){
			detailInfo = new MemberDetailInfo();
			detailInfo.setMember_id(memberQQ.getId());
		}
		int sex = 0;
		if(GeneralUtil.isNotNull(gender)){
			if(gender.equals("男")){
				sex = 1;
			}
		}
		detailInfo.setSex(sex);
		memberDetailInfoService.merge(detailInfo);
		return memberQQ;
	}

	@Override
	public MemberQQ findByOpenId(String openId) throws ServiceException {
		MemberQQ memberQQ = dbDAO.executeJPQLForQuerySingle("select c from " + MemberQQ.class.getName() + " c where c.openid = ?",MemberQQ.class, openId);
		return memberQQ;
	}

	@SuppressWarnings("unused")
	@Override
	public boolean relationMember(String phone) throws ServiceException {
		Member member= null;
		member = memberService.queryByMobile(phone, "site");
		if(GeneralUtil.isNotNull(member.getQq())){
			return false;
		}else{
			
			MemberQQ memberQQ = (MemberQQ)ActionContext.getContext().getSession().get(ContextConstants.SCOPE_MEMBER);
			if(member != null){
				member.setQq(memberQQ.getNickName());
				member.setLoginCount(1);
				memberService.merge(member);
				
				memberQQ.setMember(member);
				dbDAO.merge(memberQQ);
			}else{
				member = new Member();
				String pwd = AuthCodeUtil.generate();
				member.setPhone(phone);
				member.setLoginPwd(MD5Util.Azdg.strMD5(pwd));
				member.setQq(memberQQ.getNickName());
				member.setLoginCount(1);
				member.setImages(memberQQ.getImages());
				memberService.persist(member);
				quickMenuMemberSettingService.init(member.getId());
				
				memberQQ.setMember(member);
				dbDAO.merge(memberQQ);
				
				SmsUtil.sendSms(phone, ResourceUtil.getText("sms.regist.success",phone,pwd));
			}
			return true;
		}
	}

	@SuppressWarnings("unused")
	@Override
	public boolean relationMember(String phone, String id)
			throws ServiceException {
		try {
			Member member= null;
			member = memberService.queryByMobile(phone, "site");
			if(GeneralUtil.isNotNull(member.getQq())){
				return false;
			}else{
				
				MemberQQ memberQQ = this.queryById(MemberQQ.class.getName(), id);
				if(member != null){
					member.setQq(memberQQ.getNickName());
					member.setLoginCount(1);
					memberService.merge(member);
					
					memberQQ.setMember(member);
					dbDAO.merge(memberQQ);
				}else{
					member = new Member();
					String pwd = AuthCodeUtil.generate();
					member.setPhone(phone);
					member.setLoginCount(1);
					member.setLoginPwd(MD5Util.Azdg.strMD5(pwd));
					member.setQq(memberQQ.getNickName());
					member.setImages(memberQQ.getImages());
					memberService.persist(member);
					quickMenuMemberSettingService.init(member.getId());
					
					memberQQ.setMember(member);
					dbDAO.merge(memberQQ);
					
					SmsUtil.sendSms(phone, ResourceUtil.getText("sms.regist.success",phone,pwd));
				}
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
