package com.ticket.enumer;

/**
 * @Description：预约类型
 * @author：涂有
 * @date 2015年11月24日 上午12:51:53
 */
public enum BjdjAppointmentType {

	online("线上"),  //线上
	offline("线下"); //线下
	
	/**
	 * 枚举字段的文字描述
	 */
	private String text;
	
	private BjdjAppointmentType(String text){
		
		this.text = text;
	}

	public String getValue() {
		return this.toString();
	}
	
	public String getText(){
		return text;
	}
}
