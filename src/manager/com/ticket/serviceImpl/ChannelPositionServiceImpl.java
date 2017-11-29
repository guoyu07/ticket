package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.ChannelPosition;
import com.ticket.pojo.CommonEntity;
import com.ticket.service.IChannelPositionService;
import com.ticket.util.DecoderUtil;

/**
 * 渠道联系人岗位业务接口实现类
 * @ClassName: IChannelPositionService   
 * @Description: 提供渠道联系人岗位操作的增删改查   
 * @author HiSay  
 * @date 2016-01-11 11:35:13
 *
 */
public class ChannelPositionServiceImpl extends BaseServiceImpl<ChannelPosition, String> implements IChannelPositionService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(ChannelPositionServiceImpl.class);
	
	@Override
	public boolean merge(String id, String name,String duty,String remark, String versionFlag) throws ServiceException {
		ChannelPosition channelPosition = dbDAO.queryById(this.getTableNameFromEntity(ChannelPosition.class), id);
		channelPosition.setName(DecoderUtil.UtfDecoder(name));
		channelPosition.setDuty(DecoderUtil.UtfDecoder(duty));
		channelPosition.setRemark(DecoderUtil.UtfDecoder(remark));
		CommonEntity status = channelPosition.getStatus();
		status.setVersionFlag(versionFlag);
		channelPosition.setStatus(status);
		dbDAO.merge(channelPosition);
		return true;
	}

	@Override
	public boolean persist(String name,String duty,String remark, String versionFlag) throws ServiceException {
		ChannelPosition channelPosition = new ChannelPosition();
		channelPosition.setName(DecoderUtil.UtfDecoder(name));
		channelPosition.setDuty(DecoderUtil.UtfDecoder(duty));
		channelPosition.setRemark(DecoderUtil.UtfDecoder(remark));
		CommonEntity status = channelPosition.getStatus();
		status.setVersionFlag(versionFlag);
		channelPosition.setStatus(status);
		dbDAO.persist(channelPosition);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		ChannelPosition channelPosition = dbDAO.queryById(this.getTableNameFromEntity(ChannelPosition.class), id);
		dbDAO.remove(channelPosition);
		return true;
	}
	
	
	@Override
	public List<ChannelPosition> queryAll(Integer size) throws ServiceException {
		try {
			return dbDAO.queryByListNew(ChannelPosition.class.getSimpleName(), deleteFlag, versionFlag, 
					null, null, orderBy, size);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}