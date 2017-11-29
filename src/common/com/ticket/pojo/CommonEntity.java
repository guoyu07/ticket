package com.ticket.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ticket.constants.ContextConstants;

/**
 * 实体公用
 * @ClassName: CommonEntity   
 * @Description: 所有实体的公用属性   
 * @author HiSay 
 * @date Jul 8, 2013 8:37:22 AM   
 *
 */
@Embeddable
public class CommonEntity extends BasePojo implements Serializable {

	private static final long serialVersionUID = 121348345793475L;
	/**
	 * 实体的URL属性
	 * 每个实体的记录都有一个URL字段，用户后期查询或者前台重写用
	 */
	@Column
	private Long visitUrl = System.currentTimeMillis();
	/**
	 * 实体的排序值
	 * 排序值越大排名越靠前,默认为0值
	 */
	@Column
	private Integer orderValue = ContextConstants.STATUS_OF_ZERO;
	
	/**
	 * 实体的点击率(浏览量)值, 默认为0值
	 */
	@Column
	private Integer hits = ContextConstants.STATUS_OF_ZERO;
	/**
	 * 实体的推荐属性
	 */
	@Column
	private Integer commend = ContextConstants.STATUS_OF_ZERO;
	/**
	 * 实体的审核属性
	 */
	@Column
	private Integer audit = ContextConstants.STATUS_OF_ZERO;
	/**
	 * 实体的热门属性
	 */
	@Column
	private Integer hot = ContextConstants.STATUS_OF_ZERO;
	/**
	 * 实体的创建时间属性
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime = new Date();
	/**
	 * 实体的删除状态
	 */
	@Column
	private Integer deleteFlag = ContextConstants.STATUS_OF_ZERO;
	/**
	 * 实体的语言版本状态
	 */
	@Column(length=20)
	private String versionFlag = ContextConstants.VERSION_FLAG_OF_CHINESE;
	
	public Integer getOrderValue() {
		return orderValue;
	}
	public void setOrderValue(Integer orderValue) {
		this.orderValue = orderValue;
	}
	public Integer getHits() {
		return hits;
	}
	public void setHits(Integer hits) {
		this.hits = hits;
	}
	public Integer getCommend() {
		return commend;
	}
	public void setCommend(Integer commend) {
		this.commend = commend;
	}
	public Integer getAudit() {
		return audit;
	}
	public void setAudit(Integer audit) {
		this.audit = audit;
	}
	public Integer getHot() {
		return hot;
	}
	public void setHot(Integer hot) {
		this.hot = hot;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public String getVersionFlag() {
		return versionFlag;
	}
	public void setVersionFlag(String versionFlag) {
		this.versionFlag = versionFlag;
	}
	public Long getVisitUrl() {
		return visitUrl;
	}
	public void setVisitUrl(Long visitUrl) {
		this.visitUrl = visitUrl;
	}
}
