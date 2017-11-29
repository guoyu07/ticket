package com.ticket.serviceImpl;


import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.ticket.action.BaseAction;
import com.ticket.enumer.BjdjAppointmentType;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.AirportEmployee;
import com.ticket.pojo.BjdjAppointment;
import com.ticket.pojo.BjdjDispatch;
import com.ticket.pojo.BjdjHall;
import com.ticket.pojo.BjdjOrder;
import com.ticket.pojo.BjdjPage;
import com.ticket.pojo.BjdjServiceCode;
import com.ticket.pojo.BjdjServiceCodeOperation;
import com.ticket.pojo.BjdjServicePackage;
import com.ticket.pojo.BjdjValidation;
import com.ticket.pojo.ChannelHallRelation;
import com.ticket.pojo.Member;
import com.ticket.pojo.MemberFocusFlight;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.SystemDictionary;
import com.ticket.service.IBjdjActivateQueueService;
import com.ticket.service.IBjdjAppointmentService;
import com.ticket.service.IBjdjDispatchService;
import com.ticket.service.IBjdjHallService;
import com.ticket.service.IBjdjServiceCodeOperationService;
import com.ticket.service.IBjdjServiceCodeService;
import com.ticket.service.IBjdjValidationService;
import com.ticket.service.IChannelHallRelationService;
import com.ticket.service.ICommonTravellerService;
import com.ticket.service.IEmployeeInfoZengLogDetailService;
import com.ticket.service.IMemberFocusFlightService;
import com.ticket.service.IMemberService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.util.DateUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.PaginationContext;
import com.ticket.util.ResourceUtil;
import com.ticket.util.SmsUtil;

/**
 * 便捷登机预约表业务接口实现类
 * @ClassName: IBjdjAppointmentService   
 * @Description: 提供便捷登机预约表操作的增删改查   
 * @author HiSay  
 * @date 2015-11-23 22:48:35
 */
public class BjdjAppointmentServiceImpl extends BaseServiceImpl<BjdjAppointment, String> 
	implements IBjdjAppointmentService {

	private static final Log log = LogFactory.getLog(BjdjAppointmentServiceImpl.class);
	@Resource
	private IBjdjServiceCodeService serviceCodeService;
	@Resource
	private IMemberService memberService;
	@Resource
	private ISystemDictionaryService dictionaryService;
	@Resource
	private IBjdjHallService hallService;
	@Resource
	private IMemberFocusFlightService focusFlightService;
	@Resource
	private IBjdjServiceCodeOperationService operationService;
	@Resource
	private IChannelHallRelationService channelHallRelationService;
	@Resource
	private IBjdjValidationService validationService;
	@Resource
	private IBjdjDispatchService dispatchService;
	@Resource
	private IBjdjActivateQueueService queueService;
	@Resource
	protected IEmployeeInfoZengLogDetailService zengLogDetailService;
	@Resource 
	protected ICommonTravellerService travellerService;
	
	@Override
	public boolean remove(String id) throws ServiceException {
		
		batchRealDelete(BjdjAppointment.class, id);
		return true;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public BjdjAppointment offlineByMobile(String name, String idCard, String flightNo, String mobile, Integer count, String luggage, Date time, Date useTime) throws ServiceException {
		
		if(getActiveInfo(idCard) != null){
			
			throw new ServiceException("身份证"+idCard+"不能同时激活两个服务码");
		}
		
		if(!(getSystemUser() instanceof AirportEmployee)){
			
			throw new ServiceException(ResourceUtil.getText("airportEmployee.must"));
		}
		
		List<BjdjServiceCode> serviceCodes = serviceCodeService.getNotUsedByType("散客", 1);
		if(serviceCodes.size() == 0){
			
			throw new ServiceException(ResourceUtil.getText("serviceCode.notEnough"));
		}
		
		BjdjServiceCode serviceCode = serviceCodes.get(0);
		//考虑到服务厅的营业时间，航班早于7:30起飞、晚于23:00的航班，不能激活
		Calendar flightDate = Calendar.getInstance();
		flightDate.setTime(useTime);
		if((flightDate.get(Calendar.HOUR_OF_DAY) <= 7 && flightDate.get(Calendar.MINUTE) < 30)
				|| flightDate.get(Calendar.HOUR_OF_DAY) >= 23){
			
			throw new ServiceException(ResourceUtil.getText("serviceCode.appointment.time2"));
		}
		
		//必须在起飞前一段时间
		flightDate.add(Calendar.DAY_OF_MONTH, -2);
		flightDate.add(Calendar.HOUR_OF_DAY, 8);
		if(System.currentTimeMillis() > useTime.getTime() - 90*60*1000
				|| System.currentTimeMillis() < flightDate.getTime().getTime()){
			
			throw new ServiceException(ResourceUtil.getText("serviceCode.appointment.time"));
		}
		
		if(getByServiceCode(serviceCode.getCode()) != null){
			
			throw new ServiceException(ResourceUtil.getText("serviceCode.appointment.repeat"));
		}
		
		BjdjHall hall = hallService.accessibleHall(useTime, serviceCode.getType());
		BjdjAppointment appointment = new BjdjAppointment();
		appointment.setName(name);
		appointment.setIdCard(idCard);
		appointment.setFlightNo(flightNo);
		appointment.setMobile(mobile);
		appointment.setServiceCode(serviceCode);
		appointment.setWay(BjdjAppointmentType.offline);
		appointment.setEmployee((AirportEmployee)getSystemUser());
		appointment.setLuggage(luggage);
		appointment.setTime(time);
		appointment.setUseTime(useTime);
		appointment.setHall(hall);
		dbDAO.persist(appointment);
		
		//设置服务码状态为activated
		serviceCode = serviceCodeService.get(BjdjServiceCode.class, serviceCode.getId());
		SystemDictionary activatedDict = dictionaryService.getByName("activated");
		serviceCode.setState(activatedDict);
		serviceCode.setIsBind(true);
		serviceCode.setMember(memberService.generateByMobileAndSendSms(mobile, false));
		serviceCodeService.merge(serviceCode);
		
		//发送短信
		SystemDictionary dictionary = dictionaryService.getByName("serviceCode.activate.success");
		if(dictionary != null && dictionary.getDescription() != null){
			
			SmsUtil.sendSms(mobile, 
					MessageFormat.format(GeneralUtil.removeHtmlTags(dictionary.getDescription()), serviceCode.getCode(),
					appointment.getFlightNo(), DateUtil.formatDateToSimpleString(appointment.getUseTime())));
		}
		return appointment;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public BjdjAppointment onlineAppointment(String serviceCode_id, String idCard, String flightNo, String mobile, Date useTime, String name) throws ServiceException{
		
		BjdjServiceCode serviceCode = new BjdjServiceCode(serviceCode_id);
		return onlineAppointment(serviceCode, idCard, flightNo, mobile, useTime, name);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public BjdjAppointment onlineAppointment(BjdjServiceCode serviceCode, String idCard, String flightNo, String mobile, Date useTime, String name) throws ServiceException{
		
		if(getActiveInfo(idCard) != null){
			
			throw new ServiceException("身份证"+idCard+"不能同时激活两个服务码");
		}
		
		if(!"unused".equals(serviceCode.getState().getName())){
			
			throw new ServiceException("该服务码已经激活过，不能重复激活");
		}
		
		
		//考虑到服务厅的营业时间，航班早于7:30起飞、晚于23:00的航班，不能激活
		Calendar flightDate = Calendar.getInstance();
		flightDate.setTime(useTime);
		if((flightDate.get(Calendar.HOUR_OF_DAY) <= 7 && flightDate.get(Calendar.MINUTE) < 30)
				|| flightDate.get(Calendar.HOUR_OF_DAY) >= 23){
			
			throw new ServiceException(ResourceUtil.getText("serviceCode.appointment.time2"));
		}
		
		//必须在起飞前一段时间
		flightDate.add(Calendar.DAY_OF_MONTH, -2);
		flightDate.add(Calendar.HOUR_OF_DAY, 8);
		if(System.currentTimeMillis() > useTime.getTime() - 90*60*1000
				|| System.currentTimeMillis() < flightDate.getTime().getTime()){
			
			throw new ServiceException(ResourceUtil.getText("serviceCode.appointment.time"));
		}
		
		if(getByServiceCode(serviceCode.getCode()) != null){
			
			throw new ServiceException(ResourceUtil.getText("serviceCode.appointment.repeat"));
		}
		
		BjdjHall hall = hallService.accessibleHall(useTime, serviceCode.getType());
		BjdjAppointment appointment = new BjdjAppointment();
		appointment.setIdCard(idCard);
		appointment.setFlightNo(flightNo);
		appointment.setMobile(mobile);
		appointment.setTime(new Date());
		appointment.setServiceCode(serviceCode);
		appointment.setWay(BjdjAppointmentType.online);
		appointment.setUseTime(useTime);
		appointment.setName(name);
		appointment.setMember(getMember());
		appointment.setHall(hall);
		dbDAO.persist(appointment);
		
		//修改对应身份证用户的真实姓名（慎重）
//		Member m = memberService.getByIdCard(idCard);
//		if(m != null && GeneralUtil.isNull(m.getRealName())){
//			m.setRealName(name);
//			memberService.merge(m);
//		}

		//设置服务码状态为activated
		SystemDictionary activatedDict = dictionaryService.getByName("activated");
		serviceCode = serviceCodeService.get(BjdjServiceCode.class, serviceCode.getId());
		serviceCode.setState(activatedDict);
		serviceCodeService.merge(serviceCode);
		
		//关注航班
		MemberFocusFlight focusFlight = focusFlightService.queryByMemberAndFlightNoAndDate(flightNo, DateUtil.formatDateToShortString(useTime), "depart", versionFlag);
		if(focusFlight == null && getMember() != null){
			
			focusFlightService.persist(getMember().getId(), flightNo, useTime.toString().substring(0, 10), 
					"seat", "depart", 0, null, 1, 
					0, mobile, null, null, null, versionFlag);
		}
		
		//发送短信
		BjdjOrder order = serviceCode.getOrder();
		BjdjServicePackage bjdjServicePackage = order.getPackageType();
		BjdjPage bjdjPage = bjdjServicePackage.getBjdjPage();
		SystemDictionary parent = bjdjPage.getSmsTemplate();
		SystemDictionary dictionary = dictionaryService.queryByParentAndName(parent, "serviceCode.activate.success");
		if(dictionary != null && dictionary.getDescription() != null){
			
			SmsUtil.sendSms(serviceCode.getMember().getPhone(), 
					MessageFormat.format(GeneralUtil.removeHtmlTags(dictionary.getDescription()), serviceCode.getCode(),
							appointment.getFlightNo(),DateUtil.formatDateToSimpleString(appointment.getUseTime())));
		}
		return appointment;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void onlineAppointment(BjdjServiceCode[] codes, Date[] dates, String[] flightNos, String[] idCards, String[] cardTypes, String[] names) throws ServiceException{

		if(codes == null){
			
			throw new ServiceException(ResourceUtil.getText("serviceCode.required"));
		}
		
		//服务码验证
		for(BjdjServiceCode serviceCode : codes){
			
			if(zengLogDetailService.canActivate(serviceCode)){
				
				serviceCodeService.canActivate(serviceCode);
			}
		}
		
		//激活
		for(int i = 0; i < codes.length; i++){
			
			BjdjServiceCode serviceCode = codes[i];
			//生成预约信息
			onlineAppointment(serviceCode, idCards[i].trim(), flightNos[i].trim(), serviceCode.getMember().getPhone(), dates[i], names[i].trim());
		}
		
		//保存常用旅客信息
		for(int i = 0; i < idCards.length; i++){
			
			travellerService.persistOrNot(BaseAction.getSessionMember(), names[i], idCards[i], cardTypes[i]);
		}
	}
	
	@Override
	public BjdjAppointment getByServiceCode(String serviceCode) throws ServiceException{

		BjdjServiceCode serviceCodeObj = serviceCodeService.getByCode(serviceCode.trim());
		if(serviceCodeObj == null){
			throw new ServiceException(ResourceUtil.getText("serviceCode.notExist", serviceCode.trim()));
		}
		
		return getByServiceCode(serviceCodeObj);
	}
	
	@Override
	public BjdjAppointment getByServiceCode(BjdjServiceCode serviceCode){
		
		String tableName = getTableNameFromEntity(BjdjAppointment.class);
		BjdjAppointment appointment = (BjdjAppointment)dbDAO.executeJPQLForQuerySingle("select t from " + tableName + " t where serviceCode=?", serviceCode);
		return appointment;
	}

	@Override
	public List<BjdjValidation> getValidation(BjdjAppointment appointment) {
		
		String tableName = BjdjValidation.class.getName();
		List<BjdjValidation> list = dbDAO.executeJPQLForQuery("select t from " + tableName + " t where appointment=?", BjdjValidation.class, appointment);
		return list;
	}

	@Override
	public Pagination queryAll2(String keyword,  String order,
			Integer pageSize) throws ServiceException {
		try {
			List<Object[]> objects = new ArrayList<Object[]>();
			StringBuffer sb = new StringBuffer();
			sb.append(" and s.serviceCode is not null");
			if(GeneralUtil.isNotNull(keyword)){
				sb.append("and (s.flightNo like?3 or s.name like ?4 ) ");
				objects.add(new Object[]{3,"%"+keyword+"%"});
				objects.add(new Object[]{4,"%"+keyword+"%"});
			}
//			if(GeneralUtil.isNotNull(employeeInfo)){
//				sb.append("and s.employeeInfo_id=?5 ");
//				objects.add(new Object[]{5,employeeInfo.getId()});
//			}
//			if(GeneralUtil.isNotNull(state)){
//				sb.append("and s.state=?5 ");
//				objects.add(new Object[]{5,state});
//			}
//			if(!isEmployeeNull){
//				sb.append("and s.employeeInfo_id is not null ");
//			}
			if(GeneralUtil.isNull(order)){
				order = orderBy;
			}
			return dbDAO.queryByPageModuleNew(BjdjAppointment.class.getSimpleName(), deleteFlag, versionFlag, 
					sb.toString(), objects, order, PaginationContext.getOffset(), pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void unActivate(String serviceCode_idsValue, Object operationPerson) throws ServiceException {

		if(GeneralUtil.isNull(serviceCode_idsValue)){
			
			throw new ServiceException(ResourceUtil.getText("id.required"));
		}
		
		String[] ids = serviceCode_idsValue.split(",");
		List<BjdjServiceCode> apps = new ArrayList<BjdjServiceCode>();
		for(String id : ids){
			
			BjdjServiceCode serviceCode = get(BjdjServiceCode.class, id);
			apps.add(serviceCode);
		}
		
		//记录日志
		SystemDictionary dictionary = dictionaryService.getByName("unactive");
		if(operationPerson instanceof Member){
			
			for(BjdjServiceCode a : apps){
				
				//如果是普通会员，则要判断是否在起飞前5个小时
//				if(System.currentTimeMillis() + 5 * 60 * 60 * 1000 > a.getAppointment().getUseTime().getTime()){
//					
//					throw new ServiceException(ResourceUtil.getText("serviceCode.unactivate.tip"));
//				}
				BjdjServiceCodeOperation operation = operationService.persist(a, dictionary, getMember(), null);
				operation.setAppointment(getByServiceCode(a));
				merge(operation);
			}
		}else{
			
			for(BjdjServiceCode a : apps){
				
				BjdjServiceCodeOperation operation = operationService.persist(a, dictionary, getSystemUser());
				operation.setAppointment(getByServiceCode(a));
				merge(operation);
			}
		}
		
		//设置服务码状态为“未使用”，未绑定
		SystemDictionary unused = dictionaryService.getByName("unused");
		for(BjdjServiceCode a : apps){
			
			a.setState(unused);
			a.setIsBind(false);
			merge(a);
			
			BjdjAppointment appointment = getByServiceCode(a);
			appointment.setServiceCode(null);
			merge(appointment);
		}

		//检测是否有大厅空余，然后发送短信提示
		queueService.checkHallAndSendMessage();
	}

	@Override
	public List<BjdjAppointment> getByUseTimes(Date startTime, Date endTime) {
		List<BjdjAppointment> list = dbDAO.executeJPQLForQueryEntity("select c from " + BjdjAppointment.class.getName() + " c"
				+ " where c.hall is null and c.useTime >= ? and c.useTime <= ?", startTime, endTime);
		return list;
	}
	
	@Override
	public List<BjdjAppointment> getEnterHallByUseTimes(Date startTime, Date endTime) {
		List<BjdjAppointment> list = dbDAO.executeJPQLForQueryEntity(
				"select c from " + BjdjAppointment.class.getName() + " c"
				+ " where c.hall is not null and c.useTime >= ? and c.useTime <= ?", startTime, endTime);
		return list;
	}
	
	@Override
	public List<BjdjAppointment> getByTimes(Date startTime, Date endTime) {
		List<BjdjAppointment> list = dbDAO.executeJPQLForQueryEntity("select c from " + BjdjAppointment.class.getName() + " c"
				+ " where c.hall is null and c.status.createTime >= ? and c.status.createTime <= ?", startTime, endTime);
		return list;
	}
	
	@Override
	public List<BjdjAppointment> getEnterHallByTimes(Date startTime, Date endTime) {
		List<BjdjAppointment> list = dbDAO.executeJPQLForQueryEntity(
				"select c from " + BjdjAppointment.class.getName() + " c"
						+ " where c.hall is not null and c.status.createTime >= ? and c.status.createTime <= ?", startTime, endTime);
		return list;
	}

	@Override
	public List<BjdjAppointment> get(String flightNumber, Date flightDate)
			throws ServiceException {
		
		Calendar c = Calendar.getInstance();
		c.setTime(flightDate);
		List<BjdjAppointment> appointments = dbDAO.executeJPQLForQuery("select c from " + BjdjAppointment.class.getName() + " c"
				+ " where c.flightNo = ? and YEAR(c.useTime)=? and MONTH(c.useTime)=? and DAY(c.useTime)=?", 
				BjdjAppointment.class, flightNumber, c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH));
		return appointments;
	}
	
	@Override
	public List<BjdjAppointment> getByFlightNo(String flightNumber)
			throws ServiceException {
		List<BjdjAppointment> appointments = dbDAO.executeJPQLForQuery("select c from " + BjdjAppointment.class.getName() + " c where c.flightNo = ?", 
				BjdjAppointment.class, flightNumber);
		return appointments;
	}

	@Override
	public List<BjdjAppointment> getByIdsValue(String idsValue) throws ServiceException {
		
		List<BjdjAppointment> appointments = dbDAO.executeJPQLForQueryEntity("select c from " + BjdjAppointment.class.getName() + " c where c.id in (?)", idsValue);
		return appointments;
	}
	
	@Override
	public void flightDelay(String flightNumber, String flightDate){
		
		//获取所有的该航班对应的便捷登机预约
		try {
			List<BjdjAppointment> appointments = get(flightNumber, DateUtil.parseShortStringToDate(flightDate));
			//设置该预约的航班已经延迟的标志
			for(BjdjAppointment appointment:appointments){
				
				appointment.setIfNI(1);
				merge(appointment);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int validationTimeout(Date startTime, Date endTime) {
		
		return validationTimeout(startTime, endTime, null);
	}
	
	@Override
	public int validationTimeout(Date startTime, Date endTime, BjdjHall hall) {
		
		startTime = new Date(startTime.getTime() - 90 * 60 * 1000);
		endTime = new Date(endTime.getTime() - 90 * 60 * 1000);
		
		StringBuffer sql = new StringBuffer("select c from ").append(BjdjAppointment.class.getName())
				.append(" c where c.useTime between ? and ?");
		List<Object> params = new ArrayList<Object>();
		params.add(startTime);
		params.add(endTime);
		if(hall != null){
			
			sql.append(" and c.hall = ?");
			params.add(hall);
		}
		
		List<BjdjAppointment> list = dbDAO.executeJPQLForQuery(sql.toString(), BjdjAppointment.class, params.toArray());
		int count = 0;
		for(BjdjAppointment a : list){
			
			if(a.getValidation() == null){
				
				count++;
			}
		}
		return count;
	}
	
	@Override
	public int validationTimeout(Date endTime, BjdjHall hall) {
		
		Calendar c = Calendar.getInstance();
		c.setTime(endTime);
		c.add(Calendar.MINUTE, -90);
		
		StringBuffer sql = new StringBuffer("select c from ").append(BjdjAppointment.class.getName())
				.append(" c where c.useTime < ? and YEAR(c.useTime)=? and MONTH(c.useTime)=? and DAY(c.useTime)=?");
		List<Object> params = new ArrayList<Object>();
		params.add(c.getTime());
		params.add(c.get(Calendar.YEAR));
		params.add(c.get(Calendar.MONTH) + 1);
		params.add(c.get(Calendar.DAY_OF_MONTH));
		if(hall != null){
			
			sql.append(" and c.hall = ?");
			params.add(hall);
		}
		
		List<BjdjAppointment> list = dbDAO.executeJPQLForQuery(sql.toString(), BjdjAppointment.class, params.toArray());
		int count = 0;
		for(BjdjAppointment a : list){
			
			if(a.getValidation() == null){
				
				count++;
			}
		}
		return count;
	}

	@Override
	public int waitNumber(Date flightDate, BjdjHall hall) {
		
		Calendar start = Calendar.getInstance();
		start.setTime(flightDate);
		start.set(Calendar.HOUR_OF_DAY, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
		
//		Long number = dbDAO.executeJPQLForQuerySingle(
//				"select count(s) from " + BjdjAppointment.class.getName() + " s"
//				+ " where s.hall=? and s.validation.dispatch.ended=false and YEAR(s.status.createTime)=? and MONTH(s.status.createTime)=? and DAY(s.status.createTime)=?", 
//				Long.class, hall, c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH));
		
		//预约
		List<BjdjAppointment> appointments = getEnterHallByTimes(start.getTime(), flightDate);
		Integer online = 0;//线上预约数
		Integer offline = 0;//线下预约数
		for(BjdjAppointment appointment:appointments){
			if(appointment.getWay() == BjdjAppointmentType.online && appointment.getHall().getId().equals(hall.getId())){//线上并且是该厅的
				online += 1;
			}else if(appointment.getWay() == BjdjAppointmentType.offline && appointment.getHall().getId().equals(hall.getId())){//线下并且是该厅的
				offline += 1;
			}
		}
		
		//如果在最迟90分钟的验证时间之后都未验证，则自动放出座位
		Integer validationLength_ = validationTimeout(flightDate, hall);
		
//		//验证
//		List<BjdjValidation> bjdjValidations = validationService.getEnterHallByTimes(start.getTime(), flightDate);
//		Integer validationLength = 0;//该厅的验证数量
//		for(BjdjValidation validation:bjdjValidations){
//			BjdjAppointment appointment = validation.getAppointment();
//			if(appointment.getHall().getId().equals(hall.getId())){//是该厅的
//				validationLength += 1;
//			}
//		}
		
		//核销
		List<BjdjDispatch> bjdjDispatchs = dispatchService.getEnterHallByTimes(start.getTime(), flightDate);
		Integer dispatchListLength = 0;//该厅核销数量
		for(BjdjDispatch dispatch: bjdjDispatchs){
			BjdjValidation validation = dispatch.getValidation();
			BjdjAppointment appointment = validation.getAppointment();
			if(appointment.getHall().getId().equals(hall.getId()) && dispatch.getEnded()){//是该厅的，已核销的
				dispatchListLength += 1;
			}
		}
		
		//取消预约
		List<BjdjServiceCodeOperation> codeOperations = operationService.getCanceled(flightDate);
		Integer onlineUnactiveLength = 0;//线上取消预约数量
		Integer offlineUnactiveLength = 0;//线下取消预约数量
		for(BjdjServiceCodeOperation operation:codeOperations){
			
			if(operation.getFromMember() != null && operation.getAppointment().getHall().getId().equals(hall.getId())){//线上
				offlineUnactiveLength += 1;
			}else if(operation.getSystemUser() != null && operation.getAppointment().getHall().getId().equals(hall.getId())){//线下
				onlineUnactiveLength += 1;
			}
		}
		
		//实时座位数=公共池总数-（线上激活数+线下预约数)+对应服务码验证数超时数+线上激活取消数+线下预约取消数+核销数
		int waitNumber = (online + offline) - validationLength_ - onlineUnactiveLength - offlineUnactiveLength - dispatchListLength;
		return waitNumber;
	}
	
	@Override
	public int waitNumber(Date flightDate, SystemDictionary dictionary) {
		
		int totalWait = 0;
		List<ChannelHallRelation> list = channelHallRelationService.queryByChannel(dictionary);
		for(int i = 0; i < list.size(); i++){
			
			BjdjHall hall = list.get(i).getHall();
			totalWait += waitNumber(flightDate, hall);
		}
		return totalWait;
	}
	
	@Override
	public int waitNumber(Date flightDate) {
		
		List<BjdjHall> halls = hallService.queryAll(BjdjHall.class);
		int waitNumber = 0;
		for(BjdjHall hall : halls){
			
			waitNumber += waitNumber(flightDate, hall);
		}
		return waitNumber;
	}
	
//	@Override
//	public List<BjdjAppointment> listWait(BjdjHall hall) {
//		
//		Calendar c = Calendar.getInstance();
//		List<BjdjAppointment> list = dbDAO.executeJPQLForQuery(
//				"select s from " + BjdjAppointment.class.getName() + " s"
//				+ " where s.hall=? and s.validation.dispatch.ended=false and YEAR(s.status.createTime)=? and MONTH(s.status.createTime)=? and DAY(s.status.createTime)=?", 
//				BjdjAppointment.class, hall, c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH));
//		return list;
//	}
//	
//	@Override
//	public List<BjdjAppointment> listWait() {
//		
//		Calendar c = Calendar.getInstance();
//		List<BjdjAppointment> list = dbDAO.executeJPQLForQuery(
//				"select s from " + BjdjAppointment.class.getName() + " s"
//				+ " where s.validation.dispatch.ended=false and YEAR(s.status.createTime)=? and MONTH(s.status.createTime)=? and DAY(s.status.createTime)=?", 
//				BjdjAppointment.class, c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH));
//		return list;
//	}
	
	@Override
	public int surplus(Date flightDate, BjdjHall hall) {
		
		Integer hallCapcity = hall.getCapacity();
		int waitNumber = waitNumber(flightDate, hall);
		
		int surplus = hallCapcity - waitNumber;
		return surplus > 0 ? surplus : 0;
	}
	
	@Override
	public int surplus(Date flightDate, SystemDictionary channel) {
		
		List<ChannelHallRelation> halls = channelHallRelationService.queryByChannel(channel);
		int totalCapacity = 0;
		int totalWait = 0;
		for(ChannelHallRelation chr : halls){
			
			BjdjHall hall = chr.getHall();
			totalCapacity += hall.getCapacity();
			totalWait += waitNumber(flightDate, hall);
		}
		int surplus = totalCapacity - totalWait;
		return surplus > 0 ? surplus : 0;
	}
	
	@Override
	public boolean hasSurplus(Date flightDate, BjdjServiceCode... serviceCodes) {
		
		//key：存放每种渠道，value：对应渠道类型的服务码数量
		HashMap<SystemDictionary, Integer> countMap = new HashMap<SystemDictionary, Integer>();
		for(BjdjServiceCode serviceCode : serviceCodes){
			
			Integer count = countMap.get(serviceCode.getType());
			if(count == null){
				
				countMap.put(serviceCode.getType(), 1);
			}else{
				
				countMap.put(serviceCode.getType(), count + 1);
			}
		}
		
		for(SystemDictionary dictionary : countMap.keySet()){
			
			Integer count = countMap.get(dictionary);
			int surplus = surplus(flightDate, dictionary);
			
			if(surplus < count){
				
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean hasSurplus(Date flightDate, String... idsValue) {
		
		List<BjdjServiceCode> list = new LinkedList<BjdjServiceCode>();
		for(String id : idsValue){
			
			list.add(get(BjdjServiceCode.class, id));
		}
		return hasSurplus(flightDate, list.toArray(new BjdjServiceCode[list.size()]));
	}
	
	@Override
	public List<BjdjAppointment> getByMemberAndHall(String memberId, String hallId) {
		
		Calendar c = Calendar.getInstance();
		List<BjdjAppointment> list = dbDAO.executeJPQLForQueryEntity(
				"select s from " + BjdjAppointment.class.getName() + " s where s.serviceCode.member.id=? and s.hall.id=?"
				+ " and YEAR(s.status.createTime)=? and MONTH(s.status.createTime)=? and DAY(s.status.createTime)=?", 
				memberId, hallId, c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH));
		return list;
	}

	@Override
	public boolean canActivate(Date flightDate) {
		
		//考虑到服务厅的营业时间，航班早于7:30起飞、晚于23:00的航班，不能激活
		Calendar c = Calendar.getInstance();
		c.setTime(flightDate);
		if((c.get(Calendar.HOUR_OF_DAY) <= 7 && c.get(Calendar.MINUTE) < 30)
				|| c.get(Calendar.HOUR_OF_DAY) >= 23){
			
			return false;
		}
		
		//必须在起飞前一段时间
		c.add(Calendar.DAY_OF_MONTH, -2);
		c.add(Calendar.HOUR_OF_DAY, 8);
		if(System.currentTimeMillis() > flightDate.getTime() - 90*60*1000
				|| System.currentTimeMillis() < c.getTime().getTime()){
			
			return false;
		}
		return true;
	}

	@Override
	public long queryAllCount(String employee_id) throws ServiceException {
		List<Object> list = dbDAO.executeJPQLForQuery("select count(c) from " + BjdjAppointment.class.getName() + " c");
		long count  = 0;
		if(GeneralUtil.isNotNull(list)){
			count = (Long)list.get(0);
		}
		return count;
	}

	@Override
	public Pagination queryEntityByAdmin(String flightNo,
			Date startTime, Date endTime, String versionFlag,
			int adminCommonPageSize) throws ServiceException {
		try {
			//先获取当前的实体名称
			String entityName = this.getTableNameFromEntity(this.getClass());
			
			StringBuffer whereCondition = new StringBuffer();
			int index = 3;
			List<Object> params = new ArrayList<Object>();
			if(GeneralUtil.isNotNull(flightNo)){
				
				whereCondition.append(" and flightNo like ?");
				whereCondition.append(index++);
				params.add("%"+flightNo+"%");
			}
			if(GeneralUtil.isNotNull(startTime)){
				
				whereCondition.append(" and time>=?");
				whereCondition.append(index++);
				params.add(startTime);
			}
			if(GeneralUtil.isNotNull(endTime)){
				
				whereCondition.append(" and time<=?");
				whereCondition.append(index++);
				params.add(endTime);
			}
			if(GeneralUtil.isNotNull(startTime)){
				
				whereCondition.append(" and useTime>=?");
				whereCondition.append(index++);
				params.add(startTime);
			}
			if(GeneralUtil.isNotNull(endTime)){
				
				whereCondition.append(" and useTime<=?");
				whereCondition.append(index++);
				params.add(endTime);
			}
			return dbDAO.queryByPageModule(entityName, deleteFlag, versionFlag, whereCondition.toString(), params.toArray(), orderBy, PaginationContext.getOffset(), adminCommonPageSize);
		} catch (Exception e) {
			log.info("查询实体列表出错: " + e.fillInStackTrace());
			return null;
		}
	}

	@Override
	public Pagination queryEntityByAdmin(BjdjHall bjdjHall, String flightNo,
			Date startTime, Date endTime, String versionFlag,
			int adminCommonPageSize) throws ServiceException {
		try {
			//先获取当前的实体名称
			String entityName = this.getTableNameFromEntity(this.getClass());
			
			StringBuffer whereCondition = new StringBuffer();
			int index = 3;
			List<Object> params = new ArrayList<Object>();
			whereCondition.append(" and s.hall.id = ?");
			whereCondition.append(index++);
			params.add(bjdjHall.getId());
			if(GeneralUtil.isNotNull(flightNo)){
				
				whereCondition.append(" and s.flightNo like ?");
				whereCondition.append(index++);
				params.add("%"+flightNo+"%");
			}
			if(GeneralUtil.isNotNull(startTime)){
				
				whereCondition.append(" and s.time>=?");
				whereCondition.append(index++);
				params.add(startTime);
			}
			if(GeneralUtil.isNotNull(endTime)){
				
				whereCondition.append(" and s.time<=?");
				whereCondition.append(index++);
				params.add(endTime);
			}
			if(GeneralUtil.isNotNull(startTime)){
				
				whereCondition.append(" and s.useTime>=?");
				whereCondition.append(index++);
				params.add(startTime);
			}
			if(GeneralUtil.isNotNull(endTime)){
				
				whereCondition.append(" and s.useTime<=?");
				whereCondition.append(index++);
				params.add(endTime);
			}
			return dbDAO.queryByPageModule(entityName, deleteFlag, versionFlag, whereCondition.toString(), params.toArray(), orderBy, PaginationContext.getOffset(), adminCommonPageSize);
		} catch (Exception e) {
			log.info("查询实体列表出错: " + e.fillInStackTrace());
			return null;
		}
		
		/*try {
			//先获取当前的实体名称
			StringBuffer whereCondition = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			whereCondition.append(" where t.hall.id = ? ");
			params.add(bjdjHall.getId());
			if(GeneralUtil.isNotNull(flightNo)){
				
				whereCondition.append(" and t.flightNo=?");
				params.add(flightNo);
			}
			if(GeneralUtil.isNotNull(startTime)){
				
				whereCondition.append(" and t.time>=?");
				params.add(startTime);
			}
			if(GeneralUtil.isNotNull(endTime)){
				
				whereCondition.append(" and t.time<=?");
				params.add(endTime);
			}
			if(startTime != null){
				
				whereCondition.append(" and t.useTime>?");
				params.add(startTime);
			}
			
			if(GeneralUtil.isNotNull(endTime)){
				whereCondition.append(" and t.useTime<?");
				params.add(endTime);
			}
			
			return paginationQuery("select t from " + BjdjAppointment.class.getName() + " t" + whereCondition.toString() + " order by t.status.createTime desc",BjdjAppointment.class, params.toArray());
		} catch(Exception e) {
			log.info("查询实体列表出错: " + e.fillInStackTrace());
			return null;
		}*/
	}
	
	@Override
	public BjdjAppointment getActiveInfo(String idcard) {

		BjdjAppointment appointment = dbDAO.executeJPQLForQuerySingle(
					"select s from " + BjdjAppointment.class.getName() + " s where s.serviceCode.state.name = 'activated' and s.idCard = ? and s.serviceCode.expireTime > ?", BjdjAppointment.class, idcard, new Date());
		return appointment;
	}
}