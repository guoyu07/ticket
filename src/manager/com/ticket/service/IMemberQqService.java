package com.ticket.service;

import java.rmi.server.ServerCloneException;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.MemberQQ;

public interface IMemberQqService extends IBaseService<MemberQQ, String>{
	/**
	 * 用QQ登录后生成一个会员账号
	 * @param openId
	 * @param nickName
	 * @param gender
	 * @param images
	 * @return
	 * @throws ServerCloneException
	 */
	MemberQQ add(String openId,String nickName,String gender,String images) throws ServiceException;
	/**
	 * 根据QQ openId查找会员
	 * @param openId
	 * @return
	 * @throws ServiceException
	 */
	MemberQQ findByOpenId(String openId) throws ServiceException;
	/**
	 * 绑定会员账号
	 * @param phone
	 * @return
	 * @throws ServiceException
	 */
	boolean relationMember(String phone) throws ServiceException;
	
	/**
	 * app绑定会员账号
	 * @param phone
	 * @return
	 * @throws ServiceException
	 */
	boolean relationMember(String phone,String id) throws ServiceException;
}
