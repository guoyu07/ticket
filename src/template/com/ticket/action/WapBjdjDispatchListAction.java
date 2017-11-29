package com.ticket.action;

import com.ticket.exception.ServiceException;

public class WapBjdjDispatchListAction extends BjdjDispatchListAction {

	private static final long serialVersionUID = 1L;
	/**
	 * 管理便捷登机分单流程表
	 */
	public String manage() throws ServiceException {
		super.manage();
		return "manageWapBjdjDispatchList";
	}
	/**
	 * 管理电瓶车分单流程表
	 */
//	public String manageElectromobile() throws ServiceException {
//		super.manageElectromobile();
//		return "manageWapElemobileDispatchList";
//	}
	/**
	 * 登录页面
	 * @return
	 */
	public String loginPage() {

		return "loginPage";
	}
}
