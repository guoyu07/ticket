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
 * 会员设置快捷菜单
 * @ClassName: QuickMenuMemberSetting   
 * @Description: 会员设置快捷菜单表
 * @author HiSay  
 * @date  2015-10-31 13:04:17
 *
 */
@Entity
@Table(name="ticket_QuickMenuMemberSetting",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class QuickMenuMemberSetting implements Serializable {

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
	 * 会员id
	 */
	@Column
	private String member_id = null;

	/**
	 * 快捷菜单id
	 */
	@Column
	private String quickMenu_id = null;
	
	/**
	 * 快捷菜单显示的位置（出发中转到达）
	 */
	@Column
	private String defaultShowPosition = null;
	
	/**
	 * 航班快捷菜单
	 */
	@Column
	private String flightQuickMenu = null;


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
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getQuickMenu_id() {
		return quickMenu_id;
	}
	public void setQuickMenu_id(String quickMenu_id) {
		this.quickMenu_id = quickMenu_id;
	}
	public String getDefaultShowPosition() {
		return defaultShowPosition;
	}
	public void setDefaultShowPosition(String defaultShowPosition) {
		this.defaultShowPosition = defaultShowPosition;
	}
	public String getFlightQuickMenu() {
		return flightQuickMenu;
	}
	public void setFlightQuickMenu(String flightQuickMenu) {
		this.flightQuickMenu = flightQuickMenu;
	}
}
