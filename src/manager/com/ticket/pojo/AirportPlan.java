package com.ticket.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ticket_AirportPlan")
public class AirportPlan implements Serializable{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */ 
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键(对应excel 的数据表序号)
	 */
	@Id
	private String id = UUID.randomUUID().toString();
	
	/**
	 * 实体状态
	 */
	@Embedded
	private CommonEntity status = new CommonEntity();
	
	/**
	 * 批号
	 */
	private String batch_number;
	
	/**
	 * 航班号
	 */
	private String flt = null;

	/**
	 * 机型
	 */
	private String airportType;
	
	/**
	 * 任务
	 */
	private String task;
	
	/**
	 * 周期
	 */
	private String cycle;
	
	/**
	 * 起飞(数据库中是四字码需要转换成三字码在查询目的地)
	 */
	private String dept;
	
	/**
	 * 计划起飞时间
	 */
	private String std = null;
	
	/**
	 * 预计到达时间
	 */
	private String eta = null;
	
	/**
	 * 到达(数据库中是四字码需要转换成三字码在查询目的地)
	 */
	private String arrive;
	
	/**
	 * 起始日期
	 */
	private Date start_date;
	
	/**
	 * 终止日期
	 */
	private Date end_date;
	
	/**
	 * 相关部门
	 */
	private String relevant_departments;
	
	/**
	 * 入境点
	 */
	private String intoPoint;
	
	/**
	 * 入时
	 */
	private String intoTime;
	
	/**
	 * 进境高度
	 */
	private String intoHight;
	
	/**
	 * 出境点
	 */
	private String outPoint;
	
	/**
	 * 出点时间
	 */
	private String outTime;
	
	/**
	 * 出境高度
	 */
	private String outHeight;
	
	/**
	 * 总调批文号
	 */
	private String endBatchNum;
	
	/**
	 * 备注
	 */
	private String remark;
	
	
	/**
	 * 航路
	 */
	private String air_route;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getBatch_number() {
		return batch_number;
	}


	public void setBatch_number(String batch_number) {
		this.batch_number = batch_number;
	}


	public String getFlt() {
		return flt;
	}


	public void setFlt(String flt) {
		this.flt = flt;
	}


	public String getAirportType() {
		return airportType;
	}


	public void setAirportType(String airportType) {
		this.airportType = airportType;
	}


	public String getTask() {
		return task;
	}


	public void setTask(String task) {
		this.task = task;
	}


	public String getCycle() {
		return cycle;
	}


	public void setCycle(String cycle) {
		this.cycle = cycle;
	}


	public String getDept() {
		return dept;
	}


	public void setDept(String dept) {
		this.dept = dept;
	}


	public String getStd() {
		return std;
	}


	public void setStd(String std) {
		this.std = std;
	}


	public String getEta() {
		return eta;
	}


	public void setEta(String eta) {
		this.eta = eta;
	}


	public String getArrive() {
		return arrive;
	}


	public void setArrive(String arrive) {
		this.arrive = arrive;
	}


	public Date getStart_date() {
		return start_date;
	}


	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}


	public Date getEnd_date() {
		return end_date;
	}


	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}


	public String getRelevant_departments() {
		return relevant_departments;
	}


	public void setRelevant_departments(String relevant_departments) {
		this.relevant_departments = relevant_departments;
	}


	public String getIntoPoint() {
		return intoPoint;
	}


	public void setIntoPoint(String intoPoint) {
		this.intoPoint = intoPoint;
	}


	public String getIntoTime() {
		return intoTime;
	}


	public void setIntoTime(String intoTime) {
		this.intoTime = intoTime;
	}


	public String getIntoHight() {
		return intoHight;
	}


	public void setIntoHight(String intoHight) {
		this.intoHight = intoHight;
	}


	public String getOutPoint() {
		return outPoint;
	}


	public void setOutPoint(String outPoint) {
		this.outPoint = outPoint;
	}


	public String getOutTime() {
		return outTime;
	}


	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}


	public String getOutHeight() {
		return outHeight;
	}


	public void setOutHeight(String outHeight) {
		this.outHeight = outHeight;
	}


	public String getEndBatchNum() {
		return endBatchNum;
	}


	public void setEndBatchNum(String endBatchNum) {
		this.endBatchNum = endBatchNum;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getAir_route() {
		return air_route;
	}


	public void setAir_route(String air_route) {
		this.air_route = air_route;
	}

	public CommonEntity getStatus() {
		return status;
	}


	public void setStatus(CommonEntity status) {
		this.status = status;
	}
}
