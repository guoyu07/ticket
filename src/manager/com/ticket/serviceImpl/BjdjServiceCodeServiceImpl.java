package com.ticket.serviceImpl;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.ticket.constants.ContextConstants;
import com.ticket.enumer.BjdjOrderType;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjAppointment;
import com.ticket.pojo.BjdjDispatchList;
import com.ticket.pojo.BjdjOrder;
import com.ticket.pojo.BjdjServiceCode;
import com.ticket.pojo.BjdjServiceCodeOperation;
import com.ticket.pojo.BjdjServiceCodeRule;
import com.ticket.pojo.ChannelCustomerInfo;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.EmployeeInfoZengLogDetail;
import com.ticket.pojo.Member;
import com.ticket.pojo.OrderInfoDetail;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.SystemDictionary;
import com.ticket.service.IBjdjAppointmentService;
import com.ticket.service.IBjdjDispatchService;
import com.ticket.service.IBjdjServiceCodeOperationService;
import com.ticket.service.IBjdjServiceCodeService;
import com.ticket.service.IChannelCustomerInfoService;
import com.ticket.service.IDepartFromPortService;
import com.ticket.service.IEmployeeInfoZengLogDetailService;
import com.ticket.service.IMemberService;
import com.ticket.service.IOrderInfoDetailService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.util.DateUtil;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.PaginationContext;
import com.ticket.util.ResourceUtil;
import com.ticket.util.ServiceCodeUtil;

/**
 * 服务码表业务接口实现类
 * @ClassName: IBjdjServiceCodeService   
 * @Description: 提供服务码表操作的增删改查   
 * @author HiSay  
 * @date 2015-10-23 15:16:42
 */
public class BjdjServiceCodeServiceImpl extends BaseServiceImpl<BjdjServiceCode, String> implements IBjdjServiceCodeService {

	private static final Log log = LogFactory.getLog(BjdjServiceCodeServiceImpl.class);
	@Resource
	protected IEmployeeInfoZengLogDetailService zengLogDetailService;
	
	@Resource
	private ISystemDictionaryService dictionaryService;
	@Resource
	private IMemberService memberService;
	@Resource
	private EntityManagerFactory entityManagerFactory;
	@Resource
	private IChannelCustomerInfoService channelCustomerInfoService;
	@Resource
	private IOrderInfoDetailService orderInfoDetailService;
	@Resource
	private IDepartFromPortService fromPortService;
	@Resource
	private IBjdjAppointmentService appointmentService;
	@Resource
	private IBjdjServiceCodeOperationService operationService;
	@Resource
	private IBjdjDispatchService dispatchService;
	
	public void init() {
		
	}
	
	@Override
	public boolean merge(String id,SystemDictionary type, SystemDictionary state, String versionFlag) throws ServiceException {
		BjdjServiceCode bjdjServiceCode = dbDAO.queryById(this.getTableNameFromEntity(BjdjServiceCode.class), id);
		bjdjServiceCode.setType(type);
		bjdjServiceCode.setState(state);
		//默认过期时间三个月
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 3);
		bjdjServiceCode.setExpireTime(calendar.getTime());
		
		CommonEntity status = bjdjServiceCode.getStatus();
		status.setVersionFlag(versionFlag);
		bjdjServiceCode.setStatus(status);
		dbDAO.merge(bjdjServiceCode);
		return true;
	}

	@Override
	public boolean persist(String code,SystemDictionary type, SystemDictionary state, String versionFlag) throws ServiceException {
		BjdjServiceCode bjdjServiceCode = new BjdjServiceCode();
		bjdjServiceCode.setCode(DecoderUtil.UtfDecoder(code));
		bjdjServiceCode.setType(type);
		bjdjServiceCode.setState(state);
		
		dbDAO.persist(bjdjServiceCode);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		BjdjServiceCode bjdjServiceCode = dbDAO.queryById(this.getTableNameFromEntity(BjdjServiceCode.class), id);
		dbDAO.remove(bjdjServiceCode);
		return true;
	}

	@Override
	public List<BjdjServiceCode> getNotUsedByType(String type, int count){
		
		List<BjdjServiceCode> codes = dbDAO.executeJPQLForQuery(
					"select s from " + BjdjServiceCode.class.getName() + " s" + 
					" where s.type.name=? and (s.isBind=false or s.isBind is null) and s.state.name='unused'", BjdjServiceCode.class, 0, count, type);
		
		return codes;
	}
	
	@Override
	public BjdjServiceCode getByCode(String code) {
		
		BjdjServiceCode codeObj = (BjdjServiceCode)dbDAO.executeJPQLForQuerySingle(
				"select s from " + BjdjServiceCode.class.getName() + " s" + 
				" where code=?", code);
		return codeObj;
	}
	
	@Override
	public String getMaxCode() {
		
		Properties config = new Properties();
		try {
			
			String classPath = this.getClass().getClassLoader().getResource("/").getPath();
			classPath += "com/ticket/util/ServiceCodeConfig.properties";
			config.load(new FileInputStream(classPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int prefix = Integer.parseInt((String)config.getProperty("prefix"));
		int middle = Integer.parseInt((String)config.getProperty("middle"));
		
		Object codeObj;
		try {
			codeObj = dbDAO.executeJPQLForQuerySingle(
					"select max(substring(code," + (prefix+1) + "," + middle + ")) from " + BjdjServiceCode.class.getName()
					);
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
		return (String)codeObj;
	}
	
	@Override
	public List<BjdjServiceCode> queryByColumn(String column, Object value) {

		String tableName = BjdjServiceCode.class.getName();
		List<BjdjServiceCode> list = dbDAO.executeJPQLForQueryEntity(
				"select s from " + tableName + " s where " + column + "=?", value);
		return list;
	}
	
	@Override
	public Pagination queryEntityByAdmin(String start, String end, String type_id, String state_id, Date startTime, Date endTime, String versionFlag, Integer pageSize)
			throws ServiceException {
		try {
			//先获取当前的实体名称
			String entityName = this.getTableNameFromEntity(this.getClass());
			
			StringBuffer whereCondition = new StringBuffer();
			int index = 3;
			List<Object> params = new ArrayList<Object>();
			if(GeneralUtil.isNotNull(start)){
				
				whereCondition.append(" and code>=?");
				whereCondition.append(index++);
				params.add(start);
			}
			if(GeneralUtil.isNotNull(end)){
				
				whereCondition.append(" and code<=?");
				whereCondition.append(index++);
				params.add(end);
			}
			if(GeneralUtil.isNotNull(type_id)){
				
				whereCondition.append(" and type_id=?");
				whereCondition.append(index++);
				params.add(type_id);
			}
			if(GeneralUtil.isNotNull(state_id)){
				
				whereCondition.append(" and state_id=?");
				whereCondition.append(index++);
				params.add(state_id);
			}
			if(GeneralUtil.isNotNull(startTime)){
				
				whereCondition.append(" and status.createTime>?");
				whereCondition.append(index++);
				params.add(startTime);
			}
			if(GeneralUtil.isNotNull(endTime)){
				
				whereCondition.append(" and status.createTime<?");
				whereCondition.append(index++);
				params.add(endTime);
			}
			
			return dbDAO.queryByPageModule(entityName, deleteFlag, versionFlag, whereCondition.toString(), params.toArray(), orderBy, PaginationContext.getOffset(), pageSize);
		} catch(Exception e) {
			log.info("查询实体列表出错: " + e.fillInStackTrace());
			return null;
		}
	}
	
	public void recycle(BjdjServiceCode serviceCode, SystemDictionary...dictionaries){
		
		SystemDictionary noUse = null;
		if(dictionaries.length > 0 && dictionaries[0] != null){
			
			noUse = dictionaries[0];
		}else{
			
			noUse = dictionaryService.getByName("unused");
		}
		serviceCode.setState(noUse);
		serviceCode.setOrder(null);
		merge(serviceCode);
	}

	@Override
	public void recycle(List<BjdjServiceCode> serviceCodes,
			SystemDictionary... dictionaries) {
		
		for(BjdjServiceCode serviceCode : serviceCodes){
			
			recycle(serviceCode, dictionaries);
		}
	}

	@Override
	public List<BjdjServiceCode> receivedDonation(Member member) {
		
		String sql = "select s from " + BjdjServiceCode.class.getName() + " s where s.member=? and s.member!=s.order.member";
		List<BjdjServiceCode> list = dbDAO.executeJPQLForQueryEntity(sql, member);
		
		return list;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void checkExpire() throws ServiceException{
		
		SystemDictionary expired = dictionaryService.getByName("expired");
		
		//检查服务码是否过期
		String jpl = "select t from " + BjdjServiceCode.class.getName() + " t where t.expireTime < CURRENT_DATE()";
		List<BjdjServiceCode> list = dbDAO.executeJPQLForQueryEntity(jpl);
		for(BjdjServiceCode serviceCode : list){
			
			serviceCode.setState(expired);
			merge(serviceCode);
			
//			BjdjAppointment appointment = serviceCode.getAppointment();
//			if(appointment != null){
//				
//				BjdjValidation validation = appointment.getValidation();
//				if(validation != null){
//					
//					BjdjDispatch dispatch = validation.getDispatch();
//					if(dispatch != null){
//						
//						dispatchService.verification(dispatch, null, BjdjCheckWay.system);
//					}
//				}
//			}
		}
		
//		SystemDictionary used = dictionaryService.getByName("used");
//		SystemDictionary unused = dictionaryService.getByName("unused");
//		//检查航班是否起飞，已经“预约且验证”的服务码就设为“已使用”
//		jpl = "select sc from "+codeName+" sc"
//				+ " where sc.order.state.name = 'paid'"
//				+ " and sc.order.state.name = 'validated'"
//				+ " and sc.appointment.validations.size > 0"
//				+ " and sc.appointment.useTime < CURRENT_DATE()";
//		list = dbDAO.executeJPQLForQueryEntity(jpl);
//		for(BjdjServiceCode serviceCode : list){
//			
//			serviceCode.setState(used);
//			queueService.exitQuence(serviceCode);
//			merge(serviceCode);
//		}
//		
//		//检查航班是否起飞，已经“预约未验证”的服务码就设为“未使用”
//		jpl = "select sc from "+codeName+" sc"
//				+ " where sc.order.state.name = 'paid'"
//				+ " and sc.state.name='activated'"
//				+ " and sc.appointment.useTime < CURRENT_DATE()";
//		list = dbDAO.executeJPQLForQueryEntity(jpl);
//		for(BjdjServiceCode serviceCode : list){
//			
//			serviceCode.setState(unused);
//			remove(serviceCode.getAppointment());
//			queueService.exitQuence(serviceCode);
//			merge(serviceCode);
//		}
//		
//		//检测所有的预约，服务码就为“未使用”的就删除
//		jpl = "select a from "+BjdjAppointment.class.getName()+" a"
//				+ " where a.state.name = 'unused'";
//		List<BjdjAppointment> apps = dbDAO.executeJPQLForQuery(jpl, BjdjAppointment.class);
//		for(BjdjAppointment a : apps){
//			
//			remove(a);
//		}
	}
	
	@Override
	public boolean canActivate(String id) throws ServiceException {
		
		BjdjServiceCode serviceCode = get(BjdjServiceCode.class, id);
		return canActivate(serviceCode);
	}

	@Override
	public boolean canActivate(BjdjServiceCode serviceCode) throws ServiceException {
		
		if(serviceCode == null){
			
			throw new ServiceException(ResourceUtil.getText("serviceCode.notExist"));
		}
		
		//先查看订单是否符合要求
		BjdjOrder order = serviceCode.getOrder();
		if(order == null){

			throw new ServiceException(ResourceUtil.getText("serviceCode.notBuy", serviceCode.getCode()));
		}else if(!"paid".equals(order.getState().getName())){ //订单noPay不可以激活
			
			throw new ServiceException(ResourceUtil.getText("order.noPay"));
		}else if(order.getType() == BjdjOrderType.electromobile){//电瓶车不可以激活
			
			throw new ServiceException(ResourceUtil.getText("serviceCode.activate.order.type.error"));
		}
		
		//在查看服务码状态是否符合要求
		if(!serviceCode.getState().getName().equals("unused")
				&& !serviceCode.getState().getName().equals("donated")){//unused可以激活
			
			throw new ServiceException(ResourceUtil.getText("serviceCode.activate.order.type.error"));
		}
		return true;
	}
	
	
	public boolean canActivate(BjdjServiceCode serviceCode, Date flightDate) throws ServiceException {
		
		if(canActivate(serviceCode)){
			
			//必须大于预约在起飞前设置的时间
			Integer appointmentMinutes = Integer.parseInt(dictionaryService.getByName("appointment_minutes").getValue());
			if(System.currentTimeMillis() + appointmentMinutes*60*1000 > flightDate.getTime()){
				
				throw new ServiceException(ResourceUtil.getText("serviceCode.appointment.time", new String[]{appointmentMinutes+""}));
			}
		}
		
		return true;
	}

	@Override
	public boolean canValidate(String id) throws ServiceException {
		
		BjdjServiceCode serviceCode = queryById(BjdjServiceCode.class.getName(), id);
		return canValidate(serviceCode);
	}

	@Override
	public boolean canValidate(BjdjServiceCode serviceCode) throws ServiceException {

		BjdjOrder order = serviceCode.getOrder();
		if(order.getType() == BjdjOrderType.bjdj && appointmentService.getByServiceCode(serviceCode) != null){
			
			return true;
		}else if(order.getType() == BjdjOrderType.electromobile && "paid".equals(order.getState().getName())){

			return true;
		}
		return false;
	}

	@Override
	public boolean canDispatch(String id) throws ServiceException {
		
		if(!isMine(id)){
			
			return false;
		}
		BjdjServiceCode serviceCode = queryById(BjdjServiceCode.class.getName(), id);
		return canDispatch(serviceCode);
	}

	@Override
	public boolean canDispatch(BjdjServiceCode serviceCode) throws ServiceException {
		
		if(!isMine(serviceCode)){
			
			return false;
		}
		
		BjdjOrder order = serviceCode.getOrder();
		BjdjAppointment appointment = appointmentService.getByServiceCode(serviceCode);
		if(order.getType() == BjdjOrderType.bjdj && appointment != null && appointment.getValidation() != null){
			
			return true;
		}/*else if(order.getType() == BjdjOrderType.electromobile && serviceCode.getValidation() != null){
			
			return true;
		}*/
		
		return false;
	}

	@Override
	public boolean canDonate(String id) throws ServiceException {
	
		if(!isMine(id)){
			
			return false;
		}
		BjdjServiceCode serviceCode = queryById(BjdjServiceCode.class.getName(), id);
		return canDonate(serviceCode);
	}

	@Override
	public boolean canDonate(BjdjServiceCode serviceCode) throws ServiceException {
		
		if(!isMine(serviceCode)){
			
			return false;
		}
		
		BjdjOrder order = serviceCode.getOrder();
		if(order.getState().getName().equals("paid")
				&& serviceCode.getState().getName().equals("unused")){
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean canComment(String id) throws ServiceException {
		
		if(!isMine(id)){
			
			return false;
		}
		BjdjServiceCode serviceCode = queryById(BjdjServiceCode.class.getName(), id);
		return canComment(serviceCode);
	}

	@Override
	public boolean canComment(BjdjServiceCode serviceCode) throws ServiceException {
		
		if(!isMine(serviceCode)){
			
			return false;
		}
		
		if("used".equals(serviceCode.getState().getName())){
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean canDelay(String id) throws ServiceException {

		if(!isMine(id)){
			
			return false;
		}
		BjdjServiceCode serviceCode = queryById(BjdjServiceCode.class.getName(), id);
		return canDelay(serviceCode);
	}

	@Override
	public boolean canDelay(BjdjServiceCode serviceCode) throws ServiceException {

		if(!isMine(serviceCode)){
			
			return false;
		}

		return true;
	}

	@Override
	public boolean isMine(String id) throws ServiceException {
		
		if(GeneralUtil.isNull(id)){
			
			throw new ServiceException("serviceCode.id.required");
		}
		BjdjServiceCode serviceCode = queryById(BjdjServiceCode.class.getName(), id);
		return isMine(serviceCode);
	}

	@Override
	public boolean isMine(BjdjServiceCode serviceCode) throws ServiceException {
		
		if(serviceCode == null){
			
			throw new ServiceException(ResourceUtil.getText("serviceCode.notExist"));
		}
		
		Member me = (Member)getSession().get(ContextConstants.SCOPE_MEMBER);
		Member codeMember = serviceCode.getMember();
		if(codeMember == null){
			
			if(serviceCode.getOrder() != null){
				
				codeMember = serviceCode.getOrder().getMember();
			}
		}
		
		if(me != null && codeMember.getId().equals(me.getId())){
			
			return true;
		}
		
		return false;
	}
	
	@Override
	public List<BjdjServiceCode> queryByMobile(String mobile) {
		
		String name1 = BjdjServiceCode.class.getName();
		
		List<BjdjServiceCode> codes = dbDAO.executeJPQLForQueryEntity("select sc from " + name1 + " sc where sc.member.phone=?", mobile);
		return codes;
	}

	@Override
	public List<BjdjServiceCode> queryByMobileUser(String mobile) {
		
		String name1 = BjdjServiceCode.class.getName();
		
		List<BjdjServiceCode> codes = dbDAO.executeJPQLForQueryEntity(
				"select sc from " + name1 + " sc where sc.member.phone=? and sc.order.payMethod.name=?", 
				mobile, "mobile_integral");
		return codes;
	}
	
	@Override
	public List<BjdjServiceCode> queryMyCanActivates() {
		
		Member me = getMember();
		return queryCanActivates(me.getId());
	}
	
	@Override
	public List<BjdjServiceCode> queryCanActivates(String mid) {
		
		String sql = "select s from " + BjdjServiceCode.class.getName() + " s"
				+ " where (s.member.id=? or (s.member=null and s.order.member.id=?))"
				+ " and (s.order != null and s.order.type=? and s.order.state.name='paid')"
				+ " and (s.state.name='unused' or s.state.name='donated')";
		List<BjdjServiceCode> list =  dbDAO.executeJPQLForQueryEntity(sql, mid, mid, BjdjOrderType.bjdj);
		
		Member member = get(Member.class, mid);
		sql = "select sc from " + EmployeeInfoZengLogDetail.class.getSimpleName() + " s"
				+ " left join s.orderInfoDetail.bjdjServiceCode sc"
				+ " where s.employeeInfoZengLog.mobile=? and (sc.state.name='unused' or sc.state.name='donated') and sc.isBind=true";
		list.addAll(dbDAO.executeJPQLForQueryEntity(sql, member.getPhone()));
		return list;
	}
	
	@Override
	public List<BjdjServiceCode> queryMyCanDonates() {
		
		Member me = (Member)getSession().get(ContextConstants.SCOPE_MEMBER);
		String sql = "select s from " + BjdjServiceCode.class.getName() + " s"
				+ " where (s.member=?  or (s.member=null and s.order.member=?))"
				+ " and (s.order!=null and s.order.state.name='paid')"
				+ " and s.state.name='unused'";
		List<BjdjServiceCode> list =  dbDAO.executeJPQLForQueryEntity(sql, me, me);
		return list;
	}

	@Override
	public boolean canDelete(String id) throws ServiceException {

		if(!isMine(id)){
			
			return false;
		}
		BjdjServiceCode serviceCode = queryById(BjdjServiceCode.class.getName(), id);
		return canDelete(serviceCode);
	}

	@Override
	public boolean canDelete(BjdjServiceCode serviceCode)
			throws ServiceException {
		
		if(!isMine(serviceCode)){
			
			return false;
		}
		
		if("noPay".equals(serviceCode.getOrder().getState().getName())){
			
			return true;
		}
		
		if("unused".equals(serviceCode.getState().getName()) ||
			"used".equals(serviceCode.getState().getName())){
			
			return true;
		}

		return false;
	}

	@Override
	public String activateBjdjServiceCoede(String bjdjServiceCodeId,
			String memberId,String useDate,String flightNo) throws ServiceException {
		try {
			String result = "激活服务码失败";
			Member member = memberService.queryById(Member.class.getSimpleName(), memberId);
			Date useTime = null;
			if(GeneralUtil.isNotNull(useDate)){
				useTime = DateUtil.parseStringToDate(useDate, "yyyy-MM-dd HH:mm:ss");
			}
			BjdjServiceCode bjdjServiceCode = queryById(BjdjServiceCode.class.getSimpleName(),
					bjdjServiceCodeId);
			if(bjdjServiceCode != null){
				if("activated".equals(bjdjServiceCode.getState().getName())){
					return result;
				}
			}else{
				return result;
			}
			if(member != null){
				bjdjServiceCode.setMember(member);
				this.merge(bjdjServiceCode);
				BjdjAppointment bjdjAppointment = appointmentService.getByServiceCode(bjdjServiceCode.getCode());
				if(bjdjAppointment == null){
					bjdjAppointment = appointmentService.onlineAppointment(bjdjServiceCode, member.getIDCard(),
							flightNo, member.getPhone(), useTime, member.getRealName());
				}
				if(bjdjAppointment != null){
					result = "true";
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "激活服务码失败";
		}
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void batchGenerate(int number, String type_id) throws ServiceException {
		
		char[] rule = getRule(type_id);
		List<String> list = queryByRule(rule);
		
		//对得到的服务码排序，方便后面判断新的服务码是否已经存在
		Collections.sort(list);
		
		//先检测，根据已经指定的规则，剩余的可以生成服务码是否够要生成的个数
		BigInteger totalCount = BigInteger.valueOf(1); 
		for(int i = 0; i < rule.length; i++){
			
			char c = rule[i];
			if(c == '*'){
				
				//每一位有26自个字母和10个数字，总36种可能
				totalCount = totalCount.multiply(BigInteger.valueOf(36));
			}
		}
		BigInteger surplus = totalCount.subtract(BigInteger.valueOf(list.size()));
		if(surplus.longValue() <= 0){
			
			throw new ServiceException(ResourceUtil.getText("serviceCode.rule.count", surplus.longValue()+"", number+""));
		}
		
		//生成服务码
		SystemDictionary typeDictionary = dictionaryService.get(SystemDictionary.class, type_id);
		SystemDictionary stateDictionary = dictionaryService.getByName("unused");
		String code = "000000000000";
		for(int i = 0; i < number; i++){
			
			code = ServiceCodeUtil.next(code, rule);
			while(Collections.binarySearch(list, code) >= 0){
				
				code = ServiceCodeUtil.next(code, rule);
			}
			
			BjdjServiceCode serviceCode = new BjdjServiceCode();
			serviceCode.setCode(code);
			serviceCode.setType(typeDictionary);
			serviceCode.setState(stateDictionary);
			persist(code, typeDictionary, stateDictionary, versionFlag);
		}
	}

	public ISystemDictionaryService getDictionaryService() {
		return dictionaryService;
	}

	public void setDictionaryService(ISystemDictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	@Override
	public String iWillActivate(String channelCustomerInfoLoginId, String idCard, String flightNo,
			String useTime) throws ServiceException {
		try {
			ChannelCustomerInfo channelCustomerInfo = channelCustomerInfoService
						.queryByLoginIdExist(channelCustomerInfoLoginId);
			if(channelCustomerInfo == null){
				return "没有该渠道客户,请核对客户账号";
			}
			Member member = memberService.getByIdCard(idCard);
			if(member == null){
				return "该身份证号还未注册会员,请先注册会员";
			}
			List<OrderInfoDetail> list = orderInfoDetailService
						.getServiceCodeIdsByUnused(channelCustomerInfo.getId(),true, 1);
			if(list.isEmpty()){
				return "该客户没有可用的服务码";
			}
			BjdjServiceCode bjdjServiceCode = list.get(0).getBjdjServiceCode();
			if(member != null){
				bjdjServiceCode.setMember(member);
				this.merge(bjdjServiceCode);
				BjdjAppointment bjdjAppointment = appointmentService.getByServiceCode(bjdjServiceCode.getCode());
				if(bjdjAppointment == null){
					bjdjAppointment = appointmentService.onlineAppointment(bjdjServiceCode, member.getIDCard(),
							flightNo, member.getPhone(), DateUtil.parseStringToDate(useTime.replace("T", " "), "yyyy-MM-dd HH:mm"),
							member.getRealName());
				}
				if(bjdjAppointment != null){
					return "true";
				}
			}
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "操作失败";
		}
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void froze(List<BjdjServiceCode> bjdjServiceCodes)
			throws ServiceException {
		
		SystemDictionary sd = dictionaryService.getByName("freeze");
		for(BjdjServiceCode serviceCode : bjdjServiceCodes){
			SystemDictionary beforeFroze = serviceCode.getState();
			serviceCode.setBeforeFroze(beforeFroze);
			serviceCode.setState(sd);
			dbDAO.merge(serviceCode);
		}
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void froze(String ids) throws ServiceException {
		
		List<BjdjServiceCode> serviceCodes = new ArrayList<BjdjServiceCode>();
		String[] idsValue = ids.split(",");
		for(String id : idsValue){
			
			BjdjServiceCode code = dbDAO.queryById(BjdjServiceCode.class.getName(), id);
			serviceCodes.add(code);
		}
		froze(serviceCodes);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void unFroze(List<BjdjServiceCode> bjdjServiceCodes)
			throws ServiceException {
		
		for(BjdjServiceCode serviceCode : bjdjServiceCodes){
			
			SystemDictionary beforeFroze = serviceCode.getBeforeFroze();
			serviceCode.setBeforeFroze(null);
			serviceCode.setState(beforeFroze);
			dbDAO.merge(serviceCode);
		}
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void unFroze(String ids) throws ServiceException {
		
		List<BjdjServiceCode> serviceCodes = new ArrayList<BjdjServiceCode>();
		String[] idsValue = ids.split(",");
		for(String id : idsValue){
			
			BjdjServiceCode code = dbDAO.queryById(BjdjServiceCode.class.getName(), id);
			serviceCodes.add(code);
		}
		unFroze(serviceCodes);
	}

	@Override
	public boolean canDonate1(BjdjServiceCode serviceCode) throws ServiceException {
		
		BjdjOrder order = serviceCode.getOrder();
		if(order.getState().getName().equals("paid")
				&& serviceCode.getState().getName().equals("unused")){
			
			return true;
		}
		return false;
	}
	
	public void generateReport(OutputStream out){
		
		List<Object> data = dbDAO.executeJPQLForQuery("select sc.code," //服务码
				+ " sct.name," //渠道类型
				+ " '匿名'," //渠道用户
				+ " scs.value," //服务码状态
				+ " scom.phone," //购买账号
				+ " sco.status.createTime," //购买时间
				+ " sco.payMethod," //支付方式
				+ " scom.phone," //转赠账号
				+ " (select MAX(l.status.createTime) from " + BjdjServiceCodeOperation.class.getName() + " l where l.operation.name='donation' and l.fromMember=scom and l.toMember=scm and l.serviceCode=sc)," //转赠时间
				+ " scm.phone," //受赠号
				+ " (select (case when a.employee is not null then a.employee.employeeId else a.member.phone end) from " + BjdjAppointment.class.getName() + " a where a.serviceCode=sc)," //激活账号
				+ " sca.status.createTime," //激活时间
				+ " sca.name," //使用人姓名
				+ " sca.idCard," //身份证
				+ " sca.flightNo," //航班号
				+ " sca.useTime," //使用时间
				+ " sca.hall.number," //服务厅
				+ " (select MAX(l.remark) from " + BjdjServiceCodeOperation.class.getName() + " l where l.operation.name='unactive' and l.serviceCode=sc)," //取消激活原因
				+ " (select MAX(l.status.createTime) from " + BjdjServiceCodeOperation.class.getName() + " l where l.operation.name='unactive' and l.serviceCode=sc)," //取消激活时间
				+ " (select l.fromMember.phone from " + BjdjServiceCodeOperation.class.getName() + " l where l.operation.name='refund' and l.serviceCode=sc and l.status.createTime=(select MAX(ls.status.createTime) from " + BjdjServiceCodeOperation.class.getName() + " ls where ls.operation.name='refund' and ls.serviceCode=sc))," //退款账号
				+ " (select MAX(l.status.createTime) from " + BjdjServiceCodeOperation.class.getName() + " l where l.operation.name='refund' and l.serviceCode=sc)," //退款时间
				+ " '原渠道退回'," //退款方式
				+ " scav.status.createTime," //验证时间
				+ " scave.employeeId," //验证账号
				+ " scavd.status.createTime," //分单时间
				+ " (select dl.employee.name from " + BjdjDispatchList.class.getName() + " dl where dl.dispatch=scavd and dl.info.name='办票服务')," //服务人员
				+ " (select dl.status.createTime from " + BjdjDispatchList.class.getName() + " dl where dl.dispatch=scavd and dl.info.name='办票服务')," //接单时间，办票服务
				+ " (select dl.time from " + BjdjDispatchList.class.getName() + " dl where dl.dispatch=scavd and dl.info.name='办票服务')," //核销时间
				+ " (select dl.employee.name from " + BjdjDispatchList.class.getName() + " dl where dl.dispatch=scavd and dl.info.name='休息专区')," //服务人员
				+ " (select dl.status.createTime from " + BjdjDispatchList.class.getName() + " dl where dl.dispatch=scavd and dl.info.name='休息专区')," //接单时间，厅内服务
				+ " (select dl.time from " + BjdjDispatchList.class.getName() + " dl where dl.dispatch=scavd and dl.info.name='休息专区')," //核销时间
				+ " (select dl.employee.name from " + BjdjDispatchList.class.getName() + " dl where dl.dispatch=scavd and dl.info.name='快速过检')," //服务人员
				+ " (select dl.status.createTime from " + BjdjDispatchList.class.getName() + " dl where dl.dispatch=scavd and dl.info.name='快速过检')," //接单时间，安检服务
				+ " (select dl.time from " + BjdjDispatchList.class.getName() + " dl where dl.dispatch=scavd and dl.info.name='快速过检')," //核销时间
				+ " (select dl.employee.name from " + BjdjDispatchList.class.getName() + " dl where dl.dispatch=scavd and dl.info.name='电瓶车送机')," //服务人员
				+ " (select dl.status.createTime from " + BjdjDispatchList.class.getName() + " dl where dl.dispatch=scavd and dl.info.name='电瓶车送机')," //接单时间，送机服务
				+ " (select dl.time from " + BjdjDispatchList.class.getName() + " dl where dl.dispatch=scavd and dl.info.name='电瓶车送机')" //核销时间
				+ " from " + BjdjServiceCode.class.getName() + " sc"
				+ " left join sc.type sct"
				+ " left join sc.state scs"
				+ " left join sc.order sco"
				+ " left join sc.member scm"
				+ " left join sco.member scom"
				+ " left join sc.appointment sca"
				+ " left join sca.validation scav"
				+ " left join scav.employee scave"
				+ " left join scav.dispatch scavd"
				+ " left join scavd.employee scavde");
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		for(Object row : data){
			
			Object[] rowArray = (Object[])row;
			for(int j = 0; j < rowArray.length; j++){
				
				Object cell = rowArray[j];
				if(cell instanceof Date){
					
					rowArray[j] = format.format(cell);
				}
			}
		}
		
		@SuppressWarnings("deprecation")
		String srcFilePath = ServletActionContext.getRequest().getRealPath("/WEB-INF/excel/template/serviceCode.xls");
		try {
			InputStream inJxls = new FileInputStream(srcFilePath);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("data", data);
			Workbook wb = new XLSTransformer().transformXLS(inJxls, map);
			wb.write(out);
		} catch (ParsePropertyException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void recover(List<BjdjServiceCode> serviceCodes) throws ServiceException {
		
		SystemDictionary unused = dictionaryService.getByName("unused");
		SystemDictionary refund = dictionaryService.getByName("refund");
		for(BjdjServiceCode code : serviceCodes){
			
			code.setOrderType(null);
			code.setOrder(null);
			code.setState(unused);
			code.setBeforeFroze(null);
			code.setIsBind(false);
			code.setExpireTime(null);
			code.setMember(null);
//			code.setAppointment(null);
			code.setExpireTime(null);
			code.setOrderType(null);
			code.setTwoDimensionCodePath(null);
			code.setType(null);
			
			operationService.persist(code, refund, getMember(), null);
		}
	}

	@Override
	public BjdjServiceCode randomGetCanActivate(String member_id, final String order_id, String[] codes) {
		
		List<BjdjServiceCode> serviceCodes = queryCanActivates(member_id);
		
		//这样排序之后，就会优先选择order_id订单中的服务码
		Collections.sort(serviceCodes, new Comparator<BjdjServiceCode>() {

			@Override
			public int compare(BjdjServiceCode o1, BjdjServiceCode o2) {
				
				if(o1.getOrder() == null){
					
					return -1;
				}
				
				if(o1.getOrder().getId().equals(order_id)){
					
					return -1;
				}
				return 1;
			}
		});
		
		if(codes != null){
			
			Arrays.sort(codes);
		}
		for(int i = 0; i < serviceCodes.size(); i++){
			
			BjdjAppointment appointment = appointmentService.getByServiceCode(serviceCodes.get(i));
			if(appointment != null){
				
				continue;
			}
			
			if(codes == null){
				
				return serviceCodes.get(i);
			}else{
				
				int index = Arrays.binarySearch(codes, serviceCodes.get(i).getCode());
				if(index < 0){
					
					return serviceCodes.get(i);
				}
			}
		}
		return null;
	}

	@Override
	public void saveOrUpdateRule(String type_id, char[] rule) throws ServiceException {

		if(GeneralUtil.isNull(type_id)){
			
			throw new ServiceException(ResourceUtil.getText("id.required"));
		}
		if(rule == null){
			
			throw new ServiceException(ResourceUtil.getText("serviceCode.rule.required"));
		}
		if(rule.length != 12){
			
			throw new ServiceException(ResourceUtil.getText("serviceCode.rule.length"));
		}
		
		BjdjServiceCodeRule ruleObj = dbDAO.executeJPQLForQuerySingle("select t from " + BjdjServiceCodeRule.class.getName() + " t where t.type.id=?", BjdjServiceCodeRule.class, type_id);
		if(ruleObj == null){
			
			ruleObj = new BjdjServiceCodeRule();
			SystemDictionary type = dictionaryService.get(SystemDictionary.class, type_id);
			ruleObj.setType(type);
			ruleObj.setRule(new String(rule));
			dbDAO.persist(ruleObj);
		}else{
			
			ruleObj.setRule(new String(rule));
			dbDAO.merge(ruleObj);
		}
	}
	
	@Override
	public char[] getRule(String type_id){
		
		char[] rule = new char[12];
		BjdjServiceCodeRule ruleObj = dbDAO.executeJPQLForQuerySingle("select t from " + BjdjServiceCodeRule.class.getName() + " t where t.type.id=?", 
				BjdjServiceCodeRule.class, type_id);
		if(ruleObj != null){
			
			rule = ruleObj.getRule().toCharArray();
		}else{
			
			Arrays.fill(rule, '*');
		}
		return rule;
	}
	
	public List<String> queryByRule(char[] rule){
		
		StringBuffer sql = new StringBuffer("select t.code from ").append(BjdjServiceCode.class.getName()).append(" t where code like '");
		for(int i = 0 ; i < rule.length; i++){
			
			char c = rule[i];
			if(c == '*'){
				
				sql.append("_");
			}else{

				sql.append(c);
			}
		}
		sql.append("'");
		
		List<String> list = dbDAO.executeJPQLForQuery(sql.toString(), String.class);
		return list;
	}
}