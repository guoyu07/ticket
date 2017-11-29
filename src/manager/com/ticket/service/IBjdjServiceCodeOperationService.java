package com.ticket.service;

import java.util.Date;
import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjServiceCode;
import com.ticket.pojo.BjdjServiceCodeOperation;
import com.ticket.pojo.Member;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.SystemDictionary;
import com.ticket.pojo.SystemUser;


/**
 * 服务码日志表业务接口
 * @ClassName: IBjdjServiceCodeLogService   
 * @Description: 提供服务码日志表操作的增删改查   
 * @author HiSay  
 * @date  2015-10-23 15:17:18
 *
 */
public interface IBjdjServiceCodeOperationService extends IBaseService<BjdjServiceCodeOperation, String> {
	/**
	 * 保存服务码日志表实体
	 * @Title: IBjdjServiceCodeLogService
	 * @Description:
	 * @param operation  操作名称（字典）
	 * @param time  日期
	 * @param member_id  用户ID
	 * @param systemUser_id  操作用户ID
	 * @return  保存成功则返回true, 保存失败则返回false.
	 * @throws ServiceException   
	 */
	BjdjServiceCodeOperation persist(BjdjServiceCode serviceCode, SystemDictionary operation, Member fromMember, Member toMember) throws ServiceException;
	
	/**
	 * 保存系统管理员操作的日志
	 * @param serviceCode
	 * @param operation
	 * @param systemUser
	 * @return
	 * @throws ServiceException
	 */
	BjdjServiceCodeOperation persist(BjdjServiceCode serviceCode, SystemDictionary operation, SystemUser systemUser) throws ServiceException;
	
	/**
	 * 渠道客户保存服务码日志表实体
	 * @Title: IBjdjServiceCodeLogService
	 * @Description:
	 * @param operation  操作名称（字典）
	 * @param time  日期
	 * @param member_id  用户ID
	 * @param systemUser_id  操作用户ID
	 * @return  保存成功则返回true, 保存失败则返回false.
	 * @throws ServiceException   
	 */
	BjdjServiceCodeOperation persist(BjdjServiceCode serviceCode, SystemDictionary operation,
			String channelCustomer_id, String orderInfo_id,String orderInfoDetail_id) throws ServiceException;
	
	
	/**
	 * 修改服务码日志表实体
	 * @Title: IBjdjServiceCodeLogService
	 * @Description:
	 * @param operation  操作名称（字典）
	 * @param time  日期
	 * @param member_id  用户ID
	 * @param systemUser_id  操作用户ID
	 * @return  修改成功则返回true, 修改失败则返回false.
	 * @throws ServiceException    
	 */
	BjdjServiceCodeOperation merge(String id, BjdjServiceCode serviceCode, SystemDictionary operation, Member fromMember, Member toMember) throws ServiceException;
	
	/**
	 * 移除服务码日志表实体
	 * @Title: IBjdjServiceCodeLogService
	 * @Description: 
	 * @param id 服务码日志表ID
	 * @return 移除成功返回true, 移除失败返回false.
	 * @throws ServiceException    
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 查询用户购买的服务码
	 * @param versionFlag
	 * @param pageSize
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryEntityByUserId(String versionFlag, int pageSize, String userId) throws ServiceException;

	/**
	 * @Description：得到一个服务码最近的操作
	 * @param serviceCode 
	 * @return
	 */
	BjdjServiceCodeOperation recently(BjdjServiceCode serviceCode);
	
	/**
	 * @Description：得到一个服务码最近的一个操作
	 * @param serviceCode 服务码
	 * @param operationName 操作名称
	 * @return
	 */
	BjdjServiceCodeOperation recentlyOperation(BjdjServiceCode serviceCode, String operationName);
	
	/**
	 * @Description：得到某人的服务码最近的一个操作
	 * @param serviceCode 服务码
	 * @param operationName 操作名称
	 * @param fromMember 操作人
	 * @return
	 */
	BjdjServiceCodeOperation recentlyOperationFromMember(BjdjServiceCode serviceCode, String operationName, Member fromMember);
	
	/**
	 * @Description：得到某人的服务码最近的一个操作
	 * @param serviceCode 服务码
	 * @param operationName 操作名称
	 * @param fromMember 操作人
	 * @return
	 */
	BjdjServiceCodeOperation recentlyOperationToMember(BjdjServiceCode serviceCode, String operationName, Member toMember);
	
	/**
	 * @Description：得到一个服务码最近的一个操作(某人到某人)
	 * @param serviceCode 服务码
	 * @param operationName 操作名称
	 * @param fromMember 从用户
	 * @param toMember 到用户
	 * @return
	 */
	BjdjServiceCodeOperation recentlyOperation(BjdjServiceCode serviceCode, String operationName, Member fromMember, Member toMember);
	
	/**
	 * 根据时间段得到取消预约
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<BjdjServiceCodeOperation> getByTimes(Date startTime,Date endTime);
	
	/**
	 * 根据预约使用时间查找取消的预约
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<BjdjServiceCodeOperation> getByUseTimes(Date startTime, Date endTime);
	
	/**
	 * 根据预约使用时间查找取消的预约
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<BjdjServiceCodeOperation> getEnterHallByUseTimes(Date startTime, Date endTime);
	
	/**
	 * 根据预约使用时间查找取消的预约
	 * @return
	 */
	List<BjdjServiceCodeOperation> getCanceled(Date flightDate);
}