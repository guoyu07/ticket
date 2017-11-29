package com.ticket.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
/**
 * 员工信息
 * @ClassName: EmployeeInfo   
 * @Description: 员工信息表
 * @author HiSay  
 * @date  2015-11-03 15:33:02
 *
 */
@Entity
@Table(name="ticket_EmployeeInfo",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class EmployeeInfo extends SystemUser implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 部门
	 */
	@ManyToOne
	private DepartmentInfo department;

	/**
	 * 入职日期
	 */
	@Column
	private String entryDate;

	/**
	 * 身份证
	 */
	@Column
	private String IDCard;

	
	/**
	 * 紧急联系人
	 */
	@Column
	private String emergencyContact;

	/**
	 * 紧急联系电话
	 */
	@Column
	private String emergencyPhone;
	/**
	 * 员工状态
	 * 0 在职
	 * 1 离职
	 */
	@Column
	private Integer state = 0;
	/**
	 * 关联的职位id
	 */
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private Position position;
	/**
	 * 关联绑定的渠道客户id
	 */
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private ChannelCustomerInfo channelCustomerInfo;
	
	
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getIDCard() {
		return IDCard;
	}
	public void setIDCard(String IDCard) {
		this.IDCard = IDCard;
	}
	public String getEmergencyContact() {
		return emergencyContact;
	}
	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}
	public String getEmergencyPhone() {
		return emergencyPhone;
	}
	public void setEmergencyPhone(String emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public ChannelCustomerInfo getChannelCustomerInfo() {
		return channelCustomerInfo;
	}
	public void setChannelCustomerInfo(ChannelCustomerInfo channelCustomerInfo) {
		this.channelCustomerInfo = channelCustomerInfo;
	}
	public DepartmentInfo getDepartment() {
		return department;
	}
	public void setDepartment(DepartmentInfo department) {
		this.department = department;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
}
