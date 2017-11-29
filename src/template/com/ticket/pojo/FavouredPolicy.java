package com.ticket.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.UUID;
/**
 * 优惠政策
 * @ClassName: FavouredPolicy   
 * @Description: 优惠政策
 * @author HiSay  
 * @date  2015-11-14 14:34:06
 *
 */
@Entity
@Table(name="ticket_FavouredPolicy",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class FavouredPolicy implements Serializable {

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
	 * 优惠编号
	 */
	@Column
	private String favouredPolicyNo;

	/**
	 * 名称
	 */
	@Column
	private String name;

	/**
	 * 所需充值金额
	 */
	@Column
	private Double rechargeAmount;

	/**
	 * 折扣率
	 */
	@Column
	private Double discountRate;

	/**
	 * 渠道类型
	 */
	@Column
	private String discountWay;

	/**
	 * 描述
	 */
	@Column
	private String descript;
	/**
	 * 优惠等级
	 */
	@Column
	private Integer grade;
	/**
	 * 赠送金额
	 */
	@Column
	private Double giftAmount;
	
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
	
	public String getFavouredPolicyNo() {
		return favouredPolicyNo;
	}
	public void setFavouredPolicyNo(String favouredPolicyNo) {
		this.favouredPolicyNo = favouredPolicyNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getRechargeAmount() {
		return rechargeAmount;
	}
	public void setRechargeAmount(Double rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}
	public Double getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(Double discountRate) {
		this.discountRate = discountRate;
	}
	public String getDiscountWay() {
		return discountWay;
	}
	public void setDiscountWay(String discountWay) {
		this.discountWay = discountWay;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGiftAmount(Double giftAmount) {
		this.giftAmount = giftAmount;
	}
	public Double getGiftAmount() {
		return giftAmount;
	}
}
