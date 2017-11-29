package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.PrivilegeInfo;
import com.ticket.pojo.RolePrivilege;
import com.ticket.pojo.SystemModule;
import com.ticket.service.IRolePrivilegeService;
import com.ticket.util.DecoderUtil;

/**
 * 角色权限业务接口实现类
 * @ClassName: IRolePrivilegeService   
 * @Description: 提供角色权限操作的增删改查   
 * @author HiSay  
 * @date 2015-10-17 11:28:35
 *
 */
public class RolePrivilegeServiceImpl extends BaseServiceImpl<RolePrivilege, String> implements IRolePrivilegeService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(RolePrivilegeServiceImpl.class);
	
	@Override
	public boolean merge(String id, String roleId,String systemModuleId,String privilegeId, String versionFlag) throws ServiceException {
		RolePrivilege rolePrivilege = dbDAO.queryById(this.getTableNameFromEntity(RolePrivilege.class), id);
		rolePrivilege.setRoleId(DecoderUtil.UtfDecoder(roleId));
		rolePrivilege.setSystemModuleId(DecoderUtil.UtfDecoder(systemModuleId));
		rolePrivilege.setPrivilegeId(DecoderUtil.UtfDecoder(privilegeId));
		CommonEntity status = rolePrivilege.getStatus();
		status.setVersionFlag(versionFlag);
		rolePrivilege.setStatus(status);
		dbDAO.merge(rolePrivilege);
		return true;
	}

	@Override
	public boolean persist(String roleId,String systemModuleId,String privilegeId, String versionFlag) throws ServiceException {
		RolePrivilege rolePrivilege = new RolePrivilege();
		rolePrivilege.setRoleId(DecoderUtil.UtfDecoder(roleId));
		rolePrivilege.setSystemModuleId(DecoderUtil.UtfDecoder(systemModuleId));
		rolePrivilege.setPrivilegeId(DecoderUtil.UtfDecoder(privilegeId));
		CommonEntity status = rolePrivilege.getStatus();
		status.setVersionFlag(versionFlag);
		rolePrivilege.setStatus(status);
		dbDAO.persist(rolePrivilege);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		RolePrivilege rolePrivilege = dbDAO.queryById(this.getTableNameFromEntity(RolePrivilege.class), id);
		dbDAO.remove(rolePrivilege);
		return true;
	}

	@Override
	public RolePrivilege queryByRIDAndMIDAndPID(String roleId, String moduleId,
			String privilegeId, String versionFlag) throws ServiceException {
		return dbDAO.queryByCustom(RolePrivilege.class.getSimpleName(), deleteFlag, versionFlag, "and s.roleId =?3 and s.systemModuleId =?4 and s.privilegeId =?5", new Object[]{roleId,moduleId,privilegeId});
	}
	
	@Override
	public void selectAll(String roleId, List<SystemModule> moduleList,
			List<PrivilegeInfo> privilegeList, String versionFlag) throws ServiceException {
		for(SystemModule systemModule:moduleList){
			for(PrivilegeInfo privilegeInfo:privilegeList){
				this.persist(roleId, systemModule.getId(), privilegeInfo.getId(), versionFlag);
			}
				
		}
		
	}
	
	@Override
	public void selectNone(String roleId, String versionFlag)
			throws ServiceException {
		dbDAO.deleteByWhere(RolePrivilege.class.getSimpleName(), deleteFlag, versionFlag, "and s.roleId =?3", new Object[]{roleId});
		
	}

	@Override
	public List<RolePrivilege> queryByRoleId(String roleId)
			throws ServiceException {
		List<RolePrivilege> list = dbDAO.executeJPQLForQueryEntity("select c from " + RolePrivilege.class.getName() + " c where c.roleId = ?", roleId);
		return list;
	}
}