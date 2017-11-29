package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.AuditVisitRecord;
import com.ticket.pojo.Pagination;


/**
 * 审核拜访记录业务接口
 * @ClassName: IAuditVisitRecordService   
 * @Description: 提供审核拜访记录操作的增删改查   
 * @author HiSay  
 * @date  2016-04-29 10:39:00
 *
 */
public interface IAuditVisitRecordService extends IBaseService<AuditVisitRecord, String> {
	/**
	 * 保存审核拜访记录实体
	 * @Title: IAuditVisitRecordService
	 * @Description:
	 * @param @param record_id  记录id
	 * @param @param employee_id  审核员工id
	 * @param @param phoneOrOut  电话拜访或者是外出拜访
	 * @param @param remark  备注内容
	 * @param @param auditState  审核状态
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String record_id,String employee_id,String remark,String phoneOrOut,Integer auditState, String versionFlag) throws ServiceException;
	
	/**
	 * 修改审核拜访记录实体
	 * @Title: IAuditVisitRecordService
	 * @Description:
	 * @param @param record_id  记录id
	 * @param @param employee_id  审核员工id
	 * @param @param remark  备注内容
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String record_id,String employee_id,String remark, String versionFlag) throws ServiceException;
	
	/**
	 * 移除审核拜访记录实体
	 * @Title: IAuditVisitRecordService
	 * @Description: 
	 * @param @param id 审核拜访记录ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 查询电话拜访记录的审核历史
	 * @param pageSize 页面大小
	 * @param auditState 审核状态
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryByPhone(Integer auditState,int pageSize, String versionFlag) throws ServiceException;

	/**
	 * 查看外出拜访记录的审核历史
	 * @param pageSize 页面大小
	 * @param auditState 审核状态
	 * @param versionFlag
	 * @return
	 */
	Pagination queryByOut(Integer auditState,int pageSize, String versionFlag) throws ServiceException;

}