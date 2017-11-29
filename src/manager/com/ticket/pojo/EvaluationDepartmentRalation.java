package com.ticket.pojo;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 评论发送到部门的关联表
 * @author tuyou
 */
@Entity
@Table(name="ticket_evaluation_department")
public class EvaluationDepartmentRalation {

	@Id
	protected String id = UUID.randomUUID().toString();
	
	/**
	 * 所属的评论
	 */
	@ManyToOne
	protected Evaluation evaluation;
	
	/**
	 * 所属的部门
	 */
	@ManyToOne
	protected AirportDepartment department;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Evaluation getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

	public AirportDepartment getDepartment() {
		return department;
	}

	public void setDepartment(AirportDepartment department) {
		this.department = department;
	}
}
