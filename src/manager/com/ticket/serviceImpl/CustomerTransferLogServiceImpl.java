package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.CustomerTransferLog;
import com.ticket.service.ICustomerTransferLogService;
import com.ticket.util.DecoderUtil;

/**
 * 客户转让日志业务接口实现类
 * @ClassName: ICustomerTransferLogService   
 * @Description: 提供客户转让日志操作的增删改查   
 * @author HiSay  
 * @date 2016-04-27 15:04:28
 *
 */
public class CustomerTransferLogServiceImpl extends BaseServiceImpl<CustomerTransferLog, String> implements ICustomerTransferLogService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(CustomerTransferLogServiceImpl.class);
	
	@Override
	public boolean merge(String id, String beforeEmployee_id,String afterEmployee_id,String operator_id,String customer_id,String remark, String versionFlag) throws ServiceException {
		CustomerTransferLog customerTransferLog = dbDAO.queryById(this.getTableNameFromEntity(CustomerTransferLog.class), id);
		customerTransferLog.setBeforeEmployee_id(DecoderUtil.UtfDecoder(beforeEmployee_id));
		customerTransferLog.setAfterEmployee_id(DecoderUtil.UtfDecoder(afterEmployee_id));
		customerTransferLog.setOperator_id(DecoderUtil.UtfDecoder(operator_id));
		customerTransferLog.setCustomer_id(DecoderUtil.UtfDecoder(customer_id));
		customerTransferLog.setRemark(DecoderUtil.UtfDecoder(remark));
		CommonEntity status = customerTransferLog.getStatus();
		status.setVersionFlag(versionFlag);
		customerTransferLog.setStatus(status);
		dbDAO.merge(customerTransferLog);
		return true;
	}

	@Override
	public boolean persist(String beforeEmployee_id,String afterEmployee_id,String operator_id,String customer_id,String remark, String versionFlag) throws ServiceException {
		CustomerTransferLog customerTransferLog = new CustomerTransferLog();
		customerTransferLog.setBeforeEmployee_id(DecoderUtil.UtfDecoder(beforeEmployee_id));
		customerTransferLog.setAfterEmployee_id(DecoderUtil.UtfDecoder(afterEmployee_id));
		customerTransferLog.setOperator_id(DecoderUtil.UtfDecoder(operator_id));
		customerTransferLog.setCustomer_id(DecoderUtil.UtfDecoder(customer_id));
		customerTransferLog.setRemark(DecoderUtil.UtfDecoder(remark));
		CommonEntity status = customerTransferLog.getStatus();
		status.setVersionFlag(versionFlag);
		customerTransferLog.setStatus(status);
		dbDAO.persist(customerTransferLog);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		CustomerTransferLog customerTransferLog = dbDAO.queryById(this.getTableNameFromEntity(CustomerTransferLog.class), id);
		dbDAO.remove(customerTransferLog);
		return true;
	}

	@Override
	public List<CustomerTransferLog> queryByCustomer(String customer_id,String versionFlag) throws ServiceException {
		return  dbDAO.queryByList(CustomerTransferLog.class.getSimpleName(), deleteFlag, versionFlag, "and s.customer_id =?3", new Object[]{customer_id}, "s.status.createTime asc", null);
	}

}