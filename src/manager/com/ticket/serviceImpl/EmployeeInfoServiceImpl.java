package com.ticket.serviceImpl;


import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.ChannelCustomerInfo;
import com.ticket.pojo.DepartmentInfo;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.Position;
import com.ticket.service.IDepartmentInfoService;
import com.ticket.service.IEmployeeInfoService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.MD5Util;
import com.ticket.util.PaginationContext;

/**
 * 员工信息业务接口实现类
 * @ClassName: IEmployeeInfoService   
 * @Description: 提供员工信息操作的增删改查   
 * @author HiSay  
 * @date 2015-11-03 15:33:02
 *
 */
public class EmployeeInfoServiceImpl extends BaseServiceImpl<EmployeeInfo, String> implements IEmployeeInfoService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(EmployeeInfoServiceImpl.class);
	@Resource
	private IDepartmentInfoService departmentInfoService = null;
	
	@Override
	public boolean merge(String id, String position,String department_id,String name,String entryDate,
			String IDCard,String phone,String emergencyContact,String emergencyPhone, Integer state
			,String position_id,String loginId,String password) throws ServiceException {
		EmployeeInfo employeeInfo = dbDAO.queryById(this.getTableNameFromEntity(EmployeeInfo.class), id);
		employeeInfo.setDepartment(get(DepartmentInfo.class, department_id));
		employeeInfo.setName(DecoderUtil.UtfDecoder(name));
		employeeInfo.setEntryDate(DecoderUtil.UtfDecoder(entryDate));
		employeeInfo.setIDCard(DecoderUtil.UtfDecoder(IDCard));
		employeeInfo.setPhone(DecoderUtil.UtfDecoder(phone));
		employeeInfo.setEmergencyContact(DecoderUtil.UtfDecoder(emergencyContact));
		employeeInfo.setEmergencyPhone(DecoderUtil.UtfDecoder(emergencyPhone));
		employeeInfo.setState(state);
		employeeInfo.setPosition(get(Position.class, position_id));
		employeeInfo.setLoginId(loginId);
		if(!employeeInfo.getPassword().equals(password)){
			employeeInfo.setPassword(MD5Util.Azdg.strMD5(password));
		}
		dbDAO.merge(employeeInfo);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		EmployeeInfo employeeInfo = dbDAO.queryById(this.getTableNameFromEntity(EmployeeInfo.class), id);
		dbDAO.remove(employeeInfo);
		return true;
	}

	@Override
	public boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException {
		idsValue = GeneralUtil.convertIdsValueToStringByDelete(idsValue, ",");
		dbDAO.deleteAll(EmployeeInfo.class.getSimpleName(), idsValue, null);
		return true;
	}

	@Override
	public List<EmployeeInfo> queryByList(String versionFlag)
			throws ServiceException {
		return dbDAO.queryByList(EmployeeInfo.class.getSimpleName(), deleteFlag, versionFlag, null, null, orderBy, null);
	}

	@Override
	public List<EmployeeInfo> queryByDepartmentId(String department_id, String versionFlag) throws ServiceException {
		return dbDAO.queryByList(EmployeeInfo.class.getSimpleName(), deleteFlag, versionFlag, "and s.department.id =?3", new Object[]{department_id}, orderBy, null);
	}

	@Override
	public Pagination queryPageModuleByKeyword(String keyword, int pageSize,
			String versionFlag) throws ServiceException {
		keyword = DecoderUtil.UtfDecoder(keyword);
		return dbDAO.queryByPageModule(EmployeeInfo.class.getSimpleName(), deleteFlag, versionFlag, "and (s.customerName like ?3 or s.phone like ?4)", new Object[]{"%"+keyword+"%","%"+keyword+"%"}, orderBy, PaginationContext.getOffset(), pageSize);
	}

	@Override
	public String add(String position, String department_id, String name,
			String entryDate, String IDCard, String phone,
			String emergencyContact, String emergencyPhone,String loginId,
			String password, Integer state,String position_id)
			throws ServiceException {
		
		EmployeeInfo employeeInfo = new EmployeeInfo();
		employeeInfo.setDepartment(get(DepartmentInfo.class, department_id));
		employeeInfo.setPosition(get(Position.class, position_id));
		employeeInfo.setName(DecoderUtil.UtfDecoder(name));
		employeeInfo.setEntryDate(DecoderUtil.UtfDecoder(entryDate));
		employeeInfo.setIDCard(DecoderUtil.UtfDecoder(IDCard));
		employeeInfo.setPhone(DecoderUtil.UtfDecoder(phone));
		employeeInfo.setEmergencyContact(DecoderUtil.UtfDecoder(emergencyContact));
		employeeInfo.setEmergencyPhone(DecoderUtil.UtfDecoder(emergencyPhone));
		employeeInfo.setLoginId(DecoderUtil.UtfDecoder(loginId));
		employeeInfo.setPassword(MD5Util.Azdg.strMD5(password));
		employeeInfo.setState(state);
		dbDAO.persist(employeeInfo);
		
		if(employeeInfo.getId()!=null){
			return employeeInfo.getId();
		}else{
			return null;
		}
	}

	@Override
	public EmployeeInfo queryByLogin(String loginId, String password)
			throws ServiceException {
		try {
			return dbDAO
				.executeJPQLForQuerySingle("select e from EmployeeInfo e where e.loginId=? and e.password=? ",
						EmployeeInfo.class,loginId,MD5Util.Azdg.strMD5(password));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean bindChannelCustomerInfo(String id,
			String channelCustomerInfoId) throws ServiceException {
		try {
			EmployeeInfo employeeInfo = queryById(EmployeeInfo.class.getSimpleName(), id);
			if(employeeInfo != null && GeneralUtil.isNotNull(channelCustomerInfoId)){
				employeeInfo.setChannelCustomerInfo(get(ChannelCustomerInfo.class, channelCustomerInfoId));
				this.merge(employeeInfo);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Pagination queryWhereInDepartment(EmployeeInfo employeeInfo)
			throws ServiceException {
		Pagination pagination = new Pagination();
		pagination = this.paginationQuery("select c from EmployeeInfo c where c.department in (select s from DepartmentInfo p , DepartmentInfo s where s.path like concat('%', p.id, '%') and p.id = ? ))", employeeInfo.getDepartment());
		return pagination;
	}

}