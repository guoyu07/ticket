package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.ChannelHallRelation;
import com.ticket.pojo.SystemDictionary;


/**
 * 渠道类型服务大厅关联关系业务接口
 * @ClassName: IChannelHallRelationService   
 * @Description: 提供渠道类型服务大厅关联关系操作的增删改查   
 * @author tuyou  
 * @date  2016-03-18 16:22:12
 *
 */
public interface IChannelHallRelationService extends IBaseService<ChannelHallRelation, String> {
	/**
	 * 保存渠道类型服务大厅关联关系实体
	 * @Title: IChannelHallRelationService
	 * @Description:
	 * @param @param hall  大厅
	 * @param @param channel  渠道类型
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String hall,String channel, String versionFlag) throws ServiceException;
	
	/**
	 * 修改渠道类型服务大厅关联关系实体
	 * @Title: IChannelHallRelationService
	 * @Description:
	 * @param @param hall  大厅
	 * @param @param channel  渠道类型
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String hall,String channel, String versionFlag) throws ServiceException;
	
	/**
	 * 移除渠道类型服务大厅关联关系实体
	 * @Title: IChannelHallRelationService
	 * @Description: 
	 * @param @param id 渠道类型服务大厅关联关系ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 通过渠道类型获取关联的所有大厅
	 * @param channel
	 * @return
	 */
	List<ChannelHallRelation> queryByChannel(SystemDictionary channel);
	
	/**
	 * 通过渠道和大厅id获取关联关系
	 * @param channel_id
	 * @param hall_id
	 * @return
	 */
	ChannelHallRelation queryByChannelAndHall(String channel_id, String hall_id);
}