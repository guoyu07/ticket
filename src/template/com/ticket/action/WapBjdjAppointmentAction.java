package com.ticket.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjAppointment;
import com.ticket.pojo.BjdjServiceCode;
import com.ticket.service.IBjdjServiceCodeService;
import com.ticket.util.GeneralUtil;

import net.sf.json.JSONObject;

public class WapBjdjAppointmentAction extends BjdjAppointmentAction {

	private static final long serialVersionUID = 1L;
	@Resource
	private IBjdjServiceCodeService codeService;

	private String code;

	public IBjdjServiceCodeService getCodeService() {
		return codeService;
	}

	public void setCodeService(IBjdjServiceCodeService codeService) {
		this.codeService = codeService;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String loginPage() {

		return "loginPage";
	}
	/**
	 * 管理便捷登机预约数据
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0]
				.getMethodName());
		this.setPageModule(appointmentService.queryEntityByAdmin(versionFlag,
				ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);

		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		List<BjdjAppointment> list = new ArrayList<BjdjAppointment>();
		List<BjdjAppointment> lists = appointmentService
				.queryAll(BjdjAppointment.class);
		if(GeneralUtil.isNotNull(lists)){
			for (BjdjAppointment appointment : lists) {
				Date date2 = appointment.getUseTime();
				Calendar calendar2 = Calendar.getInstance();
				if(GeneralUtil.isNotNull(date2)){
					calendar2.setTime(date2);
					if (calendar.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
							&& calendar.get(Calendar.MONTH) == calendar2
							.get(Calendar.MONTH)
							&& calendar.get(Calendar.DAY_OF_MONTH) == calendar2
							.get(Calendar.DAY_OF_MONTH)) {
						list.add(appointment);
					}
				}
				}
		}
		//今天的数据
		ActionContext.getContext().put("list", list);

		long toDay = calendar.getTimeInMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = sdf.parse(sdf.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<BjdjAppointment> list2 = new ArrayList<BjdjAppointment>();
		for (BjdjAppointment appointment : lists) {
			Date date2 = appointment.getTime();
			try {
				date2 = sdf.parse(sdf.format(date2));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Calendar calendar2 = Calendar.getInstance();
			calendar2.setTime(date2);
			long time = calendar2.getTimeInMillis();
			long between_days = (time - toDay) / (1000 * 3600 * 24);
			Integer between_day = Integer
					.parseInt(String.valueOf(between_days));
			if (between_day <= 3 && between_day >= 0) {
				list2.add(appointment);
			}
		}
		//最近三天的数据
		ActionContext.getContext().put("list2", list2);
		return "appointmentSituation";
	}
	/**
	 * 便捷登机预约首页
	 * @return
	 */
	public String Index() {
		return "index";
	}
	/**
	 * 根据服务码查看便捷登机预约
	 * @return
	 */
	public String findById() {
		BjdjServiceCode serviceCode = codeService.getByCode(code);
		if (serviceCode == null) {
			data = AjaxData.responseError("您输入的服务码不存在");
		} else {
			BjdjAppointment appointment = appointmentService.getByServiceCode(serviceCode);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String time = sdf.format(appointment.getUseTime());
			JSONObject object = new JSONObject();
			object.put("way", appointment.getWay());
			object.put("usetime", time);
			object.put("name", appointment.getName());
			object.put("code", appointment.getServiceCode().getCode());
			object.put("mobile", appointment.getMobile());
			object.put("flightNo", appointment.getFlightNo());
			object.put("description", appointment.getDescription());
			data = AjaxData.responseSuccess(object);
		}
		return JSON;
	}
}
