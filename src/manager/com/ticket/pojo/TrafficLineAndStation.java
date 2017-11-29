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
 * 路线与车站关联
 * @ClassName: TrafficLineAndStation   
 * @Description: 交通路线与车站的关联表
 * @author HiSay  
 * @date  2015-12-20 16:30:33
 *
 */
@Entity
@Table(name="ticket_TrafficLineAndStation",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class TrafficLineAndStation implements Serializable {

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
	 * 路线类别id
	 */
	@Column
	private String trafficType_id = null;
	
	/**
	 * 路线id
	 */
	@Column
	private String trafficLine_id = null;

	/**
	 * 车站id
	 */
	@Column
	private String trafficStation_id = null;

	/**
	 * 车站类型
	 */
	@Column
	private Integer stationType = null;


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
	
	public String getTrafficLine_id() {
		return trafficLine_id;
	}
	public void setTrafficLine_id(String trafficLine_id) {
		this.trafficLine_id = trafficLine_id;
	}
	public String getTrafficStation_id() {
		return trafficStation_id;
	}
	public void setTrafficStation_id(String trafficStation_id) {
		this.trafficStation_id = trafficStation_id;
	}
	public Integer getStationType() {
		return stationType;
	}
	public void setStationType(Integer stationType) {
		this.stationType = stationType;
	}
	public String getTrafficType_id() {
		return trafficType_id;
	}
	public void setTrafficType_id(String trafficTypeId) {
		trafficType_id = trafficTypeId;
	}
}
