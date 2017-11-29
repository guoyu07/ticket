package com.ticket.enumer;

/**
 * @Description：页面缓存生成周期范围
 * @author：涂有
 * @date 2015年12月23日 上午10:36:36
 */
public enum PageCacheScope {

	/**
	 * 整个应用程序
	 */
	application("整个应用程序"), 
	
	/**
	 * 整个会话
	 */
	session("整个会话");
	
	/**
	 * 枚举字段的文字描述
	 */
	private String text;
	
	private PageCacheScope(String text){
		
		this.text = text;
	}

	public String getValue() {
		return this.toString();
	}
	
	public String getText(){
		return text;
	}
}
