package com.ticket.service;

import java.util.Date;
import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjAppointment;
import com.ticket.pojo.BjdjHall;
import com.ticket.pojo.BjdjServiceCode;
import com.ticket.pojo.BjdjValidation;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.SystemDictionary;


/**
 * 便捷登机预约表业务接口
 * @ClassName: IBjdjAppointmentService   
 * @Description: 提供便捷登机预约表操作的增删改查   
 * @author HiSay  
 * @date  2015-11-23 22:48:35
 *
 */
public interface IBjdjAppointmentService extends IBaseService<BjdjAppointment, String> {
	
	Pagination queryAll2(String keyword,String order,Integer pageSize) throws ServiceException;

	/**
	 * 取消激活
	 * @param serviceCode_idsValue 服务码id（可以有多个）
	 * @param operationPerson 操作人
	 */
	void unActivate(String serviceCode_idsValue, Object operationPerson) throws ServiceException;
	
	/**
	 * 移除便捷登机预约表实体
	 * @Title: IBjdjAppointmentService
	 * @Description: 
	 * @param @param id 便捷登机预约表ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * @Description：通过电话线下预约
	 * @param flightNo
	 * @param mobile
	 * @param count
	 * @param type
	 * @return
	 * @throws ServiceException
	 */
	BjdjAppointment offlineByMobile(String name, String idCard, String flightNo, String mobile, Integer count, String luggage, Date time, Date useTime) throws ServiceException;
	
	/**
	 * @Description：线上预约（通过服务码激活的）
	 * @param serviceCode_id
	 * @param flightNo
	 * @param mobile
	 * @return
	 */
	BjdjAppointment onlineAppointment(String serviceCode_id, String idCard, String flightNo, String mobile, Date useTime, String name) throws ServiceException;
	
	/**
	 * @Description：线上预约（通过服务码激活的）
	 * @param serviceCode_id
	 * @param flightNo
	 * @param mobile
	 * @return
	 */
	BjdjAppointment onlineAppointment(BjdjServiceCode serviceCode, String idCard, String flightNo, String mobile, Date useTime, String name) throws ServiceException;
	
	/**
	 * 激活一批服务码
	 * @author：涂有
	 * @date 2017年1月17日 下午4:13:05
	 * @param codes 服务码
	 * @param dates 日期
	 * @param idCards 证件号
	 * @param cardTypes 证件类型
	 * @param names 激活人名称
	 * @param checkQueueWait 是否检测排队
	 * @throws
	 */
	void onlineAppointment(BjdjServiceCode[] serviceCodes, Date[] dates, String[] flightNos, String[] idCards, String[] cardTypes, String[] names) throws ServiceException;
	
	/**
	 * @Description：根据服务码得到预约对象
	 * @param serviceCode
	 * @return
	 */
	BjdjAppointment getByServiceCode(String serviceCode) throws ServiceException;
	
	/**
	 * @Description：根据服务码得到预约对象
	 * @param serviceCode
	 * @return
	 */
	BjdjAppointment getByServiceCode(BjdjServiceCode serviceCode);

	/**
	 * @Description：得到一个预约的验证信息
	 * @param appointment
	 * @return
	 */
	List<BjdjValidation> getValidation(BjdjAppointment appointment);
	
	/**
	 * 根据时间得到预约对象列表
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ServiceException
	 */
	List<BjdjAppointment> getByUseTimes(Date startTime,Date endTime);
	/**
	 * 根据时间得到要进厅的预约对象列表
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ServiceException
	 */
	List<BjdjAppointment> getEnterHallByUseTimes(Date startTime,Date endTime);
	
	/**
	 * 根据时间得到预约对象列表
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ServiceException
	 */
	List<BjdjAppointment> getByTimes(Date startTime,Date endTime);
	/**
	 * 根据时间得到要进厅的预约对象列表
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ServiceException
	 */
	List<BjdjAppointment> getEnterHallByTimes(Date startTime,Date endTime);
	
	/**
	 * 根据航班查找对应的预约
	 * @param flightNo
	 * @param flightDate
	 * @return
	 * @throws ServiceException
	 */
	List<BjdjAppointment> getByFlightNo(String flightNumber) throws ServiceException;
	
	/**
	 * 根据航班查找对应的预约
	 * @param flightNo
	 * @param flightDate
	 * @return
	 * @throws ServiceException
	 */
	List<BjdjAppointment> get(String flightNo, Date flightDate) throws ServiceException;
	
	/**
	 * 根据id集合查找预约集合
	 * @param idsValue
	 * @return
	 * @throws ServiceException
	 */
	List<BjdjAppointment> getByIdsValue(String idsValue) throws ServiceException;
	
	/**
	 * 航班延误
	 * @param flightNumber
	 * @param flightDate
	 */
	void flightDelay(String flightNumber, String flightDate) throws ServiceException;
	
	/**
	 * 查询指定时间段，验证超时的统计数量
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	int validationTimeout(Date startTime,Date endTime);
	
	/**
	 * 查询指定时间段，验证超时的统计数量
	 * @param startTime
	 * @param endTime
	 * @param hall
	 * @return
	 */
	int validationTimeout(Date startTime, Date endTime, BjdjHall hall);
	
	/**
	 * 查询今天到指定时间，验证超时的统计数量
	 * @param endTime
	 * @param hall
	 * @return
	 */
	int validationTimeout(Date endTime, BjdjHall hall);
	
	/**
	 * 统计一个大厅的等待数量
	 * @param hall
	 * @return
	 */
	int waitNumber(Date flightDate, BjdjHall hall);
	
	/**
	 * 统计一个渠道类型的等待数量
	 * @param hall
	 * @return
	 */
	int waitNumber(Date flightDate, SystemDictionary dictionary);
	
	/**
	 * 等待数量
	 * @param flightDate
	 * @return
	 */
	int waitNumber(Date flightDate);
	
//	/**
//	 * @Description：计算一个厅排队的人数
//	 * @param hall_id
//	 * @return
//	 */
//	List<BjdjAppointment> listWait(BjdjHall hall);
//	
//	/**
//	 * @Description：计算所有排队的人数
//	 * @param hall_id
//	 * @return
//	 */
//	List<BjdjAppointment> listWait();
	
	/**
	 * 剩余座位数
	 * @param hall
	 * @return
	 */
	int surplus(Date flightDate, BjdjHall hall);
	
	/**
	 * 某种渠道类型的剩余座位数
	 * @param channel
	 * @return
	 */
	int surplus(Date flightDate, SystemDictionary channel);
	
	/**
	 * 剩余的大厅容量
	 * @param serviceCodes 一组服务码
	 * @return
	 */
	boolean hasSurplus(Date flightDate, BjdjServiceCode...serviceCodes);
	
	/**
	 * 剩余的大厅容量
	 * @param serviceCodes 一组服务码
	 * @return
	 */
	boolean hasSurplus(Date flightDate, String...idsValue);
	
	/**
	 * @Description：通过用户和大厅查找是否有排队的用户
	 * @param memberId
	 * @param hallId
	 * @return
	 */
	List<BjdjAppointment> getByMemberAndHall(String memberId, String hallId);
	
	/**
	 * 指定的航班时间是否现在可以激活了
	 * @param flightDate 航班时间
	 * @return
	 */
	boolean canActivate(Date flightDate);
	/**
	 * 得到所有的激活信息、预约信息
	 * @return
	 * @throws ServiceException
	 */
	long queryAllCount(String employee_id) throws ServiceException;
	
	/**
	 * 分页查询
	 * @param name
	 * @param flightNo
	 * @param startTime
	 * @param endTime
	 * @param versionFlag
	 * @param adminCommonPageSize
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryEntityByAdmin(String flightNo, Date startTime,
			Date endTime, String versionFlag, int adminCommonPageSize) throws ServiceException;
	
	/**
	 * 分页查询
	 * @param bjdjHall
	 * @param flightNo
	 * @param startTime
	 * @param endTime
	 * @param versionFlag
	 * @param adminCommonPageSize
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryEntityByAdmin(BjdjHall bjdjHall,String flightNo, Date startTime,
			Date endTime, String versionFlag, int adminCommonPageSize) throws ServiceException;
	
	/**
	 * 得到一个身份证的激活信息
	 * @param idcard
	 * @return
	 */
	BjdjAppointment getActiveInfo(String idcard);
}