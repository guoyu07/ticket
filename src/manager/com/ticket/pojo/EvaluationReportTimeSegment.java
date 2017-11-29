package com.ticket.pojo;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 评价系统-报表自动生成的时间段
 * 因为一个报表任务，对应多个时间段，所以把时间段单独成一张表
 * @author tuyou
 */
@Entity
@Table(name="ticket_EvaluationReportTimeSegment")
public class EvaluationReportTimeSegment {

	@Id
	protected String id = UUID.randomUUID().toString();
	
	/**
	 * 所属的报表任务
	 */
	@ManyToOne
	protected EvaluationReportTask task;
	
	/**
	 * 开始时间、结束时间
	 */
	protected Date startTime, endTime;
	
	/**
	 * 排序值
	 */
	protected Integer sort;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EvaluationReportTask getTask() {
		return task;
	}

	public void setTask(EvaluationReportTask task) {
		this.task = task;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
}
