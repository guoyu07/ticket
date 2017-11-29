package com.ticket.serviceImpl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.ApplayClass;
import com.ticket.pojo.ChannelCustomerInfo;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.Receipt;
import com.ticket.pojo.SystemUser;
import com.ticket.service.IChannelCustomerInfoService;
import com.ticket.service.IDepartmentInfoService;
import com.ticket.service.IReceiptService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.PaginationContext;


/**
 * 收据发票业务接口实现类
 * @ClassName: IReceiptService   
 * @Description: 提供收据发票操作的增删改查   
 * @author HiSay  
 * @date 2015-11-17 17:10:15
 *
 */
public class ReceiptServiceImpl extends BaseServiceImpl<Receipt, String> implements IReceiptService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(ReceiptServiceImpl.class);
	@Resource
	private IChannelCustomerInfoService channelCustomerInfoService = null;
	@Resource 
	private IDepartmentInfoService departmentInfoService = null;
	
	@Override
	public boolean merge(String id, String receiptNo,String name,Date issueTime,
			String channelCustomerInfoId,Double amountOfMoney,String employeeInfoId,
			Integer type,String remarks, String applyClass_id,String money) throws ServiceException {
		Receipt receipt = dbDAO.queryById(this.getTableNameFromEntity(Receipt.class), id);
		if(GeneralUtil.isNotNull(channelCustomerInfoId)){
			ChannelCustomerInfo channelCustomerInfo = channelCustomerInfoService
							.queryById(ChannelCustomerInfo.class.getSimpleName(), channelCustomerInfoId);
			if(channelCustomerInfo != null){
				receipt.setName(channelCustomerInfo.getName());
				receipt.setChannelCustomerInfoId(channelCustomerInfo.getId());
			}
		}
		receipt.setIssueTime(issueTime);
		receipt.setAmountOfMoney(amountOfMoney);
		receipt.setEmployeeInfo(get(EmployeeInfo.class, employeeInfoId));
		receipt.setType(type);
		receipt.setMoney(money);
		receipt.setRemarks(DecoderUtil.UtfDecoder(remarks));
		receipt.setApplyClass(get(ApplayClass.class, applyClass_id));
		dbDAO.merge(receipt);
		return true;
	}

	@Override
	public boolean persist(String receiptNo,String name,Date issueTime,String channelCustomerInfoId,
			Double amountOfMoney,String employeeInfoId,Integer type,String remarks, 
			String applyClass_id,String money) throws ServiceException {
		Receipt receipt = new Receipt();
		
		receipt.setIssueTime(issueTime);
		if(GeneralUtil.isNotNull(channelCustomerInfoId)){
			ChannelCustomerInfo channelCustomerInfo = channelCustomerInfoService
							.queryById(ChannelCustomerInfo.class.getSimpleName(), channelCustomerInfoId);
			if(channelCustomerInfo != null){
				receipt.setName(channelCustomerInfo.getName());
				receipt.setChannelCustomerInfoId(channelCustomerInfo.getId());
			}
		}
		receipt.setAmountOfMoney(amountOfMoney);
		receipt.setEmployeeInfo(get(EmployeeInfo.class, employeeInfoId));
		receipt.setType(type);
		receipt.setMoney(money);
		receipt.setRemarks(DecoderUtil.UtfDecoder(remarks));
		receipt.setApplyClass(get(ApplayClass.class, applyClass_id));
		dbDAO.persist(receipt);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		Receipt receipt = dbDAO.queryById(this.getTableNameFromEntity(Receipt.class), id);
		dbDAO.remove(receipt);
		return true;
	}

	@Override
	public boolean merge(String id,Integer audit, String auditRemarks, String receiptNo)
			throws ServiceException {
		Receipt receipt = dbDAO.queryById(this.getTableNameFromEntity(Receipt.class), id);
		receipt.setAuditRemarks(auditRemarks);
		CommonEntity status = receipt.getStatus();
		//status.setAudit(audit);
		status.setVersionFlag(versionFlag);
		receipt.setStatus(status);
		receipt.setReceiptNo(receiptNo);
		dbDAO.merge(receipt);
		return true;
	}

	@Override
	public Pagination queryAll(EmployeeInfo employeeInfo,String applyClass_id,String  states,Integer deleteFlag,Integer pageSize)
			throws ServiceException {
		try {
			StringBuffer sb = new StringBuffer();
			List<Object[]> objects = new ArrayList<Object[]>();
			if(employeeInfo != null){
				sb.append("and s.employeeInfo.id=?3 ");
				objects.add(new Object[]{3,employeeInfo.getId()});
			}
			if(GeneralUtil.isNotNull(states)){
				sb.append("and s.state in (");
				sb.append(states);
				sb.append(") ");
			}
			if(GeneralUtil.isNotNull(applyClass_id)){
				sb.append("and s.applyClass_id=?4 ");
				objects.add(new Object[]{4,applyClass_id});
			}
			return dbDAO.queryByPageModuleNew(Receipt.class.getSimpleName(), deleteFlag, versionFlag, 
					sb.toString(), objects, orderBy, PaginationContext.getOffset(), pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean changeState(String id, Integer state)
			throws ServiceException {
		try {
			Receipt receipt = queryById(Receipt.class.getSimpleName(), id);
			receipt.setState(state);
			this.merge(receipt);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean updateAuditPerson(String id, SystemUser systemUser)
			throws ServiceException {
		try {
			Receipt receipt = queryById(Receipt.class.getSimpleName(), id);
			receipt.setAuditTime(new Date());
			if(systemUser != null){
				receipt.setSystemUser(systemUser);
			}
			this.merge(receipt);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Pagination queryAllWhereInDepartment(EmployeeInfo employeeInfo)
			throws ServiceException {
		Pagination pagination = new Pagination();
		pagination = this.paginationQuery("select r from Receipt r where r.employeeInfo.id in (select e.id from EmployeeInfo e where e.department.id in (select s.id from DepartmentInfo p , DepartmentInfo s where s.path like concat('%', p.id, '%') and p.id = ? )))", employeeInfo.getDepartment().getId());
		return pagination;
	}

}