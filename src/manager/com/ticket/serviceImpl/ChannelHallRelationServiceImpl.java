package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjHall;
import com.ticket.pojo.ChannelHallRelation;
import com.ticket.pojo.SystemDictionary;
import com.ticket.service.IChannelHallRelationService;
import com.ticket.util.ResourceUtil;

/**
 * 渠道类型服务大厅关联关系业务接口实现类
 * @ClassName: IChannelHallRelationService   
 * @Description: 提供渠道类型服务大厅关联关系操作的增删改查   
 * @author tuyou  
 * @date 2016-03-18 16:22:12
 *
 */
public class ChannelHallRelationServiceImpl extends BaseServiceImpl<ChannelHallRelation, String> implements IChannelHallRelationService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(ChannelHallRelationServiceImpl.class);
	
	@Override
	public boolean persist(String hall,String channel, String versionFlag) throws ServiceException {
		
		ChannelHallRelation channelHallRelation = queryByChannelAndHall(channel, hall);
		if(channelHallRelation != null){
			
			throw new ServiceException(ResourceUtil.getText("repeat"));
		}
		
		channelHallRelation = new ChannelHallRelation();
		BjdjHall hallObj = get(BjdjHall.class, hall);
		channelHallRelation.setHall(hallObj);
		SystemDictionary channelObj = get(SystemDictionary.class, channel);
		channelHallRelation.setChannel(channelObj);
		dbDAO.persist(channelHallRelation);
		return true;
	}
	
	@Override
	public boolean merge(String id, String hall,String channel, String versionFlag) throws ServiceException {
		
		ChannelHallRelation channelHallRelation = queryByChannelAndHall(channel, hall);
		if(channelHallRelation != null){
			
			throw new ServiceException(ResourceUtil.getText("repeat"));
		}
		channelHallRelation = dbDAO.queryById(this.getTableNameFromEntity(ChannelHallRelation.class), id);
		BjdjHall hallObj = get(BjdjHall.class, hall);
		channelHallRelation.setHall(hallObj);
		SystemDictionary channelObj = get(SystemDictionary.class, channel);
		channelHallRelation.setChannel(channelObj);
		dbDAO.merge(channelHallRelation);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		ChannelHallRelation ChannelHallRelation = dbDAO.queryById(this.getTableNameFromEntity(ChannelHallRelation.class), id);
		dbDAO.remove(ChannelHallRelation);
		return true;
	}

	@Override
	public List<ChannelHallRelation> queryByChannel(SystemDictionary channel) {
		
		List<ChannelHallRelation> list = dbDAO.executeJPQLForQueryEntity(
				"select s from " + ChannelHallRelation.class.getName() + " s where s.channel.id=?", channel.getId());
		return list;
	}

	@Override
	public ChannelHallRelation queryByChannelAndHall(String channel_id, String hall_id) {
		
		ChannelHallRelation list = dbDAO.executeJPQLForQuerySingle(
				"select s from " + ChannelHallRelation.class.getName() + " s where s.channel.id=? and s.hall.id=?", 
				ChannelHallRelation.class, channel_id, hall_id);
		return list;
	}

}