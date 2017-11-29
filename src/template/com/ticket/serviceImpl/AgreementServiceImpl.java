package com.ticket.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.constants.JQueryUploadConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.Agreement;
import com.ticket.pojo.ApplayClass;
import com.ticket.pojo.ChannelCustomerInfo;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.CustomerProtectLog;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.LetterStation;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.StationLetterReadLog;
import com.ticket.pojo.StationLetterSendRecord;
import com.ticket.service.IAgreementService;
import com.ticket.service.IChannelCustomerInfoService;
import com.ticket.service.ICustomerProtectLogService;
import com.ticket.service.IDepartmentInfoService;
import com.ticket.service.IEmployeeInfoService;
import com.ticket.service.ILetterStationService;
import com.ticket.service.IStationLetterReadLogService;
import com.ticket.service.IStationLetterSendRecordService;
import com.ticket.util.DateUtil;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.PaginationContext;

/**
 * 客户合同业务接口实现类
 * @ClassName: IAgreementService   
 * @Description: 提供客户合同操作的增删改查   
 * @author HiSay  
 * @date 2015-11-04 14:49:37
 *
 */
public class AgreementServiceImpl extends BaseServiceImpl<Agreement, String> implements IAgreementService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(AgreementServiceImpl.class);
	@Resource
	private ICustomerProtectLogService customerProtectLogService;
	@Resource
	private IChannelCustomerInfoService channelCustomerInfoService;
	@Resource
	private IDepartmentInfoService departmentInfoService;
	@Resource
	private IEmployeeInfoService employeeInfoService;
	@Resource
	private ILetterStationService letterStationService;
	@Resource
	private IStationLetterReadLogService stationLetterReadLogService;
	@Resource
	private IStationLetterSendRecordService stationLetterSendRecordService;
	
	@Override
	public boolean merge(String id,String name, String applayClassId,Date applyDate,Integer canApplayCount,
			Integer gotCount,String chargeStatus,Date receiveDate,Date signingDate,Date receivablesDate,
			Date returnDate,Date auditDate,Date openAccountDate,Date onlineDate,Double firstRenewMoney,
			Date firstRenewDate,Date lastCheckDate,String customerId,Double amountOfMoney,
			String content,Integer agreementState) throws ServiceException {
		Agreement agreement = dbDAO.queryById(this.getTableNameFromEntity(Agreement.class), id);
		agreement.setName(name);
		agreement.setApplayClass(get(ApplayClass.class, applayClassId));
		agreement.setApplyDate(applyDate);
		agreement.setCanApplayCount(canApplayCount);
		agreement.setGotCount(gotCount);
		agreement.setChargeStatus(DecoderUtil.UtfDecoder(chargeStatus));
		agreement.setReceiveDate(receiveDate);
		agreement.setSigningDate(signingDate);
		agreement.setReceivablesDate(receivablesDate);
		agreement.setReturnDate(returnDate);
		agreement.setAuditDate(auditDate);
		agreement.setOpenAccountDate(openAccountDate);
		agreement.setOnlineDate(onlineDate);
		agreement.setFirstRenewMoney(firstRenewMoney);
		agreement.setFirstRenewDate(firstRenewDate);
		agreement.setLastCheckDate(lastCheckDate);
		agreement.setAmountOfMoney(amountOfMoney);
		agreement.setContent(content);
		agreement.setAgreementState(agreementState);
		CommonEntity status = agreement.getStatus();
		status.setVersionFlag(versionFlag);
		agreement.setStatus(status);
		dbDAO.merge(agreement);
		return true;
	}

	@Override
	public String persist(String name,String applayClassId,EmployeeInfo employeeInfo,String applyDate,
			Integer canApplayCount,Integer gotCount,String chargeStatus,Date receiveDate,
			Date signingDate,Date receivablesDate,Date returnDate,String auditDate,
			Date openAccountDate,Date onlineDate,Double firstRenewMoney,Date firstRenewDate,
			Date lastCheckDate,String customerId,Double amountOfMoney, String content,
			Integer agreementState) throws ServiceException {
		try {
			String result = "true";
			if(employeeInfo == null){
				return "系统管理员不能申请合同";
			}
			if(validateIsExist(customerId,5,1)){
				return "该客户已经成功签约过,合同还在有效期,不能申请合同";
			}
			//是别人的意向客户，申请了保护，但没有申请合同
			CustomerProtectLog log = customerProtectLogService.querybyCustomerId(customerId);
			if(log != null){
				//不是自己的客户，且保护了的
				if(!log.getEmployeeInfo().getId().equals(employeeInfo)){
					Agreement agreement = new Agreement();
					agreement.setName(name);
					agreement.setApplayClass(get(ApplayClass.class, applayClassId));
					if(employeeInfo != null){
						//将合同归属于拥有者
						agreement.setEmployeeInfo(employeeInfo);
					}
					if(GeneralUtil.isNotNull(applyDate)){
						agreement.setApplyDate(DateUtil.parseStringToDate(applyDate, "yyyy-MM-dd HH:mm:ss"));
					}else{
						agreement.setApplyDate(null);
					}
					agreement.setCanApplayCount(canApplayCount);
					agreement.setGotCount(gotCount);
					agreement.setChargeStatus(DecoderUtil.UtfDecoder(chargeStatus));
					agreement.setReceiveDate(receiveDate);
					agreement.setSigningDate(signingDate);
					agreement.setReceivablesDate(receivablesDate);
					agreement.setReturnDate(returnDate);
					if(GeneralUtil.isNotNull(auditDate)){
						agreement.setAuditDate(DateUtil.parseStringToDate(auditDate, "yyyy-MM-dd HH:mm:ss"));
					}
					
					agreement.setOpenAccountDate(openAccountDate);
					agreement.setOnlineDate(onlineDate);
					agreement.setFirstRenewMoney(firstRenewMoney);
					agreement.setFirstRenewDate(firstRenewDate);
					agreement.setLastCheckDate(lastCheckDate);
					agreement.setChannelCustomerInfo(get(ChannelCustomerInfo.class, customerId));
					agreement.setAmountOfMoney(amountOfMoney);
					agreement.setContent(content);
					agreement.setAgreementState(agreementState);
					CommonEntity status = agreement.getStatus();
					status.setVersionFlag(versionFlag);
					agreement.setStatus(status);
					dbDAO.persist(agreement);
					
					ChannelCustomerInfo info = channelCustomerInfoService.queryById(ChannelCustomerInfo.class.getName(), customerId);
					Date date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String today = sdf.format(date);
					LetterStation ls = new LetterStation();
					ls.setOperator_id(null);
					ls.setTitle("系统提示");
					ls.setContent("你保护的"+info.getName()+"的客户，其他员工在"+ today +"申请了他的合同!");
					
					letterStationService.persist(ls);
					
					StationLetterReadLog stationLetterReadLog = new StationLetterReadLog();
					stationLetterReadLog.setIsRead(0);
					stationLetterReadLog.setLetter_id(ls.getId());
					stationLetterReadLog.setObject_id(log.getEmployeeInfo().getId());
					stationLetterReadLog.setReadDate(null);
					stationLetterReadLogService.persist(stationLetterReadLog);
					
					EmployeeInfo employeeInfo2 = employeeInfoService.queryById(EmployeeInfo.class.getName(), log.getEmployeeInfo().getId());
					StationLetterSendRecord stationLetterSendRecord = new StationLetterSendRecord();
					stationLetterSendRecord.setLetter_id(ls.getId());
					stationLetterSendRecord.setObject_id(employeeInfo2.getDepartment().getId());
					stationLetterSendRecord.setObjectFlag("employee");
					stationLetterSendRecord.setOpertator_id(null);
					stationLetterSendRecord.setSendCount(1);
					
					stationLetterSendRecordService.persist(stationLetterSendRecord);
					ls.setIsSend(1);
					letterStationService.merge(ls);
					
					
					//别人申请过该客户的合同
					List<Agreement> agreement2 = queryByEmployeeIdAndName2(employeeInfo, customerId, 1);
					if(agreement2 != null){
						
						ChannelCustomerInfo infos = channelCustomerInfoService.queryById(ChannelCustomerInfo.class.getName(), customerId);
						Date date1 = new Date();
						SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
						String today1 = sdf1.format(date1);
						LetterStation ls1 = new LetterStation();
						ls1.setOperator_id(null);
						ls1.setTitle("系统提示");
						ls1.setContent("你申请的"+infos.getName()+"的合同，其他员工在"+ today1 +"也申请了此合同!");
						
						
						letterStationService.persist(ls1);
						Integer sendCount1 = 0;
						
						Set<String> infoss = new HashSet<String>();
						//向之前申请过这个客户的合同的员工发送站内信，告诉他，有其他员工也对这个客户申请了合同
						for(Agreement agreement3:agreement2){
							EmployeeInfo employeeInfo3 = agreement3.getEmployeeInfo2();
							StationLetterReadLog stationLetterReadLog1 = new StationLetterReadLog();
							stationLetterReadLog1.setIsRead(0);
							stationLetterReadLog1.setLetter_id(ls.getId());
							stationLetterReadLog1.setObject_id(employeeInfo3.getId());
							stationLetterReadLog1.setReadDate(null);
							stationLetterReadLogService.persist(stationLetterReadLog1);
							sendCount1 ++;
							
							infoss.add(employeeInfo3.getDepartment().getId());
						}
						
						for(String department_id:infoss){
							StationLetterSendRecord stationLetterSendRecord2 = new StationLetterSendRecord();
							stationLetterSendRecord2.setLetter_id(ls1.getId());
							stationLetterSendRecord2.setObject_id(department_id);
							stationLetterSendRecord2.setObjectFlag("employee");
							stationLetterSendRecord2.setOpertator_id(null);
							stationLetterSendRecord2.setSendCount(sendCount1);
							
							stationLetterSendRecordService.persist(stationLetterSendRecord2);
						}
						ls.setIsSend(1);
						letterStationService.merge(ls1);
					}
				}
			}
			
			//自己已申请过该客户的合同
			Agreement agreement1 = queryByEmployeeIdAndName(employeeInfo, customerId, 1);
			if(agreement1 != null){
				return "该客户的合同你已经申请过了！";
			}else{
				//别人申请过该客户的合同
				List<Agreement> agreement2 = queryByEmployeeIdAndName2(employeeInfo, customerId, 1);
				if(agreement2 != null){
					Agreement agreement = new Agreement();
					agreement.setName(name);
					agreement.setApplayClass(get(ApplayClass.class, applayClassId));
					if(employeeInfo != null){
						//将合同归属于拥有者
						agreement.setEmployeeInfo2(employeeInfo);
					}
					if(GeneralUtil.isNotNull(applyDate)){
						agreement.setApplyDate(DateUtil.parseStringToDate(applyDate, "yyyy-MM-dd HH:mm:ss"));
					}else{
						agreement.setApplyDate(null);
					}
					agreement.setCanApplayCount(canApplayCount);
					agreement.setGotCount(gotCount);
					agreement.setChargeStatus(DecoderUtil.UtfDecoder(chargeStatus));
					agreement.setReceiveDate(receiveDate);
					agreement.setSigningDate(signingDate);
					agreement.setReceivablesDate(receivablesDate);
					agreement.setReturnDate(returnDate);
					if(GeneralUtil.isNotNull(auditDate)){
						agreement.setAuditDate(DateUtil.parseStringToDate(auditDate, "yyyy-MM-dd HH:mm:ss"));
					}
					
					agreement.setOpenAccountDate(openAccountDate);
					agreement.setOnlineDate(onlineDate);
					agreement.setFirstRenewMoney(firstRenewMoney);
					agreement.setFirstRenewDate(firstRenewDate);
					agreement.setLastCheckDate(lastCheckDate);
					agreement.setChannelCustomerInfo(get(ChannelCustomerInfo.class, customerId));
					agreement.setAmountOfMoney(amountOfMoney);
					agreement.setContent(content);
					agreement.setAgreementState(agreementState);
					CommonEntity status = agreement.getStatus();
					status.setVersionFlag(versionFlag);
					agreement.setStatus(status);
					dbDAO.persist(agreement);
					ChannelCustomerInfo info = channelCustomerInfoService.queryById(ChannelCustomerInfo.class.getName(), customerId);
					Date date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String today = sdf.format(date);
					LetterStation ls = new LetterStation();
					ls.setOperator_id(null);
					ls.setTitle("系统提示");
					ls.setContent("你申请的"+info.getName()+"的合同，其他员工在"+ today +"也申请了此合同!");
					
					
					letterStationService.persist(ls);
					Integer sendCount = 0;
					
					Set<String> infos = new HashSet<String>();
					//向之前申请过这个客户的合同的员工发送站内信，告诉他，有其他员工也对这个客户申请了合同
					for(Agreement agreement3:agreement2){
						EmployeeInfo employeeInfo2 = agreement3.getEmployeeInfo2();
						StationLetterReadLog stationLetterReadLog = new StationLetterReadLog();
						stationLetterReadLog.setIsRead(0);
						stationLetterReadLog.setLetter_id(ls.getId());
						stationLetterReadLog.setObject_id(employeeInfo2.getId());
						stationLetterReadLog.setReadDate(null);
						stationLetterReadLogService.persist(stationLetterReadLog);
						sendCount ++;
						
						infos.add(employeeInfo2.getDepartment().getId());
					}
					
					for(String department_id:infos){
						StationLetterSendRecord stationLetterSendRecord = new StationLetterSendRecord();
						stationLetterSendRecord.setLetter_id(ls.getId());
						stationLetterSendRecord.setObject_id(department_id);
						stationLetterSendRecord.setObjectFlag("employee");
						stationLetterSendRecord.setOpertator_id(null);
						stationLetterSendRecord.setSendCount(sendCount);
						
						stationLetterSendRecordService.persist(stationLetterSendRecord);
					}
					ls.setIsSend(1);
					letterStationService.merge(ls);
					return result;
				}else{
					Agreement agreement = new Agreement();
					agreement.setName(name);
					agreement.setApplayClass(get(ApplayClass.class, applayClassId));
					if(employeeInfo != null){
						//将合同归属于拥有者
						agreement.setEmployeeInfo2(employeeInfo);
					}
					if(GeneralUtil.isNotNull(applyDate)){
						agreement.setApplyDate(DateUtil.parseStringToDate(applyDate, "yyyy-MM-dd HH:mm:ss"));
					}else{
						agreement.setApplyDate(null);
					}
					agreement.setCanApplayCount(canApplayCount);
					agreement.setGotCount(gotCount);
					agreement.setChargeStatus(DecoderUtil.UtfDecoder(chargeStatus));
					agreement.setReceiveDate(receiveDate);
					agreement.setSigningDate(signingDate);
					agreement.setReceivablesDate(receivablesDate);
					agreement.setReturnDate(returnDate);
					if(GeneralUtil.isNotNull(auditDate)){
						agreement.setAuditDate(DateUtil.parseStringToDate(auditDate, "yyyy-MM-dd HH:mm:ss"));
					}
					
					agreement.setOpenAccountDate(openAccountDate);
					agreement.setOnlineDate(onlineDate);
					agreement.setFirstRenewMoney(firstRenewMoney);
					agreement.setFirstRenewDate(firstRenewDate);
					agreement.setLastCheckDate(lastCheckDate);
					agreement.setChannelCustomerInfo(get(ChannelCustomerInfo.class, customerId));
					agreement.setAmountOfMoney(amountOfMoney);
					agreement.setContent(content);
					agreement.setAgreementState(agreementState);
					CommonEntity status = agreement.getStatus();
					status.setVersionFlag(versionFlag);
					agreement.setStatus(status);
					dbDAO.persist(agreement);
					return result;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "操作错误";
		}
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		Agreement agreement = dbDAO.queryById(this.getTableNameFromEntity(Agreement.class), id);
		dbDAO.remove(agreement);
		return true;
	}
	@Override
	public Pagination queryEntityByEmployeeId(String versionFlag, int pageSize,
			String employeeId,Integer agreementState) throws ServiceException {
		String condition="";
		Object[]objects =null;
		
		if(employeeId==null){
		
			if(agreementState==0){
				condition="and s.employeeInfo.id = ?3 and( s.agreementState = 0 or s.agreementState = 2)";
				objects = new Object[]{employeeId};
			}else{
				condition="and s.employeeInfo.id = ?3 and s.agreementState = ?4";
				objects = new Object[]{employeeId,agreementState};
			}
		}else{
			condition="and s.employeeInfo.id = ?3";
			objects = new Object[]{employeeId};
		}
		return dbDAO.queryByPageModule(Agreement.class.getSimpleName(), deleteFlag, versionFlag, condition, objects, orderBy, PaginationContext.getOffset(), pageSize);
	}


	@Override
	public Pagination queryEntityByState(String versionFlag, int pageSize,
			Integer agreementState) throws ServiceException {
		
		String condition="";
		Object[]objects =null;
		if(agreementState==0){
			
			condition="and  s.agreementState = 0 or s.agreementState = 2)";
			objects = new Object[]{};
		}else{
			condition="and s.agreementState = ?3";
			objects = new Object[]{agreementState};
		}
			
		return dbDAO.queryByPageModule(Agreement.class.getSimpleName(), deleteFlag, versionFlag, condition, objects, orderBy, PaginationContext.getOffset(), pageSize);
	}

	@Override
	public boolean merge(String id, String name, String applayClassId,String applyDate, String content) throws ServiceException {
		
		Agreement agreement = dbDAO.queryById(this.getTableNameFromEntity(Agreement.class), id);
		agreement.setName(name);
		agreement.setApplayClass(get(ApplayClass.class, applayClassId));
		if(GeneralUtil.isNotNull(applyDate)){
			agreement.setApplyDate(DateUtil.parseStringToDate(applyDate, "yyyy-MM-dd HH:mm:ss"));
		}else{
			agreement.setApplyDate(null);
		}
		agreement.setContent(content);
		CommonEntity status = agreement.getStatus();
		status.setVersionFlag(versionFlag);
		agreement.setStatus(status);
		dbDAO.merge(agreement);
		return true;
	}

	@Override
	public boolean merge(String id, String name,String auditDate, EmployeeInfo auditEmployee, String chargeStatus,
			Date lastCheckDate, Integer agreementState,String picture)
			throws ServiceException {
		Agreement agreement = dbDAO.queryById(this.getTableNameFromEntity(Agreement.class), id);
		agreement.setChargeStatus(DecoderUtil.UtfDecoder(chargeStatus));
		agreement.setName(name);
		if(GeneralUtil.isNotNull(auditDate)){
			agreement.setAuditDate(DateUtil.parseStringToDate(auditDate, "yyyy-MM-dd HH:mm:ss"));
		}
		if(GeneralUtil.isNull(picture)){
			agreement.setEffective(0);
		}
		agreement.setAuditEmployee(auditEmployee);
		agreement.setLastCheckDate(lastCheckDate);
		agreement.setAgreementState(agreementState);
		CommonEntity status = agreement.getStatus();
		status.setVersionFlag(versionFlag);
		agreement.setStatus(status);
		dbDAO.merge(agreement);
		if(GeneralUtil.isNotNull(picture)){
			this.addAnnex(agreement, agreement.getId(), picture, JQueryUploadConstants.PICTURE_TYPE_DEFAULT, versionFlag);
		}
		return true;
	}

	@Override
	public boolean merge(String id, String contacts, String phone,
			String email, String payway, Date signingDate,
			Double firstRenewMoney, Integer agreementState,String picture,String qhRemark) throws ServiceException {
		Agreement agreement = dbDAO.queryById(this.getTableNameFromEntity(Agreement.class), id);
		if(GeneralUtil.isNotNull(picture)){
			this.addAnnex(agreement, agreement.getId(), picture, 2, versionFlag);
		}
		agreement.setSigningDate(signingDate);
		agreement.setFirstRenewMoney(firstRenewMoney);
		agreement.setContacts(contacts);
		agreement.setPhone(phone);
		agreement.setEmail(email);
		agreement.setPayWay(payway);
		agreement.setQhRemark(qhRemark);
		agreement.setAgreementState(agreementState);
		dbDAO.merge(agreement);

		return true;
	}

	@Override
	public Agreement queryByChannelCustomerInfo(String channelCustomerInfoId,
			Integer agreementState,Integer effective) throws ServiceException {
		try {
			StringBuffer sb = new StringBuffer();
			List<Object[]> objects = new ArrayList<Object[]>();
			sb.append("select a from Agreement a where 1=1 ");
			if(GeneralUtil.isNotNull(channelCustomerInfoId)){
				sb.append("and a.channelCustomeInfo_id=?1 ");
				objects.add(new Object[]{1,channelCustomerInfoId});
			}
			if(GeneralUtil.isNotNull(agreementState)){
				sb.append("and a.agreementState=?2 ");
				objects.add(new Object[]{2,agreementState});
			}
			if(GeneralUtil.isNotNull(effective)){
				sb.append("and a.effective=?3 ");
				objects.add(new Object[]{3,effective});
			}
			return dbDAO.executeJPQLForQuerySingle(sb.toString(),Agreement.class,objects.toArray());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean validateIsExist(String channelCustomerInfoId,
			Integer agreementState,Integer effective) throws ServiceException {
		try {
			Agreement agreement = queryByChannelCustomerInfo(channelCustomerInfoId,agreementState,effective);
			if(agreement != null)
				return true;
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Pagination queryAll(EmployeeInfo employeeInfo,
			String agreementStates, Integer pageSize) throws ServiceException {
		try {
			StringBuffer sb = new StringBuffer();
			List<Object[]> objects = new ArrayList<Object[]>();
			sb.append("");
			if(employeeInfo != null){
				sb.append("and s.employeeInfo.id2=?3 ");
				objects.add(new Object[]{3,employeeInfo.getId()});
			}
			if(GeneralUtil.isNotNull(agreementStates)){
				sb.append("and s.agreementState in (");
				sb.append(agreementStates);
				sb.append(") ");
			}
			return dbDAO.queryByPageModuleNew(Agreement.class.getSimpleName(), deleteFlag, versionFlag,
					sb.toString(), objects, orderBy, PaginationContext.getOffset(), pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean changeAgreementState(String id, Integer agreementState)
			throws ServiceException {
		try {
			Agreement agreement = queryById(Agreement.class.getSimpleName(), id);
			agreement.setAgreementState(agreementState);
			//如果用户选择归档,那么合同将正式属于该用户
			if(agreementState.intValue() == 5){
				agreement.setEmployeeInfo(agreement.getEmployeeInfo2());
			}
			agreement.setReceiveDate(new Date());
			this.merge(agreement);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Agreement queryNewAgreement(String channelCustomerInfoId,
			Integer effective) throws ServiceException {
		try {
			String jpl = "select a from Agreement a where a.channelCustomerInfo_id=? and a.effective=? order by a.status.createTime desc";
			return dbDAO.executeJPQLForQuerySingle(jpl, Agreement.class, new Object[]{channelCustomerInfoId,effective});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String validateIsAdd(String channelCustomerInfoId,EmployeeInfo employeeInfo)
			throws ServiceException {
		try {
			if(employeeInfo == null){
				return "系统管理员不能申请合同!";
			}
			CustomerProtectLog customerProtectLog = customerProtectLogService
						.queryNewLogLess(channelCustomerInfoId, new Date(), 0);
			if(customerProtectLog != null){
				if(!customerProtectLog.getEmployeeInfo().getId().equals(employeeInfo.getId())){
					return "该用户已被保护,保护期的1-3天!";
				}
			}else{
				customerProtectLog = customerProtectLogService
						.queryNewLogThan(channelCustomerInfoId, new Date(), 0);
				if(!customerProtectLog.getEmployeeInfo().getId().equals(employeeInfo.getId())){
					return "该用户已被保护,保护期的4-15天之间,即使签约成功,该客户也不属于您的业绩,不能添加合同!";
				}
			}
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "true";
		}
	}

	@Override
	public Boolean changeEffective(String id) throws ServiceException {
		try {
			Agreement agreement = queryById(Agreement.class.getSimpleName(), id);
			if(agreement != null){
				agreement.setEffective(0);
				this.merge(agreement);
				if(agreement.getAgreementState() == 5){
					//将客户设置成意向客户
					ChannelCustomerInfo channelCustomerInfo = agreement.getChannelCustomerInfo();
					channelCustomerInfo.setState(0);
					channelCustomerInfo.setAgreement(null);
					channelCustomerInfoService.merge(channelCustomerInfo);
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Agreement getNewAgreement(String channelCustomerId,
			String employeeInfoId, String state, Integer effective)
			throws ServiceException {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("select a from Agreement a where a.channelCustomerInfo_id=? and a.effective=? ");
			sb.append("and a.agreementState in ( ");
			sb.append(state);
			sb.append(") ");
			sb.append("and a.employeeInfo2.id=? ");
			sb.append("order by a.status.createTime desc");
			return dbDAO.executeJPQLForQuerySingle(sb.toString(),
					Agreement.class, new Object[]{channelCustomerId,effective,employeeInfoId});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean changeAgreementByHitSingleLog(String channelCustomerInfoId,
			String employeeInfoId) throws ServiceException {
		try {
			List<Agreement> list = queryByCustomerInfo(channelCustomerInfoId, 1);
			if(list != null && list.size() > 0){
				for(Agreement agreement:list){
					if(!agreement.getEmployeeInfo2().getId().equals(employeeInfoId)){
						agreement.getStatus().setDeleteFlag(1);
						agreement.setEffective(0);
						this.merge(agreement);
					}
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Agreement> queryByCustomerInfo(String channelCustomerInfoId,
			Integer effective) throws ServiceException {
		try {
			String jpl= "and s.channelCustomerInfo_id=?3 and s.effective=?4 ";
			return dbDAO.queryByList(Agreement.class.getSimpleName(), deleteFlag,
					versionFlag, jpl, 
					new Object[]{channelCustomerInfoId,effective}, orderBy, null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Agreement queryByChannelCustomerInfoName(EmployeeInfo employeeInfo,
			String name, Integer effective) throws ServiceException {
		try {
			if(employeeInfo == null){
				return null;
			}
			StringBuffer sb = new StringBuffer();
			sb.append("select a from Agreement a,ChannelCustomerInfo o where ");
			sb.append("a.channelCustomerInfo_id=o.id ");
			sb.append("and o.customerName=? ");
			sb.append("and a.employeeInfo2.id=? ");
			sb.append("and a.effective=? ");
			return dbDAO.executeJPQLForQuerySingle(sb.toString(), Agreement.class, name,
					employeeInfo.getId(),effective);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Agreement> queryListByChannelCustomerInfoName(
			EmployeeInfo employeeInfo, String name, Integer effective)
			throws ServiceException {
		try {
			if(employeeInfo == null){
				return null;
			}
			StringBuffer sb = new StringBuffer();
			sb.append("select a from Agreement a,ChannelCustomerInfo o where ");
			sb.append("a.channelCustomerInfo_id=o.id ");
			sb.append("and o.customerName=? ");
			sb.append("and a.employeeInfo2.id=? ");
			sb.append("and a.effective=? ");
			return dbDAO.executeJPQLForQuery(sb.toString(), Agreement.class,  name,
					employeeInfo.getId(),effective);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String addByWap(EmployeeInfo employeeInfo, String name,
			String remark) throws ServiceException {
		try {
			Agreement agreement = queryByChannelCustomerInfoName(employeeInfo, name, 1);
			if(agreement != null){
				return "true";
			}
			ChannelCustomerInfo channelCustomerInfo = channelCustomerInfoService.IsExit(name);
			if(channelCustomerInfo == null){
				return "您没有该渠道客户,请先添加渠道客户";
			}
			agreement = new Agreement();
			agreement.setRemark(remark);
			agreement.setChannelCustomerInfo(channelCustomerInfo);
			agreement.setEmployeeInfo(employeeInfo);
			agreement.setApplyDate(new Date());
			this.persist(agreement);
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "操作错误";
		}
	}

	@Override
	public Boolean fafang(String id, Integer secondAuditState,String secondAuditRemark, Integer state,String employeeInfo_id)
			throws ServiceException {
		try {
			Agreement agreement = queryById(Agreement.class.getSimpleName(), id);
			agreement.setSecondAuditRemark(secondAuditRemark);
			agreement.setSecondAuditState(secondAuditState);
			agreement.setReturnDate(new Date());
			if(secondAuditState == 1){
				agreement.setAgreementState(state);
				//客户签约成功,将客户设置成渠道客户
				ChannelCustomerInfo channelCustomerInfo = agreement.getChannelCustomerInfo();
				channelCustomerInfo.setState(1);
				channelCustomerInfo.setAgreement(agreement);
				channelCustomerInfo.setCustomerOwner(agreement.getChannelCustomerInfo());
				channelCustomerInfo.setEmployeeInfo2(get(EmployeeInfo.class, employeeInfo_id));
				channelCustomerInfoService.merge(channelCustomerInfo);
				
				EmployeeInfo employeeInfo = employeeInfoService.queryById(EmployeeInfo.class.getName(), employeeInfo_id);
				//如果其他人也申请了这个客户的合同，但没有申请保护，告诉其他人这个客户已经被签了，那么他的合同自动取消
				List<Agreement> agreement2 = this.queryByEmployeeIdAndName2(employeeInfo, channelCustomerInfo.getId(), 1);
				if(agreement2 != null){
					LetterStation ls = new LetterStation();
					ls.setOperator_id(null);
					ls.setTitle("系统提示");
					ls.setContent("你申请的"+channelCustomerInfo.getName()+"的合同，已经被其他员工签回！你对该客户申请的合同已自动取消！请确认！");
					
					
					letterStationService.persist(ls);
					Integer sendCount = 0;
					
					Set<String> infos = new HashSet<String>();
					//这些合同自动取消
					for(Agreement agreement3:agreement2){
						boolean a = this.changeEffective(agreement3.getId());
						if(a){
							//向他发送站内信，告诉他，他的合同已自动取消
							EmployeeInfo employeeInfo2 = agreement3.getEmployeeInfo2();
							StationLetterReadLog stationLetterReadLog = new StationLetterReadLog();
							stationLetterReadLog.setIsRead(0);
							stationLetterReadLog.setLetter_id(ls.getId());
							stationLetterReadLog.setObject_id(employeeInfo2.getId());
							stationLetterReadLog.setReadDate(null);
							stationLetterReadLogService.persist(stationLetterReadLog);
							sendCount ++;
							
							infos.add(employeeInfo2.getDepartment().getId());
						}
					}
					for(String department_id:infos){
						StationLetterSendRecord stationLetterSendRecord = new StationLetterSendRecord();
						stationLetterSendRecord.setLetter_id(ls.getId());
						stationLetterSendRecord.setObject_id(department_id);
						stationLetterSendRecord.setObjectFlag("employee");
						stationLetterSendRecord.setOpertator_id(null);
						stationLetterSendRecord.setSendCount(sendCount);
						
						stationLetterSendRecordService.persist(stationLetterSendRecord);
					}
					ls.setIsSend(1);
					letterStationService.merge(ls);
				}
				
				CustomerProtectLog cpl = customerProtectLogService.querybyCustomerId(agreement.getChannelCustomerInfo().getId());
				if(cpl != null){
					
					customerProtectLogService.changeExpire(cpl.getId());
				}
			}else{
				agreement.setAgreementState(2);
			}
			this.merge(agreement);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Agreement> queryByStateAndEffective(Integer state,
			Integer effective,Integer size) throws ServiceException {
		try {
			List<Object[]> list = new ArrayList<Object[]>();
			StringBuffer sb = new StringBuffer();
			sb.append("");
			if(GeneralUtil.isNotNull(state)){
				sb.append("and s.agreementState=?3 ");
				list.add(new Object[]{3,state});
			}
			if(GeneralUtil.isNotNull(effective)){
				sb.append("and s.effective=?4 ");
				list.add(new Object[]{4,effective});
			}
			return dbDAO.queryByListNew(Agreement.class.getSimpleName(), deleteFlag, versionFlag,
					sb.toString(), list, orderBy,size);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public long allCount(Date startDate, Date endDate,EmployeeInfo employeeInfo,int type) {
		
		if(startDate == null){
			
			startDate = new Date(0);
		}
		if(endDate == null){
			
			endDate = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
		}else{
			
			endDate = new Date(endDate.getTime() + 24 * 60 * 60 * 1000 - 1);
		}
		Long count = 0L;
		if(GeneralUtil.isNotNull(employeeInfo) && type == 1){
			count = dbDAO.executeJPQLForQuerySingle("select count(a) from Agreement a where a.applyDate between ? and ? and a.employeeInfo2.id = ?",  Long.class, startDate, endDate,employeeInfo.getId());
		}else if(GeneralUtil.isNotNull(employeeInfo) && type == 2){
			count = dbDAO.executeJPQLForQuerySingle("select count(a) from Agreement a,EmployeeInfo e where a.employeeInfo2.id = e.id and e.department.id = ? and a.applyDate between ? and ? ", Long.class, employeeInfo.getDepartment().getId(),startDate, endDate);
		}else{
			count = dbDAO.executeJPQLForQuerySingle(
					"select count(t) from " + Agreement.class.getName() + " t where t.applyDate between ? and ?", Long.class, startDate, endDate);
		}
		return count == null ? 0 : count;
	}

	@Override
	public long approvalCount(Date startDate, Date endDate,EmployeeInfo employeeInfo,int type) {
		
		if(startDate == null){
			
			startDate = new Date(0);
		}
		if(endDate == null){
			
			endDate = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
		}else{
			
			endDate = new Date(endDate.getTime() + 24 * 60 * 60 * 1000 - 1);
		}
		Long count = 0L;
		if(GeneralUtil.isNotNull(employeeInfo) && type == 1){
			count = dbDAO.executeJPQLForQuerySingle(
					"select count(t) from " + Agreement.class.getName() + " t where t.applyDate between ? and ? and t.agreementState in (1,2) and t.employeeInfo2.id = ?", Long.class, startDate, endDate,employeeInfo.getId());
		}else if(GeneralUtil.isNotNull(employeeInfo) && type == 2){
			count = dbDAO.executeJPQLForQuerySingle(
					"select count(t) from Agreement t,EmployeeInfo e where t.employeeInfo2.id = e.id and e.department.id = ? and t.applyDate between ? and ? and t.agreementState in (1,2)", Long.class, employeeInfo.getDepartment().getId(),startDate, endDate);
		}else{
			count = dbDAO.executeJPQLForQuerySingle(
					"select count(t) from " + Agreement.class.getName() + " t where t.applyDate between ? and ? and t.agreementState in (1,2)", Long.class, startDate, endDate);
		}
		return count == null ? 0 : count;
	}

	@Override
	public long issueCount(Date startDate, Date endDate,EmployeeInfo employeeInfo,int type) {
		
		if(startDate == null){
			
			startDate = new Date(0);
		}
		if(endDate == null){
			
			endDate = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
		}else{
			
			endDate = new Date(endDate.getTime() + 24 * 60 * 60 * 1000 - 1);
		}
		Long count = 0L;
		if(GeneralUtil.isNotNull(employeeInfo) && type == 1){
			count = dbDAO.executeJPQLForQuerySingle(
					"select count(t) from " + Agreement.class.getName() + " t where t.employeeInfo2.id = ? and t.applyDate between ? and ? and t.agreementState = 4", Long.class,employeeInfo.getId(), startDate, endDate);
		}else if(GeneralUtil.isNotNull(employeeInfo) && type == 2){
			count = dbDAO.executeJPQLForQuerySingle(
					"select count(t) from Agreement t,EmployeeInfo e where t.employeeInfo2.id = e.id and e.department.id = ? and t.applyDate between ? and ? and t.agreementState = 4 ", Long.class, employeeInfo.getDepartment().getId(),startDate, endDate);
		}else{
			count = dbDAO.executeJPQLForQuerySingle(
					"select count(t) from " + Agreement.class.getName() + " t where t.applyDate between ? and ? and t.agreementState = 4", Long.class, startDate, endDate);
		}
		return count == null ? 0 : count;
	}

	@Override
	public long signBackCount(Date startDate, Date endDate,EmployeeInfo employeeInfo,int type) {
		
		if(startDate == null){
			
			startDate = new Date(0);
		}
		if(endDate == null){
			
			endDate = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
		}else{
			
			endDate = new Date(endDate.getTime() + 24 * 60 * 60 * 1000 - 1);
		}
		Long count = 0L;
		if(GeneralUtil.isNotNull(employeeInfo) && type == 1){
			count = dbDAO.executeJPQLForQuerySingle(
					"select count(t) from " + Agreement.class.getName() + " t where t.applyDate between ? and ? and t.agreementState = 3 and t.employeeInfo2.id = ?", Long.class, startDate, endDate,employeeInfo.getId());
		}else if(GeneralUtil.isNotNull(employeeInfo) && type == 2){
			count = dbDAO.executeJPQLForQuerySingle(
					"select count(t) from Agreement t,EmployeeInfo e where t.employeeInfo2.id = e.id and e.department.id = ? t.applyDate between ? and ? and t.agreementState = 3", Long.class,employeeInfo.getDepartment().getId(), startDate, endDate);
		}else{
			count = dbDAO.executeJPQLForQuerySingle(
					"select count(t) from " + Agreement.class.getName() + " t where t.applyDate between ? and ? and t.agreementState = 3", Long.class, startDate, endDate);
		}
		return count == null ? 0 : count;
	}

	@Override
	public long archiveCount(Date startDate, Date endDate,EmployeeInfo employeeInfo,int type) {
		
		if(startDate == null){
			
			startDate = new Date(0);
		}
		if(endDate == null){
			
			endDate = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
		}else{
			
			endDate = new Date(endDate.getTime() + 24 * 60 * 60 * 1000 - 1);
		}
		Long count = 0L;
		if(GeneralUtil.isNotNull(employeeInfo) && type == 1){
			count = dbDAO.executeJPQLForQuerySingle(
					"select count(t) from " + Agreement.class.getName() + " t where t.applyDate between ? and ? and t.agreementState = 5 and t.employeeInfo2.id = ?", Long.class, startDate, endDate,employeeInfo.getId());
		}else if(GeneralUtil.isNotNull(employeeInfo) && type == 2){
			count = dbDAO.executeJPQLForQuerySingle(
					"select count(t) from Agreement t,EmployeeInfo e where t.employeeInfo2.id = e.id and e.department.id = ? t.applyDate between ? and ? and t.agreementState = 5", Long.class,employeeInfo.getDepartment().getId(), startDate, endDate);
		}else{
			count = dbDAO.executeJPQLForQuerySingle(
					"select count(t) from " + Agreement.class.getName() + " t where t.applyDate between ? and ? and t.agreementState = 5", Long.class, startDate, endDate);
		}
		return count == null ? 0 : count;
	}

	@Override
	public List<Agreement> allList(Date startDate, Date endDate,EmployeeInfo employeeInfo,int type) {
		
		if(startDate == null){
			
			startDate = new Date(0);
		}
		if(endDate == null){
			
			endDate = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
		}else{
			
			endDate = new Date(endDate.getTime() + 24 * 60 * 60 * 1000 - 1);
		}
		List<Agreement> count = new ArrayList<Agreement>();
		if(GeneralUtil.isNotNull(employeeInfo) && type == 1){
			count = dbDAO.executeJPQLForQuery(
					"select t from " + Agreement.class.getName() + " t where t.applyDate between ? and ? and t.employeeInfo2.id = ?", Agreement.class, startDate, endDate,employeeInfo.getId());
		}else if(GeneralUtil.isNotNull(employeeInfo) && type == 2){
			count = dbDAO.executeJPQLForQuery(
					"select t from Agreement t,EmployeeInfo e where t.employeeInfo2.id = e.id and e.department.id = ? and t.applyDate between ? and ?", Agreement.class, employeeInfo.getDepartment().getId(),startDate, endDate);
		}else{
			count = dbDAO.executeJPQLForQuery(
					"select t from " + Agreement.class.getName() + " t where t.applyDate between ? and ?", Agreement.class, startDate, endDate);
		}
		return count;
	}

	@Override
	public List<Agreement> approvalList(Date startDate, Date endDate,EmployeeInfo employeeInfo,int type) {
		
		if(startDate == null){
			
			startDate = new Date(0);
		}
		if(endDate == null){
			
			endDate = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
		}else{
			
			endDate = new Date(endDate.getTime() + 24 * 60 * 60 * 1000 - 1);
		}
		List<Agreement> count = new ArrayList<Agreement>();
		if(GeneralUtil.isNotNull(employeeInfo) && type == 1){
			count = dbDAO.executeJPQLForQuery(
					"select t from " + Agreement.class.getName() + " t where t.applyDate between ? and ? and t.agreementState in (1,2) and t.employeeInfo2.id = ?", Agreement.class, startDate, endDate,employeeInfo.getId());
		}else if(GeneralUtil.isNotNull(employeeInfo) && type == 2){
			count = dbDAO.executeJPQLForQuery(
					"select t from Agreement t,EmployeeInfo e where t.employeeInfo2.id = e.id and e.department.id = ? and t.applyDate between ? and ? and t.agreementState in (1,2)", Agreement.class,employeeInfo.getDepartment().getId(), startDate, endDate);
		}else{
			count = dbDAO.executeJPQLForQuery(
					"select t from " + Agreement.class.getName() + " t where t.applyDate between ? and ? and t.agreementState in (1,2)", Agreement.class, startDate, endDate);
		}
		return count;
	}

	@Override
	public List<Agreement> issueList(Date startDate, Date endDate,EmployeeInfo employeeInfo,int type) {
		
		if(startDate == null){
			
			startDate = new Date(0);
		}
		if(endDate == null){
			
			endDate = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
		}else{
			
			endDate = new Date(endDate.getTime() + 24 * 60 * 60 * 1000 - 1);
		}
		List<Agreement> count = new ArrayList<Agreement>();
		if(GeneralUtil.isNotNull(employeeInfo) && type == 1){
			count = dbDAO.executeJPQLForQuery(
					"select t from " + Agreement.class.getName() + " t where t.applyDate between ? and ? and t.agreementState = 4 and t.employeeInfo2.id = ?", Agreement.class, startDate, endDate,employeeInfo.getId());
		}else if(GeneralUtil.isNotNull(employeeInfo) && type == 2){
			count = dbDAO.executeJPQLForQuery(
					"select t from Agreement t,EmployeeInfo e where t.employeeInfo2.id = e.id and e.department.id = ? and t.applyDate between ? and ? and t.agreementState = 4", Agreement.class,employeeInfo.getDepartment().getId(), startDate, endDate);
		}else{
			count = dbDAO.executeJPQLForQuery(
					"select t from " + Agreement.class.getName() + " t where t.applyDate between ? and ? and t.agreementState = 4", Agreement.class, startDate, endDate);
		}
		return count;
	}

	@Override
	public List<Agreement> signBackList(Date startDate, Date endDate,EmployeeInfo employeeInfo,int type) {
		
		if(startDate == null){
			
			startDate = new Date(0);
		}
		if(endDate == null){
			
			endDate = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
		}else{
			
			endDate = new Date(endDate.getTime() + 24 * 60 * 60 * 1000 - 1);
		}
		List<Agreement> count = new ArrayList<Agreement>();
		if(GeneralUtil.isNotNull(employeeInfo) && type == 1){
			count = dbDAO.executeJPQLForQuery(
					"select t from " + Agreement.class.getName() + " t where t.applyDate between ? and ? and t.agreementState = 3 and t.employeeInfo2.id = ?", Agreement.class, startDate, endDate,employeeInfo.getId());
		}else if(GeneralUtil.isNotNull(employeeInfo) && type == 2){
			count = dbDAO.executeJPQLForQuery(
					"select t from Agreement t,EmployeeInfo e where t.employeeInfo2.id = e.id and e.department.id = ? and t.applyDate between ? and ? and t.agreementState = 3", Agreement.class,employeeInfo.getDepartment().getId(), startDate, endDate);
		}else{
			count = dbDAO.executeJPQLForQuery(
					"select t from " + Agreement.class.getName() + " t where t.applyDate between ? and ? and t.agreementState = 3", Agreement.class, startDate, endDate);
		}
		return count;
	}

	@Override
	public List<Agreement> archiveList(Date startDate, Date endDate,EmployeeInfo employeeInfo,int type) {
		
		if(startDate == null){
			
			startDate = new Date(0);
		}
		if(endDate == null){
			
			endDate = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
		}else{
			
			endDate = new Date(endDate.getTime() + 24 * 60 * 60 * 1000 - 1);
		}
		List<Agreement> count = new ArrayList<Agreement>();
		if(GeneralUtil.isNotNull(employeeInfo) && type == 1){
			count = dbDAO.executeJPQLForQuery(
					"select t from " + Agreement.class.getName() + " t where t.applyDate between ? and ? and t.agreementState = 5 and t.employeeInfo2.id = ?", Agreement.class, startDate, endDate,employeeInfo.getId());
		}else if(GeneralUtil.isNotNull(employeeInfo) && type == 2){
			count = dbDAO.executeJPQLForQuery(
					"select t from Agreement t,EmployeeInfo e where t.employeeInfo2.id = e.id and e.department.id = ? and t.applyDate between ? and ? and t.agreementState = 5", Agreement.class, employeeInfo.getDepartment().getId(),startDate, endDate);
		}else{
			count = dbDAO.executeJPQLForQuery(
					"select t from " + Agreement.class.getName() + " t where t.applyDate between ? and ? and t.agreementState = 5", Agreement.class, startDate, endDate);
		}
		return count;
	}

	@Override
	public Pagination queryAllWhereIsDepartMent(EmployeeInfo employeeInfo,
			String agreementStates, Integer pageSize) throws ServiceException {
		Pagination pagination = new Pagination();
		pagination = this.paginationQuery("select a from Agreement a,EmployeeInfo e where a.employeeInfo2.id = e.id and e.department.id in (select s.id from DepartmentInfo p , DepartmentInfo s where s.path like concat('%', p.id, '%') and p.id = ? )) and a.agreementState in ("+agreementStates+")", employeeInfo.getDepartment().getId());
		return pagination;
	}

	@Override
	public Agreement queryByEmployeeIdAndName(EmployeeInfo employeeInfo,
			String customerId, Integer eff) throws ServiceException {
		Agreement agreement = dbDAO.executeJPQLForQuerySingle("select c from Agreement c where c.employeeInfo2.id = ? and c.channelCustomerInfo_id = ? and c.effective = ?", Agreement.class,employeeInfo.getId(),customerId,eff);
		return agreement;
	}

	@Override
	public List<Agreement> queryByEmployeeIdAndName2(EmployeeInfo employeeInfo,
			String customerId, Integer eff) throws ServiceException {
		List<Agreement> agreement = dbDAO.executeJPQLForQueryEntity("select c from Agreement c where c.employeeInfo2.id != ? and c.channelCustomerInfo_id = ? and c.effective = ?", employeeInfo.getId(),customerId,eff);
		return agreement;
	}

	@Override
	public Agreement querybyCustomerId(String customerId) throws ServiceException {
		Agreement agreement = dbDAO.executeJPQLForQuerySingle("select c from " + Agreement.class.getName() + " c where c.channelCustomerInfo_id = ?", Agreement.class,customerId);
		return agreement;
	}
	
}