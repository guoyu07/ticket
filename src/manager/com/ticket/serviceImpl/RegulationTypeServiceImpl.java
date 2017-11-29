package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.RegulationType;
import com.ticket.service.IRegulationTypeService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.PaginationContext;

/**
 * 规章制度类别业务接口实现类
 * @ClassName: IRegulationTypeService   
 * @Description: 提供规章制度类别操作的增删改查   
 * @author HiSay  
 * @date 2015-11-20 13:20:33
 *
 */
public class RegulationTypeServiceImpl extends BaseServiceImpl<RegulationType, String> implements IRegulationTypeService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(RegulationTypeServiceImpl.class);
	
	@Override
	public boolean merge(String id, String name,String descript, String versionFlag, Integer orderValue) throws ServiceException {
		RegulationType regulationType = dbDAO.queryById(this.getTableNameFromEntity(RegulationType.class), id);
		regulationType.setName(DecoderUtil.UtfDecoder(name));
		regulationType.setDescript(DecoderUtil.UtfDecoder(descript));
		CommonEntity status = regulationType.getStatus();
		status.setVersionFlag(versionFlag);
		status.setOrderValue(orderValue) ;
		regulationType.setStatus(status);
		dbDAO.merge(regulationType);
		return true;
	}

	@Override
	public boolean persist(String name,String descript, String versionFlag, Integer orderValue) throws ServiceException {
		RegulationType regulationType = new RegulationType();
		regulationType.setName(DecoderUtil.UtfDecoder(name));
		regulationType.setDescript(DecoderUtil.UtfDecoder(descript));
		CommonEntity status = regulationType.getStatus();
		status.setVersionFlag(versionFlag);
		status.setOrderValue(orderValue) ;
		regulationType.setStatus(status);
		dbDAO.persist(regulationType);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		RegulationType regulationType = dbDAO.queryById(this.getTableNameFromEntity(RegulationType.class), id);
		dbDAO.remove(regulationType);
		return true;
	}

	@Override
	public boolean batchRealDelete(String idsValue, String versionFlag)
			throws ServiceException {
		idsValue = GeneralUtil.convertIdsValueToStringByDelete(idsValue, ",");
		dbDAO.deleteAll(RegulationType.class.getSimpleName(), idsValue, null);
		return true;
	}

	@Override
	public List<RegulationType> queryList(String versionFlag) throws ServiceException {
		List<RegulationType> list = dbDAO.queryByList(RegulationType.class.getSimpleName(), deleteFlag, versionFlag, null, null, orderValueOrderBy, null);
		if(list != null && !list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public Pagination queryByAdmin(String versionFlag, Integer pageSize)
			throws ServiceException {
		return dbDAO.queryByPageModule(RegulationType.class.getSimpleName(), deleteFlag, versionFlag, null, null, orderValueOrderBy, PaginationContext.getOffset(), pageSize);
	}
}