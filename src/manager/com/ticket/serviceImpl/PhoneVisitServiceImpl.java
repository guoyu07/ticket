package com.ticket.serviceImpl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.PhoneVisit;
import com.ticket.service.IDepartmentInfoService;
import com.ticket.service.IPhoneVisitService;
import com.ticket.util.DateUtil;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.PaginationContext;

/**
 * 电话回访记录业务接口实现类
 * @ClassName: IPhoneVisitService   
 * @Description: 提供电话回访记录操作的增删改查   
 * @author HiSay  
 * @date 2015-11-02 23:14:13
 *
 */
public class PhoneVisitServiceImpl extends BaseServiceImpl<PhoneVisit, String> implements IPhoneVisitService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(PhoneVisitServiceImpl.class);
	
	@Resource private IDepartmentInfoService departmentInfoService = null;
	
	@Override
	public boolean merge(String id, String visitDate,String customer_id,String contact,
			String contactPhone,String content) throws ServiceException {
		PhoneVisit phoneVisit = dbDAO.queryById(this.getTableNameFromEntity(PhoneVisit.class), id);
		if(GeneralUtil.isNotNull(visitDate)){
			phoneVisit.setVisitDate(DateUtil.parseStringToDate(visitDate, "yyyy-MM-dd HH:mm:ss"));
		}else{
			phoneVisit.setVisitDate(null);
		}
		phoneVisit.setCustomer_id(DecoderUtil.UtfDecoder(customer_id));
		phoneVisit.setContact(DecoderUtil.UtfDecoder(contact));
		phoneVisit.setContactPhone(DecoderUtil.UtfDecoder(contactPhone));
		phoneVisit.setContent(DecoderUtil.UtfDecoder(content));
		CommonEntity status = phoneVisit.getStatus();
		status.setVersionFlag(versionFlag);
		phoneVisit.setStatus(status);
		dbDAO.merge(phoneVisit);
		return true;
	}

	@Override
	public boolean persist(String visitDate,EmployeeInfo employeeInfo,String customer_id,
			String contact,String contactPhone,String content) throws ServiceException {
		PhoneVisit phoneVisit = new PhoneVisit();
		if(GeneralUtil.isNotNull(visitDate)){
			phoneVisit.setVisitDate(DateUtil.parseStringToDate(visitDate, "yyyy-MM-dd HH:mm:ss"));
		}else{
			phoneVisit.setVisitDate(null);
		}
		if(employeeInfo != null){
			phoneVisit.setEmployee_id(employeeInfo.getId());
		}
		phoneVisit.setCustomer_id(DecoderUtil.UtfDecoder(customer_id));
		phoneVisit.setContact(DecoderUtil.UtfDecoder(contact));
		phoneVisit.setContactPhone(DecoderUtil.UtfDecoder(contactPhone));
		phoneVisit.setContent(DecoderUtil.UtfDecoder(content));
		CommonEntity status = phoneVisit.getStatus();
		status.setVersionFlag(versionFlag);
		phoneVisit.setStatus(status);
		dbDAO.persist(phoneVisit);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		PhoneVisit phoneVisit = dbDAO.queryById(this.getTableNameFromEntity(PhoneVisit.class), id);
		dbDAO.remove(phoneVisit);
		return true;
	}

	@Override
	public boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException {
		idsValue = GeneralUtil.convertIdsValueToStringByDelete(idsValue, ",");
		dbDAO.deleteAll(PhoneVisit.class.getSimpleName(), idsValue, null);
		return true;
	}

	@Override
	public Pagination queryEntityByEmployeeId(String versionFlag, int pageSize,
			String employeeId,int state) throws ServiceException {
		if(state != 2){
			Pagination pagination = new Pagination();
			List<PhoneVisit> list = dbDAO.executeJPQLForQueryEntity("select e from PhoneVisit e,ChannelCustomerInfo c where e.customer.id = c.id and e.employee.id = ? and c.state = ?", employeeId,state);
			if(list != null){
				pagination.setCurrentPage(PaginationContext.getOffset()+1);
				pagination.setTotalCount(list.size());
				pagination.setPageList(list);
			}
			return pagination;
		}else{
			return dbDAO.queryByPageModule(PhoneVisit.class.getSimpleName(), deleteFlag, versionFlag, "and s.employee.id =?3", new Object[]{employeeId}, orderBy, PaginationContext.getOffset(), pageSize);
		}
		
	}

	@Override
	public Pagination queryAll(EmployeeInfo employeeInfo, Integer deleteFlag,
			Integer pageSize,int state) throws ServiceException {
		if(state != 2){
			Pagination pagination = new Pagination();
			List<PhoneVisit> list = dbDAO.executeJPQLForQueryEntity("select e from PhoneVisit e,ChannelCustomerInfo c where e.customer.id = c.id and c.state = ?", state);
			if(list != null){
				pagination.setCurrentPage(PaginationContext.getOffset()+1);
				pagination.setTotalCount(list.size());
				pagination.setPageList(list);
			}
			return pagination;
		}else{
			try {
				StringBuffer sb = new StringBuffer();
				sb.append("");
				List<Object[]> objects = new ArrayList<Object[]>();
				if(employeeInfo != null){
					sb.append("and s.employee.id=?3 ");
					objects.add(new Object[]{3,employeeInfo.getId()});
				}
				return dbDAO.queryByPageModuleNew(PhoneVisit.class.getSimpleName(), deleteFlag, versionFlag, 
						sb.toString(),objects, orderBy, PaginationContext.getOffset(), pageSize);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
	}

	@Override
	public Pagination queryByCustomerId(String customer_id, int pageSize,
			String versionFlag) throws ServiceException {
		return dbDAO.queryByPageModule(PhoneVisit.class.getSimpleName(), deleteFlag, versionFlag, "and s.customer.id =?3 and s.status.audit= ?4", new Object[]{customer_id,ContextConstants.STATUS_OF_ZERO}, orderBy, PaginationContext.getOffset(), pageSize);
	}

	@Override
	public Pagination queryAllByNotAudit(String versionFlag, int pageSize)
			throws ServiceException {
		return dbDAO.queryByPageModule(PhoneVisit.class.getSimpleName(), deleteFlag, versionFlag, "and s.status.audit =?3", new Object[]{ContextConstants.STATUS_OF_ZERO}, orderBy, PaginationContext.getOffset(), pageSize);
	}

	@Override
	public long queryAllCount() throws ServiceException {
		List<Object> list = dbDAO.executeJPQLForQuery("select count(c) from " + PhoneVisit.class.getName() + " c");
		long count = 0;
		if(GeneralUtil.isNotNull(list)){
			count = (Long)list.get(0);
		}
		return count;
	}

	@Override
	public Integer countByCustomerId(String customer_id)
			throws ServiceException {
		return dbDAO.getTotalCount(PhoneVisit.class.getSimpleName(), deleteFlag, versionFlag, "and s.customer.id =?3", new Object[]{customer_id}).intValue();
	}

	@Override
	public List<PhoneVisit> queryListByCustomerId(String customer_id,
			String versionFlag) throws ServiceException {
		return dbDAO.queryByList(PhoneVisit.class.getSimpleName(), deleteFlag, versionFlag, "and s.customer.id =?3", new Object[]{customer_id}, orderBy, null);
	}

	@Override
	public Integer countByCustomerIdAndDate(String customer_id, Date startDate,
			Date endDate) {
		endDate = new Date(endDate.getTime() + 24 * 60 * 60 * 1000 - 1);
		return dbDAO.getTotalCount(PhoneVisit.class.getSimpleName(), deleteFlag, versionFlag, "and s.customer.id =?3 and s.status.createTime >= ?4 and s.status.createTime <= ?5", new Object[]{customer_id,startDate,endDate}).intValue();
	}

	@Override
	public Pagination queryAllWhereInDepartment(EmployeeInfo employeeInfo,int state)
			throws ServiceException {
		Pagination pagination = new Pagination();
		if(state != 2){
			pagination = this.paginationQuery("select p from PhoneVisit p,ChannelCustomerInfo c where p.employee in (select e from EmployeeInfo e where e.department in (select s from DepartmentInfo p , DepartmentInfo s where s.path like concat('%', p.id, '%') and p = ?)) and p.customer.id = c.id and c.state = ?", employeeInfo.getDepartment(),state);
		}else{
			pagination = this.paginationQuery("select p from PhoneVisit p where p.employee in (select e from EmployeeInfo e where e.department in (select s from DepartmentInfo p , DepartmentInfo s where s.path like concat('%', p.id, '%') and p = ?))", employeeInfo.getDepartment());
		}
		return pagination;
	}

}