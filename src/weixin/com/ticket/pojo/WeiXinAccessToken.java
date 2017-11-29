package com.ticket.pojo;

/**
 * 微信的AccessToken
 * @author haishui
 *
 */
public class WeiXinAccessToken {
	/**
	 * accessToken值
	 */
	private String accessToken = null;
	/**
	 * 获取值
	 */
	private Long getTime = null;
	
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public Long getGetTime() {
		return getTime;
	}
	public void setGetTime(Long getTime) {
		this.getTime = getTime;
	}
}
