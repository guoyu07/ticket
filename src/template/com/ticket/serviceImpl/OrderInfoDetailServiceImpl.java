package com.ticket.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjServiceCode;
import com.ticket.pojo.ChannelCustomerInfo;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.Member;
import com.ticket.pojo.OrderInfo;
import com.ticket.pojo.OrderInfoDetail;
import com.ticket.pojo.Pagination;
import com.ticket.service.IBjdjServiceCodeService;
import com.ticket.service.IChannelCustomerInfoService;
import com.ticket.service.IMemberService;
import com.ticket.service.IOrderInfoDetailService;
import com.ticket.service.IQuickMenuMemberSettingService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.MD5Util;
import com.ticket.util.PaginationContext;
import com.ticket.util.SmsUtil;
/**
 * 订单详细业务层实现类
 * @package  com.ticket.serviceImpl
 * @file     OrderInfoDetailServiceImpl.java
 * @author   wangjiafeng
 * @date     2015-12-28 上午10:26:12
 * @version  V 1.0
 */
public class OrderInfoDetailServiceImpl extends BaseServiceImpl<OrderInfoDetail, String> implements IOrderInfoDetailService{
	
	@Resource
	private IChannelCustomerInfoService channelCustomerInfoService;
	@Resource
	private IMemberService memberService;
	@Resource
	private IBjdjServiceCodeService bjdjServiceCodeService;
	@Resource
	private ISystemDictionaryService dictionaryService;
	@Resource
	private IQuickMenuMemberSettingService quickMenuMemberSettingService;
	
	@Override
	public Pagination queryAll(String channelCustomerInfoId, String order,
			Integer pageSize) throws ServiceException {
		StringBuffer sb = new StringBuffer();
		sb.append("from OrderInfoDetail od,OrderInfo o,BjdjServiceCode b where ");
		sb.append("od.orderInfo.id=o.id and o.channelCustomerInfo.id=?1 and b.id=od.bjdjServiceCode.id ");
		List<Object[]> objects = new ArrayList<Object[]>();
		objects.add(new Object[]{1,channelCustomerInfoId});
		if(GeneralUtil.isNull(order)){
			sb.append(" order by od.status.createTime desc");
		}else{
			sb.append(" order by ");
			sb.append(order);
		}
		return dbDAO.queryByPageModuleNew("select od ", sb.toString(), 
					objects, PaginationContext.getOffset(), pageSize);
	}

	@Override
	public List<OrderInfoDetail> getServiceCodeIdsByUnused(String channelCustomerInfoId,
			Boolean memberIsNull,Integer size) throws ServiceException {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("select o from OrderInfoDetail o,OrderInfo oi,BjdjServiceCode b where o.bjdjServiceCode.id=b.id ");
			sb.append("and o.orderInfo.id=oi.id and oi.channelCustomerInfo.id=? and b.state.name='unused' ");
			sb.append("and o.state=0 ");
			if(memberIsNull){
				sb.append("and b.member is null ");
			}
			if(size < 0){
				
				return dbDAO.executeJPQLForQuery(sb.toString(),OrderInfoDetail.class, channelCustomerInfoId);
			}
			return dbDAO.executeJPQLForQuery(sb.toString(),OrderInfoDetail.class, 0, size, channelCustomerInfoId);
		} catch (Exception e) {
			e.printStackTrace() ;
			return null;
		}
	}

	@Override
	public List<OrderInfoDetail> getUnusedCodeListByChannelCustomerInfoLoginId(
			String userName,Boolean memberIsNull, Integer size) throws ServiceException {
		try {
			ChannelCustomerInfo channelCustomerInfo = channelCustomerInfoService.queryByLoginIdExist(userName);
			if(channelCustomerInfo != null){
				return getServiceCodeIdsByUnused(channelCustomerInfo.getId(),memberIsNull, size);
			}else{
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String memberBindServiceCode(String mobile,
			String name,EmployeeInfo employeeInfo) throws ServiceException {
		try {
			if(employeeInfo != null && GeneralUtil.isNull(employeeInfo.getChannelCustomerInfo())){
				return "您没有绑定渠道客户,不能绑定服务码";
			}
			Member member = memberService.queryByMobile(mobile, versionFlag);
			if(member == null){
				member = new Member();
				member.setNickName(name);
				member.setPhone(mobile);
				member.setLoginPwd(MD5Util.Azdg.strMD5("123456"));
				memberService.persist(member);
				quickMenuMemberSettingService.init(member.getId());
			}
			List<OrderInfoDetail> list = getServiceCodeIdsByUnused(employeeInfo.getChannelCustomerInfo().getId() ,true, 1);
			if(list != null && list.size() > 0){
				//将获取到的服务码转赠送到指定会员手里
				BjdjServiceCode bjdjServiceCode = bjdjServiceCodeService
						.queryById(BjdjServiceCode.class.getSimpleName(), list.get(0).getBjdjServiceCode().getId());
				bjdjServiceCode.setMember(member);
				bjdjServiceCodeService.merge(bjdjServiceCode);
				//服务码绑定成功,发送短信通知
				StringBuffer sb = new StringBuffer();
				sb.append("手机号为").append(member.getPhone()).append("的用户,您的便捷登机服务码[").append(bjdjServiceCode.getCode()).append("]已绑定到您的账号,请注意查收");
				SmsUtil.sendSms(member.getPhone(),sb.toString());
				return "true";
			}else{
				return "您没有可绑定的服务码";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "操作错误";
		}
	}

	@Override
	public long buyServiceCodeCount(String channelCustomber_id) {
		
		Long count = dbDAO.executeJPQLForQuerySingle(
				"select count(od) from " + OrderInfoDetail.class.getName() + " od, " + OrderInfo.class.getName() + " o"
				+ " where o.id=od.orderInfo.id and o.channelCustomerInfo.id=?", 
				Long.class, channelCustomber_id);
		return count;
	}

	@Override
	public double buyServiceCodeAmount(String channelCustomber_id) {
		
		double price = Double.parseDouble(dictionaryService.getByName("bjdj_price").getValue());
		return buyServiceCodeCount(channelCustomber_id) * price;
	}

	@Override
	public long serviceCodeValidationCount(String channelCustomber_id) {
		
		Long count = dbDAO.executeJPQLForQuerySingle(
				"select count(od) from " + OrderInfoDetail.class.getName() + " od, " + OrderInfo.class.getName() + " o," + BjdjServiceCode.class.getName() + " sc"
				+ " where o.id=od.orderInfo.id and o.channelCustomerInfo.id=? and sc.id=od.bjdjServiceCode.id and sc.state.name='validated'", 
				Long.class, channelCustomber_id);
		return count;
	}
}
