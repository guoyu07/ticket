package com.ticket.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.enumer.BjdjCheckWay;
import com.ticket.enumer.BjdjOrderType;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.AirportEmployee;
import com.ticket.pojo.BjdjAppointment;
import com.ticket.pojo.BjdjDispatch;
import com.ticket.pojo.BjdjDispatchList;
import com.ticket.pojo.BjdjServiceCode;
import com.ticket.pojo.BjdjServiceItem;
import com.ticket.pojo.BjdjValidation;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.RoleInfo;
import com.ticket.pojo.SystemDictionary;
import com.ticket.pojo.SystemUser;
import com.ticket.service.IAirportEmployeeService;
import com.ticket.service.IBjdjAppointmentService;
import com.ticket.service.IBjdjDispatchListService;
import com.ticket.service.IBjdjDispatchService;
import com.ticket.service.IBjdjServiceCodeService;
import com.ticket.service.IBjdjServiceItemService;
import com.ticket.service.IBjdjValidationService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 便捷登机分单表控制器
 * 
 * @ClassName: BjdjDispatchAction
 * @Description: 提供便捷登机分单表的相关操作方法.
 * @author HiSay
 * @date 2015-11-23 22:53:55
 */
public class BjdjDispatchAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	// 便捷登机分单表的业务层
	@Resource
	protected IBjdjDispatchService dispatchService;
	// 分单流程表的业务层
	@Resource
	protected IBjdjDispatchListService dispatchListService;
	@Resource
	protected IBjdjAppointmentService appointmentService;
	@Resource
	protected ISystemDictionaryService dictionaryService;
	@Resource
	protected IBjdjValidationService validationService;
	@Resource
	protected IAirportEmployeeService employeeService;
	@Resource
	protected IBjdjServiceCodeService codeService;
	@Resource 
	protected ISystemOperationLogService logService;
	@Resource
	protected IBjdjServiceItemService itemService;


	// 分单流程表实体
	protected BjdjDispatchList dispatchList;
	// 便捷登机分单表实体
	protected BjdjDispatch dispatch;
	// 主键
	protected String id;
	// 派单时间
	protected Date time;
	// 服务码ID
	protected String serviceCode_id;
	// 用户ID
	protected String member_id;
	// 航班号
	protected String flightNo;
	// 登机口
	protected String boardingGate;
	// 是否结束
	protected boolean ended;
	// 分单流程
	protected String bjdjDispatchItem;
	// 电瓶车
	protected String electromobileDispatchItem;
	// 回复
	protected String feedback;

	private String keyword;
	
	/**
	 * 派单角色选择
	 * @return
	 */
	public String dispatchRole(){
		
		if (GeneralUtil.isNull(operationFlag)) {
			
			List<RoleInfo> list = dictionaryService.queryAll(RoleInfo.class); 
			ActionContext.getContext().put("list", list);
			
			String roleName = dispatchService.getDispatchRoleName();
			ActionContext.getContext().put("roleName", roleName);
			return "dispatchRole";
		}else{
			
			if(GeneralUtil.isNull(id)){
				
				data = AjaxData.responseError("角色名不能为空");
			}
			dispatchService.setDispathcRoleName(id);
			data = AjaxData.responseSuccess("设置成功");
		}
		return JSON;
	}
	
	/**
	 * @Description：接单的请求控制器
	 * @return
	 * @throws ServiceException
	 */
	public String ing() throws ServiceException {
		
		//非空验证.
		if(GeneralUtil.isNull(idsValue)) {
			data = AjaxData.responseError(getText("id.required"));
			return JSON;
		}
		String[] ids = idsValue.split(",");
		for(String id : ids){
			//判断如果有空值，则跳过
			if(id.equals("")){
				continue;
			}
			//修改分单流程表实体
			try {
				dispatchService.accept(id.trim());
			} catch (ServiceException e) {
				e.printStackTrace();
				data = AjaxData.responseError(e.getMessage());
				return JSON;
			}
		}
		String logContent = "便捷登机分单整单接单";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		data = AjaxData.responseSuccess(getText("dispatch.receive.success"));
		return JSON;
	}
	
	/**
	 * @Description：核销的请求控制器
	 * @return
	 * @throws ServiceException
	 */
	public String end() throws ServiceException {
		
		//非空验证.
		if(GeneralUtil.isNull(idsValue)) {
			data = AjaxData.responseError(getText("id.required"));
			return JSON;
		}
//		if(GeneralUtil.isNull(feedback)) {
//			data = AjaxData.responseError(getText("dispatch.verificate.feedback.required"));
//			return JSON;
//		}
		
		String[] ids = idsValue.split(",");
		for(String id : ids){
			//判断如果有空值，则跳过
			if(id.equals("")){
				continue;
			}
			//修改分单流程表实体
			try {
				dispatchService.verification(id.trim(), feedback, BjdjCheckWay.artificial);
			} catch (Exception e) {
				data = AjaxData.responseError(e.getMessage());
				return JSON;
			}
		}
		String logContent = "便捷登机分单整单核销";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		data = AjaxData.responseSuccess(getText("dispatch.verificate.success"));
		return JSON;
	}

	/**
	 * @Description：接单数量
	 * @return
	 */
	public String receiveAmount() {

		int totalAmount = dispatchService.receiveTotalAmount(id);
		int todayAmount = dispatchService.receiveAmountToday(id);

		data = AjaxData.responseSuccess(getText("dispatch.receive.tip", new String[] {totalAmount + "", todayAmount + "" }));
		return JSON;
	}

	/**
	 * 添加便捷登机分单表
	 * 
	 * @Title: BjdjDispatchAction
	 * @Description:
	 * @return
	 * @throws ServiceException
	 */
	public String add() throws ServiceException {
		if (GeneralUtil.isNull(operationFlag)) {

			BjdjValidation validation = validationService.get(BjdjValidation.class, id);
			List<BjdjServiceItem> list = null;
			if (validation.getType() == BjdjOrderType.bjdj) {

				list = itemService.get(validation.getAppointment().getServiceCode().getPackageType());
			}
			//如果是免进厅服务，去除厅内服务
			idsValue = "";
			if(validation.getAppointment().getHall() == null){
				
				for(int i = 0; i < list.size(); i++){
					
					if("厅内服务".equals(list.get(i).getName())){
						
						list.remove(i);
						break;
					}
				}
			}
			for(int i = 0; i < list.size(); i++){
				
				idsValue += list.get(i).getId() + ",";
			}
			ActionContext.getContext().put("items", list);
			
			//查看出所有的服务岗人员
			SystemUser user = getSessionAdminUser();
			if (user != null && user instanceof AirportEmployee) {

				AirportEmployee e = (AirportEmployee) user;
				List<AirportEmployee> employee = null;
				if(e.getDepartment_id() == null){
					
					employee = employeeService.queryByRoleName(dispatchService.getDispatchRoleName());
				}else{
					
					employee = employeeService.queryByRoleNameAndDepartment(dispatchService.getDispatchRoleName(), e.getDepartment_id());
				}
				ActionContext.getContext().put("employee", employee);
			}

			return "addBjdjDispatch";
		} else {
			// 非空验证.
			if (GeneralUtil.isNull(bjdjDispatchItem)) {

				data = AjaxData.responseError(getText("select.required"));
				return JSON;
			}
			BjdjValidation validation = validationService.queryById(BjdjValidation.class.getName(), id);

			String[] dispatchItemArr = bjdjDispatchItem.split(",");
			// 检查是否已经分单过了
			if (validation.getDispatch() != null) {

				data = AjaxData.responseError(getText("dispatch.exist"));
				return JSON;
			}

			// 保存便捷登机分单
			BjdjDispatch isSuc = dispatchService.persist(id, idsValue.split(","), dispatchItemArr);

			// 根据保存结果返回页面
			if (isSuc != null) {

				validationService.merge(validation);
				
				String logContent = "新增便捷登机分单";
				String logName = SystemOparetionLogUtil.getLogName();
				String logTime = SystemOparetionLogUtil.getLogTime();
				String logIp = SystemOparetionLogUtil.getLogIp();
				
				logService.persist(logName, logContent, logTime, logIp, versionFlag);
				data = AjaxData.responseSuccess(getText("addSuccess"));
			} else {
				data = AjaxData.responseError(getText("addFailed"));
			}
			return JSON;
		}
	}
	/**
	 * 批量派单
	 * @return
	 * @throws ServiceException
	 */
	public String batchPaidan() throws ServiceException {
		if (GeneralUtil.isNull(operationFlag)) {
			String[] ids = idsValue.split(",");
			BjdjValidation validation = validationService.queryById(
					BjdjValidation.class.getName(), ids[0]);
			if (validation.getType() == BjdjOrderType.bjdj) {

				List<BjdjServiceItem> list = dictionaryService.queryAll(BjdjServiceItem.class);
				ActionContext.getContext().put("items", list);
			} else {

				List<SystemDictionary> list = dictionaryService
						.querySubByParentName("electromobile_dispatch_item");
				ActionContext.getContext().put("items", list);
			}

			SystemUser user = (SystemUser) getSession().get(
					ContextConstants.SCOPE_SYSTEM_USER);
			if (user != null && user instanceof AirportEmployee) {

				AirportEmployee e = (AirportEmployee) user;
				List<AirportEmployee> employee = employeeService.queryByRoleNameAndDepartment(dispatchService.getDispatchRoleName(), e.getDepartment_id());
				ActionContext.getContext().put("employee", employee);
			}

			return "addBatchBjdjDispatch";
		} else {
			// 非空验证.
			if (GeneralUtil.isNull(bjdjDispatchItem)) {

				data = AjaxData.responseError(getText("select.required"));
				return JSON;
			}
			
			List<BjdjValidation> validations = validationService.findInIds(idsValue);
			
			// 检查是否分配了服务岗员工
			List<BjdjServiceItem> items = null;
			if (validations.get(0).getType() == BjdjOrderType.bjdj) {

				items = dictionaryService.queryAll(BjdjServiceItem.class);
			}/* else {

				items = dictionaryService
						.querySubByParentName("electromobile_dispatch_item");
			}*/
			String[] dispatchItemArr = bjdjDispatchItem.split(",");
			Boolean allRight = true;
//			String text = "";
			for(BjdjValidation validation1 : validations){
				BjdjValidation validation = validationService.queryById(
						BjdjValidation.class.getName(), validation1.getId());
				
				if (dispatchItemArr.length != items.size()) {
					allRight = false;
//					text = getText("select.required");
//					break;
					data = AjaxData.responseError(getText("select.required"));
					return JSON;
				}
				// 检查是否已经分单过了
				if (validation.getDispatch() != null) {
					allRight = false;
					/*text = getText("dispatch.exist");
					break;*/
					data = AjaxData.responseError(getText("dispatch.exist"));
					return JSON;
				}
			}
			Boolean allIsSuc = true;
			if(allRight){
				for(BjdjValidation validation : validations){
					// 保存便捷登机分单
					BjdjDispatch isSuc = dispatchService.persist(validation.getId(), idsValue.split(","), dispatchItemArr);
					if(isSuc != null){
						validationService.merge(validation);
					}else {
						allIsSuc = false;
						break;
					}
				}
			}
			if(allIsSuc){
				String logContent = "批量便捷登机分单";
				String logName = SystemOparetionLogUtil.getLogName();
				String logTime = SystemOparetionLogUtil.getLogTime();
				String logIp = SystemOparetionLogUtil.getLogIp();
				
				logService.persist(logName, logContent, logTime, logIp, versionFlag);
				data = AjaxData.responseSuccess(getText("addSuccess"));
			}else{
				data = AjaxData.responseError(getText("addFailed"));
			}
			return JSON;
		}
	}

	/**
	 * 添加便捷登机分单表
	 * 
	 * @Title: BjdjDispatchAction
	 * @Description:
	 * @return
	 * @throws ServiceException
	 */
//	public String addForElectromobile() throws ServiceException {
//		if (GeneralUtil.isNull(operationFlag)) {
//
//			List<SystemDictionary> list = dictionaryService
//					.querySubByParentName("electromobile_dispatch_item");
//			ActionContext.getContext().put("items", list);
//
//			SystemUser user = (SystemUser) getSession().get(
//					ContextConstants.SCOPE_SYSTEM_USER);
//			if (user != null && user instanceof AirportEmployee) {
//
//				AirportEmployee e = (AirportEmployee) user;
//				List<AirportEmployee> employee = employeeService
//						.queryByRoleNameAndDepartment(dispatchService.getDispatchRoleName(), e.getDepartment_id());
//				ActionContext.getContext().put("employee", employee);
//			}
//
//			return "addElectromobileDispatch";
//		} else {
//			// 非空验证.
//			if (GeneralUtil.isNull(electromobileDispatchItem)) {
//
//				AjaxUtil.writeErrorJson(response, request,
//						getText("select.required"));
//				return JSON;
//			}
//			// 检查是否分配了服务岗员工
//			List<SystemDictionary> items = dictionaryService
//					.querySubByParentName("electromobile_dispatch_item");
//			String[] dispatchItemArr = electromobileDispatchItem.split(",");
//			if (dispatchItemArr.length != items.size()) {
//
//				AjaxUtil.writeErrorJson(response, request,
//						getText("select.required"));
//				return JSON;
//			}
//			// 检查是否已经分单过了
//			BjdjValidation validation = validationService.queryById(
//					BjdjValidation.class.getName(), id);
//			if (validation.getDispatch() != null) {
//
//				AjaxUtil.writeErrorJson(response, request,
//						getText("dispatch.exist"));
//				return JSON;
//			}
//
//			// 保存便捷登机分单
//			BjdjDispatch isSuc = dispatchService.persistForElectromoible(id,
//					dispatchItemArr);
//
//			// 根据保存结果返回页面
//			if (isSuc != null) {
//
//				validationService.merge(validation);
//				String logContent = "新增电瓶车分单";
//				String logName = SystemOparetionLogUtil.getLogName();
//				String logTime = SystemOparetionLogUtil.getLogTime();
//				String logIp = SystemOparetionLogUtil.getLogIp();
//				
//				logService.persist(logName, logContent, logTime, logIp, versionFlag);
//				AjaxUtil.writeSuccessJson(response, request,
//						getText("addSuccess"));
//			} else {
//				data = AjaxData.responseError(getText("addFailed"));
//			}
//			return JSON;
//		}
//	}

	/**
	 * 修改便捷登机分单表
	 * 
	 * @Title: BjdjDispatchAction
	 * @Description:
	 * @return
	 * @throws ServiceException
	 */
	public String edit() throws ServiceException {
		if (GeneralUtil.isNull(operationFlag)) {
			this.setBjdjDispatch(dispatchService.queryById(
					BjdjDispatch.class.getSimpleName(), id));
			return "editBjdjDispatch";
		} else {
			// 非空验证.
			if (GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(time)) {
				data = AjaxData.responseError(getText("time.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(serviceCode_id)) {
				data = AjaxData.responseError(getText("serviceCode_id.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(member_id)) {
				data = AjaxData.responseError(getText("member_id.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(flightNo)) {
				data = AjaxData.responseError(getText("flightNo.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(boardingGate)) {
				data = AjaxData.responseError(getText("boardingGate.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(ended)) {
				data = AjaxData.responseError(getText("ended.required"));
				return JSON;
			}
			// 修改便捷登机分单表实体
			// BjdjDispatch isSuc = dispatchService.merge(id, time,
			// serviceCode_id, member_id, flightNo, boardingGate, ended,
			// versionFlag);
			// //根据修改结果返回页面
			// if(isSuc != null) {
			// AjaxUtil.writeSuccessJson(response, request,
			// getText("editSuccess"));
			// } else {
			// AjaxUtil.writeErrorJson(response, request,
			// getText("editFailed"));
			// }
			return JSON;
		}
	}

	/**
	 * 管理便捷登机分单表实体
	 * 
	 * @Title: BjdjDispatchAction
	 * @Description:
	 * @return
	 * @throws ServiceException
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0]
				.getMethodName());

		List<SystemDictionary> list = dictionaryService
				.querySubByParentName("bjdj_dispatch_item");
		ActionContext.getContext().put("items", list);
		if (GeneralUtil.isNotNull(keyword)) {
			BjdjServiceCode code = codeService.getByCode(keyword);
			BjdjAppointment appointment = appointmentService.getByServiceCode(code);
			BjdjValidation validation = appointment.getValidation();
			BjdjDispatch dispatch = validation.getDispatch();
			List<BjdjDispatch> dispatchs = new ArrayList<BjdjDispatch>();
			dispatchs.add(dispatch);
			Pagination pagination = new Pagination();
			pagination.setPageList(dispatchs);
			pagination.setTotalCount(dispatchs.size());
			this.setPageModule(pagination);
		} else {
			// 查询已分单的预约
			Pagination paging = dispatchService.queryEntity(
					" and s.validation.type=?3", BjdjOrderType.bjdj);
			this.setPageModule(paging);
		}
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageBjdjDispatch";
	}

	/**
	 * 管理便捷登机分单表实体
	 * 
	 * @Title: BjdjDispatchAction
	 * @Description:
	 * @return
	 * @throws ServiceException
	 */
//	public String manageForElectromobile() throws ServiceException {
//		this.setCurrentMethod(new Exception().getStackTrace()[0]
//				.getMethodName());
//
//		List<SystemDictionary> list = dictionaryService
//				.querySubByParentName("electromobile_dispatch_item");
//		ActionContext.getContext().put("items", list);
//
//		if (GeneralUtil.isNotNull(keyword)) {
//			BjdjServiceCode code = codeService.getByCode(keyword);
//			BjdjDispatch dispatch = code.getValidation().getDispatch();
//			List<BjdjDispatch> dispatchs = new ArrayList<BjdjDispatch>();
//			dispatchs.add(dispatch);
//			Pagination pagination = new Pagination();
//			pagination.setPageList(dispatchs);
//			pagination.setTotalCount(dispatchs.size());
//			this.setPageModule(pagination);
//		} else {
//			// 查询已分单的预约
//			Pagination paging = dispatchService.queryEntity(
//					" and s.validation.type=?3", BjdjOrderType.electromobile);
//			this.setPageModule(paging);
//		}
//
//		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
//		return "manageElectromobileDispatch";
//	}

	/**
	 * 管理便捷登机验证表实体
	 * 
	 * @Title: BjdjValidationAction
	 * @Description:
	 * @return
	 * @throws ServiceException
	 */
	public String manageValidation() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0]
				.getMethodName());

		// 查询验证通过，但是未分单的预约
		Pagination paging = validationService.queryEntity(" and passed=true", new Object[] {});
		if (paging != null) {

			List list = paging.getPageList();
			for (int i = 0; i < list.size(); i++) {

				BjdjValidation validation = (BjdjValidation) list.get(i);
				if (validation.getDispatch() != null) {

					list.remove(validation);
					i -= 1;
				}
			}
		}

		this.setPageModule(paging);
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageBjdjValidation";
	}

	/**
	 * 查看回收站
	 * 
	 * @Title: BjdjDispatchAction
	 * @Description:
	 * @return
	 * @throws ServiceException
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(dispatchService.queryRecycleEntity(versionFlag,
				ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleBjdjDispatch";
	}

	/**
	 * 逻辑删除便捷登机分单表对象
	 * 
	 * @Title: BjdjDispatchAction
	 * @Description: 把便捷登机分单表对象放入回收站.
	 * @return
	 * @throws ServiceException
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = dispatchService.logicDeleteEntity(
				BjdjDispatch.class.getSimpleName(), id);
		if (isSuc) {
			String logContent = "逻辑删除便捷登机分单";
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
	 * 物理删除便捷登机分单表对象
	 * 
	 * @Title: BjdjDispatchAction
	 * @Description: 把配置对象实际从数据库里面删除.
	 * @return
	 * @throws ServiceException
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = dispatchService.remove(id);
		if (isSuc) {
			String logContent = "物理删除便捷登机分单";
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
	 * 还原一个便捷登机分单表对象
	 * 
	 * @Title: BjdjDispatchAction
	 * @Description: 从回收站还原便捷登机分单表对象
	 * @return
	 * @throws ServiceException
	 */
	public String restore() throws ServiceException {
		boolean isSuc = dispatchService.restoreEntity(
				BjdjDispatch.class.getSimpleName(), id);
		if (isSuc) {
			String logContent = "还原便捷登机分单";
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
	 * 审核便捷登机分单表对象
	 * 
	 * @Title: BjdjDispatchAction
	 * @Description:
	 * @throws ServiceException
	 *             设定文件
	 * @return 返回类型
	 */
	public String audit() throws ServiceException {
		boolean isSuc = dispatchService.auditEntity(
				BjdjDispatch.class.getSimpleName(), id, statusValue);
		if (isSuc) {
			String logContent = "审核便捷登机分单";
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
	 * @Title: BjdjDispatchAction
	 * @Description:
	 * @return
	 * @throws ServiceException
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = dispatchService.batchOperationEntity(versionFlag,
				idsValue, batchOperationType, isChecked);
		if (isSuc) {
			String logContent = "批量操作便捷登机分单";
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
	 * 批量删除便捷登机分单
	 */
	public String batchRealDelete() throws ServiceException {
		boolean isSuc = baseService.batchRealDelete(BjdjDispatch.class,
				idsValue);
		if (isSuc) {
			String logContent = "批量删除便捷登机分单";
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

	public BjdjDispatch getBjdjDispatch() {
		return dispatch;
	}

	public void setBjdjDispatch(BjdjDispatch bjdjDispatch) {
		this.dispatch = bjdjDispatch;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getServiceCode_id() {
		return serviceCode_id;
	}

	public void setServiceCode_id(String serviceCode_id) {
		this.serviceCode_id = serviceCode_id;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getBoardingGate() {
		return boardingGate;
	}

	public void setBoardingGate(String boardingGate) {
		this.boardingGate = boardingGate;
	}

	public boolean getEnded() {
		return ended;
	}

	public void setEnded(boolean ended) {
		this.ended = ended;
	}

	public BjdjDispatch getDispatch() {
		return dispatch;
	}

	public void setDispatch(BjdjDispatch dispatch) {
		this.dispatch = dispatch;
	}

	public BjdjDispatchList getBjdjDispatchList() {
		return dispatchList;
	}

	public void setBjdjDispatchList(BjdjDispatchList bjdjDispatchList) {
		this.dispatchList = bjdjDispatchList;
	}

	public String getBjdjDispatchItem() {
		return bjdjDispatchItem;
	}

	public void setBjdjDispatchItem(String bjdjDispatchItem) {
		this.bjdjDispatchItem = bjdjDispatchItem;
	}

	public IBjdjDispatchService getDispatchService() {
		return dispatchService;
	}

	public void setDispatchService(IBjdjDispatchService dispatchService) {
		this.dispatchService = dispatchService;
	}

	public IBjdjDispatchListService getDispatchListService() {
		return dispatchListService;
	}

	public void setDispatchListService(
			IBjdjDispatchListService dispatchListService) {
		this.dispatchListService = dispatchListService;
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

	public IBjdjValidationService getValidationService() {
		return validationService;
	}

	public void setValidationService(IBjdjValidationService validationService) {
		this.validationService = validationService;
	}

	public IAirportEmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(IAirportEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public BjdjDispatchList getDispatchList() {
		return dispatchList;
	}

	public void setDispatchList(BjdjDispatchList dispatchList) {
		this.dispatchList = dispatchList;
	}

	public String getElectromobileDispatchItem() {
		return electromobileDispatchItem;
	}

	public void setElectromobileDispatchItem(String electromobileDispatchItem) {
		this.electromobileDispatchItem = electromobileDispatchItem;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public IBjdjServiceCodeService getCodeService() {
		return codeService;
	}

	public void setCodeService(IBjdjServiceCodeService codeService) {
		this.codeService = codeService;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
}
