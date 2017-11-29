package com.ticket.enumer;

/**
 * 评价系统-报表类型
 * @author tuyou
 */
public enum EvaluationReportType {

	/**
	 * 大类报表
	 */
	classes("大类报表"), 
	
	/**
	 * 项目报表
	 */
	project("项目报表"), 
	
	/**
	 * 指标报表
	 */
	target("指标报表"),  
	
	/**
	 * 汇总报表
	 */
	together("汇总报表");
	
	/**
	 * 枚举字段的文字描述
	 */
	private String text;
	
	private EvaluationReportType(String text){
		
		this.text = text;
	}

	public String getValue() {
		return this.toString();
	}
	
	public String getText(){
		return text;
	}
}
