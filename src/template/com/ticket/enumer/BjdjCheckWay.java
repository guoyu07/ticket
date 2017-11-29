package com.ticket.enumer;

/**
 * @Description：核销方式
 * @author：涂有
 * @date 2015年11月26日 上午10:23:34
 */
public enum BjdjCheckWay {

	system("系统核销"), //系统核销
	artificial("人工核销"); //人工
	
	/**
	 * 枚举字段的文字描述
	 */
	private String text;
	
	private BjdjCheckWay(String text){
		
		this.text = text;
	}

	public String getValue() {
		return this.toString();
	}
	
	public String getText(){
		return text;
	}
}
