package com.ticket.serviceImpl;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.LetterStation;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.SystemUser;
import com.ticket.service.ILetterStationService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.PaginationContext;

/**
 * 站内信业务接口实现类
 * @ClassName: ILetterStationService   
 * @Description: 提供站内信操作的增删改查   
 * @author HiSay  
 * @date 2016-01-03 13:52:54
 *
 */
public class LetterStationServiceImpl extends BaseServiceImpl<LetterStation, String> implements ILetterStationService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(LetterStationServiceImpl.class);
	

	@Override
	public boolean remove(String id) throws ServiceException {
		LetterStation letterStation = dbDAO.queryById(this.getTableNameFromEntity(LetterStation.class), id);
		dbDAO.remove(letterStation);
		return true;
	}

	@Override
	public boolean persist(String operator_id, String title,String content,String url)
			throws ServiceException {
		try {
			LetterStation letterStation = new LetterStation();
			letterStation.setOperator_id(operator_id);
			letterStation.setTitle(title);
			letterStation.setContent(content);
			letterStation.setUrl(url);
			this.persist(letterStation);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public Pagination queryAll(SystemUser systemUser, Integer read,
			Integer pageSize) throws ServiceException {
		try {
			StringBuffer sb = new StringBuffer();
			List<Object[]> objects = new ArrayList<Object[]>();
			sb.append("");
			if(systemUser != null){
				sb.append("and s.systemUser_id=?3 ");
				objects.add(new Object[]{3,systemUser.getId()});
			}
			if(GeneralUtil.isNotNull(read)){
				sb.append("and s.isRead=?4 ");
				objects.add(new Object[]{4,read});
			}
			return dbDAO.queryByPageModuleNew(LetterStation.class.getSimpleName(), deleteFlag, versionFlag,
					sb.toString(), objects, "s.isRead asc,s.id desc", PaginationContext.getOffset(), pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public List<LetterStation> queryAllList(String systemUserId, Integer read,
			Integer size) throws ServiceException {
		try {
			StringBuffer sb = new StringBuffer();
			List<Object[]> objects = new ArrayList<Object[]>();
			sb.append("");
			if(GeneralUtil.isNotNull(systemUserId)){
				sb.append("and s.systemUser_id=?3 ");
				objects.add(new Object[]{3,systemUserId});
			}
			if(GeneralUtil.isNotNull(read)){
				sb.append("and s.isRead=?4 ");
				objects.add(new Object[]{4,read});
			}
			return dbDAO.queryByListNew(SystemUser.class.getSimpleName(),deleteFlag, versionFlag,
					sb.toString(), objects, "s.isRead asc,s.id desc", size);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public Long queryCount(String systemUserId, Integer read)
			throws ServiceException {
		try {
			StringBuffer sb = new StringBuffer();
			List<Object[]> objects = new ArrayList<Object[]>();
			sb.append("select count(*) from LetterStation s where 1=1 ");
			if(GeneralUtil.isNotNull(systemUserId)){
				sb.append("and s.systemUser_id=?3 ");
				objects.add(new Object[]{3,systemUserId});
			}
			if(GeneralUtil.isNotNull(read)){
				sb.append("and s.isRead=?4 ");
				objects.add(new Object[]{4,read});
			}
			return (Long)dbDAO.executeJPQLForQuerySingle(sb.toString(), objects.toArray());
		} catch (Exception e) {
			e.printStackTrace();
			return 0L;
		}
	}


	@Override
	public Pagination queryByEmployeeOrCustomer(String id, String versionFlag,
			int pageSize) throws ServiceException {
		return dbDAO.queryByPageModule(LetterStation.class.getSimpleName(), deleteFlag, versionFlag, "and s.employee_id =?3", new Object[]{id}, orderBy, PaginationContext.getOffset(), pageSize);
	}


	@Override
	public boolean merge(String id, String title,String content,
			String url) throws ServiceException {
		LetterStation letterStation = dbDAO.queryById(LetterStation.class.getSimpleName(), id);
		letterStation.setTitle(title);
		letterStation.setContent(content);
		letterStation.setUrl(url);
		
		dbDAO.merge(letterStation);
		return true;
	}


}