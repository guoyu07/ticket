package com.ticket.pojo;


/**
 * 便捷登机实时数据辅助类
 * @author xw
 *
 */
public class BjdjRealTimeData {
	
	private String date;
	private String startTime;//时间段1
	private String endTime;//时间段2
	private Integer online;//在线预约数
	private Integer online_;//在线预约数(进厅)
	private Integer offline;//线下预约数
	private Integer offline_;//线下预约数（进厅）
	private Integer validationLength;//验证数量
	private Integer validationLength_;//验证数量(进厅)
	private Integer dispatchListLength;//核销数量
	private Integer dispatchListLength_;//核销数量(进厅)
	private Integer onlineUnactiveLength;//线上取消预约数量
	private Integer onlineUnactiveLength_;//线上取消预约数量（进厅）
	private Integer offlineUnactivelength;//线下取消预约数量
	private Integer offlineUnactivelength_;//线下取消预约数量（进厅）
	private Integer realSeat;//实时座位数
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getOnline() {
		return online;
	}
	public void setOnline(Integer online) {
		this.online = online;
	}
	public Integer getOffline() {
		return offline;
	}
	public void setOffline(Integer offline) {
		this.offline = offline;
	}
	public Integer getValidationLength() {
		return validationLength;
	}
	public void setValidationLength(Integer validationLength) {
		this.validationLength = validationLength;
	}
	public Integer getDispatchListLength() {
		return dispatchListLength;
	}
	public void setDispatchListLength(Integer dispatchListLength) {
		this.dispatchListLength = dispatchListLength;
	}
	public Integer getRealSeat() {
		return realSeat;
	}
	public void setRealSeat(Integer realSeat) {
		this.realSeat = realSeat;
	}
	public Integer getOnlineUnactiveLength() {
		return onlineUnactiveLength;
	}
	public void setOnlineUnactiveLength(Integer onlineUnactiveLength) {
		this.onlineUnactiveLength = onlineUnactiveLength;
	}
	public Integer getOfflineUnactivelength() {
		return offlineUnactivelength;
	}
	public void setOfflineUnactivelength(Integer offlineUnactivelength) {
		this.offlineUnactivelength = offlineUnactivelength;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getValidationLength_() {
		return validationLength_;
	}
	public void setValidationLength_(Integer validationLength_) {
		this.validationLength_ = validationLength_;
	}
	public Integer getOnline_() {
		return online_;
	}
	public void setOnline_(Integer online_) {
		this.online_ = online_;
	}
	public Integer getOffline_() {
		return offline_;
	}
	public void setOffline_(Integer offline_) {
		this.offline_ = offline_;
	}
	public Integer getOnlineUnactiveLength_() {
		return onlineUnactiveLength_;
	}
	public void setOnlineUnactiveLength_(Integer onlineUnactiveLength_) {
		this.onlineUnactiveLength_ = onlineUnactiveLength_;
	}
	public Integer getOfflineUnactivelength_() {
		return offlineUnactivelength_;
	}
	public void setOfflineUnactivelength_(Integer offlineUnactivelength_) {
		this.offlineUnactivelength_ = offlineUnactivelength_;
	}
	public Integer getDispatchListLength_() {
		return dispatchListLength_;
	}
	public void setDispatchListLength_(Integer dispatchListLength_) {
		this.dispatchListLength_ = dispatchListLength_;
	}
	
}
