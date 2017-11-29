package com.ticket.service;

import java.util.Date;
import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.MemberSendInfo;
import com.ticket.pojo.MemberSendInfo.PushMethod;
import com.ticket.pojo.WifiArea;


/**
 * 会员信息发送记录业务接口
 * @ClassName: IMemberSendInfoService   
 * @Description: 提供会员信息发送记录操作的增删改查   
 * @author HiSay  
 * @date  2016-02-03 20:53:58
 *
 */
public interface IMemberSendInfoService extends IBaseService<MemberSendInfo, String> {
	/**
	 * 保存会员信息发送记录实体
	 * @param member_id
	 * @param title
	 * @param message
	 * @param url
	 * @param flightNumber
	 * @param flightDate
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	boolean persist(String member_id, PushMethod method, String massInfo_id,String title,String message, String url, String flightNumber, Date flightDate, String versionFlag);
	
	/**
	 * 保存会员消息
	 * @param member_id
	 * @param method
	 * @param title
	 * @param message
	 * @param url
	 * @return
	 */
	MemberSendInfo persist(String member_id, PushMethod method,String title,String message, String url);
	
	/**
	 * 修改会员信息发送记录实体
	 * @Title: IMemberSendInfoService
	 * @Description:
	 * @param @param member_id  会员id
	 * @param @param phone  电话
	 * @param @param message  信息
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String member_id,String phone,String message, String versionFlag) throws ServiceException;
	
	/**
	 * 移除会员信息发送记录实体
	 * @Title: IMemberSendInfoService
	 * @Description: 
	 * @param @param id 会员信息发送记录ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 查询指定用户的信息
	 * @param member
	 * @return
	 */
	List<MemberSendInfo> query(String member_id);
	
	/**
	 * 查询我的信息
	 * @param member
	 * @return
	 */
	List<MemberSendInfo> queryMy();
	
	/**
	 * 根据当前消息获取下一则消息
	 * @param msg  当前消息
	 * @return
	 * @throws ServiceException
	 */
	MemberSendInfo queryNextMessage(MemberSendInfo msg) throws ServiceException;
	
	/**
	 * 根据当前消息获取上一则消息
	 * @param msg   当前消息
	 * @return
	 * @throws ServiceException
	 */
	MemberSendInfo queryPrevMessage(MemberSendInfo msg) throws ServiceException;
	
	/**
	 * 根据URL获取信息
	 * @param visitUrl
	 * @return
	 * @throws ServiceException
	 */
	MemberSendInfo queryByUrl(String visitUrl) throws ServiceException;
	
	/**
	 * 查询未读的数量
	 * @param member 哪个会员的统计
	 * @return
	 */
	long numberOfUnread(String member_id);
	
	/**
	 * 我账号查询未读的数量
	 * @return
	 */
	long myNumberOfUnread();
	
	/**
	 * wifi推送消息
	 * @param mac mac地址
	 * @param rid wifi设备id
	 */
	void wifiPush(String mac, String rid);
	
	/**
	 * 发送的是否达到间隔时间
	 * @param member_id
	 * @param title
	 * @return
	 */
	boolean sendInterval(WifiArea area, String member_id, String title);
}