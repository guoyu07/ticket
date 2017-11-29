package com.ticket.serviceImpl;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.SysUserExtends;
import com.ticket.service.ISysUserExtendsService;
import com.ticket.util.DecoderUtil;

/**
 * 用户扩展表业务接口实现类
 * @ClassName: ISysUserExtendsService   
 * @Description: 提供用户扩展表操作的增删改查   
 * @author HiSay  
 * @date 2015-10-23 15:14:44
 *
 */
public class SysUserExtendsServiceImpl extends BaseServiceImpl<SysUserExtends, String> implements ISysUserExtendsService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(SysUserExtendsServiceImpl.class);
	
	@Override
	public boolean merge(String id, String user_id,String name,String type,String value, String versionFlag) throws ServiceException {
		SysUserExtends sysUserExtends = dbDAO.queryById(this.getTableNameFromEntity(SysUserExtends.class), id);
		sysUserExtends.setUser_id(DecoderUtil.UtfDecoder(user_id));
		sysUserExtends.setName(DecoderUtil.UtfDecoder(name));
		sysUserExtends.setType(DecoderUtil.UtfDecoder(type));
		sysUserExtends.setValue(DecoderUtil.UtfDecoder(value));
		CommonEntity status = sysUserExtends.getStatus();
		status.setVersionFlag(versionFlag);
		sysUserExtends.setStatus(status);
		dbDAO.merge(sysUserExtends);
		return true;
	}

	@Override
	public boolean persist(String user_id,String name,String type,String value, String versionFlag) throws ServiceException {
		SysUserExtends sysUserExtends = new SysUserExtends();
		sysUserExtends.setUser_id(DecoderUtil.UtfDecoder(user_id));
		sysUserExtends.setName(DecoderUtil.UtfDecoder(name));
		sysUserExtends.setType(DecoderUtil.UtfDecoder(type));
		sysUserExtends.setValue(DecoderUtil.UtfDecoder(value));
		CommonEntity status = sysUserExtends.getStatus();
		status.setVersionFlag(versionFlag);
		sysUserExtends.setStatus(status);
		dbDAO.persist(sysUserExtends);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		SysUserExtends sysUserExtends = dbDAO.queryById(this.getTableNameFromEntity(SysUserExtends.class), id);
		dbDAO.remove(sysUserExtends);
		return true;
	}

}