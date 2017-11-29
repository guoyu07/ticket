package com.ticket.enumer;

/**
 * 评价系统-报表性质
 * @author tuyou
 *
 */
public enum EvaluationReportProperty {

	/**
	 * 普通报表
	 */
	normal("普通报表"),
	
	/**
	 * 时段对比表
	 */
	compare("时段对比表");
	
	/**
	 * 枚举字段的文字描述
	 */
	private String text;
	
	private EvaluationReportProperty(String text){
		
		this.text = text;
	}

	public String getValue() {
		return this.toString();
	}
	
	public String getText(){
		return text;
	}
}
