package com.ticket.service;

import java.util.Map;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjOrder;
import com.ticket.pojo.BjdjRefund;
import com.ticket.pojo.Member;


/**
 * 支付接口业务接口
 * @author tuyou
 */
public interface IPayService extends IBaseService<Member, String> {
	
	/**
	 * 初始化
	 */
	String getPayHandler();
	
	/**
	 * 根据便捷登机订单信息生成支付接口的参数
	 * @param order
	 * @return
	 * @throws ServiceException
	 */
	String orderPay(BjdjOrder order, String redirectPath, String showPath) throws ServiceException;
	
	/**
	 * 根据便捷登机订单信息，从服务器查询订单的详情（用于退款的时候检测订单是否关闭，如果已经关闭则不能退款；另外用于同步订单的状态）
	 * @param order
	 * @return
	 * @throws ServiceException
	 */
	String queryOrderInfo(BjdjOrder order) throws ServiceException;
	
	/**
	 * 根据便捷登机订单信息生成退款的参数
	 * @description 支付宝指定3个月内才可退款
	 * @param order
	 * @return
	 * @throws ServiceException
	 */
	String orderRefund(BjdjRefund refund) throws ServiceException;
	
	/**
	 * 加入签名信息
	 * @param params
	 * @return
	 */
	void fillSign(Map<String, String> params);
	
	/**
	 * 验证签名信息
	 * @param params
	 * @return
	 */
	boolean verifySign(Map<String, String> params);
}