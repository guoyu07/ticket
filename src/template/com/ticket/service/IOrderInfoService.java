package com.ticket.service;

import java.rmi.server.ServerCloneException;
import java.util.Date;
import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.OrderInfo;
import com.ticket.pojo.OrderInfoDetail;
import com.ticket.pojo.Pagination;

/**
 * 订单数据业务接口
 * @ClassName: IOrderInfoService   
 * @Description: 提供订单数据操作的增删改查   
 * @author HiSay  
 * @date  2015-11-04 18:04:35
 *
 */
public interface IOrderInfoService extends IBaseService<OrderInfo, String> {
	/**
	 * @author wangjiafeng
	 * 添加订单状态
	 * @method add
	 * @param buyCount
	 * @param payWay
	 * @param channelCustomerInfo_id
	 * @return
	 * @throws ServerCloneException
	 * @return String
	 * @date 2015-12-28 上午10:34:24
	 */
	String add(Integer buyCount,String payWay,String channelCustomerInfo_id) throws ServerCloneException;
	/**
	 * 移除订单数据实体
	 * @Title: IOrderInfoService
	 * @Description: 
	 * @param @param id 订单数据ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	/**
	 * 查询某员工下的所有客户服务码销量
	 * @param employee_id
	 * @return
	 */
	String getEmployeeSaleCount(String employee_id);
	/**
	 * @author wangjiafeng
	 * 管理制定客户的订单列表
	 * @method queryAll
	 * @param channelCustomerInfo_id
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 * @return Pagination
	 * @date 2015-12-29 下午03:03:43
	 */
	Pagination queryAll(String channelCustomerInfo_id,Integer pageSize) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 根据赠送日志添加赠送订单
	 * @method addByEmployeeInfoZengLog
	 * @param orderType
	 * @param channelCustomerInfo_id
	 * @param count
	 * @param orInfoDetails
	 * @return
	 * @throws ServiceException
	 * @return Boolean
	 * @date 2016-1-19 上午09:55:00
	 */
	Boolean addByEmployeeInfoZengLog(Integer orderType,String channelCustomerInfo_id,
			Integer count,List<OrderInfoDetail> orInfoDetails) throws ServiceException;
	/**
	 * 查找所有的客户购买的服务码总数
	 * @return
	 * @throws ServiceException
	 */
	long queryAllBuyCount(String employee_id) throws ServiceException;
	/**
	 * 查找所有的客户购买服务码的实付金额
	 * @return
	 * @throws ServiceException
	 */
	double queryAllPaidAmount() throws ServiceException;
	/**
	 * 根据客户id查询指定时间内该客户的所有订单数量
	 * @param channelCustomerInfo_id
	 * @return
	 * @throws ServiceException
	 */
	long queryByChannelCustomerInfo_id(String channelCustomerInfo_id,Date startDate ,Date endDate) throws ServiceException;
	
	/**
	 * 根据客户id查询该客户的所有订单
	 * @param channelCustomerInfo_id
	 * @return
	 * @throws ServiceException
	 */
	List<OrderInfo> queryAllByCustomerInfo_id(String channelCustomerInfo_id) throws ServiceException;
	/**
	 * 查询该客户的服务码的激活数
	 * @param channelCustomerInfo_id
	 * @return
	 * @throws ServiceException
	 */
	long queryAllBjdjServiceCodeAllReadyActive(String channelCustomerInfo_id,Date startDate,Date endDate) throws ServiceException;
	/**
	 * 查询该客户的服务码的验证数
	 * @param channelCustomerInfo_id
	 * @return
	 * @throws ServiceException
	 */
	long queryAllBjdjServiceCodeAllReadyValidation(String channelCustomerInfo_id,Date startDate,Date endDate) throws ServiceException;
}