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
 * 便捷登机服务套餐
 * @ClassName: BjdjServicePackage   
 * @Description: 便捷登机服务套餐
 * @author HiSay  
 * @date  2016-06-30 17:48:33
 *
 */
@Entity
@Table(name="ticket_BjdjServicePackage",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class BjdjServicePackage implements Serializable {

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
	 * 前端显示页面
	 */
	@ManyToOne
	private BjdjPage bjdjPage;
	
	/**
	 * 对应的服务厅
	 */
	@ManyToOne
	private BjdjHall bjdjHall;
	
	/**
	 * 服务套餐名称
	 */
	@Column(length=255)
	private String name;

	/**
	 * 服务套餐价格
	 */
	@Column(length=255)
	private double price;

	/**
	 * 服务项描述
	 */
	@Column(length=255)
	private String description;


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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BjdjHall getBjdjHall() {
		return bjdjHall;
	}
	public void setBjdjHall(BjdjHall bjdjHall) {
		this.bjdjHall = bjdjHall;
	}
	public BjdjPage getBjdjPage() {
		return bjdjPage;
	}
	public void setBjdjPage(BjdjPage bjdjPage) {
		this.bjdjPage = bjdjPage;
	}
}
