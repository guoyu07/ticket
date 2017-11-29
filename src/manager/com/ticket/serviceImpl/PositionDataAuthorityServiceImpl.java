package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.DataAuthoritys;
import com.ticket.pojo.PositionDataAuthority;
import com.ticket.pojo.SystemModule;
import com.ticket.service.IPositionDataAuthorityService;
import com.ticket.util.DecoderUtil;

/**
 * 岗位权限业务接口实现类
 * @ClassName: IPositionDataAuthorityService   
 * @Description: 提供岗位权限操作的增删改查   
 * @author HiSay  
 * @date 2016-05-25 15:20:45
 *
 */
public class PositionDataAuthorityServiceImpl extends BaseServiceImpl<PositionDataAuthority, String> implements IPositionDataAuthorityService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(PositionDataAuthorityServiceImpl.class);
	
	@Override
	public boolean merge(String id, String position_id,String dataAuthoritys_id, String systemModuleId,String versionFlag) throws ServiceException {
		PositionDataAuthority positionDataAuthority = dbDAO.queryById(this.getTableNameFromEntity(PositionDataAuthority.class), id);
		positionDataAuthority.setPosition_id(DecoderUtil.UtfDecoder(position_id));
		positionDataAuthority.setDataAuthoritys_id(DecoderUtil.UtfDecoder(dataAuthoritys_id));
		positionDataAuthority.setSystemModuleId(DecoderUtil.UtfDecoder(systemModuleId));
		CommonEntity status = positionDataAuthority.getStatus();
		status.setVersionFlag(versionFlag);
		positionDataAuthority.setStatus(status);
		dbDAO.merge(positionDataAuthority);
		return true;
	}

	@Override
	public boolean persist(String position_id,String dataAuthoritys_id,String systemModuleId, String versionFlag) throws ServiceException {
		PositionDataAuthority positionDataAuthority = new PositionDataAuthority();
		positionDataAuthority.setPosition_id(DecoderUtil.UtfDecoder(position_id));
		positionDataAuthority.setDataAuthoritys_id(DecoderUtil.UtfDecoder(dataAuthoritys_id));
		positionDataAuthority.setSystemModuleId(DecoderUtil.UtfDecoder(systemModuleId));
		CommonEntity status = positionDataAuthority.getStatus();
		status.setVersionFlag(versionFlag);
		positionDataAuthority.setStatus(status);
		dbDAO.persist(positionDataAuthority);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		PositionDataAuthority positionDataAuthority = dbDAO.queryById(this.getTableNameFromEntity(PositionDataAuthority.class), id);
		dbDAO.remove(positionDataAuthority);
		return true;
	}

	@Override
	public List<PositionDataAuthority> queryBuSystemModulId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PositionDataAuthority queryByRIDAndMIDAndPID(String positionId,
			String moduleId, String authorityId, String versionFlag) {
		return dbDAO.queryByCustom(PositionDataAuthority.class.getSimpleName(), deleteFlag, versionFlag, "and s.position_id =?3 and s.systemModuleId =?4 and s.dataAuthoritys_id =?5", new Object[]{positionId,moduleId,authorityId});
	}

	@Override
	public void selectAll(String position_id, List<SystemModule> moduleList,
			List<DataAuthoritys> authoritys, String versionFlag) throws ServiceException {
		for(SystemModule module : moduleList){
			for(DataAuthoritys authoritys2 : authoritys){
				this.persist(position_id,authoritys2.getId(),module.getId(),versionFlag);
			}
		}
		
	}

	@Override
	public void selectNone(String position_id, String versionFlag)
			throws ServiceException {
		dbDAO.deleteByWhere(PositionDataAuthority.class.getSimpleName(), deleteFlag, versionFlag, "and s.position_id =?3", new Object[]{position_id});
	}

	@Override
	public void deleteOthers(String id) throws ServiceException {
		PositionDataAuthority authority = this.queryById(PositionDataAuthority.class.getName(), id);
		List<PositionDataAuthority> authorities = dbDAO.executeJPQLForQueryEntity("select c from PositionDataAuthority c where c.id != ? and c.position_id = ? and c.systemModuleId = ?", id,authority.getPosition_id(),authority.getSystemModuleId());
		if(authorities != null){
			for(PositionDataAuthority dataAuthority:authorities){
				dbDAO.remove(dataAuthority);
			}
		}
	}

}