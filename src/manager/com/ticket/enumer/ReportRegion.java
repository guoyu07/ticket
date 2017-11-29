package com.ticket.enumer;

/**
 * 便捷登机验证统计报表的时间范围
 * @author tuyou
 */
public enum ReportRegion {

	today("今天"),

	thisWeek("本周"),

	thisMonth("本月");
	
	/**
	 * 枚举字段的文字描述
	 */
	private String text;
	
	private ReportRegion(String text){
		
		this.text = text;
	}

	public String getValue() {
		return this.toString();
	}
	
	public String getText(){
		return text;
	}
}
