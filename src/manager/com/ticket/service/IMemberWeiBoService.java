package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.MemberWeiBo;

public interface IMemberWeiBoService extends IBaseService<MemberWeiBo, String>{
	/**
	 * 通过微博id生成一个会员
	 * @param openId
	 * @param nickName
	 * @param images
	 * @return
	 * @throws ServiceException
	 */
	MemberWeiBo add(String openId,String nickName,String images,String gender) throws ServiceException;
	/**
	 * 根据传来的昵称查找会员
	 * @param openId
	 * @return
	 * @throws ServiceException
	 */
	MemberWeiBo findByScreenName(String screenName) throws ServiceException;
	/**
	 * 根据传来的id查找会员
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	MemberWeiBo findByOpenId(String id) throws ServiceException;
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
