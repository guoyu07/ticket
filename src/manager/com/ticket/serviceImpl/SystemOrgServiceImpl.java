package com.ticket.serviceImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.SystemOrg;
import com.ticket.service.ISystemOrgService;

/**
 * 服务厅表业务接口实现类
 * @ClassName: IBjdjHallService   
 * @Description: 提供服务厅表操作的增删改查   
 * @author HiSay  
 * @date 2015-10-23 15:24:57
 */
public class SystemOrgServiceImpl extends TreeServiceImpl implements ISystemOrgService<Object, String> {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(SystemOrgServiceImpl.class);

	@Override
	public SystemOrg persistObj(SystemOrg org) throws ServiceException {
		
		dbDAO.persist(org);
		return org;
	}

	@Override
	public SystemOrg mergeObj(SystemOrg org) throws ServiceException {
		
		SystemOrg oldOrg = dbDAO.get(SystemOrg.class, org.getId());
		oldOrg.setName(org.getName());
		oldOrg.setParent(org.getParent());
		oldOrg.getStatus().setOrderValue(org.getStatus().getOrderValue());
		oldOrg.setDescription(org.getDescription());
		dbDAO.merge(oldOrg);
		return org;
	}
}