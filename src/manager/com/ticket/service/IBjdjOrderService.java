package com.ticket.service;

import java.util.List;

import com.ticket.enumer.BjdjOrderType;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjOrder;
import com.ticket.pojo.BjdjServiceCode;
import com.ticket.pojo.BjdjServicePackage;
import com.ticket.pojo.Member;
import com.ticket.pojo.SystemDictionary;

/**
 * 便捷登机订单表业务接口
 * @ClassName: IBjdjOrderService   
 * @Description: 提供便捷登机订单表操作的增删改查   
 * @author HiSay  
 * @date  2015-10-23 15:22:31
 *
 */
public interface IBjdjOrderService extends IBaseService<BjdjOrder, String> {
	
	/**
	 * 保存便捷登机订单表实体
	 * @Title: IBjdjOrderService
	 * @param member_id  用户ID
	 * @param packageType  订单类型
	 * @param time  订单日期
	 * @param state  是否完成
	 * @return  保存成功则返回true, 保存失败则返回false.
	 * @throws ServiceException   
	 */
	BjdjOrder persist(String member_id, BjdjServicePackage packageType, Integer count, BjdjOrderType type, String mobile) throws ServiceException;

	/**
	 * 修改便捷登机订单表实体
	 * @Title: IBjdjOrderService
	 * @Description:
	 * @param number  订单号
	 * @param member_id  用户ID
	 * @param time  订单日期
	 * @param state  是否完成
	 * @return  修改成功则返回true, 修改失败则返回false.
	 * @throws ServiceException    
	 */
	BjdjOrder merge(String id, String number,String member_id, Double price, BjdjOrderType type, SystemDictionary state,String versionFlag) throws ServiceException;
	
	/**
	 * 移除便捷登机订单表实体
	 * @Title: IBjdjOrderService
	 * @Description: 
	 * @param id 便捷登机订单表ID
	 * @return 移除成功返回true, 移除失败返回false.
	 * @throws ServiceException    
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 移除便捷登机订单表实体
	 * @Title: IBjdjOrderService
	 * @Description: 
	 * @param id 便捷登机订单表ID
	 * @return 移除成功返回true, 移除失败返回false.
	 * @throws ServiceException    
	 */
	void remove(BjdjOrder order) throws ServiceException;
	
	/**
	 * 生成我方服务器订单（尚未生成第三方平台的订单）
	 * @param member 哪个用户
	 * @param packageType 什么套餐
	 * @param count 数量是多少
	 * @param type 订单类型
	 * @return
	 */
	BjdjOrder generateOrder(Member member, BjdjServicePackage packageType, Integer count, BjdjOrderType type) throws ServiceException;
	
	/**
	 * @Description：通过用户名ID得到订单对象
	 * @return 订单对象
	 */
	List<BjdjOrder> queryByMemberId(String member_id);
	
	/**
	 * @Description：通过用户名ID和状态得到订单对象
	 * @return 订单对象
	 */
	List<BjdjOrder> queryByMemberIdAndStatus(String member_id, SystemDictionary state);
	
	/**
	 * @Description：通过用户名ID得到未评价订单对象
	 * @return 订单对象
	 */
	List<BjdjOrder> queryByMemberIdAndDontComment(String member_id);
	
	/**
	 * @Description：通过服务码得到订单
	 * @param serviceCodeId 服务码id
	 * @return
	 */
	BjdjOrder getByServiceCode(String serviceCodeId);
	
	/**
	 * @Description：通过订单号获取订单
	 * @param number
	 * @return
	 */
	BjdjOrder getByNumber(String number);
	
	/**
	 * @Description：通过外部订单号获取订单
	 * @param numberOut
	 * @return
	 */
	BjdjOrder getByNumberOut(String numberOut);
	
	/**
	 * @Description：查询一个订单中我的服务码（没有转赠出去的）
	 * @return
	 */
	List<BjdjServiceCode> queryMyServiceCode(String orderId) throws ServiceException ;
	
	/**
	 * @Description：查询一个订单可以转赠的服务码
	 * @return
	 */
	List<BjdjServiceCode> queryCanDonationServiceCode(String orderId) throws ServiceException ;
	
	/**
	 * @Description：查询一个订单可以激活的服务码
	 * @return
	 */
	List<BjdjServiceCode> queryCanActivateServiceCode(String orderId) throws ServiceException ;
	
	/**
	 * @Description：查询一个订单可以评论的服务码
	 * @return
	 */
	List<BjdjServiceCode> queryCanCommentServiceCode(String orderId) throws ServiceException ;
	
	/**
	 * @Description：判断一个订单是否可以退款
	 * @param orderId
	 * @return
	 */
	boolean canRefund(String orderId) throws ServiceException ;
	
	/**
	 * @Description：判断一个订单是否可以删除
	 * @param orderId
	 * @return
	 */
	boolean canDelete(String orderId) throws ServiceException ;
	
	/**
	 * @Description：查询一个订单中我的服务码（没有转赠出去的）
	 * @return
	 */
	List<BjdjServiceCode> queryMyServiceCode(BjdjOrder order) throws ServiceException ;
	
	/**
	 * @Description：查询一个订单可以转赠的服务码
	 * @return
	 */
	List<BjdjServiceCode> queryCanDonationServiceCode(BjdjOrder order) throws ServiceException ;
	
	/**
	 * @Description：查询一个订单可以激活的服务码
	 * @return
	 */
	List<BjdjServiceCode> queryCanActivateServiceCode(BjdjOrder order) throws ServiceException ;
	
	/**
	 * @Description：查询一个订单可以评论的服务码
	 * @return
	 */
	List<BjdjServiceCode> queryCanCommentServiceCode(BjdjOrder order) throws ServiceException ;
	
	/**
	 * @Description：判断一个订单是否可以退款
	 * @param orderId
	 * @return
	 */
	boolean canRefund(BjdjOrder order) throws ServiceException ;
	
	/**
	 * @Description：判断一个订单是否可以删除
	 * @param orderId
	 * @return
	 */
	boolean canDelete(BjdjOrder order) throws ServiceException ;
	
	/**
	 * @Description：检车订单是否过期（spring的定时器调用）
	 */
	void checkExpire();
	
	/**
	 * 订单支付成功
	 */
	BjdjOrder setPaid(String order_no, String numberOut) throws ServiceException;
	
	/**
	 * 根据订单id获取服务码
	 * @return
	 * @throws ServiceException
	 */
	List<BjdjServiceCode> getByOrderId(BjdjOrder order) throws ServiceException;
}