package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.UserRole;
import com.ticket.service.IUserRoleService;
import com.ticket.util.DecoderUtil;

/**
 * 用户角色业务接口实现类
 * @ClassName: IUserRoleService   
 * @Description: 提供用户角色操作的增删改查   
 * @author HiSay  
 * @date 2015-10-17 09:41:22
 *
 */
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole, String> implements IUserRoleService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(UserRoleServiceImpl.class);
	
	@Override
	public boolean merge(String id, String userId,String roleId, String versionFlag) throws ServiceException {
		UserRole userRole = dbDAO.queryById(this.getTableNameFromEntity(UserRole.class), id);
		userRole.setUserId(DecoderUtil.UtfDecoder(userId));
		userRole.setRoleId(DecoderUtil.UtfDecoder(roleId));
		CommonEntity status = userRole.getStatus();
		status.setVersionFlag(versionFlag);
		userRole.setStatus(status);
		dbDAO.merge(userRole);
		return true;
	}

	@Override
	public boolean persist(String userId,String roleId, String versionFlag) throws ServiceException {
		UserRole userRole = new UserRole();
		userRole.setUserId(DecoderUtil.UtfDecoder(userId));
		userRole.setRoleId(DecoderUtil.UtfDecoder(roleId));
		CommonEntity status = userRole.getStatus();
		status.setVersionFlag(versionFlag);
		userRole.setStatus(status);
		dbDAO.persist(userRole);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		UserRole userRole = dbDAO.queryById(this.getTableNameFromEntity(UserRole.class), id);
		dbDAO.remove(userRole);
		return true;
	}

	@Override
	public UserRole queryByUIdAndRID(String userId, String roleId,
			String versionFlag) throws ServiceException {
		return dbDAO.queryByCustom(UserRole.class.getSimpleName(), deleteFlag, versionFlag, "and s.userId =?3 and s.roleId = ?4", new Object[]{userId,roleId});
	}

	@Override
	public List<UserRole> findByUserId(String employee_id) throws ServiceException {
		List<UserRole> userRoles = dbDAO.executeJPQLForQueryEntity("select c from " + UserRole.class.getName() + " c where c.userId = ?",employee_id);
		return userRoles;
	}

}