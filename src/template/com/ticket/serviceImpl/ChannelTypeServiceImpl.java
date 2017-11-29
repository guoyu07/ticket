package com.ticket.serviceImpl;


import java.util.List;

import javax.annotation.Resource;

import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.ChannelCustomerInfo;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.ChannelType;
import com.ticket.pojo.SystemDictionary;
import com.ticket.service.IChannelTypeService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 渠道分类业务接口实现类
 * @ClassName: IChannelTypeService   
 * @Description: 提供渠道分类操作的增删改查   
 * @author HiSay  
 * @date 2015-11-03 17:39:54
 *
 */
public class ChannelTypeServiceImpl extends BaseServiceImpl<ChannelType, String> implements IChannelTypeService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(ChannelTypeServiceImpl.class);
	@Resource
	private ISystemDictionaryService systemDictionaryService = null;
	
	@Override
	public boolean merge(String id, String name,String descript, String systemDictionary_id,Integer empCustomerFlag) throws ServiceException {
		ChannelType channelType = dbDAO.queryById(this.getTableNameFromEntity(ChannelType.class), id);
		channelType.setName(DecoderUtil.UtfDecoder(name));
		channelType.setDescript(DecoderUtil.UtfDecoder(descript));
		if(GeneralUtil.isNotNull(systemDictionary_id)){
			channelType.setType(systemDictionaryService
					.queryById(SystemDictionary.class.getSimpleName(), systemDictionary_id));
		}else{
			channelType.setType(null);
		}
		CommonEntity status = channelType.getStatus();
		status.setVersionFlag(versionFlag);
		status.setHot(empCustomerFlag);
		channelType.setStatus(status);
		dbDAO.merge(channelType);
		return true;
	}

	@Override
	public boolean persist(String name,String descript, String systemDictionary_id,Integer empCustomerFlag) throws ServiceException {
		ChannelType channelType = new ChannelType();
		channelType.setName(DecoderUtil.UtfDecoder(name));
		channelType.setDescript(DecoderUtil.UtfDecoder(descript));
		if(GeneralUtil.isNotNull(systemDictionary_id)){
			channelType.setType(systemDictionaryService
					.queryById(SystemDictionary.class.getSimpleName(), systemDictionary_id));
		}else{
			channelType.setType(null);
		}
		CommonEntity status = channelType.getStatus();
		status.setVersionFlag(versionFlag);
		status.setHot(empCustomerFlag);
		channelType.setStatus(status);
		dbDAO.persist(channelType);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		ChannelType channelType = dbDAO.queryById(this.getTableNameFromEntity(ChannelType.class), id);
		dbDAO.remove(channelType);
		return true;
	}

	@Override
	public List<ChannelType> queryList(String versionFlag)throws ServiceException {
		return dbDAO.queryByList(ChannelType.class.getSimpleName(), deleteFlag, versionFlag, null, null, orderBy, null);
	}

	@Override
	public List<ChannelType> queryEmpCustomerTypeList(String versionFlag)
			throws ServiceException {
		return dbDAO.queryByList(ChannelType.class.getSimpleName(), deleteFlag, versionFlag, "and s.status.hot =?3", new Object[]{ContextConstants.STATUS_OF_ONE}, orderBy, null);
	}

	@Override
	public ChannelType getByChannelCustomer(String channelCustomer_id) {
		
		ChannelType type = dbDAO.executeJPQLForQuerySingle(
				"select t from " + ChannelType.class.getName() + " t, " + ChannelCustomerInfo.class.getName() + " cc"
				+ " where t.id=cc.channelTypeId and cc.id=?", 
				ChannelType.class, channelCustomer_id); 
		return type;
	}

	@Override
	public ChannelType getByName(String name) {
		ChannelType channelType = (ChannelType)dbDAO.executeJPQLForQuerySingle("select c from " + ChannelType.class.getName() + " c where c.name = ?", name); 
		return channelType;
	}
	
	

}