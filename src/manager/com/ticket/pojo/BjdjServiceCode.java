package com.ticket.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import com.ticket.enumer.BjdjOrderType;
/**
 * 服务码表
 * @ClassName: BjdjServiceCode   
 * @Description: 服务码表
 * @author tuyou  
 * @date  2015-10-23 15:16:42
 */
@Entity
@Table(name="ticket_BjdjServiceCode",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class BjdjServiceCode implements Serializable {

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
	 * 服务码
	 */
	@Column(length=255)
	private String code;
	
	/**
	 * 订单id
	 */
	@ManyToOne
	private BjdjOrder order;
	
	/**
	 * 是否绑定
	 */
	@Column
	private Boolean isBind = false;
	
	/**
	 * 服务码所属人
	 */
	@ManyToOne
	private Member member;
	
	/**
	 * 服务码日志表
	 */
	@OneToMany(mappedBy="serviceCode", cascade={CascadeType.ALL})
	private List<BjdjServiceCodeOperation> serviceCodeLog;
	
//	/**
//	 * 预约
//	 */
//	@OneToOne(mappedBy="serviceCode", cascade={CascadeType.ALL})
//	private BjdjAppointment appointment;
	
//	/**
//	 * 验证
//	 */
//	@OneToOne(mappedBy="serviceCode", cascade={CascadeType.ALL})
//	private BjdjValidation validation;

	/**
	 * 服务码类型ID（字典）
	 */
	@ManyToOne
	private SystemDictionary type;
	
	/**
	 * 套餐类型
	 */
	@ManyToOne
	private BjdjServicePackage packageType;
	
	/**
	 * 服务码状态ID（字典）
	 */
	@ManyToOne
	private SystemDictionary state;
	/**
	 * 冻结之前的状态 
	 */
	@ManyToOne
	private SystemDictionary beforeFroze;
	/**
	 * 过期时间
	 */
	private Date expireTime;
	
	/**
	 * 二维码地址
	 */
	private String twoDimensionCodePath;

	/**
	 * 订单类型
	 */
	@Enumerated(EnumType.STRING)
	private BjdjOrderType orderType;
	
	/**
	 * 是否冻结
	 */
	private boolean frozed;
	
	/**
	 * 乐观锁，避免多并发造成的问题
	 */
	@Version
	private int version;
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public BjdjServiceCode() {
		super();
	}
	
	public BjdjServiceCode(String id) {
		super();
		this.id = id;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public BjdjOrder getOrder() {
		return order;
	}
	public void setOrder(BjdjOrder order) {
		this.order = order;
	}
	public String getTwoDimensionCodePath() {
		return twoDimensionCodePath;
	}
	public void setTwoDimensionCodePath(String twoDimensionCodePath) {
		this.twoDimensionCodePath = twoDimensionCodePath;
	}
	public List<BjdjServiceCodeOperation> getServiceCodeLog() {
		return serviceCodeLog;
	}
	public void setServiceCodeLog(List<BjdjServiceCodeOperation> serviceCodeLog) {
		this.serviceCodeLog = serviceCodeLog;
	}
	public SystemDictionary getType() {
		return type;
	}
	public void setType(SystemDictionary type) {
		this.type = type;
	}
	public SystemDictionary getState() {
		return state;
	}
	public void setState(SystemDictionary state) {
		this.state = state;
	}

//	public BjdjAppointment getAppointment() {
//		return appointment;
//	}
//
//	public void setAppointment(BjdjAppointment appointment) {
//		this.appointment = appointment;
//	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public BjdjOrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(BjdjOrderType orderType) {
		this.orderType = orderType;
	}

	public Boolean getIsBind() {
		return isBind;
	}

	public void setIsBind(Boolean isBind) {
		this.isBind = isBind;
	}

	public boolean isFrozed() {
		return frozed;
	}

	public void setFrozed(boolean frozed) {
		this.frozed = frozed;
	}

	public SystemDictionary getBeforeFroze() {
		return beforeFroze;
	}

	public void setBeforeFroze(SystemDictionary beforeFroze) {
		this.beforeFroze = beforeFroze;
	}

//	public BjdjValidation getValidation() {
//		return validation;
//	}
//
//	public void setValidation(BjdjValidation validation) {
//		this.validation = validation;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BjdjServiceCode other = (BjdjServiceCode) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public BjdjServicePackage getPackageType() {
		return packageType;
	}

	public void setPackageType(BjdjServicePackage packageType) {
		this.packageType = packageType;
	}


}
