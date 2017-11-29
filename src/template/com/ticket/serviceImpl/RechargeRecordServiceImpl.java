package com.ticket.serviceImpl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.ChannelCustomerInfo;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.FavouredPolicy;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.RechargeRecord;
import com.ticket.pojo.SystemUser;
import com.ticket.service.IChannelCustomerInfoService;
import com.ticket.service.ICustomerAccountService;
import com.ticket.service.IDepartmentInfoService;
import com.ticket.service.IFavouredPolicyService;
import com.ticket.service.IRechargeRecordService;
import com.ticket.util.Arith;
import com.ticket.util.DateUtil;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

/**
 * 充值记录业务接口实现类
 * @ClassName: IRechargeRecordService   
 * @Description: 提供充值记录操作的增删改查   
 * @author HiSay  
 * @date 2015-11-13 16:45:25
 *
 */
public class RechargeRecordServiceImpl extends BaseServiceImpl<RechargeRecord, String> implements IRechargeRecordService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(RechargeRecordServiceImpl.class);
	@Resource
	private IFavouredPolicyService favouredPolicyService;
	@Resource
	private IChannelCustomerInfoService channelCustomerInfoService;
	@Resource
	private ICustomerAccountService customerAccountService;
	@Resource
	private IDepartmentInfoService departmentInfoService;
	
	@Override
	public boolean merge(String id, String recordNo,Double amountOfMoney,String channelCustomerInfoId,Date payTime,String payWay,String receiptNo, String versionFlag) throws ServiceException {
		RechargeRecord rechargeRecord = dbDAO.queryById(this.getTableNameFromEntity(RechargeRecord.class), id);
		rechargeRecord.setRecordNo(DecoderUtil.UtfDecoder(recordNo));
		rechargeRecord.setAmountOfMoney(amountOfMoney);
		rechargeRecord.setChannelCustomerInfo(get(ChannelCustomerInfo.class, channelCustomerInfoId));
		rechargeRecord.setPayTime(payTime);
		rechargeRecord.setPayWay(DecoderUtil.UtfDecoder(payWay));
		rechargeRecord.setReceiptNo(DecoderUtil.UtfDecoder(receiptNo));
		CommonEntity status = rechargeRecord.getStatus();
		status.setVersionFlag(versionFlag);
		rechargeRecord.setStatus(status);
		dbDAO.merge(rechargeRecord);
		return true;
	}

	@Override
	public boolean persist(String recordNo,Double amountOfMoney,String channelCustomerInfoId,
			Date payTime,String payWay,String receiptNo, SystemUser systemUser) throws ServiceException {
		try {
			RechargeRecord rechargeRecord = new RechargeRecord();
			if(systemUser != null){
				rechargeRecord.setSystemUser(systemUser);
			}
			rechargeRecord.setRecordNo(DecoderUtil.UtfDecoder(recordNo));
			rechargeRecord.setAmountOfMoney(amountOfMoney);
			rechargeRecord.setChannelCustomerInfo(get(ChannelCustomerInfo.class, channelCustomerInfoId));
			rechargeRecord.setPayTime(payTime);
			rechargeRecord.setPayWay(DecoderUtil.UtfDecoder(payWay));
			rechargeRecord.setReceiptNo(DecoderUtil.UtfDecoder(receiptNo));
			CommonEntity status = rechargeRecord.getStatus();
			status.setVersionFlag(versionFlag);
			rechargeRecord.setStatus(status);
			dbDAO.persist(rechargeRecord);
			ChannelCustomerInfo channelCustomerInfo = channelCustomerInfoService
								.queryById(ChannelCustomerInfo.class.getSimpleName(), channelCustomerInfoId);
			Double songMoney = 0D;
			if(GeneralUtil.isNotNull(channelCustomerInfo.getFavouredPolicy())){
				FavouredPolicy favouredPolicy = channelCustomerInfo.getFavouredPolicy();
				if(favouredPolicy != null){
					if(amountOfMoney >= favouredPolicy.getRechargeAmount()){
						songMoney = Arith.mul(rechargeRecord.getAmountOfMoney(), favouredPolicy.getDiscountRate()-1);
					}
				}
			}
			StringBuffer sb = new StringBuffer();
			sb.append(channelCustomerInfo.getLoginId());
			sb.append("在");
			sb.append(DateUtil.parseDateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			sb.append("充值了");
			sb.append(amountOfMoney);
			sb.append("元");
			if(songMoney > 0){
				sb.append("并赠送了");
				sb.append(songMoney);
				sb.append("元");
			}
			customerAccountService.add(channelCustomerInfoId, 1, amountOfMoney,
					songMoney, ChannelCustomerInfo.class.getSimpleName(), channelCustomerInfoId, sb.toString());
			channelCustomerInfo.setBalance(customerAccountService.getSumMoney(channelCustomerInfoId));
			rechargeRecord.setSongMoney(songMoney);
			this.merge(rechargeRecord);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		RechargeRecord rechargeRecord = dbDAO.queryById(this.getTableNameFromEntity(RechargeRecord.class), id);
		dbDAO.remove(rechargeRecord);
		return true;
	}

	@Override
	public Pagination queryPageModuleByCustomerId(int pageSize,
			String customerId,String employee_id, String versionFlag,String keyword) throws ServiceException {
		Pagination pagination = new Pagination();
		if(GeneralUtil.isNotNull(employee_id)){
			if(GeneralUtil.isNull(keyword)){
				pagination = this.paginationQuery("select e from RechargeRecord e,ChannelCustomerInfo c " +
						" where e.channelCustomerInfo.id = c.id and c.employeeInfo.id = ? order by e.status.createTime desc" , employee_id);
			}else{
				pagination = this.paginationQuery("select e from RechargeRecord e,ChannelCustomerInfo c " +
						" where e.channelCustomerInfo.id = c.id and c.employeeInfo.id = ? and c.name like ? or c.loginId like ? order by e.status.createTime desc" , employee_id,"%"+keyword+"%","%"+keyword+"%");
			}
		}
		if(GeneralUtil.isNull(employee_id) && GeneralUtil.isNull(customerId)){
			if(GeneralUtil.isNull(keyword)){
				pagination = this.paginationQuery("select e from RechargeRecord e" +
						" order by e.status.createTime desc" );
			}else{
				
				pagination = this.paginationQuery("select e from RechargeRecord e,ChannelCustomerInfo c " +
						" where e.channelCustomerInfo.id = c.id and (c.name like ? or c.loginId like ?) order by e.status.createTime desc" ,"%"+keyword+"%","%"+keyword+"%");
			}
		}
		if(GeneralUtil.isNotNull(customerId)){
			if(GeneralUtil.isNull(keyword)){
				pagination = this.paginationQuery("select e from RechargeRecord e" +
						" where e.channelCustomerInfo.id = ? order by e.status.createTime desc" ,customerId);
			}else{
				pagination = this.paginationQuery("select e from RechargeRecord e,ChannelCustomerInfo c " +
						" where e.channelCustomerInfo.id = c.id and c.id = ? and (c.name like ? or c.loginId like ?) order by e.status.createTime desc" ,customerId,"%"+keyword+"%","%"+keyword+"%");
			}
		}
		return pagination;
	}

	@Override
	public double queryAllMoney(String employee_id) throws ServiceException {
		double countMoney = 0;
		
		if(GeneralUtil.isNotNull(employee_id)){
			countMoney = dbDAO.executeJPQLForQuerySingle("select sum(c.amountOfMoney) from RechargeRecord c,ChannelCustomerInfo cc where c.channelCustomerInfo.id = cc.id and cc.employeeInfo.id = ?",Double.class,employee_id);
		}
		countMoney = dbDAO.executeJPQLForQuerySingle("select sum(c.amountOfMoney) from RechargeRecord c",Double.class);
		
		
		return countMoney;
	}

	@Override
	public double queryThisMonthMoney() throws ServiceException {
		double money = 0;
		List<RechargeRecord> list = this.queryAll(RechargeRecord.class);
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		if(GeneralUtil.isNotNull(list)){
			for(RechargeRecord record: list){
				Date date = record.getStatus().getCreateTime();
				if(GeneralUtil.isNotNull(date)){
					if(sdf.format(now).equals(sdf.format(date))){
						money += record.getAmountOfMoney();
					}
				}
			}
		}
		return money;
	}

	@Override
	public List<RechargeRecord> queryByChannelCustomerInfoId(
			String ChannelCustomerInfoId) throws ServiceException {
		List<RechargeRecord> list = dbDAO.executeJPQLForQueryEntity("select c from " + RechargeRecord.class.getName() + " c where c.channelCustomerInfo.id = ?", ChannelCustomerInfoId);
		return list;
	}

	@Override
	public long customCount(Date startDate, Date endDate,EmployeeInfo employeeInfo,int type) {
		
		if(startDate == null){
			
			startDate = new Date(0);
		}
		if(endDate == null){
			
			endDate = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
		}else{
			
			endDate = new Date(endDate.getTime() + 24 * 60 * 60 * 1000 - 1);
		}
		long count = 0;
		if(GeneralUtil.isNotNull(employeeInfo)){
			if(type == 0){
				count = dbDAO.executeJPQLForQuerySingle("select count(t) from RechargeRecord t,ChannelCustomerInfo c where t.status.createTime between ? and ? and t.channelCustomerInfo.id = c.id and c.employeeInfo.id = ?", 
						Long.class, startDate, endDate,employeeInfo.getId());
			}
			if(type == 1){
				count = dbDAO.executeJPQLForQuerySingle("select count(t) from RechargeRecord t,ChannelCustomerInfo c,EmployeeInfo e where t.status.createTime between ? and ? and t.channelCustomerInfo.id = c.id and c.employeeInfo.id = e.id " +
						" and e.id in (select ee.id from EmployeeInfo ee where ee.department.id = ?)", 
						Long.class, startDate, endDate,employeeInfo.getDepartment().getId());
			}
		}else{
			
			count = dbDAO.executeJPQLForQuerySingle("select count(t) from " + RechargeRecord.class.getName() + " t where t.status.createTime between ? and ?", 
					Long.class, startDate, endDate);
			
		}
		return count;
	}

	@Override
	public double moneyAmount(Date startDate, Date endDate,EmployeeInfo employeeInfo,int type) {
		
		if(startDate == null){
			
			startDate = new Date(0);
		}
		if(endDate == null){
			
			endDate = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
		}else{
			
			endDate = new Date(endDate.getTime() + 24 * 60 * 60 * 1000 - 1);
		}
		Double count = 0.0;
		if(GeneralUtil.isNotNull(employeeInfo)){
			if(type == 0){
				count = dbDAO.executeJPQLForQuerySingle("select sum(t.amountOfMoney) from RechargeRecord t,ChannelCustomerInfo c where t.status.createTime between ? and ? and t.channelCustomerInfo.id = c.id and c.employeeInfo.id = ?", 
						Double.class, startDate, endDate,employeeInfo.getId());
			}
			if(type == 1){
				count = dbDAO.executeJPQLForQuerySingle("select sum(t.amountOfMoney) from RechargeRecord t,ChannelCustomerInfo c,EmployeeInfo e where t.status.createTime between ? and ? and t.channelCustomerInfo.id = c.id and c.employeeInfo.id = e.id and e.id in " +
						" (select ee.id from EmployeeInfo ee where ee.department.id = ?)", 
						Double.class, startDate, endDate,employeeInfo.getDepartment().getId());
			}
		}
		 count = dbDAO.executeJPQLForQuerySingle("select sum(t.amountOfMoney) from " + RechargeRecord.class.getName() + " t where t.status.createTime between ? and ?", 
				Double.class, startDate, endDate);
		return count;
	}

	@Override
	public long ticketCount(Date startDate, Date endDate,EmployeeInfo employeeInfo,int type) {
		
		if(startDate == null){
			
			startDate = new Date(0);
		}
		if(endDate == null){
			
			endDate = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
		}else{
			
			endDate = new Date(endDate.getTime() + 24 * 60 * 60 * 1000 - 1);
		}
		Long count = 0L;
		if(GeneralUtil.isNotNull(employeeInfo)){
			if(type == 0){
				count = dbDAO.executeJPQLForQuerySingle("select count(t) from RechargeRecord t,ChannelCustomerInfo c "
						+ " where t.status.createTime between ? and ? and (t.state = 1 or t.state = 2) and t.channelCustomerInfo.id = c.id and c.employeeInfo.id = ?", 
						Long.class, startDate, endDate,employeeInfo.getId());
			}
			if(type == 1){
				count = dbDAO.executeJPQLForQuerySingle("select count(t) from RechargeRecord t,ChannelCustomerInfo c,EmployeeInfo e "
						+ " where t.status.createTime between ? and ? and (t.state = 1 or t.state = 2) and t.channelCustomerInfo.id = c.id and c.employeeInfo.id = e.id where " +
						" e.id in (select ee.id from EmployeeInfo ee where ee.department.id = ?)", 
						Long.class, startDate, endDate,employeeInfo.getDepartment().getId());
			}
		}
		count = dbDAO.executeJPQLForQuerySingle("select count(t) from " + RechargeRecord.class.getName() + " t"
				+ " where t.status.createTime between ? and ? and (t.state = 1 or t.state = 2)", 
				Long.class, startDate, endDate);
		return count == null ? 0 : count;
	}

	@Override
	public double moneyAmountForTicket(Date startDate, Date endDate,EmployeeInfo employeeInfo,int type) {
		
		if(startDate == null){
			
			startDate = new Date(0);
		}
		if(endDate == null){
			
			endDate = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
		}else{
			
			endDate = new Date(endDate.getTime() + 24 * 60 * 60 * 1000 - 1);
		}
		Double count = 0.0;
		if(GeneralUtil.isNotNull(employeeInfo)){
			if(type == 0){
				count = dbDAO.executeJPQLForQuerySingle("select sum(t.amountOfMoney) from RechargeRecord t ,ChannelCustomerInfo c "
						+ " where t.status.createTime between ? and ? and (t.state = 1 or t.state = 2) and t.channelCustomerInfo.id = c.id and c.employeeInfo.id = ?", 
						Double.class, startDate, endDate,employeeInfo.getId());
			}
			if(type == 1){
				count = dbDAO.executeJPQLForQuerySingle("select sum(t.amountOfMoney) from RechargeRecord t ,ChannelCustomerInfo c,EmployeeInfo e "
						+ " where t.status.createTime between ? and ? and (t.state = 1 or t.state = 2) and t.channelCustomerInfo.id = c.id and c.employeeInfo.id = e.id " +
						" and e.id id (select ee.id from EmployeeInfo ee where ee.department.id = ?)", 
						Double.class, startDate, endDate,employeeInfo.getDepartment().getId());
			}
		}
		 count = dbDAO.executeJPQLForQuerySingle("select sum(t.amountOfMoney) from " + RechargeRecord.class.getName() + " t"
				+ " where t.status.createTime between ? and ? and (t.state = 1 or t.state = 2)", 
				Double.class, startDate, endDate);
		return count == null ? 0 : count;
	}

	@Override
	public List<RechargeRecord> allList(Date startDate, Date endDate) {
		
		if(startDate == null){
			
			startDate = new Date(0);
		}
		if(endDate == null){
			
			endDate = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
		}else{
			
			endDate = new Date(endDate.getTime() + 24 * 60 * 60 * 1000 - 1);
		}
		List<RechargeRecord> count = dbDAO.executeJPQLForQuery("select t from " + RechargeRecord.class.getName() + " t where t.status.createTime between ? and ?", 
				RechargeRecord.class, startDate, endDate);
		return count;
	}

	@Override
	public List<RechargeRecord> ticketList(Date startDate, Date endDate) {
		
		if(startDate == null){
			
			startDate = new Date(0);
		}
		if(endDate == null){
			
			endDate = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
		}else{
			
			endDate = new Date(endDate.getTime() + 24 * 60 * 60 * 1000 - 1);
		}
		List<RechargeRecord> count = dbDAO.executeJPQLForQuery("select t from " + RechargeRecord.class.getName() + " t"
				+ " where t.status.createTime between ? and ? and (t.state = 1 or t.state = 2)", 
				RechargeRecord.class, startDate, endDate);
		return count;
	}

	@Override
	public List<RechargeRecord> queryByChannelCustomerInfoId(
			String ChannelCustomerInfoId, Date startDate, Date endDate)
			throws ServiceException {
		endDate = new Date(endDate.getTime() + 24 * 60 * 60 * 1000 - 1);
		List<RechargeRecord> list = dbDAO.executeJPQLForQueryEntity("select c from " + RechargeRecord.class.getName() + " c where c.channelCustomerInfo.id = ? and c.status.createTime between ? and ?", ChannelCustomerInfoId,startDate,endDate);
		return list;
	}

	@Override
	public RechargeRecord lastForChannelCustomer(String channelCustomer_id) {
		
		RechargeRecord record = dbDAO.executeJPQLForQuerySingle(
				"select t from " + RechargeRecord.class.getName() + " t"
				+ " where t.status.createTime >= all(select t.status.createTime from " + RechargeRecord.class.getName() + " t2)"
				+ " and t.channelCustomerInfo.id=?", 
				RechargeRecord.class, channelCustomer_id); 
		return record;
	}

	@Override
	public long count(String channelCustomer_id) {
		
		Long count = dbDAO.executeJPQLForQuerySingle(
				"select count(t) from " + RechargeRecord.class.getName() + " t where t.channelCustomerInfo.id=?", Long.class, channelCustomer_id);
		return count;
	}
	
	@Override
	public double amount(String channelCustomer_id) {
		
		Double count = dbDAO.executeJPQLForQuerySingle(
				"select sum(t.amountOfMoney) from " + RechargeRecord.class.getName() + " t where t.channelCustomerInfo.id=?", Double.class, channelCustomer_id);
		return count == null ? 0.0 : count;
	}

	@Override
	public Pagination queryAllWhereIndepartment(EmployeeInfo employeeInfo,String keyword)
			throws ServiceException {
		Pagination pagination = new Pagination();
		if(GeneralUtil.isNull(keyword)){
			pagination = this.paginationQuery("select r from RechargeRecord r,ChannelCustomerInfo c where r.systemUser.id in (select e.id from EmployeeInfo e where e.department.id in (select s.id from DepartmentInfo p , DepartmentInfo s where s.path like concat('%', p.id, '%') and p.id = ? )) and r.channelCustomerInfo.id = c.id", employeeInfo.getDepartment().getId());
		}
		pagination = this.paginationQuery("select r from RechargeRecord r,ChannelCustomerInfo c where r.systemUser.id in (select e.id from EmployeeInfo e where e.department.id in (select s.id from DepartmentInfo p, DepartmentInfo s where s.path like concat('%', p.id, '%') and p.id = ? )) and r.channelCustomerInfo.id = c.id and (c.name like ? or c.loginId like ?) order by r.status.createTime desc", employeeInfo.getDepartment().getId(),"%"+keyword+"%","%"+keyword+"%");
		return pagination;
	}
}