package com.ticket.serviceImpl;


import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.ApplayClass;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.SurvryModular;
import com.ticket.service.ISurvryModularService;
import com.ticket.util.DecoderUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 调查模块业务接口实现类
 * @ClassName: ISurvryModularService   
 * @Description: 提供调查模块操作的增删改查   
 * @author HiSay  
 * @date 2015-11-11 17:08:07
 *
 */
public class SurvryModularServiceImpl extends BaseServiceImpl<SurvryModular, String> implements ISurvryModularService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(SurvryModularServiceImpl.class);
	
	@Override
	public boolean merge(String id, String name,String descript, String versionFlag) throws ServiceException {
		SurvryModular survryModular = dbDAO.queryById(this.getTableNameFromEntity(SurvryModular.class), id);
		survryModular.setName(DecoderUtil.UtfDecoder(name));
		survryModular.setDescript(DecoderUtil.UtfDecoder(descript));
		CommonEntity status = survryModular.getStatus();
		status.setVersionFlag(versionFlag);
		survryModular.setStatus(status);
		dbDAO.merge(survryModular);
		return true;
	}

	@Override
	public boolean persist(String name,String descript, String versionFlag) throws ServiceException {
		SurvryModular survryModular = new SurvryModular();
		survryModular.setName(DecoderUtil.UtfDecoder(name));
		survryModular.setDescript(DecoderUtil.UtfDecoder(descript));
		CommonEntity status = survryModular.getStatus();
		status.setVersionFlag(versionFlag);
		survryModular.setStatus(status);
		dbDAO.persist(survryModular);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		SurvryModular survryModular = dbDAO.queryById(this.getTableNameFromEntity(SurvryModular.class), id);
		dbDAO.remove(survryModular);
		return true;
	}

	@Override
	public List<SurvryModular> queryList(String versionFlag)
			throws ServiceException {
		return dbDAO.queryByList(SurvryModular.class.getSimpleName(), deleteFlag, versionFlag, null, null, orderBy, null);
	}

}