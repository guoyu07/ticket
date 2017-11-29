package com.ticket.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
@Entity
@Table(name="ticket_MemberWeixin",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class MemberWeixin extends Member implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 关联的会员账号
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
