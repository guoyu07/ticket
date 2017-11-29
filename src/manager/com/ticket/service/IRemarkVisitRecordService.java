package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.RemarkVisitRecord;


/**
 * 备注拜访记录业务接口
 * @ClassName: IRemarkVisitRecordService   
 * @Description: 提供备注拜访记录操作的增删改查   
 * @author HiSay  
 * @date  2016-04-28 15:38:02
 *
 */
public interface IRemarkVisitRecordService extends IBaseService<RemarkVisitRecord, String> {
	/**
	 * 保存备注拜访记录实体
	 * @Title: IRemarkVisitRecordService
	 * @Description:
	 * @param @param record_id  记录id
	 * @param @param employee_id  员工id
	 * @param @param remark  备注内容
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String record_id,String employee_id,String remark, String versionFlag) throws ServiceException;
	
	/**
	 * 修改备注拜访记录实体
	 * @Title: IRemarkVisitRecordService
	 * @Description:
	 * @param @param record_id  记录id
	 * @param @param employee_id  员工id
	 * @param @param remark  备注内容
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String record_id,String employee_id,String remark, String versionFlag) throws ServiceException;
	
	/**
	 * 移除备注拜访记录实体
	 * @Title: IRemarkVisitRecordService
	 * @Description: 
	 * @param @param id 备注拜访记录ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 根据拜访记录id查询记录列表
	 * @param record_id 拜访记录id
	 * @param versionFlag
	 * @param pageSize 页面大小
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryEntityByRecordId(String record_id, String versionFlag,
			int pageSize) throws ServiceException;

	/**
	 * 根据记录id查看所有的历史备注
	 * @param record_id 记录id
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<RemarkVisitRecord> queryListByRecordId(String record_id, String versionFlag) throws ServiceException;
}