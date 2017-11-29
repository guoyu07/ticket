package com.ticket.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 * 第三方推送过来的移动设备移动情况
 * @author tuyou  
 * @date  2016-08-09 10:49:51
 */
@Entity
@Table(name="ticket_WifiPush",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class WifiPush implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	/**
	 * 实体状态
	 */
	@Embedded
	private CommonEntity status = new CommonEntity();
	
	/**
	 * 商家id
	 */
	@Column(length=64)
	private String mid;
	
	/**
	 * 设备id
	 */
	@Column(length=64)
	private String rid;
	
	/**
	 * 手机mac地址
	 */
	@Column(length=32)
	private String mac;
	
	/**
	 * 时间戳
	 */
	@Column(length=16)
	private String ts;
	
	/**
	 * 信号强度0~65535， 一般是0-100
	 */
	@Column(length=8)
	private String sig;
	
	/**
	 * 手机号码
	 */
	@Column(length=18)
	private String tel;


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public CommonEntity getStatus() {
		return status;
	}
	public void setStatus(CommonEntity status) {
		this.status = status;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getTs() {
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}
	public String getSig() {
		return sig;
	}
	public void setSig(String sig) {
		this.sig = sig;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
}
