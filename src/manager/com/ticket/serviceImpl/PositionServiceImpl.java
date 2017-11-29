package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.Position;
import com.ticket.service.IPositionService;
import com.ticket.util.DecoderUtil;

/**
 * 员工岗位职责业务接口实现类
 * @ClassName: IPositionService   
 * @Description: 提供员工岗位职责操作的增删改查   
 * @author HiSay  
 * @date 2016-01-09 09:51:13
 *
 */
public class PositionServiceImpl extends BaseServiceImpl<Position, String> implements IPositionService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(PositionServiceImpl.class);
	
	@Override
	public boolean merge(String id, String name,String duty,String remark, String versionFlag) throws ServiceException {
		Position position = dbDAO.queryById(this.getTableNameFromEntity(Position.class), id);
		position.setName(DecoderUtil.UtfDecoder(name));
		position.setDuty(DecoderUtil.UtfDecoder(duty));
		position.setRemark(remark);
		CommonEntity status = position.getStatus();
		status.setVersionFlag(versionFlag);
		position.setStatus(status);
		dbDAO.merge(position);
		return true;
	}

	@Override
	public boolean persist(String name,String duty,String remark, String versionFlag) throws ServiceException {
		Position position = new Position();
		position.setName(DecoderUtil.UtfDecoder(name));
		position.setDuty(DecoderUtil.UtfDecoder(duty));
		position.setRemark(remark);
		CommonEntity status = position.getStatus();
		status.setVersionFlag(versionFlag);
		position.setStatus(status);
		dbDAO.persist(position);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		Position position = dbDAO.queryById(this.getTableNameFromEntity(Position.class), id);
		dbDAO.remove(position);
		return true;
	}

	@Override
	public List<Position> queryAll(Integer size) throws ServiceException {
		try {
			return dbDAO.queryByListNew(Position.class.getSimpleName(), deleteFlag, versionFlag, 
					null, null, orderBy, size);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Position queryByName(String name) throws ServiceException {
		Position position = dbDAO.executeJPQLForQuerySingle("select c from Position c where c.name = ?",Position.class, name);
		return position;
	}

}