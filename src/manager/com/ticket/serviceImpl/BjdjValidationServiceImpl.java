package com.ticket.serviceImpl;


import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.enumer.BjdjAppointmentType;
import com.ticket.enumer.BjdjOrderType;
import com.ticket.enumer.ReportRegion;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.AirportEmployee;
import com.ticket.pojo.BjdjAppointment;
import com.ticket.pojo.BjdjDispatch;
import com.ticket.pojo.BjdjHall;
import com.ticket.pojo.BjdjOrder;
import com.ticket.pojo.BjdjPage;
import com.ticket.pojo.BjdjServiceCode;
import com.ticket.pojo.BjdjServicePackage;
import com.ticket.pojo.BjdjValidation;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.Report;
import com.ticket.pojo.SystemDictionary;
import com.ticket.pojo.ValidationDetailReport;
import com.ticket.pojo.ValidationReport;
import com.ticket.service.IBjdjServiceCodeService;
import com.ticket.service.IBjdjValidationService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.util.DateUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.ResourceUtil;
import com.ticket.util.SmsUtil;

/**
 * 便捷登机验证表业务接口实现类
 * @ClassName: IBjdjValidationService   
 * @Description: 提供便捷登机验证表操作的增删改查   
 * @author HiSay  
 * @date 2015-11-23 22:52:42
 *
 */
public class BjdjValidationServiceImpl extends BaseServiceImpl<BjdjValidation, String> implements IBjdjValidationService {

	private static final Log log = LogFactory.getLog(BjdjValidationServiceImpl.class);
	@Resource
	private IBjdjServiceCodeService serviceCodeService;
	@Resource
	private ISystemDictionaryService dictionaryService;
	

	@Override
	public BjdjValidation persist(BjdjAppointment appointment,String hallId,Date time,String employee_id,boolean passed) throws ServiceException {
		
		BjdjHall hallObj = get(BjdjHall.class, hallId);
		AirportEmployee employee = get(AirportEmployee.class, employee_id);
		return persist(appointment, hallObj, time, employee, passed);
	}
	
//	@Override
//	public BjdjValidation persist(String serviceCode, String hallId, Date time,String employee_id,boolean passed) throws ServiceException {
//		
//		BjdjServiceCode serviceCodeObj = serviceCodeService.getByCode(serviceCode);
//		return persist(serviceCodeObj, new BjdjHall(hallId), time, get(AirportEmployee.class, employee_id), passed);
//	}
	
	@Override
	public BjdjValidation persist(BjdjAppointment appointment,BjdjHall hall,Date time,AirportEmployee employee,boolean passed) throws ServiceException {
		
		if(appointment == null){
			
			throw new ServiceException(ResourceUtil.getText("serviceCode.appointment.null"));
		}
		
		//服务码类型是否正确
		BjdjServiceCode serviceCodeObj = appointment.getServiceCode();
		if(serviceCodeObj.getOrder() != null && serviceCodeObj.getOrder().getType() == BjdjOrderType.electromobile){
			
			throw new ServiceException(ResourceUtil.getText("validation.type.error.electromobile"));
		}
		
		//检查是否已经验证过
		if(appointment.getValidation() != null){
			
			throw new ServiceException(ResourceUtil.getText("validation.passed.exist"));
		}
		
		//最早验证时间为航班起飞前5小时，最晚是距航班起飞时间90分钟
		Calendar firstDate = Calendar.getInstance();
		firstDate.setTime(appointment.getUseTime());
		firstDate.add(Calendar.HOUR_OF_DAY, -5);
		if(/*System.currentTimeMillis() > appointment.getUseTime().getTime() - 90*60*1000
				|| */System.currentTimeMillis() < firstDate.getTime().getTime()){
			
			throw new ServiceException(ResourceUtil.getText("serviceCode.validation.time"));
		}
		
		BjdjValidation bjdjValidation = new BjdjValidation();
		bjdjValidation.setTime(time);
		bjdjValidation.setEmployee(employee);
		bjdjValidation.setPassed(passed);
		bjdjValidation.setAppointment(appointment);
		bjdjValidation.setType(BjdjOrderType.bjdj);
		dbDAO.persist(bjdjValidation);
		
		//设置服务码状态
		BjdjServiceCode serviceCode = appointment.getServiceCode();
		serviceCode.setState(dictionaryService.getByName("validated"));
		serviceCodeService.merge(serviceCode);
		
		//修改
		BjdjOrder order = serviceCode.getOrder();
		BjdjServicePackage bjdjServicePackage = order.getPackageType();
		BjdjPage bjdjPage = bjdjServicePackage.getBjdjPage();
		SystemDictionary parent = bjdjPage.getSmsTemplate();
		SystemDictionary dictionary = dictionaryService.queryByParentAndName(parent, "serviceCode.validation.success");
		
		//修改
		//发送短信
//		SystemDictionary dictionary = dictionaryService.getByName("serviceCode.validation.success");
		if(dictionary != null && dictionary.getDescription() != null){
			
			SmsUtil.sendSms(serviceCode.getMember().getPhone(), MessageFormat.format(GeneralUtil.removeHtmlTags(dictionary.getDescription()), serviceCode.getCode()));
		}
		return bjdjValidation;
	}
	
//	@Override
//	public BjdjValidation persist(BjdjServiceCode serviceCodeObj, BjdjHall hall, Date time,AirportEmployee employee,boolean passed) throws ServiceException {
//		
//		BjdjValidation bjdjValidation = new BjdjValidation();
//		bjdjValidation.setTime(time);
//		bjdjValidation.setEmployee(employee);
//		bjdjValidation.setPassed(passed);
//		bjdjValidation.setType(BjdjOrderType.electromobile);
//		bjdjValidation.setServiceCode(serviceCodeObj);
//		dbDAO.persist(bjdjValidation);
//		
//		//设置服务码状态
//		serviceCodeObj.setState(dictionaryService.getByName("validated"));
//		serviceCodeService.merge(serviceCodeObj);
//		
//		return bjdjValidation;
//	}
	
	@Override
	public BjdjValidation merge(String id,BjdjAppointment appointment,Date time,String employee_id,boolean passed) throws ServiceException {
		
		return merge(employee_id, appointment, time, get(AirportEmployee.class, employee_id), passed);
	}
	
	@Override
	public BjdjValidation merge(String id,BjdjAppointment appointment,Date time,AirportEmployee employee,boolean passed) throws ServiceException {
		
		BjdjValidation bjdjValidation = dbDAO.queryById(this.getTableNameFromEntity(BjdjValidation.class), id);
		bjdjValidation.setTime(time);
		bjdjValidation.setEmployee(employee);
		bjdjValidation.setPassed(passed);
		bjdjValidation.setAppointment(appointment);
		dbDAO.merge(bjdjValidation);
		return bjdjValidation;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		
		batchRealDelete(BjdjValidation.class, id);
		return true;
	}

	@Override
	public boolean isPassedValidatation(String appointment_id) {
		
		return isPassedValidatation(new BjdjAppointment(appointment_id));
	}

	@Override
	public boolean isPassedValidatation(BjdjAppointment appointment) {
		
		Long count = (Long)dbDAO.executeJPQLForQuerySingle(
				"select count(*) from " + BjdjValidation.class.getName() + " where appointment=? and passed=true", appointment);
		return count > 0 ? true : false;
	}

	@Override
	public BjdjDispatch getDispatch(BjdjValidation bjdjValidation) {
		
		String tableName = BjdjDispatch.class.getName();
		BjdjDispatch list = dbDAO.executeJPQLForQuerySingle("select t from " + tableName + " t where validation=?", BjdjDispatch.class, bjdjValidation);
		return list;
	}

	@Override
	public BjdjValidation findByServiceCode(String serviceCodeId) {
		Object object = dbDAO.executeJPQLForQuerySingle("select c from " + BjdjValidation.class.getName() + " c where c.serviceCode.id = ?", serviceCodeId);
		BjdjValidation bjdjValidation = (BjdjValidation)object;
		return bjdjValidation;
	}

	@Override
	public BjdjValidation findByServiceCode1(String serviceCode) {
		Object object = dbDAO.executeJPQLForQuerySingle("select c from " + BjdjValidation.class.getName() + " c where c.appointment.serviceCode.code = ?", serviceCode);
		BjdjValidation bjdjValidation = (BjdjValidation)object;
		return bjdjValidation;
	}

	@Override
	public List<BjdjValidation> findInIds(String idsValue) {
		idsValue = GeneralUtil.convertIdsValueToString(idsValue, ",") ;
		StringBuffer sb = new StringBuffer("select c from ");
		sb.append(BjdjValidation.class.getName());
		sb.append(" c where c.id in(");
		sb.append(idsValue);
		sb.append(")");
		List<BjdjValidation> list = dbDAO.executeJPQLForQueryEntity(sb.toString());
		return list;
	}

	@Override
	public List<BjdjValidation> getByTimes(Date startTime, Date endTime) {
		List<BjdjValidation> bjdjValidations = dbDAO.executeJPQLForQueryEntity(
				"select c from " + BjdjValidation.class.getName() + " c"
				+ " where c.appointment.hall is null and c.status.createTime >= ? and c.status.createTime <= ?", startTime,endTime);
		return bjdjValidations;
	}
	
	@Override
	public List<BjdjValidation> getEnterHallByTimes(Date startTime, Date endTime) {
		List<BjdjValidation> bjdjValidations = dbDAO.executeJPQLForQueryEntity(
				"select c from " + BjdjValidation.class.getName() + " c"
						+ " where c.appointment.hall is not null and c.status.createTime >= ? and c.status.createTime <= ?", startTime, endTime);
		return bjdjValidations;
	}
	
	@Override
	public Pagination queryEntityByAdmin(String serviceCode, String flightNumber, Date startTime, Date endTime, Integer pageSize)
			throws ServiceException {
		try {
			//先获取当前的实体名称
			StringBuffer whereCondition = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			whereCondition.append(" where t.type=?");
			params.add(BjdjOrderType.bjdj);
			
			if(GeneralUtil.isNotNull(serviceCode)){
				
				whereCondition.append(" and t.appointment.serviceCode.code=?");
				params.add(serviceCode);
			}
			if(GeneralUtil.isNotNull(flightNumber)){
				
				whereCondition.append(" and t.appointment.flightNo=?");
				params.add(flightNumber);
			}
			if(startTime != null){
				
//				whereCondition.append(" and YEAR(t.appointment.useTime)=? and MONTH(t.appointment.useTime)=? and DAY(t.appointment.useTime)=?");
				whereCondition.append(" and t.appointment.useTime>?");
//				Calendar c = Calendar.getInstance();
//				c.setTime(startTime);
//				params.add(c.get(Calendar.YEAR));
//				params.add(c.get(Calendar.MONTH) + 1);
//				params.add(c.get(Calendar.DAY_OF_MONTH));
				params.add(startTime);
			}
			
			if(GeneralUtil.isNotNull(endTime)){
				whereCondition.append(" and t.appointment.useTime<?");
				params.add(endTime);
			}
			
			return paginationQuery("select t from " + BjdjValidation.class.getName() + " t" + whereCondition.toString() + " order by t.status.createTime desc", params.toArray());
		} catch(Exception e) {
			log.info("查询实体列表出错: " + e.fillInStackTrace());
			return null;
		}
	}
	
	@Override
	public Pagination queryEntityByAdmin(BjdjHall bjdjHall, String serviceCode,
			String flightNumber, Date startTime, Date endTime, Integer pageSize)
			throws ServiceException {
		try {
			//先获取当前的实体名称
			StringBuffer whereCondition = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			whereCondition.append(" where t.type=?");
			params.add(BjdjOrderType.bjdj);
			whereCondition.append(" and t.appointment.hall.id = ? ");
			params.add(bjdjHall.getId());
			if(GeneralUtil.isNotNull(serviceCode)){
				
				whereCondition.append(" and t.appointment.serviceCode.code=?");
				params.add(serviceCode);
			}
			if(GeneralUtil.isNotNull(flightNumber)){
				
				whereCondition.append(" and t.appointment.flightNo=?");
				params.add(flightNumber);
			}
			if(startTime != null){
				
				whereCondition.append(" and t.appointment.useTime>?");
				params.add(startTime);
			}
			
			if(GeneralUtil.isNotNull(endTime)){
				whereCondition.append(" and t.appointment.useTime<?");
				params.add(endTime);
			}
			
			return paginationQuery("select t from " + BjdjValidation.class.getName() + " t" + whereCondition.toString() + " order by t.status.createTime desc", params.toArray());
		} catch(Exception e) {
			log.info("查询实体列表出错: " + e.fillInStackTrace());
			return null;
		}
	}

	@Override
	public long queryAllCount() throws ServiceException {
		List<Object> list = dbDAO.executeJPQLForQuery("select count(c) from " + BjdjValidation.class.getName() + " c");
		long count = 0;
		if(GeneralUtil.isNotNull(list)){
			count = (Long)list.get(0);
		}
		return count;
	}
	

	@Override
	public List<Report> reportTogether(ReportRegion region) {
		
		//查询开始、结束时间
		Date startDate = null, endDate = null;
		//环比开始、结束时间
		Date rollStartDate = null, rollEndDate = null;
		//对比开始、结束时间
		Date sameStartDate = null, sameEndDate = null;
		
		if(region == ReportRegion.thisWeek){
			
			//本周
			Calendar thisRegion = Calendar.getInstance();
			endDate = DateUtil.getDayEnd(thisRegion.getTime());
			thisRegion.set(Calendar.DAY_OF_WEEK, 2);
			startDate = DateUtil.getDayStart(thisRegion.getTime());
			
			Calendar prevMonthWeek = Calendar.getInstance();
			prevMonthWeek.setTime(startDate);
			prevMonthWeek.add(Calendar.WEEK_OF_YEAR, -1);
			
			//上周
			thisRegion.add(Calendar.DAY_OF_MONTH, -1);
			rollEndDate = DateUtil.getDayEnd(thisRegion.getTime());
			thisRegion.set(Calendar.DAY_OF_MONTH, 2);
			rollStartDate = DateUtil.getDayStart(thisRegion.getTime());
			
			//上个月的本周
			sameStartDate = DateUtil.getDayStart(prevMonthWeek.getTime());
			prevMonthWeek.add(Calendar.DAY_OF_MONTH, +6);
			sameEndDate = DateUtil.getDayEnd(prevMonthWeek.getTime());
		}else if(region == ReportRegion.thisMonth){
			
			//本月
			Calendar thisRegion = Calendar.getInstance();
			endDate = DateUtil.getDayEnd(thisRegion.getTime());
			thisRegion.set(Calendar.DAY_OF_MONTH, 1);
			startDate = DateUtil.getDayStart(thisRegion.getTime());
			
			Calendar prevYearMonth = Calendar.getInstance();
			prevYearMonth.setTime(startDate);
			prevYearMonth.add(Calendar.YEAR, -1);
			
			//上月
			thisRegion.add(Calendar.DAY_OF_MONTH, -1);
			rollEndDate = DateUtil.getDayEnd(thisRegion.getTime());
			thisRegion.set(Calendar.DAY_OF_MONTH, 0);
			rollStartDate = DateUtil.getDayStart(thisRegion.getTime());
			
			//上一年的本月
			sameStartDate = DateUtil.getDayStart(prevYearMonth.getTime());
			prevYearMonth.set(Calendar.DAY_OF_MONTH, prevYearMonth.getMaximum(Calendar.DAY_OF_MONTH));
			sameEndDate = DateUtil.getDayEnd(prevYearMonth.getTime());
		}else{ //默认就是今天
			
			startDate = DateUtil.getTodayStart();
			endDate = DateUtil.getTodayEnd();
			
			Calendar roll = Calendar.getInstance();
			roll.add(Calendar.DAY_OF_MONTH, -1);
			rollStartDate = DateUtil.getDayStart(roll.getTime());
			rollEndDate = DateUtil.getDayEnd(roll.getTime());
			
			Calendar same = Calendar.getInstance();
			same.add(Calendar.WEEK_OF_MONTH, -1);
			sameStartDate = DateUtil.getDayStart(same.getTime());
			sameEndDate = DateUtil.getDayEnd(same.getTime());
		}
		
		List<Object> validations = dbDAO.executeJPQLForQuery(
				"select h.number, p.name, a.way, count(v), count(v) * p.price, "
				+ "(select count(v1) from " + BjdjValidation.class.getName() + " v1 left join v1.appointment a1 left join a1.hall h1 left join a1.serviceCode.order.packageType p1 where ((h is null and h1 is null) or h1=h) and ((p is null and p1 is null) or p1=p) and ((a is null and a1 is null) or a1.way=a.way) and v1.time between ? and ?),"
				+ "(select count(v2) from " + BjdjValidation.class.getName() + " v2 left join v2.appointment a2 left join a2.hall h2 left join a2.serviceCode.order.packageType p2 where ((h is null and h2 is null) or h2=h) and ((p is null and p2 is null) or p2=p) and ((a is null and a2 is null) or a2.way=a.way) and v2.time between ? and ?)"
				+ " from " + BjdjValidation.class.getName() + " v"
				+ " left join v.appointment a"
				+ " left join a.hall h"
				+ " left join a.serviceCode.order.packageType p"
				+ " where v.time between ? and ?"
				+ " group by h, p, a.way", rollStartDate, rollEndDate, sameStartDate, sameEndDate, startDate, endDate);
		
		List<ValidationReport> validationList = new ArrayList<ValidationReport>();
		for(Object row : validations){
			
			Object[] values = (Object[])row;
			
			ValidationReport validation = new ValidationReport();
			validation.setRegion(DateUtil.formatDateToShortString_ZH_CN(startDate) + " - " + DateUtil.formatDateToShortString_ZH_CN(endDate));
			validation.setHall((String)values[0]);
			validation.setPackageName((String)values[1]);
			
			BjdjAppointmentType appointmentType = (BjdjAppointmentType)values[2];
			if(appointmentType != null){
				
				validation.setAppointment(appointmentType.getText());
			}
			Long count = (Long)values[3];
			if(count != null){
				
				validation.setCount(count.intValue());
			}
			Double amount = (Double)values[4];
			if(amount != null){
				
				validation.setAmount(amount);
			}
			Long rollCount = (Long)values[5];
			if(rollCount != null){
				
				validation.setRollCount(rollCount.intValue());
			}
			Long sameCount = (Long)values[6];
			if(sameCount != null){
				
				validation.setSameCount(sameCount.intValue());
			}
			validationList.add(validation);
		}
		
		Report report = new Report();
		report.setValidationReport(validationList);
		List<Report> reports = new ArrayList<Report>();
		reports.add(report);
		return reports;
	}

	@Override
	public List<Report> reportDetails(ReportRegion region) {
		
		//查询开始、结束时间
		Date startDate = null, endDate = null;
		
		if(region == ReportRegion.thisWeek){
			
			//本周
			Calendar thisRegion = Calendar.getInstance();
			endDate = DateUtil.getDayEnd(thisRegion.getTime());
			thisRegion.set(Calendar.DAY_OF_WEEK, 2);
			startDate = DateUtil.getDayStart(thisRegion.getTime());
		}else if(region == ReportRegion.thisMonth){
			
			//本月
			Calendar thisRegion = Calendar.getInstance();
			endDate = DateUtil.getDayEnd(thisRegion.getTime());
			thisRegion.set(Calendar.DAY_OF_MONTH, 1);
			startDate = DateUtil.getDayStart(thisRegion.getTime());
		}else{ //默认就是今天
			
			startDate = DateUtil.getTodayStart();
			endDate = DateUtil.getTodayEnd();
		}
		
		List<Object> validations = dbDAO.executeJPQLForQuery(
				"select v.time, sc.code, h.number, a.flightNo, a.useTime, m.phone, v.employee.employeeId, a.way, p.name, o.price"
				+ " from " + BjdjValidation.class.getName() + " v"
				+ " left join v.appointment a"
				+ " left join a.hall h"
				+ " left join a.serviceCode sc"
				+ " left join sc.order o"
				+ " left join sc.member m"
				+ " left join o.packageType p"
				+ " where v.time between ? and ?"
				, startDate, endDate);
		
		List<ValidationDetailReport> validationList = new ArrayList<ValidationDetailReport>();
		for(Object row : validations){
			
			Object[] values = (Object[])row;
			
			ValidationDetailReport validation = new ValidationDetailReport();
			
			Date time = (Date)values[0];
			if(time != null){
				
				validation.setTime(DateUtil.formatDateToSimpleString(time));
			}
			validation.setCode((String)values[1]);
			validation.setHall((String)values[2]);
			validation.setFlightNo((String)values[3]);
			Date flightTime = (Date)values[4];
			if(flightTime != null){
				
				validation.setFlightTime(DateUtil.formatDateToSimpleString(flightTime));
			}
			validation.setFlightPerson((String)values[5]);
			validation.setValidPerson((String)values[6]);
			
			BjdjAppointmentType appointmentType = (BjdjAppointmentType)values[7];
			if(appointmentType != null){
				
				validation.setAppointMethod(appointmentType.getText());
			}
			validation.setPackageName((String)values[8]);
			
			Double amount = (Double)values[9];
			if(amount != null){
				
				validation.setPrice(amount);
			}
			validationList.add(validation);
		}
		
		Report report = new Report();
		report.setValidationDetailReport(validationList);
		List<Report> reports = new ArrayList<Report>();
		reports.add(report);
		return reports;
	}

}