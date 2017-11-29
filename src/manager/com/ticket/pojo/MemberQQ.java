package com.ticket.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 * QQ生成一个会员账号
 * @author xw
 *
 */
@Entity
@Table(name="ticket_MemberQQ",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class MemberQQ extends Member implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * 关联的QQ账号
	 */
	@ManyToOne
	private Member member = null;
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
}
