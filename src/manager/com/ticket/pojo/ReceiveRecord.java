package com.ticket.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 * 领取记录
 * @ClassName: ReceiveRecord   
 * @Description: 领取记录表
 * @author HiSay  
 * @date  2015-11-23 16:26:07
 *
 */
@Entity
@Table(name="ticket_ReceiveRecord",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class ReceiveRecord implements Serializable {

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
	 * 物品id
	 */
	@Column
	private String lostGoods_id = null;

	/**
	 * 领取人姓名
	 */
	@Column
	private String personName = null;

	/**
	 * 领取时间
	 */
	@Column
	private Date receiveTime = null;

	/**
	 * 证件类型
	 */
	@Column
	private String certificateType = null;

	/**
	 * 证件号码
	 */
	@Column
	private String receiveCertificateNumber = null;

	/**
	 * 领取人电话
	 */
	@Column
	private String phone = null;

	/**
	 * 领取方式
	 */
	@Column
	private String receiveWay = null;

	/**
	 *出库人
	 */
	@Column
	private String writeOffPerson = null;

	/**
	 * 发放人
	 */
	@Column
	private String putOutPerson = null;

	/**
	 * 备注
	 */
	@Column
	private String remark = null;
	/**
	 * 出库时间
	 */
	@Column
	private String writeOffTime = null;
	/**
	 * 发放时间
	 */
	@Column
	private String putOutTime = null;

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
	
	public String getLostGoods_id() {
		return lostGoods_id;
	}
	public void setLostGoods_id(String lostGoods_id) {
		this.lostGoods_id = lostGoods_id;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public Date getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}
	public String getCertificateType() {
		return certificateType;
	}
	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}
	public String getReceiveCertificateNumber() {
		return receiveCertificateNumber;
	}
	public void setReceiveCertificateNumber(String receiveCertificateNumber) {
		this.receiveCertificateNumber = receiveCertificateNumber;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getReceiveWay() {
		return receiveWay;
	}
	public void setReceiveWay(String receiveWay) {
		this.receiveWay = receiveWay;
	}
	public String getWriteOffPerson() {
		return writeOffPerson;
	}
	public void setWriteOffPerson(String writeOffPerson) {
		this.writeOffPerson = writeOffPerson;
	}
	public String getPutOutPerson() {
		return putOutPerson;
	}
	public void setPutOutPerson(String putOutPerson) {
		this.putOutPerson = putOutPerson;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getWriteOffTime() {
		return writeOffTime;
	}
	public void setWriteOffTime(String writeOffTime) {
		this.writeOffTime = writeOffTime;
	}
	public String getPutOutTime() {
		return putOutTime;
	}
	public void setPutOutTime(String putOutTime) {
		this.putOutTime = putOutTime;
	}
}
