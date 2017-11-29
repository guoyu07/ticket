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
 * ifi消息关联
 * @ClassName: WifiMessage   
 * @Description: ifi消息关联
 * @author HiSay  
 * @date  2016-08-09 10:51:02
 *
 */
@Entity
@Table(name="ticket_WifiAreaMessage",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class WifiAreaMessage implements Serializable {

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
	 * wifi
	 */
	@ManyToOne
	private WifiArea wifiArea;

	/**
	 * 消息
	 */
	@ManyToOne
	private MessageTemplate message;

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
	public MessageTemplate getMessage() {
		return message;
	}
	public void setMessage(MessageTemplate message) {
		this.message = message;
	}
	public WifiArea getWifiArea() {
		return wifiArea;
	}
	public void setWifiArea(WifiArea wifiArea) {
		this.wifiArea = wifiArea;
	}
}
