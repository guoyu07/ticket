package com.ticket.pojo;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 * 便捷登机服务套餐关联项
 * @ClassName: BjdjServicePackageItem   
 * @Description: 便捷登机服务套餐关联项
 * @author HiSay  
 * @date  2016-06-30 17:49:00
 *
 */
@Entity
@Table(name="ticket_BjdjServicePackageItem",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class BjdjServicePackageItem implements Serializable {

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
	 * 服务套餐名称
	 */
	@ManyToOne
	private BjdjServicePackage packageName;

	/**
	 * 服务项
	 */
	@ManyToOne
	private BjdjServiceItem item;


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
	public BjdjServiceItem getItem() {
		return item;
	}
	public void setItem(BjdjServiceItem item) {
		this.item = item;
	}
	public BjdjServicePackage getPackageName() {
		return packageName;
	}
	public void setPackageName(BjdjServicePackage packageName) {
		this.packageName = packageName;
	}
}
