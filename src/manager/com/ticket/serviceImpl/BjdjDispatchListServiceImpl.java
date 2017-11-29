package com.ticket.serviceImpl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.ticket.enumer.BjdjCheckWay;
import com.ticket.enumer.BjdjDispatchListState;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.AirportEmployee;
import com.ticket.pojo.BjdjAppointment;
import com.ticket.pojo.BjdjDispatch;
import com.ticket.pojo.BjdjDispatchList;
import com.ticket.pojo.BjdjServiceCode;
import com.ticket.pojo.BjdjServiceItem;
import com.ticket.pojo.BjdjValidation;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.SystemDictionary;
import com.ticket.service.IBjdjAppointmentService;
import com.ticket.service.IBjdjDispatchListService;
import com.ticket.service.IBjdjDispatchService;
import com.ticket.service.IBjdjHallService;
import com.ticket.service.IBjdjServiceCodeService;
import com.ticket.service.IBjdjValidationService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.util.DateUtil;
import com.ticket.util.ResourceUtil;

/**
 * 分单流程表业务接口实现类
 * @ClassName: IBjdjDispatchListService   
 * @Description: 提供分单流程表操作的增删改查   
 * @author HiSay  
 * @date 2015-11-23 22:55:31
 *
 */
public class BjdjDispatchListServiceImpl extends BaseServiceImpl<BjdjDispatchList, String> implements IBjdjDispatchListService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(BjdjDispatchListServiceImpl.class);
	@Resource
	private ISystemDictionaryService dictionaryService;
	@Resource
	private IBjdjDispatchService dispatchService;
	@Resource
	private IBjdjServiceCodeService serviceCodeService;
	@Resource
	private IBjdjValidationService validationService;
	@Resource
	private IBjdjAppointmentService appointmentService;
	@Resource
	private IBjdjHallService hallService;
	
	@Override
	public BjdjDispatchList persist(String dispatch_id,String employee_id,BjdjServiceItem info,Integer sequence) throws ServiceException {
		
		return persist(new BjdjDispatch(dispatch_id), get(AirportEmployee.class, employee_id), info, sequence);
	}
	
	@Override
	public BjdjDispatchList persist(BjdjDispatch dispatch,AirportEmployee employee,BjdjServiceItem info,Integer sequence) throws ServiceException {
		
		BjdjDispatchList bjdjDispatchList = new BjdjDispatchList();
		bjdjDispatchList.setDispatch(dispatch);
		bjdjDispatchList.setEmployee(employee);
		bjdjDispatchList.setSequence(sequence);
		bjdjDispatchList.setState(BjdjDispatchListState.wait);
		bjdjDispatchList.setInfo(info);
		
		CommonEntity status = bjdjDispatchList.getStatus();
		status.setVersionFlag(versionFlag);
		bjdjDispatchList.setStatus(status);
		dbDAO.persist(bjdjDispatchList);
		
		return bjdjDispatchList;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public BjdjDispatchList acceptDispatchList(String dispatchList_id)throws ServiceException {
		
		if(dispatchList_id == null){
			
			new ServiceException(ResourceUtil.getText("dispatch.id.required"));
		}
		BjdjDispatchList dispatchList = dbDAO.get(BjdjDispatchList.class, dispatchList_id);
		return accept(dispatchList);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public BjdjDispatchList accept(BjdjDispatchList dispatchList)throws ServiceException {

		if(dispatchList == null){
			
			new ServiceException(ResourceUtil.getText("dispatch.id.error"));
		}
		if(dispatchList.getState() != BjdjDispatchListState.wait){
			
			new ServiceException(ResourceUtil.getText("dispatch.ing"));
		}
		dispatchList.setState(BjdjDispatchListState.ing);
		merge(dispatchList);
		return dispatchList;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean verification(String id,String feedback,BjdjCheckWay way) throws ServiceException{
	
		if(id == null){
			
			throw new ServiceException(ResourceUtil.getText("dispatch.id.required"));
		}
		BjdjDispatchList dispatchList = dbDAO.queryById(this.getTableNameFromEntity(BjdjDispatchList.class), id);
		boolean flag =  verification(dispatchList, feedback, way);
		return flag;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean verification(BjdjDispatchList dispatchList,String feedback,BjdjCheckWay way) throws ServiceException{
		
		if(dispatchList == null){
			
			throw new ServiceException(ResourceUtil.getText("dispatch.id.error"));
		}
		if(dispatchList.getState() != BjdjDispatchListState.ing){
			
			throw new ServiceException(ResourceUtil.getText("dispatch.ended"));
		}
		if(!canVerificate(dispatchList)){
			
			throw new ServiceException(ResourceUtil.getText("dispatch.previous.noEnded"));
		}
		
		dispatchList.setState(BjdjDispatchListState.ended);
		dispatchList.setTime(new Date());
		dispatchList.setFeedback(feedback);
		dispatchList.setWay(way);
		dbDAO.merge(dispatchList);
		
		//检查是否全部流程核销完毕
		dispatchService.verificateIfAllEnded(dispatchList.getDispatch());

		//删除排队
//		if(dispatchList.getInfo().getName().contains("厅内服务")
//				|| dispatchList.getInfo().getValue().contains("厅内服务")){
//			
//			BjdjServiceCode serviceCode = null;
//			if(dispatchList.getDispatch().getValidation().getType() == BjdjOrderType.bjdj){
//				
//				serviceCode = dispatchList.getDispatch().getValidation().getAppointment().getServiceCode();
//			}else{
//				serviceCode = dispatchList.getDispatch().getValidation().getServiceCode();
//			}
//			queueService.exitQuence(serviceCode);
//		}

		return true;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void systemVerification(String flightNumber, String flightDate) throws ServiceException {

		//验证分单，设置服务码的状态为已使用
		SystemDictionary used = dictionaryService.getByName("used");
		//大于登机时间指定分钟的预约分单，设置服务码的状态为未使用
		SystemDictionary unused = dictionaryService.getByName("unused");
		List<BjdjAppointment> apps = appointmentService.get(flightNumber, DateUtil.parseShortStringToDate(flightDate));
		for(BjdjAppointment a : apps){
			
			if(a.getValidation() == null){
				
				a.getServiceCode().setState(unused);
				serviceCodeService.merge(a.getServiceCode());
				
				a.setServiceCode(null);
				appointmentService.merge(a);
			}else{
				
				a.getServiceCode().setState(used);
				serviceCodeService.merge(a.getServiceCode());
				if(a.getValidation().getDispatch() != null){
					
					for(BjdjDispatchList dispatchList : a.getValidation().getDispatch().getDispatchList()){
						
						try {
							verification(dispatchList, null, BjdjCheckWay.system);
						} catch (Exception e) {
						}
					}
				}
			}
		}
		
//		String serviceCodeName = BjdjServiceCode.class.getName();
//		String appointmentName = BjdjAppointment.class.getName();
//		String validationName = BjdjValidation.class.getName();
//		String dispatchName = BjdjDispatch.class.getName();
//		String dispatchListName = BjdjDispatchList.class.getName();
//		
//		StringBuffer sb = new StringBuffer();
//		StringBuffer serviceCodeValidedIds = new StringBuffer(); 
//		StringBuffer serviceCodeNoValidIds = new StringBuffer(); 
//		List<BjdjAppointment> apps = dbDAO.executeJPQLForQuery("select a from " + appointmentName + " a", BjdjAppointment.class);
//		Date now = new Date();
//		for(BjdjAppointment a : apps){
//			
//			Long nowTimeStamp = now.getTime();
//			Long expireTimeStamp = a.getUseTime().getTime() + 5 * 60 * 1000;
//			//检验预约是否超时
//			if(expireTimeStamp < nowTimeStamp){
//				
//				String sql = "select dl from "+validationName+" v,"+dispatchName+" d,"+dispatchListName+" dl"
//						+ " where v.appointment=? and v=d.validation and d=dl.dispatch and (dl.state=? or dl.state=?)";
//				
//				List<BjdjDispatchList> dls = dbDAO.executeJPQLForQuery(sql, BjdjDispatchList.class, a, BjdjDispatchListState.wait, BjdjDispatchListState.ing);
//				
//				for(BjdjDispatchList dl : dls){
//					
//					sb.append("'");
//					sb.append(dl.getId());
//					sb.append("',");
//				}
//				
//				if(validationService.isPassedValidatation(a)){
//					
//					serviceCodeValidedIds.append("'").append(a.getServiceCode().getId()).append("',");
//				}else{
//					
//					serviceCodeNoValidIds.append("'").append(a.getServiceCode().getId()).append("',");
//				}
//				
//				queueService.exitQuence(a.getServiceCode());
//			}
//		}
//		if(sb != null && sb.length() > 0){
//			
//			//大于登机时间指定分钟的预约分单，全部系统核销
//			String sql = "update " + dispatchListName + " dl set dl.state=?,dl.time=?,dl.way=? "
//					+ "where id in (" + sb.substring(0, sb.length()-1) + ")";
//			dbDAO.executeJPQLForUpdate(sql, BjdjDispatchListState.ended, new Date(), BjdjCheckWay.system);
//			
//			//如果分单子项全部核销，则分单大项要设置状态为“完毕”
//			sql = "update "+dispatchName+" set ended=? and dispatchTime = ? where id in(select id from "+dispatchName+" d,"+dispatchListName+" dl"
//					+ " where dl.dispatch=d and dl.state=? and d.ended=?"
//					+ " group by d.id"
//					+ " having count(d.id)=4)";
//			dbDAO.executeJPQLForUpdate(sql, true, new Date(), BjdjDispatchListState.ended, false);
//		}
		
//		//大于登机时间指定分钟的验证分单，设置服务码的状态为已使用
//		SystemDictionary used = dictionaryService.getByName("used");
//		if(serviceCodeValidedIds != null && serviceCodeValidedIds.length() > 0){
//			
//			String sql = "update " + serviceCodeName + " dl set dl.state=? "
//					+ "where id in (" + serviceCodeValidedIds.substring(0, serviceCodeValidedIds.length()-1) + ")";
//			dbDAO.executeJPQLForUpdate(sql, used);
//		}
//		
//		//大于登机时间指定分钟的预约分单，设置服务码的状态为未使用
//		SystemDictionary unused = dictionaryService.getByName("unused");
//		if(serviceCodeNoValidIds != null && serviceCodeNoValidIds.length() > 0){
//			
//			String sql = "update " + serviceCodeName + " dl set dl.state=? "
//					+ "where id in (" + serviceCodeNoValidIds.substring(0, serviceCodeNoValidIds.length()-1) + ")";
//			dbDAO.executeJPQLForUpdate(sql, unused);
//		}
	}
	
	@Override
	public boolean remove(String id) throws ServiceException {
		
		batchRealDelete(BjdjDispatchList.class, id);
		return true;
	}

	@Override
	public Long acceptAmount(AirportEmployee employee) {
		
		Long amount = (Long)dbDAO.executeJPQLForQuerySingle(
				"select count(dl) from " + BjdjDispatchList.class.getName() + " dl where dl.employee=? and dl.state=?", 
				employee, BjdjDispatchListState.ing);
		return amount;
	}

	@Override
	public Long noAcceptAmount(AirportEmployee employee) {
		
		Long amount = (Long)dbDAO.executeJPQLForQuerySingle(
				"select count(dl) from " + BjdjDispatchList.class.getName() + " dl where dl.employee=? and dl.state=?", 
				employee, BjdjDispatchListState.wait);
		return amount;
	}

	@Override
	public List<BjdjDispatchList> acceptDispatchList(AirportEmployee employee) {
		
		List<BjdjDispatchList> list = dbDAO.executeJPQLForQueryEntity(
				"select dl from " + BjdjDispatchList.class.getName() + " dl where dl.employee=? and dl.state=?", 
				employee, BjdjDispatchListState.ing);
		return list;
	}

	@Override
	public List<BjdjDispatchList> noAcceptDispatchList(AirportEmployee employee) {
		
		List<BjdjDispatchList> list = dbDAO.executeJPQLForQueryEntity(
				"select dl from " + BjdjDispatchList.class.getName() + " dl where dl.employee=? and dl.state=?", 
				employee, BjdjDispatchListState.wait);
		return list;
	}

	@Override
	public BjdjDispatchList previous(BjdjDispatchList dispatchList) {
		
		Integer sequence = dispatchList.getSequence();
		BjdjDispatchList list = dbDAO.executeJPQLForQuerySingle(
				"select dl from " + BjdjDispatchList.class.getName() + " dl where dl.dispatch=? and dl.sequence=?", 
				BjdjDispatchList.class, dispatchList.getDispatch(), --sequence);
		return list;
	}

	@Override
	public boolean canVerificate(BjdjDispatchList dispatchList) {
		
		if(dispatchList.getSequence() <= 2){
			
			return true;
		}
		List<BjdjDispatchList> list = dbDAO.executeJPQLForQueryEntity(
				"select s from " + BjdjDispatchList.class.getName() + " s where s.dispatch=? order by s.sequence", 
				dispatchList.getDispatch());
		for(int i = 0; i < dispatchList.getSequence()-1; i++){
			
			if(list.get(i).getState() != BjdjDispatchListState.ended){
				
				return false;
			}
		}
		return true;
	}

	@Override
	public Long endedAcceptAmount(AirportEmployee employee) {
		
		Long amount = (Long)dbDAO.executeJPQLForQuerySingle(
				"select count(dl) from " + BjdjDispatchList.class.getName() + " dl where dl.employee=? and dl.state=?", 
				employee, BjdjDispatchListState.ended);
		return amount;
	}

	@Override
	public List<BjdjDispatchList> endedDispatchList(AirportEmployee employee) {
		
		List<BjdjDispatchList> list = dbDAO.executeJPQLForQueryEntity(
				"select dl from " + BjdjDispatchList.class.getName() + " dl where dl.employee=? and dl.state=?", 
				employee, BjdjDispatchListState.ended);
		return list;
	}

	@Override
	public List<BjdjDispatchList> getByTimes(Date startTime, Date endTime) throws ServiceException {
		
		List<BjdjDispatchList> bjdjDispatchLists = dbDAO.executeJPQLForQueryEntity(
				"select c from " + BjdjDispatchList.class.getName() + " c where c.time >= ? and c.time <= ?", 
				startTime, endTime);
		return bjdjDispatchLists;
	}

	@Override
	public List<Object[]> serviceGather(Date startDate, Date endDate) {
		
		List<Object[]> list = new ArrayList<Object[]>();
		if(startDate == null || endDate == null){
			
			return list;
		}
		endDate = DateUtil.getDayEnd(endDate);
		
		String dlName = BjdjDispatchList.class.getAnnotation(Table.class).name();
		String eName = AirportEmployee.class.getAnnotation(Table.class).name();
		
		list =  dbDAO.executeSQLQueryNoCache("select dl.employee_id, date(dl.createTime), e.name"
				+ " from " + dlName + " dl"
				+ " join " + eName + " e on e.id=dl.employee_id"
				+ " where dl.createTime between ? and ?"
				+ " group by dl.employee_id, date(dl.createTime)" //根据每个人每天服务项分组
				+ " order by date(dl.createTime) desc", startDate, endDate);
		
		List<Object[]> newList = new ArrayList<Object[]>(list.size());
		List<BjdjServiceItem> serviceItems = queryAll(BjdjServiceItem.class);
		for(Object[] personalDay : list){
			
			Object[] personalService = new Object[3 + serviceItems.size()];
			personalService[0] = personalDay[0];
			personalService[1] = personalDay[1];
			personalService[2] = personalDay[2];
			
			int index = 3;
			for(BjdjServiceItem serviceItem : serviceItems){
				
				personalService[index] = dbDAO.executeSQLQueryNoCache("select count(*) from " + dlName + " s"
						+ " where date(s.createTime)=date(?) and s.employee_id=? and s.info_id=?", personalDay[1], personalDay[0], serviceItem.getId());
				index++;
			}
			newList.add(personalService);
		}
		return newList;
	}

	@Override
	public List<Object[]> serviceDetail(Date startDate, Date endDate) {
		
		List<Object[]> list = new ArrayList<Object[]>();
		if(startDate == null || endDate == null){
			
			return list;
		}
		endDate = DateUtil.getDayEnd(endDate);
		
		String dlName = BjdjDispatchList.class.getAnnotation(Table.class).name();
		String eName = AirportEmployee.class.getAnnotation(Table.class).name();
		String dName = BjdjDispatch.class.getAnnotation(Table.class).name();
		String vName = BjdjValidation.class.getAnnotation(Table.class).name();
		String aName = BjdjAppointment.class.getAnnotation(Table.class).name();
		String scName = BjdjServiceCode.class.getAnnotation(Table.class).name();
		
		list =  dbDAO.executeSQLQueryNoCache("select dl.employee_id, date(dl.createTime) date, e.name, sc.code"
				+ " from " + dlName + " dl"
				+ " join " + eName + " e on e.id=dl.employee_id"
				+ " join " + dName + " d on d.id=dl.dispatch_id"
				+ " join " + vName + " v on v.id=d.validation_id"
				+ " join " + aName + " a on a.id=v.appointment_id"
				+ " join " + scName + " sc on sc.id=a.serviceCode_id"
				+ " where dl.createTime between ? and ?"
				+ " group by dl.employee_id, date(dl.createTime)"
				+ " order by date(dl.createTime) desc", startDate, endDate);
		
		List<Object[]> newList = new ArrayList<Object[]>(list.size());
		List<BjdjServiceItem> serviceItems = queryAll(BjdjServiceItem.class);
		for(Object[] personalDay : list){
			
			Object[] personalService = new Object[3 + serviceItems.size()];
			personalService[0] = personalDay[0];
			personalService[1] = personalDay[1];
			personalService[2] = personalDay[2];
			
			int index = 3;
			for(BjdjServiceItem serviceItem : serviceItems){
				
				personalService[index] = dbDAO.executeSQLQueryNoCache("select count(*) from " + dlName + " s"
						+ " where date(s.createTime)=date(?) and s.employee_id=? and s.info_id=?", personalDay[1], personalDay[0], serviceItem.getId());
				index++;
			}
			newList.add(personalService);
		}
		return newList;
	}
}