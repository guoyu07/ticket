package com.ticket.serviceImpl;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.ticket.enumer.BjdjCheckWay;
import com.ticket.enumer.BjdjDispatchListState;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.AirportEmployee;
import com.ticket.pojo.BjdjDispatch;
import com.ticket.pojo.BjdjDispatchList;
import com.ticket.pojo.BjdjServiceCode;
import com.ticket.pojo.BjdjServiceItem;
import com.ticket.pojo.BjdjValidation;
import com.ticket.pojo.CommonEntity;
import com.ticket.service.IAirportEmployeeService;
import com.ticket.service.IBjdjDispatchListService;
import com.ticket.service.IBjdjDispatchService;
import com.ticket.service.IBjdjServiceCodeService;
import com.ticket.service.IBjdjValidationService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.ResourceUtil;

/**
 * 便捷登机分单表业务接口实现类
 * @ClassName: IBjdjDispatchService   
 * @Description: 提供便捷登机分单表操作的增删改查   
 * @author HiSay  
 * @date 2015-11-23 22:53:55
 *
 */
public class BjdjDispatchServiceImpl extends BaseServiceImpl<BjdjDispatch, String> implements IBjdjDispatchService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(BjdjDispatchServiceImpl.class);
	@Resource
	private IBjdjDispatchListService dispatchListService;
	@Resource
	private IAirportEmployeeService employeeService;
	@Resource
	private IBjdjValidationService validationService;
	@Resource
	private ISystemDictionaryService dictionaryService;
	@Resource
	private IBjdjServiceCodeService serviceCodeService;
	
	@Override
	public BjdjDispatch persist(BjdjValidation validation, String[] items_id, String[] employee_id) throws ServiceException {

		return persist(validation.getId(), items_id, employee_id);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public BjdjDispatch persist(String validation_id, String[] items_id, String[] employee_id) throws ServiceException {
		
		BjdjDispatch bjdjDispatch = new BjdjDispatch();
		bjdjDispatch.setValidation(validationService.get(BjdjValidation.class, validation_id));
		bjdjDispatch.setTime(new Date());
		bjdjDispatch.setBoardingGate(null);
		bjdjDispatch.setEnded(false);
		if(getSystemUser() instanceof AirportEmployee){
			
			bjdjDispatch.setEmployee((AirportEmployee)getSystemUser());
		}
		dbDAO.persist(bjdjDispatch);
		
		if(items_id != null && employee_id != null && items_id.length != employee_id.length){
			
			throw new ServiceException("分单项未全部分配");
		}
		
		List<BjdjDispatchList> dispatchList = new ArrayList<BjdjDispatchList>();
		for(int i = 0; i < employee_id.length; i++){
			
			BjdjDispatchList disList = new BjdjDispatchList();
			disList.setEmployee(employeeService.get(AirportEmployee.class,employee_id[i].trim()));
			disList.setDispatch(bjdjDispatch);
			disList.setState(BjdjDispatchListState.wait);
			disList.setSequence(i+1);
			
			BjdjServiceItem item = get(BjdjServiceItem.class, items_id[i]);
			disList.setInfo(item);
			dispatchList.add(disList);
			dbDAO.persist(disList);
		}
		bjdjDispatch.setDispatchList(dispatchList);
		
		//设置服务码状态
		BjdjServiceCode serviceCode = null;
//		if(bjdjDispatch.getValidation().getType() == BjdjOrderType.bjdj){
			
			serviceCode = bjdjDispatch.getValidation().getAppointment().getServiceCode();
//		}else{
//			
//			serviceCode = bjdjDispatch.getValidation().getServiceCode();
//		}
		serviceCode.setState(dictionaryService.getByName("dispatched"));
		serviceCodeService.merge(serviceCode);
		
		return bjdjDispatch;
	}
	
//	@Override
//	public BjdjDispatch persistForElectromoible(BjdjValidation validation, String...employee_id) throws ServiceException {
//		
//		return persist(validation.getId(), employee_id);
//	}
	
//	@Override
//	@Transactional(rollbackFor=Exception.class)
//	public BjdjDispatch persistForElectromoible(String validation_id, String...employee_id) throws ServiceException {
//		
//		BjdjDispatch bjdjDispatch = new BjdjDispatch();
//		List<BjdjServiceItem> items = dictionaryService.queryAll(BjdjServiceItem.class);
//		
//		List<BjdjDispatchList> dispatchList = new ArrayList<BjdjDispatchList>();
//		for(int i = 0; i < employee_id.length; i++){
//			
//			BjdjDispatchList disList = new BjdjDispatchList();
//			disList.setEmployee(employeeService.queryById(AirportEmployee.class.getName(),employee_id[i].trim()));
//			disList.setDispatch(bjdjDispatch);
//			disList.setState(BjdjDispatchListState.wait);
//			disList.setSequence(i+1);
//			disList.setInfo(items.get(i));
//			dispatchList.add(disList);
//		}
//		
//		bjdjDispatch.setDispatchList(dispatchList);
//		bjdjDispatch.setValidation(validationService.queryById(BjdjValidation.class.getName(), validation_id));
//		bjdjDispatch.setTime(new Date());
//		bjdjDispatch.setBoardingGate(null);
//		bjdjDispatch.setEnded(false);
//		CommonEntity status = bjdjDispatch.getStatus();
//		status.setVersionFlag(versionFlag);
//		bjdjDispatch.setStatus(status);
//		dbDAO.persist(bjdjDispatch);
//		
//		//设置服务码状态
//		BjdjServiceCode serviceCode = bjdjDispatch.getValidation().getServiceCode();
//		serviceCode.setState(dictionaryService.getByName("dispatched"));
//		serviceCodeService.merge(serviceCode);
//		
//		return bjdjDispatch;
//	}

	@Override
	public BjdjDispatch merge(String id,BjdjValidation validation, Date time, String boardingGate, boolean ended) throws ServiceException {
		
		return merge(id, validation.getId(), time, boardingGate, ended);
	}
	
	@Override
	public BjdjDispatch merge(String id,String validation_id, Date time, String boardingGate, boolean ended) throws ServiceException {
		BjdjDispatch bjdjDispatch = dbDAO.queryById(this.getTableNameFromEntity(BjdjDispatch.class), id);
		bjdjDispatch.setValidation(new BjdjValidation(validation_id));
		bjdjDispatch.setTime(time);
		bjdjDispatch.setBoardingGate(DecoderUtil.UtfDecoder(boardingGate));
		bjdjDispatch.setEnded(ended);
		CommonEntity status = bjdjDispatch.getStatus();
		status.setVersionFlag(versionFlag);
		bjdjDispatch.setStatus(status);
		dbDAO.merge(bjdjDispatch);
		return bjdjDispatch;
	}
	
	@Override
	public boolean remove(String id) throws ServiceException {
		BjdjDispatch bjdjDispatch = dbDAO.queryById(this.getTableNameFromEntity(BjdjDispatch.class), id);
		dbDAO.remove(bjdjDispatch);
		return true;
	}
	
	@Override
	public int receiveTotalAmount(String id) {
		
		AirportEmployee employee = null;
		try {
			employee = employeeService.queryById(AirportEmployee.class.getName(), id);
		} catch (ServiceException e) {
			e.printStackTrace();
			return 0;
		}
		Long amount = (Long)dbDAO.executeJPQLForQuerySingle(
				"select count(d) from " + BjdjDispatchList.class.getName() + " d where d.employee=?", 
				employee);
		return amount.intValue();
	}
	
	@Override
	public int receiveAmountToday(String id) {
		
		AirportEmployee employee = null;
		try {
			employee = employeeService.queryById(AirportEmployee.class.getName(), id);
		} catch (ServiceException e) {
			e.printStackTrace();
			return 0;
		}
		Calendar c = Calendar.getInstance();
		Long amount = (Long)dbDAO.executeJPQLForQuerySingle(
				"select count(d) from " + BjdjDispatchList.class.getName() + " d where d.employee=?"
				+ " and YEAR(d.status.createTime)=? and MONTH(d.status.createTime)=? and DAY(d.status.createTime)=?", 
				employee, c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DATE));
		return amount.intValue();
	}
	
	@Override
	public boolean isDispatch(String validation_id) {
		
		BjdjValidation validation;
		try {
			validation = validationService.queryById(BjdjValidation.class.getName(), validation_id);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		if(validation.getDispatch() != null){
			
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean verificateIfAllEnded(BjdjDispatch dispatch){
		
		//检查是否全部流程核销完毕
		List<BjdjDispatchList> allList = dispatch.getDispatchList();
		boolean allVerificate = true;
		for(BjdjDispatchList dList : allList){
			
			if(dList.getState() != BjdjDispatchListState.ended){

				allVerificate = false;
			}
		}
		if(allVerificate){
			
			//核销整个分单
			dispatch.setEnded(true);
			dispatch.setDispatchTime(new Date());
			merge(dispatch);
			
			//设置服务码状态
			BjdjServiceCode serviceCode = null;
//			if(dispatch.getValidation().getType() == BjdjOrderType.bjdj){
				
				serviceCode = dispatch.getValidation().getAppointment().getServiceCode();
//			}else{
//				serviceCode = dispatch.getValidation().getServiceCode();
//			}
			serviceCode.setState(dictionaryService.getByName("used"));
			try {
				serviceCodeService.merge(serviceCode);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		return allVerificate;
	}

	@Override
	public BjdjDispatch findByValidationId(String validationId) {
		BjdjDispatch object = (BjdjDispatch)dbDAO.executeJPQLForQuerySingle("select c from " + BjdjDispatch.class.getName() + " c where c.validation.id = ?", validationId);
		
		return object;
	}

	@Override
	public List<BjdjDispatch> getByTimes(Date start, Date end) {
		List<BjdjDispatch> bjdjDispatchs = dbDAO.executeJPQLForQueryEntity(
				"select c from " + BjdjDispatch.class.getName() + " c"
				+ " where c.validation.appointment.hall is null and c.dispatchTime >= ? and c.dispatchTime <= ?",start,end);
		return bjdjDispatchs;
	}
	
	@Override
	public List<BjdjDispatch> getEnterHallByTimes(Date start, Date end) {
		List<BjdjDispatch> bjdjDispatchs = dbDAO.executeJPQLForQueryEntity(
				"select c from " + BjdjDispatch.class.getName() + " c"
						+ " where c.validation.appointment.hall is not null and c.dispatchTime >= ? and c.dispatchTime <= ?",start,end);
		return bjdjDispatchs;
	}

	@Override
	public List<BjdjDispatchList> waitList(BjdjDispatch dispatch) {
		
		List<BjdjDispatchList> list = new ArrayList<BjdjDispatchList>();
		for(BjdjDispatchList dispatchList : dispatch.getDispatchList()){

			if(dispatchList.getState() == BjdjDispatchListState.wait){
				
				list.add(dispatchList);
			}
		}
		return list;
	}

	@Override
	public List<BjdjDispatchList> ingList(BjdjDispatch dispatch) {
		
		List<BjdjDispatchList> list = new ArrayList<BjdjDispatchList>();
		for(BjdjDispatchList dispatchList : dispatch.getDispatchList()){

			if(dispatchList.getState() == BjdjDispatchListState.ing){
				
				list.add(dispatchList);
			}
		}
		return list;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<BjdjDispatchList> accept(String dispatch_id)throws ServiceException {
		
		if(dispatch_id == null){
			
			new ServiceException(ResourceUtil.getText("dispatch.id.required"));
		}
		BjdjDispatch dispatch = get(BjdjDispatch.class, dispatch_id);
		return accept(dispatch);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<BjdjDispatchList> accept(BjdjDispatch dispatch)throws ServiceException {

		List<BjdjDispatchList> list = new ArrayList<BjdjDispatchList>();
		List<BjdjDispatchList> dispatchLists = dispatch.getDispatchList();
		for(int i = 0 ; i < dispatchLists.size(); i++){
			
			BjdjDispatchList item = dispatchLists.get(i);
			if(item.getState() == BjdjDispatchListState.wait){
				
				dispatchListService.accept(item);
				list.add(item);
			}
		}
		return list;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<BjdjDispatchList> verification(String id,String feedback,BjdjCheckWay way) throws ServiceException{
	
		if(id == null){
			
			throw new ServiceException(ResourceUtil.getText("dispatch.id.required"));
		}
		BjdjDispatch dispatch = get(BjdjDispatch.class, id);
		return verification(dispatch, feedback, way);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<BjdjDispatchList> verification(BjdjDispatch dispatch, String feedback, BjdjCheckWay way) throws ServiceException{
		
		List<BjdjDispatchList> list = new ArrayList<BjdjDispatchList>();
		List<BjdjDispatchList> dispatchLists = dispatch.getDispatchList();
		for(int i = 0 ; i < dispatchLists.size(); i++){
			
			BjdjDispatchList item = dispatchLists.get(i);
			if(item.getState() == BjdjDispatchListState.ing){
				
				dispatchListService.verification(item, feedback, way);
				list.add(item);
			}
		}
		return list;
	}
	
	@Override
	public String getDispatchRoleName(){
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(BjdjDispatchServiceImpl.class.getResourceAsStream("dispatchRole.config"), "utf-8"));
			String roleName = reader.readLine();
			if(GeneralUtil.isNull(roleName)){
				
				return "便捷登机服务岗";
			}
			return roleName;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "便捷登机服务岗";
	}
	
	@Override
	public void setDispathcRoleName(String name) {
		
		try {
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(BjdjDispatchServiceImpl.class.getResource("dispatchRole.config").getPath()), "utf-8"));
			writer.write(name);
			writer.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}