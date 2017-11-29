package com.ticket.serviceImpl;


import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.ChannelType;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.ApplayClass;
import com.ticket.service.IApplayClassService;
import com.ticket.util.DecoderUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 申请类别业务接口实现类
 * @ClassName: IApplayClassService   
 * @Description: 提供申请类别操作的增删改查   
 * @author HiSay  
 * @date 2015-11-04 14:18:35
 *
 */
public class ApplayClassServiceImpl extends BaseServiceImpl<ApplayClass, String> implements IApplayClassService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(ApplayClassServiceImpl.class);
	
	@Override
	public boolean merge(String id, String name,String descript, String versionFlag) throws ServiceException {
		ApplayClass applayClass = dbDAO.queryById(this.getTableNameFromEntity(ApplayClass.class), id);
		applayClass.setName(DecoderUtil.UtfDecoder(name));
		applayClass.setDescript(DecoderUtil.UtfDecoder(descript));
		CommonEntity status = applayClass.getStatus();
		status.setVersionFlag(versionFlag);
		applayClass.setStatus(status);
		dbDAO.merge(applayClass);
		return true;
	}

	@Override
	public boolean persist(String name,String descript, String versionFlag) throws ServiceException {
		ApplayClass applayClass = new ApplayClass();
		applayClass.setName(DecoderUtil.UtfDecoder(name));
		applayClass.setDescript(DecoderUtil.UtfDecoder(descript));
		CommonEntity status = applayClass.getStatus();
		status.setVersionFlag(versionFlag);
		applayClass.setStatus(status);
		dbDAO.persist(applayClass);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		ApplayClass applayClass = dbDAO.queryById(this.getTableNameFromEntity(ApplayClass.class), id);
		dbDAO.remove(applayClass);
		return true;
	}

	@Override
	public List<ApplayClass> queryList(String versionFlag)
			throws ServiceException {
		return dbDAO.queryByList(ApplayClass.class.getSimpleName(), deleteFlag, versionFlag, null, null, orderBy, null);
	}

}