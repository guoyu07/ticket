package com.ticket.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.enumer.BjdjAppointmentType;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.AirportEmployee;
import com.ticket.pojo.BjdjAppointment;
import com.ticket.pojo.BjdjDispatch;
import com.ticket.pojo.BjdjHall;
import com.ticket.pojo.BjdjRealTimeData;
import com.ticket.pojo.BjdjServiceCode;
import com.ticket.pojo.BjdjServiceCodeOperation;
import com.ticket.pojo.BjdjServicePackage;
import com.ticket.pojo.BjdjTime;
import com.ticket.pojo.BjdjValidation;
import com.ticket.pojo.SqlParam.Condition;
import com.ticket.pojo.SqlParamGroup;
import com.ticket.pojo.SystemUser;
import com.ticket.service.IBjdjAppointmentService;
import com.ticket.service.IBjdjDispatchListService;
import com.ticket.service.IBjdjDispatchService;
import com.ticket.service.IBjdjHallService;
import com.ticket.service.IBjdjServiceCodeOperationService;
import com.ticket.service.IBjdjServiceItemService;
import com.ticket.service.IBjdjServicePackageItemService;
import com.ticket.service.IBjdjServicePackageService;
import com.ticket.service.IBjdjTimeService;
import com.ticket.service.IBjdjValidationService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.DateUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 便捷登机预约表控制器
 * @ClassName: BjdjAppointmentAction   
 * @Description:  提供便捷登机预约表的相关操作方法. 
 * @author HiSay  
 * @date 2015-11-23 22:48:35
 */
public class BjdjAppointmentAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//便捷登机预约表的业务层
	@Resource 
	protected IBjdjAppointmentService appointmentService;
	@Resource
	protected ISystemDictionaryService dictionaryService;
	@Resource
	protected IBjdjValidationService validationService;
	@Resource
	protected IBjdjDispatchService dispatchService;
	@Resource 
	private ISystemOperationLogService logService;
	@Resource
	private IBjdjHallService hallService;
	@Resource 
	private IBjdjTimeService timeService;
	@Resource 
	private IBjdjDispatchListService dispatchListService;
	@Resource 
	private IBjdjServiceCodeOperationService operationService;
	@Resource
	private IBjdjServiceItemService serviceItemService;
	@Resource
	private IBjdjServicePackageService servicePackageService;
	@Resource
	private IBjdjServicePackageItemService packageItemService;
	
	//便捷登机预约表实体
	protected BjdjAppointment appointment = null;
	//主键
	protected String id = null;
    //预约方式
	protected BjdjAppointmentType way = null;
    //预约时间
	protected Date time = null;
    //预约使用时间
	protected Date useTime = null;
    //服务码ID
	protected BjdjServiceCode serviceCode = null;
	//航班号(多个用逗号隔开)
	protected String flightNo = null;
    //备注
	protected String description = null;
	//行李
	protected String luggage;
	//身份证号
	protected String IDCard;
	//姓名
	protected String name;
	//电话号码
	protected String mobile;
	//邮箱
	protected String email;
	//数量
	protected Integer count;
	
	private String keyword;
	
	private Date startTime, startTime2;
	
	private Date endTime, endTime2;
	
	protected String packageType;

	private String code;
	
	private BjdjAppointmentType type;
	private String state_id;
	
	/**
	 * 下载报表
	 * @return
	 * @throws ServiceException
	 */
	public String downReport() {
		
		SystemUser systemUser = getSessionAdminUser();
		SqlParamGroup group = new SqlParamGroup("s.status.deleteFlag", Condition.eq, ContextConstants.STATUS_OF_ZERO).and("s.status.versionFlag", Condition.eq, versionFlag);
		if(systemUser instanceof AirportEmployee){//登录的是机场员工
			
			AirportEmployee airportEmployee = (AirportEmployee)systemUser;
			BjdjHall bjdjHall = airportEmployee.getHall();
			group.and("s.hall.id", Condition.ge, bjdjHall.getId());
		}
		group.and("s.flightNo", Condition.like, flightNo);
		group.and("s.time", Condition.ge, startTime);
		group.and("s.time", Condition.le, endTime);
		group.and("s.useTime", Condition.ge, startTime2);
		group.and("s.useTime", Condition.le, endTime2);
		group.and("s.serviceCode.code", Condition.like_left, code);
		group.and("s.IDCard", Condition.like_left, IDCard);
		group.and("s.name", Condition.like_left, name);
		group.and("s.serviceCode.member.phone", Condition.like_left, mobile);
		group.and("s.serviceCode.packageType.name", Condition.eq, packageType);
		group.and("s.serviceCode.state.id", Condition.eq, state_id);
		group.and("s.way", Condition.eq, type);
		List<BjdjServiceCode> list = appointmentService.getDbDAO().executeJPQLForQuery("select s from " + BjdjAppointment.class.getName() + " s " + group.toString(true) + " order by s.status.createTime desc", group.getParamArray());
		exportExcel("/WEB-INF/excel/jasper/bjdjAppointment.jasper", list, "预约情况");
		return null;
	}
	
	/**
	 * 添加便捷登机订单表
	 * @Title: BjdjAppointmentAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String addForMobile() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addBjdjAppointmentMobile";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(IDCard)) {
				data = getText("IDCard.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(flightNo)) {
				data = getText("flightNo.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(mobile)) {
				data = getText("mobile.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(useTime)) {
				data = getText("useTime.required");
				return TEXT;
			}
			
			//根据保存结果返回页面
			try {
				appointment = appointmentService.offlineByMobile(name, IDCard, flightNo, mobile, 1, luggage, new Date(), useTime);
				BjdjServicePackage packageObj = packageItemService.get(BjdjServicePackage.class, packageType);
				appointment.getServiceCode().setPackageType(packageObj);
				appointmentService.merge(appointment.getServiceCode());
			} catch (Exception e) {
				data = AjaxData.responseError(e.getMessage());
				return JSON;
			}
			
			if(appointment != null){
				
				data = AjaxData.responseSuccess(getText("addSuccess"));
				return JSON;
			}else{
				
				data = AjaxData.responseSuccess(getText("addFaild"));
				return JSON;
			}
		}
	}
	
	/**
	 * 添加便捷登机订单表
	 * @Title: BjdjAppointmentAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String addForEmail() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addBjdjAppointmentEmail";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(IDCard)) {
				data = getText("IDCard.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(flightNo)) {
				data = getText("flightNo.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(email)) {
				data = getText("email.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(useTime)) {
				data = getText("useTime.required");
				return TEXT;
			}
			
			//根据保存结果返回页面
			try {
				appointment = appointmentService.offlineByMobile(name, IDCard, flightNo, mobile, 1, luggage, new Date(), useTime);
				appointment.setEmail(email);
				appointmentService.merge(appointment);
				
				BjdjServicePackage packageObj = packageItemService.get(BjdjServicePackage.class, packageType);
				appointment.getServiceCode().setPackageType(packageObj);
				appointmentService.merge(appointment.getServiceCode());
				data = AjaxData.responseSuccess(getText("addSuccess"));
				return JSON;
			} catch (Exception e) {
				e.printStackTrace();
				data = AjaxData.responseSuccess(e.getMessage());
				return JSON;
			}
		}
	}
	
	/**
	 * @Description：实时数据
	 * @return
	 * @throws Exception 
	 */
	public String appointmentData() throws Exception{
		
		SystemUser user = getSessionAdminUser();
		if(user != null && user instanceof AirportEmployee){//如果登录的不是后台管理员，而是机场员工，则只能看到自己所在厅的实时数据
			AirportEmployee employee = (AirportEmployee)user;
			BjdjHall hall = employee.getHall();
			String hallName = hall.getNumber();
			Integer all = hall.getCapacity();
			ActionContext.getContext().put("hall", hallName);//服务厅名称
			ActionContext.getContext().put("alls", all);//某服务厅容量
			
			List<BjdjTime> lists = timeService.getAll();
			List<List<BjdjRealTimeData>> list = new ArrayList<List<BjdjRealTimeData>>();
			for(int n = 0 ; n < 3; n++){
				
				List<BjdjRealTimeData> datas = new ArrayList<BjdjRealTimeData>();
				List<BjdjValidation> bjdjValidations = null;
				for(int i=0;i<lists.size();i++){
					
					BjdjTime time = lists.get(i);
					
					BjdjRealTimeData data = new BjdjRealTimeData();
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date today = new Date();
					today.setDate(today.getDate() + n);
					String now = sdf.format(today);
					String sta = now + " " + time.getStartTime();
					String end1 = now + " " + time.getEndTime();
					
					SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date start = sd.parse(sta);
					Date end = sd.parse(end1);
					
					//预约(根据航班时间)
					List<BjdjAppointment> appointments = appointmentService.getEnterHallByUseTimes(start, end);
					Integer online = 0;//线上预约数
					Integer offline = 0;//线下预约数
					for(BjdjAppointment appointment:appointments){
						if(appointment.getWay() == BjdjAppointmentType.online && appointment.getHall().getId().equals(hall.getId())){//线上并且是该厅的
							online += 1;
						}else if(appointment.getWay() == BjdjAppointmentType.offline && appointment.getHall().getId().equals(hall.getId())){//线下并且是该厅的
							offline += 1;
						}
					}
					Integer online_ = 0;//不重复算的线上预约数
					Integer offline_ = 0;//不重复算的线下预约数
					if(i > 0){
						
						for(BjdjAppointment appointment:appointments){
							
							if(appointment.getWay() == BjdjAppointmentType.online && 
									appointment.getHall().getId().equals(hall.getId())){//线上并且是该厅的
								
								if(appointment.getValidation() == null){
									
									online_++;
									continue;
								}else{
									
									boolean flag = true;
									for(BjdjValidation validation : bjdjValidations){
										
										if(validation.getId().equals(appointment.getValidation().getId())){//线下并且是该厅的
											
											flag = false;
										}
									}
									if(flag){
										online_++;
									}
								}
							}else if(appointment.getWay() == BjdjAppointmentType.offline && 
									appointment.getHall().getId().equals(hall.getId())){//线上并且是该厅的
								
								if(appointment.getValidation() == null){
									
									offline++;
									continue;
								}else{
									
									boolean flag = true;
									for(BjdjValidation validation : bjdjValidations){
										
										if(validation.getId().equals(appointment.getValidation().getId())){//线下并且是该厅的
											
											flag = false;
										}
									}
									if(flag){
										offline++;
									}
								}
							}
						}
					}
					
					Integer validationLength_ = 0;//验证时间超时的，自动放开座位占有权
					//如果在最迟90分钟的验证时间之后都未验证，则自动放出座位
					validationLength_ = appointmentService.validationTimeout(start, end, hall);
					
					//验证
					bjdjValidations = validationService.getEnterHallByTimes(start, end);
					Integer validationLength = 0;//该厅的验证数量
					for(BjdjValidation validation:bjdjValidations){
						BjdjAppointment appointment = validation.getAppointment();
						if(appointment.getHall().getId().equals(hall.getId())){//是该厅的
							validationLength += 1;
						}
					}
					
					//核销
					List<BjdjDispatch> bjdjDispatchs = dispatchService.getEnterHallByTimes(start, end);
					Integer dispatchListLength = 0;//该厅核销数量
					for(BjdjDispatch dispatch: bjdjDispatchs){
						BjdjValidation validation = dispatch.getValidation();
						BjdjAppointment appointment = validation.getAppointment();
						if(appointment.getHall().getId().equals(hall.getId()) && dispatch.getEnded()){//是该厅的，已核销的
							dispatchListLength += 1;
						}
					}
					
					//取消预约
					List<BjdjServiceCodeOperation> codeOperations = operationService.getEnterHallByUseTimes(start, end);
					Integer onlineUnactiveLength = 0;//线上取消预约数量
					Integer offlineUnactiveLength = 0;//线下取消预约数量
					for(BjdjServiceCodeOperation operation:codeOperations){
						if(operation.getFromMember() != null && operation.getAppointment().getHall().getId().equals(hall.getId())){//线上
							offlineUnactiveLength += 1;
						}else if(operation.getSystemUser() != null && operation.getAppointment().getHall().getId().equals(hall.getId())){//线下
							onlineUnactiveLength += 1;
						}
					}
					
					//实时座位数=公共池总数-（线上激活数+线下预约数-对应服务码验证数超时数）+线上激活取消数+线下预约取消数+核销数
					//实时座位数=实时座位数(上一时段)-（线上激活数+线下预约数-对应服务码验证数超时数）+线上激活取消数+线下预约取消数+核销数
					
					String date = DateUtil.formatDateToShortString_ZH_CN(today) + " " + DateUtil.dayOfWeek(today);
					data.setDate(date);
					data.setDispatchListLength(dispatchListLength);
					data.setEndTime(time.getEndTime());
					data.setStartTime(time.getStartTime());
					data.setOffline(offline);
					data.setOfflineUnactivelength(offlineUnactiveLength);
					data.setOnline(online);
					data.setOnlineUnactiveLength(onlineUnactiveLength);
					data.setValidationLength(validationLength);
					data.setValidationLength_(validationLength_);
					datas.add(data);
				}
				list.add(datas);
			}
			ActionContext.getContext().put("list", list);
			
			int surplus = appointmentService.surplus(new Date(), hall);
			surplus = surplus > all ? all : surplus;
			surplus = surplus < 0 ? 0 : surplus;
			ActionContext.getContext().put("real", surplus);
				
		}else{//如果登录的是后台管理员，则可以看到所有的实时数据
			List<BjdjHall> halls = hallService.queryAll(BjdjHall.class);
			Integer all = 0;
			for(BjdjHall hall : halls){
				all += hall.getCapacity();
			}
			ActionContext.getContext().put("alls", all);//公共池总数
			
			List<BjdjTime> lists = timeService.getAll();
			List<List<BjdjRealTimeData>> list = new ArrayList<List<BjdjRealTimeData>>();
			for(int n = 0 ; n < 3; n++){
				
				List<BjdjRealTimeData> datas = new ArrayList<BjdjRealTimeData>();
				List<BjdjValidation> bjdjValidations = null;
				for(int i=0;i<lists.size();i++){
					
					BjdjTime time = lists.get(i);
					
					BjdjRealTimeData data = new BjdjRealTimeData();
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date today = new Date();
					today.setDate(today.getDate() + n);
					String now = sdf.format(today);
					String sta = now + " " + time.getStartTime();
					String end1 = now + " " + time.getEndTime();
					
					SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date start = sd.parse(sta);
					Date end = sd.parse(end1);
					
					//预约
					List<BjdjAppointment> appointments = appointmentService.getByUseTimes(start, end);
					Integer online = 0;//线上预约数
					Integer offline = 0;//线下预约数
					for(BjdjAppointment appointment:appointments){
						if(appointment.getWay() == BjdjAppointmentType.online){
							online += 1;
						}else{
							offline += 1;
						}
					}
					//预约（进厅）
					List<BjdjAppointment> appointments2 = appointmentService.getEnterHallByUseTimes(start, end);
					Integer online_ = 0;//线上预约数
					Integer offline_ = 0;//线下预约数
					for(BjdjAppointment appointment:appointments2){
						if(appointment.getWay() == BjdjAppointmentType.online){//线上并且是该厅的
							online_ += 1;
						}else{//线下并且是该厅的
							offline_ += 1;
						}
					}
					Integer _online_ = 0;//不重复算的线上预约数
					Integer _offline_ = 0;//不重复算的线下预约数
					if(i > 0){
						
						for(BjdjAppointment appointment:appointments){
							
							if(appointment.getWay() == BjdjAppointmentType.online){//线上并且是该厅的
								
								if(appointment.getValidation() == null){
									
									_online_++;
									continue;
								}else{
									
									boolean flag = true;
									for(BjdjValidation validation : bjdjValidations){
										
										if(validation.getId().equals(appointment.getValidation().getId())){//线下并且是该厅的
											
											flag = false;
										}
									}
									if(flag){
										_online_++;
									}
								}
							}else if(appointment.getWay() == BjdjAppointmentType.offline){//线上并且是该厅的
								
								if(appointment.getValidation() == null){
									
									_offline_++;
									continue;
								}else{
									
									boolean flag = true;
									for(BjdjValidation validation : bjdjValidations){
										
										if(validation.getId().equals(appointment.getValidation().getId())){//线下并且是该厅的
											
											flag = false;
										}
									}
									if(flag){
										_offline_++;
									}
								}
							}
						}
					}
					//预约（根据预约时间）
//					List<BjdjAppointment> appointments3 = appointmentService.getEnterHallByTimes(start, end);
//					Integer _online_ = 0;//线上预约数
//					Integer _offline_ = 0;//线下预约数
//					for(BjdjAppointment appointment:appointments3){
//						if(appointment.getWay() == BjdjAppointmentType.online){//线上并且是该厅的
//							_online_ += 1;
//						}else{//线下并且是该厅的
//							_offline_ += 1;
//						}
//					}
					
					//验证
					bjdjValidations = validationService.getByTimes(start, end);
					Integer validationLength = bjdjValidations.size();//验证数量
					
					//如果在最迟90分钟的验证时间之后都未验证，自动放开座位占有权
					Integer validationLength_ = appointmentService.validationTimeout(start, end);
					//验证（进厅）
					validationLength_ += validationService.getEnterHallByTimes(start, end).size();
					
					//核销
					List<BjdjDispatch> bjdjDispatchs = dispatchService.getByTimes(start, end);
					Integer dispatchListLength = bjdjDispatchs.size();//核销数量
					//核销（进厅）
					List<BjdjDispatch> bjdjDispatchs2 = dispatchService.getEnterHallByTimes(start, end);
					Integer dispatchListLength_ = bjdjDispatchs2.size();//核销数量
					
					//取消预约
					List<BjdjServiceCodeOperation> codeOperations = operationService.getByUseTimes(start, end);
					Integer onlineUnactiveLength = 0;//线上取消预约数量
					Integer offlineUnactiveLength = 0;//线下取消预约数量
					for(BjdjServiceCodeOperation operation:codeOperations){
						
						if(operation.getFromMember() != null){
							offlineUnactiveLength += 1;
						}else if(operation.getSystemUser() != null){
							onlineUnactiveLength += 1;
						}
					}
					//取消预约(进厅)
					List<BjdjServiceCodeOperation> codeOperations2 = operationService.getEnterHallByUseTimes(start, end);
					Integer onlineUnactiveLength_ = 0;//线上取消预约数量
					Integer offlineUnactiveLength_ = 0;//线下取消预约数量
					for(BjdjServiceCodeOperation operation:codeOperations2){
							
						if(operation.getFromMember() != null){//线上
							onlineUnactiveLength_ += 1;
						}else if(operation.getSystemUser() != null){//线下
							offlineUnactiveLength_ += 1;
						}
					}
					
					//实时座位数=公共池总数-（线上激活数+线下预约数-对应服务码验证数超时数）+线上激活取消数+线下预约取消数+核销数
					//实时座位数=实时座位数(上一时段)-（线上激活数+线下预约数-对应服务码验证数超时数）+线上激活取消数+线下预约取消数+核销数
					
					String date = DateUtil.formatDateToShortString_ZH_CN(today) + " " + DateUtil.dayOfWeek(today);
					data.setDate(date);
					data.setEndTime(time.getEndTime());
					data.setStartTime(time.getStartTime());
					data.setOffline(offline);
					data.setOffline_(offline_);
					data.setOfflineUnactivelength(offlineUnactiveLength);
					data.setOfflineUnactivelength_(offlineUnactiveLength_);
					data.setOnline(online);
					data.setOnline_(online_);
					data.setOnlineUnactiveLength(onlineUnactiveLength);
					data.setOnlineUnactiveLength_(onlineUnactiveLength_);
					data.setValidationLength(validationLength);
					data.setValidationLength_(validationLength_);
					data.setDispatchListLength(dispatchListLength);
					data.setDispatchListLength_(dispatchListLength_);
					datas.add(data);
				}
				list.add(datas);
			}
			ActionContext.getContext().put("list", list);
			
			int surplus = all - appointmentService.waitNumber(new Date());
			surplus = surplus > all ? all : surplus;
			surplus = surplus < 0 ? 0 : surplus;
			ActionContext.getContext().put("real", surplus);
		}
		return "appointmentData";
	}
	
	/**
	 * 预约数据的统计
	 * @return
	 */
	public String realTimeData(){
		SystemUser systemUser = (SystemUser) ActionContext.getContext().getSession().get(ContextConstants.SCOPE_SYSTEM_USER);
		List<Object[]> list = new ArrayList<Object[]>();
		Object[] lastRow = new Object[4];
		//如果登录的是机场员工
		if(systemUser instanceof AirportEmployee){
			AirportEmployee airportEmployee = (AirportEmployee)systemUser;
			BjdjHall bjdjHall = airportEmployee.getHall();
			Object[] row = new Object[4];
			row[0] = bjdjHall.getNumber();
			row[1] = bjdjHall.getCapacity();
			row[2] = appointmentService.waitNumber(new Date(), bjdjHall);
			row[3] = (Integer)row[1] - (Integer)row[2] > 0 ? (Integer)row[1] - (Integer)row[2] : 0;
			list.add(row);
			
			lastRow[0] = "总体情况";
			lastRow[1] = 0;
			lastRow[2] = 0;
			lastRow[3] = 0;
			
			
			for(int i = 0; i < list.size(); i++){
				
				lastRow[1] = (Integer)lastRow[1] + (Integer)list.get(i)[1];
				lastRow[2] = (Integer)lastRow[2] + (Integer)list.get(i)[2];
				lastRow[3] = (Integer)lastRow[3] + (Integer)list.get(i)[3];
			}
			list.add(lastRow);
		}else{
			
			//全部
			List<BjdjHall> halls = hallService.queryAll(BjdjHall.class);
			for(int i = 0; i < halls.size(); i++){
				
				Object[] row = new Object[4];
				BjdjHall hall = halls.get(i);
				row[0] = hall.getNumber();
				row[1] = hall.getCapacity();
				row[2] = appointmentService.waitNumber(new Date(), hall);
				row[3] = (Integer)row[1] - (Integer)row[2] > 0 ? (Integer)row[1] - (Integer)row[2] : 0;
				list.add(row);
			}
			
			
			lastRow[0] = "总体情况";
			lastRow[1] = 0;
			lastRow[2] = 0;
			lastRow[3] = 0;
			for(int i = 0; i < list.size(); i++){
				
				lastRow[1] = (Integer)lastRow[1] + (Integer)list.get(i)[1];
				lastRow[2] = (Integer)lastRow[2] + (Integer)list.get(i)[2];
				lastRow[3] = (Integer)lastRow[3] + (Integer)list.get(i)[3];
			}
			list.add(lastRow);
		}
		
		ActionContext.getContext().put("list", list);
		return "realTimeDataPage";
	}
	
	/**
	 * 管理便捷登机预约表实体
	 * @Title: BjdjAppointmentAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		SystemUser systemUser = getSessionAdminUser();
		
		SqlParamGroup group = new SqlParamGroup("s.status.deleteFlag", Condition.eq, ContextConstants.STATUS_OF_ZERO).and("s.status.versionFlag", Condition.eq, versionFlag);
		if(systemUser instanceof AirportEmployee){//登录的是机场员工
			
			AirportEmployee airportEmployee = (AirportEmployee)systemUser;
			BjdjHall bjdjHall = airportEmployee.getHall();
			group.and("s.hall.id", Condition.ge, bjdjHall.getId());
		}
		group.and("s.flightNo", Condition.like, flightNo);
		group.and("s.time", Condition.ge, startTime);
		group.and("s.time", Condition.le, endTime);
		group.and("s.useTime", Condition.ge, startTime2);
		group.and("s.useTime", Condition.le, endTime2);
		group.and("s.serviceCode.code", Condition.like_left, code);
		group.and("s.IDCard", Condition.like_left, IDCard);
		group.and("s.name", Condition.like_left, name);
		group.and("s.serviceCode.member.phone", Condition.like_left, mobile);
		group.and("s.serviceCode.packageType.name", Condition.eq, packageType);
		group.and("s.serviceCode.state.id", Condition.eq, state_id);
		group.and("s.way", Condition.eq, type);
		this.setPageModule(appointmentService.paginationQuery("select s from " + BjdjAppointment.class.getName() + " s " + group.toString(true) + " order by s.status.createTime desc", group.getParamArray()));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageBjdjAppointment";
	}
	
	/**
	 * 取消激活
	 * @return
	 * @throws ServiceException
	 */
	public String unActivate(){
		List<BjdjAppointment> appointments = null;
		try {
			appointments = appointmentService.getByIdsValue(idsValue);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		if(appointments.size()>0){
			for(BjdjAppointment appointment: appointments){
				BjdjServiceCode code = appointment.getServiceCode();
				//取消延误标志
				String id = code.getId();
				try {
					appointmentService.unActivate(id, getSessionAdminUser());
				} catch (ServiceException e) {
					data = AjaxData.responseError(e.getMessage());
					return JSON;
				}
			}
		}
		data = AjaxData.responseSuccess(getText("serviceCode.unactivate.success"));
		return JSON;
	}
	/**
	 * 查看回收站
	 * @Title: BjdjAppointmentAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(appointmentService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleBjdjAppointment";
	}
	
	/**
	 * 逻辑删除便捷登机预约表对象
	 * @Title: BjdjAppointmentAction
	 * @Description: 把便捷登机预约表对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = appointmentService.logicDeleteEntity(BjdjAppointment.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除便捷登机预约表对象
	 * @Title: BjdjAppointmentAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = appointmentService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个便捷登机预约表对象
	 * @Title: BjdjAppointmentAction
	 * @Description: 从回收站还原便捷登机预约表对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = appointmentService.restoreEntity(BjdjAppointment.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核便捷登机预约表对象
	 * @Title: BjdjAppointmentAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = appointmentService.auditEntity(BjdjAppointment.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: BjdjAppointmentAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = appointmentService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	/**
	 * 批量删除预约表
	 */
	public String batchRealDelete() throws ServiceException {
		boolean isSuc = baseService.batchRealDelete(BjdjAppointment.class, idsValue);
		if(isSuc) {
			String logContent = "批量删除预约表";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public BjdjAppointment getBjdjAppointment() {
		return appointment;
	}
	public void setBjdjAppointment(BjdjAppointment bjdjAppointment) {
		this.appointment = bjdjAppointment;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public BjdjAppointmentType getWay() {
		return way;
	}
	public void setWay(BjdjAppointmentType way) {
		this.way = way;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Date getUseTime() {
		return useTime;
	}
	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}
	public BjdjServiceCode getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(BjdjServiceCode serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIDCard() {
		return IDCard;
	}
	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getLuggage() {
		return luggage;
	}
	public void setLuggage(String luggage) {
		this.luggage = luggage;
	}

	public IBjdjAppointmentService getAppointmentService() {
		return appointmentService;
	}

	public void setAppointmentService(IBjdjAppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}

	public ISystemDictionaryService getDictionaryService() {
		return dictionaryService;
	}

	public void setDictionaryService(ISystemDictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	public BjdjAppointment getAppointment() {
		return appointment;
	}

	public void setAppointment(BjdjAppointment appointment) {
		this.appointment = appointment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IBjdjValidationService getValidationService() {
		return validationService;
	}

	public void setValidationService(IBjdjValidationService validationService) {
		this.validationService = validationService;
	}

	public IBjdjDispatchService getDispatchService() {
		return dispatchService;
	}

	public void setDispatchService(IBjdjDispatchService dispatchService) {
		this.dispatchService = dispatchService;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public ISystemOperationLogService getLogService() {
		return logService;
	}

	public void setLogService(ISystemOperationLogService logService) {
		this.logService = logService;
	}

	public IBjdjHallService getHallService() {
		return hallService;
	}

	public void setHallService(IBjdjHallService hallService) {
		this.hallService = hallService;
	}

	public IBjdjTimeService getTimeService() {
		return timeService;
	}

	public void setTimeService(IBjdjTimeService timeService) {
		this.timeService = timeService;
	}

	public IBjdjDispatchListService getDispatchListService() {
		return dispatchListService;
	}

	public void setDispatchListService(IBjdjDispatchListService dispatchListService) {
		this.dispatchListService = dispatchListService;
	}

	public IBjdjServiceCodeOperationService getOperationService() {
		return operationService;
	}

	public void setOperationService(
			IBjdjServiceCodeOperationService operationService) {
		this.operationService = operationService;
	}

	public IBjdjServiceItemService getServiceItemService() {
		return serviceItemService;
	}

	public void setServiceItemService(IBjdjServiceItemService serviceItemService) {
		this.serviceItemService = serviceItemService;
	}

	public IBjdjServicePackageService getServicePackageService() {
		return servicePackageService;
	}

	public void setServicePackageService(
			IBjdjServicePackageService servicePackageService) {
		this.servicePackageService = servicePackageService;
	}

	public IBjdjServicePackageItemService getPackageItemService() {
		return packageItemService;
	}

	public void setPackageItemService(
			IBjdjServicePackageItemService packageItemService) {
		this.packageItemService = packageItemService;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BjdjAppointmentType getType() {
		return type;
	}

	public void setType(BjdjAppointmentType type) {
		this.type = type;
	}

	public String getState_id() {
		return state_id;
	}

	public void setState_id(String state_id) {
		this.state_id = state_id;
	}

	public Date getEndTime2() {
		return endTime2;
	}

	public void setEndTime2(Date endTime2) {
		this.endTime2 = endTime2;
	}

	public Date getStartTime2() {
		return startTime2;
	}

	public void setStartTime2(Date startTime2) {
		this.startTime2 = startTime2;
	}
}
