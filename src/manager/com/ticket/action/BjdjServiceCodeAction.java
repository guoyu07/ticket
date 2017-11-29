package com.ticket.action;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjAppointment;
import com.ticket.pojo.BjdjServiceCode;
import com.ticket.pojo.Member;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.SqlParam.Condition;
import com.ticket.pojo.SqlParamGroup;
import com.ticket.pojo.SystemDictionary;
import com.ticket.service.IBjdjAppointmentService;
import com.ticket.service.IBjdjServiceCodeOperationService;
import com.ticket.service.IBjdjServiceCodeService;
import com.ticket.service.IChannelCustomerInfoService;
import com.ticket.service.IDepartFromPortService;
import com.ticket.service.IMemberService;
import com.ticket.service.IOrderInfoService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SmsUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 服务码表控制器
 * 
 * @ClassName: BjdjServiceCodeAction
 * @Description: 提供服务码表的相关操作方法.
 * @author HiSay
 * @date 2015-10-23 15:16:42
 * 
 */
public class BjdjServiceCodeAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	// 服务码表的业务层
	@Resource
	private IBjdjServiceCodeService serviceCodeService;
	// 服务码log表的业务层
	@Resource
	private IBjdjServiceCodeOperationService serviceCodeLogService;
	// 渠道用户业务层
	@Resource
	private IChannelCustomerInfoService channelCustomerInfoService;
	// 订单业务层
	@Resource
	private IOrderInfoService orderInfoService;
	// 系统字典
	@Resource
	private ISystemDictionaryService dictionaryService;
	@Resource
	private ISystemOperationLogService logService;
	@Resource
	protected IBjdjAppointmentService appointmentService;
	@Resource
	protected IMemberService memberService;
	@Resource 
	private IDepartFromPortService fromPortService;

	protected BjdjAppointment appointment;

	// 服务码表实体
	private BjdjServiceCode bjdjServiceCode;
	// 主键
	private String id;
	// 服务码
	private String code;
	// 开始、结束服务码
	private String start, end;
	// 服务码类型ID（字典）
	private String type_id;
	// 服务码状态ID（字典）
	private String state_id;
	// 开始时间
	private Date startTime, endTime;
	// 购买数量
	private Integer number;

	protected String[] name;
	protected String[] codes;
	protected String[] idCard;
	protected String[] flightNo;
	protected Date[] date;
	protected char[] rule;

	private String mobile;

	private String loginName;

	private String realName;

	private String phone;

	private String IDCard;
	
	/**
	 * 下载报表
	 * @return
	 * @throws ServiceException
	 */
	public String downReport() {
		
		SqlParamGroup group = new SqlParamGroup("s.status.deleteFlag", Condition.eq, ContextConstants.STATUS_OF_ZERO).and("s.status.versionFlag", Condition.eq, versionFlag);
		group.and("s.code", Condition.ge, start);
		group.and("s.code", Condition.le, end);
		group.and("s.type.id", Condition.eq, type_id);
		group.and("s.state.id", Condition.eq, state_id);
		group.and("s.status.createTime", Condition.ge, startTime);
		group.and("s.status.createTime", Condition.le, endTime);
		
		group.and("s.member.loginName", Condition.like_left, loginName);
		group.and("s.member.realName", Condition.like_left, realName);
		group.and("s.member.phone", Condition.like_left, phone);
		group.and("s.member.IDCard", Condition.like_left, IDCard);
		List<BjdjServiceCode> list = serviceCodeService.getDbDAO().executeJPQLForQuery("select s from " + BjdjServiceCode.class.getName() + " s " + group.toString(true) + " order by s.status.createTime desc", group.getParamArray());
		exportExcel("/WEB-INF/excel/jasper/bjdjServiceCode.jasper", list, "服务码");
		return null;
	}
	
	/**
	 * 生成报表
	 * @return
	 * @throws IOException 
	 */
	public String generate(){
		
		try {
			response.setCharacterEncoding("utf-8");
			String fileName = URLEncoder.encode("服务码数据报表.xlsx", "utf-8"); //防止中文乱码
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			serviceCodeService.generateReport(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return JSON;
	}
	
	/**
	 * @throws Exception
	 *             添加服务码表
	 * @Title: BjdjServiceCodeAction
	 * @Description:
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String add() throws Exception {
		if (GeneralUtil.isNull(operationFlag)) {
			return "addBjdjServiceCode";
		} else {

			// 非空验证.
			if (GeneralUtil.isNull(number) && number > 0 && number < 1000) {
				data = AjaxData.responseError(getText("code.required"));
				return JSON;
			}

			try {
				serviceCodeService.batchGenerate(number, type_id);
			} catch (Exception e) {
				data = AjaxData.responseError(e.getMessage());
				return JSON;
			}
			// 根据保存结果返回页面
			String logContent = "新增服务码";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();

			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("addSuccess"));
			return JSON;
		}
	}

	/**
	 * 修改服务码表
	 * 
	 * @Title: BjdjServiceCodeAction
	 * @Description:
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String edit() throws ServiceException {
		if (GeneralUtil.isNull(operationFlag)) {
			this.setBjdjServiceCode(serviceCodeService.queryById(BjdjServiceCode.class.getSimpleName(), id));
			return "editBjdjServiceCode";
		} else {
			if (GeneralUtil.isNull(type_id)) {
				data = getText("type_id.required");
				return TEXT;
			}
			List<Object> params = new ArrayList<Object>();

			String tableName = "ticket_" + BjdjServiceCode.class.getSimpleName();
			StringBuffer sql = new StringBuffer("update ");
			sql.append(tableName);
			sql.append(" set type_id=?");
			params.add(type_id);

			StringBuffer whereCondition = new StringBuffer(" where 1=1");

			if (GeneralUtil.isNotNull(id)) {

				whereCondition.append(" and id=?");
				params.add(id);
			} else {

				if (GeneralUtil.isNotNull(start)) {

					whereCondition.append(" and code>=?");
					params.add(start);
				}
				if (GeneralUtil.isNotNull(end)) {

					whereCondition.append(" and code<=?");
					params.add(end);
				}
				if (GeneralUtil.isNotNull(startTime)) {

					whereCondition.append(" and createTime>?");
					params.add(startTime);
				}
				if (GeneralUtil.isNotNull(endTime)) {

					whereCondition.append(" and createTime<?");
					params.add(endTime);
				}
			}

			whereCondition.append(" and type_id is null");
			SystemDictionary status = dictionaryService.getByName("unused");
			whereCondition.append(" and state_id=?");
			params.add(status.getId());

			sql.append(whereCondition);

			// 修改服务码表实体
			try {
				String logContent = "修改服务码";
				String logName = SystemOparetionLogUtil.getLogName();
				String logTime = SystemOparetionLogUtil.getLogTime();
				String logIp = SystemOparetionLogUtil.getLogIp();

				logService.persist(logName, logContent, logTime, logIp,
						versionFlag);
				serviceCodeService.getDbDAO().executeSQLForUpdate(
						sql.toString().toLowerCase(), params.toArray());
				// 根据修改结果返回页面
				data = AjaxData.responseSuccess(getText("editSuccess"));
			} catch (Exception e) {
				e.printStackTrace();
				data = AjaxData.responseError(getText("editFailed"));
			}
			return JSON;
		}
	}
	
	/**
	 * 保存服务码生成规则
	 * @return
	 */
	public String saveConfig(){
		
		if (GeneralUtil.isNull(operationFlag)) {
			
			List<SystemDictionary> list = dictionaryService.querySubByParentValue("service_code_type");
			if(!list.isEmpty()){
				
				rule = serviceCodeService.getRule(list.get(0).getId());
			}

			ActionContext.getContext().put("types", list);
			return "editBjdjServiceCodeRule";
		}
		
		try {
			serviceCodeService.saveOrUpdateRule(type_id, rule);
		} catch (ServiceException e) {
			data = AjaxData.responseError(e.getMessage());
			return JSON;
		}
		
		data = AjaxData.responseSuccess("editSuccess");
		return JSON;
	}
	
	
	/**
	 * 加载服务码生成规则
	 * @return
	 */
	public String loadRule(){
		
		char[] rule = serviceCodeService.getRule(type_id);
		data = new String(rule);
		return TEXT;
	}

	/**
	 * @Description：单个/批量冻结服务码
	 * @date 
	 * @return
	 * @throws ServiceException
	 */
	public String froze() throws ServiceException {
		
		try {
			serviceCodeService.froze(idsValue);
			String logContent = "冻结服务码";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp,
					versionFlag);
			data = AjaxData.responseSuccess("冻结服务码成功");
		} catch (Exception e) {
			e.printStackTrace();
			data = AjaxData.responseError("冻结服务码失败");
		}
		return JSON;
	}
	/**
	 * 单个/批量解冻结的服务码
	 * @return
	 * @throws ServiceException
	 */
	public String unFroze() throws ServiceException{
		try {
			serviceCodeService.unFroze(idsValue);
			String logContent = "解开冻结服务码";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp,
					versionFlag);
			data = AjaxData.responseSuccess("解冻结的服务码成功");
		} catch (Exception e) {
			e.printStackTrace();
			data = AjaxData.responseError("解冻结的服务码失败");
		}
		return JSON;
	}
	/**
	 * 后台服务码转增页面
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public String donation() throws ServiceException {

		return "donation";
	}
	/**
	 * 后台服务码转增
	 * @return
	 * @throws ServiceException
	 */
	public String serviceCodeDonation() throws ServiceException {

		// 手机号不能为空
		if (GeneralUtil.isNull(mobile)) {

			data = AjaxData.responseError(getText("mobile.required"));
			return JSON;
		}

		// 检验转赠的服务码是否为空
		if (GeneralUtil.isNull(code)) {

			data = AjaxData.responseError(getText("serviceCode.required"));
			return JSON;
		}

		String[] codeArr = code.split(",");
		if (codeArr != null) {

			Member fromMember = new Member();
			Member toMember = memberService.generateByMobileAndSendSms(mobile,
					false);
			StringBuffer codes = new StringBuffer();

			SystemDictionary donation = dictionaryService.getByName("donation");
			for (int i = 0; i < codeArr.length; i++) {

				String codeStr = codeArr[i].trim();

				BjdjServiceCode serviceCode = serviceCodeService.queryById(
						BjdjServiceCode.class.getName(), codeStr);
				
				fromMember = serviceCode.getMember();
				
				// 检测是否可以赠送
				if (!serviceCodeService.canDonate1(serviceCode)) {

					data = AjaxData.responseError(getText("serviceCode.cantDonate"));
					return JSON;
				}

				// 不能赠送给自己
				if (serviceCode.getMember() == null
						&& mobile.equals(serviceCode.getOrder().getMobile())) {

					data = AjaxData.responseError(getText("order.noDonationMe"));
					return JSON;
				} else if (serviceCode.getMember() != null
						&& mobile.equals(serviceCode.getMember().getPhone())) {

					data = AjaxData.responseError(getText("order.noDonationMe"));
					return JSON;
				}

				// 设置服务码拥护者
				serviceCode.setMember(toMember);
				serviceCodeService.merge(serviceCode);

				// 设置转赠记录
				serviceCodeLogService.persist(serviceCode, donation,
						fromMember, toMember);

				codes.append(serviceCode.getCode());
				codes.append(",");
			}
			SmsUtil.sendSms(
					toMember.getPhone(),
					getText("sms.serviceCode.donation",
							new String[] { fromMember.getPhone(),
									codes.substring(0, codes.length() - 1) }));
		}
		String logContent = "转增服务码";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();

		logService.persist(logName, logContent, logTime, logIp,
				versionFlag);
		data = AjaxData.responseSuccess(getText("donation.success"));
		return JSON;
	}

	/**
	 * 后台服务码激活页面
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public String activate() throws ServiceException {

		return "activate";
	}

	/**
	 * 后台服务码激活
	 * 
	 * @return
	 */
	public String serviceCodeActivate() {
		try {
			if (codes == null) {

				data = AjaxData.responseError(getText("serviceCode.required"));
				return JSON;
			}

			// 服务码验证
			for (int i = 0; i < codes.length; i++) {

				BjdjServiceCode serviceCode = serviceCodeService.getByCode(codes[i].trim());
				serviceCodeService.canActivate(serviceCode);
			}

			// 必须大于预约在起飞前设置的时间
			Integer appointmentMinutes = Integer.parseInt(dictionaryService.getByName("appointment_minutes").getValue());
			for (int i = 0; i < date.length; i++) {

				if (System.currentTimeMillis() + appointmentMinutes * 60 * 1000 > date[i].getTime()) {

					data = AjaxData.responseError(getText("serviceCode.appointment.time", new String[] { appointmentMinutes + "" }));
					return JSON;
				}
			}

			// 激活
			for (int i = 0; i < codes.length; i++) {

				BjdjServiceCode serviceCode = serviceCodeService.getByCode(codes[i].trim());

				// 生成预约信息
				appointment = appointmentService.onlineAppointment(serviceCode,
						idCard[i].trim(), flightNo[i].trim(), serviceCode.getOrder().getMobile(), date[i], name[i].trim());

			}
			String logContent = "激活服务码";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();

			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("activateSuccess"));
			return JSON;
		} catch (NumberFormatException e) {
			data = AjaxData.responseError(e.getMessage());
			return JSON;
		} catch (ServiceException e) {
			data = AjaxData.responseError(e.getMessage());
			return JSON;
		}
	}
	
	/**
	 * 取消激活，可以传多个服务码id
	 * @return
	 */
	public String unActivate(){
		
		try {
			appointmentService.unActivate(idsValue, getSessionAdminUser());
		} catch (ServiceException e) {
			data = AjaxData.responseError(e.getMessage());
			return JSON;
		}
		data = AjaxData.responseSuccess(getText("serviceCode.unactivate.success"));
		return JSON;
	}

	/**
	 * 管理服务码表实体
	 * 
	 * @Title: BjdjServiceCodeAction
	 * @Description:
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String manage() throws ServiceException {

		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		SqlParamGroup group = new SqlParamGroup("s.status.deleteFlag", Condition.eq, ContextConstants.STATUS_OF_ZERO).and("s.status.versionFlag", Condition.eq, versionFlag);
		group.and("s.code", Condition.ge, start);
		group.and("s.code", Condition.le, end);
		group.and("s.type.id", Condition.eq, type_id);
		group.and("s.state.id", Condition.eq, state_id);
		group.and("s.status.createTime", Condition.ge, startTime);
		group.and("s.status.createTime", Condition.le, endTime);
		
		group.and("s.member.loginName", Condition.like_left, loginName);
		group.and("s.member.realName", Condition.like_left, realName);
		group.and("s.member.phone", Condition.like_left, phone);
		group.and("s.member.IDCard", Condition.like_left, IDCard);
		this.setPageModule(serviceCodeService.paginationQuery("select s from " + BjdjServiceCode.class.getName() + " s " + group.toString(true) + " order by s.status.createTime desc", group.getParamArray()));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageBjdjServiceCode";
	}

	/**
	 * 分配服务码表实体
	 * 
	 * @Title: BjdjServiceCodeAction
	 * @Description:
	 * @return
	 * @throws ServiceException
	 */
	public String assignmentManage() throws ServiceException {

		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		SystemDictionary dict = dictionaryService.getByName("unused");
		Pagination page = serviceCodeService.queryEntityByAdmin(start, end,
				null, dict.getId(), startTime, endTime, versionFlag,
				ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		this.setPageModule(page);
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "assignmentBjdjServiceCode";
	}

	/**
	 * 查看回收站
	 * 
	 * @Title: BjdjServiceCodeAction
	 * @Description:
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(serviceCodeService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleBjdjServiceCode";
	}

	/**
	 * 逻辑删除服务码表对象
	 * 
	 * @Title: BjdjServiceCodeAction
	 * @Description: 把服务码表对象放入回收站.
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = serviceCodeService.logicDeleteEntity(BjdjServiceCode.class.getSimpleName(), id);
		if (isSuc) {
			String logContent = "逻辑删除服务码";
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

	/**
	 * 物理删除服务码表对象
	 * 
	 * @Title: BjdjServiceCodeAction
	 * @Description: 把配置对象实际从数据库里面删除.
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = serviceCodeService.remove(id);
		if (isSuc) {
			String logContent = "物理删除服务码";
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

	/**
	 * 还原一个服务码表对象
	 * 
	 * @Title: BjdjServiceCodeAction
	 * @Description: 从回收站还原服务码表对象
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = serviceCodeService.restoreEntity(BjdjServiceCode.class.getSimpleName(), id);
		if (isSuc) {
			String logContent = "还原服务码";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();

			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}

	/**
	 * 审核服务码表对象
	 * 
	 * @Title: BjdjServiceCodeAction
	 * @Description:
	 * @param @return
	 * @param @throws ServiceException 设定文件
	 * @return 返回类型
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = serviceCodeService.auditEntity(BjdjServiceCode.class.getSimpleName(), id, statusValue);
		if (isSuc) {
			String logContent = "审核服务码";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();

			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}

	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * 
	 * @Title: BjdjServiceCodeAction
	 * @Description:
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = serviceCodeService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if (isSuc) {
			String logContent = "批量操作服务码";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();

			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}

	/**
	 * 物理删除对象
	 * 
	 * @Title: BjdjQueueAction
	 * @Description: 把配置对象实际从数据库里面删除.
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String batchRealDelete() throws ServiceException {

		boolean isSuc = serviceCodeService.batchRealDelete(BjdjServiceCode.class, idsValue);
		if (isSuc) {
			String logContent = "批量删除服务码";
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

	public BjdjServiceCode getBjdjServiceCode() {
		return bjdjServiceCode;
	}

	public void setBjdjServiceCode(BjdjServiceCode bjdjServiceCode) {
		this.bjdjServiceCode = bjdjServiceCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType_id() {
		return type_id;
	}

	public void setType_id(String type_id) {
		this.type_id = type_id;
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

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getNumber() {
		return number;
	}

	public String getState_id() {
		return state_id;
	}

	public void setState_id(String state_id) {
		this.state_id = state_id;
	}

	public IBjdjAppointmentService getAppointmentService() {
		return appointmentService;
	}

	public void setAppointmentService(IBjdjAppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}

	public BjdjAppointment getAppointment() {
		return appointment;
	}

	public void setAppointment(BjdjAppointment appointment) {
		this.appointment = appointment;
	}

	public String[] getName() {
		return name;
	}

	public void setName(String[] name) {
		this.name = name;
	}

	public String[] getCodes() {
		return codes;
	}

	public void setCodes(String[] codes) {
		this.codes = codes;
	}

	public String[] getIdCard() {
		return idCard;
	}

	public void setIdCard(String[] idCard) {
		this.idCard = idCard;
	}

	public String[] getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String[] flightNo) {
		this.flightNo = flightNo;
	}

	public Date[] getDate() {
		return date;
	}

	public void setDate(Date[] date) {
		this.date = date;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public IBjdjServiceCodeService getServiceCodeService() {
		return serviceCodeService;
	}

	public void setServiceCodeService(IBjdjServiceCodeService serviceCodeService) {
		this.serviceCodeService = serviceCodeService;
	}

	public IBjdjServiceCodeOperationService getServiceCodeLogService() {
		return serviceCodeLogService;
	}

	public void setServiceCodeLogService(
			IBjdjServiceCodeOperationService serviceCodeLogService) {
		this.serviceCodeLogService = serviceCodeLogService;
	}

	public IChannelCustomerInfoService getChannelCustomerInfoService() {
		return channelCustomerInfoService;
	}

	public void setChannelCustomerInfoService(
			IChannelCustomerInfoService channelCustomerInfoService) {
		this.channelCustomerInfoService = channelCustomerInfoService;
	}

	public IOrderInfoService getOrderInfoService() {
		return orderInfoService;
	}

	public void setOrderInfoService(IOrderInfoService orderInfoService) {
		this.orderInfoService = orderInfoService;
	}

	public ISystemDictionaryService getDictionaryService() {
		return dictionaryService;
	}

	public void setDictionaryService(ISystemDictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	public ISystemOperationLogService getLogService() {
		return logService;
	}

	public void setLogService(ISystemOperationLogService logService) {
		this.logService = logService;
	}

	public IMemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(IMemberService memberService) {
		this.memberService = memberService;
	}

	public IDepartFromPortService getFromPortService() {
		return fromPortService;
	}

	public void setFromPortService(IDepartFromPortService fromPortService) {
		this.fromPortService = fromPortService;
	}

	public char[] getRule() {
		return rule;
	}

	public void setRule(char[] rule) {
		this.rule = rule;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIDCard() {
		return IDCard;
	}

	public void setIDCard(String iDCard) {
		this.IDCard = iDCard;
	}
}
