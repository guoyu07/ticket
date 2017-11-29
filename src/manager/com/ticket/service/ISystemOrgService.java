package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.SystemOrg;

/**
 * @Description：系统组织机构业务层
 * @author：涂有
 * @date 2015年12月31日 上午10:53:58
 */
public interface ISystemOrgService<T, ID> extends ITreeService<Object, String>{
	
	/**
	 * @Description：保存实体
	 * @date 2015年12月31日 下午4:28:41
	 * @param org
	 * @return
	 * @throws ServiceException
	 */
	public SystemOrg persistObj(SystemOrg org) throws ServiceException;
	
	/**
	 * @Description：修改实体
	 * @date 2015年12月31日 下午4:28:50
	 * @param org
	 * @return
	 * @throws ServiceException
	 */
	public SystemOrg mergeObj(SystemOrg org) throws ServiceException;
	
}