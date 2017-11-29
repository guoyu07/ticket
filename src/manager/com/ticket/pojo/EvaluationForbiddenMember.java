package com.ticket.pojo;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 评论禁止用户
 * @author tuyou
 *
 */
@Entity
@Table(name="ticket_evaluationForbiddenMember")
public class EvaluationForbiddenMember {

	@Id
	private String id = UUID.randomUUID().toString();
	
	/**
	 * 禁止的用户
	 */
	@OneToOne
	private Member member;
	
	public EvaluationForbiddenMember() {
		super();
	}

	public EvaluationForbiddenMember(Member member) {
		super();
		this.member = member;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
}
