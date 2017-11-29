package com.ticket.serviceImpl;

import javax.annotation.Resource;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.Member;
import com.ticket.pojo.MemberDetailInfo;
import com.ticket.pojo.MemberWeixin;
import com.ticket.service.IMemberDetailInfoService;
import com.ticket.service.IMemberService;
import com.ticket.service.IMemberWeixinService;
import com.ticket.service.IQuickMenuMemberSettingService;
import com.ticket.util.AuthCodeUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.MD5Util;
import com.ticket.util.ResourceUtil;
import com.ticket.util.SmsUtil;

public class MemberWeixinServiceImpl extends BaseServiceImpl<MemberWeixin, String> implements IMemberWeixinService{
	
	@Resource private IMemberService memberService = null;
	@Resource private IMemberDetailInfoService memberDetailInfoService = null;
	@Resource
	private IQuickMenuMemberSettingService quickMenuMemberSettingService;
	
	@Override
	public MemberWeixin add(String openId, String nickName, String images)
			throws ServiceException {
		MemberWeixin memberWeixin = new MemberWeixin();
		memberWeixin.setOpenid(openId);
		memberWeixin.setNickName(nickName);
		memberWeixin.setImages(images);
		memberWeixin.setMemberType(4);
		dbDAO.persist(memberWeixin);
		return memberWeixin;
	}

	@Override
	public MemberWeixin queryByOpenId(String openId) throws ServiceException {
		MemberWeixin memberWeixin = dbDAO.executeJPQLForQuerySingle("select c from " + MemberWeixin.class.getName() + " c where c.openid = ?", MemberWeixin.class, openId);
		return memberWeixin;
	}

	@SuppressWarnings("unused")
	@Override
	public boolean relationMember(String phone,String id) throws ServiceException {
		try {
			Member member= null;
			member = memberService.queryByMobile(phone, "site");
			MemberWeixin weixin = this.queryById(MemberWeixin.class.getName(), id);
			
			if(member != null){
				MemberDetailInfo info = null;
				info = memberDetailInfoService.queryByMemberId(member.getId(), "site");
				if(info == null){
					info = new MemberDetailInfo();
					info.setWeChatId(weixin.getNickName());
					memberDetailInfoService.persist(info);
					weixin.setMember(member);
					dbDAO.merge(weixin);
				}else{
					if(GeneralUtil.isNotNull(info.getWeChatId())){
						return false;
					}else{
						info.setWeChatId(weixin.getNickName());
						memberDetailInfoService.merge(info);
						weixin.setMember(member);
						dbDAO.merge(weixin);
					}
				}
				member.setLoginCount(1);
				memberService.merge(member);
			}else{
				member = new Member();
				String pwd = AuthCodeUtil.generate();
				member.setPhone(phone);
				member.setLoginPwd(MD5Util.Azdg.strMD5(pwd));
				member.setImages(weixin.getImages());
				member.setLoginCount(1);
				member.setNickName(weixin.getNickName());
				
				memberService.persist(member);
				quickMenuMemberSettingService.init(member.getId());
				
				MemberDetailInfo info = null;
				info = memberDetailInfoService.queryByMemberId(member.getId(), "site");
				if(info == null){
					info = new MemberDetailInfo();
					info.setWeChatId(weixin.getNickName());
					memberDetailInfoService.persist(info);
				}else{
					info.setWeChatId(weixin.getNickName());
					memberDetailInfoService.merge(info);
				}
				
				weixin.setMember(member);
				dbDAO.merge(weixin);
				
				SmsUtil.sendSms(phone, ResourceUtil.getText("sms.regist.success",phone,pwd));
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
