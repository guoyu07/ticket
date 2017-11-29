package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.AirportEmployee;


/**
 * 员工信息业务接口
 * @ClassName: IAirportEmployeeService   
 * @Description: 提供员工信息操作的增删改查   
 * @author HiSay  
 * @date  2015-11-16 23:00:35
 *
 */
public interface IAirportEmployeeService extends IBaseService<AirportEmployee, String> {
	/**
	 * 保存员工信息实体
	 * @Title: IAirportEmployeeService
	 * @Description:
	 * @param @param department_id  部门id
	 * @param @param name  姓名
	 * @param @param phone  手机号
	 * @param @param employeeId  工号
	 * @param @param password  密码
	 * @param @param IDCard  身份证号
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String department_id,String name,String phone,String employeeId,String loginId,String password,String IDCard,String hall_id, String versionFlag) throws ServiceException;
	
	/**
	 * 修改员工信息实体
	 * @Title: IAirportEmployeeService
	 * @Description:
	 * @param @param department_id  部门id
	 * @param @param name  姓名
	 * @param @param phone  手机号
	 * @param @param employeeId  工号
	 * @param @param password  密码
	 * @param @param IDCard  身份证号
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String department_id,String name,String phone,String employeeId,String loginId,String password,String IDCard,String hall_id, String versionFlag) throws ServiceException;
	
	/**
	 * 移除员工信息实体
	 * @Title: IAirportEmployeeService
	 * @Description: 
	 * @param @param id 员工信息ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 批量彻底删除机场员工信息
	 * @param idsValue
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException;
	
	/**
	 * 根据电话号码和密码查询员工实体
	 * @param phone  电话号码
	 * @param password  密码
	 * @return
	 * @throws ServiceException
	 */
	AirportEmployee queryByPhoneAndPwd(String phone, String password) throws ServiceException;
	
	/**
	 * @Description：通过登录名和密码查询用户
	 * @param phone
	 * @param password
	 * @return
	 * @throws ServiceException
	 */
	AirportEmployee queryByLoginIdAndPwd(String loginId, String password) throws ServiceException;
	
	/**
	 * @Description：通过员工id和密码查询用户
	 * @param phone
	 * @param password
	 * @return
	 * @throws ServiceException
	 */
	AirportEmployee queryByEmployeeIdAndPwd(String EmployeeId, String password) throws ServiceException;
	
	/**
	 * @Description：混合登录（用户名，员工id，电话号码）
	 * @param EmployeeId
	 * @param password
	 * @return
	 * @throws ServiceException
	 */
	AirportEmployee queryByMixAndPwd(String mix, String password) throws ServiceException;

	/**
	 * 根据员工电话 姓名 工号查询员工实体
	 * @param phone  电话号码
	 * @param name   姓名
	 * @param employeeId  员工编号
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	AirportEmployee queryEmpByConditions(String phone, String name,
			String employeeId, String versionFlag) throws ServiceException;
	
	/**
	 * @Description：通过角色名查询员工
	 * @param roleName
	 * @return
	 */
	List<AirportEmployee> queryByRoleName(String roleName);
	
	/**
	 * @Description：通过角色名和部门查询员工
	 * @param roleName
	 * @param department
	 * @return
	 */
	List<AirportEmployee> queryByRoleNameAndDepartment(String roleName, String department);
	
	/**
	 * @Description：通过列名和列值得到一批数据
	 * @date 2016年1月2日 下午3:21:17
	 * @param columnName
	 * @param value
	 * @return
	 */
	public List<AirportEmployee> getByColumn(String columnName, String value);
}