package com.ticket.service;


import java.rmi.server.ServerCloneException;
import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.OrderInfoDetail;
import com.ticket.pojo.Pagination;
/**
 * 订单详细业务层接口
 * @package  com.ticket.service
 * @file     IOrderInfoDetailService.java
 * @author   wangjiafeng
 * @date     2015-12-28 上午10:24:42
 * @version  V 1.0
 */
public interface IOrderInfoDetailService extends IBaseService<OrderInfoDetail, String>{
	/**
	 * @author wangjiafeng
	 * 管理我的服务码
	 * @method queryAll
	 * @param channelCustomerInfo_id
	 * @param order
	 * @param pageSize
	 * @return
	 * @throws ServerCloneException
	 * @return Pagination
	 * @date 2015-12-29 下午04:10:08
	 */
	Pagination queryAll(String channelCustomerInfo_id,String order,Integer pageSize) throws ServiceException;
	/**
	 * @author wangjiafeng
	 *  获取指定渠道客户池子里的服务码
	 * @method getServiceCodeIdsByUnused
	 * @param channelCustomerInfo_id
	 * @param size
	 * @return
	 * @throws ServerCloneException
	 * @return List<OrderInfoDetail>
	 * @date 2016-1-18 下午05:35:54
	 */
	List<OrderInfoDetail> getServiceCodeIdsByUnused(String channelCustomerInfo_id,Boolean memberIsNull,Integer size) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 根据登录账号获取便捷等级服务码列表
	 * @method getUnusedCodeListByChannelCustomerInfoLoginId
	 * @param userName
	 * @param size
	 * @return
	 * @throws ServiceException
	 * @return List<OrderInfoDetail>
	 * @date 2016-1-19 下午03:12:46
	 */
	List<OrderInfoDetail> getUnusedCodeListByChannelCustomerInfoLoginId(String userName,Boolean memberIsNull,Integer size) throws ServiceException;
	/**
	 * @author 会员绑定客户
	 * 会员绑定客户
	 * @method memberBindServiceCode
	 * @param mobile
	 * @param idCard
	 * @param employeeInfo
	 * @return
	 * @throws ServiceException
	 * @return String
	 * @date 2016-1-20 下午02:05:02
	 */
	String memberBindServiceCode(String mobile,String name,EmployeeInfo employeeInfo) throws ServiceException;
	
	/**
	 * 购买的服务码数量
	 * @param channelCustomber_id
	 * @return
	 */
	long buyServiceCodeCount(String channelCustomber_id);
	
	/**
	 * 购买的服务码金额
	 * @param channelCustomber_id
	 * @return
	 */
	double buyServiceCodeAmount(String channelCustomber_id);
	
	
	/**
	 * 验证的服务码数量
	 * @param channelCustomber_id
	 * @return
	 */
	long serviceCodeValidationCount(String channelCustomber_id);
	
}
