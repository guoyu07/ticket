package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjOrder;
import com.ticket.pojo.BjdjRefund;


/**
 * 退款记录业务接口
 * @ClassName: IBjdjRefundService   
 * @Description: 提供退款记录操作的增删改查   
 * @author HiSay  
 * @date  2016-11-25 10:59:56
 *
 */
public interface IBjdjRefundService extends IBaseService<BjdjRefund, String> {
	
	/**
	 * 发起退款申请
	 * @Title: IBjdjRefundService
	 * @param order_id  退款订单
	 * @param reason  退款原因说明
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	BjdjRefund apply(String order_id,String reason) throws ServiceException;
	
	/**
	 * 管理员审核退款申请
	 * @Title: IBjdjRefundService
	 * @param allow  是否允许退款
	 * @param remark  管理员备注
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	BjdjRefund audit(String id, Boolean allow,String remark) throws ServiceException;
	
	/**
	 * 退款成功
	 * @param bjdjRefund
	 * @param success 是否成功
	 * @param xml 返回的xml数据，保存以做异常检查
	 * @return
	 * @throws ServiceException
	 */
	BjdjRefund response(BjdjRefund bjdjRefund, boolean success, String xml) throws ServiceException;
	
	/**
	 * 查看某个订单需要处理的申请
	 * @param order
	 * @param allow
	 * @return
	 */
	BjdjRefund queryWaitDealWith(BjdjOrder order);
	
	/**
	 * 查询指定订单对应申请时间的退款记录
	 * @param order
	 * @param createTime
	 * @return
	 */
	BjdjRefund query(BjdjOrder order, String createTime);
}