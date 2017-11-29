package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.StationLetterSendRecord;


/**
 * 站内信发送记录业务接口
 * @ClassName: IStationLetterSendRecordService   
 * @Description: 提供站内信发送记录操作的增删改查   
 * @author HiSay  
 * @date  2016-05-10 14:53:45
 *
 */
public interface IStationLetterSendRecordService extends IBaseService<StationLetterSendRecord, String> {
	/**
	 * 保存站内信发送记录实体
	 * @Title: IStationLetterSendRecordService
	 * @Description:
	 * @param @param opertator_id  操作员id
	 * @param @param letter_id  站内信id
	 * @param @param objectFlag  对象标识
	 * @param @param object_id  对象id
	 * @param @param sendCount  发送数量
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String opertator_id,String letter_id,String objectFlag,String object_id,Integer sendCount, String versionFlag) throws ServiceException;
	
	/**
	 * 修改站内信发送记录实体
	 * @Title: IStationLetterSendRecordService
	 * @Description:
	 * @param @param opertator_id  操作员id
	 * @param @param letter_id  站内信id
	 * @param @param objectFlag  对象标识
	 * @param @param object_id  对象id
	 * @param @param sendCount  发送数量
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String opertator_id,String letter_id,String objectFlag,String object_id,Integer sendCount, String versionFlag) throws ServiceException;
	
	/**
	 * 移除站内信发送记录实体
	 * @Title: IStationLetterSendRecordService
	 * @Description: 
	 * @param @param id 站内信发送记录ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 查询站内信发送给指定部门员工的记录
	 * @param id 站内信id
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<StationLetterSendRecord> queryByDept(String id, String versionFlag) throws ServiceException;

	/**
	 * 查询发送给渠道客户的记录
	 * @param id 站内信id
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<StationLetterSendRecord> queryByChannelType(String id,
			String versionFlag) throws ServiceException;
}