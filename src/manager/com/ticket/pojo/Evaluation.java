package com.ticket.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
/**
 * 评价实体类
 * @author tuyou
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Table(name="ticket_evaluation")
public class Evaluation implements Serializable {

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
	 * 评论用户
	 */
	@ManyToOne
	protected Member member;
	
	/**
	 * 评论状态
	 */
	@ManyToOne
	protected SystemDictionary state;
	
	/**
	 * 评价大类
	 */
	@ManyToOne
	protected EvaluationSetting classes;
	
	/**
	 * 评价项目
	 */
	@ManyToOne
	protected EvaluationSetting project;
	
	/**
	 * 评价指标
	 */
	@OneToMany(mappedBy="comment", cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	protected List<EvaluationItem> items;
	
	/**
	 * 评论内容
	 */
	@Column(length=1024)
	protected String content = null;
	
	/**
	 * 是否显示备注名
	 */
	protected boolean showName;
	
	/**
	 * 图片数组
	 */
	@Column(length=1024)
	protected String images = null;

	/**
	 * 评论时所在的IP地址
	 */
	@Column(length=255)
	protected String ip = null;

	/**
	 * 回复内容
	 */
	protected String feedback;
	
	/**
	 * 回复时间
	 */
	protected Date feedbackTime;
	
	/**
	 * 回复用户
	 */
	@ManyToOne
	protected SystemUser feedback_user;
	
	/**
	 * 针对此条评论的所有处理信息
	 */
	@OneToMany(mappedBy="evaluation", cascade={CascadeType.REMOVE})
	@OrderBy("createTime desc")
	protected List<EvaluationProcess> process;
	
	/**
	 * 发送人(高级管理员)
	 */
	@ManyToOne
	protected AirportEmployee advancedAdmin;
	
	/**
	 * 处理部门
	 */
	@OneToMany(mappedBy="evaluation")
	protected List<EvaluationDepartmentRalation> department;
	
	/**
	 * 涉及制度
	 */
	protected String involveSystem;
	
	/**
	 * 处理意见
	 */
	protected String msg;
	
	/**
	 * 运质意见
	 */
	protected String shipmentQualityOpinion;
	
	/**
	 * 备注
	 */
	protected String remark;
	
	/**
	 * 发送时间
	 */
	protected Date sendTime;
	
	/**
	 * 会员是否已看
	 */
	protected boolean rend;
	
	public Evaluation() {
		super();
	}
	public Evaluation(String id) {
		super();
		this.id = id;
	}
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public boolean isShowName() {
		return showName;
	}
	public void setShowName(boolean showName) {
		this.showName = showName;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public Date getFeedbackTime() {
		return feedbackTime;
	}
	public void setFeedbackTime(Date feedbackTime) {
		this.feedbackTime = feedbackTime;
	}
	public SystemUser getFeedback_user() {
		return feedback_user;
	}
	public void setFeedback_user(SystemUser feedback_user) {
		this.feedback_user = feedback_user;
	}
	public EvaluationSetting getProject() {
		return project;
	}
	public void setProject(EvaluationSetting project) {
		this.project = project;
	}
	public EvaluationSetting getClasses() {
		return classes;
	}
	public void setClasses(EvaluationSetting classes) {
		this.classes = classes;
	}
	public SystemDictionary getState() {
		return state;
	}
	public void setState(SystemDictionary state) {
		this.state = state;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public List<EvaluationProcess> getProcess() {
		return process;
	}
	public void setProcess(List<EvaluationProcess> process) {
		this.process = process;
	}
	public AirportEmployee getAdvancedAdmin() {
		return advancedAdmin;
	}
	public void setAdvancedAdmin(AirportEmployee advancedAdmin) {
		this.advancedAdmin = advancedAdmin;
	}
	public List<EvaluationDepartmentRalation> getDepartment() {
		return department;
	}
	public void setDepartment(List<EvaluationDepartmentRalation> department) {
		this.department = department;
	}
	public String getInvolveSystem() {
		return involveSystem;
	}
	public void setInvolveSystem(String involveSystem) {
		this.involveSystem = involveSystem;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public String getShipmentQualityOpinion() {
		return shipmentQualityOpinion;
	}
	public void setShipmentQualityOpinion(String shipmentQualityOpinion) {
		this.shipmentQualityOpinion = shipmentQualityOpinion;
	}
	public List<EvaluationItem> getItems() {
		return items;
	}
	public void setItems(List<EvaluationItem> items) {
		this.items = items;
	}
	public boolean isRend() {
		return rend;
	}
	public void setRend(boolean rend) {
		this.rend = rend;
	}
}
