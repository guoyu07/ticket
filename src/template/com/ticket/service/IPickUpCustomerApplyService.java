package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.PickUpCustomerApply;


/**
 * 捡单客户申请业务接口
 * @ClassName: IPickUpCustomerApplyService   
 * @Description: 提供捡单客户申请操作的增删改查   
 * @author HiSay  
 * @date  2015-11-24 15:45:34
 *
 */
public interface IPickUpCustomerApplyService extends IBaseService<PickUpCustomerApply, String> {
	/**
	 * 保存捡单客户申请实体
	 * @Title: IPickUpCustomerApplyService
	 * @Description:
	 * @param @param employee_id  申请人
	 * @param @param customer_id  客户
	 * @param @param applyState  申请状态
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String employee_id,String customer_id,Integer applyState,String oldemployee_id, String versionFlag) throws ServiceException;
	
	/**
	 * 修改捡单客户申请实体
	 * @Title: IPickUpCustomerApplyService
	 * @Description:
	 * @param @param employee_id  申请人
	 * @param @param customer_id  客户
	 * @param @param applyState  申请状态
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String employee_id,String customer_id,Integer applyState,String oldemployee_id, String versionFlag) throws ServiceException;
	
	/**
	 * 移除捡单客户申请实体
	 * @Title: IPickUpCustomerApplyService
	 * @Description: 
	 * @param @param id 捡单客户申请ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	
	boolean isExist(String employee_id,String customer_id);
	/**
	 * 转移客户数据至目标员工
	 * @param employee_id 客户id
	 * @param customer_id 原员工
	 * @param newCustomer_id 新员工
	 * @return
	 */
	boolean TransferData(String id,String employee_id,String customer_id,String oldemployee_id);

	
}