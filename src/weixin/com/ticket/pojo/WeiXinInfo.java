package com.ticket.pojo;

/**
 * 微信用户基本信息实体
 * @author haishui
 */
public class WeiXinInfo {
	/**
	 * 是否关注公众号
	 */
	private Integer subscribe = null;
	/**
	 * 用户原始ID（唯一）
	 */
	private String openid = null;
	/**
	 * 用户呢称
	 */
	private String nickname = null;
	/**
	 * 性别
	 */
	private Integer sex = null;
	/**
	 * 用户使用语言
	 */
	private String language = null;
	/**
	 * 城市
	 */
	private String city = null;
	/**
	 * 省份
	 */
	private String province = null;
	/**
	 * 国家
	 */
	private String country = null;
	/**
	 * 头像地址
	 */
	private String headimgurl = null;
	/**
	 * 关注公众号时间
	 */
	private Long subscribe_time = null;
	
	
	public Integer getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(Integer subscribe) {
		this.subscribe = subscribe;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public Long getSubscribe_time() {
		return subscribe_time;
	}
	public void setSubscribe_time(Long subscribeTime) {
		subscribe_time = subscribeTime;
	}
}
