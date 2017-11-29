package com.ticket.service;

import java.util.Date;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.ServiceBook;


/**
 * 服务订单业务接口
 * @ClassName: IServiceBookService   
 * @Description: 提供服务订单操作的增删改查   
 * @author HiSay  
 * @date  2015-10-15 12:56:11
 *
 */
public interface IServiceBookService extends IBaseService<ServiceBook, String> {
	/**
	 * 保存服务订单实体
	 * @Title: IServiceBookService
	 * @Description:
	 * @param @param member_id  会员id
	 * @param @param idCard  身份证号
	 * @param @param phone  联系电话
	 * @param @param flightNumber  航班号
	 * @param @param bookAmount  预订数量
	 * @param @param useTime  使用时间
	 * @param @param serviceKey  服务码
	 * @param @param payStatus  付款状态
	 * @param @param payTime  付款时间
	 * @param @param payWay  付款方式
	 * @param @param weChatTransld  微信交易号
	 * @param @param alipayName  支付宝名称
	 * @param @param alipayTransld  支付宝交易号
	 * @param @param bankName  网银名称
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String member_id,String idCard,String phone,String flightNumber,Integer bookAmount,Date useTime,String serviceKey,Integer payStatus,Date payTime,Integer payWay,String weChatTransld,String alipayName,String alipayTransld,String bankName, String versionFlag) throws ServiceException;
	
	/**
	 * 修改服务订单实体
	 * @Title: IServiceBookService
	 * @Description:
	 * @param @param member_id  会员id
	 * @param @param idCard  身份证号
	 * @param @param phone  联系电话
	 * @param @param flightNumber  航班号
	 * @param @param bookAmount  预订数量
	 * @param @param useTime  使用时间
	 * @param @param serviceKey  服务码
	 * @param @param payStatus  付款状态
	 * @param @param payTime  付款时间
	 * @param @param payWay  付款方式
	 * @param @param weChatTransld  微信交易号
	 * @param @param alipayName  支付宝名称
	 * @param @param alipayTransld  支付宝交易号
	 * @param @param bankName  网银名称
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String member_id,String idCard,String phone,String flightNumber,Integer bookAmount,Date useTime,String serviceKey,Integer payStatus,Date payTime,Integer payWay,String weChatTransld,String alipayName,String alipayTransld,String bankName, String versionFlag) throws ServiceException;
	
	/**
	 * 移除服务订单实体
	 * @Title: IServiceBookService
	 * @Description: 
	 * @param @param id 服务订单ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 批量彻底删除服务订单信息
	 * @param idsValue
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException;
	
	/**
	 * 预订快捷服务成功，公共座位数减一
	 * @param quantity 座位数量
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	Integer bookServiceSuc(Integer quantity,String versionFlag) throws ServiceException;
	
	
	/**
	 * 取消快捷服务，公共座位数加一
	 * @param quantity 座位数量
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	Integer cancelServiceSuc(Integer quantity,String versionFlag) throws ServiceException;
	
	/**
	 * 完成服务，公共座位数加一
	 * @param quantity 座位数量
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	Integer completeService(Integer quantity,String versionFlag) throws ServiceException;
	
	/**
	 * 判断座位是否可预订
	 * @param totalSeat 总座位数
	 * @param seatbeenBookOnline  线上预订座位数
	 * @param seatbeenBookUnderline  线下预约座位数
	 * @return
	 * @throws ServiceException
	 */
	boolean ifTheSeatEnough(Integer totalSeat,Integer seatbeenBookOnline,Integer seatbeenBookUnderline) throws ServiceException;

	/**
	 * 根据关键词搜索订单信息
	 * @param keyword
	 * @param pageSize
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryPageModuleByKeyword(String keyword, int pageSize, String versionFlag) throws ServiceException;
	
}