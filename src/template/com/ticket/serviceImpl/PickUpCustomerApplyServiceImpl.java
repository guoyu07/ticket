package com.ticket.serviceImpl;


import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.ChannelCustomerInfo;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.PickUpCustomerApply;
import com.ticket.service.IChannelCustomerInfoService;
import com.ticket.service.IPickUpCustomerApplyService;

/**
 * 捡单客户申请业务接口实现类
 * @ClassName: IPickUpCustomerApplyService   
 * @Description: 提供捡单客户申请操作的增删改查   
 * @author HiSay  
 * @date 2015-11-24 15:45:34
 *
 */
public class PickUpCustomerApplyServiceImpl extends BaseServiceImpl<PickUpCustomerApply, String> implements IPickUpCustomerApplyService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(PickUpCustomerApplyServiceImpl.class);
	@Resource
	private IChannelCustomerInfoService channelCustomerInfoService = null;
	
	@Override
	public boolean merge(String id, String employee_id,String customer_id,Integer applyState, String oldemployee_id,String versionFlag) throws ServiceException {
		PickUpCustomerApply pickUpCustomerApply = dbDAO.queryById(this.getTableNameFromEntity(PickUpCustomerApply.class), id);
		pickUpCustomerApply.setEmployee(get(EmployeeInfo.class, employee_id));
		pickUpCustomerApply.setCustomer(get(ChannelCustomerInfo.class, customer_id));
		pickUpCustomerApply.setOldemployee(get(EmployeeInfo.class, oldemployee_id));
		pickUpCustomerApply.setApplyState(applyState);
		CommonEntity status = pickUpCustomerApply.getStatus();
		status.setVersionFlag(versionFlag);
		pickUpCustomerApply.setStatus(status);
		dbDAO.merge(pickUpCustomerApply);
		return true;
	}

	@Override
	public boolean persist(String employee_id,String customer_id,Integer applyState,String oldemployee_id, String versionFlag) throws ServiceException {
		PickUpCustomerApply pickUpCustomerApply = new PickUpCustomerApply();
		pickUpCustomerApply.setEmployee(get(EmployeeInfo.class, employee_id));
		pickUpCustomerApply.setCustomer(get(ChannelCustomerInfo.class, customer_id));
		pickUpCustomerApply.setOldemployee(get(EmployeeInfo.class, oldemployee_id));
		pickUpCustomerApply.setApplyState(applyState);
		CommonEntity status = pickUpCustomerApply.getStatus();
		status.setVersionFlag(versionFlag);
		pickUpCustomerApply.setStatus(status);
		dbDAO.persist(pickUpCustomerApply);
		ChannelCustomerInfo channelCustomerInfo = channelCustomerInfoService
					.queryById(ChannelCustomerInfo.class.getSimpleName(), customer_id);
		channelCustomerInfo.setEmployeeInfo(get(EmployeeInfo.class, employee_id));
		channelCustomerInfo.setState(0);
		channelCustomerInfoService.merge(channelCustomerInfo);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		PickUpCustomerApply pickUpCustomerApply = dbDAO.queryById(this.getTableNameFromEntity(PickUpCustomerApply.class), id);
		dbDAO.remove(pickUpCustomerApply);
		return true;
	}

	@Override
	public boolean isExist(String employeeId, String customerId) {
		String tableName = PickUpCustomerApply.class.getSimpleName();
		String sql="select c from "+tableName+" c where customer.id=? and employee.id=?";
		PickUpCustomerApply obj = (PickUpCustomerApply) dbDAO.executeJPQLForQuerySingle(sql, customerId,employeeId);
		if(obj!=null){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean TransferData(String id,String employeeId, String customerId,
			String oldemployee_id) {
		String tableName = ChannelCustomerInfo.class.getSimpleName();
		String sql="update "+tableName+" c  set c.employee.id =?1 where c.id =?2";
		int result = dbDAO.executeSQL(sql,new Object[]{employeeId,customerId});
		if(result>0){
			PickUpCustomerApply pickUpCustomerApply = dbDAO.queryById(this.getTableNameFromEntity(PickUpCustomerApply.class), id);
			pickUpCustomerApply.setApplyState(1);
			dbDAO.merge(pickUpCustomerApply);
			return true;
		}else{
			return false;
		}
	}

}