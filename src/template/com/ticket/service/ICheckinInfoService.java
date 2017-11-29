package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CheckinInfo;
import com.ticket.pojo.Member;


/**
 * 值机信息表业务接口
 * @ClassName: ICheckinInfoService   
 * @Description: 提供值机信息表操作的增删改查   
 * @author HiSay  
 * @date  2016-02-24 16:09:34
 *
 */
public interface ICheckinInfoService extends IBaseService<CheckinInfo, String> {
	/**
	 * 保存值机信息表实体
	 * @Title: ICheckinInfoService
	 * @Description:
	 * @param @param member  会员id
	 * @param @param flightNumber  航班编号
	 * @param @param flightDate  航班日期
	 * @param @param mobile  联系电话
	 * @param @param ticketNo  电子客票号
	 * @param @param seatNo  座位号
	 * @param @param couponId  值机标识
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	CheckinInfo persist(Member member,String flightNumber,String flightDate,String mobile,String ticketNo,String seatNo,String couponId, String sendMobile) throws ServiceException;
	
	/**
	 * 修改值机信息表实体
	 * @Title: ICheckinInfoService
	 * @Description:
	 * @param @param member  会员id
	 * @param @param flightNumber  航班编号
	 * @param @param flightDate  航班日期
	 * @param @param mobile  联系电话
	 * @param @param ticketNo  电子客票号
	 * @param @param seatNo  座位号
	 * @param @param couponId  值机标识
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	CheckinInfo merge(String id, Member member,String flightNumber,String flightDate,String mobile,String ticketNo,String seatNo,String couponId, String versionFlag) throws ServiceException;
	
	/**
	 * 移除值机信息表实体
	 * @Title: ICheckinInfoService
	 * @Description: 
	 * @param @param id 值机信息表ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 通过航班号和航班日期获取值机信息
	 * @param flightNo 航班号
	 * @param flightDate 航班日期
	 * @return
	 */
	List<CheckinInfo> query(String flightNo, String flightDate);

	/**
	 * 根据会员查询值机信息
	 * @param member 会员实体
	 * @param flightNumber 航班号
	 * @param flightDate 航班日期
	 * @return
	 * @throws ServiceException
	 */
	CheckinInfo queryByMember(Member member, String flightNumber, String flightDate) throws ServiceException;

	/**
	 * 查询会员的值机信息
	 * @param member 会员
	 * @return
	 * @throws ServiceException
	 */
	List<CheckinInfo> queryByMemberId(Member member) throws ServiceException;

	/**
	 * 撤销分享查询分享信息
	 * @param ticketNo 电子客票号
	 * @return
	 * @throws ServiceException
	 */
	CheckinInfo queryByTicketNotShare(String ticketNo) throws ServiceException;
	
}