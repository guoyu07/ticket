package com.ticket.service;

import java.util.Date;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.StationLetterReadLog;


/**
 * 站内信阅读日志业务接口
 * @ClassName: IStationLetterReadLogService   
 * @Description: 提供站内信阅读日志操作的增删改查   
 * @author HiSay  
 * @date  2016-05-09 14:18:24
 *
 */
public interface IStationLetterReadLogService extends IBaseService<StationLetterReadLog, String> {
	/**
	 * 保存站内信阅读日志实体
	 * @Title: IStationLetterReadLogService
	 * @Description:
	 * @param @param object_id  接收站内信对象id
	 * @param @param isRead  是否阅读
	 * @param @param letter_id  信息id
	 * @param @param readDate  阅读日期
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String object_id,Integer isRead,String letter_id,Date readDate, String versionFlag) throws ServiceException;
	
	/**
	 * 修改站内信阅读日志实体
	 * @Title: IStationLetterReadLogService
	 * @Description:
	 * @param @param object_id  接收站内信对象id
	 * @param @param isRead  是否阅读
	 * @param @param letter_id  信息id
	 * @param @param readDate  阅读日期
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String object_id,Integer isRead,String letter_id,Date readDate, String versionFlag) throws ServiceException;
	
	/**
	 * 移除站内信阅读日志实体
	 * @Title: IStationLetterReadLogService
	 * @Description: 
	 * @param @param id 站内信阅读日志ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 查找用户的站内信阅读日志
	 * @param object_id 用户id
	 * @param versionFlag
	 * @param pageSize 页面大小
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryByEmployeeOrCustomer(String object_id, String versionFlag,
			int pageSize) throws ServiceException;

	/**
	 * 设置站内信为已读
	 * @param id 站内信id
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	boolean setLetterRead(String id, String versionFlag) throws ServiceException;
	
	/**
	 * 根据站内信id统计已经阅读的数量
	 * @param letter_id 站内信id
	 * @return
	 * @throws ServiceException
	 */
	Long countReadCountByLetterId(String letter_id) throws ServiceException;
	
	/**
	 * 根据站内信id统计已发送的数量
	 * @param letter_id 站内信id
	 * @return
	 * @throws ServiceException
	 */
	Long countAllCountByLetterId(String letter_id) throws ServiceException;
}