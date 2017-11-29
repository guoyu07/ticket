package com.ticket.enumer;

/**
 * @Description：分单项状态
 * @author：涂有
 * @date 2015年11月26日 下午5:02:03
 */
public enum BjdjDispatchListState {

	wait("未开始,等待接单"),  //未开始,等待接单
	ing("进行中，已经接单"),  //进行中，已经接单
	ended("结束，已经核销");   //结束，已经核销
	
	/**
	 * 枚举字段的文字描述
	 */
	private String text;
	
	private BjdjDispatchListState(String text){
		
		this.text = text;
	}

	public String getValue() {
		return this.toString();
	}
	
	public String getText(){
		return text;
	}
}
