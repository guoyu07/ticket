package com.ticket.pojo;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 * 审核拜访记录
 * @ClassName: AuditVisitRecord   
 * @Description: 对员工的拜访记录进行回访审核
 * @author HiSay  
 * @date  2016-04-29 10:39:00
 *
 */
@Entity
@Table(name="ticket_AuditVisitRecord",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class AuditVisitRecord implements Serializable {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */ 
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
	 * 记录id
	 */
	@Column
	private String record_id = null;

	/**
	 * 审核员工id
	 */
	@Column
	private String employee_id = null;
	
	/**
	 * 电话拜访或者是外出拜访 phone 代表电话拜访 out 代表外出拜访
	 */
	@Column
	private String phoneOrOut = null;

	/**
	 * 备注内容
	 */
	@Column
	private String remark = null;


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
	
	public String getRecord_id() {
		return record_id;
	}
	public void setRecord_id(String record_id) {
		this.record_id = record_id;
	}
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPhoneOrOut() {
		return phoneOrOut;
	}
	public void setPhoneOrOut(String phoneOrOut) {
		this.phoneOrOut = phoneOrOut;
	}
}
