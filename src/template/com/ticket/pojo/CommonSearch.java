package com.ticket.pojo;

import java.io.Serializable;
import java.util.Date;
/**
 * 公共搜索实体
 * @ClassName: SearchResult   
 * @Description: 搜索结果
 * @author HiSay  
 * @date  2015-12-14 19:42:22
 */
public class CommonSearch implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 信息标题
	 */
	private String title;
	
	/**
	 * 信息URL
	 */
	private String url;
	
	/**
	 * 信息URL
	 */
	private String pcUrl;
	
	/**
	 * 搜索关键词
	 */
	private String seo;
	
	/**
	 * 搜索否定词
	 */
	private String negative;
	
	/**
	 * 搜索精确否定词
	 */
	private String _negative;
	
	/**
	 * 信息描述
	 */
	private String descript;
	
	/**
	 * 信息发布时间
	 */
	private Date createTime;
	
	/**
	 * 排序值
	 */
	private int orderValue;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getSeo() {
		return seo;
	}
	public void setSeo(String seo) {
		this.seo = seo;
	}
	public String getPcUrl() {
		return pcUrl;
	}
	public void setPcUrl(String pcUrl) {
		this.pcUrl = pcUrl;
	}
	public String getNegative() {
		return negative;
	}
	public void setNegative(String negative) {
		this.negative = negative;
	}
	public int getOrderValue() {
		return orderValue;
	}
	public void setOrderValue(int orderValue) {
		this.orderValue = orderValue;
	}
	public String get_negative() {
		return _negative;
	}
	public void set_negative(String _negative) {
		this._negative = _negative;
	}
	
}
