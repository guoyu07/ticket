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
 * 有赞出售中的商品
 * @ClassName: YouZanGoodsInfo   
 * @Description: 有赞出售中的商品
 * @author HiSay  
 * @date  2017-01-05 16:13:06
 *
 */
@Entity
@Table(name="ticket_YouZanGoodsInfo",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class YouZanGoodsInfo implements Serializable {

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
	 * 商品ID
	 */
	@Column
	private String num_iid;

	/**
	 * 商品名称
	 */
	@Column
	private String name;

	/**
	 * 商品别名
	 */
	@Column
	private String alias;

	/**
	 * 商品详情url
	 */
	@Column
	private String detail_url;

	/**
	 * 商品价格
	 */
	@Column
	private String price;

	/**
	 * 商品销量
	 */
	@Column
	private String sold_num;

	/**
	 * 上架状态
	 */
	@Column
	private String listing;


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
	
	public String getNum_iid() {
		return num_iid;
	}
	public void setNum_iid(String num_iid) {
		this.num_iid = num_iid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getDetail_url() {
		return detail_url;
	}
	public void setDetail_url(String detail_url) {
		this.detail_url = detail_url;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getSold_num() {
		return sold_num;
	}
	public void setSold_num(String sold_num) {
		this.sold_num = sold_num;
	}
	public String getListing() {
		return listing;
	}
	public void setListing(String listing) {
		this.listing = listing;
	}
}
