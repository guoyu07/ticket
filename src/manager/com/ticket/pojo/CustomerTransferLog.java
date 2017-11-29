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
 * 客户转让日志
 * @ClassName: CustomerTransferLog   
 * @Description: 员工的客户转让记录
 * @author HiSay  
 * @date  2016-04-27 15:04:28
 *
 */
@Entity
@Table(name="ticket_CustomerTransferLog",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class CustomerTransferLog implements Serializable {

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
	 * 转让前员工
	 */
	@Column
	private String beforeEmployee_id = null;

	/**
	 * 转让后员工
	 */
	@Column
	private String afterEmployee_id = null;

	/**
	 * 操作员ID
	 */
	@Column
	private String operator_id = null;

	/**
	 * 客户ID
	 */
	@Column
	private String customer_id = null;

	/**
	 * 备注
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
	
	public String getBeforeEmployee_id() {
		return beforeEmployee_id;
	}
	public void setBeforeEmployee_id(String beforeEmployee_id) {
		this.beforeEmployee_id = beforeEmployee_id;
	}
	public String getAfterEmployee_id() {
		return afterEmployee_id;
	}
	public void setAfterEmployee_id(String afterEmployee_id) {
		this.afterEmployee_id = afterEmployee_id;
	}
	public String getOperator_id() {
		return operator_id;
	}
	public void setOperator_id(String operator_id) {
		this.operator_id = operator_id;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
