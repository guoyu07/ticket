package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.Regulation;
import com.ticket.service.IRegulationService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.PaginationContext;

/**
 * 规章制度信息业务接口实现类
 * @ClassName: IRegulationService   
 * @Description: 提供规章制度信息操作的增删改查   
 * @author HiSay  
 * @date 2015-11-20 13:23:55
 *
 */
public class RegulationServiceImpl extends BaseServiceImpl<Regulation, String> implements IRegulationService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(RegulationServiceImpl.class);
	
	@Override
	public boolean merge(String id, String type_id,String title,String content, String versionFlag, Integer orderValue) throws ServiceException {
		Regulation regulation = dbDAO.queryById(this.getTableNameFromEntity(Regulation.class), id);
		regulation.setType_id(DecoderUtil.UtfDecoder(type_id));
		regulation.setTitle(DecoderUtil.UtfDecoder(title));
		regulation.setContent(DecoderUtil.UtfDecoder(content));
		CommonEntity status = regulation.getStatus();
		status.setVersionFlag(versionFlag);
		status.setOrderValue(orderValue) ;
		regulation.setStatus(status);
		dbDAO.merge(regulation);
		return true;
	}

	@Override
	public boolean persist(String type_id,String title,String content, String versionFlag, Integer orderValue) throws ServiceException {
		Regulation regulation = new Regulation();
		regulation.setType_id(DecoderUtil.UtfDecoder(type_id));
		regulation.setTitle(DecoderUtil.UtfDecoder(title));
		regulation.setContent(DecoderUtil.UtfDecoder(content));
		CommonEntity status = regulation.getStatus();
		status.setVersionFlag(versionFlag);
		status.setOrderValue(orderValue);
		regulation.setStatus(status);
		dbDAO.persist(regulation);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		Regulation regulation = dbDAO.queryById(this.getTableNameFromEntity(Regulation.class), id);
		dbDAO.remove(regulation);
		return true;
	}

	@Override
	public boolean batchRealDelete(String idsValue, String versionFlag)
			throws ServiceException {
		idsValue = GeneralUtil.convertIdsValueToStringByDelete(idsValue, ",");
		dbDAO.deleteAll(Regulation.class.getSimpleName(), idsValue, null);
		return true;
	}

	@Override
	public List<Regulation> queryListByType(String type_id, String versionFlag)
			throws ServiceException {
		List<Regulation> list = dbDAO.queryByList(Regulation.class.getSimpleName(), deleteFlag, versionFlag, "and s.type_id =?3", new Object[]{type_id}, orderValueOrderBy, null);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public Pagination queryByAdmin(String versionFlag, Integer pageSize)
			throws ServiceException {
		return dbDAO.queryByPageModule(Regulation.class.getSimpleName(), deleteFlag, versionFlag, null, null, orderValueOrderBy, PaginationContext.getOffset(), pageSize);
	}

	@Override
	public Regulation findByTitle(String title) throws ServiceException {
		Regulation regulation = (Regulation)dbDAO.executeJPQLForQuerySingle("select c from " + Regulation.class.getName() + " c where c.title = ?", title);
		return regulation;
	}
}