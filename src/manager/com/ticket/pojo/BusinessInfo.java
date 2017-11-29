package com.ticket.pojo;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 * 商家信息
 * @ClassName: BusinessInfo   
 * @Description: 商家信息表
 * @author HiSay  
 * @date  2015-11-16 15:35:54
 *
 */
@Entity
@Table(name="ticket_BusinessInfo",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class BusinessInfo implements Serializable {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */ 
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
	 * 商业类别id
	 */
	@Column
	private String businessType_id = null;

	/**
	 * 商家名称
	 */
	@Column
	private String name = null;

	/**
	 * 商家简介
	 */
	@Column
	private String introduce = null;

	/**
	 * 主营商品
	 */
	@Column
	private String mainSale = null;

	/**
	 * 营业时间
	 */
	@Column
	private String businessHours = null;

	/**
	 * 活动预告
	 */
	@Column
	private String activityForecast = null;

	/**
	 * 联系电话
	 */
	@Column
	private String phone = null;

	/**
	 * 联系地址
	 */
	@Column
	private String address = null;

	/**
	 * 经度
	 */
	@Column
	private Double longitude = null;

	/**
	 * 纬度
	 */
	@Column
	private Double latitude = null;
	/**
	 * 楼层
	 */
	@Column
	private String lc = null;
	/**
	 * 位置
	 */
	@Column
	private String wz = null;
	/**
	 * 分类
	 */
	@Column
	private String fl = null;

	/**
	 * 商家评分
	 */
	@Column 
	private Integer score;
	
	/**
	 * 人均价格
	 */
	@Column
	private Double averagePrice;
	
	/**
	 * 楼层号
	 */
	@Column
	private String floorNumber;
	
	/**
	 * 登机口
	 */
	@Column
	private String djk;
	
	/**
	 * 有赞销量
	 */
	@Column
	private int youZanSales = 0;

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
	
	public String getBusinessType_id() {
		return businessType_id;
	}
	public void setBusinessType_id(String businessType_id) {
		this.businessType_id = businessType_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getMainSale() {
		return mainSale;
	}
	public void setMainSale(String mainSale) {
		this.mainSale = mainSale;
	}
	public String getBusinessHours() {
		return businessHours;
	}
	public void setBusinessHours(String businessHours) {
		this.businessHours = businessHours;
	}
	public String getActivityForecast() {
		return activityForecast;
	}
	public void setActivityForecast(String activityForecast) {
		this.activityForecast = activityForecast;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public String getLc() {
		return lc;
	}
	public void setLc(String lc) {
		this.lc = lc;
	}
	public String getWz() {
		return wz;
	}
	public void setWz(String wz) {
		this.wz = wz;
	}
	public String getFl() {
		return fl;
	}
	public void setFl(String fl) {
		this.fl = fl;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Double getAveragePrice() {
		return averagePrice;
	}
	public void setAveragePrice(Double averagePrice) {
		this.averagePrice = averagePrice;
	}
	public String getFloorNumber() {
		return floorNumber;
	}
	public void setFloorNumber(String floorNumber) {
		this.floorNumber = floorNumber;
	}
	public String getDjk() {
		return djk;
	}
	public void setDjk(String djk) {
		this.djk = djk;
	}
	public int getYouZanSales() {
		return youZanSales;
	}
	public void setYouZanSales(int youZanSales) {
		this.youZanSales = youZanSales;
	}
}
