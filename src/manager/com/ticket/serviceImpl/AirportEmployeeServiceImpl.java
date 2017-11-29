package com.ticket.serviceImpl;


import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.AirportDepartment;
import com.ticket.pojo.AirportEmployee;
import com.ticket.pojo.BjdjHall;
import com.ticket.pojo.RoleInfo;
import com.ticket.pojo.UserRole;
import com.ticket.service.IAirportDepartmentService;
import com.ticket.service.IAirportEmployeeService;
import com.ticket.service.IBjdjHallService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.MD5Util;
import com.ticket.util.ResourceUtil;

/**
 * 员工信息业务接口实现类
 * @ClassName: IAirportEmployeeService   
 * @Description: 提供员工信息操作的增删改查   
 * @author HiSay  
 * @date 2015-11-16 23:00:35
 *
 */
public class AirportEmployeeServiceImpl extends BaseServiceImpl<AirportEmployee, String> implements IAirportEmployeeService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(AirportEmployeeServiceImpl.class);
	@Resource
	protected IAirportDepartmentService<AirportDepartment, String> departmentService;
	@Resource private IBjdjHallService hallService;
	@Override
	public boolean merge(String id, String department_id,String name,String phone,String employeeId,String loginId,String password,String IDCard, String hall_id,String versionFlag) throws ServiceException {
		
		AirportEmployee airportEmployee = dbDAO.queryById(this.getTableNameFromEntity(AirportEmployee.class), id);
		//检测手机号，是否重复
		if(!airportEmployee.getPhone().equals(phone) && getByColumn("phone", phone).size() > 0){
				
			throw new ServiceException(ResourceUtil.getText("user.phone.exist"));
		}
		//检测登录名，是否重复
		if(!airportEmployee.getPhone().equals(phone) && getByColumn("loginId", loginId).size() > 0){
			
			throw new ServiceException(ResourceUtil.getText("user.exist"));
		}
		
//		AirportDepartment department = departmentService.get(AirportDepartment.class, department_id);
		airportEmployee.setDepartment_id(department_id);
		airportEmployee.setName(DecoderUtil.UtfDecoder(name));
		airportEmployee.setPhone(DecoderUtil.UtfDecoder(phone));
		airportEmployee.setEmployeeId(DecoderUtil.UtfDecoder(employeeId));
		airportEmployee.setLoginId(loginId);
		if(GeneralUtil.isNotNull(hall_id)){
			
			BjdjHall hall = hallService.queryById(BjdjHall.class.getName(), hall_id);
			airportEmployee.setHall(hall);
		}
		
		if(!airportEmployee.getPassword().equals(password)){
			
			airportEmployee.setPassword(MD5Util.Azdg.strMD5(password));
		}
		airportEmployee.setIDCard(DecoderUtil.UtfDecoder(IDCard));
		dbDAO.merge(airportEmployee);
		return true;
	}

	@Override
	public boolean persist(String department_id,String name,String phone,String employeeId,String loginId,String password,String IDCard,String hall_id, String versionFlag) throws ServiceException {
		
		//检测手机号，是否重复
		if(getByColumn("phone", phone).size() > 0){
				
			throw new ServiceException(ResourceUtil.getText("user.phone.exist"));
		}
		//检测登录名，是否重复
		if(getByColumn("loginId", loginId).size() > 0){
			
			throw new ServiceException(ResourceUtil.getText("user.exist"));
		}

		AirportEmployee airportEmployee = new AirportEmployee();
		airportEmployee.setName(DecoderUtil.UtfDecoder(name));
		airportEmployee.setPhone(DecoderUtil.UtfDecoder(phone));
		airportEmployee.setEmployeeId(DecoderUtil.UtfDecoder(employeeId));
		airportEmployee.setLoginId(loginId);
		airportEmployee.setPassword(MD5Util.Azdg.strMD5(password));
		airportEmployee.setIDCard(DecoderUtil.UtfDecoder(IDCard));
		
		if(GeneralUtil.isNotNull(hall_id)){
			
			BjdjHall hall = hallService.queryById(BjdjHall.class.getName(), hall_id);
			airportEmployee.setHall(hall);
		}
		dbDAO.persist(airportEmployee);
//		AirportDepartment department = departmentService.get(AirportDepartment.class, department_id);
		airportEmployee.setDepartment_id(department_id);
		merge(airportEmployee);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		AirportEmployee airportEmployee = dbDAO.queryById(this.getTableNameFromEntity(AirportEmployee.class), id);
		dbDAO.remove(airportEmployee);
		return true;
	}

	@Override
	public boolean batchRealDelete(String idsValue, String versionFlag)
			throws ServiceException {
		idsValue = GeneralUtil.convertIdsValueToStringByDelete(idsValue, ",");
		dbDAO.deleteAll(AirportEmployee.class.getSimpleName(), idsValue, null);
		return true;
	}

	@Override
	public AirportEmployee queryByPhoneAndPwd(String phone, String password)
			throws ServiceException {
		AirportEmployee emp = dbDAO.queryByCustom(AirportEmployee.class.getSimpleName(), deleteFlag, versionFlag, "and s.phone =?3 and s.password =?4", new Object[]{phone,MD5Util.Azdg.strMD5(password)});
		if(emp != null){
			return emp;
		}
		return null;
	}

	@Override
	public AirportEmployee queryEmpByConditions(String phone, String name, String employeeId, String versionFlag) throws ServiceException {
		AirportEmployee emp = dbDAO.queryByCustom(AirportEmployee.class.getSimpleName(), deleteFlag, versionFlag, "and s.name =?3 and s.phone =?4 and s.employeeId =?5", new Object[]{name,phone,employeeId});
		if(emp != null){
			return emp;
		}
		return null;
	}

	@Override
	public List<AirportEmployee> queryByRoleName(String roleName) {
		
		List<AirportEmployee> employees = dbDAO.executeJPQLForQueryEntity(
				"select e from " + AirportEmployee.class.getName() + " e,"
						+ UserRole.class.getName() + " ur,"
						+ RoleInfo.class.getName() + " r"
						+ " where r.name=? and r.id=ur.roleId and ur.userId=e.id", 
				roleName);
		return employees;
	}

	@Override
	public AirportEmployee queryByLoginIdAndPwd(String loginId, String password)
			throws ServiceException {

		AirportEmployee emp = dbDAO.queryByCustom(AirportEmployee.class.getSimpleName(), deleteFlag, versionFlag, "and s.loginId =?3 and s.password =?4", new Object[]{loginId,MD5Util.Azdg.strMD5(password)});
		return emp;
	}

	@Override
	public AirportEmployee queryByEmployeeIdAndPwd(String EmployeeId,
			String password) throws ServiceException {

		AirportEmployee emp = dbDAO.queryByCustom(AirportEmployee.class.getSimpleName(), deleteFlag, versionFlag, "and s.employeeId =?3 and s.password =?4", new Object[]{EmployeeId,MD5Util.Azdg.strMD5(password)});
		return emp;
	}

	@Override
	public AirportEmployee queryByMixAndPwd(String mix, String password)
			throws ServiceException {
		
		AirportEmployee emp = queryByLoginIdAndPwd(mix, password);
		if(emp == null){
			
			emp = queryByEmployeeIdAndPwd(mix, password);
		}
		if(emp == null){
			
			emp = queryByPhoneAndPwd(mix, password);
		}
		return emp;
	}

	@Override
	public List<AirportEmployee> queryByRoleNameAndDepartment(String roleName, String department_id) {
		
		List<AirportEmployee> employees = dbDAO.executeJPQLForQueryEntity(
				"select e from " + AirportEmployee.class.getName() + " e,"
						+ UserRole.class.getName() + " ur,"
						+ RoleInfo.class.getName() + " r"
						+ " where r.name=? and e.department_id=? and r.id=ur.roleId and ur.userId=e.id", 
				roleName, department_id);
		return employees;
	}

	@Override
	public List<AirportEmployee> getByColumn(String columnName, String value) {
		
		String tableName = AirportEmployee.class.getName();
		List<AirportEmployee> list = dbDAO.executeJPQLForQueryEntity(
				"select s from " + tableName + " s where " + columnName + "=?", value);
		return list;
	}
}