package com.ticket.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.Agreement;
import com.ticket.pojo.ChannelCustomerInfo;
import com.ticket.pojo.ChannelPosition;
import com.ticket.pojo.ChannelType;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.CustomerProtectLog;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.EmployeeOutVisit;
import com.ticket.pojo.FavouredPolicy;
import com.ticket.pojo.Industry;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.PhoneVisit;
import com.ticket.pojo.Position;
import com.ticket.service.IAgreementService;
import com.ticket.service.IChannelCustomerInfoService;
import com.ticket.service.ICustomerProtectLogService;
import com.ticket.service.IDepartmentInfoService;
import com.ticket.service.IEmployeeInfoService;
import com.ticket.service.IPositionService;
import com.ticket.util.DateUtil;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.MD5Util;
import com.ticket.util.PaginationContext;

/**
 * 渠道客户信息业务接口实现类
 * @ClassName: IChannelCustomerInfoService   
 * @Description: 提供渠道客户信息操作的增删改查   
 * @author HiSay  
 * @date 2015-11-04 10:39:40
 *
 */
public class ChannelCustomerInfoServiceImpl extends BaseServiceImpl<ChannelCustomerInfo, String> implements IChannelCustomerInfoService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(ChannelCustomerInfoServiceImpl.class);
	@Resource
	private ICustomerProtectLogService customerProtectLogService = null;
	@Resource
	private IAgreementService agreementService = null;
	@Resource
	private IEmployeeInfoService employeeInfoService = null;
	@Resource
	private IPositionService positionService = null;
	
	//部门信息的业务层
	@Resource IDepartmentInfoService departmentInfoService = null;
	@Override
	public boolean merge(String id, String name,String loginId,String password,
			String channelTypeId,Date openAccountDate,String contact,String contactPhone,
			Double openAccountMoney,String payWay,Double balance,String favouredPolicyId,
			String industry,String email,EmployeeInfo employeeInfo,String industry_id
			,String channelPosition_id,String idCard,String yyzzNumber,List<String> annexs) throws ServiceException {
		ChannelCustomerInfo channelCustomerInfo = dbDAO.queryById(this.getTableNameFromEntity(ChannelCustomerInfo.class), id);
		if(GeneralUtil.isNotNull(name)){
			
			channelCustomerInfo.setName(DecoderUtil.UtfDecoder(name));
		}
		if(GeneralUtil.isNotNull(loginId)){
			channelCustomerInfo.setLoginId(DecoderUtil.UtfDecoder(loginId));
		}
		if(channelCustomerInfo.getPassword() == null || !channelCustomerInfo.getPassword().equals(password)){
			channelCustomerInfo.setPassword(MD5Util.Azdg.strMD5(password));
		}
		channelCustomerInfo.setChannelType(get(ChannelType.class, channelTypeId));
		if(GeneralUtil.isNotNull(openAccountDate)){
			channelCustomerInfo.setOpenAccountDate(openAccountDate);
		}
//		channelCustomerInfo.setOpenAccountDate(openAccountDate);
		channelCustomerInfo.setContact(DecoderUtil.UtfDecoder(contact));
		channelCustomerInfo.setContactPhone(DecoderUtil.UtfDecoder(contactPhone));
		if(GeneralUtil.isNotNull(openAccountMoney)){
			channelCustomerInfo.setOpenAccountMoney(openAccountMoney);
		}
//		channelCustomerInfo.setOpenAccountMoney(openAccountMoney);
		if(GeneralUtil.isNotNull(payWay)){
			channelCustomerInfo.setPayWay(DecoderUtil.UtfDecoder(payWay));
		}
//		channelCustomerInfo.setPayWay(DecoderUtil.UtfDecoder(payWay));
		if(GeneralUtil.isNotNull(balance)){
			channelCustomerInfo.setBalance(balance);
		}
//		channelCustomerInfo.setBalance(balance);
		channelCustomerInfo.setFavouredPolicy(get(FavouredPolicy.class, favouredPolicyId));
		if(GeneralUtil.isNotNull(industry)){
			channelCustomerInfo.setIndustry(get(Industry.class, industry));
		}
//		channelCustomerInfo.setIndustry(get(Industry.class, industry));
		if(GeneralUtil.isNotNull(email)){
			channelCustomerInfo.setEmail(DecoderUtil.UtfDecoder(email));
		}
//		channelCustomerInfo.setEmail(DecoderUtil.UtfDecoder(email));
		if(employeeInfo != null){
			channelCustomerInfo.setEmployeeInfo(employeeInfo);
			channelCustomerInfo.setDepartment(employeeInfo.getDepartment());
		}
		CommonEntity status = channelCustomerInfo.getStatus();
		status.setVersionFlag(versionFlag);
		channelCustomerInfo.setIndustry(get(Industry.class, industry_id));
		channelCustomerInfo.setChannelPosition(get(ChannelPosition.class, channelPosition_id));//联系人岗位
		if(GeneralUtil.isNotNull(idCard)){
			channelCustomerInfo.setIdCard(idCard);
		}
//		channelCustomerInfo.setIdCard(idCard);
		if(GeneralUtil.isNotNull(yyzzNumber)){
			channelCustomerInfo.setYyzzNumber(yyzzNumber);
		}
//		channelCustomerInfo.setYyzzNumber(yyzzNumber);
		channelCustomerInfo.setStatus(status);
		channelCustomerInfo.setState(1);
		dbDAO.merge(channelCustomerInfo);
		if(GeneralUtil.isNotNull(annexs) && annexs.size() > 0){
			this.addManyAnnex(channelCustomerInfo, channelCustomerInfo.getId(), annexs,1, versionFlag);
		}
		return true;
	}

	@Override
	public boolean persist(String name,String loginId,String password,String channelTypeId,
			Date openAccountDate,String contact,String contactPhone,Double openAccountMoney,String payWay,
			Double balance,String favouredPolicyId,String email,EmployeeInfo employeeInfo
			,String industry_id,String channelPosition_id,Integer state) throws ServiceException {
		ChannelCustomerInfo channelCustomerInfo = new ChannelCustomerInfo();
		channelCustomerInfo.setName(DecoderUtil.UtfDecoder(name));
		channelCustomerInfo.setLoginId(DecoderUtil.UtfDecoder(loginId));
		channelCustomerInfo.setPassword(MD5Util.Azdg.strMD5(password));
		channelCustomerInfo.setChannelType(get(ChannelType.class, channelTypeId));
		channelCustomerInfo.setOpenAccountDate(openAccountDate);
		channelCustomerInfo.setContact(DecoderUtil.UtfDecoder(contact));
		channelCustomerInfo.setContactPhone(DecoderUtil.UtfDecoder(contactPhone));
		channelCustomerInfo.setOpenAccountMoney(openAccountMoney);
		channelCustomerInfo.setPayWay(DecoderUtil.UtfDecoder(payWay));
		channelCustomerInfo.setBalance(balance);
		channelCustomerInfo.setFavouredPolicy(get(FavouredPolicy.class, favouredPolicyId));
		channelCustomerInfo.setEmail(DecoderUtil.UtfDecoder(email));
		if(employeeInfo != null){
			channelCustomerInfo.setEmployeeInfo(employeeInfo);
			channelCustomerInfo.setDepartment(employeeInfo.getDepartment());
		}
		channelCustomerInfo.setIndustry(get(Industry.class, industry_id));
		channelCustomerInfo.setChannelPosition(get(ChannelPosition.class, channelPosition_id));
		channelCustomerInfo.setState(state);
		dbDAO.persist(channelCustomerInfo);
		return true;
	}
	@Override
	public boolean remove(String id) throws ServiceException {
		ChannelCustomerInfo channelCustomerInfo = dbDAO.queryById(this.getTableNameFromEntity(ChannelCustomerInfo.class), id);
		dbDAO.remove(channelCustomerInfo);
		return true;
	}

	
	@Override
	public ChannelCustomerInfo login(String loginId, String password) throws ServiceException {
		String tableName = ChannelCustomerInfo.class.getSimpleName();
		String sql="select c from "+tableName+" c where loginId=? and password=?";
		ChannelCustomerInfo obj = (ChannelCustomerInfo) dbDAO.executeJPQLForQuerySingle(sql, loginId,MD5Util.Azdg.strMD5(password));
		return obj;
	}

	@Override
	public boolean updatePassword(String id, String password)
			throws ServiceException {
		ChannelCustomerInfo customerInfo = this.queryById(ChannelCustomerInfo.class.getSimpleName(), id) ;
		customerInfo.setPassword(MD5Util.Azdg.strMD5(password));
		dbDAO.merge(customerInfo);
		return true;
	}
	

	@Override
	public boolean merge(String id, String name, String channelTypeId,
			String contact, String contactPhone, String industry, String email,
			EmployeeInfo employeeInfo,String industry_id,String channelPosition_id) throws ServiceException {
		ChannelCustomerInfo channelCustomerInfo = dbDAO.queryById(this.getTableNameFromEntity(ChannelCustomerInfo.class), id);
		channelCustomerInfo.setName(DecoderUtil.UtfDecoder(name));
		channelCustomerInfo.setChannelType(get(ChannelType.class, channelTypeId));
		channelCustomerInfo.setContact(DecoderUtil.UtfDecoder(contact));
		channelCustomerInfo.setContactPhone(DecoderUtil.UtfDecoder(contactPhone));
		channelCustomerInfo.setIndustry(get(Industry.class, industry));
		channelCustomerInfo.setEmail(DecoderUtil.UtfDecoder(email));
		if(employeeInfo != null){
			channelCustomerInfo.setEmployeeInfo(employeeInfo);
			channelCustomerInfo.setDepartment(employeeInfo.getDepartment());
		}
		channelCustomerInfo.setChannelPosition(get(ChannelPosition.class, channelPosition_id));
		dbDAO.merge(channelCustomerInfo);
		return true;
	}

	@Override
	public boolean persist(String name, String channelTypeId,
			String contact, String contactPhone, String industry, String email,
			EmployeeInfo employeeInfo,String industry_id,String channelPosition_id) throws ServiceException {
		ChannelCustomerInfo channelCustomerInfo = new ChannelCustomerInfo();
		channelCustomerInfo.setName(DecoderUtil.UtfDecoder(name));
		channelCustomerInfo.setChannelType(get(ChannelType.class, channelTypeId));
		channelCustomerInfo.setContact(DecoderUtil.UtfDecoder(contact));
		channelCustomerInfo.setContactPhone(DecoderUtil.UtfDecoder(contactPhone));
		channelCustomerInfo.setIndustry(get(Industry.class, industry));
		channelCustomerInfo.setEmail(DecoderUtil.UtfDecoder(email));
		if(employeeInfo != null){
			channelCustomerInfo.setEmployeeInfo(employeeInfo);
			channelCustomerInfo.setDepartment(employeeInfo.getDepartment());
		}
		channelCustomerInfo.setChannelPosition(get(ChannelPosition.class, channelPosition_id));
		dbDAO.persist(channelCustomerInfo);
		return true;
	}


	@Override
	public ChannelCustomerInfo IsExit(String name) throws ServiceException {
		String tableName = ChannelCustomerInfo.class.getSimpleName();
		String sql="select c from "+tableName+" c where c.name=?";
		ChannelCustomerInfo obj = (ChannelCustomerInfo) dbDAO.executeJPQLForQuerySingle(sql, name);
		return obj;
	}

	@Override
	public Pagination queryAll(String keyword, EmployeeInfo employeeInfo,
			Boolean isPublic,Integer state,String order,Integer pageSize) throws ServiceException {
		try {
			List<Object[]> objects = new ArrayList<Object[]>();
			StringBuffer sb = new StringBuffer();
			if(GeneralUtil.isNotNull(keyword)){
				sb.append("and (s.name like?3 or s.contactPhone like ?4 ) ");
				objects.add(new Object[]{3,"%"+keyword+"%"});
				objects.add(new Object[]{4,"%"+keyword+"%"});
			}
			if(GeneralUtil.isNotNull(employeeInfo)){
				sb.append("and s.employeeInfo.id=?5 ");
				objects.add(new Object[]{5,employeeInfo.getId()});
			}
			if(GeneralUtil.isNotNull(state)){
				sb.append("and s.state=?6 ");
				objects.add(new Object[]{6,state});
			}
			if(isPublic){
				sb.append("and s.customerOwner is null ");
			}
			if(GeneralUtil.isNull(order)){
				order = orderBy;
			}
			return dbDAO.queryByPageModuleNew(ChannelCustomerInfo.class.getSimpleName(), deleteFlag, versionFlag, 
					sb.toString(), objects, order, PaginationContext.getOffset(), pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Pagination queryAll2(String keyword, EmployeeInfo employeeInfo,
			Boolean isEmployeeNull,Integer state,String order,Integer pageSize) throws ServiceException {
		try {
			List<Object[]> objects = new ArrayList<Object[]>();
			StringBuffer sb = new StringBuffer();
			if(GeneralUtil.isNotNull(keyword)){
				sb.append("and (s.name like?3 or s.contactPhone like ?4 ) ");
				objects.add(new Object[]{3,"%"+keyword+"%"});
				objects.add(new Object[]{4,"%"+keyword+"%"});
			}
			if(GeneralUtil.isNotNull(employeeInfo)){
				sb.append("and s.employeeInfo.id=?5 ");
				objects.add(new Object[]{5,employeeInfo.getId()});
			}
			if(GeneralUtil.isNotNull(state)){
				sb.append("and s.state=?6 ");
				objects.add(new Object[]{6,state});
			}
			if(!isEmployeeNull){
				sb.append("and s.employeeInfo.id is not null ");
			}
			if(GeneralUtil.isNull(order)){
				order = orderBy;
			}
			return dbDAO.queryByPageModuleNew(ChannelCustomerInfo.class.getSimpleName(), deleteFlag, versionFlag, 
					sb.toString(), objects, order, PaginationContext.getOffset(), pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ChannelCustomerInfo queryByLoginIdExist(String loginId)
			throws ServiceException {
		try {
			String tableName = ChannelCustomerInfo.class.getSimpleName();
			String sql="select c from "+tableName+" c where loginId=?";
			return (ChannelCustomerInfo)dbDAO.executeJPQLForQuerySingle(sql, loginId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean batchGiveUpCustomer(String idsValue) throws ServiceException {
		try {
			if(GeneralUtil.isNotNull(idsValue)){
				String[] ids = idsValue.split(",");
				for(String id:ids){
					singleGiveUpCustomer(id);
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean singleGiveUpCustomer(String id) throws ServiceException {
		try {
			ChannelCustomerInfo channelCustomerInfo = queryById(ChannelCustomerInfo.class.getSimpleName(), id);
			channelCustomerInfo.setEmployeeInfo(null);
			this.merge(channelCustomerInfo);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<ChannelCustomerInfo> queryAllList(String keyword, Integer size)
			throws ServiceException {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("");
			List<Object[]> objects = new ArrayList<Object[]>();
			if(GeneralUtil.isNotNull(keyword)){
				sb.append("and s.name like ?3 ");
				sb.append("or s.loginId like ?4");
				objects.add(new Object[]{3,"%"+keyword+"%"});
				objects.add(new Object[]{4,"%"+keyword+"%"});
			}
			sb.append(" and s.loginId is not null");
			return dbDAO.queryByListNew(ChannelCustomerInfo.class.getSimpleName(),
					deleteFlag, versionFlag, sb.toString(), objects, orderBy, size);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ChannelCustomerInfo> queryAllList(String keyword,
			EmployeeInfo employeeInfo, Integer size) throws ServiceException {
		try {
			if(employeeInfo != null){
				return queryAllListId(keyword, employeeInfo.getId(), size);
			}
			return queryAllListId(keyword,"", size);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ChannelCustomerInfo> queryAllListId(String keyword,
			String employeeInfoId, Integer size) throws ServiceException {
		try {
			StringBuffer sb = new StringBuffer();
			List<Object[]> objects = new ArrayList<Object[]>();
			if(GeneralUtil.isNotNull(keyword)){
				sb.append("and s.name like ?3 ");
				objects.add(new Object[]{3,"%"+keyword+"%"});
				sb.append("or s.loginId like ?4 ");
				objects.add(new Object[]{4,"%"+keyword+"%"});
			}
			if(GeneralUtil.isNotNull(employeeInfoId)){
				EmployeeInfo employeeInfo = employeeInfoService.queryById(EmployeeInfo.class.getName(), employeeInfoId);
				Position position = positionService.queryByName("一线销售人员");
				if(employeeInfo.getPosition().getId().equals(position.getId())){
					sb.append("and s.employeeInfo.id=?5 ");
					objects.add(new Object[]{5,employeeInfoId});
				}
			}
			return dbDAO.queryByListNew(ChannelCustomerInfo.class.getSimpleName(), deleteFlag, versionFlag,
					sb.toString(), objects, orderBy, size);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getChannelCustomerInfoState(String id) throws ServiceException {
		try {
			ChannelCustomerInfo channelCustomerInfo = queryById(ChannelCustomerInfo.class.getSimpleName(), id);
			if(GeneralUtil.isNull(channelCustomerInfo.getEmployeeInfo())){
				return "公共客户";
			}
			List<CustomerProtectLog> list = customerProtectLogService.queryAllList(id, null, new Date(), 0, 2);
			if(list != null && list.size() > 0){
				return "已保护";
			}
			if(channelCustomerInfo.getState() != 1){
				return "意向客户";
			}else{
				return "渠道客户";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "正常客户";
		}
	}

	@Override
	public Boolean addWap(String name, String loginId,
			String password, String contact, String contactPhone,EmployeeInfo employeeInfo)
			throws ServiceException {
		try {
			ChannelCustomerInfo channelCustomerInfo = new ChannelCustomerInfo();
			channelCustomerInfo.setName(name);
			channelCustomerInfo.setLoginId(loginId);
			channelCustomerInfo.setPassword(MD5Util.Azdg.strMD5(password));
			channelCustomerInfo.setContact(contact);
			channelCustomerInfo.setContactPhone(contactPhone);
			channelCustomerInfo.setOpenAccountDate(new Date());
			if(employeeInfo != null){
				channelCustomerInfo.setEmployeeInfo(employeeInfo);
			}
			this.persist(channelCustomerInfo);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void initCustomer() throws ServiceException {
		try {
			List<ChannelCustomerInfo> list = queryAllList(null, null, null);
			if(list != null && list.size() > 0){
				for(ChannelCustomerInfo c:list){
					c.setState(0);
					this.merge(c);
				}
			}
			List<Agreement> agreements = agreementService.queryByStateAndEffective(5, 1, null);
			if(agreements != null && agreements.size() > 0){
				for(Agreement a:agreements){
					ChannelCustomerInfo channelCustomerInfo = a.getChannelCustomerInfo();
					channelCustomerInfo.setAgreement(a);
					channelCustomerInfo.setState(1);
					this.merge(channelCustomerInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean transferCustomer(String afterEmployee_id, String customer_id, String versionFlag) throws ServiceException {
		try {
			String[] splitCustomer = customer_id.split(",");
			for(String str :splitCustomer){
				ChannelCustomerInfo channelCustomerInfo = dbDAO.queryById(ChannelCustomerInfo.class.getSimpleName(), str);
				channelCustomerInfo.setEmployeeInfo(get(EmployeeInfo.class, afterEmployee_id));
				dbDAO.merge(channelCustomerInfo);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<ChannelCustomerInfo> queryIntentionCustomerListByEmployee(
			String employee_id) {
		return dbDAO.queryByList(ChannelCustomerInfo.class.getSimpleName(), deleteFlag, versionFlag, " and s.employeeInfo.id =?3 and s.state =?4", new Object[]{employee_id,ContextConstants.STATUS_OF_ZERO}, orderBy, null);
	}

	@Override
	public List<ChannelCustomerInfo> queryChannelCustomerListByEmployee(
			String employee_id) {
		return dbDAO.queryByList(ChannelCustomerInfo.class.getSimpleName(), deleteFlag, versionFlag, " and s.employeeInfo.id =?3 and s.state =?4", new Object[]{employee_id,ContextConstants.STATUS_OF_ONE}, orderBy, null);
	}

	@Override
	public List<ChannelCustomerInfo> getAllCustomer(String keyword,
			String versionFlag) throws ServiceException {
		return queryAllListId(keyword,"", null);
	}

	@Override
	public List<ChannelCustomerInfo> queryChannelCustomerList(
			String channelTypeId, String versionFlag) throws ServiceException {
		return dbDAO.queryByList(ChannelCustomerInfo.class.getSimpleName(), deleteFlag, versionFlag, "and s.state =?3 and s.channelType.id =?4", new Object[]{ContextConstants.STATUS_OF_ONE,channelTypeId}, orderBy, null);
	}

	@Override
	public long queryAllCount(String employee_id) throws ServiceException {
		List<Object> list = new ArrayList<Object>();
		if(GeneralUtil.isNotNull(employee_id)){
			
			list = dbDAO.executeJPQLForQuery("select count(c) from ChannelCustomerInfo c where c.employeeInfo.id = ? ",employee_id);
		}else{
			list = dbDAO.executeJPQLForQuery("select count(c) from ChannelCustomerInfo c");
		}
		long count = (Long)list.get(0);
		return count;
	}

	@Override
	public long queryThisMonthAddCount(String employee_id) throws ServiceException {
		List<ChannelCustomerInfo> list = this.queryAll(ChannelCustomerInfo.class);
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		long count = 0;
		if(GeneralUtil.isNotNull(list)){
			for(ChannelCustomerInfo info:list){
				if(GeneralUtil.isNotNull(employee_id)){
					if(info.getEmployeeInfo().getId().equals(employee_id)){
						Date date = info.getOpenAccountDate();
						if(GeneralUtil.isNotNull(date)){
							if(sdf.format(now).equals(sdf.format(date))){
								count++;
							}
						}
					}
				}else{
					Date date = info.getOpenAccountDate();
					if(GeneralUtil.isNotNull(date)){
						if(sdf.format(now).equals(sdf.format(date))){
							count++;
						}
					}
				}
			}
		}
		return count;
	}

	@Override
	public Date getLastDate(String channelCustomber_id) {
		
		Date datePhone = dbDAO.executeJPQLForQuerySingle(
				"select t.status.createTime from " + PhoneVisit.class.getName() + " t"
				+ " where t.customer_id = ? and t.status.createTime >= all("
				+ "select t2.status.createTime from " + PhoneVisit.class.getName() + " t2)", Date.class, channelCustomber_id);
		Date dateVisit = dbDAO.executeJPQLForQuerySingle(
				"select t.status.createTime from " + EmployeeOutVisit.class.getName() + " t"
				+ " where t.customer_id = ? and t.status.createTime >= all("
				+ "select t2.status.createTime from " + EmployeeOutVisit.class.getName() + " t2)", Date.class, channelCustomber_id);
		if(datePhone == null){
			
			return dateVisit;
		}else if(dateVisit == null){
			
			return datePhone;
		}
		return datePhone.getTime() > dateVisit.getTime() ? datePhone : dateVisit;
	}
	
	@Override
	public Long countTodayIntentionCustomer(String versionFlag)
			throws ServiceException {
		return dbDAO.getTotalCount(ChannelCustomerInfo.class.getSimpleName(), deleteFlag, versionFlag, "and s.state =0 and s.status.createTime > ?3", new Object[]{DateUtil.getTodayStart()});
	}

	@Override
	public Long countIntentionByDept(String dept_id) throws ServiceException {
		String ids = departmentInfoService.parseClassIds(dept_id);
		Long i = dbDAO.getTotalCount(ChannelCustomerInfo.class.getSimpleName(), deleteFlag, versionFlag, "and s.department.id in ("+ids+") and s.status.createTime > ?3", new Object[]{DateUtil.getTodayStart()});
		return i;
	}

	@Override
	public Long countIntentionCustomerByDate(Date startDate, Date endDate,
			String dept_id) throws ServiceException {
		String ids = departmentInfoService.parseClassIds(dept_id);
		return dbDAO.getTotalCount(ChannelCustomerInfo.class.getSimpleName(), deleteFlag, versionFlag, "and s.department.id in ("+ids+") and s.status.createTime between ?3 and ?4", new Object[]{startDate,endDate});
	}

	@Override
	public List<ChannelCustomerInfo> queryIntentionCustomerList(
			String versionFlag) throws ServiceException {
		return dbDAO.queryByList(ChannelCustomerInfo.class.getSimpleName(), deleteFlag, versionFlag, "and s.state =0", null,orderBy,null);
	}

	@Override
	public long countNewCustomer(String employee_id, Date startDate,
			Date endDate ,String channelTypeId) throws ServiceException {
		endDate = new Date(endDate.getTime() + 24 * 60 * 60 * 1000 - 1);
		List<Object> list = dbDAO.executeJPQLForQuery("select count(c) from " + ChannelCustomerInfo.class.getName() + " c where c.employeeInfo.id = ? and c.status.createTime >= ? and c.status.createTime <= ? and c.channelType.id = ?", employee_id,startDate,endDate,channelTypeId);
		long count = 0;
		if(GeneralUtil.isNotNull(list)){
			count = (Long)list.get(0);
		}
		return count;
	}

	@Override
	public long countOldCustomer(String employee_id, Date startDate,
			Date endDate ,String channelTypeId) throws ServiceException {
		endDate = new Date(endDate.getTime() + 24 * 60 * 60 * 1000 - 1);
		List<Object> list = dbDAO.executeJPQLForQuery("select count(c) from " + ChannelCustomerInfo.class.getName() + " c where c.employeeInfo.id = ? and c.status.createTime < ? and c.channelType.id = ?", employee_id,startDate,channelTypeId);
		long count = 0;
		if(GeneralUtil.isNotNull(list)){
			count = (Long)list.get(0);
		}
		return count;
	}

	@Override
	public List<ChannelCustomerInfo> queryCustomerByEmployeeId(
			String employee_id) throws ServiceException {
		return dbDAO.queryByList(ChannelCustomerInfo.class.getSimpleName(), deleteFlag, versionFlag, " and s.customerOwner =?3 and s.state =?4", new Object[]{employee_id,ContextConstants.STATUS_OF_ONE}, orderBy, null);
	}

	@Override
	public List<ChannelCustomerInfo> queryChannelCustomerListByEmployeeAndChannelType(
			String employee_id, String channelTypeId) throws ServiceException {
		List<ChannelCustomerInfo> list = dbDAO.executeJPQLForQueryEntity("select c from " + ChannelCustomerInfo.class.getName() + " c where c.employeeInfo.id = ? and c.channelType.id = ?" , employee_id,channelTypeId);
		return list;
	}


	@Override
	public List<ChannelCustomerInfo> queryByTopDepartment(String deptPath,
			String versionFlag) throws ServiceException {
		
		List<ChannelCustomerInfo> list = dbDAO.queryByList(ChannelCustomerInfo.class.getSimpleName(), deleteFlag, versionFlag, "and s.state=0 and s.department.id in ("+deptPath+") ", null, orderBy, null);
		return list;
	}

	@Override
	public Pagination queryAllWhereInDepartment(EmployeeInfo employeeInfo,Integer state)
			throws ServiceException {
		Pagination pagination = new Pagination();
		if(GeneralUtil.isNotNull(state)){
			pagination = this.paginationQuery("select c from ChannelCustomerInfo c where c.employeeInfo.id in (select e.id from EmployeeInfo e where e.department.id in (select s.id from DepartmentInfo p , DepartmentInfo s where s.path like concat('%', p.id, '%') and p.id = ?)) and c.state = ? and c.id in (select a.channelCustomerInfo.id from RechargeRecord a )", employeeInfo.getDepartment().getId(),state);
		}else{
			pagination = this.paginationQuery("select c from ChannelCustomerInfo c where c.employeeInfo.id in (select e.id from EmployeeInfo e where e.department.id in (select s.id from DepartmentInfo p , DepartmentInfo s where s.path like concat('%', p.id, '%') and p.id = ?)) and c.id in (select a.channelCustomerInfo.id from RechargeRecord a )", employeeInfo.getDepartment().getId());
		}
		return pagination;
	}

	@Override
	public Pagination queryAll3(String keyword, EmployeeInfo employeeInfo,
			Boolean isPublic, Integer state, String order, Integer pageSize)
			throws ServiceException {
		try {
			List<Object[]> objects = new ArrayList<Object[]>();
			StringBuffer sb = new StringBuffer();
			sb.append("");
			if(GeneralUtil.isNotNull(keyword)){
				sb.append("and (s.name like?3 or s.contactPhone like ?4 ) ");
				objects.add(new Object[]{3,"%"+keyword+"%"});
				objects.add(new Object[]{4,"%"+keyword+"%"});
			}
			if(GeneralUtil.isNotNull(employeeInfo)){
				sb.append("and s.employeeInfo.id2=?5 ");
				objects.add(new Object[]{5,employeeInfo.getId()});
			}
			if(GeneralUtil.isNotNull(state)){
				sb.append("and s.state=?6 ");
				objects.add(new Object[]{6,state});
			}
			if(isPublic){
				sb.append("and s.employeeInfo2.id is null ");
			}
			if(GeneralUtil.isNull(order)){
				order = orderBy;
			}
			return dbDAO.queryByPageModuleNew(ChannelCustomerInfo.class.getSimpleName(), deleteFlag, versionFlag, 
					sb.toString(), objects, order, PaginationContext.getOffset(), pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}