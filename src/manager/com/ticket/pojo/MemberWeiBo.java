package com.ticket.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 * 微博登录生成一个本网站的
 * @author Administrator
 *
 */
@Entity
@Table(name="ticket_MemberWeiBo",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class MemberWeiBo extends Member implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * 关联的会员
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
