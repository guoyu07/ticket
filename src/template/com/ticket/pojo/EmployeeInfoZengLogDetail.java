package com.ticket.pojo;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
/**
 * 员工转移服务码附表
 * @ClassName: EmployeeInfoZengLogDetail   
 * @Description: 员工转移服务码附表
 * @author HiSay  
 * @date  2016-01-18 17:27:18
 *
 */
@Entity
@Table(name="ticket_EmployeeInfoZengLogDetail",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class EmployeeInfoZengLogDetail implements Serializable {

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
	 * 关联的主表id
	 */
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private EmployeeInfoZengLog employeeInfoZengLog;
	/**
	 * 关联的订单详细id
	 */
	@OneToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private OrderInfoDetail orderInfoDetail;
	
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
	public EmployeeInfoZengLog getEmployeeInfoZengLog() {
		return employeeInfoZengLog;
	}
	public void setEmployeeInfoZengLog(EmployeeInfoZengLog employeeInfoZengLog) {
		this.employeeInfoZengLog = employeeInfoZengLog;
	}
	public OrderInfoDetail getOrderInfoDetail() {
		return orderInfoDetail;
	}
	public void setOrderInfoDetail(OrderInfoDetail orderInfoDetail) {
		this.orderInfoDetail = orderInfoDetail;
	}
	
}
