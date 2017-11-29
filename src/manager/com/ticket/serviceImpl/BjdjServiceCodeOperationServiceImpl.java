package com.ticket.serviceImpl;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjServiceCode;
import com.ticket.pojo.BjdjServiceCodeOperation;
import com.ticket.pojo.Member;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.SystemDictionary;
import com.ticket.pojo.SystemUser;
import com.ticket.service.IBjdjServiceCodeOperationService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.util.PaginationContext;

/**
 * 服务码日志表业务接口实现类
 * @ClassName: IBjdjServiceCodeLogService   
 * @Description: 提供服务码日志表操作的增删改查   
 * @author HiSay  
 * @date 2015-10-23 15:17:18
 *
 */
public class BjdjServiceCodeOperationServiceImpl extends BaseServiceImpl<BjdjServiceCodeOperation, String> 
	implements IBjdjServiceCodeOperationService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(BjdjServiceCodeOperationServiceImpl.class);
	@Resource ISystemDictionaryService dictionaryService;
	@Override
	public BjdjServiceCodeOperation persist(BjdjServiceCode serviceCode, SystemDictionary operation, Member fromMember, Member toMember) throws ServiceException {
		
		BjdjServiceCodeOperation bjdjServiceCodeLog = new BjdjServiceCodeOperation();
		bjdjServiceCodeLog.setOperation(operation);
		bjdjServiceCodeLog.setFromMember(fromMember);
		bjdjServiceCodeLog.setToMember(toMember);
		bjdjServiceCodeLog.setServiceCode(serviceCode);
		dbDAO.persist(bjdjServiceCodeLog);
		return bjdjServiceCodeLog;
	}
	
	@Override
	public BjdjServiceCodeOperation persist(BjdjServiceCode serviceCode, SystemDictionary operation, SystemUser systemUser) throws ServiceException {
		
		BjdjServiceCodeOperation bjdjServiceCodeLog = new BjdjServiceCodeOperation();
		bjdjServiceCodeLog.setOperation(operation);
		bjdjServiceCodeLog.setServiceCode(serviceCode);
		bjdjServiceCodeLog.setSystemUser(systemUser);
		dbDAO.persist(bjdjServiceCodeLog);
		return bjdjServiceCodeLog;
	}
	
	@Override
	public BjdjServiceCodeOperation merge(String id, BjdjServiceCode serviceCode, SystemDictionary operation, Member fromMember, Member toMember) throws ServiceException {
		
		BjdjServiceCodeOperation bjdjServiceCodeLog = dbDAO.queryById(this.getTableNameFromEntity(BjdjServiceCodeOperation.class), id);
		bjdjServiceCodeLog.setOperation(operation);
		bjdjServiceCodeLog.setFromMember(fromMember);
		bjdjServiceCodeLog.setToMember(toMember);
		dbDAO.merge(bjdjServiceCodeLog);
		return bjdjServiceCodeLog;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		BjdjServiceCodeOperation bjdjServiceCodeLog = dbDAO.queryById(this.getTableNameFromEntity(BjdjServiceCodeOperation.class), id);
		dbDAO.remove(bjdjServiceCodeLog);
		return true;
	}

	@Override
	public Pagination queryEntityByUserId(String versionFlag, int pageSize,
			String userId) throws ServiceException {
		
		return dbDAO.queryByPageModule(BjdjServiceCodeOperation.class.getSimpleName(), deleteFlag, versionFlag, "and s.customer_id = ?3", new Object[]{userId}, orderBy, PaginationContext.getOffset(), pageSize);

	}

	@Override
	public BjdjServiceCodeOperation persist(BjdjServiceCode serviceCode,
			SystemDictionary operation,String channelCustomer_id, String orderInfo_id,String orderInfoDetail_id)
			throws ServiceException {
		
		BjdjServiceCodeOperation bjdjServiceCodeLog = new BjdjServiceCodeOperation();
		bjdjServiceCodeLog.setOperation(operation);
		bjdjServiceCodeLog.setChannelCustomerInfo_id(channelCustomer_id);
		bjdjServiceCodeLog.setOrderInfo_id(orderInfo_id);
		bjdjServiceCodeLog.setOrderInfoDetail_id(orderInfoDetail_id);
		bjdjServiceCodeLog.setServiceCode(serviceCode);
		dbDAO.persist(bjdjServiceCodeLog);
		return bjdjServiceCodeLog;
	}

	@Override
	public BjdjServiceCodeOperation recently(BjdjServiceCode serviceCode) {
		
		BjdjServiceCodeOperation operation = dbDAO.executeJPQLForQuerySingle(
				"select s from " + BjdjServiceCodeOperation.class.getName() + " s"
						+ " order by s.status.createTime", 
				BjdjServiceCodeOperation.class, 0, 1);
		return operation;
	}

	@Override
	public BjdjServiceCodeOperation recentlyOperation(BjdjServiceCode serviceCode, String operationName) {
		
		List<BjdjServiceCodeOperation> list = dbDAO.executeJPQLForQuery(
				"select s from " + BjdjServiceCodeOperation.class.getName() + " s"
						+ " where s.operation.name=? order by s.status.createTime", 
				BjdjServiceCodeOperation.class, 
				0, 1, operationName, serviceCode);
		if(list.size() != 0){
			
			return list.get(0);
		}
		return null;
	}

	@Override
	public BjdjServiceCodeOperation recentlyOperationFromMember(
			BjdjServiceCode serviceCode, String operationName, Member fromMember) {
		
		List<BjdjServiceCodeOperation> list = dbDAO.executeJPQLForQuery(
				"select s from " + BjdjServiceCodeOperation.class.getName() + " s"
						+ " where s.operation.name=? and s.fromMember=? and s.serviceCode=? order by s.status.createTime", 
				BjdjServiceCodeOperation.class, 
				0, 1,
				operationName, fromMember, serviceCode);
		if(list.size() != 0){
			
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public BjdjServiceCodeOperation recentlyOperationToMember(
			BjdjServiceCode serviceCode, String operationName, Member fromMember) {
		
		List<BjdjServiceCodeOperation> list = dbDAO.executeJPQLForQuery(
					"select s from " + BjdjServiceCodeOperation.class.getName() + " s"
							+ " where s.operation.name=? and s.toMember=? and s.serviceCode=? order by s.status.createTime", 
					BjdjServiceCodeOperation.class, 
					0, 1,
					operationName, fromMember, serviceCode);
		if(list.size() != 0){
			
			return list.get(0);
		}
		return null;
	}

	@Override
	public BjdjServiceCodeOperation recentlyOperation(
			BjdjServiceCode serviceCode, String operationName, Member fromMember,
			Member toMember) {
		
		List<BjdjServiceCodeOperation> list = dbDAO.executeJPQLForQuery(
				"select s from " + BjdjServiceCodeOperation.class.getName() + " s"
						+ " where s.operation.name=? and s.fromMember=? and s.toMember=? and s.serviceCode=?"
						+ " order by s.status.createTime", 
				BjdjServiceCodeOperation.class, 
				0, 1,
				operationName, fromMember, toMember, serviceCode);
		
		if(list.size() != 0){
			
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<BjdjServiceCodeOperation> getByTimes(Date startTime,
			Date endTime) {
		SystemDictionary dictionary = dictionaryService.getByName("unactive");
		List<BjdjServiceCodeOperation> codeOperations = dbDAO.executeJPQLForQueryEntity(
					"select c from " + BjdjServiceCodeOperation.class.getName() + " c"
							+ " where c.status.createTime >= ? and c.status.createTime <= ? and c.operation = ?", 
					startTime,endTime,dictionary);
		return codeOperations;
	}

	@Override
	public List<BjdjServiceCodeOperation> getByUseTimes(Date startTime,
			Date endTime) {
		SystemDictionary dictionary = dictionaryService.getByName("unactive");
		List<BjdjServiceCodeOperation> codeOperations = dbDAO.executeJPQLForQueryEntity(
				"select c from " + BjdjServiceCodeOperation.class.getName() + " c"
				+ " where c.appointment.hall is null and c.appointment.useTime >= ? and c.appointment.useTime <= ? and c.operation = ?", 
				startTime,endTime,dictionary);
		return codeOperations;
	}
	
	@Override
	public List<BjdjServiceCodeOperation> getEnterHallByUseTimes(Date startTime,
			Date endTime) {
		SystemDictionary dictionary = dictionaryService.getByName("unactive");
		List<BjdjServiceCodeOperation> codeOperations = dbDAO.executeJPQLForQueryEntity(
				"select c from " + BjdjServiceCodeOperation.class.getName() + " c"
				+ " where c.appointment.hall is not null and c.appointment.useTime >= ? and c.appointment.useTime <= ? and c.operation = ?", 
				startTime,endTime,dictionary);
		return codeOperations;
	}
	
	@Override
	public List<BjdjServiceCodeOperation> getCanceled(Date flightDate) {
		
		Calendar c = Calendar.getInstance();
		c.setTime(flightDate);
		SystemDictionary dictionary = dictionaryService.getByName("unactive");
		List<BjdjServiceCodeOperation> codeOperations = dbDAO.executeJPQLForQueryEntity(
				"select c from " + BjdjServiceCodeOperation.class.getName() + " c"
						+ " where c.appointment.hall is not null and c.operation = ?"
						+ " and YEAR(c.appointment.useTime)=? and MONTH(c.appointment.useTime)=? and DAY(c.appointment.useTime)=?", 
						dictionary, c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH));
		return codeOperations;
	}

}