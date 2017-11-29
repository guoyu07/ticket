package com.ticket.pojo;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 * 便捷登机跳转页面
 * @ClassName: BjdjPage   
 * @Description: 便捷登机跳转页面
 * @author HiSay  
 * @date  2016-08-08 16:09:07
 *
 */
@Entity
@Table(name="ticket_BjdjPage",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class BjdjPage implements Serializable {

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
	 * 页面名称
	 */
	@Column
	private String name;

	/**
	 * 页面跳转链接
	 */
	@Column
	private String url;
	
	/**
	 * 该页面上的服务电话号码
	 */
	@Column
	private String servicePhone;
	
	/**
	 * 该页面上导航的点位的别名
	 */
	@Column
	private String infoPositionAlias;
	
	/**
	 * 服务流程
	 */
	@ManyToOne
	private News serviceFlow;
	
	/**
	 * 服务简介
	 */
	@ManyToOne
	private News serviceProfile;
	
	/**
	 * 使用条款
	 */
	@ManyToOne
	private News useSerms;
	
	/**
	 * 支付成功
	 */
	@ManyToOne
	private BjdjPageTemplate paySuccess;
	
	/**
	 * 首页广告轮播
	 */
	@ManyToOne
	private AdvertType advertType;
	
	/**
	 * 短信模板
	 */
	@ManyToOne
	private SystemDictionary smsTemplate;
	
	/**
	 * 激活成功
	 */
	@ManyToOne
	private BjdjPageTemplate activeSuc;

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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getServicePhone() {
		return servicePhone;
	}
	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}
	public String getInfoPositionAlias() {
		return infoPositionAlias;
	}
	public void setInfoPositionAlias(String infoPositionAlias) {
		this.infoPositionAlias = infoPositionAlias;
	}
	public News getServiceFlow() {
		return serviceFlow;
	}
	public void setServiceFlow(News serviceFlow) {
		this.serviceFlow = serviceFlow;
	}
	public News getServiceProfile() {
		return serviceProfile;
	}
	public void setServiceProfile(News serviceProfile) {
		this.serviceProfile = serviceProfile;
	}
	public News getUseSerms() {
		return useSerms;
	}
	public void setUseSerms(News useSerms) {
		this.useSerms = useSerms;
	}
	public AdvertType getAdvertType() {
		return advertType;
	}
	public void setAdvertType(AdvertType advertType) {
		this.advertType = advertType;
	}
	public SystemDictionary getSmsTemplate() {
		return smsTemplate;
	}
	public void setSmsTemplate(SystemDictionary smsTemplate) {
		this.smsTemplate = smsTemplate;
	}
	public BjdjPageTemplate getPaySuccess() {
		return paySuccess;
	}
	public void setPaySuccess(BjdjPageTemplate paySuccess) {
		this.paySuccess = paySuccess;
	}
	public BjdjPageTemplate getActiveSuc() {
		return activeSuc;
	}
	public void setActiveSuc(BjdjPageTemplate activeSuc) {
		this.activeSuc = activeSuc;
	}
}
