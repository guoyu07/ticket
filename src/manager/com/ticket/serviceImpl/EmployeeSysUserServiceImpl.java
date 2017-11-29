package com.ticket.serviceImpl;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.EmployeeSysUser;
import com.ticket.service.IEmployeeSysUserService;
import com.ticket.util.DecoderUtil;

/**
 * 员工系统用户关系业务接口实现类
 * @ClassName: IEmployeeSysUserService   
 * @Description: 提供员工系统用户关系操作的增删改查   
 * @author HiSay  
 * @date 2015-11-18 16:09:00
 *
 */
public class EmployeeSysUserServiceImpl extends BaseServiceImpl<EmployeeSysUser, String> implements IEmployeeSysUserService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(EmployeeSysUserServiceImpl.class);
	
	@Override
	public boolean merge(String id, String employee_id,String sysUser_id, String versionFlag) throws ServiceException {
		EmployeeSysUser employeeSysUser = dbDAO.queryById(this.getTableNameFromEntity(EmployeeSysUser.class), id);
		employeeSysUser.setEmployee_id(DecoderUtil.UtfDecoder(employee_id));
		employeeSysUser.setSysUser_id(DecoderUtil.UtfDecoder(sysUser_id));
		CommonEntity status = employeeSysUser.getStatus();
		status.setVersionFlag(versionFlag);
		employeeSysUser.setStatus(status);
		dbDAO.merge(employeeSysUser);
		return true;
	}

	@Override
	public boolean persist(String employee_id,String sysUser_id, String versionFlag) throws ServiceException {
		EmployeeSysUser employeeSysUser = new EmployeeSysUser();
		employeeSysUser.setEmployee_id(DecoderUtil.UtfDecoder(employee_id));
		employeeSysUser.setSysUser_id(DecoderUtil.UtfDecoder(sysUser_id));
		CommonEntity status = employeeSysUser.getStatus();
		status.setVersionFlag(versionFlag);
		employeeSysUser.setStatus(status);
		dbDAO.persist(employeeSysUser);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		EmployeeSysUser employeeSysUser = dbDAO.queryById(this.getTableNameFromEntity(EmployeeSysUser.class), id);
		dbDAO.remove(employeeSysUser);
		return true;
	}

}