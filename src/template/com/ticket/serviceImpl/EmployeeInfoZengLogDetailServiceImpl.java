package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjServiceCode;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.EmployeeInfoZengLogDetail;
import com.ticket.service.IEmployeeInfoZengLogDetailService;

/**
 * 员工转移服务码附表业务接口实现类
 * @ClassName: IEmployeeInfoZengLogDetailService   
 * @Description: 提供员工转移服务码附表操作的增删改查   
 * @author HiSay  
 * @date 2016-01-18 17:27:18
 *
 */
public class EmployeeInfoZengLogDetailServiceImpl extends BaseServiceImpl<EmployeeInfoZengLogDetail, String> implements IEmployeeInfoZengLogDetailService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(EmployeeInfoZengLogDetailServiceImpl.class);
	
	@Override
	public boolean merge(String id,  String versionFlag) throws ServiceException {
		EmployeeInfoZengLogDetail employeeInfoZengLogDetail = dbDAO.queryById(this.getTableNameFromEntity(EmployeeInfoZengLogDetail.class), id);
		CommonEntity status = employeeInfoZengLogDetail.getStatus();
		status.setVersionFlag(versionFlag);
		employeeInfoZengLogDetail.setStatus(status);
		dbDAO.merge(employeeInfoZengLogDetail);
		return true;
	}

	@Override
	public boolean persist( String versionFlag) throws ServiceException {
		EmployeeInfoZengLogDetail employeeInfoZengLogDetail = new EmployeeInfoZengLogDetail();
		CommonEntity status = employeeInfoZengLogDetail.getStatus();
		status.setVersionFlag(versionFlag);
		employeeInfoZengLogDetail.setStatus(status);
		dbDAO.persist(employeeInfoZengLogDetail);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		EmployeeInfoZengLogDetail employeeInfoZengLogDetail = dbDAO.queryById(this.getTableNameFromEntity(EmployeeInfoZengLogDetail.class), id);
		dbDAO.remove(employeeInfoZengLogDetail);
		return true;
	}

	@Override
	public List<EmployeeInfoZengLogDetail> query(String mobile) {
		
		//渠道客户那边赠送过来的
		String sql = "select s from " + EmployeeInfoZengLogDetail.class.getName() + " s where s.employeeInfoZengLog.mobile=?";
		List<EmployeeInfoZengLogDetail> list = dbDAO.executeJPQLForQueryEntity(sql, mobile);
		return list;
	}

	@Override
	public boolean canActivate(EmployeeInfoZengLogDetail zengLogDetail) {
		
		if(zengLogDetail == null){
			
			return false;
		}
		
		BjdjServiceCode serviceCode = zengLogDetail.getOrderInfoDetail().getBjdjServiceCode();
		if(serviceCode != null){
			
			//在查看服务码状态是否符合要求
			if(!serviceCode.getState().getName().equals("unused")
					&& !serviceCode.getState().getName().equals("donated")){//unused可以激活
				
				return false;
			}
			return true;
		}
		return false;
	}
	
	@Override
	public boolean canActivate(BjdjServiceCode serviceCode) {
		
		EmployeeInfoZengLogDetail zengLogDetail = dbDAO.executeJPQLForQuerySingle(
				"select zld from " + EmployeeInfoZengLogDetail.class.getSimpleName() + " zld "
				+ " left join zld.orderInfoDetail.bjdjServiceCode sc"
				+ "  where sc=?", 
				EmployeeInfoZengLogDetail.class, serviceCode);
		return canActivate(zengLogDetail);
	}

	@Override
	public boolean canComment(EmployeeInfoZengLogDetail zengLogDetail) {
		
		BjdjServiceCode serviceCode = zengLogDetail.getOrderInfoDetail().getBjdjServiceCode();
		if(serviceCode != null){
			
			if("used".equals(serviceCode.getState().getName())){
				
				return true;
			}
		}
		return false;
	}

}