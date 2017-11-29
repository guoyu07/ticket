package com.ticket.service;

import java.util.Date;
import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.MemberFocusFlight;

/**
 * 会员关注航班业务接口
 * 
 * @ClassName: IMemberFocusFlightService
 * @Description: 提供会员关注航班操作的增删改查
 * @author HiSay
 * @date 2015-12-04 16:03:42
 * 
 */
public interface IMemberFocusFlightService extends
		IBaseService<MemberFocusFlight, String> {
	/**
	 * 保存会员关注航班实体
	 * 
	 * @Title: IMemberFocusFlightService
	 * @Description:
	 * @param @param member_id 会员id
	 * @param @param flightNumber 航班号
	 * @param @param flightDate 航班日期
	 * @param @param memberRole 会员角色
	 * @param @param flightState 航班状态
	 * @param @param ifTakeLuggage 是否携带行李
	 * @param @param takePerson 携带特殊人员
	 * @param @param personCount 乘机人数
	 * @param @param ifSet 是否已设置过旅程
	 * @param @return 保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	MemberFocusFlight persist(String member_id, String flightNumber, String flightDate,
			String memberRole, String flightState, Integer ifTakeLuggage,
			String takePerson, Integer personCount, Integer ifSet,
			String mobile, String seatNo, String ticketNo, String couponId,
			String stopover);

	MemberFocusFlight persist(String member_id, String flightNumber, String flightDate, String flightState, String couponId);
	
	/**
	 * 修改会员关注航班实体
	 * 
	 * @Title: IMemberFocusFlightService
	 * @Description:
	 * @param @param member_id 会员id
	 * @param @param flightNumber 航班号
	 * @param @param flightDate 航班日期
	 * @param @param memberRole 会员角色
	 * @param @param flightState 航班状态
	 * @param @param ifTakeLuggage 是否携带行李
	 * @param @param takePerson 携带特殊人员
	 * @param @param personCount 乘机人数
	 * @param @param ifSet 是否已设置过旅程
	 * @param @return 修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	boolean merge(String id, String member_id, String flightNumber,
			String flightDate, String memberRole, String flightState,
			Integer ifTakeLuggage, String takePerson, Integer personCount,
			Integer ifSet, String mobile, String seatNo, String ticketNo,
			String couponId, String stopover) throws ServiceException;
	
	/**
	 * 设置旅程助手业务
	 * @param id
	 * @param takeLuggage
	 * @param personCount
	 * @param specialPerson
	 * @param phone
	 * @param IDCard
	 * @return
	 */
	MemberFocusFlight set(String id, Integer takeLuggage, Integer personCount, String specialPerson, String phone, String IDCard);

	/**
	 * 移除会员关注航班实体
	 * 
	 * @Title: IMemberFocusFlightService
	 * @Description:
	 * @param @param id 会员关注航班ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 会员关注航班实体
	 * 
	 * @param flightNumber
	 * @param flightDate
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	MemberFocusFlight queryByMemberAndFlightNoAndDate(String flightNumber,
			String flightDate, String flightState, String versionFlag)
			throws ServiceException;

	/**
	 * 查询会员关注列表
	 * 
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<MemberFocusFlight> queryListByMember(String versionFlag)
			throws ServiceException;

	/**
	 * 删除会员关注的航班的快捷菜单
	 * 
	 * @param menuPosition
	 *            快捷菜单编号
	 * @param versionFlag
	 *            版本标识
	 * @throws ServiceException
	 */
	void deleteMenuByFlight(String menuPosition, String versionFlag)
			throws ServiceException;

	/**
	 * 查询中转航班
	 * 
	 * @param flightState
	 *            航班状态
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<MemberFocusFlight> queryTransferListByMember(String flightState,
			String versionFlag) throws ServiceException;
	
	/**
	 * 查询中转航班
	 * 
	 * @param flightState
	 *            航班状态
	 * @param flightNumber
	 * @return
	 * @throws ServiceException
	 */
	List<MemberFocusFlight> queryListByMember(String flightState,
			String flightNumber) throws ServiceException;

	/**
	 * 查询会员是否关注过航班
	 * 
	 * @param memberId
	 *            会员id
	 * @param flightNumber
	 *            航班号
	 * @param flightDate
	 *            航班日期
	 * @param flightState
	 *            航班状态
	 * @param memberRole
	 *            会员角色
	 * @return
	 * @throws ServiceException
	 */
	MemberFocusFlight queryAppMemberFocus(String memberId, String flightNumber,
			String flightDate, String flightState, String memberRole)
			throws ServiceException;

	/**
	 * 查询会员的值机信息
	 * @param member_id 会员id
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<MemberFocusFlight> queryListByCoupon(String member_id, String versionFlag) throws ServiceException;

	/**
	 * 根据航班号和航班日期查询会员关注航班实体
	 * @param flightNumber  航班号
	 * @param flightDate    航班日期
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	MemberFocusFlight queryArrivalByMemberAndNo(String flightNumber, Date flightDate,
			String versionFlag) throws ServiceException;
	
	/**
	 * 根据航班号和航班日期查询会员关注航班实体
	 * @param member_id  会员id
	 * @param flightNumber  航班号
	 * @param flightDate   航班日期
	 * @return
	 * @throws ServiceException
	 */
	MemberFocusFlight query(String member_id, String flightNumber, String flightDate) throws ServiceException;

	/**
	 * 查询关注的经停航班
	 * @param flightNumber 航班号
	 * @param flightDate   航班日期
	 * @param flightState  航班标识（进港还是离港）
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	MemberFocusFlight queryByMemberAndStopover(String flightNumber,
			String flightDate, String flightState, String versionFlag) throws ServiceException;

	/**
	 * 查询会员关注的航班
	 * @param flightNumber 航班号
	 * @param flightDate 航班日期
	 * @param flightState 航班状态
	 * @param member_id 会员id
	 * @param stopover 中转标识
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	MemberFocusFlight queryByJudge(String value, String var,
			String flightState, String stopover,
			String versionFlag) throws ServiceException;

	/**
	 * 保存会员关注
	 * @param member_id 会员id
	 * @param flightNumber 航班号
	 * @param flightDate 航班日期
	 * @param flightState 航班状态
	 * @return
	 * @throws ServiceException
	 */
	boolean saveFocus(String member_id, String flightNumber, String flightDate,
			String flightState,String stopover) throws ServiceException;

	/**
	 * 根据会员查询会员关注信息
	 * @param flightNumber 航班号
	 * @param flightDate 航班日期
	 * @param member_id 会员id
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	MemberFocusFlight queryByMember(String flightNumber, String flightDate,
			String member_id, String versionFlag);

	/**
	 * 查询指定会员的关注列表
	 * @param member_id 会员id
	 * @return
	 * @throws ServiceException
	 */
	List<MemberFocusFlight> queryListByMemberId(String member_id) throws ServiceException;
	
	/**
	 * 查询指定日期的关注列表
	 * @param member_id 会员id
	 * @return
	 * @throws ServiceException
	 */
	List<MemberFocusFlight> queryDepartListByDate(String member_id, String flightDate);

	/**
	 * 根据电子客票号查询被分享关注的关注信息
	 * @param ticketNo 电子客票号
	 * @return
	 * @throws ServiceException
	 */
	MemberFocusFlight queryByShare(String ticketNo) throws ServiceException;
} 