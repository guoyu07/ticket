package com.ticket.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ticket.enumer.VpnStatus;

/**
 * vpn断线记录
 * @author apple
 */
@Entity
@Table(name="ticket_VpnBroken")
public class VpnBroken{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	/**
	 * 通知次数限制
	 */
	@Transient
	public static final int notifyNumber = 5;
	
	/**
	 * 断线时间
	 */
	private Date time = new Date();
	
	/**
	 * 处理结果
	 */
	private VpnStatus state = VpnStatus.untreated;
	
	/**
	 * 短信通知次数统计
	 */
	private int smsNotifyCount;
	
	/**
	 * 是否网页弹窗提示
	 */
	private boolean pageNotify;
	
	/**
	 * 处理备注
	 */
	private String remark;
	
	public VpnStatus getState() {
		return state;
	}
	public void setState(VpnStatus state) {
		this.state = state;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getSmsNotifyCount() {
		return smsNotifyCount;
	}
	public void setSmsNotifyCount(int smsNotifyCount) {
		this.smsNotifyCount = smsNotifyCount;
	}
	public boolean isPageNotify() {
		return pageNotify;
	}
	public void setPageNotify(boolean pageNotify) {
		this.pageNotify = pageNotify;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
}