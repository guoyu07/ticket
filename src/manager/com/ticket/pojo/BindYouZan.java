package com.ticket.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import java.util.UUID;
/**
 * 有赞商品绑定机场商家
 * @ClassName: BindYouZan   
 * @Description: 有赞商品绑定机场商家
 * @author HiSay  
 * @date  2017-01-11 10:26:44
 *
 */
@Entity
@Table(name="ticket_BindYouZan",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class BindYouZan implements Serializable {

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
	 * 商家
	 */
	@ManyToOne
	private BusinessInfo businessInfo;

	/**
	 * 有赞商品
	 */
	@ManyToOne
	private YouZanGoodsInfo goodsInfo;


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
	
	public BusinessInfo getBusinessInfo() {
		return businessInfo;
	}
	public void setBusinessInfo(BusinessInfo businessInfo) {
		this.businessInfo = businessInfo;
	}
	public YouZanGoodsInfo getGoodsInfo() {
		return goodsInfo;
	}
	public void setGoodsInfo(YouZanGoodsInfo goodsInfo) {
		this.goodsInfo = goodsInfo;
	}
}
