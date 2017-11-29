package com.ticket.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 * 遗失物品信息
 * @ClassName: LostGoodsInfo   
 * @Description: 遗失物品信息表
 * @author HiSay  
 * @date  2015-11-23 16:17:30
 *
 */
@Entity
@Table(name="ticket_LostGoodsInfo",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class LostGoodsInfo implements Serializable {

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
	 * 物品类型
	 */
	@Column
	private String type_id = null;

	/**
	 * 物品ID
	 */
	@Column
	private String goodsId = null;
	
	/**
	 * 物品编号
	 */
	@Column
	private String goodsNumber = null;

	/**
	 * 物品名称
	 */
	@Column
	private String name = null;
	
	/**
	 * 物品重量
	 */
	@Column
	private Double weight = null;

	/**
	 * 物品颜色
	 */
	@Column
	private String color = null;

	/**
	 * 其他信息一
	 */
	@Column
	private String otherInfoOne = null;

	/**
	 * 其他信息二
	 */
	@Column
	private String otherInfoTwo = null;

	/**
	 * 拾取者姓名
	 */
	@Column
	private String pickerName = null;

	/**
	 * 拾取者电话
	 */
	@Column
	private String pickerPhone = null;

	/**
	 * 拾取时间
	 */
	@Column
	private Date pickUpTime = null;

	/**
	 * 拾取位置
	 */
	@Column
	private String pickPosition_id = null;

	/**
	 * 物品描述
	 */
	@Column
	private String goodsDescript = null;

	/**
	 * 备注
	 */
	@Column
	private String remark = null;

	/**
	 * 接收人
	 */
	@Column
	private String operator_id = null;
	/**
	 * 上交时间
	 */
	@Column
	private String commitTime = null;
	/**
	 * 库存位置
	 */
	@Column
	private String stockPosition = null;


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
	
	public String getType_id() {
		return type_id;
	}
	public void setType_id(String type_id) {
		this.type_id = type_id;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getOtherInfoOne() {
		return otherInfoOne;
	}
	public void setOtherInfoOne(String otherInfoOne) {
		this.otherInfoOne = otherInfoOne;
	}
	public String getOtherInfoTwo() {
		return otherInfoTwo;
	}
	public void setOtherInfoTwo(String otherInfoTwo) {
		this.otherInfoTwo = otherInfoTwo;
	}
	public String getPickerName() {
		return pickerName;
	}
	public void setPickerName(String pickerName) {
		this.pickerName = pickerName;
	}
	public String getPickerPhone() {
		return pickerPhone;
	}
	public void setPickerPhone(String pickerPhone) {
		this.pickerPhone = pickerPhone;
	}
	public Date getPickUpTime() {
		return pickUpTime;
	}
	public void setPickUpTime(Date pickUpTime) {
		this.pickUpTime = pickUpTime;
	}
	public String getPickPosition_id() {
		return pickPosition_id;
	}
	public void setPickPosition_id(String pickPosition_id) {
		this.pickPosition_id = pickPosition_id;
	}
	public String getGoodsDescript() {
		return goodsDescript;
	}
	public void setGoodsDescript(String goodsDescript) {
		this.goodsDescript = goodsDescript;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOperator_id() {
		return operator_id;
	}
	public void setOperator_id(String operator_id) {
		this.operator_id = operator_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCommitTime() {
		return commitTime;
	}
	public void setCommitTime(String commitTime) {
		this.commitTime = commitTime;
	}
	public String getStockPosition() {
		return stockPosition;
	}
	public void setStockPosition(String stockPosition) {
		this.stockPosition = stockPosition;
	}
	public String getGoodsNumber() {
		return goodsNumber;
	}
	public void setGoodsNumber(String goodsNumber) {
		this.goodsNumber = goodsNumber;
	}
}
