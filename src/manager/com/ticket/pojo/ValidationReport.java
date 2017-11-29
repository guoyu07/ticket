package com.ticket.pojo;


/**
 * 便捷登机验证报表
 * @author tuyou
 */
public class ValidationReport {
	
	/**
	 * 时间区间
	 */
	private String region;
	
	/**
	 * 大厅名称
	 */
	private String hall;
	
	/**
	 * 套餐名称
	 */
	private String packageName;
	
	/**
	 * 预约方式
	 */
	private String appointment;
	
	/**
	 * 验证数量
	 */
	private int count;
	
	/**
	 * 总金额
	 */
	private double amount;
	
	/**
	 * 环比数量
	 */
	private int rollCount;
	
	/**
	 * 同比数量
	 */
	private int sameCount;
	
	public ValidationReport(){}
	
	public ValidationReport(String region, String hall, String packageName,
			String appointment, int count, double amount, int rollCount, int sameCount) {
		super();
		this.region = region;
		this.hall = hall;
		this.packageName = packageName;
		this.appointment = appointment;
		this.count = count;
		this.amount = amount;
		this.rollCount = rollCount;
		this.sameCount = sameCount;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getHall() {
		return hall;
	}

	public void setHall(String hall) {
		this.hall = hall;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getAppointment() {
		return appointment;
	}

	public void setAppointment(String appointment) {
		this.appointment = appointment;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getRollCount() {
		return rollCount;
	}

	public void setRollCount(int rollCount) {
		this.rollCount = rollCount;
	}

	public int getSameCount() {
		return sameCount;
	}

	public void setSameCount(int sameCount) {
		this.sameCount = sameCount;
	}
}
