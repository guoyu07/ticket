package com.ticket.pojo;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ticket.enumer.EvaluationReportProperty;
import com.ticket.enumer.EvaluationReportType;
import com.ticket.enumer.EvaluationReportUpdateFrequency;
/**
 * 评论报表任务
 * @ClassName: EvaluationReportTask   
 * @Description: 评论报表任务
 * @author HiSay  
 * @date  2016-02-04 21:40:28
 *
 */
@Entity
@Table(name="ticket_EvaluationReportTask",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class EvaluationReportTask implements Serializable {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */ 
	protected static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	@Id
	protected String id = UUID.randomUUID().toString();
	/**
	 * 实体状态
	 */
	@Embedded
	protected CommonEntity status = new CommonEntity();
	
	/**
	 * 是否启动
	 */
	protected boolean launch;
	
	/**
	 * 报表文件名
	 */
	@Column(length=255)
	protected String name = null;

	/**
	 * 报表性质
	 */
	@Enumerated(EnumType.STRING)
	protected EvaluationReportProperty property = null;

	/**
	 * 报表类型
	 */
	@Enumerated(EnumType.STRING)
	protected EvaluationReportType type = null;

	/**
	 * 更新频率
	 */
	@Enumerated(EnumType.STRING)
	protected EvaluationReportUpdateFrequency frequency = null;

	/**
	 * 邮箱
	 */
	@Column(length=255)
	protected String email = null;

	/**
	 * 所有的时间段（当是时段对比类型时，就有多个时间段）
	 */
	@OneToMany(mappedBy="task", fetch=FetchType.EAGER, cascade={CascadeType.REMOVE})
	@OrderBy("sort")
	protected List<EvaluationReportTimeSegment> times;

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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public EvaluationReportProperty getProperty() {
		return property;
	}
	public void setProperty(EvaluationReportProperty property) {
		this.property = property;
	}
	public EvaluationReportType getType() {
		return type;
	}
	public void setType(EvaluationReportType type) {
		this.type = type;
	}
	public EvaluationReportUpdateFrequency getFrequency() {
		return frequency;
	}
	public void setFrequency(EvaluationReportUpdateFrequency frequency) {
		this.frequency = frequency;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<EvaluationReportTimeSegment> getTimes() {
		return times;
	}
	public void setTimes(List<EvaluationReportTimeSegment> times) {
		this.times = times;
	}
	public boolean isLaunch() {
		return launch;
	}
	public void setLaunch(boolean launch) {
		this.launch = launch;
	}
}
