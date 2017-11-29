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
 * 机场信息
 * @ClassName: AirportInfo   
 * @Description: 机场信息表
 * @author HiSay  
 * @date  2016-04-01 16:37:25
 *
 */
@Entity
@Table(name="ticket_AirportInfo",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class AirportInfo implements Serializable {

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
	 * 机场名称
	 */
	@Column
	private String name = null;

	/**
	 * 英文名
	 */
	@Column
	private String englishName = null;

	/**
	 * 三字码
	 */
	@Column
	private String threeCode = null;

	/**
	 * 四字码
	 */
	@Column
	private String fourCode = null;

	/**
	 * 国家代码
	 */
	@Column
	private String countryCode = null;

	/**
	 * 地区标识
	 */
	@Column
	private String districtFlag = null;

	/**
	 * 字母搜索标识
	 */
	@Column
	private String searchFlag = null;


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
	public String getEnglishName() {
		return englishName;
	}
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	public String getThreeCode() {
		return threeCode;
	}
	public void setThreeCode(String threeCode) {
		this.threeCode = threeCode;
	}
	public String getFourCode() {
		return fourCode;
	}
	public void setFourCode(String fourCode) {
		this.fourCode = fourCode;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getDistrictFlag() {
		return districtFlag;
	}
	public void setDistrictFlag(String districtFlag) {
		this.districtFlag = districtFlag;
	}
	public String getSearchFlag() {
		return searchFlag;
	}
	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}
}
