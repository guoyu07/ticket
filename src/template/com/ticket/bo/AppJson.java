package com.ticket.bo;

public class AppJson {

	private String error;
	private Object data;
	private Integer count;
	
	public AppJson(String error, Object data, Integer count) {
		super();
		this.error = error;
		this.data = data;
		this.count = count;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
