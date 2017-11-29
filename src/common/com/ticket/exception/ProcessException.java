package com.ticket.exception;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.action.BaseAction;
import com.ticket.bo.AjaxData;

public class ProcessException extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	@Override
	public String execute() {
		
		data = AjaxData.responseError(ActionContext.getContext().getValueStack().findValue("exception").toString());
		return null;
	}
}
