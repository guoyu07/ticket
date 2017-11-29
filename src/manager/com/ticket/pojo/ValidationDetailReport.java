package com.ticket.pojo;


/**
 * 验证明细报表实体
 * @author tuyou
 */
public class ValidationDetailReport {
	
	/**
	 * 验证时间
	 */
	private String time;
	
	/**
	 * 服务码
	 */
	private String code;
	
	/**
	 * 大厅名称
	 */
	private String hall;
	
	/**
	 * 航班号
	 */
	private String flightNo;
	
	/**
	 * 航班时间
	 */
	private String flightTime;
	
	/**
	 * 乘机人
	 */
	private String flightPerson;
	
	/**
	 * 验证人员
	 */
	private String validPerson;
	
	/**
	 * 预约方式
	 */
	private String appointMethod;
	
	/**
	 * 套餐名称
	 */
	private String packageName;
	
	/**
	 * 单价
	 */
	private double price;
	
	public ValidationDetailReport(){}

	public ValidationDetailReport(String time, String code, String hall,
			String flightNo, String flightTime, String flightPerson,
			String validPerson, String appointMethod, String packageName,
			double price) {
		super();
		this.time = time;
		this.code = code;
		this.hall = hall;
		this.flightNo = flightNo;
		this.flightTime = flightTime;
		this.flightPerson = flightPerson;
		this.validPerson = validPerson;
		this.appointMethod = appointMethod;
		this.packageName = packageName;
		this.price = price;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getHall() {
		return hall;
	}

	public void setHall(String hall) {
		this.hall = hall;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getFlightTime() {
		return flightTime;
	}

	public void setFlightTime(String flightTime) {
		this.flightTime = flightTime;
	}

	public String getFlightPerson() {
		return flightPerson;
	}

	public void setFlightPerson(String flightPerson) {
		this.flightPerson = flightPerson;
	}

	public String getValidPerson() {
		return validPerson;
	}

	public void setValidPerson(String validPerson) {
		this.validPerson = validPerson;
	}

	public String getAppointMethod() {
		return appointMethod;
	}

	public void setAppointMethod(String appointMethod) {
		this.appointMethod = appointMethod;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
}
