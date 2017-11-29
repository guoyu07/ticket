package com.ticket.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OrderBy;

/**
 * 便捷登机分单表
 * @ClassName: BjdjDispatch   
 * @Description: jdjDispatch
 * @author tuyou  
 * @date  2015-11-23 22:53:55
 */
@Entity
@Table(name="ticket_BjdjDispatch",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class BjdjDispatch implements Serializable {

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
	 * 基于的验证
	 */
	@OneToOne
	private BjdjValidation validation;
	
	/**
	 * 分单项
	 */
	@OneToMany(mappedBy="dispatch")
	@OrderBy(clause="sequence")
	private List<BjdjDispatchList> dispatchList;
	
	/**
	 * 派单时间
	 */
	private Date time;

	/**
	 * 登机口
	 */
	@Column(length=255)
	private String boardingGate;
	
	/**
	 * 机场员工ID
	 */
	@ManyToOne
	private AirportEmployee employee;

	/**
	 * 是否结束
	 */
	private boolean ended;
	
	@Column
	private Date dispatchTime;

	public BjdjDispatch() {
		super();
	}
	public BjdjDispatch(String id) {
		super();
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public CommonEntity getStatus() {
		return status;
	}
	public void setStatus(CommonEntity status) {
		this.status = status;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getBoardingGate() {
		return boardingGate;
	}
	public void setBoardingGate(String boardingGate) {
		this.boardingGate = boardingGate;
	}
	public boolean getEnded() {
		return ended;
	}
	public void setEnded(boolean ended) {
		this.ended = ended;
	}
	public BjdjValidation getValidation() {
		return validation;
	}
	public void setValidation(BjdjValidation validation) {
		this.validation = validation;
	}
	public List<BjdjDispatchList> getDispatchList() {
		return dispatchList;
	}
	public void setDispatchList(List<BjdjDispatchList> dispatchList) {
		this.dispatchList = dispatchList;
	}
	public AirportEmployee getEmployee() {
		return employee;
	}
	public void setEmployee(AirportEmployee employee) {
		this.employee = employee;
	}
	public Date getDispatchTime() {
		return dispatchTime;
	}
	public void setDispatchTime(Date dispatchTime) {
		this.dispatchTime = dispatchTime;
	}
}
