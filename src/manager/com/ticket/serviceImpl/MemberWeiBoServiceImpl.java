package com.ticket.serviceImpl;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.Member;
import com.ticket.pojo.MemberDetailInfo;
import com.ticket.pojo.MemberWeiBo;
import com.ticket.service.IMemberDetailInfoService;
import com.ticket.service.IMemberService;
import com.ticket.service.IMemberWeiBoService;
import com.ticket.service.IQuickMenuMemberSettingService;
import com.ticket.util.AuthCodeUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.MD5Util;
import com.ticket.util.ResourceUtil;
import com.ticket.util.SmsUtil;

public class MemberWeiBoServiceImpl extends BaseServiceImpl<MemberWeiBo, String > implements IMemberWeiBoService{
	@Resource
	private IMemberService memberService = null;
	@Resource
	private IMemberDetailInfoService memberDetailInfoService = null;
	@Resource
	private IQuickMenuMemberSettingService quickMenuMemberSettingService;
	
	@Override
	public MemberWeiBo add(String openId, String nickName, String images,String gender)
			throws ServiceException {
		MemberWeiBo weiBo = new MemberWeiBo();
		weiBo.setOpenid(openId);
		weiBo.setNickName(nickName);
		weiBo.setLoginName(nickName);
		weiBo.setImages(images);
//		weiBo.setLoginPwd(MD5Util.Azdg.strMD5(""));
		weiBo.setMemberType(3);
		dbDAO.persist(weiBo);
		
		MemberDetailInfo detailInfo = null;
		detailInfo = memberDetailInfoService.queryByMemberId(weiBo.getId(), "site");
		int sex = 0;
		if(GeneralUtil.isNotNull(gender)){
			if(gender.equals("男")){
				sex = 1;
			}
		}
		if(detailInfo == null){
			detailInfo = new MemberDetailInfo();
			detailInfo.setMember_id(weiBo.getId());
			detailInfo.setSex(sex);
			memberDetailInfoService.persist(detailInfo);
		}else{
			detailInfo.setMember_id(weiBo.getId());
			detailInfo.setSex(sex);
			memberDetailInfoService.merge(detailInfo);
		}
		return weiBo;
	}

	@Override
	public MemberWeiBo findByScreenName(String screenName) throws ServiceException {
		MemberWeiBo bo = dbDAO.executeJPQLForQuerySingle("select c from " + MemberWeiBo.class.getName() + " c where c.nickName = ?", MemberWeiBo.class, screenName);
		return bo;
	}

	@Override
	public MemberWeiBo findByOpenId(String id) throws ServiceException {
		MemberWeiBo bo = dbDAO.executeJPQLForQuerySingle("select c from " + MemberWeiBo.class.getName() + " c where c.openid = ?", MemberWeiBo.class, id);
		return bo;
	}

	@Override
	public boolean relationMember(String phone) throws ServiceException {
		try {
			Member member = null;
			member = memberService.queryByMobile(phone, "site");
			MemberWeiBo bo = (MemberWeiBo)ActionContext.getContext().getSession().get(ContextConstants.SCOPE_MEMBER);
			if(member != null){
				MemberDetailInfo info = null;
				info = memberDetailInfoService.queryByMemberId(member.getId(), "site");
				if(info == null){
					info = new MemberDetailInfo();
					info.setWeiboNumber(bo.getNickName());
					memberDetailInfoService.persist(info);
				}else{
					if(GeneralUtil.isNotNull(info.getWeiboNumber())){
						return false;
					}else{
						info.setWeiboNumber(bo.getNickName());
						memberDetailInfoService.merge(info);
						bo.setMember(member);
						dbDAO.merge(bo);
					}
				}
				member.setLoginCount(1);
				memberService.merge(member);
			}else{
				member = new Member();
				String pwd = AuthCodeUtil.generate();
				member.setPhone(phone);
				member.setLoginPwd(MD5Util.Azdg.strMD5(pwd));
				member.setImages(bo.getImages());
				member.setLoginCount(1);
				member.setNickName(bo.getNickName());
				
				memberService.persist(member);
				quickMenuMemberSettingService.init(member.getId());
				
				MemberDetailInfo info = null;
				info = memberDetailInfoService.queryByMemberId(member.getId(), "site");
				if(info == null){
					info = new MemberDetailInfo();
					info.setWeiboNumber(bo.getNickName());
					memberDetailInfoService.persist(info);
				}else{
					info.setWeiboNumber(bo.getNickName());
					memberDetailInfoService.merge(info);
				}
				
				bo.setMember(member);
				dbDAO.merge(bo);
				
				SmsUtil.sendSms(phone, ResourceUtil.getText("sms.regist.success",phone,pwd));
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean relationMember(String phone, String id)
			throws ServiceException {
		try {
			Member member = null;
			member = memberService.queryByMobile(phone, "site");
			MemberWeiBo bo = this.queryById(MemberWeiBo.class.getName(), id);
			if(member != null){
				MemberDetailInfo info = null;
				info = memberDetailInfoService.queryByMemberId(member.getId(), "site");
				if(info == null){
					info = new MemberDetailInfo();
					info.setWeiboNumber(bo.getNickName());
					memberDetailInfoService.persist(info);
				}else{
					if(GeneralUtil.isNotNull(info.getWeiboNumber())){
						return false;
					}else{
						info.setWeiboNumber(bo.getNickName());
						memberDetailInfoService.merge(info);
						bo.setMember(member);
						dbDAO.merge(bo);
					}
				}
				member.setLoginCount(1);
				memberService.merge(member);
			}else{
				member = new Member();
				String pwd = AuthCodeUtil.generate();
				member.setPhone(phone);
				member.setLoginPwd(MD5Util.Azdg.strMD5(pwd));
				member.setImages(bo.getImages());
				member.setLoginCount(1);
				member.setNickName(bo.getNickName());
				
				memberService.persist(member);
				quickMenuMemberSettingService.init(member.getId());
				
				MemberDetailInfo info = null;
				info = memberDetailInfoService.queryByMemberId(member.getId(), "site");
				if(info == null){
					info = new MemberDetailInfo();
					info.setWeiboNumber(bo.getNickName());
					memberDetailInfoService.persist(info);
				}else{
					info.setWeiboNumber(bo.getNickName());
					memberDetailInfoService.merge(info);
				}
				
				bo.setMember(member);
				dbDAO.merge(bo);
				
				SmsUtil.sendSms(phone, ResourceUtil.getText("sms.regist.success",phone,pwd));
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
