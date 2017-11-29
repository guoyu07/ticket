package com.ticket.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
/**
 * 会员信息发送记录
 * @ClassName: MemberSendInfo   
 * @Description: 会员信息发送记录表
 * @author tuyou  
 * @date  2016-02-03 20:53:58
 */
@Entity
@Table(name="ticket_MemberSendInfo",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class MemberSendInfo implements Serializable {
	
	/**
	 * 推送方式
	 * @author tuyou
	 */
	public enum PushMethod{
		
		manual("手工推送"),
		flight("航班变更自动推送"),
		app("app推送"),
		wifi("wifi推送");
		
		/**
		 * 枚举字段的文字描述
		 */
		private String text;
		
		private PushMethod(String text){
			
			this.text = text;
		}

		public String getValue() {
			return this.toString();
		}
		
		public String getText(){
			return text;
		}
	}

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
	 * 会员
	 */
	@Column
	private String member_id;
	
	/**
	 * 推送方式
	 */
	private PushMethod method;

	/**
	 * 电话
	 */
	@Column
	private String phone;
	
	/**
	 * 模板id
	 */
	public String massInfo_id;
	
	/**
	 * 标题
	 */
	private String title;

	/**
	 * 信息
	 */
	private String message;
	
	/**
	 * 短信链接
	 */
	@Column
	private String url;
	
	/**
	 * mac地址，不做存储之用，只是用户导出报表时候等用处
	 */
	@Transient
	private String mac;
	
	/**
	 * 航班号
	 */
	private String flightNumber;
	
	/**
	 * 航班日期
	 */
	private Date flightDate;
	
	/**
	 * pc是否已读
	 */
	@Column(length=255)
	private boolean pc;

	/**
	 * android是否已读
	 */
	@Column(length=255)
	private boolean android;

	/**
	 * ios是否已读
	 */
	@Column(length=1024)
	private boolean ios;

	/**
	 * h5是否已读
	 */
	@Column(length=1024)
	private boolean h5;

	public MemberSendInfo(){}
	
	public MemberSendInfo(CommonEntity status, String phone,
			PushMethod method, String title, String message,
			String url, boolean h5) {
		
		super();
		this.status = status;
		this.phone = phone;
		this.method = method;
		this.title = title;
		this.message = message;
		this.url = url;
		this.h5 = h5;
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

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public PushMethod getMethod() {
		return method;
	}

	public void setMethod(PushMethod method) {
		this.method = method;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMassInfo_id() {
		return massInfo_id;
	}

	public void setMassInfo_id(String massInfo_id) {
		this.massInfo_id = massInfo_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public Date getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(Date flightDate) {
		this.flightDate = flightDate;
	}

	public boolean isPc() {
		return pc;
	}

	public void setPc(boolean pc) {
		this.pc = pc;
	}

	public boolean isAndroid() {
		return android;
	}

	public void setAndroid(boolean android) {
		this.android = android;
	}

	public boolean isIos() {
		return ios;
	}

	public void setIos(boolean ios) {
		this.ios = ios;
	}

	public boolean isH5() {
		return h5;
	}

	public void setH5(boolean h5) {
		this.h5 = h5;
	}
}
