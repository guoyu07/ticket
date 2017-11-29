package com.ticket.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 评价处理过程记录表 
 * @author tuyou
 */
@Entity
@Table(name="ticket_evaluationProcess")
public class EvaluationProcess implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	protected String id = UUID.randomUUID().toString();
	
	/**
	 * 属于哪个处理过程
	 */
	@ManyToOne
	protected Evaluation evaluation;
	
	/**
	 * 是否置顶
	 */
	protected boolean top;
	
	/**
	 * 是否关闭
	 */
	protected boolean close;
	
	/**
	 * 状态
	 */
	@ManyToOne
	protected SystemDictionary state;
	
	/**
	 * 处理人(部门人员)
	 */
	@ManyToOne
	protected AirportEmployee departmentAdmin;
	
	/**
	 * 处理结果、高级管理员回复信息
	 */
	protected String msg, feedbackMsg;
	
	/**
	 * 处理时间、高级管理员回复时间
	 */
	protected Date createTime = new Date(), processTime;
	
	/**
	 * 备注
	 */
	protected String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public AirportEmployee getDepartmentAdmin() {
		return departmentAdmin;
	}

	public void setDepartmentAdmin(AirportEmployee departmentAdmin) {
		this.departmentAdmin = departmentAdmin;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public boolean isTop() {
		return top;
	}

	public void setTop(boolean top) {
		this.top = top;
	}

	public boolean isClose() {
		return close;
	}

	public void setClose(boolean close) {
		this.close = close;
	}

	public String getFeedbackMsg() {
		return feedbackMsg;
	}

	public void setFeedbackMsg(String feedbackMsg) {
		this.feedbackMsg = feedbackMsg;
	}

	public Evaluation getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

	public SystemDictionary getState() {
		return state;
	}

	public void setState(SystemDictionary state) {
		this.state = state;
	}

	public Date getProcessTime() {
		return processTime;
	}

	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}
}
