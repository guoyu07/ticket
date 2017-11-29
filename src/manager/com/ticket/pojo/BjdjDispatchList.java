package com.ticket.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ticket.enumer.BjdjCheckWay;
import com.ticket.enumer.BjdjDispatchListState;

/**
 * 分单流程表
 * @ClassName: BjdjDispatchList   
 * @Description: bjdjDispatchList
 * @author tuyou  
 * @date  2015-11-23 22:55:31
 */
@Entity
@Table(name="ticket_BjdjDispatchItem",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class BjdjDispatchList implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	@Id
	private String id = UUID.randomUUID().toString();
	
	/**
	 * 实体状态
	 */
	@Embedded
	private CommonEntity status = new CommonEntity();
	
	/**
	 * 分单ID
	 */
	@ManyToOne
	private BjdjDispatch dispatch;

	/**
	 * 服务员工ID
	 */
	@ManyToOne
	private AirportEmployee employee;
	
	/**
	 * 分单名称
	 */
	@ManyToOne
	private BjdjServiceItem info;
	
	/**
	 * 顺序
	 */
	private Integer sequence;

	/**
	 * 状态
	 */
	@Enumerated(EnumType.STRING)
	private BjdjDispatchListState state;

	/**
	 * 核销时间
	 */
	private Date time;

	/**
	 * 问题反馈
	 */
	@Column(length=255)
	private String feedback;

	/**
	 * 核销方式
	 */
	@Enumerated(EnumType.STRING)
	private BjdjCheckWay way;


	public BjdjDispatchList() {
		super();
	}
	public BjdjDispatchList(String id) {
		super();
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public BjdjCheckWay getWay() {
		return way;
	}
	public void setWay(BjdjCheckWay way) {
		this.way = way;
	}
	public BjdjDispatch getDispatch() {
		return dispatch;
	}
	public void setDispatch(BjdjDispatch dispatch) {
		this.dispatch = dispatch;
	}
	public AirportEmployee getEmployee() {
		return employee;
	}
	public void setEmployee(AirportEmployee employee) {
		this.employee = employee;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public CommonEntity getStatus() {
		return status;
	}
	public void setStatus(CommonEntity status) {
		this.status = status;
	}
	public BjdjDispatchListState getState() {
		return state;
	}
	public void setState(BjdjDispatchListState state) {
		this.state = state;
	}
	public BjdjServiceItem getInfo() {
		return info;
	}
	public void setInfo(BjdjServiceItem info) {
		this.info = info;
	}
}
