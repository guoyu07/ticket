package com.ticket.serviceImpl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.ChannelCustomerInfo;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.EmployeeOutVisit;
import com.ticket.pojo.Pagination;
import com.ticket.service.IDepartmentInfoService;
import com.ticket.service.IEmployeeOutVisitService;
import com.ticket.util.DateUtil;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.PaginationContext;

/**
 * 员工外出拜访记录业务接口实现类
 * @ClassName: IEmployeeOutVisitService   
 * @Description: 提供员工外出拜访记录操作的增删改查   
 * @author HiSay  
 * @date 2015-11-02 22:49:40
 *
 */
public class EmployeeOutVisitServiceImpl extends BaseServiceImpl<EmployeeOutVisit, String> implements IEmployeeOutVisitService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(EmployeeOutVisitServiceImpl.class);
	@Resource
	private IDepartmentInfoService departmentInfoService = null;
	
	@Override
	public boolean merge(String id, Date visitDate,String customer_id,String contact,
			String contactPhone,String visitPurpose,
			String accompanyPerPerson,String setoutTime,String backTime,String visitResult) throws ServiceException {
		EmployeeOutVisit employeeOutVisit = dbDAO.queryById(this.getTableNameFromEntity(EmployeeOutVisit.class), id);
		employeeOutVisit.setVisitDate(visitDate);
		employeeOutVisit.setCustomer(get(ChannelCustomerInfo.class, customer_id));
		employeeOutVisit.setContact(DecoderUtil.UtfDecoder(contact));
		employeeOutVisit.setContactPhone(DecoderUtil.UtfDecoder(contactPhone));
		employeeOutVisit.setVisitPurpose(DecoderUtil.UtfDecoder(visitPurpose));
		employeeOutVisit.setAccompanyPerPerson(DecoderUtil.UtfDecoder(accompanyPerPerson));
		if(GeneralUtil.isNotNull(setoutTime)){
			employeeOutVisit.setSetoutTime(DateUtil.parseStringToDate(setoutTime, "yyyy-MM-dd HH:mm:ss"));
		}else{
			employeeOutVisit.setSetoutTime(null);
		}
		if(GeneralUtil.isNotNull(backTime)){
			employeeOutVisit.setBackTime(DateUtil.parseStringToDate(backTime, "yyyy-MM-dd HH:mm:ss"));
		}else{
			employeeOutVisit.setBackTime(null);
		}
		employeeOutVisit.setVisitResult(DecoderUtil.UtfDecoder(visitResult));
		CommonEntity status = employeeOutVisit.getStatus();
		status.setVersionFlag(versionFlag);
		employeeOutVisit.setStatus(status);
		dbDAO.merge(employeeOutVisit);
		return true;
	}

	@Override
	public boolean persist(Date visitDate,String customer_id,String contact,
			String contactPhone,String visitPurpose,EmployeeInfo employeeInfo,
			String accompanyPerPerson,String setoutTime,String backTime,String visitResult) throws ServiceException {
		EmployeeOutVisit employeeOutVisit = new EmployeeOutVisit();
		employeeOutVisit.setVisitDate(visitDate);
		employeeOutVisit.setCustomer(get(ChannelCustomerInfo.class, customer_id));
		employeeOutVisit.setContact(DecoderUtil.UtfDecoder(contact));
		employeeOutVisit.setContactPhone(DecoderUtil.UtfDecoder(contactPhone));
		employeeOutVisit.setVisitPurpose(DecoderUtil.UtfDecoder(visitPurpose));
		if(employeeInfo != null){
			employeeOutVisit.setEmployee(employeeInfo);
		}else{
			employeeOutVisit.setEmployee(null);
		}
		employeeOutVisit.setAccompanyPerPerson(DecoderUtil.UtfDecoder(accompanyPerPerson));
		if(GeneralUtil.isNotNull(setoutTime)){
			employeeOutVisit.setSetoutTime(DateUtil.parseStringToDate(setoutTime, "yyyy-MM-dd HH:mm:ss"));
		}else{
			employeeOutVisit.setSetoutTime(null);
		}
		if(GeneralUtil.isNotNull(backTime)){
			employeeOutVisit.setBackTime(DateUtil.parseStringToDate(backTime, "yyyy-MM-dd HH:mm:ss"));
		}else{
			employeeOutVisit.setBackTime(null);
		}
		employeeOutVisit.setVisitResult(DecoderUtil.UtfDecoder(visitResult));
		CommonEntity status = employeeOutVisit.getStatus();
		status.setVersionFlag(versionFlag);
		employeeOutVisit.setStatus(status);
		dbDAO.persist(employeeOutVisit);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		EmployeeOutVisit employeeOutVisit = dbDAO.queryById(this.getTableNameFromEntity(EmployeeOutVisit.class), id);
		dbDAO.remove(employeeOutVisit);
		return true;
	}

	@Override
	public boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException {
		idsValue = GeneralUtil.convertIdsValueToStringByDelete(idsValue, ",");
		dbDAO.deleteAll(EmployeeOutVisit.class.getSimpleName(), idsValue, null);
		return true;
	}

	@Override
	public Pagination queryEntityByEmployeeId(String versionFlag, int pageSize,
			String employeeId,int state) throws ServiceException {
		if(state != 2){
			Pagination pagination = new Pagination();
			List<EmployeeOutVisit> list = dbDAO.executeJPQLForQueryEntity("select e from EmployeeOutVisit e,ChannelCustomerInfo c where e.customer.id = c.id and e.employee.id = ? and c.state = ?", employeeId,state);
			if(list != null){
				pagination.setCurrentPage(PaginationContext.getOffset()+1);
				pagination.setTotalCount(list.size());
				pagination.setPageList(list);
			}
			
			return pagination;
		}else{
			return dbDAO.queryByPageModule(EmployeeOutVisit.class.getSimpleName(), deleteFlag, versionFlag, "and s.employee.id = ?3", new Object[]{employeeId}, orderBy, PaginationContext.getOffset(), pageSize);
		}
	}

	@Override
	public Pagination queryAll(EmployeeInfo employeeInfo, Integer deleteFlag,
			Integer pageSize,int state) throws ServiceException {
		if(state != 2){
			Pagination pagination = new Pagination();
			List<EmployeeOutVisit> list = dbDAO.executeJPQLForQueryEntity("select e from EmployeeOutVisit e,ChannelCustomerInfo c where e.customer.id = c.id and c.state = ?", state);
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
				return dbDAO.queryByPageModuleNew(EmployeeOutVisit.class.getSimpleName(), deleteFlag, versionFlag, 
						sb.toString(), objects, orderBy, PaginationContext.getOffset(), pageSize);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
	}

	@Override
	public boolean merge(String id, String backTime, String visitResult,Integer state)
			throws ServiceException {
		try {
			EmployeeOutVisit employeeOutVisit = queryById(EmployeeOutVisit.class.getSimpleName(), id);
			if(GeneralUtil.isNotNull(backTime)){
				employeeOutVisit.setBackTime(DateUtil.parseStringToDate(backTime,"yyyy-MM-dd HH:mm:ss"));
			}else{
				employeeOutVisit.setBackTime(null);
			}
			employeeOutVisit.setVisitResult(visitResult);
//			employeeOutVisit.setState(state);
			this.merge(employeeOutVisit);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Pagination queryByCustomerId(String customer_id, int pageSize, String versionFlag) throws ServiceException {
		return dbDAO.queryByPageModule(EmployeeOutVisit.class.getSimpleName(), deleteFlag, versionFlag, " and s.customer.id =?3 and s.status.audit =?4", new Object[]{customer_id,ContextConstants.STATUS_OF_ZERO}, orderBy, PaginationContext.getOffset(), pageSize);
	}

	@Override
	public Pagination queryAllByNotAudit(String versionFlag, int pageSize)
			throws ServiceException {
		return dbDAO.queryByPageModule(EmployeeOutVisit.class.getSimpleName(), deleteFlag, versionFlag, "and s.status.audit =?3", new Object[]{ContextConstants.STATUS_OF_ZERO}, orderBy, PaginationContext.getOffset(), pageSize);
	}

	@Override
	public long queryAllCount() throws ServiceException {
		List<Object> list = dbDAO.executeJPQLForQuery("select count(c) from " + EmployeeOutVisit.class.getName() + " c");
		long count = 0;
		if(GeneralUtil.isNotNull(list)){
			count = (Long)list.get(0);
		}
		return count;
	}

	@Override
	public Integer countByCustomerId(String customer_id)
			throws ServiceException {
		return dbDAO.getTotalCount(EmployeeOutVisit.class.getSimpleName(), deleteFlag, versionFlag, " and s.customer.id = ?3", new Object[]{customer_id}).intValue();
	}

	@Override
	public List<EmployeeOutVisit> queryListByCustomerId(String customer_id,
			String versionFlag) {
		return dbDAO.queryByList(EmployeeOutVisit.class.getSimpleName(), deleteFlag, versionFlag, " and s.customer.id =?3", new Object[]{customer_id}, orderBy, null);
	}

	@Override
	public Integer countByCustomerIdAndDate(String customer_id, Date startDate,
			Date endDate) {
		endDate = new Date(endDate.getTime() + 24 * 60 * 60 * 1000 - 1);
		return dbDAO.getTotalCount(EmployeeOutVisit.class.getSimpleName(), deleteFlag, versionFlag, " and s.customer.id = ?3 and s.status.createTime >= ?4 and s.status.createTime <= ?5", new Object[]{customer_id,startDate,endDate}).intValue();
	}

	@Override
	public Pagination queryAllWhereInDepartment(EmployeeInfo employeeInfo,int state)
			throws ServiceException {
		Pagination pagination = new Pagination();
		
		if(state != 2){
			pagination = this.paginationQuery("select e from EmployeeOutVisit e,ChannelCustomerInfo cc where e.employee in (select c from EmployeeInfo c where c.department in (select s from DepartmentInfo p , DepartmentInfo s where s.path like concat('%', p.id, '%') and p = ?)) and e.customer = cc and cc.state = ?", employeeInfo.getDepartment(),state);
		}else{
			pagination = this.paginationQuery("select e from EmployeeOutVisit e where e.employee in (select c from EmployeeInfo c where c.department in (select s from DepartmentInfo p , DepartmentInfo s where s.path like concat('%', p.id, '%') and p = ?))", employeeInfo.getDepartment());
		}
		return pagination;
	}

}