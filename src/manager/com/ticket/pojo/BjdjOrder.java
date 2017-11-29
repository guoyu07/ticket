package com.ticket.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import com.ticket.enumer.BjdjOrderType;
import com.ticket.enumer.PayMethod;
/**
 * 便捷登机订单表
 * @ClassName: BjdjOrder   
 * @Description: 便捷登机订单表
 * @author HiSay  
 * @date  2015-10-23 15:22:31
 *
 */
@Entity
@Table(name="ticket_BjdjOrder",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class BjdjOrder implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	@Id
	private String id = UUID.randomUUID().toString().replaceAll("-", "");
	/**
	 * 实体状态
	 */
	@Embedded
	private CommonEntity status = new CommonEntity();
	
	/**
	 * 订单号
	 */
	@Column(length=255)
	private String number;

	/**
	 * 外部订单号（直达号订单）
	 */
	@Column(length=255)
	private String numberOut;
	
	/**
	 * 所有的服务码
	 */
	@OneToMany(mappedBy="order", cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	private List<BjdjServiceCode> serviceCodes = new ArrayList<BjdjServiceCode>();
	
	/**
	 * 支付页面
	 */
	private String payUrl;
	
	/**
	 * 用户ID
	 */
	@ManyToOne
	private Member member;
	
	/**
	 * 订单状态
	 */
	@ManyToOne
	private SystemDictionary state;
	
	/**
	 * 订单类型
	 */
	@Enumerated(EnumType.STRING)
	private BjdjOrderType type;
	
	/**
	 * 套餐类型
	 */
	@ManyToOne
	private BjdjServicePackage packageType;
	
	/**
	 * 单价	
	 */
	private Double price;
	
	/**
	 * 数量
	 */
	private Integer count;
	
	/**
	 * 电话号码
	 */
	private String mobile;
	
	/**
	 * 商品名称
	 */
	private String name;
	
	/**
	 * 支付方式
	 */
	@Enumerated(EnumType.STRING)
	private PayMethod payMethod;
	
	/**
	 * 支付时间
	 */
	private Date payTime;
	
	/**
	 * 实付金额
	 */
	private Double realPay;
	
	/**
	 * 优惠金额
	 */
	private Double discountAmount;
	
	/**
	 * 消耗积分
	 */
	private Double consumptionIntegral;
	
	/**
	 * 超时时间
	 */
	private Integer timeout;
	
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

	public BjdjOrder() {
		super();
	}
	
	public BjdjOrder(String id) {
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
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public BjdjOrderType getType() {
		return type;
	}
	public void setType(BjdjOrderType type) {
		this.type = type;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public Double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}
	public Double getConsumptionIntegral() {
		return consumptionIntegral;
	}
	public void setConsumptionIntegral(Double consumptionIntegral) {
		this.consumptionIntegral = consumptionIntegral;
	}
	public Double getRealPay() {
		return realPay;
	}
	public void setRealPay(Double realPay) {
		this.realPay = realPay;
	}
	public String getNumberOut() {
		return numberOut;
	}
	public void setNumberOut(String numberOut) {
		this.numberOut = numberOut;
	}
	public String getPayUrl() {
		return payUrl;
	}
	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
	}
	public List<BjdjServiceCode> getServiceCodes() {
		return serviceCodes;
	}
	public void setServiceCodes(List<BjdjServiceCode> serviceCodes) {
		this.serviceCodes = serviceCodes;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public SystemDictionary getState() {
		return state;
	}

	public void setState(SystemDictionary state) {
		this.state = state;
	}

	public PayMethod getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(PayMethod payMethod) {
		this.payMethod = payMethod;
	}

	public BjdjServicePackage getPackageType() {
		return packageType;
	}

	public void setPackageType(BjdjServicePackage packageType) {
		this.packageType = packageType;
	}
}
