package com.ticket.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.UUID;
/**
 * 内部员工罚单
 * @ClassName: Infringement   
 * @Description: 内部员工罚单
 * @author HiSay  
 * @date  2016-03-04 19:58:20
 *
 */
@Entity
@Table(name="ticket_Infringement",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class Infringement implements Serializable {

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
	 * 时间
	 */
	@Column
	private String time = null;

	/**
	 * 检查人
	 */
	@Column
	private String inspectName = null;

	/**
	 * 单位名称
	 */
	@Column
	private String unitName = null;

	/**
	 * 违规事项
	 */
	@Column
	private String illegalMatter = null;

	/**
	 * 涉及规章制度
	 */
	@Column
	private String rules = null;

	/**
	 * 整改意见
	 */
	@Column
	private String rectificationOpinion = null;

	/**
	 * 现场照片
	 */
	@Column
	private String scenePhoto = null;

	/**
	 * 罚单编号
	 */
	@Column
	private String numberId = null;

	/**
	 * 违规人姓名
	 */
	@Column
	private String illegalName = null;

	/**
	 * 违规人证件号
	 */
	@Column
	private String illegalCard = null;


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
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getInspectName() {
		return inspectName;
	}
	public void setInspectName(String inspectName) {
		this.inspectName = inspectName;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getIllegalMatter() {
		return illegalMatter;
	}
	public void setIllegalMatter(String illegalMatter) {
		this.illegalMatter = illegalMatter;
	}
	public String getRules() {
		return rules;
	}
	public void setRules(String rules) {
		this.rules = rules;
	}
	public String getRectificationOpinion() {
		return rectificationOpinion;
	}
	public void setRectificationOpinion(String rectificationOpinion) {
		this.rectificationOpinion = rectificationOpinion;
	}
	public String getScenePhoto() {
		return scenePhoto;
	}
	public void setScenePhoto(String scenePhoto) {
		this.scenePhoto = scenePhoto;
	}
	public String getNumberId() {
		return numberId;
	}
	public void setNumberId(String numberId) {
		this.numberId = numberId;
	}
	public String getIllegalName() {
		return illegalName;
	}
	public void setIllegalName(String illegalName) {
		this.illegalName = illegalName;
	}
	public String getIllegalCard() {
		return illegalCard;
	}
	public void setIllegalCard(String illegalCard) {
		this.illegalCard = illegalCard;
	}
}
