package com.ticket.bo;

public class AjaxData {

	public String status;
	public Object info;
	
	protected AjaxData(String status, Object info) {
		super();
		this.status = status;
		this.info = info;
	}
	
	public static AjaxData responseSuccess(Object info){
		
		return new AjaxData("y", info);
	}
	
	public static AjaxData responseError(Object info){
		
		return new AjaxData("n", info);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getInfo() {
		return info;
	}

	public void setInfo(Object info) {
		this.info = info;
	}
}
