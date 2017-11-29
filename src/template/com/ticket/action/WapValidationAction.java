package com.ticket.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.zxing.NotFoundException;
import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.enumer.BjdjOrderType;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.AirportEmployee;
import com.ticket.pojo.BjdjAppointment;
import com.ticket.pojo.BjdjHall;
import com.ticket.pojo.BjdjServiceCode;
import com.ticket.pojo.BjdjValidation;
import com.ticket.util.GeneralUtil;
import com.ticket.util.QRCodeDecoderHandlerUtil;

import net.sf.json.JSONObject;

public class WapValidationAction extends BjdjValidationAction {

	private static final long serialVersionUID = 1L;
	private String img64;
	/**
	 * 添加便捷登机验证
	 */
	public String add() {
		if (GeneralUtil.isNull(operationFlag)) {

			hallList = hallService.queryAll(BjdjHall.class);

			return "yzIndex1";
		} else {
			// 非空验证.
			if (GeneralUtil.isNull(serviceCode)) {
				data = AjaxData.responseError(getText("serviceCode.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(hall)) {
				data = AjaxData.responseError(getText("hall.id.requird"));
				return JSON;
			}
			// 检查用户是否为机场员工用户
			if (getSessionAdminUser() instanceof AirportEmployee == false) {

				data = AjaxData.responseError(getText("airportEmployee.must"));
				return JSON;
			}
			AirportEmployee employee = (AirportEmployee) getSessionAdminUser();
			// 服务码是否未购买
			BjdjServiceCode serviceCodeObj = serviceCodeService
					.getByCode(serviceCode);
			if (serviceCodeObj == null) {

				data = AjaxData.responseError(getText("serviceCode.notBuy"));
				return JSON;
			}
			// 服务码类型是否正确
			if (serviceCodeObj.getOrder().getType() == BjdjOrderType.electromobile) {
				data = AjaxData.responseError(getText("validation.type.error.electromobile"));
				return JSON;
			}
			// 通过服务码得到预约对象
			BjdjAppointment appointment;
			try {
				appointment = appointmentService.getByServiceCode(serviceCode);
			} catch (ServiceException e) {
				e.printStackTrace();
				data = AjaxData.responseError(e.getMessage());
				return JSON;
			}
			if (appointment == null) {

				data = AjaxData.responseError(getText("serviceCode.noAppointment"));
				return JSON;
			}

			// 检查是否已经验证过
			if (validationService.isPassedValidatation(appointment)) {

				data = AjaxData.responseError(getText("validation.passed.exist"));
				return JSON;
			}

			// 是否大于起飞时间90分钟
			Integer appointmentMinutes = Integer.parseInt(dictionaryService
					.getByName("appointment_minutes").getValue());
			if (System.currentTimeMillis() + appointmentMinutes * 60 * 1000 > appointment
					.getUseTime().getTime()) {

				data = AjaxData.responseError(getText(
						"serviceCode.validation.time",
						new String[] { appointmentMinutes + "" }));
				return JSON;
			}

			// 保存便捷登机验证表实体
			BjdjValidation validation = null;
			try {
				validation = validationService.persist(appointment, hall,
						new Date(), employee.getId(), true);
			} catch (ServiceException e) {
				e.printStackTrace();
				data = AjaxData.responseError(e.getMessage());
				return JSON;
			}
			// 根据保存结果返回页面
			data = AjaxData.responseSuccess("验证成功");
			return JSON;
		}
	}
	/**
	 * 登录页面
	 * @return
	 */
	public String loginPage(){
		
		return "loginPage";
	}
	/**
	 * 添加电瓶车验证
	 */
//	public String addElectromobile() {
//		if (GeneralUtil.isNull(operationFlag)) {
//
//			return "yzIndex";
//		} else {
//			// 非空验证.
//			if (GeneralUtil.isNull(serviceCode)) {
//				data = AjaxData.responseError(getText("serviceCode.required"));
//				return JSON;
//			}
//			// 检查用户是否为机场员工用户
//			if (getSessionAdminUser() instanceof AirportEmployee == false) {
//
//				data = AjaxData.responseError(getText("airportEmployee.must"));
//				return JSON;
//			}
//			AirportEmployee employee = (AirportEmployee) getSessionAdminUser();
//
//			BjdjServiceCode serviceCodeObj = serviceCodeService
//					.getByCode(serviceCode);
//			if (serviceCodeObj == null) {
//
//				data = AjaxData.responseError(getText("serviceCode.notBuy"));
//				return JSON;
//			}
//
//			if (serviceCodeObj.getOrder().getType() == BjdjOrderType.bjdj) {
//
//				data = AjaxData.responseError(getText("validation.type.error.bjdj"));
//				return JSON;
//			}
//
//			// 保存电瓶车验证表实体
//			BjdjValidation validation = null;
//			try {
//				validation = validationService.persist(serviceCodeObj, null,
//						new Date(), employee, true);
//			} catch (ServiceException e) {
//				e.printStackTrace();
//				data = AjaxData.responseError(e.getMessage());
//				return JSON;
//			}
//			// 根据保存结果返回页面
//			data = AjaxData.responseSuccess("验证成功");
//			return JSON;
//		}
//	}
	/**
	 * 管理电瓶车验证
	 */
//	public String manageElectromobile() throws ServiceException {
//		super.manageElectromobile();
//		return "yanzhengjilu1";
//	}
	/**
	 * 管理便捷登机验证
	 */
	public String manage() throws ServiceException{
		super.manage();
		return "yanzhengjilu2";
	}
	/**
	 * 根据服务码查找便捷登机验证
	 * @return
	 * @throws ServiceException
	 */
	public String findByServiceCode() throws ServiceException{
		BjdjServiceCode serviceCodeObj = serviceCodeService.getByCode(serviceCode);
		if(serviceCodeObj == null){
			data = AjaxData.responseError(getText("serviceCode.notBuy"));
			return JSON;
		}
		if(serviceCodeObj.getOrder().getType() == BjdjOrderType.bjdj){
			data = AjaxData.responseError(getText("validation.type.error.bjdj"));
			return JSON;
		}
		
		
		BjdjValidation validation = validationService.findByServiceCode(serviceCode);
		if(validation == null){
			data = AjaxData.responseError("您输入的服务码尚未购买");
		}else{
			JSONObject object = new JSONObject();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String time = sdf.format(validation.getTime());
			object.put("time", time);
			object.put("name", validation.getEmployee().getName());
			object.put("serviceCode", serviceCode);
			object.put("passed", validation.isPassed());
			data = AjaxData.responseSuccess(object);
		}
		return JSON;
	}
	/**
	 * 根据服务码查找电瓶车验证
	 * @return
	 * @throws ServiceException
	 */
	public String findByServiceCode1() throws ServiceException{
		BjdjServiceCode serviceCodeObj = serviceCodeService.getByCode(serviceCode);
		if(serviceCodeObj == null){
			data = AjaxData.responseError(getText("serviceCode.notBuy"));
			return JSON;
		}
		if(serviceCodeObj.getOrder().getType() == BjdjOrderType.electromobile){
			data = AjaxData.responseError(getText("validation.type.error.electromobile"));
			return JSON;
		}
		
		BjdjAppointment appointment;
		try {
			appointment = appointmentService.getByServiceCode(serviceCode);
		} catch (ServiceException e) {
			e.printStackTrace();
			data = AjaxData.responseError(e.getMessage());
			return JSON;
		}
		if (appointment == null) {

			data = AjaxData.responseError(getText("serviceCode.noAppointment"));
			return JSON;
		}
		
		BjdjValidation validation = validationService.findByServiceCode1(serviceCode);
		if(validation == null){
			data = AjaxData.responseError("您输入的服务码尚未购买");
		}else{
			JSONObject object = new JSONObject();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = sdf.format(validation.getTime());
			object.put("time", time);
			object.put("name", validation.getEmployee().getName());
			object.put("serviceCode", serviceCode);
			object.put("passed", validation.isPassed());
			data = AjaxData.responseSuccess(object);
		}
		return JSON;
	}
	/**
	 * 二维码验证页面
	 * @return
	 * @throws ServiceException
	 */
	public String yanZhengErWeiMaIndex() throws ServiceException{
		
		return "yanZhengErWeiMaIndex";
	}
	/**
	 * 二维码验证页面
	 * @return
	 * @throws ServiceException
	 */
	public String yanZhengErWeiMaIndex1() throws ServiceException{
		return "yanzhengerweimabjdj";
	}
	/**
	 * 便捷登机二维码验证
	 * @return
	 * @throws ServiceException
	 */
	public String erWeiMa() throws ServiceException{
		try{
			String contant = QRCodeDecoderHandlerUtil.decoderQRCodeForBase64(img64);
			if(GeneralUtil.isNull(contant)){
				data = AjaxData.responseError("");
				return JSON;
			}else{
				data = AjaxData.responseSuccess(contant);
				return JSON;
			}
		}catch(NotFoundException e){
			data = AjaxData.responseError("");
			return JSON;
		}catch(Exception e1){
			data = AjaxData.responseError("");
			return JSON;
		}
	}
	/**
	 * 便捷登机二维码验证成功
	 * @return
	 * @throws Exception
	 */
	public String erWeiMaSuccess() throws Exception{
		BjdjServiceCode code = serviceCodeService.getByCode(serviceCode);
		if(GeneralUtil.isNotNull(code)){
			BjdjOrderType orderType = code.getOrderType();
			Double price = Double.parseDouble(dictionaryService.getValueByName(orderType + "_price"));
			String type = "";
			if(orderType == (BjdjOrderType.valueOf("electromobile"))){
				type = "电瓶车";
			}else{
				type="便捷登机";
			}
			ActionContext.getContext().put("price", price);
			ActionContext.getContext().put("type", type);
			ActionContext.getContext().put("serviceCode", serviceCode);
			return "erweimasuccess";
		}else{
			return ERROR;
		}
	}
	/**
	 * 电瓶车二维码验证成功
	 * @return
	 * @throws Exception
	 */
	public String erWeiMaSuccess1() throws Exception{
		BjdjServiceCode code = serviceCodeService.getByCode(serviceCode);
		if(GeneralUtil.isNotNull(code)){
			BjdjOrderType orderType = code.getOrderType();
			Double price = Double.parseDouble(dictionaryService.getValueByName(orderType + "_price"));
			String type = "";
			if(orderType == (BjdjOrderType.valueOf("electromobile"))){
				type = "电瓶车";
			}else{
				type="便捷登机";
			}
			hallList = hallService.queryAll(BjdjHall.class);
			ActionContext.getContext().put("price", price);
			ActionContext.getContext().put("type", type);
			ActionContext.getContext().put("serviceCode", serviceCode);
			ActionContext.getContext().put("hallList", hallList);
			return "erweimasuccess1";
		}else{
			return ERROR;
		}
	}
	/**
	 * 便捷登机二维码验证成功页面
	 * @return
	 * @throws ServiceException
	 */
	public String erWeiMaYanZhengSuccess() throws ServiceException{
		return "erWeiMaYanZhengSuccess";
	}
	/**
	 * 电瓶车二维码验证成功页面
	 * @return
	 * @throws ServiceException
	 */
	public String erWeiMaYanZhengSuccess1() throws ServiceException{
		return "erWeiMaYanZhengSuccess1";
	}
	
	public String getImg64() {
		return img64;
	}

	public void setImg64(String img64) {
		this.img64 = img64;
	}
}
