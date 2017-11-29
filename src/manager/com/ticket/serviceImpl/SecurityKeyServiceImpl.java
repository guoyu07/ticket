package com.ticket.serviceImpl;


import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.SecurityKey;
import com.ticket.service.ISecurityKeyService;
import com.ticket.util.DecoderUtil;

/**
 * 安全秘钥管理表业务接口实现类
 * @ClassName: ISecurityKeyService   
 * @Description: 提供安全秘钥管理表操作的增删改查   
 * @author HiSay  
 * @date 2016-01-21 14:43:44
 *
 */
public class SecurityKeyServiceImpl extends BaseServiceImpl<SecurityKey, String> implements ISecurityKeyService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(SecurityKeyServiceImpl.class);
	
	@Override
	public boolean persist(String publicKey, String remark, String versionFlag) throws ServiceException {
		SecurityKey securityKey = new SecurityKey();
		securityKey.setPublicKey(DecoderUtil.UtfDecoder(publicKey));
		securityKey.setSecretKey(UUID.randomUUID().toString().replaceAll("-", ""));
		securityKey.setRemark(remark);
		CommonEntity status = securityKey.getStatus();
		status.setVersionFlag(versionFlag);
		securityKey.setStatus(status);
		dbDAO.persist(securityKey);
		return true;
	}
	
	@Override
	public boolean merge(String id, String publicKey, String remark, String versionFlag) throws ServiceException {
		SecurityKey securityKey = dbDAO.queryById(this.getTableNameFromEntity(SecurityKey.class), id);
		securityKey.setPublicKey(DecoderUtil.UtfDecoder(publicKey));
		securityKey.setRemark(remark);
		CommonEntity status = securityKey.getStatus();
		status.setVersionFlag(versionFlag);
		securityKey.setStatus(status);
		dbDAO.merge(securityKey);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		SecurityKey securityKey = dbDAO.queryById(this.getTableNameFromEntity(SecurityKey.class), id);
		dbDAO.remove(securityKey);
		return true;
	}

	@Override
	public SecurityKey getByPublicKey(String publicKey) {

		String tableName = SecurityKey.class.getName();
		SecurityKey key = dbDAO.executeJPQLForQuerySingle("select c from " + tableName + " c where publicKey=?", SecurityKey.class, publicKey);
		return key;
	}

}