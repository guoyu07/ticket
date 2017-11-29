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
 * 渠道类型服务大厅关联关系
 * @ClassName: ChannelHallRelation   
 * @Description: hanelHallRelation
 * @author tuyou  
 * @date  2016-03-18 16:22:12
 *
 */
@Entity
@Table(name="ticket_ChannelHallRelation",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class ChannelHallRelation implements Serializable {

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
	 * 大厅
	 */
	@ManyToOne
	private BjdjHall hall = null;

	/**
	 * 渠道类型
	 */
	@ManyToOne
	private SystemDictionary channel = null;


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
	
	public BjdjHall getHall() {
		return hall;
	}
	public void setHall(BjdjHall hall) {
		this.hall = hall;
	}
	public SystemDictionary getChannel() {
		return channel;
	}
	public void setChannel(SystemDictionary channel) {
		this.channel = channel;
	}
}
