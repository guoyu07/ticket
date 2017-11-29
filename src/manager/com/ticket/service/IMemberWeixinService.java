package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.MemberWeixin;

public interface IMemberWeixinService extends IBaseService<MemberWeixin, String>{
	
	/**
	 * 添加一个微信会员
	 * @param openId
	 * @param nickName
	 * @param images
	 * @return
	 */
	MemberWeixin add(String openId,String nickName,String images) throws ServiceException;
	
	/**
	 * 通过openId查找微信会员
	 * @param openId
	 * @return
	 */
	MemberWeixin queryByOpenId(String openId) throws ServiceException;
	
	/**
	 * 绑定会员账号
	 * @param phone
	 * @return
	 * @throws ServiceException
	 */
	boolean relationMember(String phone,String id) throws ServiceException;
	
}
