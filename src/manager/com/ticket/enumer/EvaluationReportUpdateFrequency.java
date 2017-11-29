package com.ticket.enumer;

/**
 * 评价系统-报表任务更新频率
 * @author tuyou
 */
public enum EvaluationReportUpdateFrequency {

	/**
	 * 周期性
	 */
	interval("周期性"),
	
	/**
	 * 一次性
	 */
	once("一次性");
	
	/**
	 * 枚举字段的文字描述
	 */
	private String text;
	
	private EvaluationReportUpdateFrequency(String text){
		
		this.text = text;
	}

	public String getValue() {
		return this.toString();
	}
	
	public String getText(){
		return text;
	}
}
