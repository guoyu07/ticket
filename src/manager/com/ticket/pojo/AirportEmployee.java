package com.ticket.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
/**
 * 员工信息
 * @ClassName: AirportEmployee   
 * @Description: 员工信息表
 * @author HiSay  
 * @date  2015-11-16 23:00:35
 *
 */
@Entity
@Table(name="ticket_AirportEmployee",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class AirportEmployee extends SystemUser implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 部门id
	 */
//	@ManyToOne(cascade={CascadeType.ALL})
	private String department_id = null;
	
	/**
	 * 部门名称(用于报表显示作用)
	 */
	@Transient
	private String departmentName;

	/**
	 * 工号
	 */
	@Column
	private String employeeId = null;

	/**
	 * 身份证号
	 */
	@Column
	private String IDCard = null;
	
	/**
	 * 所属服务厅
	 */
	@ManyToOne
	private BjdjHall hall = null;

	public AirportEmployee() {
		super();
	}
	
	public AirportEmployee(Date createTime, String departmentName, BjdjHall hall, String name,
			String phone, String loginId, String IDCard) {
		
		this.status.setCreateTime(createTime);
		this.setDepartmentName(departmentName);
		this.setHall(hall);
		this.setName(name);
		this.setPhone(phone);
		this.setLoginId(loginId);
		this.setIDCard(IDCard);
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getIDCard() {
		return IDCard;
	}

	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}

	public BjdjHall getHall() {
		return hall;
	}

	public void setHall(BjdjHall hall) {
		this.hall = hall;
	}

	public String getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
}
