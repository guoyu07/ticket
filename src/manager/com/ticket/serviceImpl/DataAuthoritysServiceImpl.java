package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.DataAuthoritys;
import com.ticket.service.IDataAuthoritysService;
import com.ticket.util.DecoderUtil;

/**
 * 数据权限业务接口实现类
 * @ClassName: IDataAuthoritysService   
 * @Description: 提供数据权限操作的增删改查   
 * @author HiSay  
 * @date 2016-05-25 15:20:21
 *
 */
public class DataAuthoritysServiceImpl extends BaseServiceImpl<DataAuthoritys, String> implements IDataAuthoritysService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(DataAuthoritysServiceImpl.class);
	
	@Override
	public boolean merge(String id, String name,String descript,String content,String systemModulId,String inMethod, String versionFlag) throws ServiceException {
		DataAuthoritys dataAuthoritys = dbDAO.queryById(this.getTableNameFromEntity(DataAuthoritys.class), id);
		dataAuthoritys.setName(DecoderUtil.UtfDecoder(name));
		dataAuthoritys.setDescript(DecoderUtil.UtfDecoder(descript));
		dataAuthoritys.setContent(DecoderUtil.UtfDecoder(content));
		dataAuthoritys.setSystemModulId(DecoderUtil.UtfDecoder(systemModulId));
		dataAuthoritys.setInMethod(DecoderUtil.UtfDecoder(inMethod));
		CommonEntity status = dataAuthoritys.getStatus();
		status.setVersionFlag(versionFlag);
		dataAuthoritys.setStatus(status);
		dbDAO.merge(dataAuthoritys);
		return true;
	}

	@Override
	public boolean persist(String name,String descript,String content,String systemModulId,String inMethod, String versionFlag) throws ServiceException {
		DataAuthoritys dataAuthoritys = new DataAuthoritys();
		dataAuthoritys.setName(DecoderUtil.UtfDecoder(name));
		dataAuthoritys.setDescript(DecoderUtil.UtfDecoder(descript));
		dataAuthoritys.setContent(DecoderUtil.UtfDecoder(content));
		dataAuthoritys.setSystemModulId(DecoderUtil.UtfDecoder(systemModulId));
		dataAuthoritys.setInMethod(DecoderUtil.UtfDecoder(inMethod));
		CommonEntity status = dataAuthoritys.getStatus();
		status.setVersionFlag(versionFlag);
		dataAuthoritys.setStatus(status);
		dbDAO.persist(dataAuthoritys);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		DataAuthoritys dataAuthoritys = dbDAO.queryById(this.getTableNameFromEntity(DataAuthoritys.class), id);
		dbDAO.remove(dataAuthoritys);
		return true;
	}

	@Override
	public List<DataAuthoritys> queryBuSystemModulId(String id) {
		List<DataAuthoritys> authoritys = dbDAO.executeJPQLForQueryEntity("select c from " + DataAuthoritys.class.getName() + " c where c.systemModulId = ?", id);
		return authoritys;
	}

	@Override
	public List<DataAuthoritys> queryByEmployeeId(String employeeId) {
		List<DataAuthoritys> authoritys = dbDAO.executeJPQLForQueryEntity("select c from DataAuthoritys c ,EmployeeInfo e,Position p,PositionDataAuthority pd " +
				" where c.id= pd.dataAuthoritys_id and pd.position_id = p.id and p.id = e.position_id and e.id = ?", employeeId);
		return authoritys;
	}

}