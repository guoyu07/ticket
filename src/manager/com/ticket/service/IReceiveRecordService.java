package com.ticket.service;

import java.util.Date;
import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.ReceiveRecord;


/**
 * 领取记录业务接口
 * @ClassName: IReceiveRecordService   
 * @Description: 提供领取记录操作的增删改查   
 * @author HiSay  
 * @date  2015-11-23 16:26:07
 *
 */
public interface IReceiveRecordService extends IBaseService<ReceiveRecord, String> {
	/**
	 * 保存领取记录实体
	 * @Title: IReceiveRecordService
	 * @Description:
	 * @param @param lostGoods_id  物品id
	 * @param @param personName  领取人姓名
	 * @param @param receiveTime  领取时间
	 * @param @param certificateType  证件类型
	 * @param @param receiveCertificateNumber  证件号码
	 * @param @param phone  领取人电话
	 * @param @param receiveWay  领取方式
	 * @param @param writeOffPerson  核销人
	 * @param @param putOutPerson  发放人
	 * @param @param remark  备注
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String lostGoods_id,String personName,String receiveTime,String certificateType,String receiveCertificateNumber,String phone,String receiveWay,String writeOffPerson,String putOutPerson,String remark,String writeOffTime,String putOutTime, String versionFlag) throws ServiceException;
	
	/**
	 * 修改领取记录实体
	 * @Title: IReceiveRecordService
	 * @Description:
	 * @param @param lostGoods_id  物品id
	 * @param @param personName  领取人姓名
	 * @param @param receiveTime  领取时间
	 * @param @param certificateType  证件类型
	 * @param @param receiveCertificateNumber  证件号码
	 * @param @param phone  领取人电话
	 * @param @param receiveWay  领取方式
	 * @param @param writeOffPerson  核销人
	 * @param @param putOutPerson  发放人
	 * @param @param remark  备注
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String lostGoods_id,String personName,String receiveTime,String certificateType,String receiveCertificateNumber,String phone,String receiveWay,String writeOffPerson,String putOutPerson,String remark,String writeOffTime,String putOutTime, String versionFlag) throws ServiceException;
	
	/**
	 * 移除领取记录实体
	 * @Title: IReceiveRecordService
	 * @Description: 
	 * @param @param id 领取记录ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 批量彻底删除领取记录
	 * @param idsValue
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException;
	
	/**
	 * 根据指定时间范围查找领取记录信息
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ServiceException
	 */
	List<ReceiveRecord> querByTimes(Date startTime,Date endTime) throws ServiceException;
	
	ReceiveRecord queryByGoodsId(String lostGoods_id) throws ServiceException;
}