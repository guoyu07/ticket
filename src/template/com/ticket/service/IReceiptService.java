package com.ticket.service;

import java.util.Date;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.Receipt;
import com.ticket.pojo.SystemUser;


/**
 * 收据发票业务接口
 * @ClassName: IReceiptService   
 * @Description: 提供收据发票操作的增删改查   
 * @author HiSay  
 * @date  2015-11-17 17:10:15
 *
 */
public interface IReceiptService extends IBaseService<Receipt, String> {
	/**
	 * 保存收据发票实体
	 * @Title: IReceiptService
	 * @Description:
	 * @param @param receiptNo  收据编号
	 * @param @param name  收据名称
	 * @param @param issueTime  开具时间
	 * @param @param channelCustomerInfoId  付款客户
	 * @param @param amountOfMoney  开具金额
	 * @param @param employeeInfoId  申请人
	 * @param @param type  申请类型
	 * @param @param remarks  备注
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String receiptNo,String name,Date issueTime,String channelCustomerInfoId,
			Double amountOfMoney,String employeeInfoId,Integer type,String remarks, String applyClass_id,String money) throws ServiceException;
	
	/**
	 * 修改收据发票实体
	 * @Title: IReceiptService
	 * @Description:
	 * @param @param receiptNo  收据编号
	 * @param @param name  收据名称
	 * @param @param issueTime  开具时间
	 * @param @param channelCustomerInfoId  付款客户
	 * @param @param amountOfMoney  开具金额
	 * @param @param employeeInfoId  申请人
	 * @param @param type  申请类型
	 * @param @param remarks  备注
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String receiptNo,String name,Date issueTime,String channelCustomerInfoId,
			Double amountOfMoney,String employeeInfoId,Integer type,String remarks, String applyClass_id,String money) throws ServiceException;
	
	
	boolean merge(String id,Integer audit,String auditRemarks, String receiptNo) throws ServiceException;
	/**
	 * 移除收据发票实体
	 * @Title: IReceiptService
	 * @Description: 
	 * @param @param id 收据发票ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 获取所有的发票申请
	 * @method queryAll
	 * @param employeeInfo
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 * @return Pagination
	 * @date 2016-1-4 下午03:38:05
	 */
	Pagination queryAll(EmployeeInfo employeeInfo,String applyClass_id,String  states,Integer deleteFlag,Integer pageSize) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 改变收据状态
	 * @method changeState
	 * @param id
	 * @param state
	 * @return
	 * @throws ServiceException
	 * @return Boolean
	 * @date 2016-1-6 上午10:37:11
	 */
	Boolean changeState(String id,Integer state) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 保存审核人员
	 * @method updateAuditPerson
	 * @param id
	 * @param systemUser
	 * @return
	 * @throws ServiceException
	 * @return Boolean
	 * @date 2016-1-9 下午02:44:44
	 */
	Boolean  updateAuditPerson(String id,SystemUser systemUser) throws ServiceException;
	
	/**
	 * 查找登录实体所在部门的所有收据发票
	 * @param employeeInfo
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryAllWhereInDepartment(EmployeeInfo employeeInfo) throws ServiceException;
}