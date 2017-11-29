package com.ticket.action;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.enumer.BjdjDispatchListState;
import com.ticket.enumer.BjdjOrderType;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.AirportEmployee;
import com.ticket.pojo.BjdjAppointment;
import com.ticket.pojo.BjdjDispatch;
import com.ticket.pojo.BjdjDispatchList;
import com.ticket.pojo.BjdjServiceCode;
import com.ticket.pojo.BjdjServiceItem;
import com.ticket.pojo.BjdjValidation;
import com.ticket.pojo.SystemDictionary;
import com.ticket.pojo.SystemUser;
import com.ticket.service.IBjdjServiceCodeService;
import com.ticket.util.GeneralUtil;

import net.sf.json.JSONObject;

public class WapBjdjDispatchAction extends BjdjDispatchAction {

	private static final long serialVersionUID = 1L;
	@Resource
	private IBjdjServiceCodeService codeService;

	private String serviceCode;
	/**
	 * 登录页面
	 * @return
	 */
	public String loginPage() {

		return "loginPage";
	}
	/**
	 * 管理便捷登机分单表实体
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(validationService.queryEntity(" and s.type=?3", BjdjOrderType.bjdj));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);

		SystemUser user = (SystemUser) getSession().get(ContextConstants.SCOPE_SYSTEM_USER);
		List<BjdjServiceItem> list = dictionaryService.queryAll(BjdjServiceItem.class);
		ActionContext.getContext().put("items", list);
		if (user != null && user instanceof AirportEmployee) {

			AirportEmployee e = (AirportEmployee) user;
			List<AirportEmployee> employee = employeeService.queryByRoleNameAndDepartment(dispatchService.getDispatchRoleName(), e.getDepartment_id());
			ActionContext.getContext().put("employee", employee);
		}
		return "manageBjdj";
	}
	/**
	 * 管理便捷登机分单表实体
	 */
	public String manageForElectromobile() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0]
				.getMethodName());
		this.setPageModule(validationService.queryEntity(" and s.type=?3",
				BjdjOrderType.electromobile));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);

		SystemUser user = (SystemUser) getSession().get(
				ContextConstants.SCOPE_SYSTEM_USER);
		List<SystemDictionary> list = dictionaryService
				.querySubByParentName("electromobile_dispatch_item");
		ActionContext.getContext().put("items", list);
		if (user != null && user instanceof AirportEmployee) {

			AirportEmployee e = (AirportEmployee) user;
			List<AirportEmployee> employee = employeeService
					.queryByRoleNameAndDepartment(dispatchService.getDispatchRoleName(), e.getDepartment_id());
			ActionContext.getContext().put("employee", employee);
		}
		return "manageElemobile";
	}
	/**
	 * 便捷登机进度
	 * @return
	 * @throws ServiceException
	 */
	public String bjdjJIndu() throws ServiceException {
		super.manage();
		return "bjdjJinDu";
	}
	/**
	 * 电瓶车进度
	 * @return
	 * @throws ServiceException
	 */
//	public String elemobileJinDu() throws ServiceException {
//		super.manageForElectromobile();
//		return "elemobileJinDu";
//	}
	/**
	 * 根据服务码查找便捷登机
	 * @return
	 */
	public String findBjdjByServiceCode() {
		BjdjServiceCode code = codeService.getByCode(serviceCode);
		if (GeneralUtil.isNull(code)) {
			data = AjaxData.responseError("此服务码尚未购买");
			return JSON;
		}
		BjdjAppointment appointment = appointmentService.getByServiceCode(code);
		BjdjValidation validation = appointment.getValidation();

		BjdjDispatch dispatch = validation.getDispatch();
		List<BjdjDispatchList> list2 = dispatch.getDispatchList();
		JSONObject object = new JSONObject();
		for (int i = 0; i < list2.size(); i++) {
			object.put("employeeName" + i, list2.get(i).getEmployee().getName());
			BjdjDispatchListState state = list2.get(i).getState();
			object.put("state" + i, state);
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(validation.getStatus().getCreateTime());
		String useTime = sdf.format(validation.getAppointment().getUseTime());
		object.put("buyTime", time);
		object.put("serviceCode", validation.getAppointment().getServiceCode()
				.getCode());
		object.put("name", validation.getAppointment().getName());
		object.put("flightNo", validation.getAppointment().getFlightNo());
		object.put("useTime", useTime);
		object.put("ended", validation.getDispatch().getEnded());

		data = AjaxData.responseSuccess(object);
		return JSON;
	}
	/**
	 * 根据服务码查找电瓶车
	 * @return
	 * @throws ServiceException
	 */
//	public String findElemobileBySeiveiceCode() throws ServiceException {
//		BjdjServiceCode code = codeService.getByCode(serviceCode);
//		if (GeneralUtil.isNull(code)) {
//			data = AjaxData.responseError("此服务码尚未购买");
//			return JSON;
//		}
//		String serviceCOde_id = code.getId();
//		// List<BjdjValidation> list = code.getValidation();
//		// BjdjValidation validation = list.get(0);
//
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		BjdjValidation validation = validationService
//				.findByServiceCode(serviceCOde_id);
//		JSONObject object = new JSONObject();
//		String time = sdf.format(validation.getDispatch().getTime());
//		object.put("time", time);
//		object.put("serviceCode", validation.getServiceCode().getCode());
//
//		List<BjdjDispatchList> list = validation.getDispatch()
//				.getDispatchList();
//		BjdjDispatchList dispatchList = list.get(0);
//		object.put("employeeName", dispatchList.getEmployee().getName());
//
//		// String validation_id = validation.getId();
//
//		// BjdjDispatch dispatch =
//		// dispatchService.findByValidationId(validation_id);
//		object.put("ended", validation.getDispatch().getEnded());
//
//		data = AjaxData.responseSuccess(object);
//		return JSON;
//	}
	/**
	 * 添加便捷登机分单表
	 */
	public String add() throws ServiceException {
		if (",,,".equals(bjdjDispatchItem)) {

			data = AjaxData.responseError(getText("select.required"));
			return JSON;
		}
		super.add();
		return JSON;
	}
	/**
	 * 接单数量
	 */
	public String receiveAmount() {
		return super.receiveAmount();
	}
	/**
	 * 添加电瓶车分单表
	 */
//	public String addForElectromobile() throws ServiceException {
//		if (",".equals(electromobileDispatchItem)) {
//
//			AjaxUtil.writeErrorJson(response, request,
//					getText("select.required"));
//			return JSON;
//		}
//		super.addForElectromobile();
//		return JSON;
//	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
}
