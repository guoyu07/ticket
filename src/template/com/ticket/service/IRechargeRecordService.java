package com.ticket.service;

import java.util.Date;
import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.RechargeRecord;
import com.ticket.pojo.SystemUser;


/**
 * 充值记录业务接口
 * @ClassName: IRechargeRecordService   
 * @Description: 提供充值记录操作的增删改查   
 * @author HiSay  
 * @date  2015-11-13 16:45:25
 *
 */
public interface IRechargeRecordService extends IBaseService<RechargeRecord, String> {
	/**
	 * 保存充值记录实体
	 * @Title: IRechargeRecordService
	 * @Description:
	 * @param @param recordNo  记录编号
	 * @param @param amountOfMoney  充值金额
	 * @param @param channelCustomerInfoId  充值客户
	 * @param @param payTime  支付时间
	 * @param @param payWay  支付方式
	 * @param @param receiptNo  回单号
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String recordNo,Double amountOfMoney,String channelCustomerInfoId,Date payTime,
			String payWay,String receiptNo, SystemUser systemUser) throws ServiceException;
	
	/**
	 * 修改充值记录实体
	 * @Title: IRechargeRecordService
	 * @Description:
	 * @param @param recordNo  记录编号
	 * @param @param amountOfMoney  充值金额
	 * @param @param channelCustomerInfoId  充值客户
	 * @param @param payTime  支付时间
	 * @param @param payWay  支付方式
	 * @param @param receiptNo  回单号
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String recordNo,Double amountOfMoney,String channelCustomerInfoId,Date payTime,String payWay,String receiptNo, String versionFlag) throws ServiceException;
	
	/**
	 * 移除充值记录实体
	 * @Title: IRechargeRecordService
	 * @Description: 
	 * @param @param id 充值记录ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 根据登录者查询他能看到的充值记录
	 * @param keyword
	 * @param pageSize
	 * @param employee_id
	 * @param versionFlag
	 * @return
	 */
	Pagination queryPageModuleByCustomerId(int pageSize,String customer_id,String employee_id, String versionFlag,String keyword) throws ServiceException;
	/**
	 * 查询总的充值金额
	 * @return
	 * @throws ServiceException
	 */
	double queryAllMoney(String employee_id) throws ServiceException;
	
	/**
	 * 查询本月的充值金额
	 * @return
	 * @throws ServiceException
	 */
	double queryThisMonthMoney() throws ServiceException;
	
	/**
	 * 根据客户id查找该客户的充值记录
	 * @param ChannelCustomerInfoId
	 * @return
	 * @throws ServiceException
	 */
	List<RechargeRecord> queryByChannelCustomerInfoId(String ChannelCustomerInfoId) throws ServiceException;
	
	/**
	 * 充值客户数
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	long customCount(Date startDate, Date endDate,EmployeeInfo employeeInfo,int type);
	
	/**
	 * 充值金额
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	double moneyAmount(Date startDate, Date endDate,EmployeeInfo employeeInfo,int type);
	
	/**
	 * 开票数
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	long ticketCount(Date startDate, Date endDate,EmployeeInfo employeeInfo,int type);
	
	/**
	 * 开票金额
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	double moneyAmountForTicket(Date startDate, Date endDate,EmployeeInfo employeeInfo,int type);
	
	/**
	 * 所有的充值记录
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<RechargeRecord> allList(Date startDate, Date endDate);
	
	/**
	 * 需要开票的充值记录
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<RechargeRecord> ticketList(Date startDate, Date endDate);
	
	/**
	 * 查找指定时间内的充值记录
	 * @param ChannelCustomerInfoId
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws ServiceException
	 */
	List<RechargeRecord> queryByChannelCustomerInfoId(
			String ChannelCustomerInfoId,Date startDate,Date endDate) throws ServiceException;
	
	/**
	 * 获取一个渠道客户的最后充值记录
	 * @param channelCustomer_id
	 * @return
	 */
	RechargeRecord lastForChannelCustomer(String channelCustomer_id);
	
	/**
	 * 指定渠道客户的充值次数
	 * @param channelCustomer_id
	 * @return
	 */
	long count(String channelCustomer_id);
	
	/**
	 * 指定渠道客户的充值金额
	 * @param channelCustomer_id
	 * @return
	 */
	double amount(String channelCustomer_id);
	
	/**
	 * 根据登录实体所在的部门以及以下部门的全部充值记录
	 * @param employeeInfo
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryAllWhereIndepartment(EmployeeInfo employeeInfo,String keyword) throws ServiceException;
}