package com.ticket.enumer;

/**
 * 搜索统计类型
 * @author 涂有
 */
public enum SearchType {

	location("精确定位"),
	
	normal("站内搜索"),

	business("商业搜索");
	
	/**
	 * 枚举字段的文字描述
	 */
	private String text;
	
	private SearchType(String text){
		
		this.text = text;
	}

	public String getValue() {
		return this.toString();
	}
	
	public String getText(){
		return text;
	}
}
